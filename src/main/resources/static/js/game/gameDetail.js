layui.use(['form', 'layer', 'upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload;

    var gameId = $('#id').val();// 游戏ID
    var editType = $('#editType').val();// 编辑类型

    /**
     * 关闭页面
     * @param isRefresh
     */
    var closePage = function (isRefresh) {
        // 当你在frame页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        if (index) {
            parent.layer.close(index);
            if (isRefresh === true) {
                // 刷新父页面列表 TODO
            }
        } else {
            window.location.reload();
        }
    };

    /**
     * 动态增加输入框
     */
    var addTemplate = {
        // 设计师
        'designers': function (data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="designers" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#designersDiv').append(html);
        },
        // 美术
        'artists': function (data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="artists" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#artistsDiv').append(html);
        },
        // 出版商
        'publishers': function (data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="publishers" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#publishersDiv').append(html);
        },
        // 类别
        'categories': function (data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="categories" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#categoriesDiv').append(html);
        },
        // 机制
        'mechanisms': function (data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="mechanisms" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#mechanismsDiv').append(html);
        }
    };

    /**
     * 初始化桌游数据
     */
    var initData = function (gameId) {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/getDetails',
            'async': true,
            'data': {
                id: gameId
            },
            'dataType': 'json',
            'success': function (result) {
                if (result.code === 0) {
                    var data = result.data;
                    //表单初始赋值
                    form.val('gameInfoFm', {
                        "gameName": data.gameName,
                        "gameEnName": data.gameEnName,
                        "status": data.status,
                        "isEntity": data.isEntity,
                        "isDlc": data.isDlc,
                        "weight": data.weight,
                        "publishYear": data.publishYear,
                        'duration': data.duration,
                        'languageDependence': data.languageDependence,
                        "playerNumMin": data.playerNumMin,
                        "playerNumMax": data.playerNumMax,
                        "playerNumSuggested": data.playerNumSuggested,
                        'language': data.language,
                        "age": data.age,
                        "hasChinese": data.hasChinese,
                        "rating": data.rating,
                        "bggRank": data.bggRank,
                        "bggScore": data.bggScore,
                        "bggLink": data.bggLink,
                        "gameIntroduction": data.gameIntroduction,
                        'gameEnIntroduction': data.gameIntroduction
                    });
                    fillArrayField(data.types, 'types', 'checkbox');
                    fillArrayField(data.labels, 'labels', 'checkbox');
                    fillArrayField(data.designers, 'designers');
                    fillArrayField(data.artists, 'artists');
                    fillArrayField(data.publishers, 'publishers');
                    fillArrayField(data.mechanisms, 'mechanisms');
                    fillArrayField(data.categories, 'categories');
                    for (var i = 0; i < data.fileInfos.length; i++) {
                        fillFileField(data.fileInfos[i]);
                    }
                    // TODO
                    // 更新渲染
                    form.render();
                } else {
                    layer.alert(result.message);
                }
            },
            'error': function () {
                layer.alert('操作异常！');
            }
        });
    };

    /**
     * 保存桌游数据
     */
    var saveData = function (gameInfoVo) {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/save',
            'async': true,
            'data': {
                editType: editType,
                gameInfo: JSON.stringify(gameInfoVo)
            },
            'dataType': 'json',
            'success': function (result) {
                if (result.code === 0) {
                    layer.alert('保存成功！', function () {
                        closePage(true);
                    })
                } else {
                    layer.alert(result.message);
                }
            },
            'error': function () {
                layer.alert('操作异常！');
            }
        });
    };

    /**
     * 组织表单对象
     * @param form
     * @returns {object}
     */
    function buildGameInfoVo(form) {
        var gameInfoVo = {};
        var obj = $(form).serializeArray();
        $.each(obj, function () {
            if (gameInfoVo[this.name] !== undefined) {
                // if (!gameInfoVo[this.name].push) {
                //     gameInfoVo[this.name] = [gameInfoVo[this.name]];
                // }
                // gameInfoVo[this.name].push(this.value || '');
                // TODO
                gameInfoVo[this.name] += ',' + (this.value || '');
            } else {
                gameInfoVo[this.name] = this.value || '';
            }
        });
        // 组织附件
        gameInfoVo.fileInfos = [];
        $(form).find('input[name=fileKey]').each(function () {
            if ($(this).val() && $(this).val() !== '') {
                var fileInfo = {};
                fileInfo.fileName = $(this).data('fileName');
                fileInfo.fileAddress = $(this).val();
                fileInfo.type = $(this).data('type');
                fileInfo.fileType = $(this).data('fileType');
                gameInfoVo.fileInfos.push(fileInfo);
            }
        });
        delete gameInfoVo.fileKey;// 删除无用属性
        return gameInfoVo;
    }

    /**
     * 填充数组字段
     * @param fieldName
     * @param data
     * @param fieldType
     */
    function fillArrayField(data, fieldName, fieldType) {
        if (!data) {
            return;
        } else if (!data instanceof Array) {
            data = data.split(',');
        }
        if (!fieldType) {
            fieldType = 'input';
        }
        for (var i = 0; i < data.length; i++) {
            if (fieldType === 'radio' || fieldType === 'checkbox') {
                $('form').find('*[name=' + fieldName + '][value=' + data[i] + ']').prop('checked', true);
            } else {
                var field = $('form').find('*[name=' + fieldName + ']:eq(' + i + ')');
                if (field.length > 0) {
                    field.val(data[i]);
                } else {
                    var value = {
                        value: data[i]
                    };
                    addTemplate[fieldName] ? addTemplate[fieldName].call(this, value) : '';
                }
            }
        }
    }

    /**
     * 填充附件字段
     * @param data
     */
    function fillFileField(data) {
        if (!data) {
            return;
        }
        var listId;
        switch (parseInt(data.fileType)) {
            case 1:
                listId = '#imageDiv' + data.type;
                break;
            case 2:
                listId = '#videoDiv' + data.type;
                break;
            case 3:
                listId = '#fileDiv' + data.type;
                break;
            default:
                return;
        }
        var maxSize = $(listId).data('maxsize');
        if (maxSize > 1) {
            // 追加上传预览框
            $(listId).append(newFileItem(data.fileType));
        }
        var item = $(listId).find('.upload-item:last');
        $(item).find('.upload-img').attr('src', ROOT_CONTEXT + 'file/preview?fileKey=' + data.fileAddress);
        $(item).find('.upload-filename').text(data.fileName);
        $(item).find('.upload-info').text('上传成功');
        $(item).find('.upload-del').show();
        $(item).find('input[name=fileKey]').data('fileName', data.fileName).data('type', data.type).data('fileType', data.fileType).val(data.fileAddress);
    }

    /**
     * 增加上传item
     * @param fileType
     * @returns {string}
     */
    function newFileItem(fileType) {
        switch (parseInt(fileType)) {
            case 1:// 图片
                return '<div class="upload-item">' +
                    '<img class="upload-img">' +
                    '<p>' +
                    '<span class="upload-info">请上传</span>' +
                    '<input type="hidden" name="fileKey" value="">' +
                    '<a href="javascript:;" class="upload-del" style="display: none;">删除</a>' +
                    '</p>' +
                    '</div>';
            case 2:
                return '';// TODO
            case 3:// 文档
                return '<div class="upload-item">' +
                    '<span class="upload-filename"></span>' +
                    '<p>' +
                    '<span class="upload-info">请上传</span>' +
                    '<input type="hidden" name="fileKey" value="">' +
                    '<a href="javascript:void(0);" class="upload-del" style="display: none;">删除</a>' +
                    '</p>' +
                    '</div>';
            default:
                return '';
        }
    }

    /**
     * 附件上传回调方法
     */
    var uploaderChoose = function (obj) {
        var listId = this.target;// 上传结果列表ID
        var maxSize = $(listId).data('maxsize');// 最大数量
        var fileType = $(listId).data('filetype');// 附件类型
        if (maxSize > 1) {
            // 校验最大上传数 TODO 无效
            if ($(listId).find('.upload-item').length >= maxSize) {
                layer.alert('最多上传' + maxSize + '个附件！');
                return false;
            } else {
                // 追加上传预览框
                $(listId).append(newFileItem(fileType));
            }
        }
    }, uploaderBefore = function (obj) {
        var listId = this.target;// 上传结果列表ID
        var type = $(listId).data('type');// 类型
        var fileType = $(listId).data('filetype');// 附件类型
        obj.preview(function (index, file, result) {
            var item = $(listId).find('.upload-item:last');
            $(item).find('.upload-img').attr('src', result);
            $(item).find('.upload-filename').text(file.name);
            $(item).find('.upload-info').text('上传中..');
            $(item).find('input[name=fileKey]').data('fileName', file.name).data('type', type).data('fileType', fileType).val('');
        });
    }, uploaderDone = function (res, index, upload) {
        var listId = this.target;// 上传结果列表ID
        var item = $(listId).find('.upload-item:last');
        if (res && res.code === 0) {
            $(item).find('.upload-info').text('上传成功');
            $(item).find('input[name=fileKey]').val(res.data[0]);
        } else {
            $(item).find('.upload-info').text('上传失败').css('color', 'red');
        }
        $(item).find('.upload-del').show();
    }, uploaderError = function (index, upload) {
        var listId = this.target;// 上传结果列表ID
        var item = $(listId).find('.upload-item:last');
        $(item).find('.upload-info').text('上传异常').css('color', 'red');
        $(item).find('.upload-del').show();
    };

    // 根据编辑类型初始化页面
    switch (editType) {
        case 'new':
            break;
        case 'edit':
            initData(gameId);
            break;
        case 'view':
            initData(gameId);
            break;
        default:
            break;
    }

    // 图片上传按钮
    var imageUploader = upload.render({
        elem: '.upload-image-btn',
        url: ROOT_CONTEXT + 'file/upload',
        accept: 'images',
        acceptMime: 'image/png',// 格式PNG
        size: 500,// 最大500KB
        multiple: false,
        choose: uploaderChoose,
        before: uploaderBefore,
        done: uploaderDone,
        error: uploaderError
    });

    // 文档上传按钮
    var docUploader = upload.render({
        elem: '.upload-doc-btn',
        url: ROOT_CONTEXT + 'file/upload',
        accept: 'file',
        acceptMime: 'application/pdf',// 格式PDF
        size: 10240,// 最大10MB
        multiple: false,
        choose: uploaderChoose,
        before: uploaderBefore,
        done: uploaderDone,
        error: uploaderError
    });

    // 监听事件
    $('form').on('click', '.add-tpl', function () {
        var tplName = $(this).data('tpl');
        addTemplate[tplName] ? addTemplate[tplName].call(this, {value: ''}) : '';
    }).on('click', '.del-tpl', function () {
        $(this).parent('.tpl-item').remove();
    }).on('click', '.upload-del', function () {
        var list = $(this).parents('.layui-upload-list');
        $(this).parents('.upload-item').remove();
        var maxSize = $(list).data('maxsize');
        var fileType = $(list).data('filetype');
        if (maxSize <= 1) {
            $(list).append(newFileItem(fileType));
        }
    });

    // 监听提交
    form.on('submit(save)', function (data) {
        // saveData(buildGameInfoVo(data.form));
        console.log(JSON.stringify(buildGameInfoVo(data.form)));
        return false;
    });
    // 监听关闭
    form.on('submit(cancel)', function () {
        closePage(false);
        return false;
    });
});
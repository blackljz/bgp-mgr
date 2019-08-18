layui.use(['form', 'layer', 'upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload;

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
    function saveData(gameInfoVo) {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/save',
            'async': true,
            'data': {
                editType: $('#editType').val(),
                gameInfo: JSON.stringify(gameInfoVo)
            },
            'dataType': 'json',
            'success': function (result) {
                if (result.code === 0) {
                    layer.alert('保存成功！', function () {
                        // 当你在frame页面关闭自身时
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    })
                } else {
                    layer.alert(result.message);
                }
            },
            'error': function () {
                layer.alert('操作异常！');
            }
        });
    }

    /**
     * 组织表单对象
     * @param form
     * @returns {string}
     */
    function buildGameInfoVo(form) {
        var gameInfoVo = $(form).serializeObject();
        // 组织附件
        gameInfoVo.fileInfos = [];
        $(form).find('input[name=fileKey]').each(function () {
            var fileInfo = {};
            fileInfo.fileName = $(this).data('fileName');
            fileInfo.fileAddress = $(this).val();
            fileInfo.type = $(this).data('type');
            fileInfo.fileType = $(this).data('fileType');
            gameInfoVo.fileInfos.push(fileInfo);
        });
        return JSON.stringify(gameInfoVo);
    }

    /**
     * 填充数组字段
     * @param fieldName
     * @param data
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

    // 根据编辑类型初始化页面
    var gameId = $('#id').val();
    var editType = $('#editType').val();
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

    //多图片上传
    // upload.render({
    //     elem: '#test2'
    //     , url: '/upload/'
    //     , multiple: true
    //     , before: function (obj) {
    //         //预读本地文件示例，不支持ie8
    //         obj.preview(function (index, file, result) {
    //             $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
    //         });
    //     }
    //     , done: function (res) {
    //         //上传完毕
    //     }
    // });

    // 图片上传按钮
    var imageUploader = upload.render({
        elem: '.upload-btn',
        url: ROOT_CONTEXT + 'file/upload',
        data: {
            type: this.type
        },
        accept: 'images',
        acceptMime: 'image/png',
        multiple: false,
        choose: function (obj) {
            var listId = this.listId;
            var maxSize = this.maxSize;
            if (maxSize > 1 && $(listId).find('.upload-item').length >= maxSize) {
                layer.alert('最多上传' + maxSize + '个附件！');
                return false;
            }
        },
        before: function (obj) {
            var type = this.type;// 类型
            var listId = this.listId;// 上传结果列表ID
            var maxSize = this.maxSize;// 最大数量
            obj.preview(function (index, file, result) {
                var item;
                if (maxSize === 1) {
                    item = $(listId).find('.upload-item:last');
                } else {
                    item = '<div class="upload-item">' +
                        '<img class="upload-img">' +
                        '<p>' +
                        '<span class="upload-info">请上传</span>' +
                        '<input type="hidden" name="fileKey" value="">' +
                        '<a href="javascript:;" class="upload-del" style="display: none;">删除</a>' +
                        '</p>' +
                        '</div>';
                    $(listId).append(item);
                }
                $(item).find('.upload-img').attr('src', result);
                $(item).find('.upload-info').text('上传中..');
                $(item).find('input[name=fileKey]').data('', file.name).data('type', type).data('fileType', '1');
            });
        },
        done: function (res, index, upload) {
            var listId = this.listId;// 上传结果列表ID
            if (res && res.code === 0) {
                var item = $(listId).find('.upload-item:last');
                $(item).find('.upload-info').text('上传成功');
                $(item).find('.fileKey').val(res.data[0]);
            } else {
                $(listId).find('.upload-info:last').text('上传失败').css('color', 'red');
            }
            $(listId).find('.upload-del:last').show();
        },
        error: function (index, upload) {
            var listId = this.listId;// 上传结果列表ID
            $(listId).find('.upload-info:last').text('上传异常').css('color', 'red');
            $(listId).find('.upload-del:last').show();
        }
    });

    // 监听提交
    form.on('submit(save)', function (data) {
        var vo = buildGameInfoVo(data.form);
        saveData(vo);
        return false;
    });
    // 监听关闭
    form.on('submit(cancel)', function () {
        // 当你在frame页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        return false;
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
        if ($(list).find('.upload-item').length === 0) {
            $(list).append('<div class="upload-item">' +
                '<img class="upload-img">' +
                '<p>' +
                '<span class="upload-info">请上传</span>' +
                '<a href="javascript:;" class="upload-del" style="display: none;">删除</a>' +
                '</p>' +
                '</div>');
        }
    });

    /**
     * 表单序列化
     */
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

});
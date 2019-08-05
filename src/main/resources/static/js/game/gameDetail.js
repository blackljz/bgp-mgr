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
    function saveData(form) {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/save',
            'async': true,
            'data': {
                editType: $('#editType').val(),
                gameInfo: JSON.stringify($(form).serializeObject())
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

    // 监听提交
    form.on('submit(save)', function (data) {
        saveData(data.form);
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
        var defaultValue = {
            value: ''
        };
        addTemplate[tplName] ? addTemplate[tplName].call(this, defaultValue) : '';
    }).on('click', '.del-tpl', function () {
        $(this).parent('.tpl-item').remove();
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
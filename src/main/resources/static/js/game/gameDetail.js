layui.use(['form', 'layer', 'upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload;

    //根据编辑类型初始化页面
    init($('#editType').val());

    //监听提交
    form.on('submit(save)', function (data) {
        saveData(data.form);
        return false;
    });
    //监听关闭
    form.on('submit(cancel)', function () {
        close();
        return false;
    });

    $('form').on('click', '.add-tpl', function () {
        var tplName = $(this).data('tpl');
        var defaultData = {
            value: ''
        };
        addTemplate[tplName] ? addTemplate[tplName].call(this, defaultData) : '';
    }).on('click', '.del-tpl', function () {
        $(this).parent('.tpl-item').remove();
    });

    /**
     * 初始化页面
     */
    function init(editType) {
        switch (editType) {
            case 'new':
                break;
            case 'edit':
                initData();
                break;
            case 'view':
                initData();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化桌游数据
     */
    function initData() {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/getDetails',
            'async': true,
            'data': {
                id: $('#id').val()
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
                        "type": data.type,
                        "label": data.label,
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
                        'designer': data.designer,
                        'artist': data.artist,
                        'publisher': data.publisher,
                        "rating": data.rating,
                        "bggRank": data.bggRank,
                        "bggScore": data.bggScore,
                        "bggLink": data.bggLink,
                        "category": data.category,
                        "mechanism": data.mechanism,
                        "gameIntroduction": data.gameIntroduction,
                        'gameEnIntroduction': data.gameIntroduction
                    });
                    // TODO
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
                        close();
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
     * 关闭页面
     */
    function close() {
        //当你在frame页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

    /**
     * 动态增加输入框
     */
    var addTemplate = {
        // 设计师
        'designer': function(data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="designers" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#designerDiv').append(html);
        },
        // 美术
        'artist': function(data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="artists" lay-verify="required" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<i class="layui-icon layui-icon-close layui-table-tips-c del-tpl"></i>' +
                '</div>';
            $('#artistDiv').append(html);
        },
        // 出版商
        'publisher': function(data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="publishers" lay-verify="" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<button type="button" class="ayui-btn del-self">&times;</button>' +
                '</div>';
            $('#publisherDiv').append(html);
        },
        // 类别
        'category': function(data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="categorys" lay-verify="" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<button type="button" class="ayui-btn del-self">&times;</button>' +
                '</div>';
            $('#categoryDiv').append(html);
        },
        // 机制
        'mechanism': function(data) {
            var html = '<div class="layui-input-inline tpl-item">' +
                '<input type="text" name="mechanisms" lay-verify="" autocomplete="off" class="layui-input" value="' + data['value'] + '"/>' +
                '<button type="button" class="ayui-btn del-self">&times;</button>' +
                '</div>';
            $('#mechanismDiv').append(html);
        }
        // 'relatedGameId' : function (data) {
        //     var html = '<div class="layui-input-inline">' +
        //         '<input type="text" name="relatedGameId" lay-verify="" autocomplete="off" class="layui-input" value="' + data.value + '"/>' +
        //         '</div>';
        //     $('#div_relatedGameId').append(html);
        // }
    };

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
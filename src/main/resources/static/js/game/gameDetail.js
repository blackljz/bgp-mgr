layui.use(['form', 'layer', 'upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload;

    //根据编辑类型初始化页面
    var editType = $('#editType').val();
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

    //监听提交
    form.on('submit(save)', function (data) {
        $.ajax({
            'type': 'post',
            'url': ROOT_CONTEXT + 'game/save',
            'async': true,
            'data': {
                editType: editType,
                gameInfo: JSON.stringify(data.field)
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
        return false;
    });
    form.on('submit(cancel)', function () {
        close();
        return false;
    });

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
                        "severe": data.severe,
                        'designer': data.designer,
                        'languageDependence': data.languageDependence,
                        'duration': data.duration,
                        'artist': data.artist,
                        'language': data.language,
                        'peopleNumber': data.peopleNumber,
                        'publishingAge': data.publishingAge,
                        'publisher': data.publisher,
                        'chinesePublisher': data.chinesePublisher,
                        'gameIntroduction': data.gameIntroduction
                    });
                } else {
                    layer.alert(result.message);
                }
            },
            'error': function () {
                layer.alert('操作异常！');
            }
        });
    }

    function close() {
        //当你在frame页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

});
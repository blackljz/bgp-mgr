layui.use(['form', 'layer'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer;

    //自定义验证规则
    form.verify({
        'newPwd': [
            /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
        ],
        'confirmPwd': function (value) {
            if (!new RegExp($('#newPwd').val()).test(value)) {
                return '两次输入密码不一致，请重新输入！';
            }
        }
    });

    //监听提交
    form.on('submit(*)', function (data) {
        var maskLayer = layer.load();
        //ajax 提交
        $.post(ROOT_CONTEXT + 'sys/changePwd', data.field, function (data) {
            if (data.code === 0) {
                layer.alert('修改密码成功！', function () {
                    //跳回首页
                    location.href = ROOT_CONTEXT;
                });
            } else {
                layer.alert('修改密码失败：' + data.message);
            }
            layer.close(maskLayer);
        }, 'json');
        return false;
    });

});
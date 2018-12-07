layui.use(['form'], function () {
    var form = layui.form;

    function close() {
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
});
layui.use(['form', 'laydate'], function () {
    var form = layui.form
        , laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#registDateStart'
    });
    laydate.render({
        elem: '#registDateEnd'
    });
});
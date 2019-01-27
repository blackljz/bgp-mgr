layui.use(['form', 'laydate', 'table', 'layer'], function () {
    var $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer;

    //日期
    laydate.render({
        elem: '#registDateStart'
    });
    laydate.render({
        elem: '#registDateEnd'
    });

    var tableIns = table.render({
        elem: '#dataTable',
        url: ROOT_CONTEXT + 'user/queryData',
        method: 'post',
        where: {
            userName: $('#userName').val(),
            userSex: $('#userSex').val(),
            userSource: $('#userSource').val(),
            registDateStart: $('#registDateStart').val(),
            registDateEnd: $('#registDateEnd').val()
        },
        page: true,
        limit: 10,
        limits: [10, 25, 50],
        cols: [[
            {field: 'id', title: '用户编号', fixed: 'left'},
            {field: 'name', title: '用户名称'},
            {field: 'registDate', title: '注册时间'},
            {field: 'source', title: '来源'},
            {field: 'sex', title: '性别'},
            {field: 'circleCount', title: '圈子数量'},
            {field: 'assessCount', title: '评价数量'},
            {field: 'gameCount', title: '游戏数量'},
            {field: 'friendCount', title: '好友数量'},
            {field: 'lastLoginDate', title: '最后登录时间'},
            {title: '操作', toolbar: '#operateBtnTemp'}
        ]]
    });

    //监听行工具事件
    table.on('tool(dataTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'view') {
            showFrame('用户详情', ROOT_CONTEXT + 'user/view/' + data.id);
        }
    });

    //弹出frame
    function showFrame(title, url) {
        if (!url)
            return false;
        layer.open({
            type: 2,
            title: title ? title : '信息',
            area: ['1140px', '600px'],
            content: url
        });
    }

    //按钮事件
    $('#queryBtn').on('click', function () {
        tableIns.reload({
            where: {
                userName: $('#userName').val(),
                userSex: $('#userSex').val(),
                userSource: $('#userSource').val(),
                registDateStart: $('#registDateStart').val(),
                registDateEnd: $('#registDateEnd').val()
            }
        });
    });
});
layui.use(['form', 'laydate', 'table', 'layer', 'util'], function () {
    var $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        util = layui.util;

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
            {
                field: 'userId',
                title: '用户编号',
                fixed: 'left'
            },
            {
                field: 'userName',
                title: '用户名称',
                templet: function (d) {
                    return d.userName !== undefined && d.userName != null ? d.userName : '--';
                }
            },
            {
                field: 'createDate',
                title: '注册时间',
                templet: function (d) {
                    return d.createDate !== undefined && d.createDate != null ? util.toDateString(d.createDate) : '--';
                }
            },
            {
                field: 'source',
                title: '来源',
                templet: function (d) {
                    return d.source !== undefined && d.source != null ? d.source : '--';
                }
            },
            {
                field: 'sex',
                title: '性别',
                templet: function (d) {
                    return d.sex !== undefined && d.sex != null ? d.sex : '--';
                }
            },
            {
                field: 'circleCount',
                title: '圈子数量',
                templet: function (d) {
                    return d.circleCount >= 0 ? d.circleCount : '--';
                }
            },
            {
                field: 'assessCount',
                title: '评价数量',
                templet: function (d) {
                    return d.assessCount >= 0 ? d.assessCount : '--';
                }
            },
            {
                field: 'gameCount',
                title: '游戏数量',
                templet: function (d) {
                    return d.gameCount >= 0 ? d.gameCount : '--';
                }
            },
            {
                field: 'friendCount',
                title: '好友数量',
                templet: function (d) {
                    return d.friendCount >= 0 ? d.friendCount : '--';
                }
            },
            {
                field: 'lastLoginDate',
                title: '最后登录时间',
                templet: function (d) {
                    return d.lastLoginDate !== undefined && d.lastLoginDate != null ? util.toDateString(d.lastLoginDate) : '--';
                }
            },
            {
                title: '操作',
                toolbar: '#operateBtnTemp'
            }
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
        if (!url) {
            return false;
        }
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
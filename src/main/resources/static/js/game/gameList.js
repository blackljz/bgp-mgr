layui.use(['form', 'table', 'layer'], function () {
    var $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;

    var tableIns = table.render({
        elem: '#dataTable',
        url: ROOT_CONTEXT + 'game/queryData',
        method: 'post',
        where: {
            gameId: $('#gameId').val(),
            gameName: $('#gameName').val(),
            gameEnName: $('#gameEnName').val()
        },
        page: true,
        limit: 10,
        limits: [10, 25, 50],
        cols: [[
            {
                field: 'id',
                title: '桌游编号',
                fixed: 'left'
            },
            {
                field: 'gameName',
                title: '桌游名称',
                templet: function (d) {
                    return d.gameName !== undefined && d.gameName != null ? d.gameName : '--';
                }
            },
            {
                field: 'gameEnName',
                title: '英文名称',
                templet: function (d) {
                    return d.gameEnName !== undefined && d.gameEnName != null ? d.gameEnName : '--';
                }
            },
            {
                field: 'commentCount',
                title: '评价数量',
                templet: function (d) {
                    return d.commentCount >= 0 ? d.commentCount : '--';
                }
            },
            {
                field: 'ownerCount',
                title: '拥有数量',
                templet: function (d) {
                    return d.ownerCount >= 0 ? d.ownerCount : '--';
                }
            },
            {
                field: 'recordCount',
                title: '战绩数量',
                templet: function (d) {
                    return d.recordCount >= 0 ? d.recordCount : '--';
                }
            },
            {
                field: 'rating',
                title: '得分',
                templet: function (d) {
                    return d.rating !== undefined && d.rating != null ? d.rating : '--';
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
        if (obj.event === 'edit') {
            showFrame('编辑桌游信息', ROOT_CONTEXT + 'game/edit/' + data.id);
        } else if (obj.event === 'view') {
            showFrame('查看桌游信息', ROOT_CONTEXT + 'game/view/' + data.id);
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
                gameId: $('#gameId').val(),
                gameName: $('#gameName').val(),
                gameEnName: $('#gameEnName').val()
            }
        });
    });
    $('#addBtn').on('click', function () {
        showFrame('新增桌游信息', ROOT_CONTEXT + 'game/add');
    });
});
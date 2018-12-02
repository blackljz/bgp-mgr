layui.use(['element', 'form', 'table'], function () {
    var form = layui.form,
        table = layui.table;

    table.render({
        elem: '#dataTable',
        url: '/game/queryData',
        method: 'post',
        page: true,
        cols: [[
            {field: 'id', title: '桌游编号', fixed: 'left'},
            {field: 'gameName', title: '桌游名称'},
            {field: 'gameEnName', title: '英文名称'},
            {field: 'type', title: '桌游类型'},
            {field: 'commentCount', title: '评价数量'},
            {field: 'ownerCount', title: '拥有数量'},
            {field: 'recordCount', title: '战绩数量'},
            {field: 'score', title: '得分'},
            {field: '', title: '操作'}
        ]]
    });
});
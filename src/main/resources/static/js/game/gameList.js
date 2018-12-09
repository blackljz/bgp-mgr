layui.use(['form', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        layer = layui.layer;

    table.render({
        elem: '#dataTable',
        url: ROOT_CONTEXT + 'game/queryData',
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
            {title: '操作', toolbar: '#operateBtnTemp'}
        ]]
    });

    //监听行工具事件
    table.on('tool(dataTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            showIframe('编辑桌游信息', ROOT_CONTEXT + 'game/edit/' + data.id);
        } else if (obj.event === 'view') {
            showIframe('查看桌游信息', ROOT_CONTEXT + 'game/view/' + data.id);
        }
    });

    /** 显示弹出iframe */
    function showIframe(title, url) {
        if (!url)
            return false;
        layer.open({
            type: 2,
            title: title ? title : '信息',
            area: ['1140px', 'auto'],
            content: url
        });
    }
});
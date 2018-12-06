layui.use(['form', 'table'], function () {
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
            {title: '操作', toolbar: '#barTemplet'}
        ]]
    });

    //监听行工具事件
    table.on('tool(dataTable)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '编辑桌游信息',
                shadeClose: false,
                shade: 0.8,
                area: ['1140px', 'auto'],
                content: '/game/edit/' + data.id
            });
        } else if (obj.event === 'view') {
            layer.prompt({
                formType: 2
                , value: data.email
            }, function (value, index) {
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });
});
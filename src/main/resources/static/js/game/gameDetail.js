layui.use(['form', 'upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        , url: '/upload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    //多图片上传
    upload.render({
        elem: '#test2'
        , url: '/upload/'
        , multiple: true
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
            });
        }
        , done: function (res) {
            //上传完毕
        }
    });

    //指定允许上传的文件类型
    upload.render({
        elem: '#test3'
        , url: '/upload/'
        , accept: 'file' //普通文件
        , done: function (res) {
            console.log(res)
        }
    });
    upload.render({ //允许上传的文件后缀
        elem: '#test4'
        , url: '/upload/'
        , accept: 'file' //普通文件
        , exts: 'zip|rar|7z' //只允许上传压缩文件
        , done: function (res) {
            console.log(res)
        }
    });
    upload.render({
        elem: '#test5'
        , url: '/upload/'
        , accept: 'video' //视频
        , done: function (res) {
            console.log(res)
        }
    });
    upload.render({
        elem: '#test6'
        , url: '/upload/'
        , accept: 'audio' //音频
        , done: function (res) {
            console.log(res)
        }
    });

    //设定文件大小限制
    upload.render({
        elem: '#test7'
        , url: '/upload/'
        , size: 60 //限制文件大小，单位 KB
        , done: function (res) {
            console.log(res)
        }
    });

    //同时绑定多个元素，并将属性设定在元素上
    upload.render({
        elem: '.demoMore'
        , before: function () {
            layer.tips('接口地址：' + this.url, this.item, {tips: 1});
        }
        , done: function (res, index, upload) {
            var item = this.item;
            console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
        }
    })

    //选完文件后不自动上传
    upload.render({
        elem: '#test8'
        , url: '/upload/'
        , auto: false
        //,multiple: true
        , bindAction: '#test9'
        , done: function (res) {
            console.log(res)
        }
    });

    function close() {
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
});
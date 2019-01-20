package com.bgp.mgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/file")
public class FileController {

    /**
     * 上传附件
     */
    @PostMapping("/upload")
    public String uploadFile() {
        //TODO
        return "";
    }

    /**
     * 下载附件
     *
     * @param fileKey
     * @return
     */
    @PostMapping("/download")
    public String downloadFile(@RequestParam("fileKey") String fileKey) {
        //TODO
        return "";
    }
}

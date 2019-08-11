package com.bgp.mgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/file")
public class FileController {

    /**
     * 上传附件
     */
    @RequestMapping(value = "/upload", produces = "text/html; charset=UTF-8;")
    @ResponseBody
    public String uploadFile(MultipartHttpServletRequest multipartRequest, HttpServletRequest request,
                             HttpServletResponse response) {
        String type = request.getParameter("type");
        //TODO
        return "";
    }

    /**
     * 下载附件
     *
     * @param fileKey
     * @return
     */
    @RequestMapping("/download")
    @ResponseBody
    public String downloadFile(@RequestParam("fileKey") String fileKey) {
        //TODO
        return "";
    }
}

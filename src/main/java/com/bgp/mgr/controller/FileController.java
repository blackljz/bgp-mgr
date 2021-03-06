package com.bgp.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.bgp.mgr.common.constants.CommonConstant;
import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.common.utils.LoginUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${sys.storage-dir}")
    private String STORAGE_DIR;

    /**
     * 上传附件
     */
    @RequestMapping(value = "/upload", produces = "text/html; charset=UTF-8;")
    @ResponseBody
    public String uploadFile(MultipartHttpServletRequest multipartRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<String> fileKeys = new ArrayList<>();
            String pin = LoginUtils.getPin();
            MultiValueMap<String, MultipartFile> fileMap = multipartRequest.getMultiFileMap();
            List<MultipartFile> fileList = fileMap.get("file");
            for (int i = 0; i < fileList.size(); i++) {
                MultipartFile file = fileList.get(i);
                // 文件全名
                String fileFullName = file.getOriginalFilename();
                // 扩展名
                String extension = "";
                if (fileFullName != null) {
                    extension = fileFullName.substring(fileFullName.lastIndexOf("."));
                }
                // 生成KEY
                String fileKey = pin.toLowerCase() + DateFormatUtils.format(new Date(), CommonConstant.DATETIME_ALL_NO_PATTERN) + i + extension;
                File destFile = new File(STORAGE_DIR + fileKey);
                // 如果目录不存在则创建
                if (!destFile.getParentFile().exists()) {
                    if (destFile.getParentFile().mkdirs()) {
                        logger.info("创建目录：" + destFile.getParentFile() + "成功！");
                    } else {
                        logger.info("创建目录：" + destFile.getParentFile() + "失败！");
                        throw new BgpException("创建文件存储目录失败！");
                    }
                }
                // 转储到临时文件
                file.transferTo(destFile);
                fileKeys.add(fileKey);
            }
            result.put("code", 0);
            result.put("data", fileKeys);
        } catch (BgpException be) {
            logger.error("上传文件异常！", be);
            result.put("code", -1);
            result.put("msg", "上传文件异常：" + be.getMessage());
        } catch (Exception e) {
            logger.error("上传文件异常！", e);
            result.put("code", -1);
            result.put("msg", "上传文件异常！");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 预览图片文件
     *
     * @param fileKey
     * @return
     */
    @RequestMapping("/preview")
    public ResponseEntity previewImage(@RequestParam("fileKey") String fileKey) {
        if (this.isLocalFile(fileKey)) {
            File file = new File(STORAGE_DIR + fileKey);
            if (!file.exists()) {
                logger.warn("文件[" + file.getPath() + "]不存在！");
                return null;
            }
            return ResponseEntity.ok(resourceLoader.getResource("file:" + STORAGE_DIR + fileKey));
        } else {
            return ResponseEntity.ok(resourceLoader.getResource(fileKey));
        }
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

    /**
     * 判断是否本地图片
     *
     * @param fileKey
     * @return
     */
    private boolean isLocalFile(String fileKey) {
        if (fileKey == null) {
            return false;
        } else {
            return !fileKey.startsWith("http://") && !fileKey.startsWith("https://");
        }
    }
}

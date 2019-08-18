package com.bgp.mgr.service.impl;

import com.bgp.mgr.service.FileService;
import com.sun.javafx.geom.Path2D;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

//    private FileInfoMapper fileInfoMapper;

    @Override
    public List<FileInfo> getFileInfoByGameId(Long gameId) {
//        fileInfoMapper.a()
        return null;
    }
}

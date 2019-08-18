package com.bgp.mgr.service;

import com.bgp.mgr.dao.domain.AttentionInfo;
import org.apache.tomcat.jni.FileInfo;

import java.util.List;

public interface FileService {

    /**
     * 根据游戏ID查询附件列表
     *
     * @param gameId
     * @return
     */
    List<FileInfo> getFileInfoByGameId(Long gameId);
}

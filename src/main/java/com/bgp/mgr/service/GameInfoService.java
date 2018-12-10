package com.bgp.mgr.service;

import com.bgp.mgr.dao.vo.GameInfoVo;

import java.util.List;
import java.util.Map;

public interface GameInfoService {

    /**
     * 分页查询游戏库列表
     *
     * @param start
     * @param size
     * @param params
     * @return
     */
    List<GameInfoVo> queryGameInfoByPage(int start, int size, Map<String, Object> params);

    /**
     * 根据id查询游戏信息
     *
     * @param id
     * @return
     */
    GameInfoVo findGameInfoById(Long id);
}

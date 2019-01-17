package com.bgp.mgr.service;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;

import java.util.Map;

public interface GameInfoService {

    /**
     * 分页查询游戏库列表
     *
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     */
    PageVo<GameInfoVo> queryGameInfoByPage(int pageNo, int pageSize, Map<String, Object> params);

    /**
     * 根据id查询游戏信息
     *
     * @param id
     * @return
     */
    GameInfoVo findGameInfoById(Long id);

    /**
     * 增加游戏
     *
     * @param pin
     * @param gameInfo
     */
    void addGameInfo(String pin, GameInfo gameInfo);
}

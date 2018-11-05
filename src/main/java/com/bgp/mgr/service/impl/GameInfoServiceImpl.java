package com.bgp.mgr.service.impl;

import com.bgp.mgr.dao.GameInfoMapper;
import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.domain.GameInfoExample;
import com.bgp.mgr.service.GameInfoService;
import com.sun.javafx.geom.Path2D;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;
import java.util.Map;

@Service("gameInfoService")
public class GameInfoServiceImpl implements GameInfoService {

    @Resource
    private GameInfoMapper gameInfoMapper;

    @Override
    @Transactional()
    public List<GameInfo> queryGameInfoByPage(int start, int size, Map<String, Object> params) {
        GameInfoExample gameInfoExample = new GameInfoExample();
        gameInfoExample.createCriteria();
        List<GameInfo> gameInfoList = gameInfoMapper.selectByExample(gameInfoExample);
        return gameInfoList;
    }
}

package com.bgp.mgr.service.impl;

import com.bgp.mgr.dao.GameInfoMapper;
import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.domain.GameInfoExample;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.service.GameInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("gameInfoService")
public class GameInfoServiceImpl implements GameInfoService {

    @Resource
    private GameInfoMapper gameInfoMapper;

    @Override
    @Transactional(readOnly = true)
    public PageVo<GameInfoVo> queryGameInfoByPage(int pageNo, int pageSize, Map<String, Object> params) {
        PageVo<GameInfoVo> pageVo = new PageVo<>();

        GameInfoExample example = new GameInfoExample();
        GameInfoExample.Criteria criteria = example.createCriteria();
        if (params.get("id") != null) {
            criteria.andIdEqualTo((Long) params.get("id"));
        }
        if (params.get("gameName") != null) {
            criteria.andGameNameLike("%" + params.get("gameName") + "%");
        }
        if (params.get("gameEnName") != null) {
            criteria.andGameNameLike("%" + params.get("gameEnName") + "%");
        }

        int count = gameInfoMapper.countByExample(example);

        example.setLimitSize((pageNo - 1) * pageSize);
        example.setLimitSize(pageSize);
        List<GameInfo> gameInfos = gameInfoMapper.selectByExample(example);

        List<GameInfoVo> gameInfoVos = new ArrayList<>();
        if (gameInfos != null && gameInfos.size() > 0) {
            gameInfos.forEach((gameInfo) -> {
                GameInfoVo gameInfoVo = new GameInfoVo();
                BeanUtils.copyProperties(gameInfo, gameInfoVo);
                // TODO 相关统计
                gameInfoVo.setCommentCount(0);
                gameInfoVo.setOwnerCount(0);
                gameInfoVo.setRecordCount(0);
                gameInfoVos.add(gameInfoVo);
            });
        }
        pageVo.setCode(0);
        pageVo.setCount(count);
        pageVo.setData(gameInfoVos);
        return pageVo;
    }

    @Override
    @Transactional(readOnly = true)
    public GameInfoVo findGameInfoById(Long id) {
        GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(id);
        GameInfoVo gameInfoVo = new GameInfoVo();
        if (gameInfo != null) {
            BeanUtils.copyProperties(gameInfo, gameInfoVo);
        } else {
            gameInfoVo = null;
        }
        return gameInfoVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGameInfo(String pin, GameInfo gameInfo) {
        gameInfo.setCreatedBy(pin);
        gameInfo.setCreatedDate(new Date());
        gameInfo.setModifiedBy(pin);
        gameInfo.setModifiedDate(new Date());
        gameInfoMapper.insertSelective(gameInfo);
    }

    @Override
    public void updateGameInfo(String pin, GameInfo gameInfo) {
        gameInfo.setModifiedBy(pin);
        gameInfo.setModifiedDate(new Date());
        gameInfoMapper.updateByPrimaryKeySelective(gameInfo);
    }
}

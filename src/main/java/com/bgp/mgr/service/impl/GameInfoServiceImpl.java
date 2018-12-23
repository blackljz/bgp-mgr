package com.bgp.mgr.service.impl;

import com.bgp.mgr.dao.GameInfoMapper;
import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.domain.GameInfoExample;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.service.GameInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("gameInfoService")
public class GameInfoServiceImpl implements GameInfoService {

    @Resource
    private GameInfoMapper gameInfoMapper;

    @Override
    @Transactional()
    public PageVo<GameInfoVo> queryGameInfoByPage(int pageNo, int pageSize, Map<String, Object> params) {
        PageVo<GameInfoVo> pageVo = new PageVo<>();

        GameInfoExample example = new GameInfoExample();
        GameInfoExample.Criteria criteria = example.createCriteria();
        if (params.get("gameName") != null) {
            criteria.andGameNameLike("%" + (String) params.get("gameName") + "%");
        }
        if (params.get("gameType") != null) {
            criteria.andGameTypeEqualTo((String) params.get("gameType"));
        }

        int count = gameInfoMapper.countByExample(example);

        example.setOffset((pageNo - 1) * pageSize);
        example.setLimit(pageSize);
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
    public GameInfoVo findGameInfoById(Long id) {
        GameInfoExample example = new GameInfoExample();
        example.createCriteria().andIdEqualTo(id);
        List<GameInfo> gameInfos = gameInfoMapper.selectByExample(example);

        GameInfoVo gameInfoVo = new GameInfoVo();
        if (gameInfos != null && gameInfos.size() > 0) {
            BeanUtils.copyProperties(gameInfos.get(0), gameInfoVo);
        }
        return gameInfoVo;
    }
}

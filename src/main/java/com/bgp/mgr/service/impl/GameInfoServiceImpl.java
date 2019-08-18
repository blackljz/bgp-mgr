package com.bgp.mgr.service.impl;

import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.dao.FileInfoMapper;
import com.bgp.mgr.dao.GameInfoMapper;
import com.bgp.mgr.dao.domain.FileInfo;
import com.bgp.mgr.dao.domain.FileInfoExample;
import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.domain.GameInfoExample;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.service.GameInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("gameInfoService")
public class GameInfoServiceImpl implements GameInfoService {

    @Resource
    private GameInfoMapper gameInfoMapper;

    @Resource
    private FileInfoMapper fileInfoMapper;

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
                gameInfoVo.setCommentCount(-1);
                gameInfoVo.setOwnerCount(-1);
                gameInfoVo.setRecordCount(-1);
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
            // 组织附件信息
            FileInfoExample example = new FileInfoExample();
            example.createCriteria().andGameIdEqualTo(id);
            List<FileInfo> fileInfos = fileInfoMapper.selectByExample(example);
            gameInfoVo.setFileInfos(fileInfos);
        } else {
            gameInfoVo = null;
        }
        return gameInfoVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGameInfo(String pin, GameInfoVo gameInfoVo) {
        if (gameInfoVo == null) {
            throw new BgpException("游戏信息为空！");
        }

        // 保存游戏信息
        gameInfoVo.setCreatedBy(pin);
        gameInfoVo.setCreatedDate(new Date());
        gameInfoVo.setModifiedBy(pin);
        gameInfoVo.setModifiedDate(new Date());
        gameInfoMapper.insertSelective(gameInfoVo);

        // 保存附件信息
        List<FileInfo> fileInfos = gameInfoVo.getFileInfos();
        if (!CollectionUtils.isEmpty(fileInfos)) {
            for (FileInfo fileInfo : fileInfos) {
                fileInfo.setGameId(gameInfoVo.getId());
                fileInfoMapper.insertSelective(fileInfo);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGameInfo(String pin, GameInfoVo gameInfoVo) {
        if (gameInfoVo == null || gameInfoVo.getId() == null) {
            throw new BgpException("游戏信息为空！");
        }

        // 更新游戏信息
        gameInfoVo.setModifiedBy(pin);
        gameInfoVo.setModifiedDate(new Date());
        gameInfoMapper.updateByPrimaryKeySelective(gameInfoVo);

        // 更新附件信息（先删后插）
        FileInfoExample example = new FileInfoExample();
        example.createCriteria().andGameIdEqualTo(gameInfoVo.getId());
        fileInfoMapper.deleteByExample(example);
        List<FileInfo> fileInfos = gameInfoVo.getFileInfos();
        if (!CollectionUtils.isEmpty(fileInfos)) {
            for (FileInfo fileInfo : fileInfos) {
                fileInfoMapper.insertSelective(fileInfo);
            }
        }
    }
}

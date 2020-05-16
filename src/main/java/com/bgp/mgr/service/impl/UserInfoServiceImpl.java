package com.bgp.mgr.service.impl;

import com.bgp.mgr.dao.UserInfoMapper;
import com.bgp.mgr.dao.domain.UserInfo;
import com.bgp.mgr.dao.domain.UserInfoExample;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.dao.vo.UserInfoVo;
import com.bgp.mgr.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(readOnly = true)
    public PageVo<UserInfoVo> queryUserInfoByPage(int pageNo, int pageSize, Map<String, Object> params) {
        PageVo<UserInfoVo> pageVo = new PageVo<>();

        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        if (params.get("userName") != null) {
            criteria.andUserNameLike("%" + params.get("userName") + "%");
        }
        if (params.get("sex") != null) {
            criteria.andSexEqualTo((String) params.get("sex"));
        }
        if (params.get("createDateStart") != null) {
            criteria.andCreateDateGreaterThanOrEqualTo((Date) params.get("createDateStart"));
        }
        if (params.get("createDateEnd") != null) {
            criteria.andCreateDateLessThanOrEqualTo((Date) params.get("createDateEnd"));
        }
        int count = userInfoMapper.countByExample(example);

        example.setLimitStart((pageNo - 1) * pageSize);
        example.setLimitSize(pageSize);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);

        List<UserInfoVo> userInfoVos = new ArrayList<>();
        if (userInfos != null && userInfos.size() > 0) {
            userInfos.forEach((userInfo) -> {
                UserInfoVo userInfoVo = new UserInfoVo();
                BeanUtils.copyProperties(userInfo, userInfoVo);
                // TODO 相关统计
                userInfoVos.add(userInfoVo);
            });
        }
        pageVo.setCode(0);
        pageVo.setCount(count);
        pageVo.setData(userInfoVos);
        return pageVo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoVo findUserInfoById(Long id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        UserInfoVo userInfoVo = new UserInfoVo();
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfo, userInfoVo);
        } else {
            userInfoVo = null;
        }
        return userInfoVo;
    }

}

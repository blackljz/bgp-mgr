package com.bgp.mgr.service;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.dao.vo.UserInfoVo;

import java.util.Map;

public interface UserInfoService {

    /**
     * 分页查询用户列表
     *
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     */
    PageVo<UserInfoVo> queryUserInfoByPage(int pageNo, int pageSize, Map<String, Object> params);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    UserInfoVo findUserInfoById(Long id);

}

package com.bgp.mgr.service;

import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.dao.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SysUserService extends UserDetailsService {

    /**
     * 增加用户
     *
     * @param sysUser 用户信息
     */
    void addSysUser(SysUser sysUser) throws BgpException;

    /**
     * 删除用户
     *
     * @param pin 登录名
     */
    void delSysUser(String pin) throws BgpException;

    /**
     * 修改密码
     *
     * @param pin    登录名
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    void changePassWord(String pin, String oldPwd, String newPwd) throws BgpException;
}

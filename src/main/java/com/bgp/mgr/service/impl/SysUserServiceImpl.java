package com.bgp.mgr.service.impl;

import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.dao.SysUserMapper;
import com.bgp.mgr.dao.domain.SysUser;
import com.bgp.mgr.dao.domain.SysUserExample;
import com.bgp.mgr.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper sysUserMapper;

    private UserCache userCache = new NullUserCache();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        logger.info("login user={}", pin);
        //校验用户
        SysUser sysUser = sysUserMapper.selectByPin(pin);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //用户权限（暂时默认为管理员）
        List<String> userRoles = Arrays.asList("admin");
        // 封装用户信息（用户名，密码，用户权限）
        User user = new User(sysUser.getPin(), sysUser.getPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", userRoles)));
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addSysUser(SysUser sysUser) throws BgpException {
        if (sysUser == null) {
            throw new BgpException("传入用户信息为空！");
        }

        SysUser existsUser = sysUserMapper.selectByPin(sysUser.getPin());
        if (existsUser != null) {
            // 用户名已存在
            throw new BgpException("该用户已存在！");
        }

        // 创建用户
        sysUserMapper.insert(sysUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delSysUser(String pin) throws BgpException {
        if (StringUtils.isBlank(pin)) {
            throw new BgpException("用户名为空！");
        }

        SysUser existsUser = sysUserMapper.selectByPin(pin);
        if (existsUser == null) {
            // 用户名不存在
            throw new BgpException("该用户不存在！");
        }

        // 删除用户
        sysUserMapper.deleteByPrimaryKey(existsUser.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void changePassWord(String pin, String oldPwd, String newPwd) throws BgpException {
        if (StringUtils.isBlank(pin)) {
            throw new BgpException("用户名为空！");
        }

        SysUser existsUser = sysUserMapper.selectByPin(pin);
        if (existsUser == null) {
            // 用户名不存在
            throw new BgpException("该用户不存在！");
        }
        if (!passwordEncoder.matches(oldPwd, existsUser.getPwd())) {
            // 旧密码不正确
            throw new BgpException("旧密码不正确！");
        }
        if (StringUtils.isBlank(newPwd)) {
            throw new BgpException("新密码为空！");
        }

        // 更新密码
        SysUser sysUser = new SysUser();
        sysUser.setPwd(passwordEncoder.encode(newPwd));
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdEqualTo(existsUser.getId());
        sysUserMapper.updateByExampleSelective(sysUser, example);

        // 更新凭证
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(this.createNewAuthentication(currentAuth));
        userCache.removeUserFromCache(pin);
    }

    private Authentication createNewAuthentication(Authentication currentAuth) {
        UserDetails user = loadUserByUsername(currentAuth.getName());
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());
        return newAuthentication;
    }

}

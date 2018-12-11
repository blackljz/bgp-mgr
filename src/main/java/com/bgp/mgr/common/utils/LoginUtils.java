package com.bgp.mgr.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUtils {

    /**
     * 获取当前登录用户
     *
     * @return pin
     */
    public static String getPin() {
        String userCode = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userCode = ((UserDetails) principal).getUsername();
        }
        return userCode;
    }
}

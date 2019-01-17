package com.bgp.mgr.controller;

import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.common.response.ResponseBodyResult;
import com.bgp.mgr.common.utils.LoginUtils;
import com.bgp.mgr.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 修改密码页面
     *
     * @return
     */
    @GetMapping("/passwordMgr")
    public String passwordMgr() {
        return "/sys/passwordMgr";
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PostMapping("/changePwd")
    @ResponseBody
    public String changePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
        String pin = LoginUtils.getPin();
        try {
            sysUserService.changePassWord(pin, oldPwd, newPwd);
        } catch (BgpException be) {
            return ResponseBodyResult.error(be.getMessage());
        } catch (Exception e) {
            return ResponseBodyResult.error();
        }
        return ResponseBodyResult.success();
    }
}

package com.bgp.mgr.controller;

import com.bgp.mgr.common.constants.CommonConstant;
import com.bgp.mgr.common.response.ResponseBodyResult;
import com.bgp.mgr.common.utils.LoginUtils;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.dao.vo.UserInfoVo;
import com.bgp.mgr.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserInfoService userInfoService;

    /**
     * 用户管理列表页面
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "user/userList";
    }

    /**
     * 分页查询用户
     *
     * @param request
     * @return
     */
    @PostMapping("/queryData")
    @ResponseBody
    public PageVo<UserInfoVo> queryData(HttpServletRequest request) {
        PageVo<UserInfoVo> pageVo = new PageVo<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String pin = LoginUtils.getPin();
            int pageNo = Integer.parseInt(request.getParameter("page"));
            int pageSize = Integer.parseInt(request.getParameter("limit"));
            Map<String, Object> params = new HashMap<>();
            params.put("pin", pin);
            params.put("userName", StringUtils.isNotBlank(request.getParameter("userName")) ? request.getParameter("userName") : null);
            params.put("sex", StringUtils.isNotBlank(request.getParameter("userSex")) ? request.getParameter("userSex") : null);
            if (StringUtils.isNotBlank(request.getParameter("registDateStart"))) {// 注册日期查询范围开始时间
                Date createDateStart = dateFormat.parse(request.getParameter("registDateStart"));
                params.put("createDateStart", createDateStart);
            }
            if (StringUtils.isNotBlank(request.getParameter("registDateEnd"))) {// 注册日期查询范围截止时间
                Date createDateEnd = dateFormat.parse(request.getParameter("registDateEnd"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(createDateEnd);
                calendar.add(Calendar.DATE, 1);// 日期+1用于查询当天结果
                params.put("createDateEnd", calendar.getTime());
            }
            pageVo = userInfoService.queryUserInfoByPage(pageNo, pageSize, params);
        } catch (Exception e) {
            logger.error("查询用户列表异常！", e);
            pageVo.setCode(-1);
            pageVo.setMsg("查询用户列表异常！");
        }
        return pageVo;
    }

    /**
     * 查看用户详情页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.put("editType", CommonConstant.EDITTYPE_VIEW);
        modelMap.put("userId", id);
        return "user/userDetails";
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getDetails")
    @ResponseBody
    public String getUserInfoDetails(@RequestParam("id") Long id) {
        UserInfoVo userInfoVo;
        try {
            userInfoVo = userInfoService.findUserInfoById(id);
        } catch (Exception e) {
            logger.error("查询用户详情异常！", e);
            return ResponseBodyResult.error("查询用户详情异常！");
        }
        return ResponseBodyResult.success(userInfoVo);
    }

}
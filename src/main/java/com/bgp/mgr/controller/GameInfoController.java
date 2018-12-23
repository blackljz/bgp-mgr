package com.bgp.mgr.controller;

import com.bgp.mgr.common.constants.CommonConstant;
import com.bgp.mgr.common.utils.LoginUtils;
import com.bgp.mgr.dao.vo.GameInfoVo;
import com.bgp.mgr.dao.vo.PageVo;
import com.bgp.mgr.service.GameInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/game")
public class GameInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GameInfoService gameInfoService;

    @GetMapping("/list")
    public String list() {
        return "/game/gameList";
    }

    @PostMapping("/queryData")
    @ResponseBody
    public PageVo<GameInfoVo> queryData(HttpServletRequest request) {
        PageVo<GameInfoVo> pageVo = new PageVo<>();
        try {
            String pin = LoginUtils.getPin();
            int pageNo = Integer.parseInt(request.getParameter("page"));
            int pageSize = Integer.parseInt(request.getParameter("limit"));
            Map<String, Object> params = new HashMap<>();
            params.put("pin", pin);
            params.put("gameName", StringUtils.isNotBlank(request.getParameter("gameName")) ? request.getParameter("gameName") : null);
            params.put("gameEnName", StringUtils.isNotBlank(request.getParameter("gameEnName")) ? request.getParameter("gameName") : null);
            params.put("gameType", StringUtils.isNotBlank(request.getParameter("gameType")) ? request.getParameter("gameType") : null);
            pageVo = gameInfoService.queryGameInfoByPage(pageNo, pageSize, params);
        } catch (Exception e) {
            logger.error("查询桌游列表异常！", e);
            pageVo.setCode(-1);
            pageVo.setMsg("查询桌游列表异常！");
        }
        return pageVo;
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("editType", CommonConstant.EDITTYPE_ADD);
        return "/game/gameDetail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        GameInfoVo gameInfoVo = gameInfoService.findGameInfoById(id);
        modelMap.put("data", gameInfoVo);
        modelMap.put("editType", CommonConstant.EDITTYPE_EDIT);
        return "/game/gameDetail";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        GameInfoVo gameInfoVo = gameInfoService.findGameInfoById(id);
        modelMap.put("data", gameInfoVo);
        modelMap.put("editType", CommonConstant.EDITTYPE_VIEW);
        return "/game/gameDetail";
    }
}

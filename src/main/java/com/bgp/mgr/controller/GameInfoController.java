package com.bgp.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.bgp.mgr.common.constants.CommonConstant;
import com.bgp.mgr.common.response.ResponseBodyResult;
import com.bgp.mgr.common.utils.LoginUtils;
import com.bgp.mgr.dao.domain.GameInfo;
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

    /**
     * 桌游列表页面
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "game/gameList";
    }

    /**
     * 分页查询桌游
     *
     * @param request
     * @return
     */
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
            params.put("id", StringUtils.isNotBlank(request.getParameter("gameId")) ? Long.valueOf(request.getParameter("gameId")) : null);
            params.put("gameName", StringUtils.isNotBlank(request.getParameter("gameName")) ? request.getParameter("gameName") : null);
            params.put("gameEnName", StringUtils.isNotBlank(request.getParameter("gameEnName")) ? request.getParameter("gameName") : null);
            pageVo = gameInfoService.queryGameInfoByPage(pageNo, pageSize, params);
        } catch (Exception e) {
            logger.error("查询桌游列表异常！", e);
            pageVo.setCode(-1);
            pageVo.setMsg("查询桌游列表异常！");
        }
        return pageVo;
    }

    /**
     * 新增桌游页面
     *
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("editType", CommonConstant.EDITTYPE_NEW);
        modelMap.put("gameId", 0L);//新增默认id为0
        return "game/gameDetails";
    }

    /**
     * 编辑桌游页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.put("editType", CommonConstant.EDITTYPE_EDIT);
        modelMap.put("gameId", id);
        return "game/gameDetails";
    }

    /**
     * 查看桌游页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.put("editType", CommonConstant.EDITTYPE_VIEW);
        modelMap.put("gameId", id);
        return "game/gameDetails";
    }

    /**
     * 获取桌游详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getDetails")
    @ResponseBody
    public String getGameInfoDetails(@RequestParam("id") Long id) {
        GameInfoVo gameInfoVo;
        try {
            gameInfoVo = gameInfoService.findGameInfoById(id);
        } catch (Exception e) {
            logger.error("查询桌游详情异常！", e);
            return ResponseBodyResult.error("查询桌游详情异常！");
        }
        return ResponseBodyResult.success(gameInfoVo);
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveGameInfo(@RequestParam("editType") String editType, @RequestParam("gameInfo") String gameInfoJson) {
        try {
            String pin = LoginUtils.getPin();
            logger.info("准备保存桌游信息 pin={},editType={},gameInfo={}", pin, editType, gameInfoJson);
            GameInfoVo gameInfoVo = JSON.parseObject(gameInfoJson, GameInfoVo.class);
            if (CommonConstant.EDITTYPE_NEW.equals(editType)) {
                gameInfoService.addGameInfo(pin, gameInfoVo);
            } else {
                gameInfoService.updateGameInfo(pin, gameInfoVo);
            }
        } catch (Exception e) {
            logger.error("保存桌游信息异常！", e);
            return ResponseBodyResult.error("保存桌游信息异常！");
        }
        return ResponseBodyResult.success();
    }
}

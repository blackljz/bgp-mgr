package com.bgp.mgr.controller;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.service.GameInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> queryData(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<GameInfo> gameInfoList = gameInfoService.queryGameInfoByPage(0, 0, null);
            resultMap.put("code", 0);
            resultMap.put("data", gameInfoList);
        } catch (Exception e) {
            logger.error("查询桌游列表异常！", e);
        }
        return resultMap;
    }

    @GetMapping("/add")
    public String add() {
        return "/game/gameDetail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        GameInfo gameInfo = gameInfoService.findGameInfoById(id);
        modelMap.put("data", gameInfo);
        return "/game/gameDetail";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id) {
        return "/game/gameDetail";
    }
}

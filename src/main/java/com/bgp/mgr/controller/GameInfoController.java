package com.bgp.mgr.controller;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.service.GameInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
    public Map<String, Object> queryData(HttpRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<GameInfo> gameInfoList = gameInfoService.queryGameInfoByPage(0, 0, null);
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

    @GetMapping("/edit")
    public String edit() {
        return "/game/gameDetail";
    }

    @GetMapping("/view")
    public String view() {
        return "/game/gameDetail";
    }
}

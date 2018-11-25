package com.bgp.mgr.controller;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.service.GameInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameInfoController {

    @Resource
    private GameInfoService gameInfoService;

    @GetMapping("/list")
    public String list() {
        List<GameInfo> gameInfoList = gameInfoService.queryGameInfoByPage(0, 0, null);
        return "/game/gameList";
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

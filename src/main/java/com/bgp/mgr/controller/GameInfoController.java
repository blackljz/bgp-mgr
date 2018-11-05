package com.bgp.mgr.controller;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.service.GameInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller("game")
public class GameInfoController {

    @Resource
    private GameInfoService gameInfoService;

    @GetMapping(name = "list")
    public String gameInfoPage() {
        List<GameInfo> gameInfoList = gameInfoService.queryGameInfoByPage(0, 0, null);
        return "/game/list";
    }
}

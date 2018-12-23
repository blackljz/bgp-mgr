package com.bgp.mgr.controller;

import com.bgp.mgr.common.utils.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gameTag")
public class GameTagController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String list() {
        return "/gameTag/gameTagList";
    }

    @PostMapping("/queryData")
    @ResponseBody
    public Map<String, Object> queryData(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String pin = LoginUtils.getPin();
            int pageNo = Integer.parseInt(request.getParameter("page"));
            int pageSize = Integer.parseInt(request.getParameter("limit"));

            resultMap.put("code", 0);
            resultMap.put("data", null);
        } catch (Exception e) {
            logger.error("查询桌游标签Ø列表异常！", e);
        }
        return resultMap;
    }

}

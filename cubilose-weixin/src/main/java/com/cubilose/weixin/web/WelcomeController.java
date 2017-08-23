package com.cubilose.weixin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/23.
 */

@Controller
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping("/index")
    public String welcome(Map<String, Object> model) {
        model.put("wId", 123);
        model.put("message", "Hello JSP");
        return "welcome";
    }

}

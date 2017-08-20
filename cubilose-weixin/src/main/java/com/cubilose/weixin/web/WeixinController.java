package com.cubilose.weixin.web;

import com.cubilose.weixin.service.WeixinCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jianxin.wang on 2017/8/20.
 *
 * 微信消息处理
 */

@RestController
@RequestMapping("/wx")
public class WeixinController {

    private final static Logger logger
            = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private WeixinCoreService weixinService;

    /**
     * 微信开发校验
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @ResponseBody
    public String index(HttpServletRequest request) {
        // 校验
        boolean flag = weixinService.checkSignature(request);
        if (flag) {
            // 校验成功，返回随机字符串
            return request.getParameter("echostr");
        } else {
            return "";
        }
    }

    /**
     * 接收并处理微信消息
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    @ResponseBody
    public String main(HttpServletRequest request, HttpServletResponse response) {
        String result = weixinService.handleMsg(request, response);

        logger.info("返回消息：\n" + result);
        return result;
    }

}

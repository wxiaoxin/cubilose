package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.service.WeixinCoreService;
import com.cubilose.weixin.util.SignUtils;
import com.cubilose.weixin.util.XMLUtil;
import com.cubilose.weixin.wx.WeixinConfiguration;
import com.cubilose.weixin.wx.WeixinMessageConstant;
import com.cubilose.weixin.wx.message.NewsMessage;
import com.cubilose.weixin.wx.message.TextMessage;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by jianxin.wang on 2017/8/20.
 *
 * 微信消息服务实现类
 */

@Service
public class WeixinCoreServiceImpl implements WeixinCoreService {

    private final static Logger logger
            = LoggerFactory.getLogger(WeixinCoreServiceImpl.class);

    public boolean checkSignature(HttpServletRequest request) {
        // signature 微信加密签名
        String signature = request.getParameter("signature");
        // timestamp 时间戳
        String timestamp = request.getParameter("timestamp");
        // nonce 随机数
        String nonce = request.getParameter("nonce");
        // echostr 随机数
        String echostr = request.getParameter("echostr");

        // 字典排序
        String[] arr = new String[]{WeixinConfiguration.TOKEN, timestamp, nonce};
        Arrays.sort(arr);

        // SHA1加密
        StringBuilder sbf = new StringBuilder();
        for (String s : arr) {
            sbf.append(s);
        }
        String s = SignUtils.SHA1(sbf.toString());

        // 判断加密后字符串与请求中的字符串是否相等，并返回结果
        return s.equals(signature);
    }

    @Override
    public String handleMsg(HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        try {
            ServletInputStream is = request.getInputStream();
            // 解析得到消息的map格式
            Map<String, String> msgMap = XMLUtil.xmlISToMap(is);
            String toUserName = msgMap.get("ToUserName");
            String fromUserName = msgMap.get("FromUserName");
            String createTime = msgMap.get("CreateTime");
            String msgType = msgMap.get("MsgType");
            String msgId = msgMap.get("MsgId");

            logger.info("toUserName：" + toUserName);
            logger.info("fromUserName：" + fromUserName);
            logger.info("createTime：" + createTime);
            logger.info("msgType：" + msgType);
            logger.info("msgId：" + msgId);

            // 响应文本消息
            TextMessage message = new TextMessage();
            message.setToUserName(fromUserName);
            message.setFromUserName(toUserName);
            message.setCreateTime(String.valueOf(new Date().getTime()));
            message.setMsgType(WeixinMessageConstant.TYPE_TEXT);

            // 文本消息
            if(msgType.equals(WeixinMessageConstant.TYPE_TEXT)) {
                String content = msgMap.get("Content");
                message.setContent(content);

                // if(content.equals("模板")) {
                //     Map<String,String> map = new HashMap<>();
                //     map.put("first", "话费充值提醒");
                //     map.put("keyword1", "15221505770");
                //     map.put("keyword2", "100");
                //     map.put("remark", "感谢使用在线充值！");
                //
                //     templateMsgService.sendPhoneChargeTMsg(fromUserName, "http://www.baidu.com", map);
                //     message.setContent("已发送模板消息");
                // } else if(content.equals("授权")) {
                //     String url = WeixinUrlConstants.WEBAUTH_REDIRECT_URL
                //             .replace("APPID", WeixinConfigConstant.APPID)
                //             .replace("REDIRECT_URI", WeixinConfigConstant.AUTH_REDIRECT_URI)
                //             .replace("STATE", "123");
                //     String respContent = "<a href=\"" + url + "\">授权</a>";
                //     message.setContent(respContent);
                // } else {
                //     message.setContent(content);
                // }
            }

            if(msgType.equals(WeixinMessageConstant.TYPE_EVENT)) {
                String event = msgMap.get("Event");
                if(event.equals(WeixinMessageConstant.EVENT_TYPE_SUBSCRIBE)) {
                    // 订阅事件
                    message.setContent("感谢关注！");
                }

                if (event.equalsIgnoreCase(WeixinMessageConstant.EVENT_TYPE_CLICK)) {
                    // 点击事件
                    String eventKey = msgMap.get("EventKey");
                    if (eventKey.equalsIgnoreCase("coupon")) {
                        NewsMessage newsMessage = new NewsMessage();

                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setCreateTime(String.valueOf(new Date().getTime()));
                        newsMessage.setMsgType(WeixinMessageConstant.TYPE_NEWS);

                        List<NewsMessage.Item> articles = new ArrayList<>();

                        NewsMessage.Item item = new NewsMessage.Item();
                        item.setTitle("兑换优惠券");
                        item.setDescription("点我去兑换优惠券");
                        String url = "http://wxiaoxin.ngrok.cc/#/test/" + fromUserName;
                        item.setUrl(url);
                        logger.info("url: " + url);

                        articles.add(item);

                        newsMessage.setArticles(articles);
                        newsMessage.setArticleCount(articles.size());

                        return XMLUtil.newsMsgToXml(newsMessage);
                    }
                    if (eventKey.equalsIgnoreCase("expressage")) {
                        message.setContent("快递查询");
                    }
                }
            }
            result = XMLUtil.textMsgToXml(message);
            return result;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return "";
    }

}

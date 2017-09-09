package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.service.UserCouponService;
import com.cubilose.weixin.service.UserService;
import com.cubilose.weixin.service.WeixinCoreService;
import com.cubilose.weixin.util.SignUtils;
import com.cubilose.weixin.util.XMLUtil;
import com.cubilose.weixin.wx.WeixinConfiguration;
import com.cubilose.weixin.wx.WeixinMessageConstant;
import com.cubilose.weixin.wx.WeixinService;
import com.cubilose.weixin.wx.message.NewsMessage;
import com.cubilose.weixin.wx.message.TextMessage;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private UserService userService;

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
            }

            if(msgType.equals(WeixinMessageConstant.TYPE_EVENT)) {
                String event = msgMap.get("Event");
                if(event.equals(WeixinMessageConstant.EVENT_TYPE_SUBSCRIBE)) {
                    // 订阅事件
                    message.setContent("感谢关注！");

                    // 拉取一次关注者列表信息，存入数据库中
                    userService.pullUserList();
                }

                if (event.equalsIgnoreCase(WeixinMessageConstant.EVENT_TYPE_CLICK)) {
                    // 点击事件
                    String eventKey = msgMap.get("EventKey");
                    if (eventKey.equalsIgnoreCase("coupon")) {
                        // 优惠券

                        // userCouponService.

                        NewsMessage newsMessage = new NewsMessage();

                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setCreateTime(String.valueOf(new Date().getTime()));
                        newsMessage.setMsgType(WeixinMessageConstant.TYPE_NEWS);

                        List<NewsMessage.Item> articles = new ArrayList<>();

                        StringBuilder description = new StringBuilder();

                        User user = userService.queryByWId(fromUserName);
                        if (user != null) {
                            List<Map> userCoupons = userCouponService.queryByUserId(user.getId());
                            int size = userCoupons.size();
                            if (size > 0) {
                                description.append("您已有").append(size).append("张优惠券\n\n");
                                for (Map map : userCoupons) {
                                    description.append(map.get("code")).append("\n\n");
                                }
                            } else {
                                description.append("您还没有优惠券\n");
                            }
                        }
                        description.append("点我去兑换优惠券");

                        NewsMessage.Item item = new NewsMessage.Item();
                        item.setTitle("兑换优惠券");
                        item.setDescription(description.toString());
                        String url = "http://birdnesket.wenqie.info/index/" + fromUserName;
                        item.setUrl(url);
                        logger.info("url: " + url);

                        articles.add(item);

                        newsMessage.setArticles(articles);
                        newsMessage.setArticleCount(articles.size());

                        return XMLUtil.newsMsgToXml(newsMessage);
                    }
                    if (eventKey.equalsIgnoreCase("expressage")) {
                        StringBuilder description = new StringBuilder();

                        User user = userService.queryByWId(fromUserName);
                        if (user != null) {
                            List<Map> userCoupons = userCouponService.queryByUserId(user.getId());
                            int size = userCoupons.size();
                            if (size > 0) {
                                for (Map map : userCoupons) {
                                    String logistics_number = (String) map.get("logistics_number");
                                    if (logistics_number != null && !logistics_number.isEmpty()) {
                                        String[] split = logistics_number.split("-");
                                        if (split.length >= 2) {
                                            String com = split[0];
                                            String number = split[1];
                                            String url = "https://m.kuaidi100.com/result.jsp?com=" + com + "&nu=" + number;
                                            description.append("<a href=\"")
                                                    .append(url)
                                                    .append("\">物流单号：")
                                                    .append(number)
                                                    .append("</a>\n\n");
                                            logger.info(description.toString());
                                        }
                                    }
                                }
                            } else {
                                description.append("暂无物流信息");
                            }
                        }
                        message.setContent(description.toString());
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

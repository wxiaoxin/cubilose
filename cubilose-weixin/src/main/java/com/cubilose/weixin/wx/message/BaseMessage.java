package com.cubilose.weixin.wx.message;

/**
 * Created by wxiao on 2016.11.8.
 *
 * 微信消息基础类
 */

public class BaseMessage {

    private String ToUserName;

    private String FromUserName;

    private String CreateTime;

    private String MsgType;

    private String MsgId;

    public BaseMessage() {
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        this.MsgId = msgId;
    }
}

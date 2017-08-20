package com.cubilose.weixin.wx.message;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/20.
 *
 * 图文消息
 */

public class NewsMessage extends BaseMessage {

    private int ArticleCount;

    private List<Item> Articles;

    public static class Item {

        private String Title;

        private String Description;

        private String PicUrl;

        private String Url;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String picUrl) {
            PicUrl = picUrl;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Item> getArticles() {
        return Articles;
    }

    public void setArticles(List<Item> articles) {
        Articles = articles;
    }
}

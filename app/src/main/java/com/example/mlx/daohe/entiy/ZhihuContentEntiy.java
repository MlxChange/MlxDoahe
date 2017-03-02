package com.example.mlx.daohe.entiy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/28 0:02
 * 用途：
 */

public class ZhihuContentEntiy {



    @SerializedName("body")
    private String body;
    @SerializedName("image_source")
    private String imageSource;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("ga_prefix")
    private String gaPrefix;
    @SerializedName("section")
    private SectionBean section;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("js")
    private List<String> js;
    @SerializedName("images")
    private List<String> images;
    @SerializedName("css")
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public SectionBean getSection() {
        return section;
    }

    public void setSection(SectionBean section) {
        this.section = section;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionBean {
        /**
         * thumbnail : http://pic4.zhimg.com/aba72fdea6757b1afb7b7b12ee31e87f.jpg
         * id : 64
         * name : 奥斯卡
         */

        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

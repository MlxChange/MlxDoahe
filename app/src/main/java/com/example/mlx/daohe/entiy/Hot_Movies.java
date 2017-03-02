package com.example.mlx.daohe.entiy;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.entiy
 * 创建者：MLX
 * 创建时间：2017/2/11 18:07
 * 用途：首页电影数据
 */

public class Hot_Movies extends DataSupport {

    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":false,"movies":[{"late":false,"showInfo":"今天17家影院放映121场","sn":0,"cnms":0,"preSale":0,"imax":true,"vd":"","dir":"D·J·卡卢索","star":"范·迪塞尔,甄子丹,迪皮卡·帕度柯妮","cat":"动作,惊悚,冒险","wish":131897,"3d":true,"snum":54084,"sc":8.8,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"本周五上映","src":"","scm":"特工出江湖，率领群英组","dur":107,"showDate":"","nm":"极限特工：终极回归","img":"http://p1.meituan.net/165.220/movie/43aeb3fe86ed6fb9f9cc86ed0b177e26109383.jpeg","pn":609,"time":"","id":334590},{"late":false,"showInfo":"今天16家影院放映64场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"唐季礼","star":"成龙,李治廷,张艺兴","cat":"喜剧,动作,冒险","wish":89920,"3d":true,"snum":478245,"sc":8.5,"ver":"2D/3D/中国巨幕","rt":"2017-01-28上映","src":"","scm":"科学家寻宝，中印来回跑","dur":108,"showDate":"","nm":"功夫瑜伽","img":"http://p1.meituan.net/165.220/movie/d273af80e581b4c60abcbb57a95ad2b72956123.jpg","pn":305,"time":"","id":248933},{"late":false,"showInfo":"今天15家影院放映63场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"叶伟民","star":"谢霆锋,葛优,郑容和","cat":"喜剧,爱情,家庭","wish":30669,"3d":true,"snum":13540,"sc":8,"ver":"2D/3D","rt":"本周五上映","src":"","scm":"厨神争头筹 ，爱情遇阴谋","dur":98,"showDate":"","nm":"决战食神","img":"http://p0.meituan.net/165.220/movie/7da359aec37708c194080d203ea7f490342855.jpeg","pn":162,"time":"","id":346106},{"late":false,"showInfo":"今天16家影院放映51场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"丁亮,林永长,林汇达","star":"尚雯婕,鲍春来,曾舜晞","cat":"动画,冒险","wish":29669,"3d":true,"snum":191179,"sc":9.2,"ver":"2D/3D","rt":"2017-01-28上映","src":"","scm":"熊抱春来到，保护金鹿角","dur":91,"showDate":"","nm":"熊出没·奇幻空间","img":"http://p1.meituan.net/165.220/movie/cfcdb50d181a2111a2b79a0639db0f19764116.png","pn":89,"time":"","id":1189089},{"late":false,"showInfo":"今天16家影院放映48场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"韩寒","star":"邓超,彭于晏,赵丽颖","cat":"剧情,喜剧,冒险","wish":49770,"3d":false,"snum":308523,"sc":8.8,"ver":"2D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"阿浪梦追逐，意外入险途","dur":102,"showDate":"","nm":"乘风破浪","img":"http://p0.meituan.net/165.220/movie/c6cadc16e53ee7a0f79a9455a147449d50210.jpeg","pn":217,"time":"","id":1170255},{"late":false,"showInfo":"今天14家影院放映35场","sn":0,"cnms":0,"preSale":0,"imax":true,"vd":"","dir":"徐克","star":"吴亦凡,林更新,姚晨","cat":"喜剧,动作,奇幻","wish":212622,"3d":true,"snum":571364,"sc":7.8,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"取经矛盾多，面和心不和","dur":108,"showDate":"","nm":"西游伏妖篇","img":"http://p0.meituan.net/165.220/movie/cd2791a174bfc7c1bc630fbb281c30e0308728.jpg","pn":310,"time":"","id":248904},{"late":false,"showInfo":"今天6家影院放映14场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"埃里克·韦林,埃里克·萨默","star":"艾丽·范宁,佟心竹,卡莉·蕾·吉普森","cat":"喜剧,动画,冒险","wish":12299,"3d":true,"snum":21909,"sc":9.2,"ver":"2D/3D","rt":"2017-02-01上映","src":"","scm":"孤儿院少女，逐梦去巴黎","dur":90,"showDate":"","nm":"了不起的菲丽西","img":"http://p1.meituan.net/165.220/movie/f885e601ccd912011725a8d7488633b7812258.jpg","pn":124,"time":"","id":1042603},{"late":false,"showInfo":"今天7家影院放映14场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"高希希","star":"何润东,黄子韬,古力娜扎","cat":"剧情,动作","wish":11455,"3d":true,"snum":6612,"sc":8.4,"ver":"2D/3D/中国巨幕","rt":"本周五上映","src":"","scm":"上海滩黑帮，热血在胸膛","dur":126,"showDate":"","nm":"游戏规则","img":"http://p1.meituan.net/165.220/movie/2257630f02f7784eaebaf53583115d61129235.jpeg","pn":51,"time":"","id":345658},{"late":false,"showInfo":"今天5家影院放映11场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"王宝强","star":"王宝强,白客,岳云鹏","cat":"喜剧,动作,冒险","wish":241535,"3d":false,"snum":447062,"sc":7.5,"ver":"2D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"宝强取真经，争当搞笑King","dur":100,"showDate":"","nm":"大闹天竺","img":"http://p1.meituan.net/165.220/movie/015de6bbaa2ecc299254b24e4f96939d489607.jpg","pn":355,"time":"","id":248935},{"late":false,"showInfo":"今天5家影院放映9场","sn":0,"cnms":0,"preSale":1,"imax":true,"vd":"","dir":"达米恩·查泽雷","star":"瑞恩·高斯林,艾玛·斯通,约翰·传奇","cat":"爱情,歌舞","wish":31935,"3d":false,"snum":1023,"sc":0,"ver":"2D/IMAX 2D/中国巨幕/全景声","rt":"下周二上映","src":"","scm":"爵士钢琴家，恋爱舞天涯","dur":129,"showDate":"","nm":"爱乐之城","img":"http://p0.meituan.net/165.220/movie/f4990a965755af1be2a16c421951c48219905345.jpg","pn":143,"time":"","id":338436},{"late":false,"showInfo":"2017-02-14 下周二上映","sn":0,"cnms":0,"preSale":1,"imax":false,"vd":"","dir":"刘国楠","star":"郑秀文,张孝全,冯文娟","cat":"喜剧,爱情","wish":9348,"3d":false,"snum":235,"sc":0,"ver":"2D/中国巨幕","rt":"下周二上映","src":"","scm":"借精生好汉，误撞另一半","dur":96,"showDate":"","nm":"合约男女","img":"http://p1.meituan.net/165.220/movie/93c2df086df1279c43acfe8a841954b9275518.jpg","pn":106,"time":"","id":341126},{"late":false,"showInfo":"今天3家影院放映3场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"郭大雷","star":"贾乃亮,马丽,梁超","cat":"喜剧,动作","wish":31956,"3d":false,"snum":42324,"sc":7.2,"ver":"2D","rt":"2017-02-03上映","src":"","scm":"东北小混混，斗黑帮恶棍","dur":99,"showDate":"","nm":"东北往事之破马张飞","img":"http://p0.meituan.net/165.220/movie/d9d800469e30249c9319dc7c4fa0211651185.jpeg","pn":154,"time":"","id":344032},{"late":false,"showInfo":"今天2家影院放映2场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"邓伟锋","star":"姚雷,郭盛,李姗姗","cat":"喜剧,动画,奇幻","wish":4596,"3d":false,"snum":3441,"sc":8.6,"ver":"2D","rt":"2017-02-05上映","src":"","scm":"萤火光燎原，灯灯保家园","dur":85,"showDate":"","nm":"萤火奇兵","img":"http://p1.meituan.net/165.220/movie/d12de031fa8ed2f684510aa5efec821e433682.jpg","pn":53,"time":"","id":346477},{"late":false,"showInfo":"2017-02-14 下周二上映","sn":0,"cnms":0,"preSale":1,"imax":false,"vd":"","dir":"钟少雄","star":"岳云鹏,袁姗姗,孙坚","cat":"喜剧,爱情","wish":10107,"3d":false,"snum":830,"sc":0,"ver":"2D","rt":"下周二上映","src":"","scm":"护工会撩妹，女神大胆追","dur":94,"showDate":"","nm":"疯岳撬佳人","img":"http://p1.meituan.net/165.220/movie/bde4b63a30647e0f3dfa8a588c282123652748.jpeg","pn":58,"time":"","id":1132987}]}
     */

    @SerializedName("control")
    private ControlBean control;
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean extends DataSupport {
        /**
         * expires : 1800
         */

        @SerializedName("expires")
        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean extends DataSupport {
        /**
         * hasNext : false
         * movies : [{"late":false,"showInfo":"今天17家影院放映121场","sn":0,"cnms":0,"preSale":0,"imax":true,"vd":"","dir":"D·J·卡卢索","star":"范·迪塞尔,甄子丹,迪皮卡·帕度柯妮","cat":"动作,惊悚,冒险","wish":131897,"3d":true,"snum":54084,"sc":8.8,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"本周五上映","src":"","scm":"特工出江湖，率领群英组","dur":107,"showDate":"","nm":"极限特工：终极回归","img":"http://p1.meituan.net/165.220/movie/43aeb3fe86ed6fb9f9cc86ed0b177e26109383.jpeg","pn":609,"time":"","id":334590},{"late":false,"showInfo":"今天16家影院放映64场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"唐季礼","star":"成龙,李治廷,张艺兴","cat":"喜剧,动作,冒险","wish":89920,"3d":true,"snum":478245,"sc":8.5,"ver":"2D/3D/中国巨幕","rt":"2017-01-28上映","src":"","scm":"科学家寻宝，中印来回跑","dur":108,"showDate":"","nm":"功夫瑜伽","img":"http://p1.meituan.net/165.220/movie/d273af80e581b4c60abcbb57a95ad2b72956123.jpg","pn":305,"time":"","id":248933},{"late":false,"showInfo":"今天15家影院放映63场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"叶伟民","star":"谢霆锋,葛优,郑容和","cat":"喜剧,爱情,家庭","wish":30669,"3d":true,"snum":13540,"sc":8,"ver":"2D/3D","rt":"本周五上映","src":"","scm":"厨神争头筹 ，爱情遇阴谋","dur":98,"showDate":"","nm":"决战食神","img":"http://p0.meituan.net/165.220/movie/7da359aec37708c194080d203ea7f490342855.jpeg","pn":162,"time":"","id":346106},{"late":false,"showInfo":"今天16家影院放映51场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"丁亮,林永长,林汇达","star":"尚雯婕,鲍春来,曾舜晞","cat":"动画,冒险","wish":29669,"3d":true,"snum":191179,"sc":9.2,"ver":"2D/3D","rt":"2017-01-28上映","src":"","scm":"熊抱春来到，保护金鹿角","dur":91,"showDate":"","nm":"熊出没·奇幻空间","img":"http://p1.meituan.net/165.220/movie/cfcdb50d181a2111a2b79a0639db0f19764116.png","pn":89,"time":"","id":1189089},{"late":false,"showInfo":"今天16家影院放映48场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"韩寒","star":"邓超,彭于晏,赵丽颖","cat":"剧情,喜剧,冒险","wish":49770,"3d":false,"snum":308523,"sc":8.8,"ver":"2D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"阿浪梦追逐，意外入险途","dur":102,"showDate":"","nm":"乘风破浪","img":"http://p0.meituan.net/165.220/movie/c6cadc16e53ee7a0f79a9455a147449d50210.jpeg","pn":217,"time":"","id":1170255},{"late":false,"showInfo":"今天14家影院放映35场","sn":0,"cnms":0,"preSale":0,"imax":true,"vd":"","dir":"徐克","star":"吴亦凡,林更新,姚晨","cat":"喜剧,动作,奇幻","wish":212622,"3d":true,"snum":571364,"sc":7.8,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"取经矛盾多，面和心不和","dur":108,"showDate":"","nm":"西游伏妖篇","img":"http://p0.meituan.net/165.220/movie/cd2791a174bfc7c1bc630fbb281c30e0308728.jpg","pn":310,"time":"","id":248904},{"late":false,"showInfo":"今天6家影院放映14场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"埃里克·韦林,埃里克·萨默","star":"艾丽·范宁,佟心竹,卡莉·蕾·吉普森","cat":"喜剧,动画,冒险","wish":12299,"3d":true,"snum":21909,"sc":9.2,"ver":"2D/3D","rt":"2017-02-01上映","src":"","scm":"孤儿院少女，逐梦去巴黎","dur":90,"showDate":"","nm":"了不起的菲丽西","img":"http://p1.meituan.net/165.220/movie/f885e601ccd912011725a8d7488633b7812258.jpg","pn":124,"time":"","id":1042603},{"late":false,"showInfo":"今天7家影院放映14场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"高希希","star":"何润东,黄子韬,古力娜扎","cat":"剧情,动作","wish":11455,"3d":true,"snum":6612,"sc":8.4,"ver":"2D/3D/中国巨幕","rt":"本周五上映","src":"","scm":"上海滩黑帮，热血在胸膛","dur":126,"showDate":"","nm":"游戏规则","img":"http://p1.meituan.net/165.220/movie/2257630f02f7784eaebaf53583115d61129235.jpeg","pn":51,"time":"","id":345658},{"late":false,"showInfo":"今天5家影院放映11场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"王宝强","star":"王宝强,白客,岳云鹏","cat":"喜剧,动作,冒险","wish":241535,"3d":false,"snum":447062,"sc":7.5,"ver":"2D/中国巨幕/全景声","rt":"2017-01-28上映","src":"","scm":"宝强取真经，争当搞笑King","dur":100,"showDate":"","nm":"大闹天竺","img":"http://p1.meituan.net/165.220/movie/015de6bbaa2ecc299254b24e4f96939d489607.jpg","pn":355,"time":"","id":248935},{"late":false,"showInfo":"今天5家影院放映9场","sn":0,"cnms":0,"preSale":1,"imax":true,"vd":"","dir":"达米恩·查泽雷","star":"瑞恩·高斯林,艾玛·斯通,约翰·传奇","cat":"爱情,歌舞","wish":31935,"3d":false,"snum":1023,"sc":0,"ver":"2D/IMAX 2D/中国巨幕/全景声","rt":"下周二上映","src":"","scm":"爵士钢琴家，恋爱舞天涯","dur":129,"showDate":"","nm":"爱乐之城","img":"http://p0.meituan.net/165.220/movie/f4990a965755af1be2a16c421951c48219905345.jpg","pn":143,"time":"","id":338436},{"late":false,"showInfo":"2017-02-14 下周二上映","sn":0,"cnms":0,"preSale":1,"imax":false,"vd":"","dir":"刘国楠","star":"郑秀文,张孝全,冯文娟","cat":"喜剧,爱情","wish":9348,"3d":false,"snum":235,"sc":0,"ver":"2D/中国巨幕","rt":"下周二上映","src":"","scm":"借精生好汉，误撞另一半","dur":96,"showDate":"","nm":"合约男女","img":"http://p1.meituan.net/165.220/movie/93c2df086df1279c43acfe8a841954b9275518.jpg","pn":106,"time":"","id":341126},{"late":false,"showInfo":"今天3家影院放映3场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"郭大雷","star":"贾乃亮,马丽,梁超","cat":"喜剧,动作","wish":31956,"3d":false,"snum":42324,"sc":7.2,"ver":"2D","rt":"2017-02-03上映","src":"","scm":"东北小混混，斗黑帮恶棍","dur":99,"showDate":"","nm":"东北往事之破马张飞","img":"http://p0.meituan.net/165.220/movie/d9d800469e30249c9319dc7c4fa0211651185.jpeg","pn":154,"time":"","id":344032},{"late":false,"showInfo":"今天2家影院放映2场","sn":0,"cnms":0,"preSale":0,"imax":false,"vd":"","dir":"邓伟锋","star":"姚雷,郭盛,李姗姗","cat":"喜剧,动画,奇幻","wish":4596,"3d":false,"snum":3441,"sc":8.6,"ver":"2D","rt":"2017-02-05上映","src":"","scm":"萤火光燎原，灯灯保家园","dur":85,"showDate":"","nm":"萤火奇兵","img":"http://p1.meituan.net/165.220/movie/d12de031fa8ed2f684510aa5efec821e433682.jpg","pn":53,"time":"","id":346477},{"late":false,"showInfo":"2017-02-14 下周二上映","sn":0,"cnms":0,"preSale":1,"imax":false,"vd":"","dir":"钟少雄","star":"岳云鹏,袁姗姗,孙坚","cat":"喜剧,爱情","wish":10107,"3d":false,"snum":830,"sc":0,"ver":"2D","rt":"下周二上映","src":"","scm":"护工会撩妹，女神大胆追","dur":94,"showDate":"","nm":"疯岳撬佳人","img":"http://p1.meituan.net/165.220/movie/bde4b63a30647e0f3dfa8a588c282123652748.jpeg","pn":58,"time":"","id":1132987}]
         */

        @SerializedName("hasNext")
        private boolean hasNext;
        @SerializedName("movies")
        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {
            /**
             * late : false
             * showInfo : 今天17家影院放映121场
             * sn : 0
             * cnms : 0
             * preSale : 0
             * imax : true
             * vd :
             * dir : D·J·卡卢索
             * star : 范·迪塞尔,甄子丹,迪皮卡·帕度柯妮
             * cat : 动作,惊悚,冒险
             * wish : 131897
             * 3d : true
             * snum : 54084
             * sc : 8.8
             * ver : 3D/IMAX 3D/中国巨幕/全景声
             * rt : 本周五上映
             * src :
             * scm : 特工出江湖，率领群英组
             * dur : 107
             * showDate :
             * nm : 极限特工：终极回归
             * img : http://p1.meituan.net/165.220/movie/43aeb3fe86ed6fb9f9cc86ed0b177e26109383.jpeg
             * pn : 609
             * time :
             * id : 334590
             */

            @SerializedName("late")
            private boolean late;
            @SerializedName("showInfo")
            private String showInfo;
            @SerializedName("sn")
            private int sn;
            @SerializedName("cnms")
            private int cnms;
            @SerializedName("preSale")
            private int preSale;
            @SerializedName("imax")
            private boolean imax;
            @SerializedName("vd")
            private String vd;
            @SerializedName("dir")
            private String dir;
            @SerializedName("star")
            private String star;
            @SerializedName("cat")
            private String cat;
            @SerializedName("wish")
            private int wish;
            @SerializedName("3d")
            private boolean $3d;
            @SerializedName("snum")
            private int snum;
            @SerializedName("sc")
            private double sc;
            @SerializedName("ver")
            private String ver;
            @SerializedName("rt")
            private String rt;
            @SerializedName("src")
            private String src;
            @SerializedName("scm")
            private String scm;
            @SerializedName("dur")
            private int dur;
            @SerializedName("showDate")
            private String showDate;
            @SerializedName("nm")
            private String nm;
            @SerializedName("img")
            private String img;
            @SerializedName("pn")
            private int pn;
            @SerializedName("time")
            private String time;
            @SerializedName("id")
            private int id;

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public boolean is$3d() {
                return $3d;
            }

            public void set$3d(boolean $3d) {
                this.$3d = $3d;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}

package com.example.mlx.daohe.entiy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/27 23:58
 * 用途：知乎首页实体类
 */

public class ZhihuEntiy {

    /**
     * date : 20170227
     * stories : [{"images":["http://pic1.zhimg.com/7a0353968e38ed760d685d5a32a664d0.jpg"],"type":0,"id":9252748,"ga_prefix":"022722","title":"小事 · 30 岁，发现自己非亲生"},{"images":["http://pic4.zhimg.com/aba72fdea6757b1afb7b7b12ee31e87f.jpg"],"type":0,"id":9253421,"ga_prefix":"022721","title":"奥斯卡 · 最佳外语片《推销员》，如鲠在喉的道德谜题"},{"images":["http://pic2.zhimg.com/0fb6e98c55b70a7d03862c44c59d198d.jpg"],"type":0,"id":9253873,"ga_prefix":"022720","title":"打算宰客的出租车司机，是怎么看人下菜的？"},{"title":"面包的咸甜之争，咸党首先派出这款「盐面包」迎战","ga_prefix":"022720","images":["http://pic2.zhimg.com/913d24c4e9ab8893c5cc332670782bd1.jpg"],"multipic":true,"type":0,"id":9253760},{"images":["http://pic2.zhimg.com/e0a2261907d244f1349b130a6dd43139.jpg"],"type":0,"id":9252684,"ga_prefix":"022717","title":"一叶不光能知秋，也能看出生命演化的过程"},{"images":["http://pic3.zhimg.com/bff35e7be7f767f35ade149e8c2bbfee.jpg"],"type":0,"id":9253411,"ga_prefix":"022716","title":"奥斯卡 · 这是一份完整的（没出错的）获奖名单分析"},{"images":["http://pic1.zhimg.com/b873810e6fcb9ff2cbcb9962be57c3d8.jpg"],"type":0,"id":9253292,"ga_prefix":"022715","title":"过去的一年，人工智能和机器人学会了哪些新技能？"},{"images":["http://pic2.zhimg.com/b111db2d53ceea91a1446dffa5df91a1.jpg"],"type":0,"id":9253102,"ga_prefix":"022714","title":"奥斯卡 · 最佳纪录长片：上世纪一桩惊天杀妻案的细枝末节"},{"images":["http://pic2.zhimg.com/92e252ec7cb024c69bc0edf7e83d7a75.jpg"],"type":0,"id":9253164,"ga_prefix":"022714","title":"奥斯卡 · 讲述黑人、毒品、同性恋《月光男孩》，是编剧自己的故事"},{"images":["http://pic4.zhimg.com/d26fbaaa7cafa663006a875f08583533.jpg"],"type":0,"id":9241361,"ga_prefix":"022713","title":"奥斯卡 · 《月光男孩》，政治正确的胜利还是电影语言的成功？"},{"images":["http://pic2.zhimg.com/17cc0f5a02526097c485a48ae63be599.jpg"],"type":0,"id":9253072,"ga_prefix":"022713","title":"奥斯卡 · 刘看山专访了最佳导演"},{"images":["http://pic1.zhimg.com/2741a5ef48e80c5b94887d9d311f3400.jpg"],"type":0,"id":9253027,"ga_prefix":"022713","title":"奥斯卡 · 最佳女主角「石头姐」，可商业可文艺，会唱歌能跳舞"},{"title":"奥斯卡 · 你也可以穿出「最佳服装设计」的迷人风格","ga_prefix":"022712","images":["http://pic1.zhimg.com/e09e36838958d3008ede754b7dd244e4.jpg"],"multipic":true,"type":0,"id":9252719},{"images":["http://pic3.zhimg.com/78b7a1073ec7ebc00be5e906775b38be.jpg"],"type":0,"id":9252793,"ga_prefix":"022712","title":"奥斯卡 · 又斩获最佳外语片，伊朗有很多厉害导演和作品"},{"images":["http://pic2.zhimg.com/0ed99814a48fb1a96cc8e76164f97615.jpg"],"type":0,"id":9252002,"ga_prefix":"022712","title":"大误 · 劣等读心术"},{"images":["http://pic2.zhimg.com/035a93f9bb4ba92118921a734fc2a855.jpg"],"type":0,"id":9251603,"ga_prefix":"022711","title":"几千年来，人类之间暴力是不是真的减少了？"},{"images":["http://pic4.zhimg.com/4b9a204a0dcde8502f7f0e17066e50f3.jpg"],"type":0,"id":9251587,"ga_prefix":"022710","title":"国外的体育解说确实不太一样，比如可以转行当总统"},{"images":["http://pic1.zhimg.com/308d16b14f9c28928038378bf1abca0c.jpg"],"type":0,"id":9238387,"ga_prefix":"022709","title":"为什么我们总是不把时间用在「刀刃上」？（因为穷且懒）"},{"images":["http://pic4.zhimg.com/8c2cdf0c2cf1bd74069316bc0cce4cd3.jpg"],"type":0,"id":9252028,"ga_prefix":"022708","title":"工作中的「同伴效应」更多的是竞争，而不是教你知识"},{"images":["http://pic2.zhimg.com/81332486cfdee030ee595876d311f43d.jpg"],"type":0,"id":9251951,"ga_prefix":"022707","title":"日本最帅的女人们，都在这个歌剧团里"},{"images":["http://pic4.zhimg.com/228b464dffe5657e1b1837e24058ac6f.jpg"],"type":0,"id":9251925,"ga_prefix":"022707","title":"初入职场的新人，什么行为会让老员工反感？"},{"images":["http://pic3.zhimg.com/bdf74d72016886a01a3dd17f65f661ae.jpg"],"type":0,"id":9251906,"ga_prefix":"022707","title":"2005 年巴菲特打了个赌，现在胜负已毫无悬念"},{"images":["http://pic2.zhimg.com/aa8cbf3777d6c33c7db5407bbc1a18d9.jpg"],"type":0,"id":9251877,"ga_prefix":"022706","title":"瞎扯 Plus · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/6184ccf05c5bd52053f21f150fef32d6.jpg","type":0,"id":9253411,"ga_prefix":"022716","title":"奥斯卡 · 这是一份完整的（没出错的）获奖名单分析"},{"image":"http://pic1.zhimg.com/b3a2d414ac6e8d9225fbef107ad9550c.jpg","type":0,"id":9253164,"ga_prefix":"022714","title":"奥斯卡 · 讲述黑人、毒品、同性恋《月光男孩》，是编剧自己的故事"},{"image":"http://pic1.zhimg.com/ef9f4abc09282f8ea634b2a068aee720.jpg","type":0,"id":9241361,"ga_prefix":"022713","title":"奥斯卡 · 《月光男孩》，政治正确的胜利还是电影语言的成功？"},{"image":"http://pic2.zhimg.com/6119dd10f50b8b142498c77f60fac9e1.jpg","type":0,"id":9253027,"ga_prefix":"022713","title":"奥斯卡 · 最佳女主角「石头姐」，可商业可文艺，会唱歌能跳舞"},{"image":"http://pic1.zhimg.com/3fd178c5778bf2abec0d42e309938b8c.jpg","type":0,"id":9251603,"ga_prefix":"022711","title":"几千年来，人类之间暴力是不是真的减少了？"}]
     */

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<StoriesBean> stories;
    @SerializedName("top_stories")
    private List<TopStoriesBean> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoriesBean> topStories) {
        this.topStories = topStories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic1.zhimg.com/7a0353968e38ed760d685d5a32a664d0.jpg"]
         * type : 0
         * id : 9252748
         * ga_prefix : 022722
         * title : 小事 · 30 岁，发现自己非亲生
         * multipic : true
         */

        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("title")
        private String title;
        @SerializedName("multipic")
        private boolean multipic;
        @SerializedName("images")
        private List<String> images;

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

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic3.zhimg.com/6184ccf05c5bd52053f21f150fef32d6.jpg
         * type : 0
         * id : 9253411
         * ga_prefix : 022716
         * title : 奥斯卡 · 这是一份完整的（没出错的）获奖名单分析
         */

        @SerializedName("image")
        private String image;
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

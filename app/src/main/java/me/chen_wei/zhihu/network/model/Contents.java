package me.chen_wei.zhihu.network.model;

import java.util.List;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class Contents {


    /**
     * date : 20160225
     * stories : [{"images":["http://pic3.zhimg.com/625c29a726de316807851e3af8bfd19a.jpg"],"type":0,"id":7915284,"ga_prefix":"022522","title":"深夜惊奇 · 赌球一时爽"},{"images":["http://pic4.zhimg.com/5a788559ae4069ef2a317154a4e875d7.jpg"],"type":0,"id":7920008,"ga_prefix":"022521","title":"这三部片的共同点是有很多液体，有热、有冷、有黏稠"},{"images":["http://pic2.zhimg.com/9a5c9bec1a56f41b5ad58e8c9c3e518d.jpg"],"type":0,"id":7875647,"ga_prefix":"022520","title":"这首叮当咣当的校铃，最早可能出自爱我中华的神剧"},{"images":["http://pic2.zhimg.com/291814ed80c1e407f8a0f5b67bd71339.jpg"],"type":0,"id":7902359,"ga_prefix":"022519","title":"为什么鼻子和嘴巴联通？这还要从鱼类说起\u2026\u2026"},{"images":["http://pic3.zhimg.com/191cde8bdbb36aa315d196546910ee66.jpg"],"type":0,"id":7918337,"ga_prefix":"022518","title":"男性在网络上的购买欲真的弱于女性吗？"},{"images":["http://pic3.zhimg.com/071b58fcf2d35444b749e40baf00c57a.jpg"],"type":0,"id":7888997,"ga_prefix":"022517","title":"我的专业是人类学，平时会去部落里当个女王啥的（误）"},{"images":["http://pic2.zhimg.com/aed859a292250f66690c80824e905315.jpg"],"type":0,"id":7919463,"ga_prefix":"022516","title":"「白米饭比可乐还容易让人血糖高」，还能吃米饭吗？"},{"images":["http://pic3.zhimg.com/aae31fef75f4d67e89c9a406d865199a.jpg"],"type":0,"id":7918982,"ga_prefix":"022515","title":"「我以为你都懂，可是你没有」"},{"images":["http://pic3.zhimg.com/eb526023315b1b74c3895fb6597ad10e.jpg"],"type":0,"id":7918297,"ga_prefix":"022514","title":"没错，一张「毛爷爷」一旦拆散，就花得更快了"},{"images":["http://pic1.zhimg.com/026fe4dd95ad206e1fefa594b9c1d9f8.jpg"],"type":0,"id":7918246,"ga_prefix":"022513","title":"萝卜只卖一角钱，居然因为太便宜被罚一万块"},{"images":["http://pic3.zhimg.com/cc60329095ece019f97ab68e6695396e.jpg"],"type":0,"id":7910806,"ga_prefix":"022512","title":"如何看待「日本专家炮轰中国新能源车政策」？"},{"images":["http://pic2.zhimg.com/05320e4ed8d74ba7fe2e9d544c63d121.jpg"],"type":0,"id":7918261,"ga_prefix":"022511","title":"为什么有的人喜欢责备别人并且得理不饶人？"},{"images":["http://pic2.zhimg.com/942e0d44ed0c5cf340ac7988d8b173b5.jpg"],"type":0,"id":7910403,"ga_prefix":"022510","title":"花钱支持歌手，这几个公司可能都会谢谢你"},{"images":["http://pic4.zhimg.com/d1bd811467ddff55f9640f04756d3173.jpg"],"type":0,"id":7884280,"ga_prefix":"022508","title":"都别争了，选爱我的还是我爱的有科学解释了"},{"images":["http://pic1.zhimg.com/dd912e5217e6c6f26ac93182a86893d8.jpg"],"type":0,"id":7915946,"ga_prefix":"022507","title":"《功夫熊猫 3》是中美合拍，负责人说中方主要做了这些"},{"images":["http://pic1.zhimg.com/1e87e49386f4c6161f8704e168b60948.jpg"],"type":0,"id":7917099,"ga_prefix":"022507","title":"外国人爱用的这些 app，中国人怎么也想不到"},{"images":["http://pic4.zhimg.com/997afbf82c668046d647fa5a78cf500f.jpg"],"type":0,"id":7914881,"ga_prefix":"022507","title":"这项规定一出台，苹果用户心里有点慌"},{"images":["http://pic1.zhimg.com/bb20b715b634f49322d3050c5e5b28cc.jpg"],"type":0,"id":7917162,"ga_prefix":"022507","title":"读读日报 24 小时热门：在战火纷飞的地道里走私 KFC"},{"images":["http://pic2.zhimg.com/722d059ececb8be03295a10d359f3e8d.jpg"],"type":0,"id":7907685,"ga_prefix":"022506","title":"瞎扯 · 多管闲事与不懂礼貌"}]
     */

    private String date;
    /**
     * images : ["http://pic3.zhimg.com/625c29a726de316807851e3af8bfd19a.jpg"]
     * type : 0
     * id : 7915284
     * ga_prefix : 022522
     * title : 深夜惊奇 · 赌球一时爽
     */

    private List<StoriesEntity> stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public String getDate() {
        return date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public static class StoriesEntity {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getImages() {
            return images;
        }
    }
}

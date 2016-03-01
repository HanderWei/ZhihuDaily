package me.chen_wei.zhihu.network.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class Latest implements Serializable{

    /**
     * date : 20160226
     * stories : [{"images":["http://pic1.zhimg.com/f7c1de18d969350a86774230ab4a00b4.jpg"],"type":0,"id":7923064,"ga_prefix":"022619","title":"为什么近视的人不戴眼镜会听不清别人在说什么？"},{"images":["http://pic2.zhimg.com/1d5535f5becd198a26a5e4474c2f45cd.jpg"],"type":0,"id":7924329,"ga_prefix":"022618","title":"如果你身边有人想自杀，先看见并承认他的痛苦"},{"title":"穿着情趣制服招摇过市，这是我的第一次 Cosplay","ga_prefix":"022617","images":["http://pic3.zhimg.com/7701cb73c08c6bfe7eecb3c3b6752dde.jpg"],"multipic":true,"type":0,"id":7924059},{"images":["http://pic1.zhimg.com/6a7b6d2ce2543dece406c59bdb0ed0c0.jpg"],"type":0,"id":7919743,"ga_prefix":"022616","title":"比尔 · 盖茨夫妇写了封年度公开信，想要「改变世界的超能力」"},{"title":"简单来说，我的工作就是让你「看到」声音","ga_prefix":"022615","images":["http://pic3.zhimg.com/7db93f371bca7a18ad8c3c7ff53c130e.jpg"],"multipic":true,"type":0,"id":7923282},{"title":"为了好吃的涮羊肉，我一定要学会这些挑肉的方法","ga_prefix":"022614","images":["http://pic2.zhimg.com/e4419de35b1471985bf78745a28968b9.jpg"],"multipic":true,"type":0,"id":7912102},{"images":["http://pic3.zhimg.com/2799867f89aa16fe81ff8c1c840097fa.jpg"],"type":0,"id":7914085,"ga_prefix":"022613","title":"「错的不是我，是世界」，这里有了合理的解释"},{"images":["http://pic1.zhimg.com/b81e4222190f32a1cf0bde205be8d550.jpg"],"type":0,"id":7910087,"ga_prefix":"022612","title":"全世界最稀有的植物，我猜你们一定想听点新的"},{"images":["http://pic2.zhimg.com/0133a45257c460b2736dbe0fb8045c61.jpg"],"type":0,"id":7919331,"ga_prefix":"022611","title":"只要你用的是这五大银行，以后手机转账都免手续费"},{"images":["http://pic2.zhimg.com/668300e97c69ecc5b7c8f756505c762d.jpg"],"type":0,"id":7921049,"ga_prefix":"022610","title":"给艺人贴标签有时候是必要的，像王若琳这样的是异类"},{"images":["http://pic3.zhimg.com/b989d608c59d7156bc24d941fb8747ca.jpg"],"type":0,"id":7918511,"ga_prefix":"022609","title":"咦，为什么国外名校同学的成绩都那么好看？"},{"images":["http://pic3.zhimg.com/95dadb9e9fa901d9edc6d1e57211dab6.jpg"],"type":0,"id":7921365,"ga_prefix":"022608","title":"都上小学了还会尿床\u2026\u2026"},{"images":["http://pic2.zhimg.com/53b19dd1f7b6617f6b2a60a8fa088379.jpg"],"type":0,"id":7920604,"ga_prefix":"022607","title":"跟国外相比，北上广深成杭武南人口密度还不够大"},{"images":["http://pic1.zhimg.com/5e5521af241f9b77a1af501b70c8d314.jpg"],"type":0,"id":7920157,"ga_prefix":"022607","title":"从婴儿爽身粉致癌案谈起，为什么不推荐用爽身粉"},{"images":["http://pic4.zhimg.com/afce9eb8e6bd613c582ffd231425e8b3.jpg"],"type":0,"id":7919280,"ga_prefix":"022607","title":"留学之后你都形成了什么「坏习惯」？"},{"images":["http://pic3.zhimg.com/c04b5715b7a55d06b0ead498a6bef8d2.jpg"],"type":0,"id":7921768,"ga_prefix":"022607","title":"读读日报 24 小时热门：小李拿不到奥斯卡活该？求这烂梗快消失"},{"images":["http://pic3.zhimg.com/33459fa23c930a66f4dfe8de432a8106.jpg"],"type":0,"id":7900419,"ga_prefix":"022606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/a18faea79ae2a851f2c6927e5d4b4f48.jpg","type":0,"id":7924059,"ga_prefix":"022617","title":"穿着情趣制服招摇过市，这是我的第一次 Cosplay"},{"image":"http://pic2.zhimg.com/06fdfa6a49051bf161f4703ed17657b5.jpg","type":0,"id":7919743,"ga_prefix":"022616","title":"比尔 · 盖茨夫妇写了封年度公开信，想要「改变世界的超能力」"},{"image":"http://pic3.zhimg.com/39c3b94db27347a3e3c72a3b5b375d4a.jpg","type":0,"id":7918511,"ga_prefix":"022609","title":"咦，为什么国外名校同学的成绩都那么好看？"},{"image":"http://pic2.zhimg.com/6d03c693d157cbf16966d8a929ad58d1.jpg","type":0,"id":7920157,"ga_prefix":"022607","title":"从婴儿爽身粉致癌案谈起，为什么不推荐用爽身粉"},{"image":"http://pic1.zhimg.com/82b3103761f9374ed0f1b2d6ffa0c134.jpg","type":0,"id":7920604,"ga_prefix":"022607","title":"跟国外相比，北上广深成杭武南人口密度还不够大"}]
     */

    private String date;
    /**
     * images : ["http://pic1.zhimg.com/f7c1de18d969350a86774230ab4a00b4.jpg"]
     * type : 0
     * id : 7923064
     * ga_prefix : 022619
     * title : 为什么近视的人不戴眼镜会听不清别人在说什么？
     */

    private List<StoriesEntity> stories;
    /**
     * image : http://pic1.zhimg.com/a18faea79ae2a851f2c6927e5d4b4f48.jpg
     * type : 0
     * id : 7924059
     * ga_prefix : 022617
     * title : 穿着情趣制服招摇过市，这是我的第一次 Cosplay
     */

    private List<TopStoriesEntity> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public static class StoriesEntity implements Serializable{
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

    public static class TopStoriesEntity implements Serializable{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public void setImage(String image) {
            this.image = image;
        }

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

        public String getImage() {
            return image;
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
    }
}

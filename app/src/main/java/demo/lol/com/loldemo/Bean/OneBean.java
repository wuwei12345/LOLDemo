package demo.lol.com.loldemo.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class OneBean {

    /**
     * vid : 51519
     * img : http://img.demaxiya.com/uploads/160729/7-160HZPAY34_500_1000.jpg?nocache=1
     * name : 超神学院黑甲第一季09集：潘震
     * lenght : 15:14
     * updateTime : 07-29
     * type : 2
     * defaultQualityId : 3
     * quality : [{"id":1,"name":"超清","url":"http://www.demaxiya.com/app/index.php?m=play&vid=51519&quality=3&&rand_3=3"},{"id":2,"name":"高清","url":"http://www.demaxiya.com/app/index.php?m=play&vid=51519&quality=2&rand_2=2"},{"id":3,"name":"普清","url":"http://www.demaxiya.com/app/index.php?m=play&vid=51519&quality=1&rand_1=1"}]
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String vid;
        private String img;
        private String name;
        private String lenght;
        private String updateTime;
        private int type;
        private int defaultQualityId;
        /**
         * id : 1
         * name : 超清
         * url : http://www.demaxiya.com/app/index.php?m=play&vid=51519&quality=3&&rand_3=3
         */

        private List<QualityBean> quality;

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLenght() {
            return lenght;
        }

        public void setLenght(String lenght) {
            this.lenght = lenght;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDefaultQualityId() {
            return defaultQualityId;
        }

        public void setDefaultQualityId(int defaultQualityId) {
            this.defaultQualityId = defaultQualityId;
        }

        public List<QualityBean> getQuality() {
            return quality;
        }

        public void setQuality(List<QualityBean> quality) {
            this.quality = quality;
        }

        public static class QualityBean {
            private int id;
            private String name;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

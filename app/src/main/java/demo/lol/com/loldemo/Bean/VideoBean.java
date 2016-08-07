package demo.lol.com.loldemo.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class VideoBean {


    /**
     * id : 51757
     * title : lol英雄联盟五周年狂欢盛典 门票售卖开启
     * updateTime : 1470059056
     * img : http://img.demaxiya.com/uploads/allimg/160801/1-160P12153260-L.jpg
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String title;
        private String updateTime;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}

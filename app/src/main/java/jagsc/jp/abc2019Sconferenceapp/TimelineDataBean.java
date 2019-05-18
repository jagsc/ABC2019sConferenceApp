package jagsc.jp.abc2019Sconferenceapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TimelineDataBean {
    public String version;
    public List<TimelineData> data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<TimelineData> getData() {
        return data;
    }

    public void setData(List<TimelineData> data) {
        this.data = data;
    }

    public class TimelineData {
        String itemID;
        String favo;
        String title;
        String body;
        List<presenterIcons> presenterIcons;
        List<presenterNames> presenterNames;
        List<belongs> belongs;
        List<slideUrls> slideUrls;
        String place;
        String time;
        String category;
        List<tags> tags;

        public String getItemID() {
            return itemID;
        }

        public void setItemID(String itemID) {
            this.itemID = itemID;
        }

        public String getFavo() {
            return favo;
        }

        public void setFavo(String favo) {
            this.favo = favo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<presenterIcons> getPresenterIcons() {
            return presenterIcons;
        }

        public void setPresenterIcons(List<presenterIcons> presenterIcons) {
            this.presenterIcons = presenterIcons;
        }

        public List<TimelineDataBean.presenterNames> getPresenterNames() {
            return presenterNames;
        }

        public void setPresenterNames(List<TimelineDataBean.presenterNames> presenterNames) {
            this.presenterNames = presenterNames;
        }

        public List<TimelineDataBean.belongs> getBelongs() {
            return belongs;
        }

        public void setBelongs(List<TimelineDataBean.belongs> belongs) {
            this.belongs = belongs;
        }

        public List<slideUrls> getSlideUrls() {
            return slideUrls;
        }

        public void setSlideUrls(List<slideUrls> slideUrls) {
            this.slideUrls = slideUrls;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<TimelineDataBean.tags> getTags() {
            return tags;
        }

        public void setTags(List<TimelineDataBean.tags> tags) {
            this.tags = tags;
        }
    }

    public class presenterIcons {
        String iconUrl;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }

    public class slideUrls {
        String slideurl;

        public String getSlideurl() {
            return slideurl;
        }

        public void setSlideurl(String slideurl) {
            this.slideurl = slideurl;
        }
    }

    public class presenterNames {
        String presenter;

        public String getPresenter() {
            return presenter;
        }

        public void setPresenter(String presenter) {
            this.presenter = presenter;
        }
    }

    public class belongs {
        String belong;

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }
    }

    public class tags {
        String tag;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}

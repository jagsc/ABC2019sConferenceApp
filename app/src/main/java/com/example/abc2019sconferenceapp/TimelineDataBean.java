package com.example.abc2019sconferenceapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TimelineDataBean {
    //TODO どんなAPIになるかわからないので、暫定。API完成後に変える

    String itemID;
    String favo;
    String search;
    String title;
    List<presenterNames> presenterNames;
    List<belongs> belongs;
    String place;
    String time;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<TimelineDataBean.tags> getTags() {
        return tags;
    }

    public void setTags(List<TimelineDataBean.tags> tags) {
        this.tags = tags;
    }

    public class presenterNames {
        String presenter1;
        String Presenter2;

        public String getPresenter1() {
            return presenter1;
        }

        public void setPresenter1(String presenter1) {
            this.presenter1 = presenter1;
        }

        public String getPresenter2() {
            return Presenter2;
        }

        public void setPresenter2(String presenter2) {
            Presenter2 = presenter2;
        }
    }

    public class belongs {
        String belong1;
        String belong2;

        public String getBelong1() {
            return belong1;
        }

        public void setBelong1(String belong1) {
            this.belong1 = belong1;
        }

        public String getBelong2() {
            return belong2;
        }

        public void setBelong2(String belong2) {
            this.belong2 = belong2;
        }
    }

    public class tags {
        String tag1;
        String tag2;
        String tag3;

        public String getTag1() {
            return tag1;
        }

        public void setTag1(String tag1) {
            this.tag1 = tag1;
        }

        public String getTag2() {
            return tag2;
        }

        public void setTag2(String tag2) {
            this.tag2 = tag2;
        }

        public String getTag3() {
            return tag3;
        }

        public void setTag3(String tag3) {
            this.tag3 = tag3;
        }
    }


}

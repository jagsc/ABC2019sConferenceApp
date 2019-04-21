package com.example.abc2019sconferenceapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TimelineDataBean {
    //TODO どんなAPIになるかわからないので、暫定。API完成後に変える
    public List<TimelineData> data;

    public List<TimelineData> getData() {
        return data;
    }

    public void setData(List<TimelineData> data) {
        this.data = data;
    }

    public class TimelineData {
        String itemID;
        String favo;
        String search;
        String title;
        String body;
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

        public List<TimelineDataBean.tags> getTags() {
            return tags;
        }

        public void setTags(List<TimelineDataBean.tags> tags) {
            this.tags = tags;
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

package jagsc.jp.abc2019Sconferenceapp;

import java.util.List;

public class BazzarDataBean {
    public String version;
    public List<BazzarData> data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<BazzarData> getData() {
        return data;
    }

    public void setData(List<BazzarData> data) {
        this.data = data;
    }

    public class BazzarData {
        String boothNum;
        String title;
        String body;
        String companyName;
        String companyIcon;

        public String getBoothNum() {
            return boothNum;
        }

        public void setBoothNum(String boothNum) {
            this.boothNum = boothNum;
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

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyIcon() {
            return companyIcon;
        }

        public void setCompanyIcon(String companyIcon) {
            this.companyIcon = companyIcon;
        }
    }
}

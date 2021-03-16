package com.dvm.evyap.thingworxrestconsumer.model;

public class ThingworxInfo {

    private String appkey ;
    private String adminKey ;
    private String url ;
    private String kvkkVersion;

    public String getAppkey() {
        return appkey;
    }

    public String getUrl() {
        return url;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKvkkVersion() {
        return kvkkVersion;
    }

    public void setKvkkVersion(String kvkkVersion) {
        this.kvkkVersion = kvkkVersion;
    }
}

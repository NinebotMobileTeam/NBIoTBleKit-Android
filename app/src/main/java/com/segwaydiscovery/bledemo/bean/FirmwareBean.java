package com.segwaydiscovery.bledemo.bean;

/**
 * description
 *
 * @author sen
 * @version 1.0
 * @since 2021-05-28
 */
public class FirmwareBean {

    private String url;
    private String name;

    public FirmwareBean(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

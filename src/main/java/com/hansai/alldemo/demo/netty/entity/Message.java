package com.hansai.alldemo.demo.netty.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class Message implements Serializable{

    private final Charset charset = Charset.forName("utf-8");

    private Integer type;

    private String deviceid;

    private Set<String> body = new HashSet<String>();

    public Message() {
    }

    public Message(Integer type, String deviceid, Set<String> body) {
        this.type = type;
        this.deviceid = deviceid;
        this.body = body;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Set<String> getBody() {
        return body;
    }

    public void setBody(Set<String> body) {
        this.body = body;
    }
}

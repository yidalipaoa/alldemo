package com.hansai.alldemo.demo.netty.entity;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/3
 * @time: 15:53
 */
public class MessageProtocol {
    //dcba 4个字节
    String magic;
    //body长度    4个字节
    int bodyLength;
    //类型    2个字节
    short type;
    //14个字节
    String deviceId;

    //日志长度
    int logLength;
    //日志
    String log;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getLogLength() {
        return logLength;
    }

    public void setLogLength(int logLength) {
        this.logLength = logLength;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

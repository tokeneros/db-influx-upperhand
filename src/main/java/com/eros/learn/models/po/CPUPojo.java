package com.eros.learn.models.po;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * @Author: eros
 * @Description:
 * @Date: Created in 2019/8/9 11:55
 * @Version: 1.0
 * @Modified By:
 */
@Measurement(name = "cpu")
public class CPUPojo {

    @Column(name = "time")
    private Instant time;

    @Column(name = "host", tag = true)
    private String hostname;

    @Column(name = "region", tag = true)
    private String region;

    @Column(name = "idle")
    private Double idle;

    @Column(name = "happydevop")
    private Boolean happydevop;

    @Column(name = "uptimesecs")
    private Long uptimeSecs;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getIdle() {
        return idle;
    }

    public void setIdle(Double idle) {
        this.idle = idle;
    }

    public Boolean getHappydevop() {
        return happydevop;
    }

    public void setHappydevop(Boolean happydevop) {
        this.happydevop = happydevop;
    }

    public Long getUptimeSecs() {
        return uptimeSecs;
    }

    public void setUptimeSecs(Long uptimeSecs) {
        this.uptimeSecs = uptimeSecs;
    }

    @Override
    public String toString() {
        return "CPUPojo{" +
                "time=" + time +
                ", hostname='" + hostname + '\'' +
                ", region='" + region + '\'' +
                ", idle=" + idle +
                ", happydevop=" + happydevop +
                ", uptimeSecs=" + uptimeSecs +
                '}';
    }
}

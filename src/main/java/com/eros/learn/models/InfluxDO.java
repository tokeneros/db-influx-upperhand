package com.eros.learn.models;

import com.eros.learn.constant.Constants;

import java.util.Properties;

/**
 * @Author: eros
 * @Description: 连接 InfluxDB的 参数
 * @Date: Created in 2019/8/8 14:47
 * @Version: 1.0
 * @Modified By:
 */
public class InfluxDO {

    private String url;

    private String username;

    private String password;

    public InfluxDO(Properties applicationProperties) {
        this(applicationProperties.getProperty(Constants.PROPERTIES_KEY_INFLUXDB_URL,"http://localhost:8086")
        ,applicationProperties.getProperty(Constants.PROPERTIES_KEY_INFLUXDB_USERNAME,"root")
        ,applicationProperties.getProperty(Constants.PROPERTIES_KEY_INFLUXDB_PASSWORD,"root"));
    }

    private InfluxDO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

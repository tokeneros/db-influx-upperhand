package com.eros.learn.models.manager;

import com.eros.learn.models.InfluxDO;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

/**
 * @Author: eros
 * @Description:
 * @Date: Created in 2019/8/8 14:33
 * @Version: 1.0
 * @Modified By:
 */
public class InfluxDBManager {

    /**
     * 自身实例
     */
    private static InfluxDBManager influxDBManager = new InfluxDBManager();

    public static InfluxDBManager getInstance(){
        return influxDBManager;
    }

    private InfluxDB influxDB = null;

    public InfluxDBManager() {
    }

    public InfluxDB getInfluxDB() {
        return influxDB;
    }

    public void initialize(InfluxDO influxDO){
        influxDB = InfluxDBFactory.connect(influxDO.getUrl(), influxDO.getUsername(), influxDO.getPassword());
    }
}

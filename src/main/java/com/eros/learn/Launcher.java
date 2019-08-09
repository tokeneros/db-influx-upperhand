package com.eros.learn;

import com.eros.learn.constant.Constants;
import com.eros.learn.models.*;
import com.eros.learn.models.manager.InfluxDBManager;
import com.eros.learn.models.po.CPUPojo;
import com.eros.learn.service.IInfluxDBService;
import com.eros.learn.service.impl.InfluxDBServiceImpl;
import org.influxdb.annotation.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: eros
 * @Description: 实现 java 使用 influxDB 的常用的方法
 * @Date: Created in 2019/8/8 14:31
 * @Version: 1.0
 * @Modified By:
 */
public class Launcher {

    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private Properties applicationProperties;

    private IInfluxDBService influxDBService;

    public Launcher() {
        // 加载 properties
        loadConfig();
        // 配置 db
        loadDB();

        influxDBService = new InfluxDBServiceImpl(InfluxDBManager.getInstance().getInfluxDB());
    }

    private void loadDB() {
        InfluxDO influxDO = new InfluxDO(this.applicationProperties);
        InfluxDBManager.getInstance().initialize(influxDO);
    }

    private void loadConfig() {
        String appConfigPath = Constants.ROOT_PATH + Constants.CONF_PROPERTIES;
        this.applicationProperties = new Properties();
        try {
            this.applicationProperties.load(new FileReader(new File(appConfigPath)));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String dbName = "javaTest";
        String rpName = "rpTest";
        String cqName = "cqTest";
        String measurement = "cpu";

        // 这是 最简单的一版示例
        // DDL
        // 创建和删除 没有返回结果，只能判断是否存在错误
        // 重复新增，不报错
//        new Launcher().influxDBService.createDB(dbName);
//        new Launcher().influxDBService.dropDB(dbName);
//        new Launcher().influxDBService.showDB();

        // RP
//        RetentionPolicyDO createDO = new RetentionPolicyDO();
//        createDO.initCreate(rpName,dbName,"30d",1,true);
//        RetentionPolicyDO updateDO = new RetentionPolicyDO();
//        updateDO.initUpdate(rpName,dbName,"30w",true);
//        RetentionPolicyDO dropDO = new RetentionPolicyDO();
//        dropDO.initDrop(rpName,dbName);
//        new Launcher().influxDBService.createRP(createDO);
//        new Launcher().influxDBService.showRP("javaTest");
//        new Launcher().influxDBService.updateRP(updateDO);
//        new Launcher().influxDBService.showRP("javaTest");
//        new Launcher().influxDBService.dropRP(dropDO);
//        new Launcher().influxDBService.showRP(dbName);

        // CQ
//        ContinuousQueriesDO createCQDO = new ContinuousQueriesDO();
//        createCQDO.initCreate(cqName,dbName,"mean(\"uptimesecs\")","cqTest180s",measurement,"180s");
//        ContinuousQueriesDO dropCQDO = new ContinuousQueriesDO();
//        dropCQDO.initDrop(cqName,dbName);
//        new Launcher().influxDBService.createCQ(createCQDO);
//        new Launcher().influxDBService.showCQ();
//        new Launcher().influxDBService.dropCQ(dropCQDO);

        // 1. 数据形式 为 Map
        // INSERT
//        Map<String ,String> tags = new HashMap<>();
//        tags.put("host","192.168.1.88");
//        tags.put("region","hefei");
//        Map<String ,Object> fields = new HashMap<>();
//        fields.put("idle",1111.1111D);
//        fields.put("happydevop",true);
//        fields.put("uptimesecs",2222222L);
//        MeasurementDO measurementDO = new MeasurementDO.Builder()
//                .name(measurement)
//                .dbAndRqName(dbName,rpName)
//                .tags(tags)
//                .fields(fields)
//                .build();
//        new Launcher().influxDBService.insert(measurementDO);

        // 数据形式 为 Pojo

        // SELECT
//        QueryDO queryDO = new QueryDO.Builder()
//                .dbName(dbName)
//                .sql("SELECT * FROM measurementTest")
//                .build();
//        new Launcher().influxDBService.select(queryDO);


        // 2. PoJo
        // INSERT
//        CPUPojo cpuPojo = new CPUPojo();
//        cpuPojo.setHostname("192.168.1.200");
//        cpuPojo.setRegion("Shanghai");
//        cpuPojo.setIdle(1111111111111111.1111D);
//        cpuPojo.setHappydevop(false);
//        cpuPojo.setUptimeSecs(22222222222222222L);
//
//        MeasurementDO measurementDO = new MeasurementDO.Builder()
//                .dbAndRqName(dbName,rpName)
//                .obj(cpuPojo)
//                .build();
//        new Launcher().influxDBService.insertPojo(measurementDO);

        // SELECT
//        QueryDO queryDO = new QueryDO.Builder()
//                .dbName(dbName)
//                .clazz(CPUPojo.class)
//                .sql("SELECT * FROM cpu")
//                .build();
//        new Launcher().influxDBService.selectPojo(queryDO);


    }
}

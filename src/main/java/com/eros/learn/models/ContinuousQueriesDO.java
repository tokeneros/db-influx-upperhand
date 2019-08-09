package com.eros.learn.models;

/**
 * @Author: eros
 * @Description: CQ 所需的参数
 * @Date: Created in 2019/8/8 18:09
 * @Version: 1.0
 * @Modified By:
 */
public class ContinuousQueriesDO {

    /**
     * 连续查询的名称
     */
    private String cqName;

    /**
     * 具体的数据库名称
     */
    private String dbName;

    /**
     * InfluxDB 提供的一些函数
     * mean - 平均值 min - 最小值 max - 最大值 例如 : mean("field")
     */
    private String function;

    /**
     * 存新数据的表名称
     */
    private String targetMeasurement;

    /**
     * 当前表名称
     */
    private String currentMeasurement;

    /**
     * 时间范围
     * d - 天  h - 小时 m - 分钟 s - 秒 w - 星期  例如 30m
     */
    private String dateRange;

    public void initCreate(String cqName, String dbName, String function, String targetMeasurement, String currentMeasurement, String dateRange) {
        this.cqName = cqName;
        this.dbName = dbName;
        this.function = function;
        this.targetMeasurement = targetMeasurement;
        this.currentMeasurement = currentMeasurement;
        this.dateRange = dateRange;
    }

    public void initDrop(String cqName, String dbName) {
        this.cqName = cqName;
        this.dbName = dbName;
    }

    public String getCqName() {
        return cqName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFunction() {
        return function;
    }

    public String getTargetMeasurement() {
        return targetMeasurement;
    }

    public String getCurrentMeasurement() {
        return currentMeasurement;
    }

    public String getDateRange() {
        return dateRange;
    }
}

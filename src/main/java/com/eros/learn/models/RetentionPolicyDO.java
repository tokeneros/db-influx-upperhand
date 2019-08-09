package com.eros.learn.models;

/**
 * @Author: eros
 * @Description: RP 所需的参数
 * @Date: Created in 2019/8/8 16:42
 * @Version: 1.0
 * @Modified By:
 */
public class RetentionPolicyDO {

    /**
     * 策略名称
     */
    private String rpName;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 保存天数，超过将删除
     * d - 天  h - 小时 m - 分钟 s - 秒 w - 星期  例如 30d
     */
    private String duration;

    /**
     * 副本个数
     */
    private int replication;

    /**
     * 是否为默认的策略
     */
    private boolean isDefault;

    /**
     * 新增 所需参数
     */
    public void initCreate(String rpName, String dbName, String duration, int replication, boolean isDefault) {
        this.rpName = rpName;
        this.dbName = dbName;
        this.duration = duration;
        this.replication = replication;
        this.isDefault = isDefault;
    }

    /**
     * 修改 所需参数
     */
    public void initUpdate(String rpName, String dbName, String duration, boolean isDefault) {
        this.rpName = rpName;
        this.dbName = dbName;
        this.duration = duration;
        this.isDefault = isDefault;
    }

    /**
     * 删除 所需参数
     */
    public void initDrop(String rpName, String dbName) {
        this.rpName = rpName;
        this.dbName = dbName;
    }

    public String getRpName() {
        return rpName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDuration() {
        return duration;
    }

    public int getReplication() {
        return replication;
    }

    public boolean isDefault() {
        return isDefault;
    }
}

package com.eros.learn.models;

import java.util.Map;

/**
 * @Author: eros
 * @Description: 表 插入 封装的参数
 * @Date: Created in 2019/8/9 9:53
 * @Version: 1.0
 * @Modified By:
 */
public class MeasurementDO {

    private String dbName;

    private String rqName;

    private boolean dbAndRqInitialized = false;

    /**
     * 表名称
     */
    private String name;

    /**
     * 表字段
     */
    private PointsDO points;

    private Object obj;

    private Class clazz;

    private MeasurementDO() {
    }

    private MeasurementDO(Builder builder) {
        this.name = builder.getName();
        this.dbAndRqInitialized = builder.isDbAndRqInitialized();
        if(this.dbAndRqInitialized){
            this.dbName = builder.getDbName();
            this.rqName = builder.getRqName();
        }
        this.points = new PointsDO();
        this.points.setFields(builder.getFields());
        this.points.setTags(builder.getTags());
        this.obj = builder.getObj();
        this.clazz = this.obj.getClass();
    }

    public boolean isDbAndRqInitialized() {
        return dbAndRqInitialized;
    }

    public String getRqName() {
        return rqName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getName() {
        return name;
    }

    public PointsDO getPoints() {
        return points;
    }

    public Object getObj() {
        return obj;
    }

    public Class getClazz() {
        return clazz;
    }

    /**
     * Builder 之后还可以存 field
     * @param key
     * @param value
     */
    public void addField(String key, String value){
        this.points.getFields().put(key, value);
    }

    /**
     * Builder 之后还可以存 tag
     * @param key
     * @param value
     */
    public void addTag(String key, String value){
        this.points.getTags().put(key, value);
    }

    public static class Builder<T>{

        private String dbName;

        private String rqName;

        private boolean dbAndRqInitialized = false;

        private String name;

        private Map<String, String> tags;

        private Map<String, Object> fields;

        private T obj;

        public Builder() {
        }

        public boolean isDbAndRqInitialized() {
            return dbAndRqInitialized;
        }

        private String getRqName() {
            return rqName;
        }

        private String getDbName() {
            return dbName;
        }

        private String getName() {
            return name;
        }

        private Map<String, String> getTags() {
            return tags;
        }

        private Map<String, Object> getFields() {
            return fields;
        }

        private T getObj() {
            return obj;
        }

        public Builder dbAndRqName(String dbName, String rqName){
            this.dbName = dbName;
            this.rqName = rqName;
            this.dbAndRqInitialized = true;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder tags(Map<String, String> tags){
            this.tags = tags;
            return this;
        }

        public Builder fields(Map<String, Object> fields){
            this.fields = fields;
            return this;
        }

        public Builder obj(T obj){
            this.obj = obj;
            return this;
        }

        public MeasurementDO build(){
            return new MeasurementDO(this);
        }


    }

}

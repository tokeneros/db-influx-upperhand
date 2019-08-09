package com.eros.learn.models;

/**
 * @Author: eros
 * @Description: 查询 封装的参数
 * @Date: Created in 2019/8/9 10:27
 * @Version: 1.0
 * @Modified By:
 */
public class QueryDO {

    private String sql;

    private String dbName;

    private Class clazz;

    private int limit;

    private QueryDO() {
    }

    private QueryDO(Builder builder) {
        this.sql = builder.getSql();
        this.dbName = builder.getDbName();
        this.clazz = builder.getClazz();
        this.limit = builder.getLimit();
    }

    public String getSql() {
        return sql;
    }

    public String getDbName() {
        return dbName;
    }

    public Class getClazz() {
        return clazz;
    }

    public int getLimit() {
        return limit;
    }

    public static class Builder{

        private String sql;

        private String dbName;

        private int limit = 20;

        private Class clazz;

        private String getSql() {
            return sql;
        }

        private String getDbName() {
            return dbName;
        }

        private Class getClazz() {
            return clazz;
        }

        private int getLimit() {
            return limit;
        }

        public Builder sql(String sql){
            this.sql = sql;
            return this;
        }

        public Builder dbName(String dbName){
            this.dbName = dbName;
            return this;
        }

        public Builder clazz(Class clazz){
            this.clazz = clazz;
            return this;
        }

        public Builder limit(int limit){
            this.limit = limit;
            return this;
        }

        public QueryDO build(){
            return new QueryDO(this);
        }

    }

}

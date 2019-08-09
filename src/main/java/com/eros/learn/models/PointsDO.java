package com.eros.learn.models;

import java.util.Map;

/**
 * @Author: eros
 * @Description: 行数据
 * @Date: Created in 2019/8/9 9:22
 * @Version: 1.0
 * @Modified By:
 */
public class PointsDO {

    /**
     * 索引的属性
     */
    private Map<String, String> tags;

    /**
     * 记录值， 存储的数据
     */
    private Map<String, Object> fields;

    public Map<String, String> getTags() {
        return tags;
    }

    protected void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    protected void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
}

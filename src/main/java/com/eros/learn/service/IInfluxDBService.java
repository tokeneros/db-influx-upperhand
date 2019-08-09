package com.eros.learn.service;

import com.eros.learn.models.ContinuousQueriesDO;
import com.eros.learn.models.MeasurementDO;
import com.eros.learn.models.QueryDO;
import com.eros.learn.models.RetentionPolicyDO;

/**
 * @Author: eros
 * @Description:
 * @Date: Created in 2019/8/8 15:49
 * @Version: 1.0
 * @Modified By:
 */
public interface IInfluxDBService {

    /**
     * 创建数据库
     * @param dbName 数据库名称
     * @return
     */
    boolean createDB(String dbName);

    /**
     * 查看 当前账号下 有哪些数据库
     * @return
     */
    Object showDB();

    /**
     * 删除数据库
     * @param dbName 数据库名称
     * @return
     */
    boolean dropDB(String dbName);

    /**
     * 创建数据保存策略
     * @param retentionPolicyDO 组装 sql 所需的参数（全部必须）
     * @return
     */
    boolean createRP(RetentionPolicyDO retentionPolicyDO);

    /**
     * 删除数据保存策略
     * @param retentionPolicyDO 组装 sql 所需的参数（以下是必须的）
     *                          rpName
     *                          dbName
     * @return
     */
    boolean dropRP(RetentionPolicyDO retentionPolicyDO);

    /**
     * 修改数据保存策略
     * @param retentionPolicyDO 组装 sql 所需的参数（除了以下这一个全部必须）
     *                          replication
     * @return
     */
    boolean updateRP(RetentionPolicyDO retentionPolicyDO);

    /**
     * 查看当前 数据库的所有保存策略
     * @param dbName
     * @return
     */
    Object showRP(String dbName);

    /**
     * 创建连续查询
     * @return
     */
    boolean createCQ(ContinuousQueriesDO continuousQueriesDO);

    /**
     * 删除连续查询
     * @return
     */
    boolean dropCQ(ContinuousQueriesDO continuousQueriesDO);

    /**
     * 查看所有数据库的连续查询
     * @return
     */
    Object showCQ();

    /********************************* Map 组装 begin***************************************/
    /**
     * 插入 数据
     * @param measurementDO 包含插入的 数据信息
     * @return
     */
    boolean insert(MeasurementDO measurementDO);

    /**
     * 查询 数据
     * @return
     */
    Object select(QueryDO queryDO);
    /********************************* Map 组装 end***************************************/

    /********************************* Pojo 组装 begin***************************************/
    /**
     * 插入 数据 以Pojo的方式
     * @param measurementDO 包含插入的 数据信息
     * @return
     */
    boolean insertPojo(MeasurementDO measurementDO);

    /**
     * 查询数据, 并将其映射到 对应的pojo 中
     * @param queryDO
     * @return
     */
    Object selectPojo(QueryDO queryDO);
    /********************************* Pojo 组装 end***************************************/

}

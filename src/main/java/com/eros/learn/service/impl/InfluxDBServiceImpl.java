package com.eros.learn.service.impl;

import com.eros.learn.models.ContinuousQueriesDO;
import com.eros.learn.models.MeasurementDO;
import com.eros.learn.models.QueryDO;
import com.eros.learn.models.RetentionPolicyDO;
import com.eros.learn.models.po.CPUPojo;
import com.eros.learn.service.IInfluxDBService;
import com.eros.learn.utils.CollectionUtils;
import com.eros.learn.utils.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: eros
 * @Description:
 * @Date: Created in 2019/8/8 15:50
 * @Version: 1.0
 * @Modified By:
 */
public class InfluxDBServiceImpl implements IInfluxDBService {

    private static final Logger logger = LoggerFactory.getLogger(InfluxDBServiceImpl.class);;

    private final InfluxDB influxDB;

    public InfluxDBServiceImpl(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    private QueryResult execute(Query query){
        return influxDB.query(query);
    }

    /**
     * @Description: 解析 influx返回的结果集
     * @Author: eros
     * @Date: 2019/8/8 16:30
     * @param: null
     * @Return:
     * @Exception:
     */
    private void analysis(QueryResult queryResult){
        List<QueryResult.Result> resultList = queryResult.getResults();
        if(CollectionUtils.isNotEmpty(resultList)) {
            for (QueryResult.Result result : resultList) {
                if (result.hasError()) {
                    logger.error(result.getError());
                } else {
                    List<QueryResult.Series> series = result.getSeries();
                    if(CollectionUtils.isNotEmpty(series)) {
                        for (QueryResult.Series serie : series) {
                            logger.info(String.format("%s", new Object[]{serie.toString()}));
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean createDB(String dbName) {
        QueryResult queryResult = influxDB.query(new Query("CREATE DATABASE " + dbName));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public Object showDB() {
        QueryResult queryResult = influxDB.query(new Query("SHOW DATABASES"));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        analysis(queryResult);
        return true;
    }

    @Override
    public boolean dropDB(String dbName) {
        QueryResult queryResult = influxDB.query(new Query("DROP DATABASE " + dbName));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public boolean createRP(RetentionPolicyDO retentionPolicyDO) {
        String sql = "CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %d";
        String formatSql = String.format(sql, new Object[]{retentionPolicyDO.getRpName(), retentionPolicyDO.getDbName(), retentionPolicyDO.getDuration(), retentionPolicyDO.getReplication()});
        if(retentionPolicyDO.isDefault()){
            formatSql += " DEFAULT";
        }
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public boolean dropRP(RetentionPolicyDO retentionPolicyDO) {
        String sql = "DROP RETENTION POLICY \"%s\" ON \"%s\"";
        String formatSql = String.format(sql, new Object[]{retentionPolicyDO.getRpName(), retentionPolicyDO.getDbName()});
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRP(RetentionPolicyDO retentionPolicyDO) {
        String sql = "ALTER RETENTION POLICY \"%s\" ON \"%s\" DURATION %s";
        String formatSql = String.format(sql, new Object[]{retentionPolicyDO.getRpName(), retentionPolicyDO.getDbName(), retentionPolicyDO.getDuration()});
        if(retentionPolicyDO.isDefault()){
            formatSql += " DEFAULT";
        }
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public Object showRP(String dbName) {
        String sql = "SHOW RETENTION POLICIES ON \"%s\"";
        String formatSql = String.format(sql, new Object[]{dbName});
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        analysis(queryResult);
        return true;
    }

    @Override
    public boolean createCQ(ContinuousQueriesDO continuousQueriesDO) {
        String sql = "CREATE CONTINUOUS QUERY \"%s\" ON \"%s\" BEGIN SELECT %s INTO \"%s\" FROM \"%s\" GROUP BY time(%s) END";
        String formatSql = String.format(sql, new Object[]{continuousQueriesDO.getCqName(), continuousQueriesDO.getDbName()
                , continuousQueriesDO.getFunction(), continuousQueriesDO.getTargetMeasurement()
                , continuousQueriesDO.getCurrentMeasurement(), continuousQueriesDO.getDateRange()});
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public boolean dropCQ(ContinuousQueriesDO continuousQueriesDO) {
        String sql = "DROP CONTINUOUS QUERY \"%s\" ON \"%s\"";
        String formatSql = String.format(sql, new Object[]{continuousQueriesDO.getCqName(), continuousQueriesDO.getDbName()});
        QueryResult queryResult = influxDB.query(new Query(formatSql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        return true;
    }

    @Override
    public Object showCQ() {
        String sql = "SHOW CONTINUOUS QUERIES";
        QueryResult queryResult = influxDB.query(new Query(sql));
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        analysis(queryResult);
        return true;
    }

    @Override
    public boolean insert(MeasurementDO measurementDO) {
        Point point = null;
        Point.Builder builder = Point.measurement(measurementDO.getName());
        if(Objects.nonNull(measurementDO.getPoints())){
            Map<String, String> tags = measurementDO.getPoints().getTags();
            Map<String, Object> fields = measurementDO.getPoints().getFields();
            if(CollectionUtils.isNotEmpty(tags)){
                builder.tag(tags);
            }
            if(CollectionUtils.isNotEmpty(fields)){
                builder.fields(fields);
            }
            point = builder.build();
        }
        if(Objects.nonNull(point)){
            if(measurementDO.isDbAndRqInitialized()){
                // 指定 dbName 和 rqName 的插入
                influxDB.write(measurementDO.getDbName(),measurementDO.getRqName(), point);
            } else {
                // 不知道会插入到哪里
                influxDB.write(point);
            }
            return true;
        }
        return false;
    }

    @Override
    public Object select(QueryDO queryDO) {
        Query query = null;
        String dbName = queryDO.getDbName();
        if(StringUtils.isNotBlank(dbName)){
            query = new Query(queryDO.getSql(), dbName);
        } else {
            query = new Query(queryDO.getSql());
        }
        // TODO 这里起了一个线程 处理 对应的信息,后面看 官方示例在执行操作
//        influxDB.query(query, queryDO.getLimit(), queryResult -> analysis(queryResult));
        QueryResult queryResult = influxDB.query(query);
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        analysis(queryResult);
        return true;
    }

    @Override
    public boolean insertPojo(MeasurementDO measurementDO) {
        Point point = Point.measurementByPOJO(measurementDO.getClazz())
                .addFieldsFromPOJO(measurementDO.getObj()).build();
        if(Objects.nonNull(point)){
            if(measurementDO.isDbAndRqInitialized()){
                // 指定 dbName 和 rqName 的插入
                influxDB.write(measurementDO.getDbName(),measurementDO.getRqName(), point);
            } else {
                // 不知道会插入到哪里
                influxDB.write(point);
            }
            return true;
        }
        return false;
    }

    @Override
    public Object selectPojo(QueryDO queryDO) {
        Query query = null;
        String dbName = queryDO.getDbName();
        if(StringUtils.isNotBlank(dbName)){
            query = new Query(queryDO.getSql(), dbName);
        } else {
            query = new Query(queryDO.getSql());
        }
        QueryResult queryResult = influxDB.query(query);
        if(queryResult.hasError()){
            logger.error(queryResult.getError());
            return false;
        }
        // Influx 提供的 将 数据 转成对应的POJO
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        Class clazz = queryDO.getClazz();
        List list = resultMapper.toPOJO(queryResult,clazz);
        for(Object obj : list){
            CPUPojo cpuPojo = (CPUPojo) obj;
            logger.info(cpuPojo.toString());
        }
        return true;
    }

}

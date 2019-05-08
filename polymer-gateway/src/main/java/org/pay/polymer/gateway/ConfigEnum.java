package org.pay.polymer.gateway;

/**
 * 常量（枚举）
 * @program: semitransfer
 * @author: Mr.Yang
 * @date: 2018-11-29 14:22
 * @version:2.0
 **/
public enum ConfigEnum {

    /**
     * mysql驱动
     */
    MYSQL_JDBC("Mysql", "com.mysql.cj.jdbc.Driver"),
    /**
     * oracle驱动
     */
    ORACLE_JDBC("Oracle", "oracle.jdbc.driver.OracleDriver"),
    /**
     * db2驱动
     */
    DB2_JDBC("DB2", "com.ibm.db2.jcc.DB2Driver"),
    /**
     * mariadb驱动
     */
    MARIADB_JDBC("Mariadb", "org.mariadb.jdbc.Driver"),
    /**
     * postgresql驱动
     */
    POSTGRESQL_JDBC("Postgresql", "org.postgresql.Driver"),

    /**
     * 缺少nacos依赖
     */
    NACOS_MISSING_DEPENDENCY_ERROR("Can not find nacos dependency", "找不到Nacos依赖"),

    /**
     * nacos处理过程成功
     */
    NACOS_PROCESS_SUCCESS("success", "成功"),

    /**
     * 处理过程成功
     */
    PROCESS_SUCCESS("success", "成功"),

    /**
     * 处理过程失败
     */
    PROCESS_FAIL("fail", "失败"),
    /**
     * 转换错误
     */
    CONVERT_ERROR("Conversion exception check request parameter", "转换异常检查请求参数"),

    /**
     * 数据非jsonarray
     */
    CURRENT_NOT_IN_JSONARRAY("Current data is not in JSONArray format", "当前数据不是JSONArray格式"),

    /**
     * redis中的key(Table-To-Entity)不存在值
     */
    REDIS_IN_KEY_TABLE_TO_ENTITY_EMPTY("No corresponding value found in Redis,key->(Table-To-Entity)", "redis中的key(Table-To-Entity)，找不到对应的值"),

    /**
     * redis中的key(Table-To-Entity)不存在
     */
    REDIS_NOT_EXIST_TABLE_TO_ENTITY("The corresponding key could not be found in Redis,key->(Table-To-Entity) ", "redis中的key(Table-To-Entity)不存在"),

    /**
     * redis中的key(Response-Code)不存在值
     */
    REDIS_IN_KEY_RESPONSE_CODE_EMPTY("No corresponding value found in Redis,key->(Response-Code)", "redis中的key(Response-Code)，找不到对应的值"),

    /**
     * redis中的key(Response-Code)不存在
     */
    REDIS_NOT_EXIST_RESPONSE_CODE("The corresponding key could not be found in Redis,key->(Response-Code)", "redis中的key(Response-Code)不存在"),

    /**
     * nacos发布配置错误
     */
    NACOS_PULISH_CONFIG_ERROR("Nacos release configuration failed", "Nacos发布配置失败"),

    /**
     * 在nacos配置中心找不到数据
     */
    NACOS_MISSING_DATA_ERROR("No data found in nacos configuration center", "在Nacos配置中心找不到数据"),

    /**
     * nacos配置缺失
     */
    NACOS_MISSING_CONFIG_ERROR("Nacos is missing configuration information", "Nacos缺少配置信息");


    /**
     * 值
     */
    String value;

    /**
     * 值
     */
    String key;

    /**
     * 构造方法
     *
     * @param key   key
     * @param value 值
     */
    ConfigEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

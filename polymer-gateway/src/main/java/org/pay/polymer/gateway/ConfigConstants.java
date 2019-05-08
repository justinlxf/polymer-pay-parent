package org.pay.polymer.gateway;

/**
 * 常量类（接口形式）
 *
 * @program: semitransfer
 * @author: Mr.Yang
 * @date: 2018-11-29 14:22
 * @version:2.0
 **/
public interface ConfigConstants {

    /**
     * 0
     */
    int NUM_ZERO = 0;
    /**
     * 1
     */
    int NUM_ONE = 1;

    /**
     * 2
     */
    int NUM_TWO = 2;

    /**
     * 3
     */
    int NUM_THREE = 3;

    /**
     * 4
     */
    int NUM_FOUR = 4;

    /**
     * 5
     */
    int NUM_FIVE = 5;
    /**
     * 6
     */
    int NUM_SIX = 6;

    /**
     * 7
     */
    int NUM_SEVEN = 7;

    /**
     * 8
     */
    int NUM_EIGHT = 8;

    /**
     * 9
     */
    int NUM_NINE = 9;

    /**
     * 10
     */
    int NUM_TEN = 10;


    /**
     * 20
     */
    int NUM_TWENTY = 20;


    /**
     * 30
     */
    int NUM_THIRTH = 30;


    /**
     * 40
     */
    int NUM_FORTH = 40;


    /**
     * 50
     */
    int NUM_FIFTY = 50;


    /**
     * 60
     */
    int NUM_SIXTY = 60;

    /**
     * 70
     */
    int NUM_SEVENTY = 70;

    /**
     * 80
     */
    int NUM_EIGHTY = 80;

    /**
     * 90
     */
    int NUM_NINETY = 90;

    /**
     * 100
     */
    int NUM_HUNDRED = 100;

    /**
     * 列表分页
     */
    String FIELD_LIST = "list";


    /**
     * get
     */
    String FIELD_GET = "get";


    /**
     * 新增
     */
    String FIELD_SAVE = "save";


    /**
     * 更新
     */
    String FIELD_UPDATE = "update";

    /**
     * 删除
     */
    String FIELD_REMOVE = "remove";

    /**
     * 导入
     */
    String FIELD_IMPORT = "import";

    /**
     * 跳转
     */
    String FIELD_JUMP = "jump";


    /**
     * 导出
     */
    String FIELD_EXPORT = "export";

    /**
     * BaseRquestAndResponse路径
     */
    String BASE_REQUEST_RESPONSE = " com.semitransfer.common.api.BaseRquestAndResponse";

    /**
     * 响应码路径
     */
    String CODE_PATH = "config/responseMessage.properties";

    /**
     * NACOS默认分组
     */
    String NACOS_DEFAULT_GROUP = "DEFAULT_GROUP";

    /**
     * url管理中心
     */
    String URL_LIST = "Request_URL_LIST";

    /**
     * 配置首页
     */
    String CONFIG_INDEX = "generator/config";

    /**
     * jdbc连接url
     */
    String JDBC_URL = "jdbcUrl";

    /**
     * jdbc用户名
     */
    String JDBC_USER_NAME = "userName";

    /**
     * jdbc密码
     */
    String JDBC_USER_PASSWORD = "userPassword";

    /**
     * 自动创建数据库表转换实体
     */
    String TABLE_TO_ENTITY = "table/to/entity";

    /**
     * 缓存code
     */
    String PROCESS_CACHE_CODE = "process/cache/code";

    /**
     * 操作系统
     */
    String OS_NAME = "os.name";

    /**
     * WINDOWS
     */
    String WINDOWS = "windows";

    /**
     * nacos服务
     */
    String SEMITRANSFER_NACOS_PREFIX = "semitransfer.nacos.config";

    /**
     * nacos依赖路径
     */
    String NACOS_PATH = "com.alibaba.nacos.client.config.NacosConfigService";

    /**
     * redis依赖路径
     */
    String REDIS_PATH = "redis.clients.jedis.JedisPoolConfig";

    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    //###################以下为字段常量########################//
    /**
     * MYSQL
     */
    String FIELD_MYSQL = "mysql";

    /**
     * ORACLE
     */
    String FIELD_ORACLE = "oracle";


    /**
     * POSTGRESQL
     */
    String FIELD_POSTGRESQL = "postgresql";

    /**
     * DB2
     */
    String FIELD_DB2 = "db2";

    /**
     * MARIADB
     */
    String FIELD_MARIADB = "mariadb";

    /**
     * 输出路径
     */
    String FIELD_OUT_PATH = "outPath";

    /**
     * 是否启用activeRecord
     */
    String FIELD_ACTIVE_FLAG = "activeFlag";
    /**
     * 不做任何改变，原样输出
     */
    String FIELD_NO_CHANGE = "nochange";

    /**
     * 操作人
     */
    String FIELD_AUTHOR = "author";

    /**
     * 全局大写命名
     */
    String FIELD_CAPITAL_MODE = "capitalMode";

    /**
     * 表前缀
     */
    String FIELD_PREFIX = "prefix";

    /**
     * 包含表信息
     */
    String FIELD_INCLUDE_TABLES = "includeTables";

    /**
     * 排除表信息
     */
    String FIELD_EXCLUDE_TABLES = "excludeTables";

    /**
     * 父级实体class类
     */
    String FIELD_SUPER_ENTITY = "superEntityClass";


    /**
     * 父级mapper
     */
    String FIELD_SUPER_MAPPER = "superMapperClass";

    /**
     * 父级Service
     */
    String FIELD_SUPER_SERVICE = "superServiceClass";

    /**
     * 父级Service实现
     */
    String FIELD_SUPER_SERVICE_IMPL = "superServiceImplClass";

    /**
     * 父级controller
     */
    String FIELD_SUPER_CONTROLLER = "superControllerClass";

    /**
     * 实体类lombok
     */
    String FIELD_ENTITY_LOMBOK = "entityLombok";

    /**
     * Boolean类型字段是否移除is前缀处理
     */
    String FIELD_ENTITY_IS_PREFIX = "entityIsPrefix";


    /**
     * 包路径
     */
    String FIELD_BASE_PACKAGE = "basePackage";

    /**
     * xml路径
     */
    String FIELD_MAPPER_PACKAGE = "mapperPackage";
    /**
     * swagger
     */
    String FIELD_SWAGGER = "swagger";
    /**
     * file文件
     */
    String FIELD_FILE = "file";
    /**
     * 控制层名称
     */
    String FIELD_CONTROLLER_NAME = "controllerName";
    /**
     * 模型名称
     */
    String FIELD_MODEL_NAME = "modelName";
    /**
     * dao名称
     */
    String FIELD_DAO_NAME = "daoName";
    /**
     * 服务实现名称
     */
    String FIELD_IMPL_NAME = "implName";
    /**
     * 服务名称
     */
    String FIELD_SERVICE_NAME = "serviceName";
    /**
     * mapper名称
     */
    String FIELD_MAPPER_NAME = "mapperName";
    /**
     * 控制层模板
     */
    String FIELD_CONTROLLER_TEMPLET = "controllerTemplet";
    /**
     * 实体层模板
     */
    String FIELD_ENTITY_TEMPLET = "entityTemplet";
    /**
     * 模型模板
     */
    String FIELD_MODEL_TEMPLET = "modelTemplet";
    /**
     * 模型外键模板
     */
    String FIELD_MODEL_FKEY_TEMPLET = "modelKeyTemplet";
    /**
     * dao模板
     */
    String FIELD_DAO_TEMPLET = "daoTemplet";
    /**
     * 服务实现模板
     */
    String FIELD_IMPL_TEMPLET = "implTemplet";
    /**
     * 服务模板
     */
    String FIELD_SERVICE_TEMPLET = "serviceTemplet";
    /**
     * mapper模板
     */
    String FIELD_MAPPER_TEMPLET = "mapperTemplet";
    /**
     * nacos服务地址
     */
    String FIELD_SERVER_ADDR = "serverAddr";

    /**
     * 转换状态
     */
    String FIELD_CONVERT_STATUS = "status";

    /**
     * 预新增
     */
    String FIELD_PRE_ADDED = "preAdded";

    /**
     * 编码
     */
    String FIELD_CODE = "code";

    /**
     * 信息
     */
    String FIELD_MSG = "msg";


    /**
     * 表转化为实体
     */
    String FIELD_TABLE_TO_ENTITY = "Table-To-Entity";

    /**
     * 类型
     */
    String FIELD_TEPY = "type";

    /**
     * class名
     */
    String FIELD_CLASS_NAME = "className";

    /**
     * 实体类class
     */
    String FIELD_ENTITY_CLASS = "entityClass";

    /**
     * 名称
     */
    String FIELD_NAME = "name";

    /**
     * 权限
     */
    String FIELD_ACL_NAME = "aclName";
    /**
     * 链接
     */
    String FIELD_URL = "url";

    /**
     * 方法
     */
    String FIELD_METHOD = "method";

    /**
     * 方法
     */
    String FIELD_ACL_CODE = "aclCode";

    /**
     * 返回码
     */
    String FIELD_CACHE_CODE = "Response-Code";

    /**
     * 过滤拦截
     */
    String FIELD_ACL_INTERCEPTOR = "acl.interceptor";

    /**
     * 免过滤拦截
     */
    String ACL_INTERCEPTOR_LIST = ",/index/login,/index/refresh,/index/changePassword,/index/code,/index/checkModify,/index/email,/core/globalData,/index/changePassword.html,/generator/config/table/to/entity";

}

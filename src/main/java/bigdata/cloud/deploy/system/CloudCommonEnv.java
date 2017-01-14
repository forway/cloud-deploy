package bigdata.cloud.deploy.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
/**
 * cloud通用信息
 * @author hongliang
 *
 */
public class CloudCommonEnv {

	//组件名和对应的线程名映射map
	public static final Map<String, String> componentProcessMap = new HashMap<String, String>();
	
	//java scala
	public static final String JAVA_HOME = System.getenv("JAVA_HOME");
	public static final String SCALA_HOME = System.getenv("SCALA_HOME");
	// cloud environment variable
	public static final String seq = File.separator;
	public static final String CLOUD_HOME = System.getenv("CLOUD_HOME");
	public static final String CLOUD_BIN_PATH = CLOUD_HOME + seq + "bin";
	public static final String CLOUD_CONF_PATH = CLOUD_HOME + seq + "conf";
	public static final String CLOUD_DATA_PATH = CLOUD_HOME + seq + "data";
	public static final String CLOUD_LIB_PATH = CLOUD_HOME + seq + "lib";
	public static final String CLOUD_APP_PATH = CLOUD_HOME + seq + "app";
	
	//cloud config file
	public static final String CLOUD_CLUSTER_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-cluster.conf";
	public static final String CLOUD_SLAVES_FILE = CLOUD_CONF_PATH + seq + "cloud-slaves";
	public static final String CLOUD_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud.conf";
	public static final String CLOUD_ZOOKEEPER_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-zookeeper.conf";
	public static final String CLOUD_SPARK_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-spark.conf";
	public static final String CLOUD_REDIS_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-redis.conf";
	public static final String CLOUD_ELASTICSEARCH_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-elasticsearch.conf";
	public static final String CLOUD_KAFKA_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-kafka.conf";
	public static final String CLOUD_MYSQL_CONF_FILE = CLOUD_CONF_PATH + seq + "cloud-mysql.conf";
	
	//各个组件名
	public static final String CLOUD_LAUNCH = "cloud_launch";
	public static final String ZOOKEEPER = "zookeeper";
	public static final String ELASTICSEARCH = "elasticsearch";
	public static final String REDIS = "redis";
	public static final String KAFKA = "kafka";
	public static final String SPARK_MASTER = "spark_master";
	public static final String SPARK_WORKER = "spark_worker";
	public static final String MYSQL = "mysql";
	
	//各个组件对应的线程名
	public static final String CLOUD_LAUNCH_PS = "cloud_launch";
	public static final String ZOOKEEPER_PS = "QuorumPeerMain";
	public static final String ELASTICSEARCH_PS = "Elasticsearch";
	public static final String REDIS_PS = "redis";
	public static final String KAFKA_PS = "Kafka";
	public static final String SPARK_MASTER_PS = "Master";
	public static final String SPARK_WORKER_PS = "Worker";
	public static final String MYSQL_PS = "mysql";
	
	// 各个组件的配置文件键值对分隔符
	public static final String CLOUD_INFO_SPLIT = ",";
	public static final String CLOUD_CONF_SPLIT = "=";
	public static final String CLOUD_ZOOKEEPER_CONF_SPLIT = "=";
	public static final String CLOUD_REDIS_CONF_SPLIT = "";
	// 注意：es的配置中=号之后，需要空一格，如：key: value
	public static final String CLOUD_ELASTICSEARCH_CONF_SPLIT = ": "; 
	public static final String CLOUD_KAFKA_CONF_SPLIT = "=";
	public static final String CLOUD_MYSQL_CONF_SPLIT = "";
	
	//组件状态：启动、停止、不存在
	public static final String STARTED = "started"; 
	public static final String STOPPED = "stopped";
	public static final String NON_EXISTED = "non_existed";

	static {
		//初始化组件名和线程名映射map
		componentProcessMap.put(CLOUD_LAUNCH, CLOUD_LAUNCH_PS);
		componentProcessMap.put(ZOOKEEPER, ZOOKEEPER_PS);
		componentProcessMap.put(ELASTICSEARCH, ELASTICSEARCH_PS);
		componentProcessMap.put(REDIS, REDIS_PS);
		componentProcessMap.put(KAFKA, KAFKA_PS);
		componentProcessMap.put(SPARK_MASTER, SPARK_MASTER_PS);
		componentProcessMap.put(SPARK_WORKER, SPARK_WORKER_PS);
		componentProcessMap.put(MYSQL, MYSQL_PS);
	}

}

package bigdata.cloud.deploy.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bigdata.cloud.deploy.utils.CloudConfigUtil;

/**
 * cloud master environment info
 * @author hongliang
 *
 */
public class CloudMasterEnv {
	//配置key值和组件对应的map
	private static Map<String, String> paramComponentMap = new HashMap<String, String>();
	//ip和组件的映射关系
	private static Map<String, Set<String>> ipToComponentMap = new HashMap<String, Set<String>>();
	//组件和ip的映射关系
	private static Map<String, Set<String>> componentToIPMap = new HashMap<String, Set<String>>();
	//从节点ip集合
	private static Set<String> slaveIpSet;
	
	//主节点配置文件
	private static final String seq = File.separator;
	private static final String CLOUD_CLUSTER_CONF_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "cloud-cluster.conf";
	private static final String CLOUD_SLAVES_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "cloud-slaves";
	//主节点中各个组件对应的配置文件
	public static final String CLOUD_SPARK_SLAVES_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "spark" + seq + "slaves";
	public static final String CLOUD_SPARK_ENV_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "spark" + seq + "spark-env.sh";
	public static final String CLOUD_SPARK_DEFAULTS_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "spark" + seq + "spark-defaults.conf";
	
	
	
	
	//cloud组件角色配置文件key值，角色配置文件：cloud-cluster.conf
	private static final String CLOUD_LAUNCH_HOSTS_KEY = "cloud.launch.hosts";
	private static final String CLOUD_MYSQL_HOSTS_KEY = "cloud.mysql.hosts";
	private static final String CLOUD_ZOOKEEPER_HOSTS_KEY = "cloud.zookeeper.hosts";
	private static final String CLOUD_SPARK_MASTER_HOST_KEY = "cloud.spark.master.host";
	private static final String CLOUD_SPARK_WORKER_HOSTS_KEY = "cloud.spark.worker.hosts";
	private static final String CLOUD_REDIS_HOSTS_KEY = "cloud.redis.hosts";
	private static final String CLOUD_KAFKA_HOSTS_KEY = "cloud.kafka.hosts";
	private static final String CLOUD_ELASTICSEARCH_MASTER_HOSTS_KEY = "cloud.elasticsearch.master.hosts";
	private static final String CLOUD_ELASTICSEARCH_DATA_HOSTS_KEY = "cloud.elasticsearch.data.hosts";
	
	/**
	 * 获取 ip 到 组件的映射关系map
	 */
	public static Map<String, Set<String>>  getIpToComponentMap(){
		if(ipToComponentMap == null || ipToComponentMap.size() == 0){
			initMap();
		}
		return ipToComponentMap;
	}
	
	/**
	 * 获取 组件 到 ip 的映射关系map
	 */
	public static Map<String, Set<String>>  getComponentToIPMap(){
		if(componentToIPMap == null || componentToIPMap.size() == 0){
			initMap();
		}
		return componentToIPMap;
	}
	
	/**
	 * 获取 从节点ip集合
	 */
	public static Set<String> getSlaveIpSet(){
		if(slaveIpSet == null || slaveIpSet.size() == 0){
			initSlaveIpSet();
		}
		return slaveIpSet;
	}
	
	/**
	 * 初始化配置key值和组件对应的map
	 */
	public static void initParamComponentMap(){
		paramComponentMap.put(CLOUD_LAUNCH_HOSTS_KEY, CloudCommonEnv.CLOUD_LAUNCH);
		paramComponentMap.put(CLOUD_MYSQL_HOSTS_KEY, CloudCommonEnv.MYSQL);
		paramComponentMap.put(CLOUD_ZOOKEEPER_HOSTS_KEY, CloudCommonEnv.ZOOKEEPER);
		paramComponentMap.put(CLOUD_SPARK_MASTER_HOST_KEY, CloudCommonEnv.SPARK_MASTER);
		paramComponentMap.put(CLOUD_SPARK_WORKER_HOSTS_KEY, CloudCommonEnv.SPARK_WORKER);
		paramComponentMap.put(CLOUD_REDIS_HOSTS_KEY, CloudCommonEnv.REDIS);
		paramComponentMap.put(CLOUD_KAFKA_HOSTS_KEY, CloudCommonEnv.KAFKA);
		paramComponentMap.put(CLOUD_ELASTICSEARCH_MASTER_HOSTS_KEY, CloudCommonEnv.ELASTICSEARCH);
		paramComponentMap.put(CLOUD_ELASTICSEARCH_DATA_HOSTS_KEY, CloudCommonEnv.ELASTICSEARCH);
	}
	
	/**
	 * 初始化ip到组件的映射关系 ipComponentMap
	 * 初始化组件到ip的映射关系 componentToIPMap
	 * 读取集群组件角色配置文件cloud-cluster.conf
	 * @return
	 */
	public static void initMap(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(CLOUD_CLUSTER_CONF_FILE));
			//读取每一行，过滤掉注释“#”，而且必须包含key-value分割符
			String line = br.readLine();
			while(line != null && !line.startsWith("#")){
				String componentName = "none";
				if(line.startsWith("[") && line.endsWith("]")){
					//当前行是组件名
					componentName = paramComponentMap.get(line.substring(line.indexOf("[") + 1, line.lastIndexOf("]")));
				} else {
					//当前行是ip
					//ip 到 组件 映射
					Set<String> componentSet = ipToComponentMap.get(line);
					if(componentSet == null){
						componentSet = new HashSet<String>();
						componentSet.add(componentName);
						ipToComponentMap.put(line, componentSet);
					} else {
						componentSet.add(componentName);
					}
					//组件 到 ip 映射
					Set<String> ipSet = componentToIPMap.get(componentName);
					if(ipSet == null){
						ipSet = new HashSet<String>();
						ipSet.add(line);
						componentToIPMap.put(componentName, ipSet);
					} else {
						ipSet.add(line);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 初始化slaveIpSet
	 * 加载cloud-slaves文件
	 */
	public static void initSlaveIpSet(){
		slaveIpSet = CloudConfigUtil.readConfigFileToSet(CLOUD_SLAVES_FILE);
	}
	
}

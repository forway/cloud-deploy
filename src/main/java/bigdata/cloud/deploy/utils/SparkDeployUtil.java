package bigdata.cloud.deploy.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bigdata.cloud.deploy.system.CloudCommonEnv;

/**
 * spark部署工具类
 * @author hongliang
 *
 */
public class SparkDeployUtil {
	
	public static final String seq = File.separator;
	public static final String SPARK_HOME_PATH = CloudCommonEnv.CLOUD_APP_PATH + seq + "spark-1.5.1-bin-hadoop2.4";
	public static final String SPARK_CONF_PATH = SPARK_HOME_PATH + seq + "conf";	//spark配置文件目录
	public static final String SPARK_LIB_PATH = CloudCommonEnv.CLOUD_LIB_PATH + seq + "spark" + seq + "*";
	//spark需要配置的文件
	public static final String SPARK_SLAVES_FILE = SPARK_CONF_PATH + seq + "slaves";
	public static final String SPARK_ENV_FILE = SPARK_CONF_PATH + seq + "spark-env.sh";
	public static final String SPARK_DEFAULTS_FILE = SPARK_CONF_PATH + seq + "spark-defaults.conf";
	//spark配置参数的key和value分割符
	public static final String SPARK_CONF_SPLIT = "=";
	//spark 端口
	public static final String SPARK_PORT = "7077";
	//cloud-spark.conf中的fileKey
	public static final String SPARK_ENV_FILE_KEY = "spark-env";
	public static final String SPARK_DEFAULTS_FILE_KEY = "spark-defaults";
	
	/**
	 * 同步spark配置文件	<br />
	 * 1、设置所有work节点的ip到slaves文件	<br />
	 * 2、设置以下配置项到文件spark-env.sh：	<br />
	 * export SPARK_MASTER_IP=hadoop1	<br />
	 * export SPARK_MASTER_PORT=7077	<br />
	 * export SPARK_WORKER_INSTANCES=1	<br />
	 * export SPARK_WORKER_CORES=1	<br />
	 * export SPARK_WORKER_MEMORY=1g	<br />
	 * export SCALA_HOME=/app/scala	<br />
	 * export JAVA_HOME=/app/java	<br />
	 * 3、设置以下配置项到spark-defaults.conf：	<br />
	 * spark.executor.extraClassPath=/lib/card_jars/*	<br />
	 * spark.driver.extraClassPath=/lib/card_jars/*	<br />
	 * 
	 * @param sparkMasterIP spark主节点ip
	 * @param sparkWorkerIPSet spark从节点ip集合
	 * @param cloudSparkEnvFileMap cloud spark的env配置项
	 */
	public static void synchronizationConfig(String sparkMasterIP, Set<String> sparkWorkerIPSet, Map<String, String> cloudSparkEnvFileMap){
		Map<String, String> map = new HashMap<String, String>();
		//1、设置所有work节点的ip到slaves文件
		CloudConfigUtil.writeSetToConfigFile(sparkWorkerIPSet, SPARK_SLAVES_FILE);
		//2、设置配置项到文件spark-env.sh
		map.put("export SPARK_MASTER_IP", sparkMasterIP);
		map.put("export SPARK_MASTER_PORT", SPARK_PORT);
		map.put("export JAVA_HOME", CloudCommonEnv.JAVA_HOME);
		map.put("export SCALA_HOME", CloudCommonEnv.SCALA_HOME);
		map.putAll(cloudSparkEnvFileMap);
		CloudConfigUtil.writeMapToConfigFile(SPARK_ENV_FILE, map, SPARK_CONF_SPLIT);
		//3、设置以下配置项到spark-defaults.conf
		map.clear();
		map.put("spark.executor.extraClassPath", SPARK_LIB_PATH);
		map.put("spark.driver.extraClassPath", SPARK_LIB_PATH);
		CloudConfigUtil.writeMapToConfigFile(SPARK_DEFAULTS_FILE, map, SPARK_CONF_SPLIT);
	}
	
	/**
	 * 启动spark主节点
	 * sbin/start-master.sh
	 */
	public static void startMaster(){
		//启动spark主节点
		String command = SPARK_HOME_PATH + seq + "sbin" + seq + "start-master.sh";
		SystemUtil.runShell(command);
	}
	
	/**
	 * 启动spark slave节点
	 * sbin/start-slave.sh 192.1.1.207:7077
	 */
	public static void startSlave(String sparkMasterIP){
		//启动spark从节点
		String spark_master_url = sparkMasterIP + ":" + SPARK_PORT;
		String command = SPARK_HOME_PATH + seq + "sbin" + seq + "start-slave.sh " + spark_master_url;
		SystemUtil.runShell(command);
	}
	
	/**
	 * 停止spark主节点
	 * sbin/stop-master.sh
	 */
	public static void stopMaster(){
		//停止spark主节点
		String command = SPARK_HOME_PATH + seq + "sbin" + seq + "stop-master.sh";
		SystemUtil.runShell(command);
	}
	
	/**
	 * 停止spark slave从节点
	 * sbin/stop-slave.sh
	 */
	public static void stopSlave(){
		 //停止spark从节点
		String command = SPARK_HOME_PATH + seq + "sbin" + seq + "stop-slave.sh";
		SystemUtil.runShell(command);
	}

}

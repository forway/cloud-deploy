package bigdata.cloud.deploy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bigdata.cloud.deploy.system.CloudCommonEnv;
import bigdata.cloud.deploy.system.CloudMasterEnv;

/**
 * cloud主节点工具类
 * @author hongliang
 *
 */
public class CloudMasterUtil {
	
	/**
	 * spark配置文件	<br />
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
	 */
	public static void sparkConfig(){
		
		Map<String, String> map = new HashMap<String, String>();
		String sparkMasterIP = new ArrayList<String>(CloudMasterEnv.getComponentToIPMap().get(CloudCommonEnv.SPARK_MASTER)).get(0);
		Set<String> sparkWorkerIPSet = CloudMasterEnv.getComponentToIPMap().get(CloudCommonEnv.SPARK_WORKER);
		
		//1、设置所有work节点的ip到slaves文件
		CloudConfigUtil.writeSetToConfigFile(sparkWorkerIPSet, CloudMasterEnv.CLOUD_SPARK_SLAVES_FILE);
		//2、设置配置项到文件spark-env.sh
		map.put("export JAVA_HOME", CloudCommonEnv.JAVA_HOME);
		map.put("export SCALA_HOME", CloudCommonEnv.SCALA_HOME);
		map.put("export SPARK_MASTER_IP", sparkMasterIP);
		map.put("export SPARK_MASTER_PORT", CloudCommonEnv.SPARK_PORT);
		Map<String, String> envMap = CloudConfigUtil.readConfigFileToMap(CloudMasterEnv.CLOUD_SPARK_ENV_FILE, CloudCommonEnv.SPARK_CONF_SPLIT);
		map.putAll(envMap);
		CloudConfigUtil.writeMapToConfigFile(CloudMasterEnv.CLOUD_SPARK_ENV_FILE, map, CloudCommonEnv.SPARK_CONF_SPLIT);
		//3、设置以下配置项到spark-defaults.conf
		map.clear();
		map.put("spark.executor.extraClassPath", CloudMasterEnv.SPARK_LIB_PATH);
		map.put("spark.driver.extraClassPath", CloudMasterEnv.SPARK_LIB_PATH);
		CloudConfigUtil.writeMapToConfigFile(CloudMasterEnv.CLOUD_SPARK_DEFAULTS_FILE, map, CloudCommonEnv.SPARK_CONF_SPLIT);
	}
	
	
	

}

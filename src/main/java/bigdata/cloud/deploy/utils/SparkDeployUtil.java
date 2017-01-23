package bigdata.cloud.deploy.utils;

import java.io.File;

import bigdata.cloud.deploy.system.CloudCommonEnv;

/**
 * spark部署工具类
 * @author hongliang
 *
 */
public class SparkDeployUtil {
	
	public static final String seq = File.separator;
	public static final String SPARK_HOME_PATH = CloudCommonEnv.CLOUD_APP_PATH + seq + "spark";
	public static final String SPARK_CONF_PATH = SPARK_HOME_PATH + seq + "conf";	//spark配置文件目录
	//spark需要配置的文件
	public static final String SPARK_SLAVES_FILE = SPARK_CONF_PATH + seq + "slaves";
	public static final String SPARK_ENV_FILE = SPARK_CONF_PATH + seq + "spark-env.sh";
	public static final String SPARK_DEFAULTS_FILE = SPARK_CONF_PATH + seq + "spark-defaults.conf";
	
	/**
	 * 同步spark配置文件
	 */
	public static void synchronizationConfig(){
		
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
		String spark_master_url = sparkMasterIP + ":" + CloudCommonEnv.SPARK_PORT;
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

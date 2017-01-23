package bigdata.cloud.deploy.system;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bigdata.cloud.deploy.utils.CloudConfigUtil;
import bigdata.cloud.deploy.utils.SystemUtil;

/**
 * cloud environment info
 * @author hongliang
 *
 */
public class CloudEnv {
	
	//cloud master ip
	private static String cloudMasterIp;
	//主节点分配给该节点的组件角色，存放组件名
	private static Set<String> allotComponentSet = new HashSet<String>();
	
	//cloud配置文件key值，角色配置文件：cloud.conf
	private static final String seq = File.separator;
	private static final String CLOUD_CONF_FILE = CloudCommonEnv.CLOUD_CONF_PATH + seq + "cloud.conf";
	private static final String CLOUD_MASTER_HOST_KEY = "cloud.master.host";
	private static final String CLOUD_NODE_COMPONENTS_KEY = "cloud.node.components";
	
	
	/**
	 * CloudEnv初始化
	 */
	public static void cloudEnvInit(){
		Map<String, String> map = CloudConfigUtil.readConfigFileToMap(CLOUD_CONF_FILE, CloudCommonEnv.CLOUD_CONF_SPLIT);
		cloudMasterIp = map.get(CLOUD_MASTER_HOST_KEY);
		String componets = map.get(CLOUD_NODE_COMPONENTS_KEY);
		if(componets != null) {
			String[] componentArray = componets.split(CloudCommonEnv.CLOUD_INFO_SPLIT);
			allotComponentSet.addAll(Arrays.asList(componentArray));
		}
	}
	
	/**
	 * 获取cloud集群的master ip
	 * @return
	 */
	public static String getCloudMasterIp(){
		if(cloudMasterIp == null || "".equals(cloudMasterIp)){
			cloudEnvInit(); 
		}
		return cloudMasterIp;
	}
	
	/**
	 * 获取该节点分配的组件
	 * @return
	 */
	public static Set<String> getAllotComponentSet(){
		if(allotComponentSet.size() == 0){
			cloudEnvInit();
		}
		return allotComponentSet;
	}
	
	/**
	 * 判断本节点是否是cloud主节点
	 * @return
	 */
	public static boolean isCloudMaster(){
		String masterIP = getCloudMasterIp();
		if(SystemUtil.getHostIp().equals(masterIP) || SystemUtil.getHostName().equals(masterIP)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取本机所有组件的状态  <br />
	 * 返回map，key：组件名，value：状态
	 * @return
	 */
	public static Map<String, String> getAllComponentsStatus(){
		Map<String, String> map = new HashMap<String, String>();
		for(String name : allotComponentSet){
			map.put(name, getStatusByComponentName(name));
		}
		return map;
	}
	
	/**
	 * 根据组件名判断本节点该组件是否已经启动、停止、或不存在 <br />
	 * 返回值   STARTED:启动、 STOPPED:停止、  NON_EXISTED:没有改组件
	 * @param name
	 * @return
	 */
	public static String getStatusByComponentName(String name){
		//不存在
		if(!allotComponentSet.contains(name)){
			return CloudCommonEnv.NON_EXISTED;
		} 
		String command = "pgrep -f " + CloudCommonEnv.componentProcessMap.get(name);
		String command_result = SystemUtil.runShell(command);
		if(command_result != null && !"".equals(command_result)){
			//启动状态
			return CloudCommonEnv.STARTED;
		} else {
			//停止状态
			return CloudCommonEnv.STOPPED;
		}
	}
	
}

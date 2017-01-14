package bigdata.cloud.deploy.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import bigdata.cloud.deploy.system.CloudClusterEnv;
import bigdata.cloud.deploy.system.CloudCommonEnv;

/**
 * cloud集群服务类
 * @author hongliang
 *
 */
@Service
public class CloudClusterService {
	
	/**
	 * 获取spark master ip
	 * @return
	 */
	public  String getSparkMasterIP(){
		Set<String> sparkMasterIPSet = CloudClusterEnv.getComponentToIPMap().get(CloudCommonEnv.SPARK_MASTER);
		String sparkMasterIP = "localhost";
		if(sparkMasterIPSet != null && sparkMasterIPSet.size() > 0){
			for(String ip : sparkMasterIPSet){
				sparkMasterIP = ip;
			}
		}
		return sparkMasterIP;
	}

}

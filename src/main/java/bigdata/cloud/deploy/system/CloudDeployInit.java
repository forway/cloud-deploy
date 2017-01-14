package bigdata.cloud.deploy.system;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * cloud系统部署初始化
 * @author hongliang
 *
 */
@Component
public class CloudDeployInit {
	
	private static Logger logger = Logger.getLogger(CloudDeployInit.class);
	
	/**
	 * 系统启动后自动执行
	 */
	@PostConstruct
	public void initAllInfo(){
		
		//判断该节点是否是主节点
		if(CloudEnv.isCloudMaster()){
			//如果是主节点，加载配置文件：cloud-cluster.conf，cloud-slaves
			logger.info("current node is cloud master.");
			logger.info("	init cloud cluster info ......");
			CloudClusterEnv.initParamComponentMap();
			CloudClusterEnv.initMap();
			CloudClusterEnv.initSlaveIpSet();
			logger.info("	init cloud cluster info finished.");

		}
		
		logger.info("current node is cloud slave.");
		logger.info("	init cloud slave info ......");
		CloudEnv.cloudEnvInit();
		logger.info("	init cloud slave info finished.");
		
	}

}

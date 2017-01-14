package bigdata.cloud.deploy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bigdata.cloud.deploy.system.Version;

/**
 * 系统信息api类
 * 
 * @author hongliang
 *
 */
@RestController
@RequestMapping("/cloud/deploy")
public class CloudDeployAPI
{
	@Autowired
	Version version;

	/**
	 * 获取版本号等信息
	 * @return
	 */
	@RequestMapping("/version")
	@ResponseBody
	public Version getVersion()
	{
		return version;
	}
	
}

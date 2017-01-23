package bigdata.cloud.deploy.api;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * cloud从节点api
 * @author hongliang
 *
 */
@RestController
@RequestMapping("/cloud/slave")
public class CloudSlaveAPI {
	
	/**
	 * 本节点部署
	 */
	@RequestMapping("/deploy")
	@ResponseBody
	public String deploy(Map<String, Set<String>> configMap)
	{
		return null;
	}

}

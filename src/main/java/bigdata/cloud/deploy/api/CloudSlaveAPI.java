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
	 * 本节点安装，设置配置文件信息
	 * @param configMap 主节点推送过来的配置信息，key：配置文件名，value：配置信息
	 * @return
	 */
	@RequestMapping("/install")
	@ResponseBody
	public String install(Map<String, Set<String>> configMap)
	{
		return null;
	}
	
	/**
	 * 还原本节点配置文件
	 * @return
	 */
	@RequestMapping("/uninstall")
	@ResponseBody
	public String unInstall()
	{
		return null;
	}
	
	/**
	 * 启动本节点所有组件
	 * @return
	 */
	@RequestMapping("/start_all")
	@ResponseBody
	public String start_all()
	{
		return null;
	}
	
	/**
	 * 停止本节点所有组件
	 * @return
	 */
	@RequestMapping("/stop_all")
	@ResponseBody
	public String stop_all()
	{
		return null;
	}
	
	/**
	 * 启动本节点指定的组件
	 * @return
	 */
	@RequestMapping("/start_by_name")
	@ResponseBody
	public String startByName()
	{
		return null;
	}
	
	/**
	 * 停止本节点指定的组件
	 * @return
	 */
	@RequestMapping("/stop_by_name")
	@ResponseBody
	public String stopByName()
	{
		return null;
	}
	
	/**
	 * 获取本节点所有组件的状态信息
	 * @return
	 */
	@RequestMapping("/get_all_status")
	@ResponseBody
	public String getAllStatus()
	{
		return null;
	}
	
	/**
	 * 获取本节点指定组件的状态信息
	 * @return
	 */
	@RequestMapping("/get_status_by_name")
	@ResponseBody
	public String getStatusByName(String name)
	{
		return null;
	}
	
	

}

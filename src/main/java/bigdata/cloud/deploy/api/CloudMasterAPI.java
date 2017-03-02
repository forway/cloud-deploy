package bigdata.cloud.deploy.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * cloud主节点api
 * @author hongliang
 *
 */
@RestController
@RequestMapping("/cloud/master")
public class CloudMasterAPI {
	
	/**
	 * cloud安装，配置主节点所有组件的配置文件，并推送配置文件到所有节点
	 */
	@RequestMapping("/install")
	@ResponseBody
	public String install()
	{
		return null;
	}
	
	/**
	 * cloud卸载，还原主节点所有组件的配置文件，并推送配置文件到所有节点
	 */
	@RequestMapping("/uninstall")
	@ResponseBody
	public String unInstall()
	{
		return null;
	}
	
	/**
	 * 推送配置文件到所有节点
	 */
	@RequestMapping("/push_config_file")
	@ResponseBody
	public String pushConfigFile()
	{
		return null;
	}
	
	/**
	 * 启动所有节点的所有组件
	 */
	@RequestMapping("/start_all")
	@ResponseBody
	public String start_all()
	{
		return null;
	}
	
	/**
	 * 停止所有节点的所有组件
	 */
	@RequestMapping("/stop_all")
	@ResponseBody
	public String stop_all()
	{
		return null;
	}
	
	/**
	 * 启动所有节点指定的组件
	 */
	@RequestMapping("/start_by_name")
	@ResponseBody
	public String startByName(String name)
	{
		return null;
	}
	
	/**
	 * 停止所有节点指定的组件
	 */
	@RequestMapping("/stop_by_name")
	@ResponseBody
	public String stopByName(String name)
	{
		return null;
	}
	
	/**
	 * 启动指定节点的所有组件
	 */
	@RequestMapping("/start_by_host")
	@ResponseBody
	public String startByHost(String host)
	{
		return null;
	}
	
	/**
	 * 停止指定节点的所有组件
	 */
	@RequestMapping("/stop_by_host")
	@ResponseBody
	public String stopByHost(String host)
	{
		return null;
	}
	
	/**
	 * 启动指定节点的指定组件
	 */
	@RequestMapping("/start_by_host_and_name")
	@ResponseBody
	public String startByHostAndName(String host, String name)
	{
		return null;
	}
	
	/**
	 * 停止指定节点的指定组件
	 */
	@RequestMapping("/stop_by_host_and_name")
	@ResponseBody
	public String stopByHostAndName(String host, String name)
	{
		return null;
	}
	
	/**
	 * 获取所有节点所有组件的状态信息
	 */
	@RequestMapping("/get_all_status")
	@ResponseBody
	public String getAllStatus()
	{
		return null;
	}
	
	/**
	 * 获取所有节点指定组件的状态信息
	 */
	@RequestMapping("/get_status_by_name")
	@ResponseBody
	public String getStatusByName(String name)
	{
		return null;
	}
	
	/**
	 * 获取指定节点的所有组件的状态信息
	 */
	@RequestMapping("/get_status_by_host")
	@ResponseBody
	public String getStatusByHost(String name)
	{
		return null;
	}
	
	/**
	 * 获取指定节点的指定组件的状态信息
	 */
	@RequestMapping("/get_status_by_host_and_name")
	@ResponseBody
	public String getStatusByHostAndName(String host, String name)
	{
		return null;
	}

}

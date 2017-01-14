package bigdata.cloud.deploy.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * 配置文件操作工具类
 * @author hongliang
 *
 */
public class CloudConfigUtil {
	
	/**
	 * 读取指定的属性文件，把内容转为key：value对的Map结构
	 * @param propertiesFilePath	属性文件路径
	 * @return	Map<String,String> 对应 key ： value
	 */
	public static Map<String,String> readPropertiesFileToMap(String propertiesFilePath) {
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(propertiesFilePath));
			properties.load(fileInputStream);
			//获取属性文件所有的key值
			Set<String> names = properties.stringPropertyNames();
			for(String name : names) {
				paramsMap.put(name, properties.getProperty(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fileInputStream) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return paramsMap;
	}
	
	/**
	 * 读取配置文件，返回Map
	 * @param filePath	配置文件路径
	 * @param splitStr	文件参数中key和value的分隔符，如：key=value的分割符是=
	 * @return
	 */
	public static Map<String,String> readConfigFileToMap(String filePath, String splitStr){
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			//读取每一行，过滤掉注释“#”，而且必须包含key-value分割符
			String line = br.readLine();
			while(line != null && !line.startsWith("#") && line.contains(splitStr)){
				String[] lineArray = line.split(splitStr);
				map.put(lineArray[0], lineArray[1]);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	/**
	 * 读取配置文件每一行，并把每一行内容加入到set集合	<br/>
	 * 返回Set	<br/>
	 * @param filePath	配置文件路径
	 * @return
	 */
	public static TreeSet<String> readConfigFileToSet(String filePath){
		TreeSet<String> set = new TreeSet<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			//读取每一行，过滤掉注释“#”，而且必须包含key-value分割符
			String line = br.readLine();
			while(line != null && !line.startsWith("#")){
				set.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return set;
	}
	
	/**
	 * 读取配置文件，文件内容格式如下：<br/>
	 * [fileKey1]	<br/>
  	 * key1=value1	<br/>
  	 * key2=value2	<br/>
	 * [fileKey2]	<br/>
	 * key3=value3	<br/>
	 * 返回 Map<String, Map<String, String>>，其中key=fileKey1，value={key1:value1, key2:value2}	<br/>
	 * @param filePath	配置文件路径
	 * @param splitStr	文件key value对分割符
	 * @return
	 */
	public static Map<String, Map<String, String>> readConfigFileToMultiMap(String filePath, String splitStr){
		Map<String, Map<String, String>> fileMap = new HashMap<String, Map<String, String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			//读取每一行，过滤掉注释“#”，而且必须包含key-value分割符
			String line = br.readLine();
			String fileKey = "none";
			while(line != null && !line.startsWith("#")){
				if(line.startsWith("[") && line.endsWith("]")){
					fileKey = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]"));
				} else if(line.contains(splitStr)){
					String[] array = line.split(splitStr);
					Map<String, String> map = fileMap.get(fileKey);
					if(map == null){
						map = new HashMap<String, String>();
						map.put(array[0], array[1]);
						fileMap.put(fileKey, map);
					} else {
						map.put(array[0], array[1]);
					}
				}
				line = br.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileMap;
	}
	
	/**
	 * 读取配置文件，文件内容格式如下：<br/>
	 * [fileKey1]	<br/>
  	 * key1=value1	<br/>
  	 * key2=value2	<br/>
	 * [fileKey2]	<br/>
	 * key3=value3	<br/>
	 * 返回指定fileKey的配置项Map<String, String> <br/>
	 * 如：fileKey=fileKey1，返回map={key1:value1, key2:value2}
	 * @param filePath	配置文件路径
	 * @param fileKey	指定的文件key，如：fileKey1
	 * @param splitStr	文件key value对分割符
	 * @return
	 */
	public static Map<String, String> readConfigFileToMapByFileKey(String filePath, String fileKey, String splitStr){
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			//读取每一行，过滤掉注释“#”，而且必须包含key-value分割符
			String line = br.readLine();
			String fileKeyTmp = "none";
			while(line != null && !line.startsWith("#")){
				if(line.startsWith("[") && line.endsWith("]")){
					fileKeyTmp = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]"));
				} else if(fileKey.equals(fileKeyTmp) && line.contains(splitStr)){
					//如果当前的fileKey和指定的相等，则加入到map
					String[] array = line.split(splitStr);
					map.put(array[0], array[1]);
				}
				line = br.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	/**
	 * 把集合中的数据写入到指定的配置文件	<br/>
	 * 一个元素为配置文件的一行数据	<br/>
	 * 不管是修改了配置信息还是新增的配置信息，都会更新到配置文件	<br/>
	 * @param set	集合
	 * @param filePath
	 */
	public static void writeSetToConfigFile(Set<String> set, String filePath){
		TreeSet<String> sourceConfFile = readConfigFileToSet(filePath);
		//把更新的配置信息加入或更新到原配置文件
		sourceConfFile.addAll(set);
		//最后把更新后的sourceConfFile重新写入到原配置文件，覆盖方式写入（不是追加方式）
		BufferedWriter bw = null;
		try {
			//打开一个写文件器
			bw = new BufferedWriter(new FileWriter(filePath));
			for(String line : sourceConfFile){
				bw.write(line);
				bw.newLine();
            }
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 把配置信息写入到指定的配置文件	<br/>
	 * 不管是修改了配置信息还是新增的配置信息，都会更新到配置文件	<br/>
	 * @param filePath	指定的文件路径
	 * @param confMap		配置信息key - value
	 * @param split		配置信息key和value分割符
	 */
	public static void writeMapToConfigFile(String filePath, Map<String, String> confMap, String splitStr){
		Map<String, String> sourceConfFile = readConfigFileToMap(filePath, splitStr);
		//把更新的配置信息加入或更新到原配置文件
		sourceConfFile.putAll(confMap);
		//最后把更新后的sourceConfFile重新写入到原配置文件，覆盖方式写入（不是追加方式）
		BufferedWriter bw = null;
		try {
			//打开一个写文件器
			bw = new BufferedWriter(new FileWriter(filePath));
			for(String key : sourceConfFile.keySet()){
				bw.write(key + splitStr + sourceConfFile.get(key));
				bw.newLine();
            }
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	// 同步配置文件
	public static void synchronizeConfigFile() {

	}
	

}

package bigdata.cloud.deploy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

/**
 * 系统工具类
 * @author hongliang
 *
 */
@Repository
public class SystemUtil {
	
	/**
	 * 获取InetAddress
	 * @return
	 */
	private static InetAddress getInetAddress(){  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            e.printStackTrace();
        }  
        return null;  
    }  
  
	/**
	 * 获取本机ip
	 * @param netAddress
	 * @return
	 */
    public static String getHostIp(){  
        String ip = getInetAddress().getHostAddress(); //get the ip address  
        return ip;  
    }  
  
    /**
     * 获取本机主机名
     * @param netAddress
     * @return
     */
    public static String getHostName(){  
        String name = getInetAddress().getHostName(); //get the host address  
        return name;  
    }
    
    /**
     * linux系统下执行shell
     * @param command	命令
     * @return	放回运行结果
     */
    public static String runShell(String command) {
		String[] linuxCommand = new String[]{"sh","-c",command};
		String result = null;
		Process ps = null;
		BufferedReader br = null;
		 try {
			 ps = Runtime.getRuntime().exec(linuxCommand);	//得到该运行环境，并通过传入参数得到进程
			 ps.waitFor();
			 br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			 StringBuffer sb = new StringBuffer();
			 String line;
			 while ((line = br.readLine()) != null) {
				 sb.append(line).append("\n");
			 }
			 result = sb.toString();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if(br != null){
				 try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 if(ps != null){
				 ps.destroy();
			 }
		 }
		 return result;
	}
    
}

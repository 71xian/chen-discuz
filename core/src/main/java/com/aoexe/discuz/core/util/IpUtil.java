package com.aoexe.discuz.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Ip相关工具类
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Slf4j
public class IpUtil {

	static String dbPath = System.getProperty("user.dir") + File.separator + "ip2region.db";
	static DbConfig config;
	static DbSearcher searcher;

	static {
		try {
			config = new DbConfig();
			System.out.println(dbPath);
			searcher = new DbSearcher(config, dbPath);
		} catch (DbMakerConfigException e) {
			System.out.println("ipUtil初始化失败");
		} catch (FileNotFoundException e1) {
			System.out.println("ip2region文件找不到");
		}
	}

	/**
	 * 获取当前网络ip
	 *
	 * @param request
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 获取操作系统，浏览器信息
	 *
	 * @param request
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static Map<String, String> getOsAndBrowserInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";

		// =================OS Info=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("macl") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}

		// ===============Browser===========================
		try {
			if (user.contains("edge")) {
				browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
			} else if (user.contains("msie")) {
				String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
				browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
			} else if (user.contains("safari") && user.contains("version")) {
				browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr") || user.contains("opera")) {
				if (user.contains("opera")) {
					browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
							+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
				} else if (user.contains("opr")) {
					browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
							.replace("OPR", "Opera");
				}
			} else if (user.contains("chrome")) {
				browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
			} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
					|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
					|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
				browser = "Netscape-?";

			} else if (user.contains("firefox")) {
				browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
			} else if (user.contains("rv")) {
				String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
				browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
			} else {
				browser = "UnKnown";
			}
		} catch (Exception e) {
			log.error("获取浏览器版本失败");
			log.error(e.getMessage());
			browser = "UnKnown";
		}

		Map<String, String> result = new HashMap<>(2);
		result.put("OS", os);
		result.put("BROWSER", browser);
		return result;
	}

	/**
	 * 获取城市信息
	 *
	 * @param ip
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getCityInfo(String ip) {
		
		// 查询算法
		// B-tree, B树搜索（更快）
		int algorithm = DbSearcher.BTREE_ALGORITHM;
		
		//Binary,使用二分搜索
        //DbSearcher.BINARY_ALGORITHM

        //Memory,加载内存（最快）
        //DbSearcher.MEMORY_ALGORITYM
		try {
			  // 使用静态代码块，减少文件读取操作
//          DbConfig config = new DbConfig();
//          DbSearcher searcher = new DbSearcher(config, dbPath);
			Method method = null;
			switch(algorithm) {
			case DbSearcher.BTREE_ALGORITHM:
				method = searcher.getClass().getMethod("btreeSearch", String.class);
				break;
			case DbSearcher.BINARY_ALGORITHM:
				method = searcher.getClass().getMethod("binarySearch", String.class);
				break;
			case DbSearcher.MEMORY_ALGORITYM:
				method = searcher.getClass().getMethod("memorySearch", String.class);
				break;
				default:
			}
			
			DataBlock dataBlock = null;
			if(!Util.isIpAddress(ip)) {
				System.out.println("Error: Invalid ip address");
			}
			
			dataBlock = (DataBlock) method.invoke(searcher, ip);
			String ipInfo = dataBlock.getRegion();
			if(!StringUtils.isEmpty(ipInfo)) {
				ipInfo = ipInfo.replace("|0", "");
				ipInfo = ipInfo.replace("0|", "");
			}
			return ipInfo;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
}

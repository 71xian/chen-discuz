package cn.chenyuxian.discuz.core.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
public class IpUtils {

	static String dbPath;
	static DbConfig config;
	static DbSearcher searcher;

	static {
		dbPath = createFtlFileByFtlArray() + "ip2region.db";
		try {
			config = new DbConfig();
		} catch (DbMakerConfigException e) {
			e.printStackTrace();
		}
		
		try {
			searcher = new DbSearcher(config, dbPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	public static String getIpAddr(HttpServletRequest request) {
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
	 * 获取真实ip
	 *
	 * @param request
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		return checkIp(ip) ? ip
				: (checkIp(ip = request.getHeader("Proxy-Client-IP")) ? ip
						: (checkIp(ip = request.getHeader("WL-Proxy-Client-IP")) ? ip : request.getRemoteAddr()));
	}

	/**
	 * 校验ip
	 *
	 * @param ip
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static boolean checkIp(String ip) {
		return !StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip);
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
	 * 判断是否是内网ip
	 *
	 * @param ip
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static boolean isInner(String ip) {
		String reg = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(ip);
		return matcher.find();
	}

	/**
	 * 获取ip地址来源
	 *
	 * @param content
	 * @param encodingString
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getAddress(String content, String encodingString) {
		String ip = content.substring(3);
		
		if(!Util.isIpAddress(ip)) {
			log.info("IP地址为空");
			return null;
		}
		
		String cityInfo = getCityInfo(ip);
		log.info("返回的ip信息: {}", cityInfo);
		return cityInfo;
	}

	/**
	 * 
	 *
	 * @param urlStr   请求的地址
	 * @param content  请求的参数 格式为: name=xxx&pwd=xxx
	 * @param encoding 服务器端请求编码。如GBK，UTF-8等
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);

			// 新建连接实例
			connection = (HttpURLConnection) url.openConnection();

			// 设置连接超时时间，单位毫秒
			connection.setConnectTimeout(10000);

			// 设置读取数据超时时间，单位毫秒
			connection.setReadTimeout(10000);

			// 是否打开输出流
			connection.setDoOutput(true);

			// 是否打开输入流
			connection.setDoInput(true);

			// 提交方法POST|GET
			connection.setRequestMethod("POST");

			// 是否缓存true|false
			connection.setUseCaches(false);

			// 打开连接端口
			connection.connect();

			// 打开输出流对端服务器写数据
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// 写数据，也就是提交你的表单，name=xxx&pwd=xxx
			out.writeBytes(content);

			out.flush();

			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	/**
	 * 创建ip2region文件
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String createFtlFileByFtlArray() {
		String ftlPath = "city/";
		return createFtlFile(ftlPath, "ip2region.db");
	}

	/**
	 * 创建文件
	 *
	 * @param ftlPath
	 * @param ftlName
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	private static String createFtlFile(String ftlPath, String ftlName) {
		InputStream certStream = null;
		try {
			// 获取当前项目所在的绝对路径
			String proFilePath = System.getProperty("user.dir");

			// 获取模板下的路径，然后存放在temp目录下
			String newFilePath = proFilePath + File.separator + "temp" + File.separator + ftlPath;
			newFilePath = newFilePath.replace("/", File.separator);
			// 检查项目运行时的src下的对应路径
			File newFile = new File(newFilePath + ftlName);
			if (newFile.isFile() && newFile.exists()) {
				return newFilePath;
			}
			// 当项目打成jar包会运行下面的代码，并且复制一份到src路径下（具体结构看下面图片）
			certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(ftlPath);
			byte[] certData = IOUtils.toByteArray(certStream);
			FileUtils.writeByteArrayToFile(newFile, certData);
			return newFilePath;
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				certStream.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return null;
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
		if(StringUtils.isEmpty(dbPath)) {
			log.error("Error: Invalid ip2region.db file");
			return null;
		}
		if(config == null || searcher == null) {
			log.error("Error: DbSearcher or DbConfig is null");
			return null;
		}
		
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

	/**
	 * 获取ip
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "127.0.0.1";
	}

	/**
	 * 获取主机名
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return "未知";
	}
	
	public static void main(String[] args) {
		String ip = "111.58.2.71";
		String cityIpString = getCityInfo(ip);
		System.out.println(cityIpString);
	}

}

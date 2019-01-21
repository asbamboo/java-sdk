package asbamboo.java.sdk;

import java.io.File;

/**
 * 配置信息
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public class Configure
{
	/**
	 * api接口请求的url
	 *  - 开发测试url: http://developer.asbamboo.com/api
	 *  - 生产正式url: http://api.asbamboo.com
	 */
	public static String API_URL	= "http://developer.asbamboo.com/api";
	
	/**
	 * api接口的参数 app_key
	 */
	public static String API_APP_KEY	= "";
	
	/**
	 * api接口的生成sign参数使用的secret
	 */
	public static String API_SECRET	= "";

	/**
	 * api接口的参数format
	 */
	public static String API_FORMAT	= "json";

	/**
	 * api 接口的参数version
	 */
	public static String API_VERSION	= "v1.0";

	/**
	 * 发起api请求设置的连接超时的毫秒数
	 */
	public static Integer HTTP_REQUEST_TIMEOUT	= 30000; 

	/**
	 * Http请求时在header中添加"X-Asbamboo-Client-User-Agent:JAVA_SDK_1.0"
	 * 用于asbamboo系统排查SDK方面的异常信息。
	 */
	public static String HTTP_HEADER_USER_AGENT_VALUE	= "JAVA_SDK_1.0";

	/**
	 * 发起api请求设置的连接超时的毫秒数
	 */
	public static String PROJECT_LOG_PATH	= System.getProperty("user.dir").concat(File.separator).concat("var").concat(File.separator).concat("project.log"); 
}

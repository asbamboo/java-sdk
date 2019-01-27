package asbamboo.java.sdk;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 签名
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public class Sign
{
	/**
	 * 生成有效签名
	 *  - 用于api接口请求
	 * 
	 * @param data
	 * @return String
	 * @throws Exception
	 */
	public static String generate(HashMap<String, Object> data) throws Exception
	{
		String[] keys	= data.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		
		StringBuilder query	= new StringBuilder();
		for(String key : keys) {
			String value	= data.get(key).toString();
			query.append(key).append(value);
		}
		query.append(Configure.API_SECRET);
		
		return Sign.md5(query.toString());
	}

	/**
	 * 接收到notify消息时签名的生成
	 * 如果notify_url中有自定义的query_string的话,自定义的query_string部分的参数不能加入签名.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String genrateByNotify(HttpServletRequest request) throws Exception
	{
		List<String> ex_parameters	= new ArrayList<String>();
		return Sign.genrateByNotify(request, ex_parameters);
	}
	public static String genrateByNotify(HttpServletRequest request, List<String> ex_parameters) throws Exception
	{
    	Enumeration<String> params	= request.getParameterNames();
    	HashMap<String, Object> data	= new HashMap<>();
    	while(params.hasMoreElements()){
    		String name	= params.nextElement();
    		if(ex_parameters.contains(name)) {
    			continue;
    		}
    		data.put(name, request.getParameterValues(name)[0]);
    		
    	}
		return Sign.genrateByResponse(data);
	}
	
	/**
	 * 根据响应值生成响应值的有效签名
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static String genrateByResponse(HashMap<String, Object> response) throws Exception
	{
		String[] keys	= response.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		StringBuilder sign_string = new StringBuilder();
		for(String key : keys) {
			String value	= response.get(key).toString();
			if(key.equals("sign")){
				continue;
			}
			if(key.equals("data")){
				HashMap<String, Object> data= (HashMap<String, Object>) response.get("data");
				String[] dkeys				= data.keySet().toArray(new String[0]);
				Arrays.sort(dkeys);
				value						= "";
				for(String dkey : dkeys){
					value	= value + dkey + data.get(dkey).toString();
				}
			}
			sign_string.append(key).append(value);

		}
		sign_string.append(Configure.API_SECRET);
		Logger.info("响应值SIGN校验待签名字符串:\n" + sign_string.toString());
		return Sign.md5(sign_string.toString());
	}
	
	/**
	 * 对签名字符串做MD5转换
	 * 
	 * @param sign_string
	 * @return
	 * @throws Exception
	 */
	public static String md5(String sign_string) throws Exception
	{
		MessageDigest md5	= MessageDigest.getInstance("MD5");
		byte[] bytes		= md5.digest(sign_string.getBytes("UTF-8"));
	
		StringBuilder sign_builder	= new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign_builder.append("0");
			}
			sign_builder.append(hex.toUpperCase());
		}
		
		return sign_builder.toString();

	}
}

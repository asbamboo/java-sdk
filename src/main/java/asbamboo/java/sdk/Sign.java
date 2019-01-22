package asbamboo.java.sdk;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;

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
	 * 根据响应值生成响应值的有效签名
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String genrateByResponse(HashMap<String, Object> json) throws Exception
	{
		String[] keys	= json.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		StringBuilder sign_string = new StringBuilder();
		for(String key : keys) {
			String value	= json.get(key).toString();
			if(key.equals("sign")){
				continue;
			}
			if(key.equals("data")){
				HashMap<String, Object> data= (HashMap<String, Object>) json.get("data");
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

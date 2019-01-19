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
		System.out.print(query.toString());
		MessageDigest md5	= MessageDigest.getInstance("MD5");
		byte[] bytes		= md5.digest(query.toString().getBytes("UTF-8"));
	
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

package asbamboo.java.sdk;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Comparator;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

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

	public static String genrateByResponse(JSONObject json) throws Exception
	{
//		Map<String, Object> new_object	= new TreeMap<String, Object>(
//			new Comparator<String>(){
//				public int compare(String obj1, String obj2){
//					return obj1.compareTo(obj2);
//				}
//			}
//		);
//		Set<String> json_keyset	= json.keySet();
//		Iterator<String> it		= json_keyset.iterator();
//		while(it.hasNext()){
//			String key	= it.next();
//			if(key.equals("sign")){
//				continue;
//			}
//			if(key.equals("data")){
//				Map<String, Object> new_data	= new TreeMap<String, Object>(
//					new Comparator<String>(){
//						public int compare(String obj1, String obj2){
//							return obj1.compareTo(obj2);
//						}
//					}
//				);
//				JSONObject data			= (JSONObject) json.get(key);
//				Set<String> data_keyset	= data.keySet();
//				Iterator<String> dit	= data_keyset.iterator();
//				while(dit.hasNext()){
//					String dkey	= dit.next();
//					new_data.put(dkey, data.get(dkey));
//				}
//				new_object.put(key, new_data);
//				continue;
//			}
//			new_object.put(key, json.get(key));
//		}
//		
//		JSONObject json_object	= new JSONObject(new LinkedHashMap<String, Object>());
//		String[] keys			= new_object.keySet().toArray(new String[0]);
//		Arrays.sort(keys);
//		for(String key : keys){
//			json_object.put(key, new_object.get(key));
//		}	
//		System.out.print(json_object.toString());
//		System.out.print("\n");

		
//		JSONObject new_object	= new JSONObject(new LinkedHashMap<String, Object>());
//		String[] keys			= json.keySet().toArray(new String[0]);
//		Arrays.sort(keys);
//		for(String key : keys){
//			if(key == "sign"){
//				continue;
//			}
//			if(key == "data"){
//				JSONObject new_data		= new JSONObject(new LinkedHashMap<String, Object>());
//				JSONObject data			= (JSONObject) json.get(key);
//				String[] dkeys			= data.keySet().toArray(new String[0]);
//				Arrays.sort(dkeys);
//				for(String dkey : dkeys){
//					new_data.put(dkey, data.get(dkey));
//				}
//				new_object.put(key, new_data);
//				continue;
//			}
//			new_object.put(key, json.get(key));
//		}
		
		
		JSONStringer new_json_stringer	= new JSONStringer();
		JSONWriter new_json_writer		= new_json_stringer.object();
		String[] keys					= json.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		for(String key : keys){
			if(key.equals("sign")){
				continue;
			}
			if(key.equals("data")){
				JSONStringer new_data_json	= new JSONStringer();
				JSONWriter new_data_writer	= new_data_json.object();
				JSONObject data				= (JSONObject) json.get(key);
				String[] dkeys				= data.keySet().toArray(new String[0]);
				Arrays.sort(dkeys);
				for(String dkey : dkeys){
					new_data_writer.key(dkey).value(data.get(dkey));
				}
				String data_json = new_data_writer.endObject().toString();
				System.out.print(data_json);
				System.out.print("\n");
				new_json_writer.key(key).append(data_json);
				continue;
			}
			new_json_writer.key(key).value(json.get(key));
		}
		System.out.print(new_json_writer.endObject().toString());
		
//		System.out.print((new JSONObject(new_object)).toString());
		System.out.print("\n");
//		String sign_string	= new_object.toString();
//		sign_string	= Configure.API_SECRET + sign_string;
		String sign_string	= "";
		System.out.print(sign_string);
		return Sign.md5(sign_string);
	}
	
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

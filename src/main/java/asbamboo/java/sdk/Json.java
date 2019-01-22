package asbamboo.java.sdk;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月22日
 */
public class Json 
{
	static Gson gson	= new GsonBuilder()
		.registerTypeAdapter(HashMap.class, Json.getDeserializer())	// Gson 里面默认会把number类型转换成double
	.create();
	
	public static HashMap<String, Object> decode(String json)
	{
		HashMap<String, Object> djson	= Json.gson.fromJson(json, HashMap.class);
		return djson;
	}
	
	public static String encode(Object element)
	{
		return Json.gson.toJson(element);
	}
	
	static JsonDeserializer<Object> getDeserializer()
	{
		return new JsonDeserializer<Object>(){
			private HashMap<String, Object> decodeJsonObject(JsonObject json, HashMap<String, Object> decoded)
			{
	            for(Map.Entry<String, JsonElement> entry: json.entrySet()) {
	                String key = entry.getKey();
	                JsonElement element = entry.getValue();
	                if(element.isJsonObject()) {
	                	HashMap<String, Object> inner_decoded = new HashMap<>();
	                	this.decodeJsonObject(element.getAsJsonObject(), inner_decoded);
	                	decoded.put(key, inner_decoded);
	                	continue;
	                }
	                if(element.isJsonPrimitive()){
	                	JsonPrimitive primitive = element.getAsJsonPrimitive();
						decoded.put(key, this.decodeJsonPrimitive(primitive));
	                }
	            }
	            return decoded;
			}
			
			private Object decodeJsonPrimitive(JsonPrimitive primitive)
			{
				if(primitive.isJsonArray()){
					return primitive.getAsJsonArray();
				}else if(primitive.isString()){
					return primitive.getAsString();					
				}else if(primitive.isNumber()){
					return primitive.getAsNumber();
				}else if(primitive.isBoolean()){
					return primitive.getAsBoolean();
				}else if(primitive.isJsonNull()){
					return primitive.getAsJsonNull();
				}
				throw new IllegalStateException();
			}
			
			@Override
			public HashMap<String, Object> deserialize(JsonElement ele, Type type, JsonDeserializationContext context) throws JsonParseException {
				HashMap<String, Object> decoded	= new HashMap<>();
		        if (ele.isJsonObject()) {
					JsonObject json_ob	= ele.getAsJsonObject();
					this.decodeJsonObject(json_ob, decoded);
	            }
		        return decoded;
		    }
		};
	}
}

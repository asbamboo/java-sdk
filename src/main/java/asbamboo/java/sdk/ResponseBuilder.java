package asbamboo.java.sdk;  

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public class ResponseBuilder
{
	/**
	 * 当 is_success 等于 true，表示接口请求成功
	 *  - HTTP Response code 等于 200
	 *  - API Response Json Data 中 code 等于 "0"
	 *  - API Response Json Data 中 sign 有效
	 */
	private boolean is_success;
	
	/**
	 * HTTP Response code 等于 200 时, 解析 API Response Json Data 中的 message
	 * HTTP Response code 不等于 200， 等于HTTP CODE 相关的Message。
	 */
	private String message;

	/**
	 * APi 响应 HTTP CODE
	 */
	private Integer http_code;
	
	/**
	 * APi 响应 HTTP Response
	 */
	private String http_body;
	
	/**
	 * API响应 HTTP Headers
	 */
	private Map<String, List<String>> http_headers;
	
	public ResponseBuilder()
	{
		
	}
			
    /**
     * @param http_code
     * @param http_body
     * @param http_headers
     */
    public ResponseBuilder(Integer http_code, String http_body, Map<String, List<String>> http_headers)
    {
        this.http_code 	 	= http_code;
        this.http_body		= http_body;
        this.http_headers	= http_headers;
        this.parse();
    }
    
    public Integer getHttpCode()
    {
    	return this.http_code;
    }
    
    public String getHttpBody()
    {
    	return this.http_body;
    }
    
    public Map<String, List<String>> getHttpHeaders()
    {
    	return this.http_headers;
    }
    
    /**
     * 解析Api响应结果
     */
    private void parse()
    {
    	if(!this.checkHttpCode()){
    		return;
    	}
    	
    	if(!this.parseHttpBody()){
    		return;
    	}
    }
    
    private boolean checkHttpCode()
    {
    	if(this.http_code != 200){
    		this.is_success	= false;
    		this.message = "HTTP Response Code:" + this.http_code;
    		return false;
    	}
    	if(this.http_code == 200){
    		return true;
    	}
    	
    	return false;
    }
    
    private boolean parseHttpBody()
    {
    	JSONObject decoded_data	= new JSONObject(this.http_body);
    	if(decoded_data.get("code") != "0") {
    		this.is_success	= false;
    		this.message	= decoded_data.get("message").toString(); 
    	}
    	return false;
    }
}

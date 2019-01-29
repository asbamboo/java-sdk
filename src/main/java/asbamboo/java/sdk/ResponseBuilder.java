package asbamboo.java.sdk;  

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import asbamboo.java.sdk.model.*;

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
	protected boolean is_success = false;
	
	/**
	 * HTTP Response code 等于 200 时, 解析 API Response Json Data 中的 message
	 * HTTP Response code 不等于 200， 等于HTTP CODE 相关的Message。
	 */
	protected String message;
	
	/**
	 * 验证响应结果的签名是否正确
	 */
	protected boolean is_valid_sign = false;
	
	/**
	 * Api 响应 HTTP Response 的解析结果
	 */
	protected HashMap<String, Object> decoded_data;  

	/**
	 * APi 响应 HTTP CODE
	 */
	protected Integer http_code;
	
	/**
	 * APi 响应 HTTP Response
	 */
	protected String http_body;
	
	/**
	 * API响应 HTTP Headers
	 */
	protected Map<String, List<String>> http_headers;
	
	public static ResponseBuilder create(String api_name, Integer http_code, String http_body, Map<String, List<String>> http_headers) throws Exception
	{
		if(api_name == "trade.pay"){
			return new TradePayResponse(http_code, http_body, http_headers);
		}else if(api_name == "trade.query"){
			return new TradeQueryResponse(http_code, http_body, http_headers);			
		}else if(api_name == "trade.cancel"){
			return new TradeCancelResponse(http_code, http_body, http_headers);			
		}else if(api_name == "trade.refund"){
			return new TradeRefundResponse(http_code, http_body, http_headers);			
		}
		return new ResponseBuilder(http_code, http_body, http_headers);
	}
			
    /**
     * @param http_code
     * @param http_body
     * @param http_headers
     */
    public ResponseBuilder(Integer http_code, String http_body, Map<String, List<String>> http_headers) throws Exception
    {
        this.http_code 	 	= http_code;
        this.http_body		= http_body;
        this.http_headers	= http_headers;
        this.parse();
    }
    
    public boolean getIsSuccess()
    {
    	return this.is_success;
    }
    
    public String getMessage()
    {
    	return this.message;
    }
    
    public String getDataCode()
    {
    	return this.getDecodedData().get("code").toString();
    }
    
    public HashMap<String, Object> getDecodedData()
    {
    	return this.decoded_data;
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
    private void parse() throws Exception
    {
    	if(!this.checkHttpCode()){
    		return;
    	}
    	
    	if(!this.parseHttpBody()){
    		return;
    	}
    	
    	if(!this.checkSign()){
    		return;
    	}
    	
    	if(!this.checkIsSuccess()){
    		return;
    	}
    }
    
    private boolean checkHttpCode()
    {
    	if(!this.http_code.equals(200)){
    		this.is_success	= false;
    		this.message = "HTTP Response Code:" + this.http_code;
    		return false;
    	}
    	if(this.http_code.equals(200)){
    		return true;
    	}
		this.is_success	= false;    	
    	return false;
    }
    
    private boolean parseHttpBody()
    {
    	this.decoded_data	= Json.decode(this.getHttpBody());
    	if(!this.decoded_data.get("code").equals("0")){
        	this.is_success	= false;
    		this.message	= this.decoded_data.get("message").toString(); 
    	}
    	if(this.decoded_data.get("code").equals("0")){
    		this.message	= this.decoded_data.get("message").toString(); 
    		return true;
    	}
		this.is_success	= false;    	
    	return true;
    }
    
    private boolean checkSign() throws Exception
    {
    	String sign	= Sign.genrateByResponse(this.decoded_data);
    	if(this.decoded_data.get("sign").equals(sign)){
    		this.is_valid_sign	= true;
    		return true;
    	}
		this.is_success	= false;
		this.message	= "接口响应结果未通过签名认证。";
    	return false;
    }
    
    private boolean checkIsSuccess()
    {
    	if(this.http_code.equals(200) && this.decoded_data.get("code").equals("0") && this.is_valid_sign == true){
    		this.is_success	= true;
    		return true;
    	}
    	return false;
    }
}

package asbamboo.java.sdk;

import java.util.List;
import java.util.Map;

/**
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public class ApiResponse
{
	private Integer code;
	
	private String body;
	
	private Map<String, List<String>> headers;
	
    /**
     * @param code
     * @param body
     * @param headers
     */
    public ApiResponse(Integer code, String body, Map<String, List<String>> headers)
    {
        this.code 	 	= code;
        this.body		= body;
        this.headers	= headers;
    }
    
    public Integer getCode()
    {
    	return this.code;
    }
    
    public String getBody()
    {
    	return this.body;
    }
    
    public Map<String, List<String>> getHeaders()
    {
    	return this.headers;
    }
}

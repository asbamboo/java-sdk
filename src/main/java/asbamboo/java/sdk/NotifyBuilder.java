package asbamboo.java.sdk;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务器收到Notify通知(包括页面回跳url)
 *  - notify_url接收的响应
 *  - 或者return_url接收的响应
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月27日
 */
public class NotifyBuilder
{
	protected HttpServletRequest request;
	
	public NotifyBuilder(HttpServletRequest request)
	{
		this.request	= request;
	}
	
	public String getSign()
	{
		return this.request.getParameterValues("sign")[0];		
	}
	
	public String getRandom()
	{
		return this.request.getParameterValues("random")[0];				
	}
	
	public boolean checkSign()
	{
		String valid_sign	= "";
		try{			
			valid_sign	= Sign.genrateByNotify(this.request);
		}catch(Exception e){
			//
		}
		String req_sign		= this.getSign();
		return valid_sign.equals(req_sign);
	}
}

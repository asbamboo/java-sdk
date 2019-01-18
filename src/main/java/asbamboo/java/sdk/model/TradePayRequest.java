package asbamboo.java.sdk.model;

/**
 * 交易支付接口请求数据
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月17日
 */
public class TradePayRequest
{
	private String channel;
	
	private String client_ip;
	
	private String notify_url;
	
	private String out_trade_no;
	
	private String return_url;
	
	private String third_part;
	
	private String title;
	
	private Integer total_fee;
	
	public void setChannel(String channel)
	{
		this.channel	= channel;
	}
	
	public String getChannel()
	{
		return this.channel;
	}
	
	public void setClientIp(String client_ip)
	{
		this.client_ip	= client_ip;
	}
	
	public String getClientIp()
	{
		return this.client_ip;
	}
	
	public void setNotifyUrl(String notify_url)
	{
		this.notify_url	= notify_url;
	}
	
	public String getNotfyUrl()
	{
		return this.notify_url;
	}
	
	public void setOutTradeNo(String out_trade_no)
	{
		this.out_trade_no	= out_trade_no;
	}
	
	public String getOutTradeNo()
	{
		return this.out_trade_no;
	}
	
	public void setReturnUrl(String return_url)
	{
		this.return_url	= return_url;
	}
	
	public String getReturnUrl()
	{
		return this.return_url;
	}
	
	public void setThirdPart(String third_part)
	{
		this.third_part	= third_part;
	}
	
	public String getThirdPart()
	{
		return this.third_part;
	}
	
	public void setTitle(String title)
	{
		this.title	= title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTotalFee(Integer total_fee)
	{
		this.total_fee	= total_fee;
	}
	
	public Integer getTotalFee()
	{
		return this.total_fee;
	}
}

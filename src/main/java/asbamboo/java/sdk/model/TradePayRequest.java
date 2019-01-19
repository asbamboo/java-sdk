package asbamboo.java.sdk.model;

import asbamboo.java.sdk.RequestBuilder;

/**
 * 交易支付接口请求数据
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月17日
 */
public class TradePayRequest extends RequestBuilder
{
	public void setChannel(String channel)
	{
		this.request_data.put("channel", channel);
	}
	
	public void setClientIp(String client_ip)
	{
		this.request_data.put("client_ip", client_ip);
	}

	public void setNotifyUrl(String notify_url)
	{
		this.request_data.put("notify_url", notify_url);
	}
	
	public void setOutTradeNo(String out_trade_no)
	{
		this.request_data.put("out_trade_no", out_trade_no);
	}
	
	public void setReturnUrl(String return_url)
	{
		this.request_data.put("return_url", return_url);
	}
	
	public void setThirdPart(String third_part)
	{
		this.request_data.put("third_part", third_part);
	}
	
	public void setTitle(String title)
	{
		this.request_data.put("title", title);
	}
	
	public void setTotalFee(Integer total_fee)
	{
		this.request_data.put("total_fee", total_fee);
	}
	
	protected String getApiName()
	{
		return "trade.pay";
	}
}

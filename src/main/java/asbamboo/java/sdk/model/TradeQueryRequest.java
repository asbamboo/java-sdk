package asbamboo.java.sdk.model;

import asbamboo.java.sdk.RequestBuilder;

/**
 * 交易查询接口请求数据
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月17日
 */
public class TradeQueryRequest extends RequestBuilder
{
	public void setInTradeNo(String in_trade_no)
	{
		this.request_data.put("in_trade_no", in_trade_no);
	}

	public void setOutTradeNo(String out_trade_no)
	{
		this.request_data.put("out_trade_no", out_trade_no);
	}
	
	protected String getApiName()
	{
		return "trade.query";
	}
}

package asbamboo.java.sdk.model;

import asbamboo.java.sdk.RequestBuilder;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月22日
 */
public class TradeRefundRequest extends RequestBuilder
{
	public void setInTradeNo(String in_trade_no)
	{
		this.request_data.put("in_trade_no", in_trade_no);
	}

	public void setOutTradeNo(String out_trade_no)
	{
		this.request_data.put("out_trade_no", out_trade_no);
	}
	
	public void setOutRefundNo(String out_refund_no)
	{
		this.request_data.put("out_refund_no", out_refund_no);		
	}

	public void setOutRefundFee(Integer refund_fee)
	{
		this.request_data.put("refund_fee", refund_fee);		
	}

	public void setThirdPart(String third_part)
	{
		this.request_data.put("third_part", third_part);
	}
	
	protected String getApiName()
	{
		return "trade.refund";
	}
}

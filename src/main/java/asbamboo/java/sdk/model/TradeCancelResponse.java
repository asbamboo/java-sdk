package asbamboo.java.sdk.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asbamboo.java.sdk.ResponseBuilder;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月22日
 */
public class TradeCancelResponse extends ResponseBuilder
{

	private HashMap<String, Object> item;
	
	public TradeCancelResponse(Integer http_code, String http_body, Map<String, List<String>> http_headers) throws Exception
	{
		super(http_code, http_body, http_headers);
		this.item			= (HashMap<String, Object>) this.decoded_data.get("data");
	}
	
	public String getCancelYmdhis()
	{
		return this.item.get("cancel_ymdhis").toString();
	}
	
	public String getChannel()
	{
		return this.item.get("channel").toString();
	}
	
	public String getClientIp()
	{
		return this.item.get("client_ip").toString();		
	}

	public String getInTradeNo()
	{
		return this.item.get("in_trade_no").toString();		
	}

	public String getOutTradeNo()
	{
		return this.item.get("out_trade_no").toString();		
	}

	public String getTitle()
	{
		return this.item.get("title").toString();		
	}

	public String getTotalFee()
	{
		return this.item.get("total_fee").toString();		
	}
	
	public String getTradeStatus()
	{
		return this.item.get("trade_status").toString();
	}
}

package asbamboo.java.sdk.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import asbamboo.java.sdk.ResponseBuilder;

/**
 * 交易支付接口请求数据
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月17日
 */
public class TradeRefundResponse extends ResponseBuilder
{
	private Map<String, Object> item;
	
	public TradeRefundResponse(Integer http_code, String http_body, Map<String, List<String>> http_headers) throws Exception
	{
		super(http_code, http_body, http_headers);
		this.item			= (HashMap<String, Object>) this.decoded_data.get("data");
	}
	
	public String getInRefundNo()
	{
		return this.item.get("in_refund_no").toString();		
	}

	public String getInTradeNo()
	{
		return this.item.get("in_trade_no").toString();		
	}

	public String getOutRefundNo()
	{
		return this.item.get("out_refund_no").toString();		
	}

	public String getOutTradeNo()
	{
		return this.item.get("out_trade_no").toString();		
	}

	public String getRefundFee()
	{
		return this.item.get("refund_fee").toString();		
	}

	public String getRefundPayYmdhis()
	{
		return this.item.get("refund_pay_ymdhis").toString();		
	}

	public String getTradeStatus()
	{
		return this.item.get("trade_status").toString();
	}
}

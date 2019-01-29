package asbamboo.java.sdk.notify;

import javax.servlet.http.HttpServletRequest;
import asbamboo.java.sdk.NotifyBuilder;

public class TradePayNotify extends NotifyBuilder
{
	public TradePayNotify(HttpServletRequest request)
	{
		super(request);
	}

	public String getChannel()
	{
		return this.request.getParameterValues("channel")[0];
	}
	
	public String getInTradeNo()
	{
		return this.request.getParameterValues("in_trade_no")[0];
	}
	
	public String getTitle()
	{
		return this.request.getParameterValues("title")[0];		
	}

	public String getOutTradeNo()
	{
		return this.request.getParameterValues("out_trade_no")[0];		
	}

	public String getTotalFee()
	{
		return this.request.getParameterValues("total_fee")[0];		
	}

	public String getClientIp()
	{
		return this.request.getParameterValues("client_ip")[0];		
	}

	public String getTradeStatus()
	{
		return this.request.getParameterValues("trade_status")[0];		
	}

	public String getPayokYmdhis()
	{
		return this.request.getParameterValues("payok_ymdhis")[0];		
	}

	public String getPayedYmdhis()
	{
		return this.request.getParameterValues("payed_ymdhis")[0];		
	}

	public String getCancelYmdhis()
	{
		return this.request.getParameterValues("cancel_ymdhis")[0];		
	}
}
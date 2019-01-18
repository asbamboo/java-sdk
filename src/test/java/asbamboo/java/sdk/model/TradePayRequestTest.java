package asbamboo.java.sdk.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradePayRequestTest {
	
    @Test public void testMain() {
    	Date now				= new Date();
    	SimpleDateFormat sdf	= new SimpleDateFormat("yyyymmddHHmmssSSS");
    	String channel			= "ALIPAY_QRCD";
    	String client_ip		= "127.0.0.1";
    	String notify_url		= "http://notify_url";
    	String out_trade_no		= sdf.format(now);
    	String return_url		= "http://return_url";
    	String third_part		= "{}";
    	String title			= "test";
    	Integer total_fee		= 100;
    	
    	TradePayRequest trade_pay_request = new TradePayRequest();
    	trade_pay_request.setChannel(channel);
    	trade_pay_request.setClientIp(client_ip);
    	trade_pay_request.setNotifyUrl(notify_url);
    	trade_pay_request.setOutTradeNo(out_trade_no);
    	trade_pay_request.setReturnUrl(return_url);
    	trade_pay_request.setThirdPart(third_part);
    	trade_pay_request.setTitle(title);
    	trade_pay_request.setTitle(title);
    	trade_pay_request.setTotalFee(total_fee);
    	
    	org.junit.Assert.assertEquals("Channel is not equals", channel, trade_pay_request.getChannel());
    	org.junit.Assert.assertEquals("client_ip is not equals", client_ip, trade_pay_request.getClientIp());
    	org.junit.Assert.assertEquals("notify_url is not equals", notify_url, trade_pay_request.getNotfyUrl());
    	org.junit.Assert.assertEquals("out_trade_no is not equals", out_trade_no, trade_pay_request.getOutTradeNo());
    	org.junit.Assert.assertEquals("return_url is not equals", return_url, trade_pay_request.getReturnUrl());
    	org.junit.Assert.assertEquals("third_part is not equals", third_part, trade_pay_request.getThirdPart());
    	org.junit.Assert.assertEquals("title is not equals", title, trade_pay_request.getTitle());    	
    	org.junit.Assert.assertEquals("total_fee is not equals", total_fee, trade_pay_request.getTotalFee());    	
    }
    
}

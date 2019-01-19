package asbamboo.java.sdk.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import asbamboo.java.sdk.ApiResponse;
import asbamboo.java.sdk.Configure;

public class TradePayRequestTest {
	
    @Test public void testMain() {
    	try{
    		Configure.API_URL		= "http://api.asbamboo.de";
        	Configure.API_APP_KEY	= "5c3adf7db89b8";
        	Configure.API_SECRET	= "6c030d9bacbbc73dc5aabf9552e54f78";
        	
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
	    	
	    	trade_pay_request.generateSign();
	    	
	    	System.out.print(trade_pay_request.request_data);
	    	
	    	ApiResponse	response	= trade_pay_request.post();
	    	
	    	System.out.print(response.getCode());
	    	System.out.print(response.getBody());
    	}catch(Exception e){
    		org.junit.Assert.assertTrue(e.toString(),false);
    	}
    }
    
}

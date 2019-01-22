package asbamboo.java.sdk.model;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import asbamboo.java.sdk.TestConfigure;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月21日
 */
public class TradeApiRequestTest
{
	static String out_trade_no;
	
    @Test public void testPay()
    {
    	try{
    		TestConfigure.c();
    		
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
	    	
	    	TradePayResponse response	= (TradePayResponse) trade_pay_request.post();

	    	org.junit.Assert.assertTrue(response.getIsSuccess());
	    	org.junit.Assert.assertNotNull(response.getMessage());
	    	org.junit.Assert.assertEquals("200", response.getHttpCode().toString());
	    	org.junit.Assert.assertNotNull(response.getHttpBody());
	    	org.junit.Assert.assertNotNull(response.getHttpHeaders());
	    	org.junit.Assert.assertEquals("", response.getCancelYmdhis());
	    	org.junit.Assert.assertEquals(channel, response.getChannel());
	    	org.junit.Assert.assertEquals(client_ip, response.getClientIp());
	    	org.junit.Assert.assertNotNull(response.getInTradeNo());
	    	org.junit.Assert.assertEquals(out_trade_no, response.getOutTradeNo());
	    	org.junit.Assert.assertEquals("", response.getPayedYmdhis());
	    	org.junit.Assert.assertEquals("", response.getPayokYmdhis());
	    	org.junit.Assert.assertNotNull(response.getQrCode());
	    	org.junit.Assert.assertEquals(title, response.getTitle());
	    	org.junit.Assert.assertEquals(total_fee.toString(), response.getTotalFee());
	    	org.junit.Assert.assertEquals("NOPAY", response.getTradeStatus());
	    	
	    	TradeApiRequestTest.out_trade_no	= out_trade_no;
//	    	
//	    	System.out.print(response.getInTradeNo());
//	    	System.out.print(response.getHttpCode());
//	    	System.out.print(response.getHttpBody());
    	}catch(Exception e){
    		org.junit.Assert.assertTrue(e.toString(),false);
    	}
    }
    
    @Test public void testQuery()
    {
		TestConfigure.c();
    	try{
	    	TradeQueryRequest trade_query = new TradeQueryRequest();
	    	trade_query.setOutTradeNo(TradeApiRequestTest.out_trade_no);
	    	TradeQueryResponse response			= (TradeQueryResponse) trade_query.post();

//	    	System.out.println(response.getHttpHeaders());
//	    	System.out.println(response.getHttpBody());
//	    	System.out.println(response.getHttpCode());
//	    	System.out.println(response.getIsSuccess());
	    	
	    	org.junit.Assert.assertTrue(response.getIsSuccess());
	    	org.junit.Assert.assertNotNull(response.getMessage());
	    	org.junit.Assert.assertEquals("200", response.getHttpCode().toString());
	    	org.junit.Assert.assertNotNull(response.getHttpBody());
	    	org.junit.Assert.assertNotNull(response.getHttpHeaders());
	    	org.junit.Assert.assertEquals(TradeApiRequestTest.out_trade_no, response.getOutTradeNo());
	    	
    	}catch(Exception e){
    		org.junit.Assert.assertTrue(e.toString(),false);
    	}	    	
    }

    @Test public void testCancel()
    {
		TestConfigure.c();
    	try{
    		TradeCancelRequest trade_cancel		= new TradeCancelRequest();
    		trade_cancel.setOutTradeNo(TradeApiRequestTest.out_trade_no);
    		TradeCancelResponse response			= (TradeCancelResponse) trade_cancel.post();

	    	System.out.println(response.getHttpHeaders());
	    	System.out.println(response.getHttpBody());
	    	System.out.println(response.getHttpCode());
	    	System.out.println(response.getIsSuccess());
	    	
	    	org.junit.Assert.assertTrue(response.getIsSuccess());
	    	org.junit.Assert.assertNotNull(response.getMessage());
	    	org.junit.Assert.assertEquals("200", response.getHttpCode().toString());
	    	org.junit.Assert.assertNotNull(response.getHttpBody());
	    	org.junit.Assert.assertNotNull(response.getHttpHeaders());
	    	org.junit.Assert.assertEquals(TradeApiRequestTest.out_trade_no, response.getOutTradeNo());
	    	org.junit.Assert.assertEquals("CANCLE", response.getTradeStatus());
	    	
    	}catch(Exception e){
    		org.junit.Assert.assertTrue(e.toString(),false);
    	}	    	
    }
}

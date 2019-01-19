package asbamboo.java.sdk;

import java.util.HashMap;

import org.junit.Test;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public class SignTest
{
    @Test public void testSign()
    {
    	Configure.API_SECRET			= "test";
    	String sign						= "";
    	HashMap<String, Object>	data	= new HashMap<>();
    	data.put("test1", "123456");
    	data.put("test2", 100);
    	try {    		
        	sign	= Sign.generate(data);
    	}catch(Exception e){
    		
    	}
    	org.junit.Assert.assertEquals("sign is not equals", "1F7E8611433AE539B356837FD8842C72", sign);
    }
}

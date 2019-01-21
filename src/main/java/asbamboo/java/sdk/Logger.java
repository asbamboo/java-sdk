package asbamboo.java.sdk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.*;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月21日
 */
public class Logger
{
	public static void info(String message)
	{
		if(!Logger.isLog()){
			return;			
		}
		
		try{
			File file	= new File(Configure.PROJECT_LOG_PATH);
			if(!file.exists()){
				file.getParentFile().mkdirs();
			}
			FileOutputStream out	= new FileOutputStream(file, true);
			String log_message		= Logger.ymdhis() + " " + message + "\n";
			out.write(log_message.getBytes("UTF-8"));			
			out.close();
		}catch(IOException e) {
			System.out.print(e);
			//
		}
	}
	
	public static boolean isLog(){
		return null != Configure.PROJECT_LOG_PATH;
	}
	
	public static String ymdhis()
	{
		Date 				date	= new Date();
		SimpleDateFormat	format	= new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return format.format(date); 
	}
}

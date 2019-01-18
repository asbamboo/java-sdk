package asbamboo.java.sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.io.*;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
abstract class RequestBuilder
{
	protected HashMap<String, Object> request_data;
	
	public void requestBuilder()
	{
		this.request_data	= new HashMap<>();
		this.request_data.put("api_name", this.getApiName());
		this.request_data.put("app_key", this.getAppKey());
		this.request_data.put("format", this.getFormat());
		this.request_data.put("timestamp", this.getTimestamp());
		this.request_data.put("version", this.getVersion());
	}
	
	public ApiResponse post() throws IOException
	{
		URL asbamboo_url					= new URL(this.getApiUrl());
		HttpURLConnection conn				= (HttpURLConnection) asbamboo_url.openConnection();
		try {			
			conn.setConnectTimeout(this.getHttpTimeout());
			conn.setRequestProperty(Constant.HTTP_HEADER_USER_AGENT, this.getHttpUserAgentValue());
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			OutputStream out	= conn.getOutputStream();
			try{
				out.write(this.httpBuildQuery().getBytes("UTF-8"));
			}finally{
				if(out != null){
					out.close();				
				}
			}
			
			InputStream input;
	        int http_res_code							= conn.getResponseCode();
			StringBuilder http_res_data					= new StringBuilder();
			Map<String, List<String>> http_res_headers	= conn.getHeaderFields();
	        int content_length				= Integer.parseInt(conn.getHeaderField("Content-Length"));
	        if(http_res_code >= 200 && http_res_code < 300) {
	        	input	= conn.getInputStream();
	        } else {
	        	input	= conn.getErrorStream();
	        }
	        if(content_length > 0){
	        	BufferedReader	bf_reader	= new BufferedReader(new InputStreamReader(input, "UTF-8"));
	        	String line;
	        	while((line = bf_reader.readLine()) != null) {
	        		http_res_data.append(line);        		
	        	}
	        }
	        return new ApiResponse(http_res_code, http_res_data.toString(), http_res_headers);
		}finally {
            if (conn != null) {
                conn.disconnect();
            }
        }	
	}
	
	public void generateSign() throws Exception
	{
		try{
			this.request_data.remove("sign");
			this.request_data.put("sign", Sign.generate(this.request_data));	
		}catch(Exception e) {
			throw e;
		}
	}

	protected String getApiUrl()
	{
		return Configure.API_URL;
	}
	
	public String getHttpUserAgentValue()
	{
		return Configure.HTTP_HEADER_USER_AGENT_VALUE;
	}
	
	public String httpBuildQuery() throws IOException
	{
		StringBuilder query	= new StringBuilder();
		for(String key : this.request_data.keySet()) {
			String value	= this.request_data.get(key).toString(); 
			value			= URLEncoder.encode(value, "UTF-8");
			query.append(key + "=" + value + "&");			
		}
		String query_string	= query.toString();
		query_string		= query_string.substring(0, query_string.length() - 1);
		return query_string;
	}
	
	protected Integer getHttpTimeout()
	{
		return Configure.HTTP_REQUEST_TIMEOUT;
	}
	
	protected String getAppKey()
	{
		return Configure.API_APP_KEY;
	}

	protected String getFormat()
	{
		return Configure.API_FORMAT;
	}
	
	protected String getVersion()
	{
		return Configure.API_VERSION;
	}

	protected String getTimestamp()
	{
		LocalDate 			date	= LocalDate.now();
		SimpleDateFormat	format	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return format.format(date); 
	}
	
	protected abstract String getApiName();	
}

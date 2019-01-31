package asbamboo.java.sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月18日
 */
public abstract class RequestBuilder
{
	public HashMap<String, Object> request_data;
	
	public RequestBuilder()
	{
		this.request_data	= new HashMap<>();
		this.request_data.put("api_name", this.getApiName());
		this.request_data.put("app_key", this.getAppKey());
		this.request_data.put("format", this.getFormat());
		this.request_data.put("timestamp", this.getTimestamp());
		this.request_data.put("version", this.getVersion());
	}
	
	public String submit() throws Exception
	{
		this.generateSign();
		StringBuilder sb	= new StringBuilder();
		
		sb.append("<!DOCTYPE HTML>");
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<form method=\"post\" action=\"" + this.getApiUrl() + "\">\n");

        for(String key : this.request_data.keySet()) {
			String value	= this.request_data.get(key).toString().replace("\"", "&quot;"); 
			sb.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + value + "\" />\n");
		}

        sb.append("<input type=\"submit\" value=\"立即支付\">\n");
        sb.append("</form>\n");
        sb.append("<script>document.forms[0].submit();</script>");
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();
	}
	
	public ResponseBuilder post() throws Exception
	{
		return this.post(true);
	}
	public ResponseBuilder post(boolean genrate_sign) throws Exception
	{
		if(genrate_sign){
			this.generateSign();			
		}
		
		URL asbamboo_url					= new URL(this.getApiUrl());
		Logger.info("API接口请求URL:" + this.getApiUrl());
		HttpURLConnection conn				= (HttpURLConnection) asbamboo_url.openConnection();
		try {			
			conn.setConnectTimeout(this.getHttpTimeout());
			conn.setReadTimeout(this.getHttpTimeout());
			conn.setUseCaches(false);
			conn.setRequestProperty(Constant.HTTP_HEADER_USER_AGENT, this.getHttpUserAgentValue());
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			OutputStream out	= conn.getOutputStream();
			try{
				String http_query	= this.httpBuildQuery(); 
				out.write(http_query.getBytes("UTF-8"));
				Logger.info("API接口请求参数:\n" + http_query);
			}finally{
				if(out != null){
					out.close();				
				}
			}
			InputStream input;
	        int http_res_code							= conn.getResponseCode();
			StringBuilder http_res_data					= new StringBuilder();
			Map<String, List<String>> http_res_headers	= conn.getHeaderFields();
	        if(http_res_code >= 200 && http_res_code < 300) {
	        	input	= conn.getInputStream();
	        } else {
	        	input	= conn.getErrorStream();
	        }
        	BufferedReader	bf_reader	= new BufferedReader(new InputStreamReader(input, "UTF-8"));
        	String line;
        	while((line = bf_reader.readLine()) != null) {
        		http_res_data.append(line);        		
        	}
			Logger.info("API接口响应HTTPCODE:" + http_res_code);
			Logger.info("API接口响应值:\n" + http_res_data.toString());			
	        return ResponseBuilder.create(this.getApiName(), http_res_code, http_res_data.toString(), http_res_headers);
		}finally {
            if (conn != null) {
                conn.disconnect();
            }
        }	
	}
	
	public void generateSign() throws Exception
	{
		this.request_data.remove("sign");
		this.request_data.put("sign", Sign.generate(this.request_data));	
	}

	protected String getApiUrl()
	{
		return Configure.API_URL;
	}

	protected boolean isFormSubmit()
	{
		return false;
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
			key				= URLEncoder.encode(key, "UTF-8");
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
		Date 				date	= new Date();
		SimpleDateFormat	format	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return format.format(date); 
	}
	
	protected abstract String getApiName();	
}

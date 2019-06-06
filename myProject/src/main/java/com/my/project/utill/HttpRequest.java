package com.my.project.utill;

import com.sun.xml.internal.org.jvnet.mimepull.Header;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * <strong>Description : </strong>
 * <br>java post get 请求类
 * <strong>Create on : 2017年9月23日 上午12:11:29<br></strong>
 * <p>
 * <strong>Copyright (C) Inspur Co.,Ltd.<br></strong>
 * <p>
 * @author zhaoxiumin<br>
 * @version <strong>V1.0</strong>
 */
public class HttpRequest {
	public static String sendGet(String url){
	    String result = "";
        BufferedReader in = null;
        byte b[] = new byte[1024];
        OutputStream out=null;
        PrintWriter pw=null;
		try {
			/*
			 * 创建请求路径
			 */
			String urlName =url;
			  URL realUrl = new URL(urlName);
			  // 打开和URL之间的连接
	            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.setRequestProperty("Accept-Charset", "UTF-8");
	            connection.setRequestProperty("contentType", "UTF-8");
				connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false);
	            connection.connect();
	        	int responsecode =connection.getResponseCode();
	        	if(responsecode==302){
					String header = connection.getHeaderField("location");
					realUrl = new URL(header);

					connection = (HttpURLConnection) realUrl.openConnection();
					connection.setRequestMethod("GET");
					connection.addRequestProperty("Referer",header);
					connection.connect();
					int responsecode2 =connection.getResponseCode();
					System.out.println(header);
				}
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream(),"UTF-8"));
//	            out =new FileOutputStream("D:/apps/"+123+".txt",false) ;
//	           pw = new PrintWriter(out);
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
			String [] strs = result.split("\\[")[1].split("]")[0].split("}}")[0].split("\\{");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public static void main(String[] args) {
		sendGet("http://bf.139cai.com/mcache/livejcjs/20140101.js");
	}
}

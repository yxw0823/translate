package com.framework.core.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.alibaba.fastjson.JSON;

public class HttpUtils {
	private static final Charset UTF_8=Charset.forName("utf-8");
	private static final int HTTP_PORT=8080;
	private static final int HTTPS_PORT=8443;
	/**  
     * 访问https的网站  
     * @param httpclient  
     */    
    private static void setSSL(HttpClient httpclient){    
        //调用ssl    
         try {    
                SSLContext sslcontext = SSLContext.getInstance("TLS");    
                sslcontext.init(null, new TrustManager[] { truseAllManager }, null);    
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);    
//                Scheme https = new Scheme("https", sf, 443);    
//                httpclient.getConnectionManager().getSchemeRegistry().register(https);    
                ClientConnectionManager ccm = httpclient.getConnectionManager();
                SchemeRegistry sr = ccm.getSchemeRegistry();
                sr.register(new Scheme("https", HTTPS_PORT, sf));
         } catch (Exception e) {    
                e.printStackTrace();    
         }    
    }    
    /**  
     * 重写验证方法，取消检测ssl  
     */    
    private static TrustManager truseAllManager = new X509TrustManager(){    
    
        public void checkClientTrusted(    
                java.security.cert.X509Certificate[] arg0, String arg1) {    
            // TODO Auto-generated method stub    
                
        }    
    
        public void checkServerTrusted(    
                java.security.cert.X509Certificate[] arg0, String arg1) {    
            // TODO Auto-generated method stub    
                
        }    
    
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {    
            // TODO Auto-generated method stub    
            return null;    
        }    
            
    };   
    
    private static HttpClient getHttpClient()
	{
		// Create and initialize HTTP parameters
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		// Create and initialize scheme registry
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", HTTP_PORT, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", HTTPS_PORT, SSLSocketFactory.getSocketFactory()));

		// Create an HttpClient with the ThreadSafeClientConnManager.
		// This connection manager must be used if more than one thread
		// will
		// be using the HttpClient.
		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);
		// 此值应该跟系统配置同步
		cm.setMaxTotal(Integer.MAX_VALUE);
		cm.setDefaultMaxPerRoute(Integer.MAX_VALUE);

		DefaultHttpClient hc = new DefaultHttpClient(cm, params);
		hc.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

		HttpConnectionParams.setSoTimeout(params, (int) 60000 );// 设置超时时间60s，等待响应超时时间
		// 此值应该跟系统配置同步
		HttpConnectionParams.setConnectionTimeout(params, (int) 20000); // 设置连接管理器连接超时时间
		return hc;
	}
   static String sessionid="";
    @SuppressWarnings("deprecation")
	public static String post(String url,Map<String, String> params,Map<String,File> files,boolean enableSSL) throws Exception{
    	String result="";
    	HttpClient httpClient =getHttpClient();
    	if(enableSSL)
    	setSSL(httpClient);
    	HttpPost post = new HttpPost(url);  
    	
//    	if(cookieStore!=null){
//    		((AbstractHttpClient) httpClient).setCookieStore(cookieStore);
//    		 //cookie
//             cookieStore= ((AbstractHttpClient) httpClient).getCookieStore();  
//       	      List<Cookie> cookies =cookieStore.getCookies();
//	          String tmpcookies = "";
//	          for (Cookie ck : cookies) {
//	                tmpcookies += ck.toString()+";";
//	          }
//	          if (tmpcookies.length()!=0) {
//	        	System.out.println("发送cookie"+tmpcookies);
//			  }
//    	}
        	
//    	System.out.println("请求cookie"+cookieStr);
//    	post.setHeader("Cookie", cookieStr);
//    	post.setHeader("Connection","Keep-Alive");
//    	post.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);  
//        post.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);  
//        post.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);  
//        post.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);
    	post.setHeader("Connection","keep-alive");
    	if (params!=null&&files==null) {
    		 List<NameValuePair> myparams = new ArrayList<NameValuePair>();  
    		 Set<String> keys = params.keySet();
    		 for (String k : keys) {
				myparams.add(new BasicNameValuePair(k,params.get(k)));  
			 }
    		 post.setEntity(new UrlEncodedFormEntity(myparams,UTF_8));
		}
    	else if (params!=null&&files!=null) {
    		 MultipartEntity mpEntity = new MultipartEntity();
    		 Set<String> keys = params.keySet();
    		 for (String k : keys) {
 				mpEntity.addPart(k, new StringBody(params.get(k),UTF_8));
 			 }
    		 Set<String> filenames = files.keySet();
    		 for (String fname : filenames) {
				mpEntity.addPart(fname, new FileBody(files.get(fname)));
			}
    		 post.setEntity(mpEntity);
//    		 post.setHeader("Content-Type", "multipart/form-data");
    		 //addPart(data.getName(), new StringBody(data.getValue(), "text/plain", Charset.forName("UTF-8")));
		}
    	if(sessionid.length()!=0){
        	post.setHeader("Cookie", "JSESSIONID="+sessionid);
        	System.out.println("发送"+sessionid);
    	}
        // Execute request and get the response  
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
        	//context
        	 HttpEntity entityRep = response.getEntity();   
             if (entityRep != null) {      
            	 result=(EntityUtils.toString(entityRep));  
             } 
             //cookie
//             cookieStore= ((AbstractHttpClient) httpClient).getCookieStore();  
//        	  List<Cookie> cookies =cookieStore.getCookies();
//	          String tmpcookies = "";
//	          for (Cookie ck : cookies) {
//	                tmpcookies += ck.toString()+";";
//	          }
//	          if (tmpcookies.length()!=0) {
//	        	System.out.println("获取cookie"+tmpcookies);
//			  }
             String tmp_sessionid=getCookie( "JSESSIONID",httpClient);
             if(tmp_sessionid!=null)
            	 sessionid=tmp_sessionid;
             System.out.println("接受"+tmp_sessionid);
		}
       
        post.releaseConnection();
        httpClient=null;
        return result;
    }
    
    public static List<Cookie> getCookies(HttpClient httpclient) {
        return ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
      }

      public static Map<String,String> getMapCookies(HttpClient httpclient) {
        Map<String,String> map = new HashMap<String,String>();
        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        for (Cookie c : cookies) {
          map.put(c.getName(), c.getValue());
        }
        return map;
      }

      public  static String getCookie(String key,HttpClient httpclient) {
        for (Cookie c : ((AbstractHttpClient) httpclient).getCookieStore()
            .getCookies()) {
          if (c.getName().equals(key))
            return c.getValue();
        }
        return null;
      }
    
	public static void main(String[] args)throws Exception {
//		Map<String, String> param=new HashMap<String, String>(){{
//			put("username", "admin");
//			put("password", "YWRtaW4xMTE=");
//		}};
//		String result_login=(post("https://192.168.160.85:8443/webapi/auth/login",param,null,true));
//		System.out.println(result_login);
//		post("http://127.0.0.1:8080/gzyd/apps/sys/AccountAction.do?method=loginPost",true); 
	}
} 
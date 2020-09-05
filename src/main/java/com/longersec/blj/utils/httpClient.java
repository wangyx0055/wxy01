package com.longersec.blj.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class httpClient {
	 /**
     * Get请求，方便到一个url接口来获取结果
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        @SuppressWarnings("deprecation")
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    /**
     * post请求
     *
     * @param url
     * @param jsonObject object
     * @return json object
     */
    public static JSONObject doPost(String url, JSONObject jsonObject) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;

        try {
            post.addHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));
            
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(entity);
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    
    public static String doPostResStr(String url, JSONObject jsonObject) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        String result = "";
        try {
            post.addHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));
            
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public static String doGetResStr(String url){
        @SuppressWarnings("deprecation")
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try{
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    /** 
     * 发送 http post 请求，参数以原生字符串进行提交 
     * @param url 
     * @param encode 
     * @return 
     */  
    public static String httpPostRaw(String url,String stringJson,Map<String,String> headers, String encode){  
        if(encode == null){  
            encode = "utf-8";  
        }  
        //HttpClients.createDefault()等价于 HttpClientBuilder.create().build();   
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();   
        HttpPost httpost = new HttpPost(url);  
        
        //设置header
        httpost.setHeader("Content-type", "application/json");    
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        //组织请求参数  
        StringEntity stringEntity = new StringEntity(stringJson, encode);  
        httpost.setEntity(stringEntity);  
        String content = null;  
        CloseableHttpResponse  httpResponse = null;  
        try {  
            //响应信息
            httpResponse = closeableHttpClient.execute(httpost);  
            HttpEntity entity = httpResponse.getEntity();  
            content = EntityUtils.toString(entity, encode);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {
                assert httpResponse != null;
                httpResponse.close();
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        try {  //关闭连接、释放资源  
            closeableHttpClient.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }    
        return content;  
    }  
 
    public static String getrandomCharacter(int integer) {
		String result = "";
		for(int i=0; i< integer; i++) {
			result += (char)('a' + Math.random() * ('z' - 'a' + 1));
		}
		return result;
			
	}
    
    public static String getSha1(String str) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String listToString(List<String> mList, String interval) {
        String convertedListStr = "";
        if (null != mList && mList.size() > 0) {
            String[] mListArray = mList.toArray(new String[mList.size()]);
            for (int i = 0; i < mListArray.length; i++) {
            if (i < mListArray.length - 1) {
                convertedListStr += mListArray[i] + interval;
            } else {
                convertedListStr += mListArray[i];
            }
            }
            return convertedListStr;
        } else return "List is null!!!";
    }
    
    public static String getServerIp(final HttpServletRequest request){
		return request.getHeader("Host");
	}
    
    public static String getRemortIP(HttpServletRequest request) {
    	/*
    	 * if (request.getHeader("x-forwarded-for") == null) {
    		return request.getRemoteAddr();
    	}
    	return request.getHeader("x-forwarded-for");
    	*/
    	try{
			String remoteAddr = request.getHeader("X-Forwarded-For");
			// 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
			if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
				String[] array = remoteAddr.split(",");
				for (String element : array) {
					if (isEffective(element)) {
						remoteAddr = element;
						break;
					}
				}
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getHeader("X-Real-IP");
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
			return remoteAddr;
		}catch(Exception e){
			//log.error("get romote ip error,error message:"+e.getMessage());
			return "";
		}
	}
    
    /**
	 * 获取客户端源端口
	 * @param request
	 * @return
	 */
	public static Long getRemotePort(final HttpServletRequest request){
		try{
			String port = request.getHeader("X-Real-Port");
			if( !port.isEmpty()) {
				try{
					return Long.parseLong(port);
				}catch(NumberFormatException ex){
					//log.error("convert port to long error , port:	"+port);
					return 0l;
				}
			}else{
				return 0l;
			}		
		}catch(Exception e){
			//log.error("get romote port error,error message:"+e.getMessage());
			return 0l;
		}
	}
	
	/**
	 * 远程地址是否有效.
	 * 
	 * @param remoteAddr
	 *            远程地址
	 * @return true代表远程地址有效，false代表远程地址无效
	 */
	private static boolean isEffective(final String remoteAddr) {
		boolean isEffective = false;
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			isEffective = true;
		}
		return isEffective;
	}

    //验证ip，0非法，1，ipv4，2ipv6
    public static Integer validIPAddressAll(String IP) {
    	 
        if (!IP.contains(".") && !IP.contains(":")) {
            return 0;
        }
        //如果是1
        if (IP.contains(".")) {
            if (IP.endsWith(".")) {
                return 0;
            }
            String[] arr = IP.split("\\.");
            if (arr.length != 4) {
                return 0;
            }
 
            for (int i = 0; i < 4; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3) {
                    return 0;
                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') {
                        continue;
                    }
                    return 0;
                }
                if (Integer.valueOf(arr[i]) > 255 || (arr[i].length() >= 2 && String.valueOf(arr[i]).startsWith("0"))) {
                    return 0;
                }
            }
            return 1;
        }//如果是1
 
        //如果是2
        if (IP.contains(":")) {
            if (IP.endsWith(":") && !IP.endsWith("::")) {
                return 0;
            }
            //如果包含多个“::”，一个2地址中只能出现一个“::”
            if (IP.indexOf("::") != -1 && IP.indexOf("::", IP.indexOf("::") + 2) != -1) {
                return 0;
            }
 
            //如果含有一个“::”
            if (IP.contains("::")) {
                String[] arr = IP.split(":");
                if (arr.length > 7 || arr.length < 1) {//"1::"是最短的字符串
                    return 0;
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        continue;
                    }
                    if (arr[i].length() > 4) {
                        return 0;
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return 0;
                    }
                }
                return 2;
            }
 
            //如果不含有“::”
            if (!IP.contains("::")) {
                String[] arr = IP.split(":");
                if (arr.length != 8) {
                    return 0;
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].length() > 4) {
                        return 0;
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return 0;
                    }
                }
                return 2;
            }
        }//如果是2
        return 0;
    }
}

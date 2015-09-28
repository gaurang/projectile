package com.basync.b2b.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.CurrencyResult;
import com.basync.b2b.data.UserSession;
import com.google.gson.Gson;

public class RequestUtils  {

	private static final Logger logger = Logger.getLogger(PageList.class);

	/**
	 * To return an Integer value
	 * @param req
	 *  req is HttpServletRequest object
	 * 
	 * @param param
	 *  param is submit a parameter
	 *  
	 * @param defaultValue
	 *  defaultValue is set a default value
	 *   
	 * @return
	 *  If the param is bad then return defaultValue, else return defaultValue
	 */
	public static int getParam(HttpServletRequest request, String param, int defaultValue){
		try{
			String value = request.getParameter(param);
			return Integer.parseInt(value);
		}catch(Exception e){}
		return defaultValue;
	}	
	
	/**
	 * To return a Float value from request
	 * 
	 * @param request
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static float getParam(HttpServletRequest request, String param, float defaultValue){
		try{
			String value = request.getParameter(param);
			return Float.parseFloat(value);
		}catch(Exception e){}
		return defaultValue;
	}	
	
	/**
	 * To return a Double value from request
	 * 
	 * @param request
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static Double getParam(HttpServletRequest request, String param, double defaultValue){
		try{
			String value = request.getParameter(param);
			return Double.parseDouble(value);
		}catch(Exception e){}
		return defaultValue;
	}	
	
	/**
	 * To return a String value
	 * 
	 * @param req
	 *  request is HttpServletRequest object
	 * 
	 * @param param
	 *  param is submit a parameter
	 *  
	 * @param defaultValue
	 *  defaultValue is set a default value
	 *   
	 * @return
	 *  If the param is bad then return defaultValue, else return defaultValue
	 */
	public static String getParam(HttpServletRequest request, String param, String defaultValue){
		String value = request.getParameter(param);
		if (value != null) {
			value = value.trim();
		}
		return (isEmpty(value))?defaultValue:value;
	}	


    /**
     * Get cookie specified by name
     * 
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
        	return new Cookie(name, "");
        }
        Cookie theCookie = null;
        for(int i=0; i < cookies.length; i++){
            if(cookies[i].getName().equals(name)){
                theCookie = cookies[i];
                break;
            }
        }
        if(theCookie == null){
            theCookie = new Cookie(name, "");
        }
        
        return theCookie;
    }
    
    /**
     * Add a new cookie 
     * 
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie=new Cookie(name, value);
        //-1 means it will be invalid where browser is close
        cookie.setMaxAge(-1);
        cookie.setPath("/"); 
        response.addCookie(cookie);
    }
    
    /**
     * Add a new cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param age
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int age) {
        Cookie cookie=new Cookie(name, value);
        //-1 means it will be invalid where browser is close
        cookie.setMaxAge(age*3600*24);
        cookie.setPath("/"); 
        response.addCookie(cookie);
    }
    
    /**
     * Delete the given cookie
     * 
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletResponse response, String name) {
        Cookie cookie=new Cookie(name, null);
        //0 means it will be delete
        cookie.setMaxAge(0);
        cookie.setPath("/"); 
        response.addCookie(cookie);
    }
    
    
    /**
     * Check string is empty.
     *  
     * @param str
     * @return
     */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();
		if (str.equals("")) {
			return true;
		}
		if (str.length() < 1) {
			return true;
		}
		return false;
	}
	

	
	/**
	 * Get all request information
	 * 
	 * @param request 
	 *  request is HttpServletRequest object
	 * @return 
	 *  Return a StringBuffer object of request information
	 */
	@SuppressWarnings("unchecked")
	public static String getAllRequestInfo(HttpServletRequest request) {
		
		String lineSeparator = "<br>";
		StringBuffer msgInfo = new StringBuffer();
		
		msgInfo.append(lineSeparator);
		msgInfo.append("Request URL : ").append(request.getRequestURL());
		msgInfo.append(lineSeparator);
		msgInfo.append(lineSeparator);
		msgInfo.append("Request Information :-");
		msgInfo.append(lineSeparator);
		msgInfo.append("Request method : ").append(request.getMethod());
		msgInfo.append(lineSeparator);
		msgInfo.append("Request URI : ").append(request.getRequestURI());
		msgInfo.append(lineSeparator);
		msgInfo.append("Request protocol : ").append(request.getProtocol());
		msgInfo.append(lineSeparator);
		msgInfo.append("Servlet path : " ).append(request.getServletPath());
		msgInfo.append(lineSeparator);
		msgInfo.append("Path info : ").append(request.getPathInfo());
		msgInfo.append(lineSeparator);
		msgInfo.append("Path translated : ").append(request.getPathTranslated());
		msgInfo.append(lineSeparator);
		msgInfo.append("Query string : ").append(request.getQueryString());
		msgInfo.append(lineSeparator);
		msgInfo.append("Content length : ").append(request.getContentLength());
		msgInfo.append(lineSeparator);
		msgInfo.append("Content type : ").append(request.getContentType());
		msgInfo.append(lineSeparator);
		msgInfo.append("Server name : ").append(request.getServerName());
		msgInfo.append(lineSeparator);
		msgInfo.append("Server port : ").append(request.getServerPort());
		msgInfo.append(lineSeparator);
		msgInfo.append("Remote user : ").append(request.getRemoteUser());
		msgInfo.append(lineSeparator);
		msgInfo.append("Remote address : ").append(request.getRemoteAddr());
		msgInfo.append(lineSeparator);
		msgInfo.append("Remote host : ").append(request.getRemoteHost());
		msgInfo.append(lineSeparator);
		msgInfo.append("Remote port : ").append(request.getRemotePort());
		msgInfo.append(lineSeparator);
		msgInfo.append("Authorization scheme : ").append(request.getAuthType());
		msgInfo.append(lineSeparator);
		msgInfo.append("Context path : ").append(request.getContextPath());
		msgInfo.append(lineSeparator);
		msgInfo.append("Local address : ").append(request.getLocalAddr());
		msgInfo.append(lineSeparator);
		msgInfo.append("Local name : ").append(request.getLocalName());
		msgInfo.append(lineSeparator);
		msgInfo.append("Local port : ").append(request.getLocalPort());
		msgInfo.append(lineSeparator);
		msgInfo.append("Requested session id : ").append(request.getRequestedSessionId());
		msgInfo.append(lineSeparator);
		msgInfo.append("Scheme : ").append(request.getScheme());
		msgInfo.append(lineSeparator);
		
		//Write the HTTP request headers.
		msgInfo.append(lineSeparator);
		msgInfo.append("Request Headers :-");
		Enumeration e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			msgInfo.append(lineSeparator);
		    msgInfo.append(name).append(" : ").append(request.getHeader(name));
		}
		msgInfo.append(lineSeparator);
		
		//Write the cookies
		msgInfo.append(lineSeparator);
		msgInfo.append("Cookies :-");
		msgInfo.append(lineSeparator);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	    	for (int i = 0; i < cookies.length; i++) {
	    		msgInfo.append(cookies[i].getName()).append("=[").append(cookies[i].getValue()).append("]");
	    		msgInfo.append(lineSeparator);
			}
		}
		
		//Write request parameters to log.
		StringBuffer reqParaBuffer = new StringBuffer();
		reqParaBuffer.append(lineSeparator);
		reqParaBuffer.append("Request Paramaetrs :-");
		reqParaBuffer.append(lineSeparator);
		Enumeration parameterNamesEnum =  request.getParameterNames();
		while (parameterNamesEnum.hasMoreElements()) {
			String paraName = (String) parameterNamesEnum.nextElement();
			reqParaBuffer.append(paraName).append("=[").append(request.getParameter(paraName)).append("]");
			reqParaBuffer.append(lineSeparator);	
		}
		
		logger.info(msgInfo.toString() + reqParaBuffer.toString());

		return msgInfo.toString();
	}
	/**
	 * Get user session object
	 * 
	 * @param request
	 *  request is HttpServletRequest object
	 *  
	 * @return
	 *  Return a UserSession object of request.
	 */
	public static UserSession getUserSession(HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute(Constants.USER_SESSION);
		if (userSession == null) {
			userSession = new UserSession();
			
			userSession.setUserId(-1);
			userSession.setUserName("anonymous");

            request.getSession().setAttribute(Constants.USER_SESSION, userSession);
		} else {
			return userSession;
		}
		return userSession;
	}


	
	public static String getExRateJson(String from, String to) throws Exception{
		String urlStr = "http://www.google.com/ig/calculator?hl=en&q=";
        String charset = "UTF-8";
        URL url = new URL(urlStr + from + "%3D%3F" + to);
        Reader reader = new InputStreamReader(url.openStream(), charset);
        logger.debug(urlStr + from + "%3D%3F" + to);
        CurrencyResult result = new Gson().fromJson(reader, CurrencyResult.class);
			 BufferedReader reader1 =  new BufferedReader(reader);
		 // Get the value without the term currency.
		        logger.debug(reader1.toString());

		String amount = result.getRhs().split("\\s+")[0];
		return amount;
	}
	

	public static String replace(String str, String pattern, String replace) {
	    int s = 0;
	    int e = 0;
	    StringBuffer result = new StringBuffer();

	    while ((e = str.indexOf(pattern, s)) >= 0) {
	        result.append(str.substring(s, e));
	        result.append(replace);
	        s = e+pattern.length();
	    }
	    result.append(str.substring(s));
	    return result.toString();
	}

}

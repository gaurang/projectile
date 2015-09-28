package com.basync.b2b.util;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * Utility class to peform common String manipulation algorithms.
 */
public class StringUtils {

	private static final Logger logger = Logger.getLogger(StringUtils.class);
	/**
	 * Check if string is empty
	 * 
	 * @param str 
	 *  Check str value
	 *  
	 * @return
	 *  If the str is empty return true, else return false
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
	 * Generate random string for  registration
	 * @return
	 */
	public static String  randomString(){
		
		char[] cc = new char[10];
	      for (int i = 0; i<cc.length; i++)
	      {
	           while(cc[i]<'A' || cc[i]>'z')
	                cc[i]=(char)(Math.random()*(int)'z');
	  
	    }//end for
	    StringBuilder sbuilder = new StringBuilder();
	    
	    for (int i = 0; i<cc.length; i++)
	        sbuilder.append(cc[i]);
	        
	         return sbuilder.toString();
	}

	
	/**
	 * Generate random string for reset password
	 * 
	 * @param length  the length of generated string.
	 * @return
	 */
	public static String  randomString(int length){
		
		if(length<1) length=1;
		char[] cc = new char[length];
	      for (int i = 0; i<cc.length; i++)
	      {
	           while(cc[i]<'a' || cc[i]>'z')
	                cc[i]=(char)(Math.random()*(int)'z');
	  
	    }//end for
	    StringBuilder sbuilder = new StringBuilder();
	    
	    for (int i = 0; i<cc.length; i++)
	        sbuilder.append(cc[i]);
	        
	         return sbuilder.toString();
	}
	/**
	 * Numbering to increase sequentially for each new merchant/affiliate invoice
	 * @param id
	 * @return
	 */
	public static String genInvoiceNumber(Integer id){
		StringBuilder sb = new StringBuilder();
		int length = Integer.toString(id).length();
		
		if(length>=10){
			return Integer.toString(id);
		}
		for(int i=0; i<10-length; i++){
			sb.append("0");
		}
		sb.append(Integer.toString(id));
		return sb.toString();
	} 
	
	/**
	 * Calculate two number(double) multiply
	 *  
	 * @param a 
	 * @param b
	 * @return
	 */
	public static Double multiply(double a, double b) {
		
		if(a == 0.00 || b == 0.00) {
			return 0.00;
		}
		return a*b;
	}
	

	/**
	 * Calculate time interval for report
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	 public static long calculateDate(Date startDate, Date endDate , int type){
			 
			 
			 Calendar c = Calendar.getInstance(); 
			 c.setTime(startDate); 
			 c.set(Calendar.HOUR_OF_DAY, 0); 
			 c.set(Calendar.MINUTE, 0); 
			 c.set(Calendar.SECOND, 0); 
			 c.set(Calendar.MILLISECOND, 0); 
			 long l1 = c.getTimeInMillis(); 
	
			 c.setTime(endDate); 
			 c.set(Calendar.HOUR_OF_DAY, 0); 
			 c.set(Calendar.MINUTE, 0); 
			 c.set(Calendar.SECOND, 0); 
			 c.set(Calendar.MILLISECOND, 0); 
			 long l2 = c.getTimeInMillis(); 
	
			 int temp = 1000 * 60 * 60 * 24; 
			 if(type == Calendar.DATE){
			 
			 }else if(type == Calendar.MONTH){
				temp =  temp * 30 ;
			 }else {
				temp = temp * 365; 
			 }
			 
			 return (l2 - l1) / temp; 
			 
		 }
	 
	 
		/**
		 * For devleoper check sql!
		 * @param sql
		 * @param params
		 */
		
	 public static void recordErrorSql(String sql, Object[] params){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("VIEW SQL START: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("VIEW SQL:\t"+sql);
			
			if(sql==null || params==null){
				return ;
			}
			String [] p = sql.split("[?]");
			
			for(int i=0; i<params.length; i++)
			{
				if(params[i] instanceof Date) {
					System.out.println("VIEW SQL PARAMS:\tparams["+i+"] : ("+format.format(params[i])+")\t");
				}else {
					System.out.println("VIEW SQL PARAMS:\tparams["+i+"] : ("+params[i]+")\t");
				}
			}
			

			StringBuilder sb = new StringBuilder();
		
			
			if(p.length == params.length+1){
				for(int i=0; i<p.length-1; i++){
					
					sb.append(p[i]);
					if(params[i] instanceof Date) {
						sb.append(" '"+format.format(params[i])+"'");
					}else {
						sb.append(" '"+params[i]+"'");
					}
				}
				sb.append(p[p.length-1]);
			}else if(p.length == params.length){
				for(int i=0; i<p.length; i++){
					
					sb.append(p[i]);
					if(params[i] instanceof Date) {
						sb.append(" '"+format.format(params[i])+"'");
					}else {
						sb.append(" '"+params[i]+"'");
					}
				}
				
			}else {
				System.out.println("VIEW SQL : MAYBE A BAD SQL\t");
				
			}
			
			System.out.println("VIEW SQL WITH PARAMS:\t"+sb.toString()+"\t");
		
			System.out.println("VIEW SQL END: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	 
	/**
     * Split a string into an array of strings based
     * on a a seperator.
     *
     * @param input     what to split
     * @param splitChar what to split on
     * @return the array of strings
     */
    public static String[] splitToArray(String input, String splitChar) {
        /*StringTokenizer tokens = new StringTokenizer(input, splitChar);
        List<String> strList = new ArrayList<String>();
        while (tokens.hasMoreTokens()) {
            strList.add(tokens.nextToken());
        }
        return (String[]) strList.toArray(new String[strList.size()]);*/
        
        return input.split(splitChar);
    }
    
    /**
     * Split a string into a list of strings based
     * on a a seperator.
     *
     * @param input     what to split
     * @param splitChar what to split on
     * @return the list of strings
     */
    public static List<String> splitToList(String input, String splitChar) {
        /*StringTokenizer tokens = new StringTokenizer(input, splitChar);
        List<String> strList = new ArrayList<String>();
        while (tokens.hasMoreTokens()) {
            strList.add(tokens.nextToken());
        }
        return strList;*/
        
        return Arrays.asList(splitToArray(input, splitChar));
    }
    
    
    /**
     * Concatenates an array of string using a seperator.
     *
     * @param strings the strings to concatenate
     * @param separator the seperator between two strings
     * @return the concatened strings
     */
    public static String toString(String[] strings, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(strings[i]);
        }
        return sb.toString();
    }
    
    /**
     * Concatenates an array of string using a seperator.
     *
     * @param strings the strings to concatenate
     * @param separator the seperator between two strings
     * @return the concatened strings
     */
    public static String toString(List<Integer> objList, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < objList.size(); i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(objList.get(i));
        }
        return sb.toString();
    }
    
    public static String plainTextToHTML(String text){
    	if(text!=null)
    		return text.replaceAll(System.getProperty("line.separator"), "<br/>");
    	return text;
    }
    
    public static boolean isEmail(String email){
    	Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    	Matcher m = p.matcher(email);
    	return m.matches();
    }
    
    public static String removeDuplicateSpace(String string){
    	Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(string);
        boolean check = matcher.find();
        String str = matcher.replaceAll(" ");
        return str;
    }
    
    public static String formatStringForReport(String string){
    	string.replaceAll("\t", " ").replaceAll(System.getProperty("line.separator"), " ");
    	return removeDuplicateSpace(string);
    }
    
    public static String formatToDecimal(Float value){
    	DecimalFormat   df   =   new   DecimalFormat("0.00");   
    	return df.format(value);
    }
    public static String formatToDecimal(Double value){
    	DecimalFormat   df   =   new   DecimalFormat("0.00");   
    	return df.format(value);
    }
    public static String[] splitSpecialChar(String input){
    	//THIS ALL 3 class + DB change in mysql at shawn
    	 input = input.replaceAll("x","-");
    	 Pattern p = Pattern.compile("[^\\d\\w\\.]+");
	        // Split input with the pattern
	        String[] result = 
	                 p.split(input);
	       logger.debug(input +"  and   "+result.length);
	    return result;
    }
    public static boolean isContainsSpecialChar(String input){
    	
   	 Pattern p = Pattern.compile("[^\\d\\w]+");
	        // Split input with the pattern
	 Matcher m = p.matcher(input);
	 boolean result =m.find();                
   	
   	return result;
   }
    
    public static String getNormaliseString(String s ) {
    	//CHECK if it is normalised    	
    	//String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
    	if(s != null) {
    		String resultString = s.replaceAll("\\uFFB0", "");
    		return resultString;
    	}else {
    		return s;
    	}
    	
	}

}
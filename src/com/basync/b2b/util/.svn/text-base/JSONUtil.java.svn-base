package com.basync.b2b.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.basync.b2b.crm.data.CurrencyResult;
import com.basync.b2b.data.JqFilterData;
import com.google.gson.Gson;

public class JSONUtil {

	private static final Logger logger = Logger.getLogger(JSONUtil.class);

	public static String convertToJSON(Object obj){
		String json = null;
		Gson gson = new Gson();
		json=(gson.toJson(obj));
		logger.debug(json);
		return json;
	}
	
	
	
	public static HashMap<String, String> queryOptions = new HashMap<String, String>();
	
	public static void setQueryOptions(){
		queryOptions.put("eq",  " = ");
		queryOptions.put("ne"," <> ");
		queryOptions.put("lt"," < ");
		queryOptions.put("le"," <= ");
		queryOptions.put("gt"," > ");
		queryOptions.put("ge"," >= ");
		queryOptions.put("bw"," LIKE ");
		queryOptions.put( "bn"," NOT LIKE ");
		queryOptions.put( "in"," IN ");
		queryOptions.put( "ni"," NOT IN ");
		queryOptions.put( "ew"," LIKE ");
		queryOptions.put( "en"," NOT LIKE ");
		queryOptions.put( "cn"," LIKE " );
		queryOptions.put( "nc"," NOT LIKE ") ;
	}
	
	
	public static String getQueryString(String jsonData){
		Gson gson = new Gson();
		JqFilterData jqFilterData = gson.fromJson(jsonData, JqFilterData.class);
		
		String queryString = "";
		List<HashMap<String, String>> rules=jqFilterData.getRules();
		for (int i = 0; i < rules.size(); i++) {
			queryString += " "+jqFilterData.getGroupOp()+" ";
			if(rules.get(i).containsKey("op") && rules.get(i).containsKey("data")){
				queryString += toSql(rules.get(i).get("field"), rules.get(i).get("op"), rules.get(i).get("data"));
			}
		}
		return queryString;
	}
	
	public static String toSql(String field,String op,String data){
		setQueryOptions();
		String partQry =" "+field+" "+queryOptions.get(op)+" ";
		
		if(op.equals("bw")||op.equals("bn")){
			partQry += "'"+data+"%'";
		}else if (op.equals("ew")||op.equals("en")){
			partQry +=  "'%"+data+"'";
		}else if (op.equals("cn")||op.equals("nc")){
			partQry += "'%"+data+"%'";
		}else{
			partQry += "'"+data+"'";
		}
		return partQry;
	}

}

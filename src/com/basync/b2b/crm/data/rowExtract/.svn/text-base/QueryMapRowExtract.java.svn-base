package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class QueryMapRowExtract implements ResultSetExtractor {
	
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		HashMap<String,String> resMap = new HashMap<String, String>();
		
		while(rs.next()){
			resMap.put(rs.getString(1), rs.getString(2));
		}
		return resMap;
}

}

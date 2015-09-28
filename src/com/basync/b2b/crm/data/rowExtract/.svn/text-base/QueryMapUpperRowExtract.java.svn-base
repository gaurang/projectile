/**  
* CustomerInfo.java - a simple class retrieve the UpperActivity for User.  
* @author  Arvind Kushwaha
* @version 1.0 
* @see GenericServiceImpl and CrmWebUser for details
*/ 
package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class QueryMapUpperRowExtract implements ResultSetExtractor{

	public Object extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		HashMap<String,String> resMap = new HashMap<String, String>();

		while(rs.next()){
			resMap.put(rs.getString(1)+"_"+rs.getString(2), rs.getString(3));
		}
		return resMap;
		}

}

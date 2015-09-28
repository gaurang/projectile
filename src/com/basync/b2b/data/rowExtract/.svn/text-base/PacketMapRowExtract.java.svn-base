package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class PacketMapRowExtract implements RowMapper{
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		Map<String,Object> pktMap = new HashMap<String, Object>();
		
		for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
			pktMap.put(rs.getMetaData().getColumnName(j), rs.getString(j));
		}
		return pktMap;
	}

}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PrpData;

public class PrpListRowMapper implements RowMapper{

		
	public Object mapRow(ResultSet rs, int len) throws SQLException {
		PrpData spd  = new PrpData();
		
		spd.setId(rs.getInt("ID"));
		spd.setPrp(rs.getString("prp"));
		spd.setDataType(rs.getInt("dataType"));
		spd.setPrpDesc(rs.getString("prpDesc"));
		
		spd.setMinValue(rs.getFloat("MinValue"));
		spd.setMaxValue(rs.getFloat("MaxValue"));
		return spd;
	}

}

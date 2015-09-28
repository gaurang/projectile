package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PrpData;

public class PrpDetailExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int i) throws SQLException {
		PrpData spd  = new PrpData();
		
		spd.setPrp(rs.getString("prp"));
		spd.setPrpValue(rs.getString("prpValue"));
		spd.setPrpValueDesc(rs.getString("prpValueDesc"));
		spd.setSortId(rs.getString("SortId"));
		spd.setDataType(rs.getInt("dataType"));
		return spd;
	}

}

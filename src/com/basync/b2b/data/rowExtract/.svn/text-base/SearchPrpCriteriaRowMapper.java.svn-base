package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.SearchPrpData;

public class SearchPrpCriteriaRowMapper implements RowMapper {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		SearchPrpData spd = new SearchPrpData();
			spd.setPrp(rs.getString("prp"));
			spd.setPrpId(rs.getInt("prpId"));
			spd.setPrpFromNum(rs.getFloat("prpFromNum"));
			spd.setPrpToNum(rs.getFloat("prpToNum"));
			spd.setPrpIn(rs.getString("prpIn"));
		return spd;
	}
	

}

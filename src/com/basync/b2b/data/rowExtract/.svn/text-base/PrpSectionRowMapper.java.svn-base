package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PrpData;

public class PrpSectionRowMapper implements RowMapper {


	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		PrpData pd = new PrpData();
		pd.setId(rs.getInt("ID"));
		pd.setPrp(rs.getString("prp"));
		//pd.setDataType(rs.getInt("dataType"));
		pd.setWebhdr(rs.getString("webhdr"));
		pd.setWebDesc(rs.getString("webDesc"));
		pd.setWidth(rs.getInt("width"));
		return pd;
	}
}

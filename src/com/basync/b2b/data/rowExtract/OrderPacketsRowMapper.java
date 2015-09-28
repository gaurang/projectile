package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;

public class OrderPacketsRowMapper implements RowMapper{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PacketDetails pd = new PacketDetails();
		pd.setOrderId(rs.getInt("id"));
		pd.setOrderDate(rs.getString("orderDate"));
		pd.setStatus( rs.getInt("status"));
		
		pd.setPktId(rs.getInt("pktId"));
		pd.setPktCode(rs.getString("pktCode"));
		pd.setPktStatus(rs.getInt("pktStatus"));
		pd.setRate(rs.getDouble("rate"));
		
		pd.setCts(rs.getDouble("cts"));
		pd.setSh(rs.getString("sh"));
		pd.setPu(rs.getString("pu"));
		pd.setC(rs.getString("c"));
		pd.setCt(rs.getString("ct"));
		
		return pd;
	}

}

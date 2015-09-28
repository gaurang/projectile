package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;

public class PacketDetailsRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		PacketDetails pd = new PacketDetails();
		
		pd.setPktCode(rs.getString("pktCode"));
		pd.setCts(rs.getDouble("cts"));
		pd.setSh(rs.getString("sh"));
		pd.setPu(rs.getString("pu"));
		pd.setC(rs.getString("c"));
		pd.setCt(rs.getString("ct"));
		pd.setLab(rs.getString("lab"));
		pd.setFnc(rs.getString("fnc"));
		pd.setFnci(rs.getString("fnci"));
		pd.setFnco(rs.getString("fnco"));
		pd.setRemark(rs.getString("remark"));
		pd.setComments(rs.getString("comment"));
		
		
		return pd;
	}
}

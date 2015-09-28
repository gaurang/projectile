package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.basync.b2b.data.PacketDetails;

public class PacketLabReturnRowExtract implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		PacketDetails pd = new PacketDetails();
		
		pd.setOrderId(rs.getInt("orderId"));
		pd.setPktId(rs.getInt("id"));
		pd.setPktCode(rs.getString("pktcode"));
		pd.setSh(rs.getString("sh"));
		pd.setCts(rs.getDouble("cts"));
		pd.setC(rs.getString("c"));
		pd.setPu(rs.getString("PU"));
		pd.setLab(rs.getString("lab"));
		return pd;
	}

}

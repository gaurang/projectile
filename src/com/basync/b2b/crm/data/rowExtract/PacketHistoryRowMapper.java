package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PacketHistory;

public class PacketHistoryRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PacketHistory ph = new PacketHistory();
		ph.setPktid(rs.getInt("pktId"));
		ph.setPktCode(rs.getString("pktCode"));
		ph.setOldPrice(rs.getDouble("oldPrice"));
		ph.setNewPrice(rs.getDouble("newPrice"));
		ph.setNewRap(rs.getDouble("rapPrice"));
		ph.setUpdateBy(rs.getString("userName"));
		ph.setUpdateDate(rs.getString("updateDate"));
		
		return ph;
	
	}
}

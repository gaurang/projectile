package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.GlRepData;
import com.basync.b2b.crm.data.PartyMasterData;

public class PartyGLRowMapper implements RowMapper {
	
	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		GlRepData gl = new GlRepData();
		gl.setDate(rs.getString("transDate"));
		gl.setDueDate(rs.getString("dueDate"));
		gl.setBalance(rs.getDouble("amount"));
		gl.setType(rs.getString("type"));
		gl.setStatus(rs.getInt("status"));
		return gl;
	}

}

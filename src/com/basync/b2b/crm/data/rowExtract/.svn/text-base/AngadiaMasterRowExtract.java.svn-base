package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.AngadiaMaster;
import com.basync.b2b.data.QueryDescription;

public class AngadiaMasterRowExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		AngadiaMaster a = new AngadiaMaster();

		a.setId(rs.getLong("id"));
		a.setAngadiaCoName(rs.getString("angadiaCoName"));
		a.setCode(rs.getString("code"));
		a.setOpBalance(rs.getDouble("opBalance"));
		a.setCurrBalance(rs.getDouble("currBalance"));
		a.setAccCode(rs.getString("accCode"));
		a.setCurrCode(rs.getString("currCode"));
		return a; 

	}
}
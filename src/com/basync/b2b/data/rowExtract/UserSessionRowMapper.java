package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.UserSession;

public class UserSessionRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int i) throws SQLException {

		UserSession us= new UserSession();
		us.setUserId(rs.getInt("userId"));
		us.setUserName(rs.getString("userName"));
		us.setEmail(rs.getString("email"));
		us.setCompnayName(rs.getString("companyName"));
		us.setRoleId(rs.getInt("roleId"));
		us.setTerm(rs.getDouble("factor"));
		us.setPartyAccId(rs.getInt("partyAccId"));
		us.setStatus(rs.getInt("status"));
		
		//us.setTermId(rs.getInt("termId"));
		return us;
	}

}

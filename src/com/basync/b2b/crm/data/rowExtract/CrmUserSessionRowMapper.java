package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.UserSession;

public class CrmUserSessionRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int i) throws SQLException {

		UserSession us= new UserSession();
		us.setUserId(rs.getInt("userId"));
		us.setUserName(rs.getString("userName"));
		//us.setEmail(rs.getString("email"));
		us.setRoleId(rs.getInt("roleId"));
		us.setRoleName(rs.getString("roleName"));
		us.setPartyAccId(rs.getInt("partyAccId"));
		
		us.setCurrency(rs.getString("currency"));
		//us.setTermId(rs.getInt("termId"));
		return us;
	}

}

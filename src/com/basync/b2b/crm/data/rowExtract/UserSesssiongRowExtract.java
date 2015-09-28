package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.data.UserSession;

public class UserSesssiongRowExtract implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
	UserSession us = new UserSession();
	us.setUserId(rs.getInt("userId"));
	us.setUserName(rs.getString("userName"));
	us.setRoleName(rs.getString("roleName"));
	us.setRoleId(rs.getInt("roleId"));
	us.setPartyAccId(rs.getInt("partyAccId"));
	//us.setTermId(rs.getInt("termId"));
	return us;
	}
}

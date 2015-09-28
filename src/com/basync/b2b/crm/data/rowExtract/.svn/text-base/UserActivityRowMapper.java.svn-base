package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.UserActivity;

public class UserActivityRowMapper  implements RowMapper{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		UserActivity ua = new UserActivity();
		
		ua.setActivityId(rs.getInt("activityId"));
		ua.setParentActivityId(rs.getInt("ParentActivityId"));
		ua.setParentActivityName(rs.getString("ParentActivityName"));
		ua.setActivityName(rs.getString("activityName"));
		
		ua.setRoleId(rs.getInt("roleId"));
		ua.setRoleName(rs.getString("activityName"));
		
		ua.setAccessFlag(rs.getInt("accessFlag"));
		return ua;
	}

}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dataobjects.RegistrationDO;

public class RegistrationRowMapper implements RowMapper {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		RegistrationDO rd= new RegistrationDO();
		
		rd.setUserId(rs.getString("userId"));
		rd.setCompanyName(rs.getString("companyName"));
		rd.setLoginName(rs.getString("userName")); // on for login name
		rd.setCEmail(rs.getString("cEmail"));
		rd.setCName(rs.getString("cName"));
		rd.setCMobile(rs.getString("cMobile"));
		rd.setTelphone1(rs.getString("telphone1")==null?"":rs.getString("telphone1"));
		rd.setCountry(rs.getString("country"));
		rd.setTermCode(rs.getString("termCode"));
		
		rd.setId(rs.getLong("id"));
		rd.setTermId(rs.getString("termId"));
		rd.setStatus(rs.getString("status")!=null?rs.getString("status"):"1");
		return rd;
	}

}

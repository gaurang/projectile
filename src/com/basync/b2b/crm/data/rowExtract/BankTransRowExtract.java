package com.basync.b2b.crm.data.rowExtract;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.GlRepData;

public class BankTransRowExtract  implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		GlRepData gl = new GlRepData();
		gl.setType(rs.getString("trans_type"));
		gl.setCompanyName(rs.getString("companyName"));
		gl.setRef(rs.getInt("ref"));
		gl.setDate(rs.getString("trans_date"));
		gl.setDebit(rs.getDouble("Debit"));
		gl.setCredit(rs.getDouble("Credit"));
		gl.setBankAccountName(rs.getString("bankAccountName"));
		//gl.setBalance(rs.getDouble("balance"));
		
		return gl;
	}

}

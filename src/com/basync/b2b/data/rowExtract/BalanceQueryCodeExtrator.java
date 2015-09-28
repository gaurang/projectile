package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.QueryCodeDescription;

public class BalanceQueryCodeExtrator implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		QueryCodeDescription q = new QueryCodeDescription();
		q.setAccountCode(rs.getString("accountCode"));
		q.setDescription(rs.getString("description"));
		q.setAccountName(rs.getString("accountName"));
		q.setExRate(rs.getBigDecimal("exRate"));
		q.setId(rs.getString("id"));
		return q; 
	}
}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dao.CustomerInfo;

public class AccountReceivableExtractor implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CustomerInfo q = new CustomerInfo();
		q.setId(rs.getInt("id"));
		q.setCustName(rs.getString("companyName"));
		q.setAmount(rs.getBigDecimal("amount"));
		q.setExRate(rs.getBigDecimal("exRate"));
		return q;
	}
}

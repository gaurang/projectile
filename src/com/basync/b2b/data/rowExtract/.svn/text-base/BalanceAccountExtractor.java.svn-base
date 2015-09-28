package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dao.CustomerInfo;

public class BalanceAccountExtractor implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CustomerInfo q = new CustomerInfo();
		q.setAccountCode(rs.getString("accountCode"));
		q.setBranchCode(rs.getString("bankAccountName"));
		q.setBankName(rs.getString("bankName"));
		q.setBranchName(rs.getString("branchName"));
		q.setAmount(rs.getBigDecimal("amount"));
		q.setComment(rs.getString("description"));
		q.setTrans_date(rs.getString("transDate"));
		q.setExRate(rs.getBigDecimal("exRate"));
		return q;
	}
}

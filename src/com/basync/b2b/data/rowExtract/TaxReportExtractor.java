package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dao.CustomerInfo;

public class TaxReportExtractor implements RowMapper{
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CustomerInfo q = new CustomerInfo();
		q.setRef(rs.getString("ref"));
		q.setTrans_type(rs.getString("trans_type"));
		q.setTrans_date(rs.getString("trans_date"));
		q.setAmount(rs.getBigDecimal("amount"));
		q.setComment(rs.getString("comment"));
		q.setCurrency(rs.getString("currency"));
		q.setCustName(rs.getString("companyName"));
		q.setAccType(rs.getString("accName"));
		q.setExRate(rs.getBigDecimal("exRate"));
		q.setId(rs.getInt("id"));
		return q;
	}

}

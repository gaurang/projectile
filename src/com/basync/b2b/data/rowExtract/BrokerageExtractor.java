package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dao.CustomerInfo;

public class BrokerageExtractor implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CustomerInfo q = new CustomerInfo();
		q.setDate(rs.getString("invDate"));
		q.setDueDate(rs.getString("invDueDate"));
		q.setAmount(rs.getBigDecimal("amount"));
		q.setStatus(rs.getString("invStatus"));
		q.setCustName(rs.getString("brokerageName"));
		q.setBrokPer(rs.getBigDecimal("brokrage"));
		q.setExRate(rs.getBigDecimal("exRate"));
		q.setId(rs.getInt("id"));
		return q;
	}
}

package com.basync.b2b.data.rowExtract;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.dao.CustomerInfo;

public class SupplierBalanceExtractor implements RowMapper{

public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CustomerInfo q = new CustomerInfo();
		BigDecimal value =rs.getBigDecimal("credit");
		if(rs.getString("trans_type").equals("PURCHASE")){
			q.setDebit(value);
			q.setCredit(new BigDecimal(0));
		}
		else{
			q.setCredit(value);
			q.setDebit(new BigDecimal(0));
		}
		q.setCustName(rs.getString("companyName"));
		q.setTrans_date(rs.getString("trans_date"));
		q.setRef(rs.getString("ref"));
		q.setId(rs.getInt("id"));
		q.setTrans_type(rs.getString("trans_type"));
		q.setComment(rs.getString("comment"));
		q.setExRate(rs.getBigDecimal("exRate"));
		return q; 

	}
}

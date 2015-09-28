package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PurchaseMaster;

public class GoodCostDataExtractor implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PurchaseMaster pm = new PurchaseMaster();
		pm.setAmount(rs.getDouble("amount"));
		pm.setExpenses(rs.getDouble("expenses"));
		return pm;
	}
}

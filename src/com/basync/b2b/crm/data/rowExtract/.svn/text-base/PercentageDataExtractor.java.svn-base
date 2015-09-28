package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.ProfitLossData;

public class PercentageDataExtractor implements RowMapper {
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ProfitLossData pl = new ProfitLossData();
		pl.setCompanyName(rs.getString("companyName"));
		pl.setPurchaseDate(rs.getString("purchaseDate"));
		pl.setDueDate(rs.getString("dueDate"));
		pl.setAmount(rs.getDouble("amount"));
		pl.setExpenses(rs.getDouble("expenses"));
		pl.setWeight(rs.getDouble("wt"));
		pl.setCts(rs.getDouble("cts"));
		pl.setFinalRate(rs.getDouble("finalRate"));
		pl.setPurchaseId(rs.getInt("purchaseId"));
		pl.setPktcode(rs.getString("pktcode"));
		return pl;
	}
}

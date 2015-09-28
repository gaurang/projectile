package com.basync.b2b.crm.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PurchaseDetailsRowExtract implements RowMapper {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				PurchaseDetails pd = new PurchaseDetails();
				pd.setPktCode(rs.getString("pktCode"));
				pd.setRate(rs.getDouble("rate"));
				pd.setWt(rs.getDouble("wt"));
				//pd.setTax(rs.getDouble("tax"));
				//pd.setDiscount(rs.getDouble("discount"));
				//pd.setStatus(rs.getInt("status"));
				return pd;
			}

}

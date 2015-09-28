package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PurchaseMaster;

public class PurchaseMasterRowExtract implements RowMapper {

		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			PurchaseMaster pm = new PurchaseMaster();
			pm.setId(rs.getInt("id"));
			pm.setVendorId(rs.getInt("vendorid"));
			pm.setCompanyName(rs.getString("companyName"));
			pm.setComments(rs.getString("comments"));
			pm.setBillNo(rs.getString("billNo"));
			pm.setPurchaseDate(rs.getString("purchaseDate"));
			pm.setDueDate(rs.getString("dueDate"));
			pm.setStatus(rs.getInt("status"));
			pm.setExRate(rs.getDouble("exRate"));
			pm.setCurrency(rs.getString("currency"));
			pm.setAmount(rs.getDouble("amount"));
			pm.setGlTransNo(rs.getInt("glTransNo"));
			return pm;
		}

}

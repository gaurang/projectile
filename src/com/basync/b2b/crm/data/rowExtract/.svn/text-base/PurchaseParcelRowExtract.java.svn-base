package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PurchaseParcel;



public class PurchaseParcelRowExtract implements RowMapper {
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PurchaseParcel pp = new PurchaseParcel();
		
		pp.setTotalcts(rs.getDouble("totalcts"));
		pp.setCompanyname(rs.getString("companyname"));
		
		pp.setBillno(rs.getString("billno"));
		pp.setPurchaseDate(rs.getString("purchaseDate"));	
		pp.setID(rs.getString("ID"));
		pp.setPurchaseId(rs.getInt("purchaseId"));
		return pp;
	}
}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.InvoiceMaster;

public class InvoiceMasterRowExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
	
		InvoiceMaster im =new InvoiceMaster();
		im.setId(rs.getInt("id"));
		im.setCompanyName(rs.getString("companyName"));
		im.setTotalAmount(rs.getDouble("totalAmount"));
		im.setTax(rs.getDouble("tax"));
		im.setDiscount(rs.getDouble("discount"));
		im.setInvoiceDate(rs.getString("invoiceDate"));
		im.setPartyAccId(rs.getInt("partyAccId"));
		im.setUserId(rs.getInt("userId"));
		return im;
	}
	
}

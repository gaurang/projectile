package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.InvoiceMaster;

public class AccountDataExtractor implements RowMapper{
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		InvoiceMaster im = new InvoiceMaster();
		im.setFinalAmount(rs.getDouble("finalAmount"));
		return im;
	}
}

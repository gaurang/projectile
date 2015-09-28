package com.basync.b2b.crm.data.rowExtract;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.Payment;

public class PaymentRowExtract implements RowMapper {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setPaymentDate(rs.getString("paymentDate"));
				p.setCompanyName(rs.getString("companyName")+"/"+rs.getString("branchCode"));
				p.setMode(rs.getString("mode"));
				p.setPartyAccId(rs.getInt("partyAccId"));
				p.setBank(rs.getString("bank"));
				p.setBankAccNo(rs.getString("bankAccNo"));
				p.setAmount(rs.getBigDecimal("amount"));
				p.setInvId(rs.getInt("invId"));
				//p.setExRate(rs.getDouble("exRate"));
				p.setPaymentTerm(rs.getString("paymentTerm"));
				
				return p;
			}

}

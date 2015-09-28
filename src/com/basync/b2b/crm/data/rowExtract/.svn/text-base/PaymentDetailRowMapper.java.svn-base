package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.Payment;

public class PaymentDetailRowMapper implements RowMapper {

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
		//p.setExRate(rs.getBigDecimal(("exRate"));
		p.setPaymentTerm(rs.getString("paymentTerm"));
		
		p.setInvoiceId(rs.getInt("invoiceId"));
		p.setAmt(rs.getBigDecimal("amt"));
		p.setBankName(rs.getString("bankName"));
		p.setBankAcc(rs.getString("bankAcc"));
		p.setClearStatus(rs.getInt("clearStatus"));
		p.setChequeNo(rs.getString("chequeNo"));
		if(rs.getString("chequeDate")!=null && !(rs.getString("chequeDate").matches("00-00-0000")));
		p.setChequeDate(rs.getString("chequeDate"));
		p.setPaymentDetailId(rs.getInt("paymentDetailId"));
		return p;
	}

}

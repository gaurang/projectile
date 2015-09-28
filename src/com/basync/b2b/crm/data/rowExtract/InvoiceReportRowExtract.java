package com.basync.b2b.crm.data.rowExtract;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.InvoiceMaster;


public class InvoiceReportRowExtract implements RowMapper {

		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			InvoiceMaster im = new InvoiceMaster();
			im.setId(rs.getInt("id"));
			im.setInvoiceDate(rs.getString("invoiceDate"));
			im.setDueDate(rs.getString("dueDate"));
			im.setCompanyName(rs.getString("companyName")+"/"+rs.getString("branchCode"));
			im.setBrokerName(rs.getString("brokerName"));
			
			
			im.setFinalAmount(rs.getDouble("finalAmount"));
			im.setTax(rs.getDouble("tax"));
			im.setExpences(rs.getDouble("expences"));
			im.setDiscount(rs.getDouble("discount"));
			im.setShipCharges(rs.getDouble("shipCharges"));
			
			im.setInvStatus(rs.getString("invStatus"));
			im.setInvType(rs.getString("invType"));
			im.setPaidAmt(rs.getDouble("paidAmt"));
			im.setUserId(rs.getInt("userId"));
			
			im.setConsigneePartyId(rs.getInt("consigneePartyId"));
			im.setConsigneeName(rs.getString("consigneeName"));
			im.setConsignmentCode(rs.getString("consignmentCode"));
			im.setExportStatus(rs.getString("exportStatus"));
			
			return im;
		}

	}


package com.basync.b2b.crm.data.rowExtract;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.OrderMasterData;

public class OrderMasterListRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		OrderMasterData om = new OrderMasterData();
		om.setId(rs.getInt("id"));
		om.setOrderDate(rs.getString("orderDate"));
		om.setDueDate(rs.getString("dueDate"));
		om.setExrate(rs.getDouble("exRate"));
		om.setCompanyName(rs.getString("companyName")+"/"+rs.getString("branchCode")+"/"+rs.getString("termCode"));
		om.setBrokerName(rs.getString("brokerName"));
				
		om.setContactPerson(rs.getString("contactPerson"));
		om.setComments(rs.getString("comments"));
		om.setStatusCode(rs.getString("status"));
		
		om.setOrderType(rs.getString("orderType"));
		
		om.setPartyAccId(rs.getInt("partyAccId"));
		om.setDiscount(rs.getDouble("discount"));
		om.setBrokerId(rs.getInt("brokerId"));
		om.setMemo(rs.getInt("memo"));
		om.setRefPartyId(rs.getInt("refPartyId"));
		om.setTermId(rs.getInt("termId"));
		om.setCountry(rs.getString("country"));
		om.setCity(rs.getString("city"));
		om.setBrokrage(rs.getDouble("brokrage"));
		om.setAccType(rs.getString("accType"));
		/*om.setTotalAmount(rs.getDouble("TotalAmount"));
		om.setTax(rs.getDouble("tax"));
		om.setExpences(rs.getDouble("expences"));
		om.setDiscount(rs.getDouble("discount"));
		om.setFinalAmount(rs.getDouble("finalAmount"));
		*/
		return om;
	}

}


package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.OrderMasterData;

public class OrderMasterRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		OrderMasterData om = new OrderMasterData();
		om.setStatus(rs.getInt("status"));
		om.setUserId(rs.getInt("userId"));

		om.setOrderDate(rs.getString("orderDate"));
		om.setContactPerson(rs.getString("contactPerson"));
		om.setComments(rs.getString("comments"));
		om.setOrderType(rs.getString("orderType"));
		om.setTotalAmount(rs.getDouble("TotalAmount"));
		om.setTax(rs.getDouble("tax"));
		om.setExpences(rs.getDouble("expences"));
		om.setDiscount(rs.getDouble("discount"));
		om.setCompanyName(rs.getString("companyName"));
		om.setBrokerName(rs.getString("brokerName"));
		om.setBrokrage(rs.getDouble("brokrage"));
		om.setPartyAccId(rs.getInt("partyAccId"));
		om.setBrokerId(rs.getInt("brokerId"));
		om.setTermId(rs.getInt("termId"));
		om.setExrate(rs.getDouble("exRate"));
		om.setAccType(rs.getString("accType"));
		om.setDueDate(rs.getString("dueDate"));
		return om;
	}

}

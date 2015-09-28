package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PartyAccData;

public class PartyAccountRowExtract implements RowMapper{
	
	public Object mapRow(ResultSet rs, int len) throws SQLException {
		PartyAccData pad = new PartyAccData();
		pad.setId(rs.getInt("id"));
		pad.setCompanyName(rs.getString("companyName"));
		pad.setTermId(rs.getInt("TermId"));
		pad.setTermCode(rs.getString("termCode"));
		pad.setBrokerPartyId(rs.getInt("brokerPartyId"));
		pad.setSubBrokerPartyId(rs.getInt("subBrokerPartyId"));
		pad.setSubBroker2PartyId(rs.getInt("subBroker2PartyId"));
		pad.setAccType(rs.getString("accType"));
		pad.setBranchCode(rs.getString("branchCode"));
		
		return pad;
	}
}

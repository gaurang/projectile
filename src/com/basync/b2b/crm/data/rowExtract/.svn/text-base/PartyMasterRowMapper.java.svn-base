package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PartyMasterData;

public class PartyMasterRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PartyMasterData pmd = new PartyMasterData();
		pmd.setCompanyName(rs.getString("companyName"));
		pmd.setBrokerPartyName(rs.getString("brokerPartyName"));
		pmd.setBusinessType(rs.getString("businessType"));
		pmd.setCompanyType(rs.getString("companyType"));
		pmd.setTypeOfParty(rs.getString("typeOfParty"));
		pmd.setId(rs.getInt("id"));
		pmd.setActiveFlag(rs.getInt("activeFlag"));
		
		return pmd;
	}

}

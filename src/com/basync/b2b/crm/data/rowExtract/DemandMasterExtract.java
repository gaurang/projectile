package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.DemandMaster;

public class DemandMasterExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		DemandMaster dm = new DemandMaster();
		dm.setId(rs.getLong("id"));
		dm.setPartyAccId(rs.getInt("partyAccId"));
		dm.setCompanyName(rs.getString("companyName")+"/"+rs.getString("branchCode")+"/"+rs.getString("termCode"));
		dm.setFromDate(rs.getString("fromDate"));
		dm.setTodate(rs.getString("toDate"));
		dm.setAutoMail(rs.getInt("autoMail"));
		dm.setAutoMemo(rs.getInt("autoMemo"));
		dm.setNoPrice(rs.getInt("noPriceFlag"));
		dm.setDemandName(rs.getString("demandName"));
		dm.setCreateDateTime(rs.getString("d.createDateTime"));
		
		return dm;
	}
}

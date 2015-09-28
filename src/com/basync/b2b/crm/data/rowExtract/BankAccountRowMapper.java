package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.BankAccounts;

public class BankAccountRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		BankAccounts b = new BankAccounts();
		b.setId(rs.getInt("id"));
		b.setAccountCode(rs.getString("accountCode"));
		b.setAccountType(rs.getInt("accountType"));
		b.setBankAccountName(rs.getString("bankAccountName"));
		b.setBankAccountNumber(rs.getString("bankAccountNumber"));
		b.setBankAddress(rs.getString("bankAddress"));
		b.setBankName(rs.getString("bankName"));
		b.setBankCurrCode(rs.getString("bankCurrCode"));
		b.setDfltCurrAct(rs.getInt("dfltCurrAct"));
		b.setEndingReconcileBalance(rs.getDouble("endingReconcileBalance"));
		b.setLastReconciledDate(rs.getString("lastReconciledDate"));
		b.setPartyAccId(rs.getInt("partyAccId"));
		b.setStatus(rs.getInt("status"));
		b.setBranchName(rs.getString("branchName"));
		
		return b;
	}

}

package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.PartyOSRepData;

public class PartyOSRowExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		PartyOSRepData po = new PartyOSRepData();
		po.setCompanyName(rs.getString("companyName"));
		po.setBranchCode(rs.getString("branchCode"));
		po.setTotalAmount(rs.getDouble("totalAmount"));
		po.setPaidAmount(rs.getDouble("paidAmount"));
		po.setOthPaidAmount(rs.getDouble("othPaidAmount"));
		po.setTotalOs(rs.getDouble("totalAmount") - rs.getDouble("paidAmount") - rs.getDouble("othPaidAmount"));
		po.setLastPaymentData(rs.getString("lastPayDate"));
		return  po;
	}
}

package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;

public class MemoHistoryReportExtract implements RowMapper{


			/* (non-Javadoc)
			 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
			 */
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				PacketDetails pd = new PacketDetails();
				
				pd.setPktId(rs.getInt("id"));
				pd.setPktCode(rs.getString("pktCode"));
				pd.setRate(rs.getDouble("rate"));
				pd.setCts(rs.getDouble("cts"));
				pd.setSh(rs.getString("sh"));
				pd.setPu(rs.getString("pu"));
				pd.setC(rs.getString("c"));
				pd.setCt(rs.getString("ct"));
				pd.setRap(rs.getDouble("rap"));
				pd.setTotalRate(rs.getDouble("totalRate"));
				pd.setStatusCode(rs.getString("statusCode"));
				pd.setBaseRate(rs.getDouble("baseRate"));
				pd.setRootPkt(rs.getString("roughPKt"));
				pd.setCompanyName(rs.getString("companyName"));
				pd.setBrokerName(rs.getString("brokerName"));
				pd.setOrderId(rs.getInt("memoNo"));
				pd.setBrokerage(rs.getDouble("brokrage"));
				pd.setDate(rs.getString("orderDate"));
				pd.setExrate(rs.getDouble("exRate"));
				pd.setSellRate(rs.getDouble("sellRate"));
				pd.setOrderDate(rs.getString("orderDate"));
				pd.setRapPrice(rs.getDouble("rapPrice"));
				pd.setLab(rs.getString("lab"));
				pd.setPartyAccId(rs.getLong("partyAccId"));
				pd.setAccType(rs.getString("accType"));
				pd.setTerm(rs.getString("term"));
				pd.setStatus(rs.getInt("status"));
				pd.setReturnDate(rs.getString("returnDateTime"));
				pd.setIssueDate(rs.getString("issueDateTime"));
				return pd;
			}



}

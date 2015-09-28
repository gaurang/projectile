package com.basync.b2b.crm.data.rowExtract;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.StockCheckPktDetals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StockCheckRepRowExtract implements RowMapper {
	public Object mapRow(ResultSet rs, int i) throws SQLException {
				StockCheckPktDetals sd = new StockCheckPktDetals();
				
				sd.setPktId(rs.getInt("id"));
				sd.setPktCode(rs.getString("pktCode"));
				sd.setRate(rs.getDouble("rate"));
				sd.setCts(rs.getDouble("cts"));
				sd.setSh(rs.getString("sh"));
				sd.setPu(rs.getString("pu"));
				sd.setC(rs.getString("c"));
				sd.setCt(rs.getString("ct"));
				sd.setRap(rs.getDouble("rap"));
				sd.setTotalRate(rs.getDouble("totalRate"));
				sd.setStatusCode(rs.getString("statusCode"));
				sd.setBaseRate(rs.getDouble("baseRate"));
				sd.setRootPkt(rs.getString("roughPKt"));
				sd.setCompanyName(rs.getString("companyName"));
				sd.setBrokerName(rs.getString("brokerName"));
				sd.setOrderId(rs.getInt("memoNo"));
				sd.setBrokerage(rs.getDouble("brokrage"));
				sd.setDate(rs.getString("orderDate"));
				sd.setExrate(rs.getDouble("exRate"));
				sd.setSellRate(rs.getDouble("sellRate"));//
				sd.setOrderDate(rs.getString("orderDate"));
				sd.setRapPrice(rs.getDouble("rapPrice"));//
				sd.setLab(rs.getString("lab"));
				sd.setPartyAccId(rs.getLong("partyAccId"));
				sd.setAccType(rs.getString("accType"));
				sd.setTerm(rs.getString("term"));
				sd.setSrap(rs.getDouble("srap"));
				sd.setStocktotalRate(rs.getDouble("stocktotalRate"));
				sd.setOdtbaseRate(rs.getDouble("odtbaseRate"));
				//pd.setIssueDate(rs.getString("issueDate"));
			
				return sd;
			}



}

	


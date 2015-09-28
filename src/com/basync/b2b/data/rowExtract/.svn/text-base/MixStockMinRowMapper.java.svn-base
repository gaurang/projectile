package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;

public class MixStockMinRowMapper implements RowMapper{
		public Object mapRow(ResultSet rs, int i) throws SQLException {
			PacketDetails pd = new PacketDetails();
			pd.setPktId(rs.getInt("id"));
			pd.setPktCode(rs.getString("pktCode"));
			pd.setCts(rs.getDouble("cts"));
			pd.setShFr(rs.getString("shFr"));
			pd.setShTo(rs.getString("shTo"));
			
			pd.setPuFr(rs.getString("puFr"));
			pd.setPuTo(rs.getString("puTo"));
			pd.setcFr(rs.getString("cFr"));
			pd.setcTo(rs.getString("cTo"));
			
			pd.setRate(rs.getDouble("rate"));
			
			pd.setpTyp(rs.getString("PTYP"));
			pd.setSieve(rs.getString("sieve"));
			pd.setParcelNum(rs.getString("parcelNum"));
			pd.setAvgCts(rs.getDouble("AVGCTS"));
			return pd;
			
		}

}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.util.CommonUtil;

public class OrderPktsRowMapper implements RowMapper{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		PacketDetails pd = new PacketDetails();
		
		pd.setPktId(rs.getInt("ID"));
		pd.setPktCode(rs.getString("pktCode"));
		pd.setRate(rs.getDouble("rate"));
		pd.setCts(rs.getDouble("cts"));
		pd.setSh(rs.getString("sh"));
		pd.setPu(rs.getString("pu"));
		pd.setC(rs.getString("c"));
		pd.setCt(rs.getString("ct"));
		pd.setLab(rs.getString("lab"));
		pd.setRap(rs.getDouble("rap"));
		pd.setPcs(rs.getDouble("pcs"));
		pd.setTotalRate(rs.getDouble("rate")*rs.getDouble("cts"));
		pd.setStatus(rs.getInt("status"));
		pd.setRapPrice(rs.getDouble("rapPrice"));
		pd.setAddDisc(rs.getDouble("addDisc"));
		pd.setRejCts(rs.getDouble("rejCts"));
		
		List<String> list = new ArrayList<String>();
		list.add(rs.getString("cts"));
		list.add(rs.getString("sh"));
		list.add(rs.getString("pu"));
		list.add(rs.getString("c"));
		list.add(rs.getString("ct"));
		
		pd.setPrpValue(list);
		
		return pd;
	}

}

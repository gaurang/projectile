package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PacketDetails;
public class PendingPktDetailsRowMapper implements RowMapper{

		public Object mapRow(ResultSet rs, int i) throws SQLException {
			PacketDetails pd = new PacketDetails();
			
			pd.setPktCode(rs.getString("pktCode"));
			pd.setCts(rs.getDouble("cts"));
			pd.setSh(rs.getString("sh"));
			pd.setPu(rs.getString("pu"));
			pd.setC(rs.getString("c"));
			pd.setCt(rs.getString("ct"));
			pd.setLab(rs.getString("lab"));
			pd.setFnc(rs.getString("fnc"));
			pd.setFnci(rs.getString("fnci"));
			pd.setFnco(rs.getString("fnco"));
			pd.setRemark(rs.getString("remark"));
			pd.setComments(rs.getString("comment"));
			pd.setRap(rs.getDouble("rap"));
			pd.setRate(rs.getDouble("rate"));
			pd.setRapPrice(rs.getDouble("rapPrice"));
			pd.setRootPkt(rs.getString("rootPkt"));
			pd.setBaseRate(rs.getDouble("baseRate"));
			pd.setGrpId(rs.getInt("grpId"));
			pd.setStatus(rs.getInt("status"));
			pd.setRapnetFlag(rs.getInt("rapnetFlag"));
			pd.setWebSiteFlag(rs.getInt("webSiteFlag"));
			pd.setIssueDate(rs.getString("issueDate"));
			
		
			pd.setCt(rs.getString("ct"));
			pd.setPo(rs.getString("po"));
			pd.setSy(rs.getString("sy"));
			pd.setFls(rs.getString("fl"));
			pd.setFlc(rs.getString("flc"));
			pd.setT(rs.getString("t"));
			pd.setD(rs.getString("d"));
			pd.setMd(rs.getString("md"));
			pd.setXd(rs.getString("xd"));
			pd.setDp(rs.getString("dp"));		
			
			pd.setLab_so(rs.getDouble("lab_so"));		
				
			return pd;
		}

}

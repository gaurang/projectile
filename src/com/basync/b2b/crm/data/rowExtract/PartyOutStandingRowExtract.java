package com.basync.b2b.crm.data.rowExtract;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.basync.b2b.dataobjects.PartyOutStandingDO;
public class PartyOutStandingRowExtract implements RowMapper{
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PartyOutStandingDO p=new PartyOutStandingDO();
		p.setReferneceNo(rs.getInt("referneceNo"));
		p.setPartyID(rs.getInt("partyId"));
		p.setFdate(rs.getDate("date"));
		p.setDueDate(rs.getDate("dueDate"));
		p.setBrokerID(rs.getInt("brokerID"));
		p.setAmmount(rs.getDouble("ammount"));
		p.setTax(rs.getDouble("tax"));
		p.setDiscount(rs.getDouble("discount"));
		p.setExpence(rs.getDouble("expence"));
		p.setFinalAmmount(rs.getDouble("finalAmmount"));
		p.setDescription(rs.getString("description"));
		return p;
	}
}


			




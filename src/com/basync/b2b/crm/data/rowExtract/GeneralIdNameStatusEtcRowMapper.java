package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.GeneralIdNameStatusEtc;

public class GeneralIdNameStatusEtcRowMapper implements RowMapper {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	private List<String> listOfFields; 
	
	/**
	 * @param listOfFields the listOfFields to set
	 */
	public void setListOfFields(List<String> listOfFields) {
		this.listOfFields = listOfFields;
	}

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		GeneralIdNameStatusEtc g = new GeneralIdNameStatusEtc();
		
			g.setId(rs.getInt("id"));
			g.setName(rs.getString("name"));
			g.setStatus(rs.getInt("status"));
			if(rs.getMetaData().getColumnCount() > 3){
				g.setTypeId(rs.getInt("typeId"));
			}
		//	g.setTypeId(rs.getInt("typeId"));
		return g;
	}
	
	

}

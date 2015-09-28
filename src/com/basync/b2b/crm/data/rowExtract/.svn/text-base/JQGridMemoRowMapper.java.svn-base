package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.util.JQGridRow;

public class JQGridMemoRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int i) throws SQLException {

		JQGridRow jqgrid= new JQGridRow();
		jqgrid.setId(rs.getInt("id"));
	//	jqgrid.setCertId(rs.getString("certId"));
	//	jqgrid.setStatus(rs.getString("status"));
		int size= rs.getMetaData().getColumnCount();
		List<String> cell = new ArrayList<String>();
		for (int a=4; a<=size ;a++){
				cell.add(rs.getString(a));
		}
		cell.add(rs.getString("status"));
		cell.add(rs.getString("statusId"));
		cell.add(rs.getString("certId"));
		jqgrid.setCell(cell);
		return jqgrid;
	}

}

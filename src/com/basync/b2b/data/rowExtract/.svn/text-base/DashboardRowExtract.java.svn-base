package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.basync.b2b.crm.data.Dashboard;
public class DashboardRowExtract implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Dashboard ds = new Dashboard();
		ds.setQuery(rs.getString("query"));
		ds.setType(rs.getString("type"));
		ds.setParams(rs.getInt("params"));
		ds.setDescription(rs.getString("description"));
		ds.setDivPosition(rs.getString("divposition"));
		return ds;
	}	
}

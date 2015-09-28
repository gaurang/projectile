package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.QueryCodeDescription;

public class QueryCodeDescriptionRowExtract implements RowMapper {

		public Object mapRow(ResultSet rs, int index) throws SQLException {

			QueryCodeDescription q = new QueryCodeDescription();
			q.setId(rs.getString("id"));
			q.setDescription(rs.getString("description"));
			return q; 
	}
}

package com.basync.b2b.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.QueryDescription;

public class QueryDescriptionExtract implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		QueryDescription q = new QueryDescription();

		q.setId(rs.getInt("id"));
		q.setDescription(rs.getString("description"));
		return q; 

	}

}
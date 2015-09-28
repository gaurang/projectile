package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.basync.b2b.crm.data.ThreeStrings;

public class ThreeStringsRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ThreeStrings t = new ThreeStrings();
			//g.setId(rs.getInt("id"));
			t.setStr1(rs.getString("str1"));
			t.setStr2(rs.getString("str2"));
			t.setStr3(rs.getString("str3"));
		return t;
	}
}

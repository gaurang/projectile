package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.FileTypes;

public class ExcelFileTypesRowMapper implements RowMapper{
	
	

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		FileTypes ft = new FileTypes();
		ft.setId(rs.getInt("id"));
		ft.setFileName(rs.getString("fileName"));
		ft.setCompanyName(rs.getString("company"));
		ft.setType(rs.getString("type"));
		ft.setProcessType(rs.getString("fileProcess"));
		ft.setLab(rs.getString("lab"));
		ft.setPktType(rs.getString("pktType"));
		return ft;
	}
	

}

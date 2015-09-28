package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.FileMap;

public class FileMapExtract implements RowMapper{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			FileMap fm = new FileMap();
			fm.setFileId(rs.getInt("fileId"));
			fm.setColumnName(rs.getString("columnName"));
			fm.setExcelColumnName(rs.getString("excelColName"));
			fm.setColIndex(rs.getInt("colIndex"));
			
			return fm;

	}

}

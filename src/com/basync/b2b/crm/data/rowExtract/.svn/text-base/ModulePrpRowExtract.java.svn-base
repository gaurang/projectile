package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.data.PrpData;

public class ModulePrpRowExtract implements RowMapper{
		
		public Object mapRow(ResultSet rs, int len) throws SQLException {
			PrpData spd  = new PrpData();
			
			spd.setId(rs.getInt("ID"));
			spd.setPrp(rs.getString("prp"));
			spd.setDataType(rs.getInt("dataType"));
			spd.setPrpDesc(rs.getString("prpDesc"));
			
			spd.setMinValue(rs.getFloat("MinValue"));
			spd.setMaxValue(rs.getFloat("MaxValue"));
			spd.setFieldDisplayType(rs.getString("fieldDisplayType"));
			return spd;
		}

}


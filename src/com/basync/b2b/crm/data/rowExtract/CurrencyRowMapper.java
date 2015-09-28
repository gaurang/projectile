package com.basync.b2b.crm.data.rowExtract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.Currency;

public class CurrencyRowMapper  implements RowMapper {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Currency c =  new Currency();
		c.setCurrAbrev(rs.getString("currAbrev"));
		c.setCurrency(rs.getString("currency"));
		c.setCurrSymbol(rs.getString("currSymbol"));
		c.setHundredsName(rs.getString("hundredsName"));
		c.setCountry(rs.getString("country"));
		c.setAutoUpdate(rs.getInt("autoUpdate"));
		c.setStatus(rs.getInt("status"));
		return c;
	}
	
	

}

package com.basync.b2b.crm.data.rowExtract;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.basync.b2b.crm.data.GeneralIdNameStatusEtc;

	public class GlAccRowMapper  implements RowMapper{
		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			GeneralIdNameStatusEtc g = new GeneralIdNameStatusEtc();
				//g.setId(rs.getInt("id"));
				g.setName(rs.getString("accName"));
				g.setStatus(rs.getInt("status"));
				g.setTypeId(rs.getInt("accGroupId"));
				g.setCode(rs.getString("code"));
			return g;
		}

}

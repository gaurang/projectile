package com.basync.b2b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.basync.b2b.dataobjects.UserControlDO;
import com.basync.b2b.util.ConnectionUtil;

public class UserDAOImpl {
	private ConnectionUtil dataSource =null;

	public void setDataSource(ConnectionUtil dataSource) {
		this.dataSource = dataSource;
	}

	public List<UserControlDO> getUsers(){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		List<UserControlDO> userList = new ArrayList<UserControlDO>();
		try {
			pst = conn.prepareStatement("Select u.username username,r.cname companyName,r.cemail cEmail,r.phoneno phoneNo,r.cMobile cMobile,r.country country,u.status status"
							+ "	From registration r,tb_usertable u where r.loginname = u.userName");
			rs = pst.executeQuery(); 
			while (rs.next()) {
				UserControlDO userControlDO  = new UserControlDO();
				userControlDO.setUserName(rs.getString("username"));
				userControlDO.setCompanyName(rs.getString("companyName"));
				userControlDO.setCEmail(rs.getString("cEmail"));
				userControlDO.setPhoneNo1(rs.getString("phoneNo"));
				userControlDO.setCMobile(rs.getString("cMobile"));
				userControlDO.setCountry(rs.getString("country"));
				userControlDO.setStatus(rs.getString("status"));
				userList.add(userControlDO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserControlDO getUser(String userName) {
		UserControlDO userControlDO =new UserControlDO();
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		try {
			pst = conn
					.prepareStatement("select u.userName username,r.loginname partyname,u.termId termId,u.status status from "
							+ "tb_usertable u,tb_termmaster t,registration r where u.username = r.loginname and u.termid = t.id");
			
			rs= pst.executeQuery();
			while (rs.next()) {
				userControlDO.setUserName(rs.getString("username"));
				userControlDO.setCName(rs.getString("partyname"));
				userControlDO.setTermId(rs.getString("termId"));
				userControlDO.setStatus(rs.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userControlDO;
	}

	public UserControlDO saveUser(UserControlDO userControlDO) {
		Connection conn =dataSource.getConnection();
		PreparedStatement pst =null;
		try {
			pst =conn.prepareStatement("update tb_usertable set termid ="+userControlDO.getTermId()+",status="+userControlDO.getStatus());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userControlDO;
	}
}

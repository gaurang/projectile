package com.basync.b2b.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.data.rowExtract.RegistrationRowMapper;
import com.basync.b2b.data.rowExtract.UserSessionRowMapper;
import com.basync.b2b.service.IUserService;
import com.basync.b2b.util.MD5Utils;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.StringUtils;

public class UserServiceImpl extends BaseService implements IUserService{

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#updatePwd(java.lang.String, java.lang.String)
	 */
	public int updatePwd(String oldP, String newP, int userId) throws Exception {
		// TODO Auto-generated method stub
		String sql =" update tb_usertable set password =md5(?) where password = md5(?) and Id=?";
		Object[] params = new Object[3];
		params[0] =newP;
		params[1] =oldP;
		params[2] =userId;
	
	return this.getJdbcDao().update(sql, params);
	}
	
	public int updatePwd( String newP, int userId) throws Exception {
		// TODO Auto-generated method stub
		String sql =" update tb_usertable set password =md5(?) where Id=?";
		Object[] params = new Object[2];
		params[0] =newP;
		params[1] =userId;
	
	return this.getJdbcDao().update(sql, params);
	}

	public PageList getUsers(int pageSize, int pageNo, int status, String companyName){
		
		String sql ="SELECT u.id userId, u.userName, r.companyName, r.cEmail, r.cName, " +
				"r.cMobile, r.telphone1, r.country, t.TermCode, r.Id, t.Id termId, u.status FROM tb_registration r " +
				"inner join tb_usertable u on r.id= u.registrationId " +
				"inner join tb_termmaster t on t.id = u.termid";
		
		String countSql	= " select count(*) FROM tb_registration r " +
				"inner join tb_usertable u on r.id= u.registrationId " +
				"inner join tb_termmaster t on t.id = u.termid";
		Object[] params = null;
		
		return this.getJdbcDao().getPageList(sql, countSql, pageNo, pageSize, params, new RowMapperResultSetExtractor(new RegistrationRowMapper()));
		
	}
	public List<QueryDescription>  getBuyersQD() throws Exception{
		
		String sql =" SELECT r.id id, r.companyName description  FROM tb_registration r " ;
		return this.getJdbcDao().query(sql, null, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
		
	}
	
	public int updateUser(String uName, String pwd,int status, int termId) throws Exception {
		// TODO Auto-generated method stub
		String pwdStr ="";
		if(!StringUtils.isEmpty(pwd)&& pwd.length() >= 6)
			pwdStr = ", password =md5(?) ";
		String sql =" update tb_usertable set termId = ?, status = ?  "+ pwdStr +"  where userName = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(termId);
		params.add(status);
		if(!StringUtils.isEmpty(pwd)&& pwd.length() >= 6)
			params.add(pwd);
		params.add(uName);
	
	return this.getJdbcDao().update(sql, params.toArray());
	}
	/*public int newUser(String uName, String pwd, int termId) throws Exception {
		// TODO Auto-generated method stub
		String pwdStr ="";
		if(StringUtils.isEmpty(pwd)&& pwd.length() >= 6)
			pwdStr = ", password =md5(?) ";
		String sql =" update tb_usertable set termId = ? "+ pwdStr +"  where userName = ?";
		Object[] params = new Object[2];
		params[0] =termId;
		params[1] =uName;
	
	return this.getJdbcDao().update(sql, params);
	}*/
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#checkUserData(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public int checkUserData(String login, int questionNo, String answer,
			String email) throws Exception {
		String sql="select tu.id from tb_usertable tu inner join tb_registration tr on tu.registrationid = tr.id where tu.userName = ? and tu.email = ? " +
				"and question =? and answer = ? ";
			Object[] param= new Object[4];
			param[0] = login;
			param[1] = email;
			param[2] = questionNo;
			param[3] = answer;
		int i  = this.getJdbcDao().queryForInt(sql, param);	
		return i;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#checkUserId(java.lang.String)
	 */
	public int checkUserId(String userName) throws Exception {
		String sql="select count(*) from tb_usertable tu where tu.userName = ? ";
		Object[] param= new Object[1];
		param[0] = userName;
		int i  = this.getJdbcDao().queryForInt(sql, param);	
		return i;
	}
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#getUserSession(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("finally")
	public UserSession getUserSession(String userName, String password)
			throws Exception {
		Object[] param = new Object[2];
		String sql ="select ut.id UserId, userName, email ,roleId, companyName, partyAccId, factor, status  from tb_usertable ut inner join tb_termmaster tm on ut.termId =tm.ID where userName = ?  and password = ? ";
		param[0] = userName;
		param[1] = MD5Utils.to_MD5(password);
		UserSession userSession = null;

		try {
			userSession = (UserSession) this.getJdbcSlaveDao().queryForObject(sql, param,
							new UserSessionRowMapper());
			// user session value: means of affiliateId or merchantId
			
		 sql="select max(loginDateTime) from tb_loginlog where userid = ?";
		 param= new Object[1];
		 param[0] = userSession.getUserId();
		 Timestamp date =(Timestamp) this.getJdbcDao().queryForObject(sql, param, Timestamp.class);
		 userSession.setLoginDateTime(date);
		 
		} catch (EmptyResultDataAccessException e) {
			// UserId not exist
			userSession = new UserSession();
			userSession.setUserId(new Integer(-1));
		} finally {
			return userSession;
		}

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#getUserDetails(int)
	 */
	public UserSession getUserDetails(int userId) throws Exception {
		Object[] param = new Object[1];
		String sql ="select ut.id UserId, userName, email ,roleId, companyName, partyAccId, factor,status  from tb_usertable ut inner join tb_termmaster tm on ut.termId =tm.ID where ut.id = ? ";
		param[0] = userId;
		UserSession userSession = null;

		try {
			userSession = (UserSession) this.getJdbcSlaveDao().queryForObject(sql, param,
							new UserSessionRowMapper());
			// user session value: means of affiliateId or merchantId
			
		/* sql="select max(loginDateTime) from tb_loginlog where userid = ?";
		 param= new Object[1];
		 param[0] = userSession.getUserId();
		 Timestamp date =(Timestamp) this.getJdbcDao().queryForObject(sql, param, Timestamp.class);
		 userSession.setLoginDateTime(date);*/
		 
		} catch (EmptyResultDataAccessException e) {
			// UserId not exist
			userSession = new UserSession();
			userSession.setUserId(new Integer(-1));
		} finally {
			return userSession;
		}

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IUserService#updateUser(com.basync.b2b.data.UserSession)
	 */
	public int updateUser(UserSession uSession) throws Exception {
		// TODO Auto-generated method stub
		String pwdStr ="";
		if(!StringUtils.isEmpty(uSession.getPassword())&& uSession.getPassword().length() >= 6)
			pwdStr = ", password =md5(?) ";
		String sql =" update tb_usertable set termId = ?, status = ?, partyAccid = ?  "+ pwdStr +"  where userName = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uSession.getTermId());
		params.add(uSession.getStatus());
		params.add(uSession.getPartyAccId());
		if(!StringUtils.isEmpty(uSession.getPassword())&& uSession.getPassword().length() >= 6)
			params.add(uSession.getPassword());
		params.add(uSession.getUserName());
	
	return this.getJdbcDao().update(sql, params.toArray());
	}
	
	
}

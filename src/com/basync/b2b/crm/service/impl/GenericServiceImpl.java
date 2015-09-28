package com.basync.b2b.crm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.FileTypes;
import com.basync.b2b.crm.data.UserActivity;
import com.basync.b2b.crm.data.rowExtract.CrmUserSessionRowMapper;
import com.basync.b2b.crm.data.rowExtract.ExcelFileTypesRowMapper;
import com.basync.b2b.crm.data.rowExtract.FileMapExtract;
import com.basync.b2b.crm.data.rowExtract.QueryMapRowExtract;
import com.basync.b2b.crm.data.rowExtract.QueryMapUpperRowExtract;
import com.basync.b2b.crm.data.rowExtract.UserActivityRowMapper;
import com.basync.b2b.crm.data.rowExtract.UserSesssiongRowExtract;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.service.impl.BaseService;
import com.basync.b2b.util.MD5Utils;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

public class GenericServiceImpl extends BaseService implements IGenericService{

	public GenericServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenricsService#backUpHistory(java.lang.String)
	 */
	public int backUpHistory(String tableName) throws Exception {
		String sql = "insert into "+tableName+"History select * from "+tableName+"";
		return this.getJdbcDao().update(sql, null);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenricsService#truncateTable(java.lang.String)
	 */
	public int truncateTable(String tableName) throws Exception {
		String sql = "truncate table "+tableName ;
		return this.getJdbcDao().update(sql, null);
	}
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IGenericService#getCRMUserSession(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("finally")
	public UserSession getCRMUserSession(String userName, String password)
			throws Exception {
		Object[] param = new Object[2];
		String sql ="select u.id UserId, userName, roleId, roleName, partyAccId, ifnull(pa.currency,'USD') as currency from tb_users u inner join tb_userrole ur on u.roleId = ur.id and u.userName = ?  and u.password = ? left outer join tb_partyAcc pa on pa.id=u.partyAccId ";
		param[0] = userName;
		param[1] = MD5Utils.to_MD5(password);
		UserSession userSession = null;

		try {
			userSession = (UserSession) this.getJdbcSlaveDao().queryForObject(sql, param,
							new CrmUserSessionRowMapper());
		
			//userSession.setTermId(3);
			userSession.setTerm(1d);
			// user session value: means of affiliateId or merchantId
			
		 sql="select max(loginDateTime) from tb_loginlog where userid = ?";
		 param= new Object[1];
		 param[0] = userSession.getUserId();
		 Timestamp date =(Timestamp) this.getJdbcDao().queryForObject(sql, param, Timestamp.class);
		 userSession.setLoginDateTime(date);
		 
		 HashMap<String, String> userActivityMap = new HashMap<String, String>();
		 userActivityMap = getRoleActivity(userSession.getRoleId());
		 if(userActivityMap.size() == 0){
			 userActivityMap = getUserActivity(userSession.getUserId());
		 }
		 userSession.setUserActivityMap(userActivityMap);
		 // Updated by Arvind on 28/06/2012 for getting upper User Activity throug userUpperActivityMap;
		 
		 HashMap<String, String> userUpperActivityMap = new HashMap<String, String>();
		 userUpperActivityMap = getUpperRoleActivity(userSession.getRoleId());
		 if(userUpperActivityMap.size() == 0){
			 userUpperActivityMap = getUpperUserActivity(userSession.getUserId());
		 }
		 userSession.setUserUpperActivityMap(userUpperActivityMap);
		 // End Here
		} catch (EmptyResultDataAccessException e) {
			// UserId not exist
			userSession = new UserSession();
			userSession.setUserId(new Integer(-1));
		} finally {
			return userSession;
		}

	}

	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#genUserSession(javax.servlet.http.HttpServletRequest)
	 */
	public UserSession genUserSession(HttpServletRequest request)
			throws Exception {
		UserSession userSession = RequestUtils.getUserSession(request);
		// if user session has expired, rebuild it by cookie
		if (userSession.getUserId() < 0) {
			String userId = RequestUtils.getCookie(request, "userId")
					.getValue();
			String userKey = RequestUtils.getCookie(request, "userKey")
					.getValue();

			if (userKey.equals(MD5Utils.to_MD5(userId + Constants.COOKIE_SECRET))) {
				// rebuild userSession by userId.
				logger.debug("Rebuild session , UserId="+ userId);
				userSession = genUserSession(new Integer(userId));

				request.getSession().setAttribute(Constants.USER_SESSION, userSession);

			}

		}
		return userSession;
	}

	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#genUserSession(java.lang.Integer)
	 */
	@SuppressWarnings("finally")
	public UserSession genUserSession(Integer userId) throws Exception {
		UserSession userSession = null;

		if (userId <= -1) {
			userSession = new UserSession();
			userSession.setUserId(new Integer(-1));
			userSession.setUserName(Constants.UT_ANONYMOUS);
			return userSession;
		}
		
		Object[] param = new Object[1];
		String sql ="select u.id UserId, userName, roleId, roleName, partyAccId, ifnull(pa.currency,'USD') as currency from tb_users u inner join tb_userrole ur on u.roleId = ur.id and u.id = ? left outer join tb_partyAcc pa on pa.id=u.partyAccId ";
		param[0] = userId;

		try {
			userSession = (UserSession) this.getJdbcSlaveDao().queryForObject(sql, param,
							new CrmUserSessionRowMapper());
		
			//userSession.setTermId(3);
			userSession.setTerm(1d);
			// user session value: means of affiliateId or merchantId
			
		 sql="select max(loginDateTime) from tb_loginlog where userid = ?";
		 param= new Object[1];
		 param[0] = userSession.getUserId();
		 Timestamp date =(Timestamp) this.getJdbcDao().queryForObject(sql, param, Timestamp.class);
		 userSession.setLoginDateTime(date);
		 
		 HashMap<String, String> userActivityMap = new HashMap<String, String>();
		 userActivityMap = getRoleActivity(userSession.getRoleId());
		 if(userActivityMap.size() == 0){
			 userActivityMap = getUserActivity(userSession.getUserId());
		 }
		 userSession.setUserActivityMap(userActivityMap);
		 
		 // Updated by Arvind on 28/06/2012 for getting upper User Activity throug userUpperActivityMap;
		 
		 HashMap<String, String> userUpperActivityMap = new HashMap<String, String>();
		 userUpperActivityMap = getUpperRoleActivity(userSession.getRoleId());
		 if(userUpperActivityMap.size() == 0){
			 userUpperActivityMap = getUpperUserActivity(userSession.getUserId());
		 }
		 userSession.setUserUpperActivityMap(userUpperActivityMap);
		 // End Here
		 
		} catch (EmptyResultDataAccessException e) {
			// UserId not exist
			userSession = new UserSession();
			userSession.setUserId(new Integer(-1));
		} finally {
			return userSession;
		}

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getExportFiles()
	 */
	//kri
	@SuppressWarnings("unchecked")
	public List<FileTypes> getExportFiles(String fileProcess, String pktType) throws Exception {
		//		String sql = " select id, fileName, company , type, fileProcess from tb_excelfile  order by sort ";
		String sql = " select id, fileName, company , type, lab, fileProcess, pktType from tb_excelfile  " ;
		List<Object> param = new ArrayList<Object>();	

		if(!StringUtils.isEmpty(pktType)){
			param.add(pktType);
			sql+=" where pktType = ? ";
		}
		if(!StringUtils.isEmpty(fileProcess)){
			param.add(fileProcess);
			sql+="and fileProcess = ? ";
		}
		sql+= " order by sort";
		return this.getJdbcSlaveDao().query(sql, param.toArray(), new RowMapperResultSetExtractor(new ExcelFileTypesRowMapper()));
	}

	/*\
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getExportFiles(java.lang.String)
	 */
	public List<FileTypes> getExportFiles(String fileProcess) throws Exception {
		return getExportFiles(fileProcess, "single"); 		
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getExportFileMapping(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FileMap> getExportFileMapping(Integer fileId) throws Exception {
		StringBuilder sql = new StringBuilder("select fileId, columnName, excelColName, colIndex from tb_excelmap where fileId =? ")
							.append(" union ") 
							.append(" select fileId, columnName, excelColName, colIndex from  tb_excelmap where fileId =0 and columnName not in ")
							.append(" 	(select  columnName from tb_excelmap where fileId =? and columnName is not null) order by fileid desc,colindex asc ");
		Object[] param = new Object[2];
		param[0] =fileId;
		param[1] =fileId;
		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new FileMapExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#updateExportFileMapping(java.util.List, java.lang.Integer)
	 */
	public int updateExportFileMapping(List<FileMap> fileMapList, Integer fileId) throws Exception {
		
		String sql = "";
		int z;
		Object[] params;
		if(fileId > -1){
			sql = " delete from tb_excelmap where fileId = ? ";
			params = new Object[1];
			params[0] =fileId;
			z= this.getJdbcDao().update(sql, params);
		}		
		List<Object[]> paramList = new ArrayList<Object[]>();
		sql = " insert into  tb_excelmap (fileId, columnName, excelColName, colIndex) values (?, ?, ? , ?) ";
		for (int i = 0; i < fileMapList.size(); i++) {
			FileMap fm = fileMapList.get(i);

			params = new Object[4];
			params[0] =fileId;
			params[1] =fm.getColumnName();
			params[2] =fm.getExcelColumnName();
			params[3] =fm.getColIndex();
			paramList.add(params);
		}
		int[] updateSIze = this.getJdbcDao().batchUpdate(sql, paramList);
		
		return updateSIze.length;
	}

	
	public List<FileMap> getFileMapping(String companyName, int fileId, int sort, String pktType, String fileProcess) throws Exception {
		StringBuilder sql = new StringBuilder("select fileId, columnName, excelColName, colIndex,type from ") 
		.append(" tb_excelmap e,tb_excelfile ef where e.fileId = ef.id ");
		Object[] param;
		if(!StringUtils.isEmpty(companyName)){
			param = new Object[1];
			param[0] =companyName;
			sql.append("and ef.Company = ? order by colIndex asc");
		}else if(fileId != -1){
			param = new Object[1];
			param[0] =fileId;
			sql.append("and ef.id = ? order by colIndex asc");
		}else{
			param = new Object[3];
			param[0] =sort;
			param[1] =pktType;
			param[2] =fileProcess; 
			sql.append("and ef.sort = ? and pktType = ? and FileProcess= ?  order by colIndex asc");
		}
		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new FileMapExtract()));
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getFileMapping(java.lang.String)
	 */
	public List<FileMap> getFileMapping(String companyName, int fileId, int sort) throws Exception {
		return getFileMapping(companyName,  fileId,  sort, "single","IN") ; // Changed to get the default file Id one extra parameter is added to the query.
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getUserActivity(java.lang.Integer)
	 */
	public HashMap<String, String> getUserActivity(Integer userId)
			throws Exception {
		StringBuilder sql = new StringBuilder("select a.activityName, ua.accessFlag from ") 
		 	.append(" tb_userActivity ua, tb_activityMaster a where a.id = ua.activityId and ua.userId = ? ");
		Object[] param = new Object[1];
		param[0] = userId;
		
		return (HashMap<String, String>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql.toString(), param, new QueryMapRowExtract());
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getRoleActivity(java.lang.Integer)
	 * This method is used to get the value of Upper Activity for user based on ParentActivityId, ParentActivityName and their Access based on roleId.
	 * sum(ua.accessFlag) is used to check the sum of access flag. IF sum of access flag is greater than zero means Upper Activity is Enabled.
	 */

	public HashMap<String, String> getUpperUserActivity(Integer userId)
	throws Exception {
		
		//IF sum of accessflag is greater than zero means Upper Activity is Enabled.
		StringBuilder sql = new StringBuilder("select a.ParentActivityId,a.ParentActivityName, sum(ua.accessFlag) as accessFlag from ") 
		 	.append(" tb_userActivity ua, tb_activityMaster a where a.id = ua.activityId and ua.userId = ? group by a.ParentActivityId ");
		Object[] param = new Object[1];
		param[0] = userId;
		
		return (HashMap<String, String>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql.toString(), param, new QueryMapUpperRowExtract());
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getRoleActivity(java.lang.Integer)
	 */
	public HashMap<String, String> getRoleActivity(Integer roleId)
			throws Exception {
		StringBuilder sql = new StringBuilder("select a.activityName, ua.accessFlag from ") 
	 	.append(" tb_userActivity ua, tb_activityMaster a where a.id = ua.activityId and ua.roleId = ? ");
		Object[] param = new Object[1];
		param[0] = roleId;
	
		return (HashMap<String, String>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql.toString(), param, new QueryMapRowExtract());
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getRoleActivity(java.lang.Integer)
	 * This method is used to get the value of Upper Activity for user based on ParentActivityId, ParentActivityName and their Access based on roleId.
	 * sum(ua.accessFlag) is used to check the sum of accessflag. IF sum of accessflag is greater than zero means Upper Activity is Enabled.
	 */
	public HashMap<String, String> getUpperRoleActivity(Integer roleId)
			throws Exception {
		//IF sum of accessflag is greater than zero means Upper Activity is Enabled.
		StringBuilder sql = new StringBuilder("select a.ParentActivityId,a.ParentActivityName, sum(ua.accessFlag) as accessFlag from ") 
	 	.append(" tb_userActivity ua, tb_activityMaster a where a.id = ua.activityId and ua.roleId = ?  group by a.ParentActivityId");
		Object[] param = new Object[1];
		param[0] = roleId;
	
		return (HashMap<String, String>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql.toString(), param, new QueryMapUpperRowExtract());
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#insertFileFormat(com.basync.b2b.crm.data.FileTypes)
	 */
	public int insertFileFormat(FileTypes fileType) throws Exception {
		
		String sql = " insert into tb_excelfile  (FileName, Company, Type, FileProcess, rapFormat, pktType ) value (?, ?, ?, ?, ?,?) ";
		Object [] params = new Object[6];
		params[0] =fileType.getFileName();
		params[1] =fileType.getCompanyName();
		params[2] =fileType.getType();
		params[3] =fileType.getProcessType();
		params[4] =fileType.getRapFormat();
		params[5] =fileType.getPktType();
		this.getJdbcDao().update(sql, params);
		
		sql = " select max(id) from tb_excelfile ";
		int z = this.getJdbcDao().queryForInt(sql, null);
		
		this.getJdbcDao().update("update tb_excelfile set sort = id*10 where id = "+z, null);
		return z;
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#updatePassword(java.lang.String, java.lang.String, java.lang.int)
	 */
	public int updatePassword(String oldPwd, String newPwd, Integer userId)
			throws Exception {
		
		String sql = " update tb_users set password = md5(?) where id = ? and password =  md5(?) ";
		
		Object [] params = new Object[3];
		params[0] = newPwd;
		params[1] = userId;
		params[2] = oldPwd;
		
		return this.getJdbcDao().update(sql, params);
	}

	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getUsersList()
	 */
	public List<UserSession> getUsersList() throws Exception {
		StringBuilder sql = new StringBuilder("select u.id userId, u.username, u.roleId, ur.roleName, u.activeFlag, u.partyAccId  from ") 
						.append(" tb_users u inner join tb_userrole ur on u.roleId = ur.id  ");
		
		return this.getJdbcSlaveDao().query(sql.toString(), null, new RowMapperResultSetExtractor(new UserSesssiongRowExtract()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getUserRoles()
	 */
	public List<QueryDescription> getUserRoles() throws Exception {
		String sql= "select id , roleName as description from tb_userrole ur order by id ";
		
		return this.getJdbcSlaveDao().query(sql, null, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getUserDetails(java.lang.Long)
	 */
	public UserSession getUserDetails(Long userId) throws Exception {
		Object[] param = new Object[1];
		String sql ="select u.id UserId, userName, roleId, roleName, partyAccId, ifnull(pa.currency,'USD') as currency from tb_users u inner join tb_userrole ur on u.roleId = ur.id "+
				" left outer join tb_partyAcc pa on pa.id=u.partyAccId where u.id = ?";  
		param[0] = userId;
		UserSession userSession = (UserSession) this.getJdbcSlaveDao().queryForObject(sql, param,
							new CrmUserSessionRowMapper());
		return userSession;
		}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#checkCRMUserName(java.lang.String)
	 */
	public int checkCRMUserName(String userName) throws Exception {
		String sql="select count(*) from tb_users tu where tu.userName = ? ";
		Object[] param= new Object[1];
		param[0] = userName;
		int i  = this.getJdbcDao().queryForInt(sql, param);	
		return i;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#updateCRMUser(com.basync.b2b.data.UserSession)
	 */
	public int updateCRMUser(UserSession userSession,String password,int userId) throws Exception {
		String sql=" update tb_users set userName = ?, roleId = ? , partyAccId = ?, modifiedDate = now(), modifiedBy = ? where id = ?";
		 
		List<Object> param= new ArrayList<Object>();
		param.add(userSession.getUserName());
		
    	if(!StringUtils.isEmpty(password)){
			 sql=" update tb_users set userName = ?,password = md5(?), roleId = ? , partyAccId = ? , modifiedDate = now(), modifiedBy = ? where id = ?";
			 param.add(password);
		}
		
		param.add(userSession.getRoleId());
		param.add(userSession.getPartyAccId());
		param.add(userId);
		param.add(userSession.getUserId());
		
		int i  = this.getJdbcDao().update(sql, param.toArray());	
		return i;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#insertCRMUser(com.basync.b2b.data.UserSession)
	 */
	public int insertCRMUser(UserSession userSession,String password, int userId) throws Exception {
		String sql = " insert into tb_users (username, password , roleId, activeFlag, createDate, createdBy, partyAccId) value (?, md5(?), ?, ?, now(), ?, ?)";
		 
		List<Object> param= new ArrayList<Object>();
		param.add(userSession.getUserName());
		
    	if(!StringUtils.isEmpty(password)){
			 param.add(password);
		}else{
			 param.add(userSession.getUserName());
		}
		
		param.add(userSession.getRoleId());
		param.add(1);
		param.add(userId);
		param.add(userSession.getPartyAccId());
		
		int i  = this.getJdbcDao().update(sql, param.toArray());	
		return i;
		
		}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#getRoleActivity(int)
	 */
	public List<UserActivity> getRoleActivity(int roleId) throws Exception {
		String sql = "select ua.activityId,am.ParentActivityId,am.ParentActivityName, am.activityName, ua.roleId, ur.roleName, ua.accessFlag from tb_userActivity ua " +
				"inner join tb_activityMaster am on am.id = ua.activityId " +
				"inner join tb_userrole ur on ur.id = ua.roleId where ur.id = ? ";
		
		Object[] param= new Object[1];
		param[0] = roleId;
		
		return  (List<UserActivity>) this.getJdbcDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new UserActivityRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IGenericService#updateRoleActivity(java.util.List)
	 */
	public int[] updateRoleActivity(List<UserActivity> uaList)
			throws Exception {
		
		Object[] params = null;
		List<Object[]> paramList = new ArrayList<Object[]>();
		String sql = "update tb_userActivity ua set accessFlag = ? where ua.userId = ? and ua.roleId = ? and ua.activityId = ? " ;
		for (int i = 0; i < uaList.size(); i++) {
			UserActivity ua = uaList.get(i);

			params = new Object[4];
			params[0] = ua.getAccessFlag();
			params[1] = ua.getUserId();
			params[2] = ua.getRoleId();
			params[3] = ua.getActivityId();
			paramList.add(params);
		}
		
		int[] updateSIze = this.getJdbcDao().batchUpdate(sql, paramList);
		return updateSIze;
	}

	/* (non-Javadoc)
	 * @seecom.basync.b2b.crm.service.IGenericService#getSysPref(java.lang.String)
	 */
	public String getSysPref(String sysPref) throws Exception {
		String sql = "select value from tb_sysPrefs where name = ? ";
		Object[] param = new Object[1];
		param[0] = sysPref;
		return (String) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, String.class);
	}
	
	public int insertRapNetUser(String rapUserName,String rapPassword) throws Exception{
		
		String sqlId = " select max(id+1) from tb_rapnetMaster ";
		int z = this.getJdbcDao().queryForInt(sqlId, null);
			if(z==0){
				z=1;
			}
			String sql="INSERT INTO tb_rapnetMaster (id,rapUserName,rapPassword) values (?,?, md5(?))";
			Object [] param = new Object[3];
			param[0]=z;
			param[1]=rapUserName;
			param[2]=rapPassword;
			return this.getJdbcDao().update(sql, param);
	}
	
}

package com.basync.b2b.crm.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.FileTypes;
import com.basync.b2b.crm.data.UserActivity;
import com.basync.b2b.crm.data.rowExtract.UserActivityRowMapper;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.TermMaster;

public interface IGenericService {

	/**
	 * 
	 * backup in _history tables
	 * @param tablename
	 * @return
	 * @throws Exception
	 */
	public int backUpHistory(String tablename)throws Exception;
	
	/**
	 * Truncate Table with tablename
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int truncateTable(String tableName)throws Exception;
	
	
	/**
	 * Get USer Session * moved from commonm services to avoid caching 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserSession getCRMUserSession(String userName, String password) throws Exception;

	/**
	 * To generate new user Session from cookies
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public UserSession genUserSession(HttpServletRequest request)
	throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserSession genUserSession(Integer userId)
	throws Exception;


	/**
	 * Get mapping files 
	 * @return
	 * @throws Exception
	 */
	public List<FileTypes> getExportFiles(String fileProcess, String pktType)throws Exception;//kri change to argument type
	
	public List<FileTypes> getExportFiles(String fileProcess)throws Exception;
	/**
	 * Get List of fields for file
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public List<FileMap> getExportFileMapping(Integer fileId)throws Exception;
	
	
	/**
	 * 
	 * @param fileMapList
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	
	public int  updateExportFileMapping( List<FileMap> fileMapList, Integer fileId)throws Exception;

	/**
	 * 
	 * @param companyName
	 * @param fileId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	//kri
	public List<FileMap> getFileMapping(String companyName, int fileId, int sort)throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getUserActivity(Integer userId)throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getRoleActivity(Integer roleId)throws Exception;
	
	/**
	 * 
	 * @param fileType
	 * @return
	 */
	public int insertFileFormat(FileTypes fileType) throws Exception ;
	
	/**
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(String oldPwd, String newPwd, Integer userId) throws Exception ;
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserSession> getUsersList() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getUserRoles() throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserSession getUserDetails(Long userId) throws Exception ;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public int checkCRMUserName(String userName) throws Exception ;
	
	/**
	 * 
	 * @param userSession
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public int updateCRMUser(UserSession userSession,String password, int userId) throws Exception ;
	
	/**
	 * 
	 * @param userSession
	 * @param password
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int insertCRMUser(UserSession userSession,String password, int userId) throws Exception ;
	
	/**
	 * 
	 * @param roleId
	 * @throws Exception
	 */
	public List<UserActivity> getRoleActivity(int roleId) throws Exception ;
	
	/**
	 * 
	 * @return
	 */
	public int[] updateRoleActivity(List<UserActivity> uaList) throws Exception ;
	
	/**
	 * 
	 * @param sysPref
	 * @return
	 * @throws Exception
	 */
	public String getSysPref(String sysPref)throws Exception ;
	
	public List<FileMap> getFileMapping(String companyName, int fileId, int sort, String pktType, String fileProcess) throws Exception;
	
	public int insertRapNetUser(String rapUserName,String rapPassword) throws Exception;
}

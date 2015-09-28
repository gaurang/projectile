package com.basync.b2b.service;

import java.util.List;

import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.util.PageList;

public interface IUserService {

	/**
	 * Update Old password
	 * @param oldP
	 * @param newP
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(String oldP, String newP, int userId) throws Exception;

	/**
	 * Update Password master
	 * @param newP
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updatePwd( String newP, int userId) throws Exception;
	
	/**
	 * Get list of Users
	 * @param pageSize
	 * @param pageNo
	 * @param status
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	public PageList getUsers(int pageSize, int pageNo, int status, String companyName) throws Exception;
		
	/**
	 * Get buyers data in Query Description
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getBuyersQD() throws Exception;
	

	/**
	 * Update User data
	 * @param uName
	 * @param pwd
	 * @param status
	 * @param termId
	 * @return
	 * @throws Exception
	 */
	public int updateUser(String uName, String pwd,int status, int termId) throws Exception;

	/**
	 * Check user verification for password
	 * @param login
	 * @param questionNo
	 * @param answer
	 * @return
	 * @throws Exception
	 */
	public int checkUserData(String login, int questionNo,String answer, String email)throws Exception;
 	
	/**
	 * Check userId 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int checkUserId(String user)throws Exception;

	/**
	 * Get USer Session * moved from commonm services to avoid caching 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	//	public String getPrp
	public UserSession getUserSession(String userName, String password) throws Exception;

//	public String getPrp
	public UserSession getUserDetails(int userId) throws Exception;
	
	public int updateUser(UserSession uSession) throws Exception;

}


package com.basync.b2b.service;

import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;


public interface ICommonService {
	
	/**
	 * Get property Value 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getPropertiesByName(String key) throws Exception;

 	
	/**
	 * 
	 * @throws Exception
	 */
	public int loginLog(int userId, String ipAddr )throws Exception;
	
	 
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getAllPrpList()throws Exception;
	
	
	/**
	 * 
	 * @param sectionId
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getResultPrpList(String sectionId) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<QueryCodeDescription>> getPrpLOV() throws Exception;
	
	/**
	 * 
	 * @param module
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getModulePrpList(String module, Integer flag) throws Exception ;
	
	

	/**
	 * 
	 * @param actiVeflag
	 * @param partyType
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getPartyByType(Integer actiVeflag, String partyType)throws Exception;

	/**
	 * Get List of all terms
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getTermsList() throws Exception;
	
	/**
	 * Get list of all 
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getMemoStatusList() throws Exception;
	
	/**
	 * Get list of all 
	 * @return
	 * @throws Exception
	 */
	public List<QueryCodeDescription> getMemoTypeList() throws Exception;	

}

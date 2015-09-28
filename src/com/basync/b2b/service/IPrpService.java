package com.basync.b2b.service;

import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.SearchPrpData;

public interface IPrpService {
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getSearchPrpList() throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<QueryCodeDescription>> getPrpLOV() throws Exception;

	/**
	 * 
	 * @param sectionId
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getResultPrpList(String sectionId) throws Exception;

	/**
	 * 
	 * @param prpList
	 * @param userId
	 * @param SearchType
	 * @param saveSearch
	 * @return
	 * @throws Exception
	 */
	public int insertSearchPrp(List<SearchPrpData> prpList, int userId, String SearchType, boolean saveSearch, String sDesc) throws Exception;
	
	/**
	 * 
	 * @param prpList
	 * @return
	 * @throws Exception
	 */
	public String getSearchCriteria(List<SearchPrpData> prpList) throws Exception;
	/**
	 * 
	 * @param prpList
	 * @return
	 * @throws Exception
	 */
	public String getSearchCriteriaMix(List<SearchPrpData> prpList) throws Exception;
	
	/**
	 * 
	 * @param searchId
	 * @param smartSearchId
	 * @return
	 * @throws Exception
	 */
	public Map getSearchPrpDetail(int searchId, int smartSearchId)throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getSmasrtSearch(int userId)throws Exception;
	
	/**
	 * 
	 * @param module
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public List<PrpData> getModulePrpList(String module, Integer flag) throws Exception ;
	
}

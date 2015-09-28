/**
 * 
 */
package com.basync.b2b.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.ParcelMaster;
import com.basync.b2b.crm.data.PurchaseDetails;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.PurchaseParcel;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.PriceMasterPrpDO;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.StringUtils;

/**
 * @author Gaurang
 *
 */
public interface IStockService {
	/**
	 * 
	 * @param prpLst
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getStockData(Double terms, List<PrpData> prpLst,String whereClause,int userId, int page, int pageSize, String srtIndex, String srtType,String currency, Double factor) throws Exception;
	
	/**
	 * 
	 * @param term
	 * @param param
	 * @param whereClause
	 * @return
	 * @throws Exception
	 */
	public Map getTotals(Double term,Object[] param, String whereClause) throws Exception;
	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String getPrpStr(List<PrpData> list, Double terms) throws Exception;

	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	//public String  getPrpWebHdr(List<PrpData> list) throws Exception;

	public int[] addToFavorite(List<String> list, int userID) throws Exception;

	
	/**
	 * 
	 * @param list
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public int[] removeFavorite(List<String> list, int userID) throws Exception;

	/**
	 * 
	 * @param prpLst
	 * @param userID
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getFavorites(Double term,List<PrpData> prpLst, int userID, int page, int pageSize, String srtIndex, String srtType) throws Exception;

	/**
	 * 
	 * @param prpLst
	 * @param stage
	 * @return
	 */
	public String[] getJQGridColModel(List<PrpData> prpLst , int stage);

	
	/**
	 * 
	 * @param prpLst
	 * @param currency
	 * @param factor
	 * @return
	 */
	public String[] getJQGridColModel(List<PrpData> prpLst , String currency, Double factor);
	/**
	 * 
	 * @param prpLst
	 * @param pktCode
	 * @param orderIds
	 * @param userID
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getStockByPktNoOrderId(Double term, List<PrpData> prpLst,List<String> pktCode, List<String> orderIds,  int userID, int page, int pageSize, String srtIndex, String srtType) throws Exception;

	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int insertOrder(OrderMasterData omd,Double terms) throws Exception;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int insertOrderMemo(OrderMasterData omd,Double terms, Integer status, Boolean salesMix) throws Exception;
	
	/**
	 * 
	 * @param pkts
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateStoneStatus(List<PacketDetails> pkts,int status) throws Exception;
	
	
	/**
	 * 
	 * @param status
	 * @param orderIdString
	 * @return
	 * @throws Exception
	 */
	public OrderMasterData getOrderMasterDetail(int userId, int orderId) throws Exception;
	
	/**
	 * For internal users 
	 * @param status
	 * @param orderIdString
	 * @return
	 * @throws Exception
	 */
	public OrderMasterData getOrderMasterDetail( int orderId) throws Exception;
	/**
	 * 
	 * @param status
	 * @param orderIdString
	 * @return
	 * @throws Exception
	 */

	
	public List<PacketDetails> getPktDetailsByOrderId(int status, String orderIdString, int orderPktStatus) throws Exception;

	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int upDateOrderDetails(OrderMasterData omd)throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @param status
	 * @param orderType
	 * @param fromDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList getOrderMasterData(int userId,int status, int pktStatus, String orderType, Date fromDate, String srtIndex, String srtType, int pageNo,int pageSize) throws Exception;

	/**
	 * 
	 * @param userId
	 * @param orderType
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getOrdersList(int userId,String orderType, int status ,int periodDys)throws Exception;
		
	/**
	 * 
	 * @param pktCode
	 * @return
	 * @throws Exception
	 */
	public  Map<String,Object> findStockByPktCode(String pktCode,  List<String> columnNames)throws Exception;
	/**
	 * 
	 * @param pktId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findStockByPktId(Integer pktId, Integer grpId,
			List<String> columnNames) throws Exception;

	
	public List<Map<String,Object>> findStockByPktCodeList(String pktCode, List<String> columnNames) throws Exception;
	/**
	 * 
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Integer clearStock(Integer status, Integer partyAccId) throws Exception;

	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int editMemoExRate(OrderMasterData omd) throws Exception;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int editMemoType(OrderMasterData omd) throws Exception ;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public PacketDetails getPacketDetails(int pktId) throws Exception ;
	
	/**
	 * 
	 * @param pktCode
	 * @return
	 * @throws Exception
	 */
	public int deletePkt(String pktCode) throws Exception ;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int approveWebMemo(OrderMasterData omd) throws Exception ;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int editMemoDetails(OrderMasterData omd) throws Exception ;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getPendingStock(int status, int grpId, int priceNull, int partyAccid, String whereClause) throws Exception ;


	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getSimilarStock(int grpId, String pktCode,double range, int partyAccid, String lab) throws Exception ;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getSimilarPriceStock(int grpId, String pktCode, int partyAccid, String lab) throws Exception ;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getSoldPkt(int grpId, String pktCode, int partyAccid, int days, String lab) throws Exception ;

	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<QueryCodeDescription> transferStock(List<PacketDetails> pList,UserSession us) throws Exception ;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public QueryCodeDescription transferStockSingle(PacketDetails pd,UserSession us) throws Exception ;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean validatePkt(PacketDetails pd) throws Exception ;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> findpartyEmails(PacketDetails pd, int partyAccid, int days) throws Exception ;

	
	public Map<String,Object> findStockForMailPktCode(String pktCode, List<FileMap> fileMapList, String lab) throws Exception;
		
	public List<String> getColumns(int fileId, int sortId) throws Exception;
	
	public int uploadRap(String pktCode, List<FileMap> fileMapList, String lab) throws Exception;
	
	//kri_28
	public int uploadParcelStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession, PurchaseMaster pm, int fileId) throws Exception; 

	//kri_28
//	public int findPktCode(int pktCode) throws Exception;
	//kri_28
	public Map<String, Object> findMixPktCode(String pktCode) throws Exception ;
	public Map<String, Object> findMixPktId(String pktCode) throws Exception ;
	//kri_30
	//public List<String> searchBarCodebyPktno(String pktCode) throws Exception;
	//kri
	public PacketDetails getBarCodePktPrint(String pktCode) throws Exception;
	public List<PacketDetails> getPktPrintLab(String pktCode) throws Exception; 
	public Map<String, Object> getpktCode() throws Exception;
	public int updateParcelStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession, int fileId) throws Exception;
	public int updateSplitData(Integer pktId, double totCts) throws Exception;
	
	/*public int uploadParcelInsertStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession,
			PurchaseMaster pm, int fileId) throws Exception;*/
	public void updateParcelStock(StockMasterDO sMasterDO, StockPRPDO sPrpDO, UserSession us) throws Exception;
	public int insertParcelStock(StockMasterDO sMasterDO, StockPRPDO sPrpDO, UserSession us, Integer fixedFlag) throws Exception;


	public void saveParcelMaster(List<ParcelMaster> pmList) throws Exception;
	
	public ParcelMaster getParcelMaster(String id) throws Exception;
	
	public List<ParcelMaster> getParcelMasterList(String whereClause) throws Exception;
	
	public List<QueryCodeDescription> getParcelMasterQD() throws Exception;
	
	/**
	 * 
	 * @param pkts
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateStoneStatusParcel(List<PacketDetails> pkts,int status) throws Exception;
	
	/**
	 * 
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getMixStock(int status, int fixedFlag) throws Exception;
	
	/**
	 * 
	 * @param pktList
	 * @param rate
	 * @param pktNum
	 * @param pktCode
	 * @param us
	 * @return
	 * @throws Exception
	 */
	
	public int mergePackets(List<Integer> pktList, Double rate, Integer pktNum,String pktCode, UserSession us) throws Exception;
	
	public void addPriceMasterData(List<PriceMasterPrpDO> priceMstList) throws Exception ;
	
	public int insertParcelMaster(ParcelMaster pm) throws Exception;
	
	public int insertPurchaseAndGL(PurchaseMaster pm, PurchaseDetails pd, String accountCode, Integer partyAccId, String localCurr, String currency) throws Exception;
	
	public List<String> getfixedpkts() throws Exception;
	
	public List<PurchaseParcel> getPurchaseParcel() throws Exception;
	
	public int updateStockMaster(String codes, String cts) throws Exception;

	public int updatePkts(Integer iParcelID, Double totalCts) throws Exception;
	
	public int insertParcelHistory(Integer iParcelID, String sCts, String sCodes, Integer iPurchaseId) throws Exception;
	
	public int  insertFixedStockPkt(Integer pktId,Integer  deleteFlag, Integer fixedFlag)	throws Exception;
	
	public int getMaxStockMasterID() throws Exception;
}


package com.basync.b2b.crm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.AngadiaMaster;
import com.basync.b2b.crm.data.BankAccounts;
import com.basync.b2b.crm.data.Currency;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.GeneralIdNameStatusEtc;
import com.basync.b2b.crm.data.InvoiceMaster;
import com.basync.b2b.crm.data.Payment;
import com.basync.b2b.crm.data.PaymentDetails;
import com.basync.b2b.crm.data.ProfitLossData;
import com.basync.b2b.crm.data.PurchaseDetails;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.Dashboard;
import com.basync.b2b.dao.CustomerInfo;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.PageList;

public interface IMemoService {

	
	/**
	 * 
	 * @param list
	 * @param term
	 * @param factor
	 * @return
	 * @throws Exception
	 */
	public String getPrpStr(List<PrpData> list, Double term,Double factor,boolean isMemo, Integer rapChange,UserSession us) throws Exception;
	
	
	/**
	 * 
	 * @param list
	 * @param term
	 * @param factor
	 * @param rapDiscCol
	 * @return
	 * @throws Exception
	 */
	public String getPrpStrMap(List<FileMap> list, Double term,Double factor, Integer rapChange,boolean rapDiscCol,UserSession us) throws Exception;
	/**
	 * Get Stock details for memo
	 * @param term
	 * @param prpLst
	 * @param whereClause
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param currency
	 * @param factor
	 * @return
	 * @throws Exception
	 */
	
	public JQGridContainer getMemoStock(Double term, List<PrpData> prpLst,String whereClause,int userId, int page, int pageSize, String srtIndex, String srtType,String currency,Double factor, Integer rapChange, boolean isParcel,UserSession us) throws Exception;
	
	/**
	 * 
	 * @param term
	 * @param prpLst
	 * @param whereClauseList
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param currency
	 * @param factor
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getMemoStock(Double term, List<PrpData> prpLst,List<String> whereClauseList,int userId, int page, int pageSize, String srtIndex, String srtType,String currency,Double factor, Integer rapChange, boolean isParcel,UserSession us) throws Exception;
	
	/**
	 * 
	 * @param term
	 * @param prpLst
	 * @param whereClause
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param currency
	 * @param factor
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getMemoStockByMemoId(Double term, List<PrpData> prpLst,String whereClause,int userId, int page, int pageSize, String srtIndex, String srtType,String currency,Double factor, boolean isParcel,UserSession us) throws Exception;
	
	
	/**
	 * 
	 * @param param
	 * @param whereClause
	 * @return
	 * @throws Exception
	 */
	public Map getTotals(Object[] param, String whereClause) throws Exception;
	
	/**
	 * 
	 * @param param
	 * @param whereClause
	 * @return
	 * @throws Exception
	 */
	public Map getTotals(Double term, String whereClause,
			 Integer userId,boolean isMemo,boolean isParcel) throws Exception;
	
	
	/**
	 * 
	 * @param prpLst
	 * @param stage
	 * @return
	 */
	
	public String[] getJQGridColModel(List<PrpData> prpLst, int stage,UserSession us);
	
	/**
	 * 
	 * @param prpLst
	 * @param currency
	 * @param factor
	 * @return
	 */
	
	/*public String[] getJQGridColModel(List<PrpData> prpLst, String currency, Double factor);*/
	
	/**
	 * 
	 * @param activeFlag
	 * @return
	 * @throws Exception 
	 */
	public List<QueryDescription> getStatus(Integer activeFlag) throws Exception;
	
	/**
	 * 
	 * @param term
	 * @param prpString
	 * @param whereClause
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param isMemo
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getQueryResult(Double term,String prpString, String whereClause,
			 Integer userId,Integer page, Integer pageSize, String srtIndex,
				String srtType,boolean isMemo,boolean isParcel) throws Exception;
		
	/**
	 * 
	 * @param term
	 * @param prpString
	 * @param whereClauseList
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param isMemo
	 * @return
	 * @throws Exception
	 */
	public JQGridContainer getQueryResult(
			Double term,String prpString, List<String> whereClauseList,
			 Integer userId,Integer page, Integer pageSize, String srtIndex,
				String srtType,boolean isMemo ,boolean isParcel  ) throws Exception;
	
	/**
	 * 
	 * @param memoNos
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int memoReturnFull(String[]  memoNos, Integer userId)throws Exception;
	
	/**
	 * 
	 * @param pktNos
	 * @param memoNos
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int memoReturnByPktNos(String[] pktNos,String[] memoNos, Integer userId)throws Exception;
	/**
	 * 
	 * @param pktNos
	 * @param memoNos
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int memoReturnByPktNos(String[]  pktNos, String[] memoNos, Integer userId, Integer status )
	throws Exception;
	
	
	/**
	 * 
	 * @param pktNos
	 * @param memoNos
	 * @return
	 * @throws Exception
	 */
	public int returnParcelDtl(String[] pktNos,String[] memoNos)throws Exception;
	/**
	 * 
	 * @param status
	 * @param memoDate
	 * @param partyAccId
	 * @param partyAddId
	 * @param partyId
	 * @param orderType
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 
	 * @param queryClause
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	public PageList getMemoList(String memoDate, int partyAccId,
			int partyAddId, int partyId, String orderType, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId,int iMemoStatus, String sMemoType, 
			String accType, String sDuedate, String sToDate, String sFromDate, Integer exRate , String sCompanyName, Integer memoNo) throws Exception;
	
	public PageList getMemoListWeb(String memoDate,
			int partyAddId, int partyId, String orderType, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId ,int iMemoStatus, 
			String sMemoType, String accType, String sDueDate,
			String sToDate, String sFromDate, Integer exRate, String sCompanyName, Integer memoNo) throws Exception;
	
	/**
	 * 
	 * @param invType
	 * @param invDate
	 * @param partyAccId
	 * @param partyAddId
	 * @param partyId
	 * @param invStatus
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	public PageList getInvoiceList(String invType,String invDate, int partyAccId, int partyAddId, int partyId, String invStatus, Integer page, Integer pageSize, String srtIndex,
			String srtType, int userId, int selfPartyAccId)throws Exception;
	
	/**
	 * 
	 * @param queryClause
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	public PageList getInvoiceList(String queryClause,  Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId)throws Exception;

	/**
	 * 
	 * @param memoId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int insertMemoToInvoice(InvoiceMaster im, int memoId, int userId)throws Exception;
	
	/**
	 * 
	 * @param invId
	 * @return
	 * @throws Exception
	 */
	public InvoiceMaster getInvoiceMaster(Integer invId)throws Exception;
	
	/**
	 * 
	 * @param invId
	 * @return
	 * @throws Exception
	 */
	public List getInvoiceDetails(Integer invId)throws Exception;
	
	/**
	 * 
	 * @param im
	 * @return
	 * @throws Exception
	 */
	public Map getInvoiceAddDetails(InvoiceMaster im)throws Exception;
	
	/**
	 * 
	 * @param payment
	 * @throws Exception
	 */
	public void save(Payment payment)throws Exception;
	
	/**
	 * 
	 * @param payment
	 * @throws Exception
	 */
	public void update(Payment payment)throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getInvoiceList(Integer partyAccId)throws Exception;
	
	public List<QueryDescription> getInvoiceList(Integer partyAccId, String accType)
	throws Exception;
	/**
	 * 
	 * @param partyAccId
	 * @param accType
	 * @return
	 * @throws Exception
	 */
	public List<QueryDescription> getInvoiceList(Integer partyAccId, String accType, String exRate)
	throws Exception;
	/**
	 * 
	 * @param invId
	 * @param amount
	 * @param invStatus
	 * @return
	 * @throws Exception
	 */
	public int updateInvoiceStatus(Integer invId, BigDecimal amount, String invStatus)throws Exception;
 	
	/**
	 * 
	 * @param invId
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public int updateInvoiceStatus(Integer invId, BigDecimal amount) throws Exception ;	
	/**
	 * 
	 * @param status
	 * @param invIdString
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getPktDetailsByInvId(String invIdString) throws Exception;
	
	/**
	 * 
	 * @param pktIds
	 * @param partyAccId
	 * @param userSession
	 * @return
	 * @throws Exception
	 */
	public int trnPkts(String[] pktIds, Integer partyAccId, UserSession userSession) throws Exception;


	/**
	 * 
	 * @param queryClause
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	public PageList getPaymentList(String queryClause,  Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId, int paymentId, String partyName, String mode, String frDate, String toDate)throws Exception;

	/**
	 * 
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	public  List<QueryDescription> getPaymentDetails(Integer paymentId)throws Exception;

	
	/**
	 * 
	 * @param whereClause
	 * @param page
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @param userId
	 * @param selfPartyAccId
	 * @return
	 * @throws Exception
	 */
	public PageList getStockReportData(String whereClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId,
			int selfPartyAccId) throws Exception;

	/**
	 * 
	 * @param im
	 * @throws Exception
	 */
	public void updateInvoiceMasterData(InvoiceMaster im)throws Exception;
	
	
	/**
	 * 
	 * @param memoId
	 * @return
	 * @throws Exception
	 */
	public InvoiceMaster getInvoiceMasterByMemoId(Integer memoId)throws Exception;
	
	/**
	 * 
	 * @param om
	 * @return
	 * @throws Exception
	 */
	public Map getOrderAddDetails(OrderMasterData om) throws Exception ;
	
	
	public PageList getPartyOS(String whereClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId) throws Exception ;

	public PageList getPaurchaseRep(String whereClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId) throws Exception ;
	
	public List<PurchaseDetails> getPaurchaseDetails(String whereClause, Integer purchaseId) throws Exception ;

	public int addNewAngadia(AngadiaMaster am, UserSession us) throws Exception ;
	
	public int updateAngadia(AngadiaMaster am, UserSession us) throws Exception ;
	
	public List<AngadiaMaster> getAngadiaData(Integer status) throws Exception ;
	
	public AngadiaMaster getAngadiaDataById(Integer angadiaId) throws Exception ;
	
	public int addNewGLAccTyp(GeneralIdNameStatusEtc g) throws Exception ;
	
	public int updateGLAccTyp(GeneralIdNameStatusEtc g) throws Exception ;
	
	public List<GeneralIdNameStatusEtc> getGLAccTypData(Integer status) throws Exception ;
	
	public int addNewGLAccGrp(GeneralIdNameStatusEtc g) throws Exception ;
	
	public int updateGLAccGrp(GeneralIdNameStatusEtc g) throws Exception ;
	
	public List<GeneralIdNameStatusEtc> getGLAccGrpData(Integer status) throws Exception ;

	public int addNewGLAcc(GeneralIdNameStatusEtc g) throws Exception ;
	
	public int updateGLAcc(GeneralIdNameStatusEtc g) throws Exception ;
	
	public List<GeneralIdNameStatusEtc> getGLAccData(Integer status) throws Exception ;
	
	public List<QueryCodeDescription> getCurrencyQD(Integer status) throws Exception ;

	public List<BankAccounts> getBankAccList(Integer status, Integer partyAccId) throws Exception ;
	
	public BankAccounts getBankAcc(Integer bankAccId) throws Exception ;
	
	public int addNewBankAcc(BankAccounts b) throws Exception ;
	
	public int updateBankAcc(BankAccounts b) throws Exception ;
	
	public List<Currency> getCurrencyList(Integer status) throws Exception ;
	
	public int addNewCurrency(Currency c) throws Exception ;
	
	public int updateCurrency(Currency c) throws Exception ;
	
	public int deleteBankAcc(int bankAccId) throws Exception ;
	
	public int deleteCurrency(String currAbrev) throws Exception ;
	
	/*public List<QueryDescription> getBankAcc(int partyAccId) throws Exception ;*/
	
	public Double getBankBalance(int bankAccId) throws Exception ;
	
	public int addBankTran(int type, int transNo, int bankAccId, String ref,String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate)throws Exception ;
	
//	public BigDecimal addGlTran(int type, int transNo, String accountCode, String ref, String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate, int personTypeId,  Integer partyAccId, String currCode, String userCurrency)throws Exception ;
	
	public int addAngadiaTran(int type, int transNo, int angadiaId, String ref, String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate)throws Exception ;
	
	public int addSuppTran(int type, int transNo, int vendorId,String vendorRef, String ref, String dsc, String date, Double amount, int userId, Double exRate)throws Exception ;
	
	public int addCustTran(Payment p, int userId, Double exRate)throws Exception ;
	
	public void addBankTrf(int fromBankAccId, int toBankAccId, String ref, String date, BigDecimal amt,BigDecimal charges, BigDecimal exRate1, BigDecimal exRate2,  String dsc, int userId, Integer partyAccId,  String userCurrency)throws Exception ;
	
	public void addPaymentTrf(int fromAccType, int toPartyType, int fromBankAccId, int fromAngadiaId, 
			int toPartyAccId, int vendorAccId, String toPartyName, String accountCode, String paymentDate, BigDecimal amount, BigDecimal actualAmount,String mode, String ref, String dsc , BigDecimal exRate, int userId, Integer invoice, PaymentDetails pd, String chequeDate, Integer partyAccId, String userCurrency) throws Exception;
	
	//public void addAngadiaPaymentTrf()throws Exception ;
	public int addDepositTrf(int fromPartyType, int toAccType, int fromPartyAccId, int VendorAccId, String fromPartyName,  int toBankAccId, int toAngadiaId, 
			 String paymentDate, BigDecimal totAmount, String mode, String ref, String dscMain , int userId, List<PaymentDetails> pdtl,  String payTerms,String currency, BigDecimal exRate2, Integer partyAccId, String localCurr, Integer checkBoxVal)throws Exception ;

	
	public void addJournalEntry(List<Object> paramList, String paymentDate, int userId, String ref,Integer partyAccId, String localCurr)throws Exception ;
	
	//public void addAngadiaPaymentTrf()throws Exception ;
	public int addPurchaseTrf(String fromPartyType, Integer vendorAccId,String accountCode,
			String paymentDate, BigDecimal amount, String ref, BigDecimal exRate,Integer partyAccId, String billNO,Integer status, Integer glTransNo, String localCurr, String partyCurr) throws Exception ;	

	public void addSaleTrf(int fromPartyType, int toPartyType, int fromBankAccId,
			int fromAngadiaId, int toPartyAccId, int packetNo,
			String toPartyName,String fromPartyName,String loc, String accountCode, String paymentDate,
			BigDecimal amount, String mode, String ref, String dsc, BigDecimal exRate,
			int userId,BigDecimal tax,BigDecimal discount, int status) throws Exception;

	
	public GeneralIdNameStatusEtc getGlAcc( int accCode)throws Exception ;
	
	public List<QueryCodeDescription> getPktMemo(String[] pktNos,String[] memoNos) throws Exception;
	
	/**
	 * 
	 * @param omd
	 * @return
	 * @throws Exception
	 */
	public int updateOrderMemo(OrderMasterData omd) throws Exception;
	
	/**
	 * 
	 * @param memoOrderId
	 * @return
	 * @throws Exception
	 */
	public int getInvIdFromMemo(int memoOrderId)throws Exception;


	public Object getBankAccCode(Integer accCode) throws Exception;

	
	/**
	 * 
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	public int clearPayment(int paymentDetailId)throws Exception ;

	public Float getExRateFromOrderId(int memoId) throws Exception;
	
	public int memoReturnTrf(String[] pktIds, int UserId, int PartyAccId) throws Exception;
	
	public int checkCompName(String companyName)throws Exception;
	public int checkMobNo(String mobNo)throws Exception;
	public Double getSalesData(String fromDate, String toDate)throws Exception;

	public PageList getPacketDetailCert() throws Exception;
	
	public PageList getPacketDetailCert(int iAllLab) throws Exception;
	public PurchaseMaster getGoodCostData(String fromDate, String toDate)throws Exception;
	public List<ProfitLossData> getPerReportData(String fromDate, String toDate, String partyName)throws Exception;
	public int storedProcedureCall(String sql, Object [] params) throws Exception;
	
	public  List<Dashboard> getDashboardQueries() throws Exception;
	
	public Integer getdashboardDataInt(String sql) throws Exception;
	
	public List<QueryDescription> getdashboardDataQueryCode(String sql) throws Exception;
	
	public int getInvalidStockCount(int iPartyAccid) throws Exception;
	
	public int getOverdueMemoCount(int iPartyAccid, String sDate) throws Exception;
	
	public List<QueryDescription> getPurchaseInvoiceList(Integer vendorId, Integer paidStatus) throws Exception;
	
	public BigDecimal addGlTrans(int type, int transNo, String accountCode, String ref,
			String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate,int personTypeId, Integer partyAccId, String currency)
			throws Exception;

	public List<PurchaseMaster> getPurchaseMaster(int partyAccId, String date) throws Exception;
	
	public int updatePurchaseDetails(List<PurchaseDetails> lst, Integer glTransNo, Double amount, String Currency, Integer purchaseId, Integer partyAccId) throws Exception;
	
	public List<GeneralIdNameStatusEtc> getGroupGlAcc(String accCodes) throws Exception;
	//public BigDecimal updateGlTran(BigDecimal amount , String glTransNo, String desc, String currency, String acccountCode) throws Exception ;
	public void deleteGlTran(String glTransNo) throws Exception;
	public List<PurchaseDetails> getPurchaseDetails(Integer purchaseId) throws Exception;
	public List<CustomerInfo> getCustomerBalanceList(String fromDate,String toDate,String custName,String branch,Integer partyAccId) throws Exception;
	public List<CustomerInfo> getSupplierBalanceList(String fromDate,String toDate,String custName,String branch,Integer partyAccId) throws Exception;
	public List<CustomerInfo> getPaymentReport(String fromDate,String toDate,String custName,String branch,Integer partyAccId) throws Exception;
	public List<CustomerInfo> getTaxReport(String fromDate,String toDate,String branch) throws Exception;
	public List<CustomerInfo> getCheckingAccountOpen(String fromDate, String toDate,String accountCode,String branch) throws Exception;
	public List<CustomerInfo> getCheckingAccountClose(String fromDate, String toDate,String accountCode,String branch) throws Exception;
	public List<CustomerInfo> getInvoiceDetails(String fromDate, String toDate,String branch) throws Exception;
	public List<CustomerInfo> getPayDetails(String fromDate, String toDate,String branch) throws Exception;
	public List<CustomerInfo> getPurchaseDetailsReport(String fromDate, String toDate, String branch) throws Exception;
	public List<CustomerInfo> getBrokerageList(String fromDate, String toDate,Integer partyAccId,String branch) throws Exception;

}

package com.basync.b2b.crm.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.GlRepData;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.ThreeStrings;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.StockCheckPktDetals;
import com.basync.b2b.util.PageList;

public interface IRepService {
	public List<GlRepData> getGlRepData(String fromDate, String toDate, int AccCode)throws Exception;
	public Double getGlAccOpBal(String fromDate, int AccCode)throws Exception;
	public Double getGlAccClBal(String toDate,int accCode) throws Exception;
	public PageList getSaleReportData(String fromDt, String todate, String pktNos, String srtIndex,String srtType, int selfPartyAccId, int userId,int page, int pageSize) throws Exception ;
	public Map getTotals(Object[] param, String sql) throws Exception;
	
	public PageList getPartyOSData(String fromDt, String todate, String pktNos, String srtIndex,String srtType, int selfPartyAccId, int userId,int page, int pageSize) throws Exception ;
	
	public PageList getPartyOSDataByParty(String fromDt, String todate, String pktNos, String srtIndex,String srtType, int partyAccId,int page, int pageSize) throws Exception ;
    
	//kri
	public Double getBankStatementOpBal(String fromDate)throws Exception;
	//kri
	public Double getBankAcc(String fromDate, int AccCode)throws Exception;
	//kri
	public Double getBankClBal(String toDate)throws Exception;
   //kri
	//public List<bankStmRepData> getbankStmRepData(String fromDate, String toDate)throws Exception;
	//kri
	public List<GlRepData> getBankAccountsRepData(String fromDate, String toDate) throws Exception;
    //kri
	public Double getAngadiaOpBal(String fromDate)throws Exception;
	//kri
	public Double getAngadiaClBal(String toDate)throws Exception;
	//kri
	public List<GlRepData> getAngadiaReportData(String fromDate, String toDate) throws Exception;
	//kri
	public List<GlRepData> getbankAccTrans(String fromDate, String toDate,int  bankAccId) throws Exception;
	//kri
	public Double getBankStatementOpBal(String fromDate,int bankAccId) throws Exception ;
	//kri
	public Double getBankClBal(String toDate,int bankAccId) throws Exception;
	//kri
	public List<GlRepData> getAngadiaTran(String fromDate, String toDate,int angdAccId) throws Exception;
	//kri
	public Double getAngadiaOpBal(String fromDate,int angdAccId) throws Exception;
	//kri
	public Double getAngadiaClBal(String toDate,int  angdAccId) throws Exception;
	//kri
	//public List<BankAccounts> getAngadiaAccList(Integer status) throws Exception;
	//kri
	public List<QueryDescription> getAngadiaAccList(Integer status) throws Exception;
	//krishna 17/1 to search  whole stock report 
	
	public List<StockCheckPktDetals> getStockChekRepData(String[] size, String[] status, String[] sh, String[] pu,String[] c, String pktCode,int partyAccId) throws Exception;
   
	//Get balance OS due till date
	public BigDecimal getPartyOSDue(String toDate, int partyAccId) throws Exception;
	//Get balance OS till date
	public Map<String, BigDecimal> getPartyOS(String toDate, int partyAccId) throws Exception;
	
	//Get party payment done to self company
	public BigDecimal getPartyPayment(String toDate, int partyAccId) throws Exception;
	
	//Get party payment which is pending (PDC) to self company
	public BigDecimal getPartyPendingPayment(String toDate, int partyAccId) throws Exception;
	
	//GetParty Balacnce
	public BigDecimal getPartyBal(String toDate, int partyAccId) throws Exception ;
	
	//Get GL party data 
	public List<GlRepData> getPartyGL(String fromDate, String toDate,int partyAccId) throws Exception;
	
	public  List<ThreeStrings> getGlAccClGroupBal(String fromDate, String accCodes) throws Exception;
	
	public  List<ThreeStrings> getGlAccOpGroupBal(String fromDate, String accCodes) throws Exception;
	
	public List<GlRepData> getGlGroupRepData(String fromDate, String toDate, 	String accCodes) throws Exception ;
	
	public  List<QueryCodeDescription> getBlAccOpGroupBal(String fromDate, String accCodes, String branch) throws Exception;
	public  List<QueryCodeDescription> getBlAccClGroupBal(String fromDate, String accCodes, String branch) throws Exception;
}

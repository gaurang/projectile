package com.basync.b2b.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.dataobjects.StockUploadDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.ConnectionUtil;
import com.basync.b2b.util.StringUtils;
import com.mysql.jdbc.Statement;

public class StockUploadDAOImpl {
	
	private ConnectionUtil dataSource =null;
	private MailSenderPooled mailSender;
	private IMemoService memoService;
	private IGenericService genericService;
	private IStockService stockService;
	private IPriceService priceService;
	private JdbcDao jdbcDao;
	
	public JdbcDao getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	protected Logger logger = Logger.getLogger(getClass());
	private String updateCnt= "";
	
	public IStockService getStockService() {
		return stockService;
	}

	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}
	/**
	 * @return the mailSender
	 */
	public MailSenderPooled getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(MailSenderPooled mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setDataSource(ConnectionUtil dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the memoService
	 */
	public IMemoService getMemoService() {
		return memoService;
	}

	/**
	 * @param memoService the memoService to set
	 */
	public void setMemoService(IMemoService memoService) {
		this.memoService = memoService;
	}
	
	
	
	/**
	 * @return the genericService
	 */
	public IGenericService getGenericService() {
		return genericService;
	}

	/**
	 * @param genericService the genericService to set
	 */
	public void setGenericService(IGenericService genericService) {
		this.genericService = genericService;
	}

		
	
	/**
	 * @return the priceService
	 */
	public IPriceService getPriceService() {
		return priceService;
	}

	/**
	 * @param priceService the priceService to set
	 */
	public void setPriceService(IPriceService priceService) {
		this.priceService = priceService;
	}

	public void uploadStock(List<StockUploadDO> list) {}

	public List<String> getColumns(int fileId) {
		List<String> list=new ArrayList<String> ();
		Connection conn = dataSource.getConnection();
		logger.debug("^^^^^"+conn);
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			pst = conn
					.prepareStatement("SELECT columnName FROM tb_excelmap e,tb_excelfile ef where e.fileId=ef.id and ef.id = "+fileId+" order by colIndex asc");
			if(fileId == -1){
				pst = conn
					.prepareStatement("SELECT columnName FROM tb_excelmap e,tb_excelfile ef where e.fileId=ef.id and ef.sort = 0 order by colIndex asc");
			}
			
			rs = pst.executeQuery();
			while(rs.next()){
				list.add(rs.getString("columnName"));
			}
		} catch (Exception e) {
			logger.error("ERROR IN GETTING DATA",e);
		}
		finally{
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return list;
	}
	
	public String getFileType(int fileId) {
		List<String> list=new ArrayList<String> ();
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		String type = "EXCEL";
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT type FROM tb_excelfile ef where ef.id = "+fileId+" ");
			rs = pst.executeQuery();
			while(rs.next()){
				type = rs.getString(1);
			}
		} catch (Exception e) {
			logger.error("ERROR IN GETTING DATA",e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return type;
	}
	public int getFileRapFormat(int fileId) {
		List<String> list=new ArrayList<String> ();
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		int rapFormat = 1;
		
		try {
			String sql = "SELECT ifnull(rapFormat,1) FROM tb_excelfile ef where ef.id = "+fileId+" ";
			if(fileId == -1){
				sql = "SELECT ifnull(rapFormat,1) FROM tb_excelfile ef where sort = 0 ";
			}
			PreparedStatement pst = conn
					.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				rapFormat = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error("ERROR IN GETTING DATA",e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return rapFormat;
	}
	public HashMap<String, String> getPrpValMap() {
		HashMap<String, String> hMap=new HashMap<String, String>();
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT prp, prpValue prpValue, sortId   FROM tb_prpmaster p inner join tb_prpdetail pd "+
								" on p.ID =pd.prpId ");
			rs = pst.executeQuery();
			while(rs.next()){
				hMap.put(rs.getString("prp")+"_"+rs.getString("prpValue").toUpperCase().replaceAll(" ", "-"), rs.getString("sortId"));
				//logger.debug("#################"+rs.getString("prp")+"_"+rs.getString("prpValue")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return hMap;
	}
	
	public HashMap<String, String> getValPrpMap() {
		HashMap<String, String> hMap=new HashMap<String, String>();
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT prp, prpValue prpValue, sortId   FROM tb_prpmaster p inner join tb_prpdetail pd "+
								" on p.ID =pd.prpId where pd.validFlag = 1");
			rs = pst.executeQuery();
			while(rs.next()){
				if(rs.getString("prp").equals("SZ")){
					hMap.put(rs.getString("prp")+"_"+rs.getString("prpValue"), rs.getString("sortId"));
				}else{
					hMap.put(rs.getString("prp")+"_"+rs.getString("sortId"),  rs.getString("prpValue"));
					//logger.debug("#################"+rs.getString("prp")+"_"+rs.getString("prpValue"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return hMap;
	}
	
	public String getSizes(Double value) {
		
		if(value <= 0.299d )
			return "+0.10";
		else if(value >= 0.30d && value <= 0.399d )	
			return "+0.30";
		else if(value >= 0.40d && value <= 0.499d )	
			return "+0.40";
		else if(value >= 0.50d && value <= 0.599d )	
			return "+0.50";
		else if(value >= 0.60d && value <= 0.699d )	
			return "+0.60";
		else if(value >= 0.70d && value <= 0.799d )	
			return "+0.70";
		else if(value >= 0.80d && value <= 0.899d )	
			return "+0.80";
		else if(value >= 0.90d && value <= 0.999d )	
			return "+0.90";
		else if(value >= 1d && value <= 1.499d )	
			return "+1.00";
		else if(value >= 1.5d && value <= 1.999d )	
			return "+1.50";
		else if(value >= 2d && value <= 2.999d )	
			return "+2.00";
		else if(value >= 3d && value <= 3.999d )	
			return "+3.00";
		else if(value >= 4d && value <= 4.999d )	
			return "+4.00";
		else if(value >= 5d )	
			return "+5.00";
		else 
			return "+10.00";
	}

	public int uploadStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession, PurchaseMaster pm, int fileId) throws Exception {
		
		Connection conn =dataSource.getConnection();
		PreparedStatement pst =null;
		int countEx=0;
		//conn.setAutoCommit(false);
		List<Integer> purchaseList = new ArrayList<Integer>();
		Double totalPrice = 0d;
		int rapFormat = getFileRapFormat(fileId); 
		
		HashMap<String, String> hMap = getValPrpMap();
		ResultSet rs = null;
		int upCnt = 0;
		int insertCnt = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		SimpleDateFormat sdf2 = new SimpleDateFormat(this.getGenericService().getSysPref("date_format"));
		String  askingPrice = this.getGenericService().getSysPref("askingPrice");
		//kri
		Double askingPrDbl = 0d;
		List<StockMasterDO> stockMasterPurchaseList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPrpPurchaseList = new ArrayList<StockPRPDO>();
		
		if(!StringUtils.isEmpty(askingPrice))
			askingPrDbl = Double.parseDouble(askingPrice);
		
		String msg="";
		Integer userId = userSession.getUserId();
		try {
			pst = conn.prepareStatement("delete from tb_pktupload ");
			pst.executeUpdate();
			
			for (int i = 0; i < stockMasterList.size(); i++) {
				StockMasterDO stockMasterDO = new StockMasterDO();
				stockMasterDO = stockMasterList.get(i);
				stockMasterDO.setPartyAccId(userSession.getPartyAccId());
				stockMasterDO.setUserId(userSession.getUserId());
				StockPRPDO stockPRPDO = new StockPRPDO();
				stockPRPDO = stockPRPList.get(i);
				
				
				//Updated details
				String currDate = sdf.format(new Date());
				stockMasterDO.setLastUpdateDate(currDate);
				stockMasterDO.setUpdateBy(userId);
				stockPRPDO.setLastUpdateDate(currDate);
				stockPRPDO.setUpdateBy(userId);
				
				//for rap format
				if(rapFormat == 100 && stockMasterDO.getRap() !=null)
					stockMasterDO.setRap(stockMasterDO.getRap()*rapFormat);
				logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+rapFormat);
				//Set asking rap relation if rap is null kri
				if(stockMasterDO.getRap()==null && stockMasterDO.getAskingPriceDisc() !=null)
				{
					stockMasterDO.setRap(stockMasterDO.getAskingPriceDisc() - askingPrDbl);
				}
				//for lab
				if(stockPRPDO.getLAB()!=null){
					if(stockPRPDO.getLAB().equals("IGI")){
						if(stockPRPDO.getFL()!= null){
							if(stockPRPDO.getFL().startsWith("Medium")){
								stockPRPDO.setFL("Slight");
							}else if(stockPRPDO.getFL().startsWith("Faint")){
								stockPRPDO.setFL("V.Slight");
							}
						}
					}else{
						if(stockPRPDO.getFL()!= null){
							if(stockPRPDO.getFL().startsWith("Medium")){
								stockPRPDO.setFL("Medium");
							}else if(stockPRPDO.getFL().startsWith("Faint")){
								stockPRPDO.setFL("Faint");
							}
						}
					}
				}
				//for SH
				if(stockPRPDO.getSH_so() != null && hMap.containsKey("SH_"+stockPRPDO.getSH_so().intValue()))
					stockPRPDO.setSH(hMap.get("SH_"+stockPRPDO.getSH_so().intValue()));
				
				//DATA manupalation
				if(stockMasterDO.getPairStock()!=null)
					stockMasterDO.setIsMatched("1");
				stockMasterDO.setTotalCts(stockPRPDO.getCTS());
				stockMasterDO.setTotalPcs(stockMasterDO.getPcs());
				stockPRPDO.setRapLab(stockMasterDO.getRap());
				stockPRPDO.setRapPriceLab(stockMasterDO.getRapPrice());
				stockPRPDO.setRateLab(stockMasterDO.getRate());
				
				
				String[] queryElem ={
						 "GRPID","CTS","certLabId","certLabUrl", "SH", 
						 "SH_so","SZ","SZ_so","PU","PU_so","C",
						 "C_so","CT","CT_so","IG","IG_so","BI",
						 "BI_so","TI","TI_so","DP","DP_so","T",
						 "T_so","PO","PO_so","SY","SY_so","FL",
						 "FL_so","GD","GD_so","PA","PA_so","LI","LI_so",
						 "AD","AD_so","MD","MD_so","XD",
						 "XD_so","D","D_so","CA","CA_so","CH",
						 "CH_so","FLS","FLS_so","SD","SD_so",
						 "LS","LS_so","TOP","TOP_so","COP",
						 "COP_so","POP","POP_so","L","L_so",
						 "W","W_so","PD","PD_so","CU","CU_so",
						 "SL","SL_so","LG","LG_so","GBA",
						 "GBA_so","GHA","GHA_so","ORG","ORG_so","LAB","LAB_so",
						 "FNC", "FNC_so", "FNCI", "FNCI_so", "FNCO", "FNCO_so", 
						 "GTN" , "GTN_so", "GTH", "GTH_so", "GC", "GC_so", "CC", "CC_so", "lastUpdateDate","updateBy",
						 "shFr",  "shFr_so", "shTo", "shTo_so","puFr", "puFr_so", "puTo", "puTo_so","cFr", "cFr_so", "cTo", "cTo_so", "AVGCTS", "SIEVE", "SIEVE_so", "PPC",
						 "PTYP", "PTYP_so","rapLab","rapPriceLab","rateLab" , 
						 "job_no" , "ctrl_no" , "DD" , "PU_ST" , "C_DESC" , "LH" , "PAINT" , "PAINT_COMM" , "KEY2SYMB" , "INSCRIPTION" , 
						 "GD_PER" , "SYNTH_IND" , "REPORT_COMM", "sieveFr", "sieveFr_so", "sieveTo", "sieveTo_so", "comment1", "comment2", "comment3"
				};
				//split into different tables 
					int cntPkt=0;	
					int pktId =0;
					
			try{
					if(StringUtils.isEmpty(stockMasterDO.getPktCode())){
						// Auto generated numbers 
						pst = conn.prepareStatement("select max(id) from tb_stockmaster ");
						CommonUtil.getInstance();
						
						Integer pktCode = 10000;
						if(CommonUtil.getPropertiesByName("b2b.pktcode.seq")!=null){
							pktCode = Integer.parseInt(CommonUtil.getPropertiesByName("b2b.pktcode.seq"));
						} 
						
						rs= pst.executeQuery();
						if(rs.next())
							pktCode = rs.getInt(1)+1;
						stockMasterDO.setPktCode(pktCode.toString());
					}
				
				
					//For pair numbering
					updateMatchPair(stockMasterDO,conn);
				
					pst = conn.prepareStatement("select count(*) from tb_stockmaster where pktCode = '"+stockMasterDO.getPktCode()+"'");
					
					rs= pst.executeQuery();
					if(rs.next())
						cntPkt = rs.getInt(1);
					
					String smQueryElem[] = {
							"pktCode", "pcs", "rate", "baseRate",
							"issueDate", "status","totalCts", "rapPrice","rap","certId", "ownerCode"
							,"companyName", "certURL", "updateDate", 
							"treatment","cashPrice","cashDiscount","comment","country","state","city",
							"isMatchedSep", "isMatched", "pairStock", "allowRapFeed", "parcelNum" ,"diamondImage","d3Image","tradeShow","vendorId","rootPkt","remark","partyAccId","userId","totalPcs","value", "pairNo" , "pktCode2",
							"rapCode", "vendorStockCode", "availabilty", "sarinFile", "gemFile", "clientRow", "lastUpdateDate","updateBy","askingPriceDisc"	 };
					if(stockPRPDO.getGRPID() != 1l){
						String smQueryElem2[] = {
								"pktCode", "pcs",  "baseRate",
								"issueDate", "status","totalCts","certId", "ownerCode"
								,"companyName", "certURL", "updateDate", 
								"treatment","cashPrice","cashDiscount","comment","country","state","city",
								"isMatchedSep", "isMatched", "pairStock", "allowRapFeed", "parcelNum" ,"diamondImage","d3Image","tradeShow","vendorId","rootPkt","remark","partyAccId","userId","totalPcs","value", "pairNo" , "pktCode2",
								"rapCode", "vendorStockCode", "availabilty", "sarinFile", "gemFile", "clientRow", "lastUpdateDate","updateBy","askingPriceDisc"	 };
						smQueryElem = smQueryElem2;
					}
					
					if(cntPkt == 0){
						//Purcase only if pkt is not duplicate
						stockMasterPurchaseList.add(stockMasterDO);
						stockPrpPurchaseList.add(stockPRPDO);
						
						
						if(!StringUtils.isEmpty(stockMasterDO.getIssueDate())){
							Date date = sdf2.parse(stockMasterDO.getIssueDate());
							stockMasterDO.setIssueDate(sdf.format(date));
						}else{
							stockMasterDO.setIssueDate(sdf.format(new Date()));
						}	
						
						String sql ="insert into tb_stockmaster" + "("
										+ StringUtils.toString(smQueryElem, ",")
										+" ) values ( ";

						for (int j = 0; j < smQueryElem.length; j++) {
							if(j==0)
								sql += getQueryVal(smQueryElem[j],stockMasterDO);
							else
								sql += ", "+getQueryVal(smQueryElem[j],stockMasterDO);
						}		
						sql += ")";
						
						pst.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
						
						rs = pst.getGeneratedKeys();
						if(rs.next())
						pktId = rs.getInt(1);
						
						//Stock Prp
						
						String query = " insert into tb_stockprp"
								+ "(PKTID," + StringUtils.toString(queryElem, ",") 
								+ ") values (" + pktId ;
						for (int j = 0; j < queryElem.length; j++) {
							query += ", "+getQueryVal(queryElem[j],stockPRPDO);
						}		
								
						query += ")";
						//logger.debug("\n query id "+query);
						pst = conn.prepareStatement(query);
						pst.executeUpdate();
						//insert into fixed stock packet table....Arun
						this.getStockService().insertFixedStockPkt(null, 0, 0);
						
						totalPrice += ((stockMasterDO.getBaseRate()!=null?stockMasterDO.getBaseRate():0)*stockPRPDO.getCTS());
						insertCnt++;
						
						updateRapRates(stockMasterDO,stockPRPDO, conn,1);
					}else{
						
				//		String arr[] ={"rate", "baseRate", "rap", "rapPrice"};
				//		Rate = "+stockMasterDO.getRate()+", BaseRate = "+stockMasterDO.getBaseRate()+", Rap = " +
				//			+stockMasterDO.getRap()+", RapPrice = "+stockMasterDO.getRapPrice()+"
						String qrySubStr ="";
					/*	if(stockMasterDO.getRate()!=null)
							qrySubStr += "rate = ("+stockMasterDO.getRate() +" ) , ";
						if(stockMasterDO.getBaseRate()!=null)
							qrySubStr += "baseRate = "+stockMasterDO.getBaseRate()+", ";
						if(stockMasterDO.getRap()!=null)
							qrySubStr += "rap = "+stockMasterDO.getRap()+" , ";
						if(stockMasterDO.getRapPrice()!=null)
							qrySubStr += "rapPrice = "+stockMasterDO.getRapPrice()+", ";*/
						pst = conn.prepareStatement("select ID from tb_stockmaster where pktCode = '"+stockMasterDO.getPktCode()+"' and status in (0,1,7) and partyAccId = "+userSession.getPartyAccId());
						ResultSet rs1= pst.executeQuery();
						int Id = 0;
						if(rs1.next()){
							Id = rs1.getInt(1);
						}else{
							msg+="<br/> Packet is duplicate and already sold or not at your location skipped verify pktcode "+stockMasterDO.getPktCode();
							continue;
						}
						
						//update Master
						int a = this.getPriceService().updateStockPriceHistory(stockMasterDO.getRate(), stockMasterDO.getRap(),
								Id,  userId);
						//If original property is to be edited is when only property updates required
						for (int j = 1; j < smQueryElem.length; j++) {
							if(smQueryElem[j].equalsIgnoreCase("status"))
								continue;
							
							if(j!= 1)
								qrySubStr +=  ", ";
							qrySubStr +=  smQueryElem[j]+" = "+getQueryVal(smQueryElem[j],stockMasterDO);
						}
						if(!StringUtils.isEmpty(stockMasterDO.getEditPktCode())){
							qrySubStr +=  ", pktCode = '"+stockMasterDO.getEditPktCode()+"'";
						}
						String qryStr = "update tb_stockmaster set "+qrySubStr +" where pktCode = '"+stockMasterDO.getPktCode()+"'";
						
						
						logger.debug(qryStr);
						pst = conn.prepareStatement(qryStr);
						
						pst.executeUpdate();
					
						/*
						 * //if else for new grp logic changed on 23 jan 2012
						 * 
						String qry = " update tb_stockprp set status = 1 ";
						for (int j = 0; j < queryElem.length; j++) {
							qry +=  ", "+queryElem[j]+" = "+getQueryVal(queryElem[j],stockPRPDO);
						}
						qry += " where pktId = "+Id+" and grpId = "+stockPRPDO.getGRPID() +" and ifnull(lab_so,0) = '"+(stockPRPDO.getLAB()!=null?stockPRPDO.getLAB_so():0)+"'";
						
						logger.debug(qry);
						pst = conn.prepareStatement(qry);
						int z =pst.executeUpdate();*/
						
							//if(z == 0){//if else for new grp logic changed on 23 jan 2012
								boolean update =false;
								//This is when only property updates required
								/*if(stockPRPDO.getLAB_so()!=null){*/
								
								//stockPRPDO.setGRPID(stockPRPDO.getLAB_so().longValue());
								
								//set lab in editing form as well
								pst = conn.prepareStatement("select count(*) from tb_stockprp where pktId = "+Id +" and ifnull(lab,'') = '"+(stockPRPDO.getLAB()!=null?stockPRPDO.getLAB():"")+"'");
								rs1= pst.executeQuery();
								if(rs1.next()){
									if(rs1.getInt(1) > 0){
										update = true;
									}
								}
								/*}
					
								else{
									pst = conn.prepareStatement("select max(grpid) from tb_stockprp where pktId = "+Id);
									rs1= pst.executeQuery();
									long grpId = 0;
									if(rs1.next())
										grpId = rs1.getLong(1)+1;
									stockPRPDO.setGRPID(grpId);
								}*/
								
								if(!update){
									
									pst = conn.prepareStatement("select max(grpId)  from tb_stockprp where pktId = "+Id);
									rs1= pst.executeQuery();
									long grpId = 0;
									if(rs1.next())
										grpId = rs1.getLong(1)+1;
									pst = conn.prepareStatement("update tb_stockprp set grpId = ifnull(lab_so,"+grpId+") where pktId = "+Id +" and grpId = "+stockPRPDO.getGRPID());
									int z = pst.executeUpdate();
									
									String query = " insert into tb_stockprp"
											+ "(PKTID," + StringUtils.toString(queryElem, ",") 
											+ ") values (" + Id ;
									for (int j = 0; j < queryElem.length; j++) {
										query += ", "+getQueryVal(queryElem[j],stockPRPDO);
									}		
											
									query += ")";
									//logger.debug("\n query id "+query);
									pst = conn.prepareStatement(query);
									pst.executeUpdate();
									
								}else{
									String qry = " update tb_stockprp set status = 1 ";
									for (int j = 0; j < queryElem.length; j++) {
										qry +=  ", "+queryElem[j]+" = "+getQueryVal(queryElem[j],stockPRPDO);
									}
									qry += " where pktId = "+Id+" and ifnull(lab,'') = '"+(stockPRPDO.getLAB()!=null?stockPRPDO.getLAB():"")+"'";
									if(stockPRPDO.getGRPID() >0){
										qry += " and GRPID = "+stockPRPDO.getGRPID();
									}
									
									pst = conn.prepareStatement(qry);
									int z =pst.executeUpdate();
								}
								
							//	} //if else for new grp logic changed on 23 jan 2012
							
							//logger.debug("#########"+ z +" id "+Id);
							updateRapRates(stockMasterDO,stockPRPDO, conn,1);
						upCnt++;
					}
					
				countEx++;	
				
				setUpdateCnt("(Inserted - "+insertCnt +" & Updated - "+upCnt+"" +msg);
				}catch (Exception e) {
					logger.error("Error in uploading pktCode "+stockMasterDO.getPktCode() +"   " +e.getMessage());
					e.printStackTrace();
				}
			}
			
			//if(countEx == stockMasterList.size())
			//updateStockStatus(stockMasterList, conn);
			if( pm.getVendorId() !=null && pm.getVendorId() > -1 && stockMasterPurchaseList.size()>0){
				//Add only if purchase from vendor
				pm.setAmount(totalPrice);
				insertPurchaseEntry(stockMasterPurchaseList,stockPrpPurchaseList, pm ,userSession, conn);
			}
		//	conn.commit();
		} catch (Exception e) {
			//conn.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		return countEx;
	}
	
	/**
	 * @return the updateCnt
	 */
	public String getUpdateCnt() {
		return updateCnt;
	}

	/**
	 * @param updateCnt the updateCnt to set
	 */
	public void setUpdateCnt(String updateCnt) {
		this.updateCnt = updateCnt;
	}

	public void updateMatchPair(StockMasterDO stockMasterDO,Connection conn) throws Exception{
		if(stockMasterDO.getPairStock()!=null){
			PreparedStatement pst = conn.prepareStatement("select ifnull(max(pairNo),-1) from tb_stockmaster where pairStock = '"+stockMasterDO.getPktCode() +"'");
			ResultSet rs = pst.executeQuery();
			boolean prePkt = false;
			if(rs.next() && rs.getDouble(1) > -1d){
				stockMasterDO.setPairNo(rs.getDouble(1));
			}else{
				if(!rs.wasNull() && rs.getDouble(1) == -1d)
					prePkt = true;
				
				double pairCnt = 0;
				pst = conn.prepareStatement("select max(pairNo) from tb_stockmaster where pairNo is not null and pktCode not in ('"+stockMasterDO.getPktCode()+"','"+stockMasterDO.getPairStock()+"')");
				rs = pst.executeQuery();
				if(rs.next())
					pairCnt = rs.getDouble(1)+1;
				else
					pairCnt = 1;
				stockMasterDO.setPairNo(pairCnt);
				if(prePkt){
					pst = conn.prepareStatement("update tb_stockmaster set isMatched=1, pairStock = '"+stockMasterDO.getPktCode()+"', pairNo = "+pairCnt+" where pktCode = '"+stockMasterDO.getPairStock() +"'");
					pst.executeUpdate();
				}
					
			}
		}
	}
	public String getQueryVal(String elem,StockPRPDO sp) throws Exception{
			
			Field f = StockPRPDO.class.getDeclaredField(elem);
			
			f.setAccessible(true);
			Object val = f.get(sp); 
			if(val == null )
				return " null";
			else if(NumberUtils.isNumber(val.toString()))
				return " "+val.toString() ;
			else
				return " '"+val.toString()+"'";
	}
	public String getQueryVal(String elem,StockMasterDO sm) throws Exception{
		
		
		Field f = StockMasterDO.class.getDeclaredField(elem);
		
		f.setAccessible(true);
		Object val = f.get(sm); 

		if(val == null )
			return " null";
		else if(NumberUtils.isNumber(val.toString()))
			return " "+val.toString().replaceAll("%", "") ;
		else
			return " '"+val.toString().replaceAll("%", "")+"'";
}
	
	public int updateRapRates(StockMasterDO sm,StockPRPDO sp, Connection conn,Integer status) throws SQLException{
		
		int i=0;
		if(!StringUtils.isEmpty(sm.getEditPktCode())){
			sm.setPktCode(sm.getEditPktCode());
		}
		
		List<String> pktLst =new ArrayList<String>();
		PreparedStatement pst = conn.prepareStatement("insert into tb_pktupload (pktCode) value (?)");
		
		pst.setString(1, sm.getPktCode());
		int arr[] = pst.executeBatch();
		
		String qryPart= "";
		if(sp.getGRPID()==1l){
			qryPart = " s.RapPrice  = rp.price, s.Rate = ROUND(rp.Price *(1+ (s.rap/100)))," ;
		}
		
		String sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id inner join "+
					" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
					" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
					" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END) set "+qryPart+
					"sp.RapPriceLab  = rp.price , sp.rateLab = ROUND(rp.Price *(1+ (s.rap/100))) where s.pktCode  = ? and s.rap is not null and sp.grpid = "+sp.getGRPID() ;
		if(sm.getRap() == null){
			
			if(sp.getGRPID()==1l){
				qryPart = "s.RapPrice  = rp.price , " +
				"s.Rap = ROUND((((s.Rate*100)/rp.price)-100),2)," ;
			}
			 sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id inner join "+
			" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
			" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
			" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END) set "+qryPart+
			"sp.rapPriceLab =rp.price,sp.rapLab=ROUND((((s.Rate*100)/rp.price)-100),2) where s.pktCode  = ? and s.Rate is not null and sp.grpid = "+sp.getGRPID() ;
		}
		pst = conn.prepareStatement(sql);
		pst.setString(1, sm.getPktCode());
		i = pst.executeUpdate();
		logger.debug("$$$$$$$$$$$ updates status =1 count "+sql +" "+i);
		return i;
	}
	//not req anymore
	public int updateStockStatus(List<StockMasterDO> sm, Connection conn) throws SQLException{
		PreparedStatement pst =null;
		pst = conn.prepareStatement("delete from tb_pktupload ");
		pst.executeUpdate();
		
		int i=0;
		List<String> pktLst =new ArrayList<String>();
		pst = conn.prepareStatement("insert into tb_pktupload (pktCode) value (?)");
		
		for (int j = 0; j < sm.size(); j++) {
			pktLst.add(sm.get(j).getPktCode());
			pst.setString(1, sm.get(j).getPktCode());
			pst.addBatch();
		}
		int arr[] = pst.executeBatch();
		
		String sql = "update tb_stockmaster set status = 4 where PktCode not in (select distinct pktcode from tb_pktupload)";
		pst = conn.prepareStatement(sql);
		i = pst.executeUpdate();
		logger.debug("$$$$$$$$$$$ updates status =4 count"+i);
		return i;
	}
	
	public int deleteSold() throws Exception{
		Connection conn =dataSource.getConnection();
		try{
			PreparedStatement pst =null;
			String sql = "delete from tb_stockmaster where PktCode not in (select distinct pktcode from tb_pktupload)";
			pst = conn.prepareStatement(sql);
			int i = pst.executeUpdate();
			
			sql = "delete from tb_stockprp where pktid not in (select distinct id from tb_stockmaster)";
			pst = conn.prepareStatement(sql);
			i = pst.executeUpdate();
			
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
	}
	public void insertPurchaseEntry(List<StockMasterDO> sm,List<StockPRPDO> sp,PurchaseMaster pm, UserSession us) throws Exception{
		Connection conn =dataSource.getConnection();
		try{
			insertPurchaseEntry(sm,sp,pm,us,conn);
			logger.debug("$$$$$$$$$$$$$$$$$$$$"+sm.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
	}
	public void insertPurchaseEntry(List<StockMasterDO> sm,List<StockPRPDO> sp,PurchaseMaster pm, UserSession us, Connection conn) throws Exception{
		PreparedStatement pst =null;
		//Make GL Entry
		Integer glTransNo = 0;
		try {
			glTransNo = this.getMemoService().addPurchaseTrf(Constants.PARTY_TYPE_VENDOR, pm.getVendorId(), genericService.getSysPref(Constants.default_inventory_act), 
					pm.getPurchaseDate(), new BigDecimal(pm.getAmount()), "", new BigDecimal(pm.getExRate()), us.getPartyAccId(), 
					pm.getBillNo(), Constants.STATUS_PERFORMED_INSERT, -1, us.getCurrency(), pm.getCurrency());
		} catch (Exception e) {
			logger.debug("Error in inserting GL");
			e.printStackTrace();
			throw new  Exception("ERROR in GL entry");
		}
		int i=0;
		String sql = "insert into tb_purchasemaster (vendorId, comments, userId, billNo, purchaseDate, dueDate, status, amount, tax," +
							" expenses, paymentTerm,currency,exrate, glTransNo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, pm.getVendorId());
		pst.setString(2, pm.getComments());
		pst.setInt(3, us.getUserId());
		pst.setString(4, pm.getBillNo());
		pst.setString(5, pm.getPurchaseDate());
		pst.setString(6, pm.getDueDate());
		pst.setInt(7, 1);
		pst.setDouble(8, pm.getAmount());
		pst.setDouble( 9, pm.getTax());
		pst.setDouble( 10, pm.getExpenses());
		pst.setString( 11, pm.getPaymentTerm());
		pst.setString( 12, pm.getCurrency());
		pst.setDouble( 13, pm.getExRate());
		pst.setInt( 14, glTransNo);
		
		int z = pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		int purId = 0;
		if(rs.next())
			purId = rs.getInt(1);
		logger.debug(z+"   $$$$$$$$$$$$$$$$$$$$ in purchase entry    "+purId);
		
		
		pst = conn.prepareStatement("insert into tb_purchaseDetails (purchaseId, pktCode, rate,  wt, totalrate, finalrate, fixedexp, percentexp) " +
		"values (?, ?, ?, ?, ?, ?, ?, ?)");
		StockMasterDO smo;
		StockPRPDO spo;
		for (int j = 0; j < sm.size(); j++) {
			smo = new StockMasterDO();
			spo= new StockPRPDO();
			smo = sm.get(j);
			spo= sp.get(j);
			
			Double dBaserate = smo.getBaseRate()!=null?smo.getBaseRate():0;
			Double dCTS = spo.getCTS();
			pst.setInt(1, purId);
			pst.setString(2,  smo.getPktCode());
			pst.setDouble(3, dBaserate);
			pst.setDouble(4, dCTS);			
			pst.setDouble(5, dBaserate * dCTS);
			pst.setDouble(6, dBaserate * dCTS);//TODO currently 'finalrate' is kept the same 'totalrate'...also 'fixedexp' and 'percentexp' are set to 0...to be changed later
			pst.setDouble(7,0);
			pst.setDouble(8,0);
			pst.addBatch();
		}
		int arr[] = pst.executeBatch();
		
		/*String sqlStored="call glEntryInsert(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Object [] params = new Object[9];
		params[0]=1;
		params[1]=2;
		params[2]=pm.getPurchaseDate();
		params[3]=2;
		params[4]=1;
		params[5]=2;
		params[6]=1;
		params[7]=2;
		params[8]=1;
		try{
			this.memoService.storedProcedureCall(sqlStored,params);
		}catch(Exception e){
			e.printStackTrace();
		}
				
		//TODO
		//add gl entry for stock inventory
	//	this.getMemoService().addSuppTran(type, transNo, vendorId, vendorRef, ref, dsc, date, amount, userId, exRate)
		//add gl entry for stock tax
		*
		*
		*/
		
		
	}
	
		public void insertLab(List<String> doPropertyList ,Map<String, Object> pktDetailsMap,String pktCode, int defaultLab) throws Exception{
			
			Connection conn = dataSource.getConnection();
			PreparedStatement pst =null;
			try{
				Long pktId = getPktIdfromPktCode(pktCode);
				
				double d = 0d;
				if(pktDetailsMap.containsKey("LAB_so") ){
						d = Double.parseDouble(pktDetailsMap.get("LAB_so").toString());
				}else{
					d = 9999d;
				}
				if(defaultLab == 1){
					String upSql = " update into tb_stockprp set grpid = ifnull(lab_so, 9999) where  pktId = "+pktId;
					pst = conn.prepareStatement(upSql);
					pst.executeUpdate();
					d= 1;
				}
				
				String sql = "insert into tb_stockprp (pktid, grpid " ;
				for (int i = 0; i < doPropertyList.size(); i++) {
					sql += ", "+ doPropertyList.get(i) ;
				}
				sql+= ") values ("+pktId+", "+d;
				for (int i = 0; i < doPropertyList.size(); i++) {
					if(pktDetailsMap.containsKey(doPropertyList.get(i))&& !StringUtils.isEmpty(pktDetailsMap.get(doPropertyList.get(i))+"")){
						sql += ", '"+ pktDetailsMap.get(doPropertyList.get(i))+"'";
					}
					else{
						sql += ",null";
					}
				}
				sql+= ") ";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
			finally{
				try {
					conn.close();
				} catch (SQLException e) {
					conn =null;
					logger.error("ERROR IN CLOSE CONN",e);
				}
			}
		}
		
		public void editLab(List<String> doPropertyList , Map<String, Object> pktDetailsMap,String pktCode, int grp, int defaultLab) throws Exception{
			
			Connection conn = dataSource.getConnection();
			PreparedStatement pst =null;
			try{
			Long pktId = getPktIdfromPktCode(pktCode);
			int grpId = 9999;	
				
			if(defaultLab == 1){
				String upSql = " update tb_stockprp set grpid = ifnull(lab_so, 9999) where  pktId = "+pktId+" and grpid <> "+grp;
				pst = conn.prepareStatement(upSql);
				pst.executeUpdate();
				grpId = 1;
			}else{
				grpId = grp;
			}
				
			String sql = "update tb_stockprp set  pktid = "+pktId+", grpid =" +grpId;
						
			for (int i = 0; i < doPropertyList.size(); i++) {
				if(pktDetailsMap.containsKey(doPropertyList.get(i)) && !StringUtils.isEmpty(pktDetailsMap.get(doPropertyList.get(i))+"")){
					sql += " , "+doPropertyList.get(i) +" =  '"+ pktDetailsMap.get(doPropertyList.get(i))+"'";
				}else{
					sql += " , "+doPropertyList.get(i) +" =  null ";
				}
			}
			sql+= " where pktId = "+pktId+" and grpid = "+grp;
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		}
		
		public Long getPktIdfromPktCode(String pktCode) throws Exception{
			Connection conn = dataSource.getConnection();
		try{
			String sql = "select id from tb_stockmaster where pktCode = "+pktCode;
			PreparedStatement pst =null;
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			Long pktId = 0l;
			if(rs.next()){
				pktId = rs.getLong(1);
			}
			return pktId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				conn =null;
				logger.error("ERROR IN CLOSE CONN",e);
			}
		}
		}
		
		public int updatePacketId(String pktId ,String editPktCode)throws Exception{
			Connection conn =dataSource.getConnection();
			String sql= "update tb_stockmaster set PktCode = ? where PktCode =?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,editPktCode);
			pst.setString(2, pktId);
			return pst.executeUpdate();
		}
			
}

package com.basync.b2b.crm.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.basync.b2b.conf.Constants;
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
import com.basync.b2b.crm.data.PurchaseDetailsRowExtract;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.Dashboard;
//import com.basync.b2b.crm.data.rowExtract.AccountDataExtractor;
import com.basync.b2b.crm.data.rowExtract.AngadiaMasterRowExtract;
import com.basync.b2b.crm.data.rowExtract.BankAccountRowMapper;
import com.basync.b2b.crm.data.rowExtract.CurrencyRowMapper;
import com.basync.b2b.crm.data.rowExtract.GeneralIdNameStatusEtcRowMapper;
import com.basync.b2b.crm.data.rowExtract.GlAccRowMapper;
import com.basync.b2b.crm.data.rowExtract.GlRepDataRowExtract;
import com.basync.b2b.crm.data.rowExtract.GoodCostDataExtractor;
import com.basync.b2b.crm.data.rowExtract.InvoiceReportRowExtract;
import com.basync.b2b.crm.data.rowExtract.JQGridMemoRowMapper;
import com.basync.b2b.crm.data.rowExtract.OrderMasterListRowMapper;
import com.basync.b2b.crm.data.rowExtract.PacketLabReturnRowExtract;
import com.basync.b2b.crm.data.rowExtract.PartyOSRowExtract;
import com.basync.b2b.crm.data.rowExtract.PaymentRowExtract;
import com.basync.b2b.crm.data.rowExtract.PercentageDataExtractor;
import com.basync.b2b.crm.data.rowExtract.PurchaseMasterRowExtract;
import com.basync.b2b.crm.data.rowExtract.QueryCodeDescriptionRowExtract;
import com.basync.b2b.crm.data.rowExtract.StockReportRowExtract;
import com.basync.b2b.dao.CustomerInfo;
import com.basync.b2b.data.rowExtract.DashboardRowExtract;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.data.rowExtract.AccountReceivableExtractor;
import com.basync.b2b.data.rowExtract.BalanceAccountExtractor;
import com.basync.b2b.data.rowExtract.BrokerageExtractor;
import com.basync.b2b.data.rowExtract.CustomerBalanceExtrator;
import com.basync.b2b.data.rowExtract.CustomerInformationExtract;
import com.basync.b2b.data.rowExtract.InvoiceMasterRowExtract;
import com.basync.b2b.data.rowExtract.OrderPktsRowMapper;
import com.basync.b2b.data.rowExtract.PaymentReportExtractor;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.data.rowExtract.SupplierBalanceExtractor;
import com.basync.b2b.data.rowExtract.TaxReportExtractor;
import com.basync.b2b.service.impl.BaseService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.NumberUtils;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.sun.tools.jxc.apt.Const;

/**
 * @author gaurang
 *
 */
public class MemoServiceImpl extends BaseService implements IMemoService {


private  HibernateTemplate hibernateTemplate;

private  IGenericService genericService;

 

public String prpRate = " s.rate "; 
public String prpRap = " s.rap "; 
/**
 * @param genericService the genericService to set
 */
public void setGenericService(IGenericService genericService) {
	this.genericService = genericService;
}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPrpStr(java.util.List, java.lang.Double, java.lang.Double)
	 */
	public String getPrpStr(List<PrpData> list, Double term,Double factor,boolean isMemo, Integer rapChange,UserSession us) throws Exception {
		StringBuilder prpStr= new StringBuilder();
		for(int i=0; i<list.size();i++){
			String prp =list.get(i).getPrp();
			if(list.get(i).getPrp().equalsIgnoreCase("Rate")){
				if(!isMemo){
					if(rapChange ==null || rapChange ==-1){
						prpRate = "s."+list.get(i).getPrp()+" * "+term;
						prp = prpRate +" as RATE ";
						if(factor!= null && factor > 0){
							prpRate = "s."+list.get(i).getPrp()+" * "+term+" * "+String.valueOf(factor);
							prp += ","+ prpRate + " as RATE ";
						}
					}else{
						prpRate = "ifnull((s.rapPrice * (1 + ((s.rap-("+rapChange+"))/100))), s.rate)";
						prp = prpRate + " as RATE ";
						if(factor!= null && factor > 0){
							prpRate = " ifnull((s.rapPrice * (1 - ((s.rap-("+rapChange+"))/100))), s.rate) * "+String.valueOf(factor) ;
							prp = ", "+ prpRate + "  as RATE ";
						}
					}
					
				}else{
					prpRate = "od."+list.get(i).getPrp()+" * "+term;
					prp = prpRate + " as RATE ";
					
					if(factor!= null && factor > 0){
						prpRate = "od."+list.get(i).getPrp()+" * "+term+" * "+String.valueOf(factor);
						prp = ","+prpRate + " as RATE ";
					}
				}
				if(!us.getCurrency().equalsIgnoreCase("USD")){
					prp += ","+prpRate + " as RATE_"+us.getCurrency();
				}
			}else if(list.get(i).getPrp().equalsIgnoreCase("Rap")){
				if(!isMemo){
					if(rapChange ==null || rapChange ==-1){
							prp = "(100 - ("+term+" * (100 + s."+list.get(i).getPrp()+"))) *-1 " ;
						}
					else{
							prp = " (s."+list.get(i).getPrp()+"-("+rapChange+")) ";
						}
				}else{
					prp = " (100 - od.rate/s.rapPrice *100) *-1 ";
				}
				prpRap = prp;
				prp += "as "+list.get(i).getPrp();
			}else if(list.get(i).getPrp().equalsIgnoreCase("cts")){
				if(isMemo){
					prp = "od."+prp;
				}else{
					prp = "sp."+prp;
				}
			}
			else if(list.get(i).getPrp().equalsIgnoreCase("pcs")){
				if(isMemo){
					prp = "od."+prp;
				}else{
					prp = "s."+prp;
				}
			}
			else if(list.get(i).getPrp().equalsIgnoreCase("pktCode")){
				prp = "s."+prp;
				if(isMemo){
					prp+= ", om.id memoId, osm.statusCode memoPktStatus";
				}
			}
			else if(list.get(i).getPrp().equalsIgnoreCase("status")){
				prp = "sm.statusCode status";
			}
			else if(list.get(i).getPrp().equalsIgnoreCase("partyAccId")){
				prp = "s.partyAccId";
			}
			//For mix if
			else if(prp.equalsIgnoreCase("SH") || prp.equalsIgnoreCase("PU") ||prp.equalsIgnoreCase("C")){
				prp =  "if(s.pcs =1, "+ prp+", concat("+ prp+"Fr,'-',"+ prp+"To)) as "+ prp;
			}
			else if(prp.equalsIgnoreCase("ACTS") ){
				prp =  "sp.CTS as "+ prp;
			}
			else if(prp.equalsIgnoreCase("REJ") ){
				prp =  "0 as "+ prp;
			}
			else if(prp.equalsIgnoreCase("LAB") ){
				prp =  "sp.LAB as "+ prp;
			}
			else if(StringUtils.isContainsSpecialChar(prp)){
				prp =  " CONCAT("+ StringUtils.toString(StringUtils.splitSpecialChar(prp),",'*',")+") as "+StringUtils.toString(StringUtils.splitSpecialChar(prp),"");
			}else if(prp.equalsIgnoreCase("total")){
				prp = "("+prpRate+"*sp.cts) total ";
				
				if(!us.getCurrency().equalsIgnoreCase("USD")){
					prp += ", ("+prpRate+"*sp.cts)  as total_"+us.getCurrency();
				}
			}
			
			
			
			if (i ==0){
				prpStr.append(prp);
			}else{
				if(!StringUtils.isEmpty(prp)) {
					prpStr.append(", ");
					prpStr.append(prp);
				}
			}		
		}
		if(!prpStr.toString().contains("sm.statusCode status")){
			prpStr.append (", sm.statusCode status");
		}
		if(!prpStr.toString().contains("certId")){
			prpStr.append (", s.certId");
		}
		if(!prpStr.toString().contains("s.status statusId")){
			prpStr.append (", s.status statusId");
		}

		
		logger.debug("PRPSTRING =="+prpStr.toString());
		return prpStr.toString();
	
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPrpStrMap(java.util.List, java.lang.Double, java.lang.Double,java.lang.boolean)
	 */
	public String getPrpStrMap(List<FileMap> list, Double term, Double factor, Integer rapChange, boolean rapDiscCol,UserSession us)
			throws Exception {
		StringBuilder prpStr= new StringBuilder();
		int fncIdx =0;
		for(int i=0; i<list.size();i++){
			if(list.get(i).getColumnName()!=null && list.get(i).getColumnName().equals("FNC")){
		    	fncIdx=i;
			}
		}
		
		for(int i=0; i<list.size();i++){
			if(list.get(i).getFileId() == 0)
				break;
			String prp =list.get(i).getColumnName();
			if(prp==null){
				prp = " '' ";
			}else if(prp.equalsIgnoreCase("Rate")){
				if(rapDiscCol){
					continue;
				}
					if(rapChange ==null || rapChange ==-1){
						prpRate = "s."+prp+" * "+term ;
						prp =  prpRate+" as RATE ";
						if(factor!= null && factor > 0){
							prpRate = "s."+prp+" * "+term+" * "+String.valueOf(factor);
							prp = ", "+ prpRate+" as RATE ";
						}
					}else{
						prpRate = "ifnull((s.rapPrice * (1 + ((s.rap-("+rapChange+"))/100))),s.rate)";
						prp = prpRate +" as RATE ";
						if(factor!= null && factor > 0){
							prpRate = "if((s.rapPrice * (1 - ((s.rap-("+rapChange+"))/100))),s.rate) * "+String.valueOf(factor) ;
							prp = ", "+prpRate +"  as RATE ";
						}
					}
				
			}else if(prp.equalsIgnoreCase("baseRate")||prp.equalsIgnoreCase("pktCode")||prp.equalsIgnoreCase("remark")){
				prp = "s."+prp;
			}
			else if(prp.equalsIgnoreCase("Rap")){
				if(rapChange ==null || rapChange ==-1){
					prpRap = " (100 - ("+term+" * (100 + s.rap)))*-1 ";
					prp = prpRap + "as rap";;
				}
				else{
					prpRap = " (s.rap -("+rapChange+"))  ";
					prp = prpRap + "as rap";;
				}
				if(rapDiscCol){
					prpRate = "(100 - (100 + s.rap))";
					prp += " , "+ prpRate +" as RATE ";
				}else {
					prp += "";
				}
				
			}else if(prp.equalsIgnoreCase("status")){
				prp = "sm.statusCode status";
			}
			else if(prp.equalsIgnoreCase("partyAccId")){
				prp = "s.partyAccId";
			}
			//For mix if
			else if(prp.equalsIgnoreCase("SH") || prp.equalsIgnoreCase("PU") ||prp.equalsIgnoreCase("C")){
				prp =  "if(s.pcs =1, "+ prp+", concat("+ prp+"Fr,'-',"+ prp+"To)) as "+ prp;
			}
			else if(prp.equalsIgnoreCase("ACTS") ){
				prp =  "sp.CTS as "+ prp;
			}
			else if(prp.equalsIgnoreCase("REJ") ){
				prp =  "0 as "+ prp;
			}
			else if(prp.equalsIgnoreCase("C")){
				if(fncIdx > 0){
					prp = "sp.C";
				}else{
					prp = "ifnull(sp.c, concat(sp.FNC, ' ',sp.FNCI,' ',sp.FNCO)) as C ";
				}
			}
			else if(StringUtils.isContainsSpecialChar(prp)){
				prp =  " CONCAT("+ StringUtils.toString(StringUtils.splitSpecialChar(prp),",'*',")+") as "+StringUtils.toString(StringUtils.splitSpecialChar(prp),"");
			}else if(prp.equalsIgnoreCase("total")){
				if(rapDiscCol){
					prp = "'' total ";
				}
				prp = "("+prpRate+"*sp.cts) total ";
			}
				
			if (i ==0){
				prpStr.append(prp);
			}else{
				if(prp != null) {
					if(!StringUtils.isEmpty(prp)){
						prpStr.append(", ");
						prpStr.append(prp);
					}
				}
			}		
		}
		
		if(!prpStr.toString().contains("sm.statusCode status")){
			prpStr.append (", sm.statusCode status");
		}
		if(!prpStr.toString().contains("certId")){
			prpStr.append (", s.certId");
		}
		if(!prpStr.toString().contains("s.status statusId")){
			prpStr.append (" , s.status statusId");
		}

		logger.debug("PRPSTRING =="+prpStr.toString());
		return prpStr.toString();
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoStock(java.lang.Double, java.util.List, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double,int)
	 */
	public JQGridContainer getMemoStock(Double term, List<PrpData> prpLst,
			String whereClause, int userId, int page, int pageSize,
			String srtIndex, String srtType, String currency, Double factor,Integer rapDiff, boolean isParcel,UserSession us)
			throws Exception {
		
		String prpString = "";
		if(!StringUtils.isEmpty(currency))
			prpString = getPrpStr(prpLst, term, factor,false,rapDiff,us);
		else
			prpString = getPrpStr(prpLst, term, null,false,rapDiff,us);
		//TODO
		//sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		return getQueryResult(term,prpString, whereClause,userId,page,pageSize,srtIndex,srtType, false, isParcel);
	}


	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoStock(java.lang.Double, java.util.List, java.util.List, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double,int)
	 */
	public JQGridContainer getMemoStock(Double term, List<PrpData> prpLst,
			List<String> whereClauseList, int userId, int page, int pageSize,
			String srtIndex, String srtType, String currency, Double factor,Integer rapDiff,boolean isParcel,UserSession us)
			throws Exception {
		CommonUtil.getInstance();
		String prpString = "";
		if(!StringUtils.isEmpty(currency))
			prpString = getPrpStr(prpLst, term, factor,false,rapDiff,us);
		else
			prpString = getPrpStr(prpLst, term, null,false,rapDiff,us);
		//TODO
		return getQueryResult(term,prpString, whereClauseList,userId,page,pageSize,srtIndex,srtType,false, isParcel);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoStockByMemoId(java.lang.Double, java.util.List, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double)
	 */
	public JQGridContainer getMemoStockByMemoId(Double term,
			List<PrpData> prpLst, String whereClause, int userId, int page,
			int pageSize, String srtIndex, String srtType, String currency,
			Double factor, boolean isParcel,UserSession us) throws Exception {
		
		String prpString = "";
		if(!StringUtils.isEmpty(currency))
			prpString = getPrpStr(prpLst, term, factor,true,null,us);
		else
			prpString = getPrpStr(prpLst, term, null,true,null,us);
		
		//sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		return getQueryResult(term, prpString, whereClause,userId,page,pageSize,srtIndex,srtType, true, isParcel);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getTotals(java.lang.Double, java.lang.Object[], java.lang.String)
	 */
	public Map getTotals(Object[] param, String sql) throws Exception{
		return this.getJdbcDao().queryForMap(sql, param);
	}
	
	
	/*
	 * 
	 * 
	 */
	public Map getTotals(Double term,String whereClause,
			 Integer userId,boolean isMemo,boolean isParcel) throws Exception {
		CommonUtil.getInstance();
		StringBuilder sqlStr = null; 	
		
		String finalClause = whereClause.replaceAll("s.RAP", prpRap);
		finalClause = finalClause.replaceAll("s.RATE", prpRate);
		//finalClause += " and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 ";
		 if(!isParcel){
				finalClause += " and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 ";
			}
			else{
				finalClause += " and s.pcs <> 1 ";
			}

		if(!isMemo){
			sqlStr = 	new StringBuilder (" select count(*) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP, (sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE, sum(sp.cts) CTS, sum("+prpRate+"*sp.cts) total, s.partyAccId  " )	
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "+userId+"  and tm.removeDateTime is null ")
				.append(" where s.status in (1,2) ")
				.append(finalClause);
		}else{
		
			sqlStr = 	new StringBuilder (" select count(*) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP, (sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE, sum(sp.cts) cts, sum("+prpRate+"*sp.cts) total " )	
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" inner join tb_orderdetail od on  od.pktId = s.id ")
				.append(" inner join tb_ordermaster om on  om.id = od.orderid ")
				.append(" where om.status in (1, 2) and od.status in (1, 2) and od.returnDateTime is null and (s.status <> 9 or s.status <> 1)  ")
				.append(finalClause);
		}
		
		logger.debug(" clause"+finalClause);	
		
		Map userData = new HashMap();
		userData = getTotals(null, sqlStr.toString());
		return userData;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getJQGridColModel(java.util.List, int)
	 */
	public String[] getJQGridColModel(List<PrpData> prpLst, int stage,UserSession us) {
			
			StringBuilder headers = new StringBuilder();
			StringBuilder colModel = new StringBuilder();
			/*colModel.append("{name:'pktCode',index:'pktCode', width:65, formatter:getDGCLink}");
			headers.append( "'Packet No.' ");
			colModel.append(", {name:'status',index:'status', width:65}");
			headers.append( ", 'Status' ");
			headers.append( ",'Pcs' ");
				*/

			for(int i =0;i< prpLst.size();i++){
				PrpData pd= prpLst.get(i);
				if(i > 0){
					colModel.append(", ");
					headers.append(", ");
				}
				headers.append("'").append(pd.getWebhdr()+"'");
				Integer check=Integer.parseInt(us.getUserActivityMap().get(Constants.SELL_PRICE_EDITABLE));
				if(pd.getPrp().equalsIgnoreCase("cts")){
					if(check==1){
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", editable: true, editrules:{number:true}}");
					}else{
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", editable: false, editrules:{number:true}}");
					}
				}else if(pd.getPrp().equalsIgnoreCase("lab")){
					colModel.append(" {name:'lab',index:'lab', width:"+ pd.getWidth()+", formatter:getCertLink}");
				}else if(pd.getPrp().equalsIgnoreCase("rate")){
					if(check==1){
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: true,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
					}else{
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: false,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
					}
					if(!us.getCurrency().equalsIgnoreCase("USD")){
						if(check==1){
						colModel.append(", {name:'"+ pd.getPrp()+"_LOCAL',index:'"+ pd.getPrp()+"_"+us.getCurrency()+"', editable: true,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
						headers.append(",'").append(pd.getWebhdr()+"("+us.getCurrency()+")'");
						}else{
							colModel.append(", {name:'"+ pd.getPrp()+"_LOCAL',index:'"+ pd.getPrp()+"_"+us.getCurrency()+"', editable: false,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
							headers.append(",'").append(pd.getWebhdr()+"("+us.getCurrency()+")'");
						}
					}
				}
				else if(pd.getPrp().equalsIgnoreCase("rap")){
					if(check==1){
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: true ,editrules:{number:true, custom:true, custom_func:priceCheckRap}, width:"+ pd.getWidth()+", formatter:getRap}");
					}else{
						colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: false ,editrules:{number:true, custom:true, custom_func:priceCheckRap}, width:"+ pd.getWidth()+", formatter:getRap}");
					}
				}
				else if(pd.getPrp().equalsIgnoreCase("pcs")){
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: true ,editrules:{number:true, custom:true, custom_func:priceCheckRap}, width:"+ pd.getWidth()+", formatter:getRap}");
				}
				else if(pd.getPrp().equalsIgnoreCase("rej")){
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', editable: true ,editrules:{number:true, custom:true, custom_func:priceCheckRap}, width:"+ pd.getWidth()+", formatter:getRap}");
				}
				else if(pd.getPrp().equalsIgnoreCase("pktCode")){
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getDGCLink}");
					if(stage == Constants.STAGE_MEMO){
						colModel.append(", {name:'Id',index:'om.Id', width:65}");
						headers.append( ", 'Memo No.' ");
						colModel.append(", {name:'statusCode',index:'osm.statusCode', width:65}");
						headers.append( ", 'Memo Status' ");
					}
				
				}
				else if(pd.getPrp().equalsIgnoreCase("total")){
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRate}");
					if(!us.getCurrency().equalsIgnoreCase("USD")){
						if(check==1){
							colModel.append(", {name:'"+ pd.getPrp()+"_LOCAL',index:'"+ pd.getPrp()+"_"+us.getCurrency()+"', editable: true,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
							headers.append(",'").append(pd.getWebhdr()+"("+us.getCurrency()+")'");
						}else{
							colModel.append(", {name:'"+ pd.getPrp()+"_LOCAL',index:'"+ pd.getPrp()+"_"+us.getCurrency()+"', editable: false,editrules:{number:true, custom:true, custom_func:priceCheck}, width:"+ pd.getWidth()+", formatter:getRate }");
							headers.append(",'").append(pd.getWebhdr()+"("+us.getCurrency()+")'");
						}
					}
				}
				else if(pd.getPrp().equalsIgnoreCase("partyAccId")){
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getPartyAcc}");
				}
				else{
					colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+"}");
				}
			}
			
			/*headers.append(", 'Root'");
			colModel.append(", {name:'rootPkt',index:'rootPkt', width:70}");
			
			headers.append(", 'Pair pkt'");
			colModel.append(", {name:'pairStock',index:'pairStock', width:70}");
			
			headers.append(", 'Total'");
			colModel.append(", {name:'total',index:'total', width:70, formatter:getRate }");
			
			headers.append(", 'Location'");
			colModel.append(", {name:'partyAccId',index:'partyAccId', width:70, formatter:getPartyAcc}");*/
			
			String[] gridData = new String[2];
			gridData[0]=headers.toString();
			gridData[1]=colModel.toString();
			
			return gridData;
		}
	
	
	/*
	 (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getJQGridColModel(java.util.List, java.lang.String, java.lang.Double)
	 
	public String[] getJQGridColModel(List<PrpData> prpLst, String currency, Double factor) {
		
		StringBuilder headers = new StringBuilder();
		StringBuilder colModel = new StringBuilder();
		colModel.append("{name:'pktCode',index:'pktCode', width:65, formatter:getCertLink}");
		headers.append( "'Packet No.' ");
		colModel.append(", {name:'status',index:'status', width:65}");
		headers.append( ",'Status' ");
		
		colModel.append(", {name:'pcs',index:'pcs', width:65}");
		headers.append( ",'Pcs' ");
		
		for(int i =0;i< prpLst.size();i++){
			PrpData pd= prpLst.get(i);
			headers.append(", '").append(pd.getWebhdr()+"'");
			if(pd.getPrp().equalsIgnoreCase("lab")){
				colModel.append(", {name:'lab',index:'lab', width:"+ pd.getWidth()+", formatter:getCertLink}");
			}else if(pd.getPrp().equalsIgnoreCase("rate")){
				colModel.append(", {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRate }");
				headers.append(", '").append("Rate("+currency+")'");
				colModel.append(", {name:'rate',index:'rate', width:70,sortable:false, formatter:getRate }");
			}
			else if(pd.getPrp().equalsIgnoreCase("rap")){
				colModel.append(", {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRap}");
			}
			else{
				colModel.append(", {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+"}");
			}
		}


		
		headers.append(", 'Root'");
		colModel.append(", {name:'rootPkt',index:'rootPkt', width:70}");

		headers.append(", 'Pair pkt'");
		colModel.append(", {name:'pairStock',index:'pairStock', width:70}");

		headers.append(", 'Total'");
		colModel.append(", {name:'total',index:'total', width:70, formatter:getRate }");
		
		String[] gridData = new String[2];
		gridData[0]=headers.toString();
		gridData[1]=colModel.toString();
		
		return gridData;
	}*/


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getStatus(java.lang.Integer)
	 */
	public List<QueryDescription> getStatus(Integer activeFlag) throws Exception {
		
		String sql = "select id, statusCode description from tb_statusMaster where activeFlag = ? order by id";
		Object param[] = new Object[1];
		param[0] = activeFlag;
		
		return this.getJdbcSlaveDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}
	
	
	public JQGridContainer getQueryResult(Double term,String prpString, String whereClause,
			 Integer userId,Integer page, Integer pageSize, String srtIndex,
				String srtType,boolean isMemo, boolean isParcel) throws Exception{
		CommonUtil.getInstance();
		StringBuilder sql = null;
		StringBuilder countSql = null;
		StringBuilder sqlStr = null; 	
		
		String finalClause = whereClause.replaceAll("s.RAP", prpRap);
		finalClause = finalClause.replaceAll("s.RATE", prpRate);
		
		String mixJoin = "";
		if(!isParcel){
			finalClause += " and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 ";
		}
		else{
			finalClause += " and s.pcs <> 1 ";
			mixJoin=" inner join tb_fixedStockPkt f on f.pktId = sp.pktId and sp.cts > if(f.fixedflag=0, 0, -1000000000) ";
		}
		if(!isMemo){
			 sql = new StringBuilder (" select s.id, s.certId, s.status statusId, "+ prpString + "" ) 
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "+userId+"  and tm.removeDateTime is null ")
				.append(" left join tb_partyAcc pa on pa.id = s.partyAccId "+mixJoin)
				.append(" where s.status in (1,2) ")
				.append(finalClause);
		
			 countSql = new StringBuilder (" select count(*) ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on  ")
				.append(" s.id = sp.pktId and grpid = 1 "+mixJoin+"where s.status in (1,2) ")
				.append(finalClause);
		
			sqlStr = 	new StringBuilder (" select count(*) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP, (sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE, sum(sp.cts) CTS, sum("+prpRate+"*sp.cts) total, s.partyAccId  " )	
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "+userId+"  and tm.removeDateTime is null "+mixJoin)
				.append(" where s.status in (1,2) ")
				.append(finalClause);
		}else{
		
		sql = new StringBuilder (" select s.id, s.certId,s.status statusId,   "+ prpString + "") 
			.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 inner join tb_statusMaster sm on sm.id = s.status ")
			.append(" inner join tb_orderdetail od on  od.pktId = s.id ")
			.append(" inner join tb_ordermaster om on  om.id = od.orderid ")
			.append(" inner join tb_orderStatusMaster osm on  osm.id = od.status ")
			.append(" left join tb_partyAcc pa on pa.id = s.partyAccId ")
			.append(" where om.status in (1, 2) and od.status in (1, 2) and od.returnDateTime is null and (s.status <> 9 or s.status <> 1 or s.pcs <> 1 ) ")
			.append(finalClause);
		
		countSql = new StringBuilder (" select count(*) ")
			.append(" from tb_stockmaster s inner join tb_stockprp sp on ")
			.append(" s.id = sp.pktId  and grpid = 1  ")
			.append(" inner join tb_orderdetail od on  od.pktId = s.id ")
			.append(" inner join tb_ordermaster om on  om.id = od.orderid ")
			.append(" where om.status in (1, 2) and od.status in (1, 2) and od.returnDateTime is null and (s.status <> 9 or s.status <> 1 or s.pcs <> 1 ) ")
			.append(finalClause);
		sqlStr = 	new StringBuilder (" select count(*) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP, (sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE, sum(sp.cts) cts, sum("+prpRate+"*sp.cts) total " )	
			.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
			.append(" inner join tb_orderdetail od on  od.pktId = s.id ")
			.append(" inner join tb_ordermaster om on  om.id = od.orderid ")
			.append(" where om.status in (1, 2) and od.status in (1, 2) and od.returnDateTime is null and (s.status <> 9 or s.status <> 1 or s.pcs <> 1 )  ")
			.append(finalClause);
		}
		
		logger.debug(" clause"+finalClause);	
		logger.debug(sql.toString());
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		JQGridContainer container = this.getJdbcDao().getGridList(sql.toString(),countSql.toString(), page, pageSize, null, new RowMapperResultSetExtractor(new JQGridMemoRowMapper()));
		
	
		Map userDate = new HashMap();
		userDate = getTotals(null, sqlStr.toString());
		container.setUserdata(userDate);
			
	return container;
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getQueryResult(java.util.List, java.lang.String, java.lang.Integer, java.lang.Double, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, boolean)
	 */
	public JQGridContainer getQueryResult( Double term,String prpString, List<String> whereClauseList,
			 Integer userId,Integer page, Integer pageSize, String srtIndex,
			String srtType,  boolean isMemo, boolean isParcel)
			throws Exception {
		
		StringBuilder sql = new StringBuilder ("select * from (");
		StringBuilder countSql = new StringBuilder ("select sum(count) from (" );
		StringBuilder sqlStr = 	new StringBuilder (" select sum(pktCode) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP,(sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE,  sum(cts) CTS, sum("+prpRate+"*cts) total from (");
	
		for (int i=0;i<whereClauseList.size();i++) {
			 String whereClause  = whereClauseList.get(i);
			 String finalClause = whereClause.replaceAll("s.RAP", prpRap);
			 String mixJoin = "";
			 finalClause = finalClause.replaceAll("s.RATE", prpRate);
			// finalClause += " and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 ";
			 if(!isParcel){
					finalClause += " and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 ";
				}
				else{
					finalClause += " and s.pcs <> 1 ";
					mixJoin=" inner join tb_fixedStockPkt f on f.pktId = sp.pktId and sp.cts > if(f.fixedflag = 0, 0, -1000000000)";
					
				}
			 
			if(i>0){
				 sql.append(" UNION ");
				 countSql.append(" UNION ");
				 sqlStr.append(" UNION ");
			 }
			 sql.append("( select s.id, s.certId, s.status statusId "+ prpString + "" ) 
			.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpid = 1 inner join tb_statusMaster sm on sm.id = s.status ")
			.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "+userId+"  and tm.removeDateTime is null ")
			.append(" left join tb_partyAcc pa on pa.id = s.partyAccId "+mixJoin)
			.append(" where s.status in (1,2) ")
			.append(finalClause);
			
			countSql.append(" select count(*) count")
			.append(" from tb_stockmaster s inner join tb_stockprp sp on ")
			.append(" s.id = sp.pktId  and grpid = 1 "+mixJoin+" where s.status in (1,2) ")
			.append(finalClause);
			
				
			logger.debug(" clause"+finalClause);	
			
			sqlStr.append(" select count(*) pktCode, ((sum(if(rapPrice>0,"+prpRate+",0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP,(sum("+prpRate+"*sp.CTS)/sum(sp.CTS)) RATE, sum(sp.CTS) CTS, sum("+prpRate+"*sp.CTS) total " )	
			.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpid = 1 ")
			.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "+userId+"  and tm.removeDateTime is null "+mixJoin)
			.append(" where s.status in (1,2) ")
			.append(finalClause);
			
			if(!StringUtils.isEmpty(srtIndex)){
				sql.append(" order by ").append(srtIndex).append(" ").append(srtType+") ");
			}
		}
		 sql.append(" ) s");
		 countSql.append(" ) s");
		 sqlStr.append(" ) s");
		
		logger.debug(sql.toString());
		Object[] param = null;
		JQGridContainer container = this.getJdbcDao().getGridList(sql.toString(),countSql.toString(), page, pageSize, param, new RowMapperResultSetExtractor(new JQGridMemoRowMapper()));
		
		Map userDate = new HashMap();
		userDate = getTotals( null, sqlStr.toString());
		container.setUserdata(userDate);
		
		//sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		return container;
	}


	public int memoReturnFull(String[] memoNos, Integer userId)
			throws Exception {
		
		
		String sql = "update tb_ordermaster om, tb_orderdetail od set om.status = 3 , od.status =3, od.returnDateTime = now() where om.id = od.orderId and om.id in ("+StringUtils.toString(memoNos, ",")+") and om.status = 2 " +
				"and od.status = 2 and od.returnDateTime is null";
		
		int z  = this.getJdbcDao().update(sql, null);
		
		if(z> 0 ){
			returnParcelDtl(new String[0], memoNos);
			//sql = "update tb_stockmaster sm, tb_orderdetail od  set sm.status = 1 where sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") " +
			//	"and od.status <> 3 and od.returnDateTime is null ";
			
			//Reprice and update status After return 
			String insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId) select sm.id, sm.rate, round((((100+sm.Rap)/100)*rp.price),0),now(),? " +
					"from tb_stockmaster sm, tb_rapPrices rp, tb_stockprp sp, tb_orderdetail od  where  " +
					"sm.id = sp.PKTID and GRPID = 1 and if(STRCMP(sp.SH_so, '10'),'ROUND','PEAR')  = rp.shape and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and if(highSize>10,20,highSize) " +
					" and sp.C = rp.Color and sp.PU = rp.Clarity and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") " ;
			
			Object[] param = new Object[1];
			param[0] = userId ;
			this.getJdbcDao().update(insertQ, param);
			
			sql = "update tb_stockmaster sm, tb_orderdetail od, tb_stockprp sp, tb_rapPrices rp  set sm.status = ?, sm.RapPrice  = (rp.price) , " +
					"sm.Rate = round((((100+sm.Rap)/100)*rp.price),0)," +
					" sp.RapPriceLab  = (rp.price) , " +
					" sp.RateLab = round((((100+sp.rapLab)/100)*rp.price),0) where " +
					" sm.id = sp.PKTID and GRPID = 1 and rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and if(highSize>10,20,highSize) " +
					" and rp.color = if(sp.C > 'M' ,'M',sp.C) and rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sm.rap is not null " ;
			
			param=new Object[1];
			param[0] = Constants.STATUS_TYPE_MA;
			
			int i  = this.getJdbcDao().update(sql, param);
			
			//other lab updates
			sql = "update tb_stockmaster sm, tb_orderdetail od, tb_stockprp sp, tb_rapPrices rp  set sp.RapPriceLab  = (rp.price) , " +
			"sp.RateLab = round((((100+sp.RapLab)/100)*rp.price),0) where " +
			" sm.id = sp.PKTID and GRPID <> 1 and rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and if(highSize>10,20,highSize) " +
			" and rp.color = if(sp.C > 'M' ,'M',sp.C) and rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sp.rapLab is not null " ;
			i  = this.getJdbcDao().update(sql, null);
			
			
			//Update status
			sql = "update tb_stockmaster sm inner join tb_orderdetail od on od.pktId = sm.id inner join tb_stockprp sp on sp.pktId = sm.id set sm.status = ?, sp.cts = if(sm.pcs <> 1,(sp.cts + od.cts),sp.cts)  where " +
				" od.orderId in ("+StringUtils.toString(memoNos, ",")+")  " ;
			param=new Object[1];
			param[0] = Constants.STATUS_TYPE_MA;
			this.getJdbcDao().update(sql, param);
			
			
			if(i >= 1){
				sql = "update tb_invoiceMaster i set i.invStatus = 'REJ' where (select count(*) from tb_orderdetail od where od.orderId in ("+StringUtils.toString(memoNos, ",")+") and od.status <> 3 )= 0 and i.memoOrderId in ("+StringUtils.toString(memoNos, ",")+")  ";
				this.getJdbcDao().update(sql, null);
				//TO Check for STATUS
				for (int j = 0; j < memoNos.length; j++) {
					Integer orderId = Integer.parseInt(memoNos[j]);
					updateMemoToInvoice(orderId);
				}
			}
		}
		return z;
	}

	public int memoReturnTrf(String[] pktIds, int UserId, int PartyAccId) throws Exception{
		String sql = "update tb_ordermaster om, tb_orderdetail od set om.status = 3 , od.status =3, od.returnDateTime = now() where om.id = od.orderId and om.id in ("+StringUtils.toString(pktIds, ",")+") and om.status = 2 " +
		"and od.status = 2 and od.returnDateTime is null";

		//update tb_stockmaster set partyAccId = ?  where pktId = ? and exists (select * from tb_ordermaster )
		
		
		//query for Partyaccid, if partyaccid = UserId
		//allow return...else return 0 
		int z = 0;
		z = this.getJdbcDao().update(sql, null);
		if(z > 0) {
			//query for 
			String sql1 = "select refpartyid from tb_ordermaster where id in ("+StringUtils.toString(pktIds, ",")+")";
			int trfPartyAccid = this.getJdbcDao().queryForInt(sql1, null);
			if (trfPartyAccid == PartyAccId) {
				String Updatesql = "update tb_stockmaster set partyaccid = ? where pktCode in (select pktid from tb_orderdetail where orderid = ("+StringUtils.toString(pktIds, ",")+"))";
				Object[] param = new Object[1];
				param[0] = PartyAccId ;
				this.getJdbcDao().update(Updatesql, param);
			}			
			//int trfPartyid = String sql = update tb_stockmaster set partyAccId = ?  where pktId = ? and exists (select  from tb_ordermaster )
		}
		return z;
	}
	
	public int memoReturnByPktNos(String[] pktNos, String[] memoNos,
			Integer userId) throws Exception {
		return memoReturnByPktNos(pktNos, memoNos, userId, Constants.STATUS_TYPE_MA);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#memoReturnByPktNos(java.util.List,,java.lang.Integer)
	 */
	public int memoReturnByPktNos(String[]  pktNos, String[] memoNos, Integer userId, Integer status)
			throws Exception {
		
		String sql = "update tb_orderdetail od set od.status =3, od.returnDateTime = now() where od.pktid in ("+StringUtils.toString(pktNos, ",")+")" +
					" and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and od.status = 2 and od.returnDateTime is null";
		
		int z  = this.getJdbcDao().update(sql, null);
		
		if(z > 0 ){
			returnParcelDtl(pktNos, memoNos);
			
			//Reprice and update status After return 
			String insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId) select sm.id, sm.rate, round((((100+sm.Rap)/100)*rp.price),0),now(),? " +
			"from tb_stockmaster sm, tb_rapPrices rp, tb_stockprp sp, tb_orderdetail od  where  " +
			"sm.id = sp.PKTID and GRPID = 1 and CAST((if(STRCMP(sp.SH_so, 10),'ROUND','PEAR')) as char(10))=rp.shape and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and rp.HighSize " +
			" and sp.C = rp.Color and sp.PU = rp.Clarity and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sm.id in ("+StringUtils.toString(pktNos, ",")+")  " ;
	
			Object[] param = new Object[1];
			param[0] = userId ;
			int r = this.getJdbcDao().update(insertQ, param);
			
			sql = "update tb_stockmaster sm, tb_orderdetail od, tb_stockprp sp, tb_rapPrices rp  set sm.status = ?, sm.RapPrice  = (rp.price) , " +
					"sm.Rate = round((((100+sm.Rap)/100)*rp.price),0)," +
					"sp.RapPriceLab  = (rp.price) , " +
					"sp.RateLab = round((((100+sp.RapLab)/100)*rp.price),0) where " +
					" sm.id = sp.PKTID and GRPID = 1 and rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and rp.HighSize " +
					" and rp.color = if(sp.C > 'M' ,'M',sp.C) and rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sm.id in ("+StringUtils.toString(pktNos, ",")+") and sm.rap is not null " ;
			
			param=new Object[1];
			param[0] = status;
			int i  = this.getJdbcDao().update(sql, param);			

			//other lab updates
			sql = "update tb_stockmaster sm, tb_orderdetail od, tb_stockprp sp, tb_rapPrices rp  set sp.RapPriceLab  = (rp.price) , " +
			"sp.RateLab = round((((100+sp.RapLab)/100)*rp.price),0) where " +
			" sm.id = sp.PKTID and GRPID <> 1 and rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.LowSize and if(highSize>10,20,highSize) " +
			" and rp.color = if(sp.C > 'M' ,'M',sp.C) and rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) and sm.id = od.pktId and od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sm.id in ("+StringUtils.toString(pktNos, ",")+")  and sp.rapLab is not null " ;
			i  = this.getJdbcDao().update(sql, null);
			
			
			sql = "update tb_stockmaster sm inner join tb_orderdetail od on od.pktId = sm.id inner join tb_stockprp sp on sp.pktId = sm.id set sm.status = ?, sp.cts = if(sm.pcs <> 1,(sp.cts + od.cts),sp.cts) where " +
				" od.orderId in ("+StringUtils.toString(memoNos, ",")+") and sm.id in ("+StringUtils.toString(pktNos, ",")+")  " ;
			param=new Object[1];
			param[0] = status;
			int k = this.getJdbcDao().update(sql, param);
			
			//Update memo status if no pkts left in memo
				sql = "update tb_ordermaster om set om.status = 3 where (select count(*) from tb_orderdetail od where od.orderId = om.id and od.status <> 3 )= 0 and om.id in ("+StringUtils.toString(memoNos, ",")+")  ";
				i = this.getJdbcDao().update(sql, null);
				if(i >= 1){
					sql = "update tb_invoiceMaster i set i.invStatus = 'REJ' where (select count(*) from tb_orderdetail od where od.orderId in ("+StringUtils.toString(memoNos, ",")+") and od.status <> 3 )= 0 and i.memoOrderId in ("+StringUtils.toString(memoNos, ",")+")  ";
					this.getJdbcDao().update(sql, null);
					//TO Check for STATUS
					for (int j = 0; j < memoNos.length; j++) {
						Integer orderId = Integer.parseInt(memoNos[j]);
						updateMemoToInvoice(orderId);
					}
				}
				
		}
		return z;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#returnParcelDtl(java.lang.String[], java.lang.String[], java.lang.Integer)
	 */
	public int returnParcelDtl(String[] pktNos, String[] memoNos)
			throws Exception {
		String sql = "update tb_stockmaster sm, tb_orderdetail od, tb_stockprp sp set sm.pcs = (sm.pcs + od.pcs), sp.cts = (sp.cts+od.cts) where " +
			" sm.id = sp.PKTID and grpid = 1 and sm.id = od.pktId and sm.totalCts >= (sp.cts+od.cts) and  sm.totalPcs >= (sm.pcs+od.cts) and sm.totalPcs > 1 and sm.status = ? and od.orderId in ("+StringUtils.toString(memoNos, ",")+") " ;

			if(pktNos.length>0){
				sql+= " and sm.id in ("+StringUtils.toString(pktNos, ",")+")" ;
			}
		Object[] param=new Object[1];
		param[0] = Constants.STATUS_TYPE_SL;
		
		//TO Check for STATUS
		for (int j = 0; j < memoNos.length; j++) {
			Integer orderId = Integer.parseInt(memoNos[j]);
			updateMemoToInvoice(orderId);
		}
		return this.getJdbcDao().update(sql, param);		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoList(int, java.lang.String, int, int, int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String,int,int)
	 */
	public PageList getMemoList(String memoDate, int partyAccId,
			int partyAddId, int partyId, String orderType, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId,int iMemoStatus, 
			String sMemoType, String accType, String sDueDate,
			String sToDate, String sFromDate, Integer exRate, String sCompanyName, Integer memoNo) throws Exception {		
		StringBuilder sql = new StringBuilder (" select om.id, osm.statuscode status, om.userId,om.memo,om.termId,om.brokrage, pam.country,pam.city, om.acctype," +
				" om.refPartyId, dateformat(om.orderDate) as orderDate, dateformat(om.dueDate) as dueDate, om.exrate, pm.companyName, pam.branchCode ,tm.termCode, bpm.companyName brokerName, om.brokerid,om.contactPerson, om.comments ,om.ordertype,om.partyAccId,om.discount " ) 
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		//.append(" inner join tb_memotype mt ON mt.shortcode = om.ordertype")
		.append(" where  1=1 ");
	
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		.append(" where  1=1 ");
		
		List<Object> paramList =  new ArrayList<Object>();
		paramList.add(selfPartyAccId);
		//show overdue memos
		if(sDueDate != null){
			sql.append(" And om.status = 1 AND DATE_FORMAT(om.dueDate,'%Y-%m-%d') <  ?   ");
			countSql.append(" And om.status = 1 AND DATE_FORMAT(om.dueDate,'%Y-%m-%d') <  ? ");		
			paramList.add(sDueDate);
		}
		else if(exRate > 0) {
			sql.append(" AND om.exrate = 0 ");
			countSql.append(" AND om.exrate = 0 ");					
		}
		else {
			if(!StringUtils.isEmpty(sFromDate) && !StringUtils.isEmpty(sToDate)){
				sql.append("AND om.orderdate BETWEEN STR_TO_DATE(?,'%d-%m-%Y') and STR_TO_DATE(?,'%d-%m-%Y')");
				countSql.append("AND om.orderdate BETWEEN STR_TO_DATE(?,'%d-%m-%Y') and STR_TO_DATE(?,'%d-%m-%Y')");
				paramList.add(sFromDate);
				paramList.add(sToDate);				
			}
			if(!StringUtils.isEmpty(sCompanyName)){
				sql.append(" and  pm.companyName like  ?  ");
				countSql.append(" and  pm.companyName like  ?  ");
				paramList.add(sCompanyName+"%");							
			}
			if(iMemoStatus > -1){
				sql.append(" AND om.status =  ? ");
				countSql.append(" AND om.status  =  ? ");
				paramList.add(iMemoStatus);
			}
			if(!StringUtils.isEmpty(accType)){
				sql.append(" AND om.accType =  ? ");
				countSql.append(" AND om.accType  =  ? ");
				paramList.add(accType);
			}
			if(!StringUtils.isEmpty(sMemoType)){
				if(sMemoType.equals("Confirm")) {
					sql.append(" AND om.orderType IN ('Confirm', 'INV')");
					countSql.append(" AND om.orderType IN ('Confirm', 'INV')");
				}
				else{
				sql.append(" AND om.orderType =  ? ");
				countSql.append(" AND om.orderType  =  ? ");
				paramList.add(sMemoType);
				}
			}
			if(!StringUtils.isEmpty(memoDate)){
				sql.append(" AND DATE_FORMAT(om.orderDate,'%Y-%m-%d') =  ? ");
				countSql.append(" AND DATE_FORMAT(om.orderDate,'%Y-%m-%d') =  ? ");
				paramList.add(memoDate);		
			}
			if(!memoNo.equals(0))
			{
				sql.append(" AND om.id =  ? ");
				countSql.append(" AND om.id =  ? ");
				paramList.add(memoNo);
			}			
			if(partyAccId > -1){
				sql.append("pa.id =  ? ");
				countSql.append("pa.id =  ? ");
				paramList.add(partyAccId);
			}
			if(partyAddId > -1){
				sql.append("pam.id =  ? ");
				countSql.append("pam.id =  ? ");
				paramList.add(partyAddId);
	
			}
			if(partyId > -1){
				sql.append("pm.id =  ? ");
				countSql.append("pm.id =  ? ");
				paramList.add(partyId);
	
			}
			if(!StringUtils.isEmpty(orderType)){
				sql.append("om.orderType =  ? ");
				countSql.append("om.orderType =  ? ");
				paramList.add(orderType);
			}
			
			if(userId > -1){
				sql.append("om.userId =  ? ");
				countSql.append("om.userId  =  ? ");
				paramList.add(userId);
			}
			if(!StringUtils.isEmpty(srtIndex)){
				sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
			}
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new OrderMasterListRowMapper()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoList(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 *
	 *acctype =--> Locat and export
	 */
	/*public PageList getMemoList(String queryClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, Integer userId,
			Integer selfPartyAccId, String orderType, Integer status, String accType ) throws Exception {*/
		
	
	/*public PageList getMemoList(String queryClause,  Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId, int iMemoStatus, int iMemoType)throws Exception {		
		StringBuilder	sql = new StringBuilder (" select om.id, osm.statusCode status, om.userId, 	om.orderDate, om.dueDate, om.exrate, pm.companyName, pam.branchCode ,tm.termCode, bpm.companyName brokerName, om.brokrage, om.contactPerson, om.comments ,om.orderType, om.partyAccId, om.brokerId, om.refPartyId, om.memo, om.termId, tm.termDesc, im.id, pam.country, pam.city, om.accType " ) 
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = om.brokerId ")
		.append(" left outer join tb_invoiceMaster im on im.memoOrderId = om.id ") //on 16-11-2011 gaurang
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		.append(" where  1=1 and memo = 1 ")
		.append(queryClause);
	StringBuilder	sql = new StringBuilder (" select om.id, osm.statusCode status, om.userId, 	om.orderDate, om.dueDate, om.exrate, pm.companyName, pam.branchCode ,tm.termCode, bpm.companyName brokerName, om.contactPerson, om.comments ,om.orderType " ) 
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		.append(" where  1=1 ");
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ?  ")
		.append(" where  1=1 and memo = 1 ")
		.append(queryClause);
		List<Object> paramList =  new ArrayList<Object>();
		paramList.add(selfPartyAccId);
		if(userId > -1){
			sql.append("om.userId =  ? ");
			countSql.append("om.userId  =  ? ");
			paramList.add(userId);
		}
		if(iMemoStatus > -1){
			sql.append(" AND om.status =  ? ");
			countSql.append("om.status  =  ? ");
			paramList.add(iMemoStatus);
		}
		if(iMemoType > -1){
			sql.append(" AND om.status =  ? ");
			countSql.append("om.status  =  ? ");
			paramList.add(iMemoType);
		}
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new OrderMasterListRowMapper()));

	}*/


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getMemoList(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 */
	//Remove this later
	/*public PageList getMemoList(String queryClause,  Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId)throws Exception{
				return null;		
	}*/
	
	//public PageList getMemoListWeb(String queryClause, Integer page,
			//Integer pageSize, String srtIndex, String srtType) throws Exception {
		public PageList getMemoListWeb(String memoDate, 
			int partyAddId, int partyId, String orderType, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int iMemoStatus, 
			String sMemoType, String accType, String sDueDate,
			String sToDate, String sFromDate, Integer exRate, String sCompanyName, Integer memoNo) throws Exception {
		
		StringBuilder	sql = new StringBuilder (" select om.id, osm.statusCode status, om.userId, 	dateformat(om.orderDate) as orderDate, dateformat(om.dueDate) as dueDate, om.exrate, pm.companyName, pam.branchCode ,tm.termCode, bpm.companyName brokerName, om.brokrage, om.contactPerson, om.comments ,om.orderType, om.partyAccId, om.brokerId, om.refPartyId,om.discount, om.memo, om.termId, tm.termDesc, im.id, pam.country,mt.description ordertype ,pam.city, om.accType " ) 
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  pam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" left outer join tb_invoiceMaster im on im.memoOrderId = om.id and im.status = 2 ")
		.append(" inner join tb_memotype mt ON mt.shortcode = om.ordertype")
		.append(" where  1=1 and memo is null ");
		//.append(queryClause);

		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_ordermaster om ")
		.append(" inner join tb_orderStatusMaster osm on  osm.id = om.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" where  1=1 and memo is null ");
		//.append(queryClause);
		List<Object> paramList =  new ArrayList<Object>();
		
		if(sDueDate != null){
			sql.append(" And om.status = 1 AND DATE_FORMAT(om.dueDate,'%Y-%m-%d') <  ?   ");
			countSql.append(" And om.status = 1 AND DATE_FORMAT(om.dueDate,'%Y-%m-%d') <  ? ");		
			paramList.add(sDueDate);
		}
		else if(exRate > 0) {
			sql.append(" AND om.exrate = 0 ");
			countSql.append(" AND om.exrate = 0 ");					
		}
		else {
			if(!StringUtils.isEmpty(sFromDate) && !StringUtils.isEmpty(sToDate)){
				sql.append("AND om.orderdate BETWEEN STR_TO_DATE(?,'%d-%m-%Y') and STR_TO_DATE(?,'%d-%m-%Y')");
				countSql.append("AND om.orderdate BETWEEN STR_TO_DATE(?,'%d-%m-%Y') and STR_TO_DATE(?,'%d-%m-%Y')");
				paramList.add(sFromDate);
				paramList.add(sToDate);				
			}
			if(!StringUtils.isEmpty(sCompanyName)){
				sql.append(" and  pm.companyName like  ?  ");
				countSql.append(" and  pm.companyName like  ?  ");
				paramList.add(sCompanyName+"%");							
			}
			if(iMemoStatus > -1){
				sql.append(" AND om.status =  ? ");
				countSql.append(" AND om.status  =  ? ");
				paramList.add(iMemoStatus);
			}
			if(!StringUtils.isEmpty(accType)){
				sql.append(" AND om.accType =  ? ");
				countSql.append(" AND om.accType  =  ? ");
				paramList.add(accType);
			}
			if(!StringUtils.isEmpty(sMemoType)){
				sql.append(" AND om.orderType =  ? ");
				countSql.append(" AND om.orderType  =  ? ");
				paramList.add(sMemoType);
			}
			if(!StringUtils.isEmpty(memoDate)){
				sql.append(" AND DATE_FORMAT(om.orderDate,'%Y-%m-%d') =  ? ");
				countSql.append(" AND DATE_FORMAT(om.orderDate,'%Y-%m-%d') =  ? ");
				paramList.add(memoDate);		
			}
			
			if(partyAddId > -1){
				sql.append("pam.id =  ? ");
				countSql.append("pam.id =  ? ");
				paramList.add(partyAddId);
	
			}
			if(partyId > -1){
				sql.append("pm.id =  ? ");
				countSql.append("pm.id =  ? ");
				paramList.add(partyId);
	
			}
			if(!StringUtils.isEmpty(orderType)){
				sql.append("om.orderType =  ? ");
				countSql.append("om.orderType =  ? ");
				paramList.add(orderType);
			}
			
			if(userId > -1){
				sql.append("om.userId =  ? ");
				countSql.append("om.userId  =  ? ");
				paramList.add(userId);
			}
			if(memoNo>0){
				sql.append(" AND om.id =  ? ");
				countSql.append(" AND om.id  =  ? ");
				paramList.add(memoNo);
			}
		
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new OrderMasterListRowMapper()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceList(String, java.lang.String, int, int, int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public PageList getInvoiceList(String invType, String invDate, int partyAccId,
			int partyAddId, int partyId, String invStatus, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId, int selfPartyAccId) throws Exception {
		StringBuilder	sql = new StringBuilder (" select im.id, im.userId, im.invoiceDate, im.dueDate,  pm.companyName, pam.branchCode , bpm.companyName brokerName, im.finalAmount, im.paidAmt, im.exportStatus, im.consignee,conP.companyName consigneeName, im.consignmentCode, im.memoOrderId, im.tax, im.discount, im.expences, im.shipCharges, im.invStatus, im.invType " ) 
		.append(" from tb_invoiceMaster im ")
		.append(" inner join tb_partyAcc pa on  pa.id = im.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = im.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" left outer join tb_partyMaster conP on  conP.id = im.consignee ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		.append(" where  im.invStatus <> 'REJ' ");
	
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_invoiceMaster im ")
		.append(" inner join tb_partyAcc pa on  pa.id = im.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = im.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" left outer join tb_partyMaster conP on  conP.id = im.consignee ")
		.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
		.append(" where  im.invStatus <> 'REJ' ");
		
		List<Object> paramList =  new ArrayList<Object>();
		paramList.add(selfPartyAccId);
		if(!StringUtils.isEmpty(invType) ){
			sql.append("im.invType =  ? ");
			countSql.append("im.invType =  ? ");
			paramList.add(invType);
		}
		if(!StringUtils.isEmpty(invDate)){
			sql.append("DATE_FORMAT(im.invoiceDate,'%Y-%m-%d') =  ? ");
			countSql.append("DATE_FORMAT(im.invoiceDate,'%Y-%m-%d') =  ? ");
			paramList.add(invDate);
		
		}
		if(partyAccId > -1){
			sql.append("pa.id =  ? ");
			countSql.append("pa.id =  ? ");
			paramList.add(partyAccId);
		}
		if(partyAddId > -1){
			sql.append("pam.id =  ? ");
			countSql.append("pam.id =  ? ");
			paramList.add(partyAddId);

		}
		if(partyId > -1){
			sql.append("pm.id =  ? ");
			countSql.append("pm.id =  ? ");
			paramList.add(partyId);

		}
		if(!StringUtils.isEmpty(invStatus)){
			sql.append("im.invStatus =  ? ");
			countSql.append("im.invStatus =  ? ");
			paramList.add(invStatus);
		}
		
		if(userId > -1){
			sql.append("im.userId =  ? ");
			countSql.append("im.userId  =  ? ");
			paramList.add(userId);
		}
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new InvoiceReportRowExtract()));

	}
	
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceList(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 */
	public PageList getInvoiceList(String queryClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId,
			int selfPartyAccId) throws Exception {
		StringBuilder	sql = new StringBuilder (" select im.id, im.userId, dateformat(im.invoiceDate) as invoiceDate, dateformat(im.dueDate) as dueDate,  pm.companyName, pam.branchCode , bpm.companyName brokerName, im.finalAmount, im.paidAmt, im.exportStatus, im.consigneePartyId,conP.companyName consigneeName, im.consignmentCode, im.memoOrderId, im.tax, im.discount, im.expences, im.shipCharges, im.invStatus, im.invType " ) 
		.append(" from tb_invoiceMaster im ")
		.append(" inner join tb_partyAcc pa on  pa.id = im.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = im.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")

		.append(" left outer join tb_partyMaster conP on  conP.id = im.consigneePartyId ")
		.append(" inner join tb_users u on  u.id = im.userId and u.partyAccId = ? ")
		.append(" where  im.invStatus <> 'REJ' ")
		.append(queryClause);
		
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_invoiceMaster im ")
		.append(" inner join tb_partyAcc pa on  pa.id = im.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = im.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" left outer join tb_partyMaster conP on  conP.id = im.consigneePartyId ")
		.append(" inner join tb_users u on  u.id = im.userId and u.partyAccId = ? ")
		.append(" where  im.invStatus <> 'REJ' ")
		.append(queryClause);
		List<Object> paramList =  new ArrayList<Object>();
		paramList.add(selfPartyAccId);
		
		if(userId > -1){
			sql.append("im.userId =  ? ");
			countSql.append("im.userId  =  ? ");
			paramList.add(userId);
		}
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new InvoiceReportRowExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#insertMemoToInvoice(int,int)
	 */
	
	public int  insertMemoToInvoice(InvoiceMaster im, int memoId, int userId) throws Exception {
		// TODO Auto-generated method stub
		int  id = 0;
		synchronized (im) {
			Object param[]=new Object[15];
			//SimpleDateFormat smf = new SimpleDateFormat("yy-MM");
			//String invCd = smf.format(new Date())
			
			String sql = "insert into tb_invoiceMaster (partyAccId, invoiceDate, dueDate, invType,  totalAmount, tax, discount, shipCharges, expences, invStatus, memoOrderId, brokerId,  brokrage, exportStatus,finalAmount, userId, consigneePartyId,consignmentCode, expRefNo,othRefNo,destination,preCarrierPartyId,placeOfPreCarrier,vesselFlight,portOfLoad,portOfDischarge,consigneeName,CstVat,pan, status, createDate, grandTotal ) " +
					" SELECT om.partyAccId, om.orderDate, om.dueDate, om.accType , ROUND(sum(od.cts*od.rate),2), "+ //updated as new logic by Gaurang 
					"om.tax, om.discount, om.expences, om.shipCharges, 'UNPAID', om.id, om.brokerId, om.brokrage, 'TBE' , (ROUND(sum(od.cts*od.rate) + (sum(od.cts*od.rate)*IFNULL(om.tax,0)/100) - (sum(od.cts*od.rate)* IFNULL(om.discount,0)/100) +  IFNULL(om.expences,0),2)*(1-(ifnull(tm.byrComm,0)/100))), ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, now(),ROUND(sum(od.cts*od.rate) + (sum(od.cts*od.rate)*IFNULL(om.tax,0)/100) - (sum(od.cts*od.rate)* IFNULL(om.discount,0)/100) +  IFNULL(om.expences,0),2) finalFActor  FROM tb_ordermaster om inner join  "+
					"tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 inner join tb_stockmaster sm on s.pktId = sm.id "+
					"inner join  tb_termmaster tm on tm.id= om.termId  where om.id  = ? and om.status = 2 and sm.status = if(od.pcs=1,4,1) " ;
					
			param[0] = userId ;
			param[1] = im.getConsigneePartyId() ;
			param[2] = im.getConsignmentCode() ;
			param[3] = im.getExpRefNo() ;
			param[4] = im.getOthRefNo() ;
			param[5] = im.getDestination();
			param[6] = im.getPreCarrierPartyId();
			param[7] = im.getPlaceOfPreCarrier() ;
			param[8] = im.getVesselFlight() ;
			param[9] = im.getPortOfLoad() ;
			param[10] = im.getPortOfDischarge() ;
			param[11] = im.getConsigneeName() ;
			param[12] = im.getCstVat() ;
			param[13] = im.getPan() ;
			param[14] = memoId ;
			
			int  i =  this.getJdbcDao().update(sql, param);
			
			
			sql = "select max(id) from tb_invoiceMaster" ;
			
			id =  this.getJdbcDao().queryForInt(sql, null);
			
			sql = "insert into tb_invoiceDetail (invId, pktId, rate, totalRate , tax, discount, finalRate, status, pcs,cts, rap, addDisc, rejCts,grandTotal ) " +
			" SELECT ?, od.pktId, od.rate, ROUND(od.cts*od.rate,2), null, null, (ROUND(od.cts*od.rate,2)*(1-(ifnull(tm.byrComm,0)/100))), 2 ,od.pcs, od.cts, od.rap,od.addDisc,od.rejCts,ROUND(od.cts*od.rate,2)  from "+
			" tb_ordermaster om inner join  tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 "+
			" inner join tb_stockmaster sm on sm.id = s.pktid inner join  tb_termmaster tm on tm.id= om.termId  where om.id  = ?  and od.status =2 " ;
			
			
			param = new Object[2];
			
			param[0] = id ;
			param[1] = memoId ;
			this.getJdbcDao().update(sql, param);
			
			sql = "update tb_ordermaster set orderType = 'Confirm' where id = ?" ;
			param = new Object[1];
			
			param[0] = memoId ;
			this.getJdbcDao().update(sql, param);
			
			
			/*sql = "update tb_stockmaster set status = ?  where id = ?" ;
			param = new Object[2];
			param[0] = Constants.STATUS_TYPE_INV ;
			param[1] = memoId ;
			this.getJdbcDao().update(sql, param);*/
		
			
			//TODO
			//add gl entry for stock inventory  invoiced stock PAYABLE ACCOUNT
			
			//add gl entry for stock tax remove tax invoiced stock PAYABLE ACCOUNT
			
				sql="Select im.id,pm.companyName, im.totalAmount,im.tax,im.discount,im.invoiceDate,im.partyAccId,im.userId from tb_invoiceMaster im inner join tb_partyAcc pa on pa.id=im.partyAccId  inner join tb_partyAddMaster pam on pam.id=partyAddId  inner join tb_partyMaster pm on pm.id=pam.partyId where im.id = ?";
				param = new Object[1];
				param[0] = id ;
			    InvoiceMaster inv = (InvoiceMaster)this.getJdbcDao().queryForObject(sql, param, new InvoiceMasterRowExtract());
				addSaleTrf(0, 0, 0, 0, 0, memoId,inv.getCompanyName() ,im.getDestination(), im.getDestination(), "", inv.getInvoiceDate(), CommonUtil.getBigDecimalFromDouble(inv.getTotalAmount()), "", "", "", new BigDecimal("-1"), userId, CommonUtil.getBigDecimalFromDouble(inv.getTax()), CommonUtil.getBigDecimalFromDouble(inv.getDiscount()), 0);
			
			}
			return id;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceMaster(java.lang.Integer)
	 */
	public InvoiceMaster getInvoiceMaster(Integer invId) throws Exception {
		List list = this.hibernateTemplate.find(" from InvoiceMaster where id  = ? ",invId);
			return (InvoiceMaster)list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceDetails(java.lang.Integer)
	 */
	/*
	String sql = "insert into tb_invoiceMaster (partyAccId, invoiceDate, dueDate, invType,  totalAmount, tax, discount, shipCharges, expences, invStatus, memoOrderId, brokerId,  brokrage, exportStatus,finalAmount, userId, consigneePartyId,consignmentCode, expRefNo,othRefNo,destination,preCarrierPartyId,placeOfPreCarrier,vesselFlight,portOfLoad,portOfDischarge,consigneeName ) " +
		" SELECT om.partyAccId, now(), ADDDATE(curdate(), tm.creditDays), if(ifnull(exRate,0)>0,'L','E'), ROUND(sum(od.cts*od.rate),2), "+
		"om.tax, om.discount, om.expences, om.shipCharges, 'UNPAID', om.id, om.brokerId, om.brokrage, 'TBE' , ROUND(sum(od.cts*od.rate) + (sum(od.cts*od.rate)*IFNULL(om.tax,0)/100) - (sum(od.cts*od.rate)* IFNULL(om.discount,0)/100) +  IFNULL(om.expences,0),2), ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?  FROM tb_ordermaster om inner join  "+
		"tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 inner join tb_stockmaster sm on s.pktId = sm.id "+
		"inner join  tb_termmaster tm on tm.id= om.termId  where om.id  = ? and om.status = 2 and sm.status = 4 " ;
		*/
	public List getInvoiceDetails(Integer invId)
			throws Exception {
		Object[] param = new Object[2];
		
		param[0] = invId ;
		param[1] = 2 ;
		List list = this.hibernateTemplate.find(" from InvoiceDetails where id  = ? and status = ? ",param);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceAddDetails(com.basync.b2b.crm.data.InvoiceMaster)
	 */
	public Map getInvoiceAddDetails(InvoiceMaster im) throws Exception {
		
		String sql = " select pm.companyName, pbm.companyName brokerName, pam.branchCode, ps.address1, ps.address2,ps.address3,ps.city,ps.state,ps.pin,ps.country,ps.contactPerson, cp.companyName preCarrierPartyName, conp.companyName consigneePartyName " +
				" from tb_partyMaster pm inner join tb_partyAddMaster pam on pm.id =pam.partyId inner join tb_partyAcc pa on pa.partyAddId = pam.id inner Join tb_partyShipAdd ps on ps.partyAddId = pam.id" +
				" left outer join  tb_partyMaster pbm on pbm.id = ?  left outer join tb_partyMaster cp on cp.id = ? " +
				" left outer join tb_partyMaster conp on conp.id = ? " +
				" where pa.id = ? ";
		Object[] param = new Object[4];
		
		param[0] = im.getBrokerId() ;
		param[1] = im.getPreCarrierPartyId() ;
		param[2] = im.getConsigneePartyId() ;
		param[3] = im.getPartyAccId() ;
		return this.getJdbcSlaveDao().getJdbcTemplate().queryForMap(sql, param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#save(com.basync.b2b.crm.data.Payment)
	 */
	public void save(Payment payment) throws Exception {
		this.hibernateTemplate.save(payment);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#update(com.basync.b2b.crm.data.Payment)
	 */
	public void update(Payment payment) throws Exception {
		this.hibernateTemplate.saveOrUpdate(payment);
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceList(java.lang.Integer)
	 */
	public List<QueryDescription> getInvoiceList(Integer partyAccId)
			throws Exception {
		String sql = "select i.id, concat(i.id,'-',DATE_FORMAT(i.invoiceDate,'%d/%m/%y'),'-Amt:',(i.finalAmount-i.paidAmt)) description from tb_invoiceMaster i inner join tb_ordermaster o on o.id = i.memoOrderId where i.partyAccId = ? and i.invStatus = 'UNPAID' and  i.finalAmount-i.paidAmt >0";
			Object[] param = new Object[1];
			param[0] = partyAccId ;
			return (List<QueryDescription>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}
	
	public List<QueryDescription> getInvoiceList(Integer partyAccId, String accType)
	throws Exception {
if(accType.equals("L") || accType.equals("E")){
	String sql = "select i.id, concat(i.id,'-',DATE_FORMAT(i.invoiceDate,'%d/%m/%y'),'-Amt:',(i.finalAmount-i.paidAmt), '-exRate:',o.exRate) description from tb_invoiceMaster i inner join tb_ordermaster o on o.id = i.memoOrderId where i.partyAccId = ? and i.invStatus = 'UNPAID' and  i.finalAmount-i.paidAmt >0";
		Object[] param = new Object[1];
		param[0] = partyAccId ;
		return (List<QueryDescription>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
}else{
	return getInvoiceList(partyAccId);	
}
}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceList(java.lang.Integer)
	 */
	public List<QueryDescription> getInvoiceList(Integer partyAccId, String accType, String exRate)
			throws Exception {
		if(accType.equals("L") || accType.equals("E")){
			String sql = "select i.id, concat(i.id,'-',DATE_FORMAT(i.invoiceDate,'%d/%m/%y'),'-Amt:',(i.finalAmount-i.paidAmt), '-exRate:',?) description from tb_invoiceMaster i inner join tb_ordermaster o on o.id = i.memoOrderId where i.partyAccId = ? and i.invStatus = 'UNPAID' and  i.finalAmount-i.paidAmt >0";
				Object[] param = new Object[2];
				param[0] = exRate ;
				param[1] = partyAccId ;
				return (List<QueryDescription>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
		}else{
			return getInvoiceList(partyAccId);	
		}
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateInvoiceStatus(java.lang.Integer, java.lang.Double, java.lang.String)
	 */
	public int updateInvoiceStatus(Integer invId, BigDecimal amount,
			String invStatus) throws Exception {
		String sql = "update tb_invoiceMaster set invStatus = ?, paidAmt = IFNULL(paidAmt,0)+?  where id = ? ";
		Object[] param = new Object[3];
		param[0] = invStatus ;
		param[1] = amount ;
		param[2] = invId ;
		
		return this.getJdbcDao().update(sql, param);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateInvoiceStatus(java.lang.Integer, java.lang.Double)
	 */
	public int updateInvoiceStatus(Integer invId, BigDecimal amount) throws Exception {
		String sql = "update tb_invoiceMaster set invStatus = if((finalAmount-IFNULL(paidAmt,0))+?<1,'PAID','UNPAID') , paidAmt = IFNULL(paidAmt,0)+?  where id = ? ";
		Object[] param = new Object[3];
		param[0] = amount ;
		param[1] = amount ;
		param[2] = invId ;
		
		return this.getJdbcDao().update(sql, param);
	}

	/**
	 * 
	 */
	public List<PacketDetails> getPktDetailsByInvId(String invIdString) throws Exception{
		
		StringBuilder sql = new StringBuilder (" select s.id, s.pktCode, idt.rate, if(s.pcs =1, sp.sh, concat(sp.shFr,'-', sp.shTo)) sh, if(s.pcs =1, sp.c, concat(sp.cFr,'-', sp.cTo)) c,if(s.pcs =1,  sp.pu, concat(sp.puFr,'-', sp.puTo)) pu, sp.ct, sp.lab, idt.pcs, idt.cts,idt.rap, idt.status, s.rapPrice, idt.addDisc,idt.rejCts  " )  
			.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
			.append(" join tb_invoiceDetail idt on s.Id = idt.pktId and idt.status  = 2 ")
			.append(" where  idt.invId in  ")
			.append(" ("+invIdString+") ");
		List<Object> params = new ArrayList<Object>();

		
		return this.getJdbcDao().query(sql.toString(), params.toArray(), new RowMapperResultSetExtractor(new OrderPktsRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#trnPkts(java.lang.String[], java.lang.Integer, com.basync.b2b.data.UserSession)
	 */
	public int trnPkts(String[] pktIds, Integer partyAccId,
			UserSession userSession) throws Exception {
		String sql = "update tb_stockmaster sm set partyAccId = ?, userId = ? where sm.id in ("+StringUtils.toString(pktIds, ",")+") and partyAccId = ? ";
		
		Object[] params = new Object[3];
		params[0] = partyAccId;
		params[1] = userSession.getUserId();
		params[2] = userSession.getPartyAccId();
		
		return this.getJdbcDao().update(sql.toString(),params);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaymentList(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 */
	public PageList getPaymentList(String queryClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId,
			int selfPartyAccId, int paymentId, String partyName, String mode, String frDate, String toDate) throws Exception {
		StringBuilder	sql = new StringBuilder (" select p.id, p.userId, DATE_FORMAT(p.paymentDate,'"+Constants.DATE_FORMAT_VIEWMYSQL+"'), p.partyAccId,  pm.companyName, pam.branchCode ,p.mode, p.bank,p.bankAccNo, p.amount, p.invId, p.paymentTerm, p.exRate, p.status,p.payFromPartyId " ) 
		.append(" from tb_payment p ")
		.append(" inner join tb_partyAcc pa on  pa.id = p.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" inner join tb_users u on  u.id = p.userId and u.partyAccId = ? ")
		.append(" where 1 = 1 and p.amount > 0 ")
		.append(queryClause);
		
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_payment p ")
		.append(" inner join tb_partyAcc pa on  pa.id = p.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" inner join tb_users u on  u.id = p.userId ")
		.append(" where  1 = 1 and p.amount > 0 ")
		.append(queryClause);
		List<Object> paramList =  new ArrayList<Object>();
		
		if(selfPartyAccId > -1){
			sql.append(" and u.partyAccId =  ? ");
			countSql.append(" and u.partyAccId =  ? ");
			paramList.add(selfPartyAccId);
		}
		if(userId > -1){
			sql.append("p.userId =  ? ");
			countSql.append("p.userId  =  ? ");
			paramList.add(userId);
		}
		if(!StringUtils.isEmpty(partyName)){
			sql.append(" and p.companyName like ? ");
			countSql.append(" and p.companyName  like ? ");
			paramList.add(paymentId+"%");
		}
		if(!StringUtils.isEmpty(frDate) ){
			sql.append(" and p.paymentDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
			countSql.append(" and p.paymentDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
			paramList.add(frDate);
		}
		if(!StringUtils.isEmpty(toDate) ){
			sql.append(" and p.paymentDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
			countSql.append(" and p.paymentDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
			paramList.add(toDate);
		}
		if(!StringUtils.isEmpty(srtIndex)){
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
		}
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new PaymentRowExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaymentDetails(java.lang.Integer)
	 */
	public List<QueryDescription> getPaymentDetails(Integer paymentId) throws Exception {
		String sql = "select invId id,amount description from tb_paymentDetails where paymentId = ?";
		Object[] param = new Object[1];
		param[0] = paymentId ;
		return (List<QueryDescription>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getStockReportData(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 */
	public PageList getStockReportData(String whereClause,
			Integer page, Integer pageSize, String srtIndex, String srtType,
			int userId, int selfPartyAccId) throws Exception {
			StringBuilder sql = new StringBuilder (" select s.id, s.pktCode,s.rap, s.rate, idt.rate sellRate, sp.cts, sp.sh, sp.c, sp.pu, sp.ct, ROUND(sp.cts* IFNULL(idt.rate,s.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate" )  
				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId  and grpid = 1 ")
				.append(" left outer join tb_invoiceDetail idt on s.Id = idt.pktId and idt.status  = 2 ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" where s.partyAccId = ? ")
				.append(" "+whereClause+" ");
			
			
			StringBuilder countSql = new StringBuilder (" select count(*) ")
			.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId  and grpid = 1 ")
			.append(" left outer join tb_invoiceDetail idt on s.Id = idt.pktId and idt.status  = 2 ")
			//.append(" inner join tb_statusMaster sm on sm.id = s.status ")
			.append(" where s.partyAccId = ? ")
			.append(" "+whereClause+" ");
			
			StringBuilder sqlStr = new StringBuilder(" select count(*) pktCode, avg(s.rap) RAP, round(avg(s.rate),2) rate, sum(sp.CTS) cts, round(sum(s.rate*sp.CTS),2) totalRate, round(sum(IFNULL(s.baseRate,0)),2) baseRate " )	
			.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId  and grpid = 1 ")
			.append(" left outer join tb_invoiceDetail idt on s.Id = idt.pktId and idt.status  = 2 ")
			//.append(" inner join tb_statusMaster sm on sm.id = s.status ")
			.append(" where s.partyAccId = ? ")
			.append(" "+whereClause+" ");
			
			List<Object> paramList = new ArrayList<Object>();
			paramList.add(selfPartyAccId);
			if(userId > -1){
				sql.append("p.userId =  ? ");
				countSql.append("p.userId  =  ? ");
				sqlStr.append("p.userId  =  ? ");
				paramList.add(userId);
			}
			if(!StringUtils.isEmpty(srtIndex)){
				if(srtIndex.equals("sh")||srtIndex.equals("c")||srtIndex.equals("pu")||srtIndex.equals("ct")){
					srtIndex = srtIndex+"_so";
				}
				
				sql.append(" order by ").append(srtIndex).append(" ").append(srtType+" ");
			}
			
			Map userData = new HashMap();
			userData = getTotals(paramList.toArray(), sqlStr.toString());
			PageList pageList = this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new StockReportRowExtract()));
			pageList.setUserdata(userData);
			return pageList;

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateInvoiceMasterData(com.basync.b2b.crm.data.InvoiceMaster)
	 */
	public void updateInvoiceMasterData(InvoiceMaster im) throws Exception {
		this.hibernateTemplate.update(im);
		
		String sql = "update tb_ordermaster set orderType = 'INV' where id = (select memoOrderId from tb_invoiceMaster where id = ? ) " ;
		Object[] param = new Object[1];
		
		param[0] = im.getId() ;
		this.getJdbcDao().update(sql, param);
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getInvoiceMasterByMemoId(java.lang.Integer)
	 */
	public InvoiceMaster getInvoiceMasterByMemoId(Integer memoId)
			throws Exception {
		
		List list = this.hibernateTemplate.find(" from InvoiceMaster where memoOrderId  = ? ",memoId);
		return (InvoiceMaster)list.get(0);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getOrderAddDetails(com.basync.b2b.crm.data.OrderMasterData)
	 */
	public Map getOrderAddDetails(OrderMasterData om) throws Exception {
		
		String sql = " select pm.companyName,pm.phoneNo1, pm.phoneNo2,tm.termDesc, pbm.companyName brokerName, pam.branchCode, ps.address1, ps.address2,ps.address3,ps.city,ps.state,ps.pin,ps.country,ps.contactPerson " +
				" from tb_partyMaster pm inner join tb_partyAddMaster pam on pm.id =pam.partyId inner join tb_partyAcc pa on pa.partyAddId = pam.id inner Join tb_partyShipAdd ps on ps.partyAddId = pam.id" +
				" left outer join  tb_partyMaster pbm on pbm.id = ?  "+
				" join  tb_termmaster tm on tm.id = ?  "+
				" where pa.id = ? ";
		Object[] param = new Object[3];
		
		param[0] = om.getBrokerId() ;
		param[1] = om.getTermId() ;
		param[2] = om.getPartyAccId() ;
		return this.getJdbcSlaveDao().getJdbcTemplate().queryForMap(sql, param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPartyOS(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	public PageList getPartyOS(String whereClause, Integer page, Integer pageSize,
			String srtIndex, String srtType, int userId) throws Exception {
		
		StringBuilder sql = new StringBuilder ("select pm.companyName, pam.branchCode, contactPerson, sum(IFNULL(im.finalAmount,0)) as totalAmount, ")
						.append("sum(IFNULL(im.paidAmt,0)) as paidAmount, sum(IFNULL(pd.amount,0)) othPaidAmount, max(paymentDate) as lastPayDate ")
						.append("from tb_partyMaster pm inner join tb_partyAddMaster pam on pm.id = pam.partyId ")
						.append("inner join tb_partyAcc pa on pam.id = pa.partyAddId ")
						.append("inner join tb_invoiceMaster im on im.partyAccId = pa.ID ") 
						.append("left outer join tb_payment p on p.partyAccId =  im.partyAccId " )
						.append("left outer join tb_paymentDetails pd on p.partyAccId =  im.partyAccId ")
						.append(whereClause)
						.append("group by pm.companyName, pam.branchCode ");
		
		StringBuilder countSql = new StringBuilder ("select count(*) ")
						.append("from tb_partyMaster pm inner join tb_partyAddMaster pam on pm.id = pam.partyId ")
						.append("inner join tb_partyAcc pa on pam.id = pa.partyAddId ")
						.append("inner join tb_invoiceMaster im on im.partyAccId = pa.ID ") 
						.append("left outer join tb_payment p on p.partyAccId =  im.partyAccId " )
						.append("left outer join tb_paymentDetails pd on p.partyAccId =  im.partyAccId ")
						.append(whereClause);
		
						
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, null, new RowMapperResultSetExtractor(new PartyOSRowExtract()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaurchaseRep(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	public PageList getPaurchaseRep(String whereClause, Integer page,
			Integer pageSize, String srtIndex, String srtType, int userId)
			throws Exception {

		StringBuilder sql = new StringBuilder ("select pur.id, pur.vendorId, pm.companyName, billNo, dateformat(purchaseDate) as purchaseDate, dateformat(dueDate) as dueDate, pur.comments, pur.status ") 
									.append(" from tb_purchasemaster pur ") 
									.append(" inner join tb_partyMaster pm on pm.id = pur.vendorid  ");
		
		StringBuilder countSql = new StringBuilder ("select count(*) ") 
							.append(" from tb_purchasemaster pur ") 
							.append(" inner join tb_partyMaster pm on pm.id = pur.vendorid  ");		
						
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, null, new RowMapperResultSetExtractor(new PurchaseMasterRowExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaurchaseDetails(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	public List<PurchaseDetails> getPaurchaseDetails(String whereClause, Integer purchaseId)
			throws Exception {
		String sql = "select pktCode, rate, discount, wt, tax, status from tb_purchaseDetails where purchaseId = ?";
		Object[] param = new Object[1];
		param[0] =purchaseId;
		return ( List<PurchaseDetails>) this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new PurchaseDetailsRowExtract()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewAngadia(com.basync.b2b.crm.data.AngadiaMaster, com.basync.b2b.data.UserSession)
	 */
	public int addNewAngadia(AngadiaMaster am, UserSession us) throws Exception {
		SimpleDateFormat smd = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		String sql = "insert into tb_angadiaMaster (angadiaCoName, opBalance, createdDate, createdBy, code, dsc, status, accCode,currCode) values (?,?,?,?,?,?,?,?, ?)";
		Object[] param = new Object[9];
		param[0] = am.getAngadiaCoName();
		param[1] = am.getOpBalance();
		param[2] = smd.format(new Date());
		param[3] = us.getUserId() ;
		param[4] = am.getCode();
		param[5] = am.getDsc();
		param[6] = am.getStatus();
		param[7] = am.getAccCode();
		param[8] = am.getCurrCode();
		return this.getJdbcDao().update(sql.toString(),param);

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getAngadiaData()
	 */
	public List<AngadiaMaster> getAngadiaData(Integer status) throws Exception {
		
		String sql = "select id, angadiaCoName, code, opBalance, currBalance, accCode, currCode from tb_angadiaMaster ";
		Object[] param = null;
		if(status > 0){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
			
		
		return (List<AngadiaMaster>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new AngadiaMasterRowExtract()));
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getAngadiaDataById(java.lang.Integer)
	 */
	public AngadiaMaster getAngadiaDataById(Integer angadiaId) throws Exception {
		String sql = "select id, angadiaCoName, code, opBalance, currBalance, accCode,currCode from tb_angadiaMaster where id = ? ";
		Object[] param = new Object[1];
			param[0] = angadiaId;
		return (AngadiaMaster)this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, new AngadiaMasterRowExtract());
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateAngadia(com.basync.b2b.crm.data.AngadiaMaster, com.basync.b2b.data.UserSession)
	 */
	public int updateAngadia(AngadiaMaster am, UserSession us)
			throws Exception {
		SimpleDateFormat smd = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		String sql = "update tb_angadiaMaster set angadiaCoName = ?, modifiedDate = ? , modifiedBy = ?, code = ?, dsc = ?,currCode = ? where id = ?";
		Object[] param = new Object[7];
		param[0] = am.getAngadiaCoName();
		param[1] = smd.format(new Date());
		param[2] = us.getUserId() ;
		param[3] = am.getCode();
		param[4] = am.getDsc();
		param[5] = am.getId();
		param[6] = am.getCurrCode();
		
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewGLAccTyp(com.basync.b2b.data.GeneralIdNameStatusEtc)
	 */
	public int addNewGLAccTyp(GeneralIdNameStatusEtc g)
			throws Exception {
		String sql = "insert into tb_acc_types (accTypeName, status) values (?,?)";
			Object[] param = new Object[2];
			param[0] = g.getName();
			param[1] = g.getStatus();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateGLAccTyp(com.basync.b2b.data.GeneralIdNameStatusEtc, com.basync.b2b.data.UserSession)
	 */
	public int updateGLAccTyp(GeneralIdNameStatusEtc g)
			throws Exception {
		String sql = "update tb_acc_types set accTypeName = ? , status = ? where id = ?";
		Object[] param = new Object[3];
			param[0] = g.getName();
			param[1] = g.getStatus();
			param[2] = g.getId();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 */
	public List<GeneralIdNameStatusEtc> getGLAccTypData(Integer status) throws Exception {
		String sql = "select id , accTypeName name, status from tb_acc_types ";
		Object[] param = null;
		if(status > 0){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
		return (List<GeneralIdNameStatusEtc>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new GeneralIdNameStatusEtcRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewGLAccGrp(com.basync.b2b.data.GeneralIdNameStatusEtc)
	 */
	public int addNewGLAccGrp(GeneralIdNameStatusEtc g)
			throws Exception {
		String sql = "insert into tb_acc_group (groupName, typeId, status) values (?,?, ?)";
		Object[] param = new Object[3];
			param[0] = g.getName();
			param[1] = g.getTypeId();
			param[2] = g.getStatus();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateGLAccGrp(com.basync.b2b.data.GeneralIdNameStatusEtc)
	 */
	public int updateGLAccGrp(GeneralIdNameStatusEtc g)
			throws Exception {
		String sql = "update tb_acc_group set groupName = ? ,typeId = ?, status = ? where id = ?";
		Object[] param = new Object[4];
			param[0] = g.getName();
			param[1] = g.getTypeId();
			param[2] = g.getStatus();
			param[3] = g.getId();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getGLAccGrpData(int)
	 */
	public List<GeneralIdNameStatusEtc> getGLAccGrpData(Integer status) throws Exception {
		String sql = "select id , groupName name, typeId, status from tb_acc_group ";
		Object[] param = null;
		if(status > -1){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
		return (List<GeneralIdNameStatusEtc>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new GeneralIdNameStatusEtcRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewGLAcc(com.basync.b2b.crm.data.GeneralIdNameStatusEtc)
	 */
	public int addNewGLAcc(GeneralIdNameStatusEtc g) throws Exception {
		String sql = "insert into tb_acc_glacc (accName, code, accGroupId, status) values (?, ?, ?, ?)";
		Object[] param = new Object[4];
			param[0] = g.getName();
			param[1] = g.getCode();
			param[2] = g.getTypeId();
			param[3] = g.getStatus();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateGLAcc(com.basync.b2b.crm.data.GeneralIdNameStatusEtc)
	 */
	public int updateGLAcc(GeneralIdNameStatusEtc g) throws Exception {
		String sql = "update tb_acc_glacc set accName = ?, code = ? ,accGroupId = ?, status = ? where id = ?";
		Object[] param = new Object[5];
			param[0] = g.getName();
			param[1] = g.getCode();
			param[2] = g.getTypeId();
			param[3] = g.getStatus();
			param[4] = g.getId();
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getGLAccData(java.lang.Integer)
	 */
	public List<GeneralIdNameStatusEtc> getGLAccData(Integer status)
			throws Exception {
		String sql = "select code, accName, accGroupId, status from tb_acc_glacc ";
		Object[] param = null;
		if(status > 0){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
		return (List<GeneralIdNameStatusEtc>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new GlAccRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getCurrencyQD(java.lang.Integer)
	 */
	public List<QueryCodeDescription> getCurrencyQD(Integer status)
			throws Exception {
		String sql = "select currAbrev id, currency description  from tb_currencies ";
		Object[] param = null;
		if(status > 0){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
		return (List<QueryCodeDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new QueryCodeDescriptionRowExtract()));

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getBankAccList(java.lang.Integer, java.lang.Integer)
	 */
	public List<BankAccounts> getBankAccList(Integer status, Integer partyAccId) throws Exception {
		String sql = "select id, accountCode, accountTYpe, bankAccountName, bankAccountNumber, bankName, bankAddress, bankCurrCode, dfltCurrAct, endingReconcileBalance,lastReconciledDate, partyAccId, status,branchName  from tb_bankAccounts ";
		List<Object> param = new ArrayList<Object>();
		if(status > -1){
			sql+= " where status = ? ";
			param.add(status);
		}
		if(partyAccId > -1){
			sql+= " and  partyAccId = ? ";
			param.add(partyAccId);
		}
		return (List<BankAccounts>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param.toArray(), new RowMapperResultSetExtractor(new BankAccountRowMapper()));
	}

	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getBankAcc(java.lang.Integer)
	 */
	public BankAccounts getBankAcc(Integer bankAccId) throws Exception {
		String sql = "select id, accountCode, accountTYpe, bankAccountName, bankAccountNumber, bankName, bankAddress, bankCurrCode, dfltCurrAct, endingReconcileBalance,lastReconciledDate, partyAccId, status,branchName  from tb_bankAccounts  where id = ? ";
		Object[] param = new Object[1];
		param[0] = bankAccId;
		return (BankAccounts)this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, new BankAccountRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewBankAcc(com.basync.b2b.crm.data.BankAccounts)
	 */
	public int addNewBankAcc(BankAccounts b) throws Exception {
		String sql = "insert into tb_bankAccounts (accountCode, accountType, bankAccountName, bankAccountNumber, bankAddress, bankName,bankCurrCode, dfltCurrAct, partyAccId, status, lastReconciledDate, branchName) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(),?)";
		Object[] param = new Object[11];
			param[0] = b.getAccountCode();
			param[1] = b.getAccountType();
			param[2] = b.getBankAccountName();
			param[3] = b.getBankAccountNumber();
			param[4] = b.getBankAddress();
			param[5] = b.getBankName();
			param[6] = b.getBankCurrCode();
			param[7] = b.getDfltCurrAct();
			param[8] = b.getPartyAccId();
			param[9] = b.getStatus();
			param[10] = b.getBranchName();
			
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateBankAcc(com.basync.b2b.crm.data.BankAccounts)
	 */
	public int updateBankAcc(BankAccounts b) throws Exception {
		String sql = "update tb_bankAccounts set bankAccountName = ? , bankAccountNumber = ?, dfltCurrAct = ?, bankAddress = ?, bankName = ?, status = ?,branchName = ? where id = ? ";
		Object[] param = new Object[8];
			param[0] = b.getBankAccountName();
			param[1] = b.getBankAccountNumber();
			param[2] = b.getDfltCurrAct();
			param[3] = b.getBankAddress();
			param[4] = b.getBankName();
			param[5] = b.getStatus();
			param[6] = b.getId();
			param[7] = b.getBranchName();
			
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getCurrencyList(java.lang.Integer)
	 */
	public List<Currency> getCurrencyList(Integer status) throws Exception {
		String sql = "select  currAbrev, currency, currSymbol, country, hundredsName, autoUpdate, status from tb_currencies ";
		Object[] param = null;
		if(status > 0){
			sql+= "where status = ? ";
			param = new Object[1];
			param[0] = status;
		}
		return (List<Currency>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new CurrencyRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addNewCurrency(com.basync.b2b.crm.data.Currency)
	 */
	public int addNewCurrency(Currency c) throws Exception {
		String sql = "insert into tb_currencies (currAbrev, currency, currSymbol, country, hundredsName, autoUpdate, status) values (?,?,?,?,?,?,?)";
		Object[] param = new Object[7];
		param[0] = c.getCurrAbrev();
		param[1] = c.getCurrency();
		param[2] = c.getCurrSymbol();
		param[3] = c.getCountry();
		param[4] = c.getHundredsName();
		param[5] = c.getAutoUpdate();
		param[6] = c.getStatus();
		
	return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateCurrency(com.basync.b2b.crm.data.Currency)
	 */
	public int updateCurrency(Currency c) throws Exception {
		String sql = "update tb_currencies currency = ? , currSymbol = ?, country = ?, hundredsName = ?, autoUpdate = ?, status = ? where currAbrev = ?";
		Object[] param = new Object[7];
		param[0] = c.getCurrency();
		param[1] = c.getCurrSymbol();
		param[2] = c.getCountry();
		param[3] = c.getHundredsName();
		param[4] = c.getAutoUpdate();
		param[5] = c.getStatus();
		param[6] = c.getCurrAbrev();
		
	return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#deleteBankAcc(int)
	 */
	public int deleteBankAcc(int bankAccId) throws Exception {
		String sql = " delete tb_bankAccounts where id  = ? ";
		Object[] param = new Object[1];
		param[0] = bankAccId;
		return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#deleteCurrency(java.lang.String)
	 */
	public int deleteCurrency(String currAbrev) throws Exception {
		String sql = " delete tb_currencies where currAbrev  = ? ";
		Object[] param = new Object[1];
		param[0] = currAbrev;
		return this.getJdbcDao().update(sql,param);

	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getBankBalance(int)
	 */
	public Double getBankBalance(int bankAccId)
			throws Exception {
		String sql = "select ifnull(sum(amount),0) from tb_bank_trans where bankAccId = ? ";
		Object[] param = new Object[1];
		param[0] = bankAccId;
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, Double.class);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addBankTran(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, int, java.lang.Double)
	 */
	public int addBankTran(int type, int transNo, int bankAccId, String ref,
			String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate)
			throws Exception {
		//for getting master record
		
		String sql = "insert into tb_bank_trans (type, transNo,bankAccId, ref, transDate, amount, userId, exRate,description ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[9];
		param[0] = type;
		param[1] = transNo;
		param[2] = bankAccId;
		param[3] = ref;
		param[4] = date;
		param[5] = amount;
		param[6] = userId;
		param[7] = exRate;
		param[8] = dsc;
		
	return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addGlTran(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, int, java.lang.Double)
	 */
	/*public BigDecimal addGlTran(int type, int transNo, String accountCode, String ref,
			String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate,int personTypeId, Integer partyAccId, String currCode, String userCurrency)
			throws Exception {
		BigDecimal localAmt = amount;
		if(!userCurrency.equals(currCode)) {
			localAmt = amount.multiply(exRate);
			localAmt = amount = localAmt.setScale(2, RoundingMode.HALF_UP);
			userCurrency = currCode;
		}
		String sql = "insert into tb_gl_trans (type, transNo, transDate, accountCode, description , amount ,userId, personTypeId, currency, exRate, partyAccId, localAmt) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[12];
		param[0] = type;
		param[1] = transNo;
		param[2] = date;
		param[3] = accountCode;
		param[4] = dsc;
		param[5] = amount;
		param[6] = userId;
		if(personTypeId > 0){
			param[7] = personTypeId;
		}else{
			param[7] = null;
		}
		param[8] = userCurrency;
		param[9] = exRate;
		param[10] = partyAccId;
		param[11] = localAmt;
		this.getJdbcDao().update(sql.toString(),param);
	return amount;
	}*/

	public BigDecimal addGlTrans(int type, int transNo, String accountCode, String ref,
			String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate,int personTypeId, Integer partyAccId, String currency)
			throws Exception {
		String sql = "insert into tb_gl_trans (type, transNo, transDate, accountCode, description , amount ,userId, personTypeId, currency, exRate, partyAccId, localAmt) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[12];
		param[0] = type;
		param[1] = transNo;
		param[2] = date;
		param[3] = accountCode;
		param[4] = dsc;
		param[5] = amount;
		param[6] = userId;
		if(personTypeId > 0){
			param[7] = personTypeId;
		}else{
			param[7] = null;
		}
		param[8] = currency;
		param[9] = exRate;
		param[10] = partyAccId;
		param[11] = 0;
		this.getJdbcDao().update(sql.toString(),param);
	return amount;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addAngadiaTran(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, int, java.lang.Double)
	 */
	public int addAngadiaTran(int type, int transNo, int angadiaId, String ref,
			String dsc, String date, BigDecimal amount, int userId, BigDecimal exRate)
			throws Exception {
		String sql = "insert into tb_angadia_trans (type, transNo, transDate, angadiaId, description , amount ,userId) values (?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[7];
		param[0] = type;
		param[1] = transNo;
		param[2] = date;
		param[3] = angadiaId;
		param[4] = dsc;
		BigDecimal finalAmt = amount; 
		if(!exRate.equals(0)){
			finalAmt = amount.multiply(exRate);
		}
		param[5] = finalAmt;
		param[6] = userId;
		
	return this.getJdbcDao().update(sql.toString(),param);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addSuppTran(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, int, java.lang.Double)
	 */
	public int addSuppTran(int type, int transNo, int vendorId,
			String vendorRef, String ref, String dsc, String date,
			Double amount, int userId, Double exRate) throws Exception {
		/*
		String sql = "insert into tb_payment ( type, transNo, partyAccId, paymentDate , mode , bank, bankAccNo, amount, invId, paymentTerm, exRate, userId, status , dsc) " +
							"	values (?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[7];
		param[0] = type;
		param[1] = transNo;
		param[2] = date;
		param[3] = angadiaId;
		param[4] = dsc;
		double finalAmt =0d; 
		if(exRate !=0){
			finalAmt = amount * exRate ;
		}
		param[5] = finalAmt;
		param[6] = userId;
		
	return this.getJdbcDao().update(sql.toString(),param);
	*/
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addCustTran(com.basync.b2b.crm.data.Payment, int, java.lang.Double)
	 */
	public int addCustTran(Payment p, int userId, Double exRate)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addBankTrf(int, int, java.lang.String, java.lang.String, double, double, double, java.lang.String)
	 */
	public synchronized void addBankTrf(int fromBankAccId, int toBankAccId, String ref,
			String paymentDate, BigDecimal amt, BigDecimal charges, BigDecimal exRate1, BigDecimal exRate2, String dsc, int userId, Integer partyAccId,  String userCurrency)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String trDate = sdfSql .format(sdf.parse(paymentDate));
		
		Object param[] = new Object[1];
		
		String sql = "select max(id)+1 from tb_refs where type = ?";
		param[0] = Constants.ST_BANKTRF;
		int transNo = this.getJdbcDao().queryForInt(sql, param );
		
		sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
		param = new Object[3];
		param[0] = transNo;
		param[1] = Constants.ST_BANKTRF;
		param[2] = ref ; 
		
		this.getJdbcDao().update(sql.toString(),param);
		
		
		BankAccounts fromBankAcc = getBankAcc(fromBankAccId);
		BankAccounts toBankAcc = getBankAcc(toBankAccId);
		BigDecimal total = Constants.BIGDECIMAL_ZERO;
		BigDecimal fromExRate = Constants.BIGDECIMAL_ONE;
		BigDecimal toExRate = Constants.BIGDECIMAL_ONE;
		BigDecimal fromAmt = Constants.BIGDECIMAL_ZERO;
		BigDecimal toAmt = Constants.BIGDECIMAL_ZERO;
		BigDecimal glAmt = Constants.BIGDECIMAL_ZERO;
		BigDecimal glCharges = Constants.BIGDECIMAL_ZERO;
		BigDecimal glExrate = Constants.BIGDECIMAL_ZERO;
		String glDsc = "From "+fromBankAcc.getBankAccountName() +" To "+toBankAcc.getBankAccountName();
		String fromCurr = fromBankAcc.getBankCurrCode();
		String toCurr = toBankAcc.getBankCurrCode();
		BigDecimal fromCharges = charges;
		fromAmt =  amt;
		//USD TO USD
		if(fromCurr.equals("USD") && toCurr.equals("USD")) {
			toAmt = amt;
			glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", userCurrency));
			glAmt = amt.multiply(glExrate);
			glAmt.setScale(2, RoundingMode.HALF_UP);
			if(fromCharges != Constants.BIGDECIMAL_ZERO) {
				glCharges = glExrate.multiply(fromCharges);
				glCharges.setScale(2, RoundingMode.HALF_UP);
			}
		}
		//USD TO LOCAL
		else if(fromCurr.equals("USD") && !toCurr.equals("USD") && toCurr.equals(userCurrency)) {
			toExRate  = glExrate = exRate2;
			glAmt = amt.multiply(fromAmt);
			glAmt.setScale(2, RoundingMode.HALF_UP);
			if(fromCharges != Constants.BIGDECIMAL_ZERO) {
				glCharges = glExrate.multiply(fromCharges);
				glCharges.setScale(2, RoundingMode.HALF_UP);
			}
			toAmt = fromAmt.multiply(toExRate);
			toAmt.setScale(2, RoundingMode.HALF_UP);
		}
		//LOCAL TO USD
		else if(!fromCurr.equals("USD") && fromCurr.equals(userCurrency) && toCurr.equals("USD")) {
			toExRate  = glExrate = exRate1;
			glAmt = fromAmt;
			glCharges = fromCharges;
			toAmt = fromAmt.divide(glExrate, 2, RoundingMode.HALF_UP);
		}
		//LOCAL TO LOCAL
		else if(fromCurr.equals(userCurrency) && toCurr.equals(userCurrency)) {
			glExrate = Constants.BIGDECIMAL_ONE;
			toAmt = fromAmt;
			glAmt = fromAmt;
			glCharges = fromCharges;
		}
		//USD TO OTHER
		else if(fromCurr.equals("USD") && !toCurr.equals(userCurrency) && !toCurr.equals("USD")) {
			fromExRate = exRate2;
			glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", userCurrency));
			toAmt = fromAmt.multiply(fromExRate);
			toAmt.setScale(2, RoundingMode.HALF_UP);
			glAmt = fromAmt.multiply(glExrate);
			glAmt.setScale(2, RoundingMode.HALF_UP);
			if(fromCharges != Constants.BIGDECIMAL_ZERO) {
				glCharges = fromCharges.multiply(glExrate);
				glCharges.setScale(2, RoundingMode.HALF_UP);
			}
		}
		//OTHER TO USD
		else if(!fromCurr.equals(userCurrency) && !fromCurr.equals("USD") && toCurr.equals("USD")) {
			fromExRate = exRate1;
			glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", userCurrency));
			BigDecimal usdAmt = fromAmt.divide(fromExRate, 2, RoundingMode.HALF_UP);
			toAmt = usdAmt;
			BigDecimal usdCharges = Constants.BIGDECIMAL_ZERO;
			glAmt = glExrate.multiply(usdAmt);
			glAmt.setScale(2, RoundingMode.HALF_UP);
			if(fromCharges != Constants.BIGDECIMAL_ZERO) {
				usdCharges = fromCharges.divide(exRate1, 2, RoundingMode.HALF_UP);
				glCharges = glExrate.multiply(usdCharges);
				glCharges.setScale(2, RoundingMode.HALF_UP);
			}
		}
		//OTHER TO OTHER
		else if(!fromCurr.equals(userCurrency) && !fromCurr.equals("USD")  && !toCurr.equals(userCurrency) && !toCurr.equals("USD")) {
			fromExRate = exRate1;
			toExRate = exRate2;
			glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", userCurrency));
			BigDecimal usdAmt = fromAmt.divide(fromExRate, 2, RoundingMode.HALF_UP);
			toAmt = usdAmt.multiply(toExRate);
			toAmt.setScale(2, RoundingMode.HALF_UP);
			BigDecimal usdCharges = Constants.BIGDECIMAL_ZERO;
			glAmt = glExrate.multiply(usdAmt);
			glAmt.setScale(2, RoundingMode.HALF_UP);
			if(fromCharges != Constants.BIGDECIMAL_ZERO) {
				usdCharges = fromCharges.divide(exRate1, 2, RoundingMode.HALF_UP);
				glCharges = glExrate.multiply(usdCharges);
				glCharges.setScale(2, RoundingMode.HALF_UP);
			}
		}
		//add gl entry for bank amount
		total.add(addGlTrans(Constants.ST_BANKTRF, transNo, fromBankAcc.getAccountCode(), ref, glDsc, trDate, (glAmt.add(glCharges)).negate(), userId, glExrate, -1, partyAccId, fromCurr));
		//amount from bank acc
		addBankTran(Constants.ST_BANKTRF, transNo, fromBankAccId, ref, dsc, trDate, (fromAmt.add(fromCharges)).negate(), userId, fromExRate);		
		
		if(!glCharges.equals(Constants.BIGDECIMAL_ZERO)){
			String accNo = genericService.getSysPref(Constants.bank_charge_act);
			total.add(addGlTrans(Constants.ST_BANKTRF, transNo, accNo, ref, glDsc, trDate, glCharges, userId, glExrate, -1, partyAccId, fromCurr));
		}	
		//amount to bank acc
		total.add(addGlTrans(Constants.ST_BANKTRF, transNo, toBankAcc.getAccountCode(), ref, glDsc, trDate, glAmt, userId, glExrate, -1, partyAccId, fromCurr));
		//amount ro bank acc
		addBankTran(Constants.ST_BANKTRF, transNo, toBankAccId, ref, dsc, trDate, toAmt, userId, toExRate);		

		//TODO Ex rate variatons for home currency 
		
		if(!total.equals(Constants.BIGDECIMAL_ZERO)){
			String accNo = genericService.getSysPref(Constants.exchange_diff_act);
			addGlTrans(Constants.ST_BANKTRF, transNo, accNo, ref, glDsc+" adjust ", trDate, total.negate(), userId, glExrate, -1, partyAccId, fromCurr);
		}
	}



	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addPaymentTrf(int, int, int, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public void addPaymentTrf(int fromAccType, int toPartyType,
			int fromBankAccId, int fromAngadiaId, int toPartyAccId,
			int vendorAccId, String toPartyName, String accountCode,
			String paymentDate, BigDecimal amount, BigDecimal actualAmount, String mode, String ref,
			String dsc, BigDecimal exRate, int userId, Integer invoice, PaymentDetails pd, String chequeDate, Integer partyAccId, String userCurrency) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String trDate = sdfSql .format(sdf.parse(paymentDate));
		if(!StringUtils.isEmpty(chequeDate))chequeDate = sdfSql.format(sdf.parse(chequeDate));
		
		Object param[] = new Object[1];
		
		String sql = "select max(id)+1 from tb_refs where type = ?";
		param[0] = Constants.ST_BANKTRF;
		int transNo = this.getJdbcDao().queryForInt(sql, param );
		
		sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
		param = new Object[3];
		param[0] = transNo;
		param[1] = Constants.ST_BANKTRF;
		param[2] = ref ; 
		
		this.getJdbcDao().update(sql.toString(),param);
		
		BankAccounts fromBankAcc = getBankAcc(fromBankAccId);
		String currCode = fromBankAcc.getBankCurrCode();
		BigDecimal glAmt = amount;
		/*if(!currCode.equals("USD")) {
			glUSDAmt = amount.divide(exRate, 2, RoundingMode.HALF_UP);
		}*/
		String glDsc = "";
		BigDecimal total = Constants.BIGDECIMAL_ZERO;
		//find type of trans
		if(fromAccType == 0){
			//bank
			
			total.add(addGlTrans(Constants.ST_PAYMENT, transNo, fromBankAcc.getAccountCode(), ref, glDsc, trDate, glAmt.negate(), userId, exRate, toPartyType, partyAccId, currCode));
			//amount from bank acc
			addBankTran(Constants.ST_PAYMENT, transNo, fromBankAccId, ref, dsc, trDate, amount.negate(), userId, exRate);		
			
		}else{
			//angadia
			AngadiaMaster a = getAngadiaDataById(fromAngadiaId);
			total.add(addGlTrans(Constants.ST_PAYMENT, transNo, a.getAccCode() , ref, glDsc, trDate, glAmt.negate(), userId, exRate, toPartyType, partyAccId, currCode));
			addAngadiaTran(Constants.ST_PAYMENT, transNo, fromAngadiaId, ref, glDsc, trDate, amount.negate(), userId, exRate);
		}
		glDsc = toPartyName;
		//To entry
		total.add(addGlTrans(Constants.ST_PAYMENT, transNo, accountCode, ref, glDsc, trDate, glAmt, userId, exRate, toPartyType, partyAccId, currCode));
		if(toPartyType == 0 || toPartyType == 1 ){
			Payment p = new Payment();
			p.setType(Constants.ST_PAYMENT);
			p.setTransNo(transNo);
			if(toPartyType == 1)
				p.setPartyAccId(toPartyAccId);
			else if(toPartyType == 0)
				p.setPartyAccId(vendorAccId);
			p.setPaymentDate(trDate);
			p.setMode(mode);
			p.setAmount(amount.negate());
			p.setInvId(0);
		    //p.setExRate(exRate);
		    p.setDsc(dsc);
		    p.setStatus(1);
			//make entry in paymentdetails
			//
			pd.setAmount(actualAmount.subtract(amount));
			if(NumberUtils.isEqualToBigDecimal(actualAmount, amount))pd.setClearStatus(1);
			else pd.setClearStatus(0);
			pd.setClearDate(trDate);
			pd.setDsc(glDsc);
			pd.setExRate(exRate);
			pd.setPayment(p);
			pd.setChequeDate(chequeDate);
			pd.setInvId(0);
			pd.setAmount(amount.negate());
			pd.setActualEnteredAmt(amount);
			List<PaymentDetails> pDet = new ArrayList<PaymentDetails>();
			pDet.add(pd);
			p.setPaymentDetails(pDet);
			save(p);
		}
		if (invoice != -1) {
			Integer paidStatus = 0;
			if(NumberUtils.isEqualToBigDecimal(actualAmount, amount))paidStatus = 1; 
			sql = "update tb_purchasemaster set amount = ?, paidAmount = paidAmount + ?, paidStatus = ? where id=?";
			Object []  params = new Object[4];
			params[0] = actualAmount.subtract(amount);
			params[1] = amount;
			params[2] = paidStatus;
			params[3] = invoice;
			this.getJdbcDao().update(sql, params);
		}
		//TODO add entry in purchasemaster
		//TODO Ex rate variatons for home currency 
		
		if(!total.equals(0)){
			String accNo = genericService.getSysPref(Constants.exchange_diff_act);
			addGlTrans(Constants.ST_BANKTRF, transNo, accNo, ref, glDsc+" adjust ", trDate, total.negate(), userId, exRate, -1, partyAccId, currCode);
		}
		
	}

	
	/* 
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addDepositTrf(int, int, int, int, java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, double, int, java.util.List, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int addDepositTrf(int fromPartyType, int toAccType,
			int fromPartyAccId, int vendorAccId, String fromPartyName,
			int toBankAccId, int toAngadiaId,
			String paymentDate, BigDecimal totAmount, String mode, String ref,
			String dsc,  int userId, List<PaymentDetails> pDtl,
			String payTerms, String currency, BigDecimal exRate2, Integer partyAccId, String localCurr, Integer checkBoxVal)
			throws Exception {
		Object param[] = new Object[1];
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String trDate = sdfSql .format(sdf.parse(paymentDate));
		//exRate2 inc param
		
		String sql = "select max(id)+1 from tb_refs where type = ?";
		param[0] = Constants.ST_BANKTRF;
		int transNo = this.getJdbcDao().queryForInt(sql, param );
		
		sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
		param = new Object[3];
		param[0] = transNo;
		param[1] = Constants.ST_BANKTRF;
		param[2] = ref ; 
		
		this.getJdbcDao().update(sql.toString(),param);
		
		String glDsc = "";
		BigDecimal total = Constants.BIGDECIMAL_ZERO;
		
		glDsc = fromPartyName;
		
		if(fromPartyType == 0 || fromPartyType == 1 ){
			Payment p = new Payment();
			p.setType(Constants.ST_DEPOSIT);
			p.setTransNo(transNo);
			if(fromPartyType == 1)
				p.setPartyAccId(fromPartyAccId);
			else if(fromPartyType == 0)
				p.setPartyAccId(vendorAccId);
			p.setPaymentDate(trDate);
			p.setMode(mode);
			p.setAmount(totAmount);
			p.setInvId(0);//TODO inv id
			
		    p.setDsc(dsc);
		    p.setStatus(1);
		    p.setUserId(userId);
		    p.setPaymentDetails(pDtl);
		    p.setCurrency(currency);
		    for (int i = 0; i < p.getPaymentDetails().size(); i++) {
				PaymentDetails pd = new PaymentDetails();
				pd =  p.getPaymentDetails().get(i);
				//String chequeDate = sdfSql.format(sdf.parse(pd.getChequeDate()));
				String chequeDate = pd.getChequeDate();
				if(!StringUtils.isEmpty(chequeDate)){
					chequeDate = sdfSql.format(sdf.parse(chequeDate));
					if(sdfSql.parse(chequeDate).getTime() > new Date().getTime()) pd.setClearStatus(0);
					logger.debug(pd.getClearStatus()+"ZZZZZZZZZZZZZZZZ");
				}else{
					pd.setClearStatus(1);
					chequeDate = "0000-00-00";
				}
				//logger.debug(pd.getClearStatus()+"ZZZZZZZZZZZZZZZZZZZZZ"+sdf.parse(chequeDate).getTime()+" | "+new Date().getTime()+" |  "+sdf.parse(chequeDate)+"    |  "+new Date());
				pd.setChequeDate(chequeDate);
				
				if(fromPartyType ==1){
					if(pd.getInvId() > -1 && pd.getClearStatus() == 1){
						int z = updateInvoiceStatus(pd.getInvId(), pd.getAmount());
						if( z == 0)
							return -1; 
					}
				}
				BankAccounts bankAcc = getBankAcc(toBankAccId);
				BigDecimal finalExrate = Constants.BIGDECIMAL_ONE;
				BigDecimal amount = pd.getAmount();
				BigDecimal glAmt = Constants.BIGDECIMAL_ZERO;
				BigDecimal glExrate = Constants.BIGDECIMAL_ZERO;
				BigDecimal bankAmt = Constants.BIGDECIMAL_ZERO;
				//USD to local
				if(currency.equals("USD") && bankAcc.getBankCurrCode().equals(localCurr)) {
					//exrate = exrate2
					glAmt = amount.multiply(exRate2);
					glExrate = exRate2;
					glAmt = glAmt.setScale(2, RoundingMode.HALF_UP);
					bankAmt = glAmt;
					finalExrate = exRate2;
				}
				//local to USD
				else if(currency.equals(localCurr) && bankAcc.getBankCurrCode().equals("USD")) {
					glAmt = amount;
					finalExrate = pd.getExRate();
					glExrate = Constants.BIGDECIMAL_ONE;
					bankAmt = amount.divide(finalExrate, 2, RoundingMode.HALF_UP);
					bankAmt = bankAmt.setScale(2, RoundingMode.HALF_UP);
				}
				//USD to USD
				else if(currency.equals("USD") && bankAcc.getBankCurrCode().equals("USD")) {
					glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", localCurr));
					 glAmt = amount.multiply(glExrate);
					 glAmt = glAmt.setScale(2, RoundingMode.HALF_UP);
					 finalExrate = Constants.BIGDECIMAL_ONE;
					 bankAmt = amount;
				}
				//otherCurr to USD
				else if(!currency.equals(localCurr) && bankAcc.getBankCurrCode().equals("USD")) {
					finalExrate = pd.getExRate();
					bankAmt = amount.divide(finalExrate, 2, RoundingMode.HALF_UP);
					glExrate = new BigDecimal(RequestUtils.getExRateJson(currency, localCurr));
					glAmt = glExrate.multiply(amount);
					glAmt = glAmt.setScale(2, RoundingMode.HALF_UP);
				}
				//otherCurr to localCurr
				else if(!currency.equals(localCurr) && bankAcc.getBankCurrCode().equals(localCurr)) {
					glExrate = exRate2;
					finalExrate = pd.getExRate();
					//convert amount to USD
					BigDecimal amtInUSD = amount.divide(finalExrate, 2, RoundingMode.HALF_UP);
					glAmt = glExrate.multiply(amtInUSD);
					glAmt = glAmt.setScale(2, RoundingMode.HALF_UP);
					bankAmt = glAmt;
				}
				//OtherCurr to OtherCurr
				else if(!currency.equals(localCurr) && !currency.equals("USD") && !bankAcc.getBankCurrCode().equals(localCurr) && !bankAcc.getBankCurrCode().equals("USD")) {
					//convert amount to USD
					finalExrate = pd.getExRate();
					BigDecimal amtInUSD = amount.divide(finalExrate, 2, RoundingMode.HALF_UP);
					//convert USD to localcurr for glEntry
					glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", localCurr));
					glAmt = amtInUSD.divide(glExrate, 2, RoundingMode.HALF_UP);
					//convert USD to BankCurr using
					bankAmt = amtInUSD.multiply(exRate2);
					bankAmt = bankAmt.setScale(2, RoundingMode.HALF_UP);
				}
				//USD to Othercurr
				else if(currency.equals("USD") && !bankAcc.getBankCurrCode().equals("USD") && !bankAcc.getBankCurrCode().equals(localCurr)) {
					glExrate = new BigDecimal(RequestUtils.getExRateJson("USD", localCurr));
					glAmt = glExrate.multiply(amount);
					glAmt = glAmt.setScale(2, RoundingMode.HALF_UP);
					finalExrate = exRate2;
					bankAmt = finalExrate.multiply(amount);
				}
				total.add(addGlTrans(Constants.ST_DEPOSIT, transNo, pd.getAccountCode(), ref, glDsc, trDate, glAmt.negate(), userId, glExrate, fromPartyType, partyAccId, currency));
				if(toAccType == 0){
					//bank
					total.add(addGlTrans(Constants.ST_DEPOSIT, transNo, bankAcc.getAccountCode(), ref, glDsc, trDate, glAmt, userId,  glExrate, fromPartyType, partyAccId, currency));
					//amount from bank acc
				
					addBankTran(Constants.ST_DEPOSIT, transNo, toBankAccId, ref, pd.getDsc(), trDate, bankAmt, userId,  finalExrate);		
				}else{
					//angadia
					AngadiaMaster a = getAngadiaDataById(toAngadiaId);
					total.add(addGlTrans(Constants.ST_DEPOSIT, transNo, a.getAccCode() , ref, glDsc, trDate, amount, userId,  glExrate, fromPartyType, partyAccId, currency));
					addAngadiaTran(Constants.ST_DEPOSIT, transNo, toAngadiaId, ref, glDsc, trDate, bankAmt, userId,  finalExrate);
					
				}
				//To entry
				//TODO Ex rate variatons for home currency 
				p.getPaymentDetails().set(i,pd);
			}
		    
			save(p);
		}
		
		//adjustment amount
		if(!total.equals(Constants.BIGDECIMAL_ZERO)){
			String accNo = genericService.getSysPref(Constants.exchange_diff_act);
			//convert total to localCurrency
			addGlTrans(Constants.ST_BANKTRF, transNo, accNo, ref, glDsc+" adjust ", trDate, total.negate(), userId, Constants.BIGDECIMAL_ONE, -1, partyAccId, currency);
		}
		return 1;
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addJournalEntry(java.util.List)
	 */
	public void addJournalEntry(List<Object> paramList,String paymentDate,int userId, String ref, Integer partyAccId, String localCurr) throws Exception {
		Object param[] = new Object[1];
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String trDate = sdfSql .format(sdf.parse(paymentDate));
		
		String sql = "select max(id)+1 from tb_refs where type = ?";
		param[0] = Constants.ST_BANKTRF;
		int transNo = this.getJdbcDao().queryForInt(sql, param );
		
		sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
		param = new Object[3];
		param[0] = transNo;
		param[1] = Constants.ST_BANKTRF;
		param[2] = ref ; 
		
		BigDecimal exRate = Constants.BIGDECIMAL_ONE;
		if(!localCurr.equals("USD")) {
			exRate = new BigDecimal(RequestUtils.getExRateJson("USD", localCurr));
			exRate.setScale(2, RoundingMode.HALF_UP);
		}
		this.getJdbcDao().update(sql.toString(),param);
		for (int i = 0; i < paramList.size(); i++) {
			Object[] journalRow  = (Object[])paramList.get(i);
			if(Double.parseDouble(journalRow[1].toString())!=0){
				addGlTrans(Constants.ST_DEPOSIT, transNo, journalRow[0].toString(), ref, journalRow[3].toString(), trDate, (new BigDecimal(journalRow[1].toString())).negate(), userId, exRate, 2, partyAccId, localCurr);
			}
			if(Double.parseDouble(journalRow[2].toString())!=0){
				addGlTrans(Constants.ST_DEPOSIT, transNo, journalRow[0].toString(), ref, journalRow[3].toString(), trDate, (new BigDecimal(journalRow[2].toString())), userId, exRate, 2, partyAccId, localCurr);
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#addSaleTrf(int, int, int, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, double, int)
	 */
	public void addSaleTrf(int fromPartyType, int toPartyType, int fromBankAccId,
			int fromAngadiaId, int toPartyAccId, int packetNo,
			String toPartyName,String fromPartyName,String loc, String accountCode, String paymentDate,
			BigDecimal amount, String mode, String ref, String dsc, BigDecimal exRate,
			int userId,BigDecimal tax,BigDecimal discount, int status) throws Exception {
		//TODO  need to finish date - 5 sept 2011
		UserSession us = new UserSession();
		Integer partyAccId = us.getPartyAccId();

		Object param[] = new Object[1];
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String trDate = sdfSql .format(sdf.parse(paymentDate));
		
		String sql = "select max(id)+1 from tb_refs where type = ?";
		param[0] = Constants.ST_BANKTRF;
		int transNo = this.getJdbcDao().queryForInt(sql.toString(), param );
		sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
		param = new Object[3];
		param[0] = transNo;
		param[1] = Constants.ST_BANKTRF;
		param[2] = ref ; 
		
		this.getJdbcDao().update(sql.toString(),param);
		
		String glDsc = "Sale from party: "+fromPartyName +" to party: "+toPartyName +"to the Location : "+loc+" with packet No: "+packetNo+", paymentDate : "+paymentDate;
		BigDecimal total = Constants.BIGDECIMAL_ZERO;
		
		// Get the Account code for sales from tb_sysprefs 
		String salesAccNo = genericService.getSysPref(Constants.default_sales_act);
		// Get the Account code for Discount Given from tb_sysprefs 
		String disAccNo=genericService.getSysPref(Constants.default_sales_discount_act);
		// Get the Account code for Account Reciable Given from tb_sysprefs
		String recAccNo=genericService.getSysPref(Constants.debtors_act);
		// Get the Account code for Exchance Differences Given from tb_sysprefs
		String excAccNo = genericService.getSysPref(Constants.exchange_diff_act);
		// Get the Default value for sales Tax from tb_sysprefs
		String t = genericService.getSysPref(Constants.default_sales_tax);
		// get the Account code for Sales Tax from tb_sysprefs
		String salesTaxAcc = genericService.getSysPref(Constants.default_sales_tax_account);
		
		BigDecimal salesValue=new BigDecimal(t);
		BigDecimal disAmt= Constants.BIGDECIMAL_ZERO;
		BigDecimal amtAfterDiscount=Constants.BIGDECIMAL_ZERO;
		BigDecimal saleTax=Constants.BIGDECIMAL_ZERO;
		BigDecimal amtAftersaleTax=Constants.BIGDECIMAL_ZERO;
		
		if(status==Constants.STATUS_PERFORMED_INSERT){
			if(!salesValue.equals(Constants.BIGDECIMAL_ZERO)){
				saleTax=salesValue.divide(new BigDecimal(100)).multiply(amount);
				amtAftersaleTax=amount.subtract(saleTax);
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesAccNo, ref, glDsc, trDate, amtAftersaleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesTaxAcc, ref, glDsc, trDate, saleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
			}else if(!tax.equals(Constants.BIGDECIMAL_ZERO)){
				saleTax=tax.divide(new BigDecimal(100)).multiply(amount);
				amtAftersaleTax=amount.subtract(saleTax);
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesAccNo, ref, glDsc, trDate, amtAftersaleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesTaxAcc, ref, glDsc, trDate, saleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
			}else if((!tax.equals(Constants.BIGDECIMAL_ZERO)) && (!salesValue.equals(Constants.BIGDECIMAL_ZERO))){
				BigDecimal tempAmt=Constants.BIGDECIMAL_ZERO;
				tempAmt=tax.add(salesValue);
				saleTax=tempAmt.divide(new BigDecimal(100)).multiply(amount);
				amtAftersaleTax=amount.subtract(saleTax);
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesAccNo, ref, glDsc, trDate, amtAftersaleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesTaxAcc, ref, glDsc, trDate, saleTax.negate(), userId, exRate, fromPartyType, partyAccId, ""));
			}else{
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, salesAccNo, ref, glDsc, trDate, amount.negate(), userId, exRate, fromPartyType, partyAccId, ""));
			}
			if(!discount.equals(Constants.BIGDECIMAL_ZERO)){
				disAmt=(discount.divide(new BigDecimal(100)).multiply(amount));
				amtAfterDiscount=amount.subtract(disAmt);
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, disAccNo, ref, glDsc, trDate, disAmt, userId, exRate, fromPartyType, partyAccId, ""));
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, recAccNo, ref, glDsc, trDate, amtAfterDiscount, userId, exRate, fromPartyType, partyAccId, ""));
			}
			else{
				total.add(addGlTrans(Constants.ST_SALESORDER, transNo, recAccNo, ref, glDsc, trDate, amount, userId, exRate, fromPartyType, partyAccId, ""));
			}
		} 
		if(status==Constants.STATUS_PERFORMED_EDIT){
			//TODO delete from gltrans and insert new
			//total.add(updateGlTran(amount.negate(),salesAccNo,glDsc));
			if(!discount.equals(Constants.BIGDECIMAL_ZERO)){
				//total.add(updateGlTran(disAmt,disAccNo,glDsc));
				//total.add(updateGlTran(amtAfterDiscount,recAccNo,glDsc));
			}
			else{
				//total.add(updateGlTran(amount,recAccNo,glDsc));
			}
		}
		if(!total.equals(Constants.BIGDECIMAL_ZERO)){
			addGlTrans(Constants.ST_SALESORDER, transNo, excAccNo, ref, glDsc, trDate, total, userId, exRate, fromPartyType, partyAccId, "");
		}
	} 
	
	
	public GeneralIdNameStatusEtc getGlAcc(int accCode) throws Exception {
		String sql = "select code, accName, accGroupId, status from tb_acc_glacc ";
		Object[] param = new Object[1];;
			sql+= "where code = ? ";
			param[0] = accCode;
		return (GeneralIdNameStatusEtc)this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, new GlAccRowMapper());
	}
	//getBankAcc_kri
	/*public BankAccounts getBakAccc(int accCode) throws Exception {
		String sql = "SELECT id, accountCode,accountType, bankAccountName, bankAccountNumber,bankName, bankAddress, bankCurrCode, dfltCurrAct,lastReconciledDate,endingReconcileBalance,status,partyAccId FROM tb_bankAccounts  ";
		Object[] param = new Object[1];;
			sql+= "where accountCode=?";
			param[0] = accCode;
		return (BankAccounts)this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, new BankAccountRowMapper());
	}*/
	
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPktMemo(java.lang.String[], java.lang.String[])
	 */
	public List<QueryCodeDescription> getPktMemo(String[] pktNos, String[] memoNos)
			throws Exception {
		String sql = "select s.pktCode id, om.Id description from tb_stockmaster s inner join tb_orderdetail od on od.pktId= s.id and od.status = 2 inner join  tb_ordermaster om on om.id = od.orderId and om.status = 2 where s.id in (select pktId from tb_orderdetail where orderId in ("+StringUtils.toString(memoNos, ",")+") " ;
			if(pktNos != null && pktNos.length > 0)
				sql+=" and pktId in ("+StringUtils.toString(pktNos, ",")+")" ;
			sql+=")";
		
			
		return (List<QueryCodeDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql,new RowMapperResultSetExtractor( new QueryCodeDescriptionRowExtract()));
		
	} 
	
	public int updateOrderMemo(OrderMasterData omd) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatIn = new SimpleDateFormat("dd-MM-yyyy");
		String sql = "update tb_ordermaster set modifiedBy  = ?,modifiedOn = now(), orderDate = ?,contactPerson = ?, comments = ?, dueDate = ?,  status = ?, termId = ?, brokerId = ? , brokrage = ?,exRate = ?,accType = ? where id= ? " ;
		Object[] params = new Object[12];
		params[0] = omd.getUserId();
		
		params[1] = sdf.format(!omd.getOrderDate().equals("")?formatIn.parse(omd.getOrderDate()) :new Date());
		params[2] = omd.getContactPerson();
		params[3] = omd.getComments();
		params[4] = sdf.format(!omd.getDueDate().equals("")?formatIn.parse(omd.getDueDate()) :new Date());;
		params[5] = 2;
		params[6] =  omd.getTermId();
		params[7] = omd.getBrokerId();
		params[8] = omd.getBrokrage();
		params[9] =  omd.getExrate();
		params[10] = omd.getAccType();
		params[11] = omd.getId();
		int result = this.getJdbcDao().update(sql, params);
		
		Integer orderId = omd.getId();
		String[]pktIds = new String[omd.getPacketList().size()];
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd= omd.getPacketList().get(i);
			pktIds[i] = pd.getPktId().toString();	
			} 
		String pktString = StringUtils.toString(pktIds, ",");
		
		//history
		sql = "INSERT INTO tb_orderdetailHistory (orderId, pktId, baseRate, rate,issueDateTime,returnDateTime, status, rap, pcs, cts, addDisc, updateDate) " +
				"select orderId, pktId, baseRate, rate,issueDateTime,returnDateTime, status, rap, pcs, cts, addDisc, now() from tb_orderdetail od where orderId = ? and pktId in ("+ pktString+")";
		
		
		params = new Object[1];
	 	params[0] = orderId;
	 	this.getJdbcDao().update(sql, params);
		//history end
		
		sql = "UPDATE tb_orderdetail od inner join tb_stockprp sp on sp.pktId = od.pktId set od.Rate = ? , od.rap = ?, od.cts = if(od.pcs=1,od.cts, ? ), od.rejCts = ?, sp.cts = if(od.pcs=1,sp.cts, sp.cts + ? )   where od.pktId= ? and od.orderId = ?";
		List<Object[]> paramList = new ArrayList<Object[]>();
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd= omd.getPacketList().get(i);
			
				params = new Object[7];
				params[0] = pd.getRate() ;
				params[1] =  pd.getRap();
			 	params[2] = pd.getCts();
			 	params[3] = pd.getRejCts();
			 	params[4] = pd.getFinalCts()!=null?pd.getFinalCts():0;
			 	params[5] = pd.getPktId();
			 	params[6] = orderId;
			 	paramList.add(params);
			} 
		int[] res = this.getJdbcDao().batchUpdate(sql, paramList);
		
		//return remaining pkts
		sql = "select pktId from tb_orderdetail od where orderId = ? and pktId not in  ("+ pktString+")";
		params = new Object[1];
	 	params[0] = orderId;
		List<Integer> returnPkts = this.getJdbcSlaveDao().queryForList(sql, params, Integer.class);
		String[] pktNos =  new String[returnPkts.size()];
		for (int i = 0; i < returnPkts.size(); i++) {
			pktNos[i] = returnPkts.get(i).toString();
		}
		String[] memoNos =  new String[1];
		memoNos[0] = orderId.toString();
		if(pktNos.length > 0){
			memoReturnByPktNos(pktNos, memoNos, omd.getUserId());
		}
		
		//Edit invoice  TODO when make inv and memo together
		
		updateMemoToInvoice(orderId);
		return result;
	}
	
	
	//updateMemoToIvoice_k
	public int updateMemoToInvoice(Integer orderId) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatIn = new SimpleDateFormat("dd-MM-yyyy");
		String sql = "update tb_invoiceMaster im inner join (" +
			"select om.orderDate ,  om.dueDate,om.accType, ROUND(sum(od.cts*od.rate),2) totalAmt, " + 
			" om.tax ,  om.discount,  om.brokerId,  om.brokrage, ROUND(sum(od.cts*od.rate) + (sum(od.cts*od.rate)*IFNULL(om.tax,0)/100) - (sum(od.cts*od.rate)* IFNULL(om.discount,0)/100) +  IFNULL(om.expences,0),2) finalAmount, om.id orderId , tm.factor*(1-(ifnull(tm.byrComm,0)/100)) finalFactor " +
			"from  tb_ordermaster om inner join  "+
			"tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 inner join tb_stockmaster sm on s.pktId = sm.id "+
			"inner join  tb_termmaster tm on tm.id= om.termId where om.id  = ? and om.status = 2 and (s.status in (4,9,10) or sm.pcs <> 1) group by om.id) z on z.orderId = im.memoOrderId set im.invoiceDate  = z.orderDate , im.dueDate = z.dueDate, im.invType = z.accType, im.totalAmount = z.totalAmt, " + 
			"im.tax = z.tax , im.discount = z.discount,  im.brokerId = z.brokerId, im.brokrage = z.brokrage, im.finalAmount = z.finalAmount/(finalFactor), im.grandTotal = z.finalAmount,  updateDate = now()  " ;
		Object[] params = new Object[1];
		params[0] = orderId;
		int result = this.getJdbcDao().update(sql, params);
		
		sql = "select id from tb_invoiceMaster where memoOrderId = ? " ;
		params = new Object[1];
		params[0] = orderId;
		int  id =  this.getJdbcDao().queryForInt(sql, params);
		
		
		sql = " update tb_invoiceDetail set status = 3 , returnDate = now()  where invid = ? and pktid in (select pktid from tb_orderdetail od where od.status = 3 and od.orderId = ? ) ";
		params = new Object[2];
		params[0] = id;
		params[1] = orderId;
		this.getJdbcDao().update(sql, params);
		
		
		sql = "update tb_invoiceDetail id inner join tb_invoiceMaster im on im.id= id.invId inner join tb_ordermaster om on om.id = im.memoOrderId inner join  tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 and s.pktId = id.pktId "+
		" inner join tb_stockmaster sm on sm.id = s.pktid inner join tb_termmaster tm on tm.id= om.termId set id.rate = od.rate, totalRate = (od.cts*od.rate), id.rap = od.rap, id.cts = od.cts,id.rejCts = od.rejCts, finalRate = (od.cts*od.rate)*(1-(ifnull(tm.byrComm,0)/100)), im.grandTotal =(od.cts*od.rate)  " +
		"  where id.invId = ? and om.id  = ? and od.status =2 ";
		params = new Object[2];
		params[0] = id;
		params[1] = orderId;
		this.getJdbcDao().update(sql, params);
		
		sql="Select im.id,pm.companyName, im.totalAmount,im.tax,im.discount,im.invoiceDate,im.partyAccId,im.userId from tb_invoiceMaster im inner join tb_partyAcc pa on pa.id=im.partyAccId  inner join tb_partyAddMaster pam on pam.id=partyAddId  inner join tb_partyMaster pm on pm.id=pam.partyId where im.id = ?";
		params = new Object[1];
		params[0] = id ;
	    InvoiceMaster inv = (InvoiceMaster)this.getJdbcDao().queryForObject(sql, params, new InvoiceMasterRowExtract());
		addSaleTrf(0, 0, 0, 0, 0, orderId,inv.getCompanyName() ,"", "", "", inv.getInvoiceDate(), CommonUtil.getBigDecimalFromDouble(inv.getTotalAmount()), "", "", "", new BigDecimal("-1"), inv.getUserId(), CommonUtil.getBigDecimalFromDouble(inv.getTax()), CommonUtil.getBigDecimalFromDouble(inv.getDiscount()), 0);
		return result;
	}

	public int getInvIdFromMemo(int memoOrderId) throws Exception {
		String sql = "select max(id) from tb_invoiceMaster where memoOrderId  = "+memoOrderId+"";
		int id =  this.getJdbcDao().queryForInt(sql, null);
		if(id > 0){
			return id;
		}else{
			return 0;
		}
	}
	
	//getting BankAccCode
	public BankAccounts getBankAccCode(Integer bankAccCode) throws Exception {
		String sql = "select id, accountCode, accountTYpe, bankAccountName, bankAccountNumber, bankName, bankAddress, bankCurrCode, dfltCurrAct, endingReconcileBalance,lastReconciledDate, partyAccId, status  from tb_bankAccounts  where  accountCode = ? ";
		Object[] param = new Object[1];
		param[0] = bankAccCode;
		return (BankAccounts)this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, new BankAccountRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#clearPayment(int)
	 */
	public int clearPayment(int paymentDetailId) throws Exception {
		String sql = "update tb_invoiceMaster im inner join  tb_paymentDetails pd on pd.invId =im.id and pd.id = ? set invStatus = if((finalAmount-IFNULL(paidAmt,0))+pd.amount<1,'PAID','UNPAID') , paidAmt = IFNULL(paidAmt,0)+pd.amount,clearStatus =1 where pd.chequeDate < now() and clearStatus = 0 ";
		Object[] param = new Object[1];
		param[0] = paymentDetailId ;
		
		return this.getJdbcDao().update(sql, param);
		
	}
	
	public Float getExRateFromOrderId(int memoId) throws Exception{
		String sql = "select exRate from tb_ordermaster where ID = ?";
		Object[] param = new Object[1];
		param[0] = memoId;
		return (Float)this.getJdbcDao().getJdbcTemplate().queryForObject(sql, param, Float.class);
	}
	
	public int checkCompName(String companyName)throws Exception{
		String sql="Select COUNT(companyName) from tb_partyMaster where companyName= ?";
		Object[] param = new Object[1];
		param[0] = companyName;
		return this.getJdbcDao().getJdbcTemplate().queryForInt(sql, param);
	}
	
	public int checkMobNo(String mobNo)throws Exception{
		String sql="Select COUNT(ownerMobNo) from tb_partyMaster where ownerMobNo= ?";
		Object[] param = new Object[1];
		param[0] = mobNo;
		return this.getJdbcDao().getJdbcTemplate().queryForInt(sql, param);
	}
	
	public Double getSalesData(String fromDate, String toDate)throws Exception{
		String sql=null;
		Object[] param=null;
		if(fromDate!="" && toDate!=""){
			sql="select sum(finalAmount) as finalAmount from tb_invoicemaster where invoiceDate>=STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') AND invoiceDate<= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')";
			param = new Object[2];
			param[0] = fromDate;
			param[1] = toDate;
		}else{
			sql="select sum(finalAmount) as finalAmount from tb_invoicemaster";
		}
		return (Double)this.getJdbcDao().queryForObject(sql, param, Double.class);
	}
	public PageList getPacketDetailCert() throws Exception {
		return getPacketDetailCert(0);		
	}
	
	public PageList getPacketDetailCert(int iAllLAb) throws Exception {
		String sql = "select om.id as orderId, s.id, s.pktcode, sp.sh, sp.cts, sp.c, sp.PU, sp.lab from tb_stockmaster s inner join tb_stockprp sp ON sp.pktid = s.id inner join tb_orderdetail od ON od.pktid = sp.pktid inner join tb_ordermaster om ON od.orderid = om.id ";
		String countSql = "select count(*) from tb_stockmaster s inner join tb_stockprp sp ON sp.pktid = s.id inner join tb_orderdetail od ON od.pktid = sp.pktid inner join tb_ordermaster om ON od.orderid = om.id ";
		
		if(iAllLAb == 0) {			
			sql += "and om.lab =sp.lab ";
			countSql += "and om.lab =sp.lab ";
		}
		
		sql += "where om.ordertype = 'CI' and om.status = 2 and od.status=2 order by orderId";
		countSql += "where om.ordertype = 'CI' and om.status = 2 and od.status=2 ";
		
		return this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), 1, -1, null, new RowMapperResultSetExtractor(new PacketLabReturnRowExtract()));


	}	
	
	@SuppressWarnings("unchecked")
	public List<Dashboard> getDashboardQueries() throws Exception{
		String sql = "select query, type, params, description, divposition from tb_dashboard where status = 1";		
		return this.getJdbcDao().query(sql, null, new RowMapperResultSetExtractor(new DashboardRowExtract()));
	}
	
	@SuppressWarnings("unchecked")
	public Integer getdashboardDataInt(String sql) throws Exception{		
		return this.getJdbcDao().queryForInt(sql, null); 
	}
	
	@SuppressWarnings("unchecked")
	public List<QueryDescription> getdashboardDataQueryCode(String sql) throws Exception{		
		return  this.getJdbcDao().query(sql, null, new RowMapperResultSetExtractor(new QueryDescriptionExtract())) ;
	}

	public int getInvalidStockCount(int iPartyAccId) throws Exception{
		String sql = "select count(*)  from tb_stockmaster s inner join tb_stockprp sp  " +
				"on s.id = sp.pktId and s.partyAccId = ? and s.pcs = 1  and s.status in (0, 1)  " +
				"and (sp.SH_so is null or sp.pu_so is null or sp.cts is null or sp.cts = 0 " +
				"or s.rate is null or s.rate = 0 )  left outer join tb_rapPrices rp on  " +
				"rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = " +
				"if(sp.C > 'M' ,'M',sp.C) and  rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU)  " +
				"and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize " +
				"and rp.highsize   order by sp.cts desc, s.pktCode, grpId ";
		Object[] params = new Object[1];
		params[0] = iPartyAccId;
		return this.getJdbcDao().queryForInt(sql, params);
	}
	
	public int getOverdueMemoCount(int iPartyAccId, String sDate) throws Exception{
		String sql = "select count(*) from tb_ordermaster om  inner join tb_orderStatusMaster osm on " +
					 "osm.id = om.status  inner join tb_termmaster tm on  tm.id = om.termId  inner join " +
					 "tb_partyAcc pa on  pa.id = om.partyAccId  inner join tb_partyAddMaster  pam on " +
					 "pam.id = pa.partyAddId  inner join tb_partyMaster pm on  pm.id = pam.partyId " +
					 "left outer join tb_partyAcc bpa on  bpa.id = om.brokerId  left outer join " +
					 "tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId  left outer join tb_partyMaster " +
					 "bpm on  bpm.id = bpam.partyId  inner join tb_users u on  u.id = om.userId and " +
					 "u.partyAccId = ?  where  1=1  And om.status = 1 AND DATE_FORMAT(om.dueDate,'%Y-%m-%d') < ?";
		Object[] params = new Object[2];
		params[0] = iPartyAccId;
		params[1] = sDate;
		int i = this.getJdbcDao().queryForInt(sql, params); 
		return i;
	}
	
	public PurchaseMaster getGoodCostData(String fromDate, String toDate)throws Exception{
		String sql=null;
		Object[] param=null;
		if(!StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(toDate)){
			sql="select sum(amount) as amount , sum(expenses) as expenses from tb_purchasemaster where purchaseDate>=STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') AND purchaseDate<= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')";
			param = new Object[2];
			param[0] = fromDate;
			param[1] = toDate;
		}else{
			sql="select sum(amount) as amount,  sum(expenses) as expenses from tb_purchasemaster";
		}
		return (PurchaseMaster)this.getJdbcDao().queryForObject(sql, param, new GoodCostDataExtractor());
	}
	
	public List<ProfitLossData> getPerReportData(String fromDate, String toDate, String partyName)throws Exception{
		String sql = "SELECT `pm`.`companyName`, dateformat(`prc`.`purchaseDate`) as `purchaseDate`,dateformat(`prc`.`dueDate`) as `dueDate`, " +
							"`prc`.`amount`, `prc`.`expenses`, `pd`.`wt`, `inv`.`cts`, `inv`.`finalRate`,  pd.pktcode, pd.purchaseId " +
							"FROM `tb_partyMaster` `pm` " +
							"INNER JOIN `tb_partyAddMaster` `pa` ON `pm`.`id` = `pa`.`partyId` " +
							"INNER JOIN `tb_partyAcc` `pc` ON `pc`.`partyAddid` = `pa`.`id` " +
							"INNER JOIN `tb_purchasemaster` `prc` ON `pc`.`id` = `prc`.`vendorId` " +
							"INNER JOIN `tb_purchaseDetails` `pd` ON `prc`.`id` = `pd`.`purchaseId` " +
							"LEFT OUTER JOIN `tb_stockmaster` `sm` ON `pd`.`pktcode` = `sm`.`pktcode` " +
							"LEFT OUTER JOIN `tb_invoiceDetail` `inv` ON `sm`.`id` = `inv`.`pktId` " +
							"LEFT OUTER JOIN `tb_invoiceMaster` `im` ON `im`.`id` = `inv`.`invId` " +
							"WHERE 1 = 1 ";
		List<Object> paramList = new ArrayList<Object>();
		if(!partyName.equals("All")){
			sql += " and pm.companyName like ?  ";
			paramList.add(partyName);
		}
		if(!StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(toDate)){ 
			sql += " and prc.purchaseDate>=STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') AND prc.purchaseDate<=STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')";
			paramList.add(fromDate);
			paramList.add(toDate);
		}		
		sql += " group by pd.purchaseid";
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new PercentageDataExtractor()));
	}
	
	public int storedProcedureCall(String sql, Object [] params) throws Exception{
		return this.getJdbcDao().execute(sql, params);
	}

	public List<QueryDescription> getPurchaseInvoiceList(Integer vendorId, Integer paidStatus) throws Exception{
		String sql = "SELECT id,  CONCAT(amount ,'|', exRate, '|',currency) as description  FROM `hridhesh`.`tb_purchasemaster` where vendorid = ? and paidstatus = ? and amount > 0 ";
		Object[] param = new Object[2];
		param[0] = vendorId;
		param[1] = paidStatus;
		return  this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract())) ;
	}


	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPurchaseMaster()
	 */
	@SuppressWarnings("unchecked")
	public List<PurchaseMaster> getPurchaseMaster(int partyAccId, String date) throws Exception{
		//Party ACC Id filer for self company
		//date filter in method set date for last 3 months from controller  old last year 
		String sql = "select pur.id,pur.vendorId,pm.companyName,pur.comments,pur.status,pur.billNo," +
							" dateformat(purchaseDate) as purchaseDate,dateformat(dueDate) as dueDate," +
							" pur.exRate,pur.currency, pur.amount,pur.glTransNo from tb_purchasemaster pur " +
							" inner join tb_partyAcc pa ON pa.id = pur.vendorid inner join tb_partyAddMaster pam " +
							" ON pam.id = pa.partyAddId inner join tb_partyMaster pm ON pm.id = pam.partyId " +
							" left outer join tb_users bur on pur.userid=bur.id "+ 
							" where purchaseDate >= STR_TO_DATE(?, '"+Constants.DATE_FORMAT_VIEWMYSQL+"') and bur.partyAccId = ? ";
		Object[] param = new Object[2];
		param[0] = date;
		param[1] = partyAccId;
		return  this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new PurchaseMasterRowExtract())) ;
	}
	
	public List<PurchaseDetails> getPurchaseDetails(Integer purchaseId) throws Exception{
		String sql = "select pktcode, rate, wt  from tb_purchasedetails where purchaseId = ?";
		Object[] params = new Object[1];
		params[0] = purchaseId;
		return  this.getJdbcDao().query(sql, params, new RowMapperResultSetExtractor(new PurchaseDetailsRowExtract())) ;
	}
	
	/* (non-Javadoc)
	 */
	public int addPurchaseTrf(String fromPartyType, Integer vendorAccId,String accountCode,
			String paymentDate, BigDecimal amount, String ref, BigDecimal exRate,Integer partyAccId, String billNO,Integer status, Integer glTransNo, String localCurr, String partyCurr) throws Exception {
		
		//TODO just copied need to finish 5 sept 2011
		Object param[] = new Object[1];
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfSql = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		Date paymentDt = sdf.parse(paymentDate);
		String trDate = sdfSql .format(paymentDt);
		
		String glDsc = "Purchase from Vendor Id: "+ vendorAccId +" And BillNo: "+billNO+", payment Date :"+paymentDate;
		BigDecimal total = Constants.BIGDECIMAL_ZERO;
		

		//glDsc = fromPartyName;
		//total.add(addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accountCode, ref, glDsc, trDate, amount.negate(), userId, exRate, -1, partyAccId, partyCurr));

		// Get the Account code for Accounts Payable from tb_acc_glacc 
		String accNo = genericService.getSysPref(Constants.creditors_act);
		//glDsc = fromPartyName;
		// check for Insert or Update
		if(status==Constants.STATUS_PERFORMED_INSERT){
			String sql = "select max(id)+1 from tb_refs where type = ?";
			param[0] = Constants.ST_BANKTRF;
			glTransNo = this.getJdbcDao().queryForInt(sql, param);
			sql = "insert into tb_refs (id, type, ref) values (?, ?, ?) ";
			param = new Object[3];
			param[0] = glTransNo;
			param[1] = Constants.ST_BANKTRF;
			param[2] = ref; 
			this.getJdbcDao().update(sql.toString(),param);
			
			//Payable 
			total.add(addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accNo, ref, glDsc, trDate, amount.negate(), partyAccId, exRate, -1, partyAccId, partyCurr));
			//Inventory
			total.add(addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accountCode, ref, glDsc, trDate, amount, partyAccId, exRate, 0, partyAccId, partyCurr));
			
			if(!total.equals(Constants.BIGDECIMAL_ZERO)){
				accNo = genericService.getSysPref(Constants.exchange_diff_act);
				addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accNo, ref, glDsc, trDate, total, partyAccId, exRate, -1, partyAccId, partyCurr);
			}
		}
		if(status==Constants.STATUS_PERFORMED_EDIT){
			accNo = genericService.getSysPref(Constants.creditors_act);
			//TODO ADD CURRENCY TO GL
			//delete
			deleteGlTran(glTransNo.toString());
			
			//addGltrans
			total.add(addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accNo, ref, glDsc, trDate, amount.negate(), partyAccId, exRate, -1, partyAccId, partyCurr));
			total.add(addGlTrans(Constants.ST_SUPPINVOICE, glTransNo, accountCode, ref, glDsc, trDate, amount, partyAccId, exRate, 0, partyAccId, partyCurr));
			
//			total.add(updateGlTran(amount.negate(),accNo,glDsc, partyCurr));
//			total.add(updateGlTran(amount,accountCode,glDsc, partyCurr));
//			if(!total.equals(Constants.BIGDECIMAL_ZERO)){
//				accNo = genericService.getSysPref(Constants.exchange_diff_act);
//				total.add(updateGlTran(amount,accNo,glDsc, partyCurr));
//			}
		}
		if(total.equals(0)){
			accNo = genericService.getSysPref(Constants.exchange_diff_act);
			addGlTrans(Constants.ST_BANKTRF, glTransNo, accNo, ref, glDsc+" adjust ", trDate, total.negate(), partyAccId, exRate, -1, partyAccId, localCurr);
		}
		return glTransNo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updatePurchaseDetails(java.lang.Integer, java.lang.Double, com.basync.b2b.crm.data.PurchaseDetails)
	 */
	public int updatePurchaseDetails(List<PurchaseDetails> lst, Integer glTransNo, Double amount, String Currency, Integer purchaseId, Integer partyAccId) throws Exception{
		//update purchaseMaster
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		Date dt = new Date();
		String today = sdf.format(dt);
		String sql = "update tb_purchasemaster  set amount = ?, glTransNo = ? currency = ? where id = ?";
		Object[] param = new Object[3];
		param[0] = amount;
		param[1] = glTransNo;
		param[2] = purchaseId;
		//update purchaseDetails
		PurchaseDetails pd;
		Object[] params = new Object[5];
		Double oldRate = 0d;
		for(int i = 0; i < lst.size(); i++) {
			pd = lst.get(i);
			//getoldcostprice
			sql = "select rate from tb_purchaseDetails where pktcode = '" + pd.getPktCode()+"'";
			oldRate = (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, null, Double.class);
			sql = "update tb_purchaseDetails  set rate = ?, oldrate = ?, lastmodified = ?, modifiedBy = ? where pktCode = ?";
			params[0] = pd.getRate();
			params[1] = oldRate;
			params[2] = today;
			params[3] = partyAccId;
			params[4] = pd.getPktCode();
			this.getJdbcDao().update(sql, params);
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getGroupGlAcc(java.lang.String)
	 */
	public List<GeneralIdNameStatusEtc> getGroupGlAcc(String accCodes) throws Exception {
		String sql = "select code as id, accName as name, accGroupId as typeId, status from tb_acc_glacc ";
		sql+= "where code in ("+accCodes+") ";
		return (List<GeneralIdNameStatusEtc>)this.getJdbcSlaveDao().query(sql.toString(), null, new RowMapperResultSetExtractor(new GeneralIdNameStatusEtcRowMapper()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#updateGlTran(java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteGlTran(String glTransNo) throws Exception {
		String sql = "delete from tb_gl_trans where transNo=?";
		Object[] param = new Object[1];
		param[0] = glTransNo;
		this.getJdbcDao().update(sql.toString(),param);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getCustomerBalanceList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<CustomerInfo> getCustomerBalanceList(String fromDate,String toDate,String custName,String branch,Integer partyAccId) throws Exception{

		List<Object> paramList = new ArrayList<Object>();
		String sql="select if(p.amount>0,p.amount,null) as credit, pm.companyName, dateformat(p.paymentDate) as trans_date,r.ref as ref,bpa.id as id,t.name as trans_type,p.dsc as comment, pd.exRate as exRate from tb_payment p "+
		" inner join tb_refs r on p.transNo=r.id "+
		" inner join tb_trans_type t on t.id=r.type "+ 
		" inner join tb_partyAcc pa on pa.id=p.partyAccId "+
		" inner join tb_partyAddMaster pam on pam.id=partyAddId "+
		" inner join tb_partyMaster pm on pm.id=pam.partyId "+
		" inner join tb_termmaster tm on tm.id = pa.termId " +
		" left outer join tb_users ur on p.userId=ur.id " +
		" left outer join tb_paymentDetails pd on p.id= pd.paymentId "+
		"  where 1=1 and p.amount>0";	
		
		if(fromDate!="" && toDate!=""){
			sql+=" and p.paymentDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and p.paymentDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
		if(!custName.equals("-1")){
			sql+=" and pa.id = ?";
			paramList.add(custName);
		}
		if(!branch.equals("-1") && !branch.equals("")){
			sql+=" and ur.partyAccId = ? ";
			paramList.add(branch);
		}
		
	  sql+=	"  UNION ALL "+
		" select if(im.totalAmount>0,im.totalAmount,null) as credit,pm.companyName,dateformat(im.invoicedate) as trans_date,im.id as ref ,cpa.id as id,'INVOICE',im.invStatus as comment, odm.exRate as exRate from tb_invoicemaster im "+
		" inner join tb_partyAcc pa on pa.id=im.partyAccId "+
		" inner join tb_partyAddMaster pam on pam.id=partyAddId "+
		" inner join tb_partyMaster pm on pm.id=pam.partyId "+
		" inner join tb_termmaster tm on tm.id = pa.termId " +
		" left outer join tb_users bur on im.userid=bur.id "+
		" left outer join tb_ordermaster odm on im.memoOrderId = odm.ID "+
		" where 1=1 "; 
	  
	  if(fromDate!="" && toDate!=""){
			sql+=" and im.invoicedate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and im.invoicedate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
	  if(!custName.equals("-1")){
			sql+=" and pa.id = ?";
			paramList.add(custName);
		}
		if(!branch.equals("-1") && !branch.equals("")){
			sql+=" and bur.partyAccId = ?";
			paramList.add(branch);
		}
	
	  sql+=" ORDER BY 3";
				
		
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new CustomerBalanceExtrator())) ;
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getSupplierBalanceList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<CustomerInfo> getSupplierBalanceList(String fromDate,String toDate,String custName,String branch, Integer partyAccId) throws Exception{
		List<Object> paramList = new ArrayList<Object>();
		String sql="select if(p.amount>0,p.amount,null) as credit, pm.companyName, dateformat(p.paymentDate) as trans_date,r.ref as ref,bpa.id as id,t.name as trans_type,p.dsc as comment, pd.exRate  as exRate " +
				" from tb_payment p  inner join tb_refs r on p.transNo=r.id  " +
				" inner join tb_trans_type t on t.id=r.type  " +
				" inner join tb_partyAcc pa on pa.id=p.partyAccId  " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId  " +
				" inner join tb_termmaster tm on tm.id = pa.termId " +
				" left outer join tb_users ur on p.userId=ur.id "+
				" left outer join tb_paymentDetails pd on p.id= pd.paymentId "+
				" where 1=1 and p.amount>0 ";
		
		if(fromDate!="" && toDate!=""){
			sql+=" and p.paymentDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and p.paymentDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
		if(!custName.equals("-1")){
			sql+=" and pa.id = ?";
			paramList.add(custName);
		}
		if(!branch.equals("-1") && !branch.equals("0")){
			sql+=" and ur.partyAccId = ?";
			paramList.add(branch);
		}
		
	  sql+="	UNION ALL "+ 
	  " select if(pur.amount>0,pur.amount,null) as credit,pm.companyName,dateformat(pur.purchaseDate) as trans_date,pur.id as ref ,cpa.id as id,'PURCHASE',pur.comments as comment, pur.exRate as exRate  " +
	  " from tb_purchasemaster pur  inner join tb_partyAcc pa on pa.id=pur.vendorId  " +
	  " inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
	  " inner join tb_partyMaster pm on pm.id=pam.partyId  " +
	  " inner join tb_termmaster tm on tm.id = pa.termId " +
	  " left outer join tb_users bur on pur.userId=bur.id "+
	  " where 1=1 and pur.amount>0 ";
	  
	  if(fromDate!="" && toDate!=""){
			sql+=" and pur.purchaseDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and pur.purchaseDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
	  if(!custName.equals("-1")){
			sql+=" and pa.id = ?";
			paramList.add(custName);
		}
		if(!branch.equals("-1") && !branch.equals("0")){
			sql+=" and bur.partyAccId = ?";
			paramList.add(branch);
		}	
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new SupplierBalanceExtractor())) ;
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaymentReport(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<CustomerInfo> getPaymentReport(String fromDate,String toDate,String custName,String branch,Integer partyAccId) throws Exception{

		List<Object> paramList = new ArrayList<Object>();
		String sql=" SELECT p.id as ref,t.name as trans_type,dateformat(p.paymentDate) as trans_date,p.mode,p.amount,p.dsc as comment,p.currency, pd.payType, pd.chequeNo, pa.id,  pm.companyName, pd.exRate" +
				" from tb_payment p left outer join tb_paymentDetails pd on p.id =pd.paymentId  " +
				" inner join tb_refs r on p.transNo=r.id  " +
				" inner join tb_trans_type t on t.id=r.type  " +
				" inner join tb_partyAcc pa on pa.id=p.partyAccId  " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId " +
				" left outer join tb_users ur on p.userId=ur.id "+
				" where p.amount<0 ";
		if(fromDate!="" && toDate!=""){
			sql+=" and p.paymentDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and p.paymentDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
		if(!custName.equals("-1")){
			sql+=" and pa.id = ?";
			paramList.add(custName);
		}
		if(!branch.equals("-1") && !branch.equals("")){
			sql+=" and ur.partyAccId = ?";
			paramList.add(branch);
		}
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new PaymentReportExtractor())) ;
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getPaymentReport(java.lang.String, java.lang.String)
	 */
	public List<CustomerInfo> getTaxReport(String fromDate,String toDate,String branch) throws Exception{

		List<Object> paramList = new ArrayList<Object>();
		String sql="select gl.id as ref,dateformat(gl.transDate) as trans_Date , gl.description as comment, gl.amount,gl.currency, t.name as trans_type,gla.accName, pm.companyName,gl.exRate,  bpa.id as id " +
				" from tb_gl_trans gl inner join tb_acc_glacc gla on gla.code=gl.accountCode " +
				" inner join tb_refs r on r.id=gl.transNo " +
				" inner join tb_trans_type t on t.id=r.type " +
				" inner join tb_payment p on gl.transNo=p.transNo  " +
				" inner join tb_partyAcc pa on pa.id=p.partyAccId  " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId " +
				" left outer join tb_users ur on gl.userId=ur.id "+
				" where gl.accountCode in (2110,2120,2130,2140,2150,2160,5510,5520,5530,5540,5550,5560) ";
		
		if(fromDate!="" && toDate!=""){
			sql+=" and gl.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and gl.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
		if(!branch.equals("-1")&& !branch.equals("")){
			sql+=" and ur.partyAccId = ?";
			paramList.add(branch);
		}
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new TaxReportExtractor())) ;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IMemoService#getBalanceSheet(java.lang.String, java.lang.String)
	 */

	public List<CustomerInfo> getCheckingAccountOpen(String fromDate, String toDate,String accountCode, String branch) throws Exception{

		List<Object> paramList = new ArrayList<Object>();
		String sql="SELECT ba.accountCode,ba.bankAccountName,ba.bankName,ba.branchName,ifnull(sum(b.amount),0) as amount,b.description,dateformat(b.transDate) as transDate  ,b.exRate " +
				" from tb_bankaccounts ba inner join tb_bank_trans b on ba.id=b.bankAccId  " +
				" left outer join tb_users bur on b.userid=bur.id "+  
				" where 1=1" ;
		
				if(fromDate!="" ){
					sql+=" and b.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
					paramList.add(toDate);
				}
				if(!accountCode.equals("")){
					sql+=" and ba.accountCode=? ";
					paramList.add(accountCode);
				}
				if(!branch.equals("-1") && !branch.equals("")){
					sql+=" and bur.partyAccId = ?";
					paramList.add(branch);
				}
				sql+=" group by b.bankAccId";
		
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new BalanceAccountExtractor())) ;	
	}
	/**
	 * This method is used to get the details of checking Account close balance..
	 * @param String
	 * @param String
	 * @param String 
	 * @param Integer 
	 * @return List<CustomerInfo>
	 * @throws Exception
	 */
	public List<CustomerInfo> getCheckingAccountClose(String fromDate, String toDate,String accountCode,  String branch) throws Exception{
		List<Object> paramList = new ArrayList<Object>();
		String sql="SELECT ba.accountCode,ba.bankAccountName,ba.bankName,ba.branchName,ifnull(sum(b.amount),0) as amount,b.description,dateformat(b.transDate) as transDate  ,b.exRate " +
				" from tb_bankaccounts ba inner join tb_bank_trans b on ba.id=b.bankAccId  " +
				" left outer join tb_users bur on b.userid=bur.id "+  
				" where 1=1" ;
		
				if(toDate!="" ){
					sql+=" and b.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
					paramList.add(toDate);
				}
				if(!accountCode.equals("")){
					sql+=" and ba.accountCode=? ";
					paramList.add(accountCode);
				}
				if(!branch.equals("-1") && !branch.equals("")){
					sql+=" and bur.partyAccId = ?";
					paramList.add(branch);
				}
				sql+=" group by b.bankAccId";
		
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new BalanceAccountExtractor())) ;	
	}
	/**
	 * This method is used to get the details of invoice.
	 * @param String
	 * @param String
	 * @param Integer 
	 * @return List<CustomerInfo>
	 * @throws Exception
	 */
	public List<CustomerInfo> getInvoiceDetails(String fromDate, String toDate,String branch) throws Exception{
		List<Object> paramList = new ArrayList<Object>();
		String sql="SELECT sum(im.totalAmount) as amount, pm.companyName, pm.id,odm.exRate as exRate " +
				" from tb_invoicemaster im inner join tb_partyAcc pa on pa.id=im.partyAccId  " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId  " +
				" left outer join tb_users bur on im.userid=bur.id "+
				" left outer join tb_ordermaster odm on im.memoOrderId = odm.ID "+
				" where 1=1 ";
				
		 if(fromDate!="" && toDate!=""){
				sql+=" and im.invoicedate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and im.invoicedate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
				paramList.add(fromDate);
				paramList.add(toDate);
			}
		 if(!branch.equals("-1") && !branch.equals("")){
				sql+=" and bur.partyAccId = ?";
				paramList.add(branch);
			}
		sql+=" group by pm.companyName ";
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new AccountReceivableExtractor())) ;	
	}
	/**
	 * This method is used to get the details of Payment.
	 * @param String
	 * @param String
	 * @param Integer 
	 * @return List<CustomerInfo>
	 * @throws Exception
	 */
	public List<CustomerInfo> getPayDetails(String fromDate, String toDate,String branch) throws Exception{
		List<Object> paramList = new ArrayList<Object>();
		String sql= "Select sum(p.amount) as amount, pm.companyName,pm.id,pd.exRate "+
			" from tb_payment p inner join tb_partyAcc pa on pa.id=p.partyAccId "+  
			" inner join tb_partyAddMaster pam on pam.id=partyAddId "+ 
			" inner join tb_partyMaster pm on pm.id=pam.partyId "+
			" left outer join tb_users bur on p.userid=bur.id "+
			" left outer join tb_paymentDetails pd on p.id= pd.paymentId "+
			" where 1=1 ";
			if(fromDate!="" && toDate!=""){
				sql+= " and p.paymentDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and p.paymentDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
				paramList.add(fromDate);
				paramList.add(toDate);
			 }
			if(!branch.equals("-1") && !branch.equals("")){
				sql+=" and bur.partyAccId = ?";
				paramList.add(branch);
			 }
			sql+= " group by pm.companyName " ;
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new AccountReceivableExtractor())) ;	
	}

	/**
	 * This method is used to get the details of Purchase.
	 * @param String
	 * @param String
	 * @param Integer 
	 * @return List<CustomerInfo>
	 * @throws Exception
	 */
	public List<CustomerInfo> getPurchaseDetailsReport(String fromDate, String toDate,String branch) throws Exception{
		
		List<Object> paramList = new ArrayList<Object>();
		String sql=" SELECT sum(im.amount) as amount, pm.companyName, pm.id  ,im.exRate as exRate " +
				" from tb_purchasemaster im inner join tb_partyAcc pa on pa.id=im.vendorId   " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId   " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId   " +
				" left outer join tb_users bur on im.userid=bur.id  "+ 
				" where 1=1 " ;
		
			if(fromDate!="" && toDate!=""){
				sql+= " and im.purchaseDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and im.purchaseDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
				paramList.add(fromDate);
				paramList.add(toDate);
			 }
			if(!branch.equals("-1") && !branch.equals("")){
				sql+=" and bur.partyAccId = ?";
				paramList.add(branch);
			 }
				sql+= " group by pm.companyName";
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new AccountReceivableExtractor())) ;	
	}
	/**
	 * This method is used to get the details of Brokerage List.
	 * @param String
	 * @param String
	 * @param Integer 
	 * @return List<CustomerInfo>
	 * @throws Exception
	 */
	public List<CustomerInfo> getBrokerageList(String fromDate, String toDate,Integer partyAccId,String branch) throws Exception{
		List<Object> paramList = new ArrayList<Object>();
		String sql="SELECT dateformat(im.invoiceDate) invDate,dateformat(im.dueDate) invDueDate,im.totalAmount  amount, im.invStatus, om.brokrage,pm.companyName brokerageName, om.exRate,cpa.id   " +
				" from tb_invoicemaster im inner join tb_ordermaster om on om.id=im.memoOrderId " +
				" inner join tb_partyAcc pa on pa.id=om.brokerId  " +
				" inner join tb_partyAddMaster pam on pam.id=partyAddId  " +
				" inner join tb_partyMaster pm on pm.id=pam.partyId " +
				" left outer join tb_users bur on im.userid=bur.id "+ 
				" where om.brokrage > 0 ";
		
		if(fromDate!="" && toDate!=""){
			sql+=" and im.invoicedate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and im.invoicedate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
			paramList.add(toDate);
		}
		if(!branch.equals("-1") && !branch.equals("")){
			sql+=" and bur.partyAccId = ?";
			paramList.add(branch);
		}
		return this.getJdbcDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new BrokerageExtractor())) ;	
	}
}

package com.basync.b2b.crm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.BankAccounts;
import com.basync.b2b.crm.data.GlRepData;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.ThreeStrings;
import com.basync.b2b.crm.data.rowExtract.BankAccountRowMapper;
import com.basync.b2b.crm.data.rowExtract.BankTransRowExtract;
import com.basync.b2b.crm.data.rowExtract.GlRepDataRowExtract;
import com.basync.b2b.crm.data.rowExtract.AngRepTransRowExtract;
import com.basync.b2b.crm.data.rowExtract.PartyGLRowMapper;
import com.basync.b2b.crm.data.rowExtract.PartyOutStandingRowExtract;
import com.basync.b2b.crm.data.rowExtract.QueryCodeDescriptionRowExtract;
import com.basync.b2b.crm.data.rowExtract.SaleReportExtract;
import com.basync.b2b.crm.data.rowExtract.StockCheckRepRowExtract;
import com.basync.b2b.crm.data.rowExtract.ThreeStringsRowMapper;
import com.basync.b2b.crm.service.IRepService;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.rowExtract.BalanceQueryCodeExtrator;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.dataobjects.PartyOutStandingDO;
import com.basync.b2b.service.impl.BaseService;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.basync.b2b.data.StockCheckPktDetals;

public class RepServiceImpl extends BaseService implements IRepService {

	public List<GlRepData> getGlRepData(String fromDate, String toDate,
			int accCode) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ")
								.append(" tt.name trans_type, t.transNo ref, dateformat(t.transDate) trans_date, pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_gl_trans t  ")
								.append(" 								left outer join tb_trans_type tt on tt.id = t.type ")
								.append(" 			left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append(" 			left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append(" 			left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append(" 			left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append(" 		where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode = ? ");
		Object[] param = new Object[3];
		param[0] = fromDate;
		param[1] = toDate;
		param[2] = accCode;
		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
	}
	
	

	public Double getGlAccOpBal(String fromDate, int accCode) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_gl_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode = ? ");
		Object[] param = new Object[2];
		param[0] = fromDate;
		param[1] = accCode;
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);

	}
	
	
	public Double getGlAccClBal(String toDate,int accCode) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ifnull(sum(amount),0) closing_bal FROM tb_gl_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountcode = ?	");
		Object[] param = new Object[2];
		param[0] = toDate;
		param[1] = accCode;
		
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);
	
	}

	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IRepService#getSaleReportData(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public PageList getSaleReportData(String fromDt, String toDt,
			String pktNos, String srtIndex,String srtType, int selfPartyAccId, int userId,int page, int pageSize ) throws Exception {
			//Temp change on 27 01 2012 change it immiditely

			StringBuilder sql = new StringBuilder (" select s.id, s.pktCode, odt.rap, s.rate,odt.rate memoRate, (odt.rate*(1-(ifnull(tm.byrComm,0)/100))) sellRate, om.exRate, s.rootPkt roughPkt, om.id memoNo, sp.cts, if(s.pcs =1, sp.sh, concat(sp.shFr,'-', sp.shTo)) sh, if(s.pcs =1, sp.c, concat(sp.cFr,'-', sp.cTo)) c,if(s.pcs =1,  sp.pu, concat(sp.puFr,'-', sp.puTo)) pu, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate , pm.companyName, bpm.companyName brokerName, om.brokrage,om.discount, dateformat(om.orderDate) as orderDate,dateformat(om.dueDate) as dueDate, s.rapPrice, sp.lab, u.partyAccId,tm.termName term, ifnull(om.accType, pa.accType) accType, odt.rejCts, DATEDIFF(om.dueDate,om.orderDate) days, om.comments" )  

				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1) and sp.grpid = 1 ")
				.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
				.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
				.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
				.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
				.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
				.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" inner join tb_users u on  u.id = om.userId ")
				.append(" ");
			
			
			StringBuilder countSql = new StringBuilder (" select count(*) ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1)  and sp.grpid = 1 ")
				.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
				.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
				.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
				.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
				.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
				.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" inner join tb_users u on  u.id = om.userId ");
				
			StringBuilder sqlStr = new StringBuilder(" select count(*) pktCode, ((sum(if(rapPrice>0,odt.rate,0)*sp.CTS)/sum(ifnull(s.rapPrice,0)*sp.CTS)-1)*100) RAP, avg(s.rate) RATE ,round(avg(odt.rate/(1-(ifnull(tm.byrComm,0)/100))),2) selRate, sum(odt.CTS) cts, round(sum(odt.rate*odt.CTS),2) totalRate, round(sum(IFNULL(s.baseRate,0)),2) baseRate " )	
				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1)  and sp.grpid = 1 ")
				.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
				.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType   in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
				.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
				.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
				.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
				.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" inner join tb_users u on  u.id = om.userId ");
	
			//.append(" inner join tb_statusMaster sm on sm.id = s.status ")
			
			List<Object> paramList = new ArrayList<Object>();
			if(selfPartyAccId > -1){
				sql.append(" and u.partyAccId = ? ");
				countSql.append(" and u.partyAccId = ? ");
				sqlStr.append(" and u.partyAccId = ? ");
				paramList.add(selfPartyAccId);
			}
			if(userId > -1){
				sql.append(" and om.userId =  ? ");
				countSql.append(" and om.userId  =  ? ");
				sqlStr.append(" and om.userId  =  ? ");
				paramList.add(userId);
			}
			if(!StringUtils.isEmpty(fromDt) ){
				sql.append(" and om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
				countSql.append( "  and  om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
				sqlStr.append("  and om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
				paramList.add(fromDt);
			}
			if(!StringUtils.isEmpty(toDt) ){
				sql.append(" and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
				countSql.append( " and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
				sqlStr.append(" and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
				paramList.add(toDt);
			}
			if(!StringUtils.isEmpty(pktNos) ){
				sql.append(" and s.pktCode in ("+pktNos+") ");
				countSql.append(" and s.pktCode in ("+pktNos+") ");
				sqlStr.append(" and s.pktCode in ("+pktNos+") ");
			}
			if(!StringUtils.isEmpty(srtIndex)){
				if(srtIndex.equals("sh")||srtIndex.equals("c")||srtIndex.equals("pu")||srtIndex.equals("ct")){
					srtIndex = srtIndex+"_so";
				}
				
				sql.append(" order by u.partyAccId, ").append(srtIndex).append(" ").append(srtType+", om.id ");
			}
			
			Map userData = new HashMap();
			userData = getTotals(paramList.toArray(), sqlStr.toString());
			PageList pageList = this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new SaleReportExtract()));
			pageList.setUserdata(userData);
			return pageList;

	}
/**
 * 
 */
	public Map getTotals(Object[] param, String sql) throws Exception{
		return this.getJdbcDao().queryForMap(sql, param);
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IRepService#getPartyOSData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, int, int)
	 */
	public PageList getPartyOSData(String fromDt, String todate, String pktNos,
			String srtIndex, String srtType, int selfPartyAccId, int userId,
			int page, int pageSize) throws Exception {
		
		
		StringBuilder sql = new StringBuilder (" select s.id, s.pktCode, odt.rap, s.rate, (odt.rate*(1-(ifnull(tm.byrComm,0)/100))) sellRate, om.exRate, s.rootPkt roughPkt, om.id memoNo, sp.cts, if(s.pcs =1, sp.sh, concat(sp.shFr,'-', sp.shTo)) sh, if(s.pcs =1, sp.c, concat(sp.cFr,'-', sp.cTo)) c,if(s.pcs =1,  sp.pu, concat(sp.puFr,'-', sp.puTo)) pu, sp.ct, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate , pm.companyName, bpm.companyName brokerName, om.brokrage, om.orderDate,s.rapPrice, sp.lab, u.partyAccId,tm.termName term, ifnull(om.accType, pa.accType) accType, odt.rejCts " )  
		.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1) and sp.grpid = 1 ")
		.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
		.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
		.append(" inner join tb_statusMaster sm on sm.id = s.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId ")
		.append(" ");
	
	
	StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1)  and sp.grpid = 1 ")
		.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
		.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
		.append(" inner join tb_statusMaster sm on sm.id = s.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId ");
		
	StringBuilder sqlStr = new StringBuilder(" select count(*) pktCode, avg(odt.rap) RAP, avg(s.rate) RATE ,round(avg(odt.rate*(1-(ifnull(tm.byrComm,0)/100))),2) selRate, sum(odt.CTS) cts, round(sum(odt.rate*odt.CTS),2) totalRate, round(sum(IFNULL(s.baseRate,0)),2) baseRate " )	
		.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and (s.status in (4,9,10) or s.pcs <> 1)  and sp.grpid = 1 ")
		.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
		.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType   in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) ")
		.append(" inner join tb_statusMaster sm on sm.id = s.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
		.append(" inner join tb_users u on  u.id = om.userId ");

	//.append(" inner join tb_statusMaster sm on sm.id = s.status ")
	
	List<Object> paramList = new ArrayList<Object>();
	if(selfPartyAccId > -1){
		sql.append(" and u.partyAccId = ? ");
		countSql.append(" and u.partyAccId = ? ");
		sqlStr.append(" and u.partyAccId = ? ");
		paramList.add(selfPartyAccId);
	}
	if(userId > -1){
		sql.append(" and om.userId =  ? ");
		countSql.append(" and om.userId  =  ? ");
		sqlStr.append(" and om.userId  =  ? ");
		paramList.add(userId);
	}
	if(!StringUtils.isEmpty(fromDt) ){
		sql.append(" and om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
		countSql.append( "  and  om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
		sqlStr.append("  and om.orderDate >=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
		paramList.add(fromDt);
	}
	if(!StringUtils.isEmpty(todate) ){
		sql.append(" and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ");
		countSql.append( " and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
		sqlStr.append(" and  om.orderDate <=  STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  ");
		paramList.add(todate);
	}
	if(!StringUtils.isEmpty(pktNos) ){
		sql.append(" and s.pktCode in ("+pktNos+") ");
		countSql.append(" and s.pktCode in ("+pktNos+") ");
		sqlStr.append(" and s.pktCode in ("+pktNos+") ");
	}
	if(!StringUtils.isEmpty(srtIndex)){
		if(srtIndex.equals("sh")||srtIndex.equals("c")||srtIndex.equals("pu")||srtIndex.equals("ct")){
			srtIndex = srtIndex+"_so";
		}
		
		sql.append(" order by u.partyAccId, ").append(srtIndex).append(" ").append(srtType+" ");
	}
	
	Map userData = new HashMap();
	userData = getTotals(paramList.toArray(), sqlStr.toString());
	PageList pageList = this.getJdbcDao().getPageList(sql.toString(),countSql.toString(), page, pageSize, paramList.toArray(), new RowMapperResultSetExtractor(new SaleReportExtract()));
	pageList.setUserdata(userData);
	return pageList;
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IRepService#getPartyOSDataByParty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, int)
	 */
	public PageList getPartyOSDataByParty(String fromDt, String todate,
			String pktNos, String srtIndex, String srtType, int partyAccId,
			int page, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Double getBankAcc(String fromDate, int AccCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
//getBankClBal_kri
	public Double getBankClBal(String toDate) throws Exception {
		// TODO Auto-generated method stub
		
		
			StringBuilder sql = new StringBuilder("SELECT ifnull(sum(amount),0) closing_bal FROM tb_bank_trans t where t.transDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
					
			Object[] param = new Object[1];
			param[0] = toDate;
			
			
			return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);
		
		
		
		
		
	}
	//method getBankStatementOpBal_kri (TODO finding the first three month date sum..
	public Double getBankStatementOpBal(String fromDate) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_bank_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		Object[] param = new Object[1];
		param[0] = fromDate;
		//param[1] = accCode;
		
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);

	}
	
	//method getAccOpBal(String fromDate, int accCode);
//		return this.getJdbcSlaveDao().queryForDouble(sql, params)
	
	//method getBankAccounts_kri
	public Double getBankAccounts(String fromDate, int accCode) throws Exception {
		//StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_bank_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode = ? ");
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_bank_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode = ? ");
		Object[] param = new Object[1];
		param[0] = fromDate;
		//param[1] = accCode;
		
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);

	}	
	//getBankStatementReportData_kri
	public List<GlRepData> getBankAccountsRepData(String fromDate, String toDate) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ")  
								.append(" tt.name trans_type, t.transNo ref, t.transDate trans_date,pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_bank_trans t  ")
								.append(" 								left outer join tb_trans_type tt on tt.id = t.type ")
								.append(" 			left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append(" 			left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append(" 			left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append(" 			left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append(" 		where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		Object[] param = new Object[2];
		param[0] = fromDate;
		param[1] = toDate;

		

		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
	}

//angadiaopening bal_kri
	public Double getAngadiaOpBal(String fromDate) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_angadia_trans t where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		Object[] param = new Object[1];
		param[0] = fromDate;
		//param[1] = accCode;
		
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);

	}
	//getAngadiaClosingBal_kri
	public Double getAngadiaClBal(String toDate) throws Exception {
		// TODO Auto-generated method stub
		
		
			StringBuilder sql = new StringBuilder("SELECT ifnull(sum(amount),0) closing_bal FROM tb_angadia_trans t where t.transDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
					
			Object[] param = new Object[1];
			param[0] = toDate;
			
			
			return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), param, Double.class);
		
		
	   }
//getAngadiaReportData_kri
	public List<GlRepData> getAngadiaReportData(String fromDate, String toDate) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ")//tt.name trans_type, t.transNo ref, t.transDate trans_date, pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" tt.name trans_type, t.transNo ref, t.transDate trans_date,pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_angadia_trans t  ")
								.append(" 								left outer join tb_trans_type tt on tt.id = t.type ")
								.append(" 			left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append(" 			left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append(" 			left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append(" 			left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append(" 		where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		Object[] param = new Object[2];
		param[0] = fromDate;
		param[1] = toDate;

		

		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
	}

	
	//to getting bankAccountName
	public List<GlRepData> getbankAccTrans(String fromDate, String toDate,int bankAccId) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ")  
								.append(" tt.name trans_type, t.transNo ref, dateformat(t.transDate) trans_date,ba.bankAccountName,t.id,ba.bankName,pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_bank_trans t  ")
								.append(" 								left outer join tb_trans_type tt on tt.id = t.type ")
								.append(" 			left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append(" 			left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append(" 			left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append(" 			left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append(" 			left outer join tb_bankAccounts ba on ba.id =t.BankAccId ")
								.append(" 		where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') " );
		//Object[] param = new Object[3]; //list
		//param[0] = fromDate;
		//param[1] = toDate;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(fromDate);
		paramList.add(toDate);
		if(bankAccId > -1){
			sql.append(" and ba.id =  ? ");
			paramList.add(bankAccId);
		}
		//param[2] = bankAccId;
		
		sql.append(" order by ba.id ");
//tb_bankAccounts id   tb_bank_trans  id
		

		return this.getJdbcSlaveDao().query(sql.toString(),paramList.toArray() , new RowMapperResultSetExtractor(new BankTransRowExtract()));
	}
//getBankStatementOpBal_fromdate_openingBalance_kri
	public Double getBankStatementOpBal(String fromDate,int bankAccId) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_bank_trans t") .append(" 			left outer join tb_bankAccounts ba on ba.id =t.BankAccId ")
		.append("where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add( fromDate);
		if(bankAccId > -1){
			sql.append(" and ba.id =  ? ");
			paramList.add(bankAccId);
		}
			sql.append(" order by ba.id ");
		return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), paramList.toArray(), Double.class);

	}
	//getBankClBal_toDate__bankAccId
	public Double getBankClBal(String toDate,int bankAccId) throws Exception {
		// TODO Auto-generated method stub
		
		
			StringBuilder sql = new StringBuilder("SELECT ifnull(sum(amount),0) closing_bal FROM tb_bank_trans t") .append(" 			left outer join tb_bankAccounts ba on ba.id =t.BankAccId ")
		.append("where DATE_FORMAT(t.transDate,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')  < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
					
			List<Object> paramList = new ArrayList<Object>();
			paramList.add( toDate);
			if(bankAccId > -1){
				sql.append(" and ba.id =  ? ");
				paramList.add(bankAccId);
			}
			sql.append(" order by ba.id ");
			
			
			return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), paramList.toArray(), Double.class);
		}
	//getAngadiaTran
	public List<GlRepData> getAngadiaTran(String fromDate, String toDate,int angdAccId) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT ")//tt.name trans_type, t.transNo ref, t.transDate trans_date, pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" tt.name trans_type, t.transNo ref, dateformat(t.transDate) trans_date,pm.companyName companyName,agm.angadiaCoName angadiaCoName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_angadia_trans t  ")
								.append(" 								left outer join tb_trans_type tt on tt.id = t.type ")
								.append(" 			left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append(" 			left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append(" 			left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append(" 			left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append(" 			left outer join tb_angadiaMaster agm on agm.id = t.angadiaId")
								.append(" 		where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
		List<Object> paramList = new ArrayList<Object>();
	
		paramList.add(fromDate);
		paramList.add(toDate);
		if(angdAccId > -1){
			sql.append(" and agm.id =  ? ");
			paramList.add(angdAccId);
		}
		sql.append(" order by agm.id ");
		return this.getJdbcSlaveDao().query(sql.toString(), paramList.toArray(), new RowMapperResultSetExtractor(new AngRepTransRowExtract()));
	}
	public Double getAngadiaClBal(String toDate,int  angdAccId) throws Exception {
		// TODO Auto-generated method stub
		
		
			StringBuilder sql = new StringBuilder("SELECT ifnull(sum(amount),0) closing_bal FROM tb_angadia_trans t ")
			.append("left outer join tb_angadiaMaster agm on agm.id = t.angadiaId  ")
			.append("where t.transDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
			
			List<Object> paramList=new ArrayList<Object>();
			paramList.add(toDate);
			if(angdAccId>-1){
				sql.append(" and agm.id = ?");
				paramList.add(angdAccId);
			}
			
			sql.append(" order by agm.id ");
			
			return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), paramList.toArray(), Double.class);
		
		
	   }
	//getAngadiaOpBal
	public Double getAngadiaOpBal(String fromDate,int angdAccId) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT ifnull(sum(amount),0) opening_bal FROM tb_angadia_trans t ")
			.append("left outer join tb_angadiaMaster agm on agm.id = t.angadiaId  ")
			.append("where t.transDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"')");
			
			List<Object> paramList=new ArrayList<Object>();
			paramList.add(fromDate);
			if(angdAccId >-1){
				sql.append(" and agm.id = ?");
				paramList.add(angdAccId);
			}
			
			sql.append(" order by agm.id ");
			
			return (Double) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql.toString(), paramList.toArray(), Double.class);
		
	}
	//getAngadiaAccListRep_kri
	public List<QueryDescription> getAngadiaAccList(Integer status) throws Exception{
	//public List<BankAccounts> getAngadiaAccList(Integer status) throws Exception {
		String sql = "select id, angadiaCoName description, address, phoneNo, code, dsc, status, opBalance, currBalance, createdDate,createdBy, modifiedDate, modifiedBy,accCode  from tb_angadiaMaster ";
		List<Object> param = new ArrayList<Object>();
		if(status > -1){
			sql+= " where status = ? ";
			param.add(status);
		}
		
		//return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
		//
		//return (List<QueryDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param.toArray(), new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
		return this.getJdbcSlaveDao().query(sql, param.toArray(), new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}
//krishna 17/1/12 to search whole stock report by using status,purity,color and pktCode
	
	
	public List<StockCheckPktDetals> getStockChekRepData(String[] size, String[] status, String[] sh, String[] pu,String[] c, String pktCode,int partyAccId) throws Exception{
		  //select s.id, s.pktCode, odt.rap, s.rate,s.issueDate, odt.rate sellRate, om.exRate, s.rootPkt roughPkt, om.id memoNo, sp.cts, sp.sh, sp.c, sp.pu,sp.sz, sp.ct, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate ,s.status, pm.companyName, bpm.companyName brokerName, om.brokrage, om.orderDate,s.rapPrice, sp.lab, u.partyAccId,tm.termName term, ifnull(om.accType, pa.accType) accType " )  
		StringBuilder sql = new StringBuilder ("select s.id, s.pktCode, o.rap,s.rap srap,s.rapPrice, s.rate,s.issueDate, sellRate, o.exRate, s.rootPkt roughPkt, memoNo, sp.cts, sp.sh, sp.c, sp.pu,sp.sz, sp.ct,totalRate, ROUND(sp.cts* IFNULL(s.rate,s.rate),2) stocktotalRate,sm.statusCode, IFNULL(s.baseRate,0) baseRate ,odtbaseRate, s.status, pm.companyName, bpm.companyName brokerName, o.brokrage, o.orderDate, sp.lab, o.partyAccId,tm.termName term, ifnull(o.accType, pa.accType) accType  from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and sp.grpid = 1  ");
		
		if(!StringUtils.isEmpty(pktCode) ){
			
			sql.append("and s.pktCode like '%"+pktCode+"%'");
			 logger.debug("jjjjjjjjjjjjc"+pktCode);
		}
	
		if(status!=null && status.length>0){
			
			sql.append(" and s.status in('"+StringUtils.toString(status, "','")+"') ");
		}
		if(size!=null && size.length>0){
			
			sql.append(" and sp.SZ_so in('"+StringUtils.toString(size, "','")+"') ");
	     
		}

		if(partyAccId > -1){
					
			sql.append(" and s.partyAccId  =  "+ partyAccId+ "");
			     
		}
	 /* if(frCts>0 && toCts>0){
            sql.append(" and sp.CTS >= "+ frCts +" and sp.CTS <= "+toCts);
            paramList.add(frCts);
            paramList.add(toCts);
            
       }*/

	   if(sh!=null && sh.length>0){
        	 sql.append(" and sp.SH_so in('"+StringUtils.toString(sh, "','")+"') ");
        }
		logger.debug("getSTocRep 4");
	    if(pu!=null && pu.length>0){
	    	 sql.append(" and sp.PU_so in('"+StringUtils.toString(pu, "','")+"') ");
         }
        if(c!=null && c.length>0){
			  sql.append(" and sp.C_so in('"+StringUtils.toString(c, "','")+"') ");
	    }  
        
        sql.append(	" left outer join (select odt.rap, odt.rate sellRate, om.exRate, om.id memoNo, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate,IFNULL(odt.baseRate,0) odtbaseRate, om.brokrage, om.orderDate, om.accType accType,odt.pktId,om.userId, om.termId, om.brokerId,om.partyAccId from tb_orderdetail odt inner join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"+Constants.ORDER_TYPE_CONFRIM +"', 'INV' ) and odt.status  = 2 inner join tb_users u on  u.id = om.userId  ) o on s.Id = o.pktId ")
       // sql.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
		//.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in  ")
		.append(" inner join tb_statusMaster sm on sm.id = s.status ")
		.append(" left outer join tb_termmaster tm on  tm.id = o.termId ")
		.append(" left outer join tb_partyAcc pa on  pa.id = o.partyAccId ")
		.append(" left outer join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" left outer join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = o.brokerId ")
		//.append(" left outer join tb_users u on  u.id = om.userId ")
		.append(" ");

        return  this.getJdbcSlaveDao().query(sql.toString(), null,new RowMapperResultSetExtractor(new StockCheckRepRowExtract()));

	}

	public BigDecimal getPartyOSDue(String toDate, int partyAccId) throws Exception {
		// TODO convert mysql function
		Object[] param = new Object[3];
		param[0] = partyAccId;
		param[1] = toDate;
		param[2] = toDate;
		String sql = "select ifnull(sum(finalAmount),0) total from tb_invoiceMaster im where partyAccId = ? and im.invoiceDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and im.dueDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') "; 
		BigDecimal finalAmt  =(BigDecimal) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, BigDecimal.class); 
	
		return finalAmt;
	   }
	
	public Map<String, BigDecimal> getPartyOS(String toDate, int partyAccId) throws Exception {
		// TODO convert mysql function
		Object[] param = new Object[2];
		param[0] = partyAccId;
		param[1] = toDate;
		String sql = "select ifnull(sum(im.finalAmount),0) total, ifnull(sum(im.finalAmount*o.exRate),0) totalLCL from tb_invoiceMaster im inner join tb_ordermaster o on o.id = im.memoOrderId where im.partyAccId = ? and im.invoiceDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') "; 
		return (Map<String, BigDecimal>)this.getJdbcSlaveDao().getJdbcTemplate().queryForMap(sql, param); 
		
	   }
	public BigDecimal getPartyPayment(String toDate, int partyAccId) throws Exception {
		// TODO convert mysql function
		Object[] param = new Object[2];
		param[0] = partyAccId;
		param[1] = toDate;
		String sql = "SELECT ifnull(sum(pd.amount),0) paidAmt from tb_payment p inner join tb_paymentDetails pd on pd.paymentId =p.id  and p. partyAccId = ? where paymentDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and clearStatus = 1"; 
		BigDecimal finalAmt  =(BigDecimal) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, BigDecimal.class); 
		
		return finalAmt;
	   }
	
	
	public BigDecimal getPartyPendingPayment(String toDate, int partyAccId) throws Exception {
		// TODO convert mysql function
		Object[] param = new Object[2];
		param[0] = partyAccId;
		param[1] = toDate;
		String sql = "SELECT ifnull(sum(pd.amount),0) paidAmt from tb_payment p inner join tb_paymentDetails pd on pd.paymentId =p.id and p. partyAccId = ? where paymentDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and clearStatus = 0 "; 
		BigDecimal pendingPayment  =(BigDecimal) this.getJdbcSlaveDao().getJdbcTemplate().queryForObject(sql, param, BigDecimal.class); 
	
		return pendingPayment;
	   }
	
	public BigDecimal getPartyBal(String toDate, int partyAccId) throws Exception {
		// TODO convert mysql function
		
		Map<String, BigDecimal> map = getPartyOS(toDate, partyAccId);
		BigDecimal os = map.get("total");
		BigDecimal pay = getPartyPayment(toDate, partyAccId);
		
		return os.subtract(pay);
	   }
	
	//getAngadiaAccListRep_kri
	public List<GlRepData> getPartyGL(String fromDate, String toDate,int partyAccId) throws Exception{
		
		
		String sql = " select * from ((select invoiceDate as transDate, dueDate, finalAmount as amount, 'SALES-INVOICE' as type, status from tb_invoiceMaster where partyAccId = ? and invoiceDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and invoiceDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and invStatus <> 'REJ' and status <> 0 ) union (select paymentDate as transDate, '' as duedate, pd.amount as amount, tt.name as type,clearStatus as status from tb_payment p inner join tb_paymentDetails pd on pd.paymentId = p.id inner join tb_trans_type tt on p.type= tt.id where partyAccId = ? and paymentDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and paymentDate <= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and status <> 0 and pd.clearStatus <> 0  ))as t order by t.transDate ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(partyAccId);
		paramList.add(fromDate);
		paramList.add(toDate);
		
		paramList.add(partyAccId);
		paramList.add(fromDate);
		paramList.add(toDate);
		//return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
		
		//return (List<QueryDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param.toArray(), new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
		return this.getJdbcSlaveDao().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new PartyGLRowMapper()));
	}

	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IRepService#getGlAccOpGroupBal(java.lang.String, java.lang.String[])
	 */

	public  List<ThreeStrings> getGlAccOpGroupBal(String fromDate, String accCodes) throws Exception {
		String sql = "SELECT gl.accountCode as str1, ifnull(sum(gl.amount), 0) as str2, acc.accName as str3 " +
							"FROM tb_gl_trans gl inner join tb_acc_glacc acc ON gl.accountCode = acc.code " +
							"where gl.transDate <= STR_TO_DATE(?, '%d-%m-%Y') and gl.accountCode in ("+accCodes+") group by accountcode";

		Object[] param = new Object[1];
		param[0] = fromDate;
		return  (List<ThreeStrings>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new ThreeStringsRowMapper()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IRepService#getGlAccClGroupBal(java.lang.String, java.lang.String[])
	 */
	public List<ThreeStrings> getGlAccClGroupBal(String fromDate, String accCodes) throws Exception {
		String sql = "SELECT gl.accountCode as str1, ifnull(sum(gl.amount), 0) as str2, acc.accName as str3 " +
							"FROM tb_gl_trans gl inner join tb_acc_glacc acc ON gl.accountCode = acc.code " +
							"where gl.transDate <= STR_TO_DATE(?, '%d-%m-%Y') and gl.accountCode in ("+accCodes+") group by accountcode";
		//String sql = "SELECT t.accountCode as id, ifnull(sum(t.amount),0) as description, gla.accName as accountName FROM tb_gl_trans t inner join tb_acc_glacc gla on t.accountCode=gla.code where t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode in ("+accCodes+") group by accountcode";
		Object[] param = new Object[1];
		param[0] = fromDate;
		return  (List<ThreeStrings>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, param, new RowMapperResultSetExtractor(new ThreeStringsRowMapper()));
	}
	
	public  List<QueryCodeDescription> getBlAccOpGroupBal(String fromDate, String accCodes, String branch) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "SELECT t.accountCode as accountCode, ifnull(sum(t.amount),0) as description, gla.accName as accountName , t.exRate,cpa.id as id  " +
				" FROM tb_gl_trans t " +
				" inner join tb_acc_glacc gla on t.accountCode=gla.code " +
				" left outer join tb_users bur on t.userid=bur.id  "+
				" left outer join tb_partyacc cpa on bur.partyAccId=cpa.id "+  
				" left outer join tb_partyaddmaster cpam on cpam.id=cpa.partyAddId "+  
				" left outer join tb_partymaster cpm on cpm.id=cpam.partyId "+
				" where 1=1 ";
		if(fromDate!="" ){
			sql+=" and t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(fromDate);
		}
		if(!branch.equals("-1")){
			sql+=" and cpa.id = ?";
			paramList.add(branch);
		}
		sql+= " and accountCode in ("+ accCodes+") group by accountcode";
		return  (List<QueryCodeDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new BalanceQueryCodeExtrator()));
	}
	
	public  List<QueryCodeDescription> getBlAccClGroupBal(String toDate, String accCodes, String branch) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "SELECT t.accountCode as accountCode, ifnull(sum(t.amount),0) as description, gla.accName as accountName , t.exRate,cpa.id as id  " +
				" FROM tb_gl_trans t " +
				" inner join tb_acc_glacc gla on t.accountCode=gla.code " +
				" left outer join tb_users bur on t.userid=bur.id  "+
				" left outer join tb_partyacc cpa on bur.partyAccId=cpa.id "+  
				" left outer join tb_partyaddmaster cpam on cpam.id=cpa.partyAddId "+  
				" left outer join tb_partymaster cpm on cpm.id=cpam.partyId "+
				" where 1=1 ";
		if(toDate!="" ){
			sql+=" and t.transDate < STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') ";
			paramList.add(toDate);
		}
		if(!branch.equals("-1")){
			sql+=" and cpa.id = ?";
			paramList.add(branch);
		}
		sql+= " and accountCode in ("+ accCodes+") group by accountcode";
		return  (List<QueryCodeDescription>)this.getJdbcSlaveDao().getJdbcTemplate().query(sql, paramList.toArray(), new RowMapperResultSetExtractor(new BalanceQueryCodeExtrator()));
	}
	public List<GlRepData> getGlGroupRepData(String fromDate, String toDate,
			String accCodes) throws Exception {
		StringBuilder sql = new StringBuilder("SELECT accountCode,  ")

								.append(" tt.name trans_type, t.transNo ref, dateformat(t.transDate) trans_date, pm.companyName companyName, if(t.amount<0,t.amount,null)*-1 debit, if(t.amount>0,t.amount,null) credit ")    
								.append(" FROM  tb_gl_trans t  ")
								.append("left outer join tb_trans_type tt on tt.id = t.type ")
								.append("left outer join tb_payment pay on pay.transNo = t.transNo ")
								.append("left outer join tb_partyAcc pa on pay.partyAccId = pa.id  ")
								.append("left outer join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
								.append("left outer join tb_partyMaster pm on pm.id = pam.partyId ")
								.append("where t.transDate >= STR_TO_DATE(?,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and t.transDate <= STR_TO_DATE(? ,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') and accountCode in ("+accCodes+") order by accountcode ");
		Object[] param = new Object[2];
		param[0] = fromDate;
		param[1] = toDate;
		

		return this.getJdbcSlaveDao().query(sql.toString(), param, new RowMapperResultSetExtractor(new GlRepDataRowExtract()));
	}
}


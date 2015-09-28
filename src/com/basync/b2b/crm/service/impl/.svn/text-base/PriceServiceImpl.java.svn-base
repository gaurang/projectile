package com.basync.b2b.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.PacketHistory;
import com.basync.b2b.crm.data.RapPriceData;
import com.basync.b2b.crm.data.rowExtract.MemoHistoryReportExtract;
import com.basync.b2b.crm.data.rowExtract.PacketHistoryRowMapper;
import com.basync.b2b.crm.data.rowExtract.SaleReportExtract;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.service.impl.BaseService;
import com.basync.b2b.util.CommonUtil;

public class PriceServiceImpl extends BaseService implements IPriceService {

	
	
	public PriceServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#insertRapPrices(java.util.List)
	 */
	public int[] insertRapPrices(List<RapPriceData> rapPriceList)
	throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "insert into tb_rapPrices (shape, lowSize, highSize, color, clarity, price, priceDate) values (?, ?, ?,?, ?, ?, ?)";
		
		Object[] param = null;
		List<Object[]> paramList = new ArrayList<Object[]>();
		
		for(int i=0;i<rapPriceList.size();i++){
			RapPriceData rpd= rapPriceList.get(i);
		
			param = new Object[7];
			param[0] = rpd.getShape() ;
			param[1] = rpd.getLowSize(); 
			param[2] = rpd.getHighSize();
			param[3] = rpd.getColor(); 
			param[4] = rpd.getClarity();
			param[5] = rpd.getPrice(); 
			param[6] = smf.format(new Date());
			paramList.add(param);
		}
		int[] x = this.getJdbcDao().batchUpdate(sql, paramList);
		
		//Update stock prices 
		updateRapStockPrice(-1, -1);
		
		return x;
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#getLocalRap(java.lang.String, java.lang.Double, java.lang.String, java.lang.String)
	 */
	public Double getLocalRap(String shape, Double size,String color,String clarity)
	throws Exception {
		String sql = "Select price from tb_rapPrices where shape = ? and lowSize <= ? and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END) >= ? and color = ? and clarity = ? ";
		
		Object[] param = new Object[5];
		param[0] = shape ;
		param[1] = size; 
		param[2] = size;
		param[3] = color; 
		param[4] = clarity;
		
		return (Double) this.getJdbcDao().queryForObject(sql,param,Double.class);
	}
	
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#getLocalRapHistory(java.lang.String, java.lang.Double, java.lang.String, java.lang.String, java.util.Date)
	 */
	public Double getLocalRapHistory(String shape, Double size,String color,String clarity,Date date )
	throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String sql = "Select max(price) from tb_rapPricesHistory where shape = ? and lowSize < ? and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END) > ? and color = ? and clarity = ? and " +
				"trunc(priceDate,'"+Constants.DATE_FORMAT_MINI_MYSQL+"') = ?";
		
		Object[] param = new Object[6];
		param[0] = shape ;
		param[1] = size; 
		param[2] = size;
		param[3] = color; 
		param[4] = clarity;
		param[6] = smf.format(date);
		
		return (Double) this.getJdbcDao().queryForObject(sql,param,Double.class);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateRapStockPrice(java.lang.Integer, java.lang.Integer)
	 */
	public int updateRapStockPrice(Integer pktID, Integer userId)
			throws Exception {

		String insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, round(rp.Price *(1+ (s.rap/100))),now(),? , s.rapPrice " +
				"from tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID = 1 inner join "+
					" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
					" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
					" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END)  where s.status in (1) and s.Rap is not null " ;
		
		List<Object> param = new ArrayList<Object>();
		param.add(userId) ;
		if(pktID > -1){
			insertQ += "and sm.id =  ?";
			param.add(pktID);
		}
		this.getJdbcDao().update(insertQ, param.toArray());
		
		String sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID = 1 inner join "+
				" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
				" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
				" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END) set s.RapPrice  = rp.price , " +
				" s.Rate = round(rp.Price *(1+ (s.rap/100))),sp.rapPriceLab  = rp.price , " +
				" sp.rateLab = round(rp.Price *(1+ (sp.rapLab/100))) where s.status in (1) and s.Rap is not null " ;
		
		param = new ArrayList<Object>();
		if(pktID > -1){
			sql += " and  sm.id =  ?";
			param.add(pktID);
		}
		this.getJdbcDao().update(sql, param.toArray());
		
		//update other lab
		sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID <> 1 inner join "+
		" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
		" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
		" and sp.CTS between rp.lowsize and rp.highsize set sp.rapPriceLab  = rp.price , " +
		" sp.rateLab = round(rp.Price *(1+ (sp.rapLab)/100)) where s.status in (1,2,3,11)  " ;

		return this.getJdbcDao().update(sql, param.toArray());

	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateRapStockPricingBulk()
	 */
	public int updateRapStockPricingBulk() throws Exception {
		return updateRapStockPrice(-1, -1);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateStockPrice(java.lang.Double, java.lang.Double, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public int updateStockPrice(Double rate, Double rap, Integer pktID,
			Integer partyAccId, Integer userId) throws Exception {
		String insertQ = "";
		
		Object[] param = new Object[4];
		if(rap != null){
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, round(((100-?)/100)*rapPrice),now(),?, s.rapPrice from tb_stockmaster where Id = ? and partyAccId = ?";
			param[0] = rap;
		}else{
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate,?,now(),?, rapPrice from tb_stockmaster where Id = ? and partyAccId = ? ";
			param[0] = rate;
		}
		param[1] = userId;
		param[2] = pktID;
		param[3] = partyAccId;
		int i= this.getJdbcDao().update(insertQ, param);
		
		String sql = "update tb_stockmaster sm set rate = ?, rap = (((?*100)/rapPrice)-100)  where Id = ? and partyAccId = ? ";
		param = new Object[4];
		if(rap != null){
			sql = "update tb_stockmaster sm set rate = round(((100+?)/100)*rapPrice), rap = ?  where Id = ? and partyAccId = ? ";
			param[0] = rap;
			param[1] = rap;
		}else{
			param[0] = rate;
			param[1] = rate;
		}
		param[2] = pktID;
		param[3] = partyAccId;
		return this.getJdbcDao().update(sql, param);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateStockPriceBulk(java.lang.String, java.lang.Double, java.lang.Integer, java.lang.Integer)
	 */
	public int updateStockPriceBulk(String whereClause, Double rapChange,
			Integer partyAccId, Integer userId) throws Exception {
		String insertQ = "";
		
		Object[] param = new Object[3];
		insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, round(((100-(rap-?))/100)*rapPrice),now(),?, s.rapPrice from tb_stockmaster sm inner join tb_stockprp sp where sm.partyAccId =? "+whereClause;
		param[0] = rapChange;
		param[1] = userId;
		param[2] = partyAccId;
		
		int i= this.getJdbcDao().update(insertQ, param);
		
		param = new Object[3];
		String 	sql = "update tb_stockmaster sm, tb_stockprp sp set rate = round(((100+(rap -?))/100)*rapPrice), rap = (rap-?)  where sm.partyAccId =?   "+whereClause;
		param[0] = rapChange;
		param[1] = rapChange;
		param[2] = partyAccId;
		
		return this.getJdbcDao().update(sql, param);
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#getPriceHistory(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateStockPriceHistory(java.lang.Double, java.lang.Double, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public int updateStockPriceHistory(Double rate, Double rap, Integer pktID, Integer userId) throws Exception {
	String insertQ = "";
		
		Object[] param = new Object[3];
		if(rap != null){
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, rp.Price *(1+ (?/100)),now(),? , s.rapPrice " +
				"from tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID = 1 inner join "+
					" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
					" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
					" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END)  where s.status in (1) and s.Rap is not null and s.Id = ? ";
			param[0] = rap;
		}else{
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, ?, now(),? , s.rapPrice " +
			"from tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID = 1 inner join "+
				" tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "+
				" rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
				" and sp.CTS between rp.lowsize and (CASE highSize WHEN 5.99 THEN 10 WHEN 10.99 THEN 15 ELSE highSize END)  where s.status in (1) and s.Rap is not null and s.Id = ? ";

			param[0] = rate;
		}
		param[1] = userId;
		param[2] = pktID;
		int i= this.getJdbcDao().update(insertQ, param);
		return i;
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#getPriceHistory(java.lang.String, int)
	 */
	public List<PacketHistory> getPriceHistory(String pktCode, int days
			) throws Exception {
		//For last few months adn for only changes
	String sql = "select ph.pktId,s.pktCode, ph.oldPrice, ph.newPrice , ph.updateDate, ph.rapPrice, ph.userId,u.username, s.status, sm.statusCode from tb_priceHistory ph inner join " +
			"tb_stockmaster s on ph.pktId = s.id and s.pktCode = ? and ph.newPrice <> ph.oldPrice inner join tb_statusMaster sm on sm.id = s.status left outer join tb_users u on u.id = ph.userId  ";
		Object[] param = new Object[1];
		param[0] = pktCode;
		
		
		sql+=" order by updateDate desc";
		return  this.getJdbcDao().query(sql,param,new RowMapperResultSetExtractor(new PacketHistoryRowMapper()));
	}
	
	
	/**
	 * 
	 */
	public List<PacketDetails> getPktMemoHistory(String pktCode, int days) throws Exception {
		//For last few months adn for only changes
		StringBuilder sql = new StringBuilder (" select s.id, s.pktCode, odt.rap, s.rate, odt.rate sellRate, om.exRate, s.rootPkt roughPkt, om.id memoNo, sp.cts, sp.sh, sp.c, sp.pu, sp.ct, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate , pm.companyName, bpm.companyName brokerName, om.brokrage, om.orderDate,s.rapPrice, sp.lab, u.partyAccId,tm.termName term, ifnull(om.accType, pa.accType) accType, odt.returnDateTime, odt.status, odt.issueDateTime " )  
		.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and sp.grpid = 1 and s.pktCode = ? ")
		.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId ")
		.append(" left outer join tb_ordermaster om on om.Id = odt.orderId ")
		.append(" inner join tb_statusMaster sm on sm.id = s.status ")
		.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
		.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = om.brokerId ")
		.append(" inner join tb_users u on  u.id = om.userId ");
		Object[] param = new Object[1];
		param[0] = pktCode;
		sql.append(" order by issueDateTime desc");
		return  this.getJdbcDao().query(sql.toString(),param,new RowMapperResultSetExtractor(new MemoHistoryReportExtract()));
	}
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPriceService#updateStockPrice(java.lang.Double, java.lang.Double, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public int insertPriceLog(Double rate, Double rap, Integer pktID,String pktCode, Integer partyAccId, Integer userId,String section) throws Exception {
		String insertQ = "";
		
		Object[] param = new Object[5];
		if(rap != null){
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice,section) select id,rate, round(((100-?)/100)*rapPrice),now(),?,s.rapPrice,? from tb_stockmaster where partyAccId = ?";
			param[0] = rap;
		}else{
			insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice,section) select id,rate,?,now(),?, rapPrice,? from tb_stockmaster where partyAccId = ? ";
			param[0] = rate;
		}
		param[1] = userId;
		param[2] = section;
		param[3] = partyAccId;
		if(pktID == -1){
			insertQ+=" and pktCode = ? ";
			param[4] = pktCode;
		}else{
			insertQ+=" and id = ? ";
			param[4] = pktID;
		}
		int i= this.getJdbcDao().update(insertQ, param);
		return i;
	}

}
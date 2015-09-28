package com.basync.b2b.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.DemandMaster;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.crm.data.PartyAddMaster;
import com.basync.b2b.crm.data.PartyMasterData;
import com.basync.b2b.crm.data.PartyShipAdd;
import com.basync.b2b.crm.data.Payment;
import com.basync.b2b.crm.data.rowExtract.DemandMasterExtract;
import com.basync.b2b.crm.data.rowExtract.PartyAccountRowExtract;
import com.basync.b2b.crm.data.rowExtract.PartyMasterRowMapper;
import com.basync.b2b.crm.data.rowExtract.PartyOutStandingRowExtract;
import com.basync.b2b.crm.data.rowExtract.PaymentDetailRowMapper;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.SearchPrpData;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.dataobjects.PartyOutStandingDO;
import com.basync.b2b.dataobjects.TermMaster;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.impl.BaseService;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

/**
 * @author gaurang
 *
 */
public class PartyServiceImpl  extends BaseService implements IPartyService {

	private  HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#insertPartyMaster(com.basync.b2b.crm.data.PartyMasterData)
	 */
	public void insertPartyMaster(PartyMasterData partyMasterData)
			throws Exception {
		this.hibernateTemplate.save(partyMasterData);
		Object[] param = new Object[2];
		param[0] = partyMasterData.getActiveFlag();
		param[1] = partyMasterData.getTypeOfParty();
		
		getCacheInterceptor().clearCaches(ICommonService.class, "getPartyByType" ,param);
	}
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#update(com.basync.b2b.crm.data.PartyMasterData)
	 */
	public void update(PartyMasterData partyMasterData) throws Exception {
		this.hibernateTemplate.update(partyMasterData);
		
		Object[] param = new Object[2];
		param[0] = partyMasterData.getActiveFlag();
		param[1] = partyMasterData.getTypeOfParty();
		getCacheInterceptor().clearCaches(ICommonService.class, "getPartyByType" ,param);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#insertPartyAddMaster(com.basync.b2b.crm.data.PartyAddMaster)
	 */
	public void insertPartyAddMaster(PartyAddMaster partyAddMaster)
			throws Exception {
		this.hibernateTemplate.save(partyAddMaster);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#insertPartyShipAdd(com.basync.b2b.crm.data.PartyShipAdd)
	 */
	public void insertPartyShipAdd(PartyShipAdd partyShipAdd) throws Exception {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(partyShipAdd);
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#insertPartyAcc(com.basync.b2b.crm.data.PartyAccData)
	 */
	public void insertPartyAcc(PartyAccData partyAccData) throws Exception {
		this.hibernateTemplate.save(partyAccData);
	}

	
	public PartyMasterData findByPartyMasterById(int id){
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
		
        List list =hibernateTemplate.find(" from PartyMasterData where id = ? ",id);
		return (PartyMasterData)list.get(0);
	}
	
	public PartyAddMaster findByPartyAddMasterById(int partyAddId){
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
		
        List list =hibernateTemplate.find(" from PartyAddMaster where id = ? ",partyAddId);
		return (PartyAddMaster)list.get(0);
	}
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getTermsFactor(java.lang.Integer)
	 */
	public Double getTermsFactor(Integer termId) throws Exception {
		String sql =" SELECT factor description FROM tb_termmaster where id= ?" ;
		Object[] params = new Object[1];
		params[0] =termId;
		return (Double) this.getJdbcDao().queryForObject(sql, params, Double.class);
	}
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPartyService#getAllPartyList(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public PageList getAllPartyList(Integer actiVeflag, String typeOfParty,  String companyName, String broker, String businessType,
			Integer pageNo, Integer pageSize, String orderBy) throws Exception {
		
		StringBuilder sql = new StringBuilder(" SELECT pm.id, pm.companyName, pmb.companyName brokerPartyName,pm.businessType,pm.companyType,pm.typeOfParty, pm.activeFlag FROM  tb_partyMaster pm left outer join tb_partyAcc bpa on bpa.id = pm.brokerPartyId left outer join tb_partyAddMaster bpam on bpam.id = bpa.partyAddId left outer join tb_partyMaster pmb on pmb.id = bpam.partyId and pmb.activeFlag = 1 where 1=1  ") ;
		
		StringBuilder countSql = new StringBuilder(" SELECT count(*) FROM  tb_partyMaster pm left outer join tb_partyMaster pmb on pmb.id = pm.brokerPartyId and pmb.activeFlag = 1 where 1=1  ") ;
		
		List<Object> params = new ArrayList<Object>();
		if(actiVeflag !=-1){
			sql.append(" and pm.activeFlag = ?  ");
			countSql.append(" and  pm.activeFlag = ?  ");
			params.add(actiVeflag);
			
		}if(actiVeflag == 0){
			sql.append(" and pm.activeFlag <> 1  ");
			countSql.append(" and  pm.activeFlag <> 1  ");
		
		}
		if(!StringUtils.isEmpty(typeOfParty)){
			sql.append(" and  pm.typeOfParty = ?  ");
			countSql.append(" and  pm.typeOfParty = ?  ");
			params.add(typeOfParty);
		}
		if(!StringUtils.isEmpty(companyName)){
			sql.append(" and  pm.companyName like  ?  ");
			countSql.append(" and  pm.companyName like  ?  ");
			params.add("%"+companyName+"%");
		}
		if(!StringUtils.isEmpty(broker)){
			sql.append(" and pmb.companyName  ?   ");
			countSql.append(" and pmb.companyName like  ?   ");
			params.add("%"+broker+"%");
		}
		if(!StringUtils.isEmpty(businessType)){
			sql.append(" and  pm.businessType = ?  ");
			countSql.append(" and  pm.businessType = ?  ");
			params.add(businessType	);
		}
		sql.append(" order by "+orderBy+"");
		
		return this.getJdbcSlaveDao().getPageList(sql.toString(), countSql.toString(), pageNo, pageSize, params.toArray(), new RowMapperResultSetExtractor(new PartyMasterRowMapper()));
	}
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getPartyAcc(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<PartyAccData> getPartyAcc(Integer partyActiveFlag,
			Integer accActiveFlag, String accType, String typeOfParty, Integer partyAccId) throws Exception {
		
		StringBuilder sql = new StringBuilder("select pa.id, pm.companyName, pa.termId, tm.termCode, pm.brokerPartyId, pm.subBrokerPartyId, pm.subBroker2PartyId, pa.accType, pam.branchCode from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " )
			.append(" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId inner join tb_users ur on pa.userId=ur.id ")
            .append(" where pm.activeFlag = ? and pa.activeFlag = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(partyActiveFlag);
		param.add(accActiveFlag);
		
		if(!StringUtils.isEmpty(accType)){
			sql.append(" and pa.accType = ? ");
			param.add(accType);
		}	
		
		if(!StringUtils.isEmpty(typeOfParty)){
			sql.append(" and pm.typeOfParty = ? ");
			param.add(typeOfParty);
		}else{
			sql.append(" and pm.typeOfParty <> ? ");
			param.add(Constants.PARTY_TYPE_SELF);
		}	
		//TODO insert ROLE MANAGEMENT for Super User
		if(partyAccId != null ) {
			sql.append(" and ur.partyAccId = ? ");//User id logic from user table for 
			param.add(partyAccId);
		}
		sql.append(" order by pm.companyName ");
		return this.getJdbcSlaveDao().query(sql.toString(), param.toArray(), new RowMapperResultSetExtractor(new PartyAccountRowExtract()));
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getPartyEmailList(java.lang.String)
	 */
	public List<String> getPartyEmailList(String ch) throws Exception {
		String sql = " SELECT email FROM  tb_partyMaster pm where email like ? " ;
		Object[] param = new Object[1];
		param[0] = ch+"%";
		
		return this.getJdbcSlaveDao().queryForList(sql, param, String.class);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#deleteParty(java.lang.Integer)
	 */
	public void deleteParty(Integer partyId) throws Exception {
		Object partyMasterData = this.hibernateTemplate.load(PartyMasterData.class, partyId);
		this.hibernateTemplate.delete(partyMasterData);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#deletePartyAdd(java.lang.Integer)
	 */
	public void deletePartyAdd(Integer partyAddId) throws Exception {
		//Object partyShipAdd = this.hibernateTemplate.load(PartyShipAdd.class, partyAddId);
//		/this.hibernateTemplate.delete(partyShipAdd);
		
		Object partyAddMaster = this.hibernateTemplate.load(PartyAddMaster.class, partyAddId);
		this.hibernateTemplate.delete(partyAddMaster);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getTerms()
	 */
	public List<TermMaster> getTerms() throws Exception {
		return this.hibernateTemplate.find(" from TermMaster order by sort");
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getTermsById(java.lang.Long)
	 */
	public TermMaster getTermsById(Long id) throws Exception {
		
		List list = this.hibernateTemplate.find(" from TermMaster where id  = ? ", id);
		return (TermMaster)list.get(0);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getAddTerm()
	 */
	public void addTerm(TermMaster tm) throws Exception {
		this.hibernateTemplate.save(tm);
		getCacheInterceptor().clearCaches(ICommonService.class, "getTermsList" ,null);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getEditTerm(java.lang.Integer)
	 */
	public void editTerm(TermMaster tm) throws Exception {
		this.hibernateTemplate.update(tm);
		getCacheInterceptor().clearCaches(ICommonService.class, "getTermsList" ,null);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#deleteTerm(com.basync.b2b.dataobjects.TermMaster)
	 */
	public void deleteTerm(TermMaster tm) throws Exception {
		this.hibernateTemplate.delete(tm);
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#addPartyDemand(com.basync.b2b.crm.data.DemandMaster)
	 */
	public void addPartyDemand(DemandMaster dm, UserSession us) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "insert into tb_demand (partyAccId, dmdType, fromDate, toDate, autoMail,  autoMemo, noPriceFlag, createDateTime, createUserId, demandName ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
			Object[] param = new Object[10];
			param[0] = dm.getPartyAccId();
			param[1] = dm.getDmdType();
			param[2] = dm.getFromDate();
			param[3] = dm.getTodate();
			param[4] = dm.getAutoMail();
			param[5] = dm.getAutoMemo();
			param[6] = dm.getNoPrice();
			param[7] = smf.format(new Date()); 
			param[8] = us.getUserId() ;
			param[9] = dm.getDemandName();
			
			int i = this.getJdbcDao().update(sql, param);
			
			sql =  "select max(id) from tb_demand";
			
		int demandId = this.getJdbcDao().queryForInt(sql, null);
		
		List<Object[]> paramList = new ArrayList<Object[]>();
		sql = "insert into tb_demandDetails (demandId, prpId, numFr, numTo, prpIn) values (?, ?, ?, ?, ?) ";
		for (int a=0;a<dm.getSearchPrpList().size();a++){
			SearchPrpData spd = new SearchPrpData();
			spd = dm.getSearchPrpList().get(a);
			param = new Object[5];
			param[0] = demandId;
			param[1] = spd.getPrpId();
			param[2] = spd.getPrpFromNum();
			param[3] = spd.getPrpToNum();
			param[4] = spd.getPrpIn();
			paramList.add(param);
		}
		int z[] = this.getJdbcDao().batchUpdate(sql, paramList);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#editPartyDemand(com.basync.b2b.crm.data.DemandMaster, com.basync.b2b.data.UserSession)
	 */
	public void editPartyDemand(DemandMaster dm, UserSession u)
			throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "update into tb_demand set dmdType = ?,  fromDate = ?,  toDate = ? , autoMail = ?,  autoMemo = ?, noPriceFlag = ?, modifiedDateTime = ?, modifiedUserId = ?, demandName  =? where id = ?";	
			Object[] param = new Object[10];
			param[0] = dm.getDmdType();
			param[1] = dm.getFromDate();
			param[2] = dm.getTodate();
			param[3] = dm.getAutoMail();
			param[4] = dm.getAutoMemo();
			param[5] = dm.getNoPrice();
			param[6] = smf.format(new Date()); 
			param[7] = u.getUserId() ;
			param[8] = dm.getDemandName();
			param[9] = dm.getId();
			
			int i = this.getJdbcDao().update(sql, param);
			
		sql =  "delete from tb_demandDetails where demandId = ?";
		param = new Object[1];
		param[0] = dm.getId();
		this.getJdbcDao().update(sql, null);
		 
		List<Object[]> paramList = new ArrayList<Object[]>();
		sql = "insert into tb_demandDetails (demandId, prpId, numFr, numTo, prpIn) values (?, ?, ?, ?, ?) ";
		for (int a=0;a<dm.getSearchPrpList().size();a++){
			SearchPrpData spd = new SearchPrpData();
			spd = dm.getSearchPrpList().get(a);
			param = new Object[5];
			param[0] = dm.getId();
			param[1] = spd.getPrpId();
			param[2] = spd.getPrpFromNum();
			param[3] = spd.getPrpToNum();
			param[4] = spd.getPrpIn();
			paramList.add(param);
		}
		int z[] = this.getJdbcDao().batchUpdate(sql, paramList);
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getDemandDate(java.lang.Integer)
	 */
	public void getDemandDate(Integer demandId) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getDemandListByParty(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageList getDemandListByParty(Integer partyAccId,
			Integer pageNo, Integer pageSize, String srtIndex, String srtType)
			throws Exception {
		
		String sql = "select d.id, d.partyAccId, pm.companyName, pam.branchCode, pa.termId, tm.termCode, d.fromDate,  d.toDate, d.autoMail , d.autoMemo, d.noPriceFlag, d.createDateTime, d.demandName " +
		"from tb_demand d inner join tb_partyAcc pa on d.partyAccId = pa.id inner join tb_partyAddMaster pam on pam.id = pa.partyAddId "+ 
        " inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where d.partyAccId = ?";

		sql += " order by "+srtIndex +" "+srtType ;

		String countSql = "select count(*) " +
		"from tb_demand d inner join tb_partyAcc pa on d.partyAccId = pa.id inner join tb_partyAddMaster pam on pam.id = pa.partyAddId "+ 
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where d.partyAccId = ?";
		
		Object[] param = new Object[1];
		param[0] = partyAccId;
		
		return this.getJdbcSlaveDao().getPageList(sql.toString(), countSql.toString(), pageNo, pageSize, param, new RowMapperResultSetExtractor(new DemandMasterExtract()));

	}
	public PageList getDemandDetails(Integer dmdId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getPartyIDByPartyAccId(java.lang.Integer)
	 */
	public Integer getPartyIDByPartyAccId(Integer partyAccId) throws Exception {
		String sql = "select partyId " +
					"from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId "+ 
					" inner join tb_partyMaster pm on pm.id = pam.partyId where pa.id = ? ";	
		Object[] param = new Object[1];
		param[0] = partyAccId;
		return (Integer) this.getJdbcSlaveDao().queryForObject(sql, param, Integer.class);
		
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getPartyAddIdByPartyAccId(java.lang.Integer)
	 */
	public Integer getPartyAddIdByPartyAccId(Integer partyAccId)
			throws Exception {
		String sql = "select pa.partyAddId " +
				"from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId "+ 
				" where pa.id = ? ";	
		Object[] param = new Object[1];
		param[0] = partyAccId;
		return (Integer) this.getJdbcSlaveDao().queryForObject(sql, param, Integer.class);
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#isWebPartyCreated(java.lang.Integer)
	 */
	public Boolean isWebPartyCreated(Integer RegistrationId) throws Exception {
		String sql = "select count(*) from tb_partyMaster where registrationId = ? ";	
		Object[] param = new Object[1];
		param[0] = RegistrationId;
		
		Boolean b = false;
		if((Integer)this.getJdbcSlaveDao().queryForObject(sql, param, Integer.class) > 0){
			b = true;
		}
		return b;
	}
	//krishna 9/1/12 insert the data in tb_partyOutStanding table    ( start)
	public int getPartyOtStdRefNo(PartyOutStandingDO p) throws Exception{
		String ref="select count(ReferneceNo) from tb_partyOutStanding where ReferneceNo= ?";
		Object par[]=new Object[1];
		par[0]=p.getReferneceNo();
		 int compref = this.getJdbcDao().queryForInt(ref,par);
		 logger.debug("gggggggggggggggggggggggggggg"+compref);
		 return compref;
		
	}
	public void insertPartyOutStding(PartyOutStandingDO p) throws Exception {
		////String ref="select ReferneceNo from tb_partyOutStanding where ReferneceNo="+p.getReferneceNo();
///logger.debug("gggggggggggggggggggggggggggg"+ref);
		
		//to check the duplicate Reference no
		
		/*
		String ref="select count(ReferneceNo) from tb_partyOutStanding where ReferneceNo= ?";
		Object par[]=new Object[1];
		par[0]=p.getReferneceNo();
		 int compref = this.getJdbcDao().queryForInt(ref,par);
		 logger.debug("gggggggggggggggggggggggggggg"+compref);
		 if(compref>1){
*/
      //if(ref.equals(p.getReferneceNo())){
		//Date DueDate = DateUtil.getDate(p.getDueDate(),"yyyy-MM-dd");
		//Date dte=DateUtil.getDate(p.getFdate());
		
		//Date duDt=dateFormat.parse(DueDate);//(DueDate);
		//Date dat=dateFormat.parse(dte);//(dte);
		//int comprefno=this.getPartyOtStdRefNo(p);
	    //if(compref>1){
		String sql="insert into tb_partyOutStanding (ReferneceNo,PartyID,date,DueDate,BrokerID,Ammount,Tax, Discount,Expence,FinalAmmount,Description) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object param[]=new Object[11];
		param[0]=p.getReferneceNo();
		param[1]=p.getPartyID();
		param[2]=p.getFdate();//dat;
		param[3]=p.getDueDate();//duDt;
		param[4]=p.getBrokerID();
		param[5]=p.getAmmount();
		param[6]=p.getTax();
		param[7]=p.getDiscount();
		param[8]=p.getExpence();
		param[9]=p.getFinalAmmount();
		param[10]=p.getDescription();
		int i = this.getJdbcDao().update(sql, param);
		
		//String sql="update  tb_partyOutStanding set PartyID = ?,date = ?,DueDate = ?,BrokerID = ?,Ammount = ?,Tax = ?, Discount = ?,Expence = ?,FinalAmmount = ?,Description = ? where ReferneceNo = ?";
	    	
	}
//to getting list of partyoutstanding krishna 10/1/12	
 public List<PartyOutStandingDO> getPartyOutStandingList()throws Exception {
	
	 String  sql="select ReferneceNo,PartyID,Date,DueDate,BrokerID,Ammount,Tax,Discount,Expence,FinalAmmount,Description from tb_partyOutStanding";

		return this.getJdbcSlaveDao().query(sql,null,new RowMapperResultSetExtractor(new PartyOutStandingRowExtract()));
 }
 // to getting list of partyoutstanding by referenceNo  krishna 10/1/12 
 public PartyOutStandingDO getPartyOutStandingByRefList(Integer refno)throws Exception {
	 logger.debug("rrrrrrrrrrrrrrr"+refno);
	 String  sql="select ReferneceNo,PartyID,Date,DueDate,BrokerID,Ammount,Tax,Discount,Expence,FinalAmmount,Description from tb_partyOutStanding where ReferneceNo = ?";
	 Object[] param=new Object[1];
     param[0]=refno;	 
     return (PartyOutStandingDO) this.getJdbcSlaveDao().queryForObject(sql, param, new PartyOutStandingRowExtract());
    // this.getJdbcSlaveDao().queryForObject(sql, param,new RowMapperResultSetExtractor(new PartyOutStandingRowExtract()));
	//return this.getJdbcSlaveDao().queryfo(sql,param,new RowMapperResultSetExtractor(new PartyOutStandingRowExtract()));
 }
 //krishna (for updating the partyOutstanding) 
 public int editPartyOutStanding(PartyOutStandingDO p)throws Exception {
	 String sql="update  tb_partyOutStanding set PartyID = ?,date = ?,DueDate = ?,BrokerID = ?,Ammount = ?,Tax = ?, Discount = ?,Expence = ?,FinalAmmount = ?,Description = ? where ReferneceNo = ?";
	 Object[] param=new Object[11];
	 //param[0]=p.getReferneceNo();
	 param[0]=p.getPartyID();
	 param[1]=p.getFdate();//dat;
	 param[2]=p.getDueDate();//duDt;
	 param[3]=p.getBrokerID();
	 param[4]=p.getAmmount();
	 param[5]=p.getTax();
	 param[6]=p.getDiscount();
	 param[7]=p.getExpence();
	 param[8]=p.getFinalAmmount();
	 param[9]=p.getDescription();
	 param[10]=p.getReferneceNo();
	 return this.getJdbcDao().update(sql, param);
 }
 
 public List<Payment> getPaymentDetailReportList(String srtIndex, String srtType,int userId,
			int selfPartyAccId, int paymentId, String partyName, String mode, String frDate, String toDate)throws Exception {
	 StringBuilder	sql = new StringBuilder (" select p.id, p.userId, DATE_FORMAT(p.paymentDate,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') as paymentDate, p.partyAccId,  pm.companyName, pam.branchCode ,p.mode, p.bank,p.bankAccNo, p.amount, p.invId, p.paymentTerm, p.status,p.payFromPartyId,")
	    .append("	pd.invId as invoiceId, pd.amount as amt, pd.bank as bankName, pd.bankAccNo  as bankAcc, pd.clearStatus, pd.chequeNo , DATE_FORMAT(pd.chequeDate,'"+Constants.DATE_FORMAT_VIEWMYSQL+"') as chequeDate, pd.id as paymentDetailId ") 
		.append(" from tb_payment p ")
		.append(" inner join tb_paymentDetails pd on  pd.paymentId = p.id ")
		.append(" inner join tb_partyAcc pa on  pa.id = p.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" inner join tb_users u on  u.id = p.userId ")
		.append(" where 1 = 1 and p.amount > 0 ");
		
		StringBuilder countSql = new StringBuilder (" select count(*) ")
		.append(" from tb_payment p ")
		.append(" inner join tb_paymentDetails pd on  pd.paymentId = p.id ")
		.append(" inner join tb_partyAcc pa on  pa.id = p.partyAccId ")
		.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
		.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" inner join tb_users u on  u.id = p.userId ")
		.append(" where  1 = 1 and p.amount > 0 ");
		List<Object> paramList =  new ArrayList<Object>();
		
		if(selfPartyAccId > -1){
			sql.append(" and u.partyAccId =  ? ");
			countSql.append(" and u.partyAccId =  ? ");
			paramList.add(userId);
		}
		if(userId > -1){
			sql.append("and p.userId =  ? ");
			countSql.append("and p.userId  =  ? ");
			paramList.add(userId);
		}
		if(!StringUtils.isEmpty(partyName)){
			sql.append(" and p.companyName like ? ");
			countSql.append(" and p.companyName  like ? ");
			paramList.add(partyName+"%");
		}
		if(!StringUtils.isEmpty(mode)){
			sql.append(" and p.mode = ? ");
			countSql.append(" and p.mode  = ? ");
			paramList.add(mode);
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
			sql.append(" order by ").append(srtIndex).append(" ").append(srtType+", p.id ");
		}
		return this.getJdbcDao().query(sql.toString(), paramList.toArray(), new RowMapperResultSetExtractor(new PaymentDetailRowMapper()));

 }
 
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPartyService#getPrtyByType(java.lang.Integer, java.lang.String)
	 */
	public List<QueryDescription> getPartyByTypeAuto(Integer actiVeflag,
			String partyType, String filter) throws Exception {
		Object[] param = new Object[3];
		param[0] = actiVeflag;
		param[1] = partyType;
		param[2] = filter==null?"%":filter+"%";
		
		String sql = "select pa.id as id, pm.companyName as description from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " +
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where pm.activeFlag = ? and typeOfParty = ? and "+
		" pm.companyName like ? order by pm.companyName"; 
		List<QueryDescription> list = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	  return list;
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getPartyAcc(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<PartyAccData> getPartyAccAuto(Integer partyActiveFlag,
			Integer accActiveFlag, String accType, String typeOfParty, String filter) throws Exception {
		
		StringBuilder sql = new StringBuilder("select pa.id, pm.companyName, pa.termId, tm.termCode, pm.brokerPartyId, pm.subBrokerPartyId, pm.subBroker2PartyId, pa.accType, pam.branchCode from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " )
			.append(" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where pm.activeFlag = ? and pa.activeFlag = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(partyActiveFlag);
		param.add(accActiveFlag);
		
		if(!StringUtils.isEmpty(accType)){
			sql.append(" and pm.typeOfParty = ? ");
			param.add(accType);
		}	
		if(!StringUtils.isEmpty(typeOfParty)){
			sql.append(" and pm.typeOfParty = ? ");
			param.add(typeOfParty);
		}else{
			sql.append(" and pm.typeOfParty <> ? ");
			param.add(Constants.PARTY_TYPE_SELF);
		}	
		if(!StringUtils.isEmpty(filter)){
			sql.append(" and pm.companyName like ? ");
			param.add(filter+"%");
		}	
		sql.append(" order by pm.companyName ");
		return this.getJdbcSlaveDao().query(sql.toString(), param.toArray(), new RowMapperResultSetExtractor(new PartyAccountRowExtract()));
	}
	
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPartyService#getPartyName(java.lang.Integer)
	 */
	public PartyAccData getPartyName(Integer partyAccId) throws Exception {
		Object[] param = new Object[1];
		param[0] = partyAccId;
		
		String sql = "select pa.id, pm.companyName, pa.termId, tm.termCode, pm.brokerPartyId, pm.subBrokerPartyId, pm.subBroker2PartyId, pa.accType, pam.branchCode from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " +
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where "+
		" pa.id =  ? "; 
		
	  return  (PartyAccData) this.getJdbcDao().queryForObject(sql, param, new PartyAccountRowExtract());
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#getBankDetails(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Map getBankDetails(Integer partyAccId) throws Exception {
		String sql ="select pam.bank, pam.bankAccNo from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " +
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where "+
		" pa.id =  ? ";
		Object[] param = new Object[1];
		param[0] = partyAccId;
		
		return this.getJdbcDao().queryForMap(sql, param);
	}
	
	public List<QueryDescription> getPartyBrokerParties(Integer actiVeflag, Integer brokerId
			) throws Exception {
		Object[] param = new Object[2];
		param[0] = actiVeflag;
		param[1] = brokerId;
		
		String sql = "select pa.id as id, pm.companyName as description from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " +
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where pm.activeFlag = ? and (pm.typeOfParty = 'BKR' or pa.id = ? ) order by companyName"; 
		List<QueryDescription> list = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	  return list;
	}
	/* (non-Javadoc)
	 * @see com.basync.b2b.crm.service.IPartyService#findPartyAccByPartyAccId(java.lang.Integer)
	 */
	public PartyAccData findPartyAccByPartyAccId(Integer partyAccId)
			throws Exception {
		 List list =hibernateTemplate.find(" from PartyAccData where id = ? ",partyAccId);
		return (PartyAccData)list.get(0);
	}
	
	/*public Integer getPartyAccId() throws Exception{
		String sql="";
		
		return id;
	}
*/	
}

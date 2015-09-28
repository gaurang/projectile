package com.basync.b2b.crm.service;

import java.util.List;
import java.util.Map;

import com.basync.b2b.crm.data.DemandMaster;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.crm.data.PartyAddMaster;
import com.basync.b2b.crm.data.PartyMasterData;
import com.basync.b2b.crm.data.PartyShipAdd;
import com.basync.b2b.crm.data.Payment;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.PartyOutStandingDO;
import com.basync.b2b.dataobjects.TermMaster;
import com.basync.b2b.util.PageList;

public interface IPartyService {
	
	/**
	 * 
	 * @param partyMasterData
	 * @return
	 * @throws Exception
	 */
	public void insertPartyMaster(PartyMasterData partyMasterData) throws Exception;
	
	/**
	 * 
	 * @param partyMasterData
	 * @throws Exception
	 */
	public void update(PartyMasterData partyMasterData) throws Exception;
	
	/**
	 * 
	 * @param partyAddMaster
	 * @return
	 * @throws Exception
	 */
	public void insertPartyAddMaster(PartyAddMaster partyAddMaster) throws Exception;
	
	
	/**
	 * 
	 * @param partyShipAdd
	 * @return
	 * @throws Exception
	 */
	public void insertPartyShipAdd(PartyShipAdd partyShipAdd) throws Exception;
	
	/**
	 * Insert new Party Account with terms
	 * @param partyAccData
	 * @return
	 * @throws Exception
	 */
	public void insertPartyAcc(PartyAccData partyAccData) throws Exception;
		
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PartyMasterData findByPartyMasterById(int id)throws Exception;
	
	/**
	 * 
	 * @param partyAddId
	 * @return
	 */
	public PartyAddMaster findByPartyAddMasterById(int partyAddId)throws Exception;
	
	/**
	 * 
	 * @param termId
	 * @return
	 * @throws Exception
	 */
	public Double getTermsFactor(Integer termId) throws Exception;
	
	/**
	 * 
	 * @param actiVeflag
	 * @param typeOfParty
	 * @return
	 * @throws Exception
	 */
	public PageList getAllPartyList(Integer actiVeflag, String typeOfParty, String companyName, String broker, String businesType, Integer pageNo, Integer pageSize,String orderBy)throws Exception;
	
	
	/**
	 * 	
	 * @param partyActiveFlag
	 * @param accActiveFlag
	 * @param accType
	 * @param typeOfParty
	 * @return
	 * @throws Exception
	 */
	public List<PartyAccData> getPartyAcc(Integer partyActiveFlag,Integer accActiveFlag, String accType, String typeOfParty, Integer partyAccId) throws Exception;

	/**
	 * 
	 * @param ch
	 * @return
	 * @throws Exception
	 */
	public List<String> getPartyEmailList(String ch) throws Exception;

	/**
	 * 
	 * @param partyId
	 * @throws Exception
	 */
	public void deleteParty(Integer partyId) throws Exception;
	/**
	 * 
	 * @param partyId
	 * @throws Exception
	 */
	public void deletePartyAdd(Integer partyAddId) throws Exception;
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TermMaster> getTerms() throws Exception;
	
	/**
	 * 	
	 * @return
	 * @throws Exception
	 */
	public TermMaster getTermsById(Long id) throws Exception;
	
	/**
	 * 
	 * @param tm
	 * @return
	 * @throws Exception
	 */
	public void addTerm(TermMaster tm) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public void editTerm(TermMaster tm) throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteTerm(TermMaster tm) throws Exception;
	
	/**
	 * 
	 * @param dm
	 * @param u
	 * @throws Exception
	 */
	public void addPartyDemand(DemandMaster dm, UserSession u)throws Exception;
	
	/**
	 * 
	 * @param dm
	 * @param u
	 * @throws Exception
	 */
	public void editPartyDemand(DemandMaster dm, UserSession u)throws Exception;
	
	
	/**
	 * 
	 * 
	 * @param demandId
	 * @throws Exception
	 */
	public void getDemandDate(Integer demandId)throws Exception;
	
	/**
	 * 
	 * @param partyAccId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList getDemandListByParty(Integer partyAccId, Integer pageNo,Integer pageSize, String srtIndex, String srtType)throws Exception;

	/**
	 * 
	 * @param partyAccId
	 * @param pageNo
	 * @param pageSize
	 * @param srtIndex
	 * @param srtType
	 * @return
	 * @throws Exception
	 */
	public PageList getDemandDetails(Integer dmdId )throws Exception;
	
	/**
	 * 
	 * @param partyAccId
	 * @return
	 * @throws Exception
	 */
	public Integer getPartyIDByPartyAccId(Integer partyAccId)throws Exception;
	
	/**
	 * 
	 * @param partyAccId
	 * @return
	 * @throws Exception
	 */
	public Integer getPartyAddIdByPartyAccId(Integer partyAccId)throws Exception;
	
	/**
	 * 
	 * @param RegistrationId
	 * @return
	 * @throws Exception
	 */
	public Boolean isWebPartyCreated(Integer RegistrationId)throws Exception;
	//krishna 9/1/12 (from partyoutstanding ) -start
	public void insertPartyOutStding(PartyOutStandingDO p) throws Exception; 
	public List<PartyOutStandingDO> getPartyOutStandingList()throws Exception;
	public int getPartyOtStdRefNo(PartyOutStandingDO p) throws Exception;
	public int editPartyOutStanding(PartyOutStandingDO p)throws Exception;
	// public List<PartyOutStandingDO> getPartyOutStandingByRefList(Integer refno)throws Exception;
	public PartyOutStandingDO getPartyOutStandingByRefList(Integer refno)throws Exception;
	//krishna 9/1/12  -end
	 public List<Payment> getPaymentDetailReportList(String srtIndex, String srtType,int userId,
				int selfPartyAccId, int paymentId, String partyName, String mode, String frDate, String toDate)throws Exception ;
	
	 /**
	  * 
	  * @param partyActiveFlag
	  * @param accActiveFlag
	  * @param accType
	  * @param typeOfParty
	  * @param filter
	  * @return
	  * @throws Exception
	  */
	 public List<PartyAccData> getPartyAccAuto(Integer partyActiveFlag,
				Integer accActiveFlag, String accType, String typeOfParty, String filter) throws Exception ;
	
	 /**
	  * 
	  * @param actiVeflag
	  * @param partyType
	  * @param filter
	  * @return
	  * @throws Exception
	  */
	 public List<QueryDescription> getPartyByTypeAuto(Integer actiVeflag,
				String partyType, String filter) throws Exception;

	 /**
	  * 
	  * @param partyAccId
	  * @return
	  * @throws Exception
	  */
	 //Get party Name from partyAccId
	 public PartyAccData getPartyName(Integer partyAccId) throws Exception ;
	 
	 public Map getBankDetails(Integer partyAccId) throws Exception ;
	 
	 public List<QueryDescription> getPartyBrokerParties(Integer actiVeflag, Integer brokerId
		) throws Exception;
	 
	 public PartyAccData findPartyAccByPartyAccId(Integer partyAccId) throws Exception;
	 
}

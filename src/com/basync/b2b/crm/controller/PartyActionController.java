package com.basync.b2b.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.crm.data.PartyAddMaster;
import com.basync.b2b.crm.data.PartyMasterData;
import com.basync.b2b.crm.data.PartyShipAdd;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

public class PartyActionController extends SimpleFormController{
	protected Logger logger = Logger.getLogger(getClass());
	public PartyActionController() {
		setCommandClass(PartyMasterData.class);
		setCommandName("partyMasterData");
	}
	private PartyMasterData partyMasterData;
	
	private IGenericService genericService;
	
	private IPartyService partyService;
	
	private ICommonService commonService;
	
	private IMemoService memoService;
	
	/**
	 * @param partyMasterData the partyMasterData to set
	 */
	public void setPartyMasterData(PartyMasterData partyMasterData) {
		this.partyMasterData = partyMasterData;
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
	 * @return the partyService
	 */
	public IPartyService getPartyService() {
		return partyService;
	}



	/**
	 * @param partyService the partyService to set
	 */
	public void setPartyService(IPartyService partyService) {
		this.partyService = partyService;
	}



	/**
	 * @return the commonService
	 */
	public ICommonService getCommonService() {
		return commonService;
	}



	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
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



	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		super.onSubmit(request, response, command, errors);
		PartyMasterData partyMasterData =(PartyMasterData) command;
		//partyMasterData.getPartyAddMasters().iterator().next().getPartyShipAdd().setPartyAddMaster(partyMasterData.getPartyAddMasters().iterator().next());
		List<PartyAddMaster> pAddMasters = partyMasterData.getPartyAddMasters();
		
		if(RequestUtils.getParam(request, "partyMaster", -1)==1){
			partyMasterData.setPartyAddMasters(null);
		}
		else if(pAddMasters.size()==1){
			PartyAddMaster partyAddMaster =  pAddMasters.iterator().next();
			//if(partyMasterData.getTypeOfParty().equals(Constants.PARTY_TYPE_VENDOR))
			//	partyMasterData.setPartyAddMasters(null);	
			//Default entry for except vendor
			//No data push if address is not entered
			
		}else if(pAddMasters.size() > 1){
			for (int i =0 ;i < pAddMasters.size(); i++) {
				PartyAddMaster partyAddMaster = pAddMasters.get(i);
				if(StringUtils.isEmpty(partyAddMaster.getAddress1())){
					pAddMasters.remove(i);
				}
			}
			if(pAddMasters.size() == 0){
				partyMasterData.setPartyAddMasters(null);	
			}
		}
		if(partyMasterData.getPartyAddMasters()!=null)
			partyMasterData.getPartyAddMasters().iterator().next().getPartyShipAdd().setPartyAddMaster(partyMasterData.getPartyAddMasters().iterator().next());
		
		partyMasterData.setActiveFlag(1);
		if(partyMasterData.getId()==null){
			logger.debug("INSERTED ");
			this.partyService.insertPartyMaster(partyMasterData);
		}else{
			logger.debug("UPDATED ");
			this.partyService.update(partyMasterData);
		}
		
		logger.debug("ID of generated Buyer"+partyMasterData.getId());
		
/*		Set<PartyAddMaster> pAddMasters = new HashSet<PartyAddMaster>(partyMasterData.getPartyAddMasters());
		partyMasterData.setPartyAddMasters(new HashSet<PartyAddMaster>());
		
		Iterator<PartyAddMaster> i =pAddMasters.iterator();
		 while(i.hasNext()){
			 PartyAddMaster partyAddMaster = i.next();
			 PartyShipAdd partyShipAdd =  partyAddMaster.getPartyShipAdd();
			 this.partyService.insertPartyMaster(partyMasterData);
		 }
*/		HashMap<String, Object> paryMasterObj = new HashMap();
		paryMasterObj.put("id",partyMasterData.getId());
		if(partyMasterData.getPartyAddMasters()!=null && partyMasterData.getPartyAddMasters().size() >0)
			paryMasterObj.put("partyAddId",partyMasterData.getPartyAddMasters().get(0).getId());
		
		String json = JSONUtil.convertToJSON(paryMasterObj);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;		
	}
		
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {
		//List<RegistrationViewDO> list = this.partyService.findByPartyMasterById();
		Integer partyId =RequestUtils.getParam(request, "partyId", -1);
		if( partyId == -1)
			partyId =RequestUtils.getParam(request, "id", -1);
		PartyMasterData partyMasterData = new PartyMasterData();
		if(partyId==-1){
			
			List<PartyAddMaster> partyAddMasters=new ArrayList<PartyAddMaster>();
			for(int i=0;i<1;i++){
				PartyAddMaster pAddMaster = new PartyAddMaster();
				
				List<PartyAccData> partyAccs=new ArrayList<PartyAccData>();
				PartyAccData partyAccdata = new PartyAccData();
				partyAccdata.setAccType("E");
				partyAccdata.setTermId(1);
				partyAccdata.setUserId(RequestUtils.getUserSession(request).getUserId());
				partyAccs.add(partyAccdata);
				pAddMaster.setPartyAccs(partyAccs);
		
				pAddMaster.setPartyShipAdd(new PartyShipAdd());
				partyAddMasters.add(pAddMaster);
			}
			partyMasterData.setPartyAddMasters(partyAddMasters);
		}else{
			partyMasterData = this.getPartyService().findByPartyMasterById(partyId);
			
		}
		request.setAttribute("TERMS_LIST", this.getCommonService().getTermsList());
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		request.setAttribute("BROKER_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BROKER));
		
		return partyMasterData;
		
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{       
	  binder.registerCustomEditor(List.class, "PartyAddMaster", new CustomCollectionEditor(List.class)
	  {
	    protected Object convertElement(Object element)
	    {
	        String name = "";

	        if (element instanceof String)
	                name = (String) element;

	        return name != null ? new PartyAddMaster() : null;
	    }
	  });
	}

}

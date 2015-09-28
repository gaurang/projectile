package com.basync.b2b.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

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

public class PartyAddAccController extends SimpleFormController{
	protected Logger logger = Logger.getLogger(getClass());
	public PartyAddAccController() {
		setCommandClass(PartyAddMaster.class);
		setCommandName("partyAddMaster");
	}
	private PartyAddMaster partyAddMaster;
	
	private IGenericService genericService;
	
	private IPartyService partyService;
	
	
	private ICommonService commonService;
	
	private IMemoService memoService;
	/**
	 * @param partyAddMaster the partyAddMaster to set
	 */
	public void setPartyAddMaster(PartyAddMaster partyAddMaster) {
		this.partyAddMaster = partyAddMaster;
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
		
		PartyAddMaster partyAddMaster =(PartyAddMaster) command;
		
		Integer partyId = RequestUtils.getParam(request, "partyId", -1);
		//partyAddMaster.setPartyId(partyId);
		PartyMasterData pmd = this.getPartyService().findByPartyMasterById(partyId);
		
		partyAddMaster.getPartyShipAdd().setPartyAddMaster(partyAddMaster);
		List<PartyAddMaster> partyAddMasters=new ArrayList<PartyAddMaster>();
		
		
		partyAddMasters.add(partyAddMaster);
		pmd.setPartyAddMasters(partyAddMasters);
		
		if(partyId!=-1){
			this.partyService.update(pmd);
		}else{
			//do nothing
		}
		
		HashMap<String, Object> paryMasterObj = new HashMap();
		paryMasterObj.put("id",partyId);
		String json = JSONUtil.convertToJSON(paryMasterObj);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;		
	}
		
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {
		//List<RegistrationViewDO> list = this.partyService.findByPartyMasterById();
		List list = this.getCommonService().getTermsList();
		request.setAttribute("TERMS_LIST",list);
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		Integer partyId =RequestUtils.getParam(request, "partyId", -1);
		if(partyId ==-1){
			//error page
			return null;
		}
		Integer partyAddId =RequestUtils.getParam(request, "partyAddId", -1);
		PartyMasterData pmd = this.getPartyService().findByPartyMasterById(partyId);
		
		PartyAddMaster partyAddMaster = new PartyAddMaster();
		partyAddMaster.setPartyId(partyId);
		if(partyAddId==-1){
			partyAddMaster.setPartyShipAdd(new PartyShipAdd());
			List<PartyAccData> partyAccs=new ArrayList<PartyAccData>();
			PartyAccData partyAccdata = new PartyAccData();
			partyAccdata.setAccType("E");
			partyAccdata.setTermId(1);
			partyAccs.add(partyAccdata);
			partyAddMaster.setPartyAccs(partyAccs);
		}else{
			partyAddMaster = this.getPartyService().findByPartyAddMasterById(partyAddId);
			
		}
		request.setAttribute("partyMasterData", pmd);
		
		return partyAddMaster;
		
	}

	

}

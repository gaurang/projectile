package com.basync.b2b.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.dao.RegistrationDAOImpl;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.service.IUserService;
import com.basync.b2b.util.RequestUtils;

public class WebUser extends AbstractController implements IExceptionHandler {

	protected IPrpService prpService;
	
	protected ICommonService commonService;
	
	private MailSenderPooled mailSender;
	
	protected IStockService stockService;
	
	protected IUserService userService;
	
	protected IPartyService partyService;
	
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param stockService the stockService to set
	 */
	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	private RegistrationDAOImpl registrationDao;
	
	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService =    commonService;
	}

	public IPrpService getPrpService() {
		return prpService;
	}

	public void setPrpService(IPrpService prpService) {
		this.prpService = prpService;
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
	
	/**
	 * @return the stockService
	 */
	public IStockService getStockService() {
		return stockService;
	}
	/**
	 * @param stockService the stockService to set
	 */
	/**
	 * @return the registrationDao
	 */
	public RegistrationDAOImpl getRegistrationDao() {
		return registrationDao;
	}

	/**
	 * @param registrationDao the registrationDao to set
	 */
	public void setRegistrationDao(RegistrationDAOImpl registrationDao) {
		this.registrationDao = registrationDao;
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

	public ModelAndView webUserSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		int userId = RequestUtils.getUserSession(request).getUserId(); 
		request.setAttribute(Constants.PRP_LOV, prpService.getPrpLOV());
		request.setAttribute(Constants.PRP_LIST, prpService.getSearchPrpList());
		
		List<PrpData> prpRsltLst = this.getPrpService().getResultPrpList(Integer.toString(2));
		request.getSession().setAttribute(Constants.PRP_DATA,prpRsltLst);
		
		int searchId = -1;
		if(request.getSession().getAttribute("SEARCH_ID")!=null)
			searchId = Integer.parseInt(request.getSession().getAttribute("SEARCH_ID").toString());
		 
		int smartSearchId = RequestUtils.getParam(request, "smartSearchId", -1);
		
		int s = RequestUtils.getParam(request, "s", -1);
		
		if(smartSearchId > 0)
			searchId = -1;
		if((smartSearchId > -1 || searchId > -1) && s ==-1)
			request.setAttribute("SEARCH_PRP_MAP", prpService.getSearchPrpDetail(searchId, smartSearchId));
		
		//request.setAttribute("OrderList",this.getStockService().getOrdersList(userId, Constants.ORDER_TYPE_REQUEST,-1, 30));
		request.setAttribute(Constants.SMART_SEARCH, prpService.getSmasrtSearch(userId));
		
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		session.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, RequestUtils.getUserSession(request).getPartyAccId()));
		return new ModelAndView("search");
	}

	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Error"+ex.toString());
		ex.printStackTrace();
		return null;
	}
	
	public ModelAndView registrationView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int userId = RequestUtils.getUserSession(request).getUserId();
		if(RequestUtils.getUserSession(request).getRoleId()!=1){
			userId = RequestUtils.getParam(request, "uID", userId);
			logger.debug("####################"+userId);
		}
		
		RegistrationDO rd = registrationDao.getUserDetails(userId);
		if(rd ==null)
			request.setAttribute("registrationDO", new RegistrationDO());
		else
			request.setAttribute("registrationDO", rd);
		return new ModelAndView("Registration");
	}
	
	public ModelAndView contactUs(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("contactUs");
	}
	public ModelAndView payment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("payment");
	}
	public ModelAndView changepwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("changepwd");
	}
	public ModelAndView updatePwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String oldP = RequestUtils.getParam(request, "oldPwd", "");
		String newP = RequestUtils.getParam(request, "newPwd", "");
		int userId = RequestUtils.getUserSession(request).getUserId();
		
		
		int z = this.getUserService().updatePwd(oldP, newP, userId);
		if(z !=1){
			request.setAttribute("error", "Wrong/invalid password");
			return new ModelAndView("changepwd");
		}
		
		request.setAttribute("section", "Change Password");
		request.setAttribute("url", "changepwd.html");
		return new ModelAndView("Success");
	}
	
	public ModelAndView comments(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("comments");
	}
	
	public ModelAndView commentsSend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = RequestUtils.getParam(request, "name", "");
		String contact = RequestUtils.getParam(request, "contact", "");
		String msg = RequestUtils.getParam(request, "comment", "");
		
		this.getMailSender().general_send_mail(  name +" <br/>"+ contact +"<br/>"+ msg, "Query/feedback ");
		request.setAttribute("msg", "We have recived Your Comments");
		return new ModelAndView("comments");
	}
}

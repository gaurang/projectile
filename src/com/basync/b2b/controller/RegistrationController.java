package com.basync.b2b.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.dao.RegistrationDAOImpl;
import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.StringUtils;

public class RegistrationController extends SimpleFormController{
	protected Logger logger = Logger.getLogger(getClass());
	public RegistrationController() {
		setCommandClass(RegistrationDO.class);
		setCommandName("registrationDO");
	}
	private RegistrationDAOImpl registrationDAO;
	private MailSenderPooled mailSender;
	protected ICommonService commonService;
	
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
	
	public void setRegistrationDAO(RegistrationDAOImpl registrationDAO) {
		this.registrationDAO = registrationDAO;
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
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		super.onSubmit(request, response, command, errors);
		RegistrationDO registrationDO =(RegistrationDO) command;
		if(registrationDO.getID()!=null && Integer.parseInt(registrationDO.getID()) > 0){
			registrationDAO.updateUser(registrationDO);
			request.setAttribute("url", "webRegistrationView.html");
			request.setAttribute("section", "");
			//sendRegistrationMail(registrationDO, "User Profile Edit");
		}else{
			registrationDAO.registerUser(registrationDO);
			request.setAttribute("url", "webRegistration.html");
			request.setAttribute("section", "");
			//sendRegistrationMail(registrationDO, "Registration Mail");
		}
			
		logger.debug("$$$$$$$$$$$$$$$$$$ in onSubmit"+registrationDO.getLoginName());
		
		return new ModelAndView(new RedirectView("webUsers.html"));		
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		return new RegistrationDO();
	}
		
	public void sendRegistrationMail(RegistrationDO registrationDO, String subject) throws Exception{
		 String[] elemArr = {"loginName", "companyName",
				 "brokerName", "businessType",
				 "memberOfAssociation", "cEmail",
				 "alternateEmail", "webSiteName",
				 "comments", "cName",
				 "cMobile", "tradeCompanyName",
				 "contactPerson",
				 "phoneNo1",
				 "trMobile1",
				 "tradeCompanyName1",
				 "contactPerson1",
				 "phoneNo2",
				 "trMobile2",
				 "designation",
				 "address",
				 "city",
				 "state",
				 "country",
				 "pinCode",
				 "telphone1",
				 "telphone2",
				 "fax",
				 "shipName",
				 "shipDesignation",
				 "shipAddress",
				 "shipCity",
				 "shipState",
				 "shipCountry",
				 "shipPinCode",
				 "shipTelphone1",
				 "shipTelphone2",
				 "shipFax", "shipMobile","emailUpdates" };
		 
		 StringBuilder  str = new StringBuilder("<div> <b>Profile Details</b></div>");
		 str.append("<table border=1>");
		 for (int i = 0; i < elemArr.length; i++) {
			 str.append("<tr><td>");
			 String value= registrationDAO.getObjVal(elemArr[i],registrationDO,"");
			 str.append(elemArr[i]+" </td><td> "+ (StringUtils.isEmpty(value)==true?"":value));
			 str.append("</td></tr>");
		 }
		 str.append("</table>");
		 String email=this.getCommonService().getPropertiesByName("b2b.sales.email");
		 String cc=this.getCommonService().getPropertiesByName("b2b.owner.email");
		
		 mailSender.general_send_mail(email, cc, str.toString(), subject);
	}

}

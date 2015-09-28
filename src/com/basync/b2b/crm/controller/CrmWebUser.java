package com.basync.b2b.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.controller.AbstractController;
import com.basync.b2b.controller.IExceptionHandler;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.MD5Utils;
import com.basync.b2b.util.RequestUtils;

public class CrmWebUser  extends AbstractController implements IExceptionHandler {

	
	protected ICommonService commonService;
	
	private MailSenderPooled mailSender;
	
	protected IGenericService genericService;
	
	protected IMemoService memoService;


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

	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * 	Web user Logout
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logOutCrm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.getSession().removeAttribute("userSession");
		request.getSession().invalidate();

		// delete cookie in memory.
		RequestUtils.delCookie(response, "userId");
		RequestUtils.delCookie(response, "userKey");

		return new ModelAndView("indexCrm");
		
	}
	
	/**
	 * Admin panel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView indexCrm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession usersession = this.getGenericService().genUserSession(request);
		if(usersession.getUserId() >0 && usersession.getPartyAccId() != null){
			return new ModelAndView(new RedirectView("projectile.html"));
		}else {
			return new ModelAndView("login");
		}
	}
	
	/**
	 * Login admin panel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logInCrm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("IS IT VALID logInAdmin ");
		String userName = RequestUtils.getParam(request, "username", "kunal");
		String password = RequestUtils.getParam(request, "password", "kunal");
			
		//Same object used with couple of changes to crm usersession login
		UserSession userSession = getGenericService().getCRMUserSession(userName, password);
		
		
		if(userSession != null){
			request.getSession(true).setAttribute(Constants.USER_SESSION,userSession );
			logger.debug("IS IT VALID  ");
		}
		else{
			request.setAttribute("INVALID", "Invalid User Name/Password");
			logger.debug("IS IT NOT VALID  ");
			return new ModelAndView("indexCrm");
		}

		
		//session Fillings
		HttpSession session = request.getSession();
		
		// Save userId/userKey into Client cookie , rebuild userSession use it.
		RequestUtils.addCookie(response, "userId", Integer.toString(userSession.getUserId().intValue()));
		RequestUtils.addCookie(response, "userKey", MD5Utils.to_MD5(userSession.getUserId().intValue()+ Constants.COOKIE_SECRET));
		
		return new ModelAndView(new RedirectView("projectile.html"));
	}
	
	
}

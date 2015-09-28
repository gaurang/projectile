package com.basync.b2b.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.service.IUserService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

public class DefaultController extends AbstractController implements IExceptionHandler {
	
	
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
	 * Web user login
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logIn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userName = RequestUtils.getParam(request, "username", "kunal");
		String password = RequestUtils.getParam(request, "password", "kunal");
			
		UserSession userSession = getUserService().getUserSession(userName, password);
		if(userSession != null){
			request.getSession(true).setAttribute(Constants.USER_SESSION,userSession );
			this.commonService.loginLog(userSession.getUserId(), request.getRemoteAddr());
		}
		else{
			request.setAttribute("INVALID", "Invalid User Name/Password");
			return new ModelAndView("index");
		}
		return new ModelAndView(new RedirectView("webUserSearch.html"));
	}

	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Error"+ex.toString());
		ex.printStackTrace();
		return null;
	}
	
	/**
	 * 	Web user Logout
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.getSession().invalidate();
		return new ModelAndView("index");
		
	}
	
	/**
	 * Index page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}
	
	/**
	 * Registration page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView registration(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("registrationDO", new RegistrationDO());
		return new ModelAndView("Registration");
	}
	/**
	 * ALl users list in CP
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView users(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		if(RequestUtils.getUserSession(request).getRoleId() != 3)
			return new ModelAndView("index.html");
		int pageNo= RequestUtils.getParam(request, "page", 1);
		int pageSize= RequestUtils.getParam(request, "pageSize", -1);
		
		request.setAttribute("pageList",this.getUserService().getUsers(pageSize, pageNo, -1, ""));
		return new ModelAndView("users");
	}
	
	/**
	 * User edit CP 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(RequestUtils.getUserSession(request).getRoleId() != 3)
			return new ModelAndView("index.html");
		
			request.setAttribute("byrList", this.getUserService().getBuyersQD());
			request.setAttribute("termsList", this.getCommonService().getTermsList());
		return new ModelAndView("userEdit");
	}
	
	/**
	 * Updation of user details by admin
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			if(RequestUtils.getUserSession(request).getRoleId() != 3)
				return new ModelAndView("index.html");
			
			String uName = RequestUtils.getParam(request, "uNm", "");
			int termId = RequestUtils.getParam(request, "trm", -1);
			String pwd = RequestUtils.getParam(request, "pwd", "");
			int status = RequestUtils.getParam(request, "status", 0);
			
			int i =0;
			if(RequestUtils.getParam(request, "edit", -1) == 1)
			{
				i = this.getUserService().updateUser(uName, pwd, status, termId);
				logger.debug("OK Updated");
			}else{
				//TODO
			}		
			if(i>0)
				request.setAttribute("msg", "Successfully Updated");
			return new ModelAndView(new RedirectView("users.html"));
	}
	
	/**
	 * WC integration
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView worldclock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("worldclock");
	}
	
	/**
	 * Admin panel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView indexAdmin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("webpage/indexAdmin");
	}
	
	/**
	 * Login admin panel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logInAdmin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("IS IT VALID logInAdmin ");
		String userName = RequestUtils.getParam(request, "username", "kunal");
		String password = RequestUtils.getParam(request, "password", "kunal");
			
		UserSession userSession = getUserService().getUserSession(userName, password);
		
		if(userSession != null && userSession.getRoleId()!=1){
			request.getSession(true).setAttribute(Constants.USER_SESSION,userSession );
			logger.debug("IS IT VALID  ");
		}
		else{
			request.setAttribute("INVALID", "Invalid User Name/Password");
			logger.debug("IS IT NOT VALID  ");
			return new ModelAndView("webpage/indexAdmin");
		}
		return new ModelAndView(new RedirectView("users.html"));
	}
	
	/**
	 * Terms page if available
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termsNCond(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("termsNCond");
	}
	
	/**
	 * To forgot password jsp
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forgotpwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("forgotpwd");
	}
	
	/**
	 * Redirect on index page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView diamond_stock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(new RedirectView("index.html"));
	}
	
	/**
	 * Mail sent for forgot password
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forgotpwdmail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String email = RequestUtils.getParam(request, "email", "");
		//String companyName = RequestUtils.getParam(request, "company", "");
		String loginName = RequestUtils.getParam(request, "login", "");
		int seqQuestion = RequestUtils.getParam(request, "question", -1);
		String answer = RequestUtils.getParam(request, "answer", "");
		

		
		int userId = getUserService().checkUserData(loginName, seqQuestion, answer, email);
		
		String password = StringUtils.randomString(6);
		String success="ok", json="";
		
		if(userId > 0){
			int i = this.getUserService().updatePwd(password, userId);
			if(i > 0){
				String msgDtls ="<div><b>Forgot Password</b></div><br/><br/>";
				msgDtls+= "User Name  : "+loginName +"<br/>";
				//msgDtls+= "Comapny Name  : "+companyName +"<br/>";
				msgDtls+= "email  : "+email +"<br/>";
				msgDtls+= "password  : "+password +"<br/>";
				
				msgDtls+= "<br/> Kindly login to website using following password";
				/*		String msgDtls ="<div><b>Forgot Password</b></div><br/><br/>";
				msgDtls+= "User Name  : "+loginName +"<br/>";
				msgDtls+= "Comapny Name  : "+companyName +"<br/>";
				msgDtls+= "email  : "+email +"<br/>";
				
				msgDtls+= "<br/> Kindly review Clients details and then cordinate with client to change their password";
				String emailId=this.getCommonService().getPropertiesByName("b2b.sales.email");
				String cc=this.getCommonService().getPropertiesByName("b2b.owner.email");
				*/		
				this.mailSender.general_send_mail(email,  msgDtls, "H.Riddhesh & Co. : Request to change password");
				logger.debug("PASSWORD      "+password);
			}
				
		}else{
			success = "fail";
		}
		
		String[] data = new String[1];
		data[0]=success;
		
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * To display cert on jsp for hiding url
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView cert(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("cert");
	}
	
	/**
	 * Send Comments mail
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView commentsSend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = RequestUtils.getParam(request, "name", "");
		String contact = RequestUtils.getParam(request, "contact", "");
		String msg = RequestUtils.getParam(request, "comment", "");
		String email = RequestUtils.getParam(request, "email", "");
		String city = RequestUtils.getParam(request, "city", "");
		String state = RequestUtils.getParam(request, "state", "");
		
		this.getMailSender().general_send_mail(  "Name : "+ name +" <br/>Contact : "+ contact +"<br/> Email : "+ email +"<br/>City : "+ city +"<br/>State : "+ state +"<br/>"+ msg, "Query/feedback ");
		request.setAttribute("section", "We have recived Your Comments");
		request.setAttribute("url", "contact.jsp");
		return new ModelAndView("Success");
	}
	
	/**
	 * Check UserId of user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkUserId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userName = RequestUtils.getParam(request, "user", "");
		String success="ok", json="";
		
		if(getUserService().checkUserId(userName)>0){
			success="fail";
		}
		String[] data = new String[1];
		data[0]=success;
		
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	public ModelAndView bkup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("crm/bkup");
	}
	public ModelAndView bkupDB(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int processFlag = CommonUtil.backUpScript(CommonUtil.getBkupDetails());
		if (processFlag == 0){
			request.setAttribute("msg","BackUp successFully Done");
		}else{
			request.setAttribute("msg","BackUp failed");
		}
		
		return new ModelAndView("crm/bkup");
	}
	public ModelAndView bkupDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, String> sqlMap = CommonUtil.getBkupDetails();
		int processFlag = CommonUtil.backUpScript(sqlMap);
		processFlag = CommonUtil.emptyDB(sqlMap);
		if (processFlag == 1){
			request.setAttribute("msg","BackUp/Del successFully Done");
		}else{
			request.setAttribute("msg","BackUp/Del failed");
		}
		return new ModelAndView("crm/bkup");
	}
	public ModelAndView restore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("crm/restore");
	}
	public ModelAndView restoreDB(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			HashMap<String, String> sqlMap = CommonUtil.getBkupDetails();
			
			String username = RequestUtils.getParam(request, "username", "");
			String password = RequestUtils.getParam(request, "password", "");
			if(username.equals(sqlMap.get("username")) && password.equals(sqlMap.get("password")))
			{
				int processFlag = CommonUtil.restoreScript(sqlMap);
				if (processFlag == 1){
					request.setAttribute("msg","Restore successFully Done");
				}else if(processFlag == -2){
					request.setAttribute("msg","Restore failed as Database already present");
				}else {
					request.setAttribute("msg","Restore failed");
				}
				
			}else{
				request.setAttribute("msg","Wrong User/password");	
			}
			return new ModelAndView("crm/restore");
		}
	
}

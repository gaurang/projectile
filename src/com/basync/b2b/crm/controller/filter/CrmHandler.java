package com.basync.b2b.crm.controller.filter;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;

/**
 * Servlet Filter implementation class CrmHandler
 */
public class CrmHandler extends HandlerInterceptorAdapter { 

	
	private IGenericService genericService;
	private static final Logger logger = Logger.getLogger(PageList.class);
		public IGenericService getGenericService() {
			return genericService;
		}
		public void setGenericService(IGenericService genericService) {
			this.genericService = genericService;
		}

		/* (non-Javadoc)
		 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
		 */
		@Override
		public void postHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler, ModelAndView modelAndView)
		throws Exception {
			// TODO Auto-generated method stub
			super.postHandle(request, response, handler, modelAndView);
		}
		/* (non-Javadoc)
		 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
		 */
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			UserSession usersession = this.getGenericService().genUserSession(request);
			if(request.getParameter("webLink")!=null && request.getRequestURI()!=null) {
				String webURL = request.getRequestURI();
				webURL = webURL.substring(webURL.lastIndexOf("/")+1);
				request.getSession(true).setAttribute("webURL", webURL);
				logger.debug("##############"+webURL);
			}
			
			if(usersession.getUserId() >0 && usersession.getPartyAccId() != null){
				logger.debug("###############1111111111"+usersession.getUserId());
				return true;
			}
			else{
				logger.debug("###############"+usersession.getUserId());
			   	throw new ModelAndViewDefiningException(new ModelAndView(new RedirectView("indexCrm.html")));
			}
		}

}

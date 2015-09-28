package com.basync.b2b.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.data.UserSession;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;

public class WebHandler extends HandlerInterceptorAdapter {
	
private ICommonService commonService;
private static final Logger logger = Logger.getLogger(PageList.class);
	public ICommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
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
		UserSession usersession = RequestUtils.getUserSession(request);
		if(usersession.getUserId() >0){
			logger.debug("###############1111111111"+usersession.getUserId());
			return true;
		}
		else{
			logger.debug("###############"+usersession.getUserId());
		   	throw new ModelAndViewDefiningException(new ModelAndView(new RedirectView("index.html")));
		}
	}
}

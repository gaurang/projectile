
package com.basync.b2b.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.RequestUtils;

/**
 * Spring will pass exceptions to this class and this class will dispatch
 * them to different pages
 * 
 * @author Danny Qiu
 * @version 0.1
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	protected Logger logger = Logger.getLogger(getClass());
	private MailSenderPooled mailSender;
	private ICommonService commonService;

	/**
	 * @return the mailSender
	 */
	public MailSenderPooled getMailSender() {
		return mailSender;
	}

	/**
	 * 
	 * @param mailSender the mailSender to set 
	 */
	public void setMailSender(MailSenderPooled mailSender) {
		this.mailSender = mailSender;
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

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView();
		logger.error("-------------------------[Exception start]--------------------------------");
		logger.error("Exception message   :  " + ex.getMessage());
		logger.error("Exception type      :  " + ex.getClass());
		logger.error("Exception source    :  " + handler.getClass());
		logger.error("Exception trace     :  ", ex);
		logger.error("-------------------------[Exception end]----------------------------------");
		
		sendErrorMail(request, response, handler, ex);
		
		if (ex instanceof DaoException) {
			mv = getErrorModelAndView(request, response, handler, ex);
		} else if (ex instanceof ServiceException) {
			mv = getErrorModelAndView(request, response, handler, ex);
		} else if (ex instanceof DataAccessException) {
			mv = getErrorModelAndView(request, response, handler, ex);
		} else if (ex instanceof NullPointerException) {
			mv = getErrorModelAndView(request, response, handler, ex);
		} else if (ex instanceof Exception) {			
			mv = getDefaultModelAndView(request, response, ex);
		}

		mv.addObject("errors", ex.getMessage());
		return mv;
	}

	public ModelAndView getDefaultModelAndView(HttpServletRequest request,
			HttpServletResponse response, Exception ex) {
		
		try {
		} catch (Exception e) {			
			//e.printStackTrace();
			logger.error("Error occured while calling getBrouchurewareProperties: " + e.getMessage(), e);
		}
		
		return new ModelAndView("uncaughtException").addObject("exception", ex);
	}

	private ModelAndView getErrorModelAndView(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = null;
		/*if (handler instanceof IExceptionHandler) {
			IExceptionHandler controller = (IExceptionHandler) handler;

			try {
				mv = controller.dealException(request, response, ex, "");
			} catch (Exception e) {
				logger.error(e.getMessage());
				mv = getDefaultModelAndView(request, response, e);
			}
		}
		if (mv == null) {
			mv = getDefaultModelAndView(request, response, null);
		}*/
		
		//In event of any exception, user shall be forwarded to the error page
		mv = getDefaultModelAndView(request, response, ex);
		
		return mv;
	}
	
	/**
	 * This method will send email to the admin with the details of the error
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param handler Object
	 * @param ex Exception
	 */
	private void sendErrorMail(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		String lineSeparator = "<br>";
		String subject = "TAG Error";
		String message = "";
		try {
			
			if(ex instanceof CannotCreateTransactionException) {
				subject = "TAG Database Error";
			}
			
			StringBuffer msgInfo = new StringBuffer();
			msgInfo.append("An Unknown Error has occurred on TAG application");
			msgInfo.append(lineSeparator);
			msgInfo.append(lineSeparator);
			
			msgInfo.append("Exception message : " + ex.getMessage());
			msgInfo.append(lineSeparator);
			msgInfo.append("Exception type : " + ex.getClass());
			msgInfo.append(lineSeparator);
			msgInfo.append("Exception source : " + handler.getClass());
			msgInfo.append(lineSeparator);
					
			//Display StackTrace
			msgInfo.append(lineSeparator);
			msgInfo.append("Error StackTrace : ");
			msgInfo.append(lineSeparator);
			StackTraceElement[] st = ex.getStackTrace();

			for (Object o : st) {
				msgInfo.append(o.toString());
				msgInfo.append(lineSeparator);
			}
			
			//Display request info
			msgInfo.append(RequestUtils.getAllRequestInfo(request));
			
			message  = msgInfo.toString();
			String email = getCommonService().getPropertiesByName("tracking.error.email");
			
			getMailSender().general_send_mail(email, message, subject);
			
		} catch (Exception e) {
			logger.error("Error sending email : ", e);
			logger.info("Email Message : " + subject + "\n" + message);
		}
	}

}

package com.basync.b2b.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.crm.webservice.RapPriceWebService;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		Logger logger = Logger.getLogger(InitServlet.class);
		logger.debug("INIT servlet ");
	//	logger.info("Creating scheduling....");
		
	//	logger.info("In scheduling Job");
	//	StockDownload stockDownlaod  = new StockDownload();
	//    Timer timer = new Timer();
	    //System.out.println(getTomorrowMorning4am());
	//    timer.scheduleAtFixedRate(stockDownlaod, getTomorrowMorning4am(), f_PER_DAY);
	 //   logger.info("In scheduling Job at time "+getTomorrowMorning4am());
	//	logger.info("Created scheduling....");
		
		ServletContext context = config.getServletContext();
		    WebApplicationContext applicationContext =
		            WebApplicationContextUtils
		            .getWebApplicationContext(context);
		
		try {
			
			
			Timer timer = new Timer();
			
			timer.scheduleAtFixedRate( new RapPriceWebService((IPriceService) applicationContext.getBean("priceServiceBean"),(IGenericService) applicationContext.getBean("genericServiceBean")), getFridayNight(), f_PER_WEEK);
			//new RapPriceWebService((IPriceService) applicationContext.getBean("priceServiceBean"),(IGenericService) applicationContext.getBean("genericServiceBean"));
			timer.schedule( new RapPriceWebService((IPriceService) applicationContext.getBean("priceServiceBean"),(IGenericService) applicationContext.getBean("genericServiceBean")), new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	//expressed in milliseconds
	private final static long f_PER_WEEK = 1000*60*60*24*7;
	
	private final static int fONE_DAY = 0;
	private final static int fFOUR_AM = 01;
	private final static int fZERO_MINUTES = 00;

	  public static Date getFridayNight(){
	    Calendar date = new GregorianCalendar();
	    date.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
	    
	    date.set(Calendar.HOUR, 0);
	    date.set(Calendar.MINUTE, 0);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);

	    return date.getTime();
	  }
}

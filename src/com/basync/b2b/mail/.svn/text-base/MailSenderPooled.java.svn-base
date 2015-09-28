package com.basync.b2b.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.StringUtils;


public class MailSenderPooled implements MailSender, Runnable {

	protected Logger logger = Logger.getLogger(getClass());
	private ICommonService commonService;
    final public static String COMPANY_NAME = "H. Riddhesh & Co."; 

	// -------------------------------------------------------------------------

	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

	public String getAdminemail() {
		return adminemail;
	}

	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	/**
	 * Java Mail sender
	 */
	private JavaMailSenderImpl mailSender;

	private String adminemail;


	private static Map<String, String> templateMap = new HashMap<String, String>();

	// -------------------------------------------------------------------------

	/**
	 * Mail sending thread
	 */
	private Thread thread;

	/**
	 * Mail queue
	 */
	private Queue queue = new Queue();

	/**
	 * Lock
	 * 
	 * @see #run()
	 * @see #add(Object)
	 */
	private Object mutex = new Object();

	// -------------------------------------------------------------------------

	/**
	 * Constructor
	 * 
	 */
	public MailSenderPooled() {
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	// -------------------------------------------------------------------------

	/**
	 * An iteration of mail sending
	 * 
	 * @see #add(Object)
	 * 
	 */
	public void run() {
		while (!isClose()) {
			if (!isEmpty()) {
				Object object = poll();
				try {
					// read send
					doSend(object);
					logger.info("Mail sent");
				} catch (Exception ex) {
					logger.error("Mails end Exception: " + ex.getMessage(), ex);
					ex.printStackTrace();
				}
			}
			// Wait till notified by 'ADD' method
			synchronized (mutex) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					logger.error("InterruptedException : ", e);
					e.printStackTrace();
				}
			}
		}
	}

	// -------------------------------------------------------------------------

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	/**
	 * Set the mail sender
	 * 
	 * @param mailSender
	 */
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	// -------------------------------------------------------------------------

	/**
	 * Close the pool
	 * 
	 */
	public void close() {
		queue.clear();
		queue = null;
	}

	/**
	 * isClosed ?
	 */
	public boolean isClose() {
		return queue == null;
	}

	protected boolean isEmpty() {
		return queue != null && queue.isEmpty();
	}

	/**
	 * Add the mail into the queue
	 * 
	 * @param obj
	 * @see #run()
	 */
	protected void add(Object obj) {

		queue.add(obj);
		synchronized (mutex) {
			mutex.notify();
		}
	}

	protected Object poll() {

		return queue.poll();
	}

	/**
	 * 
	 */
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		add(simpleMessage);
	}

	/**
	 * 
	 */
	public void send(SimpleMailMessage[] simpleMessages) throws MailException {

		SimpleMailMessage[] messages = simpleMessages;
		for (int i = 0; i < messages.length; i++) {
			SimpleMailMessage message = messages[i];
			add(message);
		}
	}

	/**
	 * 
	 * @param mimeMessage
	 * @throws MailException
	 */
	public void send(MimeMessage mimeMessage) throws MailException {
		add(mimeMessage);
	}

	/**
	 * 
	 * @param mimeMessages
	 * @throws MailException
	 */
	public void send(MimeMessage[] mimeMessages) throws MailException {

		MimeMessage[] messages = mimeMessages;
		for (int i = 0; i < messages.length; i++) {
			MimeMessage message = messages[i];
			add(message);
		}

	}

	/**
	 * 
	 * @param mimeMessagePreparator
	 * @throws MailException
	 */
	public void send(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		add(mimeMessagePreparator);
	}

	/**
	 * 
	 * @param mimeMessagePreparators
	 * @throws MailException
	 */
	public void send(MimeMessagePreparator[] mimeMessagePreparators)
			throws MailException {

		MimeMessagePreparator[] preparators = mimeMessagePreparators;
		for (int i = 0; i < preparators.length; i++) {
			MimeMessagePreparator preparator = preparators[i];
			add(preparator);
		}
	}

	/**
	 * 
	 * @return
	 */
	public MimeMessage createMimeMessage() {
		return ((JavaMailSender) mailSender).createMimeMessage();
	}

	/**
	 * 
	 * @param contentStream
	 * @return
	 * @throws MailException
	 */
	public MimeMessage createMimeMessage(InputStream contentStream)
			throws MailException {
		return ((JavaMailSender) mailSender).createMimeMessage(contentStream);
	}

	/**
	 * Do the real send here
	 * 
	 * @param object
	 */
	public void doSend(Object object) throws MailException {
		if (object instanceof SimpleMailMessage) {
			doSend((SimpleMailMessage) object);
		} else if (object instanceof MimeMessage) {
			doSend((MimeMessage) object);
		} else if (object instanceof MimeMessagePreparator) {
			doSend((MimeMessagePreparator) object);
		}
	}

	/**
	 * 
	 * @param simpleMessage
	 * @throws MailException
	 */
	public void doSend(SimpleMailMessage simpleMessage) throws MailException {
		logger.info("Sending SimpleMailMessage mail to: " + simpleMessage.getTo() + ",cc: " + simpleMessage.getCc() + " subject: " + simpleMessage.getSubject());
		mailSender.send(simpleMessage);
	}

	/**
	 * 
	 * @param mimeMessage
	 * @throws MailException
	 */
	public void doSend(MimeMessage mimeMessage) throws MailException {
		try {
			logger.info("Sending MimeMessage mail to: " + mimeMessage.getRecipients(Message.RecipientType.TO) 
					+ ",cc: " + mimeMessage.getRecipients(Message.RecipientType.CC) 
					+ " subject: " + mimeMessage.getSubject());
		} catch (Exception e) {
			// TODO: handle exception
		}
//		((JavaMailSender) mailSender).send(mimeMessage);
		mailSender.send(mimeMessage);
	}

	/**
	 * 
	 * @param mimeMessagePreparator
	 * @throws MailException
	 */
	public void doSend(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
//		((JavaMailSender) mailSender).send(mimeMessagePreparator);
		mailSender.send(mimeMessagePreparator);
	}
	
	public void general_send_mail(String content, String subject)throws Exception {
		String email = this.commonService.getPropertiesByName(Constants.DEBUG_MAILID);
		general_send_mail(email,content,subject);
	}
	/**
	 * 
	 * @param email
	 * @param password
	 * @param subject
	 * @throws Exception
	 */
	public void general_send_mail(String email, String content, String subject) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();

		msg.setFrom(new InternetAddress(adminemail, COMPANY_NAME));

		InternetAddress[] tos = InternetAddress.parse(email);
		msg.setRecipients(Message.RecipientType.TO, tos);
		
		// subject
		msg.setSubject(subject);

		// msg.setText(message);// simple email
		MimeBodyPart mbps = new MimeBodyPart();

		// set the content
		mbps.setContent(StringUtils.plainTextToHTML(content), "text/html;charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mbps);

		msg.setContent(multipart);
		
		logger.info("sending general mail to " + email);

		send(msg);
	}
	/**
	 * 
	 * @param email
	 * @param cc
	 * @param content
	 * @param subject
	 * @throws Exception
	 */
	
	public void general_send_mail(String email, String cc, String content, String subject) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();

		msg.setFrom(new InternetAddress(adminemail, COMPANY_NAME));

		InternetAddress[] tos = InternetAddress.parse(email);
		msg.setRecipients(Message.RecipientType.TO, tos);
		
		InternetAddress[] ccs = InternetAddress.parse(cc);
		msg.setRecipients(Message.RecipientType.CC, tos);
		
		// subject
		msg.setSubject(subject);

		// msg.setText(message);// simple email
		MimeBodyPart mbps = new MimeBodyPart();

		// set the content
		mbps.setContent(StringUtils.plainTextToHTML(content), "text/html;charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mbps);

		msg.setContent(multipart);
		
		logger.info("sending general mail to " + email);

		send(msg);
	}
	/**
	 * 
	 * @param email
	 * @param cc
	 * @param content
	 * @param subject
	 * @throws Exception
	 */
	
	public void general_send_mail(String email, String cc, String content, String subject, List<File> attachMents) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();

		msg.setFrom(new InternetAddress(adminemail,  COMPANY_NAME));

		InternetAddress[] tos = InternetAddress.parse(email);
		msg.setRecipients(Message.RecipientType.TO, tos);
		
		if(StringUtils.isEmpty(cc)){
			InternetAddress[] ccs = InternetAddress.parse(cc);
			msg.setRecipients(Message.RecipientType.CC, tos);
		}
		// subject
		msg.setSubject(subject);

		// msg.setText(message);// simple email
		MimeBodyPart mbps = new MimeBodyPart();

		// set the content
		mbps.setContent(StringUtils.plainTextToHTML(content), "text/html;charset=utf-8");
		for (int i=0;i<attachMents.size();i++) {
			mbps.attachFile(attachMents.get(i));
		}
		

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mbps);

		msg.setContent(multipart);
		
		logger.info("sending general mail to " + email);

		send(msg);
	}
	
	 public void sendMail(String email, String subject,HttpServletRequest request, int orderId) throws Exception {
		 URL uri;
		   HttpSession ses = request.getSession(true);

	    try {
	            String dtls = "";
	                uri = 
	                	new URL("http://" + request.getServerName() + ":" + request.getServerPort() + 
	        request.getContextPath() + "/invoiceReprint.html?orderId="+orderId+"&mail=Y");
	        HttpURLConnection ucon = 
	                    (HttpURLConnection)uri.openConnection();
	                //   Cookie[] cookie = request.getCookies();
	                //   CookieManager cm = new CookieManager();
	                // cm.storeCookies(ucon);
	                //  cm.setCookies(ucon);
	                ucon.setRequestProperty("Cookie", "JSESSIONID=" + ses.getId());
	                ucon.connect();
	                if (ucon.getResponseCode() == 500) {
	                    System.out.println(" ERROR IN SENDING INVOICEMAIL");
	                }
	                InputStream in = ucon.getInputStream();
	                InputStreamReader isr = new InputStreamReader(in);
	                Reader inReader = new BufferedReader(isr);
	                StringBuffer buf = new StringBuffer();
	                int ch;
	                while ((ch = inReader.read()) > -1) {
	                    buf.append((char)ch);
	                }
	                inReader.close();
	                dtls = buf.toString();
	                if(!(dtls.contains("Confirm Shipment")))
	                {
	                	subject=subject.replaceAll("Confirm Selection/","");
	                }


	            MimeMessage msg = mailSender.createMimeMessage();

	    		msg.setFrom(new InternetAddress("admin@hrcdiamonds.com", COMPANY_NAME));

	    		InternetAddress[] tos = InternetAddress.parse(email);
	    		msg.setRecipients(Message.RecipientType.TO, tos);

	    		// subject
	    		msg.setSubject(subject);

	    		// msg.setText(message);// simple email
	    		MimeBodyPart mbps = new MimeBodyPart();

	    		String content = null;

	    		content = dtls;

	    		// set the content
	    		mbps.setContent(content, "text/html;charset=utf-8");

	    		Multipart multipart = new MimeMultipart();
	    		multipart.addBodyPart(mbps);

	    		msg.setContent(multipart);
	    		
	    		logger.info("sending INVOICE mail " + email);

	    		send(msg);


	        } 
	        catch (MalformedURLException e) 
	        {
	            e.printStackTrace();
	            
	        } 
	        catch (IOException e)
	        {
	            e.printStackTrace();

	        }

		 }
	
}

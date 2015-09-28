package com.basync.crm.webservice;



import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.basync.b2b.crm.data.RapPriceData;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.util.CommonUtil;
import com.sun.xml.messaging.saaj.util.JAXMStreamSource;

	public class RapPriceWebService  extends TimerTask {
		
	private static final String TECHNET_NAMESPACE_PREFIX = "technet";
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	
	
	public void run() {
		try {
			getRapPrices();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public RapPriceWebService() {
	}

	public RapPriceWebService(IPriceService priceService,
			IGenericService genericService) {
		super();
		this.priceService = priceService;
		this.genericService = genericService;
	}


	private static final String WEBSERVICE_SECURE_URL =
	"https://technet.rapaport.com/webservices/prices/rapaportprices.asmx";
	private static final String WEBSERVICE_INSECURE_URL =
	"http://technet.rapaport.com/webservices/prices/rapaportprices.asmx";

	private enum Shapes {
	ROUND("Round"), PEAR("Pear");

	private final String enumString;

	private Shapes(final String enumString) {
	this.enumString = enumString;
	}
	};
	
	Date currDate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	private IPriceService priceService;
	private IGenericService genericService;
	
	/**
	 * @return the priceService
	 */
	public IPriceService getPriceService() {
		return priceService;
	}

	/**
	 * @param priceService the priceService to set
	 */
	public void setPriceService(IPriceService priceService) {
		this.priceService = priceService;
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


	static Logger logger = Logger.getLogger(RapPriceWebService.class);

	
	
	public  void getRapPrices() throws Exception {
		try{
//		final RapPriceWebService rapPriceWebService = new RapPriceWebService();
		CommonUtil.getInstance();
		final String authenticationTicket = login(CommonUtil.getPropertiesByName("b2b.rapnet.username"), CommonUtil.getPropertiesByName("b2b.rapnet.password"));
			//backup and clear old data
			getPriceBackUpTrunc(); 
	
			//rapPriceWebService.getPrice(authenticationTicket, "Round", 0.4F, "D", "VS1");
			logger.info("Prices downloading for ROUND");
			getPriceSheet(authenticationTicket, Shapes.ROUND);
			logger.info("Prices downloaded for ROUND");
			
			logger.info("Prices downloading for PEAR");
			getPriceSheet(authenticationTicket, Shapes.PEAR);
			logger.info("Prices downloaded for PEAR");
		}catch (Exception e) {
			logger.error("ERROR IN RAP WEBSERVICE", e);
		}
		
	}

	/**
	* Get the login token
	*
	* @param username
	* @param password
	* @return The authentication ticket
	* @throws SOAPException
	*/
	private String login( final String username, final String password) throws SOAPException {
	final SOAPMessage soapMessage = getSoapMessage();
	final SOAPBody soapBody = soapMessage.getSOAPBody();
	final SOAPElement loginElement = soapBody.addChildElement("Login", TECHNET_NAMESPACE_PREFIX);

	loginElement.addChildElement("Username", TECHNET_NAMESPACE_PREFIX).addTextNode(username);
	loginElement.addChildElement("Password", TECHNET_NAMESPACE_PREFIX).addTextNode(password);

	soapMessage.saveChanges();

	final SOAPConnection soapConnection = getSoapConnection();
	final SOAPMessage soapMessageReply = soapConnection.call(soapMessage,WEBSERVICE_SECURE_URL);
	final String textContent = soapMessageReply.getSOAPHeader().getFirstChild().getTextContent();

	soapConnection.close();

	return textContent;
	}

	/**
	* Returns the price
	*
	* @param authenticationTicket
	* @param shape
	* @param size
	* @param color
	* @param clarity
	* @throws SOAPException
	*/
	private void getPrice( final String authenticationTicket, final String shape, final float size, final String color,
	final String clarity) throws SOAPException {
	final SOAPMessage soapMessage = getSoapMessage();

	addAuthenticationTicket(authenticationTicket, soapMessage);

	final SOAPBody soapBody = soapMessage.getSOAPBody();
	final SOAPElement getPriceElement = soapBody.addChildElement("GetPrice", TECHNET_NAMESPACE_PREFIX);
	getPriceElement.addChildElement("shape", TECHNET_NAMESPACE_PREFIX).addTextNode(shape);
	getPriceElement.addChildElement("size", TECHNET_NAMESPACE_PREFIX).addTextNode(String.valueOf(size));
	getPriceElement.addChildElement("color", TECHNET_NAMESPACE_PREFIX).addTextNode(color);
	getPriceElement.addChildElement("clarity", TECHNET_NAMESPACE_PREFIX).addTextNode(clarity);

	soapMessage.saveChanges();

	final SOAPConnection soapConnection = getSoapConnection();

	final SOAPMessage soapMessageReply = soapConnection.call(soapMessage,WEBSERVICE_INSECURE_URL);

	final SOAPBody replyBody = soapMessageReply.getSOAPBody();
	final Node getPriceResponse = replyBody.getFirstChild();
	final Node getPriceResult = getPriceResponse.getFirstChild();

	final NodeList childNodes = getPriceResult.getChildNodes();
	final String replyShape = childNodes.item(0).getTextContent();
	final String lowSize = childNodes.item(1).getTextContent();

	// ... etc etc
	// You can create a bean that will encompass all elements

	soapConnection.close();
	} /**
	* Gets the price sheet
	*
	* @param authenticationTicket
	* @param shapes
	 * @throws Exception 
	*/
	private void getPriceSheet( final String authenticationTicket, final Shapes shapes)
	throws Exception {
		final SOAPMessage soapMessage = getSoapMessage();
	
		addAuthenticationTicket(authenticationTicket, soapMessage);
	
		final SOAPBody soapBody = soapMessage.getSOAPBody();
	
		final SOAPElement getPriceSheetElement =
		soapBody.addChildElement("GetPriceSheet", TECHNET_NAMESPACE_PREFIX);
	
		getPriceSheetElement.addChildElement(
		"shape", TECHNET_NAMESPACE_PREFIX).addTextNode(shapes.enumString);
	
		soapMessage.saveChanges();
	
		final SOAPConnection soapConnection = getSoapConnection();
		final SOAPMessage soapMessageReply = soapConnection.call(soapMessage, WEBSERVICE_INSECURE_URL);
	
		//soapMessageReply.writeTo(System.out);
		// this will print out the result
		// Create transformer
	
		final TransformerFactory tff = TransformerFactory.newInstance();
		final Transformer tf = tff.newTransformer();
	
		// Get reply content
		final Source sc = soapMessageReply.getSOAPPart().getContent();
	
		CommonUtil commonUtil = CommonUtil.getInstance();
		FileOutputStream fos = new FileOutputStream(commonUtil.getPropertiesByName("b2b.download.filepath")+
										commonUtil.getPropertiesByName("b2b.download.priceList.filename")+
									sdf.format(currDate)+"_"+shapes.toString()+"."
										+commonUtil.getPropertiesByName("b2b.download.priceList.type"));

		 Node root = null;
		 if (sc instanceof com.sun.xml.internal.messaging.saaj.util.JAXMStreamSource) {
				 InputStream inStream =  ((com.sun.xml.internal.messaging.saaj.util.JAXMStreamSource) sc).getInputStream();
			     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			     dbf.setNamespaceAware(true);
			     DocumentBuilder db = null;
		
			     db = dbf.newDocumentBuilder();
		
			     Document doc = db.parse(inStream);
			     root = (Node) doc.getDocumentElement();
			     NodeList list = doc.getElementsByTagName("Table");
			     
			     List<RapPriceData>  rapPriceList = new ArrayList<RapPriceData>();
			     for(int i=0;i<list.getLength();i++){
			    	NodeList childNodeObj = list.item(i).getChildNodes();
			    	RapPriceData rpd= new RapPriceData();
			    	rpd.setColor(childNodeObj.item(0).getTextContent());
			    	rpd.setClarity(childNodeObj.item(1).getTextContent());
			    	rpd.setHighSize(Double.parseDouble(childNodeObj.item(2).getTextContent()!=null?childNodeObj.item(2).getTextContent():"0"));
			    	rpd.setLowSize(Double.parseDouble(childNodeObj.item(3).getTextContent()!=null?childNodeObj.item(3).getTextContent():"0"));
			    	rpd.setPrice(Double.parseDouble((childNodeObj.item(4).getTextContent())!=null?(childNodeObj.item(4).getTextContent()):"0"));
			    	rpd.setShape(shapes.name());	
			    	rapPriceList.add(rpd);
			     }
			    
			     priceService.insertRapPrices(rapPriceList);
			     
		    }
		 
	     // Set output transformation to file 
		 
		final StreamResult result = new StreamResult(fos);
		tf.transform(sc, result);
	
		// Close connection
		soapConnection.close();
	}

	/**
	* Create a SOAP Connection
	*
	* @return
	* @throws UnsupportedOperationException
	* @throws SOAPException
	*/
	private SOAPConnection getSoapConnection() throws UnsupportedOperationException, SOAPException {
	final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	final SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	return soapConnection;
	}

	/**
	* Create the SOAP Message
	*
	* @return
	* @throws SOAPException
	*/
	private SOAPMessage getSoapMessage() throws SOAPException {
	final MessageFactory messageFactory = MessageFactory.newInstance();
	final SOAPMessage soapMessage = messageFactory.createMessage();

	// Object for message parts
	final SOAPPart soapPart = soapMessage.getSOAPPart();
	final SOAPEnvelope envelope = soapPart.getEnvelope();

	envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
	envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	envelope.addNamespaceDeclaration("enc", "http://schemas.xmlsoap.org/soap/encoding/");
	envelope.addNamespaceDeclaration("env", "http://schemas.xmlsoap.org/soap/envelop/");

	// add the technet namespace as "technet"
	envelope.addNamespaceDeclaration(TECHNET_NAMESPACE_PREFIX, "http://technet.rapaport.com/");

	envelope.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");

	return soapMessage;
	}

	private void addAuthenticationTicket( final String authenticationTicket, final SOAPMessage soapMessage)
	throws SOAPException {
		final SOAPHeader header = soapMessage.getSOAPHeader();
		final SOAPElement authenticationTicketHeader =
		header.addChildElement("AuthenticationTicketHeader", TECHNET_NAMESPACE_PREFIX);
		authenticationTicketHeader.addChildElement(
		"Ticket", TECHNET_NAMESPACE_PREFIX).addTextNode(authenticationTicket);
	}
	
	public void getPriceBackUpTrunc() throws Exception{
		//get backUp 
		String tableName ="tb_rapPrices";
		getGenericService().backUpHistory(tableName);
	
		//truncate old data
		getGenericService().truncateTable(tableName);
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
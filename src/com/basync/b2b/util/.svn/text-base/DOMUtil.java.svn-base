
package com.basync.b2b.util;

import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class DOMUtil {

	protected static final Logger logger = Logger.getLogger(DOMUtil.class);
	/**
	 * This method is used for caster javabean to xml
	 * 
	 * @param obj
	 * @return
	 */
	public static String getXMLFromObject(Object obj) {
		String result = null;
		try {
			Document d = genDocInstance();

			Node root = d.createElement("root");
			Marshaller mar = new Marshaller(root);
			mar.marshal(obj);
			d.appendChild(root);
			result = docToString(d);

		} catch (MarshalException e) {
			logger.error(e);
			//e.printStackTrace();
		} catch (ValidationException e) {
			logger.error(e);
			//e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			//e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * Get Document instance,
	 * 
	 * @see org.w3c.dom.Document
	 * 
	 * @param args
	 */
	private static Document genDocInstance() {

		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			domFactory.setValidating(false);
			domFactory.setNamespaceAware(true);

			DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

			Document doc = domBuilder.newDocument();

			return doc;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * Transfer a W3C XML document to string
	 * 
	 * @param d 
	 * @return
	 * @throws Exception
	 */
	private static String docToString(Document d) throws Exception {

		StringWriter stringOut = new StringWriter();
		try {
			OutputFormat format = new OutputFormat(d);
			XMLSerializer serial = new XMLSerializer(stringOut, format);
			serial.asDOMSerializer();
			serial.serialize(d.getDocumentElement());
		} catch (Exception e) {
			logger.error(e);
			return "";

		}
		return stringOut.toString();
	}

	

	/**
	 * Generate PDF use these given params. 
	 * 
	 * 
	 * @param xmlPath  xml path
	 * @param xslPath  xslt path
	 * @param pdfPath  generate pdf's path
	 * @return
	 * @throws Exception
	 */
    public static int  convert( String xmlPath, String xslPath, String pdfPath) throws Exception{
            logger.debug("FOP generate\n");
            logger.debug("Preparing...");
            
            // Setup input and output files            
            File xmlfile = new File(xmlPath);
            File xsltfile = new File(xslPath);
            File pdffile = new File(pdfPath);

            logger.debug("Input: XML (" + xmlfile + ")");
            logger.debug("Stylesheet: " + xsltfile);
            logger.debug("Output: PDF (" + pdffile + ")");
            logger.debug("\n");
            logger.debug("Transforming...");
            
            // configure fopFactory as desired
            FopFactory fopFactory = FopFactory.newInstance();

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            // configure foUserAgent as desired

            // Setup output
            OutputStream out = new java.io.FileOutputStream(pdffile);
            out = new java.io.BufferedOutputStream(out);
            
            try {
                // Construct fop with desired output format
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
    
                // Setup XSLT
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                
                // Set the value of a <param> in the stylesheet
                transformer.setParameter("versionParam", "2.0");
            
                // Setup input for XSLT transformation
                Source src = new StreamSource(xmlfile);
            
                // Resulting SAX events (the generated FO) must be piped through to FOP
                Result res = new SAXResult(fop.getDefaultHandler());
    
                // Start XSLT transformation and FOP processing
                transformer.transform(src, res);
            } finally {
                out.close();
            }
            
            logger.debug("Success!");
    	return 1; 
    }
    
}

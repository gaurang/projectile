package com.basync.crm.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.basync.b2b.util.CommonUtil;

public class RapUpload {

	/**
	* Get the ticket
	*
	* @param username
	* @param password
	* @return
	* @throws IOException
	*/
	public String getAuthenticationTicket(final String username, final String password) throws IOException {
		final URL url = new URL("https://technet.rapaport.com/HTTP/Authenticate.aspx");
		final String data = URLEncoder.encode("username", "UTF-8").concat("=")
		.concat(URLEncoder.encode(username, "UTF-8")).concat("&")
		.concat(URLEncoder.encode("password", "UTF-8")).concat("=")
		.concat(URLEncoder.encode(password, "UTF-8"));
	
		final URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		final OutputStream outputStream = connection.getOutputStream();
		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		outputStreamWriter.write(data);
		outputStreamWriter.flush();
	
		// Get the response
		final BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
		stringBuffer.append(line);
		}
		outputStreamWriter.close();
		rd.close();
	
		return stringBuffer.toString();
	}
	
	public String uploadRapStock(String uploadString) throws Exception {
		CommonUtil.getInstance();
		String uploadURL = CommonUtil.getPropertiesByName("b2b.rapnet.uplaodURL");
		
		String authTicket = getAuthenticationTicket(CommonUtil.getPropertiesByName("b2b.rapnet.username"),
				CommonUtil.getPropertiesByName("b2b.rapnet.password"));
		final String sendData = URLEncoder.encode("ticket", "UTF-8").concat("=")
		.concat(URLEncoder.encode(authTicket, "UTF-8")).concat("&")
		.concat(URLEncoder.encode("UploadCSVString", "UTF-8")).concat("=")
		.concat(URLEncoder.encode(uploadString, "UTF-8")).concat("&")
		.concat(URLEncoder.encode("ReplaceAll", "UTF-8")).concat("=")
		.concat(URLEncoder.encode("false", "UTF-8"));
		
		final URL url = new URL(uploadURL);
		final URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		final OutputStream outputStream = connection.getOutputStream();
		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		outputStreamWriter.write(sendData);
		outputStreamWriter.flush();
		final BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
		stringBuffer.append(line);
		}
		outputStreamWriter.close();
		rd.close();
	
		return stringBuffer.toString();
		
	}
}

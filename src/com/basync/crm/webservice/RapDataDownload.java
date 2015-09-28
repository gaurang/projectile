package com.basync.crm.webservice;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.OutputStreamWriter;
	import java.net.URL;
	import java.net.URLConnection;
	import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import com.basync.b2b.util.CommonUtil;

	public class RapDataDownload {
	public static void main(String[] args) throws Exception {
	final RapDataDownload rapDataDownload = new RapDataDownload();
	final String authenticationTicket =
	rapDataDownload.getAuthenticationTicket("username", "password");

	rapDataDownload
	.getData(
			new URL( "http://technet.rapaport.com/HTTP/RapLink/download.aspx?Programmatically=yes"), authenticationTicket);
	}

	/**
	* Get the ticket
	*
	* @param username
	* @param password
	* @return
	* @throws IOException
	*/
	private String getAuthenticationTicket(final String username, final String password) throws IOException {
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

	/**
	* Gets the data
	*
	* @param url
	*
	eg

	*
	http://technet.rapaport.com/HTTP/RapLink/download.aspx?CaratSizeFrom

	*
	=0.30&CaratSizeTo=5

	* @param ticket
	*
	authentication ticket

	* @throws IOException
	*/
	private void getData(final URL url, final String ticket) throws IOException {
		final String data = URLEncoder.encode("ticket", "UTF-8").concat("=").concat(URLEncoder.encode(ticket, "UTF-8"));
		final URLConnection connection = url.openConnection();
	
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Encoding", "gzip");
	
		final OutputStream outputStream = connection.getOutputStream();
		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
	
		outputStreamWriter.write(data);
		outputStreamWriter.flush();
	
		final InputStream replyStream = connection.getInputStream();
		final InputStreamReader inputStreamReader;
	
		// The following is to check if the server sending the response supports
		// Gzip
		final String contentEncodingField = connection.getHeaderField("Content-Encoding");
		if (contentEncodingField != null && contentEncodingField.equalsIgnoreCase("gzip")) {
		// read the gzipped format
		final GZIPInputStream gzipInputStream = new GZIPInputStream(replyStream);
		inputStreamReader = new InputStreamReader(gzipInputStream);
		} else {
		inputStreamReader = new InputStreamReader(replyStream);
		}
	
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		while ((line = reader.readLine()) != null) {
		System.out.println(line);
		}
		outputStreamWriter.close();
		reader.close();
	}
	
}

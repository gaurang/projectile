package com.basync.b2b.data;

import java.net.URISyntaxException;
import java.net.URL; 
import java.net.URLEncoder;
import java.net.MalformedURLException; 
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Test{
	
	
 public static void main(String[] args) throws MalformedURLException{
	 File file=new File("/home/aa/Arvind/server/16.jpg");
	  URL url=null;
	  try{
		  //The file may or may not exist
		  url=file.toURL(); //file:/C:/work/chandan/deepak.txt
		  System.out.println("The url is" + url);

		  // change the URL to a file object
		  file=new File(url.getFile());  // c:/work/chandan/deepak.txt
		  System.out.println("The file name is " + file);
		  int i;
	  
	  //opens an input stream
	  InputStream is=url.openStream();
	  BufferedReader br=new BufferedReader(new InputStreamReader(is));
	  do{
	  i=br.read();
	 // System.out.println((char)i);
	  }while (i!=-1);
	  is.close();
	  }
	  catch (MalformedURLException e){
	 // System.out.println("Don't worry,exception has been caught" + e);
	  }
	  catch (IOException e){
	  //System.out.println(e.getMessage());
	  }  
	  }
}

package com.basync.b2b.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.avalon.framework.logger.Loggable;
import org.apache.commons.lang.math.NumberUtils;

import jxl.CellView;
import jxl.SheetSettings;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;

import com.basync.b2b.crm.data.CurrencyResult;
import com.basync.b2b.crm.data.FileMap;
import com.google.gson.Gson;

public class CommonUtil {

	private static CommonUtil util = null;
	private static Properties properties = new Properties();
	private CommonUtil() throws IOException {
		util = this;
		InputStream fis = this.getClass().getResourceAsStream("/b2b.properties");
		properties.load(fis);
	}

	public static CommonUtil getInstance() throws IOException {
		if (util == null) {
			util = new CommonUtil();
		}
		return util;
	}

	public static java.sql.Date getDate(String dtStr) {
		return DateUtil.getDate(dtStr);
	}

	public static java.sql.Timestamp getTimestamp(String tsStr) {
		return DateUtil.getTimestamp(tsStr);
	}

	/**
	 * Converts the Date to String.
	 * 
	 * @param a_dtDate
	 *            - i/p date value
	 * @return String
	 * @throws
	 */
	public static String getString(java.util.Date a_dtDate) {
		return DateUtil.getString(a_dtDate);
	}

	public static String checkNull(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else
			return "";
	}

	/**
	 * gets the String value of a request parameter of type string. Checks for
	 * null values
	 * 
	 * @param a_oparam
	 *            the value to check
	 * @return the string value of the parameter.
	 */
	public static String getStringValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			a_strParam = a_strParam.trim();
		} else {
			a_strParam = null;
		}

		return a_strParam;
	}

	/**
	 * gets the String value of a request parameter of type string. Checks for
	 * null values
	 * 
	 * @param a_Param
	 *            the object to check
	 * @return the string value of the parameter.
	 */
	public static String getStringValue(Object a_Param) {
		if (a_Param != null) {
			return a_Param.toString();
		}
		return null;
	}

	/**
	 * gets the Date value of the specified request parameter from the request
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Date value of the parameter if valid else null.
	 * @throws
	 */
	public static Date getDateValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return getDate(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the Short value of the specified request parameter
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Short value of the parameter if valid else null.
	 */
	public static Short getShortValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return new Short(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the Long value of the specified request parameter
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Long value of the parmeter if valid else null
	 */
	public static Long getLongValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return new Long(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the Double value of the specified request parameter
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Double value of the parameter if valid else null
	 */
	public static Double getDoubleValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return new Double(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the Float value of the specified request parameter
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Float value of the parameter if valid else null
	 */
	public static Float getFloatValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return new Float(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the Integer value of the specified request parameter
	 * 
	 * @param a_oparam
	 *            the name of the parameter
	 * @return the Double value of the parameter if valid else null
	 */
	public static Integer getIntegerValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return new Integer(a_strParam.trim());
		}

		return null;
	}

	/**
	 * gets the timestamp value of the specified request parameter
	 * 
	 * @param a_strParam
	 *            the name of the parameter
	 * @return the Timestamp value of the parameter if valid else null
	 */
	public static Timestamp getTimestampValue(String a_strParam) {
		if ((a_strParam != null) && !a_strParam.trim().equals("")) {
			return Timestamp.valueOf(a_strParam.trim());
		}
		return null;
	}

	/**
	 * This method used for getting a double formatted as per string specified
	 * 
	 * @param a_dVal
	 *            - i/p double value
	 * @param a_strFormatPattern
	 *            - the frmat string
	 * @return String
	 */
	public static String getFormattedDouble(Double a_dVal, String a_strFormatPattern) {
		String strRetVal = "";
		if (a_dVal != null) {
			DecimalFormat oDF = new DecimalFormat(a_strFormatPattern);
			strRetVal = oDF.format(a_dVal);
		}

		return strRetVal;
	}



	public static String convertToString(List<String> listString) {
		StringBuffer stringBuffer = new StringBuffer();
		for (String str : listString) {
			stringBuffer.append(str + ",");
		}
		String str = stringBuffer.toString();
		if (str.length() > 0)
			str = str.substring(0, str.lastIndexOf(','));
		return str;
	}
	
	public static String getPropertiesByName(String key) throws Exception {
		return properties.getProperty(key, "");
	}

	public static void fillXLWeb(WritableSheet s, JQGridContainer container, List<String> hdrs)
			throws Exception {
		
		List<JQGridRow> rows =container.getRows();
		int labIdx = hdrs.indexOf("Lab")-1;
		int rateIdx = hdrs.indexOf("Rate")-1;
		int discIdx = hdrs.indexOf("Discount")-1;
		int caratIdx = hdrs.indexOf("Carats")-1;
		int totalIdx = hdrs.indexOf("Total")-1;
		NumberFormat nf = new DecimalFormat("#0.00");
		WritableCellFormat wc = new WritableCellFormat();
		wc.setAlignment(jxl.format.Alignment.CENTRE);
		
		WritableCellFormat wcBG = new WritableCellFormat();
		wcBG.setAlignment(jxl.format.Alignment.CENTRE);
		//wcBG.setBackground(Colour.AQUA);

		for (int i = 0; i < hdrs.size(); i++) {
			CellView cell=s.getColumnView(i);
			cell.setAutosize(true);
			s.setColumnView(i, cell);
			Label label = new Label(i, 0, "	"+hdrs.get(i)+" ");
			label.setCellFormat(wc);
			if(i == discIdx+1)
				label.setCellFormat(wcBG);
			s.addCell(label);
		}
		
		int i=0,j=0;
		for ( i = 0; i < rows.size(); i++) {
			List<String> cells = rows.get(i).getCell();
			for ( j = 0; j < hdrs.size(); j++) {
				
				if(labIdx+1 ==j){
					//s.addHyperlink(new WritableHyperlink(j, i+1, new URL(getPropertiesByName("b2b.server.url")+"cert/"+cells.get(0)+".jpg")));
					Label label = new Label(j, i+1, cells.get(j));
					label.setCellFormat(wc);
					s.addCell(label);

				}
				else if(j == rateIdx+1 || j == totalIdx+1 ||j == discIdx+1){
					if(!StringUtils.isEmpty(cells.get(j))){
						Label label =new Label(j, i+1, nf.format(Double.parseDouble(cells.get(j))));
						
						label.setCellFormat(wc);
						if(j == discIdx+1)
							label.setCellFormat(wcBG);
						s.addCell(label);

					}else{
						Label label = new Label(j, i+1, cells.get(j));
						label.setCellFormat(wc);
						if(j == discIdx+1)
							label.setCellFormat(wcBG);
						s.addCell(label);
					}
					
				}
				else{
						Label label = new Label(j, i+1, cells.get(j));
						label.setCellFormat(wc);
						s.addCell(label);
					}
			}
		}
		
		Map userData = container.getUserdata();
		j=0;
		s.addCell(new Label(j++, i+1, "Totals"));
		s.addCell(new Label(j++, i+1, "Stones "+userData.get("pktCode").toString()));
		s.addCell(new Label(caratIdx+1, i+1, "Carats "+nf.format(Double.parseDouble(userData.get("cts").toString()))));
		s.addCell(new Label(discIdx+1, i+1, "Avg Discount "+nf.format(Double.parseDouble(userData.get("rap").toString()))));
		s.addCell(new Label(rateIdx+1, i+1, "Avg. rate/cts "+nf.format(Double.parseDouble(userData.get("rate").toString()))));
		s.addCell(new Label(totalIdx+1, i+1, "Total Value "+nf.format(Double.parseDouble(userData.get("total").toString()))));
	}
	
	public static int fillXLSimplified(WritableSheet s, JQGridContainer container, List<FileMap> fileMapList,boolean isUserData, int fnc)
	throws Exception {
		Logger logger = Logger.getLogger(CommonUtil.class.getName());
		SheetSettings settings = s.getSettings();
		settings.setDefaultRowHeight(400);
		
		List<JQGridRow> rows =container.getRows();
		int rateIdx = 0;
		int discIdx = 0;
		int caratIdx = 0;
		int totalIdx = 0;
		//kri_23
		int cIdx=0;
		NumberFormat nf = new DecimalFormat("#0");
		
		NumberFormat nfCts = new DecimalFormat("#0.00");
		nfCts.setRoundingMode(RoundingMode.FLOOR);
		
		WritableCellFormat wc = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wc.setAlignment(jxl.format.Alignment.CENTRE);
		wc.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		
		WritableCellFormat wcBG = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wcBG.setAlignment(jxl.format.Alignment.CENTRE);
		wcBG.setBackground(Colour.AQUA);
		wcBG.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

		int c = 0;
		for (int i = 0; i < fileMapList.size(); i++) {
			
			System.out.println("   COL MAIN " +fileMapList.get(i).getExcelColumnName());
			FileMap fileMap = fileMapList.get(i);
			if(fileMapList.get(i).getColumnName()!=null){
				if(fnc != 1 ){
					if((fileMap.getColumnName().equals("FNC")||
							fileMap.getColumnName().equals("FNCI")||fileMap.getColumnName().equals("FNCO"))){
						continue;
					} 
				}
				if(fileMap.getColumnName().equals("pairStock"))
						continue;//For no pairs in regular stock
				
				System.out.println(c +"   COL " +fileMapList.get(i).getExcelColumnName());
				
				Label label = new Label(c, 2, "  "+(fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():
					(fileMapList.get(i).getColumnName()!=null?fileMapList.get(i).getColumnName():""))+"  ");
				label.setCellFormat(wc);
				if(fileMapList.get(i).getColumnName().equals("rate"))
					rateIdx = c;
				else if(fileMapList.get(i).getColumnName().equals("rap")){
					discIdx = c;
					label.setCellFormat(wcBG);
				}
				else if(fileMapList.get(i).getColumnName().equals("CTS"))
					caratIdx = c;
				else if(fileMapList.get(i).getColumnName().equals("total"))
					totalIdx = c;
				
				else if(fileMapList.get(i).getColumnName().equals("c")){
			    	cIdx=c;
				}
				
				System.out.println(c +"   COL " +label.getColumn() + "   "+label.getContents());
				s.addCell(label);
				CellView cell=s.getColumnView(c);
				cell.setAutosize(true);
				s.setColumnView(c, cell);
			}
			
			c++;
		}
		
		int i=0,j=0,x = 4;//x =xl field row number ;
		String grpStr = ""; 
		double cts = 0d;
		Double totalCts = 0d;
		for ( i = 0; i < rows.size(); i++) {
			List<String> cells = rows.get(i).getCell();
			 try
			    {
				 	String ct = cells.get(caratIdx);
				 	if(ct != null || !StringUtils.isEmpty(ct)) {
				 		cts = Double.parseDouble(ct);
				 	}
			    }
			    catch(NumberFormatException ex)
			    {
			    	cts=0;
			    	continue;
			    }
			
			if(!grpStr.equals(getSizesString(cts))){
				Label label;
				if(!StringUtils.isEmpty(grpStr)){
					label = new Label(caratIdx, x, nfCts.format(Double.parseDouble(totalCts.toString())));
					label.setCellFormat(wc);
					s.addCell(label);
					totalCts = 0d;
					x++;
				}
				
				grpStr = getSizesString(cts);
				label = new Label(0, x, grpStr);
				label.setCellFormat(wc);
				s.addCell(label);
				x++;
			}
			totalCts +=cts;		
			
			c=0;
			for ( j = 0; j < fileMapList.size(); j++) {
				if(j == cells.size()-1)break;
				FileMap fm = fileMapList.get(j);
				if(fm.getColumnName()!=null){
					if(fnc != 1 ){
							if((fm.getColumnName().equals("FNC")||
								fm.getColumnName().equals("FNCI")||fm.getColumnName().equals("FNCO"))){
							continue;
							}   
					}
					if(fm.getColumnName().equals("pairStock"))
						continue;//For no pairs in regular stock
				}
				if(fileMapList.get(j).getFileId() == 0)
					break;
				
				if(fm.getColumnName()==null){
					Label label = new Label(c, x, "");
					label.setCellFormat(wc);
					s.addCell(label);

				}else if( fm.getColumnName().equalsIgnoreCase("certLabId")){
					//s.addHyperlink(new WritableHyperlink(j, i+1, new URL(getPropertiesByName("b2b.server.url")+"cert/"+cells.get(0)+".jpg")));
					logger.info(c+"  ZZZZZZZZ "+x+ "  "+cells.get(j)+" certLabId " );
					
					Label label = new Label(c, x, cells.get(j));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else if( fm.getColumnName().equalsIgnoreCase("rate") || fm.getColumnName().equalsIgnoreCase("total") ){
					if(!StringUtils.isEmpty(cells.get(j))){
						Label label =new Label(c, x,  nf.format(Double.parseDouble(cells.get(j))));
						label.setCellFormat(wc);
						s.addCell(label);
					}else{
						Label label = new Label(c, x, cells.get(j));
						label.setCellFormat(wc);
						s.addCell(label);
					}
				}else if(fm.getColumnName().equalsIgnoreCase("CTS")){
					if(cells.get(j) != null && !StringUtils.isEmpty(cells.get(j))){
						Label label = new Label(c, x, nfCts.format(Double.parseDouble(cells.get(j))));
						label.setCellFormat(wc);
						s.addCell(label);
					}
				}
				else{
					
					if(cells.get(j) != null && !StringUtils.isEmpty(cells.get(j))){
							Label label = new Label(c, x, NumberUtils.isNumber(cells.get(j))?nfCts.format(Double.parseDouble(cells.get(j))):cells.get(j));
							label.setCellFormat(wc);
							if(fm.getColumnName().equalsIgnoreCase("rap") )
								label.setCellFormat(wcBG);
							s.addCell(label);
					}
				}
				c++;
			}
			x++;
		}
		if(isUserData){
				Map userData = container.getUserdata();
				j=0;
				s.addCell(new Label(j++, x, "Totals"));
				s.addCell(new Label(j++, x, "Stones "+userData.get("pktCode").toString()));
				if(userData.get("cts")!=null)
				s.addCell(new Label(caratIdx, x, "Carats "+nfCts.format(Double.parseDouble(userData.get("cts").toString()))));
				if(userData.get("rap")!=null)
					s.addCell(new Label(discIdx, x, "Avg Discount "+nfCts.format(Double.parseDouble(userData.get("rap").toString()))));
				if(userData.get("rate")!=null)
					s.addCell(new Label(rateIdx, x, "Avg. rate/cts "+nfCts.format(Double.parseDouble(userData.get("rate").toString()))));
				if(userData.get("total")!=null)
					s.addCell(new Label(totalIdx, x, "Total Value "+nfCts.format(Double.parseDouble(userData.get("total").toString()))));
			}
		return x;
	}
	public static int fillXLRegular(WritableSheet s, JQGridContainer container, List<FileMap> fileMapList,boolean isUserData)
	throws Exception {
		Logger logger = Logger.getLogger(CommonUtil.class.getName());
		SheetSettings settings = s.getSettings();
		settings.setDefaultRowHeight(400);
		
		List<JQGridRow> rows =container.getRows();
		int rateIdx = 0;
		int discIdx = 0;
		int caratIdx = 0;
		int totalIdx = 0;
		//kri_23
		int cIdx=0;
		int prIds=0;
		NumberFormat nf = new DecimalFormat("#0");
		
		NumberFormat nfCts = new DecimalFormat("#0.00");
		nfCts.setRoundingMode(RoundingMode.FLOOR);
		
		WritableCellFormat wc = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wc.setAlignment(jxl.format.Alignment.CENTRE);
		wc.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		
		WritableCellFormat wcBG = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wcBG.setAlignment(jxl.format.Alignment.CENTRE);
		wcBG.setBackground(Colour.AQUA);
		wcBG.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

		int c = 0;
		for (int i = 0; i < fileMapList.size(); i++) {
		
			Label label = new Label(c, 0, "  "+(fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():
																	(fileMapList.get(i).getColumnName()!=null?fileMapList.get(i).getColumnName():""))+"  ");
			label.setCellFormat(wc);
			if(fileMapList.get(i).getColumnName()!=null){
				if(fileMapList.get(i).getColumnName().equals("rate"))
					rateIdx = c;
				else if(fileMapList.get(i).getColumnName().equals("rap")){
					discIdx = c;
					label.setCellFormat(wcBG);
				}
				else if(fileMapList.get(i).getColumnName().equals("CTS"))
					caratIdx = c;
				else if(fileMapList.get(i).getColumnName().equals("total"))
					totalIdx = c;
				
				else if(fileMapList.get(i).getColumnName().equals("c")){//StringUtils.isEmpty(askingPrice)){
			    	cIdx=c;
				}
			}
			s.addCell(label);
			CellView cell=s.getColumnView(i);
			cell.setAutosize(true);
			s.setColumnView(c, cell);
			c++;
		}
		
		int i=0,j=0,x = 1;//x =xl field row number ;
		String grpStr = ""; 
		Double totalCts = 0d;
		for ( i = 0; i < rows.size(); i++) {
			List<String> cells = rows.get(i).getCell();
			/*
			double cts = Double.parseDouble(cells.get(caratIdx));
			if(!grpStr.equals(getSizesString(cts))){
				Label label;
				if(!StringUtils.isEmpty(grpStr)){
					label = new Label(caratIdx, x, nfCts.format(Double.parseDouble(totalCts.toString())));
					label.setCellFormat(wc);
					s.addCell(label);
					totalCts = 0d;
					x++;
				}
				
				grpStr = getSizesString(cts);
				label = new Label(0, x, grpStr);
				label.setCellFormat(wc);
				s.addCell(label);
				x++;
			}
			totalCts +=cts;		
			*/
			c=0;
			for ( j = 0; j < fileMapList.size(); j++) {
				if(fileMapList.get(j).getFileId() == 0)
					break;
				FileMap fm = fileMapList.get(j);
				
				if(fm.getColumnName()==null){
					Label label = new Label(c, x, "");
					label.setCellFormat(wc);
					s.addCell(label);

				}else if( fm.getColumnName().equalsIgnoreCase("certLabId")){
					//s.addHyperlink(new WritableHyperlink(j, i+1, new URL(getPropertiesByName("b2b.server.url")+"cert/"+cells.get(0)+".jpg")));
					logger.info(c+"  ZZZZZZZZ REG "+x+ "  "+cells.get(j)+" certLabId " );
					Label label = new Label(c, x, cells.get(j));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else if( fm.getColumnName().equalsIgnoreCase("rate") || fm.getColumnName().equalsIgnoreCase("total") ){
					if(!StringUtils.isEmpty(cells.get(j))){
						Label label =new Label(c, x,  nf.format(Double.parseDouble(cells.get(j))));
						label.setCellFormat(wc);
						s.addCell(label);
					}else{
						Label label = new Label(c, x, cells.get(j));
						label.setCellFormat(wc);
						s.addCell(label);
					}
				}else if(fm.getColumnName().equalsIgnoreCase("CTS")){
					Label label = new Label(c, x, nfCts.format(Double.parseDouble(cells.get(j))));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else{
						Label label = new Label(c, x, NumberUtils.isNumber(cells.get(j))?nfCts.format(Double.parseDouble(cells.get(j))):cells.get(j));
						label.setCellFormat(wc);
						if(fm.getColumnName().equalsIgnoreCase("rap") )
							label.setCellFormat(wcBG);
						s.addCell(label);
					}
				c++;
			}
			x++;
		}
		if(isUserData){
				Map userData = container.getUserdata();
				j=0;
				s.addCell(new Label(j++, x, "Totals"));
				s.addCell(new Label(j++, x, "Stones "+userData.get("pktCode").toString()));
				if(userData.get("cts")!=null)
				s.addCell(new Label(caratIdx, x, "Carats "+nfCts.format(Double.parseDouble(userData.get("cts").toString()))));
				if(userData.get("rap")!=null)
					s.addCell(new Label(discIdx, x, "Avg Discount "+nfCts.format(Double.parseDouble(userData.get("rap").toString()))));
				if(userData.get("rate")!=null)
					s.addCell(new Label(rateIdx, x, "Avg. rate/cts "+nfCts.format(Double.parseDouble(userData.get("rate").toString()))));
				if(userData.get("total")!=null)
					s.addCell(new Label(totalIdx, x, "Total Value "+nfCts.format(Double.parseDouble(userData.get("total").toString()))));
			}
		return x;
	}
	public static int fillXLPairSimplified(WritableSheet s, JQGridContainer container, List<FileMap> fileMapList,int x, int fnc)
	throws Exception {
		Logger logger = Logger.getLogger(CommonUtil.class.getName());
		boolean isHdr = true;
		x++;
		x++;
		SheetSettings settings = s.getSettings();
		settings.setDefaultRowHeight(400);
		
		List<JQGridRow> rows =container.getRows();
		NumberFormat nf = new DecimalFormat("#0");
		
		NumberFormat nfCts = new DecimalFormat("#0.00");
		nfCts.setRoundingMode(RoundingMode.FLOOR);
		
		WritableCellFormat wc = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wc.setAlignment(jxl.format.Alignment.CENTRE);
		wc.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		
		WritableCellFormat wcBG = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wcBG.setAlignment(jxl.format.Alignment.CENTRE);
		wcBG.setBackground(Colour.AQUA);
		wcBG.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		Label label2 = new Label(0, x++, "Pairs");
		label2.setCellFormat(wc);
		s.addCell(label2);
		
		int rateIdx = 0;
		int discIdx = 0;
		int caratIdx = 0;
		int totalIdx = 0;
		for (int i = 0; i < fileMapList.size(); i++) {
			FileMap fileMap = fileMapList.get(i);
			if(fileMap.getColumnName()!=null){
				if(fnc != 1 ){
					if((fileMap.getColumnName().equals("FNC")||
							fileMap.getColumnName().equals("FNCI")||fileMap.getColumnName().equals("FNCO"))){
						continue;
					}   
				}
			}
			Label label = new Label(i, x, "  "+(fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():
																	(fileMapList.get(i).getColumnName()!=null?fileMapList.get(i).getColumnName():""))+"  ");
			label.setCellFormat(wc);
			if(fileMapList.get(i).getColumnName()!=null){
				if(fileMapList.get(i).getColumnName().equals("rate"))
					rateIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("rap")){
					discIdx = i;
					label.setCellFormat(wcBG);
				}
				else if(fileMapList.get(i).getColumnName().equals("CTS"))
					caratIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("total"))
					totalIdx = i;
			}else{
				
			}
			if(isHdr){
				s.addCell(label);
				CellView cell=s.getColumnView(i);
				cell.setAutosize(true);
				s.setColumnView(i, cell);
			}
		}
		x++;
		
		for (int i = 0; i < rows.size(); i++) {
			List<String> cells = rows.get(i).getCell();
			//if(i%2==0)x+=1;
			for (int j = 0; j < fileMapList.size(); j++) {
				FileMap fm = fileMapList.get(j);
				if(fm.getColumnName()!=null){
					if(fnc != 1 ){
						if((fm.getColumnName().equals("FNC")||
								fm.getColumnName().equals("FNCI")||fm.getColumnName().equals("FNCO"))){
							continue;
						}   
					}
				}
				if(fileMapList.get(j).getFileId() == 0)
					break;
				
				if(fm.getColumnName()==null){
					Label label = new Label(j, x, "");
					label.setCellFormat(wc);
					s.addCell(label);

				}else if( fm.getColumnName().equalsIgnoreCase("certLabId")){
					logger.info(j+"  ZZZZZZZZ pair "+x+ "  "+cells.get(j)+" certLabId " );
					
					Label label = new Label(j, x, cells.get(j));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else if( fm.getColumnName().equalsIgnoreCase("rate") || fm.getColumnName().equalsIgnoreCase("total") ){
					if(!StringUtils.isEmpty(cells.get(j))){
						Label label =new Label(j, x, nf.format(Double.parseDouble(cells.get(j))));
						label.setCellFormat(wc);
						s.addCell(label);
					}else{
						Label label = new Label(j, x, cells.get(j));
						label.setCellFormat(wc);
						s.addCell(label);
					}
				}else if(fm.getColumnName().equalsIgnoreCase("CTS")){
					String cts = cells.get(j);
					Label label = null;
					if(cts !=null && !StringUtils.isEmpty(cts)){
						try
					    {
							label = new Label(j, x, nfCts.format(Double.parseDouble(cts)));
							
					    }
					    catch(NumberFormatException ex)
					    {
					    	label = new Label(j, x, nfCts.format(0));
					    	continue;
					    }
					    label.setCellFormat(wc);
						s.addCell(label);
					}
				}
				else{
						Label label = new Label(j, x, NumberUtils.isNumber(cells.get(j))?nfCts.format(Double.parseDouble(cells.get(j))):cells.get(j));
						label.setCellFormat(wc);
						if(fm.getColumnName().equalsIgnoreCase("rap") )
							label.setCellFormat(wcBG);
						s.addCell(label);
					}
			}
			x++;
		}
		
			Map userData = container.getUserdata();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX SIZE "+userData.size());
			int j=0;
			s.addCell(new Label(j++, x, "Pairs Totals"));
			s.addCell(new Label(j++, x, "Stones "+userData.get("pktCode").toString()));
			if(userData.get("cts")!=null)
			s.addCell(new Label(caratIdx, x, "Carats "+nfCts.format(Double.parseDouble(userData.get("cts").toString()))));
			if(userData.get("rap")!=null)
				s.addCell(new Label(discIdx, x, "Avg Discount (Pairs) "+nfCts.format(Double.parseDouble(userData.get("rap").toString()))));
			return x++;
	}
	public static int fillXLPairRegular(WritableSheet s, JQGridContainer container, List<FileMap> fileMapList,int x)
	throws Exception {
		Logger logger = Logger.getLogger(CommonUtil.class.getName());
		boolean isHdr = false;
		if(x == 1 ){
			isHdr = true;
		}
		x++;
		x++;
		SheetSettings settings = s.getSettings();
		settings.setDefaultRowHeight(400);
		
		List<JQGridRow> rows =container.getRows();
		NumberFormat nf = new DecimalFormat("#0");
		
		NumberFormat nfCts = new DecimalFormat("#0.00");
		nfCts.setRoundingMode(RoundingMode.FLOOR);
		
		WritableCellFormat wc = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wc.setAlignment(jxl.format.Alignment.CENTRE);
		wc.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		
		WritableCellFormat wcBG = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10));
		wcBG.setAlignment(jxl.format.Alignment.CENTRE);
		wcBG.setBackground(Colour.AQUA);
		wcBG.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
		
		Label label2 = new Label(0, x++, "Pairs");
		label2.setCellFormat(wc);
		s.addCell(label2);
		
		int rateIdx = 0;
		int discIdx = 0;
		int caratIdx = 0;
		int totalIdx = 0;
		for (int i = 0; i < fileMapList.size(); i++) {
			Label label = new Label(i, 2, "  "+(fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():
																	(fileMapList.get(i).getColumnName()!=null?fileMapList.get(i).getColumnName():""))+"  ");
			label.setCellFormat(wc);
			if(fileMapList.get(i).getColumnName()!=null){
				if(fileMapList.get(i).getColumnName().equals("rate"))
					rateIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("rap")){
					discIdx = i;
					label.setCellFormat(wcBG);
				}
				else if(fileMapList.get(i).getColumnName().equals("CTS"))
					caratIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("total"))
					totalIdx = i;
			}else{
				
			}
			if(isHdr){
				s.addCell(label);
				CellView cell=s.getColumnView(i);
				cell.setAutosize(true);
				s.setColumnView(i, cell);
			}
		}
		
		
		for (int i = 0; i < rows.size(); i++) {
			List<String> cells = rows.get(i).getCell();
			//if(i%2==0)x+=1;
			for (int j = 0; j < fileMapList.size(); j++) {
				
				if(fileMapList.get(j).getFileId() == 0)
					break;
				FileMap fm = fileMapList.get(j);
				
				if(fm.getColumnName()==null){
					Label label = new Label(j, x, "");
					label.setCellFormat(wc);
					s.addCell(label);

				}else if( fm.getColumnName().equalsIgnoreCase("certLabId")){
					logger.info(j+"  ZZZZZZZZ pair "+x+ "  "+cells.get(j)+" certLabId " );
					Label label = new Label(j, x, cells.get(j));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else if( fm.getColumnName().equalsIgnoreCase("rate") || fm.getColumnName().equalsIgnoreCase("total") ){
					if(!StringUtils.isEmpty(cells.get(j))){
						Label label =new Label(j, x, nf.format(Double.parseDouble(cells.get(j))));
						label.setCellFormat(wc);
						s.addCell(label);
					}else{
						Label label = new Label(j, x, cells.get(j));
						label.setCellFormat(wc);
						s.addCell(label);
					}
				}else if(fm.getColumnName().equalsIgnoreCase("CTS")){
					Label label = new Label(j, x, nfCts.format(Double.parseDouble(cells.get(j))));
					label.setCellFormat(wc);
					s.addCell(label);
				}
				else{
						Label label = new Label(j, x, NumberUtils.isNumber(cells.get(j))?nfCts.format(Double.parseDouble(cells.get(j))):cells.get(j));
						label.setCellFormat(wc);
						if(fm.getColumnName().equalsIgnoreCase("rap") )
							label.setCellFormat(wcBG);
						s.addCell(label);
					}
			}
			x++;
		}
		
			Map userData = container.getUserdata();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX SIZE "+userData.size());
			int j=0;
			s.addCell(new Label(j++, x, "Pairs Totals"));
			s.addCell(new Label(j++, x, "Stones "+userData.get("pktCode").toString()));
			if(userData.get("cts")!=null)
			s.addCell(new Label(caratIdx, x, "Carats "+nfCts.format(Double.parseDouble(userData.get("cts").toString()))));
			if(userData.get("rap")!=null)
				s.addCell(new Label(discIdx, x, "Avg Discount (Pairs) "+nfCts.format(Double.parseDouble(userData.get("rap").toString()))));
			return x++;
	}
	public static void fillUserData(WritableSheet s, Map userData,  List<FileMap> fileMapList,int x)
	throws Exception {
		NumberFormat nfCts = new DecimalFormat("#0.00");
		nfCts.setRoundingMode(RoundingMode.FLOOR);
		
		int rateIdx = 0;
		int discIdx = 0;
		int caratIdx = 0;
		int totalIdx = 0;
		for (int i = 0; i < fileMapList.size(); i++) {
			if(fileMapList.get(i).getColumnName()!=null){
				if(fileMapList.get(i).getColumnName().equals("rate"))
					rateIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("rap")){
					discIdx = i;
				}
				else if(fileMapList.get(i).getColumnName().equals("CTS"))
					caratIdx = i;
				else if(fileMapList.get(i).getColumnName().equals("total"))
					totalIdx = i;
			}else{
				
			}
		}
		x++;
			int j=0;
			s.addCell(new Label(j++, x, "Totals"));
			s.addCell(new Label(j++, x, "Stones "+userData.get("pktCode").toString()));
			if(userData.get("cts")!=null)
			s.addCell(new Label(caratIdx, x, "Carats "+nfCts.format(Double.parseDouble(userData.get("cts").toString()))));
			if(userData.get("rap")!=null)
				s.addCell(new Label(discIdx, x, "Avg Discount "+nfCts.format(Double.parseDouble(userData.get("rap").toString()))));
			if(userData.get("rate")!=null)
				s.addCell(new Label(rateIdx, x, "Avg. rate/cts "+nfCts.format(Double.parseDouble(userData.get("rate").toString()))));
			if(userData.get("total")!=null)
				s.addCell(new Label(totalIdx, x, "Total Value "+nfCts.format(Double.parseDouble(userData.get("total").toString()))));
	}
	
	public static void fillBlankData(WritableSheet s, Map userData,  List<FileMap> fileMapList,int x)
			throws Exception {
			for (int i = 0; i < fileMapList.size(); i++) {
				Label label = new Label(i, 0, "  "+(fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():
																		(fileMapList.get(i).getColumnName()!=null?fileMapList.get(i).getColumnName():""))+"  ");
				s.addCell(label);
				CellView cell=s.getColumnView(i);
				cell.setAutosize(true);
				s.setColumnView(i, cell);
			}
	}
	 
		public static String getSizesString(Double value) {
			if(value <= 0.180d )
				return "less then 0.18";
			else if(value >= 0.180d && value <= 0.229d )	
				return "0.18 to 0.22";
			else if(value >= 0.230d && value <= 0.299d )	
				return "0.23 to 0.29";
			else if(value >= 0.30d && value <= 0.399d )	
				return "0.30 to 0.39";
			else if(value >= 0.40d && value <= 0.499d )	
				return "0.40 to 0.49";
			else if(value >= 0.50d && value <= 0.599d )	
				return "0.50 to 0.59";
			else if(value >= 0.60d && value <= 0.699d )	
				return "0.60 to 0.69";
			else if(value >= 0.70d && value <= 0.799d )	
				return "0.70 to 0.79";
			else if(value >= 0.80d && value <= 0.899d )	
				return "0.80 to 0.89";
			else if(value >= 0.90d && value <= 0.999d )	
				return "0.90 to 0.99";
			else if(value >= 1d && value <= 1.499d )	
				return "1.00 to 1.49";
			else if(value >= 1.5d && value <= 1.999d )	
				return "1.50 to 1.99";
			else if(value >= 2d && value <= 2.999d )	
				return "2.00 to 2.99";
			else if(value >= 3d && value <= 3.999d )	
				return "3.00 to 3.99";
			else if(value >= 4d && value <= 4.999d )	
				return "4.00 to 4.99";
			else if(value >= 5d && value <= 9.999d  )	
				return "5.00 and above";
			else 
				return "10.00 and above";
		}
	
	public static HashMap<String, String> getBkupDetails() throws Exception{
		InputStream fis =  CommonUtil.class.getClassLoader().getResourceAsStream("/appConfig.properties");
		properties.load(fis);
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("dbUser", getPropertiesByName("jdbc.username"));
		sqlMap.put("dbPass", getPropertiesByName("jdbc.password"));
		String url = getPropertiesByName("jdbc.url");
		sqlMap.put("url", url);
		sqlMap.put("dbName", url.substring(url.lastIndexOf("/")+1));
		sqlMap.put("username", getPropertiesByName("b2b.admin.email.username"));
		sqlMap.put("password", getPropertiesByName("b2b.admin.email.password"));
		
		fis =  CommonUtil.class.getClassLoader().getResourceAsStream("/b2b.properties");
		properties.load(fis);
		sqlMap.put("loc", getPropertiesByName("b2b.download.filepath"));
		return sqlMap;
	} 
	
	public static int backUpScript(HashMap<String, String> sqlMap) throws Exception{
		String dbUser = sqlMap.get("dbUser");
		String dbPass = sqlMap.get("dbPass");
		String dbName = sqlMap.get("dbName");
		String loc = sqlMap.get("loc");
		
		String executeCmd = "";
		executeCmd = "mysqldump -u"+dbUser+" -p"+dbPass+" "+dbName+" -r "+loc+dbName+".sql";
		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();
				
		return processComplete;
	}
	
	public static int emptyDB(HashMap<String, String> sqlMap) throws Exception{
		String dbUser = sqlMap.get("dbUser");
		String dbPass = sqlMap.get("dbPass");
		String dbName = sqlMap.get("dbName");
		String url = sqlMap.get("url");
		Connection conn = null;
		Statement stmt  = null;
		int i=0;
    	try{
			  Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(url, dbUser, dbPass);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Deleting database...");
		      stmt = conn.createStatement();
		      
		      String sql = "DROP DATABASE "+dbName;
		      stmt.executeUpdate(sql);
		      System.out.println("Database deleted successfully...");
		      
		      sql = "CREATE DATABASE "+dbName;
		      i = stmt.executeUpdate(sql);
		      System.out.println("Database Created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }
				
		return i;
	}

	public static int restoreScript(HashMap<String, String> sqlMap) throws Exception{
		String dbUser = sqlMap.get("dbUser");
		String dbPass = sqlMap.get("dbPass");
		String dbName = sqlMap.get("dbName");
		String loc = sqlMap.get("loc");
		String url = sqlMap.get("url");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Statement stmt  = null;
		int i=0;
		
		try{
		conn = DriverManager.getConnection(url, dbUser, dbPass);
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		rs.last();
		int processComplete = -1;
		if(rs.getRow() == 0){
			String[] executeCmd = new String[]{"/bin/sh", "-c", "mysql -u" + dbUser+ " -p"+dbPass+" " + dbName+ " < "+loc+dbName+".sql"};
	
			Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
			processComplete = runtimeProcess.waitFor();
		}else{
			processComplete = -2;
		}
		return processComplete;
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	
 /**
  *  Converting Double Value into Big Decimal
  *  @param Double
  *  @return BigDecimal
  */
	
	  public static BigDecimal getBigDecimalFromDouble(Double number){
	    String strNumber = number.toString();
	    BigDecimal bdReturn = new BigDecimal(strNumber);
	    return bdReturn;
	  }

}

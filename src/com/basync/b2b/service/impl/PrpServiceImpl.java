package com.basync.b2b.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.rowExtract.ModulePrpRowExtract;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.SearchPrpData;
import com.basync.b2b.data.rowExtract.PrpDetailExtract;
import com.basync.b2b.data.rowExtract.PrpSectionRowMapper;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.data.rowExtract.SearchPrpCriteriaRowMapper;
import com.basync.b2b.data.rowExtract.SearchPrpExtract;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.util.StringUtils;

public class PrpServiceImpl  extends BaseService  implements IPrpService {


	@SuppressWarnings("unchecked")
	public List<PrpData> getSearchPrpList() throws Exception {
		 
		/*  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
          session.beginTransaction();
          
          List<PrpData> searchPrpList = session.createQuery("select pm.id, pm.prp, pm.data_Type, pm.min_value, pm.max_value, ps.web_hdr, ps.web_desc from " +
          "prp_master as pm, prp_section_list ps where pm.ID = prp_id and SECTION = 1").list();
         
          session.getTransaction().commit();
          System.out.println("$$$  ---> length  "+ searchPrpList.size());
		*/
		
		String sql = "select pm.id, pm.prp, pm.dataType, ps.subSectionId, ps.DIsplayDataType, pm.minValue, pm.maxValue, ps.webHdr, ps.webDesc from " +
		          " tb_prpmaster as pm, tb_prpsectionlist ps where pm.ID = ps.prpId and ps.sectionID = 1 and ps.validFlag = 0 order by sortId";
		
		if(this.getJdbcDao() ==null )
			System.out.println("################"+null);
		List<PrpData> searchPrpList = this.getJdbcDao().query(sql, null, 
				new RowMapperResultSetExtractor(new SearchPrpExtract())) ;

		return searchPrpList;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getPrpLOV()
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<QueryCodeDescription>> getPrpLOV()
			throws Exception {
		
		String sql = "select pm.prp, pd.prpValue, pd.prpValueDesc ,pd.SortId ,pm.dataType   from " +
        " tb_prpmaster as pm, tb_prpdetail pd where pm.ID = pd.prpId and pm.dataType >= 1 and pd.validFlag=1 order by pm.prpSortId, pd.sortId";

		List<PrpData> list = this.getJdbcDao().query(sql, null,
				new RowMapperResultSetExtractor(new PrpDetailExtract()));
		
		List<QueryCodeDescription> qdList = new ArrayList<QueryCodeDescription>();
		
		Map<String, List<QueryCodeDescription>> prpLovMap = new HashMap<String, List<QueryCodeDescription>>();
		PrpData spd;
		String lPrp = "";
		for(int i = 0 ; i<list.size() ;i++ ){
			spd =  new PrpData();
			spd = (PrpData)list.get(i);
			if(!lPrp.equals(spd.getPrp())){
				if(!lPrp.equals("")){
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+lPrp);
					prpLovMap.put(lPrp, qdList);
					qdList = new ArrayList<QueryCodeDescription>();
				}
			}
			QueryCodeDescription qd = new QueryCodeDescription();
				if(StringUtils.isEmpty(spd.getPrpValueDesc()))
					qd.setDescription(spd.getPrpValue());
				else
					qd.setDescription(spd.getPrpValue());
				if(spd.getDataType() == 2){
					qd.setId(spd.getPrpValue());
				}else{
					qd.setId(spd.getSortId());
				}
			lPrp = spd.getPrp();
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+lPrp);
		prpLovMap.put(lPrp, qdList);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+prpLovMap.size());
		return prpLovMap;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getResultPrpList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<PrpData> getResultPrpList(String sectionId) throws Exception {
		String sql = "select ps.prpid id, ps.prp, ps.webhdr, ps.webDesc, ps.width "+
					 "from tb_prpsectionlist ps "+
					 " where ps.sectionId = ? and ps.validFlag = 0 order by sortId";
	Object []param =new Object[1];
	param[0] = sectionId;
		
	
	return this.getJdbcDao().query(sql, param,
				new RowMapperResultSetExtractor(new PrpSectionRowMapper()));
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#insertSearchPrp(java.util.List, int, java.lang.String, int)
	 */
	public int insertSearchPrp(List<SearchPrpData> prpList, int userId,
			String searchType, boolean saveSearch,String sDesc) throws Exception {

		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "insert into tb_searchmaster (UserId, SearchDateTime, SearchType) values (?, ?, ?)";	
			Object[] param = new Object[3];
			param[0] = userId ;
			param[1] = smf.format(new Date()); 
			param[2] = searchType;
			
			int i = this.getJdbcDao().update(sql, param);
			
			sql =  "select max(id) from tb_searchmaster";
			
		int searchId = this.getJdbcDao().queryForInt(sql, null);
		
		List<Object[]> paramList = new ArrayList<Object[]>();
		sql = "insert into tb_searchprp (searchId, prpId, prpFromNum, prpToNum, prpIn) values (?, ?, ?, ?, ?) ";
		for (int a=0;a<prpList.size();a++){
			SearchPrpData spd = new SearchPrpData();
			spd=prpList.get(a);
			param = new Object[5];
			param[0] = searchId;
			param[1] = spd.getPrpId();
			param[2] = spd.getPrpFromNum();
			param[3] = spd.getPrpToNum();
			param[4] = spd.getPrpIn();
			paramList.add(param);
		}
		int z[] = this.getJdbcDao().batchUpdate(sql, paramList);
		
		if(saveSearch){
			sql = "insert into tb_smartsearch (searchId, dateTime, userId, Description) values (?, ?, ?, ?) ";
			param = new Object[4];
			param[0] = searchId;
			param[1] = smf.format(new Date());
			param[2] = userId;
			param[3] = sDesc;
			
			this.getJdbcDao().update(sql, param);
		}
		return searchId;
		
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getSearchCriteria(java.util.List)
	 */
	public String getSearchCriteria(List<SearchPrpData> prpList)
			throws Exception {
		StringBuilder whereClause = new StringBuilder(""); 
		for(int i= 0; i< prpList.size();i++){
			SearchPrpData spd = new SearchPrpData();
		    spd = prpList.get(i);
		  /*  if(spd.getPrp().equals("RAP")){
		    	whereClause.append(" and "+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
		    }
		    else if(spd.getPrp().equals("RATE")){
		    	whereClause.append(" and s."+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
		    }*/
		     if(!StringUtils.isEmpty(spd.getPrpIn())) {
		    	 if(spd.getPrpId() == 0 || spd.getDataType() == 2 ){
		    		 if(spd.getPrpIn().length() > 0){
		    			 String arr[] = spd.getPrpIn().split(",");
		    			 if(arr.length ==1 && arr[0].equals("-1"))
		    				 continue;
		    			 whereClause.append(" and (");
		    			 for (int j = 0; j < arr.length; j++) {
			    				 if(j == 0)
			    					 whereClause.append(" "+spd.getPrp()+" like '%"+arr[j]+"%' ");
			    				 else
			    					 whereClause.append(" OR "+spd.getPrp()+" like '%"+arr[j]+"%' ");
		    			 }
		    			 whereClause.append(" )");
		    		 }
		    	 }else{
		    		 whereClause.append(" and "+spd.getPrp()+"_so in ("+spd.getPrpIn()+") ");
		    	 }
		    	 if(spd.getPrp().equals("LAB")){
					 if(spd.getPrpIn().contains("100000")){
						 whereClause.append(" or  "+spd.getPrp()+"_so is null ");
					 }
				 }
			}else{	
					if(spd.getPrpFromNum() == null && spd.getPrpToNum() ==null ){
						whereClause.append(" and "+spd.getPrp()+"_so is null " );
						continue;
					}
					if(spd.getPrpFromNum() != -1f ){
						if(spd.getPrp().equals("CTS"))
							whereClause.append(" and "+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
						else if(spd.getPrp().equals("RAP") ||spd.getPrp().equals("RATE"))
							whereClause.append(" and s."+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
						else
							whereClause.append(" and "+spd.getPrp()+"_so >= "+spd.getPrpFromNum()+" ");
					}
					if(spd.getPrpToNum() != 9999f){
						if(spd.getPrp().equals("CTS"))
							whereClause.append(" and "+spd.getPrp()+" <= "+spd.getPrpToNum()+" ");
						else if(spd.getPrp().equals("RAP") ||spd.getPrp().equals("RATE"))
							whereClause.append(" and s."+spd.getPrp()+" <= "+spd.getPrpToNum()+" ");
						else
							whereClause.append(" and "+spd.getPrp()+"_so <= "+spd.getPrpToNum()+" ");
					}
			}
		}	
		return whereClause.toString();
	}
	
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getSearchCriteriaMix(java.util.List)
	 */
	public String getSearchCriteriaMix(List<SearchPrpData> prpList)
			throws Exception {
		StringBuilder whereClause = new StringBuilder(""); 
		whereClause.append(" and pcs <> 1 ");
		for(int i= 0; i< prpList.size();i++){
			SearchPrpData spd = new SearchPrpData();
		    spd = prpList.get(i);
		  /*  if(spd.getPrp().equals("RAP")){
		    	whereClause.append(" and "+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
		    }
		    else if(spd.getPrp().equals("RATE")){
		    	whereClause.append(" and s."+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
		    }*/
		    if(spd.getPrp().equals("SH")||spd.getPrp().equals("PU")||spd.getPrp().equals("C")) {
		    		 if(spd.getPrpIn().length() > 0){
		    			 String arr[] = spd.getPrpIn().split(",");
		    			 whereClause.append(" and (");
		    			 for (int j = 0; j < arr.length; j++) {
		    				 if(j == 0)
		    					 whereClause.append(" "+arr[j]+" between "+spd.getPrp()+"Fr_so and "+spd.getPrp()+"To_so ");
		    				 else
		    					 whereClause.append(" OR "+arr[j]+" between "+spd.getPrp()+"Fr_so and "+spd.getPrp()+"To_so ");
		    			 }
		    			 whereClause.append(" )");
		    		 }
			}else if(!StringUtils.isEmpty(spd.getPrpIn())) {
		    	 if(spd.getPrpId() == 0 || spd.getDataType() == 2 ){
		    		 if(spd.getPrpIn().length() > 0){
		    			 String arr[] = spd.getPrpIn().split(",");
		    			 whereClause.append(" and (");
		    			 for (int j = 0; j < arr.length; j++) {
		    				 if(j == 0)
		    					 whereClause.append(" "+spd.getPrp()+" like '%"+arr[j]+"%' ");
		    				 else
		    					 whereClause.append(" OR "+spd.getPrp()+" like '%"+arr[j]+"%' ");
		    			 }
		    			 whereClause.append(" )");
		    		 }
		    	 }else{
		    		 whereClause.append(" and "+spd.getPrp()+"_so in ("+spd.getPrpIn()+") ");
		    	 }
			}else{	
					if(spd.getPrpFromNum() == null && spd.getPrpToNum() ==null ){
						whereClause.append(" and "+spd.getPrp()+"_so is null " );
						continue;
					}
					
					
					if(spd.getPrpFromNum() != -1f ){
						if(spd.getPrp().equals("CTS") || spd.getPrp().equals("AVGCTS") ||spd.getPrp().equals("PPC"))
							whereClause.append(" and "+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
						else if(spd.getPrp().equals("RAP") ||spd.getPrp().equals("RATE"))
							whereClause.append(" and s."+spd.getPrp()+" >= "+spd.getPrpFromNum()+" ");
						else if(spd.getPrp().equals("TOTAL"))
							whereClause.append(" and (s.RATE*sp.CTS) >= "+spd.getPrpFromNum()+" ");
						else
							whereClause.append(" and "+spd.getPrp()+"_so >= "+spd.getPrpFromNum()+" ");
					}
					if(spd.getPrpToNum() != 9999f){
						if(spd.getPrp().equals("CTS") || spd.getPrp().equals("AVGCTS") ||spd.getPrp().equals("PPC")||spd.getPrp().equals("PARTYP"))
							whereClause.append(" and "+spd.getPrp()+" <= "+spd.getPrpToNum()+" ");
						else if(spd.getPrp().equals("RAP") ||spd.getPrp().equals("RATE"))
							whereClause.append(" and s."+spd.getPrp()+" <= "+spd.getPrpToNum()+" ");
						else if(spd.getPrp().equals("TOTAL"))
							whereClause.append(" and (s.RATE*sp.CTS) <= "+spd.getPrpFromNum()+" ");
						else
							whereClause.append(" and "+spd.getPrp()+"_so <= "+spd.getPrpToNum()+" ");
					}
				
			}
		}	
		return whereClause.toString();
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getSearchPrpDetail(int, int)
	 */
	public Map getSearchPrpDetail(int searchId, int smartSearchId)
			throws Exception {
		String sql="";
		Object[] param = new Object[1];
		if(searchId != -1){
		 sql = "select IFNULL(pm.prp,'partyAccId') prp, sp.prpId, sp.PrpFromNum, sp.PrpToNum, sp.prpIn from tb_searchprp sp " +
				" left outer join tb_prpmaster pm on pm.ID = sp.prpId join tb_searchmaster sm on sm.ID = sp.SearchId " +
				" where sm.ID =? ";
		 
		 param[0] = searchId;
		}else{
		 sql = "select IFNULL(pm.prp,'partyAccId') prp, sp.prpId, sp.PrpFromNum, sp.PrpToNum, sp.prpIn from tb_searchprp sp  " +
		 		" left outer join tb_prpmaster pm on pm.ID= sp.prpId join tb_smartsearch ss on ss.searchID = sp.SearchId " +
		 		" where ss.ID =? ";
		 param[0] = smartSearchId;
		}
		HashMap<String, SearchPrpData> sPrpMap = new HashMap<String, SearchPrpData>();
		List<SearchPrpData> list =  this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new SearchPrpCriteriaRowMapper()));
		
		
		for(SearchPrpData spd :list ){
			sPrpMap.put(spd.getPrp(), spd);
		}
		logger.debug("\n\n$$$$$$$$$$$$$$$$$$$$$"+sPrpMap.size());
		return sPrpMap;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getSmasrtSearch(int)
	 */
	public List<QueryDescription> getSmasrtSearch(int userId) throws Exception {
			String sql ="select id, description from tb_smartsearch where userId = ? " +
						"and deleteFlag = 0 ";
			
			Object[] param = new Object[1];
			param[0] = userId;
			
			List<QueryDescription> list = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	
		if(list.size()>0)
			return list;
		else
			return null;
				
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IPrpService#getModulePrpList(java.lang.String, Integer)
	 */
	public List<PrpData> getModulePrpList(String module, Integer flag)
			throws Exception {
		

        String sql = "select pm.id, pm.prp,pm.prpDesc, pm.dataType, pm.minValue, pm.maxValue, mp.fieldDisplayType "+
        				" from tb_prpmaster as pm, tb_module_prp mp where pm.ID = mp.prpId and mp.module = ? and mp.Flag = ? order by prpSortId ";

        Object []param =new Object[2];
    	param[0] = module;
    	param[1] = flag;
        
        List<PrpData> modulePrpList = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new ModulePrpRowExtract()));
       
        return modulePrpList;
    }
	
	
	
}

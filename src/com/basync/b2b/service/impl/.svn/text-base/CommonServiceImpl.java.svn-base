
package com.basync.b2b.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.cache.CacheInterceptor;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.rowExtract.ModulePrpRowExtract;
import com.basync.b2b.crm.data.rowExtract.QueryCodeDescriptionRowExtract;
import com.basync.b2b.dao.JdbcDao;
import com.basync.b2b.dao.JdbcSlaveDao;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.rowExtract.PrpDetailExtract;
import com.basync.b2b.data.rowExtract.PrpListRowMapper;
import com.basync.b2b.data.rowExtract.PrpSectionRowMapper;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.StringUtils;



public class CommonServiceImpl extends BaseService implements ICommonService {

	protected Logger logger = Logger.getLogger(getClass());
	
	private static int pageSize = 0;
	
	private JdbcDao jdbcDao;
	
	private JdbcSlaveDao jdbcSlaveDao;
	
	private CacheInterceptor cacheInterceptor;
	
	Properties properties = new Properties();
	
	List<String> recordsPerPageOptions = null;
	
	
	public JdbcDao getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	public CommonServiceImpl() throws IOException {
		super();


		InputStream fis = this.getClass().getResourceAsStream("/b2b.properties");
		properties.load(fis);
	}
	public CommonServiceImpl(String fileName) throws IOException {
		super();


		InputStream fis = this.getClass().getResourceAsStream(fileName);
		properties.load(fis);
	}

	/**
	 * @return the cacheInterceptor
	 */
	public CacheInterceptor getCacheInterceptor() {
		return cacheInterceptor;
	}

	/**
	 * @param cacheInterceptor the cacheInterceptor to set
	 */
	public void setCacheInterceptor(CacheInterceptor cacheInterceptor) {
		this.cacheInterceptor = cacheInterceptor;
	}

	/**
	 * @return the jdbcSlaveDao
	 */
	public JdbcSlaveDao getJdbcSlaveDao() {
		return jdbcSlaveDao;
	}

	/**
	 * @param jdbcSlaveDao the jdbcSlaveDao to set
	 */
	public void setJdbcSlaveDao(JdbcSlaveDao jdbcSlaveDao) {
		this.jdbcSlaveDao = jdbcSlaveDao;
	}

	public String getPropertiesByName(String key) throws Exception {
		return properties.getProperty(key, "");
	}


	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#loginLog(int, java.lang.String)
	 */
	public int loginLog(int userId, String ipAddr) throws Exception {

		
		String sql="insert into tb_loginlog (userId, loginDateTime, IP) values (?, curdate(), ?)";
		Object[] param= new Object[2];
		param[0] = userId;
		param[1] = ipAddr;
		
		return this.getJdbcDao().update(sql, param);
		
	}


	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getAllPrpList()
	 */
	public List<PrpData> getAllPrpList() throws Exception {
		
		String sql = "select pm.id, pm.prp,pm.prpDesc, pm.dataType, pm.minValue, pm.maxValue from " +
        " tb_prpmaster as pm inner join tb_module_prp mp on mp.prpid = pm.id and mp.flag = 1 order by prpSortId";
		
		if(this.getJdbcDao() ==null )
			System.out.println("################"+null);
		List<PrpData> prpList = this.getJdbcDao().query(sql, null, 
				new RowMapperResultSetExtractor(new PrpListRowMapper())) ;
		
		return prpList;
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getResultPrpList(java.lang.String)
	 */
	public List<PrpData> getResultPrpList(String sectionId) throws Exception {
		String sql = "select ps.prpid as id, ps.prp, ps.webhdr, ps.webDesc, ps.width "+
		 "from tb_prpsectionlist ps "+
		 " where ps.sectionId = ? and ps.validFlag = 0 order by sortId";
		Object []param =new Object[1];
		param[0] = sectionId;
		
		
		return this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new PrpSectionRowMapper()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getPrpLOV()
	 */
	public Map<String, List<QueryCodeDescription>> getPrpLOV() throws Exception {
		
		String sql = "select pm.prp, pd.prpValue, pd.prpValueDesc , pd.SortId, pm.dataType  from " +
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
				
				qdList.add(qd);
			lPrp = spd.getPrp();
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+lPrp);
		prpLovMap.put(lPrp, qdList);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+prpLovMap.size());
		return prpLovMap;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getModulePrpList(java.lang.String, java.lang.Integer)
	 */
	public List<PrpData> getModulePrpList(String module, Integer flag)
			throws Exception {

        String sql = "select pm.id, pm.prp,mp.dispDesc prpDesc, pm.dataType, pm.minValue, pm.maxValue, mp.fieldDisplayType "+
        				" from tb_prpmaster as pm, tb_module_prp mp where pm.ID = mp.prpId and mp.module = ? and mp.Flag = ? order by mp.SortId ";

        Object []param =new Object[2];
    	param[0] = module;
    	param[1] = flag;
        
        List<PrpData> modulePrpList = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new ModulePrpRowExtract()));
       
        return modulePrpList;	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getPrtyByType(java.lang.Integer, java.lang.String)
	 */
	public List<QueryDescription> getPartyByType(Integer actiVeflag,
			String partyType) throws Exception {
		Object[] param = new Object[2];
		param[0] = actiVeflag;
		param[1] = partyType;
		
		String sql = "select pa.id as id, pm.companyName as description from tb_partyAcc pa inner join tb_partyAddMaster pam on pam.id = pa.partyAddId " +
		" inner join tb_partyMaster pm on pm.id = pam.partyId inner join tb_termmaster tm on tm.id = pa.termId where pm.activeFlag = ? and typeOfParty = ? order by companyName"; 
		List<QueryDescription> list = this.getJdbcDao().query(sql, param, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	  return list;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getTermsList()
	 */
	public List<QueryDescription> getTermsList() throws Exception {
		String sql ="SELECT id, termcode description FROM tb_termmaster order by sort" ;
		return this.getJdbcDao().query(sql, null, new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}

		
	/* (non-Javadoc)
	 * @see com.basync.b2b.service.ICommonService#getTermsList()
	 * //Arun sharma 06-01-2012
	 */
	public List<QueryDescription> getMemoStatusList() throws Exception {
		String sql = "select id, statuscode as description from tb_orderStatusMaster order by statuscode";
		List<Object> param = new ArrayList<Object>();
		return this.getJdbcSlaveDao().query(sql.toString(), param.toArray(), new RowMapperResultSetExtractor(new QueryDescriptionExtract()));
	}
	
	public List<QueryCodeDescription> getMemoTypeList() throws Exception{
		String sql = "select shortcode as id, description from tb_memotype";
		List<Object> param = new ArrayList<Object>();
		return this.getJdbcSlaveDao().query(sql.toString(), param.toArray(), new RowMapperResultSetExtractor(new QueryCodeDescriptionRowExtract()));	
	}
}

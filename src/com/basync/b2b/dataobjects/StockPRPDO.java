package com.basync.b2b.dataobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="tb_stockprp", catalog = "hridhesh")
public class StockPRPDO implements Serializable{
	
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 7800673192152044067L;
	
@Id	
  @Column(name="PKTID", insertable = false, updatable = false)
  private Long PKTID 	;
@Id
  @Column(name="GRPID")
  private Long GRPID = 1L	;
  @Column(name="CTS")
  private Double CTS	;
  @Column(name="certLabId")
  private String certLabId	;
  @Column(name="certLabUrl")
  private String certLabUrl	;
  @Column(name="Status")
  private Double Status	;
  @Column(name="SH")
  private String SH 	;
  @Column(name="SH_so")
  private Double SH_so	;
  @Column(name="SZ")
  private String SZ 	;
  @Column(name="SZ_so")
  private Double SZ_so	;
  @Column(name="PU")
  private String PU 	;
  @Column(name="PU_so")
  private Double PU_so	;
  @Column(name="C")
  private String C	;
  @Column(name="C_so")
  private Double C_so 	;
  @Column(name="CT")
  private String CT 	;
  @Column(name="CT_so")
  private Double CT_so	;
  @Column(name="IG")
  private String IG 	;
  @Column(name="IG_so")
  private Double IG_so	;
  @Column(name="BI")
  private String BI 	;
  @Column(name="BI_so")
  private Double BI_so	;
  @Column(name="TI")
  private String TI 	;
  @Column(name="TI_so")
  private Double TI_so	;
  @Column(name="DP")
  private String DP 	;
  @Column(name="DP_so")
  private Double DP_so	;
  @Column(name="T")
  private String T 	;
  @Column(name="T_so")
  private Double T_so	;
  @Column(name="v")
  private String PO 	;
  @Column(name="PO_so")
  private Double PO_so	;
  @Column(name="SY")
  private String SY 	;
  @Column(name="SY_so")
  private Double SY_so	;
  @Column(name="FL")
  private String FL 	;
  @Column(name="FL_so")
  private Double FL_so	;
  @Column(name="GD")
  private String GD 	;
  @Column(name="GD_so")
  private Double GD_so	;
  @Column(name="PA")
  private String PA 	;
  @Column(name="PA_so")
  private Double PA_so	;
  @Column(name="AD")
  private String AD 	;
  @Column(name="AD_so")
  private Double AD_so	;
  @Column(name="MD")
  private String MD 	;
  @Column(name="MD_so")
  private Double MD_so	;
  @Column(name="XD")
  private String XD 	;
  @Column(name="XD_so")
  private Double XD_so	;
  @Column(name="D")
  private String D 	;
  @Column(name="D_so")
  private Double D_so 	;
  @Column(name="CA")
  private String CA 	;
  @Column(name="CH_so")
  private Double CH_so	;
  @Column(name="CA_so")
  private Double CA_so	;
  @Column(name="CH")
  private String CH	;
  @Column(name="FLS")
  private String FLS 	;
  @Column(name="FLS_so")
  private Double FLS_so	;
  @Column(name="SD")
  private String SD 	;
  @Column(name="SD_so")
  private Double SD_so	;
  @Column(name="LS")
  private String LS 	;
  @Column(name="LS_so")
  private Double LS_so	;
  @Column(name="TOP")
  private String TOP 	;
  @Column(name="TOP_so")
  private Double TOP_so	;
  @Column(name="COP")
  private String COP 	;
  @Column(name="COP_so")
  private Double COP_so	;
  @Column(name="POP")
  private String POP 	;
  @Column(name="POP_so")
  private Double POP_so	;
  @Column(name="L")
  private String L	;
  @Column(name="L_so")
  private Double L_so 	;
  @Column(name="W")
  private String W 	;
  @Column(name="W_so")
  private Double W_so 	;
  @Column(name="PD")
  private String PD 	;
  @Column(name="PD_so")
  private Double PD_so	;
  @Column(name="CU")
  private String CU 	;
  @Column(name="CU_so")
  private Double CU_so	;
  @Column(name="SL")
  private String SL 	;
  @Column(name="SL_so")
  private Double SL_so	;
  @Column(name="LG")
  private String LG 	;
  @Column(name="LG_so")
  private Double LG_so	;
  @Column(name="GBA")
  private String GBA 	;
  @Column(name="GBA_so")
  private Double GBA_so	;
  @Column(name="GHA")
  private String GHA 	;
  @Column(name="GHA_so")
  private Double GHA_so	;
  @Column(name="ORG")
  private String ORG 	;
  @Column(name="ORG_so")
  private Double ORG_so	;
  @Column(name="LAB")
  private String LAB    ;
  @Column(name="LAB_so")
  private Double LAB_so ;
  @Column(name="FNC")
  private String FNC 	;
  @Column(name="FNC_so")
  private Double FNC_so	;
  @Column(name="FNCI")
  private String FNCI    ;
  @Column(name="FNCI_so")
  private Double FNCI_so ;	  
  @Column(name="FNCO")
  private String FNCO 	;
  @Column(name="FNCO_so")
  private Double FNCO_so	;
  @Column(name="GTN")
  private String GTN    ;
  @Column(name="GTN_so")
  private Double GTN_so ;	
  @Column(name="GTH")
  private String GTH    ;
  @Column(name="GTH_so")
  private Double GTH_so ;	  
  @Column(name="GC")
  private String GC    ;
  @Column(name="GC_so")
  private Double GC_so ;	  
  @Column(name="CC")
  private String CC    ;
  @Column(name="CC_so")
  private Double CC_so ;	
  @Column(name="LI")
  private String LI    ;
  @Column(name="LI_so")
  private Double LI_so ;	
  
  @Column(name="NT")
  private String NT    ;
  @Column(name="NT_so")
  private Double NT_so ;
  
  @Column(name="shFr")
  private String shFr    ;
  @Column(name="shFr_so")
  private Double shFr_so ;
  
  @Column(name="shTo")
  private String shTo    ;
  @Column(name="shTo_so")
  private Double shTo_so ;
  
  
  @Column(name="puFr")
  private String puFr    ;
  @Column(name="puFr_so")
  private Double puFr_so ;
  
  
  @Column(name="puTo")
  private String puTo    ;
  @Column(name="puTo_so")
  private Double puTo_so ;
  
  
  @Column(name="cFr")
  private String cFr    ;
  @Column(name="cFr_so")
  private Double cFr_so ;
  
  
  @Column(name="cTo")
  private String cTo    ;
  @Column(name="cTo_so")
  private Double cTo_so ;
  
  @Column(name="AVGCTS")
  private Double AVGCTS ;
  
  @Column(name="SIEVE")
  private String SIEVE ;
  
  @Column(name="SIEVE_so")
  private Double SIEVE_so ;
  
  @Column(name="sieveFr")
  private String sieveFr;
  
  @Column(name="sieveFr_so")
  private String sieveFr_so;

  @Column(name="sieveTo")
  private String sieveTo;
  
  @Column(name="sieveTo_so")
  private String sieveTo_so;
  
  
  @Column(name="PPC")
  private Double PPC ;
  
  @Column(name="PTYP")
	private String PTYP; 
  
  @Column(name="PTYP_so")
	private Double PTYP_so; 

  //only for log use
  @Column(name="lastUpdateDate")
  private String lastUpdateDate ;	
  
  @Column(name="updateBy")
  private Integer updateBy ;	
  
  @Column(name="rateLab")
  private Double rateLab ;
  
  @Column(name="rapLab")
  private Double rapLab ;

  @Column(name="rapPriceLab")
  private Double rapPriceLab ;
  
  @Transient
  private String oldLab ;
  
  @ManyToOne
  @JoinColumn(name="PRPID", insertable = false, updatable = false)
  private StockMasterDO stockMasterDO;
  
  @Column(name="job_no")
  private String job_no;  
  
  @Column(name="ctrl_no")
  private String ctrl_no;  
  
  @Column(name="DD")
  private String DD;  
  
  @Column(name="PU_ST")
  private String PU_ST;  
  
  @Column(name="C_DESC")
  private String C_DESC; 
  
  @Column(name="LH")
  private String LH;  
  
  @Column(name="PAINT")
  private String PAINT;  
  
  @Column(name="PAINT_COMM")
  private String PAINT_COMM;  
  
  @Column(name="KEY2SYMB")
  private String KEY2SYMB;  
  
  @Column(name="INSCRIPTION")
  private String INSCRIPTION;  
  
  @Column(name="GD_PER")
  private String GD_PER;  
  
  @Column(name="SYNTH_IND")
  private String SYNTH_IND;  
  
  @Column(name="REPORT_COMM")
  private String REPORT_COMM;  
  
  @Column(name="comment1")
  private String comment1;  
  
  @Column(name="comment2")
  private String comment2;  
  
  @Column(name="comment3")
  private String comment3;  
  
	  /**
 * @return the job_no
 */
public String getJob_no() {
	return job_no;
}


/**
 * @param jobNo the job_no to set
 */
public void setJob_no(String jobNo) {
	job_no = jobNo;
}


/**
 * @return the ctrl_no
 */
public String getCtrl_no() {
	return ctrl_no;
}


/**
 * @param ctrlNo the ctrl_no to set
 */
public void setCtrl_no(String ctrlNo) {
	ctrl_no = ctrlNo;
}


/**
 * @return the dD
 */
public String getDD() {
	return DD;
}


/**
 * @param dD the dD to set
 */
public void setDD(String dD) {
	DD = dD;
}


/**
 * @return the pU_ST
 */
public String getPU_ST() {
	return PU_ST;
}


/**
 * @param pUST the pU_ST to set
 */
public void setPU_ST(String pUST) {
	PU_ST = pUST;
}


/**
 * @return the c_DESC
 */
public String getC_DESC() {
	return C_DESC;
}


/**
 * @param cDESC the c_DESC to set
 */
public void setC_DESC(String cDESC) {
	C_DESC = cDESC;
}


/**
 * @return the lH
 */
public String getLH() {
	return LH;
}


/**
 * @param lH the lH to set
 */
public void setLH(String lH) {
	LH = lH;
}


/**
 * @return the pAINT
 */
public String getPAINT() {
	return PAINT;
}


/**
 * @param pAINT the pAINT to set
 */
public void setPAINT(String pAINT) {
	PAINT = pAINT;
}


/**
 * @return the pAINT_COMM
 */
public String getPAINT_COMM() {
	return PAINT_COMM;
}


/**
 * @param pAINTCOMM the pAINT_COMM to set
 */
public void setPAINT_COMM(String pAINTCOMM) {
	PAINT_COMM = pAINTCOMM;
}


/**
 * @return the kEY2SYMB
 */
public String getKEY2SYMB() {
	return KEY2SYMB;
}


/**
 * @param kEY2SYMB the kEY2SYMB to set
 */
public void setKEY2SYMB(String kEY2SYMB) {
	KEY2SYMB = kEY2SYMB;
}


/**
 * @return the iNSCRIPTION
 */
public String getINSCRIPTION() {
	return INSCRIPTION;
}


/**
 * @param iNSCRIPTION the iNSCRIPTION to set
 */
public void setINSCRIPTION(String iNSCRIPTION) {
	INSCRIPTION = iNSCRIPTION;
}


/**
 * @return the gD_PER
 */
public String getGD_PER() {
	return GD_PER;
}


/**
 * @param gDPER the gD_PER to set
 */
public void setGD_PER(String gDPER) {
	GD_PER = gDPER;
}


/**
 * @return the sYNTH_IND
 */
public String getSYNTH_IND() {
	return SYNTH_IND;
}


/**
 * @param sYNTHIND the sYNTH_IND to set
 */
public void setSYNTH_IND(String sYNTHIND) {
	SYNTH_IND = sYNTHIND;
}


/**
 * @return the rEPORT_COMM
 */
public String getREPORT_COMM() {
	return REPORT_COMM;
}


/**
 * @param rEPORTCOMM the rEPORT_COMM to set
 */
public void setREPORT_COMM(String rEPORTCOMM) {
	REPORT_COMM = rEPORTCOMM;
}


/**
 * @return the serialversionuid
 */
public static long getSerialversionuid() {
	return serialVersionUID;
}


	public StockPRPDO() {
		super();
		this.GRPID = 1L;
	}


	public Long getPKTID() {
		return PKTID;
	}
	  
	  
	public void setPKTID(Long pktid) {
		PKTID = pktid;
	}
	public Long getGRPID() {
		return GRPID;
	}
	public void setGRPID(Long grpid) {
		GRPID = grpid;
	}
	public Double getCTS() {
		return CTS;
	}
	public void setCTS(Double cts) {
		CTS = cts;
	}
	public Double getStatus() {
		return Status;
	}
	public void setStatus(Double status) {
		Status = status;
	}
	public String getSH() {
		return SH;
	}
	public void setSH(String sh) {
		SH = sh;
	}
	public Double getSH_so() {
		return SH_so;
	}
	public void setSH_so(Double sh_so) {
		SH_so = sh_so;
	}
	public String getSZ() {
		return SZ;
	}
	public void setSZ(String sz) {
		SZ = sz;
	}
	public Double getSZ_so() {
		return SZ_so;
	}
	public void setSZ_so(Double sz_so) {
		SZ_so = sz_so;
	}
	public String getPU() {
		return PU;
	}
	public void setPU(String pu) {
		PU = pu;
	}
	public Double getPU_so() {
		return PU_so;
	}
	public void setPU_so(Double pu_so) {
		PU_so = pu_so;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public Double getC_so() {
		return C_so;
	}
	public void setC_so(Double c_so) {
		C_so = c_so;
	}
	public String getCT() {
		return CT;
	}
	public void setCT(String ct) {
		CT = ct;
	}
	public Double getCT_so() {
		return CT_so;
	}
	public void setCT_so(Double ct_so) {
		CT_so = ct_so;
	}
	public String getIG() {
		return IG;
	}
	public void setIG(String ig) {
		IG = ig;
	}
	public Double getIG_so() {
		return IG_so;
	}
	public void setIG_so(Double ig_so) {
		IG_so = ig_so;
	}
	public String getBI() {
		return BI;
	}
	public void setBI(String bi) {
		BI = bi;
	}
	public Double getBI_so() {
		return BI_so;
	}
	public void setBI_so(Double bi_so) {
		BI_so = bi_so;
	}
	public String getTI() {
		return TI;
	}
	public void setTI(String ti) {
		TI = ti;
	}
	public Double getTI_so() {
		return TI_so;
	}
	public void setTI_so(Double ti_so) {
		TI_so = ti_so;
	}
	public String getDP() {
		return DP;
	}
	public void setDP(String dp) {
		DP = dp;
	}
	public Double getDP_so() {
		return DP_so;
	}
	public void setDP_so(Double dp_so) {
		DP_so = dp_so;
	}
	public String getT() {
		return T;
	}
	public void setT(String t) {
		T = t;
	}
	public Double getT_so() {
		return T_so;
	}
	public void setT_so(Double t_so) {
		T_so = t_so;
	}
	public String getPO() {
		return PO;
	}
	public void setPO(String po) {
		PO = po;
	}
	public Double getPO_so() {
		return PO_so;
	}
	public void setPO_so(Double po_so) {
		PO_so = po_so;
	}
	public String getSY() {
		return SY;
	}
	public void setSY(String sy) {
		SY = sy;
	}
	public Double getSY_so() {
		return SY_so;
	}
	public void setSY_so(Double sy_so) {
		SY_so = sy_so;
	}
	public String getFL() {
		return FL;
	}
	public void setFL(String fl) {
		FL = fl;
	}
	public Double getFL_so() {
		return FL_so;
	}
	public void setFL_so(Double fl_so) {
		FL_so = fl_so;
	}
	public String getGD() {
		return GD;
	}
	public void setGD(String gd) {
		GD = gd;
	}
	public Double getGD_so() {
		return GD_so;
	}
	public void setGD_so(Double gd_so) {
		GD_so = gd_so;
	}
	public String getPA() {
		return PA;
	}
	public void setPA(String pa) {
		PA = pa;
	}
	public Double getPA_so() {
		return PA_so;
	}
	public void setPA_so(Double pa_so) {
		PA_so = pa_so;
	}
	public String getAD() {
		return AD;
	}
	public void setAD(String ad) {
		AD = ad;
	}
	public Double getAD_so() {
		return AD_so;
	}
	public void setAD_so(Double ad_so) {
		AD_so = ad_so;
	}
	public String getMD() {
		return MD;
	}
	public void setMD(String md) {
		MD = md;
	}
	public Double getMD_so() {
		return MD_so;
	}
	public void setMD_so(Double md_so) {
		MD_so = md_so;
	}
	public String getXD() {
		return XD;
	}
	public void setXD(String xd) {
		XD = xd;
	}
	public Double getXD_so() {
		return XD_so;
	}
	public void setXD_so(Double xd_so) {
		XD_so = xd_so;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public Double getD_so() {
		return D_so;
	}
	public void setD_so(Double d_so) {
		D_so = d_so;
	}
	public String getCA() {
		return CA;
	}
	public void setCA(String ca) {
		CA = ca;
	}
	public Double getCH_so() {
		return CH_so;
	}
	public void setCH_so(Double ch_so) {
		CH_so = ch_so;
	}
	public Double getCA_so() {
		return CA_so;
	}
	public void setCA_so(Double ca_so) {
		CA_so = ca_so;
	}
	public String getCH() {
		return CH;
	}
	public void setCH(String ch) {
		CH = ch;
	}
	public String getFLS() {
		return FLS;
	}
	public void setFLS(String fls) {
		FLS = fls;
	}
	public Double getFLS_so() {
		return FLS_so;
	}
	public void setFLS_so(Double fls_so) {
		FLS_so = fls_so;
	}
	public String getSD() {
		return SD;
	}
	public void setSD(String sd) {
		SD = sd;
	}
	public Double getSD_so() {
		return SD_so;
	}
	public void setSD_so(Double sd_so) {
		SD_so = sd_so;
	}
	public String getLS() {
		return LS;
	}
	public void setLS(String ls) {
		LS = ls;
	}
	public Double getLS_so() {
		return LS_so;
	}
	public void setLS_so(Double ls_so) {
		LS_so = ls_so;
	}
	public String getTOP() {
		return TOP;
	}
	public void setTOP(String top) {
		TOP = top;
	}
	public Double getTOP_so() {
		return TOP_so;
	}
	public void setTOP_so(Double top_so) {
		TOP_so = top_so;
	}
	public String getCOP() {
		return COP;
	}
	public void setCOP(String cop) {
		COP = cop;
	}
	public Double getCOP_so() {
		return COP_so;
	}
	public void setCOP_so(Double cop_so) {
		COP_so = cop_so;
	}
	public String getPOP() {
		return POP;
	}
	public void setPOP(String pop) {
		POP = pop;
	}
	public Double getPOP_so() {
		return POP_so;
	}
	public void setPOP_so(Double pop_so) {
		POP_so = pop_so;
	}
	public String getL() {
		return L;
	}
	public void setL(String l) {
		L = l;
	}
	public Double getL_so() {
		return L_so;
	}
	public void setL_so(Double l_so) {
		L_so = l_so;
	}
	public String getW() {
		return W;
	}
	public void setW(String w) {
		W = w;
	}
	public Double getW_so() {
		return W_so;
	}
	public void setW_so(Double w_so) {
		W_so = w_so;
	}
	public String getPD() {
		return PD;
	}
	public void setPD(String pd) {
		PD = pd;
	}
	public Double getPD_so() {
		return PD_so;
	}
	public void setPD_so(Double pd_so) {
		PD_so = pd_so;
	}
	public String getCU() {
		return CU;
	}
	public void setCU(String cu) {
		CU = cu;
	}
	public Double getCU_so() {
		return CU_so;
	}
	public void setCU_so(Double cu_so) {
		CU_so = cu_so;
	}
	public String getSL() {
		return SL;
	}
	public void setSL(String sl) {
		SL = sl;
	}
	public Double getSL_so() {
		return SL_so;
	}
	public void setSL_so(Double sl_so) {
		SL_so = sl_so;
	}
	public String getLG() {
		return LG;
	}
	public void setLG(String lg) {
		LG = lg;
	}
	public Double getLG_so() {
		return LG_so;
	}
	public void setLG_so(Double lg_so) {
		LG_so = lg_so;
	}
	public String getGBA() {
		return GBA;
	}
	public void setGBA(String gba) {
		GBA = gba;
	}
	public Double getGBA_so() {
		return GBA_so;
	}
	public void setGBA_so(Double gba_so) {
		GBA_so = gba_so;
	}
	public String getGHA() {
		return GHA;
	}
	public void setGHA(String gha) {
		GHA = gha;
	}
	public Double getGHA_so() {
		return GHA_so;
	}
	public void setGHA_so(Double gha_so) {
		GHA_so = gha_so;
	}
	public String getORG() {
		return ORG;
	}
	public void setORG(String org) {
		ORG = org;
	}
	public Double getORG_so() {
		return ORG_so;
	}
	public void setORG_so(Double org_so) {
		ORG_so = org_so;
	}


	/**
	 * @return the lAB
	 */
	public String getLAB() {
		return LAB;
	}


	/**
	 * @param lAB the lAB to set
	 */
	public void setLAB(String lAB) {
		LAB = lAB;
	}


	/**
	 * @return the lAB_so
	 */
	public Double getLAB_so() {
		return LAB_so;
	}


	/**
	 * @param lABSo the lAB_so to set
	 */
	public void setLAB_so(Double lABSo) {
		LAB_so = lABSo;
	}


	/**
	 * @return the fNC
	 */
	public String getFNC() {
		return FNC;
	}


	/**
	 * @param fNC the fNC to set
	 */
	public void setFNC(String fNC) {
		FNC = fNC;
	}


	/**
	 * @return the fNC_so
	 */
	public Double getFNC_so() {
		return FNC_so;
	}


	/**
	 * @param fNCSo the fNC_so to set
	 */
	public void setFNC_so(Double fNCSo) {
		FNC_so = fNCSo;
	}


	/**
	 * @return the fNCI
	 */
	public String getFNCI() {
		return FNCI;
	}


	/**
	 * @param fNCI the fNCI to set
	 */
	public void setFNCI(String fNCI) {
		FNCI = fNCI;
	}


	/**
	 * @return the fNCI_so
	 */
	public Double getFNCI_so() {
		return FNCI_so;
	}


	/**
	 * @param fNCISo the fNCI_so to set
	 */
	public void setFNCI_so(Double fNCISo) {
		FNCI_so = fNCISo;
	}


	/**
	 * @return the fNCO
	 */
	public String getFNCO() {
		return FNCO;
	}


	/**
	 * @param fNCO the fNCO to set
	 */
	public void setFNCO(String fNCO) {
		FNCO = fNCO;
	}


	/**
	 * @return the fNCO_so
	 */
	public Double getFNCO_so() {
		return FNCO_so;
	}


	/**
	 * @param fNCOSo the fNCO_so to set
	 */
	public void setFNCO_so(Double fNCOSo) {
		FNCO_so = fNCOSo;
	}


	/**
	 * @return the gTN
	 */
	public String getGTN() {
		return GTN;
	}


	/**
	 * @param gTN the gTN to set
	 */
	public void setGTN(String gTN) {
		GTN = gTN;
	}


	/**
	 * @return the gTN_so
	 */
	public Double getGTN_so() {
		return GTN_so;
	}


	/**
	 * @param gTNSo the gTN_so to set
	 */
	public void setGTN_so(Double gTNSo) {
		GTN_so = gTNSo;
	}


	/**
	 * @return the gTH
	 */
	public String getGTH() {
		return GTH;
	}


	/**
	 * @param gTH the gTH to set
	 */
	public void setGTH(String gTH) {
		GTH = gTH;
	}


	/**
	 * @return the gTH_so
	 */
	public Double getGTH_so() {
		return GTH_so;
	}


	/**
	 * @param gTHSo the gTH_so to set
	 */
	public void setGTH_so(Double gTHSo) {
		GTH_so = gTHSo;
	}


	/**
	 * @return the gC
	 */
	public String getGC() {
		return GC;
	}


	/**
	 * @param gC the gC to set
	 */
	public void setGC(String gC) {
		GC = gC;
	}


	/**
	 * @return the gC_so
	 */
	public Double getGC_so() {
		return GC_so;
	}


	/**
	 * @param gCSo the gC_so to set
	 */
	public void setGC_so(Double gCSo) {
		GC_so = gCSo;
	}


	/**
	 * @return the cC
	 */
	public String getCC() {
		return CC;
	}


	/**
	 * @param cC the cC to set
	 */
	public void setCC(String cC) {
		CC = cC;
	}


	/**
	 * @return the cC_so
	 */
	public Double getCC_so() {
		return CC_so;
	}


	/**
	 * @param cCSo the cC_so to set
	 */
	public void setCC_so(Double cCSo) {
		CC_so = cCSo;
	}


	/**
	 * @return the lI
	 */
	public String getLI() {
		return LI;
	}


	/**
	 * @param lI the lI to set
	 */
	public void setLI(String lI) {
		LI = lI;
	}


	/**
	 * @return the lI_so
	 */
	public Double getLI_so() {
		return LI_so;
	}


	/**
	 * @param lISo the lI_so to set
	 */
	public void setLI_so(Double lISo) {
		LI_so = lISo;
	}


	/**
	 * @return the nT
	 */
	public String getNT() {
		return NT;
	}


	/**
	 * @param nT the nT to set
	 */
	public void setNT(String nT) {
		NT = nT;
	}


	/**
	 * @return the nT_so
	 */
	public Double getNT_so() {
		return NT_so;
	}


	/**
	 * @param nT_so the nT_so to set
	 */
	public void setNT_so(Double nT_so) {
		NT_so = nT_so;
	}


	/**
	 * @return the stockMasterDO
	 */
	public StockMasterDO getStockMasterDO() {
		return stockMasterDO;
	}


	/**
	 * @param stockMasterDO the stockMasterDO to set
	 */
	public void setStockMasterDO(StockMasterDO stockMasterDO) {
		this.stockMasterDO = stockMasterDO;
	}


	/**
	 * @return the certLabId
	 */
	public String getCertLabId() {
		return certLabId;
	}


	/**
	 * @param certLabId the certLabId to set
	 */
	public void setCertLabId(String certLabId) {
		this.certLabId = certLabId;
	}


	/**
	 * @return the certLabUrl
	 */
	public String getCertLabUrl() {
		return certLabUrl;
	}


	/**
	 * @param certLabUrl the certLabUrl to set
	 */
	public void setCertLabUrl(String certLabUrl) {
		this.certLabUrl = certLabUrl;
	}


	/**
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}


	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	/**
	 * @return the updateBy
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}


	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}


	/**
	 * @return the shFr
	 */
	public String getShFr() {
		return shFr;
	}


	/**
	 * @param shFr the shFr to set
	 */
	public void setShFr(String shFr) {
		this.shFr = shFr;
	}


	/**
	 * @return the shFr_so
	 */
	public Double getShFr_so() {
		return shFr_so;
	}


	/**
	 * @param shFr_so the shFr_so to set
	 */
	public void setShFr_so(Double shFr_so) {
		this.shFr_so = shFr_so;
	}


	/**
	 * @return the shTo
	 */
	public String getShTo() {
		return shTo;
	}


	/**
	 * @param shTo the shTo to set
	 */
	public void setShTo(String shTo) {
		this.shTo = shTo;
	}


	/**
	 * @return the shTo_so
	 */
	public Double getShTo_so() {
		return shTo_so;
	}


	/**
	 * @param shTo_so the shTo_so to set
	 */
	public void setShTo_so(Double shTo_so) {
		this.shTo_so = shTo_so;
	}


	/**
	 * @return the puFr
	 */
	public String getPuFr() {
		return puFr;
	}


	/**
	 * @param puFr the puFr to set
	 */
	public void setPuFr(String puFr) {
		this.puFr = puFr;
	}


	/**
	 * @return the puFr_so
	 */
	public Double getPuFr_so() {
		return puFr_so;
	}


	/**
	 * @param puFr_so the puFr_so to set
	 */
	public void setPuFr_so(Double puFr_so) {
		this.puFr_so = puFr_so;
	}


	/**
	 * @return the puTo
	 */
	public String getPuTo() {
		return puTo;
	}


	/**
	 * @param puTo the puTo to set
	 */
	public void setPuTo(String puTo) {
		this.puTo = puTo;
	}


	/**
	 * @return the puTo_so
	 */
	public Double getPuTo_so() {
		return puTo_so;
	}


	/**
	 * @param puTo_so the puTo_so to set
	 */
	public void setPuTo_so(Double puTo_so) {
		this.puTo_so = puTo_so;
	}


	/**
	 * @return the cFr
	 */
	public String getcFr() {
		return cFr;
	}


	/**
	 * @param cFr the cFr to set
	 */
	public void setcFr(String cFr) {
		this.cFr = cFr;
	}


	/**
	 * @return the cFr_so
	 */
	public Double getcFr_so() {
		return cFr_so;
	}


	/**
	 * @param cFr_so the cFr_so to set
	 */
	public void setcFr_so(Double cFr_so) {
		this.cFr_so = cFr_so;
	}


	/**
	 * @return the cTo
	 */
	public String getcTo() {
		return cTo;
	}


	/**
	 * @param cTo the cTo to set
	 */
	public void setcTo(String cTo) {
		this.cTo = cTo;
	}


	/**
	 * @return the cTo_so
	 */
	public Double getcTo_so() {
		return cTo_so;
	}


	/**
	 * @param cTo_so the cTo_so to set
	 */
	public void setcTo_so(Double cTo_so) {
		this.cTo_so = cTo_so;
	}


	/**
	 * @return the aVGCTS
	 */
	public Double getAVGCTS() {
		return AVGCTS;
	}


	/**
	 * @param aVGCTS the aVGCTS to set
	 */
	public void setAVGCTS(Double aVGCTS) {
		AVGCTS = aVGCTS;
	}


	/**
	 * @return the sIEVE
	 */
	public String getSIEVE() {
		return SIEVE;
	}


	/**
	 * @param sIEVE the sIEVE to set
	 */
	public void setSIEVE(String sIEVE) {
		SIEVE = sIEVE;
	}


	/**
	 * @return the sIEVE_so
	 */
	public Double getSIEVE_so() {
		return SIEVE_so;
	}


	/**
	 * @param sIEVE_so the sIEVE_so to set
	 */
	public void setSIEVE_so(Double sIEVE_so) {
		SIEVE_so = sIEVE_so;
	}


	/**
	 * @return the pPC
	 */
	public Double getPPC() {
		return PPC;
	}


	/**
	 * @param pPC the pPC to set
	 */
	public void setPPC(Double pPC) {
		PPC = pPC;
	}


	/**
	 * @return the pTYP
	 */
	public String getPTYP() {
		return PTYP;
	}


	/**
	 * @param pTYP the pTYP to set
	 */
	public void setPTYP(String pTYP) {
		PTYP = pTYP;
	}


	/**
	 * @return the pTYP_so
	 */
	public Double getPTYP_so() {
		return PTYP_so;
	}


	/**
	 * @param pTYP_so the pTYP_so to set
	 */
	public void setPTYP_so(Double pTYP_so) {
		PTYP_so = pTYP_so;
	}


	/**
	 * @return the rateLab
	 */
	public Double getRateLab() {
		return rateLab;
	}


	/**
	 * @param rateLab the rateLab to set
	 */
	public void setRateLab(Double rateLab) {
		this.rateLab = rateLab;
	}


	/**
	 * @return the rapLab
	 */
	public Double getRapLab() {
		return rapLab;
	}


	/**
	 * @param rapLab the rapLab to set
	 */
	public void setRapLab(Double rapLab) {
		this.rapLab = rapLab;
	}


	/**
	 * @return the rapPriceLab
	 */
	public Double getRapPriceLab() {
		return rapPriceLab;
	}


	/**
	 * @param rapPriceLab the rapPriceLab to set
	 */
	public void setRapPriceLab(Double rapPriceLab) {
		this.rapPriceLab = rapPriceLab;
	}


	/**
	 * @return the oldLab
	 */
	public String getOldLab() {
		return oldLab;
	}


	/**
	 * @param oldLab the oldLab to set
	 */
	public void setOldLab(String oldLab) {
		this.oldLab = oldLab;
	}


	/**
	 * @return the sieveFr
	 */
	public String getSieveFr() {
		return sieveFr;
	}


	/**
	 * @param sieveFr the sieveFr to set
	 */
	public void setSieveFr(String sieveFr) {
		this.sieveFr = sieveFr;
	}


	/**
	 * @return the sieveTo
	 */
	public String getSieveTo() {
		return sieveTo;
	}


	/**
	 * @param sieveTo the sieveTo to set
	 */
	public void setSieveTo(String sieveTo) {
		this.sieveTo = sieveTo;
	}


	/**
	 * @return the sieveFr_so
	 */
	public String getSieveFr_so() {
		return sieveFr_so;
	}


	/**
	 * @param sieveFr_so the sieveFr_so to set
	 */
	public void setSieveFr_so(String sieveFr_so) {
		this.sieveFr_so = sieveFr_so;
	}


	/**
	 * @return the sieveTo_so
	 */
	public String getSieveTo_so() {
		return sieveTo_so;
	}


	/**
	 * @param sieveTo_so the sieveTo_so to set
	 */
	public void setSieveTo_so(String sieveTo_so) {
		this.sieveTo_so = sieveTo_so;
	}


	/**
	 * @return the comment2
	 */
	public String getComment2() {
		return comment2;
	}


	/**
	 * @param comment2 the comment2 to set
	 */
	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}


	
}

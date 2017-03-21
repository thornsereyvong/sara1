package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tblsbquotedetails")
public class QuoteDetails implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QuoteDetailsID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer quoteDetailsId;
	
	@Column(name="SalID")
	private String saleId;
	
	@Column(name="LineNo")
	private Integer lineNo;
	
	@Column(name="ItemID")
	private String itemId;
	
	@Column(name="UomID")
	private String uomId;
	
	@Column(name="LocationID")
	private String locationId;
	
	@Column(name="SalQty")
	private Double saleQuantity;
	
	@Column(name="UnitPrice")
	private Double unitPrice;
	
	@Column(name="TotalAmt")
	private Double totalAmt;
	
	//@Column(name="COGSUnnit")
	@Transient
	private Double COGSUnit;
	
	//@Column(name="COGSAC1'")
	@Transient
	private Double COGSAC1;
	
	//@Column(name="COGSAC2")
	@Transient
	private Double COGSAC2;
	
	//@Column(name="COGSAC3")
	@Transient
	private Double COGSAC3;
	
	//@Column(name="COGSAC4")
	@Transient
	private Double COGSAC4;
	
	//@Column(name="COGSAC5")
	@Transient
	private Double COGSAC5;
	
	//@Column(name="COGSTotal")
	@Transient
	private Double COGSTotal;
	
	@Column(name="PostStatus")
	private String postStatus;
	
	//@Column(name="JID")
	@Transient
	private Integer jId;
	
	@Column(name="NetTotalAmt")
	private Double netTotalAmt;
	
	@Column(name="DisDol")
	private Double disDol;
	
	@Column(name="DisPer")
	private Double disPer;
	
	@Column(name = "STaxDol")
	private Double STaxDol;
	
	@Column(name="STaxPer")
	private Double STaxPer;
	
	@Column(name="VTaxDol")
	private Double VTaxDol;
	
	@Column(name="VTaxPer")
	private Double VTaxPer;
	
	@Column(name="ItemStatus")
	private String itemStatus;
	
	@Column(name="ReportPrice")
	private Double reportPrice;
	
	//@Column(name="AccID")
	@Transient
	private Integer accId;
	
	//@Column(name="Des")
	@Transient
	private String des;
	
	@Column(name="Factor")
	private Double factor;
	
	//@Column(name="ClientID")
	@Transient
	private String clientId;
	
	//@Column(name="RKSPrice")
	@Transient
	private Double RKSPrice;
	
	@Column(name="IsVariable")
	private Short isVariable;
	
	@Column(name="ClassID")
	private String classId;
	
	@Column(name="DisInv")
	private Double disInv;
	
	//@Column(name="SchPayPeriodID")
	@Transient
	private String schPayPeriodID;
	
	//@Column(name="SchTermStart")
	@Transient
	private String schTermStart;
	
	//@Column(name="SchTermEnd")
	@Transient
	private String schTermEnd;
	
	//@Column(name="SchProgramTypeID")
	@Transient
	private String schProgramTypeID;
	
	//@Column(name="SchSchoolGradeID")
	@Transient
	private String schSchoolGradeId;
	
	//@Column(name="SchClsGradeID")
	@Transient
	private String schClsGradeID;
	
	//@Column(name="SchSessionID")
	@Transient
	private String schSessionId;
	
	//@Column(name="SchStatus")
	@Transient
	private String schStatus;
	
	//@Column(name="SchSD")
	@Transient
	private Date schSD;
	
	//@Column(name="SchTD")
	@Transient
	private Date schTD;
	
	//@Column(name="SchSA")
	@Transient
	private Date schSA;
	
	//@Column(name="SchTA")
	@Transient
	private Date schTA;
	
	@Transient
	private MeDataSource dataSource;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUomId() {
		return uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Double getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Double saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getCOGSUnit() {
		return COGSUnit;
	}

	public void setCOGSUnit(Double cOGSUnit) {
		COGSUnit = cOGSUnit;
	}

	public Double getCOGSAC1() {
		return COGSAC1;
	}

	public void setCOGSAC1(Double cOGSAC1) {
		COGSAC1 = cOGSAC1;
	}

	public Double getCOGSAC2() {
		return COGSAC2;
	}

	public void setCOGSAC2(Double cOGSAC2) {
		COGSAC2 = cOGSAC2;
	}

	public Double getCOGSAC3() {
		return COGSAC3;
	}

	public void setCOGSAC3(Double cOGSAC3) {
		COGSAC3 = cOGSAC3;
	}

	public Double getCOGSAC4() {
		return COGSAC4;
	}

	public void setCOGSAC4(Double cOGSAC4) {
		COGSAC4 = cOGSAC4;
	}

	public Double getCOGSAC5() {
		return COGSAC5;
	}

	public void setCOGSAC5(Double cOGSAC5) {
		COGSAC5 = cOGSAC5;
	}

	public Double getCOGSTotal() {
		return COGSTotal;
	}

	public void setCOGSTotal(Double cOGSTotal) {
		COGSTotal = cOGSTotal;
	}

	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public Integer getjId() {
		return jId;
	}

	public void setjId(Integer jId) {
		this.jId = jId;
	}

	public Double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(Double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public Double getDisDol() {
		return disDol;
	}

	public void setDisDol(Double disDol) {
		this.disDol = disDol;
	}

	public Double getDisPer() {
		return disPer;
	}

	public void setDisPer(Double disPer) {
		this.disPer = disPer;
	}

	public Double getSTaxDol() {
		return STaxDol;
	}

	public void setSTaxDol(Double sTaxDol) {
		STaxDol = sTaxDol;
	}

	public Double getSTaxPer() {
		return STaxPer;
	}

	public void setSTaxPer(Double sTaxPer) {
		STaxPer = sTaxPer;
	}

	public Double getVTaxDol() {
		return VTaxDol;
	}

	public void setVTaxDol(Double vTaxDol) {
		VTaxDol = vTaxDol;
	}

	public Double getVTaxPer() {
		return VTaxPer;
	}

	public void setVTaxPer(Double vTaxPer) {
		VTaxPer = vTaxPer;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Double getReportPrice() {
		return reportPrice;
	}

	public void setReportPrice(Double reportPrice) {
		this.reportPrice = reportPrice;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Double getRKSPrice() {
		return RKSPrice;
	}

	public void setRKSPrice(Double rKSPrice) {
		RKSPrice = rKSPrice;
	}

	public Short getIsVariable() {
		return isVariable;
	}

	public void setIsVariable(Short isVariable) {
		this.isVariable = isVariable;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Double getDisInv() {
		return disInv;
	}

	public void setDisInv(Double disInv) {
		this.disInv = disInv;
	}

	public String getSchPayPeriodID() {
		return schPayPeriodID;
	}

	public void setSchPayPeriodID(String schPayPeriodID) {
		this.schPayPeriodID = schPayPeriodID;
	}

	public String getSchTermStart() {
		return schTermStart;
	}

	public void setSchTermStart(String schTermStart) {
		this.schTermStart = schTermStart;
	}

	public String getSchTermEnd() {
		return schTermEnd;
	}

	public void setSchTermEnd(String schTermEnd) {
		this.schTermEnd = schTermEnd;
	}

	public String getSchProgramTypeID() {
		return schProgramTypeID;
	}

	public void setSchProgramTypeID(String schProgramTypeID) {
		this.schProgramTypeID = schProgramTypeID;
	}

	public String getSchSchoolGradeId() {
		return schSchoolGradeId;
	}

	public void setSchSchoolGradeId(String schSchoolGradeId) {
		this.schSchoolGradeId = schSchoolGradeId;
	}

	public String getSchClsGradeID() {
		return schClsGradeID;
	}

	public void setSchClsGradeID(String schClsGradeID) {
		this.schClsGradeID = schClsGradeID;
	}

	public String getSchSessionId() {
		return schSessionId;
	}

	public void setSchSessionId(String schSessionId) {
		this.schSessionId = schSessionId;
	}

	public String getSchStatus() {
		return schStatus;
	}

	public void setSchStatus(String schStatus) {
		this.schStatus = schStatus;
	}

	public Date getSchSD() {
		return schSD;
	}

	public void setSchSD(Date schSD) {
		this.schSD = schSD;
	}

	public Date getSchTD() {
		return schTD;
	}

	public void setSchTD(Date schTD) {
		this.schTD = schTD;
	}

	public Date getSchSA() {
		return schSA;
	}

	public void setSchSA(Date schSA) {
		this.schSA = schSA;
	}

	public Date getSchTA() {
		return schTA;
	}

	public void setSchTA(Date schTA) {
		this.schTA = schTA;
	}

	public final Integer getQuoteDetailsId() {
		return quoteDetailsId;
	}

	public final void setQuoteDetailsId(Integer quoteDetailsId) {
		this.quoteDetailsId = quoteDetailsId;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}

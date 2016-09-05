package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tblitem")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="ItemID", nullable = false)
	private String itemId;
	
	@Column(name="ItemName")
	private String itemName;
	
	@Column(name="VAT")
	private double VAT;
	
	@Column(name="STax")
	private double STax;
	
	@Column(name="ReOrderPoint")
	private double reorderPoint;
	
	@Column(name="ItemGroupID")
	private String itemGroupId;
	
	@Column(name="UOMID")
	private String uomId;
	
	@Column(name="Remark")
	private String Remark;
	
	@Column(name="ImagePath")
	private String imagePath;
	
	@Column(name="Status")
	private short status;
	
	@Column(name="Inactive")
	private short inactive;
	
	@Column(name="IsAddon")
	private short isAddon;
	
	@Column(name="ShowOnPos")
	private short showOnPos;
	
	@Column(name="AF1")
	private String AF1;
	
	@Column(name="AF2")
	private String AF2;
	
	@Column(name="AF3")
	private String AF3;
	
	@Column(name="AF4")
	private String AF4;
	
	@Column(name="AF5")
	private String AF5;
	
	@Column(name="AF6")
	private String AF6;
	
	@Column(name="AF7")
	private String AF7;
	
	@Column(name="AF8")
	private String AF8;
	
	@Column(name="AF9")
	private String AF9;
	
	@Column(name="AF10")
	private String AF10;
	
	@Column(name="ConInfo")
	private String conInfo;
	
	@Column(name="OwnerName")
	private String OwnerName;
	
	@Column(name="ProName")
	private String ProName;
	
	@Column(name="SignName")
	private String signName;
	
	@Column(name="No")
	private String no;
	
	@Column(name="Street")
	private String street;
	
	@Column(name="Village")
	private String village;
	
	@Column(name="SangKat")
	private String commune;
	
	@Column(name="Khan")
	private String district;
	
	@Column(name="BusSector")
	private String busSector;
	
	@Column(name="SignType")
	private String signType;
	
	@Column(name="LanUsed")
	private String lanUsed;
	
	@Column(name="Width")
	private double width;
	
	@Column(name="Heigth")
	private double heigth;
	
	@Column(name="NoSide")
	private double noSide;
	
	@Column(name="Price")
	private double price;
	
	@Column(name="Sponsor")
	private String sponsor;
	
	@Column(name="CreateDate", updatable = false, columnDefinition = "DATETIME")
	private Date createDate;
	
	@Column(name="PriceCode")
	private String priceCode;
	
	@Column(name="StartDate", columnDefinition = "DATETIME")
	private Date startDate;
	
	@Column(name="EndDate")
	private Date endDate;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="ManufacturerID")
	private String manufacturerId;
	
	@Column(name="ModelID")
	private String modelId;
	
	@Column(name="IColorID")
	private String iColorId;
	
	@Column(name="EColorID")
	private String eColorId;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="StatusID")
	private short statusId;
	
	@Column(name="VinNo")
	private String vinNo;
	
	@Column(name="PlateNo")
	private String plateNo;
	
	@Column(name="VignetteNo")
	private String vignetteNo;
	
	@Column(name="TransmissionID")
	private String transmissionId;

	@Column(name="FuleTypeID")
	private String fuleTypeId;
	
	
	@Column(name="StyleTypeID")
	private String styleTypeId;
	
	@Column(name="EngineCylinderID")
	private String engineCylinderId;
	
	@Column(name="engineName")
	private String engineCC;
	
	@Column(name="AutoLocationID")
	private String autoLocationId;
	
	@Column(name="CusId")
	private String CusId;
	
	@Column(name="CusPrice")
	private String CusPrice;
	
	@Column(name="VendorID")
	private String vendorId;
	
	@Column(name="VendorPrice")
	private String vendorPrice;
	
	@Column(name="Mileage")
	private String mileage;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="StockID")
	private String stockId;
	
	@Column(name="Approved")
	private short approved;
	
	@Column(name="hit")
	private int hit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CDate", columnDefinition = "TIMESTAMP", updatable = false)
	private Date creatDate;
	
}

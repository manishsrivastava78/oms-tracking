package com.tcs.eas.rest.apis.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author 44745
 *
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691676148924563243L;

	private int productid;

	private String productname;

	private String manufacturer;

	private Date manufactureddate;

	private String batchnumber;

	private String productdesc;

	private String category;

	private Double price;

	private String currency;

	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}

	/**
	 * @param productid the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the batchnumber
	 */
	public String getBatchnumber() {
		return batchnumber;
	}

	/**
	 * @param batchnumber the batchnumber to set
	 */
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	/**
	 * @return the manufactureddate
	 */
	public Date getManufactureddate() {
		return manufactureddate;
	}

	/**
	 * @param manufactureddate the manufactureddate to set
	 */
	public void setManufactureddate(Date manufactureddate) {
		this.manufactureddate = manufactureddate;
	}

	public Product() {

	}

	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}

	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the productdesc
	 */
	public String getProductdesc() {
		return productdesc;
	}

	/**
	 * @param productdesc the productdesc to set
	 */
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}

}

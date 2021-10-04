package com.tcs.eas.rest.apis.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author 44745
 *
 */
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3882619201810226319L;
	private int orderid;
	private int customerid;
	private int productid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dop ;

	/**
	 * @return the orderid
	 */
	public int getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

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
	 * @return the dop
	 */
	public Date getDop() {
		return dop;
	}

	/**
	 * @param dop the dop to set
	 */
	public void setDop(Date dop) {
		this.dop = dop;
	}
}

package com.tcs.eas.rest.apis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author 44745
 *
 */
@ApiModel
@Entity(name = "Tracking")
public class Tracking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8678458829753517756L;
	

	@Id
	@GenericGenerator(name = "tracking_id", strategy = "com.tcs.eas.rest.apis.utility.KeyGenerator")
	@GeneratedValue(generator = "tracking_id") 
	private int trackingid;
	
	@NotNull(message="trackingnumber field is missing")
	@Column(unique=true)
	private String trackingnumber;
	
	@NotNull(message="status field is missing")
	private int status;
	
	@NotNull(message="orderid field is missing")
	private int orderid;

	@NotNull(message="customerid field is missing")
	private int customerid;
	
	@NotNull(message="productid field is missing")
	private int productid;
	
	private String remarks;
	
	

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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return the trackingid
	 */
	public int getTrackingid() {
		return trackingid;
	}

	/**
	 * @param trackingid the trackingid to set
	 */
	public void setTrackingid(int trackingid) {
		this.trackingid = trackingid;
	}

	/**
	 * @return the trackingnumber
	 */
	public String getTrackingnumber() {
		return trackingnumber;
	}

	/**
	 * @param trackingnumber the trackingnumber to set
	 */
	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

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
	 * 
	 * @param trackingid
	 * @param trackingnumber
	 * @param status
	 * @param orderid
	 */
	public Tracking(int trackingid, @NotNull(message = "trackingnumber field is missing") String trackingnumber,
			@NotNull(message = "status field is missing") int status,
			@NotNull(message = "orderid field is missing") int orderid,@NotNull(message="customerid field is missing")
	 int customerid,@NotNull(message="productid field is missing")
	 int productid) {
		super();
		this.trackingid = trackingid;
		this.trackingnumber = trackingnumber;
		this.status = status;
		this.orderid = orderid;
		this.customerid = customerid;
		this.productid = productid;
	}
	
	/**
	 * 
	 */
	public Tracking() {
		
	}
		
}

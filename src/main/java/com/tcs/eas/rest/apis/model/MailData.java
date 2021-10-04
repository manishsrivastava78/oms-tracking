package com.tcs.eas.rest.apis.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author 44745
 *
 */
public class MailData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6331422268120956809L;

	/**
	 * Order id of the purchase
	 */
	private int orderId;
	
	/**
	 * Date of purchase
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dop;

	/**
	 * Customer's details
	 */
	private Customer customer;
	
	/**
	 *Product's details 
	 */
	private Product product;
	
	/**
	 * Unique template id to identify the mail template
	 */
	private int mailTemplate;

	/**
	 * Unique tracking number to track the order status
	 */
	private String trackingNumber;
	
	/**
	 * Delivery date of the order
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dod;
	
	/**
	 * status of the order
	 * like shipped, delivered 
	 */
	private String status;
	
	/**
	 * remarks from the agent
	 */
	private String remarks;
	
	

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the dod
	 */
	public Date getDod() {
		return dod;
	}

	/**
	 * @param dod the dod to set
	 */
	public void setDod(Date dod) {
		this.dod = dod;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the mailTemplate
	 */
	public int getMailTemplate() {
		return mailTemplate;
	}

	/**
	 * @param mailTemplate the mailTemplate to set
	 */
	public void setMailTemplate(int mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
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

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 
	 */
	public MailData() {

	}
	
}
	
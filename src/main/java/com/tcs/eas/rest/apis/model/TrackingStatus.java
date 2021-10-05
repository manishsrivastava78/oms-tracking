package com.tcs.eas.rest.apis.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author 44745
 *
 */
@ApiModel
public class TrackingStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8462588299028934095L;
	
	private String trackingnumber;
	
	private int status;
	
	private String remarks;

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

	public TrackingStatus() {
		super();
	}
	
	
}

package com.tcs.eas.rest.apis.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.eas.rest.apis.model.Tracking;


/**
 * 
 * @author 44745
 *
 */
public interface TrackingRepository extends JpaRepository<Tracking,Integer>{
	/**
	 * 
	 * @param trackingNumber
	 * @return
	 */
	public Tracking getTrackingByTrackingnumber(String trackingNumber);
	
	/**
	 * 
	 * @return
	 */
	public List<Tracking> findAllByCustomerid(int customerId);
	
}

package com.tcs.eas.rest.apis.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.model.Tracking;

/**
 * 
 * @author 44745
 *
 */
@Component
public class TrackingDaoService {

	@Autowired
	TrackingRepository trackingRepository;

	@Autowired
	LoggingService loggingService;

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Tracking save(Tracking tracking) {
		trackingRepository.save(tracking);
		return tracking;
	}

	

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tracking getTrackingByTrackingnumber(String trackingNumber) {
		return trackingRepository.getTrackingByTrackingnumber(trackingNumber);
	}
}

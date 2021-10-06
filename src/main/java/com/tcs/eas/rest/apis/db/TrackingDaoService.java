package com.tcs.eas.rest.apis.db;

import java.util.List;

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
	 * @return
	 */
	public List<Tracking> findAll(){
		return trackingRepository.findAll();
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

package com.tcs.eas.rest.apis.controller;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.rest.apis.db.TrackingDaoService;
import com.tcs.eas.rest.apis.event.producer.Producer;
import com.tcs.eas.rest.apis.exception.TrackingNotFound;
import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.model.Customer;
import com.tcs.eas.rest.apis.model.MailData;
import com.tcs.eas.rest.apis.model.Order;
import com.tcs.eas.rest.apis.model.Product;
import com.tcs.eas.rest.apis.model.Tracking;
import com.tcs.eas.rest.apis.model.TrackingStatus;
import com.tcs.eas.rest.apis.utility.Utility;

/**
 * 
 * @author 44745
 *
 */
@RestController
@RequestMapping("/apis/v1")
public class TrackingController {

	@Autowired
	LoggingService loggingService;

	@Autowired
	private Producer producer;

	/**
	 * 
	 */
	@Autowired
	TrackingDaoService trackingDaoService;

	@Value(value = "${CUSTOMER_SERVICE_HOST}")
	private String customerServiceHost;

	@Value(value = "${CUSTOMER_SERVICE_PORT}")
	private int customerServicePort;

	@Value(value = "${PRODUCT_SERVICE_HOST}")
	private String productServiceHost;

	@Value(value = "${PRODUCT_SERVICE_PORT}")
	private int productServicePort;

	@Value(value = "${ORDER_SERVICE_HOST}")
	private String orderServiceHost;

	@Value(value = "${ORDER_SERVICE_PORT}")
	private int orderServicePort;

	@Value(value = "${KAFKA_MAIL_TOPIC}")
	private String mailTopic;

	/**
	 * 
	 * @param product
	 * @param headers
	 * @return
	 */
	@PostMapping("/trackings")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Tracking tracking,
			@RequestHeader Map<String, String> headers) {
		Tracking savedTracking = trackingDaoService.save(tracking);
		loggingService.writeProcessLog("POST", "trackings", "createTracking", tracking);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{trackingid}")
				.buildAndExpand(savedTracking.getTrackingid()).toUri();
		//sendMailMessage(savedTracking);
		return ResponseEntity.created(location).headers(Utility.getCustomResponseHeaders(headers)).build();
	}

	@GetMapping("/trackings/{trackingnumber}")
	public ResponseEntity<Tracking> getProduct(@PathVariable String trackingnumber,
			@RequestHeader Map<String, String> headers) {

		Tracking tracking = trackingDaoService.getTrackingByTrackingnumber(trackingnumber);
		if (tracking == null) {
			throw new TrackingNotFound("tracking number " + trackingnumber + " does not exist");
		}
		loggingService.writeProcessLog("GET", "trackings", "getTracking by tracking numver", tracking);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(tracking);
	}
	
	@GetMapping("/trackings")
	public ResponseEntity<List<Tracking>> getTrackings(@RequestHeader Map<String, String> headers) {
		List<Tracking> tracking = trackingDaoService.findAll();
		loggingService.writeProcessLog("GET", "trackings", "getTrackings", tracking);
		return  ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(tracking);
	}

	@PutMapping("/trackings")
	public ResponseEntity<Tracking> updateTracking(@Valid @RequestBody TrackingStatus tracking,
			@RequestHeader Map<String, String> headers) {
		Tracking searchedTracking = trackingDaoService.getTrackingByTrackingnumber(tracking.getTrackingnumber());
		searchedTracking.setStatus(tracking.getStatus());
		if (tracking.getRemarks() != null && !tracking.getRemarks().isEmpty()) {
			searchedTracking.setRemarks(tracking.getRemarks());
		}
		trackingDaoService.save(searchedTracking);
		loggingService.writeProcessLog("PUT", "trackings", "updateTracking", searchedTracking);
		// URI location =
		// ServletUriComponentsBuilder.fromCurrentRequest().path("/{trackingid}")
		// .buildAndExpand(tracking.getTrackingid()).toUri();
		sendMailMessage(searchedTracking);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).build();
	}

	/**
	 * 
	 * @param tracking
	 */
	private void sendMailMessage(Tracking tracking) {
		MailData data = new MailData();

		Customer customer = getCustomer(tracking.getCustomerid());
		data.setCustomer(customer);

		Product product = getProduct(tracking.getProductid());
		data.setCustomer(customer);

		Order order = getOrder(tracking.getOrderid());

		data.setDop(order.getDop());

		data.setOrderId(order.getOrderid());

		data.setProduct(product);

		data.setTrackingNumber(tracking.getTrackingnumber());

		if (tracking.getStatus() == 3) {
			data.setMailTemplate(3);
			data.setDod(new Date(System.currentTimeMillis()));
			data.setStatus("Delivered");
			data.setRemarks(tracking.getRemarks());
		} else {
			data.setMailTemplate(2);
			data.setStatus("Shipped");
			data.setRemarks(tracking.getRemarks());
		}
		producer.sendMessageToMailTopic(getMailDataInJson(data));
	}

	/**
	 * 
	 * @return
	 */
	private Customer getCustomer(int customerId) {
		Customer customer = null;
		RestTemplate restTemplate = new RestTemplate();
		String customerUrl = "http://" + customerServiceHost + ":" + customerServicePort + "/apis/v1/customers/"
				+ customerId;
		try {
			ResponseEntity<Customer> response = restTemplate.getForEntity(customerUrl, Customer.class);
			customer = response.getBody();
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
		return customer;

	}

	/**
	 * 
	 * @return
	 */
	private Product getProduct(int productId) {
		Product product = null;
		RestTemplate restTemplate = new RestTemplate();
		String productUrl = "http://" + productServiceHost + ":" + productServicePort + "/apis/v1/products/"
				+ productId;
		try {
			ResponseEntity<Product> response = restTemplate.getForEntity(productUrl, Product.class);
			product = response.getBody();
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
		return product;
	}

	/**
	 * 
	 * @return
	 */
	private Order getOrder(int orderId) {
		Order order = null;
		RestTemplate restTemplate = new RestTemplate();
		String orderUrl = "http://" + orderServiceHost + ":" + orderServicePort + "/apis/v1/orders/" + orderId;
		try {
			ResponseEntity<Order> response = restTemplate.getForEntity(orderUrl, Order.class);
			order = response.getBody();
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
		
		/**
		 * dummy order
		 */
		if(order == null) {
			order = new Order();
			order.setOrderid(orderId);
			order.setDop(new Date(System.currentTimeMillis()));
			
		}
		
		return order;
	}

	/**
	 * 
	 * @param mailData
	 * @return
	 */
	private String getMailDataInJson(MailData mailData) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(mailData);
			System.out.println("ResultingJSONstring = " + json);
			// System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
}

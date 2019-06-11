package com.webservice.restclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.webservice.restclient.pojo.Result;
import com.webservice.restclient.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientDemoApplicationTests {
	/*
	 * Get method with no request data with string response
	 */
	// @Test
	public void requestGreet1() {

		String endPointUrl = "http://localhost:8081/restapi/greet1";

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.getForEntity(endPointUrl, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		showResponse(response);
	}

	/*
	 * Get method with one request parameter name with string response
	 */
	// @Test
	public void requestGreet2() {

		String endPointUrl = "http://localhost:8081/restapi/greet2";
		String name = "Vishal";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endPointUrl).queryParam("name", name);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		showResponse(response);
	}

	/*
	 * Post method with one request parameter name and with string response
	 */
	// @Test
	public void requestGreet3() {

		String endPointUrl = "http://localhost:8081/restapi/greet3";
		String name = "Vishal";

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("name", name);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				null);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity(endPointUrl, requestEntity, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		showResponse(response);
	}

	/*
	 * Get method with one path parameter name and with string response
	 */
	// @Test
	public void requestGreet4() {

		String endPointUrl = "http://localhost:8081/restapi/greet4/wish/{name}";
		String name = "Vishal";

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.exchange(endPointUrl, HttpMethod.GET, null, String.class, name);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		showResponse(response);
	}

	/*
	 * Get method with no parameter Request accept type JSON Response in JSON
	 * Converting in User object
	 */
	// @Test
	public void requestGreetJSON5() {

		String endPointUrl = "http://localhost:8081/restapi/greet5";

		HttpHeaders headers = new HttpHeaders();
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_JSON);
		headers.setAccept(list);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		RestTemplate template = new RestTemplate();
		// Getting Response in String
		// ResponseEntity<String> response = template.exchange(endPointUrl,
		// HttpMethod.GET, requestEntity, String.class);
		// assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		// System.out.println(response.getBody());

		// Getting Response in User Object
		ResponseEntity<User> response = template.exchange(endPointUrl, HttpMethod.GET, requestEntity, User.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		System.out.println(response.getBody());
	}

	/*
	 * Get method with no parameter Request accept type JSON Response in XML
	 * Converting in User object
	 */
	// @Test
	public void requestGreetXML5() {

		String endPointUrl = "http://localhost:8081/restapi/greet5";

		HttpHeaders headers = new HttpHeaders();
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_XML);
		headers.setAccept(list);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		RestTemplate template = new RestTemplate();
		// Getting Response in String
		// ResponseEntity<String> response = template.exchange(endPointUrl,
		// HttpMethod.GET, requestEntity, String.class);
		// assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		// System.out.println(response.getBody());

		// Getting Response in User Object
		ResponseEntity<User> response = template.exchange(endPointUrl, HttpMethod.GET, requestEntity, User.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		System.out.println(response.getBody());
	}

	/*
	 * Post method with user object as request body Content-type JSON Response in
	 * String
	 */
	//@Test
	public void requestGreetJSON6() {

		String endPointUrl = "http://localhost:8081/restapi/greet6";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		User user = new User();
		user.setUserId(101);
		user.setUserName("user1");
		user.setEmail("user1@gmail.com");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		System.out.println("Request : " + requestEntity);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity(endPointUrl, requestEntity, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		System.out.println("Response : " + response.getBody());
	}

	/*
	 * Post method with user object as request body Content-type XML Response in
	 * String
	 */
	//@Test
	public void requestGreetXML6() {

		String endPointUrl = "http://localhost:8081/restapi/greet6";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		User user = new User();
		user.setUserId(101);
		user.setUserName("user1");
		user.setEmail("user1@gmail.com");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		System.out.println("Request : " + requestEntity);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity(endPointUrl, requestEntity, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		System.out.println("Response : " + response.getBody());
	}
	
	/*
	 * Post method with user object JSON as request body Content-type XML Response in
	 * XML Object
	 */
	@Test
	public void requestGreetJSON_XML7() {

		String endPointUrl = "http://localhost:8081/restapi/greet7";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		User user = new User();
		user.setUserId(101);
		user.setUserName("user1");
		user.setEmail("user1@gmail.com");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		System.out.println("Request : " + requestEntity);

		RestTemplate template = new RestTemplate();
		//Response in XML
		//ResponseEntity<String> response = template.postForEntity(endPointUrl, requestEntity, String.class);

		//assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		//System.out.println("Response : " + response.getBody());
		
		//Response in XML to Object
		ResponseEntity<Result> response = template.postForEntity(endPointUrl, requestEntity, Result.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		System.out.println("Response : " + response.getBody());
	}

	public void showResponse(ResponseEntity<String> response) {
		System.out.println("Status code : " + response.getStatusCode());
		System.out.println("Status Code Value : " + response.getStatusCodeValue());
		HttpHeaders headers = response.getHeaders();
		for (Entry<String, List<String>> x : headers.entrySet()) {
			String headerKey = x.getKey();
			List<String> headerValue = x.getValue();
			System.out.println("--------------------------------");
			System.out.println("Header Key :" + headerKey);
			System.out.println("--------------------------------");
			System.out.print("Header value/values :");
			for (String value : headerValue) {
				System.out.print(value + "\t");
			}
			System.out.println();

		}
		System.out.println("Response Body : " + response.getBody());
	}
}

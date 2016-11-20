package step_definitions;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import util.LoadProperties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RESTAPISteps {

	static RestTemplate restTemplate;
	String responseMsg;
	HttpClient httpClient;
	static Properties prop;
	Quote q;

	public RESTAPISteps() {
		// load properties file
		prop = LoadProperties.getInstance().loadProperties();
		
		Quote q2= new Quote();
		Human venu = new Human();
		String apple="a";
		venu.eat(apple);
	}
	

	@Given("^I Configure REST API$")
	public void configureSpringRestTemplate() throws IOException {
		restTemplate = createRestTemplate();
	}

	@When("^I send the GET API Request$")
	public void sendXMLMessage() throws IOException {
		q = restTemplate.getForObject(
				"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	}

	@Then("^I Should receive the Response$")
	public void receiveAndAssertJsonResponse() throws Exception {
		System.out.println("The type we got here is...." + q.getType());
		Assert.assertTrue(q.getType().equalsIgnoreCase("success"));
	}
	@Then("^I should recieve the response with success$")
	public void receiveRestAPIResponse() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(responseMsg);

		String responseStatus = actualObj.get("type").asText();
		Assert.assertTrue(responseStatus.equalsIgnoreCase("success"));
		System.out.println("The success message is..." + responseStatus);
	}

	public RestTemplate createRestTemplate() {
		HttpComponentsClientHttpRequestFactory factory = setProxyFactory();
		restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	@Given("^I configure REST API with HTTP client$")
	public void createRestClient() throws HttpResponseException, IOException {
		// set the proxy and create a http client.
		httpClient = buildHTTPClient();
	}

	@When("^I send the GET API Request with HTTP client$")
	public void callAPIWithHTTPClient() throws ClientProtocolException,
			IOException {

		// Create new getRequest with below mentioned URL
		HttpGet getRequest = new HttpGet(
				"http://gturnquist-quoters.cfapps.io/api/random");

		// Execute your request and catch response
		HttpResponse response = httpClient.execute(getRequest);

		// Check for HTTP response code: 200 = success
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		responseMsg = new BasicResponseHandler().handleResponse(response);
		System.out.println("Printing the http response message" + responseMsg);
	}

	private HttpComponentsClientHttpRequestFactory setProxyFactory() {
		httpClient = buildHTTPClient();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);
		return factory;
	}

	private HttpClient buildHTTPClient() {
		final String username = prop.getProperty("PROXY_USER_NAME");
		final String password = prop.getProperty("PROXY_PASSWORD");
		final String proxyUrl = prop.getProperty("PROXY_SERVER_URL");
		final int port = Integer
				.parseInt(prop.getProperty("PROXY_SERVER_PORT"));

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(proxyUrl, port),
				new UsernamePasswordCredentials(username, password));

		HttpHost myProxy = new HttpHost(proxyUrl, port);
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		clientBuilder.setProxy(myProxy)
				.setDefaultCredentialsProvider(credsProvider)
				.disableCookieManagement();

		return clientBuilder.build();
	}

}

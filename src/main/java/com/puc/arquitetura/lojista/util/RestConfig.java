package com.puc.arquitetura.lojista.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Map;

@Service
public class RestConfig {

	private RestTemplate restTemplate;
	
	private final Logger log = LoggerFactory.getLogger(RestConfig.class);
	
	@Bean
	private RestTemplate createTemplate() {
		restTemplate = new RestTemplate(createClientHttpRequestFactory());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}

	@Bean
	private ClientHttpRequestFactory createClientHttpRequestFactory() 
	{
		final int connectTimeout = 120000;
		final int readTimeout = 120000;

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).build();
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
	            .setDefaultRequestConfig(config).build();
		
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
		httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
		
		return httpComponentsClientHttpRequestFactory;
	}
	
	private String getUrl() {
		String url = "http://localhost:8080/api/v1/atacadista";
		validateURLBase(url);
		return url;
	}
	
	private void validateURLBase(String url) {
		if (StringUtils.isEmpty(url)) {
			throw new InvalidParameterException("URL not found");
		}
	}

	private void validateRestActionMapping(String requestMapping) {
		if (StringUtils.isEmpty(requestMapping)) {
			throw new InvalidParameterException("Action mapping not found");
		}
	}

	public <T> ResponseEntity<T> get(String requestMapping, Class<T> responseType, Map<String, String> parameteres) throws Exception {
		try {
			validateRestActionMapping(requestMapping);
			String url = getUrl() + requestMapping;
			restTemplate = createTemplate();
			return restTemplate.getForEntity(url + getParamsString(parameteres), responseType);

		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			throw new Exception();
		}
	}

	public <T> ResponseEntity<T> post(String requestMapping, Class<T> responseType, Map<String, String> parameteres) throws Exception {
		try {
			validateRestActionMapping(requestMapping);
			String url = getUrl() + requestMapping;
			restTemplate = createTemplate();
			return restTemplate.postForEntity(url + getParamsString(parameteres), null, responseType);

		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			throw new Exception();
		}
	}
	
	private String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(entry.getValue());
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}

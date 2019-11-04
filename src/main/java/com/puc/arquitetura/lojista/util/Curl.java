package com.puc.arquitetura.lojista.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Curl {

	private static URI createUri(String scheme, String host, String path) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPath(path);
		return uriBuilder.build();
	}

	private static String formatResponse(HttpResponse httpResponse) throws IOException {
		ResponseHandler<String> handler = new BasicResponseHandler();
		return handler.handleResponse(httpResponse).toString();
	}

	private static HttpResponse getData(URI uri) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(uri);
		return client.execute(httpGet);
	}

	private static HttpResponse postData(URI uri, String requestBody) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(uri);
		StringEntity requestBodyEntity = new StringEntity(requestBody);
		httpPost.setEntity(requestBodyEntity);
		HttpResponse httpResponse = client.execute(httpPost);
		return httpResponse;
	}

	private static HttpResponse putData(URI uri, String requestBody) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut httpPut = new HttpPut(uri);
		StringEntity requestBodyEntity = new StringEntity(requestBody);
		httpPut.setEntity(requestBodyEntity);
		return client.execute(httpPut);
	}

	public static String makeRequest(String scheme, String host, String path, String type, String requestBody) throws URISyntaxException, IOException {
		URI uri = createUri(scheme, host, path);
		HttpResponse response;

		switch (type){
			case "POST":
				response = postData(uri, requestBody);
				break;
			case "PUT":
				response = putData(uri, requestBody);
				break;
			default:
				response = getData(uri);
		}

		return formatResponse(response);
	}
}

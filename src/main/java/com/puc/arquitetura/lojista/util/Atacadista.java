package com.puc.arquitetura.lojista.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Atacadista {
	
	private static Map<String, String> wholesalerApi = new HashMap<String, String>() {{
		put("scheme", "http");
		put("host", "localhost:8090/api/v1");
		put("postOrder", "/pedido");
		put("putBudget", "/budget");
	}};

	public static String postPedido(String requestBody) throws IOException, URISyntaxException {
		String response = Curl.makeRequest(
				wholesalerApi.get("scheme"),
				wholesalerApi.get("host"),
				wholesalerApi.get("postOrder"),
				"POST",
				requestBody);

		return response;
	}

	public static String postBudgetAcceptance(String requestBody) throws IOException, URISyntaxException {
		String response = Curl.makeRequest(
				wholesalerApi.get("scheme"),
				wholesalerApi.get("host"),
				wholesalerApi.get("putBudget"),
				"PUT",
				requestBody);

		return response;
	}
}

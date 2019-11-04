package com.puc.arquitetura.lojista.service;

import com.puc.arquitetura.lojista.domain.Pedido;
import com.puc.arquitetura.lojista.util.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class RestAtacadistaServiceImpl {
	
	private static final String POST_PEDIDO = "/pedido?";

	@Autowired
	private RestConfig restful;

	Pedido createPedido(Long id, Integer codigoProduto, Integer quantidade, String observacoes) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		params.put("codigoProduto", codigoProduto.toString());
		params.put("quantidade", quantidade.toString());
		params.put("observacoes", observacoes);

		ResponseEntity<Pedido> response = restful.post(POST_PEDIDO, Pedido.class, params);
		if (response != null) {
			return response.getBody();
		}
		return null;
 	}
	
}

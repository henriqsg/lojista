package com.puc.arquitetura.lojista.service;

import com.puc.arquitetura.lojista.domain.Orcamento;
import com.puc.arquitetura.lojista.domain.Pedido;
import com.puc.arquitetura.lojista.service.exception.PedidoNotFound;

import java.util.List;

public interface PedidoService {

    String getStatus(Long id) throws PedidoNotFound;

    Pedido createPedido(Integer codigoProduto, Integer quantidade, String observacoes);

    List<Pedido> all();

    Pedido authorize(boolean autorizar);
}

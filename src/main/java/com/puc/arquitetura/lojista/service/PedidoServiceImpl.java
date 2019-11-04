package com.puc.arquitetura.lojista.service;

import com.puc.arquitetura.lojista.domain.Pedido;
import com.puc.arquitetura.lojista.service.exception.PedidoNotFound;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final List<Pedido> pedidos = new ArrayList<>();

    private final RestAtacadistaServiceImpl restAtacadista;

    public PedidoServiceImpl(RestAtacadistaServiceImpl restAtacadista) {
        this.restAtacadista = restAtacadista;
    }

    @Override
    public String getStatus(Long id) throws PedidoNotFound {
        Optional<Pedido> pedido = pedidos.stream().filter(item -> item.getId().equals(id)).findFirst();
        return pedido.map(Pedido::getStatus).orElseThrow(PedidoNotFound::new);
    }

    @Override
    public Pedido createPedido(Integer codigoProduto, Integer quantidade, String observacoes) {
        Pedido pedido = new Pedido(codigoProduto, quantidade, observacoes);
        if (pedidos.isEmpty()) {
            pedido.setId(1L);
        } else {
            pedido.setId(pedidos.size() + 1L);
        }
        pedidos.add(pedido);

        Pedido response;
        // Comunicar com servico Atacadista
        try {
            response = restAtacadista.createPedido(pedido.getId(), codigoProduto, quantidade, observacoes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public List<Pedido> all() {
        return pedidos;
    }

    @Override
    public Pedido authorize(boolean autorizar) {
        return null;
    }


}

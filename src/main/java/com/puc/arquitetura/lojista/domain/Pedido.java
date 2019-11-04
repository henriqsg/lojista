package com.puc.arquitetura.lojista.domain;

import com.puc.arquitetura.lojista.service.util.EnumStatusPedido;

public class Pedido {

    private Long id;
    private Integer codigoProduto;
    private Integer quantidade;
    private String status;
    private String observacoes;

    private Orcamento orcamento;

    public Pedido(Integer codigoProduto, Integer quantidade, String observacoes) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
        this.status = EnumStatusPedido.PE.getTexto();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

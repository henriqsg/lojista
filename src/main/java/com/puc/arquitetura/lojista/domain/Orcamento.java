package com.puc.arquitetura.lojista.domain;

import java.time.ZonedDateTime;

public class Orcamento {

    private Long id;
    private boolean autorizado;
    private Integer valor;
    private ZonedDateTime dataEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public ZonedDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(ZonedDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}

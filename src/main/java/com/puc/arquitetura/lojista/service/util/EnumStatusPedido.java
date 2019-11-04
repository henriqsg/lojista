package com.puc.arquitetura.lojista.service.util;

public enum EnumStatusPedido {

    PE("Pendente"),
    SO("Solicitado"),
    EF("Em Fabricação"),
    FI("Finalizado"),
    DE("Despachado");

    private String texto;

    EnumStatusPedido(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }
}

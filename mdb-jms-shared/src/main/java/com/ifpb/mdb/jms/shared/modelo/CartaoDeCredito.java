package com.ifpb.mdb.jms.shared.modelo;

import java.math.BigDecimal;

/**
 *
 * @author jozimar
 */
public class CartaoDeCredito {

    private BigDecimal saldoDoCartao;
    private Long numero;

    public CartaoDeCredito() {
        this.saldoDoCartao = new BigDecimal(1000);
        this.numero = 1111111111111111L;
    }

    public BigDecimal getSaldoDoCartao() {
        return saldoDoCartao;
    }

    public void setSaldoDoCartao(BigDecimal saldoDoCartao) {
        this.saldoDoCartao = saldoDoCartao;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public boolean realizarPagamento(BigDecimal valorDaCompra) {
        if (getSaldoDoCartao().compareTo(valorDaCompra) != -1) {
            setSaldoDoCartao(getSaldoDoCartao().subtract(valorDaCompra));
            return true;
        }
        return false;
    }
}

package com.ifpb.mdb.shared.modelo;

import java.io.Serializable;

/**
 *
 * @author jozimar
 */
public class RespostaDoProcessamento implements Serializable {

    private String emailUsuario;
    private String mensagem;
    private int codigoPedido;

    public RespostaDoProcessamento() {
    }

    public RespostaDoProcessamento(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
}

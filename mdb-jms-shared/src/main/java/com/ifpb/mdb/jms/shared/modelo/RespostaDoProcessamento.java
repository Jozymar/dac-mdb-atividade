package com.ifpb.mdb.jms.shared.modelo;

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

    public RespostaDoProcessamento(String emailUsuario, String mensagem, int codigoPedido) {
        this.emailUsuario = emailUsuario;
        this.mensagem = mensagem;
        this.codigoPedido = codigoPedido;
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

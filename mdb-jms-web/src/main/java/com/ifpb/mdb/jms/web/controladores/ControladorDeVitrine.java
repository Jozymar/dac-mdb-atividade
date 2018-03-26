package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.jms.web.servico.BuscarProdutoEmDestaque;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeVitrine implements Serializable {

    @Inject
    private BuscarProdutoEmDestaque buscarProdutoEmDestaque;

    private String produtoEmDestaque;

    @PostConstruct
    public void init() {
        produtoEmDestaque = buscarProdutoEmDestaque.getProdutoEmDestaque();
    }

    public String getProdutoEmDestaque() {
        return produtoEmDestaque;
    }

    public void setProdutoEmDestaque(String produtoEmDestaque) {
        this.produtoEmDestaque = produtoEmDestaque;
    }
}

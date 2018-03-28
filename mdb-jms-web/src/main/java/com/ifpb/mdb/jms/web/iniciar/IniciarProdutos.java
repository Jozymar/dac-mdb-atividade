package com.ifpb.mdb.jms.web.iniciar;

import com.ifpb.mdb.jms.shared.modelo.Produto;
import com.ifpb.mdb.jms.shared.servico.DaoProduto;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author jozimar
 */
@Singleton
@Startup
public class IniciarProdutos {

    private Produto produto1;
    private Produto produto2;
    private Produto produto3;

    @Inject
    private DaoProduto daoProduto;

    @PostConstruct
    public void init() {
        produto1 = new Produto("Geladeira", new BigDecimal(2500));
        produto2 = new Produto("Fogão", new BigDecimal(800));
        produto3 = new Produto("Batedeira", new BigDecimal(500));

        if (daoProduto.consultarPorDescricao(produto1.getDescricao()) != null) {
            System.out.println("Já existe um produto cadastrado com a descrição informada");
        } else {
            daoProduto.cadastrar(produto1);
        }

        if (daoProduto.consultarPorDescricao(produto2.getDescricao()) != null) {
            System.out.println("Já existe um produto cadastrado com a descrição informada");
        } else {
            daoProduto.cadastrar(produto2);
        }

        if (daoProduto.consultarPorDescricao(produto3.getDescricao()) != null) {
            System.out.println("Já existe um produto cadastrado com a descrição informada");
        } else {
            daoProduto.cadastrar(produto3);
        }
    }
}

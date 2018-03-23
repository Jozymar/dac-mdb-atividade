package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.shared.modelo.Produto;
import com.ifpb.mdb.shared.servico.DaoProduto;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeProduto {

    private Produto produto = new Produto();

    @Inject
    private DaoProduto daoProduto;

    public List<Produto> todosOsProdutos() {
        return daoProduto.todosOsProdutos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

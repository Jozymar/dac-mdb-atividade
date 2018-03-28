package com.ifpb.mdb.jms.web.servico;

import com.ifpb.mdb.jms.shared.servico.DaoProduto;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author jozimar
 */
@Singleton
public class BuscarProdutoEmDestaque {

    private String produtoEmDestaque;

    @Inject
    private DaoProduto daoProduto;

    @Schedule(hour = "*", minute = "*", second = "*/1")
    public void ProdutoEmDestaque() {
        int idLimite = daoProduto.retornarLimiteDeId();
        int idRandomico = GeradorRandomico.gerarNumeroRandomico(idLimite);
        while (!daoProduto.produtoExiste(idRandomico)) {
            idRandomico = GeradorRandomico.gerarNumeroRandomico(idLimite);
        }
        setProdutoEmDestaque(daoProduto.buscarProdutoporId(idRandomico).getDescricao());
    }

    public String getProdutoEmDestaque() {
        return produtoEmDestaque;
    }

    public void setProdutoEmDestaque(String produtoEmDestaque) {
        this.produtoEmDestaque = produtoEmDestaque;
    }
}

package com.ifpb.mdb.jms.web.servico;

import com.ifpb.mdb.jms.shared.modelo.Produto;
import java.util.Optional;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Singleton
public class BuscarProdutoEmDestaque {

    @PersistenceContext(unitName = "persistencia")
    EntityManager em;

    private String produtoEmDestaque;

    private int retornarLimiteDeId() {
        String querySql = "SELECT max(produto.id) FROM Produto produto";
        TypedQuery<Integer> query = em
                .createQuery(querySql, int.class);
        if (query.getSingleResult() == null) {
            return 0;
        } else {
            return query.getSingleResult();
        }
    }

    private Produto buscarProdutoporId(int id) {
        String querySql = "SELECT produto FROM Produto produto WHERE produto.id=:id";
        TypedQuery<Produto> query = em
                .createQuery(querySql, Produto.class);
        query.setParameter("id", id);
        Optional<Produto> produto = query.getResultList().stream().findFirst();
        if (produto.isPresent()) {
            return produto.get();
        } else {
            return null;
        }
    }

    private boolean produtoExiste(int id) {
        String querySql = "SELECT produto FROM Produto produto "
                + "WHERE produto.id=:id";
        TypedQuery<Produto> query = em
                .createQuery(querySql, Produto.class);
        query.setParameter("id", id);
        Optional<Produto> produto = query.getResultList().stream().findFirst();
        return produto.isPresent();
    }

    @Schedule(hour = "*", minute = "*", second = "*/1")
    public void ProdutoEmDestaque() {
        int idLimite = retornarLimiteDeId();
        if (idLimite != 0) {
            int idRandomico = GeradorRandomico.gerarNumeroRandomico(idLimite);
            while (!produtoExiste(idRandomico)) {
                idRandomico = GeradorRandomico.gerarNumeroRandomico(idLimite);
            }
            setProdutoEmDestaque(buscarProdutoporId(idRandomico).getDescricao());
        }
    }

    public String getProdutoEmDestaque() {
        return produtoEmDestaque;
    }

    public void setProdutoEmDestaque(String produtoEmDestaque) {
        this.produtoEmDestaque = produtoEmDestaque;
    }
}

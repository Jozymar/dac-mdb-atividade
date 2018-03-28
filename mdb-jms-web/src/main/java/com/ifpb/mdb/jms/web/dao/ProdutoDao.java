package com.ifpb.mdb.jms.web.dao;

import com.ifpb.mdb.jms.shared.modelo.Produto;
import com.ifpb.mdb.jms.shared.servico.DaoProduto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Stateless
@Local(DaoProduto.class)
public class ProdutoDao implements DaoProduto {

    @PersistenceContext(unitName = "persistencia")
    EntityManager em;

    @Override
    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    @Override
    public Produto consultarPorDescricao(String descricao) {
        TypedQuery<Produto> query = em
                .createQuery("SELECT produto FROM Produto produto "
                        + "WHERE produto.descricao=:descricao", Produto.class);
        query.setParameter("descricao", descricao);
        Optional<Produto> produto = query.getResultList().stream().findFirst();
        if (produto.isPresent()) {
            return produto.get();
        } else {
            return null;
        }
    }

    @Override
    public int retornarLimiteDeId() {
        String querySql = "SELECT max(produto.id) FROM Produto produto";
        TypedQuery<Integer> query = em
                .createQuery(querySql, int.class);
        return query.getSingleResult();

    }

    @Override
    public Produto buscarProdutoporId(int id) {
        String querySql = "SELECT produto FROM Produto produto WHERE produto.id=:id";
        TypedQuery<Produto> query = em
                .createQuery(querySql, Produto.class
                );
        query.setParameter("id", id);
        Optional<Produto> produto = query.getResultList().stream().findFirst();
        if (produto.isPresent()) {
            return produto.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean produtoExiste(int id) {
        String querySql = "SELECT produto FROM Produto produto "
                + "WHERE produto.id=:id";
        TypedQuery<Produto> query = em
                .createQuery(querySql, Produto.class
                );
        query.setParameter("id", id);
        Optional<Produto> produto = query.getResultList().stream().findFirst();
        return produto.isPresent();
    }

    @Override
    public List<Produto> todosOsProdutos() {
        String sqlQuery = "SELECT produto FROM Produto produto ";
        TypedQuery<Produto> query = em.createQuery(sqlQuery, Produto.class
        );
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

}

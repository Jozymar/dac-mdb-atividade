package com.ifpb.mdb.cartcredit.daos;

import com.ifpb.mdb.shared.modelo.Pedido;
import com.ifpb.mdb.shared.servico.DaoPedido;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jozimar
 */
@Stateless
@Local(DaoPedido.class)
public class PedidoDao implements DaoPedido {

    @PersistenceContext(unitName = "persistencia")
    EntityManager em;

    @Override
    public void cadastrar(Pedido pedido) {
        em.persist(pedido);
    }

    @Override
    public void remover(Pedido pedido) {
        em.remove(pedido);
    }
}

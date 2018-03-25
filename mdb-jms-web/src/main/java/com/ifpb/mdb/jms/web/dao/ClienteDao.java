package com.ifpb.mdb.jms.web.dao;

import com.ifpb.mdb.jms.shared.modelo.Cliente;
import com.ifpb.mdb.jms.shared.servico.DaoCliente;
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
@Local(DaoCliente.class)
public class ClienteDao implements DaoCliente {

    @PersistenceContext(unitName = "persistencia")
    EntityManager em;

    @Override
    public void cadastrar(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public Cliente consultarPorEmail(String email) {
        TypedQuery<Cliente> query = em
                .createQuery("SELECT cliente FROM Cliente cliente "
                        + "WHERE cliente.email=:email", Cliente.class);
        query.setParameter("email", email);
        Optional<Cliente> cliente = query.getResultList().stream().findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    @Override
    public Cliente autenticar(String email, String senha) {
        String querySql = "SELECT cliente FROM Cliente cliente WHERE cliente.email= :email "
                + "AND cliente.senha= :senha";
        TypedQuery<Cliente> query = em.createQuery(querySql, Cliente.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);

        Optional<Cliente> cliente = query.getResultList().stream().findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

}

package com.ifpb.mdb.jms.shared.servico;

import com.ifpb.mdb.jms.shared.modelo.Cliente;

/**
 *
 * @author jozimar
 */
public interface DaoCliente {

    public void cadastrar(Cliente cliente);

    public Cliente autenticar(String email, String senha);

    public Cliente consultarPorEmail(String email);
}

package com.ifpb.mdb.shared.servico;

import com.ifpb.mdb.shared.modelo.Cliente;

/**
 *
 * @author jozimar
 */
public interface DaoCliente {

    public void cadastrar(Cliente cliente);

    public Cliente autenticar(String email, String senha);

    public Cliente consultarPorEmail(String email);
}

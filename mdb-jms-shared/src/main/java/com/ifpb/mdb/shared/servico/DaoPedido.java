package com.ifpb.mdb.shared.servico;

import com.ifpb.mdb.shared.modelo.Pedido;

/**
 *
 * @author jozimar
 */
public interface DaoPedido {

    public void cadastrar(Pedido pedido);

    public void remover(Pedido pedido);
}

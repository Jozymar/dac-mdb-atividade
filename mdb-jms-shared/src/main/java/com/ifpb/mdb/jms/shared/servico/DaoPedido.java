package com.ifpb.mdb.jms.shared.servico;

import com.ifpb.mdb.jms.shared.modelo.Pedido;

/**
 *
 * @author jozimar
 */
public interface DaoPedido {

    public void cadastrar(Pedido pedido);

    public void remover(Pedido pedido);
}

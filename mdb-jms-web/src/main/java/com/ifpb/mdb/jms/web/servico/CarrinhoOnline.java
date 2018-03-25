package com.ifpb.mdb.jms.web.servico;

import com.ifpb.mdb.jms.shared.modelo.Cliente;
import com.ifpb.mdb.jms.shared.modelo.Pedido;
import com.ifpb.mdb.jms.shared.modelo.Produto;
import com.ifpb.mdb.jms.shared.servico.ServicoCarrinho;
import java.util.Collections;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author jozimar
 */
@Stateful
@Local(ServicoCarrinho.class)
public class CarrinhoOnline implements ServicoCarrinho {

    private Pedido pedido = new Pedido();

    @Inject
    private EnviarMensagemFinal mensagemFinal;

    @Override
    public void adicionarProduto(Produto produto) {
        pedido.add(produto);
    }

    @Override
    public void removerProduto(Produto produto) {
        pedido.remove(produto);
    }

    @Override
    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(pedido.getProdutos());
    }

    @Override
    public void addCliente(Cliente cliente) {
        pedido.setCliente(cliente);
    }

    @Remove
    @Override
    public void finalizarCompra() {
        mensagemFinal.enviar(pedido);
    }

}

package com.ifpb.mdb.shared.servico;

import com.ifpb.mdb.shared.modelo.Cliente;
import com.ifpb.mdb.shared.modelo.Produto;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface ServicoCarrinho {

    public void adicionarProduto(Produto produto);

    public void removerProduto(Produto produto);

    public List<Produto> listarProdutos();

    public void addCliente(Cliente cliente);

    public void finalizarCompra();
}

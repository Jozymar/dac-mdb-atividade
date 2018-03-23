package com.ifpb.mdb.shared.servico;

import com.ifpb.mdb.shared.modelo.Produto;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface DaoProduto {

    public void cadastrar(Produto produto);

    public List<Produto> todosOsProdutos();
}

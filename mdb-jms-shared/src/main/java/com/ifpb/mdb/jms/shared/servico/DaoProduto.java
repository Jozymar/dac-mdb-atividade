package com.ifpb.mdb.jms.shared.servico;

import com.ifpb.mdb.jms.shared.modelo.Produto;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface DaoProduto {

    public void cadastrar(Produto produto);

    public Produto consultarPorDescricao(String descricao);

    public List<Produto> todosOsProdutos();

    public int retornarLimiteDeId();

    public Produto buscarProdutoporId(int id);

    public boolean produtoExiste(int id);
}

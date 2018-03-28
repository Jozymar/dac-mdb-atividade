package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.jms.shared.modelo.Produto;
import com.ifpb.mdb.jms.shared.servico.DaoProduto;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeProduto {

    private Produto produto = new Produto();

    @Inject
    private DaoProduto daoProduto;

    public String cadastrarProduto() {
        if (daoProduto.consultarPorDescricao(produto.getDescricao()) != null) {
            mensagemErro("Cadastro de Produtos", "Já existe um produto cadastrado "
                    + "com a descrição informada!");
        } else {
            daoProduto.cadastrar(produto);
            return "cadastrodeprodutos.xhtml?faces-redirect=true";
        }
        return null;
    }

    public List<Produto> todosOsProdutos() {
        return daoProduto.todosOsProdutos();
    }

    private void mensagemErro(String titulo, String corpo) {
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

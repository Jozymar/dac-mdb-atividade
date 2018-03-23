package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.shared.modelo.Cliente;
import com.ifpb.mdb.shared.modelo.Produto;
import com.ifpb.mdb.shared.servico.DaoCliente;
import com.ifpb.mdb.shared.servico.DaoProduto;
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
public class ControladorDeCadastro {

    private Cliente cliente = new Cliente();

    private Produto produto = new Produto();

    @Inject
    private DaoCliente daoCliente;

    @Inject
    private DaoProduto daoProduto;

    public String cadastrar() {
        if (daoCliente.consultarPorEmail(cliente.getEmail()) != null) {
            mensagemErro("Cadastro", "Já existe um cliente cadastrado "
                    + "com o e-mail informado!");
        } else {
            daoCliente.cadastrar(cliente);
            return "index.xhtml?faces-redirect=true";
        }
        return null;
    }

    private void mensagemErro(String titulo, String corpo) {
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }

    public String cadastrarProduto() {
        daoProduto.cadastrar(produto);
        return "cadastrodeprodutos.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

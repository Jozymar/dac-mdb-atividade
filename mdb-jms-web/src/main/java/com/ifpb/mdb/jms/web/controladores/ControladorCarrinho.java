package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.shared.modelo.Cliente;
import com.ifpb.mdb.shared.modelo.Produto;
import com.ifpb.mdb.shared.servico.ServicoCarrinho;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jozimar
 */
@Named
@SessionScoped
public class ControladorCarrinho implements Serializable {

    @EJB
    private ServicoCarrinho carrinho;

    private Produto produto = new Produto();

    private Cliente cliente;

    private HttpSession sessao;

    private ExternalContext externalContext;

    private InitialContext contexto;

    @PostConstruct
    public void init() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessao = (HttpSession) externalContext.getSession(true);
        cliente = (Cliente) sessao.getAttribute("cliente");
        this.carrinho.addCliente(cliente);
    }

    public String adicionarProduto(Produto p) {
        carrinho.adicionarProduto(p);
        produto = new Produto();
        return null;
    }

    public String removerProduto(Produto produto) {
        carrinho.removerProduto(produto);
        return null;
    }

    public String finalizarCompra() throws NamingException {
        carrinho.finalizarCompra();
        contexto = new InitialContext();
        carrinho = (ServicoCarrinho) contexto.lookup("java:global/mdb-jms-web/CarrinhoOnline");
        carrinho.addCliente(cliente);
        return null;
    }

    public List<Produto> todosOsProdutos() {
        return carrinho.listarProdutos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ServicoCarrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ServicoCarrinho carrinho) {
        this.carrinho = carrinho;
    }
}

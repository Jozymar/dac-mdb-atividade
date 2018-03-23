package com.ifpb.mdb.jms.web.controladores;

import com.ifpb.mdb.shared.modelo.Cliente;
import com.ifpb.mdb.shared.servico.DaoCliente;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorCliente {

    private HttpSession sessao;

    private Cliente cliente = new Cliente();

    @Inject
    private DaoCliente daoCliente;

    public String realizarlogin() {
        Cliente clienteLogado = daoCliente.consultarPorEmail(cliente.getEmail());
        if (clienteLogado == null) {
            mensagemErro("Login", "O cliente informado não está cadastrado!");
            return null;
        } else {
            Cliente clienteAutenticavel = daoCliente
                    .autenticar(cliente.getEmail(), cliente.getSenha());
            if (clienteAutenticavel == null) {
                mensagemErro("Login", "Os dados informados estão incorretos!");
                return null;
            } else {
                sessao = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
                sessao.setAttribute("cliente", clienteLogado);
                sessao.setAttribute("nome", clienteLogado.getNome());
                return "inicial.xhtml?faces-redirect=true";
            }
        }
    }

    public String getNomeSession() {
        sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return sessao.getAttribute("nome").toString();
    }

    private void mensagemErro(String titulo, String corpo) {
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

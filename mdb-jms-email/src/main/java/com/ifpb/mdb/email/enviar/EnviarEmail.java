package com.ifpb.mdb.email.enviar;

import com.ifpb.mdb.shared.modelo.Pedido;
import com.ifpb.mdb.shared.modelo.RespostaDoProcessamento;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author jozimar
 */
@Stateless
public class EnviarEmail {

    private final String EMAIL = "projetodacq@gmail.com";
    private final String SENHA = "projetodac123";
    private final Email email = new SimpleEmail();

    @PostConstruct
    public void init() {
        email.setHostName("smtp.googlemail.com");
        email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
        email.setTLS(true);
        email.setSSL(true);
    }

    public void sendFinalizar(Pedido pedido) {
        try {
            email.setFrom(EMAIL);
            email.addTo(pedido.getCliente().getEmail());
            email.setMsg("Pedido finalizado, informações: "
                    + "Cliente: " + pedido.getCliente().getNome() + ", Produto(s): " + pedido.getProdutos().toString()
            );
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendProcessar(RespostaDoProcessamento resposta) {
        try {
            email.setFrom(EMAIL);
            email.addTo(resposta.getEmailUsuario());
            email.setMsg("Seu pedido foi processado. "
                    + "Veja as informações: " + resposta.getMensagem());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

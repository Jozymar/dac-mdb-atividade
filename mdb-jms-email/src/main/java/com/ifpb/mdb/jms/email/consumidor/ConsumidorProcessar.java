package com.ifpb.mdb.jms.email.consumidor;

import com.ifpb.mdb.jms.email.enviar.EnviarEmail;
import com.ifpb.mdb.jms.shared.modelo.RespostaDoProcessamento;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author jozimar
 */
@MessageDriven(
        mappedName = "java:global/jms/queueProcessar",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
            ,
            @ActivationConfigProperty(propertyName = "destinationName", propertyValue = "queueProcessar"),}
)
public class ConsumidorProcessar implements MessageListener {

    @Inject
    private EnviarEmail remetente;

    @Override
    public void onMessage(Message message) {
        try {
            RespostaDoProcessamento resposta = message.getBody(RespostaDoProcessamento.class);
            remetente.sendProcessar(resposta);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorProcessar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

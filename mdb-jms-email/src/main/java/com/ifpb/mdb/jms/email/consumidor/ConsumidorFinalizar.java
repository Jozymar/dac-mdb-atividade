package com.ifpb.mdb.jms.email.consumidor;

import com.ifpb.mdb.jms.email.enviar.EnviarEmail;
import com.ifpb.mdb.jms.shared.modelo.Pedido;
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
        mappedName = "java:global/jms/TopicFinalizar",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName", propertyValue = "TopicFinalizar"),}
)
public class ConsumidorFinalizar implements MessageListener {

    @Inject
    private EnviarEmail remetente;

    @Override
    public void onMessage(Message message) {
        try {
            Pedido pedido = message.getBody(Pedido.class);
            remetente.sendFinalizar(pedido);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorFinalizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

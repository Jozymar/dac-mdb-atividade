package com.ifpb.mdb.jms.web;

import com.ifpb.mdb.shared.modelo.Pedido;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 *
 * @author jozimar
 */
@Stateless
public class EnviarMensagemFinal {

    @Resource(lookup = "java:global/jms/TopicFinalizar")
    private Topic topico;

    @Inject
    private JMSContext context;

    public void enviar(Pedido pedido) {
        JMSProducer produtor = context.createProducer();
        produtor.send(topico, pedido);
        System.out.println("O Pedido foi enviado para a finalização");
    }
}

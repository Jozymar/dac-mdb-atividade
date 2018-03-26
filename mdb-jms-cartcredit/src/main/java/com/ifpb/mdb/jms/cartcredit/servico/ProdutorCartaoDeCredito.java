package com.ifpb.mdb.jms.cartcredit.servico;

import com.ifpb.mdb.jms.shared.modelo.RespostaDoProcessamento;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 *
 * @author jozimar
 */
@JMSDestinationDefinition(
        name = "java:global/jms/queueProcessar",
        interfaceName = "javax.jms.Queue",
        resourceAdapter = "jmsra",
        destinationName = "queueProcessar")

@Stateless
public class ProdutorCartaoDeCredito {

    @Resource(lookup = "java:global/jms/queueProcessar")
    private Queue fila;

    @Inject
    private JMSContext context;

    public void enviar(RespostaDoProcessamento respostaDoProcessamento) {
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, respostaDoProcessamento);
    }
}

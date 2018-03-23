package com.ifpb.mdb.cartcredit.servicos;

import com.ifpb.mdb.shared.modelo.RespostaDoProcessamento;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 *
 * @author jozimar
 */
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

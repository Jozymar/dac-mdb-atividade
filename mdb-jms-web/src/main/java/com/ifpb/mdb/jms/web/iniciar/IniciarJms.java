package com.ifpb.mdb.jms.web.iniciar;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

/**
 *
 * @author jozimar
 */
@JMSDestinationDefinitions(value = {
    @JMSDestinationDefinition(
            name = "java:global/jms/TopicFinalizar",
            interfaceName = "javax.jms.Topic",
            resourceAdapter = "jmsra",
            destinationName = "TopicFinalizar"
    )
    ,
@JMSDestinationDefinition(
            name = "java:global/jms/queueProcessar",
            interfaceName = "javax.jms.Queue",
            resourceAdapter = "jmsra",
            destinationName = "queueProcessar")
})
@Singleton
@Startup
public class IniciarJms {

    public IniciarJms() {
        System.out.println("Iniciando o JMS");
    }
}

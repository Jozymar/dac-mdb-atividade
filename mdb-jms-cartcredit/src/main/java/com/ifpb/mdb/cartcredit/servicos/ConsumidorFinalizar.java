package com.ifpb.mdb.cartcredit.servicos;

import com.ifpb.mdb.shared.modelo.CartaoDeCredito;
import com.ifpb.mdb.shared.modelo.Pedido;
import com.ifpb.mdb.shared.modelo.RespostaDoProcessamento;
import com.ifpb.mdb.shared.servico.DaoPedido;
import java.math.BigDecimal;
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
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
            ,
            @ActivationConfigProperty(propertyName = "destinationName", propertyValue = "TopicFinalizar"),}
)
public class ConsumidorFinalizar implements MessageListener {

    @Inject
    private ProdutorCartaoDeCredito produtor;

    @Inject
    private DaoPedido daoPedido;

    @Override
    public void onMessage(Message message) {
        CartaoDeCredito cartao = new CartaoDeCredito();
        try {
            Pedido pedido = message.getBody(Pedido.class);
            BigDecimal valorDaCompra = pedido.getValorTotal();
            RespostaDoProcessamento resposta = new RespostaDoProcessamento(pedido.getCliente().getEmail());
            if (cartao.realizarPagamento(valorDaCompra)) {
                daoPedido.cadastrar(pedido);
                resposta.setMensagem("O pagamento do pedido foi confirmado com sucesso!"
                        + ", utilizando o cartão: " + cartao.getNumero());
            } else {
                resposta.setMensagem("Não há saldo suficiente para realizar o pagamento!");
            }
            produtor.enviar(resposta);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorFinalizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

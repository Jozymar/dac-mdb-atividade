package com.ifpb.mdb.jms.web.servico;

import java.util.Random;

/**
 *
 * @author jozimar
 */
public class GeradorRandomico {

    public static int gerarNumeroRandomico(int limite) {
        Random gerador = new Random();
        return gerador.nextInt(limite);
    }
}

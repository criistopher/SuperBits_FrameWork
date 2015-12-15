/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.soap;

import com.super_bits.modulosSB.SBCore.Controller.SBWS_Client;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Salvio
 */
public abstract class SBWS_IO<T> extends SBWS_Client<T> {

    public static boolean ativo = false;

    public boolean ativaServidor(Class implementacaoWebservice) {

        try {
            try {
                System.out.println("iniciando publicação de " + getUrl());
                Endpoint.publish("http://" + getDnsName() + ":" + getporta().toString() + "/ws/" + getNomeServico(), implementacaoWebservice.newInstance());
                System.out.println("WebService Publicado em:" + getUrlWsdl());
                return true;

            } catch (InstantiationException e) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao instanciar a Classe do servidor, certifique que a classe seja uma classe sem parametros de constructor e que implemente a Interface do webservice", e);
                return false;
            }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao iniciar WS Soap", e);
            return false;
        }

    }
}

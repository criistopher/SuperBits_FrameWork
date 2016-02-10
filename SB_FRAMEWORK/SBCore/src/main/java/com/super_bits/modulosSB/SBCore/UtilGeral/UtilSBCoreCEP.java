/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.Controller.UtilWebService.cep.WebServiceCepPostmon;
import com.super_bits.Controller.UtilWebService.cep.WebServiceCepRepublicaVirtual;
import com.super_bits.modulosSB.SBCore.InfoCampos.cep.ItemBairro;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreCEP {

    /**
     *
     * Configura rua, cidade, estado, nome do local, a partir de um CEP
     *
     * @param cep cep utilizado em uma pesquisa
     * @param pLocal O local onde o endereço será configurado
     * @return True se encontrar o CEP, false se não encontrar
     */
    public static boolean configuraEndereco(String cep, ItfLocal pLocal) {

        WebServiceCepRepublicaVirtual republicaVirtual = WebServiceCepRepublicaVirtual.searchCep("30190030");
        if (!republicaVirtual.isCepNotFound()) {
            //ItfBairro bairroEncontrao = new ItemBairro(republicaVirtual);

            pLocal.setNome(republicaVirtual.getLogradouroFull());
            pLocal.configIDFromNomeCurto();

            return true;
        }

        return false;

    }

    public static List<String> cepsEncontrados(String parametros) {
        return new ArrayList<>();
    }

}

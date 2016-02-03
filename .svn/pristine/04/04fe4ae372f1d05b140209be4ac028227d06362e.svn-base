/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.util;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreItens;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBPersistenciaFabricas {

    private static List<ItfBeanSimples> listaRegistros(Class pFabrica) {
        List<ItfBeanSimples> lista = new ArrayList<>();

        for (Object obj : pFabrica.getEnumConstants()) {
            ItfBeanSimples registro = (ItfBeanSimples) ((ItfFabrica) obj).getRegistro();
            lista.add(registro);

        }
        return lista;

    }

    private static List<ItfBeanSimples> listaOrdenada(List<ItfBeanSimples> pLista) {
        return UtilSBCoreItens.ordernarPorId(pLista);

    }

    /**
     *
     * Realiza a persistencia de todos os registros obtidos com getRegistro da
     * fábrica Ordena por id, e persiste
     *
     * @param pFabrica Enum que extende ItfFabrica e retorna entidades
     * persistiveis no getRegistro
     * @param pEM Gerenciamento de sessão
     */
    public static void persistirRegistrosDaFabrica(Class pFabrica, EntityManager pEM) {
        if (pFabrica.getEnumConstants() == null) {

            ItfMensagem msg = FabMensagens.ERRO.getMsgSistema("Nenum Enum foi encontrado para persistir nesta fabrica" + pFabrica.getSimpleName());

            SBCore.getCentralDeMensagens().enviaMensagem(msg);
            return;

        }

        for (Object entidade : listaOrdenada(listaRegistros(pFabrica))) {

            UtilSBPersistencia.mergeRegistro(entidade, pEM);
        }

    }
}

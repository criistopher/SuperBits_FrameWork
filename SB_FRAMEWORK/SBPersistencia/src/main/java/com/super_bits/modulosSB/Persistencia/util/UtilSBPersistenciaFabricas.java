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
    
    public enum TipoOrdemGravacao{
        ORDERNAR_POR_ID,ORDERNAR_POR_ORDEM_DE_DECLARCAO
    }

    private static List<ItfBeanSimples> listaRegistros(Class pFabrica) {
        List<ItfBeanSimples> lista = new ArrayList<>();

        for (Object obj : pFabrica.getEnumConstants()) {
            ItfBeanSimples registro = (ItfBeanSimples) ((ItfFabrica) obj).getRegistro();
            lista.add(registro);

        }
        return lista;

    }

    private static List<ItfBeanSimples> listaOrdenadaPorID(List<ItfBeanSimples> pLista) {
        return UtilSBCoreItens.ordernarPorId(pLista);

    }
    private static List<ItfBeanSimples> listaOrdenadaPorOrdemDEclaracao(Class pFabrica){
        return listaRegistros(pFabrica);
    }

    /**
     *
     * Realiza a persistencia de todos os registros obtidos com getRegistro da
     * fábrica Ordena por id, e persiste
     *
     * @param pFabrica Enum que extende ItfFabrica e retorna entidades
     * persistiveis no getRegistro
     * @param pEM Gerenciamento de sessão
     * @param pTipoOrdem Especifica a ordem da gravação (podendo ser pelo id do registro, ou pela ordem de declaração do Enum)
     */
    public static void persistirRegistrosDaFabrica(Class pFabrica, EntityManager pEM,TipoOrdemGravacao pTipoOrdem) {
        
        if (pFabrica.getEnumConstants() == null) {
            ItfMensagem msg = FabMensagens.ERRO.getMsgSistema("Nenum Enum foi encontrado para persistir nesta fabrica" + pFabrica.getSimpleName());
            SBCore.getCentralDeMensagens().enviaMensagem(msg);
            return;
        }
        
        switch (pTipoOrdem){
            case ORDERNAR_POR_ID:
                for (Object entidade : listaOrdenadaPorID(listaRegistros(pFabrica))) {
                UtilSBPersistencia.mergeRegistro(entidade, pEM);
                }
                break;
            case ORDERNAR_POR_ORDEM_DE_DECLARCAO:
                for (Object entidade: listaRegistros(pFabrica)){
                    UtilSBPersistencia.mergeRegistro(entidade,pEM);
                }
                break;
            default:
                throw new AssertionError(pTipoOrdem.name());
            
        
        }
        
        
    }
}

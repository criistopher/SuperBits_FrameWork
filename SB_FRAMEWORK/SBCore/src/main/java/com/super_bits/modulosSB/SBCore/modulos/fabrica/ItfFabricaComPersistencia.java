/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fabrica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabricaComPersistencia extends ItfFabrica {

    /**
     *
     *
     * Este método retornar o Objeto atualizado A partir do banco de dados Um
     * exemplo de utlização: UtilSBPersistencia.loadEntidade(getRegistro(),
     * pEM);
     *
     * @return return (ObjetoEmQuestao)
     * UtilSBPersistencia.loadEntidade(getRegistro(), pEM);
     */
    public ItfBeanSimples getRegstroJPABag(EntityManager pEM);

}

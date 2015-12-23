/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.fabrica;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import java.util.List;

/**
 *
 * Interface para implementar fabricas de Modulos
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 16/12/2015
 * @version 1.0
 */
public interface ItfFabricaModulos extends ItfFabrica {

    /**
     * Não implementado ainda;
     *
     * @return
     */
    public List<ItfGrupoUsuario> getAcessoGruposLiberadosPadrao();

    /**
     *
     * Retorna o mesmo que o getRegistro
     *
     * @return
     */
    public ItfAcaoDoSistema getAcaoDoSistema();
}

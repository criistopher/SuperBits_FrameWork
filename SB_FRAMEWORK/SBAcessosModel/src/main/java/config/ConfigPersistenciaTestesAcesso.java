/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabSegurancaGruposPadrao;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.ItfConfigSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 05/01/2015
 * @version 1.0
 */
public class ConfigPersistenciaTestesAcesso implements ItfConfigSBPersistencia {

    @Override
    public String bancoPrincipal() {
        return "SBDBTestes";
    }

    @Override
    public String[] bancosExtra() {
        return new String[0];
    }

    @Override
    public String formatoDataBanco() {
        return "yyyy-MM-dd HH:mm:ss";
    }

    @Override
    public String formatoDataUsuario() {
        return "dd/mm/yy HH:mm:ss";
    }

    @Override
    public String pastaImagensJPA() {
        return SBCore.getDiretorioBase() + "/resource/img";
    }

    @Override
    public void criarBancoInicial() {
        System.out.println("Nada a ser feito no criar banco inicial");
    }

    @Override
    public Class<? extends ItfFabrica>[] fabricasRegistrosIniciais() {
        return new Class[]{FabSegurancaGruposPadrao.class};
    }

}

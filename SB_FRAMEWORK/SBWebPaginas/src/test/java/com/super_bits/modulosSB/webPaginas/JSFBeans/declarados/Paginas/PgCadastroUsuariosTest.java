/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes.TestePaginaEntidade;
import config.ConfigPersistenciaTestesAcesso;
import config.FabConfiguracoesCoreAcessosModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class PgCadastroUsuariosTest extends TestePaginaEntidade<UsuarioSB> {

    public PgCadastroUsuariosTest() {
    }

    @Override
    public void configurarDAdosInsert() {

        GrupoUsuarioSB novogrupo = new GrupoUsuarioSB();

        novogrupo.setAtivo(true);
        novogrupo.setNome("teste");
        novogrupo.setId(1);

        pagina.getEntidadeSelecionada().setGrupo(novogrupo);
    }

    @Override
    public void configurarDadosEditar() {

    }

    @Override
    public void configurarPesquisa() {
        System.out.println("Config Pesquisa");
    }

    @Override
    public ItfPaginaGerenciarEntidade definirPagina() {
        return new PgCadastroUsuarios();
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador(), true);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}

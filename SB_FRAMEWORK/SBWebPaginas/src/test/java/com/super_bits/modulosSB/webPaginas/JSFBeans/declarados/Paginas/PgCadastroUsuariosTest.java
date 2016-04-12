/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarDadosEditar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarPesquisa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

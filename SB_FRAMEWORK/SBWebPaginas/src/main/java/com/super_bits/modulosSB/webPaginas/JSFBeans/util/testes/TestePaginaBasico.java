/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public abstract class TestePaginaBasico extends TesteJunitSBPersistencia {

    /**
     *
     * Retorna a pagina que será testada, já instanciada
     *
     * @return
     */
    public abstract ItfB_Pagina getPaginaBasico();

    @Test
    public void testeAcaoVinculada() {
        ItfB_Pagina pagina = null;
        try {
            pagina = getPaginaBasico();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

        ItfAcaoGerenciarEntidade acaoGerenciarDominio = pagina.getAcaoVinculada();
        try {
            UtilTestePagina.testaAcaoFormulario((ItfAcaoFormulario) acaoGerenciarDominio);
            UtilTestePagina.testaconfigIcone(acaoGerenciarDominio.getEnumAcaoDoSistema());
        } catch (Throwable e) {
            lancarErroJUnit(e);
        }
    }

}

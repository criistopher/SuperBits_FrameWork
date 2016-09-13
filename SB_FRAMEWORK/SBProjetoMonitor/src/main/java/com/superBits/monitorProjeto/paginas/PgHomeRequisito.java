/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superBits.monitorProjeto.paginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;

/**
 *
 * @author salvioF
 */
@InfoPagina(nomeCurto = "Home", tags = {"pagina inicial"})
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_MB_HOME)
public class PgHomeRequisito extends MB_Pagina {

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.admin_developer.FabAcaoAdminDeveloper;
import com.super_bits.modulosSB.webPaginas.controller.admin_developer.InfoAcaoAdminDeveloper;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "SYS_ADM", tags = "Administração do Sistema")
@ViewScoped
@InfoAcaoAdminDeveloper(acao = FabAcaoAdminDeveloper.DEV_PROJETO_MB)
public class PgAdminSistema extends MB_PaginaConversation {

    private AcaoDoSistema acaoListarFerramentas;
    private List<AcaoDoSistema> ferramentasDisponiveis;

    @PostConstruct
    public void ferramentas() {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.InfoAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@Named
@SessionScoped
@InfoAcaoProjetoSB(acao = FabAcaoProjetoSB.PROJETO_GERENCIAR_MB)
@InfoPagina(nomeCurto = "Proj", tags = "Andamento do Projeto")
public class PgProjetoSBVisaoGeral extends MB_PaginaConversation {

    private List<AcaoDoSistema> acoesGestaoDoModulo;

    private ModuloAcaoSistema moduloSelecionado;
    private List<ModuloAcaoSistema> modulosDoSistema;
    private List<ItfBeanSimples> objetosDoSistema;
    private AcaoFormulario acaoVisualizarPontosDeFuncao;
    private AcaoFormulario acaoVisualizarBancoDeDados;

    public List<AcaoDoSistema> getAcoesGestaoDoModulo() {
        return acoesGestaoDoModulo;

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @PostConstruct
    public void init() {
        modulosDoSistema = (List) MapaAcoesSistema.getModulos();

    }

    public ModuloAcaoSistema getModuloSelecionado() {
        return moduloSelecionado;
    }

    public void setModuloSelecionado(ModuloAcaoSistema moduloSelecionado) {
        this.moduloSelecionado = moduloSelecionado;
    }

    public List<ModuloAcaoSistema> getModulosDoSistema() {
        return modulosDoSistema;
    }

    public void setModulosDoSistema(List<ModuloAcaoSistema> modulosDoSistema) {
        this.modulosDoSistema = modulosDoSistema;
    }

    public List<ItfBeanSimples> getObjetosDoSistema() {
        return objetosDoSistema;
    }

    public void setObjetosDoSistema(List<ItfBeanSimples> objetosDoSistema) {
        this.objetosDoSistema = objetosDoSistema;
    }

    public void setAcoesGestaoDoModulo(List<AcaoDoSistema> acoesGestaoDoModulo) {
        this.acoesGestaoDoModulo = acoesGestaoDoModulo;
    }

}

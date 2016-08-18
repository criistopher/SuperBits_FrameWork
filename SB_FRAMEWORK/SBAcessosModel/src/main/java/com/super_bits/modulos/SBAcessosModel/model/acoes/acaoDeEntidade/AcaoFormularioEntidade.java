/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSecundaria;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação de formulário para entidade"}, plural = "Ações de entidade")
public class AcaoFormularioEntidade extends AcaoSecundaria implements ItfAcaoFormulario, ItfAcaoSecundaria, ItfAcaoFormularioEntidade {

    private String xhtml = AcaoFormulario.VARIAVEIS_ACAO_DO_SISTEMA.VIEW_NAO_IMPLEMENTADA.toString();
    @Transient
    private ItfAcaoDoSistema acaoExectarFormulario;
    @Transient
    private List<CaminhoCampoReflexao> campos = new ArrayList<>();
    @Transient
    private List<GrupoCampos> grupos;

    public AcaoFormularioEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao, String pXhtml) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        xhtml = pXhtml;

    }

    public AcaoFormularioEntidade() {
    }

    public AcaoFormularioEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);

    }

    public AcaoFormularioEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao, FabTipoAcaoSistemaGenerica acaoGenerica) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao, acaoGenerica);

        setAcaoPrincipal(pAcaoPrincipal);
    }

    /**
     *
     * Constructor para ações gerenciar entidade
     *
     * @param pFabricaAcao
     * @param pXhtml
     */
    public AcaoFormularioEntidade(Class pclasse, ItfFabricaAcoes pFabricaAcao, String pXhtml) {
        super(pclasse, FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        if (!this.getClass().isAssignableFrom(AcaoGestaoEntidade.class)) {
            throw new UnsupportedOperationException("Este constructor só deve ser usado por uma acaoGestaoEntidade");
        }
        xhtml = pXhtml;

    }

    public AcaoFormularioEntidade(Class pclasse, ItfFabricaAcoes pFabricaAcao) {
        super(pclasse, FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);

    }

    @Override
    public String getXhtml() {
        return xhtml;
    }

    @Override
    public ItfAcaoDoSistema getAcaoExectarFormulario() {
        return acaoExectarFormulario;
    }

    @Override
    public List<CaminhoCampoReflexao> getCampos() {
        return campos;
    }

    @Override
    public void setAcaoExectarFormulario(ItfAcaoDoSistema pAcaoExectarFormulario) {
        acaoExectarFormulario = pAcaoExectarFormulario;
    }

    @Override
    public void setCampos(List<CaminhoCampoReflexao> pCampos) {
        campos = pCampos;
    }

    @Override
    public void setXhtml(String pXhtml) {
        xhtml = pXhtml;
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return true;
    }

    @Override
    public List<GrupoCampos> getGruposDeCampos() {
        if (grupos == null) {
            grupos = new ArrayList<>();
        }
        if (grupos.isEmpty()) {
            grupos = UtilSBCoreReflexaoCampos.buildAgrupamentoCampos(campos);
        }
        return grupos;
    }

    @Override
    public GrupoCampos getGrupoCampoByID(int pID) {
        if (pID >= getGruposDeCampos().size() - 1) {
            pID = 0;
        }
        if (getGruposDeCampos().isEmpty()) {
            return null;
        }
        return grupos.get(pID);
    }

    @Override
    public String getPastaXhtml() {
        return UtilSBCoreDiretorios.getDiretorioMenosXCasas(getXhtml(), 1);
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return getComoSecundaria().getAcaoPrincipal();
    }

}

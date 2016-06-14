/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
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
@InfoClasse(tags = {"Ação de Abertura de Formulário"})
public class AcaoFormulario extends AcaoDoSistema implements ItfAcaoFormulario {

    private String xhtml;
    @Transient
    private ItfAcaoDoSistema acaoExectarFormulario;
    @Transient
    private List<CaminhoCampoReflexao> campos;

    public AcaoFormulario() {
        super();
        campos = new ArrayList<>();

    }

    public AcaoFormulario(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao) {
        super(ptipoAcao, pAcao);
    }

    @Override
    public ItfAcaoSecundaria comoSecundaria() {
        return (ItfAcaoSecundaria) this;
    }

    public static enum VARIAVEIS_ACAO_DO_SISTEMA {
        VIEW_NAO_IMPLEMENTADA,;

        @Override
        public String toString() {
            switch (this) {
                case VIEW_NAO_IMPLEMENTADA:
                    return "/resources/SBComp/SBSystemPages/naoimplementado.xhtml";

                default:
                    return super.toString();

            }

        }
    }

    @Override
    public ItfAcaoDoSistema getAcaoExectarFormulario() {
        return acaoExectarFormulario;
    }

    @Override
    public void setAcaoExectarFormulario(ItfAcaoDoSistema acaoExectarFormulario) {
        this.acaoExectarFormulario = acaoExectarFormulario;
    }

    @Override
    public List<CaminhoCampoReflexao> getCampos() {
        return campos;
    }

    @Override
    public void setCampos(List<CaminhoCampoReflexao> campos) {
        this.campos = campos;
    }

    @Override
    public String getXhtml() {
        return xhtml;
    }

    /**
     *
     * @param xhtml
     */
    @Override
    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return true;
    }

    public List<GrupoCampos> getGruposDeCampos() {
        List<GrupoCampos> grupocampos = new ArrayList<>();
        if (!getCampos().isEmpty()) {
            GrupoCampos grupoatual = null;

            if (getCampos().get(0).isUmCampoSeparador()) {
                grupoatual = new GrupoCampos(UtilSBCoreReflexaoCampos.getCaminhoSemNomeClasse(nomeAcao));
            } else {
                grupoatual = new GrupoCampos();
            }
            grupocampos.add(grupoatual);

            for (CaminhoCampoReflexao campo : getCampos()) {
                if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(campo.getCaminhoCompletoString())) {

                    grupoatual = new GrupoCampos(UtilSBCoreReflexaoCampos.getNomeGrupoPorStrSeparador(campo.getCaminhoCompletoString()));
                    grupocampos.add(grupoatual);
                } else {
                    grupoatual.adicionarCampo(campo);
                }

            }
        }
        return grupocampos;
    }

}

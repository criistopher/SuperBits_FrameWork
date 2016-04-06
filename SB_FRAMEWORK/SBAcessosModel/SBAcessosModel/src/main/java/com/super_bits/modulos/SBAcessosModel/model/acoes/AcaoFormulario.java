/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author desenvolvedor
 */
@Entity
public class AcaoFormulario extends AcaoDoSistema implements ItfAcaoFormulario {

    private String xhtml;

    public AcaoFormulario() {
        super();
    }

    public static enum VARIAVEIS_ACAO_DO_SISTEMA {
        VIEW_NAO_IMPLEMENTADA,;

        @Override
        public String toString() {
            switch (this) {
                case VIEW_NAO_IMPLEMENTADA:
                    return "/resources/sistema/naoimplementado.xhtml";

                default:
                    return super.toString();

            }

        }
    }

    private ItfAcaoDoSistema acaoExectarFormulario;
    private List<CaminhoCampoReflexao> campos;

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

}

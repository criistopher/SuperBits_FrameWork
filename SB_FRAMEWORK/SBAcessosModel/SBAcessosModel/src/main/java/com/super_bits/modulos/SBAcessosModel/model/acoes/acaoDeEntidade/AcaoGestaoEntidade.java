/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSecundaria;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.view.InfoPagina;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
public class AcaoGestaoEntidade extends AcaoFormularioEntidade implements ItfAcaoGerenciarEntidade {

    @Transient
    private InfoPagina infoPagina;
    @Transient
    private boolean utilizarMesmoFormEditarInserir = true;

    @OneToMany(mappedBy = "acaoPrincipal")
    private List<AcaoSecundaria> acoesVinculadas;

    @Transient
    private List<ItfFabricaAcoes> enumAcoesVinculadas;

    public AcaoGestaoEntidade() {
        super();

    }

    public AcaoGestaoEntidade(ItfFabricaAcoes pFabrica, Class pClasse, String pXhtml) {

        super(pClasse, pFabrica, pXhtml);

        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
    }

    public ItfAcaoDoSistema criarAcaoSecundaria(FabTipoAcaoSistemaGenerica pAcaoGenerica) {

        if (true) {
            throw new UnsupportedOperationException("Aind não implementado");
        }
        ItfAcaoDoSistema novaAcao = null;
        String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(this.getClasseRelacionada());
        String diretorioFormulariosEntidade = "/site/" + this.getClass().getSimpleName();

        switch (pAcaoGenerica) {

        }

        return (ItfAcaoDoSistema) novaAcao;
    }

    public boolean isUtilizarMesmoFormEditarInserir() {
        return utilizarMesmoFormEditarInserir;
    }

    public void setUtilizarMesmoFormEditarInserir(boolean utilizarMesmoFormEditarInserir) {
        this.utilizarMesmoFormEditarInserir = utilizarMesmoFormEditarInserir;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return true;
    }

    public List<ItfAcaoSecundaria> getAcoesVinculadas() {

        return (List) acoesVinculadas;
    }

    public void setAcoesVinculadas(List<ItfAcaoSecundaria> acoesVinculadas) {
        this.acoesVinculadas = (List) acoesVinculadas;
    }

    public List<ItfFabricaAcoes> getEnumAcoesVinculadas() {
        return enumAcoesVinculadas;
    }

    public void setEnumAcoesVinculadas(List<ItfFabricaAcoes> enumAcoesVinculadas) {
        this.enumAcoesVinculadas = enumAcoesVinculadas;
    }

}

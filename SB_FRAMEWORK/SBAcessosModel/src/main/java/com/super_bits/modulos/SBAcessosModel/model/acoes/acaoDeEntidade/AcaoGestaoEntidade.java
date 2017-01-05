/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSecundaria;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Açao de Gestão de Dominio da Entidade"}, plural = "Ações de Dominio")
public class AcaoGestaoEntidade extends AcaoFormularioEntidade implements ItfAcaoGerenciarEntidade {

    @Transient
    private boolean utilizarMesmoFormEditarInserir = true;

    @OneToMany(mappedBy = "acaoPrincipal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AcaoSecundaria> acoesVinculadas;

    @Transient
    private List<ItfFabricaAcoes> enumAcoesVinculadas;

    public AcaoGestaoEntidade() {
        super();

    }

    public AcaoGestaoEntidade(ItfFabricaAcoes pFabrica, Class pClasse, String pXhtml) {

        super(pClasse, pFabrica, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
        try {
            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            InfoTipoAcaoGestaoEntidade acaoGestao = campo.getAnnotation(InfoTipoAcaoGestaoEntidade.class);
            setUtilizarMesmoFormEditarInserir(acaoGestao.utilizarMesmoFormEdicao());
        } catch (Throwable t) {

        }
    }

    public ItfAcaoDoSistema criarAcaoSecundaria(FabTipoAcaoSistemaGenerica pAcaoGenerica) {

        if (true) {
            throw new UnsupportedOperationException("Aind não implementado");
        }
        ItfAcaoDoSistema novaAcao = null;
        String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjetoPorAnotacaoInfoClasse(this.getClasseRelacionada());
        String diretorioFormulariosEntidade = "/site/" + this.getClass().getSimpleName();

        switch (pAcaoGenerica) {

        }

        return (ItfAcaoDoSistema) novaAcao;
    }

    public boolean isUtilizarMesmoFormEditarInserir() {
        return utilizarMesmoFormEditarInserir;
    }

    public final void setUtilizarMesmoFormEditarInserir(boolean utilizarMesmoFormEditarInserir) {
        this.utilizarMesmoFormEditarInserir = utilizarMesmoFormEditarInserir;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return true;
    }

    @Override
    public List<ItfAcaoSecundaria> getAcoesVinculadas() {
        return (List) acoesVinculadas;
    }

    @Override
    public void setAcoesVinculadas(List<ItfAcaoSecundaria> acoesVinculadas) {
        this.acoesVinculadas = (List) acoesVinculadas;
    }

    public List<ItfFabricaAcoes> getEnumAcoesVinculadas() {
        return enumAcoesVinculadas;
    }

    public void setEnumAcoesVinculadas(List<ItfFabricaAcoes> enumAcoesVinculadas) {
        this.enumAcoesVinculadas = enumAcoesVinculadas;
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return this;
    }

    public ItfAcaoDoSistema getSubAcaoByString(String pString) {
        try {
            for (ItfAcaoDoSistema acao : acoesVinculadas) {
                String nomeUrlDaAcao = UtilSBCoreStrings.makeStrUrlAmigavel(acao.getNomeAcao().toLowerCase());
                if (nomeUrlDaAcao
                        .contains(UtilSBCoreStrings.makeStrUrlAmigavel(pString.toLowerCase()))) {
                    return acao;
                }

            }
            for (ItfAcaoDoSistema acao : acoesVinculadas) {
                if (acao.getEnumAcaoDoSistema().toString().toLowerCase().contains(pString)) {
                    return acao;
                }
            }
            throw new UnsupportedOperationException("Sub ação de " + getEnumAcaoDoSistema().toString());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando ação por string na ação de gestão" + getEnumAcaoDoSistema().toString() + pString, t);
            throw new UnsupportedOperationException("Ação " + pString + " não localizada em" + getEnumAcaoDoSistema().toString());

        }

    }

}

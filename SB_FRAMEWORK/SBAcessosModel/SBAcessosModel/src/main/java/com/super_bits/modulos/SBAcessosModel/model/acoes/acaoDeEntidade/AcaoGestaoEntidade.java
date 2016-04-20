/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.view.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
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
    private final List<ItfParametroTela> parametros;

    public AcaoGestaoEntidade() {
        super();
        parametros = new ArrayList<>();
    }

    /**
     *
     * Adiciona um parametro para abertura desta tela de gestão
     *
     * @param pParametro
     */
    public void addParametro(ItfParametroTela pParametro) {
        parametros.add(pParametro);
    }

    public AcaoGestaoEntidade(ItfFabricaAcoes pFabrica, Class pClasse, String pXhtml) {

        super(pClasse, pFabrica, pXhtml);
        parametros = new ArrayList<>();
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

}

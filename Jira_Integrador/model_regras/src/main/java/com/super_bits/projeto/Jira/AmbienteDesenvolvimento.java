/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.modulosSB.SBCore.fabrica.UtilSBCoreFabrica;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AmbienteDesenvolvimento {

    private int taxaAdminsitrativa = 20;
    private int adicionalHoraExtra = 50;
    private int adicionalEntregaAntecipada = 40;
    private int valorPadraoHorasDiarias = 6;
    private List<DesenvolvedorProjetoSB> desenvolvedores;
    private final List<TipoProfissional> tiposProfissional = UtilSBCoreFabrica.getListaTodosRegistrosDaFabrica(FabTipoProfissional.class);

    private TipoProfissional getDetalhesProfissionalByTipo(FabTipoProfissional pTipo) {
        for (TipoProfissional p : tiposProfissional) {
            if (p.getFabrica().equals(pTipo)) {
                return p;
            }
        }
        return pTipo.getRegistro();
    }

    public TipoProfissional getDetalhesProfissionalTDD() {
        return getDetalhesProfissionalByTipo(FabTipoProfissional.ANALISTA_LOGICA_TDD);
    }

    public TipoProfissional getDetalhesProfissionalImplementacao() {
        return getDetalhesProfissionalByTipo(FabTipoProfissional.ANALISTA_IMPLEMENTACAO);
    }

    public TipoProfissional getDetalhesProfissionalRequistos() {
        return getDetalhesProfissionalByTipo(FabTipoProfissional.ANALISTA_REQUISITOS);
    }

    public TipoProfissional getDetalhesProfissionalTelas() {
        return getDetalhesProfissionalByTipo(FabTipoProfissional.ANALISTA_TELAS);
    }

    public int getTaxaAdminsitrativa() {
        return taxaAdminsitrativa;
    }

    public void setTaxaAdminsitrativa(int taxaAdminsitrativa) {
        this.taxaAdminsitrativa = taxaAdminsitrativa;
    }

    public int getAdicionalHoraExtra() {
        return adicionalHoraExtra;
    }

    public void setAdicionalHoraExtra(int adicionalHoraExtra) {
        this.adicionalHoraExtra = adicionalHoraExtra;
    }

    public int getAdicionalEntregaAntecipada() {
        return adicionalEntregaAntecipada;
    }

    public void setAdicionalEntregaAntecipada(int adicionalEntregaAntecipada) {
        this.adicionalEntregaAntecipada = adicionalEntregaAntecipada;
    }

    public int getValorPadraoHorasDiarias() {
        return valorPadraoHorasDiarias;
    }

    public void setValorPadraoHorasDiarias(int valorPadraoHorasDiarias) {
        this.valorPadraoHorasDiarias = valorPadraoHorasDiarias;
    }

    public List<DesenvolvedorProjetoSB> getDesenvolvedores() {
        return desenvolvedores;
    }

    public void setDesenvolvedores(List<DesenvolvedorProjetoSB> desenvolvedores) {
        this.desenvolvedores = desenvolvedores;
    }

}

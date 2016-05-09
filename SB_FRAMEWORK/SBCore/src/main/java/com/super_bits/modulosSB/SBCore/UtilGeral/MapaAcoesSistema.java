/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public abstract class MapaAcoesSistema implements ItfFabricaAcoes {

    private static Map<String, ItfAcaoDoSistema> acaoBynomeUnico;
    private static Map<Class, List<ItfAcaoDoSistema>> acoesByClasse;
    private static Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> acoesByModulo;

    public void makeMapaAcoesSistema() {

        for (Class fabrica : SBCore.getFabricasDeAcaoDoSistema()) {

            for (Object objAcao : fabrica.getEnumConstants()) {
                ItfFabricaAcoes fabricaAcao = (ItfFabricaAcoes) objAcao;
                ItfAcaoDoSistema acao = fabricaAcao.getAcaoDoSistema();
                acaoBynomeUnico.put(acao.getNomeUnico(), acao);
            }

        }

    }

    public List<ItfAcaoDoSistema> getAcoesByEntidade() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public List<ItfAcaoDoSistema> getAcoesByDominioEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public List<ItfAcaoController> getAcoesControllersByEntidade() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public List<ItfAcaoController> getAcoesControllerByEntidadeEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public List<ItfAcaoController> getAcoesListagemByEntidadeEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class getEntidadeDominio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

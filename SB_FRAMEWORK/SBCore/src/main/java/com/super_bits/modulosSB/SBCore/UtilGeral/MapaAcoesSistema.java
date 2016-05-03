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
import com.super_bits.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public abstract class MapaAcoesSistema {

    private static final Map<String, ItfAcaoDoSistema> ACAO_BY_NOME_UNICO = new HashMap<>();
    private static final Map<Class, List<ItfAcaoDoSistema>> ACOES_BY_CLASSE = new HashMap<>();
    private static final Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> ACOES_BY_MODULO = new HashMap<>();

    public static void makeMapaAcoesSistema() {

        for (Class fabrica : SBCore.getFabricasDeAcaoDoSistema()) {

            for (Object objAcao : fabrica.getEnumConstants()) {
                ItfFabricaAcoes fabricaAcao = (ItfFabricaAcoes) objAcao;
                ItfAcaoDoSistema acao = fabricaAcao.getAcaoDoSistema();

                List<ItfAcaoDoSistema> acoesDoModulo = ACOES_BY_MODULO.get(acao.getModulo());
                if (acoesDoModulo == null) {
                    acoesDoModulo = new ArrayList();
                }
                acoesDoModulo.add(acao);
                ACOES_BY_MODULO.put(acao.getModulo(), acoesDoModulo);
                System.out.println("Adicionando" + acao + " do modulo" + acao.getModulo());
                ACAO_BY_NOME_UNICO.put(acao.getNomeUnico(), acao);
                if (acao.isUmaAcaoDeEntidade()) {
                    ItfAcaoEntidade acaodeEntidade = (ItfAcaoEntidade) acao;
                    Class classeRelacionada = acaodeEntidade.getClasseRelacionada();
                    List<ItfAcaoDoSistema> acoesDaEntidade = ACOES_BY_CLASSE.get(classeRelacionada);
                    if (acoesDaEntidade == null) {
                        acoesDaEntidade = new ArrayList<>();
                        ACOES_BY_CLASSE.put(classeRelacionada, acoesDaEntidade);

                    }
                    acoesDaEntidade.add(acao);
                    System.out.println("Acao" + acao + "Adicionada em ações de entidade");
                }

            }

        }

    }

    public static List<ItfAcaoDoSistema> getAcoesByEntidade(Class pEntidade) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static List<ItfAcaoDoSistema> getAcoesByDominioEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static List<ItfAcaoController> getAcoesControllersByEntidade() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static List<ItfAcaoController> getAcoesControllerByEntidadeEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static List<ItfAcaoController> getAcoesListagemByEntidadeEModulo() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static ItfAcaoDoSistema getAcaoDoSistema(ItfFabricaAcoes pAcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfAcaoController getAcaoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Class getEntidadeDominio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomeModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

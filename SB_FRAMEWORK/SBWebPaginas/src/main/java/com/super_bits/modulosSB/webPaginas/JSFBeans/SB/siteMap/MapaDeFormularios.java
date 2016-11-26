/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.EstruturaDeFormulario;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class MapaDeFormularios {

    private static Map<String, EstruturaDeFormulario> mapaFormulariosByXhtmlPrincipal;
    private static Map<String, EstruturaDeFormulario> mapaFormulariosByAcaoGestao;
    private static Map<String, EstruturaDeFormulario> mapaFormulariosByNomeCompletoClasse;

    private static boolean criouEestrutura = false;

    public static void buildEstrutura() {

        if (criouEestrutura) {

        }
        criouEestrutura = true;

    }

    private static void buildEstrutura(Class pClasseFormulario) {

        if (mapaFormulariosByNomeCompletoClasse.get(pClasseFormulario.getName()) != null) {
            return;
        }
        EstruturaDeFormulario estrutura = new EstruturaDeFormulario(pClasseFormulario);

        mapaFormulariosByAcaoGestao.put(estrutura.getAcaoGestaoVinculada().getNomeUnico(), estrutura);
        mapaFormulariosByXhtmlPrincipal.put(
                estrutura.getAcaoGestaoVinculada().getXhtml(),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

        mapaFormulariosByNomeCompletoClasse.put(
                pClasseFormulario.getName(),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

    }

    public static EstruturaDeFormulario getEstruturaByClasseMB(String pClasse) {

        EstruturaDeFormulario est = mapaFormulariosByNomeCompletoClasse.get(pClasse);
        if (est == null) {
            throw new UnsupportedOperationException("A estrutura de pagnia vinculada a classe" + pClasse + " não foi encontrada, talvez ela não tenha sido injetada no SiteMapa (o nome enviado deve ser o nome completo e não SimpleName)");
        }
        return est;

    }

    public static EstruturaDeFormulario getEstruturaByNomeAcao(String pnomeAcao) {

        EstruturaDeFormulario est = mapaFormulariosByXhtmlPrincipal.get(pnomeAcao);
        if (est == null) {

            throw new UnsupportedOperationException("A estrutura de pagnia vinculada a ação" + pnomeAcao + " não foi encontrada,  utilize Acao.getNomeUnico, e certifique que está ação tenha sido declarada no core.");
        }
        return est;

    }

    public static String getUrlFormulario(ItfAcaoDoSistema pAcao, Object... parametros) {

        EstruturaDeFormulario strtura = mapaFormulariosByXhtmlPrincipal.get(pAcao.getNomeUnico());

        if (parametros != null) {
            for (Object pr : parametros) {
                strtura
            }
        }

    }

}

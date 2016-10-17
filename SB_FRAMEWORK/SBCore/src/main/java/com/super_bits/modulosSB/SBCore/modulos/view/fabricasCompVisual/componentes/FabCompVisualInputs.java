/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.UtilSBFabricaComponenteVisual;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabCompVisualInputs implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Texto Com FOrmatacao",
            xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "inputMascara.xhtml",
            classesCSS = "campoMascara",
            idHTMLObjetoPrincipal = "inputMaskara"
    )
    TEXTO_COM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto Sem Formatacao",
            xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "inputSimples.xhtml",
            classesCSS = "campoSimples",
            idHTMLObjetoPrincipal = "inputTexto"
    )
    TEXTO_SEM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto Grande com Formatacao", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "minimoEMaximo.xhtml",
            classesCSS = "campoGrande"
    )
    TEXTO_GRANDE_COM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto multiplas Linhas", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "descritivo.xhtml",
            classesCSS = "campoMultiplasLinhas"
    )
    TEXTMO_MULTIPLAS_LINHAS,
    @InfoComponenteVisual(nome = "Valor com mínimo e Maximo", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "minimoEMaximo.xhtml",
            classesCSS = "camoMinimoEMaximo"
    )
    NUMERO_MINIMO_MAXIMO,
    @InfoComponenteVisual(nome = "Senha", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "senha.xhtml",
            classesCSS = "campoSenha"
    )
    SENHA,
    @InfoComponenteVisual(nome = "Cep", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "cep.xhtml",
            classesCSS = "campoCEP"
    )
    CEP,
    @InfoComponenteVisual(nome = "Cor", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "cor.xhtml",
            classesCSS = "campoCor")
    COR,
    @InfoComponenteVisual(nome = "HTML", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "html.xhtml",
            classesCSS = "campoHTML")
    HTML,
    @InfoComponenteVisual(nome = "Quantidade", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "quantidade.xhtml",
            classesCSS = "campoQuantidade")
    QUANTIDADE,
    @InfoComponenteVisual(nome = "Moeda", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "moeda.xhtml",
            classesCSS = "campoMoeda")
    MOEDA,
    @InfoComponenteVisual(nome = "Email", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "email.xhtml",
            classesCSS = "campoEmail")
    EMAIL,
    @InfoComponenteVisual(nome = "Data", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "data.xhtml",
            classesCSS = "campoData")
    DATA,
    @InfoComponenteVisual(nome = "Data Hora", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "dataHora.xhtml",
            classesCSS = "campoDataHora")
    DATA_HORA,
    @InfoComponenteVisual(nome = "Ligado ou Desligado", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "verdadeiroOuFalso.xhtml",
            classesCSS = "campoLigadoDesligado")
    LIGADO_DESLIGADO,
    @InfoComponenteVisual(nome = "Entidade Simples", xhtmlJSF = FabCompVisualInputs.PASTA_CAMPOS + "entidadeSimples.xhtml",
            classesCSS = "campoEntidadeSimples")
    ENTIDADE_SIMPLES;
    public static final String PASTA_CAMPOS = "input/campo/";
    public static final String PASTA_SELETOR = "input/campo/";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.INPUT;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

    @Override
    public ComponenteVisualSB getRegistro() {
        return getComponente();
    }

}

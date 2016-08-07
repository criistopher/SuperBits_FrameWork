/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ComponenteVisualSB extends ItemSimples implements ItfComponenteVisualSB {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;

    private List<Object> parametros;

    private String xhtmlJSF;

    private String xhtmlAndroid;

    private String htmlWordPress;

    private String nomeComponente;

    private FabFamiliaCompVisual familia;

    private String caminhoIdHTMLObjetoPrincipal;

    private String ClasseCSS;

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public List<Object> getParametros() {
        return parametros;
    }

    public void setParametros(List<Object> parametros) {
        this.parametros = parametros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getXhtmlJSF() {
        return xhtmlJSF;
    }

    public void setXhtmlJSF(String xhtmlJSF) {
        this.xhtmlJSF = xhtmlJSF;
    }

    @Override
    public String getXhtmlAndroid() {
        return xhtmlAndroid;
    }

    public void setXhtmlAndroid(String xhtmlAndroid) {
        this.xhtmlAndroid = xhtmlAndroid;
    }

    @Override
    public String getHtmlWordPress() {
        return htmlWordPress;
    }

    public void setHtmlWordPress(String htmlWordPress) {
        this.htmlWordPress = htmlWordPress;
    }

    @Override
    public String getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return familia;
    }

    public void setFamilia(FabFamiliaCompVisual familia) {
        this.familia = familia;
    }

    @Override
    public String getCaminhoIdHTMLObjetoPrincipal() {
        return caminhoIdHTMLObjetoPrincipal;
    }

    public void setCaminhoIdHTMLObjetoPrincipal(String caminhoIdHTMLObjetoPrincipal) {
        this.caminhoIdHTMLObjetoPrincipal = caminhoIdHTMLObjetoPrincipal;
    }

    @Override
    public String getClasseCSS() {
        return ClasseCSS;
    }

    public void setClasseCSS(String ClasseCSS) {
        this.ClasseCSS = ClasseCSS;
    }

}

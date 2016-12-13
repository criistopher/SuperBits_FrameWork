/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

/**
 *
 * @author salvioF
 */
@InfoClasse(tags = "Detalhes do sistema", plural = "Sistemas")
public class SistemaWebAtual extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id = 0;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabCampos.URL)
    private String url;
    private String empresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        if (nome == null) {
            nome = SBCore.getNomeProjeto();
        }
        return nome;
    }

    public String getDescricao() {
        if (descricao == null) {
            SBWebPaginas.getTituloApp();
        }
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        if (url == null) {
            url = SBWebPaginas.getSiteHost();
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmpresa() {
        if (empresa == null) {
            empresa = SBCore.getInfoAplicacao().getCliente();
        }
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}

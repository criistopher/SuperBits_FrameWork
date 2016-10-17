/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.FabTipoBeanSBGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
@InfoClasse(plural = "Dados Estruturados", tags = {"Estrutura de Tabela"})
public class EstruturaDeEntidade extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    private List<String> listaEnum;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nomeEntidade;

    private List<EstruturaCampo> campos;

    private List<LigacaoMuitosParaUm> muitosParaUm;

    private List<LigacaoUmParaMuitos> umParaMuitos;

    private List<LigacaoMuitosParaMuitos> muitosParaMuitos;

    private List<String> tags;

    private String plural, icone;

    private FabTipoBeanSBGenerico tipoEntidade;

    private List<CalculoDeEntidade> calculos;

    private List<ListaDeEntidade> listas;

    private String descricao;

    public List<CalculoDeEntidade> getCalculos() {
        return calculos;
    }

    public void setCalculos(List<CalculoDeEntidade> calculos) {
        this.calculos = calculos;
    }

    public List<ListaDeEntidade> getListas() {
        return listas;
    }

    public void setListas(List<ListaDeEntidade> listas) {
        this.listas = listas;
    }

    public List<LigacaoMuitosParaMuitos> getMuitosParaMuitos() {
        return muitosParaMuitos;
    }

    public void setMuitosParaMuitos(List<LigacaoMuitosParaMuitos> muitosParaMuitos) {
        this.muitosParaMuitos = muitosParaMuitos;
    }

    public EstruturaDeEntidade() {
        campos = new ArrayList<>();
        muitosParaUm = new ArrayList<>();
        muitosParaMuitos = new ArrayList<>();
        umParaMuitos = new ArrayList<>();
        calculos = new ArrayList<>();
        listas = new ArrayList<>();

    }

    public String getNomeEntidade() {
        return nomeEntidade;

    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
        id = nomeEntidade.hashCode();
    }

    public FabTipoBeanSBGenerico getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(FabTipoBeanSBGenerico tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void adicionarTags(String pTag) {

        String[] array;
        array = pTag.split(",");

        for (int i = 0; i < array.length; i++) {

            if (tags == null) {

                tags = new ArrayList();
                tags.add(array[i]);

            } else {

                tags.add(array[i]);

            }
        }
    }

    public void adicionarEnum(String enums) {
        String[] array;
        array = enums.split(",");

        for (int i = 0; i < array.length; i++) {
            if (listaEnum == null) {
                listaEnum = new ArrayList();
                for (ListaDeEntidade entidade : listas) {
                    listaEnum.add(entidade.getNomeEnum());
                }
            } else {
                listaEnum.add(array[i]);
            }
        }
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public List<EstruturaCampo> getCampos() {
        return campos;
    }

    public void setCampos(List<EstruturaCampo> campos) {
        this.campos = campos;
    }

    public List<LigacaoMuitosParaUm> getMuitosParaUm() {
        return muitosParaUm;
    }

    public void setMuitosParaUm(List<LigacaoMuitosParaUm> muitosParaUm) {
        this.muitosParaUm = muitosParaUm;
    }

    public List<LigacaoUmParaMuitos> getUmParaMuitos() {
        return umParaMuitos;
    }

    public void setUmParaMuitos(List<LigacaoUmParaMuitos> umParaMuitos) {
        this.umParaMuitos = umParaMuitos;
    }

    public List<String> getListaEnum() {
        return listaEnum;
    }

    public void setListaEnum(List<String> listaEnum) {
        this.listaEnum = listaEnum;
    }

    @Override
    public String getIconeDaClasse() {
        return icone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

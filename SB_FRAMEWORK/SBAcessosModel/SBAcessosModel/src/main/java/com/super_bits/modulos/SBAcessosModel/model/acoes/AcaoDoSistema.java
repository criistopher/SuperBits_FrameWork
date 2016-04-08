/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação do Sistema"}, description = "Implementa a ação do Sistema")
@DiscriminatorColumn(name = "tipoAcaoDB")
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    private FabTipoAcaoSistema tipoAcao;
    protected FabTipoAcaoSistemaGenerica tipoAcaoGenerica;
    @Id
    protected int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    protected String nomeAcao;
    private String iconeAcao;
    private String cor;
    private String descricao;
    private boolean precisaPermissao;
    @ManyToOne
    private ModuloAcaoSistema modulo;
    private String idDescritivoJira;
    @Column(insertable = false, updatable = false)
    private String tipoAcaoDB;

    public AcaoDoSistema() {
        System.out.println("ATENÇÃO UMA AÇÃO DO SISTEMA SEM PARAMETROS NO CONSTRUTOR SÓ DEVE SER INSTANCIADA PELO HIBERNATE");
    }

    public AcaoDoSistema(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao) {
        try {
            if (ptipoAcao == null || pAcao == null) {
                throw new UnsupportedOperationException("O tipo de ação e a fabrica que originou a acao são obrigatórios");
            }

            tipoAcao = ptipoAcao;
            nomeAcao = pAcao.toString();
            descricao = "Descrição não documentada";
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, nomeAcao, t);
        }
    }

    public void copiarDadosDaAcao(ItfAcaoDoSistema pAcaoOriginal) {

    }

    public boolean isUmaAcaoGenerica() {
        return tipoAcaoGenerica != null;
    }

    public FabTipoAcaoSistema getTipoAcao() {
        return tipoAcao;
    }

    @Override
    public String getNomeAcao() {
        return nomeAcao;
    }

    @Override
    public String getIconeAcao() {
        return iconeAcao;

    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean isPrecisaPermissao() {
        return precisaPermissao;
    }

    @Override
    public void setId(int pId) {
        id = pId;
    }

    @Override
    public void setIconeAcao(String pIcone) {
        iconeAcao = pIcone;
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        modulo = (ModuloAcaoSistema) pmodulo;
    }

    @Override
    public boolean isConfigurado() {

        // verifica se o nome da ação foi alterado, ou se o icone foi setado
        return !(null == iconeAcao || !nomeAcao.equals(tipoAcao.toString()));

    }

    @Override
    public String getNomeEnumOriginal() {
        return tipoAcao.toString();
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return getTipoAcao();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return this.getClass().isAssignableFrom(ItfAcaoSecundaria.class);

    }

    @Override
    public ModuloAcaoSistema getModulo() {
        return modulo;
    }

    public void setModulo(ModuloAcaoSistema modulo) {
        this.modulo = modulo;
    }

    public void setTipoAcao(FabTipoAcaoSistema tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public void setTipoAcaoGenerica(FabTipoAcaoSistemaGenerica tipoAcaoGenerica) {
        this.tipoAcaoGenerica = tipoAcaoGenerica;
        TipoAcaoPadrao tipoAcaoGen = tipoAcaoGenerica.getRegistro();
        if (nomeAcao == null) {
            nomeAcao = tipoAcaoGen.getNomePadrao();
        }
        if (descricao == null) {
            nomeAcao = tipoAcaoGen.getDescricaoPadrao();
        }
        if (iconeAcao == null) {
            iconeAcao = tipoAcaoGen.getIconePadrao();
        }

    }

    @Override
    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecisaPermissao(boolean precisaPermissao) {
        this.precisaPermissao = precisaPermissao;
    }

    @Override
    public String getIdDescritivoJira() {
        return idDescritivoJira;
    }

    /**
     *
     * @param idDescritivoJira
     */
    @Override
    public void setIdDescritivoJira(String idDescritivoJira) {
        this.idDescritivoJira = idDescritivoJira;
    }

    @Override
    public String getNomeUnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        copiaDados(pAcaoDoSistema);
    }

    @Override
    public boolean isAcaoFormulario() {
        return this.getClass().isAssignableFrom(ItfAcaoFormulario.class);
    }

    public String getTipoAcaoDB() {
        return tipoAcaoDB;
    }

}

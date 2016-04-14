/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@DiscriminatorColumn(name = "tipoAcaoDB")
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    @Transient
    private FabTipoAcaoSistema tipoAcao;
    @Transient
    protected FabTipoAcaoSistemaGenerica tipoAcaoGenerica;
    @Transient
    private ItfFabricaAcoes enumAcao;

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

    public AcaoDoSistema(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao, FabTipoAcaoSistemaGenerica pAcaoGenerica) {
        this(ptipoAcao, pAcao);
        tipoAcaoGenerica = pAcaoGenerica;
    }

    public AcaoDoSistema(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao) {
        try {
            if (ptipoAcao == null || pAcao == null) {
                throw new UnsupportedOperationException("O tipo de ação e a fabrica que originou a acao são obrigatórios");
            }

            tipoAcao = ptipoAcao;
            nomeAcao = pAcao.toString();
            descricao = "Descrição não documentada";
            id = UtilSBController.gerarIDAcaoDoSistema(pAcao);
            enumAcao = pAcao;

            InfoModulo moduloanotacao = pAcao.getClass().getAnnotation(InfoModulo.class);
            ModuloAcaoSistema moduloDaAcao = new ModuloAcaoSistema();

            if (moduloDaAcao == null) {
                throw new UnsupportedOperationException("A Fabrica de ações não foi anodada com InfoModulo" + pAcao.getClass().getSimpleName());
            }

            moduloDaAcao.setNome(moduloanotacao.nomeDoModulo());
            moduloDaAcao.setId(pAcao.getClass().getSimpleName().hashCode());
            moduloDaAcao.setDescricao(moduloanotacao.descricao());
            setModulo(moduloDaAcao);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, nomeAcao, t);
        }
    }

    public void copiarDadosDaAcao(ItfAcaoDoSistema pAcaoOriginal) {

    }

    @Override
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
        try {
            return ((ItfAcaoSecundaria) this).getAcaoPrincipal() != null;

        } catch (Throwable t) {
            return false;
        }

    }

    @Override
    public ModuloAcaoSistema getModulo() {
        return modulo;
    }

    public final void setModulo(ModuloAcaoSistema modulo) {
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

    @Override
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
        return UtilSBController.gerarNomeUnicoAcaoDoSistema(enumAcao);
    }

    @Override
    public String getNome() {
        return nomeAcao;
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        copiaDados(pAcaoDoSistema);
    }

    @Override
    public boolean isAcaoFormulario() {
        return false;
    }

    public String getTipoAcaoDB() {
        return tipoAcaoDB;
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return tipoAcaoGenerica;
    }

    public boolean isAcaoGestaoDominio() {
        return this.getClass().isAssignableFrom(ItfAcaoGerenciarEntidade.class);
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        return enumAcao;
    }

}

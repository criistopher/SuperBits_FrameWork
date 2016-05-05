/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@Entity
@DiscriminatorColumn(name = "tipoAcaoDB")
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    @Enumerated(EnumType.STRING)
    private FabTipoAcaoSistema tipoAcao;
    @Enumerated(EnumType.STRING)
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
    private String nomeUnico;

    // Objetos não persistiveis
    @Transient
    private final List<ItfParametroTela> parametroTela = new ArrayList<>();
    @Transient
    private ItfFabricaAcoes enumAcao;
    private String nomeDominio;

    /**
     *
     * Adiciona um parametro para abertura desta tela de gestão
     *
     * @param pParametro
     */
    public void addParametro(ItfParametroTela pParametro) {
        parametroTela.add(pParametro);
    }

    /**
     *
     * Caso a execução desta ação modifique algum parametro de tela de exibição,
     * a lista dos parametros são adicionadas aqui
     *
     * @return a lista dos parametros que devem ser modificados pela ação
     */
    public List<ItfParametroTela> getParametrosTela() {
        return parametroTela;
    }

    public AcaoDoSistema() {
        //    System.out.println("ATENÇÃO UMA AÇÃO DO SISTEMA SEM PARAMETROS NO CONSTRUTOR SÓ DEVE SER INSTANCIADA PELO HIBERNATE");
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
            nomeUnico = UtilSBController.gerarNomeUnicoAcaoDoSistema(pAcao);

            setModulo(UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(pAcao));

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

        return nomeUnico;
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
    public boolean isUmaAcaoFormulario() {
        return false;
    }

    public String getTipoAcaoDB() {
        return tipoAcaoDB;
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return tipoAcaoGenerica;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        try {
            ItfAcaoEntidade teste = (ItfAcaoGerenciarEntidade) this;
        } catch (Throwable t) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        try {
            ItfAcaoEntidade teste = (ItfAcaoEntidade) this;
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isUmaAcaoController() {
        try {
            ItfAcaoController teste = (ItfAcaoController) this;
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        if (enumAcao == null) {
            enumAcao = SBCore.getFabricaByNOME_UNICO(nomeUnico);
        }

        return enumAcao;
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return false;
    }

    @Override
    public String getNomeDominio() {
        return nomeDominio;
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoParao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author sfurbino
 */
@Entity
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    @Id
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nomeAcao;

    private String iconeAcao;

    private String cor;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;

    private boolean precisaPermissao;

    private boolean acaoConfigurada;
    @Transient
    private ItfFabricaAcoes fabrica;

    private String xhtmlAcao;

    private boolean acessoAPagina;

    private int idMetodo;

    private boolean isAcaoPrincipal;

    @ManyToOne(targetEntity = ModuloAcaoSistema.class)
    private ModuloAcaoSistema modulo;

    @ManyToOne(targetEntity = AcaoDoSistema.class)
    private AcaoDoSistema acaoPrincipal;

    private String nomeOriginalEnum;

    private FabTipoAcaoParao tipoAcao;

    /**
     *
     * COnfigura o caminho para o XHTML de acordo com o nome da classe e tipo de
     * ação
     *
     * @param pClasse
     */
    public void ConfigurarXHTMLPorClasse(Class pClasse) {

        if (tipoAcao == null) {
            throw new UnsupportedOperationException("Para configurar um xhtml por classe é nessesário informar o tipo de ação antes " + nomeOriginalEnum);
        }

        xhtmlAcao = "/site/" + pClasse.getSimpleName() + "/" + tipoAcao.toString() + ".xhtml";

    }

    public AcaoDoSistema() {
        super();
    }

    private void atualizaAcaoConfigurada() {
        //TODO verificar todas variaveis que determinam se uma ação está configurada
        acaoConfigurada = true;
    }

    /**
     *
     * @param pAcaoDoSistema Fabrica Enum da fabrica de Ação
     * @param nomeAcao Nome da ação
     * @param iconeAcao Icone
     * @param cor cor
     * @param descricao descricao
     */
    public AcaoDoSistema(ItfFabricaAcoes pAcaoDoSistema, String nomeAcao, String iconeAcao, String cor, String descricao) {
        this.id = UtilSBController.gerarIDAcaoDoSistema(pAcaoDoSistema);
        this.nomeAcao = nomeAcao;
        this.iconeAcao = iconeAcao;
        this.cor = cor;
        this.descricao = descricao;
        this.nomeOriginalEnum = pAcaoDoSistema.toString();

        ModuloAcaoSistema moduloDaAcao = new ModuloAcaoSistema();
        ItfFabricaAcoes enumModulo = pAcaoDoSistema;
        fabrica = pAcaoDoSistema;
        InfoModulo anotacaoModulo = enumModulo.getClass().getAnnotation(InfoModulo.class);
        moduloDaAcao.setId(enumModulo.getClass().getSimpleName().hashCode());
        moduloDaAcao.setNome(anotacaoModulo.nomeDoModulo());
        moduloDaAcao.setDescricao(anotacaoModulo.descricao());
        modulo = moduloDaAcao;

    }

    /**
     *
     * @see ItfAcaoDoSistema#getNomeAcao()
     *
     * @return
     */
    @Override
    public String getNomeAcao() {

        return nomeAcao;
    }

    /**
     *
     * @see ItfAcaoDoSistema#getIconeAcao()
     * @return
     */
    @Override
    public String getIconeAcao() {

        return iconeAcao;
    }

    /**
     * @see ItfAcaoDoSistema#getCor()
     *
     * @return
     */
    @Override
    public String getCor() {

        return cor;
    }

    /**
     *
     * @see ItfAcaoDoSistema#getDescricao()
     *
     *
     * @return
     */
    @Override
    public String getDescricao() {

        return descricao;
    }

    @Override
    public boolean isPrecisaPermissao() {

        acaoConfigurada = true;
        return precisaPermissao;
    }

    public void setPrecisaPermissao(boolean precisaPermissao) {
        this.precisaPermissao = precisaPermissao;
    }

    @Override
    public String getXHTMLAcao() {
        return xhtmlAcao;
    }

    public void setXHTMLAcao(String urlAction) {
        this.xhtmlAcao = urlAction;
    }

    @Override
    public int getIdMetodo() {
        return idMetodo;
    }

    /**
     *
     * @param idMetodo
     */
    @Override
    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeAcao(String nomeAcao) {
        atualizaAcaoConfigurada();
        this.nomeAcao = nomeAcao;
    }

    public void setIconeAcao(String iconeAcao) {
        atualizaAcaoConfigurada();
        this.iconeAcao = iconeAcao;
    }

    public void setCor(String cor) {
        atualizaAcaoConfigurada();
        this.cor = cor;
    }

    public void setDescricao(String descricao) {
        atualizaAcaoConfigurada();
        this.descricao = descricao;
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        modulo = (ModuloAcaoSistema) pmodulo;
    }

    public boolean isAcessoAPagina() {
        return acessoAPagina;
    }

    public void setAcessoAPagina(boolean acessoAPagina) {
        this.acessoAPagina = acessoAPagina;
    }

    @Override
    public void setIcone(String pIcone) {
        atualizaAcaoConfigurada();
        iconeAcao = pIcone;
    }

    @Override
    public boolean isTipoAcaoDireta() {
        return getXHTMLAcao() == null;
    }

    @Override
    public boolean isTipoAcao() {
        return true;
    }

    @Override
    public boolean isTipoSessaoMenu() {
        return false;
    }

    @Override
    public boolean isConfigurado() {
        return acaoConfigurada;
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }

    @Override
    public void validarAcao(boolean validarNaoConfigurado) {

        if (acaoConfigurada || validarNaoConfigurado) {

            if (isAcaoPrincipal && acaoPrincipal != null) {
                throw new UnsupportedOperationException("Por ser uma ação principal, eta ação não pode conter uma ação principal" + fabrica.toString() + nomeAcao); //To change body of generated methods, choose Tools | Templates.
            }
            if (!isAcaoPrincipal && acaoPrincipal == null) {
                throw new UnsupportedOperationException("esta ação é uma ação secondária, portando deve ser configurada uma ação Principal a ela.." + fabrica.toString() + nomeAcao); //To change body of generated methods, choose Tools | Templates.
            }

            if (xhtmlAcao != null) {
                if (SBCore.getConfiguradorDePermissao().getMetodoByAcao(this) == null) {

                    throw new UnsupportedOperationException("esta ação não contem um formulário, "
                            + "sendo assim é provavel que esta  seja uma ação da camada controoler, "
                            + "porém não encontrei um metodo estatico anotado com esta ação no sistema  nas classes da camada controller"
                            + "Ao nomear a ação, não esqueça de começar pelo nome do objeto, em seguida o nome da ação"
                            + "" + nomeAcao); //To change body of generated methods, choose Tools | Templates.
                }
            }

        } else {
            System.out.println("[SBINFO]: A AÇÃO " + nomeAcao + " ainda não foi configurada");

        }
    }

    @Override
    public boolean isUmaAcaoPrincipal() {
        return isAcaoPrincipal;
    }

    @Override
    public void setIsAcaoPrincipal(Boolean pisAcaoPrincipal
    ) {
        isAcaoPrincipal = pisAcaoPrincipal;
        if (pisAcaoPrincipal) {
            acessoAPagina = true;
        }
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {
        acaoPrincipal = (AcaoDoSistema) pAcaoPrincipal;
    }

    @Override
    public String getNomeEnumOriginal() {
        return nomeOriginalEnum;
    }

    @Override
    public String getNomeUnico() {
        return getModulo().getNome() + "." + getNomeEnumOriginal();
    }

    @Override
    public FabTipoAcaoParao getTipoAcao() {
        return tipoAcao;
    }

    public void configurarAcaoPadrao(FabTipoAcaoParao pTipoAcao) {

        TipoAcaoPadrao tipoAcaoConfiguracao = (TipoAcaoPadrao) pTipoAcao.getRegistro();
        nomeAcao = tipoAcaoConfiguracao.getNomePadrao();
        iconeAcao = tipoAcaoConfiguracao.getIconePadrao();
        descricao = tipoAcaoConfiguracao.getDescricaoPadrao();
        tipoAcao = pTipoAcao;

    }

}

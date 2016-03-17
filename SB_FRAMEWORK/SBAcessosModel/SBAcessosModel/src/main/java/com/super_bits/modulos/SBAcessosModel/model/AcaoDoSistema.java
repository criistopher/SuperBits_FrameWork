/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoPadrao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Method;
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

    public static enum VARIAVEIS_ACAO_DO_SISTEMA {
        VIEW_NAO_IMPLEMENTADA,;

        @Override
        public String toString() {
            switch (this) {
                case VIEW_NAO_IMPLEMENTADA:
                    return "/resources/sistema/naoimplementado.xhtml";

                default:
                    return super.toString();

            }

        }
    }

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

    private boolean caminhoAutomaticoXHTML;

    private String idDescritivoJira;

    private int idMetodo;

    private boolean isAcaoPrincipal;

    @ManyToOne(targetEntity = ModuloAcaoSistema.class)
    private ModuloAcaoSistema modulo;

    @ManyToOne(targetEntity = AcaoDoSistema.class)
    private AcaoDoSistema acaoPrincipal;

    private String nomeOriginalEnum;

    private FabTipoAcaoPadrao tipoAcao;

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
        caminhoAutomaticoXHTML = true;
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

        if ((!acaoConfigurada || validarNaoConfigurado) || acaoConfigurada) {

            if (isAcaoPrincipal && acaoPrincipal != null) {
                throw new UnsupportedOperationException("Erro configurando a ação:" + fabrica.toString() + nomeAcao + ", Por ser uma ação principal, esta ação não pode conter uma ação principal "); //To change body of generated methods, choose Tools | Templates.
            }

            if (!isAcaoPrincipal) {
                if (acaoPrincipal == null) {
                    throw new UnsupportedOperationException("esta ação é uma ação secondária, portando deve ser configurada uma ação Principal a ela.." + fabrica.toString() + nomeAcao); //To change body of generated methods, choose Tools | Templates.
                }

                if (!acaoPrincipal.isAcaoPrincipal) {
                    throw new UnsupportedOperationException("A ação principal configurada para " + this.getNomeAcao() + " não é uma ação pricipal");
                }
            }

            if (iconeAcao == null) {
                throw new UnsupportedOperationException("Falta definir um ícone para a ação" + fabrica.toString());
            } else {

            }

            if (xhtmlAcao == null || xhtmlAcao == "") {
                Method metodo = SBCore.getConfiguradorDePermissao().getMetodoByAcao(this);
                if (metodo == null) {
                    System.out.println("teste");
                    throw new UnsupportedOperationException("esta ação não contem um formulário, portanto deve existir uma ação vinculada a ela na camada Controller "
                            + "nehum método foi encontrado,"
                            + "Dica: Ao nomear a ação, não esqueça de começar pelo nome do objeto em questão, em seguida o nome da ação"
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
    public void setIsAcaoPrincipal(boolean pisAcaoPrincipal) {
        isAcaoPrincipal = pisAcaoPrincipal;
        tipoAcao = FabTipoAcaoPadrao.GERENCIAR;
        if (pisAcaoPrincipal) {
            acessoAPagina = true;
        }
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {

        if (pAcaoPrincipal.getNomeUnico().equals(this.getNomeUnico())) {
            throw new UnsupportedOperationException("Erro configurando ação principal de " + this.getAcaoPrincipal() + "A ação principal não pode ser ela mesma.");
        }
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
    public FabTipoAcaoPadrao getTipoAcao() {
        return tipoAcao;
    }

    public void configurarAcaoPadrao(FabTipoAcaoPadrao pTipoAcao) {

        TipoAcaoPadrao tipoAcaoConfiguracao = (TipoAcaoPadrao) pTipoAcao.getRegistro();
        nomeAcao = tipoAcaoConfiguracao.getNomePadrao();
        iconeAcao = tipoAcaoConfiguracao.getIconePadrao();
        descricao = tipoAcaoConfiguracao.getDescricaoPadrao();
        tipoAcao = pTipoAcao;

    }

    @Override
    public boolean isCaminhoAutomaticoXHTML() {
        return caminhoAutomaticoXHTML;
    }

    public String getIdDescritivoJira() {
        return idDescritivoJira;
    }

    public void setIdDescritivoJira(String idDescritivoJira) {
        this.idDescritivoJira = idDescritivoJira;
    }

}

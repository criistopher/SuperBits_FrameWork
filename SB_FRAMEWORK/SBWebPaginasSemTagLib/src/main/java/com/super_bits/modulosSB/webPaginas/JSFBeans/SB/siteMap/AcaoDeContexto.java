/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDeContexto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.validacaoRegistro.CampoInvalido;
import java.lang.reflect.Field;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDeContexto implements ItfAcaoDeContexto {

    private ItfAcaoDoSistema acaoVinculada;
    private boolean permitidoExecutar;
    private boolean emPaginaDaGestao;

    @Override
    public boolean isPermitidoExecutar() {
        return permitidoExecutar;
    }

    @Override
    public boolean isEmPaginaDaGestao() {
        return emPaginaDaGestao;
    }

    public AcaoDeContexto(ItfAcaoDoSistema pAcaoDoSistema, FacesContext fc, ItfB_Pagina pPaginaAtual, Object... parametros) {

        try {
            acaoVinculada = pAcaoDoSistema;
            if (fc == null) {
                throw new UnsupportedOperationException("O contexto não foi enviado para criação de ação de contexto");
            }
            if (pPaginaAtual.getAcaoVinculada().getNomeUnico().equals(pAcaoDoSistema.getAcaoDeGestaoEntidade().getNomeUnico())) {
                emPaginaDaGestao = true;
            } else {
                emPaginaDaGestao = false;
            }

            permitidoExecutar = SBCore.getControleDeSessao().getSessaoAtual().isAcessoPermitido(pAcaoDoSistema);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Criando ação de contexto", t);

        }
    }

    @Override
    public void setNomeAcao(String pNome) {
        acaoVinculada.setNomeAcao(pNome);
    }

    @Override
    public String getNomeAcao() {
        return acaoVinculada.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoVinculada.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoVinculada.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoVinculada.getDescricao();
    }

    @Override
    public void setDescricao(String pDescricao) {
        acaoVinculada.setDescricao(pDescricao);
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoVinculada.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoVinculada.setId(pId);
    }

    @Override
    public void setIconeAcao(String pIcone) {
        acaoVinculada.setIconeAcao(pIcone);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoVinculada.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoVinculada.getModulo();
    }

    @Override
    public boolean isConfigurado() {
        return acaoVinculada.isConfigurado();
    }

    @Override
    public String getNomeUnico() {
        return acaoVinculada.getNomeUnico();
    }

    @Override
    public String getNomeEnumOriginal() {
        return acaoVinculada.getNomeEnumOriginal();
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return acaoVinculada.getTipoAcaoSistema();
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        return acaoVinculada.getEnumAcaoDoSistema();
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        acaoVinculada.configurarPropriedadesBasicas(pAcaoDoSistema);
    }

    @Override
    public String getIdDescritivoJira() {
        return acaoVinculada.getIdDescritivoJira();
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        acaoVinculada.setIdDescritivoJira(pIdJira);
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {
        acaoVinculada.setPrecisaPermissao(pPermissao);
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return acaoVinculada.getTipoAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return acaoVinculada.isUmaAcaoFormulario();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoVinculada.isTemAcaoPrincipal();
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return acaoVinculada.isUmaAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return acaoVinculada.isUmaAcaoGestaoDominio();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return acaoVinculada.isUmaAcaoSessaoMenu();
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoVinculada.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoVinculada.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoVinculada.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoVinculada.getComoFormulario();
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        return acaoVinculada.getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoVinculada.getComoGestaoEntidade();
    }

    @Override
    public ItfAcaoController getComoController() {
        return acaoVinculada.getComoController();
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        return acaoVinculada.getComoSecundaria();
    }

    @Override
    public ItfAcaoControllerEntidade getComoControllerEntidade() {
        return acaoVinculada.getComoControllerEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return acaoVinculada.getAcaoDeGestaoEntidade();
    }

    @Override
    public String getNomeUnicoSlug() {
        return acaoVinculada.getNomeUnicoSlug();
    }

    @Override
    public String getNomeCurto() {
        return acaoVinculada.getNomeCurto();
    }

    @Override
    public String getNome() {
        return acaoVinculada.getNome();
    }

    @Override
    public String getIconeDaClasse() {
        return acaoVinculada.getIconeDaClasse();
    }

    @Override
    public String getXhtmlVisao() {
        return acaoVinculada.getXhtmlVisao();
    }

    @Override
    public int getId() {
        return acaoVinculada.getId();
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoVinculada.uploadFoto(pTipo, pRecurso);
    }

    @Override
    public String getImgPequena() {
        return acaoVinculada.getImgPequena();
    }

    @Override
    public void configIDPeloNome() {
        acaoVinculada.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return acaoVinculada.getNomeDoObjeto();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        acaoVinculada.adicionarItemNaLista(nomeDaLista);
    }

    @Override
    public boolean isTemCampoAnotado(FabCampos pCampo) {
        return acaoVinculada.isTemCampoAnotado(pCampo);
    }

    @Override
    public void setNome(String pNome) {
        acaoVinculada.setNome(pNome);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {
        return acaoVinculada.getCamposInstaciadosInvalidos();
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return acaoVinculada.getCampoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(CaminhoCampoReflexao pNome) {
        return acaoVinculada.getCampoByCaminhoCampo(pNome);
    }

    @Override
    public Object getValorCampoByCaminhoCampo(CaminhoCampoReflexao pNome) {
        return getValorCampoByCaminhoCampo(pNome);
    }

    @Override
    public List<CaminhoCampoReflexao> getEntidadesVinculadas() {
        return acaoVinculada.getEntidadesVinculadas();
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        return acaoVinculada.getBeanSimplesPorNomeCampo(pNomeCampo);
    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(CaminhoCampoReflexao pCaminho) {
        return acaoVinculada.getItemPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(CaminhoCampoReflexao pCaminho) {
        return acaoVinculada.getListaPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<CampoInvalido> getCamposInvalidos() {
        return acaoVinculada.getCamposInvalidos();
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return acaoVinculada.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return acaoVinculada.getNomeCampo(pInfocampo);
    }

}

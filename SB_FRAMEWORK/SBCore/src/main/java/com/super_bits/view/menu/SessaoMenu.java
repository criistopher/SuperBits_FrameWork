/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.menu;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * Aramezena uma sessaão do sistema, contendo ações e outras sessoes
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 08/01/2016
 * @version 1.0
 *
 */
public class SessaoMenu implements ItfAcaoDoSistema {

    private ItfAcaoDoSistema acaoSessao;
    public List<ItfAcaoDoSistema> acoes;
    public List<ItfAcaoDoSistema> sessoes;

    public List<ItfAcaoDoSistema> getAcoes() {
        return acoes;
    }

    public void addAcao(ItfAcaoDoSistema pAcao) {
        if (pAcao == null) {
            throw new UnsupportedOperationException("A ação adicionada na sessao não pode ser nula");
        }
        acoes.add(pAcao);

    }

    public SessaoMenu(ItfAcaoDoSistema pAcaoSessao) {
        acaoSessao = pAcaoSessao;
        acaoSessao.setIcone("fa fa-list-alt");
    }

    @Override
    public String getNomeAcao() {
        return acaoSessao.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoSessao.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoSessao.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoSessao.getDescricao();
    }

    @Override
    public String getXHTMLAcao() {
        return acaoSessao.getXHTMLAcao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoSessao.getModulo();
    }

    @Override
    public int getIdMetodo() {
        return acaoSessao.getIdMetodo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoSessao.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoSessao.setId(pId);
    }

    @Override
    public void setIdMetodo(int pID) {
        acaoSessao.setId(pID);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoSessao.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public String getImgPequena() {
        return acaoSessao.getImgPequena();
    }

    @Override
    public String getNomeCurto() {
        return acaoSessao.getNomeCurto();
    }

    @Override
    public int getId() {
        return acaoSessao.getId();
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return getNomeCampo(pInfocampo);
    }

    @Override
    public Field getCampo(FabCampos pInfoCampo) {
        return getCampo(pInfoCampo);
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoSessao.uploadFoto(pTipo, pRecurso);
    }

    @Override
    public void setIcone(String pIcone) {
        acaoSessao.setIcone(pIcone);
    }

}

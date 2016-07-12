package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanPermisionado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.util.Date;
import java.util.List;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

public abstract class EntidadeNormal extends EntidadeSimples implements ItfBeanNormal, ItfBeanPermisionado {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public EntidadeNormal() {
        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_LONGO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_DESCRITIVO, "Lorem ipsum dolor smodo accumsan. Morbi egestas gravida mattis. Suspendisse luctus est a elit gravida imperdiet. Nam in lectus at odio ultricies pretium non a nibh. Suspendisse quis libero sem, sit amet egestas libero. Vestibulum gravida ipsum volutpat nisi dapibus accumsan. Pellentesque imperdiet convallis mollis. Fusce tincidunt diam tempor quam lacinia dapibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget ipsum at mauris commodo tempus a in nulla. Aliquam erat volutpat. Aliquam non sem a orci tincidunt aliquet. Proin eu gravida odio. Suspendisse potenti."));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_ATIVO_INATIVO), false);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_DATAALTERACAO), false);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_DATAALTERACAO), false);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_USUARIO_ALTERACAO), false);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_USUARIO_INSERCAO), false);

    }

    @Override
    public String getNomeLongo() {
        camposEsperados.getCampo(FabCampos.AAA_NOME_LONGO).setValorPadrao(getNomeCurto());
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_LONGO);

    }

    @Override
    public String getDescritivo() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_DESCRITIVO);
    }

    @Override
    public String getImgGrande() {

        //return (String) GetValor(TC.IMG_GRANDE);
        return UtilSBPersistenciaArquivosDeEntidade.getURLImagem(this, FabCampos.IMG_GRANDE);
    }

    @Override
    public String getImgMedia() {
        //return (String) GetValor(TC.IMG_MEDIA);
        return UtilSBPersistenciaArquivosDeEntidade.getURLImagem(this, FabCampos.IMG_MEDIA);
    }

    @Override
    public List<String> getGaleria() {
        return UtilSBPersistenciaArquivosDeEntidade.getURLImagens(this, "galeria");
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDataHoraAlteracao() {
        return (Date) getValorByTipoCampoEsperado(FabCampos.REG_DATAALTERACAO);
    }

    @PostPersist
    private void configLogPersistencia() {

        if (getCampo(FabCampos.REG_DATAINSERCAO) != null) {
            setValorByTipoCampoEsperado(FabCampos.REG_DATAINSERCAO, new Date());
        }
        if (getCampo(FabCampos.REG_USUARIO_INSERCAO) != null) {
            setValorByTipoCampoEsperado(FabCampos.REG_USUARIO_INSERCAO, SBCore.getUsuarioLogado());
        }

    }

    @PreUpdate
    private void configLogUpdate() {

        if (getCampo(FabCampos.REG_DATAALTERACAO) != null) {
            setValorByTipoCampoEsperado(FabCampos.REG_DATAINSERCAO, new Date());
        }

        if (getCampo(FabCampos.REG_USUARIO_ALTERACAO) != null) {
            setValorByTipoCampoEsperado(FabCampos.REG_USUARIO_ALTERACAO, SBCore.getUsuarioLogado());
        }

    }

    @Override
    public Date getDataHoraInsercao() {
        return (Date) getValorByTipoCampoEsperado(FabCampos.REG_DATAINSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioInsersao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabCampos.REG_USUARIO_INSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioAlteracao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabCampos.REG_USUARIO_ALTERACAO);
    }

    @Override
    public void setAtivo(boolean pAtivo) {
        setValorByTipoCampoEsperado(FabCampos.REG_ATIVO_INATIVO, pAtivo);
    }

    @Override
    public boolean isAtivo() {
        return (boolean) getValorByTipoCampoEsperado(FabCampos.REG_ATIVO_INATIVO);
    }

    @Override
    public void setNomeLongo(String pnomeLongo) {
        setValorByTipoCampoEsperado(FabCampos.NOME_COMPLETO, this);
    }

    @Override
    public void setDescritivo(String pDescritivo) {
        setValorByTipoCampoEsperado(FabCampos.AAA_DESCRITIVO, this);
    }

}

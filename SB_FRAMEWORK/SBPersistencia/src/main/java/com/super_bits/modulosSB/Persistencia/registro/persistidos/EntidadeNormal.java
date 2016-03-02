package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.util.Date;
import java.util.List;

public abstract class EntidadeNormal extends EntidadeSimples implements ItfBeanNormal {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public EntidadeNormal(Class<?> pClasseModelo) {
        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.IMG_GRANDE, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_LONGO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_DESCRITIVO, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet augue arcu. Mauris faucibus urna et ante commodo accumsan. Morbi egestas gravida mattis. Suspendisse luctus est a elit gravida imperdiet. Nam in lectus at odio ultricies pretium non a nibh. Suspendisse quis libero sem, sit amet egestas libero. Vestibulum gravida ipsum volutpat nisi dapibus accumsan. Pellentesque imperdiet convallis mollis. Fusce tincidunt diam tempor quam lacinia dapibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget ipsum at mauris commodo tempus a in nulla. Aliquam erat volutpat. Aliquam non sem a orci tincidunt aliquet. Proin eu gravida odio. Suspendisse potenti."));

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAtivo() {
        return (Boolean) getValorByTipoCampoEsperado(FabCampos.RET_ATIVO_INATIVO);
    }

}

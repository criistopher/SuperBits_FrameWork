package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanNormal;
import java.util.List;

public abstract class ItemNormal extends ItemSimples implements ItfBeanNormal {

    public ItemNormal(Class<?> pClasseModelo) {
        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_CURTO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.IMG_GRANDE, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_LONGO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_DESCRITIVO, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet augue arcu. Mauris faucibus urna et ante commodo accumsan. Morbi egestas gravida mattis. Suspendisse luctus est a elit gravida imperdiet. Nam in lectus at odio ultricies pretium non a nibh. Suspendisse quis libero sem, sit amet egestas libero. Vestibulum gravida ipsum volutpat nisi dapibus accumsan. Pellentesque imperdiet convallis mollis. Fusce tincidunt diam tempor quam lacinia dapibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget ipsum at mauris commodo tempus a in nulla. Aliquam erat volutpat. Aliquam non sem a orci tincidunt aliquet. Proin eu gravida odio. Suspendisse potenti.")
        );

    }

    public String getNomeLongo() {
        camposEsperados.getCampo(FabCampos.AAA_NOME_LONGO).setValorPadrao(getNomeCurto());
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_LONGO);

    }

    public String getDescritivo() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_DESCRITIVO);
    }

    public String getImgGrande() {

        return (String) getValorByTipoCampoEsperado(FabCampos.IMG_GRANDE);
        //	return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_GRANDE);
    }

    public String getImgMedia() {

        return (String) getValorByTipoCampoEsperado(FabCampos.IMG_MEDIA);
        //return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_MEDIA);
    }

    public List<String> getGaleria() {
        return null;// OrganizadorDeArquivos.getURLImagens(this, "galeria");
    }

}

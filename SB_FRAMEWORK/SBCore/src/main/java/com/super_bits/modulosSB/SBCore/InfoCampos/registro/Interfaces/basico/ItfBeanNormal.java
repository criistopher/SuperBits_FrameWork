package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import java.util.Date;
import java.util.List;

public interface ItfBeanNormal extends ItfBeanSimples {

    public String getNomeLongo();

    public void setNomeLongo(String pnomeLongo);

    public String getDescritivo();

    public void setDescritivo(String pDescritivo);

    public String getImgGrande();

    public String getImgMedia();

    public List<String> getGaleria();

    public boolean isAtivo();

    public void setAtivo(boolean pAtivo);

}

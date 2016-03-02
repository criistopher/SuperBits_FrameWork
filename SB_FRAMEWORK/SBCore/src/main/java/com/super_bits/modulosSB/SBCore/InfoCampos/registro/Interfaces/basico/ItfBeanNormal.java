package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import java.util.Date;
import java.util.List;

public interface ItfBeanNormal extends ItfBeanSimples {

    public String getNomeLongo();

    public String getDescritivo();

    public String getImgGrande();

    public String getImgMedia();

    public List<String> getGaleria();

    public Date getDataHoraAlteracao();

    public Date getDataHoraInsercao();

    public ItfUsuario getUsuarioAlteracao();

    public ItfUsuario getUsuarioInsersao();

    public boolean isAtivo();

    public void setAtivo(boolean pAtivo);

}

package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

public interface ItfUsuario extends ItfBeanEndereco {

    public String getEmail();

    public String getSenha();

    public String getNome();

    public ItfGrupoUsuario getGrupo();

    public void setGrupo(ItfGrupoUsuario grupo);
}

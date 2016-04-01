package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import java.util.Date;
import java.util.List;

public interface ItfUsuario extends ItfBeanEndereco {

    public String getEmail();

    public String getSenha();

    public String getNome();

    public ItfGrupoUsuario getGrupo();

    public void setGrupo(ItfGrupoUsuario grupo);

    public List<ItfGrupoUsuario> getGruposAdicionais();

    public Date getDataCadastro();

    public String getApelido();

    public boolean isAtivo();

    public String getTipoUsuario();

}

/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Salvio
 */
public class UsuarioSistemaRoot extends ItemGenerico implements ItfUsuario, Serializable {

    @Override
    public String getEmail() {
        return "root@superBits.com";
    }

    @Override
    public String getSenha() {
        return "senh@Screta";
    }

    @Override
    public String getComplemento() {
        return "um lugar no espaço tempo";
    }

    @Override
    public String getCEP() {
        return null;
    }

    @Override
    public String getTelefone() {
        return "66666666";
    }

    @Override
    public String getNomeLongo() {
        return "root";
    }

    @Override
    public String getDescritivo() {
        return "Usuário utilizado para realizar operações do sistema";
    }

    @Override
    public String getImgGrande() {
        return "/img/SBFW/rootGrade.jpg";

    }

    @Override
    public String getImgMedia() {
        return "/img/SBFW/rootMedio.jpg";
    }

    @Override
    public List<String> getGaleria() {
        return new ArrayList<>();
    }

    @Override
    public String getImgPequena() {
        return "/img/SBFW/rootPequena.jpg";
    }

    @Override
    public String getNomeCurto() {
        return "root";
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getNome() {
        return "root";
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }

    @Override
    public ItfGrupoUsuario getGrupo() {
        return new GrupoUsuariosDoSistema();
    }

    @Override
    public void setGrupo(ItfGrupoUsuario grupo) {
        System.out.println("Este usuário é estático e não pode ser manipulado..");
    }

    @Override
    public List<ItfGrupoUsuario> getGruposAdicionais() {
        return new ArrayList<>();
    }

    @Override
    public Date getDataCadastro() {
        return new Date();
    }

    @Override
    public String getApelido() {
        return "O sistema";
    }

    @Override
    public boolean isAtivo() {
        return true;
    }

}

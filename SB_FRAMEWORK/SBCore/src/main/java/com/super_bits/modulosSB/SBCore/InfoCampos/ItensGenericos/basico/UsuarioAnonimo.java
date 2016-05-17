/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemNormal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Usuário anonimo
 *
 * TODO: Criar pacote Objetos SuperBits, mover Itens Genericos e interfaces,
 * separando por assunto
 *
 * @author Salvio
 */
public class UsuarioAnonimo extends ItemNormal implements ItfUsuario, Serializable {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO)
    private Date dataHoraAlteracao;
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO)
    private Date dataHoraInsersao;
    @InfoCampo(tipo = FabCampos.REG_USUARIO_INSERCAO)
    private ItfUsuario usuarioInsercao;
    @InfoCampo(tipo = FabCampos.REG_USUARIO_ALTERACAO)
    private ItfUsuario usuarioAlteracao;

    public UsuarioAnonimo() {
        super(UsuarioAnonimo.class);
    }

    @Override
    public String getEmail() {
        return "anonimo@naoTenhoEmail.com.br";
    }

    @Override
    public String getSenha() {
        return "";
    }

    @Override
    public String getComplemento() {
        return "";
    }

    @Override
    public String getCEP() {
        return "00000000";
    }

    @Override
    public String getTelefone() {
        return "";
    }

    @Override
    public String getNomeLongo() {
        return "Usuário não Identificado";
    }

    @Override
    public String getDescritivo() {
        return "usuário anoniomo, não identificado no sistema";
    }

    @Override
    public String getImgGrande() {
        return "/img/usuarioanonimo.jpg";
    }

    @Override
    public String getImgMedia() {
        return "/img/usuarioanonimo.jpg";
    }

    @Override
    public List<String> getGaleria() {
        return new ArrayList<>();
    }

    @Override
    public String getImgPequena() {
        return "/img/usuarioanonimo.jpg";
    }

    @Override
    public String getNomeCurto() {
        return "Anônimo";
    }

    @Override
    public int getId() {
        return getNome().hashCode();
    }

    @Override
    public String getNome() {
        return "Anônimo";
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
        System.out.println("Este usuário é estático e não pode ser manipulado");
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
        return "Anonymous";
    }

    @Override
    public boolean isAtivo() {
        return true;
    }

    @Override
    public String getTipoUsuario() {
        return "UsuarioDoSistema";
    }

}

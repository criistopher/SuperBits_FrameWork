/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.Controller.anotacoes.InfoAcao;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 *
 *
 *
 * @author Salvio
 */
@Entity
public class AcessoSB extends EntidadeSimples implements ItfAcesso, Serializable {

    @Id
    private int id;

    private String nomeAcesso;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nomeAmigavel;

    private TIPO_AUTENTICACAO tipoAutenticacao;

    private String descricao;

    private boolean padraoLiberado;

    @Transient
    private List<ItfUsuario> listaTodosUsuarios;

    @Transient
    private List<ItfGrupoUsuario> listaTodosGruposUsuarios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Permitido_Usuarios",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<UsuarioSB> usuariosPermitidos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Negado_Usuarios",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<UsuarioSB> usuariosNegados;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Permitido_Grupos",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<GrupoUsuarioSB> gruposPermitidos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Negado_Grupos",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<GrupoUsuarioSB> gruposNegados;

    public AcessoSB() {
        super();
        this.gruposPermitidos = new ArrayList<>();
        this.gruposNegados = new ArrayList<>();
        this.usuariosPermitidos = new ArrayList<>();
        this.usuariosNegados = new ArrayList<>();
    }

    public AcessoSB(Method pMetodo) {
        this.gruposPermitidos = new ArrayList<>();
        this.gruposNegados = new ArrayList<>();
        this.usuariosPermitidos = new ArrayList<>();
        this.usuariosNegados = new ArrayList<>();

        String pNomeAcesso = pMetodo.getDeclaringClass().getSimpleName() + "." + pMetodo.getName();
        InfoAcao info = pMetodo.getAnnotation(InfoAcao.class);
        if (info != null) {
            nomeAmigavel = info.nomeAmigavel();
        } else {
            nomeAmigavel = pNomeAcesso;
        }

        this.loadByID(pNomeAcesso.hashCode());
        if (id == 0) {
            //System.out.println("cadastrando Acao ID:" + pNomeAcesso.hashCode());
            id = pNomeAcesso.hashCode();
            nomeAcesso = pNomeAcesso;
            System.out.println("cadastrando Acao ID:" + getId());
            UtilSBPersistencia.mergeRegistro(this);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setGruposPermitidos(List<GrupoUsuarioSB> gruposPermitidos) {
        this.gruposPermitidos = gruposPermitidos;
    }

    public void setGruposNegados(List<GrupoUsuarioSB> gruposNegados) {
        this.gruposNegados = gruposNegados;
    }

    public boolean isPadraoLiberado() {
        return padraoLiberado;
    }

    public void setPadraoLiberado(boolean padraoLiberado) {
        this.padraoLiberado = padraoLiberado;
    }

    public void addUsuarioPermitido(UsuarioSB pUsuario) {
        usuariosPermitidos.add(pUsuario);
    }

    public void addGrupoPermitido(GrupoUsuarioSB pGrupoPermitido) {
        gruposPermitidos.add(pGrupoPermitido);
    }

    @Override
    public String getNomeAcesso() {
        return nomeAcesso;
    }

    public void setNomeAcesso(String pNomeAcesso) {
        nomeAcesso = pNomeAcesso;
    }

    @Override
    public String getDescricaoAcesso() {
        return descricao;
    }

    @Override
    public boolean padraoLiberado() {
        return padraoLiberado;
    }

    @Override
    public List<ItfUsuario> getUsuariosPermitidos() {
        return (List) usuariosPermitidos;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposPermitidos() {
        return (List) gruposPermitidos;
    }

    public String getNomeAmigavel() {
        return nomeAmigavel;
    }

    public void setNomeAmigavel(String nomeAmigavel) {
        this.nomeAmigavel = nomeAmigavel;
    }

    @Override
    public List<ItfUsuario> getUsuariosDisponiveis() {
        try {
            listaTodosUsuarios = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class);

            List<ItfUsuario> usuariosDisponiveis = new ArrayList<>();
            usuariosDisponiveis = listaTodosUsuarios;

            if (getUsuariosNegados().isEmpty() == false) {
                usuariosDisponiveis.removeAll(getUsuariosNegados());
            }

            if (getUsuariosPermitidos().isEmpty() == false) {
                usuariosDisponiveis.removeAll(getUsuariosPermitidos());
            }

            return usuariosDisponiveis;
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: getUsuariosDisponiveis \n\n", exception);
        }

        return null;
    }

    @Override
    public List<ItfUsuario> getUsuariosNegados() {
        return (List) usuariosNegados;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposNegados() {
        return (List) gruposNegados;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposDisponiveis() {
        try {
            listaTodosGruposUsuarios = (List<ItfGrupoUsuario>) UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class);

            List<ItfGrupoUsuario> grupoUsuariosDisponiveis = new ArrayList<>();
            grupoUsuariosDisponiveis = listaTodosGruposUsuarios;

            if (getGruposNegados().isEmpty() == false) {
                grupoUsuariosDisponiveis.removeAll(getGruposNegados());
            }

            if (getGruposPermitidos().isEmpty() == false) {
                grupoUsuariosDisponiveis.removeAll(getGruposPermitidos());
            }

            return grupoUsuariosDisponiveis;
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: getGruposUsuarioDisponiveis \n\n", exception);
        }

        return null;
    }

    @Override
    public TIPO_AUTENTICACAO getTipoAutenticacao() {
        return tipoAutenticacao;
    }
}

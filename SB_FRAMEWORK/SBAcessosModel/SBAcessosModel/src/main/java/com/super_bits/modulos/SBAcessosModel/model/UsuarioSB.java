/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 *
 *
 * @author Salvio
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoUsuario")
@InfoClasse(tags = {"Usuários"})
public class UsuarioSB extends EntidadeNormal implements ItfUsuario, Serializable {

    @Id
    @Generated(GenerationTime.NEVER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Nome", descricao = "Nome do Usuário")
    @NotNull
    private String nome;
    @Column(nullable = false, unique = true)
    @NotNull
    @InfoCampo(tipo = FabCampos.EMAIL, label = "E-mail", descricao = "email do usuário")
    private String email;
    @NotNull
    @Column(unique = true)
    @InfoCampo(label = "Usuário")
    private String apelido;

    @InfoCampo(tipo = FabCampos.SENHA, label = "Senha")
    @NotNull
    private String senha;
    private String complemento;
    @InfoCampo(tipo = FabCampos.LCCEP, label = "CEP", descricao = "CEP do usuário")
    private String CEP;

    @InfoCampo(tipo = FabCampos.TELEFONE_CELULAR, label = "Telefone", descricao = "Telefone celular de contato do usuário")
    private String telefone;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoUsuario;

    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "Data Cadastro", descricao = "Data de cadastramento do usuário")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @InfoCampo(tipo = FabCampos.REG_ATIVO_INATIVO, label = "Status", descricao = "Status do usuário (ativo/inativo)")
    private boolean ativo = true;

    @ManyToOne(targetEntity = GrupoUsuarioSB.class)
    @NotNull
    @InfoCampo(tipo = FabCampos.LOOKUP, label = "Grupo", descricao = "Grupo de usuário que irá permitir acesso as funcionalidades")
    private GrupoUsuarioSB grupo;

    @InfoCampo(tipo = FabCampos.LOOKUP, label = "Grupos Adicionais", descricao = "Grupos do usuário")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    private List<GrupoUsuarioSB> gruposAdicionais;

    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO, label = "Data/Hora Alteração", descricao = "Data e hora de alteração do perfil do usuário")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAlteracao;

    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO, label = "Data/Hora Inserção", descricao = "Data e hora de inserção do perfil do usuário")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInsersao;

    @InfoCampo(tipo = FabCampos.REG_USUARIO_INSERCAO, label = "Usuário Inserção", descricao = "Usuário que fez a inserção de outro na base de dados")
    @ManyToOne
    private UsuarioSB usuarioInsercao;
    @InfoCampo(tipo = FabCampos.REG_USUARIO_ALTERACAO, label = "Usuário Alteração", descricao = "Usuário que fez a alteração de outro na base de dados")
    @ManyToOne
    @OneToMany(mappedBy = "usuarioAlteracao")
    private UsuarioSB usuarioAlteracao;

    public UsuarioSB() {
        super(UsuarioSB.class);
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public ItfGrupoUsuario getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(ItfGrupoUsuario grupo) {
        this.grupo = (GrupoUsuarioSB) grupo;
    }

    @Override
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
    }

    @Override
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setGrupo(GrupoUsuarioSB grupo) {
        this.grupo = grupo;
    }

    @PrePersist
    public void configuracoesInsert() {
        dataCadastro = new Date();
    }

    @PreUpdate
    public void ajustaConfiguracoes() {
        System.out.println("Ajustando Configuracoes para salvar");
        if (dataCadastro == null) {
            dataCadastro = new Date();
        }
        if (email == null) {
            if (getGrupo() != null) {
                email = apelido + "@" + getGrupo().getNome();

            }
        }
        if (apelido == null) {
            if (email != null) {
                apelido = email;
            }
        }

        if (apelido != null) {
            if (nome == null) {
                nome = apelido;
            }
        }

        if (nome != null) {
            if (apelido == null) {
                apelido = nome;
            }
        }
        if (getGruposAdicionais() != null && getGrupo() != null) {

            if (!getGruposAdicionais().contains(getGrupo())) {
                getGruposAdicionais().add(grupo);
            }
        }

    }

    @Override
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposAdicionais() {
        return (List) gruposAdicionais;
    }

    public void setGruposAdicionais(List<GrupoUsuarioSB> gruposAdicionais) {
        this.gruposAdicionais = gruposAdicionais;
    }

    public boolean isGrupoPrincipal(GrupoUsuarioSB pGrupo) {
        return (pGrupo == grupo);
    }

    @Override
    public Date getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(Date dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public Date getDataHoraInsersao() {
        return dataHoraInsersao;
    }

    public void setDataHoraInsersao(Date dataHoraInsersao) {
        this.dataHoraInsersao = dataHoraInsersao;
    }

    public UsuarioSB getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(UsuarioSB usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    public UsuarioSB getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(UsuarioSB usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

}

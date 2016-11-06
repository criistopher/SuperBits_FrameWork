/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.registro.exemploImplementacao;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author desenvolvedor
 */
public class ExemploCorporativoCompleto {

    @Id
    @InfoCampo(tipo = FabCampos.ID)
    @GeneratedValue()
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;

//propriedades Entidade Normal
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descritivo;
    @InfoCampo(tipo = FabCampos.REG_ATIVO_INATIVO)
    private boolean ativo;
    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInsercao;
    //renomear
    @ManyToOne()
    private ItfUsuario usuarioAlteracao;
    @ManyToOne()
    private ItfUsuario usuarioInsercao;

    // propriedades Entidade Contato
    @InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL)
    private String telefone;

    @ManyToOne(targetEntity = Localizacao.class)
    private Localizacao localizacao;

    // Propriedades contato corporativo
    @InfoCampo(tipo = FabCampos.SITE)
    private String site;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enderecos_secundarios_ContatoCorporativo<-substituaissoPeloNomeDaTabela",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<Localizacao> enderecosSecundarios;

}

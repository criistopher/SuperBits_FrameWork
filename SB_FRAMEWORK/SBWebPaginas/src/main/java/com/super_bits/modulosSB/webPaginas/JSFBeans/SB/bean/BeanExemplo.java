/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.bean;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Bairro;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeContatoCorporativo;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localidade;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.lang.reflect.Field;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/12/2015
 * @version 1.0
 *
 */
public class BeanExemplo
        extends EntidadeContatoCorporativo {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.EMAIL)
    private String email;
    @InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL)
    private String telefone;
    @InfoCampo(tipo = FabCampos.TELEFONE_CELULAR)
    private String celular;
    @InfoCampo(tipo = FabCampos.TELEFONE_FIXO_INTERNACIONAL)
    private String telInternacional;

    @InfoCampo(tipo = FabCampos.CPF)
    @NotNull
    private String cpf;
    @InfoCampo(tipo = FabCampos.CNPJ)
    @NotNull
    private String cnpj;
    @InfoCampo(tipo = FabCampos.INSCRICAO_ESTADUAL)
    @NotNull
    private String inscricaoEstadual;
    @InfoCampo(tipo = FabCampos.INSCRIACAO_MUNICIPAL)
    @NotNull
    private String inscricaoMunicipal;

    @InfoCampo(tipo = FabCampos.COR)
    @NotNull
    private String cor;
    @InfoCampo(Mask = "")
    private String MascaraEspecial;

    @InfoCampo(tipo = FabCampos.HTML)
    private String HTML;
    @InfoCampo(tipo = FabCampos.SITE, obrigatorio = true)

    private String site;

    @InfoCampo(tipo = FabCampos.URL)
    private String url;

    @InfoCampo(tipo = FabCampos.SENHA)
    private String senha;
    @InfoCampo(tipo = FabCampos.QUANTIDADE)
    private int quantidade;
    @InfoCampo(tipo = FabCampos.PERCENTUAL)
    private Double porcentagem;
    @InfoCampo(tipo = FabCampos.MOEDA_REAL)
    private Double dinheiro;
    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO)
    private Date dataAlteracao;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String apelido;
    @InfoCampo(tipo = FabCampos.AAA_NOME_LONGO)
    private String nome;

    @InfoCampo(tipo = FabCampos.LCComplemeto)
    private String complemento;
    @InfoCampo(tipo = FabCampos.LCCEP, obrigatorio = true)
    @NotNull
    private String cep;

    private Localizacao localizacao;

    public BeanExemplo() {
        super(BeanExemplo.class);
    }

}

/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfRegiao;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 12/12/2015
 * @version 1.0
 *
 */
@Entity
@InfoClasse(tags = {"Regiao", "Regioes"})
public class Regiao extends EntidadeSimples implements ItfRegiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Nome regiao")
    private String nomeRegiao;

    @ManyToMany
    private List<Cidade> cidades;
    @ManyToMany
    private List<Bairro> bairros;

    private String sigla;

    private int quantidadeCidades;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criadoEm;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alteradoEM;

    @NotNull
    @InfoCampo(tipo = FabCampos.VERDADEIRO_FALSO, label = "Status")
    private boolean ativo;

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getQuantidadeCidades() {
        return quantidadeCidades;
    }

    public void setQuantidadeCidades(int quantidadeCidades) {
        this.quantidadeCidades = quantidadeCidades;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Date getAlteradoEM() {
        return alteradoEM;
    }

    public void setAlteradoEM(Date alteradoEM) {
        this.alteradoEM = alteradoEM;
    }

}

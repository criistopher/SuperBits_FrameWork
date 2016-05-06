/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.Ips;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ips"})
public class Ips extends EntidadeNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 15, nullable = false)
    @NotNull
    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Inicial", descricao = "Inicio da faixa de ip's autorizados")
    private String inicialFaixa;

    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "Final", descricao = "Final da faixa de ip's autorizados")
    @Column(length = 15, nullable = false)
    @NotNull
    private String finalFaixa;

    @NotNull
    private String dns;

    @ManyToOne(targetEntity = TipoIp.class)
    private TipoIp tipo;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getInicialFaixa() {
        return inicialFaixa;
    }

    public void setInicialFaixa(String inicialFaixa) {
        this.inicialFaixa = inicialFaixa;
    }

    public String getFinalFaixa() {
        return finalFaixa;
    }

    public void setFinalFaixa(String finalFaixa) {
        this.finalFaixa = finalFaixa;
    }

    @NotNull
    public Ips() {
        super(Ips.class);
    }

    public TipoIp getTipo() {
        return tipo;
    }

    public void setTipo(TipoIp tipo) {
        this.tipo = tipo;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

}

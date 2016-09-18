/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90
 */
package com.super_bits.modulos.SBAcessosModel.model.Ips;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Tipo ip"}, plural = "Tipos de Ip")
class TipoIp extends EntidadeSimples {

    @Id
    @GeneratedValue
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private String descricao;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

}

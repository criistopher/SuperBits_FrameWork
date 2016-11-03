/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvioF
 */
@InfoClasse(tags = {"Sistema Atual"}, plural = "Sistemas")
public class SistemaAtualInfo extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id = 0;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;

    private String empresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        if (nome == null) {
            nome = SBCore.getNomeProjeto();
        }
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresa() {
        if (empresa == null) {
            empresa = SBCore.getInfoAplicacao().getCliente();
        }
        return empresa;
    }

}

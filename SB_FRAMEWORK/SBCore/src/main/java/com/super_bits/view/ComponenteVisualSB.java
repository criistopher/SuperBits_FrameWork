/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ComponenteVisualSB extends ItemSimples {

    private String nome;
    private String descricao;
    private List<Object> parametros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Object> getParametros() {
        return parametros;
    }

    public void setParametros(List<Object> parametros) {
        this.parametros = parametros;
    }

}

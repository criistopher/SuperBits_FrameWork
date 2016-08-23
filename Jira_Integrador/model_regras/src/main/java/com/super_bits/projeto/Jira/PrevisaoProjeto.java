/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoProjeto {

    private List<PrevisaoEntidade> entidadesPrevistas;
    private List<PrevisaoGestaoEntidade> gestoesPrevista;

    private List<TarefaSuperBits> tarefasProximaVersao;
    private List<TarefaSuperBits> todasTarefas;

    private AmbienteDesenvolvimento ambienteDesenvolvimento;

    public AmbienteDesenvolvimento getAmbienteDesenvolvimento() {
        return ambienteDesenvolvimento;
    }

    public List<PrevisaoEntidade> getEntidadesPrevistas() {
        return entidadesPrevistas;
    }

    public void setEntidadesPrevistas(List<PrevisaoEntidade> entidadesPrevistas) {
        this.entidadesPrevistas = entidadesPrevistas;
    }

    public List<PrevisaoGestaoEntidade> getGestoesPrevista() {
        return gestoesPrevista;
    }

    public void setGestoesPrevista(List<PrevisaoGestaoEntidade> gestoesPrevista) {
        this.gestoesPrevista = gestoesPrevista;
    }

}

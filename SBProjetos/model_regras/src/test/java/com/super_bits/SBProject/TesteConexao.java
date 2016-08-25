/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.SBProject;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.sbProjetos.Model.ConfigPersistenciaSBProject;
import com.super_bits.sbProjetos.Model.Requisito;
import com.super_bits.sbProjetos.configAppp.ConfigCoreInomeProjetoI;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class TesteConexao {

    @Before
    public void configuracao() {
        SBCore.configurar(new ConfigCoreInomeProjetoI());
        SBPersistencia.configuraJPA(new ConfigPersistenciaSBProject());
    }

    @Test
    public void listarRequisitos() {
        List<Requisito> requisitos = (List) UtilSBPersistencia.getListaTodos(Requisito.class);
        System.out.println(requisitos);
        for (Requisito rq : requisitos) {
            System.out.println(rq.getNome());

        }
    }

}

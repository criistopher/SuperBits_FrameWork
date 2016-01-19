/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.configSBFW.acessos;

import com.super_bits.sbProjetos.AcoesInomeProjetoI;
import com.super_bits.modulos.SBAcessosModel.model.AcessoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.BeansInterface.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.ConfigAcessoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 * @author Salvio
 */
public class ConfigAcessosOld extends ConfigAcessoAbstratoSBCore {

    private static Class[] getClasses() {
        Class[] classes = {AcoesInomeProjetoI.class};
        return classes;
    }

    public ConfigAcessosOld() {
        super(getClasses());
    }

    public void cadastraUsuarios() {

    }

    @Override
    public List<ItfAcesso> configuraAcessos() {

        //Exemplo busca acessos no banco de dados
        List<ItfAcesso> resp = new ArrayList<>();

        try {
            resp = (List<ItfAcesso>) UtilSBPersistencia.getListaTodos(AcessoSB.class);
        } catch (Exception e) {
            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "Erro obtendo lista de acessos", e);
        }
        return resp;

    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {

        List<ItfUsuario> resposta = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class
        );

        return resposta;
    }

}
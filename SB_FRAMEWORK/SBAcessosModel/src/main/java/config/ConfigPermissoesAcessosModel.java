/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.MenuSBFW;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.MenusDaSessao;

/**
 *
 * @author desenvolvedor
 */
public class ConfigPermissoesAcessosModel extends ConfigPermissoesAcessoModelAbstrato {

    private static Class[] getClasses() {
        Class[] classes = {};
        return classes;
    }

    public ConfigPermissoesAcessosModel() {
        super(getClasses());
    }

    @Override
    public MenusDaSessao definirMenu(ItfGrupoUsuario pGrupo) {
        return new MenusDaSessao(new MenuSBFW());
    }

}

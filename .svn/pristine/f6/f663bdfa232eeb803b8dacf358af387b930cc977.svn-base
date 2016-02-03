/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.configSBFW.acessos;

import com.super_bits.config.webPaginas.acoes.AcoesWebPaginasDemo;
import com.super_bits.modulos.SBAcessosModel.UtilSBAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.AcessoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.BeansInterface.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.ConfigAcessoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSB;
import java.util.List;
import javax.persistence.EntityManager;

public class ConfigAcessos extends ConfigAcessoAbstratoSBCore {

    private static Class[] classesControllers = {AcoesWebPaginasDemo.class};
    private static EntityManager conexaoBanco;

    public ConfigAcessos() {
        super(classesControllers);

    }

    @Override
    public List<ItfAcesso> configuraAcessos() {

        UtilSBAcessosModel.criarAcessosNoBanco();
        try {
            List<ItfAcesso> listaAcessos = (List<ItfAcesso>) UtilSBPersistencia.getListaTodos(AcessoSB.class, conexaoBanco);
            return listaAcessos;
        } catch (Throwable e) {
            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "Erro obtendo lista de acessos", e);
            return null;
        }

    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {
        return (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class);
    }

}

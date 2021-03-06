package com.super_bits.modulosSB.webPaginas.controller.listenners;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ItfInicioFimAppWP;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *
 * @author Salvio
 */
public class ContextoWebPaginas implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando contexto");
        try {
            Class classeInicioFim = UtilSBCoreReflexao.getClasseQueEstendeIsto(ItfInicioFimAppWP.class, "com.super_bits.config.webPaginas");

            System.out.println("A classe de inicio e fim de contexto encontrada foi" + classeInicioFim.getName());

            ItfInicioFimAppWP inicio = (ItfInicioFimAppWP) classeInicioFim.newInstance();

            ConfiguradorCoreDeProjetoWebWarAbstrato.contextoDoServlet = sce.getServletContext();
            inicio.inicio();
            // ServiceLoader<ItfInicioFimAppWP> services = ServiceLoader.load(ItfInicioFimAppWP.class);

            // if (!services.iterator().hasNext()) {
            //        FabErro.PARA_TUDO.paraSistema("classe de especificada em service para início e fim não foi encontrada", null);
            //    }
            //   ItfInicioFimAppWP inicio = services.iterator().next().getValue();
            //   inicio.inicio();
        } catch (Throwable ex) {
            FabErro.PARA_TUDO.paraSistema("Erro carregando Classe de inicio e fim de contexto", ex);
        }

        // PgUtil paginautil = new PgUtil();
        //paginautil.getCores().carregarCores();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Finalizando contexto");
    }

}

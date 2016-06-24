/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.configSBFW.acessos;

import com.super_bits.modulos.SBAcessosModel.model.AcessoSB;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.BeansInterface.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.ConfigAcessoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSB;
import com.super_bits.sbProjetos.Model.Cliente;
import com.super_bits.sbProjetos.Model.Desenvolvedor;
import com.super_bits.sbProjetos.Model.GerenteProjeto;
import com.super_bits.sbProjetos.Model.Projeto;
import com.super_bits.sbProjetos.Model.StatusRequisito;
import com.super_bits.sbProjetos.SBProjectController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 * @author Salvio
 */
public class ConfigAcessos extends ConfigAcessoAbstratoSBCore {

    private static Class[] getClasses() {
        Class[] classes = {SBProjectController.class};
        return classes;
    }

    public ConfigAcessos() {
        super(getClasses());
    }

    public void cadastraUsuarios() {

    }

    @Override
    public List<ItfAcesso> configuraAcessos() {

        boolean criatudo = false;

        if (criatudo) {

            UtilSBPersistencia.mergeRegistro(StatusRequisito.futuro);
            UtilSBPersistencia.mergeRegistro(StatusRequisito.proximaVersao);
            UtilSBPersistencia.mergeRegistro(StatusRequisito.sujestao);
            UtilSBPersistencia.mergeRegistro(StatusRequisito.finalizado);

            Cliente superBits = new Cliente();
            superBits.setNome("Super Bits");
            superBits.setId(1);

            Cliente sphera = new Cliente();

            sphera.setNome("Sphera Security");
            sphera.setId(2);
            System.out.println("CLIENTE CADASTRADO");
            UtilSBPersistencia.mergeRegistro(superBits);
            UtilSBPersistencia.mergeRegistro(sphera);

            Cliente teste = new Cliente();
            teste.loadByID(2);

            GerenteProjeto sergioOku = new GerenteProjeto();

            GerenteProjeto julio = new GerenteProjeto();
            GerenteProjeto joaquim = new GerenteProjeto();
            Desenvolvedor alline = new Desenvolvedor();
            Desenvolvedor eduardo = new Desenvolvedor();
            Desenvolvedor marcos = new Desenvolvedor();
            Desenvolvedor salvio = new Desenvolvedor();

            GrupoUsuarioSB gerenteDeProjeto = new GrupoUsuarioSB();
            gerenteDeProjeto.setId(1);
            gerenteDeProjeto.setNome("Gerente de Pronjeto");
            gerenteDeProjeto.setDescricao("Usuarios com função de gerenciar projeto");
            gerenteDeProjeto.adcionaUsuario(sergioOku);

            UtilSBPersistencia.mergeRegistro(gerenteDeProjeto);

            GrupoUsuarioSB desenvolvedor = new GrupoUsuarioSB();
            desenvolvedor.setNome("Desenvolvedor");

            UtilSBPersistencia.mergeRegistro(desenvolvedor);

            sergioOku.setNome("Sergio Oku");
            sergioOku.setEmail("sergio.oku@spherasecurity.com");
            sergioOku.setId(1);
            UtilSBPersistencia.mergeRegistro(sergioOku);

            julio.setNome("José Júlio");
            julio.setEmail("julio@spherasecurity.com");
            julio.setId(2);
            UtilSBPersistencia.mergeRegistro(julio);

            joaquim.setNome("Joaquim Marques");
            joaquim.setEmail("jmarques@spherasecurity.com");
            joaquim.setId(3);
            UtilSBPersistencia.mergeRegistro(joaquim);

            alline.setNome("Alline Basile");
            alline.setEmail("alline@spherasecurity.com");
            alline.setId(4);
            UtilSBPersistencia.mergeRegistro(alline);

            marcos.setNome("Marcos Vinicius");
            marcos.setEmail("mvrcorreia@outlook.com");
            marcos.setId(5);
            UtilSBPersistencia.mergeRegistro(marcos);

            eduardo.setNome("Eduardo Lima");
            eduardo.setEmail("eduarddollima@yahoo.com.br");
            eduardo.setId(6);
            UtilSBPersistencia.mergeRegistro(eduardo);

            salvio.setNome("Salvio Furbino");
            salvio.setEmail("salviof@gmail.com");
            salvio.setId(7);
            Projeto proj = new Projeto();
            proj.loadByID(1);

            salvio.getProjetos().add(proj);
            UtilSBPersistencia.mergeRegistro(salvio);

            int i = 1;
            String nomeController = "SBProjectController.";
            AcessoSB criarRquisito = new AcessoSB(getMetodoByName(nomeController + "criarRequisito", true));
            criarRquisito.addGrupoPermitido(gerenteDeProjeto);
            criarRquisito.addUsuarioPermitido(sergioOku);
            criarRquisito.addUsuarioPermitido(joaquim);
            criarRquisito.addUsuarioPermitido(alline);
            criarRquisito.addUsuarioPermitido(julio);
            criarRquisito.addUsuarioPermitido(salvio);
            UtilSBPersistencia.mergeRegistro(criarRquisito);

            AcessoSB promoverRequisito = new AcessoSB(getMetodoByName(nomeController + "promoverRequisito", true));
            promoverRequisito.addUsuarioPermitido(sergioOku);
            promoverRequisito.addUsuarioPermitido(salvio);
            System.out.println("Adcionando salvio:" + salvio.getId() + "nome" + salvio.getNome());

            promoverRequisito.setPadraoLiberado(false);
            UtilSBPersistencia.mergeRegistro(promoverRequisito);

            AcessoSB rebaixarRequisito = new AcessoSB(getMetodoByName("SBProjectController.rebaixarRequisito", true));
            SBProjectRebaixarRequisito:
            rebaixarRequisito.addUsuarioPermitido(salvio);
            rebaixarRequisito.addUsuarioPermitido(sergioOku);

            UtilSBPersistencia.mergeRegistro(rebaixarRequisito);
            AcessoSB aprovarRequisito = new AcessoSB(getMetodoByName("SBProjectController.aprovarRequisito", true));
            aprovarRequisito.addUsuarioPermitido(salvio);
            UtilSBPersistencia.mergeRegistro(aprovarRequisito);

            AcessoSB finalizarRequisito = new AcessoSB(getMetodoByName("SBProjectController.finalizarRequisito", true));
            finalizarRequisito.addUsuarioPermitido(salvio);
            UtilSBPersistencia.mergeRegistro(finalizarRequisito);
        }
        get List  <ItfAcesso > resp = new ArrayList<>();
        try {
            resp = (List<ItfAcesso>) UtilSBPersistencia.getListaTodos(AcessoSB.class);
        } catch (Exception e) {
            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "Erro obtendo lista de acessos", e);
        }
        return resp;

    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {

        List<ItfUsuario> resposta = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class);

        return resposta;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreDeveloper;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.Controller.anotacoes.InfoAcesso;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSB;
import com.super_bits.sbProjetos.Model.Cliente;
import com.super_bits.sbProjetos.Model.ConfigPersistenciaSBProject;
import com.super_bits.sbProjetos.Model.Desenvolvedor;
import com.super_bits.sbProjetos.Model.Projeto;
import com.super_bits.sbProjetos.Model.Projeto_Desenvolvedor;
import com.super_bits.sbProjetos.Model.Requisito;
import com.super_bits.sbProjetos.Model.StatusRequisito;
import com.super_bits.sbProjetos.Model.Trabalho;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * Funções estaticas de acesso ao banco de dados da camada Controladora
 *
 * @author Salvio
 */
public class SBProjectController extends ControllerAppAbstratoSBCore {

    public static void configCoreEPersistenciaBasicos() {
        SBCore.configurar(new ConfigCoreDeveloper());
        SBPersistencia.configuraJPA(new ConfigPersistenciaSBProject());
    }

    public static Trabalho iniciarTrabalho(Desenvolvedor pDesenvolvedor, Requisito requisito) {

        try {
            Trabalho trabalhoAtual = trabalhoAtivoPorDesenvolvedor(pDesenvolvedor);
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("dfjskdfjsldkjfklsdklfjgslkdjfg", ItfCentralMensagens.TP_MENSAGEM.ALERTA);

            if (trabalhoAtual == null) {
                System.out.println("criou Novo");
                trabalhoAtual = new Trabalho(requisito, pDesenvolvedor);
                trabalhoAtual = (Trabalho) UtilSBPersistencia.mergeRegistro(trabalhoAtual);
            }
            return trabalhoAtual;
        } catch (Exception e) {

            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "Erro controler iniciando trabalho", e);
            return null;
        }

    }

    public static Trabalho finalizarTrabalho(Trabalho ptrabalhoAtual) {
        ptrabalhoAtual.setFim(new Date());
        EntityManager em = UtilSBPersistencia.getNovoEM();
        try {
            em.getTransaction().begin();
            Requisito req = ptrabalhoAtual.getRequisito();
            req.atualizarHorasTrabalhadadas();
            UtilSBPersistencia.mergeRegistro(req, em);
            return (Trabalho) UtilSBPersistencia.mergeRegistro(ptrabalhoAtual, em);

        } catch (Exception e) {
            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "Erro Finalizando trabalho", e);
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
                em = null;
            }
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();

            }
        }
        return ptrabalhoAtual;
    }

    public static Trabalho AtualizarHistorioDeTrabalho(Trabalho pTrabalho, String novoHistorico) {

        pTrabalho.setHistorico(pTrabalho.getHistorico() + "\n" + novoHistorico);
        return (Trabalho) UtilSBPersistencia.mergeRegistro(pTrabalho);
    }

    public static Trabalho trabalhoAtivoPorDesenvolvedor(Desenvolvedor pDev) {
        EntityManager em = null;
        try {
            em = UtilSBPersistencia.getNovoEM();
            Object trabalhoAtivo = UtilSBPersistencia.getRegistroByJPQL("from Trabalho   where desenvolvedor.id=" + pDev.getId() + " and fim is null ", Trabalho.class, em);
            if (trabalhoAtivo == null) {
                System.out.println("Nenhum trabalho ativo encontrado");
                return null;
            }
            System.out.println("Trabalho Ativo=" + trabalhoAtivo);

            return (Trabalho) trabalhoAtivo;
        } finally {
            if (em != null) {
                //  em.close();
            }
        }
    }

    public static List<Requisito> getRequisitosDisponiveis(Desenvolvedor pDesenvolvedor) {
        return new ArrayList<>();
    }

    @Override
    protected List<ItfAcesso> carregaAcessos() {
        return SBCore.getConfiguradorDeAcessos().configuraAcessos();
    }

    @InfoAcesso(padraoBloqueado = true, nomeAmigavel = "Promover Requisito")
    public static boolean promoverRequisito(Requisito req) {

        System.out.println("verificando autorizacao para promover");

        if (!autorizar()) {
            return false;
        }
        if (req.getStatusRequisito().getId() == StatusRequisito.finalizado.getId()) {
            SBCore.getCentralDeMensagens().enviaMensagemSistema("O requisisto já foi promovido", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }

        if (req.getHorasEstimadas() < 1) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Especifique as Horas Estimadas", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }

        if (req.getStatusRequisito().getId() == StatusRequisito.proximaVersao.getId()) {
            finalizarRequisito(req);
            return false;
        }

        try {
            StatusRequisito novoStatus = new StatusRequisito();
            System.out.println("Setando novo status" + novoStatus.getDescricao());
            novoStatus.loadByID(req.getStatusRequisito().getId() + 1);
            if (novoStatus.getId() > 0) {
                req.setStatusRequisito(novoStatus);
                UtilSBPersistencia.mergeRegistro(req);
                System.out.println("promovido");

            }
            return true;
        } catch (Exception e) {
            SBCore.RelatarErro(ErroSB.TIPO_ERRO.ALERTA_PROGRAMADOR, "erro promovendo requisito", e);
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Erro tentando promover o Requisito", ItfCentralMensagens.TP_MENSAGEM.ERRO);
            return false;
        }

    }

    @InfoAcesso(padraoBloqueado = true, nomeAmigavel = "Aprovar Requisito")
    public static void aprovarRequisito(Requisito req) {
        if (!autorizar()) {
            return;
        }
        req.setStatusRequisito(StatusRequisito.futuro);
        UtilSBPersistencia.mergeRegistro(req);
    }

    public static void rebaixarRequisito(Requisito req) {
        System.out.println("verificando autorizacao para rebaixar");

        if (!autorizar()) {
            return;
        }

        if (req.getStatusRequisito().getId() == StatusRequisito.sujestao.getId()) {
            SBCore.getCentralDeMensagens().enviaMensagemSistema("Requisito já se encontra como sujestão", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return;
        }

        StatusRequisito novoStatus = new StatusRequisito();

        novoStatus.loadByID(req.getStatusRequisito().getId() - 1);
        System.out.println("Setando novo status" + novoStatus.getDescricao());
        if (novoStatus.getId() > 0) {
            req.setStatusRequisito(novoStatus);
            UtilSBPersistencia.mergeRegistro(req);
            System.out.println("Rebaixado");
        }

    }

    public static boolean criarRequisito(String descricao, String motivacao, String pNome, Projeto projeto) {
        if (descricao == null || motivacao == null || projeto == null) {
            SBCore.getCentralDeMensagens().enviaMensagemSistema("Preencha todos os parametros", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }
        System.out.println("criando requisito:" + descricao + "motivacao" + motivacao + "projeto:" + projeto);
        if (!autorizar()) {
            return false;
        }
        System.out.println("autorizado");
        Requisito novoRequisito = new Requisito();
        novoRequisito.setDescricao(descricao);
        novoRequisito.setMotivacao(motivacao);
        novoRequisito.setNome(pNome);
        novoRequisito.setProjeto(projeto);
        novoRequisito.setStatusRequisito(StatusRequisito.sujestao);

        if (UtilSBPersistencia.persistirRegistro(novoRequisito)) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Requisito [" + pNome + "]cadastrado com sucesso", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return true;
        } else {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Erro ao cadastrar requisito [" + pNome + "]", ItfCentralMensagens.TP_MENSAGEM.ERRO);
            return false;
        }
    }

    @InfoAcesso(nomeAmigavel = "Criar novo projeto")
    public static boolean criarProjeto(String pnome, String pDescricao, Cliente pCliente) {

        if (!autorizar()) {
            return false;
        }

        Projeto novoprojeto = new Projeto();
        novoprojeto.setDescricao(pDescricao);
        novoprojeto.setCliente(pCliente);
        novoprojeto.setNomeProjeto(pnome);

        return criarProjeto(novoprojeto);

    }

    public static boolean criarCliente(Cliente pCliente) {
       // if (!autorizar()) {

        //       return false;
        //   }
        if (UtilSBPersistencia.persistirRegistro(pCliente)) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Cliente cadastrado com sucesso", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return true;
        } else {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Desculpe, aconteceu um problema ao tentar cadastrar o Cliente, você preencheu todos os campos obrigatórios?", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }
    }

    public static boolean criarProjeto(Projeto pProjeto) {
        if (pProjeto.getCliente() == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Não foi possível criar o projeto, Cliente não selecionado", ItfCentralMensagens.TP_MENSAGEM.ALERTA);
            return false;
        }
        if (pProjeto.getDescricao() == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Não foi possível criar o projeto, a descrição não foi preenchida ", ItfCentralMensagens.TP_MENSAGEM.ALERTA);
            return false;
        }
        if (pProjeto.getNomeProjeto() == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Não foi possível criar o projeto, o nome não foi configurado", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }

        pProjeto.setDataCriacao(new Date());
        pProjeto.setPastaDoProjeto("c:\\home\\projetos\\source\\" + pProjeto.getCliente().getNomeCurto() + "\\" + pProjeto.getNomeCurto());

        boolean gravou = UtilSBPersistencia.persistirRegistro(pProjeto);
        if (gravou) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Projeto " + pProjeto.getNomeProjeto() + " cadastrado com sucesso", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return true;
        } else {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Ocorreu um erro ao tentar gravar o novo projeto", ItfCentralMensagens.TP_MENSAGEM.ALERTA);
            return false;
        }

    }

    public static void finalizarRequisito(Requisito pRequisito) {
        if (!autorizar()) {
            return;
        }
        pRequisito.setStatusRequisito(StatusRequisito.finalizado);
        UtilSBPersistencia.mergeRegistro(pRequisito);
    }

    public static Boolean adcionarDesenvolvedorAoProjeto(Projeto pProjeto, Desenvolvedor pDesenvolvedor, int horasSemanais) {

        if (pProjeto == null || pDesenvolvedor == null || horasSemanais == 0) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Os parametros projeto, desenvolvedor e horas semanais são obrigatórios", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }

        List<Desenvolvedor> desenvolvedores = pProjeto.getDesenvolvedores();
        for (Desenvolvedor dev : desenvolvedores) {
            if (dev.getId() == pDesenvolvedor.getId()) {
                System.out.println("usuario já cadastrado");
                SBCore.getCentralDeMensagens().enviaMensagemUsuario("Usuário já cadastrado", ItfCentralMensagens.TP_MENSAGEM.AVISO);
                return false;
            }
        }

        Projeto_Desenvolvedor projDev = new Projeto_Desenvolvedor();
        projDev.setDesenvolvedor(pDesenvolvedor);
        projDev.setProjeto(pProjeto);
        projDev.setHorasDia(horasSemanais);

        pProjeto.getDesenvolvedoresInfoCompleta().add(projDev);

        if (UtilSBPersistencia.persistirRegistro(projDev)) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Desenvolvedor: " + pDesenvolvedor.getNome() + " adcionado com sucesso ao projeto" + pProjeto.getNomeCurto(), ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return true;
        } else {
            return false;
        }

    }

    private static boolean alteraHorasSemanaisDesenvolvedor(Projeto pProjeto, Desenvolvedor pDesenvolvedor, int alteracao) {
        if (pProjeto == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Parametro projeto é obrigatório", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }
        if (pDesenvolvedor == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario(null, ItfCentralMensagens.TP_MENSAGEM.AVISO);
        }

        Projeto_Desenvolvedor novohoraRioSemanal = (Projeto_Desenvolvedor) UtilSBPersistencia.getRegistroByJPQL(" SELECT pd  from Projeto_Desenvolvedor pd where projeto_id=" + pProjeto.getId() + " and desenvolvedor_id=" + pDesenvolvedor.getId(), Projeto_Desenvolvedor.class);
        if (novohoraRioSemanal == null) {
            SBCore.getCentralDeMensagens().enviaMensagemUsuario("Usuário Não trabalha neste projeto", ItfCentralMensagens.TP_MENSAGEM.AVISO);
            return false;
        }

        novohoraRioSemanal.setHorasDia(novohoraRioSemanal.getHorasDia() + alteracao);
        UtilSBPersistencia.mergeRegistro(novohoraRioSemanal);
        return true;
    }

    public static boolean incrementarHorasSemanaisDesenvolvedor(Projeto pProjeto, Desenvolvedor pDesenvolvedor) {
        return alteraHorasSemanaisDesenvolvedor(pProjeto, pDesenvolvedor, 1);
    }

    public static boolean decrementarHorasSemanaisDesenvolvedor(Projeto pProjeto, Desenvolvedor pDesenvolvedor) {
        return alteraHorasSemanaisDesenvolvedor(pProjeto, pDesenvolvedor, -1);
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoUsuarioInteracao;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.Mensagem;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ItfInfoErroSB;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.RelatorioTesteAbstrato;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes.UtilTestePagina;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class RelatorioTesteWebPaginas extends RelatorioTesteAbstrato {

    @Override
    public List<ItfInfoErroSB> executarTestesBanco() {
        List<ItfInfoErroSB> erros = new ArrayList<>();
        for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
            try {
                System.out.println("entidade::::" + entidade.getSimpleName());
                UtilSBCoreReflexao.getNomeDoObjetoPorAnotacaoInfoClasse(entidade);
            } catch (Throwable t) {
                ErroSBCoreFW errro = new ErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }
        }
        return erros;
    }

    @Override
    public List<ItfInfoErroSB> executarTestesBancoAcoes() {
        List<ItfInfoErroSB> erros = new ArrayList<>();
        erros.addAll(executarTestesAcoes());
        erros.addAll(executarTestesBanco());
        return erros;
    }

    @Override
    public List<ItfInfoErroSB> executarTestesAcoes() {
        List<ItfInfoErroSB> erros = new ArrayList<>();

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            try {
                UtilTestePagina.testaconfigIcone(acao.getEnumAcaoDoSistema());
                if (acao.isUmaAcaoFormulario()) {

                    UtilTestePagina.testaAcaoFormulario(acao.getComoFormulario());
                }
            } catch (Throwable t) {
                ErroSBCoreFW errro = new ErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }

        }
        return erros;

    }

}

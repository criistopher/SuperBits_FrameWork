/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.FabTipoUsuarioInteracao;
import com.super_bits.modulosSB.SBCore.Mensagens.Mensagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ItfInfoErroSB;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.RelatorioTesteAbstrato;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
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
    public List<ItfInfoErroSB> executarTestes() {
        List<ItfInfoErroSB> erros = new ArrayList<>();

        for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
            try {
                System.out.println("entidade::::" + entidade.getSimpleName());
                UtilSBCoreReflexao.getNomeDoObjeto(entidade);
            } catch (Throwable t) {
                ErroSBCoreFW errro = new ErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }
        }

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            try {
                UtilTestePagina.testaconfigIcone(acao.getEnumAcaoDoSistema());
                if (acao.isUmaAcaoFormulario()) {

                    UtilTestePagina.testaAcaoFormulario(acao.comoFormulario());
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

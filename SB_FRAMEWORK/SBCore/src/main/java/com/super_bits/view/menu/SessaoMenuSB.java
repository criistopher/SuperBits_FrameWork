/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.menu;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSessaoCategoria;
import com.super_bits.Controller.anotacoes.AcaoGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Aramezena uma sessaão do sistema, contendo ações e outras sessoes
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 08/01/2016
 * @version 1.0
 *
 */
public class SessaoMenuSB extends AcaoGenerica implements ItfAcaoSessaoCategoria {

    private ItfAcaoDoSistema acaoSessao;
    private List<ItfAcaoDoSistema> acoes;
    private List<SessaoMenuSB> sessoes;

    public List<ItfAcaoDoSistema> getAcoes() {
        return acoes;
    }

    public void addAcao(ItfAcaoDoSistema pAcao) {
        if (pAcao == null) {
            throw new UnsupportedOperationException("A ação adicionada na sessao não pode ser nula");
        }
        acoes.add(pAcao);

    }

    public SessaoMenuSB(ItfAcaoDoSistema pAcaoSessao) {
        acaoSessao = pAcaoSessao;
        if (acaoSessao.getIconeAcao() == null) {
            acaoSessao.setIcone("fa fa-list-alt");
        }
        acoes = new ArrayList<>();
    }

    @Override
    public List<ItfAcaoSessaoCategoria> getSessoes() {
        return (List) sessoes;
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 08/01/2016
 * @version 1.0
 *
 */
public class MenuSBFW {

    private final List<SessaoMenuSB> sessoes;

    public MenuSBFW() {
        sessoes = new ArrayList<>();
    }

    public void addSessao(SessaoMenuSB pSessao) {
        sessoes.add(pSessao);

    }

    public List<SessaoMenuSB> getSessoes() {
        return sessoes;
    }

    public boolean isTemMenu() {
        return getSessoes().size() > 0;
    }

}

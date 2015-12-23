/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import javax.enterprise.context.ApplicationScoped;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 22/12/2015
 * @version 1.0
 *
 */
@ApplicationScoped
public class TipoVisualBotao extends ConstantesWeb {

    public TipoVisualBotao() {
        super(FabTipoVisualBotaoAcao.class);
    }

    @Override
    public String getPadrao() {
        FabTipoVisualBotaoAcao botaoAcao = FabTipoVisualBotaoAcao.APENAS_ICONE;
        switch (botaoAcao) {
            case APENAS_ICONE:
                break;
            case ICONE_NOME:
                break;
            case BOTAO_GIGANTE:
                break;
            case APENAS_NOME:
                break;
            case APENAS_DESCRICAO:
                break;
            default:
                throw new AssertionError(botaoAcao.name());

        }
        return botaoAcao.toString();
    }

    public String getApenasIcone() {
        return FabTipoVisualBotaoAcao.APENAS_ICONE.toString();
    }

    public String getBotaoGigante() {
        return FabTipoVisualBotaoAcao.BOTAO_GIGANTE.toString();
    }

    public String getApenasNome() {
        return FabTipoVisualBotaoAcao.APENAS_NOME.toString();
    }

    public String getApenasDescricao() {
        return FabTipoVisualBotaoAcao.BOTAO_GIGANTE.toString();
    }

}

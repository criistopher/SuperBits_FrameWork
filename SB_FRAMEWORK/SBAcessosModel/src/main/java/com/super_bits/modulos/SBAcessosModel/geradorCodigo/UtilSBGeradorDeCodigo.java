/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class UtilSBGeradorDeCodigo {


    public static String makeAnotacaoDaAcao(ItfAcaoDoSistema pAcao) {
        // retorna a string com a anotação do enum da ação exemplo:
       // @InfoTipoFormulario(nomeAcao="teste" , campos="{'nome','telefone'} , precisaPermissao=true)
        if (pAcao.isUmaAcaoController()) {
            return "@InfoTipoAcaoController(nomeAcao = \" " + pAcao.getNome() + " \", descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \" \n"
                    + " , precisaPermissao = " + pAcao.isPrecisaPermissao() + " , codigoJira = \" " + pAcao.getIdDescritivoJira() + "\" )\n ";
        } else if (pAcao.isUmaAcaoGestaoDominio()) {
            return "@InfoTipoAcaoGestaoEntidade(nomeAcao = \" " + pAcao.getNome() + " \", descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \"  \n"
                    + " , xhtmlDaAcao = \" " + ((ItfAcaoFormulario) pAcao).getXhtml() + " \", precisaPermissao = " + pAcao.isPrecisaPermissao() + ", codigoJira = \" " + pAcao.getIdDescritivoJira() + "\" \n) ";
        } else if (pAcao.isUmaAcaoFormulario() && !pAcao.isUmaAcaoGestaoDominio()) {
            String campos = "";
            if (!pAcao.getComoFormulario().getGruposDeCampos().isEmpty()) {
                for (GrupoCampos acao : pAcao.getComoFormulario().getGruposDeCampos()) {
                    campos += acao.toString();
                }
            }
            return "@InfoTipoAcaoFormulario(nomeAcao = \" " + pAcao.getNome() + " \",descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \"  \n"
                    + " , xhtmlDaAcao = \" " + ((ItfAcaoFormulario) pAcao).getXhtml() + " \",precisaPermissao = " + pAcao.isPrecisaPermissao() + " , codigoJira = \" " + pAcao.getIdDescritivoJira() + " \" \n) "
                    + campos + "= {" + pAcao.getComoFormulario().getGruposDeCampos() + "}";
        } else {

            return null;
        }
    }

    public static String makeEnumFabricaDeAcoes(List<ItfAcaoDoSistema> pAcoes, ItfModuloAcaoSistema pModulo) {

        // primeiro infoModulos, segundo declaracaoEnum, terceiro anotacao de acordo com o tipo da acao executada
        // nome da aplicação a ser incluida no infomodulos
        String nomeAplicacao = null;
        // nome da fabrica a ser incluida na declaração do enum
        String fabrica = null;
        // inicial generica para o enum
        String generico = null;

        // somente o infoModulos com os nomes de nome da aplicação
        String infoModulos = "@InfoModulos[" + nomeAplicacao + "](modulo = FabModulo[" + nomeAplicacao + "].ADMINISTRATIVO)\n";
        // declaração do enum
        String declaracaoEnum = "public enum FabAcao[" + fabrica + "] implements ItfFabricaAcoes {\n\n"; // não esquecer de fechar a declaração do enum no final de tudo
        // cabeçalho = infoModulos + declaraEnum
        String cabecalho = infoModulos + declaracaoEnum;

        String enumGerado = null;
        for (ItfAcaoDoSistema acao : pAcoes) {

            // colocar o MB a frente das outras ações do sistema
            if (acao.isUmaAcaoGestaoDominio()) {
                // setar primeiro uma ação de gestão de domínio
                enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_MB_GERENCIAR,\n";

                switch (acao.getTipoAcaoGenerica()) {

                    case FORMULARIO_NOVO_REGISTRO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_NOVO_REGISTRO,\n";
                        break;
                    case FORMULARIO_EDITAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_EDITAR,\n";
                        break;
                    case FORMULARIO_PERSONALIZADO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_PERSONALIZADO,\n";
                        break;
                    case FORMULARIO_VISUALIZAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_VISUALIZAR,\n";
                        break;
                    case FORMULARIO_LISTAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_LISTAR,\n";
                        break;
                    case FORMULARIO_MODAL:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_MODAL,\n";
                        break;
                    case SELECAO_DE_ACAO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_FRM_SELETOR,\n";
                        break;
                    case CONTROLLER_SALVAR_EDICAO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_SALVAR_EDICAO,\n";
                        break;
                    case CONTROLLER_SALVAR_NOVO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_SALVAR_NOVO,\n";
                        break;
                    case CONTROLLER_SALVAR_MODO_MERGE:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_SALVAR_MERGE,\n";
                        break;
                    case CONTROLLER_PERSONALIZADO:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_PERSONALIZADO,\n";
                        break;
                    case CONTROLLER_ATIVAR_DESATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_ATIVAR_DESATIVAR,\n";
                        break;
                    case CONTROLLER_ATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_ATIVAR,\n";
                        break;
                    case CONTROLLER_REMOVER:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_REMOVER,\n";
                        break;
                    case CONTROLLER_DESATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + "[" + generico + "]_CTR_DESATIVAR,\n";
                        break;

                }

            }
            // trocar ultimo caracter por ;
            if (enumGerado.endsWith(",")) {
                int ultimoCaracter = enumGerado.length() - 1;
                enumGerado.replace(enumGerado.substring(ultimoCaracter), ";");

            }

        }

        // todo  criar o switch grande
        String MetodoGetRegistro = " @Override\n"
                + "    public AcaoDoSistema getRegistro() {\n"
                + "        try {\n"
                + "            if (MapaAcoesSistema.isMapaCriado()) {\n"
                + "                return (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistema(this);\n"
                + "            }\n"
                + "\n"
                + "            ItfAcaoDoSistema acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);\n"
                + "\n"
                + "            return (AcaoDoSistema) acao;\n"
                + "        } catch (Throwable t) {\n"
                + "            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, \"Erro configurando ação\" + this, t);\n"
                + "\n"
                + "        }\n"
                + "        return null;\n"
                + "\n"
                + "    }";

        String getAcaoDoSistema = "@Override\n"
                + "    public AcaoDoSistema getAcaoDoSistema() {\n"
                + "        return getRegistro();\n"
                + "    }";

        String getNomeModulo = "@Override\n"
                + "    public String getNomeModulo() {\n"
                + "        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();\n"
                + "    }";



        //retorna uma string contendo todo conteúdo da enum (cada ação com sua respectiva anotação, e os metodos obrigatórios

        // ao final não esquecer de adicionar os métodos com implementação obrigatória,
        // conforme exemplo abaixo, e do metodo  getEntidadeDominio()
        //
        /**
         *
         * @Override public AcaoDoSistema getRegistro() { try { if
         * (MapaAcoesSistema.isMapaCriado()) { return (AcaoDoSistema)
         * MapaAcoesSistema.getAcaoDoSistema(this); }
         *
         * ItfAcaoDoSistema acao =
         * UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
         *
         * return (AcaoDoSistema) acao; } catch (Throwable t) {
         * SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando ação"
         * + this, t);
         *
         * }
         * return null;
         *
         * }
         * @Override public AcaoDoSistema getAcaoDoSistema() { return
         * getRegistro(); }
         */
        //@Override
        //public AcaoDoSistema getAcaoDoSistema() {
        //     return getRegistro();
        // }
        //   @Override
        //public String getNomeModulo() {
        //    return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
        //}

        return null;
    }

    public static String makeClasseAnotacaoInfoAcao(ItfAcaoDoSistema pAcao) {

        // COnstroi uma anotação do tipo InfoAcaoNomeDoModulo
        return null;
    }

    public static String makeEnumListas(Class pClasse) {
        return null;
    }

    public static String makeListasAnotacao(Class pClasse) {
        return null;
    }

    public static String makeEnumCalculos(Class pClasse) {

        return null;
    }

    public static String makeCalculoAnotacaos(Class pClasse) {
        return null;
    }

    public static String makeEntidade(EstruturaDeEntidade pEstrutura) {

        String classeFormatada = "";

        for (EstruturaCampo campo : pEstrutura.getCampos()) {

            switch (campo.getTipoValor()) {

                case NUMERO:
                    break;

                case LETRAS:
                    break;

                case DATAS:
                    break;

                default:
                    throw new AssertionError(campo.getTipoValor().name());

            }

        }

        /*  String anotcaoDoCampo = "@InfoCampo(tipo=\"" + campo.getTipoCampo().toString() + "\" label=\"" + campo.getLabel() + "\" ) \n";
            classeFormatada += anotcaoDoCampo;
            String declaracaoDoCampo = " private " + campo.getNomeDeclarado();
            classeFormatada += declaracaoDoCampo;
         */
        return classeFormatada;
    }

    public static void criarArquivosDoSistema(List<EstruturaDeEntidade> entidades, List<ItfAcaoDoSistema> acoesDoSistema) {

        /// para obter o caminho da instalação deve se usar o seguinte diretorio:
        String caminhoCodigoGerado = SBCore.getCaminhoDesenvolvimento() + "/codigoGerado";
        String caminhoArquivosClasse = caminhoCodigoGerado + "/java";
        String caminhoArquivosXHTML = caminhoCodigoGerado + "/web";

        // O script deve criar utilizando os metodos acima e a funcao UtilSBArquivos para gerar o código
        // automátioco de acordo com os parametros recebidos.
        /**
         * 1- Para cada anotacao de entidade cria no pacote
         * com.super_bits.nomeDoCliente.model uma classe de Anotação <br>
         * 2- Para cada anotação de Ação do sistema, organiza-se as ações por
         * modulo, chama o metodo makeEnumFabricaDeAcoes, e cria o com o
         * conteúdo arquivo no pacote com.super_bits.nomeDocliente.controller,
         * 3-Para cada modulo, cria um arquivo de controller
         *
         *
         *
         */
    }

}

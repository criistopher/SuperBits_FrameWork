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
        String nomeAplicacao = "[nomeDaAplicacao]";
        // nome da fabrica a ser incluida na declaração do enum
        String fabrica = null;
        // inicial generica para o enum
        String generico = null;

        // somente o infoModulos com os nomes de nome da aplicação
        String infoModulos = "@InfoModulos" + nomeAplicacao + "(modulo = FabModulo[" + nomeAplicacao + "].ADMINISTRATIVO)\n";
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
                + "    }\n";

        String getAcaoDoSistema = "@Override\n"
                + "    public AcaoDoSistema getAcaoDoSistema() {\n"
                + "        return getRegistro();\n"
                + "    }\n";

        String getNomeModulo = "@Override\n"
                + "    public String getNomeModulo() {\n"
                + "        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();\n"
                + "    }\n";


        String modulo = "MÓDULO_GENÉRICO"; // modulo que irá compor os enum do switch da classe getEntidadeDeDominio()
        String classe = "Classe"; // classe para ser colocada no switch do da classe getEntidadeDeDominio()
        String classeGetEntidadeDeDominio = " "
                + "@Override\n"
                + "    public Class\n"
                + "            getEntidadeDominio() {\n"
                + "\n"
                + "        switch (this) {\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_ALTERACAO:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_LISTARPEDIDOS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_NOVO:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FILIAL_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FILIAL_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_FILIAL_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FILIAL_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FILIAL_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FILIAL_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_FILIAL_CTR_ALTERAR_STATUS:\n"
                + "                return FilialComprador.class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_NOVO:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_ALTERACAO:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR_CAMPANHA:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FILIAL_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FILIAL_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_FILIAL_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_FILIAL_VISUALIZAR:\n"
                + "            case [" + modulo + "]_FILIAL_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FILIAL_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FILIAL_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_MB_GERENCIAR_ENTRADA:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_FRM_NOVO:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_FRM_LISTAR:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_MB_GERENCIAR_ENTRADA:\n"
                + "            case SOLICITACAO_FRM_ENTRADA_[" + modulo + "]_CADASTRAR:\n"
                + "            case SOLICITACAO_FRM_ENTRADA_[" + modulo + "]_VISUALIZAR:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_FRM_LISTAR:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case SOLICITACAO_ENTRADA_[" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR_EMANDAMENTO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR_PROGRAMADA:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR_ADMINISTRADOR:\n"
                + "            case [" + modulo + "]_CTR_APROVAR:\n"
                + "            case [" + modulo + "]_CTR_REPROVAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_LISTAR_PEDIDO:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_FRM_APROVAR_REPROVAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [modulo]_DE_PAGAMENTO_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_[" + modulo + "]_CTR_REMOVER:\n"
                + "            case [" + modulo + "]_CIDADE_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_CIDADE_FRM_NOVO:\n"
                + "            case [" + modulo + "]_CIDADE_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_ENVIAR_RESPOSTA:\n"
                + "            case [" + modulo + "]_FRM_CRIAR_MENSAGEM:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_FRM_NOVO_MODELO:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_CTR_DOWNLOAD:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_CTR_GERAR:\n"
                + "            case [" + modulo + "]_CTR_UPLOAD:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVOBANNER_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_EDITAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZA:\n"
                + "            case [" + modulo + "]AFATURAR_MB_GERENCIAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case SUGESTAO_[" + modulo + "]_FRM_LISTAR:\n"
                + "            case SUGESTAO_[" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case SUGESTAO_[" + modulo + "]_MB_GERENCIAR:\n"
                + "            case SUGESTAO_[" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case SUGESTAO_[" + modulo + "]_FRM_NOVO:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            case [" + modulo + "]_MB_GERENCIAR:\n"
                + "            case [" + modulo + "]_FRM_VISUALIZAR:\n"
                + "            case [" + modulo + "]_CTR_ALTERAR_STATUS:\n"
                + "            case [" + modulo + "]_CTR_SALVAR_MERGE:\n"
                + "            case [" + modulo + "]_FRM_NOVO:\n"
                + "            case [" + modulo + "]_FRM_LISTAR:\n"
                + "                return [" + classe + "].class;\n"
                + "\n"
                + "            default:\n"
                + "                throw new AssertionError(this.name());\n"
                + "        }\n"
                + "\n"
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

    private static String getTagsDaEntidade(List<String> pTags){

        String tagsFormatadas = "";

        for (int i = 0; i < pTags.size(); i++) {

            if(i > 0){

            tagsFormatadas += ", \""+pTags.get(i)+"\"";

            }else{

                tagsFormatadas += "\""+pTags.get(i)+"\"";

            }

        }

        return tagsFormatadas;
    }

    public static String makeEntidade(EstruturaDeEntidade pEstrutura) {

        // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
        String classeFormatada = "";

        // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
        classeFormatada

                // ADICIONA A STRING DE ANOTAÇÃO ENTITY DA CLASSE NA VARIAVEL ClasseFormata
                += "@Entity\n"

                // ADICIONA A STRING DE ANOTAÇÃO INFOCLASSE DA CLASSE NA VARIAVEL ClasseFormata
                + "@InfoClasse(tags = {"+ getTagsDaEntidade(pEstrutura.getTags()) +"}, icone = \""+pEstrutura.getIcone()+"\", plural = \""+pEstrutura.getPlural()+"\")\n"

                // ADICIONA A STRING DE DECLARAÇÃO DA CLASSE, SEU NOME E EXTENSÕES NA VARIAVEL ClasseFormata
                + "public class "+pEstrutura.getNomeEntidade()+ " extends "+ pEstrutura.getTipoEntidade().getNomeClasseEntidade()+" {\n\n";

        for (EstruturaCampo campo : pEstrutura.getCampos()) {

            switch (campo.getTipoValor()) {

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO INTEIRO
                //
                //
                //
                case INTEIRO:

                    // VERIFICA SE O CAMPO É DO TIPO ID
                    if (campo.getTipoCampo().equals(FabCampos.ID)) {

                        // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                        classeFormatada
                                // ADICIONA A STRING DE ANOTAÇÃO ID DO CAMPO NA VARIAVEL ClasseFormata
                                += "@Id\n"
                                // ADICIONA A STRING DE ANOTAÇÃO GENERATEDVALUE DO CAMPO NA VARIAVEL ClasseFormata
                                + "@GeneratedValue(strategy = GenerationType.AUTO)\n"
                                // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                                + "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    } else {

                        // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                        classeFormatada
                                // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormata
                                += "@InfoCampo(tipo = " + campo.getTipoCampo().toString() + ", label = \"" + campo.getLabel() + "\", descricao = \"" + campo.getDescricao() + "\")\n";

                        // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                        if (campo.isObrigatorio()) {

                            // ADICIONA A STRING DE ANOTAÇÃO NOTNULL SE OBRIGATÓRIO NA VARIAVEL ClasseFormatada
                            classeFormatada += "@NotNull\n";

                        }

                        // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                        classeFormatada += "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    }

                    break;

                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO INTEIRO

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO DATA
                //
                //
                //
                case DATAS:

                    // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                    classeFormatada
                            // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                            += "@InfoCampo(tipo = " + campo.getTipoCampo().toString() + ", label = \"" + campo.getLabel() + "\", descricao = \"" + campo.getDescricao() + "\")\n"
                            // ADICIONA A STRING DE ANOTAÇÃO TEMPORAL NO CAMPO NA VARIAVEL ClasseFormatada
                            + "@Temporal(javax.persistence.TemporalType.DATE)\n";

                    // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                    if (campo.isObrigatorio()) {

                        // ADICIONA A STRING DE ANOTAÇÃO NOTNULL SE OBRIGATÓRIO NA VARIAVEL ClasseFormatada
                        classeFormatada += "@NotNull\n";

                    }

                    // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                    classeFormatada += "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    break;

                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO DATA

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO LETRAS
                //
                //
                //
                case LETRAS:

                    // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                    classeFormatada
                            // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                            += "@InfoCampo(tipo = " + campo.getTipoCampo().toString() + ", label = \"" + campo.getLabel() + "\", descricao = \"" + campo.getDescricao() + "\")\n";

                    // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                    if (campo.isObrigatorio()) {

                        // ADICIONA A STRING DE ANOTAÇÃO COLUMN DO CAMPO NA VARIAVEL ClasseFormatada
                        classeFormatada += "@Column(length = " + campo.getValorMaximo() + ", nullable = true)\n"
                                // ADICIONA A STRING DE ANOTAÇÃO NOTNULL DO CAMPO NA VARIAVEL ClasseFormatada
                                + "@NotNull\n";

                    } else {

                        // ADICIONA A STRING DE ANOTAÇÃO COLUMN DO CAMPO NA VARIAVEL ClasseFormatada
                        classeFormatada += "@Column(length = " + campo.getValorMaximo() + ", nullable = false)\n";

                    }

                    // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                    classeFormatada += "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    break;

                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO LETRAS

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO BOOLEAN
                //
                //
                //
                case BOOLEAN:

                    // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                    classeFormatada
                            // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                            += "@InfoCampo(tipo =" + campo.getTipoCampo().toString() + ", label = \"" + campo.getLabel() + "\", descricao = \"" + campo.getDescricao() + "\")\n";

                    // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                    if (campo.isObrigatorio()) {

                        // ADICIONA A STRING DE ANOTAÇÃO NOTNULL DO CAMPO NA VARIAVEL ClasseFormatada
                        classeFormatada += "@NotNull\n";

                    }

                    // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                    classeFormatada += "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    break;

                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO BOOLEAN

                 // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO ENTIDADE
                //
                //
                //
                case ENTIDADE:

                    throw new UnsupportedOperationException("O CASE ENTIDADE NÃO RETORNA UMA STRING DE FORMATAÇÃO DE CLASSE!!!");


                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO BOOLEAN
                default:
                    throw new AssertionError(campo.getTipoValor().name());

            }

        }

        // ADICIONA O CARACTER } PARA FECHAR A CLASSE
        classeFormatada += "}";

        // RETORNA A VARIAVEL ClasseFormatada COM A STRING DE CRIAÇÃO DA CLASSE
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

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;

import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaUm;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoUmParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.ListaDeEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;

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
                    + " , xhtmlDaAcao = \"" + ((ItfAcaoFormulario) pAcao).getXhtml() + "\", precisaPermissao = " + pAcao.isPrecisaPermissao() + ", codigoJira = \" " + pAcao.getIdDescritivoJira() + "\" \n) ";
        } else if (pAcao.isUmaAcaoFormulario() && !pAcao.isUmaAcaoGestaoDominio()) {

            String campos = "";
            if (!pAcao.getComoFormulario().getGruposDeCampos().isEmpty()) {
                for (GrupoCampos grupoCampo : pAcao.getComoFormulario().getGruposDeCampos()) {
                    campos += grupoCampo.toString();
                }
            }

            return "@InfoTipoAcaoFormulario(nomeAcao = \" " + pAcao.getNome() + " \",descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \"  \n"
                    + " , xhtmlDaAcao = \"" + ((ItfAcaoFormulario) pAcao).getXhtml() + "\",precisaPermissao = " + pAcao.isPrecisaPermissao() + " , codigoJira = \" " + pAcao.getIdDescritivoJira() + " \" \n) "
                    + "campos = {" + campos + "})";
        } else {

            return null;
        }
    }

    public static String makeEnumFabricaDeAcoes(List<ItfAcaoDoSistema> pAcoes, ItfModuloAcaoSistema pModulo) {

        // primeiro infoModulos, segundo declaracaoEnum, terceiro anotacao de acordo com o tipo da acao executada
        // nome da aplicação a ser incluida no infomodulos
        String nomeAplicacao = "[NOMEDAAPLICACAO]";

        // nome da fabrica a ser incluida na declaração do enum
        String fabrica = "[NOMEDAFABRICA]";
        // inicial generica para o enum
        String generico = "[DOMÍNIO]";
        // somente o infoModulos com os nomes de nome da aplicação
        String infoModulos = "@InfoModulos" + nomeAplicacao + "(modulo = FabModulo[" + nomeAplicacao + "].ADMINISTRATIVO)\n";
        // declaração do enum
        String declaracaoEnum = "public enum FabAcao[" + fabrica + "] implements ItfFabricaAcoes {\n\n";
        // cabeçalho = infoModulos + declaraEnum
        String cabecalho = infoModulos + declaracaoEnum;

        String enumGerado = null;
        for (ItfAcaoDoSistema acao : pAcoes) {
            // colocar o MB a frente das outras ações do sistema
            if (acao.isUmaAcaoGestaoDominio()) {
                // setar primeiro uma ação de gestão de domínio
                enumGerado += makeAnotacaoDaAcao(acao) + generico + "_MB_GERENCIAR,\n";

            }
            for (ItfAcaoDoSistema action : pAcoes) {

                switch (action.getTipoAcaoGenerica()) {

                    case FORMULARIO_NOVO_REGISTRO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_NOVO_REGISTRO,\n";
                        break;
                    case FORMULARIO_EDITAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_EDITAR,\n";
                        break;
                    case FORMULARIO_PERSONALIZADO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_PERSONALIZADO,\n";
                        break;
                    case FORMULARIO_VISUALIZAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_VISUALIZAR,\n";
                        break;
                    case FORMULARIO_LISTAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_LISTAR,\n";
                        break;
                    case FORMULARIO_MODAL:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_MODAL,\n";
                        break;
                    case SELECAO_DE_ACAO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_FRM_SELETOR,\n";
                        break;
                    case CONTROLLER_SALVAR_EDICAO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_SALVAR_EDICAO,\n";
                        break;
                    case CONTROLLER_SALVAR_NOVO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_SALVAR_NOVO,\n";
                        break;
                    case CONTROLLER_SALVAR_MODO_MERGE:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_SALVAR_MERGE,\n";
                        break;
                    case CONTROLLER_PERSONALIZADO:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_PERSONALIZADO,\n";
                        break;
                    case CONTROLLER_ATIVAR_DESATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_ATIVAR_DESATIVAR,\n";
                        break;
                    case CONTROLLER_ATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_ATIVAR,\n";
                        break;
                    case CONTROLLER_REMOVER:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_REMOVER,\n";
                        break;
                    case CONTROLLER_DESATIVAR:
                        enumGerado += makeAnotacaoDaAcao(acao) + generico + "_CTR_DESATIVAR,\n";
                        break;
                }
            }
        }

        // trocar ultimo caracter por ; // trocar o método de substituição de , por ;
        StringBuilder enumTreta = new StringBuilder(enumGerado);
        enumTreta.delete(enumTreta.length() - 2, enumTreta.length() - 1);
        enumTreta.append(";\n");

        enumGerado = enumTreta.toString();

        String metodoGetGestaoDeEntidade = "public AcaoGestaoEntidade getGestaodeEntidade() {\n"
                + "\n"
                + "        return (AcaoGestaoEntidade) UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(this);\n"
                + "    }\n";

        String metodoGetRegistro = " @Override\n"
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

        String metodoGetAcaoDoSistema = "@Override\n"
                + "    public AcaoDoSistema getAcaoDoSistema() {\n"
                + "        return getRegistro();\n"
                + "    }\n";

        String metodoGetNomeModulo = "@Override\n"
                + "    public String getNomeModulo() {\n"
                + "        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();\n"
                + "    }\n";

        String classeGetEntidadeDeDominio
                = "@Override\n"
                + "                   public Class\n"
                + "                       getEntidadeDominio() {\n";

        for (ItfAcaoDoSistema acao : pAcoes) {

            if (acao.isUmaAcaoGestaoDominio()) {
                classeGetEntidadeDeDominio += "case GERENCIAR_DOMINIO:\n "
                        + "return ";
                classeGetEntidadeDeDominio += acao.getComoGestaoEntidade().getClasseRelacionada().getSimpleName() + ".class;\n";
            }

            classeGetEntidadeDeDominio += "switch(acao.getTipoAcaoGenerica()){\n"
                    + "                case FORMULARIO_NOVO_REGISTRO:\n"
                    + "                case FORMULARIO_EDITAR:\n"
                    + "                case FORMULARIO_PERSONALIZADO:\n"
                    + "                case FORMULARIO_VISUALIZAR:\n"
                    + "                case FORMULARIO_LISTAR:\n"
                    + "                case FORMULARIO_MODAL:\n"
                    + "                    return acao.getComoFormularioEntidade().getClasseRelacionada().getSimpleName()";
            classeGetEntidadeDeDominio += ".class;\n";

            classeGetEntidadeDeDominio += " case SELECAO_DE_ACAO:\n"
                    + "                    return acao.getComoFormularioEntidade().getClasseRelacionada().getSimpleName()";
            classeGetEntidadeDeDominio += ".class;\n";

            classeGetEntidadeDeDominio += "case CONTROLLER_SALVAR_EDICAO:\n"
                    + "                case CONTROLLER_SALVAR_NOVO:\n"
                    + "                case CONTROLLER_SALVAR_MODO_MERGE:\n"
                    + "                case CONTROLLER_PERSONALIZADO:\n"
                    + "                case CONTROLLER_ATIVAR_DESATIVAR:\n"
                    + "                case CONTROLLER_ATIVAR:\n"
                    + "                case CONTROLLER_REMOVER:\n"
                    + "                case CONTROLLER_DESATIVAR:\n";
            classeGetEntidadeDeDominio += "return acao.getComoControllerEntidade().getClasseRelacionada().getSimpleName()";
            classeGetEntidadeDeDominio += ".class;\n";

            classeGetEntidadeDeDominio += "}\n";
        }
        //retorna uma string contendo todo conteúdo da enum (cada ação com sua respectiva anotação, e os metodos obrigatórios
        // ao final não esquecer de adicionar os métodos com implementação obrigatória,
        // conforme exemplo abaixo, e do metodo  getEntidadeDominio()        //
        /**
         * @Override public AcaoDoSistema getRegistro() { try { if
         * (MapaAcoesSistema.isMapaCriado()) { return (AcaoDoSistema)
         * MapaAcoesSistema.getAcaoDoSistema(this); }
         *
         * ItfAcaoDoSistema acao =
         * UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
         *
         * return (AcaoDoSistema) acao; } catch (Throwable t) {
         * SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando ação"
         * + this, t); * * } return null; * * }
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
        return cabecalho + enumGerado + metodoGetGestaoDeEntidade + classeGetEntidadeDeDominio + metodoGetRegistro + metodoGetAcaoDoSistema + metodoGetNomeModulo + "\n }";
    }

    public static String makeClasseAnotacaoInfoAcao(ItfAcaoDoSistema pAcao) {
        // COnstroi uma anotação do tipo InfoAcaoNomeDoModulo
        //  TODO -> validar
        String modulo = "[GENERICO]";
        String infoAcao = "";
        infoAcao += "@Documented\n"
                + "@Retention(RetentionPolicy.RUNTIME)\n";
        infoAcao += "public @interface InfoAcao" + modulo + " {\n"
                + "\n"
                + "    public boolean padraoBloqueado() default true;\n"
                + "\n"
                + "    public FabAcaoAdministrador acao();\n"
                + "}";
        return infoAcao;
    }

    public static String makeEnumListas(EstruturaDeEntidade pEntidade) {
        String stringcomtodosoosenuns = makeEnumListasEntidade(pEntidade.getListas());
//        if (stringcomtodosoosenuns.endsWith(",\n")) {
//            int ultimoCaracter = stringcomtodosoosenuns.length() - 1;
//            stringcomtodosoosenuns.replace(stringcomtodosoosenuns.substring(ultimoCaracter), ";\n");
//        }
        String classe = "[classeGenérica]";
        String enumGerado = "public enum Listas" + classe + " implements ItfListas {\n\n";
        enumGerado += stringcomtodosoosenuns + "\n\n";
        enumGerado += "@Override\n"
                + "    public List getLista() {\n\n"
                + "    }\n";
        enumGerado += "\n}";
        return enumGerado;
    }

    public static String makeListasAnotacao() {
        String classe = "[ClasseGenérica]";
        String listaAnotacao = "";
        listaAnotacao += "@Documented\n"
                + "@Retention(RetentionPolicy.RUNTIME)\n";
        listaAnotacao += "public @interface Lista" + classe + "{ \n";
        listaAnotacao += "    Listas" + classe + " lista();\n"
                + "\n";
        listaAnotacao += "\n}";
        return listaAnotacao;
    }

    // refatorar este método para utilizar a EstruturaDeEntidade
    public static String makeEnumCalculos(EstruturaDeEntidade pEntidade) {

        String classe = "[classeGenérica]";
        String enums = "";
        String declaracaoEnum = "public enum Calculos" + classe + " implements ItfCalculos {\n";
        String enumsCalculo = makeEnumCalculosEntidade(pEntidade.getCalculos()) + "\n";
        enums += declaracaoEnum + enumsCalculo + "}\n";
        return enums;
    }
    // utilizar este método no makeEnumCalculos
    public static String makeEnumCalculosEntidade(List<CalculoDeEntidade> pCalculoEntidade) {
        String listaEnumEntidade = "";
        int i = 0;
        for (CalculoDeEntidade entidade : pCalculoEntidade) {
            listaEnumEntidade += entidade.getJavaDoc();
            listaEnumEntidade += "\n";
            listaEnumEntidade += entidade.getNomeEnum();
            i++;
            if (i <= pCalculoEntidade.size() - 1) {
                listaEnumEntidade += ",\n";
            } else {
                listaEnumEntidade += ";\n";
            }
        }
        return listaEnumEntidade;
    }

    public static String makeCalculoAnotacaos() {

        String anotacaoCalculos = "";
        String classe = "[ClasseGenérica]";
        anotacaoCalculos += "@Documented\n"
                + "@Retention(RetentionPolicy.RUNTIME)\n";
        anotacaoCalculos += "public @interface Calculo" + classe + " {\n"
                + "\n"
                + "    Calculos" + classe + " calculo();\n"
                + "}";
        return anotacaoCalculos;
    }

    private static String getTagsDaEntidade(List<String> pTags) {

        String tagsFormatadas = "";

        for (int i = 0; i < pTags.size(); i++) {

            if (i > 0) {

                tagsFormatadas += ", \"" + pTags.get(i) + "\"";

            } else {

                tagsFormatadas += "\"" + pTags.get(i) + "\"";

            }
        }
        return tagsFormatadas;
    }

    public static String makeEnumListasEntidade(List<ListaDeEntidade> pEnum) {

        String enums = "";
        int i = 0;
        for (ListaDeEntidade tempEnum : pEnum) {
            enums += tempEnum.getJavaDoc();
            enums += "\n";
            enums += tempEnum.getNomeEnum();
            i++;
            if (i <= pEnum.size() - 1) {
                enums += ",\n";
            } else {
                enums += ";\n";
            }
        }
        return enums;
    }

    public static String makeDeclaracaoGetSetCampos(EstruturaCampo pCampo) {

        String getSetCampoFormatado = "";

        getSetCampoFormatado += "public " + pCampo.getTipoValor().getDeclaracaoJava() + " get" + pCampo.getNomeDeclarado() + "(){\n\n";

        getSetCampoFormatado += "return " + pCampo.getNomeDeclarado() + ";\n\n";

        getSetCampoFormatado += "}\n\n";

        getSetCampoFormatado += "public void set" + pCampo.getNomeDeclarado() + "(" + pCampo.getTipoValor().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ") {\n\n";

        getSetCampoFormatado += "this." + pCampo.getNomeDeclarado() + " = " + pCampo.getNomeDeclarado() + ";\n\n";

        getSetCampoFormatado += "}\n\n";

        return getSetCampoFormatado;
    }

    public static String makeDeclaracaoCampo(EstruturaCampo pCampo) {

        String campoFormatado = "";

        switch (pCampo.getTipoValor()) {

            // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO INTEIRO
            //
            //
            //
            case INTEIRO:

                // VERIFICA SE O CAMPO É DO TIPO ID
                if (pCampo.getTipoCampo().equals(FabCampos.ID)) {

                    // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                    campoFormatado
                            // ADICIONA A STRING DE ANOTAÇÃO ID DO CAMPO NA VARIAVEL ClasseFormata
                            += "@Id\n"
                            // ADICIONA A STRING DE ANOTAÇÃO GENERATEDVALUE DO CAMPO NA VARIAVEL ClasseFormata
                            + "@GeneratedValue(strategy = GenerationType.AUTO)\n"
                            // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                            + "private " + pCampo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ";\n\n";

                } else {

                    // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
                    campoFormatado
                            // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormata
                            += "@InfoCampo(tipo = FabCampos." + pCampo.getTipoCampo().toString() + ", label = \"" + pCampo.getLabel() + "\", descricao = \"" + pCampo.getDescricao() + "\")\n";

                    // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                    if (pCampo.isObrigatorio()) {

                        // ADICIONA A STRING DE ANOTAÇÃO NOTNULL SE OBRIGATÓRIO NA VARIAVEL ClasseFormatada
                        campoFormatado += "@NotNull\n";

                    }

                    // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                    campoFormatado += "private " + pCampo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ";\n\n";

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
                campoFormatado
                        // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                        += "@InfoCampo(tipo = FabCampos." + pCampo.getTipoCampo().toString() + ", label = \"" + pCampo.getLabel() + "\", descricao = \"" + pCampo.getDescricao() + "\")\n"
                        // ADICIONA A STRING DE ANOTAÇÃO TEMPORAL NO CAMPO NA VARIAVEL ClasseFormatada
                        + "@Temporal(javax.persistence.TemporalType.DATE)\n";

                // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                if (pCampo.isObrigatorio()) {

                    // ADICIONA A STRING DE ANOTAÇÃO NOTNULL SE OBRIGATÓRIO NA VARIAVEL ClasseFormatada
                    campoFormatado += "@NotNull\n";

                }

                // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                campoFormatado += "private " + pCampo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ";\n\n";

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
                campoFormatado
                        // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                        += "@InfoCampo(tipo = FabCampos." + pCampo.getTipoCampo().toString() + ", label = \"" + pCampo.getLabel() + "\", descricao = \"" + pCampo.getDescricao() + "\")\n";

                // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                if (pCampo.isObrigatorio()) {

                    // ADICIONA A STRING DE ANOTAÇÃO COLUMN DO CAMPO NA VARIAVEL ClasseFormatada
                    campoFormatado += "@Column(length = " + pCampo.getValorMaximo() + ", nullable = true)\n"
                            // ADICIONA A STRING DE ANOTAÇÃO NOTNULL DO CAMPO NA VARIAVEL ClasseFormatada
                            + "@NotNull\n";

                } else {

                    // ADICIONA A STRING DE ANOTAÇÃO COLUMN DO CAMPO NA VARIAVEL ClasseFormatada
                    campoFormatado += "@Column(length = " + pCampo.getValorMaximo() + ", nullable = false)\n";

                }

                // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                campoFormatado += "private " + pCampo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ";\n\n";

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
                campoFormatado
                        // ADICIONA A STRING DE ANOTAÇÃO INFOCAMPO DO CAMPO NA VARIAVEL ClasseFormatada
                        += "@InfoCampo(tipo = FabCampos." + pCampo.getTipoCampo().toString() + ", label = \"" + pCampo.getLabel() + "\", descricao = \"" + pCampo.getDescricao() + "\")\n";

                // VERIFICA SE É UM CAMPO OBRIGATÓRIO
                if (pCampo.isObrigatorio()) {

                    // ADICIONA A STRING DE ANOTAÇÃO NOTNULL DO CAMPO NA VARIAVEL ClasseFormatada
                    campoFormatado += "@NotNull\n";

                }

                // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                campoFormatado += "private " + pCampo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + pCampo.getNomeDeclarado() + ";\n\n";

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
                throw new AssertionError(pCampo.getTipoValor().name());

        }

        return campoFormatado;

    }

    public static String makeDeclaracaoUmParaMuitos(LigacaoUmParaMuitos pEstrutura) {

        String umParaMuitosDeclarado = "";

        umParaMuitosDeclarado += "@InfoCampo(tipo = FabCampos.LISTA, label = \"" + pEstrutura.getLabel() + "\", descricao = \"" + pEstrutura.getDescricao() + "\")\n";

        umParaMuitosDeclarado += "@OneToMany(targetEntity = " + pEstrutura.getNomeEntidade() + ".class, cascade = CascadeType.ALL, orphanRemoval = true)\n";

        umParaMuitosDeclarado += "private List<" + pEstrutura.getNomeEntidade() + ">" + pEstrutura.getNomeDeclarado() + ";\n\n";

        return umParaMuitosDeclarado;
    }

    public static String makeDeclaracaoMuitosParaUm(LigacaoMuitosParaUm pEstrutura) {

        String muitosParaUmDeclarado = "";

        muitosParaUmDeclarado += "@InfoCampo(tipo = FabCampos.LOOKUP, label = \"" + pEstrutura.getLabel() + "\", descricao = \"" + pEstrutura.getDescricao() + "\")\n";

        muitosParaUmDeclarado += "@OneToMany(targetEntity = " + pEstrutura.getNomeEntidade() + ")\n";

        muitosParaUmDeclarado += "private List<" + pEstrutura.getNomeEntidade() + ">" + pEstrutura.getNomeDeclarado() + ";\n\n";

        return muitosParaUmDeclarado;

    }

    public static String makeDeclaracaoMuitosParaMuitos(LigacaoMuitosParaMuitos pEstrutura) {

        String muitosParaMuitosDeclarado = "";

        muitosParaMuitosDeclarado += "@ManyToMany(fetch = FetchType.EAGER)\n";

        muitosParaMuitosDeclarado += "@JoinTable(name = \"" + pEstrutura.getJoinTableName() + "\", joinColumns = @JoinColumn(name = \"" + pEstrutura.getJoinColumName() + "\"), inverseJoinColumns = @JoinColumn(name = \"" + pEstrutura.getInverseJoinColumName() + "\"))\n";

        muitosParaMuitosDeclarado += "private List<" + pEstrutura.getNomeEntidade() + ">" + pEstrutura.getNomeDeclarado() + ";\n\n";

        return muitosParaMuitosDeclarado;

    }

    public static String makeDeclaracaoListas(ListaDeEntidade pLista) {

        String listaFormatada = "";

        listaFormatada += "@Transient\n";

        listaFormatada += "@Lista" + pLista.getNomeEntidade() + "(lista = Listas" + pLista.getNomeEntidade() + "." + pLista.getNomeEnum() + ")\n";

        listaFormatada += "private List<" + pLista.getNomeObjetoListado() + "> " + pLista.getNomeDeclaracao() + ";\n\n";

        return listaFormatada;
    }

    public static String makeDeclaracaoCalculos(CalculoDeEntidade pCalculo) {

        String calculoFormatado = "";

        calculoFormatado += "@InfoCampo(tipo = FabCampos." + pCalculo.getTipoCampo().toString() + ", label = \"" + pCalculo.getLabel() + "\", descricao = \"" + pCalculo.getDescricao() + "\")\n";

        calculoFormatado += "@CalculoCampanha(calculo = Calculos" + pCalculo.getEstruturaPai().getNomeEntidade() + "." + pCalculo.getNomeEnum() + ")\n";

        calculoFormatado += "private " + pCalculo.getTipoValor().getDeclaracaoJava() + " " + pCalculo.getNomeDeclarado() + ";\n\n";

        return calculoFormatado;
    }

    public static String makeDeclaracaoGetSetListas(ListaDeEntidade pLista) {

        String getSetListaFormatados = "";

        getSetListaFormatados += "public List<" + pLista.getNomeObjetoListado() + "> get" + pLista.getNomeDeclaracao() + "{\n\n";

        getSetListaFormatados += "return pedidosEmAtraso;\n\n";

        getSetListaFormatados += "}\n\n";

        return getSetListaFormatados;
    }

    public static String makeDeclaracaoGetSetCalculos(CalculoDeEntidade pCalculo) {

        String getSetCalculoFormatados = "";

        getSetCalculoFormatados += "public " + pCalculo.getTipoValor().getDeclaracaoJava() + " get" + pCalculo.getNomeEnum().toLowerCase() + " {\n\n";

        getSetCalculoFormatados += "Object resultado = getRetornoSoma();\n\n";

        getSetCalculoFormatados += "if (resultado != null) {\n\n";

        getSetCalculoFormatados += "return (" + pCalculo.getTipoValor().getDeclaracaoJava() + ") resultado;\n\n";

        getSetCalculoFormatados += "} else {\n\n";

        getSetCalculoFormatados += "return 0;\n\n";

        getSetCalculoFormatados += "}\n\n";

        return getSetCalculoFormatados;
    }

    public static String makeEntidade(EstruturaDeEntidade pEstrutura) {

        // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
        String classeFormatada = "";

        // VARIAVEL RECEBENDO A STRING DE CRIAÇÃO DO CAMPO
        classeFormatada
                // ADICIONA A STRING DE ANOTAÇÃO ENTITY DA CLASSE NA VARIAVEL ClasseFormata
                += "@Entity\n"
                // ADICIONA A STRING DE ANOTAÇÃO INFOCLASSE DA CLASSE NA VARIAVEL ClasseFormata
                + "@InfoClasse(tags = {" + getTagsDaEntidade(pEstrutura.getTags()) + "}, icone = \"" + pEstrutura.getIcone() + "\", plural = \"" + pEstrutura.getPlural() + "\")\n"
                // ADICIONA A STRING DE DECLARAÇÃO DA CLASSE, SEU NOME E EXTENSÕES NA VARIAVEL ClasseFormata
                + "public class " + pEstrutura.getNomeEntidade() + " extends " + pEstrutura.getTipoEntidade().getNomeClasseEntidade() + " {\n\n";

        for (EstruturaCampo campo : pEstrutura.getCampos()) {

            classeFormatada += makeDeclaracaoCampo(campo);
        }

        for (LigacaoUmParaMuitos pLigacao : pEstrutura.getUmParaMuitos()) {

            classeFormatada += makeDeclaracaoUmParaMuitos(pLigacao);
        }

        for (LigacaoMuitosParaUm pLigacao : pEstrutura.getMuitosParaUm()) {

            classeFormatada += makeDeclaracaoMuitosParaUm(pLigacao);
        }

        for (LigacaoMuitosParaMuitos pLigacao : pEstrutura.getMuitosParaMuitos()) {

            classeFormatada += makeDeclaracaoMuitosParaMuitos(pLigacao);
        }

        for (ListaDeEntidade pLista : pEstrutura.getListas()) {

            classeFormatada += makeDeclaracaoListas(pLista);
        }

        for (CalculoDeEntidade pCalculo : pEstrutura.getCalculos()) {

            classeFormatada += makeDeclaracaoCalculos(pCalculo);

        }

        for (ListaDeEntidade pLista : pEstrutura.getListas()) {

            classeFormatada += makeDeclaracaoGetSetListas(pLista);

        }

        for (CalculoDeEntidade pCalculo : pEstrutura.getCalculos()) {

            classeFormatada += makeDeclaracaoGetSetCalculos(pCalculo);

        }

        for (EstruturaCampo pCampo : pEstrutura.getCampos()) {

            classeFormatada += makeDeclaracaoGetSetCampos(pCampo);

        }

        // ADICIONA O CARACTER } PARA FECHAR A CLASSE
        classeFormatada += "}";

        // RETORNA A VARIAVEL ClasseFormatada COM A STRING DE CRIAÇÃO DA CLASSE
        return classeFormatada;
    }

    public static void criarArquivosDoSistema(List<EstruturaDeEntidade> entidades, List<ItfAcaoDoSistema> acoesDoSistema) {

        /// para obter o caminho da instalação deve se usar o seguinte diretorio:
        String caminhoCodigoGerado = SBCore.getCaminhoDesenvolvimento() + "/codigoGerado";
        String caminhoArquivosClasse = caminhoCodigoGerado + "/java/com/super_bits/model/";
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
        for (EstruturaDeEntidade pEntidade : entidades) {

            String caminhoDiretorio = caminhoArquivosClasse + pEntidade.getNomeEntidade() + "/";
            String nomeArquivoEntidade = pEntidade.getNomeEntidade() + ".java";
            String nomeArquivoLista = "Listas" + pEntidade.getNomeEntidade() + ".java";
            String nomeCalculo = "Calculo" + pEntidade.getNomeEntidade() + ".java";

            UtilSBCoreArquivoTexto.limparArquivoTexto(caminhoDiretorio + nomeArquivoEntidade);
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoDiretorio + nomeArquivoEntidade, makeEntidade(pEntidade));
            UtilSBCoreArquivoTexto.limparArquivoTexto(caminhoDiretorio + nomeArquivoLista);
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoDiretorio + nomeArquivoLista, makeEnumListas(pEntidade));

        }

        for (ItfAcaoDoSistema pAcao : acoesDoSistema) {

            String caminhoFinal = caminhoArquivosClasse + pAcao.getModulo() + "/" + pAcao.getModulo().getNome() + ".java";

            UtilSBCoreArquivoTexto.limparArquivoTexto(caminhoFinal);
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoFinal, makeEnumFabricaDeAcoes(acoesDoSistema, pAcao.getModulo()));

        }

    }

}

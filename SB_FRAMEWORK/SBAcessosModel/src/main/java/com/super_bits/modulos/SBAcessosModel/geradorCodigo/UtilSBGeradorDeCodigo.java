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
                    + " , precisaPermissao = " + pAcao.isPrecisaPermissao() + " , codigoJira = \" " + pAcao.getIdDescritivoJira() + "\" ) ";
        } else if (pAcao.isUmaAcaoGestaoDominio()) {
            return "@InfoTipoAcaoGestaoEntidade(nomeAcao = \" " + pAcao.getNome() + " \", descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \"  \n"
                    + " , xhtmlDaAcao = \" " + ((ItfAcaoFormulario) pAcao).getXhtml() + " \", precisaPermissao = " + pAcao.isPrecisaPermissao() + ", codigoJira = \" " + pAcao.getIdDescritivoJira() + "\" ) ";
        } else if (pAcao.isUmaAcaoFormulario() && !pAcao.isUmaAcaoGestaoDominio()) {
            String campos = "";
            if (!pAcao.getComoFormulario().getGruposDeCampos().isEmpty()) {
                for (GrupoCampos acao : pAcao.getComoFormulario().getGruposDeCampos()) {
                    campos += acao.toString();
                }
            }
            return "@InfoTipoAcaoFormulario(nomeAcao = \" " + pAcao.getNome() + " \",descricao = \" " + pAcao.getDescricao() + " \" , icone = \" " + pAcao.getIconeAcao() + " \"  \n"
                    + " , xhtmlDaAcao = \" " + ((ItfAcaoFormulario) pAcao).getXhtml() + " \",precisaPermissao = " + pAcao.isPrecisaPermissao() + " , codigoJira = \" " + pAcao.getIdDescritivoJira() + " \" ) "
                    + campos + "= {" + pAcao.getComoFormulario().getGruposDeCampos() + "}";
        } else {

            return null;
        }
    }

    public static String makeEnumFabricaDeAcoes(List<ItfAcaoDoSistema> pAcoes, ItfModuloAcaoSistema pModulo) {

        String enumGerado = "@InfoModulosSuperKompras(modulo = FabModuloSuperKompras.ADMINISTRATIVO)\n"
                + "public enum FabAcaoAdministrador implements ItfFabricaAcoes {";

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

        classeFormatada += "@Entity\n" + "@InfoClasse(tags = {\""+ "\"}, icone = \""+"\", plural = \""+"\")";

        for (EstruturaCampo campo : pEstrutura.getCampos()) {

            switch (campo.getTipoValor()) {

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO INTEIRO
                //
                //
                //
                case INTEIRO:

                    // VERIFICA SE O CAMPO É DO TIPO ID
                    if (campo.getTipoCampo().equals(FabCampos.ID)) {

                        // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
                        classeFormatada
                                // ADICIONA A STRING DE ANOTAÇÃO ID DO CAMPO NA VARIAVEL ClasseFormata
                                += "@Id\n"
                                // ADICIONA A STRING DE ANOTAÇÃO GENERATEDVALUE DO CAMPO NA VARIAVEL ClasseFormata
                                + "@GeneratedValue(strategy = GenerationType.AUTO)\n"
                                // ADICIONA STRING DE DECLARAÇÃO DO CAMPO, SEU TIPO E SUA VISUALIZAÇÃO NA VARIAVEL ClasseFormatada
                                + "private " + campo.getTipoCampo().getTipoPrimitivo().getDeclaracaoJava() + " " + campo.getNomeDeclarado() + ";\n\n";

                    } else {

                        // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
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

                    // RETORNA A VARIAVEL ClasseFormatada COM A STRING DO CAMPO CRIADO
                    return classeFormatada;
                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO INTEIRO

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO DATA
                //
                //
                //
                case DATAS:

                    // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
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

                    // RETORNA A VARIAVEL ClasseFormatada COM A STRING DO CAMPO CRIADO
                    return classeFormatada;
                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO DATA

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO LETRAS
                //
                //
                //
                case LETRAS:

                    // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
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

                    // RETORNA A VARIAVEL ClasseFormatada COM A STRING DO CAMPO CRIADO
                    return classeFormatada;
                //
                //
                //
                // FIM DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO LETRAS

                // INICIO DO MODELO GERAL DE CRIAÇÃO DE CAMPOS DO TIPO BOOLEAN
                //
                //
                //
                case BOOLEAN:

                    // VARIAVEL QUE ARMAZENA A STRING DE CRIAÇÃO DO CAMPO
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

                    // RETORNA A VARIAVEL ClasseFormatada COM A STRING DO CAMPO CRIADO
                    return classeFormatada;

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

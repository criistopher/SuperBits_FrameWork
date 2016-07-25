/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaCampo;
<<<<<<< HEAD

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;

=======
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
>>>>>>> ded4b082900c9d8ac2a7ac12645f0b1b05954869
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

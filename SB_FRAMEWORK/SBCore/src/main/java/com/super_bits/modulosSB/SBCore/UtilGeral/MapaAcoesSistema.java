/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.UtilFabricaDeAcoesBasico;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public abstract class MapaAcoesSistema {

    private static final Map<String, ItfAcaoDoSistema> ACAO_BY_NOME_UNICO = new HashMap<>();
    private static final Map<ItfFabricaAcoes, ItfAcaoDoSistema> ACAO_BY_ENUM = new HashMap<>();
    private static final Map<Class, List<ItfAcaoDoSistema>> ACOES_BY_CLASSE = new HashMap<>();
    private static final Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> ACOES_BY_MODULO = new HashMap<>();
    private static final Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> ACOES_MANAGED_BEAN_BY_MODULO = new HashMap<>();
    private static final Map<ItfAcaoGerenciarEntidade, List<ItfAcaoSecundaria>> SUBACOES_BY_ACAO_GERENCIAR_MB = new HashMap<>();

    private static final Map<String, List<ItfAcaoDoSistema>> ACOES_BY_DOMINIO = new HashMap<>();
    private static boolean mapaCriado = false;

    /// cria mapeamentos relacionais dos Objetos todos os (OneToMany) Ex: adiciona as açoes do modulo no objeto Modulo
    private static void buildRelacoes() {

        for (ItfModuloAcaoSistema modulosEncontrado : ACOES_BY_MODULO.keySet()) {
            if (modulosEncontrado.getAcoes().isEmpty()) {
                modulosEncontrado.getAcoes().addAll(ACOES_BY_MODULO.get(modulosEncontrado));
            }
        }

        for (ItfAcaoGerenciarEntidade acao : SUBACOES_BY_ACAO_GERENCIAR_MB.keySet()) {
            if (acao.getAcoesVinculadas().isEmpty()) {
                acao.setAcoesVinculadas(SUBACOES_BY_ACAO_GERENCIAR_MB.get(acao));
            }
        }

    }

    public static void makeMapaAcoesSistema() {
        if (mapaCriado) {
            return;
        }

        if (SBCore.getFabricasDeAcaoDoSistema() == null) {
            throw new UnsupportedOperationException("O mapa de ações não pode ser definido, pos as Fabricas de ações do core não foram definidas");
        }

        for (Class fabrica : SBCore.getFabricasDeAcaoDoSistema()) {

            for (Object objAcao : fabrica.getEnumConstants()) {
                ItfFabricaAcoes fabricaAcao = (ItfFabricaAcoes) objAcao;
                ItfAcaoDoSistema acao = fabricaAcao.getAcaoDoSistema();

                List<ItfAcaoDoSistema> acoesDoModulo = ACOES_BY_MODULO.get(acao.getModulo());
                List<ItfAcaoDoSistema> acoesManagedBeansDoModulo = ACOES_MANAGED_BEAN_BY_MODULO.get(acao.getModulo());
                UtilFabricaDeAcoesBasico.validaIntegridadeAcaoDoSistema(acao);
                /// ADICIONANDO MAPAS SIMPLES
                ACAO_BY_NOME_UNICO.put(acao.getNomeUnico(), acao);
                ACAO_BY_ENUM.put(fabricaAcao, acao);

                if (acoesDoModulo == null) {
                    acoesDoModulo = new ArrayList();
                    acoesManagedBeansDoModulo = new ArrayList<>();
                    ACOES_BY_MODULO.put(acao.getModulo(), acoesDoModulo);
                    ACOES_MANAGED_BEAN_BY_MODULO.put(acao.getModulo(), acoesManagedBeansDoModulo);
                }

                acoesDoModulo.add(acao);
                if (acao.isUmaAcaoGestaoDominio()) {
                    acoesManagedBeansDoModulo.add(acao);

                } else if (acao.isTemAcaoPrincipal()) {
                    ItfAcaoGerenciarEntidade acaoprincipal = (ItfAcaoGerenciarEntidade) ACAO_BY_ENUM.get(((ItfAcaoSecundaria) acao).getAcaoPrincipal().getEnumAcaoDoSistema());
                    if (acaoprincipal == null) {
                        throw new UnsupportedOperationException(
                                "A acaoPrincipal: " + ((ItfAcaoSecundaria) acao).getAcaoPrincipal().getNomeUnico() + "deve ser declarada antes de " + acao.getNomeUnico() + " no enum (Coloque as açoes _MB na frente das subAções, "
                                + "e certifique que o dominio e o inicio do nome da ação estão sendo coincidentes entre ações e subações)");
                    }
                    if (SUBACOES_BY_ACAO_GERENCIAR_MB.get(((ItfAcaoSecundaria) acao).getAcaoPrincipal()) == null) {
                        List<ItfAcaoSecundaria> subacoesDomodulo = new ArrayList();
                        SUBACOES_BY_ACAO_GERENCIAR_MB.put(acaoprincipal, subacoesDomodulo);

                    }

                    SUBACOES_BY_ACAO_GERENCIAR_MB.get(acaoprincipal).add((ItfAcaoSecundaria) acao);
                }

                if (ACOES_BY_DOMINIO.get(acao.getNomeDominio()) == null) {
                    List<ItfAcaoDoSistema> acoesporDominio = new ArrayList<>();
                    ACOES_BY_DOMINIO.put(acao.getNomeDominio(), acoesporDominio);

                }
                ACOES_BY_DOMINIO.get(acao.getNomeDominio()).add(acao);

                if (acao.isUmaAcaoDeEntidade()) {
                    ItfAcaoEntidade acaodeEntidade = (ItfAcaoEntidade) acao;
                    Class classeRelacionada = acaodeEntidade.getClasseRelacionada();
                    List<ItfAcaoDoSistema> acoesDaEntidade = ACOES_BY_CLASSE.get(classeRelacionada);
                    if (acoesDaEntidade == null) {
                        acoesDaEntidade = new ArrayList<>();
                        ACOES_BY_CLASSE.put(classeRelacionada, acoesDaEntidade);

                    }
                    acoesDaEntidade.add(acao);

                }

            }

        }
        buildRelacoes();
        mapaCriado = true;
    }

    /**
     *
     * Retorna todas as ações Referentes a Entidade
     *
     *
     * @param pEntidade Entidade referencia
     * @return Todas as ações da Entidade
     */
    public static List<ItfAcaoDoSistema> getAcoesByEntidade(Class pEntidade) {
        return ACOES_BY_CLASSE.get(pEntidade);
    }

    /**
     *
     * Um domínio corresponde a primeira parte do enum da ação antes das chaves
     * principais de identificação
     *
     *
     * @param pDominio O domimio referencia para seleção
     * @param modulo O módulo referencia para seleção
     * @return Todas as ações que possuem o dominio enviado
     */
    public static List<ItfAcaoDoSistema> getAcoesByDominioEModulo(String pDominio, ItfModuloAcaoSistema modulo) {
        List<ItfAcaoDoSistema> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_DOMINIO.get(pDominio)) {
            if (acao.getModulo().equals(acao.getModulo())) {
                lista.add(acao);
            }
        }
        return lista;

    }

    /**
     *
     * Retorna todas as ações de Controller a partir da referencia de uma
     * entidade (que implementam alguma alteração no sistema)
     *
     * @param pEntidade A entidade referenciada
     * @return todas as ações controller da entidade
     */
    public static List<ItfAcaoController> getAcoesControllersByEntidade(Class pEntidade) {
        List<ItfAcaoController> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.isUmaAcaoController()) {
                lista.add((ItfAcaoController) acao);
            }
        }
        return lista;
    }

    /**
     *
     * Retorna todas as ações referentes a uma entidade que são de determinado
     * módulo
     *
     * @param pEntidade Entidade Referenciada
     * @param pModulo T Modulo Referencia
     * @return Todas as ações do tipo controller que são da entidade e do modulo
     * referenciados
     */
    public static List<ItfAcaoController> getAcoesControllerByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {
        List<ItfAcaoController> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.getModulo().equals(pModulo)) {
                lista.add((ItfAcaoController) acao);
            }
        }
        return lista;
    }

    /**
     *
     * Retorna todos os formulários de listagem da entidade por modulo
     *
     * @param pEntidade A Entidade referenciada
     * @param pModulo Modulo Referenciado
     * @return Todos foromularios de listagem referenciados pela entidade e
     * modulo
     */
    public static List<ItfAcaoFormulario> getAcoesListagemByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {
        List<ItfAcaoFormulario> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.isUmaAcaoFormulario()) {
                if (acao.getTipoAcaoGenerica() == FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR) {
                    lista.add((ItfAcaoFormulario) acao);
                }
            }
        }
        return lista;
    }

    /**
     *
     * Retorna a ação a partir de uma Fabrica
     *
     * @param pFabAcao Fabrica refencia
     * @return Ação do Sistema instanciada
     */
    public static ItfAcaoDoSistema getAcaoDoSistema(ItfFabricaAcoes pFabAcao) {
        return ACAO_BY_ENUM.get(pFabAcao);
    }

    /**
     *
     * REtorna uma ação de gestão de entidade a partir de uma fabrica <br>
     * caso uma ação
     *
     * @param pFabAcao
     * @return Ação de
     */
    public ItfAcaoEntidade getAcaoDeEntidade(ItfFabricaAcoes pFabAcao) {

        try {
            return (ItfAcaoEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;

    }

    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoFormularioEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de Entidade Formulario", t);
        }
        return null;
    }

    public ItfAcaoControllerEntidade getAcaoEntidadeController(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoControllerEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public ItfAcaoController getAcaoController(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoController) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoGerenciarEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public static List<ItfModuloAcaoSistema> getModulos() {

        List<ItfModuloAcaoSistema> modulosEncontrados = new ArrayList<>();
        modulosEncontrados.addAll(ACOES_BY_MODULO.keySet());

        return modulosEncontrados;

    }

    /**
     *
     *
     * @return Se o Mapa já foi criado ou não
     */
    public static boolean isMapaCriado() {
        return mapaCriado;
    }

}

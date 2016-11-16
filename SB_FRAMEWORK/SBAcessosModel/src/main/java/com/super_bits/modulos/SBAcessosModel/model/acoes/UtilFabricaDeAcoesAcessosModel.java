/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringEnumECaixaAlta;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_DESATIVAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_PERSONALIZADO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_REMOVER;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_EDICAO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_MODO_MERGE;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_NOVO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_PERSONALIZADO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica.SELECAO_DE_ACAO;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilFabricaDeAcoesAcessosModel {

    public static void configFormulario(ItfAcaoDoSistema acao, String formulario) {
        try {
            ItfAcaoFormulario acaoformulario = (ItfAcaoFormulario) acao;
            acaoformulario.setXhtml(formulario);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro setando formulario para ação generica" + acao, t);
        }

    }

    /**
     *
     * Cria o modulo da ação apartir da anotação Info Modulo
     *
     * @param pAcao
     * @return
     */
    public static ModuloAcaoSistema getModuloByFabrica(ItfFabricaAcoes pAcao) {
        try {
            ItfFabricaModulo fabModulo = (ItfFabricaModulo) UtilSBCoreReflexao.getFabricaDaClasseByAnotacao(pAcao.getClass(), "modulo", true);

            ModuloAcaoSistema moduloDaAcao = (ModuloAcaoSistema) fabModulo.getModulo();

            if (moduloDaAcao == null) {
                throw new UnsupportedOperationException("A Fabrica de ações não foi anodada com InfoModulo" + pAcao.getClass().getSimpleName());
            }

            moduloDaAcao.setId(pAcao.getClass().getSimpleName().hashCode());

            return moduloDaAcao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo modulo da Ação pela anotação infomodulo, você anotou o nome do modulo na fabrica de ações?", t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Obtendo modulo da Ação pela anotação infomodulo, você anotou o nome do modulo na fabrica de ações?", t);
        }
        return null;
    }

    public static String getNomeDominio(ItfFabricaAcoes pAcao) {

        String nomeAcao = pAcao.toString();
        String dominio = nomeAcao.split("_")[0];
        return dominio;
    }

    public static List<ItfFabricaAcoes> getSubAcoesDaAcaoPrincipal(ItfFabricaAcoes pAcaoPrincipal) {
        List<ItfFabricaAcoes> acoes = new ArrayList<>();
        String nomeDominio = getNomeDominio(pAcaoPrincipal);
        for (ItfFabricaAcoes acao : pAcaoPrincipal.getClass().getEnumConstants()) {

            // Verificando se a ação inicia igual
            if (nomeDominio.equals(getNomeDominio(acao))) {
                //verificando se os dois possuem a mesma classe
                if (acao.getEntidadeDominio().equals(pAcaoPrincipal.getEntidadeDominio())) {
                    if (acao != pAcaoPrincipal) {
                        acoes.add(acao);
                    }
                }
            }
        }
        return acoes;

    }

    /**
     *
     * Retorna o tipo de ação generica de acordo com a nomeclatura <br>
     * Ex: <br>
     * MB Ação managed Bean FRMAÇÃO DE FORMULARIO <br>
     * FRM_NOVO <br>
     * FRM_EDITAR <br>
     * FRM_VISUALIZAR <br>
     * CTR AÇÃO CAMADA CONTROLLER <br>
     * CTR_ALTERAR_STATUS <br>
     * CTR_SALVAR_MERGE  <br>
     * CTR_REMOVER <br>
     *
     * @param pFabrica Fabrica de ação referencia
     * @return o Tipo de ação generica de acordo com a fabrica referenia enviada
     */
    public static FabTipoAcaoSistemaGenerica getTipoAcaoByNome(ItfFabricaAcoes pFabrica) {

        return FabTipoAcaoSistemaGenerica.getAcaoGenericaByNome(pFabrica.toString());

    }

    public static ItfFabricaAcoes getAcaoPrincipalDoDominio(ItfFabricaAcoes pAcao) {
        try {
            Class classeDominioDaAcao = pAcao.getEntidadeDominio();

            if (pAcao.toString().contains("_MB")) {
                return null;
            }
            // Metodo um Para determinar Ação de Gestão: Mesma Classe de Dominio na mesma fabrica de ação
            for (ItfFabricaAcoes acao : pAcao.getClass().getEnumConstants()) {
                // verifica se a classe de dominio é a mesma da ação enviada
                Class dominioDaAcaoAtual = acao.getEntidadeDominio();
                if (dominioDaAcaoAtual != null) {
                    if (dominioDaAcaoAtual.getName().equals(classeDominioDaAcao.getName())) {
                        // verifica se alem de ser o mesmo dominio possui o MB
                        if (acao.toString().contains("_MB")) {
                            return acao;
                        }
                    }
                }
            }

            // Metodo 2 Mesmo inicio de nome
            //criando mapa de nomes
            Map<String, ItfFabricaAcoes> mapaDeAcoes = new HashMap<>();
            for (ItfFabricaAcoes acao : pAcao.getClass().getEnumConstants()) {
                String inicioDoNome = UtilSBCoreStrings.getStringAteEncontrarIsto(acao.toString(), "_MB");
                if (inicioDoNome != null) {
                    mapaDeAcoes.put(inicioDoNome, acao);
                }
            }
            for (String prefixo : mapaDeAcoes.keySet()) {
                if (pAcao.toString().startsWith(prefixo)) {
                    return mapaDeAcoes.get(prefixo);
                }

            }
            // Determinado tipo de Acao

            return null;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando a ação principal da ação" + pAcao, t);

        }
        return null;

    }

    private static void configuraAnotacao(AcaoDoSistema pAcao, InfoTipoAcaoController pAnotacaoController, Class pEntidadeVinculada) {
        configurarValorBasicoAcaoAnotacao(pAcao, pAnotacaoController.nomeAcao(), pAnotacaoController.descricao(), pAnotacaoController.precisaPermissao(), pEntidadeVinculada);
        configurarValorIconeAnotacao(pAcao, pAnotacaoController.icone(), pAnotacaoController.iconeFonteAnsowame(), null);
        configurarValorFormularioAnotacao(pAcao, pAnotacaoController.xhtmlDaAcao());
        configurarValorJiraConfluenceAnotacao(pAcao, pAnotacaoController.codigoJira());
    }

    private static void configuraAnotacao(AcaoDoSistema pAcao, InfoTipoAcaoFormulario pAnotacaoFormulario, Class pEntidadeVinculada) {
        configurarValorBasicoAcaoAnotacao(pAcao, pAnotacaoFormulario.nomeAcao(), pAnotacaoFormulario.descricao(), pAnotacaoFormulario.precisaPermissao(), pEntidadeVinculada);
        configuraValoresCampos(pAcao, pAnotacaoFormulario.campos());
        configurarValorIconeAnotacao(pAcao, pAnotacaoFormulario.icone(), pAnotacaoFormulario.iconeFonteAnsowame(), null);
        configurarValorFormularioAnotacao(pAcao, pAnotacaoFormulario.xhtmlDaAcao());
        configurarValorJiraConfluenceAnotacao(pAcao, pAnotacaoFormulario.codigoJira());
        switch (pAcao.getTipoAcaoGenerica()) {
            // TODO permitir encontrar CaminhCampo pelo nome do método (Verificar viabilidade)
            case FORMULARIO_NOVO_REGISTRO:
                break;
            case FORMULARIO_EDITAR:
                break;

            case FORMULARIO_VISUALIZAR:
                break;
            case FORMULARIO_LISTAR:
                //       CaminhoCampoReflexao id = UtilSBCoreReflexaoCampos.getCaminhoByStringRelativaEClasse("id", pAcao.getEnumAcaoDoSistema().getEntidadeDominio());
                //       ((ItfAcaoFormularioEntidade) pAcao).getCampos().add(id);
                //      CaminhoCampoReflexao nome = UtilSBCoreReflexaoCampos.getCaminhoByStringRelativaEClasse("nome", pAcao.getEnumAcaoDoSistema().getEntidadeDominio());
                //     ((ItfAcaoFormularioEntidade) pAcao).getCampos().add(nome);
                break;

        }
    }

    private static void configuraAnotacao(AcaoDoSistema pAcao, InfoTipoAcaoGestaoEntidade pAnotacaoGestao, Class pEntidadeVinculada) {

        configurarValorBasicoAcaoAnotacao(pAcao, pAnotacaoGestao.nomeAcao(), pAnotacaoGestao.descricao(), pAnotacaoGestao.precisaPermissao(), pEntidadeVinculada);
        configurarValorIconeAnotacao(pAcao, pAnotacaoGestao.icone(), pAnotacaoGestao.iconeFonteAnsowame(), pEntidadeVinculada);
        configurarValorFormularioAnotacao(pAcao, pAnotacaoGestao.xhtmlDaAcao());
        configurarValorJiraConfluenceAnotacao(pAcao, pAnotacaoGestao.codigoJira());

    }

    private static void configurarValorIconeAnotacao(AcaoDoSistema pAcaoDoSistema, String pIconeSTRPersonalizado, FabIconeFontAwesome pIconeFA, Class pClasseEntidade) {

        // configura o icone seguindo a seguinte prioridade (String personalizada, IconeViaFabricaDeação,E icone da entidade vinculada, este utilizado apenas em ações de gestão..)
        if (pIconeSTRPersonalizado != null && pIconeSTRPersonalizado.length() > 2) {
            pAcaoDoSistema.setIconeAcao(pIconeSTRPersonalizado);
        } else if (pIconeFA != null && pIconeFA != FabIconeFontAwesome.ICONE_PERSONALIZADO) {
            pAcaoDoSistema.setIconeAcao(pIconeFA.getIcone().getTagHtml());
        } else if (pClasseEntidade != null) {
            String iconeDaClasse = UtilSBCoreReflexao.getIconeDoObjeto(pClasseEntidade);
            if (iconeDaClasse != null) {
                pAcaoDoSistema.setIconeAcao(iconeDaClasse);
            }

        }
    }

    private static void configurarValorFormularioAnotacao(AcaoDoSistema pAcaoDoSistema, String pFormularioAnotacao) {
        if (pFormularioAnotacao != null && pFormularioAnotacao.length() > 2) {
            ((ItfAcaoFormulario) pAcaoDoSistema).setXhtml(pFormularioAnotacao);
        }
    }

    private static void configurarValorJiraConfluenceAnotacao(AcaoDoSistema pAcaoDoSistema, String pCodigoJira) {
        if (pCodigoJira != null && pCodigoJira.length() > 2) {
            pAcaoDoSistema.setIdDescritivoJira(pCodigoJira);
        }

    }

    private static void configurarValorBasicoAcaoAnotacao(AcaoDoSistema pAcaoDoSistema, String pNomeAcao, String pDescricao, boolean pAcessoPermitido, Class classeEntidade) {

        if (pNomeAcao != null && pNomeAcao.length() > 2) {
            pAcaoDoSistema.setNomeAcao(pNomeAcao);
        }
        if (pDescricao != null && pDescricao.length() > 2) {
            pAcaoDoSistema.setDescricao(pDescricao);
        } else {
            pAcaoDoSistema.setDescricao(pNomeAcao);
        }

        if (pAcaoDoSistema.isUmaAcaoDeEntidade()) {
            if (pAcaoDoSistema.isUmaAcaoFormulario()) {
                pAcaoDoSistema.getComoFormularioEntidade().setClasseRelacionada(classeEntidade);
            }

        }
        pAcaoDoSistema.setPrecisaPermissao(pAcessoPermitido);

    }

    private static void configuraValoresCampos(AcaoDoSistema pAcaoDoSistema, String[] campos) {

        if (campos != null && campos.length > 0) {

            for (String cp : campos) {
                try {
                    if (cp.equals("")) {
                        throw new UnsupportedOperationException("Existe um campo em branco (igual a: [ \"\" ]) na ação" + pAcaoDoSistema.getNomeUnico());
                    }
                    Class classeEntidade = pAcaoDoSistema.getEnumAcaoDoSistema().getEntidadeDominio();
                    try {
                        ItfAcaoEntidade acao = (ItfAcaoEntidade) pAcaoDoSistema;
                        classeEntidade = acao.getClasseRelacionada();
                    } catch (Throwable t) {

                    }
                    CaminhoCampoReflexao caminhoCampo = UtilSBCoreReflexaoCampos.getCaminhoByStringRelativaEClasse(cp, classeEntidade);
                    if (caminhoCampo == null) {
                        throw new UnsupportedOperationException("Erro Configurando campos da ação a partir de anotações ,verifique os campos  anotados em: " + pAcaoDoSistema.getNomeUnico());
                    }
                    ((ItfAcaoFormularioEntidade) pAcaoDoSistema).getCampos().add(caminhoCampo);

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campo: " + cp + " da anotação " + pAcaoDoSistema.getNomeUnico(), t);
                }
            }
            // adiciona campos nas anotações
        }

    }

    private static void configurarAnotacoesAcao(AcaoDoSistema pAcao) throws NoSuchFieldException {

        Field campo = pAcao.getEnumAcaoDoSistema().getClass().getField(pAcao.getEnumAcaoDoSistema().toString());
        String textoAnotacoes = "Utilize: Para ação controller: @" + InfoTipoAcaoController.class.getSimpleName()
                + " para  ação formulario: @" + InfoTipoAcaoFormulario.class.getSimpleName()
                + " para ação de gestão:  @" + InfoTipoAcaoGestaoEntidade.class.getSimpleName();
        FabTipoAcaoSistemaGenerica tipoAcao = getTipoAcaoByNome(pAcao.getEnumAcaoDoSistema());
        Class entidadeDaAcao = pAcao.getEnumAcaoDoSistema().getEntidadeDominio();
        if (tipoAcao.toString().contains("FORMULARIO")) {
            InfoTipoAcaoFormulario anotacaoFormulario = campo.getAnnotation(InfoTipoAcaoFormulario.class);

            if (anotacaoFormulario != null) {
                if (!anotacaoFormulario.entidade().equals(Void.class)) {
                    entidadeDaAcao = anotacaoFormulario.entidade();
                    if (entidadeDaAcao.getSimpleName().equals("CodigoDeAcesso")) {
                        System.out.println("Aqui");
                    }
                }

                configuraAnotacao(pAcao, anotacaoFormulario, entidadeDaAcao);
            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro  anotação ou nome de ação incompatível em " + pAcao.getNomeUnico() + textoAnotacoes);
            }

        }

        if (tipoAcao.toString().contains("CONTROLLER")) {
            InfoTipoAcaoController anotacaocontroller = campo.getAnnotation(InfoTipoAcaoController.class);

            if (anotacaocontroller != null) {
                if (!anotacaocontroller.entidade().equals(Void.class)) {
                    entidadeDaAcao = anotacaocontroller.entidade();
                }
                configuraAnotacao(pAcao, anotacaocontroller, entidadeDaAcao);
            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro anotação ou nome de ação incompatível anotação  em " + pAcao.getNomeUnico() + " " + textoAnotacoes);
            }
        }

        if (tipoAcao.toString().contains("GERENCIAR")) {
            InfoTipoAcaoGestaoEntidade anotacaoGerenciar = campo.getAnnotation(InfoTipoAcaoGestaoEntidade.class);

            if (anotacaoGerenciar != null) {
                if (!anotacaoGerenciar.entidade().equals(Void.class)) {
                    entidadeDaAcao = anotacaoGerenciar.entidade();
                }
                configuraAnotacao(pAcao, anotacaoGerenciar, entidadeDaAcao);
            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro anotação ou nome de ação incompatível em " + pAcao.getNomeUnico() + "" + textoAnotacoes);
            }

        }

    }

    /**
     *
     * @param pAcao Cria uma nova ação do sistema de forma automática por
     * reflexão
     * @return
     */
    public static ItfAcaoDoSistema getNovaAcao(
            ItfFabricaAcoes pAcao) {

        // Verificando se a ação já foi criada
        if (MapaAcoesSistema.isMapaCriado()) {
            AcaoDoSistema acao = (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistema(pAcao);
            if (acao == null) {
                throw new UnsupportedOperationException("O Mapa de ações foi criado, mas a ação procurada não foi encontra, certifique que a ação chamada esteja no core., a ação foi: " + pAcao.getClass().getSimpleName() + "." + pAcao);
            }

            return acao;
        }

        FabTipoAcaoSistemaGenerica pTipoAcaoGenerica = getTipoAcaoByNome(pAcao);

        ItfFabricaAcoes fabricaDadoPrincipal = getAcaoPrincipalDoDominio(pAcao);
        ItfAcaoGerenciarEntidade pAcaoPrincipal = null;
        try {
            if (fabricaDadoPrincipal != null) {

                pAcaoPrincipal = fabricaDadoPrincipal.getAcaoDoSistema().getComoGestaoEntidade();
            }

            if (pAcaoPrincipal == null) {
                switch (pTipoAcaoGenerica) {
                    case FORMULARIO_NOVO_REGISTRO:
                    case FORMULARIO_EDITAR:
                    case FORMULARIO_PERSONALIZADO:
                    case FORMULARIO_VISUALIZAR:
                    case FORMULARIO_LISTAR:
                    case FORMULARIO_MODAL:
                    case CONTROLLER_PERSONALIZADO:

                        throw new UnsupportedOperationException("A ação " + pAcao + " deveria ter uma ação principal vinculada, por ser do tipo " + pTipoAcaoGenerica);

                }

            }

            AcaoDoSistema novaAcao = null;

            String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjetoPorAnotacaoInfoClasse(pAcao.getEntidadeDominio());
            ItfAcaoFormularioEntidade novaAcaoRefForm;

            AcaoGestaoEntidade acaoPrincipal;
            // Setar o id do metodo aqui??? #Todo pensar nessa ideia,
            // afinal deve ser negado o direito de exeutar o código do sistema antes de criar os métodos do controller?
            ItfAcaoController novaAcaoRefController;
            switch (pTipoAcaoGenerica) {
                case FORMULARIO_NOVO_REGISTRO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    acaoPrincipal = (AcaoGestaoEntidade) novaAcaoRefForm.getAcaoPrincipal();
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcaoRefForm.setIconeAcao("fa fa-plus");
                    novaAcao.setTipoAcaoGenerica(pTipoAcaoGenerica);
                    novaAcao.setNome("Novo " + nomeDoObjeto);
                    novaAcao.setDescricao("Novo " + nomeDoObjeto);
                    if (acaoPrincipal.isUtilizarMesmoFormEditarInserir()) {
                        novaAcaoRefForm.setXhtml(FORMULARIO_EDITAR.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    } else {
                        novaAcaoRefForm.setXhtml(FORMULARIO_NOVO_REGISTRO.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    }

                    break;
                case FORMULARIO_EDITAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.setTipoAcaoGenerica(pTipoAcaoGenerica);
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcao.setNome("Editar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(FORMULARIO_EDITAR.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    novaAcao.setDescricao("Editar " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-edit");
                    break;
                case FORMULARIO_LISTAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.setTipoAcaoGenerica(pTipoAcaoGenerica);
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);

                    novaAcao.setNome("Listar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(FORMULARIO_LISTAR.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    novaAcao.setDescricao("Editar " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-list-alt");
                    break;
                case FORMULARIO_VISUALIZAR:

                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    acaoPrincipal = (AcaoGestaoEntidade) novaAcaoRefForm.getAcaoPrincipal();

                    novaAcao.setNome("Visualizar " + nomeDoObjeto);
                    if (acaoPrincipal.isUtilizarMesmoFormEditarInserir()) {
                        novaAcaoRefForm.setXhtml(FORMULARIO_EDITAR.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    } else {
                        novaAcaoRefForm.setXhtml(FORMULARIO_VISUALIZAR.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    }
                    novaAcao.setDescricao("Visualizar " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-eye");
                    break;
                case FORMULARIO_PERSONALIZADO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.setNome(
                            UtilSBCoreStringEnumECaixaAlta.getUltimaParteNomeEnumPrimeiraEmMaiusculo((Enum) novaAcao.getEnumAcaoDoSistema())
                            + " " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(FORMULARIO_PERSONALIZADO.getnomeXHTMLPadrao(novaAcao.getEnumAcaoDoSistema()));
                    break;

                case CONTROLLER_SALVAR_EDICAO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar edição do(a) " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-save");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    break;
                case CONTROLLER_SALVAR_NOVO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-save");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    break;
                case CONTROLLER_SALVAR_MODO_MERGE:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-save");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_ATIVAR_DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Alterar status " + nomeDoObjeto);
                    novaAcao.setDescricao("Alterar status do " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-retweet");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    break;
                case CONTROLLER_ATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Ativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Ativar " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-check");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    break;
                case CONTROLLER_DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Desativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Desativar " + nomeDoObjeto);
                    novaAcao.setIconeAcao("fa fa-close");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;

                case SELECAO_DE_ACAO:
                    break;
                case FORMULARIO_MODAL:
                    break;
                case GERENCIAR_DOMINIO:
                    novaAcao = new AcaoGestaoEntidade(pAcao, pAcao.getEntidadeDominio(), "gerenciar.xhtml");
                    AcaoGestaoEntidade novaAcaoGestao = (AcaoGestaoEntidade) novaAcao;
                    novaAcaoGestao.setEnumAcoesVinculadas(getSubAcoesDaAcaoPrincipal(pAcao));
                    break;
                case CONTROLLER_REMOVER:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome("Excluir " + nomeDoObjeto);
                    break;
                case CONTROLLER_PERSONALIZADO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.setNome(
                            UtilSBCoreStringEnumECaixaAlta.getUltimaParteNomeEnumPrimeiraEmMaiusculo((Enum) novaAcao.getEnumAcaoDoSistema())
                            + " " + nomeDoObjeto);

                    break;

                default:
                    throw new AssertionError("Este tipo de ação não parece ser uma ação secundária" + pTipoAcaoGenerica.name());

            }
            if (novaAcao == null) {
                throw new UnsupportedOperationException("Não foi possível determinar um constructor para a acao " + pAcao.toString() + " verifique a nomeclatura de acordo com a documentação e tente novamente");
            }
            if (novaAcao.getIconeAcao() == null) {
                novaAcao.setIconeAcao(pTipoAcaoGenerica.getIconePadrao());
            }
            configurarAnotacoesAcao((AcaoDoSistema) novaAcao);
            return novaAcao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando ação automática por nome:" + t.getMessage(), t);
        }
        throw new UnsupportedOperationException("Erro Criando ação do sistema >>" + pAcao);
    }

    public static AcaoDoSistema criaAcaodoSistemaPorTipoAcao(FabTipoAcaoSistemaGenerica tipoDeAcao) {

        AcaoDoSistema acao = new AcaoDoSistema();

        acao.setTipoAcaoGenerica(tipoDeAcao);
        return acao;

    }

}

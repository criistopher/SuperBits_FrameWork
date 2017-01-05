/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao.Resposta;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

/**
 *
 *
 *
 * Classe para extender as ações do sistema da camada Controller do modelo MVC
 *
 *
 * @author Salvio
 */
public abstract class ControllerAppAbstratoSBCore implements ItfControlerAPP {

    private static final Map<Integer, ItfPermissao> PERMISSAO_POR_ACAO_ID = new HashMap<>();
    private static final Map<Integer, Integer> IDPERMISSAO_POR_METODOID = new HashMap<>();
    private static final Map<String, ItfUsuario> USUARIOS = new HashMap<>();

    public static ItfUsuario getUsuarioByEmail(String pEmail) {
        if (USUARIOS == null || USUARIOS.isEmpty()) {
            reloadAcessos();
        }
        return USUARIOS.get(pEmail);
    }

    /**
     *
     * Instancia uma nova resposta
     *
     * @param pTipoRetorno Classe esperada para o objeto de retorno
     * @return A nova resposta instanciada
     */
    protected static ItfResposta getNovaResposta(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));

        return resp;
    }

    /**
     *
     * Instancia uma nova resposta com erro inesperado
     *
     * @param pTipoRetorno Classe esparada para objeto de retorno
     * @return Uma nova resposta já com um erro inesperado adicionado 'a lista
     * de mensagem
     */
    protected static ItfResposta getRespostaErroInesperado(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));
        ItfAcaoDoSistema acao = getAcaoDoMetodo();
        resp.addErro("Erro inesperado executando  " + acao.getNomeAcao() + " o suporte foi notificado, você pode nos escrever sobre isso caso queira, obrigado");
        return resp;
    }

    /**
     *
     * Instancia uma nova resposta, e caso o usuário logado não possua permissão
     * adiciona uma mensagem de erro
     *
     * @param pTipoRetorno Tipo de retorno esperado
     * @return a nova resposta
     */
    protected static ItfResposta getNovaRespostaComAutorizacao(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, null);
        addMensagemDeAutorizacao(resp);
        return resp;
    }

    /**
     *
     * @return Resposta com o erro não implementado
     */
    protected ItfResposta getNovaRespostaNaoImplementado() {
        Resposta naoImplementado = new Resposta(null, null);
        return naoImplementado.addMensagemErroDisparaERetorna("Os algorítimos para [" + getMetodoChamado() + "] do modulo [" + this.getClass().getSimpleName() + "] não foi implementado.");
    }

    protected List<ItfPermissao> carregaAcessos() {
        return SBCore.getConfiguradorDePermissao().configuraPermissoes();
    }

    public static ItfPermissao getPermissaoPorAcao(ItfAcaoDoSistema pAcao) {
        return getPermissoes().get(pAcao.getId());
    }

    protected static Map<Integer, ItfPermissao> getPermissoes() {
        if (PERMISSAO_POR_ACAO_ID == null || PERMISSAO_POR_ACAO_ID.isEmpty()) {
            reloadAcessos();
        }
        return PERMISSAO_POR_ACAO_ID;
    }

    protected static ItfPermissao getPermissaoByMethodID(Integer pMethodID) {
        if (IDPERMISSAO_POR_METODOID == null || IDPERMISSAO_POR_METODOID.isEmpty()) {
            reloadAcessos();
        }
        Integer idPermissao = IDPERMISSAO_POR_METODOID.get(pMethodID);
        if (idPermissao != null) {
            return getPermissoes().get(idPermissao);
        } else {
            FabErro.PARA_TUDO.paraSistema("Nennhuma permissão foi encontrada vinculada a este método", null);
            return null;
        }

    }

    private static String buildNomeClasse() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String nomeMetodo = stackTraceElements[3].getMethodName();
        String nomeClasse = stackTraceElements[3].getClassName();
        String[] classeFull = nomeClasse.split("\\.");

        nomeClasse = classeFull[classeFull.length - 1];

        return nomeClasse + "." + nomeMetodo;
    }

    protected static Method getMetodoChamado() {

        int inicioPesquisaMetodo = 3;

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();
        int idfinalStacktrace = stackTraceTodosMetodos.length - 1;
        if (inicioPesquisaMetodo >= stackTraceTodosMetodos.length) {
            inicioPesquisaMetodo = stackTraceTodosMetodos.length - 1;
        }
        for (int i = inicioPesquisaMetodo; (i <= idfinalStacktrace); i++) {
            final StackTraceElement stackTrhaceMetodoAtual = stackTraceTodosMetodos[i];
            final String methodName = stackTrhaceMetodoAtual.getMethodName();
            final String className = stackTrhaceMetodoAtual.getClassName();

            Class<?> classedoMetodo;
            try {
                classedoMetodo = Class.forName(className);

                do {
                    for (final Method candidate : classedoMetodo.getDeclaredMethods()) {
                        if (candidate.getName().equals(methodName)) {

                            if (UtilSBController.possuiMetodoDeAcao(candidate)) {
                                return candidate;
                            }

                        }
                    }
                    classedoMetodo = classedoMetodo.getSuperclass();
                } while (classedoMetodo != null);
            } catch (Throwable tt) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando obter Método vinculado", tt);
            }

        }

        return null;
    }

    public static void reloadAcessos() {
        try {

            USUARIOS.clear();
            PERMISSAO_POR_ACAO_ID.clear();

            ItfCfgPermissoes configPermissoes = SBCore.getConfiguradorDePermissao();
            List<ItfUsuario> usuariosAtualizados = configPermissoes.configuraUsuarios();

            for (ItfUsuario novousuario : usuariosAtualizados) {
                USUARIOS.put(novousuario.getEmail(), novousuario);
            }
            ItfCfgPermissoes classeResponsavelPermissao = SBCore.getConfiguradorDePermissao();
            List<ItfPermissao> permissoesAtualizadas = classeResponsavelPermissao.configuraPermissoes();

            if (permissoesAtualizadas != null) {
                for (ItfPermissao ac : permissoesAtualizadas) {

                    if (ac.getAcao() != null) {
                        if (ac.getAcao().isUmaAcaoController()) {
                            PERMISSAO_POR_ACAO_ID.put(ac.getAcao().getId(), ac);
                            IDPERMISSAO_POR_METODOID.put(ac.getAcao().getComoController().getIdMetodo(), ac.getAcao().getId());
                        }

                    }
                }
            }

        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando Reload de Acessos", e);

        }
    }

    private ItfResposta gerarRespostaAcessoNegado(ItfResposta resp) {
        return resp.addMensagemErroDisparaERetorna("Acesso negado, solicite acesso a este recurso.");
    }

    /**
     *
     * Verifica se o parametro é nulo e adiciona uma mensagem de erro na
     * resposta caso seja.
     *
     * @param pParametro o parametro que será verificado
     * @param nomeParametro o nome que será apresentado na mensagem de erro
     * @param pResp O objeto de resposta onde a mensagem de erro será adicionada
     */
    protected static void addMensagemNulo(Object pParametro, String nomeParametro, ItfResposta pResp) {

        if (pParametro == null) {
            pResp.addErro("o parametro " + nomeParametro + " não pode ser núlo");
        }

    }

    /**
     *
     * Retorna ação que está vinculada ao metodo;
     *
     *
     *
     *
     * @return A ação que estiver anotada no metodo
     */
    protected static ItfAcaoDoSistema getAcaoDoMetodo() {
        try {
            Method metodo = getMetodoChamado();
            ItfAcaoDoSistema acao = UtilSBController.getAcaoByMetodo(metodo, true);
            if (acao == null) {
                String nomeMetodo = "NULO!!!";
                if (metodo != null) {
                    nomeMetodo = metodo.getName();
                }
                throw new UnsupportedOperationException("Não foi possível identificar a ação do método" + nomeMetodo);
            }
            return acao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo ação do sistema do método ", t);
            return null;
        }
    }

    /**
     *
     * Verifica se o usuário logado possui autorização para executar este método
     *
     * @param pResp O objeto de resposta onde a mensagem de erro será adicionada
     */
    @SuppressWarnings("null")
    protected static void addMensagemDeAutorizacao(@NotNull ItfResposta pResp) {
        try {
            if (pResp == null) {
                throw new UnsupportedOperationException("Tentativa de adicionar uma mensgem de autorização em uma resposta nula");
            }

            ItfUsuario usuario = SBCore.getControleDeSessao().getSessaoAtual().getUsuario();

            if (usuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
                return;
            }

            Method metodo = getMetodoChamado();

            ItfPermissao permissao = null;

            ItfAcaoDoSistema acao = UtilSBController.getAcaoByMetodo(metodo, true);
            if (acao == null) {
                FabErro.PARA_TUDO.paraSistema("A ANOTAÇÃO DE AÇÃO NÃO FOI ENCONTRADA NO METODO DE AÇÃO DO SISTEMA", null);
            }
            if (!acao.isPrecisaPermissao()) {
                return;
            }

            permissao = getPermissoes().get(acao.getId());

            if (permissao == null) {
                FabErro.PARA_TUDO.paraSistema("O sistema não encontrou o registro de permissao da ação" + acao.getNomeAcao(), null);
            }

            if (isPermitido(permissao, usuario)) {
                return;

            } else {
                pResp.addErro(usuario.getNome() + ",parece que o acesso a esta ação do sistema ainda não foi liberada para você, entre em contato com o responsável e tente novamente");
                return;
            }
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando verificar permissao a ação do sistema", t);
        }
    }

    private static boolean isPermitido(ItfPermissao pAcesso, ItfUsuario pUsuario) {

        return SBCore.getConfiguradorDePermissao().isAcaoPermitidaUsuario(pUsuario, pAcesso.getAcao());

    }

    /**
     *
     * Verifica se o usuário possui acesso ao recurso
     *
     * @param pUsuario
     * @param pAcao
     * @return
     */
    @Override
    public boolean isAcessoPermitido(ItfUsuario pUsuario, ItfAcaoDoSistema pAcao) {
        if (!pAcao.isPrecisaPermissao()) {
            return true;
        } else {
            return isPermitido(getPermissoes().get(pAcao.getId()), pUsuario);
        }
    }

    public static boolean isAcessoPermitido(ItfAcaoDoSistema pAcao) {
        if (!pAcao.isPrecisaPermissao()) {
            return true;
        } else {
            return isPermitido(getPermissoes().get(pAcao.getId()), SBCore.getUsuarioLogado());
        }
    }

    /**
     *
     * Verifica se este modulo possui esta ação cadastrada em algum metodo
     *
     * @param permissao
     * @return
     */
    @Override
    public boolean possuiEstaAcao(ItfAcaoDoSistema permissao) {
        return getPermissoes().get(permissao.getId()) != null;
    }

}

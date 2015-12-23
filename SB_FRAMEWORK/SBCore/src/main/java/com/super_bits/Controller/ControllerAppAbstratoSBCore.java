/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfCfgPermissoes;
import com.super_bits.Controller.Interfaces.ItfPermissao;
import com.super_bits.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.comunicacao.Resposta;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * CLASSE PARA CONFIGURAÇÃO DE AÇOES DE ARQUIVAR_LOG.
 *
 *
 *
 *
 * @author Salvio
 */
public abstract class ControllerAppAbstratoSBCore implements ItfControlerAPP {

    private static final Map<Integer, ItfPermissao> permissoesPorMethodID = new HashMap<>();
    private static final Map<String, ItfUsuario> usuarios = new HashMap<>();

    public static ItfUsuario getUsuarioByEmail(String pEmail) {
        if (usuarios == null) {
            reloadAcessos();
        }
        return usuarios.get(pEmail);
    }

    protected void getAcaoDoMetodo(Method pMetodo) {

    }

    public static ItfResposta getNovaResposta(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado()));

        return resp;
    }

    protected ItfResposta getNovaRespostaNaoImplementado() {
        Resposta naoImplementado = new Resposta(null, null);
        return naoImplementado.addMensagemErroDisparaERetorna("Os algorítimos para [" + getMetodoChamado() + "] do modulo [" + this.getClass().getSimpleName() + "] não foi implementado.");
    }

    protected List<ItfPermissao> carregaAcessos() {
        return SBCore.getConfiguradorDePermissao().configuraPermissoes();
    }

    protected static ItfPermissao getPermissaoPorAcao(ItfAcaoDoSistema pAcao) {
        return getPermissoes().get(pAcao.getIdMetodo());
    }

    protected static Map<Integer, ItfPermissao> getPermissoes() {

        if (permissoesPorMethodID == null || permissoesPorMethodID.isEmpty()) {

            reloadAcessos();

        }

        return permissoesPorMethodID;

    }

    private static String buildNomeClasse() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String nomeMetodo = stackTraceElements[3].getMethodName();
        String nomeClasse = stackTraceElements[3].getClassName();
        String[] classeFull = nomeClasse.split("\\.");

        nomeClasse = classeFull[classeFull.length - 1];

        return nomeClasse + "." + nomeMetodo;
    }

    private static Method getMetodoChamado() {
        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StackTraceElement ste = stackTrace[3];
        final String methodName = ste.getMethodName();
        final String className = ste.getClassName();
        Class<?> kls;
        try {
            kls = Class.forName(className);

            do {
                for (final Method candidate : kls.getDeclaredMethods()) {
                    if (candidate.getName().equals(methodName)) {
                        return candidate;
                    }
                }
                kls = kls.getSuperclass();
            } while (kls != null);

        } catch (ClassNotFoundException ex) {
            return null;

        }

        return null;
    }

    public static void reloadAcessos() {
        try {

            usuarios.clear();
            permissoesPorMethodID.clear();

            ItfCfgPermissoes configPermissoes = SBCore.getConfiguradorDePermissao();
            List<ItfUsuario> usuariosAtualizados = configPermissoes.configuraUsuarios();

            for (ItfUsuario novousuario : usuariosAtualizados) {
                usuarios.put(novousuario.getEmail(), novousuario);
            }
            List<ItfPermissao> permissoesAtualizadas = SBCore.getConfiguradorDePermissao().configuraPermissoes();

            if (permissoesAtualizadas != null) {
                for (ItfPermissao ac : permissoesAtualizadas) {

                    if (ac.getAcao() != null) {
                        permissoesPorMethodID.put(ac.getAcao().getIdMetodo(), ac);
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
     * Retorna a resposta verificando as permissões e adicionando mensagens de
     * erro para parametros não enviados
     *
     * @param pTipoRetorno
     * @return
     */
    protected static ItfResposta getNovaRespostaAutorizaChecaNulo(Class pTipoRetorno) {
        ItfResposta resposta = getNovaRespostaComAutorizacao(pTipoRetorno);
        Method metodo = getMetodoChamado();

        return resposta;
    }

    protected static ItfResposta getNovaRespostaComAutorizacao(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, null);
        return resp;
    }

    protected static ItfResposta addMensagemDeAutorizacao(ItfResposta pResp) {

        ItfUsuario usuario = SBCore.getControleDeSessao().getSessaoAtual().getUsuario();

        if (usuario == new UsuarioSistema()) {

            return pResp;
        }

        Method metodo = getMetodoChamado();

        ItfPermissao acesso = null;

        ItfAcaoDoSistema acao = UtilSBController.obterAcaoByMethodo(metodo);

        if (acao == null) {
            FabErro.PARA_TUDO.paraSistema("A ANOTAÇÃO DE AÇÃO NÃO FOI ENCONTRADA NO METODO DE AÇÃO DO SISTEMA", null);
        }

        acesso = getPermissoes().get(acao.getIdMetodo());

        if (acesso == null) {
            FabErro.PARA_TUDO.paraSistema("O sistema não encontrou o registro de permissao da ação" + acao.getNomeAcao(), null);
        }

        if (isPermitido(acesso, usuario)) {
            return pResp;

        } else {
            pResp.addErro(usuario.getNome() + ",parece que o acesso a esta ação do sistema ainda não foi liberada para você, entre em contato com o responsável e tente novamente");
            return pResp;
        }
    }

    private static boolean isPermitido(ItfPermissao pAcesso, ItfUsuario pUsuario) {
        System.out.println("PEsquisando lista de acesso negado");

        if (!pAcesso.getAcao().isPrecisaPermissao()) {
            return true;
        }

        for (ItfUsuario usuario : pAcesso.getUsuariosNegados()) {
            if (usuario.getId() == pUsuario.getId()) {
                System.out.println("Acesso negado de:" + pUsuario.getNome() + "registro usuario encontrado:" + usuario.getId());
                return false;
            }
        }
        for (ItfGrupoUsuario grupo : pAcesso.getGruposNegados()) {

            for (ItfUsuario user : grupo.getUsuarios()) {
                if (pUsuario.getGrupo().getId() == pUsuario.getGrupo().getId()) {
                    return false;
                }
                if (user.getId() == pUsuario.getId()) {
                    return false;
                }
            }
        }

        System.out.println("Listando permitidos");
        for (ItfUsuario usuario : pAcesso.getUsuariosPermitidos()) {
            System.out.println("permitido:" + usuario.getNome() + pAcesso.getAcao());
            if (usuario.getId() == pUsuario.getId()) {
                return true;
            }
        }

        for (ItfGrupoUsuario grupo : pAcesso.getGruposPermitidos()) {
            if (pUsuario.getGrupo().getId() == pUsuario.getGrupo().getId()) {
                return true;
            }

            for (ItfUsuario user : grupo.getUsuarios()) {
                if (user.getId() == pUsuario.getId()) {
                    return true;
                }
            }
        }

        return false;

    }

}

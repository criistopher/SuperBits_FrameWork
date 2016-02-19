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
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Map<Integer, ItfPermissao> permissoesPorAcaoID = new HashMap<>();
    private static final Map<Integer, Integer> idPermissaoPorMetodoID = new HashMap<>();
    private static final Map<String, ItfUsuario> usuarios = new HashMap<>();

    public static ItfUsuario getUsuarioByEmail(String pEmail) {
        if (usuarios == null) {
            reloadAcessos();
        }
        return usuarios.get(pEmail);
    }

    public static ItfResposta getNovaResposta(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));

        return resp;
    }

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
        if (permissoesPorAcaoID == null || permissoesPorAcaoID.isEmpty()) {
            reloadAcessos();
        }
        return permissoesPorAcaoID;
    }

    protected static ItfPermissao getPermissaoByMethodID(Integer pMethodID) {
        if (idPermissaoPorMetodoID == null || idPermissaoPorMetodoID.isEmpty()) {
            reloadAcessos();
        }
        Integer idPermissao = idPermissaoPorMetodoID.get(pMethodID);
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
            permissoesPorAcaoID.clear();

            ItfCfgPermissoes configPermissoes = SBCore.getConfiguradorDePermissao();
            List<ItfUsuario> usuariosAtualizados = configPermissoes.configuraUsuarios();

            for (ItfUsuario novousuario : usuariosAtualizados) {
                usuarios.put(novousuario.getEmail(), novousuario);
            }
            List<ItfPermissao> permissoesAtualizadas = SBCore.getConfiguradorDePermissao().configuraPermissoes();

            if (permissoesAtualizadas != null) {
                for (ItfPermissao ac : permissoesAtualizadas) {

                    if (ac.getAcao() != null) {
                        permissoesPorAcaoID.put(ac.getAcao().getId(), ac);
                        idPermissaoPorMetodoID.put(ac.getAcao().getIdMetodo(), ac.getAcao().getId());
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
     * erro para parametros não enviados (AINDA NÂO IMPLEMENTADO)
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param pTipoRetorno
     * @return
     */
    @Deprecated
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

        if (usuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
            return pResp;
        }

        Method metodo = getMetodoChamado();

        ItfPermissao permissao = null;

        ItfAcaoDoSistema acao = UtilSBController.getAcaoByMetodo(metodo, true);
        if (acao == null) {
            FabErro.PARA_TUDO.paraSistema("A ANOTAÇÃO DE AÇÃO NÃO FOI ENCONTRADA NO METODO DE AÇÃO DO SISTEMA", null);
        }

        permissao = getPermissoes().get(acao.getId());

        if (permissao == null) {
            FabErro.PARA_TUDO.paraSistema("O sistema não encontrou o registro de permissao da ação" + acao.getNomeAcao(), null);
        }

        if (isPermitido(permissao, usuario)) {
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
        if (pUsuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
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
            if (pUsuario.getGrupo().getId() == grupo.getId()) {
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
        return isPermitido(permissoesPorAcaoID.get(pAcao.getId()), pUsuario);
    }

    public static boolean isAcessoPermitido(ItfAcaoDoSistema pAcao) {
        return isPermitido(permissoesPorAcaoID.get(pAcao.getId()), SBCore.getUsuarioLogado());
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

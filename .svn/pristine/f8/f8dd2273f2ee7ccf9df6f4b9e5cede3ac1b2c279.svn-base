/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.Controller.anotacoes.InfoAcao;
import com.super_bits.modulosSB.SBCore.Controller.comunicacao.Resposta;
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
 * @author Salvio
 */
public abstract class ControllerAppAbstratoSBCore implements ItfControlerAPP {

    private static Map<Integer, ItfAcesso> acessos;
    private static Map<String, ItfUsuario> usuarios;

    public static ItfUsuario getUsuarioByEmail(String pEmail) {
        if (usuarios == null) {
            reloadAcessos();
        }
        return usuarios.get(pEmail);
    }

    public static ItfResposta getNovaResposta(Class pTipoRetorno) {
        Resposta resp = new Resposta(pTipoRetorno);
        return resp;
    }

    protected boolean lancarExcecaoNaoImplementado() {
        throw new UnsupportedOperationException("O código para [" + getMetodoChamado() + "] do modulo [" + this.getClass().getSimpleName() + "] não foi implementado.");
    }

    protected List<ItfAcesso> carregaAcessos() {
        return SBCore.getConfiguradorDeAcessos().configuraAcessos();
    }

    protected static Map<Integer, ItfAcesso> getAcessos() {

        if (acessos == null) {

            reloadAcessos();

        }

        return acessos;

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
            Map<Integer, ItfAcesso> acessosRecarregados = new HashMap<>();
            usuarios = new HashMap<>();

            List<ItfUsuario> novosUsuarios = SBCore.getConfiguradorDeAcessos().configuraUsuarios();

            for (ItfUsuario novousuario : novosUsuarios) {
                usuarios.put(novousuario.getEmail(), novousuario);
            }
            List<ItfAcesso> novosAcessos = SBCore.getConfiguradorDeAcessos().configuraAcessos();
            if (acessos == null) {
                acessos = new HashMap<>();
            }
            if (novosAcessos != null) {
                for (ItfAcesso ac : novosAcessos) {
                    System.out.println("nomeAcesso:" + ac.getNomeAcesso());

                    if (ac.getNomeAcesso() != null) {
                        acessos.put(ac.getNomeAcesso().hashCode(), ac);
                    }
                }
            }

        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando REload de Acessos", e);

        }
    }

    protected static boolean autorizar() {

        ItfUsuario usuario = SBCore.getControleDeSessao().getSessaoAtual().getUsuario();
        if (usuario == new UsuarioSistema()) {
            return true;
        }

        Method metodo = getMetodoChamado();
        String nomeAcesso = buildNomeClasse();
        System.out.println("Verificando permissao para:" + nomeAcesso);
        InfoAcao infoAcessoMetodo = metodo.getAnnotation(InfoAcao.class);
        ItfAcesso acesso = getAcessos().get(nomeAcesso.hashCode());

        if (acesso == null) {
            SBCore.enviarMensagemUsuario("Olá, " + usuario.getNome() + ",Este Acesso não está cadastrado no sistema ", FabMensagens.ALERTA);
            return false;
        }

        String nomeAmigavel = infoAcessoMetodo.nomeAmigavel();
        if (nomeAmigavel == null) {
            nomeAmigavel = nomeAcesso;
        }
        if (isPermitido(acesso, usuario)) {
            return true;

        } else {
            SBCore.enviarMensagemUsuario(usuario.getNome() + ", SOLICITE ACESSO ao recurso  " + nomeAmigavel, FabMensagens.AVISO);
            return false;
        }
    }

    private static boolean isPermitido(ItfAcesso pAcesso, ItfUsuario pUsuario) {
        System.out.println("PEsquisando lista de acesso negado");
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
            System.out.println("permitido:" + usuario.getNome() + pAcesso.getNomeAcesso());
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

        if (pAcesso.padraoLiberado()) {
            return true;
        }
        return false;

    }

    protected void mensagemAcessoNegado(String pMensagem) {
        if (!autorizar()) {
            return;
        }

    }

    protected void mensagemAcessoNegado(ItfAcesso pAcao) {
        String mensagem = " Acesso NEGADO para:" + pAcao.getNomeAcesso();
        FabMensagens.enviarMensagemUsuario(mensagem, FabMensagens.ALERTA);

    }

}

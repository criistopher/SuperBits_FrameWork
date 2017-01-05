package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.modulos.SBAcessosModel.model.ConfiguracaoDePermissao;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/*

 */
/**
 *
 * @author desenvolvedor
 */
public abstract class ConfigPermissoesAcessoModelAbstrato extends ConfigPermissaoSBCoreAbstrato {

    private static boolean ACOES_DO_SISTEMA_CRIADAS = false;
    private static boolean PERMISSOES_CRIADAS = false;

    public ConfigPermissoesAcessoModelAbstrato(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    /**
     *
     * Cria as ações do sistema no banco de dados
     *
     */
    protected void persistirAcoesNoBancoDeDados() {
        EntityManager em = null;
        try {
            if (ACOES_DO_SISTEMA_CRIADAS) {
                return;
            }
            if (!houveAlteracoes()) {
                ACOES_DO_SISTEMA_CRIADAS = true;
                return;
            }

            em = UtilSBPersistencia.getNovoEM();
            for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

                if (acao.isTemAcaoPrincipal()) {
                    ItfAcaoSecundaria acaosecundaria = (ItfAcaoSecundaria) acao;
                    if (UtilSBPersistencia.mergeRegistro(acaosecundaria.getAcaoPrincipal().getModulo(), em) == null) {
                        throw new UnsupportedOperationException("Erro Salvando modulo ");
                    }

                    if (UtilSBPersistencia.mergeRegistro(acaosecundaria.getAcaoPrincipal(), em) == null) {
                        throw new UnsupportedOperationException("Erro Salvando ação principal: " + acaosecundaria.getAcaoPrincipal().getNomeUnico());
                    }

                }

                if (UtilSBPersistencia.mergeRegistro(acao.getModulo(), em) == null) {
                    throw new UnsupportedOperationException("Erro Salvando  modulo da  ação  modulo: ->" + acao.getModulo());
                }
                if (UtilSBPersistencia.mergeRegistro(acao, em) == null) {
                    throw new UnsupportedOperationException("Erro Salvando ação ->" + acao.getNomeUnico());
                }

            }
            ACOES_DO_SISTEMA_CRIADAS = true;
        } catch (Throwable t) {
            ACOES_DO_SISTEMA_CRIADAS = false;
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro persistindo ações do Banco de dados", t);
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    private boolean houveAlteracoes() {
        ConfiguracaoDePermissao configPermissao = null;
        if (!SBCore.isEmModoProducao()) {
            configPermissao = (ConfiguracaoDePermissao) UtilSBPersistencia.getRegistroByID(ConfiguracaoDePermissao.class, 0);
            if (configPermissao == null) {
                configPermissao = new ConfiguracaoDePermissao();
                configPermissao = (ConfiguracaoDePermissao) UtilSBPersistencia.mergeRegistro(configPermissao);

            }
            if (configPermissao.getUltimaVersaoBanco() != null) {
                if (!configPermissao.getUltimaVersaoBanco().equals(SBPersistencia.getDevOps().getHashBancoGerado())) {
                    PERMISSOES_CRIADAS = true;
                    System.out.println("As permissões desta versão já foram persistidas no banco");
                    return true;
                } else {
                    return false;

                }
            }
        }
        return true;
    }

    private boolean persistirPermissoesNoBanco() {
        EntityManager em = null;

        try {
            if (!PERMISSOES_CRIADAS) {
                if (!houveAlteracoes()) {
                    PERMISSOES_CRIADAS = true;
                    return true;
                }
                em = UtilSBPersistencia.getNovoEM();
                for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {
                    AcaoDoSistema ac = (AcaoDoSistema) acao;
                    if (ac.isPrecisaPermissao()) {
                        //Verificando se a permissao já existe
                        PermissaoSB novaPermissao = new PermissaoSB(ac);
                        PermissaoSB permissãoEncontrada
                                = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, novaPermissao.getId(), em);

                        if (permissãoEncontrada == null) {
                            em.getTransaction().begin();
                            if (UtilSBPersistencia.mergeRegistro(novaPermissao, em) == null) {
                                throw new UnsupportedOperationException("Erro persistindo permissão em banco de dados");
                            }
                            em.getTransaction().commit();
                        }

                    }
                }
                if (!SBCore.isEmModoProducao()) {
                    ConfiguracaoDePermissao configPermissao = null;
                    configPermissao = (ConfiguracaoDePermissao) UtilSBPersistencia.getRegistroByID(ConfiguracaoDePermissao.class, 0);
                    if (configPermissao == null) {
                        throw new UnsupportedOperationException("Impossível determinar a configuração de acesso do sistema");
                    }
                    configPermissao.setUltimaVersaoBanco(SBPersistencia.getDevOps().getHashBancoGerado());
                    if (UtilSBPersistencia.mergeRegistro(configPermissao) == null) {
                        throw new UnsupportedOperationException("Erro Persistindo nova versão do banco em tabela de configuracao");
                    }

                }
                PERMISSOES_CRIADAS = true;

            }
            return true;
        } catch (Throwable t) {
            PERMISSOES_CRIADAS = false;
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando tabelas de permissao, (ESTE ERRO É GRAVE E FUTURAMENTE GERARÁ UM ERRO DO TIPO PARATUDO)", t);
            return false;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    /**
     *
     * Retorna uma lista atualizada com as permissões do sistema
     *
     *
     * @return
     */
    @Override
    public List<ItfPermissao> configuraPermissoes() {

        persistirAcoesNoBancoDeDados();
        persistirPermissoesNoBanco();
        List<ItfPermissao> resp = new ArrayList<>();
        try {
            //Exemplo busca acessos no banco de dados

            resp = (List<ItfPermissao>) UtilSBPersistencia.getListaTodos(PermissaoSB.class);
            return resp;
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo lista de acessos", t);
        }
        return resp;
    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {

        List<ItfUsuario> resposta = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class);

        return resposta;
    }

    @Override
    public void atualizarInformacoesDePermissoesDoSistema() {
        ControllerAppAbstratoSBCore.reloadAcessos();

    }

    public static List<AcaoDoSistema> listarAcoesDoGrupo(@NotNull GrupoUsuarioSB pGrpUsuario, @NotNull ModuloAcaoSistema pModulo) {
        List<AcaoDoSistema> resp = new ArrayList<>();

        for (ItfAcaoDoSistema acao : pModulo.getAcoes()) {
            PermissaoSB permissao = (PermissaoSB) ControllerAppAbstratoSBCore.getPermissaoPorAcao(acao);
            //TODO sobrescrever metodo permissao no modulo SBPErmissao utilizando loadBY
            //   permissao = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, permissao.getId(), em);
            if (permissao != null) {
                if (permissao.getGruposPermitidos().contains(pGrpUsuario)) {
                    resp.add((AcaoDoSistema) acao);
                }
            }

        }

        return resp;
    }

    @Override
    public boolean isAcaoPermitidaUsuario(ItfUsuario pUsuario, ItfAcaoDoSistema acao) {
        return isPermitidoUsuario(pUsuario, new PermissaoSB((AcaoDoSistema) acao));
    }

    @Override
    public boolean isAcaoPermitidaUsuarioLogado(ItfAcaoDoSistema acao) {
        return isPermitidoUsuario(SBCore.getUsuarioLogado(), new PermissaoSB((AcaoDoSistema) acao));
    }

    @Override
    public boolean isPermitidoUsuario(ItfUsuario pUsuario, ItfPermissao pPermissao) {
        EntityManager em = null;
        try {
            if (!PERMISSOES_CRIADAS) {
                configuraPermissoes();
            }
            System.out.println("PEsquisando lista de acesso negado");
            if (pUsuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
                return true;
            }
            if (!pPermissao.getAcao().isPrecisaPermissao()) {
                return true;
            }

            em = UtilSBPersistencia.getNovoEM();

            PermissaoSB pAcesso = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, pPermissao.getId(), em);
            if (pAcesso == null) {
                throw new UnsupportedOperationException("Nenhuma permissao foi encontrada para" + pPermissao);
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

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando permissao", t);
            return false;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return false;
    }

}

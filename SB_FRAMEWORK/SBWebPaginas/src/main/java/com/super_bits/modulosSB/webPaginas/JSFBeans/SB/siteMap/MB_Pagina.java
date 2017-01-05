package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

public abstract class MB_Pagina extends B_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<>();
    private String urlAcessada;
    private SessaoAtualSBWP sessaoAtual;

    public MB_Pagina() {
        super();
    }

    @PostConstruct
    private void initBean() {
        try {
            System.out.println("Iniciando InitBeanDePagina" + this.getClass().getSimpleName());
            configParametros();
            if (getAcaoVinculada().isPrecisaPermissao()) {
                try {

                    if (!SBCore.getConfiguradorDePermissao().isAcaoPermitidaUsuario(sessaoAtual.getUsuario(), getAcaoVinculada())) {
                        UtilSBWP_JSFTools.vaParaPagina("/resources/SBComp/SBSystemPages/acessoNegado.xhtml");
                    }

                } catch (Throwable t) {
                    System.out.println("Erro verificando permissão de acesso para pagina" + getAcaoVinculada().getNomeUnico());
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando inito principal do bean", t);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando inito principal do bean", t);

        }

    }

    @Override
    public void abrePagina() {
        super.abrePagina();
    }

    private Map<String, SBNQ> listas;

    public Map<String, SBNQ> getListas() {
        return listas;
    }

    public boolean isAcessoPermitido() {
        if (getAcaoVinculada() != null) {
            return ControllerAppAbstratoSBCore.isAcessoPermitido((ItfAcaoDoSistema) getAcaoVinculada());
        }
        return false;
    }

    @Override
    protected String defineTitulo() {
        try {

            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getDescricao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Título da pagina " + this.getClass().getSimpleName() + " não pode ser definido";
    }

    @Override
    protected String defineNomeLink() {
        try {
            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getNomeAcao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo nome do link da pagina";
    }

    @Override
    protected String defineDescricao() {
        try {
            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getDescricao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo Descricao  da pagina";
    }

    @Override
    public int getId() {
        try {
            return getAcaoVinculada().getId();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return -1;
    }

}

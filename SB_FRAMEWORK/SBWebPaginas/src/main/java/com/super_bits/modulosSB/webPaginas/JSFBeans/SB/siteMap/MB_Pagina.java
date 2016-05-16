package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

public abstract class MB_Pagina extends B_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<String, String>();
    private String urlAcessada;

    public MB_Pagina() {
        super();
    }

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina" + this.getClass().getSimpleName());
        foiInjetado = true;
        configParametros();

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
            return getAcaoVinculada().getDescricao();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo titulo da pagina";
    }

    @Override
    protected String defineNomeLink() {
        try {
            return getAcaoVinculada().getNomeAcao();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo nome do link da pagina";
    }

    @Override
    protected String defineDescricao() {
        try {

            return getAcaoVinculada().getDescricao();
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

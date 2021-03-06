package com.super_bits.modulosSB.Persistencia.Campo;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ItfTabelaLigacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CampoMultiplo extends Campo {

    private final List<ItfBeanSimples> listaAutoComplet;
    private List<ItfBeanSimples> selecao;

    public CampoMultiplo(Field pCampo, String PvalorPadrao, Object tabelaLigacao) {
        super(FabCampos.LISTA_OBJETOS);

        List<ItfTabelaLigacao> tabLigacao = (List<ItfTabelaLigacao>) tabelaLigacao;

        List<ItfBeanSimples> opcoes = new ArrayList<ItfBeanSimples>();

        for (ItfTabelaLigacao cliEsp : tabLigacao) {
            opcoes.add(cliEsp.getCampoDiferente("Prestador"));
        }

        selecao = opcoes;
        Class<?> classeOpcoes = tabLigacao.get(0).getCampoDiferente("Prestador").getClass();

        DaoGenerico<ItfBeanSimples> todasEspecialidadesdao = (DaoGenerico<ItfBeanSimples>) new DaoGenerico<ItfBeanSimples>(classeOpcoes);

        listaAutoComplet = new ArrayList<ItfBeanSimples>();
        for (ItfBeanSimples item : ((DaoGenerico<ItfBeanSimples>) todasEspecialidadesdao).todos()) {
            //		System.out.println("Add Selecao"+item.getNomeCurto());
            listaAutoComplet.add(item);
        }

    }

    public List<ItfBeanSimples> listaAutoComplet(String pLocalizador) {
        //	DaoGenerico<Especialidade> todasEspecialidadesdao = (DaoGenerico<Especialidade>) new DaoGenerico<Especialidade>(Especialidade.class);
        //	listaAutoComplet=new ArrayList<ItfBeanSimples>();
        //	 for (Especialidade esp : ((DaoGenerico<Especialidade>) todasEspecialidadesdao).todos()) {
        //			System.out.println("Add Selecao"+esp.getNomeCurto());
        //		 listaAutoComplet.add(esp);
        //	 }
        return listaAutoComplet;
    }

    public List<ItfBeanSimples> getSelecao() {
        return selecao;
    }

    public void setSelecao(List<ItfBeanSimples> selecao) {
        this.selecao = selecao;
    }

}

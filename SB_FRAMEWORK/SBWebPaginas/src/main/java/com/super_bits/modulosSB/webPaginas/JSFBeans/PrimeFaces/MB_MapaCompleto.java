package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeEndereco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanEndereco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.B_ItemGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

public abstract class MB_MapaCompleto<T> extends B_ItemGenerico implements Serializable {

    public abstract List<T> getRegistros();

    private static final long serialVersionUID = 1L;
    private MapModel mapa;
    private Marker marcador;
    private ItfBeanEndereco registroSelecionado;
    private boolean temRegistros;
    private String cepLocalizacao;

    private void setRegistroSelecionado(String pNome) {

        List<T> registros = getRegistros();
        for (T registro : registros) {
            if (((ItfBeanEndereco) registro).getNomeCurto().equals(pNome)) {
                registroSelecionado = (ItfBeanEndereco) registro;
                break;
            }
        }

    }

    // apenas para compatibilidade com WELD
    protected MB_MapaCompleto() {
        System.out.println("ATENCAO mapa iniciado ignorando o construtor");
    }

    @PostConstruct
    public void startBean() {
        //DaoGenerico<T> dao = new DaoGenerico<T>(Prestador.class,dados.getEm());
        configRegistros();

    }

    protected MB_MapaCompleto(Class<T> tipo) {

        super(tipo);
        mapa = new DefaultMapModel();

        //TODO Ver uma form
    }

    public MapModel getMapa() {
        return mapa;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marcador = (Marker) event.getOverlay();
        setRegistroSelecionado(marcador.getTitle());
    }

    public void configRegistros() {
        //	 List<T> registros = setRegistros(getRegistros());

        mapa = new DefaultMapModel();

        List<T> registros = getRegistros();
        if (registros == null || registros.size() == 0) {
            temRegistros = false;
            return;
        }
        List<EntidadeEndereco> locais = new ArrayList();
        for (T registro : registros) {
            locais.add((EntidadeEndereco) registro);
        }

        setRegistroSelecionado(((ItfBeanSimples) registros.get(0)).getNomeCurto());

        for (EntidadeEndereco local : locais) {
            System.out.println("Falta implementar MApa");
        }
        //	mapa.addOverlay(new Marker(  , local.getNomeCurto(),null,local.getImgPequena()));
        //TODO adciona novo LAtlong;

    }

    public Marker getMarcador() {
        return marcador;
    }

    public ItfBeanEndereco getRegistroSelecionado() {
        return registroSelecionado;
    }

    public String getCepLocalizacao() {
        return cepLocalizacao;
    }

    public void setCepLocalizacao(String cepLocalizacao) {
        this.cepLocalizacao = cepLocalizacao;
    }

    public boolean isTemRegistros() {
        return temRegistros;
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.modelosPagina;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
@InfoClasse(tags = {"Modelo de Pagina"}, plural = "Modelos de paginas")
public class ModeloPagina extends ItemSimples implements ItfBeanSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nomeModelo;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;
    private String xhtmlVinculado;
    private List<String> areas;

    public ModeloPagina() {
        areas = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getXhtmlVinculado() {
        return xhtmlVinculado;
    }

    public void setXhtmlVinculado(String xhtmlVinculado) {
        this.xhtmlVinculado = xhtmlVinculado;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public String getAreaPrincipal() {
        if (areas.isEmpty()) {
            throw new UnsupportedOperationException("Area principal não definida em" + getNomeModelo());
        }
        return areas.get(0);
    }

    public String getAreaSecundaria() {
        if (areas.isEmpty() || areas.size() < 2) {
            throw new UnsupportedOperationException("Area secundaria não encontrada em" + getNomeModelo());
        }
        return areas.get(1);

    }

    public String getAreaExtra1() {
        if (areas.isEmpty() || areas.size() < 3) {
            throw new UnsupportedOperationException("Area extra1 não encontrada em" + getNomeModelo());
        }
        return areas.get(2);
    }

    public String getAreaExtra2() {
        if (areas.isEmpty() || areas.size() < 4) {
            throw new UnsupportedOperationException("Area extra2 não encontrada em" + getNomeModelo());
        }
        return areas.get(3);
    }

    public String getAreaExtra3() {
        if (areas.isEmpty() || areas.size() < 5) {
            throw new UnsupportedOperationException("Area extra3 não encontrada em" + getNomeModelo());
        }
        return areas.get(4);
    }

}

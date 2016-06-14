/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class GrupoCampos {

    private String nomeGrupo;
    private final List<CaminhoCampoReflexao> campos = new ArrayList<>();

    public GrupoCampos(String pTituloGrupo) {
        nomeGrupo = pTituloGrupo;

    }

    public GrupoCampos() {

    }

    public boolean isUmCampoComNome() {
        return nomeGrupo != null;
    }

    public void adicionarCampo(CaminhoCampoReflexao pCampo) {
        if (!campos.contains(pCampo)) {
            campos.add(pCampo);
        }

    }

    public List<CaminhoCampoReflexao> getCampos() {
        return campos;
    }

}

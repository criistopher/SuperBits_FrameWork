/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class GrupoCampos extends ItemSimples {

    @InfoCampo(label = "Nome do Grupo", tipo = FabCampos.AAA_NOME)
    private String nomeGrupo = "Dados Cadastrais";
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    private final List<CaminhoCampoReflexao> campos = new ArrayList<>();

    public GrupoCampos(String pTituloGrupo) {
        nomeGrupo = pTituloGrupo;

    }

    public GrupoCampos() {

    }

    @Override
    public int getId() {
        return nomeGrupo.hashCode();
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

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public int getResponsividade() {
        if (campos.size() % 2 == 0) {
            return 33;
        } else {
            return 50;
        }
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CaminhoCampoReflexao extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    private final List<String> partesCaminho = new ArrayList<>();
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String caminhoComleto;
    private Field campoFieldReflection;

    public CaminhoCampoReflexao(String caminho) {
        CaminhoCampoReflexao cm = UtilSBCoreReflexaoCampos.getCaminhoCAmpoByString(caminho);
        campoFieldReflection = cm.getCampoFieldReflection();
        caminhoComleto = cm.getCaminhoCompletoString();
        partesCaminho.addAll(cm.getPartesCaminho());
        id = caminhoComleto.hashCode();
    }

    private void makePartesCaminho() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    private void makeCaminhoCompleto() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    /**
     *
     * @param pCaminho Caminho para encontrar o Campo, separado por . exemplo:
     * usuario.localizacao.bairro
     * @param campo O Field obtido por reflecion
     */
    public CaminhoCampoReflexao(String pCaminho, Field campo) {

        //setCaminho(pCaminho);
        caminhoComleto = pCaminho;
        this.campoFieldReflection = campo;

        makePartesCaminho();
        id = caminhoComleto.hashCode();
    }

    public CaminhoCampoReflexao(List<String> pPartesCaminho, Field campo) {

        partesCaminho.addAll(pPartesCaminho);
        this.campoFieldReflection = campo;
        makeCaminhoCompleto();
        id = caminhoComleto.hashCode();
    }

    /**
     *
     * Adiciona uma parte do caminho para o campo caminho.add(Parate)
     *
     * @param pParteCaminho
     */
    public void addParteCaminho(String pParteCaminho) {
        partesCaminho.add(pParteCaminho);
    }

    /**
     *
     * @return O caminho em formato de uma string, exemplo:
     * usuario.localizacao.bairro
     */
    public String getCaminhoCompletoString() {
        return caminhoComleto;
    }

    public String getCaminhoSemNomeClasse() {
        int i = 0;
        String caminhoParcial = "";
        for (String parte : getCaminhoCompletoString().split("\\.")) {

            if (i > 0) {
                caminhoParcial += parte;
            }
            i++;

        }
        return caminhoParcial;
    }

    public List<String> getPartesCaminho() {
        return partesCaminho;
    }

    public Field getCampoFieldReflection() {
        return campoFieldReflection;
    }

    @Override
    public String toString() {
        return getCaminhoCompletoString();
    }

    @Override
    public int getId() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int pID) {
        setId(pID); //To change body of generated methods, choose Tools | Templates.
    }

}

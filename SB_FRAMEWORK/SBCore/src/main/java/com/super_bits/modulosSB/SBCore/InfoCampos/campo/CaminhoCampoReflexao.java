/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
public final class CaminhoCampoReflexao extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    private final List<String> partesCaminho = new ArrayList<>();
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String caminhoComleto;
    private final Field campoFieldReflection;
    private boolean umCampoListavel;
    private boolean umaEntidade;
    private boolean umCampoSeparador;
    private boolean umCampoVinculado;

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
        configuraInformacoesBasicasDoCampoPorReflexao();
        makePartesCaminho();
        id = caminhoComleto.hashCode();
    }

    /**
     *
     * @param pPartesCaminho
     * @param campo
     */
    public CaminhoCampoReflexao(List<String> pPartesCaminho, Field campo) {

        partesCaminho.addAll(pPartesCaminho);
        this.campoFieldReflection = campo;
        configuraInformacoesBasicasDoCampoPorReflexao();
        makeCaminhoCompleto();
        id = caminhoComleto.hashCode();
    }

    private void configuraInformacoesBasicasDoCampoPorReflexao() {

        umCampoVinculado = campoFieldReflection != null;

        if (umCampoVinculado) {

            if (campoFieldReflection.getType().getSimpleName().equals("List")) {
                umCampoListavel = true;
                umaEntidade = true;
                if (caminhoComleto.contains("[]")) {

                } else {
                    caminhoComleto += "[]";
                    makePartesCaminho();
                }
            }

            if (campoFieldReflection.isAnnotationPresent(ManyToOne.class)) {
                umaEntidade = true;
            }
        } else {

        }
    }

    /**
     *
     * @param caminho
     */
    public CaminhoCampoReflexao(String caminho) {
        if (!UtilSBCoreReflexaoCampos.isUmCampoSeparador(caminho)) {
            CaminhoCampoReflexao cm = UtilSBCoreReflexaoCampos.getCaminhoCAmpoByString(caminho);
            umCampoSeparador = true;
            campoFieldReflection = cm.getCampoFieldReflection();
            caminhoComleto = cm.getCaminhoCompletoString();
            partesCaminho.addAll(cm.getPartesCaminho());
            id = caminhoComleto.hashCode();
        } else {
            campoFieldReflection = null;
            caminhoComleto = caminho;
            partesCaminho.addAll(getPartesCaminho());
            id = caminhoComleto.hashCode();
        }
        umCampoVinculado = false;
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

    public boolean isUmCampoSeparador() {
        return UtilSBCoreReflexaoCampos.isUmCampoSeparador(caminhoComleto);
    }

    public boolean temUmTipoComOutrasPropriedades() {

        return caminhoComleto.split("\\.").length > 2;

    }

    public boolean isUmTipoComOutrasPropriedades() {
        return getTipoCampo().isAnnotationPresent(InfoClasse.class);
    }

    /**
     *
     *
     * @return O tipo do campo caso seja uma
     */
    public Class getTipoCampo() {

        if (umCampoListavel) {

            ParameterizedType genericoTipo = (ParameterizedType) campoFieldReflection.getGenericType();
            Class tipoDaLista = (Class<?>) genericoTipo.getActualTypeArguments()[0];

            return tipoDaLista;
        } else {
            return campoFieldReflection.getType();
        }
    }

    public boolean isUmCampoListavel() {
        return umCampoListavel;
    }

    public boolean isUmaEntidade() {
        return umaEntidade;
    }

    public boolean isUmCampoVinculado() {
        return umCampoVinculado;
    }

    public String getUtimoNome() {
        return partesCaminho.get(partesCaminho.size() - 1);
    }

    public List<String> getTodosCaminhosPossiveis() {
        List<String> caminhosPossiveis = new ArrayList();
        String novoCaminho = "";
        for (String parte : partesCaminho) {
            if (!novoCaminho.isEmpty()) {
                novoCaminho += "." + parte;
            } else {
                novoCaminho = parte;
            }

            caminhosPossiveis.add(novoCaminho);

        }
        return caminhosPossiveis;

    }

    public List<String> getTodasListas() {
        List<String> caminhosComLista = new ArrayList();
        return caminhosComLista;
    }

}

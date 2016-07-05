/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import static com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminho;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
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

    private boolean umCampoListavel;
    private boolean umaEntidade;
    private boolean umCampoSeparador;
    private boolean umCampoVinculado;

    public void defineNomeCompleto(String pCaminho, Class pClasse) {
        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(pCaminho)) {
            caminhoComleto = pCaminho;
            return;
        }
        if (UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(pCaminho)) {
            caminhoComleto = pCaminho;
        } else {
            if (pClasse == null) {
                throw new UnsupportedOperationException("impossícel determinar o caminho completo pela string, sem enviar a classe como parametro");
            }
            caminhoComleto = pClasse.getSimpleName() + "." + pCaminho;

        }

    }

    public void defineNomeCompleto(String pCaminho) {

        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(pCaminho)) {
            caminhoComleto = pCaminho;
            return;
        }

        if (UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(pCaminho)) {
            caminhoComleto = pCaminho;
        } else {

            throw new UnsupportedOperationException("impossícel determinar o caminho completo pela string, sem enviar a classe como parametro");
        }

    }

    /**
     *
     * @param pCaminho Caminho para encontrar o Campo, separado por . exemplo:
     * usuario.localizacao.bairro
     * @param campo O Field obtido por reflecion
     */
    public CaminhoCampoReflexao(String pCaminho) {

        //setCaminho(pCaminho);
        defineNomeCompleto(pCaminho);

        configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(null));
        makePartesCaminho();
        id = caminhoComleto.hashCode();

    }

    public CaminhoCampoReflexao(String pCaminho, Class pClasse) {

        //setCaminho(pCaminho);
        defineNomeCompleto(pCaminho, pClasse);

        configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(pClasse));
        makePartesCaminho();
        id = caminhoComleto.hashCode();

    }

    private Field validaCampo(Class pClasse) {
        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(caminhoComleto)) {
            return null;
        }
        Field campo = null;
        if (pClasse == null) {
            campo = getFieldByNomeCompletoCaminho(caminhoComleto);
        } else {
            campo = UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminhoEClasse(caminhoComleto, pClasse);
        }
        if (campo == null) {
            throw new UnsupportedOperationException("Não foi possivel encontrar o campo pelo caminho [" + caminhoComleto + "] ");
        }
        return campo;

    }

    /**
     *
     * @param pPartesCaminho
     * @param campo
     */
    public CaminhoCampoReflexao(List<String> pPartesCaminho) {

        partesCaminho.addAll(pPartesCaminho);

        makeCaminhoCompleto();
        defineNomeCompleto(caminhoComleto);
        configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(null));
        id = caminhoComleto.hashCode();
    }

    private void configuraInformacoesBasicasDoCampoPorReflexao(Field pField) {

        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(caminhoComleto)) {
            return;
        }

        umCampoVinculado = UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminho(caminhoComleto) != null;

        if (umCampoVinculado) {

            if (pField.getType().getSimpleName().equals("List")) {
                umCampoListavel = true;
                umaEntidade = true;
                if (caminhoComleto.endsWith("[]")) {

                } else {
                    caminhoComleto += "[]";
                    makePartesCaminho();
                }
            }

            if (pField.isAnnotationPresent(ManyToOne.class)) {
                umaEntidade = true;
            }
        } else {

            throw new UnsupportedOperationException("O campo vinculado não pode ser encontrado pelo caminho " + caminhoComleto);

        }
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
                if (i > 1) {
                    caminhoParcial += "." + parte;
                } else {
                    caminhoParcial += parte;
                }
            }
            i++;

        }
        return caminhoParcial;
    }

    public List<String> getPartesCaminho() {
        return partesCaminho;
    }

    public Field getCampoFieldReflection() {
        return UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminho(caminhoComleto);
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
        Field campoFieldReflection = UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminho(caminhoComleto);
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

    public List<String> getTodosCaminhosPossiveisSemUltimoParametro() {
        List<String> caminhosPossiveis = new ArrayList();
        String novoCaminho = "";
        int i = 0;
        for (String parte : partesCaminho) {
            if (!novoCaminho.isEmpty()) {
                novoCaminho += "." + parte;
            } else {
                novoCaminho = parte;
            }
            if (i < partesCaminho.size() - 1) {
                caminhosPossiveis.add(novoCaminho);
            }
            i++;
        }
        return caminhosPossiveis;

    }

    public List<String> getTodasListas() {
        List<String> caminhosComLista = new ArrayList();
        return caminhosComLista;
    }

    public String getLabel() {
        Field campoFieldReflection = UtilSBCoreReflexaoCampos.getFieldByNomeCompletoCaminho(caminhoComleto);
        InfoCampo anotacao = campoFieldReflection.getAnnotation(InfoCampo.class);

        if (anotacao != null) {
            return anotacao.label();
        }

        return "sem label";

    }

}

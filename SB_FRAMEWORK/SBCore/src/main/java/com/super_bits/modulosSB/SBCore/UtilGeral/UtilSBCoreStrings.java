package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import de.svenjacobs.loremipsum.LoremIpsum;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Salvio
 */
public abstract class UtilSBCoreStrings {

    public static String teste = "teste";
    public static String teste2 = "teste";

    public enum TIPO_LOREN {

        PARAGRAFO, PALAVRAS
    }

    public enum TIPO_SINALIZADOR {

        COLCHETE, TAG, ASPAS
    }

    /**
     *
     * @param ptexto
     * @param pTipoBusca
     * @return Uma lista com as palavras encontradas entre os Sinalizadores ex:
     * [teste1] 123 [teste2] retorna uma lista com as palavras teste1 e teste 2
     */
    public static List<String> getListaStringEntreCaracter(String ptexto, TIPO_SINALIZADOR pTipoBusca) {
        switch (pTipoBusca) {
            case ASPAS:
                return getListaStringEntreCaracter(ptexto, '"', '"');
            case TAG:
                return getListaStringEntreCaracter(ptexto, '<', '>');
            case COLCHETE:
                return getListaStringEntreCaracter(ptexto, '[', ']');

        }
        return getListaStringEntreCaracter(ptexto, '"', '"');
    }

    /**
     *
     * Retorna String entre determinados caracteres exemplo:
     *
     * texto= dfkjklaskdjf[parametroum] [parametro2] inicial=[ final= ]
     * retornaria uma lista com parametroum,parametro2
     *
     * @param texto texto onde as strings serão encontradas
     * @param pInicial caracter que indica o inicio da string
     * @param pFinal caracter que indica o fim da string
     * @return
     */
    public static List<String> getListaStringEntreCaracter(String texto, char pInicial, char pFinal) {

        char cInicial = pInicial;
        char cFinal = pFinal;
        List<String> resposta = new ArrayList<>();

        String regex = "(" + cInicial + "\\w" + cFinal + ")+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(texto);

        while (m.find()) {
            resposta.add(m.group());
        }
        return resposta;
    }

    public static String removeCaracteresEspeciais(String param) {
        param = Normalizer.normalize(param, Normalizer.Form.NFD);
        param = param.replaceAll("[^\\p{ASCII}]", "");
        return param;

    }

    public static String removeCaracteresEspeciaisEspacosETracos(String param) {
        param = Normalizer.normalize(param, Normalizer.Form.NFD);
        param = param.replaceAll("[^\\p{ASCII}]", "");
        param = param.replace(" ", "");
        param = param.replace(":", "");
        return param;

    }

    public static String makeStrUrlAmigavel(String param) {
        String resposta = removeCaracteresEspeciais(param);
        resposta = resposta.replace(" ", "_");
        resposta = resposta.replace(":", "-");
        return resposta;
    }

    public static List<String> getlistadeLinhas(String pString) {
        String[] linhas = pString.split("[\\r\\n]+");
        List<String> listaLinhas = new ArrayList<>();

        for (String ln : linhas) {
            listaLinhas.add(ln);
        }
        return listaLinhas;

    }

    /**
     *
     * Gera uma única string adicionando um /n entre cada uma
     *
     * @param lista Lista de Strings
     * @return Rrtorna uma única string adicionando um /n entre cada uma
     */
    public static String getStringDaListaComBarraN(List<String> lista) {
        return getStringDaListaComSeparador(lista, "\n");

    }

    /**
     *
     *
     *
     * Gera uma única string adicionando um Caracter especifico entre cada uma
     *
     * @param lista
     * @param separador
     * @return
     */
    public static String getStringDaListaComSeparador(List<String> lista, String separador) {
        String resultado = "";
        if (lista == null) {
            return null;
        }
        for (String iten : lista) {
            resultado = resultado + separador + iten;
        }
        return resultado;

    }

    /**
     *
     * Separa palavras que iniciam com maisuculas emmm uma lista de Strings
     *
     * @param pParametros
     * @return
     */
    public static List<String> splitMaiuscula(String pParametros) {
        List<String> resp = new ArrayList<String>();
        String nomeTabela = "";
        int i = 0;
        for (char car : pParametros.toCharArray()) {
            if (Character.isUpperCase(car)) {
                if (nomeTabela.equals("") || nomeTabela == null) {
                    nomeTabela = "" + car;
                } else {
                    resp.add(nomeTabela);
                    nomeTabela = "" + car;
                }
            } else {
                nomeTabela = nomeTabela + car;
            }

            if (pParametros.length() == i + 1) {
                resp.add(nomeTabela);
            }
            i++;
        }
        return resp;
    }

    /**
     *
     * Procura e retorna o conteúdo que estiver entre parenteses
     *
     * @param parametro String onde o conteúdo será pesquisado
     * @return a string que estava entre parenteses
     */
    public static String strValorEntreParenteses(String parametro) {

        int inicioIndex = parametro.lastIndexOf("(");

        if (inicioIndex != -1) {

            int fimIndex = parametro.lastIndexOf(")");
            if (fimIndex != -1) {
                return parametro.substring(inicioIndex + 1, fimIndex);
            }

        }

        return null;
    }

    /**
     *
     * Util para consultas de sql, onde os paramentros estão nomeados como:
     * :nomedoParametro ele substitui este parametro por ?
     *
     * @param parametro
     * @return
     */
    public static String substituiParametrosNomeadosPorInterroga(String parametro) {

        return UtilSBCoreParametrosEmString.substituiParametrosNomeadosPorInterroga(parametro);
    }

    public static Date dtMesBarraAno(String parametro) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat("DD/mm/yy");

        return frm.parse(parametro);

    }

    /**
     *
     * Compara 2 arrays de String e retorna true caso os arrays senam iguais
     *
     * @param ar1 array 1 para comparaçõa
     * @param ar2 array 2 para comparaçõa
     * @return
     */
    public static boolean compara2StrArrays(String[] ar1, String[] ar2) {
        if (ar1.length != ar2.length) {
            return false;
        }
        int i = 0;
        for (String comp1 : ar1) {
            if (!comp1.equals(ar2[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     *
     *
     *
     * @param linha String onde o valor vai ser encontrado
     * @param posicaoInicial Início posição esperada
     * @param posicaoFinal Fim posição esperada
     * @param pQuantidaCaracteres Tamanho esperado (em caso de usar Regex para
     * localizar parte)
     * @param descarte Caso encontre este valor descarte
     * @param confirma Caso não possua esste valor descarte
     * @param regex Caso não passe pela string regex descarte
     * @return
     */
    public static String localizarParte(String linha, int posicaoInicial, int posicaoFinal, Integer pQuantidaCaracteres, String descarte, String confirma, String regex) {

        boolean registroValido = true;
        if (pQuantidaCaracteres == null || pQuantidaCaracteres == 0) {
            pQuantidaCaracteres = posicaoFinal - posicaoInicial;
        }

        String retorno = "";
        if (linha.length() >= posicaoInicial + (posicaoFinal - posicaoInicial)) {
            retorno = linha.substring(posicaoInicial, posicaoFinal);
        }
        if (descarte != null) {
            if (retorno.contains(descarte)) {
                registroValido = false;
            }
        }
        if (confirma != null) {
            if (!retorno.contains(confirma)) {
                registroValido = false;
            }
        }
        if (regex != null) {
            Pattern padrao = Pattern.compile(regex);
            Matcher comp = padrao.matcher(retorno);

            if (!comp.find()) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Leitura de SBsubstringPLus ignorada por regex=" + regex + " valor:" + retorno, null);
                registroValido = false;
            } else {
                if (pQuantidaCaracteres > retorno.length()) {
                    pQuantidaCaracteres = retorno.length();
                }
                try {
                    retorno = retorno.substring(comp.start(), pQuantidaCaracteres);

                } catch (Exception e) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Tamanho do caractér incorreto", e);
                    registroValido = false;
                }
            }

        }
        if (!registroValido) {
            retorno = "";
        }
        return retorno;

    }

    public static String[] getStringsByURL() {
        throw new UnsupportedOperationException("NÃO IMPLEMENTADO");
    }

    /**
     *
     *
     * @return
     */
    public static List<String> getStringsByLocalFile() {
        throw new UnsupportedOperationException("NÃO IMPLEMENTADO");
    }

    /**
     *
     * Gera uma lista de Strings sendo cada uma referente a uma linha do arquivo
     *
     * @param pInputStream Origem do arquivo para leitura
     * @return Lista de strings, cada linha em uma String
     */
    public static List<String> getStringsByInputStream(InputStream pInputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        List<String> resp = new ArrayList<>();
        String line;
        try {

            br = new BufferedReader(new InputStreamReader(pInputStream));
            while ((line = br.readLine()) != null) {
                resp.add(line);

            }

        } catch (IOException e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo string a partir de arquivo", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro fechando arquivo", e);
                }
            }
        }

        return resp;
    }

    /**
     *
     * Retorna uma String a partir de uma fonte de InputStream
     *
     * @param pInputStream
     * @return
     */
    public static String getStringByInputStream(InputStream pInputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(pInputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo string a partir de arquivo", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro fechando arquivo", e);
                }
            }
        }

        return sb.toString();
    }

    /**
     *
     * Retorna uma String apartir de um outrput
     *
     * @param pOutputStream
     * @return
     */
    public static String getStringByOutputStream(OutputStream pOutputStream) {
        ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
        try {
            pOutputStream.write(byte1.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(UtilSBCoreStrings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byte1.toString();
    }

    /**
     *
     * Utiliza .replaceAll com regex para substituir um espaço por algum
     * caracter específico ########## ATENÇÃO, ESTE MÉTODO RETIRA AS QUEBRAS DE
     * LINHA
     *
     * @param pString
     * @param pParametro
     * @return
     */
    public static String substituirEspacosPorCaracter(String pString, String pParametro) {
        return pString.replaceAll("\\s", pParametro);
    }

    /**
     *
     *
     * Formata string com LPad Exemplo texto [exemplo] tamanho [10] digito [-]
     * vira: [---exemplo]
     *
     * @param pValor String que será formatada
     * @param pCasas número de casas
     * @param pDigito digito Digito que ocupará os espacos
     *
     *
     * @return Retorna a string formatada
     */
    public static String getLpad(String pValor, int pCasas, String pDigito) {
        try {

            /*String resultado = String.format("%" + pCasas + "d", Integer.parseInt(pValor));
             resultado = resultado.replace(" ", digito);
             return resultado;
             */
            pValor = String.format("%" + pCasas + "s", pValor).replace(" ", pDigito);

            return pValor;

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao formatar Lpad", e);
            return pValor;
        }
    }

    /**
     *
     * Formata string com RPad Exmplo texto [exemplo] tamanho [10] digito [-]
     * vira: [exemplo---]
     *
     * @param pValor String que será formatada
     * @param pCasas x número de casas
     * @param pDigito - digito Digito que ocupará os espacos
     *
     *
     * @return Retorna a string formatada
     */
    public static String getRpad(String pValor, int pCasas, String pDigito) {
        try {

            /*String resultado = String.format("%" + pCasas + "d", Integer.parseInt(pValor));
             resultado = resultado.replace(" ", digito);
             return resultado;
             */
            pValor = String.format(pValor, "%" + pCasas + "s").replace(" ", pDigito);

            return pValor;

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao formatar Lpad", e);
            return pValor;
        }
    }

    /**
     * Gera Loren Ipsum para utilização em textos
     *
     * @param pQuantidade
     * @param pTipoLTipo_loren
     * @return
     */
    public static String GetLorenIpsilum(int pQuantidade, TIPO_LOREN pTipoLTipo_loren) {
        if (pQuantidade == 0) {
            pQuantidade = 3;
        }

        LoremIpsum lorem = new LoremIpsum();
        switch (pTipoLTipo_loren) {
            case PALAVRAS:

                return lorem.getWords(pQuantidade);
            case PARAGRAFO:
                return lorem.getParagraphs(pQuantidade);

        }
        return lorem.getWords(3);

    }

    /**
     * Gera Lorem ipsulum de 3 palavras
     *
     * @param pTipoLTipo_loren
     * @return Loren ipsulum de 3 palavras ou pragrafos de acordo com o campo
     * escolhido
     */
    public static String getLorenIpsilum(TIPO_LOREN pTipoLTipo_loren) {
        return GetLorenIpsilum(0, pTipoLTipo_loren);
    }

    /**
     *
     * Quebra a String adicionando /n a cada X caracteres, sem quebrar palavras
     * ao meio
     *
     * @param pString
     * @param caracteresMaximos
     * @return
     */
    public static String quebrarStringEmLinhas(String pString, int caracteresMaximos) {
        return WordUtils.wrap(pString, caracteresMaximos);
    }

    /**
     *
     * Coleta as primeiras letras de uma String utilizando substring
     *
     * @param pString
     * @param NumeroCasas
     * @return
     */
    public static String getPrimeirasXLetrasDaString(String pString, int NumeroCasas) {
        try {
            return pString.substring(0, NumeroCasas);

        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo primeiras letras da string" + pString, e);
        }
        return null;
    }

    /**
     *
     *
     *
     * @param pQuantidade quantidade de caracteres retornados
     * @return Retorna uma String randomica de acordo com o n de caracteres
     */
    public static String getStringRandomica(int pQuantidade) {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, pQuantidade);
    }

    /**
     *
     *
     * Retira as letras e retorna apenas os numeros na string
     *
     * @param pString String completa (com numeros e letras)
     * @return string Contendo apenas as letras do numro
     */
    public static String getNumericosDaString(String pString) {

        return pString.replaceAll("\\D*", ""); //To numeric digits only
    }

    public static String getMascaraJavaMaskParaJQueryMask(String pMascara) {
        if (pMascara != null) {

            return pMascara.replace('#', '9').replace('U', 'a').replace('?', '*').replace('L', 'a');
        } else {
            return "";
        }
    }
}

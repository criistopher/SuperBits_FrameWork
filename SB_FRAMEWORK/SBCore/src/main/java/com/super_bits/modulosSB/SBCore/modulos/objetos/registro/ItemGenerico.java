package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.util.ErrorMessages;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroDeMapaDeCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroObtendoValorDoCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroSetandoValorDeCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanReflexoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.validacaoRegistro.CampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfCalculos;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author sfurbino
 */
public abstract class ItemGenerico extends Object implements ItfBeanGenerico, ItfBeanReflexoes, Serializable {

    protected CampoMapValores camposEsperados;
    private Map<String, ItfCampoInstanciado> mapaCamposInstanciados;
    private Map<String, Field> mapaCampoPorAnotacao;
    private boolean mapeouTodosOsCampos = false;

    private Map<ItfCalculos, Boolean> controleCalculo;

    public static enum DEFINICAO_VIEW {
        NAO_DEFINIDO, PADRAO, PERSONALIZADO
    }

    protected void zerarControleCalculos() {

        getControleCalculo().keySet().stream().forEach((calc) -> {
            getControleCalculo().put(calc, false);
        });

    }

    protected Map<ItfCalculos, Boolean> getControleCalculo() {

        if (controleCalculo == null) {
            controleCalculo = new HashMap();
        }
        return controleCalculo;
    }

    // Descobrir um meio de obter o campo através da instancia (não pode se atravez do valor, precisa ser instancia para funcionar com campos com o mesmo valor
    //private Map<Object, Campo> mapaCamposByInstancia;
    private static Class<?> classeModelo;

    public String getTesteParametro(String pteste) {
        if (pteste == null) {
            pteste = "";
        }
        return "o parametro é" + pteste;

    }

    protected Object getInstancia() {
        return this;
    }

    /**
     *
     *
     * A classe Campo Item Generico instanciado oferece todas as propriedades
     * extendidas que um campo deve ter, e possui um getValor e SetValor, que
     * acessa o campo diretamente via reflexão
     *
     *
     */
    private class CampoIntemGenericoInstanciado extends CampoInstanciadoGenerico implements ItfCampoInstanciado {

        public CampoIntemGenericoInstanciado(Campo pcampo, Field pCampoReflection) {
            super(pcampo, pCampoReflection);

        }

        @Override
        public void setValor(Object pValor) {

            String infoMensagemErro = "";

            try {

                if (indiceValorLista > 0) {
                    List lista = (List) campoReflection.campo().get(getInstancia());
                    if (lista == null) {
                        return;

                    }
                    if (indiceValorLista <= lista.size() - 1) {
                        lista.set(indiceValorLista, pValor);
                    }

                } else {

                    //
                    infoMensagemErro = campoReflection.campo().getName();
                    String nomeMetodo = infoMensagemErro.substring(1);
                    char primeiraLetra = campoReflection.campo().getName().toUpperCase().charAt(0);
                    String marcador = Character.toString(primeiraLetra);
                    nomeMetodo = "set" + marcador + nomeMetodo;

                    try {

                        String classeDeclarada = campoReflection.campo().getDeclaringClass().toString();

                        String tipo = campoReflection.campo().getType().toString();

                        String classe = campoReflection.campo().getClass().toString();

                        String tipoGenerico = campoReflection.campo().getGenericType().toString();

                        Method metodo = getInstancia().getClass().getMethod(nomeMetodo, campoReflection.campo().getType());

                        if (tipo.equals("int")) {
                            try {

                                pValor = Integer.parseInt(pValor.toString());

                            } catch (Throwable t) {

                            }

                        }

                        if (tipo.equals("double")) {

                            pValor = Double.parseDouble(pValor.toString());

                        }

                        if (tipo.equals("float")) {

                            pValor = Float.parseFloat(pValor.toString());

                        }

                        if (metodo != null) {
                            try {
                                metodo.invoke(getInstancia(), pValor);

                            } catch (IllegalArgumentException t) {
                                String tipoClasse = "indefinido";
                                if (pValor != null) {
                                    tipoClasse = pValor.getClass().getSimpleName();
                                }

                                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Tentativa de chamar metodo Set com parametro incompativel: " + metodo.getName() + " o tipo do parametro enviado foi " + tipoClasse, t);

                            } catch (Throwable t) {

                            }
                        }

                    } catch (NoSuchMethodException metodoNaoExiste) {
                        System.out.println("O metodo não foi encontrado o valor foi acessado diretamente");
                        if (campoReflection.campo().getType() == int.class) {
                            int valor = (int) Integer.parseInt(pValor.toString());
                            campoReflection.campo().set(getInstancia(), valor);
                            return;
                        }

                        if (campoReflection.campo().getType() == double.class
                                || campoReflection.campo().getType() == double.class) {
                            double valor = (int) Double.parseDouble(pValor.toString());
                            campoReflection.campo().set(getInstancia(), valor);
                            return;
                        }

                        campoReflection.campo().setAccessible(true);
                        Object instancia = getInstancia();
                        Field cp = campoReflection.campo();

                        campoReflection.campo().set(getInstancia(), pValor);

                    } catch (Throwable t) {

                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ouve um erro ao inesperado ao setar um valor", t);

                    }

                    //
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, ex.getMessage() + "Erro setando valor via CampoGenericoInstanciado " + infoMensagemErro + " ", ex);
                System.out.println(ex.getCause());
                System.out.println(ex.getLocalizedMessage());
            }
        }

        @Override
        public Object getValor() {
            String infomensagemErro = "";
            try {

                if (indiceValorLista > 0) {

                    List lista = (List) campoReflection.campo().get(getInstancia());
                    if (lista == null) {
                        return null;

                    }
                    if (indiceValorLista <= lista.size() - 1) {
                        return lista.get(indiceValorLista);
                    } else {
                        return null;
                    }

                } else {

                    if (campoReflection == null) {
                        throw new UnsupportedOperationException("O campo" + infoCampo + "não possui um Field configurado");
                    }

                    infomensagemErro = campoReflection.campo().getName();
                    String nomeMetodo = infomensagemErro.substring(1);
                    char primeiraLetra = campoReflection.campo().getName().toUpperCase().charAt(0);
                    String marcador = Character.toString(primeiraLetra);
                    nomeMetodo = "get" + marcador + nomeMetodo;

                    try {

                        Method metodo = getInstancia().getClass().getMethod(nomeMetodo);

                        if (metodo != null) {
                            return metodo.invoke(getInstancia());
                        }

                    } catch (Throwable t) {

                        campoReflection.campo().setAccessible(true);
                        Object instancia = getInstancia();
                        Field cp = campoReflection.campo();
                        return cp.get(instancia);

                    }

                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, ex.getMessage() + "Erro obtendo valor do item Generico Instanciado" + infomensagemErro + " ", ex);
                System.out.println(ex.getCause());
                System.out.println(ex.getLocalizedMessage());
            }
            return null;
        }

        @Override
        public Object getParent() {
            return getInstancia();
        }

        @Override
        public boolean validarCampo() {
            NotNull naopodeSerNulo = campoReflection.campo().getAnnotation(NotNull.class);

            if (naopodeSerNulo == null) {
                if (getValor() == null) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public String getXhtmlVisao() {
            return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
        }

    }

    /**
     *
     * Obtem o um Objeto do Tipo Campo, analizando as anotações do Field ( do
     * java reflection)
     *
     * @param pCampo
     * @return
     */
    protected Campo getCampoByFieldReflexao(Field pCampo) {
        return FabCampos.getCampoByAnotacoesSimplesSemPersistencia(pCampo);
    }

    /**
     *
     * Retorna uma mapa de campos que não sejam estaticos de uma classe
     * contendo;
     *
     * @param object Classe Analizada
     * @return Mapa, contendo: nomeDoCampo,Field (do reflection Java)
     */
    protected static Map<String, Field> analyze(Object object) {
        try {

            if (object == null) {
                throw new NullPointerException("Erro obtendo análalise de propriedades da classe");
            }
            String teste = "";
            StringBuilder teaste = new StringBuilder();

            Map<String, Field> map = new TreeMap<>();

            Class<?> current = object.getClass();

            for (Field field : current.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!map.containsKey(field.getName())) {
                        map.put(field.getName(), field);
                    }
                }

            }

            return map;
        } catch (NullPointerException | SecurityException e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro analizando propriedades da class", e);
            return new TreeMap<>();
        }
    }

    /**
     *
     * Copia todos os campos de uma classe para outra do mesmo tipo, utilizando
     * reflection
     *
     * @param dados
     */
    public void copiaDados(Object dados) {
        if (dados == null) {
            return;
        }
        Map<String, Field> fromFields = analyze(dados);
        Map<String, Field> toFields = analyze(this);
        fromFields.keySet().retainAll(toFields.keySet());

        for (Entry<String, Field> fromFieldEntry : fromFields.entrySet()) {
            final String name = fromFieldEntry.getKey();
            final Field sourceField = fromFieldEntry.getValue();
            final Field targetField = toFields.get(name);
            if (targetField.getType().isAssignableFrom(sourceField.getType())) {
                sourceField.setAccessible(true);
                if (Modifier.isFinal(targetField.getModifiers())) {
                    continue;
                }
                targetField.setAccessible(true);
                try {
                    targetField.set(this, sourceField.get(dados));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Can't access field!");
                }
            }
        }
    }

    protected ItemGenerico() {
        super();
        this.camposEsperados = new CampoMapValores();
        classeModelo = this.getClass();
        UtilSBCoreReflexao.instanciarListas(this);
    }

    protected boolean isClasseFinalPesquisa(Class pClasse) {
        return (pClasse == ItemGenerico.class || pClasse == Object.class);

    }

    /**
     *
     * Retorna o Campo de acordo com a Anotação
     *
     * @param pNomedaAnotacao
     * @return
     */
    protected Field getCampoByAnotacao(FabCampos pNomedaAnotacao) {

        return UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(classeModelo, pNomedaAnotacao);

    }

    /**
     *
     * Cria um campo Esperado que não é obrigatório
     *
     *
     * @param pCampo
     * @deprecated
     */
    @Deprecated
    protected void adcionaCampoEsperado(CampoEsperado pCampo) {
        adcionaCampoEsperado(pCampo, false);
    }

    /**
     *
     * Quando um Objeto generico é criado, é nescessário especificar quais os
     * campos devem ser anotados ao extender a classe exemplo: Uma classe
     * abstrata que implementa ItfBeanGenerico deve ter em seu constructor
     *
     * adcionaCampoEsperado(new CampoEsperado(FabCampos.NOME_CURTO),true);
     * adcionaCampoEsperado(new CampoEsperado(FabCampos.ID),true);
     *
     * Desta forma sempre que a Classe for instanciada e estes campos NãO
     * tiverem sido anotados o sistema irá disparar um erro do tipo PARATUDO
     *
     * @param pCampo Campo que deve ser instanciado com o FabCampo
     * @param pObrigatorio (Especifica que a anotação de um campo deste tipo é
     * obrigatória na classe).
     */
    protected final void adcionaCampoEsperado(CampoEsperado pCampo, boolean pObrigatorio) {

        try {
            Field campo;

            campo = getCampoByAnotacao(pCampo.getTipoCampo());
            pCampo.setAnotacaoObrigatoria(pObrigatorio);
            if (campo != null) {
                pCampo.setCampoReflex(campo);
                pCampo.setFoiAnotado(true);

            } else {
                pCampo.setFoiAnotado(false);
                if (pObrigatorio) {
                    throw new UnsupportedOperationException("Campo " + pCampo.getTipoCampo() + " obrigatório não foi implementado na classe " + this.getClass().getSimpleName());
                }
            }

            camposEsperados.AdcionaCampo(pCampo);
        } catch (Throwable e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Campo obrigatório para o tipo de objeto não foi anotado com infoCampo, O tipo não encontrado foi: " + pCampo.getTipoCampo().name() + " Objeto: " + this.getClass().getSimpleName() + " (Atenção, nas proximas versões este erro irá gerar um PARATUDO", e);
        }

    }

    /**
     *
     * Procura o primeiro campo anotado com certa anotação e seta um valor a ele
     *
     * @param tipoCampo
     * @param valor
     */
    protected void setValorByTipoCampoEsperado(FabCampos tipoCampo, Object valor) {
        try {

            setValorByFieldReflexao(getCampoReflexaoByAnotacao(tipoCampo), valor);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro stando valor para Tipo de campo esperado", t);
        }
    }

    /**
     *
     * Procura o primeiro campo anotado com certa anotação e seta um valor do
     * tipo primitivo int a ele
     *
     * @param tipoCampo Anotação pesquisada
     * @param valor Valor a ser configurado
     */
    protected void setValorByTipoCampoEsperado(FabCampos tipoCampo, int valor) {
        try {

            setValorByFieldReflexao(getCampoReflexaoByAnotacao(tipoCampo), valor);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro stando valor para Tipo de campo esperado", t);
        }
    }

    private void setValorByFieldReflexao(Field pCampoReflexao, Object valor) throws ErroSetandoValorDeCampoPorReflexao {

        try {
            if (pCampoReflexao == null) {
                throw new UnsupportedOperationException("Chamada de SetValorByFieldReflexao, com Fileld nulo em " + this);
            }
            pCampoReflexao.setAccessible(true);

            pCampoReflexao.set(getInstancia(), valor);
            return;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando setar novo valor para o campo " + pCampoReflexao.getName() + " na classe " + this.getClass().getSimpleName() + " via reflection", ex);
        }

        throw new ErroSetandoValorDeCampoPorReflexao();

    }

    private void setValorByFieldReflexao(Field pCampoReflexao, int valor) throws ErroSetandoValorDeCampoPorReflexao {
        pCampoReflexao.setAccessible(true);
        try {
            pCampoReflexao.set(this, valor);
            return;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando setar novo valor via reflection", ex);
        }

        throw new ErroSetandoValorDeCampoPorReflexao();

    }

    private Object getValorByFieldReflexao(Field pCampoReflexao) throws ErroObtendoValorDoCampoPorReflexao {
        try {

            pCampoReflexao.setAccessible(true);

            Object valor;
            try {
                String tipoDeValor = pCampoReflexao.getType().getName();
                if (tipoDeValor.equals(String.class.toString())) {
                    valor = (String) pCampoReflexao.get(this);
                } else // System.out.println("TTTTIIIPOOOO diferente de String:"+campoReflecao.getType().getName());
                {
                    if (pCampoReflexao.getType().getName().equals("int")) {
                        // System.out.println("TTTTIIIPOOOO int");
                        valor = (Integer) pCampoReflexao.get(this);
                    } else if (pCampoReflexao.getType().getName()
                            .equals("java.lang.Double")
                            || pCampoReflexao.getType().getName()
                            .equals("double")) {
                        valor = (Double) pCampoReflexao.get(this);
                    } else if (pCampoReflexao.getType().getSimpleName()
                            .equals("Date")) {
                        valor = ((Date) pCampoReflexao.get(this)).toString();
                    } else if (pCampoReflexao.get(this) != null) {
                        valor = pCampoReflexao.get(this).toString();
                        return valor;
                    } else {
                        return null;
                    }
                }
                return valor;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo Valor do Campo tipo:" + pCampoReflexao, e);
            }

        } catch (SecurityException e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo Valor do Campo", e);
        }

        throw new ErroObtendoValorDoCampoPorReflexao();

    }

    /**
     *
     * Retorna o Valor da propriedade de acordo com a anotação
     *
     * @param tipoCampo Tipo do campo procurado
     * @return Valor da propriedade pojo anotada com o campo procurado
     */
    protected Object getValorByTipoCampoEsperado(FabCampos tipoCampo) {
        CampoEsperado campoEsperadoEncontrado = camposEsperados.getCampo(tipoCampo);

        if (campoEsperadoEncontrado.getFoiAnotado()) {

            Field campoReflecao = campoEsperadoEncontrado.getCampoReflex();

            Object valor;
            try {
                valor = getValorByFieldReflexao(campoReflecao);

                if (valor == null || valor.toString().equals("")) {
                    return campoEsperadoEncontrado.getValorPadrao();
                } else {
                    return valor;
                }

            } catch (ErroObtendoValorDoCampoPorReflexao ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O infoCampo  " + tipoCampo + " pesquisado não foi encontrado na classe" + this.getClass().getName(), ex);
            }

            if (campoEsperadoEncontrado.getAnotacaoObrigatoria()) {

                try {
                    throw new ErroDeMapaDeCampos(String.format(
                            ErrorMessages.CAMPO_ANOTACAO_OBRIGATORIO,
                            campoEsperadoEncontrado.getTipoCampo()));
                } catch (ErroDeMapaDeCampos e) {

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Um campo  do tipo " + campoEsperadoEncontrado.getTipoCampo().getRegistro() + "era esperado, e não foi localizado na classe" + this.getClass(), e);
                }
            }

            return campoEsperadoEncontrado.getValorPadrao();

        }
        return null;
    }

    /**
     *
     * @return This.class
     */
    @Deprecated
    public static Class<?> getClasseModelo() {

        return classeModelo;
    }

    private void carregaCampoInstanciado(Field pCampoReflection) {

        CampoIntemGenericoInstanciado campoformatado = new CampoIntemGenericoInstanciado(getCampoByFieldReflexao(pCampoReflection), pCampoReflection); //se encontrar adiciona duas vezes, para ser encontrado também pelo nome da anotacao
        mapaCamposInstanciados.put(pCampoReflection.getName(), campoformatado);
        InfoCampo anotacao = pCampoReflection.getAnnotation(InfoCampo.class);
        if (anotacao != null) {
            mapaCamposInstanciados.put(anotacao.tipo().toString().toUpperCase(), campoformatado);
        }

    }

    public Map<String, ItfCampoInstanciado> getmapaCamposInstanciados(String pCampo) {

        if (mapaCampoPorAnotacao == null) {
            mapaCamposInstanciados = new HashMap<>();
            mapaCampoPorAnotacao = new HashMap<>();
        }

        if (mapaCampoPorAnotacao.isEmpty()) {

            mapaCamposInstanciados = new HashMap<>();
            mapaCampoPorAnotacao = new HashMap<>();
            Class classeAnalizada = this.getClass();
            while (!UtilSBCoreReflexaoCampos.isClasseBasicaSB(classeAnalizada)) {

                for (Field campoEncontrado : classeAnalizada.getDeclaredFields()) {
                    InfoCampo anotacao = campoEncontrado.getAnnotation(InfoCampo.class);
                    if (anotacao != null) {
                        mapaCampoPorAnotacao.put(anotacao.tipo().toString().toUpperCase(), campoEncontrado);
                    }
                }
                classeAnalizada = classeAnalizada.getSuperclass();
            }

        }
        boolean pesquisouanotacao = false;
        pCampo = pCampo.replaceAll("\\[]", "");
        if (mapaCamposInstanciados.containsKey(pCampo)) {
            return mapaCamposInstanciados;
        } else {

            Class classeAnalizada = this.getClass();

            while (!UtilSBCoreReflexaoCampos.isClasseBasicaSB(classeAnalizada)) {

                try {
                    Field campoEncontrado = classeAnalizada.getDeclaredField(pCampo);
                    campoEncontrado.setAccessible(true);
                    carregaCampoInstanciado(campoEncontrado);
                    return mapaCamposInstanciados;
                    // Caso não encontre pelo nome, procura pela anotação
                } catch (NoSuchFieldException | SecurityException ex) {
                    if (!pesquisouanotacao) {
                        pesquisouanotacao = true;
                        if (mapaCampoPorAnotacao.containsKey(pCampo.toUpperCase())) {
                            carregaCampoInstanciado(mapaCampoPorAnotacao.get(pCampo.toUpperCase()));
                            return mapaCamposInstanciados;
                        }
                    }

                }
                classeAnalizada = classeAnalizada.getSuperclass();
            }

        }

        return mapaCamposInstanciados;

    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(CaminhoCampoReflexao pCaminhoCampo) {
        return getCampoByNomeOuAnotacao(pCaminhoCampo.getCaminhoSemNomeClasse());
    }

    @Override
    public Object getValorCampoByCaminhoCampo(CaminhoCampoReflexao pCaminhoCampo) {
        try {
            ItfCampoInstanciado campoInstanciado = getCampoByNomeOuAnotacao(pCaminhoCampo.getCaminhoSemNomeClasse());
            if (campoInstanciado == UtilSBCoreReflexaoCampos.CAMPO_NAO_IMPLEMENTADO) {
                return null;
            }
            Object resposta = campoInstanciado.getValor();
            return resposta;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo valor de campo:" + pCaminhoCampo + " na classe " + this.getClass().getSimpleName(), t);
            throw new UnsupportedOperationException("impossivel obter valor pelo caminho do campo");
        }

    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNomeOuANotacao) {

        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(pNomeOuANotacao)) {
            return UtilSBCoreReflexaoCampos.getCampoSeparador(pNomeOuANotacao);
        }
        int quantidade = UtilSBCoreReflexaoCampos.getQuantidadeSubCampos(pNomeOuANotacao);

        if (quantidade == 1) {
            TIPO_ORIGEM_VALOR_CAMPO tipoOrigem = UtilSBCoreReflexaoCampos.getTipoCampoLista(pNomeOuANotacao);

            switch (tipoOrigem) {
                case VALOR_COM_LISTA:
                case VALOR_LIVRE:
                    return getmapaCamposInstanciados(pNomeOuANotacao).get(pNomeOuANotacao);
                case VALORES_COM_LISTA:
                case VALORES_LIVRE:
                    String nomeCampoSemIndice = UtilSBCoreReflexaoCampos.getListaSemColchete(pNomeOuANotacao);
                    return getmapaCamposInstanciados(nomeCampoSemIndice).get(nomeCampoSemIndice);

                case REGISTRO_ESTATICO_DA_LISTA:
                    int idIndiceCampo = UtilSBCoreReflexaoCampos.getIdCampoDaLista(pNomeOuANotacao);
                    String nomeCampoSemIndice2 = UtilSBCoreReflexaoCampos.getListaSemColchete(pNomeOuANotacao);
                    ItfCampoInstanciado cp = getmapaCamposInstanciados(nomeCampoSemIndice2).get(nomeCampoSemIndice2);

                    cp.setIndiceValorLista(idIndiceCampo);
                    return cp;
                default:
                    throw new AssertionError(tipoOrigem.name());

            }

        } else {

            String nomeProximoObjeto = UtilSBCoreReflexaoCampos.getStrPrimeiroCampoDoCaminhoCampo(pNomeOuANotacao);

            TIPO_ORIGEM_VALOR_CAMPO tipo = UtilSBCoreReflexaoCampos.getTipoCampoLista(nomeProximoObjeto);

            if (tipo.equals(TIPO_ORIGEM_VALOR_CAMPO.REGISTRO_ESTATICO_DA_LISTA)) {

                int idReflexao = UtilSBCoreReflexaoCampos.getIdCampoDaLista(nomeProximoObjeto);
                String nomeCampoSemIndice = UtilSBCoreReflexaoCampos.getListaSemColchete(nomeProximoObjeto);
                ItfCampoInstanciado lista = getmapaCamposInstanciados(nomeCampoSemIndice).get(nomeCampoSemIndice);
                ItfBeanSimples itemDaLista = (ItfBeanSimples) ((List) lista.getValor()).get(idReflexao);
                String nomeProximoCAmpo = UtilSBCoreReflexaoCampos.getStrCaminhoCampoSemPrimeiroCampo(pNomeOuANotacao);
                return itemDaLista.getCampoByNomeOuAnotacao(nomeProximoCAmpo);

            } else {

                ItfCampoInstanciado itemAtual = getmapaCamposInstanciados(nomeProximoObjeto).get(nomeProximoObjeto);

                if (itemAtual == null) {
                    return UtilSBCoreReflexaoCampos.CAMPO_NAO_IMPLEMENTADO;
                }

                ItemGenerico itemSuperior = (ItemGenerico) itemAtual.getValor();

                if (itemSuperior == null) {
                    return UtilSBCoreReflexaoCampos.CAMPO_NAO_IMPLEMENTADO;
                }
                String nomeRestante = UtilSBCoreReflexaoCampos.getStrCaminhoCampoSemPrimeiroCampo(pNomeOuANotacao);
                return itemSuperior.getCampoByNomeOuAnotacao(nomeRestante);
            }
        }
    }

    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabCampos pTipocampo) {
        return getCampoByNomeOuAnotacao(pTipocampo.toString());
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(this.getClass(), pInfoCampo);
    }

    @Override
    public boolean isTemCampoAnotado(FabCampos pCampo) {
        return UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(this.getClass(), pCampo) != null;
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        try {
            Field campo = UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(this.getClass(), pInfocampo);
            if (campo == null) {
                throw new UnsupportedOperationException("a anotação " + pInfocampo + " não foi encontrada em " + this.getClass().getSimpleName());
            }
            return campo.getName();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo nome do campo em" + this.getClass().getSimpleName() + "<b>Utilize isTem campo anotado para verificar se o item possui ou não este campo declarado </b>", t);
            return null;
        }
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso
    ) {
        throw new UnsupportedOperationException("Upload de foto de item generico não foi implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public List<CampoInvalido> getCamposInvalidos() {

        throw new UnsupportedOperationException("A lista de campos instanciaados está sob análise, sem tempo previsto para retorno do desenvolivmento");

    }

    @Override
    @Deprecated
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {

        throw new UnsupportedOperationException("A lista de campos instanciaados invalidos está sob análise, sem tempo previsto para retorno do desenvolivmento");

    }

    /**
     *
     * Localiza o nomeCurto, retira acentos, espaços e caracteres especiais,
     * coloca tudo em maiusculo, e calcula o hash desta palavra
     *
     * Exemplo de utilização: Para integrar ids em sistemas diferentes
     *
     *
     */
    @Override
    public void configIDPeloNome() {
        try {
            String nomeparaHash = (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
            nomeparaHash = UtilSBCoreStrings.removeCaracteresEspeciaisEspacosETracos(nomeparaHash);
            nomeparaHash = nomeparaHash.toUpperCase();
            setValorByTipoCampoEsperado(FabCampos.ID, nomeparaHash.hashCode());
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando definir o ID para o item" + this.getClass().getName(), t);

        }
    }

    /**
     *
     *
     *
     * @return Todos os campos Instanciados do Objeto
     */
    @Deprecated
    public List<ItfCampoInstanciado> getTodosCamposInstanciados() {

        if (mapeouTodosOsCampos) {
            return Lists.newLinkedList(mapaCamposInstanciados.values());
        }

        for (Class classse : UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(classeModelo)) {
            for (Field campo : classse.getDeclaredFields()) {
                getCampoByNomeOuAnotacao(campo.getName());
            }
        }
        mapeouTodosOsCampos = true;
        return Lists.newLinkedList(mapaCamposInstanciados.values());
    }

    @Override
    public List<CaminhoCampoReflexao> getEntidadesVinculadas() {

        return Lists.newArrayList(UtilSBCoreReflexaoCampos.getCamposComSubCamposDaClasse(this.getClass()).values());

    }

    /**
     *
     * @param pCaminho
     * @return
     */
    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(CaminhoCampoReflexao pCaminho
    ) {
        try {

            // to do substituir por acesso direto sem precisar instanciar o CampoInstanciado
            return (ItfBeanSimples) getCampoByNomeOuAnotacao(pCaminho.getCaminhoSemNomeClasse()).getValor();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter item por caminho do campo" + pCaminho, t);
        }
        return null;
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(CaminhoCampoReflexao pCaminho
    ) {
        try {

            return (List) getCampoByNomeOuAnotacao(pCaminho.getCaminhoSemNomeClasse()).getValor();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter item por caminho do campo " + pCaminho, t);
        }
        return null;
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo
    ) {

        try {
            ItfBeanSimples ret = null;
            if (pNomeCampo == null) {
                throw new UnsupportedOperationException("String nula enviada para obter um bean simples por nome campo");
            }
            try {

                Field cp = getClass().getDeclaredField(pNomeCampo);
                cp.setAccessible(true);
                ret = (ItfBeanSimples) cp.get(this);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Campo: [" + pNomeCampo + "] não encontrado na classe : [" + getClass().getSimpleName() + "]", ex);
            }
            return ret;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter campo BenSimples pelo nome" + pNomeCampo, t
            );
        }
        return null;
    }

    @Override
    public String getNomeDoObjeto() {
        return UtilSBCoreReflexao.getNomeDoObjetoPorAnotacaoInfoClasse(this.getClass());
    }

    /**
     *
     * Adiciona um novo item na em uma lista da entidade ex:
     *
     * uma classe chamada EntidadeParanormal contendo private List<Pedidos>
     * meusPedidos;
     *
     * ao chamar adicionarItemNaLista("meusPedidos") um novo pedido é adicionado
     * na lista
     *
     * @param nomeDaLista
     */
    @Override
    public void adicionarItemNaLista(String nomeDaLista
    ) {
        nomeDaLista = nomeDaLista.replace("[]", "");
        try {
            Field campo = this.getClass().getDeclaredField(nomeDaLista);
            campo.setAccessible(true);
            List lista = (List) campo.get(this);
            Class classeTipo = UtilSBCoreReflexaoCampos.getClasseGenerica(campo);

            lista.add(classeTipo.newInstance());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando registro em  lista:" + nomeDaLista, ex);
        }
    }

    public EstruturaDeEntidade getEstruturaDaEntidade() {
        return MapaObjetosProjetoAtual.getEstruturaObjeto(this.getClass());
    }

    public String getXhtmlVisualizacao() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

}

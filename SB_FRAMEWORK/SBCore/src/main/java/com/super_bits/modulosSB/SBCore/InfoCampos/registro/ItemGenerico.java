package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.util.ErrorMessages;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.InfoCampos.excecao.ErroDeMapaDeCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.excecao.ErroObtendoValorDoCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.excecao.ErroSetandoValorDeCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanReflexoes;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.validacaoRegistro.CampoInvalido;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
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
 *
 *
 *
 * @author sfurbino
 */
public abstract class ItemGenerico extends Object implements ItfBeanGenerico, ItfBeanReflexoes, Serializable {

    protected CampoMapValores camposEsperados;

    private Map<String, ItfCampoInstanciado> mapaCamposInstanciados;

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
            try {
                campoReflection.set(getInstancia(), pValor);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro setando valor via CampoGenericoInstanciado", ex);
            }
        }

        @Override
        public Object getValor() {
            String infomensagemErro = "";
            try {
                if (campoReflection == null) {
                    throw new UnsupportedOperationException("O campo" + infoCampo + "não possui um Field configurado");
                }
                infomensagemErro = campoReflection.getName();
                campoReflection.setAccessible(true);
                Object instancia = getInstancia();
                Field cp = campoReflection;
                return cp.get(instancia);
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
            NotNull naopodeSerNulo = campoReflection.getAnnotation(NotNull.class);

            if (naopodeSerNulo == null) {
                if (getValor() == null) {
                    return false;
                }
            }

            return true;
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
     * Cria um mapa com todos os campos da classe.
     *
     * Aqueles encontrados encontrados comgetCamposInstaciadosInvalidos a
     * Anotação @InfoCampo, tem suas configurações personalizadas
     *
     * Aqueles sem esta anotação são configurados de maneira padrão de acordo
     * com o tipo
     *
     */
    protected void makeMapaCampos() {

        // Por segurança, sai caso o campo já tenha sido criado
        if (mapaCamposInstanciados != null) {
            if (mapaCamposInstanciados.isEmpty()) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("O mapa de campos não foi criado, pois nenhum campo foi encontrado, este erro é grave, reflita sobre a vida, tome um café quem sabe..", null);
            }
            return;
        }

        // Definindo Classe Para analize de campos
        Class<?> classeAnalizada = this.getClass();
        mapaCamposInstanciados = new HashMap<>();

        while (!UtilSBCoreReflexaoCampos.isClasseBasicaSB(classeAnalizada)) {
            for (Field campoEncontrado : classeAnalizada.getDeclaredFields()) {
                campoEncontrado.getAnnotations(); // SE o campo não for estatico
                if (!Modifier.isStatic(campoEncontrado.getModifiers())) {
                    campoEncontrado.setAccessible(true);
                    // Se este campo não foi adicionado antes (por segurança)
                    if (!mapaCamposInstanciados.containsKey(campoEncontrado.getName())) {
                        InfoCampo anotacao = campoEncontrado.getAnnotation(InfoCampo.class);
                        CampoIntemGenericoInstanciado campoformatado = new CampoIntemGenericoInstanciado(getCampoByFieldReflexao(campoEncontrado), campoEncontrado); //se encontrar adiciona duas vezes, para ser encontrado também pelo nome da anotacao
                        mapaCamposInstanciados.put(campoEncontrado.getName(), campoformatado);
                        if (anotacao != null) {
                            mapaCamposInstanciados.put(anotacao.tipo().toString(), campoformatado);
                        }
                    }
                }
            }
            classeAnalizada = classeAnalizada.getSuperclass();
        }
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
        } catch (Throwable e) {

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
     * @param name
     * @return
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

    public Map<String, ItfCampoInstanciado> getmapaCamposInstanciados() {
        if (mapaCamposInstanciados == null || mapaCamposInstanciados.isEmpty()) {

            makeMapaCampos();
        }
        return mapaCamposInstanciados;

    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(pNome)) {
            return UtilSBCoreReflexaoCampos.getCampoSeparador(pNome);
        }
        return getmapaCamposInstanciados().get(pNome);
    }

    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabCampos pTipocampo) {
        return getCampoByNomeOuAnotacao(pTipocampo.toString());
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(this.getClass(), pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(this.getClass(), pInfocampo).getName();
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CampoInvalido> getCamposInvalidos() {
        List<CampoInvalido> camposInvalidados = new ArrayList<>();
        for (Entry<String, ItfCampoInstanciado> campo : getmapaCamposInstanciados().entrySet()) {
            if (campo.getValue().validarCampo()) {
                CampoInvalido novoCampoInvalido = new CampoInvalido();
                novoCampoInvalido.setNomeCampo(campo.getValue().getLabel());
                novoCampoInvalido.setProblemaInvalidou(" não pode ser nulo");
                camposInvalidados.add(novoCampoInvalido);
            }
        }
        return camposInvalidados;

    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {

        List<ItfCampoInstanciado> camposInstanciadosInvalidados = new ArrayList<>();
        for (Entry<String, ItfCampoInstanciado> campo : getmapaCamposInstanciados().entrySet()) {
            if (campo.getValue().validarCampo()) {
                camposInstanciadosInvalidados.add(campo.getValue());

            }

        }
        return camposInstanciadosInvalidados;

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
    public List<ItfCampoInstanciado> getTodosCamposInstanciados() {
        List<ItfCampoInstanciado> camposInstanciados = new ArrayList<>();
        for (ItfCampoInstanciado campo : getmapaCamposInstanciados().values()) {
            if (!camposInstanciados.contains(campo)) {
                camposInstanciados.add(campo);

            }
        }
        return camposInstanciados;
    }

    @Override
    public List<CaminhoCampoReflexao> getEntidadesVinculadas() {

        return UtilSBCoreReflexaoCampos.getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(this.getClass());

    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(CaminhoCampoReflexao pCaminho) {
        try {

            int i = 0;

            ItfBeanSimples entidade = (ItfBeanSimples) this;
            for (String caminho : pCaminho.getPartesCaminho()) {

                // pulando a primeira parte do caminho que refere a ele mesmo
                if (i > 0) {
                    entidade = ((ItfBeanSimples) entidade).getBeanSimplesPorNomeCampo(caminho);
                    if (entidade == null) {
                        return null;
                    }
                }
                i++;

            }

            return entidade;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter item por caminho do campo", t);
        }
        return null;
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {

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
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, pNomeCampo, ex);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

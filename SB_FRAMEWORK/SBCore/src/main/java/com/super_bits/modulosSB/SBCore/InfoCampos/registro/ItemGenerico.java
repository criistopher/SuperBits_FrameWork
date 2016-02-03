package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.util.ErrorMessages;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.InfoCampos.excecao.ErroDeMapaDeCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.validacaoRegistro.CampoInvalido;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.NotNull;

/**
 *
 *
 *
 *
 *
 * @author sfurbino
 */
public abstract class ItemGenerico extends Object implements ItfBeanGenerico, Serializable {

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

    private class CampoIntemGenericoInstanciado extends CampoInstanciadoGenerico implements ItfCampoInstanciado {

        public CampoIntemGenericoInstanciado(Campo pcampo, Field pCampoReflection) {
            super(pcampo, pCampoReflection);

        }

        @Override
        public void setValor(Object pValor) {
            try {
                campoReflection.set(getInstancia(), pValor);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ItemGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public Object getValor() {
            try {
                return campoReflection.get(getInstancia());
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ItemGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        @Override
        public Object getParent() {
            return getInstancia();
        }

        @Override
        public boolean validarCampo() {
            NotNull naopodeSerNulo =campoReflection.getAnnotation(NotNull.class);

            if (naopodeSerNulo==null){
                if (getValor()==null ){
                    return false;
                }
            }
          
            return true;
        }

    }

    protected Campo getCampoByAnotacoes(Field pCampo) {
        return FabCampos.getCampoByAnotacoesSimplesSemPersistencia(pCampo);
    }

    /**
     * cria um mapa com todos os campos da classe.
     *
     * Aqueles encontrados encontrados comgetCamposInstaciadosInvalidos a Anotação @InfoCampo, tem suas
     * configurações personalizadas
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

        while (!UtilSBCoreCampoReflexao.isClasseBasicaSB(classeAnalizada)) {

            for (Field campoEncontrado : classeAnalizada.getDeclaredFields()) {
                campoEncontrado.getAnnotations(); // SE o campo não for estatico
                if (!Modifier.isStatic(campoEncontrado.getModifiers())) {
                    campoEncontrado.setAccessible(true);
                    // Se este campo não foi adicionado antes (por segurança)
                    if (!mapaCamposInstanciados.containsKey(campoEncontrado.getName())) {
                        InfoCampo anotacao = campoEncontrado.getAnnotation(InfoCampo.class);
                        CampoIntemGenericoInstanciado campoformatado = new CampoIntemGenericoInstanciado(getCampoByAnotacoes(campoEncontrado), campoEncontrado); //se encontrar adiciona duas vezes, para ser encontrado também pelo nome da anotacao
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

        return UtilSBCoreCampoReflexao.getSBCampobyTipoCampo(classeModelo, pNomedaAnotacao);

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
    protected void adcionaCampoEsperado(CampoEsperado pCampo, boolean pObrigatorio) {

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
     * Retorna o Valor da propriedade de acordo com a anotação
     *
     * @param tipoCampo Tipo do campo procurado
     * @return Valor da propriedade pojo anotada com o campo procurado
     */
    protected Object getValorByTipoCampoEsperado(FabCampos tipoCampo) {
        CampoEsperado campoPadrao = camposEsperados.getCampo(tipoCampo);

        classeModelo = this.getClass();
        if (campoPadrao.getFoiAnotado()) {
            Field campoReflecao;
            try {

                campoReflecao = campoPadrao.getCampoReflex();

                campoReflecao.setAccessible(true);
                // System.out.println(campoReflecao.toString());
                Object valor;
                try {
                    String tipoDeValor = campoReflecao.getType().getName();
                    if (tipoDeValor.equals("java.lang.String")) {
                        valor = (String) campoReflecao.get(this);
                    } else {

                        // System.out.println("TTTTIIIPOOOO diferente de String:"+campoReflecao.getType().getName());
                        if (campoReflecao.getType().getName().equals("int")) {
                            // System.out.println("TTTTIIIPOOOO int");
                            valor = (Integer) campoReflecao.get(this);
                        } else if (campoReflecao.getType().getName()
                                .equals("java.lang.Double")
                                || campoReflecao.getType().getName()
                                .equals("double")) {
                            valor = (Double) campoReflecao.get(this);
                        } else if (campoReflecao.getType().getSimpleName()
                                .equals("Date")) {
                            valor = ((Date) campoReflecao.get(this)).toString();

                        } else {

                            valor = campoReflecao.get(this).toString();
                        }
                    }

                    if (valor == null || valor.toString().equals("")) {
                        return campoPadrao.getValorPadrao();
                    } else {
                        return valor;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo Valor do Campo", e);
                }

            } catch (SecurityException e) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo Valor do Campo", e);
            }
        }

        if (campoPadrao.getAnotacaoObrigatoria()) {

            try {
                throw new ErroDeMapaDeCampos(String.format(
                        ErrorMessages.CAMPO_ANOTACAO_OBRIGATORIO,
                        campoPadrao.getTipoCampo()));
            } catch (ErroDeMapaDeCampos e) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro na função de Obter Valor pelo tipo Esperado", e);
            }
        }

        return campoPadrao.getValorPadrao();

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

    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return getmapaCamposInstanciados().get(pNome);
    }

    @Override
    public Field getCampo(FabCampos pInfoCampo) {
        return UtilSBCoreCampoReflexao.getSBCampobyTipoCampo(this.getClass(), pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return UtilSBCoreCampoReflexao.getSBCampobyTipoCampo(this.getClass(), pInfocampo).getName();
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CampoInvalido> getCamposInvalidos() {
            List<CampoInvalido> camposInvalidados=new ArrayList<>();
            for (Entry<String, ItfCampoInstanciado> campo: getmapaCamposInstanciados().entrySet()){
             if (campo.getValue().validarCampo()) {
                 CampoInvalido novoCampoInvalido=new CampoInvalido();
                 novoCampoInvalido.setNomeCampo(campo.getValue().getLabel());
                 novoCampoInvalido.setProblemaInvalidou(" não pode ser nulo");
                 camposInvalidados.add(novoCampoInvalido);
             }
             
         }
        return camposInvalidados;
     
       
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {

        
              List<ItfCampoInstanciado> camposInstanciadosInvalidados=new ArrayList<>();
            for (Entry<String, ItfCampoInstanciado> campo: getmapaCamposInstanciados().entrySet()){
             if (campo.getValue().validarCampo()) {
                 camposInstanciadosInvalidados.add(campo.getValue());
                 
             }
             
         }
        return camposInstanciadosInvalidados;
        
    }

    
    
    
    
    
    

}

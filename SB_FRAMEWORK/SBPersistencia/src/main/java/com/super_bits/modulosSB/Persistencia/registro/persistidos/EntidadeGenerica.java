package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.Controller.Interfaces.ItfCalculos;
import com.super_bits.modulosSB.Persistencia.Campo.CampoMultiplo;
import com.super_bits.modulosSB.Persistencia.Campo.FabCamposPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public abstract class EntidadeGenerica extends ItemGenerico implements Serializable {

    protected Field searchCampoIdentificacao() {

        Class classeDoCampo = this.getClass();
        Field campo;
        campo = UtilSBCoreReflexaoCampos.getSBCampobyTipoCampo(classeDoCampo, FabCampos.ID);
        if (campo == null) {
            campo = UtilSBCoreReflexaoCampos.getFieldByClasseAnotacao(classeDoCampo, Id.class);
        }

        return campo;
    }

    @Override
    protected void adcionaCampoEsperado(CampoEsperado pCampo) {
        Field campo;
        if (pCampo.equals("id")) {
            campo = searchCampoIdentificacao();
            pCampo.setAnotacaoObrigatoria(true);
        } else {
            super.adcionaCampoEsperado(pCampo);
        }
    }

    @Override
    protected Campo getCampoByFieldReflexao(Field pCampo) {
        return FabCamposPersistencia.getCampoByAnotacoes(pCampo);
    }

    @Override
    protected Field getCampoByAnotacao(FabCampos pNomedaAnotacao) {

        if (pNomedaAnotacao == FabCampos.ID) {
            return searchCampoIdentificacao();
        }

        return super.getCampoByAnotacao(pNomedaAnotacao);
    }

    public List<CampoMultiplo> getLookupMultuplo() {
        List<CampoMultiplo> resp = new ArrayList<CampoMultiplo>();
        for (Field campo : getCamposUmParaMuitos()) {
            try {
                campo.setAccessible(true);
                //Object teste = campo.get(this);
                CampoMultiplo novoCampo = new CampoMultiplo(campo, "teste",
                        campo.get(this));
                resp.add(novoCampo);

            } catch (IllegalArgumentException | IllegalAccessException e) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro localizando lookp Multiplo", null);
            }

        }
        return resp;
    }

    protected EntityManager getEm() {
        return UtilSBPersistencia.getNovoEM();
        //BeansUtil.getAppBean("dados")).getEm();
    }

    public void loadByID(int pId) {

        Object resultado = UtilSBPersistencia.getRegistroByID(this.getClass(), pId);
        System.out.println("ATENÇÃO O METODO LOAD BY ID AINDA NÃO SUPORTA CLASSES COM POLIMORFISMO DE ENTIDADE");
        //todo compativel com Extenção de classe
        if (resultado != null) {
            copiaDados(resultado);
        }

    }

    public void loadByID(int pId, EntityManager pEM) {

        Object resultado = UtilSBPersistencia.getRegistroByID(this.getClass(), pId, pEM);
        System.out.println("ATENÇÃO O METODO LOAD BY ID AINDA NÃO SUPORTA CLASSES COM POLIMORFISMO DE ENTIDADE");
        //todo compativel com Extenção de classe
        if (resultado != null) {
            copiaDados(resultado);
        }

    }

    public static Object createByID(int pId) {
        Class currentClass = new Object() {
        }.getClass().getEnclosingClass();
        System.out.println("classe clinica" + currentClass.getSimpleName());

        // return UtilSBPersistencia.getRegistroByID(currentClass, pId);
        throw new UnsupportedOperationException("Recurso para criar entidade via reflexão ainda não foi desenvolvido, motivo:"
                + "this.getClass não é suportado,  gabiarras precisam ser descobertas para obter a classe e vai ser nescessário "
                + " adcionar o loadByID na interface de entidade ");

    }

    protected EntidadeGenerica() {
        super();
    }

    protected static List<Field> getCamposUmParaMuitos() {
        List<Field> resposta = new ArrayList<Field>();
        Field[] fields = getClasseModelo().getDeclaredFields();

        for (Field field : fields) {
            OneToMany campoAnotado = field.getAnnotation(OneToMany.class);

            if (campoAnotado != null) {
                resposta.add(field);
            }
        }
        return resposta;
    }

    protected static List<Field> getCamposMuitosParaUm() {
        List<Field> resposta = new ArrayList<Field>();
        Field[] fields = getClasseModelo().getDeclaredFields();

        for (Field field : fields) {
            ManyToOne campoAnotado = field.getAnnotation(ManyToOne.class);
            if (campoAnotado != null) {
                resposta.add(field);
            }
        }
        return resposta;
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected Object getRetornoSoma() {
        // Obtem a anotação por reflexao do nome do metodo por atributo
        // seta o valor no atrbuto, e retorna o valor obtido
        ItfCalculos calculo = null;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String nomeMetodo = stackTraceElements[2].getMethodName();
        String nomeCampo = nomeMetodo.substring(3);
        nomeCampo = nomeCampo.substring(0, 1).toLowerCase() + nomeCampo.substring(1);
        try {
            Field campo = this.getClass().getDeclaredField(nomeCampo);
            campo.setAccessible(true);
            Annotation anotacao = UtilSBCoreReflexao.getAnotacaoComEsteMetodo(campo.getAnnotations(), "calculo");

            Method metodoCalculo = anotacao.annotationType().getMethod("calculo");

            calculo = (ItfCalculos) metodoCalculo.invoke(anotacao);

            return calculo.getValor(this);

            //anotacaoCalculo.get
        } catch (NoSuchFieldException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não Enconcontrou o campo " + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        } catch (SecurityException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha de segurança ao tentar modificar o campo: " + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        } catch (IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Acesso ilegal tentando identificar o campo: " + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        } catch (IllegalArgumentException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Argumento ilegal tentando configurar o campo: " + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        } catch (InvocationTargetException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro chamando método calculo na tentativa de obter a Fabrica de ação do campo " + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        } catch (NoSuchMethodException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O metodo calculo não foi encontrado na anotação do campo" + nomeCampo + " em " + this.getClass().getSimpleName(), ex);
        }

        return null;//calculo.getValor((ItfBeanSimples) this);
    }

}

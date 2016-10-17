package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.Campo.CampoMultiplo;
import com.super_bits.modulosSB.Persistencia.Campo.FabCamposPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.modulos.objetos.UtilSBCoreReflecaoIEstruturaEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfCalculos;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfListas;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
        List<CampoMultiplo> resp = new ArrayList<>();
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
        List<Field> resposta = new ArrayList<>();
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
        List<Field> resposta = new ArrayList<>();
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

    protected List getListaDaEtidade(boolean pAtualizarSempre) {

        String nomeCampo;
        String nomeMetodo = "Metodo não encontrado (este metodo só deve ser chamado dentro metodo get padrão pojo ex:   entidade.getValorDoCalculo();";
        try {

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            nomeMetodo = stackTraceElements[2].getMethodName();
            nomeCampo = nomeMetodo.substring(3);
            nomeCampo = nomeCampo.substring(0, 1).toLowerCase() + nomeCampo.substring(1);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando atributo de  lista atravez do metodo " + nomeMetodo + " na classe " + this.getClass().getSimpleName(), t);
            return null;
        }

        Field campo;

        try {

            ItfListas lista;
            campo = this.getClass().getDeclaredField(nomeCampo);
            lista = UtilSBCoreReflecaoIEstruturaEntidade.getListaByField(campo);

            List valorAnteriorLista = (List) campo.get(this);
            if (valorAnteriorLista != null) {
                if (valorAnteriorLista.size() > 0) {
                    if (pAtualizarSempre) {
                        campo.set(lista.getLista(this), this);
                    }
                }
            } else {
                campo.set(lista.getLista(this), this);
            }
            return (List) campo.get(this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando calculo para o campo" + nomeCampo + " na tabela " + this.getClass().getSimpleName(), ex);
            return null;
        }

    }

    /**
     *
     * Este metodo deve ser chamado em metodos padrão POJO do tipo get, ele
     * buscará o atributo referente ao método, e atravéz
     *
     * @return
     */
    protected Object getRetornoSoma() {
        // Obtem a anotação por reflexao do nome do metodo por atributo
        // seta o valor no atrbuto, e retorna o valor obtido
        String nomeCampo;
        String nomeMetodo = "Metodo não encontrado (este metodo só deve ser chamado dentro metodo get padrão pojo ex:   entidade.getValorDoCalculo();";
        try {

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            nomeMetodo = stackTraceElements[2].getMethodName();
            nomeCampo = nomeMetodo.substring(3);
            nomeCampo = nomeCampo.substring(0, 1).toLowerCase() + nomeCampo.substring(1);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando atributo de  calculo atravez do metodo " + nomeMetodo + " na classe " + this.getClass().getSimpleName(), t);
            return null;
        }

        Field campo;
        try {

            ItfCalculos calculo;
            campo = this.getClass().getDeclaredField(nomeCampo);
            calculo = UtilSBCoreReflecaoIEstruturaEntidade.getCalculoByField(campo);
            try {
                campo.set(calculo.getValor(this), calculo);
            } catch (Throwable t) {
                Logger.getGlobal().info("O calor do campo  " + campo.getName() + " não pode ser configurado por reflexão, o valor enviado foi" + calculo + " o erro que aconteceu foi: " + t.getMessage());
            }

            return campo.get(this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando calculo para o campo" + nomeCampo + " na tabela " + this.getClass().getSimpleName(), ex);
            return null;
        }

    }

}

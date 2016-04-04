package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.Campo.CampoMultiplo;
import com.super_bits.modulosSB.Persistencia.Campo.FabCamposPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.Serializable;
import java.lang.reflect.Field;
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
            campo = UtilSBCoreReflexaoCampos.getCampoByClasseAnotacao(classeDoCampo, Id.class);
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

    public Field getCampo(FabCampos pInfoCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void configIDFromNomeCurto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< HEAD
    public String getNomeCurto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeDoObjeto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNome(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
=======
>>>>>>> 060627d4dab0e6cd969ab7869bb986043ad4d8a5

}

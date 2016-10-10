/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.InfoCalculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfCalculos;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.CalculoDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.ListaDeEntidade;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.InfoLista;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreReflecaoIEstruturaEntidade {

    public static CalculoDeEntidade getCalculoEstruturaByField(Field campoReflection, Campo pCampo, EstruturaDeEntidade pEstrutura) {
        InfoCalculo infoCalculo = getAnotacaoCalulo(campoReflection);
        ItfCalculos calculo = getCalculoByField(campoReflection);
        String nomeEnum = calculo.toString();
        String descricao = infoCalculo.descricao();
        String tipoRetorno = campoReflection.getType().getSimpleName();
        String javaDoc = infoCalculo.descricao();
        CalculoDeEntidade calculoDeEntidade = new CalculoDeEntidade(nomeEnum, descricao, tipoRetorno, pCampo, pEstrutura, javaDoc);
        return calculoDeEntidade;
    }

    public static ListaDeEntidade getListaEstruturaByField(Field pCampoReflection) {
        return null;
    }

    public static InfoLista getInfoListaByEnum(ItfListas pLista) {
        Field campo;
        try {
            campo = pLista.getClass().getField(pLista.toString());
            InfoLista InfoLista = campo.getAnnotation(InfoLista.class);
            return InfoLista;
        } catch (NoSuchFieldException | SecurityException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A  anotação de info Lista não pode ser recuperada em: " + pLista.toString(), ex);
            return null;
        }

    }

    public static InfoCalculo getAnotacaoCalulo(Field pCampo) {
        try {
            ItfCalculos calculo = getCalculoByField(pCampo);
            Field campo = calculo.getClass().getField(calculo.toString());
            return campo.getAnnotation(InfoCalculo.class);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UtilSBCoreReflecaoIEstruturaEntidade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param pCampo
     * @return O enum do calculo, vinculado ao Campo
     * @throws UnsupportedOperationException Caso não encontre, dispara um erro
     */
    public static ItfCalculos getCalculoByField(Field pCampo) throws UnsupportedOperationException {
        try {
            ItfCalculos calculo = null;
            pCampo.setAccessible(true);
            Annotation anotacao = UtilSBCoreReflexao.getAnotacaoComEsteMetodo(pCampo.getAnnotations(), "calculo");
            Method metodoCalculo = anotacao.annotationType().getMethod("calculo");
            calculo = (ItfCalculos) metodoCalculo.invoke(anotacao);
            return calculo;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new UnsupportedOperationException("impossivel determinar o Calculo para o campo" + pCampo.getName(), ex);
        }
    }

    public static ItfListas getListaByField(Field pCampo) throws UnsupportedOperationException {
        try {
            ItfListas lista = null;
            pCampo.setAccessible(true);
            Annotation anotacao = UtilSBCoreReflexao.getAnotacaoComEsteMetodo(pCampo.getAnnotations(), "lista");
            Method metodoCalculo = anotacao.annotationType().getMethod("lista");
            lista = (ItfListas) metodoCalculo.invoke(anotacao);
            return lista;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new UnsupportedOperationException("impossivel determinar a lista para o campo" + pCampo.getName(), ex);
        }
    }

}

package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "conversorGenerico", forClass = ItfBeanSimplesSomenteLeitura.class)
public class ConversorGenerico implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        if (FacesContext.getCurrentInstance().isValidationFailed()) {

            System.out.println("Desnescessário converter a String [" + value + "] em Objeto: em: " + component.getFamily() + "." + component.getId() + "-" + component.getRendererType() + " falhou"
                    + ", a validação da view não Foi realizada com sucesso ");

            System.out.println(" ############ A AÇÃO NÃO FOI EXECUTADA, POIS O FORMULÁRIO NÃO FOI VALIDADO, CERTIFIQUE QUE TODOS OS "
                    + "INPUTS CONTIDOS NO FORM ESTEJAM PREENCHIDOS, E QUE AS MENSAGENS DE NÃO CONFORMIDADE TENHAM SIDO CONFIGURADAS, ");
            System.out.println("DEIXAR AÇÕES DE AJAX FORA DE FORMS, OU UM FORM DENTRO DO OUTRO TAMBÉM PODE CAUSAR PROBLEMAS IMPREVISÍVEIS DE VALIDAÇÃO");
            System.out.println(" ############");
            return null;
        }

        try {
            if (value != null) {
                // ItfBeanSimples entity =(ItfBeanSimples)
                // getAttributesFrom(component).get(value);
                // System.out.println(entity.getNomeCurto());
                // System.out.println("Convertido OBJ:" +value
                //+"retornado:"+getAttributesFrom(component).get(value)+"Objeto="+getAttributesFrom(component).get(value).getClass().toString());
                Object resposta = this.getAttributesFrom(component).get(value);
                if (resposta == null) {
                    System.out.println("objeto não Encontrado");
                }
                return resposta;

            }

        } catch (Exception e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro no conversor Generico, obtendo:" + value, e);
        }
        System.out.println("retornouNulo para " + value);
        return null;

    }

    /**
     *
     * Converte um objeto em String
     *
     *
     * @param ctx
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext ctx, UIComponent component,
            Object value) {

        try {

            if (value != null && !"".equals(value)) {
                ItfBeanSimplesSomenteLeitura item = (ItfBeanSimplesSomenteLeitura) value;

                if (item.getId() != 0) {
                    this.addAttribute(component, item);

                    if (item.getId() != 0) {
                        System.out.println(String.valueOf(item.getId()));
                        return String.valueOf(item.getId());
                    }
                    System.out.println("ExisteValor, mas o nome  é nulo");
                    return (String) value;
                }
            }
            System.out.println("OBJETO IMPROPRIO PARA CONVERSÃO" + value);
            return "";

        } catch (Exception e) {
            System.out.println("Erro de conversao STR");
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro de conversão generica As String FacesContext:" + ctx.toString() + "-- Component" + component.toString() + " objeto" + value, e);

        }
        System.out.println("Nao Convertido STRSTR:" + value);
        return "";

    }

    /**
     *
     * Adiciona o atributo no componente (mapa de nome do campo por Objeto)
     *
     * @param component
     * @param o
     */
    protected void addAttribute(UIComponent component, Object o) {
        String key = String.valueOf(((ItfBeanSimplesSomenteLeitura) o).getId()); // codigo da empresa
        // como chave neste
        // caso
        //  System.out.println("Adcionando Key e Objeto no mapa de atributos" + key);
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        Object resposta = component.getAttributes();
        if (resposta == null) {
            System.out.println("objeto nao encontrado");
        }
        return component.getAttributes();
    }

}

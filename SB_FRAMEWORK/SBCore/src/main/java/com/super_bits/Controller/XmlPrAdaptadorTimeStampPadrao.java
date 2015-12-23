/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converte TimeStamp padrão:
 *
 * ex: "20/12/2014 14:30:59"
 *
 * @author Salvio
 */
public class XmlPrAdaptadorTimeStampPadrao extends XmlAdapter<String, Date> {

    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Override
    public Date unmarshal(String v) throws Exception {
        try {
            return formato.parse(v);
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro convertendo" + v, e);

            return null;
        }
    }

    @Override
    public String marshal(Date v) throws Exception {
        try {
            return formato.format(v);
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro convertendo" + v, e);
            return null;
        }
    }

}

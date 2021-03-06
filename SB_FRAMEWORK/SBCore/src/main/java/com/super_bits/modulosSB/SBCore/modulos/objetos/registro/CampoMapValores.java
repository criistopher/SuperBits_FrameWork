package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.util.ErrorMessages;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroDeFormatoDoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroDeMapaDeCampos;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class CampoMapValores implements Serializable {

    private final Map<FabCampos, CampoEsperado> mapaDeCampos = new EnumMap<>(FabCampos.class);

    public void AdcionaCampo(CampoEsperado pCampo) {

        if ((pCampo.getTipoCampo() == null)) {
            try {
                throw new ErroDeMapaDeCampos(String.format(ErrorMessages.FIELD_CAN_NOT_BE_NULL, pCampo.getTipoCampo(), pCampo.getValorPadrao()));
            } catch (ErroDeMapaDeCampos e) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro adcionando campo esperado em CampoMapValores", e);
            }
        }
        // setando UPERCASE para os nomes dos campos
        this.mapaDeCampos.put(pCampo.getTipoCampo(), pCampo);
    }

    public CampoEsperado getCampo(FabCampos name) {

        CampoEsperado campo = this.mapaDeCampos.get(name);

        if (campo == null) {
            try {
                throw new ErroDeMapaDeCampos(String.format(ErrorMessages.FIELD_NOT_FOUNDED, name));
            } catch (ErroDeMapaDeCampos e) {

                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro adcionando campo esperado em CampoMapValores", e);

            }
        }
        return campo;
    }

    public Object getValorPadrao(FabCampos name) throws ErroDeMapaDeCampos {
        CampoEsperado campo = getCampo(name);
        return campo.getValorPadrao();
    }

    public Integer getIntValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        Object value = getValorPadrao(name);

        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Long getLongValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Double getDoubleValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Float getFloatValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Boolean getBooleanValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Character getCharacterValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        if (value.length() != 1) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name));
        }

        try {
            return value.charAt(0);
        } catch (NumberFormatException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

    public Date getDateValue(FabCampos name) throws ErroDeMapaDeCampos, ErroDeFormatoDoCampo {
        String value = getValorPadrao(name).toString();

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        try {
            return formater.parse(value);
        } catch (ParseException e) {
            throw new ErroDeFormatoDoCampo(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
        }
    }

}

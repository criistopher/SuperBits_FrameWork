package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.lang.reflect.Field;
import java.util.List;

@SuppressWarnings("serial")
public abstract class TabelaLigacao extends EntidadeGenerica implements ItfTabelaLigacao {

    private ItfBeanSimples getcampo(int pNumero) {
        String nomeTabela = this.getClass().getSimpleName();
        List<String> tabelasStr = UtilSBCoreStrings.splitMaiuscula(nomeTabela);
        try {
            Field field = this.getClass().getDeclaredField(tabelasStr.get(pNumero - 1).toLowerCase());
            field.setAccessible(true);
            return (ItfBeanSimples) field.get(this);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro getCampo pelo numero em TabelaLigacao", e);
        }
        return null;

    }

    public ItfBeanSimples getCampo1() {
        return getcampo(1);
    }

    public ItfBeanSimples getCampo2() {
        return getcampo(2);

    }

    public ItfBeanSimples getCampoDiferente(String pParamentro) {

        List<String> tabelasStr = UtilSBCoreStrings.splitMaiuscula(this.getClass().getSimpleName());

        if (tabelasStr.get(0).equals(pParamentro)) {
            return getcampo(2);
        } else {
            return getcampo(1);
        }

    }

}

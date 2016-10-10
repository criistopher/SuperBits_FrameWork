package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreReflexaoObjetoSuperBits {

    public static String getNomeObjeto(Class<? extends ItemGenerico> pClasse) {
        try {
            InfoClasse info = pClasse.getAnnotation(InfoClasse.class);

            return info.tags()[0];
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O nome do objeto não foi definido para " + pClasse.getSimpleName(), t);
            return pClasse.getSimpleName();
        }
    }

    public static String getNomeObjetoPlural(Class<? extends ItemGenerico> pClasse) {
        try {
            InfoClasse info = pClasse.getAnnotation(InfoClasse.class);

            return info.tags()[0];
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O plural do objeto não foi definodo para" + pClasse.getSimpleName(), t);
            return pClasse.getSimpleName();
        }
    }

    public static List<CaminhoCampoReflexao> getCamposDoObjeto() {
        throw new UnsupportedOperationException("Operação ainda não suportada");
    }

}

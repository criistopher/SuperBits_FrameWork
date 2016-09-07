package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
/**
 *
 * @author sfurbino
 */
public abstract class UtillSBWPReflexoesWebpaginas {

    public static List<Field> instanciarInjecoes(Object instancia) {

        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();

        List<Field> resposta = new ArrayList<>();
        for (Field campo : fields) {

            if (campo.isAnnotationPresent(Inject.class)) {
                campo.setAccessible(true);
                try {
                    if (campo.getType().getName().equals(classe.getName())) {
                        throw new UnsupportedOperationException("Voce não pode injetar a classe nela mesma :" + classe.getName());
                    }
                    campo.set(instancia, campo.getType().newInstance());

                    //System.out.println("Lista Auto Instanciada" + campo.getName());
                } catch (Throwable ex) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Não foi possível simular a Injeção do campo" + campo.getName(), ex);
                }
            }
        }
        return resposta;
    }

}

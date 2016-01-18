/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller;

import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfConfiguradorDeAcessos;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ConfigAcessoAbstratoSBCore implements ItfConfiguradorDeAcessos {

    private Map<Integer, Method> metodosByNome = new HashMap<>();
    private Class[] classesControllers;

    /**
     *
     * Encontra o Método de ação pelo nome
     *
     * @param nomeMetodo
     * @return
     */
    protected Method getMetodoByName(String nomeMetodo, boolean pararSistemaCasoNull) {
        Method metodo = metodosByNome.get(nomeMetodo.hashCode());
        if (metodo == null) {
            if (pararSistemaCasoNull) {
                FabErro.PARA_TUDO.paraSistema("\"Metodo [\" + nomeMetodo + \" ] não encontrado certifique que o nome do metodo está correto, e que a classe do controler foi configurada no constructor de \" + this.getClass().getName()", null);
            } else {
                System.out.println("Info:Metodo " + nomeMetodo + " não encontrado");
            }
        }
        return metodo;
    }

    protected Method localizarMetodo(String nomeMetodo) {
        Method metodoEncontrado = null;
        for (Method metodo : metodosByNome.values()) {
            if (metodo.getName().contains(nomeMetodo)) {
                metodoEncontrado = metodo;
            }
        }

        return metodoEncontrado;

    }

    /**
     *
     * Percorre todos as Classes do tipo controllers, e cadastra os métodos
     *
     * @param pClassesControllers
     */
    public ConfigAcessoAbstratoSBCore(Class[] pClassesControllers) {
        classesControllers = pClassesControllers;
        System.out.println("iniciando config Acessos");
        if (pClassesControllers == null) {
            return;
        }
        for (Class classe : pClassesControllers) {
            Method[] metodos
                    = classe.getDeclaredMethods();
            metodosByNome.clear();
            for (Method metodo : metodos) {
                String nomeMetodo = classe.getSimpleName() + "." + metodo.getName();
                System.out.println("Adicionando" + nomeMetodo + " para configuração de controle de acesso ");
                metodosByNome.put(nomeMetodo.hashCode(), metodo);
            }
        }

    }

    @Override
    public Class[] getClassesController() {
        return classesControllers;
    }

    protected Method MT(Class pClasse, String pNomeMetodo) {
        Method[] metodos = pClasse.getMethods();

        for (Method mt : metodos) {
            if (mt.getName().equals(pNomeMetodo)) {
                return mt;
            }
        }
        FabErro.PARA_TUDO.paraSistema("Erro procurando " + pNomeMetodo + " na classe " + pClasse, null);

        return null;
    }

    @Override
    public boolean ACAOCRUD(Class pEntidade, String tipoAcao) {
        //TODO CRIAR MODULOS DE ENUNS PARA UNIFICAR AS APLICAÇÕES

        String strCRUD = "D";

        switch (tipoAcao) {
            case "MERGE":
            case "UPDATE":
                strCRUD = "U";
                break;
            case "DELETE":
                strCRUD = "D";
                break;
            case "INSERT":
                strCRUD = "C";
                break;

        }

        Method metodo = localizarMetodo(strCRUD + "_" + pEntidade.getSimpleName());
        if (metodo == null) {
            return true;
        } else {
            try {
                Object[] parametros = {pEntidade};

                Object resposta = metodo.invoke(null, "teste");

                return (boolean) resposta;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConfigAcessoAbstratoSBCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ConfigAcessoAbstratoSBCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ConfigAcessoAbstratoSBCore.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

    }

}

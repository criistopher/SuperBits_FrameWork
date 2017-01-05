/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ConfigPermissaoSBCoreAbstrato implements ItfCfgPermissoes {

    private final Class[] classesControllers;

    protected final Map<Integer, String> acoesNomeUnicoByHashMetodo = new HashMap<>();
    private final Map<Integer, Method> metodosByHashMetodo = new HashMap<>();
    private final Map<String, Method> metodoByAcao = new HashMap<>();

    public static List<ItfAcaoDoSistema> getAcoesDoSistema() {
        throw new UnsupportedOperationException("Get Açoes do Sistema ainda não foi implementado");
    }

    public static List<ItfAcaoDoSistema> getAcoesDoModulo(Class<? extends ControllerAppAbstratoSBCore> modulo) {
        throw new UnsupportedOperationException("Get Açoes do modulo ainda não foi implementado");

    }

    /**
     *
     * Retorna a Ação do sistema criada apartir de seu factury (sem ligação com
     * o banco de dados)
     *
     * @param pMetodo
     * @return
     */
    public ItfAcaoDoSistema getAcaoByMetodo(Method pMetodo) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(acoesNomeUnicoByHashMetodo.get(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo)));
    }

    @Override
    public Method getMetodoByAcao(ItfAcaoDoSistema pAcao) {

        return metodoByAcao.get(pAcao.getNomeUnico());

    }

    /**
     *
     * Encontra o Método de ação que contenha a string no nome
     *
     * @param nomeMetodo
     * @return
     */
    protected Method localizarLikeNomeMetodo(String nomeMetodo) {
        Method metodoEncontrado = null;

        for (Method metodo : metodosByHashMetodo.values()) {
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
    public ConfigPermissaoSBCoreAbstrato(Class[] pClassesControllers) {

        classesControllers = pClassesControllers;

        try {
            System.out.println("Ajustando configurações de serviços");
            if (pClassesControllers == null) {
                System.out.println("Nenhuma Classe de Controller foi Cadastrada no sistema");
                return;
            }
            System.out.println("As seguinte classes de serviço foram encontradas:" + Arrays.toString(pClassesControllers));

            for (Class classe : pClassesControllers) {
                try {
                    if (classe == null) {
                        throw new UnsupportedOperationException("Uma das classes Cadastradas no sistema como classe de controller é um null!!, verifique a declaração no core");
                    }
                    Method[] metodos = classe.getDeclaredMethods();
                    acoesNomeUnicoByHashMetodo.clear();
                    for (Method metodo : metodos) {
                        ItfAcaoController acaovinculoMetodo = UtilSBController.getAcaoByMetodo(metodo, true);

                        //   if (classeAcao.isAssignableFrom(ItfAcaoController.class) == false) {
                        //       throw new UnsupportedOperationException("A ação " + acaovinculoMetodo.getNomeAcao() + " não é do tipo controller e foi vinculada ao método:" + metodo.getName() + " Na classe " + metodo.getDeclaringClass().getSimpleName());
                        // }
                        acoesNomeUnicoByHashMetodo.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), acaovinculoMetodo.getNomeUnico());
                        metodosByHashMetodo.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), metodo);
                        metodoByAcao.put(acaovinculoMetodo.getNomeUnico(), metodo);
                    }
                } catch (Throwable t) {
                    String nomeclasse = "Classe nula";
                    if (classe != null) {
                        nomeclasse = classe.getSimpleName();
                    }
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando metodos de ação para classe de serviço: " + nomeclasse, t);
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Vinculo de ações do sistema aos metodos", t);
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

}

/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.Controller.comunicacao.Resposta;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ConfigPermissaoAbstratoSBCore implements ItfCfgPermissoes {

    private final Class[] classesControllers;

    protected final Map<Integer, ItfAcaoDoSistema> acoesByHashMetodo = new HashMap<>();
    private final Map<Integer, Method> metodosByHashMetodo = new HashMap<>();

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
        return acoesByHashMetodo.get(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));
    }

    public Method getMetodoByAcao(ItfAcaoDoSistema pAcao) {

        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.PRODUCAO) {
            System.out.println("O metodo getMetodo pela ação deve ser usado com "
                    + "moderação no modo de produção. (o custo de processamento é "
                    + "elevado para utilizar muitas vezes seguida");
        }

        for (Map.Entry<Integer, Method> entry : metodosByHashMetodo.entrySet()) {

            ItfAcaoDoSistema acaoDoMetodo = UtilSBController.getAcaoByMetodo(entry.getValue(), true);
            if (acaoDoMetodo.getNomeAcao().equals(pAcao.getNomeAcao())) {
                return entry.getValue();
            }
        }
        return null;

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
    public ConfigPermissaoAbstratoSBCore(Class[] pClassesControllers) {

        classesControllers = pClassesControllers;
        try {
            System.out.println("iniciando config Acessos");
            if (pClassesControllers == null) {
                return;
            }
            for (Class classe : pClassesControllers) {
                Method[] metodos
                        = classe.getDeclaredMethods();
                acoesByHashMetodo.clear();
                for (Method metodo : metodos) {
                    ItfAcaoController acaovinculoMetodo = UtilSBController.getAcaoByMetodo(metodo, true);
                    Class classeAcao = acaovinculoMetodo.getClass();

                    //   if (classeAcao.isAssignableFrom(ItfAcaoController.class) == false) {
                    //       throw new UnsupportedOperationException("A ação " + acaovinculoMetodo.getNomeAcao() + " não é do tipo controller e foi vinculada ao método:" + metodo.getName() + " Na classe " + metodo.getDeclaringClass().getSimpleName());
                    // }
                    acoesByHashMetodo.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), acaovinculoMetodo);

                    metodosByHashMetodo.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), metodo);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
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
    public ItfResposta ACAOCRUD(Class pEntidade, String tipoAcao) {

        Resposta resp = new Resposta(pEntidade, null);
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

        Method metodo = localizarLikeNomeMetodo(strCRUD + "_" + pEntidade.getSimpleName());
        if (metodo == null) {
            return resp;
        } else {
            try {
                Object[] parametros = {pEntidade};

                Object resposta = metodo.invoke(null, "teste");

                return (ItfResposta) resposta;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("NÃO FOI POSSÍVEL EXECUTAR O METODO DE AÇÃO "
                        + "DE AÇÃOCRUD PARA ENTIDADE" + pEntidade.getSimpleName() + ", certifique que a "
                        + "nomeclatura do metodo esteja correta e que o método esteja retornando um objeto do tipo resposta  ", ex);
                Logger.getLogger(ConfigPermissaoAbstratoSBCore.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    }

}

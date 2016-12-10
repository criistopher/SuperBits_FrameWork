/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe para ser utilizada onde uma lista será carregada á partir de outra
 * Muito util em formulários de cadastro do Estilo PickList
 *
 * Para utilizar junto com o pickList do primefaces utilize a classe BP_pickList
 *
 *
 * @author Salvio Furbino
 */
public abstract class B_listaComOrigem<T> {

    private List<ItfBeanSimples> origemCompleta;
    protected final List<ItfBeanSimples> lista;
    protected final List<ItfBeanSimples> origem;
    private Class classeOrigem;

    public B_listaComOrigem(List<T> pLista, List<T> pOrigem) {
        carregaOrigemCompleta(pOrigem);
        origem = (List) pOrigem;
        lista = (List) pLista;
        ajuste(true);
    }

    public B_listaComOrigem(List<ItfBeanSimples> pLista, Class pClasseOrigemRetornaTodos) {
        lista = pLista;
        classeOrigem = pClasseOrigemRetornaTodos;
        origem = (List) UtilSBPersistencia.getListaTodos(pClasseOrigemRetornaTodos);

        ajuste(true);
    }

    protected final void carregaOrigemCompleta(List<T> pList) {
        origemCompleta = (List) new ArrayList<T>();
        origemCompleta.clear();
        for (T item : pList) {
            origemCompleta.add((ItfBeanSimples) item);
        }
    }

    protected void carregaOrigemFromDataBase() {
        carregaOrigemCompleta((List) UtilSBPersistencia.getListaTodos(classeOrigem));
        ajuste(true);
    }

    protected final void ajuste(boolean pRenovar) {
        if (pRenovar) {
            origem.clear();
            origem.addAll(origemCompleta);
        }
        if (!origem.isEmpty()) {
            origem.removeAll(getLista());
        }
        atualizaPickList();
    }

    public List<ItfBeanSimples> getLista() {
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;

    }

    public void setLista(List<T> pLista) {

        ajuste(true);
        atualizaPickList();
    }

    public List<ItfBeanSimples> getOrigem() {
        return origem;
    }

    public void reloadOrigemDatabase() {
        carregaOrigemFromDataBase();
        ajuste(true);
        atualizaPickList();
    }

    public abstract void atualizaPickList();

    public abstract void atualizaListaOrigem();

}

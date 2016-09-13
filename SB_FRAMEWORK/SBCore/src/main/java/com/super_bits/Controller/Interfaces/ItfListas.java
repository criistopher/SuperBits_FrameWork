/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.geradorCodigo.model.ListaDeEntidade;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfListas {

    public List getLista(Object... pObjeto);

    public Class getClasse();

    public ListaDeEntidade getEstruturaLista();

}

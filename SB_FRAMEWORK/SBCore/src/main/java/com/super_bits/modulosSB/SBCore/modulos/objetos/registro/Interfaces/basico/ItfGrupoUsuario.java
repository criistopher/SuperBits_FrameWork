/*  
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90 

 */

package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico;

import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfGrupoUsuario {
    
    public int getId();
    public String getNome();
    public String getDescricao();
    public List<? extends ItfUsuario> getUsuarios();
    public String getXhtmlPaginaInicial();
    public boolean isAtivo();
}

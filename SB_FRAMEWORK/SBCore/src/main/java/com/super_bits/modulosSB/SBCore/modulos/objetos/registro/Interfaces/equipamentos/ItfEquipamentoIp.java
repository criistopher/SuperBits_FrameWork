/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.equipamentos;

/**
 *
 *
 * Interface com os metodos e atributos comuns a todo equipamento IP
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 18/01/2015
 * @version 1.0
 */
public interface ItfEquipamentoIp {

    public String getIP();

    public boolean respondePing();

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvioF
 */
@InfoClasse(tags = {"Configuração do Modulo"}, plural = "Configurações de Modulos")
public class SBInfoConfigModulo extends ItemSimples {

    private int id;

    private String nomeModulo;
}

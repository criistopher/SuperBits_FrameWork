/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class EstruturaDeEntidade {

    private String nomeEntidade;
    private List<Campo> campos;

    private List<EstruturaDeEntidade> muitosParaUm;
    private List<EstruturaDeEntidade> umParaMuitos;

    public EstruturaDeEntidade() {

    }

}

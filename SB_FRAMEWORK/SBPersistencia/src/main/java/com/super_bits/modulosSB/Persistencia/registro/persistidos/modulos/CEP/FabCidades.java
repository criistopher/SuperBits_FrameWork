/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabCidades implements ItfFabrica {

    BELO_HORIZONTE, CONTAGEM, Baldim,
    Betim, Brumadinho,
    Caeté,
    Capim_Branco,
    Confins,
    Esmeraldas,
    Florestal,
    Ibirité,
    Igarapé,
    Itaguara,
    Itatiaiuçu,
    Jaboticatubas,
    Juatuba,
    Lagoa_Santa,
    MArio_Campos,
    Mateus_Leme,
    Matozinhos,
    Nova_Lima,
    Nova_União,
    Pedro_Leopoldo,
    Raposos,
    Ribeirao_das_Neves,
    Rio_Acima,
    Rio_Manso,
    Sabara,
    Santa_Luzia,
    Sao_Joaquim_de_Bicas,
    São_Jose_da_Lapa,
    Sarzedo,
    Taquaracu_de_Minas,
    Vespasiano;

    @Override
    public Cidade getRegistro() {
        Cidade novaCidade = new Cidade();
        novaCidade.setUnidadeFederativa(FabUnidadesFederativas.MG.getRegistro());
        switch (this) {

        }
        novaCidade.configIDFromNomeCurto();
        return novaCidade;
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class Dialogo {

    private List<ItfMensagem> dialogo;
    private ItfUsuario usuarioInicioDialogo;
    private FabTipoRespostaComunicacao respostaObtida;

}

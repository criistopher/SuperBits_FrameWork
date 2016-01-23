/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 29/07/2015
 * @version 1.0
 */
public class GrupoUsuariosDoSistema implements ItfGrupoUsuario {

    @Override
    public int getId() {
        return -99;
    }

    @Override
    public String getNome() {
        return "Grupos do Sistema";
    }

    @Override
    public String getDescricao() {
        return "Grupo utilizado para usuários do sistema";
    }

    @Override
    public List<? extends ItfUsuario> getUsuarios() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAtivo() {
       return true;
    }

    @Override
    public String getXhtmlPaginaInicial() {
        return "/site/home.xhtml";
    }

}

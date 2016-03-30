/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
<<<<<<< HEAD:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoNaoPersistida.java
=======
<<<<<<< HEAD:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoNaoPersistida.java
>>>>>>> 4e493a3b613f438b5eb83bcc8979df701d2a86c7:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoGenerica.java
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
=======
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoGenerica.java

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 16/12/2015
 * @version 1.0
 *
 */
public class AcaoGenerica extends ItemSimples implements ItfAcaoDoSistema {

    @Override
    public String getNomeAcao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconeAcao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPrecisaPermissao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIcone(String pIcone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isConfigurado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeUnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeEnumOriginal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoNaoPersistida.java
    public FabTipoAcaoSistemaGenerica getTipoAcao() {
=======
<<<<<<< HEAD:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoNaoPersistida.java
    public FabTipoAcaoSistemaGenerica getTipoAcao() {
=======
    public String getNome() {
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoGenerica.java
>>>>>>> 4e493a3b613f438b5eb83bcc8979df701d2a86c7:SB_FRAMEWORK/SBCore/src/main/java/com/super_bits/Controller/anotacoes/AcaoGenerica.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

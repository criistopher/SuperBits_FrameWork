/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import java.lang.reflect.Field;

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
public class AcaoNaoPersistida implements ItfAcaoDoSistema {

    @Override
    public String getNomeAcao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getIconeAcao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getCor() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getDescricao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getXHTMLAcao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getNomeCurto() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public int getId() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public Field getCampo(FabCampos pInfoCampo) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public int getIdMetodo() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public boolean isPrecisaPermissao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public void setId(int pId) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public void setIdMetodo(int pID) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public void setIcone(String pIcone) {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public boolean isTipoAcaoDireta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoAcao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoSessaoMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

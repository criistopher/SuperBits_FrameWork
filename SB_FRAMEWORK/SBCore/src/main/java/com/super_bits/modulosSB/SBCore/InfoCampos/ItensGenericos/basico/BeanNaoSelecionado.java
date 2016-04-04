/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 12/02/2015
 * @version 1.0
 */
public class BeanNaoSelecionado implements ItfBeanSimplesSomenteLeitura {

    @Override
    public String getImgPequena() {
        return null;
    }

    @Override
    public String getNomeCurto() {
        return "Não selecionado";
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getNome() {
        return "Não Selecionado";
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configIDPeloNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeDoObjeto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.validacaoRegistro;

/**
 *
 * Um campo inválido é um Objeto utilizado para exibição de mensagens de erro 
 * de validação;
 * 
 * @author desenvolvedor
 */
public class CampoInvalido {
    
    private String nomeCampo;
    private String problemaInvalidou;

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getProblemaInvalidou() {
        return problemaInvalidou;
    }

    public void setProblemaInvalidou(String problemaInvalidou) {
        this.problemaInvalidou = problemaInvalidou;
    }
    
    public  String getMensagemCampoInvalido(){
        return "O campo "+nomeCampo+" "+problemaInvalidou;
    }
    
    
    
    
    
}

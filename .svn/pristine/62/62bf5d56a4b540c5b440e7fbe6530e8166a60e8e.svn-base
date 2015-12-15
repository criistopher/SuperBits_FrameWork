/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import java.util.List;

/**
 *
 * Interface de configurador de Webpagina (implemente, e chame em SBWebPaginas.configurar
 * 
 * 
 * @author Salvio
 */
public interface ItfConfigWebPagina {
    
    
    /** Endereço do host + porta exemplo: https://www.meuEnderecoOuIP.com.br:8080*/
    public abstract String SITE_HOST();

    /** Diretorio base principal onde ficarão os jpgs */
    public abstract String pastaImagens();

    /** nome do pacote da aplição que corresponde ao url/aplicacao quando em desenvolvimento */
    public abstract String nomePacoteProjeto();

    /**  Titulo da aplicação (aparecerá no title da pagina caso nao seja setado um)  */
    public abstract String TituloAppWeb();

    /** subdominios ou subdiretorios que estarão sempre presentes no endereço do site */
    public abstract String URLBASE();
    
    public abstract Class mapaSite();
    
    public abstract List<ParametroURL> parametrosDeAplicacao(); 
    
    public abstract boolean parametroDeAplicacaoEmSubDominio();

}

package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import java.util.List;

public abstract class SBWebPaginas {

    // variaveis temporarias apenas para compatibilidade
    // VARIAVEIS DE ARQUIVAR_LOG
    private static String SITE_HOST;
    private static Integer porta = 8080;
    private static String pastaImagens;
    private static String nomePacoteProjeto;
    private static String SITE_URL;
    private static String TituloAppWeb;
    private static String URLBASE = "";
    private static boolean configurado = false;
    private static Class siteMap;

    private static List<ParametroURL> parametros;
    private static boolean parametroEmSubdominio;

    public static void configurar(ItfConfigWebPagina config) {
        SITE_HOST = config.SITE_HOST();
        pastaImagens = config.pastaImagens();
        nomePacoteProjeto = config.nomePacoteProjeto();
        SITE_URL = SITE_HOST + "/" + nomePacoteProjeto;
        System.out.println("siteURL=" + SITE_URL);
        TituloAppWeb = config.TituloAppWeb();
        URLBASE = config.URLBASE();
        siteMap = config.mapaSite();
        parametros = config.parametrosDeAplicacao();
        configurado = true;
        ArquivoConfiguracaoDistribuicao distribuicao = SBCore.getArquivoDistribuicao();

        if (distribuicao == null) {
            System.out.println("O arquivo de implantação ainda não foi configurado, esta aplicação rodara no modo localhost:8080/" + nomePacoteProjeto);
        } else {

            if (distribuicao.isTemArquivoImplantacao()) {
                String urlDistribuicao = distribuicao.getSERVIDOR_HOMOLOGACAO();
            }

            if (distribuicao.isEmAmbienteDeProducao()) {
                SITE_HOST = distribuicao.getSERVIDOR_HOMOLOGACAO() + ":" + String.valueOf(porta);
                SITE_URL = SITE_HOST;
            }
        }
    }

    private static void validaConfigurado() {
        if (configurado) {
            return;
        }

        System.out.println("CONFIG DO SBWEBPAGINAS  DEFINIDO");

        FabErro.PARA_TUDO.paraSistema("CONFIG DO SBWEBPAGINAS NAO DEFINIDO", null);
        try {
            throw new UnsupportedOperationException("CONFIG DO CORE NAO DEFINIDO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    /**
     *
     * @return Endereço de acesso externo ao site exemplo
     * http://www.meusiteOuIp.com.br /*
     */
    public static String getSiteURL() {
        validaConfigurado();
        return SITE_URL;
    }

    /**
     * Caso exista um prefixo após a "/" em toda URL da aplicação
     */
    public static String getURLBase() {
        validaConfigurado();
        return URLBASE;
    }

    /**
     * Titulo da Aplicação (aparece no titulo da pagina se não especificado)
     */
    public static String getTituloApp() {
        validaConfigurado();
        return TituloAppWeb;
    }

    /**
     * caminho para pasta base onde ficaram as imagens do site (sem a url
     * completa)
     */
    public static String getPastaBaseImagens() {
        validaConfigurado();
        return pastaImagens;
    }

    /**
     * endereço com a porta caso diferente da porta 80 exemplo:
     * https://MeuEnderecoOuIp:666
     *
     * @return endereço do host+porta
     */
    public static String getSiteHost() {
        validaConfigurado();
        return SITE_HOST;
    }

    /**
     *
     * @return Classe SiteMap
     */
    public static Class getSiteMap() {
        validaConfigurado();
        return siteMap;
    }

    /**
     *
     * @return especifica parametros da aplicação nescessários em todas as
     * paginas
     */
    public static List<ParametroURL> getParametrosAplicacao() {
        validaConfigurado();
        return parametros;
    }

    /**
     *
     * @return especifica se os parametros da aplicação vão estar em subdominios
     * ou em url
     */
    public static boolean isParametrosEmSubdominios() {
        validaConfigurado();
        return parametroEmSubdominio;
    }

    public static String getCaminhoWebAppDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp";
    }

    public static String getCaminhoWebResourcesDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources";
    }

    public static String getCaminhoWebSBCompDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources/SBComp";
    }

    public static String getCaminhoWebExemplorsDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources/exemplos";
    }

}

package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.reflexao.ReflexaoCampo;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.InfoMBBean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMBIdComponente;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_IdWidget;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import com.super_bits.modulosSB.webPaginas.visualizacao.ServicoVisuaslizacaoWebResponsivo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 *
 * @author Salvio
 */
@SuppressWarnings("serial")
public abstract class B_Pagina implements Serializable, ItfB_Pagina {

    public static final String PAGINAINICIALID = "inicial";

    private int id;
    private Boolean abriuPagina = false;
    private final List<String> tags = new ArrayList<>();
    private Map<String, ParametroURL> parametrosURL;
    private boolean parametrosDeUrlPreenchido = false;
    @Inject()
    protected PgUtil paginaUtil;

    private boolean anotacoesConfiguradas = false;
    private AcaoGestaoEntidade acaoVinculada;

    protected abstract String defineTitulo();

    protected abstract String defineNomeLink();

    protected abstract String defineDescricao();

    private String nomeCurto;
    private final List<String> urls = new ArrayList<>();
    private String urlPadrao;
    private String tagUsada;
    private String recursoXHTML;
    private final String titulo;
    private String linkRotulo;
    private final String descricao;
    @InfoMB_Bean(descricao = "Div onde será exibido o xhtml da ação selecionada", exemplo = "<h:painelGroup id={#PaginaAtual.infoPagina.idConteudoPagina}")
    protected String idAreaExbicaoAcaoSelecionada = "divSBAreaAcaoSelecionada";

    protected String xhtmlAcaoAtual;
    protected ItfAcaoDoSistema acaoSelecionada;
    private List<ItfAcaoDoSistema> acoesDaPagina;

    public static List<AcaoGestaoEntidade> acoesMB;
    private String nomeMB;

    private final Map<String, String> infoIds = new HashMap<>();
    private final Map<String, String> infoWidget = new HashMap<>();

    private final Map<String, BeanDeclarado> beansDeclarados = new HashMap<>();
    private final List<InfoMBAcao> infoAcoes = new ArrayList<>();

    private EntityManager emPagina;
    private boolean acessoLivre = true;

    public B_Pagina() {
        System.out.println("Constructor da pagina " + this.getClass().getName() + " iniciado");
        aplicarAnotacoes();
        titulo = defineTitulo();
        descricao = defineDescricao();
        UtilSBCoreReflexao.instanciarListas(this);
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            UtillSBWPReflexoesWebpaginas.instanciarInjecoes(this);
            paginaUtil = new PgUtil();
        }

    }

    protected void setAcaoSelecionadaPorEnum(ItfFabricaAcoes fabrica) {
        if (fabrica != null) {
            acaoSelecionada = fabrica.getAcaoDoSistema();
        }
    }

    protected void executaAcaoSelecionadaPorEnum(ItfFabricaAcoes fabrica) {
        if (fabrica != null) {
            acaoSelecionada = fabrica.getAcaoDoSistema();
            executarAcaoSelecionada();
        }
    }

    protected void setEExecutaAcaoSelecionada(ItfAcaoDoSistema pAcao) {
        acaoSelecionada = pAcao;
        executarAcaoSelecionada();
    }

    protected boolean isAcaoSelecionadaIgualA(ItfFabricaAcoes fabrica) {
        if (acaoSelecionada == null) {
            return false;

        }
        return acaoSelecionada.getEnumAcaoDoSistema().equals(fabrica);
    }

    public Object getInstanciaPagina() {
        return this;
    }

    protected void atualizarIdAreaExibicaoAcaoSelecionada() {
        paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
    }

    public class BeanDeclarado extends ReflexaoCampo implements ItfBeanDeclarado {

        private final InfoMBBean infoBean;

        public BeanDeclarado(Field campoReflection) {
            super(campoReflection);
            infoBean = new InfoMBBean(campoReflection);

        }

        @Override
        public InfoMBBean getInfoBean() {
            return infoBean;
        }

        @Override
        public Object getInstancia() {
            return getInstanciaPagina();
        }

        @Override
        public String getVisualizacaoItem() {
            if (getValor() == null) {
                return ServicoVisuaslizacaoWebResponsivo.CAMINHO_ITEM_SIMPLES_NULO;
            } else {
                return infoBean.getVisualizacaoItem();
            }
        }

    }

    @Override
    public AcaoGestaoEntidade getAcaoVinculada() {
        try {
            if (this.getClass().getName().equals(PaginaSimples.class.getName())) {
                return null;
            }

            if (acaoVinculada == null) {
                configAnotacoesClasse();
            }

            return acaoVinculada;
        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação vinculada a pagina" + this.getClass().getSimpleName(), e);
        }
        return acaoVinculada;
    }

    public EntityManager getEMPagina() {

        try {
            if (emPagina == null) {
                emPagina = UtilSBPersistencia.getNovoEM();
            } else if (!emPagina.isOpen()) {
                emPagina = UtilSBPersistencia.getNovoEM();
            }
            return emPagina;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo entity manager para pagina", e);
        }
        return null;
    }

    protected void renovarEMPagina() {
        try {
            EntityManager em = getEMPagina();
            em.close();

            emPagina = null;
            emPagina = getEMPagina();
            emPagina.clear();

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao renovar EM", null);
        }

    }

    /**
     * Beans de ações JSF: actions e actionsListeners
     *
     * @return Retorna a lista de ações da Pagina
     */
    public List<InfoMBAcao> getInfoAcoes() {
        return infoAcoes;
    }

    /**
     * Beans de exibição
     *
     * @return Retorna a lista de beans da Pagina
     */
    public List<BeanDeclarado> getInfoBeans() {
        return Lists.newArrayList(beansDeclarados.values());
    }

    /**
     * Retorna a lista de IDS de componentes dinamicos da pagina
     *
     * @return id dinamico
     */
    public Map<String, String> getInfoIds() {
        return infoIds;
    }

    public String getNomeMB() {
        return nomeMB;
    }

    public void setNomeMB(String nomeMB) {
        this.nomeMB = nomeMB;
    }

    protected void configParametros() {
        try {
            if (parametrosURL != null) {
                return;
            }

            parametrosURL = new HashMap<>();
            SBCore.soutInfoDebug("Configurando paramentros:: ");
            List<ParametroURL> lista = (List<ParametroURL>) UtilSBCoreReflexao.procuraInstanciasDeCamposPorTipo(this, ParametroURL.class);
            SBCore.soutInfoDebug(lista.size() + "parametos encontrados" + lista);
            parametrosURL.clear();
            for (ParametroURL pr : lista) {
                parametrosURL.put(pr.getNome(), pr);
                // System.out.println("add"+pr.getNome());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando parametros da pagina" + this.getClass().getSimpleName(), t);
        }

    }

    private void aplicarAnotacoes() {
        try {
            if (anotacoesConfiguradas) {
                return;
            }
            anotacoesConfiguradas = true;
            configAnotacoesClasse();
            SBCore.soutInfoDebug("Configurando Anotações de Bean");

            System.out.println("Configurando Anotações de Parametros");

            SBCore.soutInfoDebug("Aplicando valores");
            nomeMB = "#{" + this.getClass().getSimpleName() + "}";
            SBCore.soutInfoDebug("Configurando Anotações de Pagnia");

            SBCore.soutInfoDebug("Constructor da pagina " + this.getClass().getName() + " finalizado");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando anotações da pagina" + this.getClass().getName(), t);
        }
    }

    @Override
    public void aplicaValoresDeParametrosModoDesenvolvimento(Map<String, String> valorStringPorParametro) {
        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("O metodo aplicaValores de parametros modo DESENVOLVIMENTO só pode ser chamado com a aplicação no modo desenvolvimento");
        }
        aplicaValoresURLEmParametros(valorStringPorParametro);

    }

    protected void aplicaValoresURLEmParametros(Map<String, String> valorStringPorParametro) {
        // if (forcarAtualizacao)

        try {

            /// Definindo Tag utilizada para abertura da pagina
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                tagUsada = (String) UtilSBWPServletTools.getRequestBean("tagUsada");
            } else {
                tagUsada = getTags().get(0);
            }

            // caso tag não seja encontrada utilizar a tag padrão
            if (tagUsada == null) {
                System.out.println("Utilizando tagPadrão (contornando erro obtendo tag por requestScoped, este comportamento é normal em ambiente testeS");
                tagUsada = getTags().get(0);

            }

            for (String pr : getMapaParametros().keySet()) {
                String valorStringURL = valorStringPorParametro.get(pr);

                switch (parametrosURL.get(pr).getTipoParametro()) {

                    case ENTIDADE:
                        ItfBeanSimples registroByURL = null;
                        try {
                            registroByURL = (ItfBeanSimples) UtilSBPersistencia.getRegistroByNomeSlug(parametrosURL.get(pr).getTipoEntidade(), (String) valorStringURL, getEMPagina());

                        } catch (Exception e) {
                            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo registro de parametroURL de entidade pela URL", e);

                        }

                        if (registroByURL == null) {
                            // Se o Parametro não foi setado ainda Utiliza o valor Padrão
                            if (parametrosURL.get(pr).getValor() == null) {
                                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Não encontrado Registro" + parametrosURL.get(pr).getValor() + " do tipo" + parametrosURL.get(pr).getTipoEntidade().getSimpleName(), null);
                                parametrosURL.get(pr).setValor(parametrosURL.get(pr).getValorPadrao());
                                // caso contrario renova o Objeto
                            } else {
                                ItfBeanSimples registroRenovado = (ItfBeanSimples) UtilSBPersistencia.getRegistroByID(parametrosURL.get(pr).getValor().getClass(), ((ItfBeanSimples) parametrosURL.get(pr).getValor()).getId(), getEMPagina());
                                parametrosURL.get(pr).setValor(registroRenovado);
                            }

                        } else {
                            parametrosURL.get(pr).setValor(registroByURL);
                        }
                        break;
                    case TEXTO:
                        parametrosURL.get(pr).setValor(valorStringURL);

                        break;
                    case OBJETO_COM_CONSTRUCTOR:
                        throw new UnsupportedOperationException("Objeto com constructor ainda não foi implementado");
                }

            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro aplicando Valores de parametros da pagina" + this.getClass() + " pela URL", e);
        }

    }

    private void verificaAbriuPagina() {
        if (!abriuPagina) {
            try {
                throw new ErroSBCriticoWeb("pagina" + nomeCurto
                        + "não herdou AbrePagina");
            } catch (ErroSBCriticoWeb e) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Utilizando bean Pagina Atual sem Chamar método abrir pagina", e);
            }
        }
    }

    private void configAnotacoesClasse() {
        try {
            InfoPagina anotacaoInfopagina = this.getClass().getAnnotation(InfoPagina.class);
            if (anotacaoInfopagina == null) {
                if (!this.getClass().getSimpleName().equals(PaginaSimples.class.getSimpleName())
                        & !getClass().getName().contains("$Proxy$")) {
                    FabErro.PARA_TUDO.paraSistema("A Pagina::" + this.getClass().getName() + " não foi anotada com InfoPagina", null);

                }
                return;
            }
            if (anotacaoInfopagina.nomeCurto() == null) {
                FabErro.PARA_TUDO.paraSistema(" O nome curto na anotação InfoPagina da Pagina:" + this.getClass().getName() + "  não foi configurado", null);
            }
            //TODO tratar duplicidade de TAGS e Existencia do recurso

            setNomeCurto(anotacaoInfopagina.nomeCurto());

            acessoLivre = anotacaoInfopagina.acessoLivre();
            for (String tg : anotacaoInfopagina.tags()) {
                addTag(tg);
            }
        } catch (Exception e) {
            FabErro.PARA_TUDO.paraSistema("Erro configurando anotações de infoPagina da pagina" + this.getClass().getSimpleName(), e);

        }

        //Verificando se é uma pagina simples que não precisa de ação ManagedBean Vinculada
        if (!this.getClass().toString().equals(PaginaSimples.class.toString())
                & !getClass().getName().contains("$Proxy$")) {
            ItfAcaoDoSistema acao = UtilSBController.getAcaoByClasse(this.getClass());

            try {
                if (acao == null) {
                    throw new UnsupportedOperationException("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + this.getClass().toString());
                }
                acaoVinculada = (AcaoGestaoEntidade) acao;
                setRecursoXHTML(acaoVinculada.getXhtml());

            } catch (Throwable t) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN " + this.getClass().getSimpleName(), t);
                FabErro.PARA_TUDO.paraSistema("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + this.getClass().toString(), t);
            }
        }

    }

    private void buldBeansDeclarados() {
        Field[] camposDeclarados = this.getClass().getDeclaredFields();

        Method[] metodos = this.getClass().getDeclaredMethods();

        for (Method metodo : metodos) {

            InfoMB_Acao acao = metodo.getAnnotation(InfoMB_Acao.class);
            InfoMB_IdWidget widget = metodo.getAnnotation(InfoMB_IdWidget.class);
            if (acao != null) {
                infoAcoes.add(new InfoMBAcao("#{" + this.getClass().getSimpleName() + "." + metodo.getName() + "}", acao.descricao()));
            }

            if (widget != null) {
                infoWidget.put(metodo.getName(), widget.descricao());

            }

        }
        for (Field campo : camposDeclarados) {

            InfoMBIdComponente idcomp = campo.getAnnotation(InfoMBIdComponente.class);
            try {
                if (TIPO_PRIMITIVO.getTIPO_PRIMITIVO(campo).equals(TIPO_PRIMITIVO.ENTIDADE)) {
                    beansDeclarados.put(campo.getName(), new BeanDeclarado(campo));
                }
            } catch (Throwable t) {
                System.out.println("todo retirar criação de beandeclarado em modo criação offiline");
            }
//            if (infoBean != null) {

            //          }
            if (idcomp != null) {
                infoIds.put(campo.getName(), idcomp.descricao());
                campo.setAccessible(true);
                try {
                    campo.set(this, campo.getName());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro configurando anotações de InfoBean de " + this.getClass().getSimpleName(), ex);
                }
            }

        }
    }

    private void makeURLPadrao() {
        boolean redirecionarSemParametro = false;
        String url = SBWebPaginas.getURLBase();

        String tagURL;

        if (tagUsada == null) {
            tagURL = getTags().get(0);
        } else {
            tagURL = tagUsada;
        }
        url = url + "/" + UtilSBCoreStrings.makeStrUrlAmigavel(tagURL);
        System.out.println("Criando URL padrao");
        for (String prStr : getMapaParametros().keySet()) {
            String camada = "";
            ParametroURL pr = getMapaParametros().get(prStr);
            System.out.println("definindo link padrão para" + pr.getNome() + " valor" + pr.getValor() + "padrao" + pr.getValorPadrao());
            Object valorParametro = pr.getValor();
            if (valorParametro == null) {
                if (pr.isParametroObrigatorio()) {
                    redirecionarSemParametro = true;
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Parametro da pagina  setado como nulo,em " + this.getClass().getName() + "- " + pr.getNome() + "  o <b>Valor padrão deste parametro é obrigatório</> ", null);
                }
            } else if (pr.getTipoParametro() == ItfParametroTela.TIPO_URL.ENTIDADE) {
                try {
                    camada = ((ItfBeanSimples) valorParametro).getNomeUnicoSlug();
                    camada = UtilSBCoreStrings.makeStrUrlAmigavel(camada);
                } catch (Exception e) {

                    FabErro.SOLICITAR_REPARO.paraSistema("IMPOSSÍVEL OBTER O VALOR DO PARAMETRO DE URL DA ENTIDADE " + pr.getNome() + "em " + this.getClass().getSimpleName(), e);
                }
            } else if (camada != null) {
                camada = UtilSBCoreStrings.makeStrUrlAmigavel((String) valorParametro);
            }

            System.out.println("camada adcionada=" + camada);
            if (camada != null) {
                url = url + "/" + UtilSBCoreStrings.makeStrUrlAmigavel(camada);
            }
        }

        setUrlPadrao(url + "/.wp");
        System.out.println("urlCompletaFormada:: " + urlPadrao);
    }

    @Override
    @PreDestroy
    public void fecharPagina() {
        if (emPagina != null) {
            if (emPagina.isOpen()) {
                emPagina.close();
            }
        }
        SBCore.soutInfoDebug("Bean de escopo" + this.getClass().getSimpleName() + " Encerrado (B_Pagina.FecharPagina)");
    }

    @Override
    public void abrePagina() {
        try {
            if (abriuPagina) {
                SBCore.soutInfoDebug("Comando Abre PAgina já foi executado, saindo do método");
                //throw new UnsupportedOperationException("Comando abre pagina foi chamado 2 vezes");
                return;
            }
            abriuPagina = true;
            SBCore.soutInfoDebug("Comando Abre Pagina de " + this.getClass() + "Sendo executado pela primeira vez, os parametros iniciais serão definidos:");

            configParametros();

            Boolean tudoPreenchido = true;
            // DEFININDO OS VALORES DE PARAMETROS POR URL
            Map<String, String> valoresStrPorParametro = new HashMap<>();
            for (String pr : getMapaParametros().keySet()) {
                Object valorStringURL = UtilSBWPServletTools.getRequestBean(pr);

                /// SE O PARAMETRO NÃO FOI DEFINIDO NO REQUEST MARCA COMO NÃO PREENCHIDO, E REDIRECIONA PARA PAGINA PADRÃO
                if (valorStringURL == null) {
                    tudoPreenchido = false;
                    System.out.println("parametro" + pr + "não encontrado na URL");
                    parametrosURL.get(pr).setValor(parametrosURL.get(pr).getValorPadrao());
                } else {
                    valoresStrPorParametro.put(pr, valorStringURL.toString());
                }
            }
            parametrosDeUrlPreenchido = tudoPreenchido;

            if (!isParametrosDeUrlPreenchido()) {
                System.out.println("Os parametros não estavam preenchidos, redirecionando a pagina");

                UtilSBWP_JSFTools.vaParaPagina(getUrlPadrao());
            } else {

                aplicaValoresURLEmParametros(valoresStrPorParametro);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro execuntando metodo abre pagina de " + this.getClass().getSimpleName(), t);
        }
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getRecursoXHTML() {
        return recursoXHTML;
    }

    @Override
    public void setRecursoXHTML(String recursoXHTML) {
        this.recursoXHTML = recursoXHTML;
    }

    @Override
    public List<String> getTags() {
        if (tags == null) {
            configAnotacoesClasse();
        }

        return tags;
    }

    @Override
    public void addTag(String pTag) {
        getTags().add(pTag);
    }

    public void addParametro(String pNome, String pValorPadrao, ItfParametroTela.TIPO_URL ptipo) {
        getMapaParametros().put(pNome,
                new ParametroURL(true, pNome, pValorPadrao, ptipo));
    }

    public void addParametro(ParametroURL pParametro) {
        getMapaParametros().put(UtilSBCoreStrings.makeStrUrlAmigavel(pParametro.getNome()), pParametro);
    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeCurto() {
        if (nomeCurto == null) {
            configAnotacoesClasse();
        }
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    @Override
    public String getLinkRotulo() {
        return linkRotulo;
    }

    public void setLinkRotulo(String linkRotulo) {
        this.linkRotulo = linkRotulo;
    }

    @Override
    public String getTagUsada() {
        return tagUsada;
    }

    @Override
    public void setTagUsada(String tagUsada) {

        this.tagUsada = tagUsada;
    }

    @Override
    public String getUrlPadrao() {
        //if (!permitirUsarObjetoInjetadoIgualANulo)	verificaAbriuPagina();
        if (urlPadrao == null) {
            makeURLPadrao();
        }
        return urlPadrao;
    }

    public void setUrlPadrao(String urlCompleta) {
        this.urlPadrao = urlCompleta;
    }

    @Override
    public List<ParametroURL> getParametrosURL() {
        if (parametrosURL == null) {
            configParametros();
        }
        List<ParametroURL> parametros = new ArrayList<>(getMapaParametros().values());
        return parametros;
    }

    private Map<String, ParametroURL> getMapaParametros() {
        if (parametrosURL == null) {
            configParametros();
        }
        return parametrosURL;
    }

    @Override
    public ParametroURL getParametroByNome(String pNome) {
        if (getMapaParametros().containsKey(UtilSBCoreStrings.makeStrUrlAmigavel(pNome))) {
            return getMapaParametros().get(pNome);
        }

        UtilSBWP_JSFTools.mensagens().erroSistema("Parametro de Pagina (" + pNome + ") nao encontrado para a pagina" + getNomeCurto());
        return null;

    }

    public boolean exiteEsteParametro(String pNome) {
        return getMapaParametros().containsKey(UtilSBCoreStrings.makeStrUrlAmigavel(pNome));
    }

    @Override
    public ParametroURL getParametrobyTipoEntidade(String nomeEntidade) {
        for (ParametroURL pr : parametrosURL.values()) {
            if (pr.getTipoEntidade().getSimpleName().equals(nomeEntidade)) {
                return pr;
            }
        }
        UtilSBWP_JSFTools.mensagens().erroSistema("a pagina " + nomeCurto + " não contem um parametro do tipo" + nomeEntidade);
        return null;
    }

    public boolean isParametrosDeUrlPreenchido() {
        return parametrosDeUrlPreenchido;
    }

    public Map<String, String> getInfoWidget() {
        return infoWidget;
    }

    @Override
    public boolean isAcessoLivre() {
        if (acaoVinculada == null) {
            return true;
        } else {
            return !acaoVinculada.isPrecisaPermissao();
        }

    }

    @Override
    public String getNomeParametroById(int pId) {
        try {
            return getParametrosURL().get(pId).getNome();
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Não existe parametro com id" + pId, null);
            return null;
        }

    }

    @PostConstruct
    public void testePostConstructInterno() {
        System.out.println("PostConstruct interno");
        System.out.println("Executou post construct interno para" + this.getClass().getSimpleName());

    }

    /**
     *
     * Este metodo por padrão troca o xhtml atual caso nescessário, e atualiza o
     * conteudo, normalmente é substituído por um método superior, mas pode ser
     * utilizado de forma combinada (Chamando super.executarAcaoSelecionada)
     * dentro do metodo com override
     *
     *
     *
     *
     */
    @Override
    public void executarAcaoSelecionada() {
        try {
            if (acaoSelecionada == null) {
                throw new UnsupportedOperationException("A ação selecionada não pode ser nula");
            }

            if (acaoSelecionada.isUmaAcaoFormulario()) {
                if (!acaoSelecionada.getComoFormulario().getXhtml().equals(xhtmlAcaoAtual)) {
                    xhtmlAcaoAtual = acaoSelecionada.getComoFormulario().getXhtml();
                    paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
                    System.out.println("Info: O XHTML foi alterado para" + xhtmlAcaoAtual + " com a execução de" + acaoSelecionada.getNomeUnico());
                } else {
                    System.out.println("Info: O Managebean já estava no estado da ação:" + acaoSelecionada.getNomeUnico());
                }
            } else {
                System.out.println("Info: A ação " + acaoSelecionada.getNomeUnico() + "não é uma ação de formulário, portanto não possui XHTML vinlado");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação selecionada da pagina" + this.getClass().getSimpleName(), t);
        }
    }

    /**
     *
     *
     *
     *
     *
     * @return Todas as ações do sistema declaradas no Managed Bean da pagina
     */
    @Override
    public List<ItfAcaoDoSistema> getAcoesDaPagina() {
        if (acoesDaPagina != null) {
            return acoesDaPagina;
        }
        try {
            acoesDaPagina = new ArrayList<>();

            List<Field> camposDeAcao = UtilSBCoreReflexao.getCamposRecursivoPorInterface(this.getClass(), ItfAcaoDoSistema.class, B_Pagina.class, MB_PaginaConversation.class);
            for (Field cp : camposDeAcao) {
                cp.setAccessible(true);
                acoesDaPagina.add((ItfAcaoDoSistema) cp.get(this));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro obtendo Ação do sistema", t);
        }

        return acoesDaPagina;
    }

    public String getIdAreaExbicaoAcaoSelecionada() {
        return idAreaExbicaoAcaoSelecionada;
    }

    @Override
    public ItfAcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    public void setIdAreaExbicaoAcaoSelecionada(String idAreaExbicaoAcaoSelecionada) {
        this.idAreaExbicaoAcaoSelecionada = idAreaExbicaoAcaoSelecionada;
    }

    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    @Override
    public ItfPaginaGerenciarEntidade<?> getComoPaginaEntidade() {
        try {
            ItfPaginaGerenciarEntidade paginaDeEntidade = (ItfPaginaGerenciarEntidade) this;
            return paginaDeEntidade;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A pagina" + this.getClass().getSimpleName() + " não implementa Pagina de Entidade", t);
            return null;
        }

    }

    @Override
    public BeanDeclarado getBeanDeclarado(String nomeBean) {
        if (beansDeclarados == null || beansDeclarados.isEmpty()) {
            buldBeansDeclarados();
        }
        return beansDeclarados.get(nomeBean);
    }

}

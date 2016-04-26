package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.InfoMBBean;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMBIdComponente;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_IdWidget;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    private Map<String, ParametroURL> parametrosURL = new HashMap<String, ParametroURL>();
    private boolean parametrosDeUrlPreenchido = false;
    protected boolean foiInjetado = false;
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
    private String titulo;
    private String linkRotulo;
    private String descricao;
    public static List<AcaoGestaoEntidade> acoesMB;
    private String nomeMB;
    private final Map<String, String> infoIds = new HashMap<>();
    private final Map<String, String> infoWidget = new HashMap<>();
    private final List<InfoMBBean> infoBeans = new ArrayList<>();
    private final List<InfoMBAcao> infoAcoes = new ArrayList<>();
    private EntityManager emPagina;
    private boolean acessoLivre = true;

    public B_Pagina() {
        System.out.println("Constructor da pagina " + this.getClass().getName() + " iniciado");
        titulo = defineTitulo();
        descricao = defineDescricao();
        aplicarAnotacoes();
        UtilSBCoreReflexao.instanciarListas(this);
        SBCore.ESTADO_APP teste = SBCore.getEstadoAPP();
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            UtillSBWPReflexoesWebpaginas.instanciarInjecoes(this);
        }

    }

    @Override
    public AcaoGestaoEntidade getAcaoVinculada() {

        if (this.getClass().toString().equals(PaginaSimples.class.toString())) {
            return null;
        }

        if (acaoVinculada == null) {
            configAnotacoesClasse();
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
    public List<InfoMBBean> getInfoBeans() {
        return infoBeans;
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
        System.out.println("Configurando paramentros:: ");
        List<ParametroURL> lista = (List<ParametroURL>) UtilSBCoreReflexao.procuraInstanciasDeCamposPorTipo(this, ParametroURL.class);
        System.out.println(lista.size() + "parametos encontrados" + lista);
        parametrosURL = new HashMap<>();
        for (ParametroURL pr : lista) {
            parametrosURL.put(pr.getNome(), pr);
            // System.out.println("add"+pr.getNome());
        }

    }

    private void aplicarAnotacoes() {
        if (anotacoesConfiguradas) {
            return;
        }
        anotacoesConfiguradas = true;
        configAnotacoesClasse();
        System.out.println("Configurando Anotações de Bean");
        configAnotacoesBeans();
        System.out.println("Configurando Anotações de Parametros");

        System.out.println("Aplicando valores");
        nomeMB = "#{" + this.getClass().getSimpleName() + "}";
        System.out.println("Configurando Anotações de Pagnia");

        System.out.println("Constructor da pagina " + this.getClass().getName() + " finalizado");
    }

    protected void aplicaValoresURLEmParametros(Boolean forcarAtualizacao) {
        // if (forcarAtualizacao)

        try {
            Boolean tudoPreenchido = true;
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
                Object valorStringURL = UtilSBWPServletTools.getRequestBean(pr);

                if (valorStringURL == null) {
                    tudoPreenchido = false;
                    System.out.println("parametro" + pr + "não encontrado na URL");
                    parametrosURL.get(pr).setValor(parametrosURL.get(pr).getValorPadrao());
                } else {

                    switch (parametrosURL.get(pr).getTipoParametro()) {

                        case ENTIDADE:
                            ItfBeanSimples registroByURL = null;
                            try {
                                registroByURL = (ItfBeanSimples) UtilSBPersistencia.getRegistroByLikeNomeCurto(parametrosURL.get(pr).getTipoEntidade(), (String) valorStringURL, getEMPagina());

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

            }
            parametrosDeUrlPreenchido = tudoPreenchido;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro aplicando Valores de parametros da pagina pela URL", e);
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
            FabErro.PARA_TUDO.paraSistema("Erro configurando anotações de infoPagina", e);

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
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN", t);
                FabErro.PARA_TUDO.paraSistema("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + this.getClass().toString(), t);
            }
        }

    }

    /**
     * Cria Listas de Informações Tecnicas da pagina
     */
    private void configAnotacoesBeans() {

        Field[] fields = this.getClass().getDeclaredFields();

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
        for (Field field : fields) {

            InfoMB_Bean infoBean = field.getAnnotation(InfoMB_Bean.class);
            InfoMBIdComponente idcomp = field.getAnnotation(InfoMBIdComponente.class);

            if (infoBean != null) {
                infoBeans.add(new InfoMBBean(
                        field.getType().getSimpleName(), infoBean.descricao(), infoBean.exemplo(), "#{" + this.getClass().getSimpleName() + "." + field.getName() + "}"));
            }
            if (idcomp != null) {
                infoIds.put(field.getName(), idcomp.descricao());
                field.setAccessible(true);
                try {
                    field.set(this, field.getName());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro configurando anotações de InfoBean", ex);
                }
            }

        }

    }

    private void makeURLPadrao() {

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
                FabErro.PARA_TUDO.paraSistema("Parametro da pagina  setado como nulo,em " + this.getClass().getName() + "- " + pr.getNome() + "  o Valor padrão é obrigatório ", null);

            }

            if (pr.getTipoParametro() == ItfParametroTela.TIPO_URL.ENTIDADE) {
                try {
                    camada = ((ItfBeanSimples) valorParametro).getNomeCurto();
                    camada = UtilSBCoreStrings.makeStrUrlAmigavel(camada);
                } catch (Exception e) {

                    FabErro.PARA_TUDO.paraSistema("IMPOSSÍVEL OBTER O VALOR DO PARAMETRO DE URL DA ENTIDADE " + pr.getNome(), e);
                }
            } else {
                camada = UtilSBCoreStrings.makeStrUrlAmigavel((String) valorParametro);
            }
            System.out.println("camada adcionada=" + camada);
            url = url + "/" + UtilSBCoreStrings.makeStrUrlAmigavel(camada);
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
        System.out.println("Bean de escopo" + this.getClass().getSimpleName() + " Encerrado (B_Pagina.FecharPagina)");
    }

    @Override
    public void abrePagina() {
        if (abriuPagina) {
            System.out.println("Comando Abre PAgina já foi executado, saindo do método");
            return;
        }
        abriuPagina = true;
        System.out.println("Comando Abre Pagina de " + this.getClass() + "Sendo executado pela primeira vez, os parametros iniciais serão definidos:");

        if (!foiInjetado) {
            // carregarAnotacoes();
            configParametros();
        }

        aplicaValoresURLEmParametros(true);

        if (!isParametrosDeUrlPreenchido()) {
            System.out.println("Os parametros não estavam preenchidos, redirecionando a pagina");

            UtilSBWP_JSFTools.vaParaPagina(getUrlPadrao());
        }

    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (tags.isEmpty()) {
            configAnotacoesBeans();
        }
        return tags;
    }

    @Override
    public void addTag(String pTag) {
        getTags().add(pTag);
    }

    public void addParametro(String pNome, String pValorPadrao, ItfParametroTela.TIPO_URL ptipo) {
        getMapaParametros().put(pNome,
                new ParametroURL(pNome, pValorPadrao, ptipo));
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
        if (parametrosURL.isEmpty()) {
            configParametros();
        }
        List<ParametroURL> parametros = new ArrayList<>(getMapaParametros().values());
        return parametros;
    }

    private Map<String, ParametroURL> getMapaParametros() {
        if (parametrosURL.isEmpty()) {
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

}

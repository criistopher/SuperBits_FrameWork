/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.CalculoDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.ListaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaObjetosProjetoAtual {

    // Tabela contendo nome da classe, e classe
    private static final Map<String, Class> CLASSE_ENTIDADE_BY_NOME = new HashMap<>();
    private static boolean TODAS_CLASSES_CONFIGURADAS = false;
    private static final Map<Class, EstruturaDeEntidade> ESTRUTURA_BY_CLASSE = new HashMap<>();
    private static final Map<Class, String> VIEW_BY_CLASSE = new HashMap<>();

    public static final void adcionarObjeto(Class pObjeto) {
        CLASSE_ENTIDADE_BY_NOME.put(pObjeto.getSimpleName(), pObjeto);
        //throw new UnsupportedOperationException("TODO Erro adiionando objeto novamente para melhorar qualidade do código");
    }

    public static final void adcionarObjetoInstanciado(ItfBeanSimples pObjeto) {
        CLASSE_ENTIDADE_BY_NOME.put(pObjeto.getClass().getSimpleName(), pObjeto.getClass());
        //throw new UnsupportedOperationException("TODO Erro adiionando objeto novamente para melhorar qualidade do código");
    }

    public static final Class getClasseDoObjetoByNome(String pNome) {
        Class classe = CLASSE_ENTIDADE_BY_NOME.get(pNome);
        return classe;
    }

    public static final void configuraraTodasAsClasses(List<Class> entidades) {
        try {
            if (!TODAS_CLASSES_CONFIGURADAS) {
                for (Class entidade : entidades) {
                    System.out.println("Configurando campos de:" + entidade.getSimpleName());
                    if (!entidade.getSimpleName().contains("Acao")) {

                        CLASSE_ENTIDADE_BY_NOME.put(entidade.getSimpleName(), entidade);
                    }

                }
            } else {
                throw new UnsupportedOperationException("As classes já foram configuras");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando todas as classes", t);
            TODAS_CLASSES_CONFIGURADAS = false;
        }

        TODAS_CLASSES_CONFIGURADAS = true;
    }

    private static EstruturaDeEntidade makeEstrutura(Class pClasse) {
        EstruturaDeEntidade novaEstrutura = new EstruturaDeEntidade();
        Class classeEtrutura = pClasse;
        InfoClasse infoClasse = UtilSBCoreReflexao.getInfoClasseObjeto(classeEtrutura);

        novaEstrutura.setNomeEntidade(classeEtrutura.getSimpleName());
        novaEstrutura.setIcone(infoClasse.icone());
        novaEstrutura.setPlural(infoClasse.plural());
        novaEstrutura.setDescricao(infoClasse.description());
        novaEstrutura.setTags(Lists.newArrayList(infoClasse.tags()));

        for (Field campo : UtilSBCoreReflexao.getCamposRecursivodaClasseAteConterNomeObjetoFinal(pClasse, "Entidade", "Item")) {
            Campo campoSB = FabCampos.getCampoByAnotacoesSimplesSemPersistencia(campo);
            EstruturaCampo cp = new EstruturaCampo(campoSB, novaEstrutura);

            switch (campoSB.getTipoDeclaracao()) {
                case VALOR_CALCULADO:
                    CalculoDeEntidade calculo = UtilSBCoreReflecaoIEstruturaEntidade.getCalculoEstruturaByField(campo, campoSB, novaEstrutura);
                    novaEstrutura.getCalculos().add(calculo);
                    break;
                case LISTA_DINIMICA:
                    ListaDeEntidade lista = UtilSBCoreReflecaoIEstruturaEntidade.getListaEstruturaByField(campo);
                    novaEstrutura.getListas().add(lista);
                    break;
                case OBJETO_TRANSIENTE:
                    break;
                default:
                    novaEstrutura.getCampos().add(cp);
            }

        }
        return novaEstrutura;
    }

    public static final EstruturaDeEntidade getEstruturaObjeto(Class pClasse) {
        EstruturaDeEntidade estrutura = ESTRUTURA_BY_CLASSE.get(pClasse);
        if (estrutura == null) {
            estrutura = makeEstrutura(pClasse);
            ESTRUTURA_BY_CLASSE.put(pClasse, estrutura);
        }
        return estrutura;
    }

    public static final String getVisualizacaoDoObjeto(Class pClasseDoObjeto) {
        try {
            String formulario = VIEW_BY_CLASSE.get(pClasseDoObjeto);
            if (formulario == null) {
                formulario = SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCard(pClasseDoObjeto);
                VIEW_BY_CLASSE.put(pClasseDoObjeto, formulario);
                if (formulario == null) {
                    throw new UnsupportedOperationException("View do objeto não pode ser determinada para" + pClasseDoObjeto);
                }
            }
            return formulario;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo visualização do objeto", t);
            return "ErroAoGerarVisualizacaoDoObjeto";
        }

    }

}

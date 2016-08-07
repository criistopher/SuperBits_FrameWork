/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.view.fabricasCompVisual.ItfComponenteVisualSB;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public interface ItfCampoInstanciado extends ItfCampo {

    /**
     * ex: Avô.mãe.filha -> retornaria a mãe
     *
     * @return O Objeto pai deste objeto
     */
    public Object getParent();

    /**
     *
     * @return O valor deste campo
     */
    public Object getValor();

    /**
     *
     * @param pValor Modifica o valor do campo
     */
    public void setValor(Object pValor);

    /**
     *
     * @return O nome declarado no campo na classe
     */
    public String getNomeCamponaClasse();

    /**
     *
     * @return O Field vinculado ao campo
     */
    public Field getCampoReflection();

    /**
     *
     * @return Anotação InfoCampo com detalhes sobre o campo
     */
    public InfoCampo getInfoCampo();

    /**
     *
     * Leva em conta anotações da Java Bean Validation (JSR 303) e Infocampo
     *
     * @return Valida o valor do campo utilizando as anotações
     */
    public boolean validarCampo();

    /**
     *
     * @return True se Vazio ou nulo
     */
    public boolean isVazio();

    /**
     *
     * Um campo não instanciado, refere-se a um campo que não está acessível
     * porque não foi instanciado
     *
     * exemplo: você tenta acessar o campo Avó.mae.filha e a mãe é = a null,
     * porque a avó nem teve filha do gênero feminino, em vez de receber um
     * nuloPonto Exception vc recebe um campo não instanciado.
     *
     * @return true se o valor não pôde ser alcançado
     */
    public boolean isUmCampoNaoInstanciado();

    /**
     *
     * @return O label do campo sem carcteres Especiais
     */
    public String getLabelSlug();

    /**
     *
     * Retorna o id do campo de lista ex: O campo objetoMor.subobjetos[2]
     * retornaria 2
     *
     * @return O indice da lista
     */
    public int getIndiceValorLista();

    /**
     *
     * Seta um indice específico para um campo de lista em vez e retornar a
     * lista inteira, passa retornar um único objeto representado pela lista
     *
     * caso o indice não exista, retornará um CampoNãoinstanciado
     *
     * @param pIndice O indice que deseja setar
     */
    public void setIndiceValorLista(int pIndice);

    /**
     *
     * Retorna o nome da classe + _ + nome da declaração do campo na classe
     *
     * @return um nome único para identificar o compoenente no html
     */
    public String getNomeUnicoParaIDHtml();

    /**
     *
     * @return O xhtml que representa a exibição do campo
     */
    public ItfComponenteVisualSB getComponenteVisualPadrao();

    /**
     *
     * Caso seja nulo, retorna o XHTML padrão, Caso seja o XHTML de outra
     * família de compoentes retorna o xhtml avisando que eles não são
     * compatíveis
     *
     * Caso contrário, retona o XHTML do componente enviado como paramentro
     *
     *
     * @param pComponente
     * @return
     */
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente);

}

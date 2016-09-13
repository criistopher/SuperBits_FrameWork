package com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabModoExibicaoCampo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.TIPO_PRIMITIVO;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoCampo {

    // TIPO CAMPO
    /**
     * Informa o tipo de campo, o valor padrão é texto Simples
     *
     * @return
     */
    FabCampos tipo() default FabCampos.TEXTO_SIMPLES;

    /**
     *
     * Infoma o Label (nome do campo) do campo que será exibido nos formulários
     * de cadastro, ou visualização
     *
     * @return
     */
    String label() default "";

    /**
     *
     * Configura uma mascara utilizando os parametros do MastkFormater do java
     * ou seja :
     *
     *
     * <table border=1 summary="Cadacteres válidos e suas descrições">
     * <tr>
     * <th>Character&nbsp;</th>
     * <th><p align="left">Description</p></th>
     * </tr>
     * <tr>
     * <td>#</td>
     * <td> Qualquer número valido, Utiliza <code>Character.isDigit</code>.</td>
     * </tr>
     * <tr>
     * <td>'</td>
     * <td> Caracter de used to escape any of the special formatting
     * characters.</td>
     * </tr>
     * <tr>
     * <td>U</td><td> Qualquer Letra, utiliza:
     * (<code>Character.isLetter</code>). Todas as letras minusculas serão
     * convertidas para maiusculas
     * </td>
     * </tr>
     * <tr><td>L</td><td>Qualquer caracter, utiliza
     * (<code>Character.isLetter</code>). Todos as letras serão convertidas para
     * minusculo</td>
     * </tr>
     * <tr><td>A</td><td>Qualquer letra ou numero, utiliza:
     * (<code>Character.isLetter</code> or <code>Character.isDigit</code>)</td>
     * </tr>
     * <tr><td>?</td><td>Qualquer letra(<code>Character.isLetter</code>).</td>
     * </tr>
     * <tr><td>*</td><td>Qualquer coisa</td></tr>
     * <tr><td>H</td><td>Caracteres ExaDecimais (0-9, a-f or A-F).</td></tr>
     * </table>
     *
     *
     *
     *
     * @return
     */
    String Mask() default "";

    /**
     *
     * Configura o valor padrão para o campo
     *
     * @return
     */
    String valorPadrao() default "";

    /**
     *
     * O campo também será considerado obrigatório com a anotação @NotNull, a
     * diferença caso setado sem o notnull, é que o banco será criado permitindo
     * not null, porém antes de salvar atravez do framework esta verificação
     * será sempre realizada
     *
     * @return True se o Campo for obrigatorio, falso para dispensável
     */
    boolean obrigatorio() default false;

    int acesso() default 1;

    /**
     * COnfigura o valor mínimo para validação, no caso de numericos, um @Min do
     * Validate também é aceito, e sobrescreve este valor
     *
     *
     * @return
     */
    int valorMinimo() default 0;

    /**
     * Configura o valor maximo para validação, um @Max do validate também é
     * aceito, e sobrescreve este valor assim como o length do @Collun
     *
     * @return
     */
    int valorMaximo() default 0;

    ValorAceito[] valoresAceitos() default {};

    /**
     *
     * Informa o tipo primitivo do campo, que pode ser Letras, Numeros, ou Datas
     * o padrão são letras
     *
     * @return
     */
    TIPO_PRIMITIVO tipoPrimitivo() default TIPO_PRIMITIVO.LETRAS;

    /**
     *
     * @return Os locais onde o campo será exibido
     */
    FabModoExibicaoCampo[] modosExibicao() default {FabModoExibicaoCampo.TODOS_MENOS_LISTA};

    /**
     *
     * @return Uma fabrica com as opções disponíveis
     */
    public Class fabricaDeOpcoes() default void.class;

    /**
     *
     * @return O caminho do campo para obter a lista
     */
    public String caminhoParaLista() default "";

    String descricao() default "";
}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.tempo;

/**
 *
 * @author salvioF
 */
public class QuantidadeTempo {

    private final TipoQuantidadeTempo tipoQuantidade;
    private long valorEmMileSegundos;
    private FabTipoQuantidadeTempo baseCalculo = FabTipoQuantidadeTempo.ANOS;
    private long quantidade = Long.parseLong("0");
    private boolean ignorarSemana = true;
    private String nome;

    public QuantidadeTempo(Long pValorEmMileSegundos, TipoQuantidadeTempo pTipoQuantidade) {
        valorEmMileSegundos = pValorEmMileSegundos;
        tipoQuantidade = pTipoQuantidade;
    }

    public QuantidadeTempo(Long pValorEmMileSegundos, TipoQuantidadeTempo pTipoQuantidade, FabTipoQuantidadeTempo pBaseCalculo) {
        valorEmMileSegundos = pValorEmMileSegundos;
        baseCalculo = pBaseCalculo;
        tipoQuantidade = pTipoQuantidade;

    }

    private void atualizarQuantidade() {
        tipoQuantidade.getTipoInformacao().calcularQuantidade(valorEmMileSegundos, baseCalculo, ignorarSemana);
        quantidade = 0;
    }

    public void setValorEmMileSegundos(long valorEmMileSegundos, boolean pIgnorarSemana) {
        this.valorEmMileSegundos = valorEmMileSegundos;
        atualizarQuantidade();
    }

    public long getQuantidade() {
        return quantidade;
    }

    public FabTipoQuantidadeTempo getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(FabTipoQuantidadeTempo baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public boolean isIgnorarSemana() {
        return ignorarSemana;
    }

    public void setIgnorarSemana(boolean ignorarSemana) {
        this.ignorarSemana = ignorarSemana;
    }

    public TipoQuantidadeTempo getTipoQuantidade() {
        return tipoQuantidade;
    }

    public long getValorEmMileSegundos() {
        return valorEmMileSegundos;
    }

    public String getNome() {

        if (quantidade != 1) {
            return getTipoQuantidade().getNomePlural();
        } else {
            return getTipoQuantidade().getNomeSingular();
        }

    }

}

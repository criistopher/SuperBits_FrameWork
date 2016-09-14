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
    private FabTipoQuantidadeTempo divisorMaximo = FabTipoQuantidadeTempo.ANOS;
    private long quantidade = Long.parseLong("0");
    private boolean ignorarSemana = true;
    private String nome;

    public QuantidadeTempo(Long pValorEmMileSegundos, FabTipoQuantidadeTempo pTipoQuantidade) {

        valorEmMileSegundos = pValorEmMileSegundos;
        tipoQuantidade = pTipoQuantidade.getTipoQuantidade();
    }

    public QuantidadeTempo(Long pValorEmMileSegundos, TipoQuantidadeTempo pTipoQuantidade, FabTipoQuantidadeTempo pBaseCalculo) {

        this.valorEmMileSegundos = pValorEmMileSegundos;
        divisorMaximo = pBaseCalculo;
        tipoQuantidade = pTipoQuantidade;

    }

    private void atualizarQuantidade() {
        //tipoQuantidade.getTipoInformacao().calcularQuantidade(valorEmMileSegundos, divisorMaximo, ignorarSemana);
        //quantidade = 0;
        quantidade = tipoQuantidade.getTipoInformacao().calcularQuantidade(valorEmMileSegundos, divisorMaximo, ignorarSemana);
    }

    public void setValorEmMileSegundos(long valorEmMileSegundos, boolean pIgnorarSemana) {
        this.valorEmMileSegundos = valorEmMileSegundos;
        atualizarQuantidade();
    }

    public long getQuantidade() {
        return quantidade;
    }

    public FabTipoQuantidadeTempo getDivisorMaximo() {
        return divisorMaximo;
    }

    public void setDivisorMaximo(FabTipoQuantidadeTempo divisorMaximo) {
        this.divisorMaximo = divisorMaximo;
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

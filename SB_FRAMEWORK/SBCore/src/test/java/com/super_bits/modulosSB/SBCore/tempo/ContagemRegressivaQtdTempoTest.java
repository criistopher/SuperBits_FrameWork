/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.tempo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvioF
 */
public class ContagemRegressivaQtdTempoTest {

    public ContagemRegressivaQtdTempoTest() {
    }

    @Test
    public void testGetQuantidadeTempo() {

        // Setar uma variavel Long, correspontente a 1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
        long valorVariavel = 0;
        ContagemRegressivaQtdTempo contagem = new ContagemRegressivaQtdTempo(valorVariavel);
        contagem.setQuantidadeInformacao(6);

        assertEquals("A contagem regressiva deve retonar 6 valores", contagem.getQuantidadesTempo().size(), 6);
        QuantidadeTempo ano = contagem.getQuantidadesTempo().get(0);
        assertEquals("O tipo da primeira quantidade esperada deveria ser anual", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.ANOS);
        QuantidadeTempo meses = contagem.getQuantidadesTempo().get(1);
        assertEquals("O tipo da 2 quantidade esperada deveria ser meses", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.MESES);
        QuantidadeTempo dias = contagem.getQuantidadesTempo().get(2);
        assertEquals("O tipo da 3 quantidade esperada deveria ser dias", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.DIAS);
        QuantidadeTempo horas = contagem.getQuantidadesTempo().get(3);
        assertEquals("O tipo da 4 quantidade esperada deveria ser horas", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.HORAS);
        QuantidadeTempo minutos = contagem.getQuantidadesTempo().get(4);
        assertEquals("O tipo da 5 quantidade esperada deveria ser minutos", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.MINUTOS);
        QuantidadeTempo segundos = contagem.getQuantidadesTempo().get(5);
        assertEquals("O tipo da 6 quantidade esperada deveria ser segundos", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.SEGUNDOS);

        assertEquals("A quantidade de anos não foi a esperada", ano.getQuantidade(), 1);
        assertEquals("A quantidade de anos não foi a esperada", meses.getQuantidade(), 2);
        assertEquals("A quantidade de anos não foi a esperada", dias.getQuantidade(), 16);
        assertEquals("A quantidade de anos não foi a esperada", horas.getQuantidade(), 4);
        assertEquals("A quantidade de anos não foi a esperada", minutos.getQuantidade(), 2);
        assertEquals("A quantidade de anos não foi a esperada", segundos.getQuantidade(), 15);

        contagem.setDividirDiasEmSemanas(true);

        // Agora o mesmo calculo adicionando as semanas
        contagem.setDividirDiasEmSemanas(true);

    }

}

/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCorePadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 20/07/2014
 *
 */
public class TestesCore {

    public void testaConfigEErroRElat() {
        System.out.println("Teste relato de Erro");
        SBCore.configurar(new ConfigCorePadrao());
        //   SBCore.LancarErro(InfoErroSB.FabErro.USUARIO, "Executa Alerta Usuario");
        System.out.println("Relato de Erro OK");
    }

    @Test
    public void testeToString() {
        //    InfoErroSB.FabErro tipo = InfoErroSB.FabErro.ARQUIVAR_LOG;
//        TransparenciaClient client = new TransparenciaClient("3BnvXAL7Mly6");

        //Busca candidato por ID, 'true' popula automaticamente os campos 'bens', 'candidaturas', 'doadores' e 'estatisticas'
        //Candidato candidato = client.get
        /**
         * try {
         *
         * List<Candidato> candidatos = client.getCandidatosByCargo("MG",
         * "Vereador"); // Candidato teste = client.getCandidatoById("1000",
         * true); // System.out.println(teste.getNome()); for (Candidato
         * candidato : candidatos) { System.out.println(candidato.getNome());
         * System.out.println(candidato.getCargo());
         * System.out.println(candidato.getProcessos());
         * System.out.println(candidato.getTitulo());
         *
         * }
         * //List<Candidato> candidado=client.get; List<Partido> partidos =
         * client.getPartidos(); for (Partido p : partidos) {
         * System.out.println(p.getSigla()); } // System.out.println(tipo); }
         * catch (RestException ex) {
         * Logger.getLogger(TestesCore.class.getName()).log(Level.SEVERE, null,
         * ex); }
         */
    }

}

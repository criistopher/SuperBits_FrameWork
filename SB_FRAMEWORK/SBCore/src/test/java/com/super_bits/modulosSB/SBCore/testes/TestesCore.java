/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigCoreSBCore;
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
        SBCore.configurar(FabConfigCoreSBCore.DESENVOLVIMENTO.getConfigurador(), true);
        //   SBCore.LancarErro(InfoErroSB.FabErro.USUARIO, "Executa Alerta Usuario");
        System.out.println("Relato de Erro OK");
    }

    class TestaString {

        private String stringReferenciada;

        public String getStringReferenciada() {
            return stringReferenciada;
        }

        public void setStringReferenciada(String stringReferenciada) {
            this.stringReferenciada = stringReferenciada;
        }

        public void testeToString() {

            TestaString teste1 = new TestaString();

            teste1.setStringReferenciada("sblabla");

            TestaString teste2 = new TestaString();
            teste2.setStringReferenciada("22222222222222");

            System.out.println("teste1= " + teste1.getStringReferenciada());
            System.out.println("teste2= " + teste2.getStringReferenciada());
            System.out.println(teste1.getStringReferenciada());

            String nome, sobrenome = "dfasdf";
            sobrenome = "bbbb";

            System.out.println(sobrenome);

            System.out.println("Teste");

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
             * candidato : candidatos) {
             * System.out.println(candidato.getNome());
             * System.out.println(candidato.getCargo());
             * System.out.println(candidato.getProcessos());
             * System.out.println(candidato.getTitulo());
             *
             * }
             * //List<Candidato> candidado=client.get; List<Partido> partidos =
             * client.getPartidos(); for (Partido p : partidos) {
             * System.out.println(p.getSigla()); } // System.out.println(tipo);
             * } catch (RestException ex) {
             * Logger.getLogger(TestesCore.class.getName()).log(Level.SEVERE,
             * null, ex); }
             */
        }
    }

    @Test
    public void teste() {

        String teste = "sdasdasdasdasd,\n";
        if (teste.endsWith(",\n")) {
            System.out.println("Funciona");
        } else {
            System.out.println("Não Funciona");
        }

    }

}

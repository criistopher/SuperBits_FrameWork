/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.InomeProjetoI.paginas;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.projeto.Jira.PrevisaoGestaoEntidade;
import com.super_bits.projeto.Jira.PrevisaoModulo;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import testesConformidade.TestesWebAppRequisitoInomeProjetoI;

/**
 *
 * @author desenvolvedor
 */
public class PgRequisitosVisaoGeralTest extends TestesWebAppRequisitoInomeProjetoI {

    public PgRequisitosVisaoGeralTest() {
    }

    @Test
    public void testeCustos() {

        for (Class teste : UtilSBPersistencia.getTodasEntidades()) {
            System.out.println(teste.getSimpleName());
        }
        EntityManagerFactory fab = UtilSBPersistencia.getEmfabricaPadrao();
        for (String prop : fab.getProperties().keySet()) {
            System.out.println(prop);
        }
        //fab.getPersistenceUnitUtil().

        PgRequisitosVisaoGeral pagina = new PgRequisitosVisaoGeral();
        pagina.recarregarValores();
        pagina.getAmbienteDesenvolvimento();
        pagina.getPrevisaoProjeto().getAmbienteDesenvolvimento();
        for (PrevisaoModulo prevModulo : pagina.getPrevisaoProjeto().getModulosPrevistos()) {

            for (PrevisaoGestaoEntidade prevGestao : prevModulo.getGestoesPrevistas()) {
                System.out.println("Custo=" + prevGestao.getCustoDesenvolvimento().getValorTotal());;
            }
        }

    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author desenvolvedor
 */
public class UtilFabricaDeAcoesBasico {

    public static boolean validaIntegridadeAcaoDoSistema(ItfAcaoDoSistema pAcaoDoSistema) {

        assertNotNull("O enum da ação do sistema" + pAcaoDoSistema + " não foi definido", pAcaoDoSistema.getEnumAcaoDoSistema());

        if (pAcaoDoSistema.isUmaAcaoDeEntidade()) {
            assertNotNull("A entidade vindulada da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido", pAcaoDoSistema.getEnumAcaoDoSistema().getEntidadeDominio());
        }
        if (pAcaoDoSistema.isUmaAcaoFormulario()) {

            assertNotNull("O Xhtml da ação de formulario" + pAcaoDoSistema.getNomeUnico() + " está nula", ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());
            assertFalse("O xhtml da acao  de formulario" + pAcaoDoSistema.getNomeUnico() + "está em branco",
                    "".equals(((ItfAcaoFormulario) pAcaoDoSistema).getXhtml()));

        }

        if (pAcaoDoSistema.isTemAcaoPrincipal()) {
            assertNotNull("A ação secundária" + pAcaoDoSistema.getNomeUnico() + " principal não foi definida em ", ((ItfAcaoSecundaria) pAcaoDoSistema).getAcaoPrincipal());
        }

        assertNotNull("O icone da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido", pAcaoDoSistema.getIconeAcao());

        return true;
    }

}

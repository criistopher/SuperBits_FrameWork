/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilFabricaDeAcoesBasico {

    public static boolean validaIntegridadeAcaoDoSistema(ItfAcaoDoSistema pAcaoDoSistema) {

        try {
            if (pAcaoDoSistema == null) {

            }
            if (pAcaoDoSistema.getEnumAcaoDoSistema() == null) {
                throw new UnsupportedOperationException("O enum da ação do sistema" + pAcaoDoSistema + " não foi definido");
            }

            if (pAcaoDoSistema.isUmaAcaoDeEntidade()) {

                if (pAcaoDoSistema.getEnumAcaoDoSistema().getEntidadeDominio() == null) {
                    throw new UnsupportedOperationException("A entidade vindulada da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido");
                }

            }
            if (pAcaoDoSistema.isUmaAcaoFormulario()) {
                if (((ItfAcaoFormulario) pAcaoDoSistema).getXhtml() == null) {
                    throw new UnsupportedOperationException("O Xhtml da ação de formulario" + pAcaoDoSistema.getNomeUnico() + " está nula");
                } else if (((ItfAcaoFormulario) pAcaoDoSistema).getXhtml().length() < 3) {
                    throw new UnsupportedOperationException("O xhtml da acao  de formulario" + pAcaoDoSistema.getNomeUnico() + "está em branco");
                }

                if (pAcaoDoSistema.isTemAcaoPrincipal()) {
                    if (((ItfAcaoSecundaria) pAcaoDoSistema).getAcaoPrincipal() == null) {
                        throw new UnsupportedOperationException("A ação secundária" + pAcaoDoSistema.getNomeUnico() + " principal não foi definida em ");
                    }
                }

                if (pAcaoDoSistema.getIconeAcao() == null) {
                    throw new UnsupportedOperationException("O icone da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido");
                }

            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando ação ", t);
            return false;
        }
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.Persistencia.ERROS;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
public class TesteJunitSBPersistencia extends TesteJunit {

    private EntityManager emTeste;

    public EntityManager renovarConexao() {
        emTeste.close();
        emTeste = null;
        emTeste = UtilSBPersistencia.getNovoEM();
        return emTeste;

    }

    public EntityManager getEmTeste() {
        try {
            if (emTeste == null) {
                emTeste = UtilSBPersistencia.getNovoEM();
            } else {
                if (emTeste.isOpen()) {
                    emTeste = UtilSBPersistencia.getNovoEM();
                }
            }
            return emTeste;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Obtendo entity manager para pagina", e);
        }
        return null;
    }

}

package testesLuciano.enviarSalvio;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.FabConfiguracoesCoreAcessosModel;
import javax.persistence.EntityManager;
import org.junit.Test;

public class TestaJPA extends TesteJunitSBPersistencia {

    @Test
    public void testaBanco() {

        CidadeLuciano cidade = new CidadeLuciano();
        cidade.setDescricao("orlandia");
        UtilSBPersistencia.persistirRegistro(cidade);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador(), true);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesLuciano());
    }
}

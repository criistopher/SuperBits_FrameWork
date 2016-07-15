package testesLuciano.enviarSalvio;

import aux.FabConfigCoreSBSBAcessosModel;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import javax.persistence.EntityManager;
import org.junit.Test;

public class TestaJPA extends TesteJunitSBPersistencia {

    @Test
    public void testaBanco() {

        CidadeLuciano cidade = new CidadeLuciano();
        cidade.setDescricao("orlandia");
        UtilSBPersistencia.persistirRegistro(cidade);

        EnderecoLuciano enderecoPrincipal = new EnderecoLuciano();
        //  enderecoPrincipal.setCidadeEndereco(cidade);
        enderecoPrincipal.setLogradouro2("teste");
        //  enderecoPrincipal.setNro("Numerooo");
        enderecoPrincipal.setLogradouro2("eNDEREÇO TESTE");
        PessoaLuciano pessoa = new PessoaLuciano();
        pessoa.setEnderecoPrincipal(enderecoPrincipal);
        UtilSBPersistencia.persistirRegistro(pessoa);

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBSBAcessosModel.DESENVOLVIMENTO.getConfigurador(), true);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesLuciano());
    }
}

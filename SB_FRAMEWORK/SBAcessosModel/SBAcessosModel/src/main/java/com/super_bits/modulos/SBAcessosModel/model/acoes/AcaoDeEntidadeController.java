/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.acoes.ParametroDeAcaoController;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 *
 * Uma ação de entidade Da camada controller, é uma ação que executa alguma
 * alteração no sistema
 *
 * Em geral , uma ação de entidade possui apenas a entidade como parametro, mas
 * ela pode conter também parametros extras que devem ser especificados
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação de controller relacionada a entidade"})
public class AcaoDeEntidadeController extends AcaoDeEntidade implements ItfAcaoController, ItfAcaoSecundaria, ItfAcaoControllerEntidade {

    @Transient
    private List<ParametroDeAcaoController> parametrosAdicionais;

    @Transient
    private ItfAcaoGerenciarEntidade acaoPrincipal;

    private int idMetodo;

    public AcaoDeEntidadeController() {
        super();
    }

    public AcaoDeEntidadeController(ItfAcaoGerenciarEntidade pAcaoPrincipal,
            FabTipoAcaoSistemaGenerica pAcaoGenerica, ItfFabricaAcoes pFabAcao) {

        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER, pFabAcao, pAcaoGenerica);
        setAcaoPrincipal(pAcaoPrincipal);

    }

    @Override
    public boolean isTemParametroExtra() {
        if (parametrosAdicionais == null) {
            return false;
        } else {
            return !parametrosAdicionais.isEmpty();
        }
    }

    @Override
    public int getIdMetodo() {
        try {
            if (idMetodo == 0) {
                idMetodo = UtilSBController.gerarIDMetodoAcaoDoSistema(UtilSBCoreReflexao.getMetodoByAcao(this));
            }
            return idMetodo;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando id do metodo para ação" + getNomeUnico() + " Certifique que exista um método para essa Ação, caso não seja este o problema,verifique se o modulo está cadastrado na configuração de permissoes", t);
            throw new UnsupportedOperationException("Erro gerando id do metodo para ação" + getNomeUnico());
        }

    }

    @Override
    public void setIdMetodo(Method pMetodo) {
        idMetodo = UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo);
    }

    @Override
    public List<ParametroDeAcaoController> getParametros() {
        return parametrosAdicionais;
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoPrincipal() {
        return acaoPrincipal;
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoGerenciarEntidade pAcaoPrincipal) {
        acaoPrincipal = pAcaoPrincipal;
    }

}

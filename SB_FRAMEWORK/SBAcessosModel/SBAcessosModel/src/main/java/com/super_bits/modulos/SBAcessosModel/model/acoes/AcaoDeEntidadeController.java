/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.ParametroDeAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeNovoRegistro;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeEditar;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.lang.reflect.Method;
import java.util.List;

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
public class AcaoDeEntidadeController extends AcaoDeEntidade implements ItfAcaoController {

    private List<ParametroDeAcaoController> parametrosAdicionais;
    private int idMetodo;

    public AcaoDeEntidadeController() {
        super(null, null, null);
    }

    public AcaoDeEntidadeController(ItfAcaoGerenciarEntidade pClasseRelacionada) {
        super(null, FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER);

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
        return idMetodo;
    }

    @Override
    public void setIdMetodo(Method pMetodo) {
        idMetodo = UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo);
    }

    @Override
    public List<ParametroDeAcaoController> getParametros() {
        return parametrosAdicionais;
    }

}
/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.ParametroDeAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.Transient;

/**
 *
 * Uma ação de controller é uma ação do sistema que pode ou não conter
 * parametros, e deve retornar algo.
 *
 *
 * @author desenvolvedor
 */
public class AcaoController extends AcaoDoSistema implements ItfAcaoController {

    private int idMetodo;
    @Transient
    private List<ParametroDeAcaoController> parametros;

    public AcaoController() {
        super(FabTipoAcaoSistema.ACAO_CONTROLLER, null);
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
    public boolean isTemParametroExtra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ParametroDeAcaoController> getParametros() {
        return parametros;
    }

}

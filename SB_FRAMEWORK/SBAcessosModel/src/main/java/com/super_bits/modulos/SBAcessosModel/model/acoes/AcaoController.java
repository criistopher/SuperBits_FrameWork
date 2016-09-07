/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ParametroDeAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * Uma ação de controller é uma ação do sistema, que executa alguma alteração
 * definitiva no banco de dados,
 *
 * Toda ação controller deve ter um método estatico de execução, vinculado a ela
 * via anotaçaõ
 *
 *
 * ela pode ou não conter parametros, e deve retornar algo.
 *
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação da cadamada de controller "}, plural = "Ações Controllers")
public class AcaoController extends AcaoDoSistema implements ItfAcaoController {

    private int idMetodo;

    @Transient
    private List<ParametroDeAcaoController> parametros;

    public AcaoController() {
        System.out.println("Uma ação só deve ser iniciada sem constructor apartir do hibernate");
    }

    public AcaoController(ItfFabricaAcoes pAcao) {
        super(FabTipoAcaoSistema.ACAO_CONTROLLER, pAcao);
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

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return getComoSecundaria().getAcaoPrincipal();
    }

}

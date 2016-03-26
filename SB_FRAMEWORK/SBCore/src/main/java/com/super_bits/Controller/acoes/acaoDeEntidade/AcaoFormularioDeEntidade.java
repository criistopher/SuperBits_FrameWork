/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.acoes.AcaoDeEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioDeEntidade extends AcaoDeEntidade {

    private final ItfAcaoDoSistema acaoPrincipal;
    private final List<CaminhoCampoReflexao> camposDoFormulario;
    private final String xhtml;

    /**
     *
     * @param acaoPrincipal Ação que corresponde ao botão executar formulário
     * @param classeRelacionada Classe Relacionada
     *
     */
    public AcaoFormularioDeEntidade(ItfAcaoDoSistema acaoPrincipal, Class classeRelacionada, String pXhtml) {
        super(classeRelacionada, FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO);
        this.acaoPrincipal = acaoPrincipal;
        xhtml = pXhtml;
        camposDoFormulario = new ArrayList<>();

    }

    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }

    public List<CaminhoCampoReflexao> getCamposDoFormulario() {
        return camposDoFormulario;
    }

    public String getXhtml() {
        return xhtml;
    }

}

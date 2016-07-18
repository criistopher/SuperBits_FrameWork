/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSelecionarAcao;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação para selação de ações"}, plural = "Ações de seleção")
public class AcaoSelecionarAcao extends AcaoDoSistema implements ItfAcaoSelecionarAcao {

    @Transient
    private List<ItfAcaoDoSistema> acoes;

    public AcaoSelecionarAcao() {
        super();
    }

    public AcaoSelecionarAcao(List<ItfAcaoDoSistema> acoes) {
        super();
        this.acoes = acoes;
    }

    @Override
    public List<ItfAcaoDoSistema> getAcoes() {
        return acoes;
    }

}

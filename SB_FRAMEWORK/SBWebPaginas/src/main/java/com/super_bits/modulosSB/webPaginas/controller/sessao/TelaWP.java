
/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.sessao.ItfTipoView;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public class TelaWP implements ItfTipoView {

    private String dispositivo;

    private String aplicativo;

    private String versaoAplicativo;

    private int x;

    private int y;

    private int numeroMaximoColunas;

    private final Map<String, String> parametrosEncontrados;

    public TelaWP(String parametroTela) {
        parametrosEncontrados = new HashMap<>();
        try {

            for (String pr : parametroTela.split("\\|\\|")) {
                String[] parametro = pr.split(":");
                parametrosEncontrados.put(parametro[0], parametro[1]);
            }

            setAplicativo(parametrosEncontrados.get("aplicativo"));
            setVersaoAplicativo(parametrosEncontrados.get("versaoAplicativo"));
            setX(Integer.parseInt(parametrosEncontrados.get("tamanhoX")));
            setY(Integer.parseInt(parametrosEncontrados.get("tamanhoY")));

            if (x < 600) {
                numeroMaximoColunas = 1;
            } else {
                numeroMaximoColunas = 3;
            }

        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro configurando parametros da tela para" + parametroTela, e);
        }

    }

    @Override
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String getAplicativo() {
        return aplicativo;
    }

    public final void setAplicativo(String aplicativo) {
        this.aplicativo = aplicativo;
    }

    @Override
    public String getVersaoAplicativo() {
        return versaoAplicativo;
    }

    public final void setVersaoAplicativo(String versaoAplicativo) {
        this.versaoAplicativo = versaoAplicativo;
    }

    @Override
    public int getX() {
        return x;
    }

    public final void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public final void setY(int y) {
        this.y = y;
    }

    @Override
    public int getNumeroMaximoColunas() {
        return numeroMaximoColunas;
    }

}

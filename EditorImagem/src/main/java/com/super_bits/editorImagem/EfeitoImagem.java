/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.editorImagem;

import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Tipos de Efeitos para aplicar em imágens conhecidos pelo Sistema
 *
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 02/03/2015
 * @version 1.0
 */
public class EfeitoImagem extends ItemGenerico implements ItfBeanSimples {

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCurto() {
        String nome = getTipoEfeito().toString();

        for (PARAMETRO_EFEITO pr : parametros.keySet()) {
            nome = nome + " -" + pr + ":" + parametros.get(pr);
        }
        return nome;
    }

    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static enum TIPO_EFEITO {

        BLUR, CORTAR_RETANGULO, GAMA, REDUZIR, SUBTRACTBACKGROUND, RETANGULO_FACE, RETANGULOSIMAGEM, GIRAR_ESQUERDA, GIRAR_DIREITA, BRILHO, CONTRASTE
    }

    public static enum PARAMETRO_EFEITO {

        VALOR, PT_SUP_ESQUERDA, PT_SUP_DIREITA, PT_INFERIOR_EQUERDA, PT_INFERIOR_DIREITA,
    }

    public EfeitoImagem(TIPO_EFEITO pTipoEfeito) {
        parametros = new HashMap<>();
        tipoEfeito = pTipoEfeito;
        switch (pTipoEfeito) {
            case BLUR:
            case GAMA:
            case BRILHO:
            case CONTRASTE:
            case REDUZIR:
            case GIRAR_DIREITA:
            case GIRAR_ESQUERDA:
                parametros.put(PARAMETRO_EFEITO.VALOR, "0");
                break;
            case CORTAR_RETANGULO:
                parametros.put(PARAMETRO_EFEITO.PT_SUP_ESQUERDA, "0");
                parametros.put(PARAMETRO_EFEITO.PT_SUP_DIREITA, "0");
                parametros.put(PARAMETRO_EFEITO.PT_INFERIOR_EQUERDA, "0");
                parametros.put(PARAMETRO_EFEITO.PT_SUP_DIREITA, "0");
                break;

        }

    }

    private TIPO_EFEITO tipoEfeito;

    private Map<PARAMETRO_EFEITO, String> parametros;

    public TIPO_EFEITO getTipoEfeito() {
        return tipoEfeito;
    }

    public void setTipoEfeito(TIPO_EFEITO tipoEfeito) {
        this.tipoEfeito = tipoEfeito;
    }

    public Map<PARAMETRO_EFEITO, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<PARAMETRO_EFEITO, String> parametros) {
        this.parametros = parametros;
    }

    public BufferedImage processarImagem(BufferedImage pImagem) {

        try {
            switch (tipoEfeito) {
                case BLUR:
                    if (!parametros.get(PARAMETRO_EFEITO.VALOR).equals("0")) {
                        return UtilSBImagemEdicao.aplicarBlur(pImagem, Integer.parseInt(parametros.get(PARAMETRO_EFEITO.VALOR)));
                    }
                    break;
                case CORTAR_RETANGULO:
                    throw new UnsupportedOperationException("Efeito corte não foi implementado");
                case GAMA:
                    if (!parametros.get(PARAMETRO_EFEITO.VALOR).equals("0")) {
                        return UtilSBImagemEdicao.aplicarGama(pImagem, Integer.parseInt(parametros.get(PARAMETRO_EFEITO.VALOR)));
                    }
                    break;
                case REDUZIR:
                    if (!parametros.get(PARAMETRO_EFEITO.VALOR).equals("0")) {
                        return UtilSBImagemEdicao.redimencionaImagem(pImagem, Integer.parseInt(parametros.get(PARAMETRO_EFEITO.VALOR)));
                    }
                    break;
                case RETANGULOSIMAGEM:
                    return UtilSBImagemEdicao.localizarRetangulo(pImagem);
                case RETANGULO_FACE:
                    return UtilSBImagemEdicao.contornarFace(pImagem);
                case GIRAR_DIREITA:
                    return UtilSBImagemEdicao.rotacionarParaDireita(pImagem, Integer.parseInt(parametros.get(PARAMETRO_EFEITO.VALOR)));
                case GIRAR_ESQUERDA:
                    return UtilSBImagemEdicao.rotacionarParaEsquerda(pImagem, Integer.parseInt(parametros.get(PARAMETRO_EFEITO.VALOR)));
                case BRILHO:
                    throw new UnsupportedOperationException("não foi implementado ainda");
                case CONTRASTE:
                    throw new UnsupportedOperationException("não foi implementado ainda");

            }
        } catch (Throwable e) {
            FabMensagens.enviarMensagemUsuario("Erro aplicando efeito em imagem", FabMensagens.ERRO);
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando aplicar Efeito", e);
        }
        return pImagem;
    }

}

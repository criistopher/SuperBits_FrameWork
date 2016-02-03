/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.teste.exemplos.futebol;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author sfurbino
 */
public class GraficoObjetoEmCampo extends JPanel {

    private int largura;
    private int altura;
    private String recurso;

    public GraficoObjetoEmCampo(JPanel painelPai, String pRecurso) {

        largura = painelPai.getWidth();
        altura = painelPai.getHeight();
        recurso = pRecurso;
        painelPai.add(this);
        painelPai.doLayout();
        this.setBounds(0, 0, largura, altura);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        try {

            BufferedImage imagemOriginal = ImageIO.read(ClassLoader.getSystemResource(recurso));

            g.drawImage(imagemOriginal, 0, 0, largura, altura, null);
            g.dispose();
        } catch (IOException ex) {
            System.out.println("ERROOOO CARREGANDO IMAGEM");
            Logger.getLogger(GraficoObjetoEmCampo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

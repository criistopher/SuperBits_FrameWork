/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreDeveloperStopMessage;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreEmailTest {

    public UtilSBCoreEmailTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testEnviaGmailporSSL() {
        SBCore.configurar(new ConfigCoreDeveloperStopMessage(), true);
        UtilSBCoreEmail.enviaGmailporSSL(
                "guiasemktdigitalcontagem@gmail.com",
                "",
                "Olá, conforme solicitação, segue a senha,<br/> mas antes, como esssa "
                + "é uma mensagem provisória não posso deixar de observar a completa falta de senso nexo e conformidade com a realidade, um ser humano viver esquecendo sua senha...  <br/>"
                + "Mas blz, dessa vez passa a senha é  pEmail",
                "salviof@gmail.com",
                "Teste");
    }

}

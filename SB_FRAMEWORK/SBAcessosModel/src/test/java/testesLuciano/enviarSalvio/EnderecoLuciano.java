package testesLuciano.enviarSalvio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class EnderecoLuciano implements Serializable {

    @Column(name = "LOGRADOURO_TESTE")
    private String logradouro222;

    @Column(name = "LOGRADOURO_TESTE")
    public String getLogradouro2() {
        return logradouro222;
    }

    public void setLogradouro2(String logradouro) {
        this.logradouro222 = logradouro;
    }

}

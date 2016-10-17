package testesLuciano.enviarSalvio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnderecoLuciano implements Serializable {

    @Column(name = "logradouro222")
    private String logradouro222;

    public String getLogradouro222() {
        return logradouro222;
    }

    public void setLogradouro222(String pLogradouro222) {
        this.logradouro222 = pLogradouro222;
    }

}

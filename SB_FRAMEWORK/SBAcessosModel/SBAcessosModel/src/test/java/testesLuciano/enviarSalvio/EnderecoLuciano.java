package testesLuciano.enviarSalvio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class EnderecoLuciano implements Serializable {

    @Column(insertable = false, updatable = false)
    private CidadeLuciano cidadeEndereco;
    private String logradouro;
    private String nro;

    @OneToOne
    public CidadeLuciano getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(CidadeLuciano cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

}

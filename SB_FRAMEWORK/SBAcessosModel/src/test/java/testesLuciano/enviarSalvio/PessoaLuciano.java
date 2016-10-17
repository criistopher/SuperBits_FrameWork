package testesLuciano.enviarSalvio;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PessoaLuciano implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nomeFantasia;

    private String startDateasdasd;

    private EnderecoLuciano enderecoPrincipal;

    private EnderecoLuciano enderecossecundario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Embedded()
    @AttributeOverrides({
        @AttributeOverride(name = "logradouro222",
                column = @Column(name = "logradouroPrincipal"))
    })
    public EnderecoLuciano getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(EnderecoLuciano enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    @AttributeOverrides({
        @AttributeOverride(name = "logradouro222", column = @Column(name = "entrega"))
    })
    public EnderecoLuciano getEnderecossecundario() {
        return enderecossecundario;
    }

    public void setEnderecossecundario(EnderecoLuciano enderecossecundario) {
        this.enderecossecundario = enderecossecundario;
    }

}

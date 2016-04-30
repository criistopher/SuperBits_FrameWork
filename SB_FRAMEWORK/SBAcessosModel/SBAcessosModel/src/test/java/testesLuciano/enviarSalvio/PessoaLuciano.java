package testesLuciano.enviarSalvio;

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
public class PessoaLuciano {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeFantasia;
    private EnderecoLuciano enderecoPrincipal;
    private EnderecoLuciano enderecoEntrega;
    //private EnderecoLuciano enderecoCobranca;

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

    @Embedded
    public EnderecoLuciano getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(EnderecoLuciano enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    @AttributeOverrides({
        @AttributeOverride(name = "cep", column = @Column(name = "cepEntrega")),
        @AttributeOverride(name = "cidadeEndereco", column = @Column(name = "cidadeEntrega")),
        @AttributeOverride(name = "bairro", column = @Column(name = "bairroEntrega")),
        @AttributeOverride(name = "logradouro", column = @Column(name = "logradouroEntrega")),
        @AttributeOverride(name = "nro", column = @Column(name = "nroEntrega")),
        @AttributeOverride(name = "complemento", column = @Column(name = "ComplementoEntrega")),
        @AttributeOverride(name = "caixaPostal", column = @Column(name = "caixaPostalEntrega")),})
    @Embedded
    public EnderecoLuciano getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoLuciano enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
}

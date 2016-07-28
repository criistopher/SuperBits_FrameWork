@Entity
@InfoClasse(tags = {"Comprador", "Cliente", "Compra", "Colaborador", "Parceiro"}, icone = "fa fa-shopping-cart", plural = "Compradores")
public class Comprador extends EntidadeContatoCorporativo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "nome descritivo", descricao = "Descreve o nome da entidade")
@Column(length = 0, nullable = false)
private String nome;

@InfoCampo(tipo = FabCampos.TEXTO_SIMPLES, label = "texto simples", descricao = "Um campo de texto simples")
@Column(length = 0, nullable = false)
private String razaoSocial;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "Telefone", descricao = "Telefone fixo nacional")
@Column(length = 0, nullable = false)
private String telefone;

@InfoCampo(tipo = FabCampos.CNPJ, label = "CNPJ", descricao = "NUmero de registro da empresa")
@Column(length = 0, nullable = false)
private String cnpj;

@InfoCampo(tipo = FabCampos.LISTA, label = "Filiais", descricao = "Filiais do Comprador")
@OneToMany(targetEntity = FilialComprador.class, cascade = CascadeType.ALL, orphanRemoval = true)
private List<FilialComprador>filiais;

@InfoCampo(tipo = FabCampos.LOOKUP, label = "Filial Principal", descricao = "Filial Principal do Comprador")
@OneToMany(targetEntity = FilialComprador)
private List<FilialComprador>filialPrincipal;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "Compradores_Permitidos", joinColumns = @JoinColumn(name = "acesso_id"), inverseJoinColumns = @JoinColumn(name = "comprador_id"))
private List<Comprador>compradoresPermitidos;

@Transient
@ListaCampanha(lista = ListasCampanha.ENUM_TESTE)
private List<Pedido> declarado;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "LABEL", descricao = "DESCRIÇÃO TESTE")
@CalculoCampanha(calculo = CalculosComprador.ENUM_TESTE)
private String calculoLeroLero;

public List<Pedido> getdeclarado{

return pedidosEmAtraso;

}

public String getenum_teste {

Object resultado = getRetornoSoma();

if (resultado != null) {

return (String) resultado;

} else {

return 0;

}

}

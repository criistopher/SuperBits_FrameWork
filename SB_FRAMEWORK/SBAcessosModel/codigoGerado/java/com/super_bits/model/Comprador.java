@Entity
@InfoClasse(tags = {"Comprador", "Cliente", "Compra", "Colaborador", "Parceiro"}, icone = "fa fa-shopping-cart", plural = "Compradores")
public class Comprador extends EntidadeContatoCorporativo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String nome;

@InfoCampo(tipo = FabCampos.TEXTO_SIMPLES, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String razaoSocial;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String telefone;

@InfoCampo(tipo = FabCampos.CNPJ, label = "null", descricao = "null")
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
private List<Pedido> ENUM_TESTE;

@InfoCampo(tipo = FabCampos.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8, label = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8", descricao = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8")
@CalculoCampanha(calculo = Calculoscom.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8)
private com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8 com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@2d9836f8;

public List<Pedido> getPedidosEmAtraso() {
return pedidosEmAtraso;
}

public double getTotal() {
Object resultado = getRetornoSoma();
if (resultado != null) {
return (double) resultado;
} else {
return 0;
}

}
@Entity
@InfoClasse(tags = {"Comprador", "Cliente", "Compra", "Colaborador", "Parceiro"}, icone = "fa fa-shopping-cart", plural = "Compradores")
public class Comprador extends EntidadeContatoCorporativo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String nome;

@InfoCampo(tipo = FabCampos.TEXTO_SIMPLES, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String razaoSocial;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String telefone;

@InfoCampo(tipo = FabCampos.CNPJ, label = "null", descricao = "null")
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
private List<Pedido> ENUM_TESTE;

@InfoCampo(tipo = FabCampos.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f, label = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f", descricao = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f")
@CalculoCampanha(calculo = Calculoscom.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f)
private com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f;

public List<Pedido> getPedidosEmAtraso() {
return pedidosEmAtraso;
}

public double getTotal() {
Object resultado = getRetornoSoma();
if (resultado != null) {
return (double) resultado;
} else {
return 0;
}

}
@Entity
@InfoClasse(tags = {"Comprador", "Cliente", "Compra", "Colaborador", "Parceiro"}, icone = "fa fa-shopping-cart", plural = "Compradores")
public class Comprador extends EntidadeContatoCorporativo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String nome;

@InfoCampo(tipo = FabCampos.TEXTO_SIMPLES, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String razaoSocial;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "null", descricao = "null")
@Column(length = 0, nullable = false)
private String telefone;

@InfoCampo(tipo = FabCampos.CNPJ, label = "null", descricao = "null")
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
private List<Pedido> ENUM_TESTE;

@InfoCampo(tipo = FabCampos.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f, label = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f", descricao = "com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f")
@CalculoCampanha(calculo = Calculoscom.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f.com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f)
private com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade@6eaee86f;

public List<Pedido> getPedidosEmAtraso() {
return pedidosEmAtraso;
}

public double getTotal() {
Object resultado = getRetornoSoma();
if (resultado != null) {
return (double) resultado;
} else {
return 0;
}

}

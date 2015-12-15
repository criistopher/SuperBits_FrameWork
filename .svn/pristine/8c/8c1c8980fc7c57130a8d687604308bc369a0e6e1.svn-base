package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.webSite;

import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.temas.TemaPrimeFaces;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.temas.TemasDisponiveis;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ConfigDeSessao implements Serializable{
	private static final long serialVersionUID = 6360072868199574706L;
	
	private TemaPrimeFaces temaAtual ;
	private List<TemaPrimeFaces> temas= TemasDisponiveis.instance().getThemes();


public ConfigDeSessao() {
	setTemaAtual(TemasDisponiveis.instance().getTheme("redmond"));
}


public TemaPrimeFaces getTemaAtual() {
	return temaAtual;
}


public void setTemaAtual(TemaPrimeFaces temaAtual) {
	this.temaAtual = temaAtual;
}


public List<TemaPrimeFaces> getTemas() {
	return temas;
}


public void setTemas(List<TemaPrimeFaces> temas) {
	this.temas = temas;
}



}

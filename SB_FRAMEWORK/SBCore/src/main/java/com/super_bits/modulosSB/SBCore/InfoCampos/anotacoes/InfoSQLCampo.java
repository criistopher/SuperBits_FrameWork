package com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoSQLCampo {
	// TIPO CAMPO
	
	
	String sqlCodigo() ;
	
	
	
	
	
	

}

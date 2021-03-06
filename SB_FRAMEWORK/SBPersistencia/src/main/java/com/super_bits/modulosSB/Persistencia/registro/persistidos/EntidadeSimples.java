package com.super_bits.modulosSB.Persistencia.registro.persistidos;

//import com.super_bits.modulosSB.webPaginas.ConfigGeral.CInfo;
//Simport com.super_bits.modulosSB.webPaginas.JSFBeans.util.OrganizadorDeArquivos;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PrePersist;

public abstract class EntidadeSimples extends EntidadeGenerica implements
        ItfBeanSimples {

    public EntidadeSimples() {
        super();

        //adcionaCampoEsperado(new CampoEsperado(TC.IMG_PEQUENA, CInfo.SITE_URL
        //CInfo.pastaImagens + "/SBPequeno.jpg"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME), true);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.ID), true);

    }

    @Override
    public String getImgPequena() {

        return SBCore.getCentralDeArquivos().getEndrRemotoImagem(this, FabCampos.IMG_PEQUENA);
    }

    @Override
    public String getNomeCurto() {
        try {
            String nome = (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
            String nomeCurto = "";
            nome = nome.replace("-", " ");
            nome = nome.replace(".", " ");
            for (String parte : nome.split(" ")) {
                if (nomeCurto.length() < 15) {
                    if (nomeCurto.length() > 0) {
                        nomeCurto = nomeCurto + " " + parte;
                    } else {
                        nomeCurto = nomeCurto + parte;
                    }
                }
            }
            return nomeCurto;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Obtendo o campo nome da classe" + this.getClass().getSimpleName() + " certifique que o nome tenha sido anotado, e que o tipo retornado seja String", t);
        }
        return null;
    }

    public String getNomeCurtoURLAmigavel() {
        String nomeCurto = getNomeCurto();
        return UtilSBCoreStrings.makeStrUrlAmigavel(nomeCurto);
    }

    @Override
    public int getId() {
        Object valorId = getValorByTipoCampoEsperado(FabCampos.ID).toString();
        if (valorId != null && !"".equals(valorId)) {
            return Integer.parseInt(valorId.toString());
        } else {
            return 0;
        }
    }

    public String getNomeUnico() {
        return this.getClass().getSimpleName() + getId();
    }

    /**
     *
     * Retorna o nome do Campo anotados com InfoCampo proprio para pesquisa em
     * SQL
     *
     * Futuramente este metodo deve funcionar analizando a anotação collum do
     * Hibernate para casos onde o nome da coluna é diferente
     *
     * @param pCAmpo Nome do campos procurado
     * @return O nome do campo proprio para SQL
     */
    public String getCampoSQL(FabCampos pCAmpo) {
        try {
            Field campo = getCampoByAnotacao(FabCampos.AAA_NOME);
            if (campo == null) {

                throw new UnexpectedException("nome_curto_nao_encontrado_na_classe");

            }
            return campo.getName();
        } catch (Throwable ex) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando obter o nome do campo para select ", ex);
            return null;
        }
    }

    /**
     *
     * Retorna os nomes do Campos que foram anotados para pesquisas SQL
     *
     * Futuramente este metodo deve funcionar analizando a anotação collum do
     * Hibernate para casos onde o nome da coluna é diferente
     *
     * @param pCAmpo Tipo de campo pesquisado
     * @return Lista com os nomes encontrados
     *
     */
    public List<String> getCamposSQL(FabCampos pCAmpo) {
        List<String> lista = new ArrayList<>();
        try {

            Field campo = getCampoByAnotacao(FabCampos.AAA_NOME);
            if (campo == null) {

                throw new UnexpectedException("nome_curto_nao_encontrado_na_classe");

            }

            lista.add(campo.getName());
        } catch (Throwable ex) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando obter o nome do campo para select ", ex);

        }
        return lista;

    }

    /**
     *
     *
     * Substitua por: getCampoSQL(FabCampos pCAmpo) Antes que seja tarde demais!
     *
     * @return
     */
    @Deprecated
    public String getCampoSQLNomeCurto() {
        return getCampoSQL(FabCampos.AAA_NOME);
    }

    public void uploadFoto(Object event) {

        SBCore.getControleDeSessao(); //    throw new UnsupportedOperationException("Ainda não implementado");
        //  FileUploadEvent evento = (Primef)
        //  String categoria = (String) event.getComponent().getAttributes()
        //           .get("catImagem");
        //    UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(this,
        //            event.getFile(), categoria);
        //    throw new UnsupportedOperationException("Ainda não implementado");

    }

    @Override
    public String toString() {
        if (this == null) {
            return "nulo";
        }
        return getNomeCurto();
    }

    @Override
    public int hashCode() {
        if (this == null) {
            return "nulo".hashCode();
        }

        return (this.getClass() + getNomeUnico()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        return obj.hashCode() == this.hashCode();
    }

    public List<ItfBeanSimples> listaOpcoes(ItfBeanSimples objeto) {
        return (List) UtilSBPersistencia.getListaTodos(objeto.getClass());

    }

    @PrePersist
    public void configUsuarioAlteriou() {
        System.out.println("EXECUTOU PRE PERSIST!!!!" + this.getClass().getSimpleName());
    }

    public Long getQuantidadeRegistros() {
        return UtilSBPersistencia.getQuantidadeRegistrosNaTabela(this.getClass());
    }

    @Override
    public String getNome() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
    }

    @Override
    public void setNome(String pNome) {
        setValorByTipoCampoEsperado(FabCampos.AAA_NOME, pNome);
    }

    @Override
    public void setId(int pID) {
        setValorByTipoCampoEsperado(FabCampos.ID, pID);
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public String getIconeDaClasse() {
        return UtilSBCoreReflexao.getIconeDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

}

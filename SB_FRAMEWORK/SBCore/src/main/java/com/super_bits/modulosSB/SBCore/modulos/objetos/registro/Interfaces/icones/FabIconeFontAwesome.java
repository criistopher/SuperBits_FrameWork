/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones;

/**
 *
 * @author salvioF
 */
public enum FabIconeFontAwesome implements ItfFabricaIcone {
    ICONE_PERSONALIZADO,
    REG_NOVO, REG_EDITAR,
    REG_ATUALIZAR, REG_ENVIAR, REG_BAIXAR, REG_GERENCIAR, REG_VISUALIZAR, REG_SALVAR,
    REG_AGRUPAR_REGISTROS, REG_LISTAR,
    ESCRITORIO_DOCUMENTO_TEXTO,
    PESSOA_CORACAO,
    COMERCIO_PRESENTE,
    COMERCIO_BITCOINS,
    TECNOLOGIA_BATERIA,
    SISTEMA_HOME,
    SISTEMA_ACESSO_NEGADO,
    SISTEMA_CARTAO_DE_ACESSO,
    COMUNICACAO_OK,
    COMNUNICACAO_NAO_ESTA_OK;

    @Override
    public itfIcone getIcone() {

        IconeSistema icone = new IconeSistema();
        icone.setId(this.name().hashCode());
        switch (this) {
            case ICONE_PERSONALIZADO:
                icone.setNome("Icone indefinido");
                break;
            case REG_NOVO:
                icone.setNome("Registro- Novo");
                icone.setTagHtml("fa fa-plus");
                break;
            case REG_EDITAR:
                icone.setNome("Registro- Editar");
                icone.setTagHtml("fa fa-pencil-square-o");
                break;
            case REG_ATUALIZAR:
                icone.setNome("Registro- Atualizar");
                icone.setTagHtml("fa fa-refresh");
                break;
            case REG_ENVIAR:
                icone.setNome("Registro- Enviar");
                icone.setTagHtml("fa fa-upload");
                break;
            case REG_BAIXAR:
                icone.setTagHtml("fa fa-download");
                icone.setNome("Registro - Baixar");
                break;
            case REG_GERENCIAR:
                icone.setNome("Registro - Gerenciar Entidade");
                icone.setTagHtml("fa fa-connectdevelop");
                break;
            case REG_VISUALIZAR:
                icone.setNome("REgistro - visualizar");
                icone.setTagHtml("fa fa-eye");
                break;
            case ESCRITORIO_DOCUMENTO_TEXTO:
                icone.setTagHtml("fa fa-file-word-o");
                icone.setNome("Escritorio documento texto");

                break;
            case PESSOA_CORACAO:
                icone.setTagHtml("fa fa-heart");
                icone.setNome("Pessoa coração");
                break;
            case COMERCIO_PRESENTE:
                icone.setTagHtml("fa fa-gift");
                icone.setNome("Comercio - presente");
                break;
            case COMERCIO_BITCOINS:
                icone.setNome("Coemrcio- bitcoins");
                icone.setTagHtml("fa fa-btc");
                break;
            case TECNOLOGIA_BATERIA:
                icone.setTagHtml("fa fa-battery-three-quarters");
                icone.setNome("Tecnologia ");
                break;
            case REG_SALVAR:
                icone.setNome("Registro - salvar");
                icone.setTagHtml("fa fa-floppy-o");
                break;
            case REG_AGRUPAR_REGISTROS:
                icone.setNome("Registro -Agrupar");
                icone.setTagHtml("fa fa-th-large");
                break;
            case SISTEMA_HOME:
                icone.setNome("Sistema Home");
                icone.setTagHtml("fa fa-home");
                break;
            case SISTEMA_ACESSO_NEGADO:
                icone.setNome("Sistema - Acesso Negado");
                icone.setTagHtml("fa fa-address-card-o   <i class=\"fa fa-ban fa-stack-2x text-danger\"></i>");
                break;
            case SISTEMA_CARTAO_DE_ACESSO:
                icone.setNome("Sistema - Cartão de Acesso");
                icone.setTagHtml("fa fa-address-card-o");
                break;
            case COMUNICACAO_OK:
                icone.setNome("Ok");
                icone.setTagHtml("fa fa-thumbs-o-up");
                break;
            case COMNUNICACAO_NAO_ESTA_OK:
                icone.setNome("Não OK");
                icone.setNome("fa fa-thumbs-o-down");
                break;
            case REG_LISTAR:
                icone.setNome("Registro- listar");
                icone.setTagHtml("fa fa-list");
                break;
            default:

        }
        return icone;
    }

    @Override
    public itfIcone getRegistro() {
        return getIcone();
    }

}

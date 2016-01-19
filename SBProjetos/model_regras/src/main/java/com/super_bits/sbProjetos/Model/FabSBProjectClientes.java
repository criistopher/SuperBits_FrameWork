/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.Model;

/**
 *
 * @author sfurbino
 */
public enum FabSBProjectClientes {

    SUPERBITS {

                @Override
                public Cliente getCliente() {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome("Super Bits");
                    return novoCliente;

                }
            }, VIP {

                @Override
                public Cliente getCliente() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }, SPHERA {

                @Override
                public Cliente getCliente() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }, GUIASE {

                @Override
                public Cliente getCliente() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };

    public abstract Cliente getCliente();

}

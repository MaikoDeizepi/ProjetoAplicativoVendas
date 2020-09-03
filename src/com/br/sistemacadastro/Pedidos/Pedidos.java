/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Pedidos;

import com.br.sistemacadastro.Clientes.Clientes;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class Pedidos {
    
    private Integer codped;
    private Clientes clientes;
    private Integer codcliente;
    private String data;

    
    public Pedidos() {
    }

    public Integer getCodped() {
        return codped;
    }

    public void setCodped(Integer codped) {
        this.codped = codped;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Integer codcliente) {
        this.codcliente = codcliente;
    }
    
    
    
}

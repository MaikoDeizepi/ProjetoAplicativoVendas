/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Itens_pedidos;

import com.br.sistemacadastro.Pedidos.Pedidos;
import com.br.sistemacadastro.Produtos.Produtos;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class Itens_pedidos {
    
    private Integer codpedidos;
    private Integer codprodutos;
    private double qtde;
    private Pedidos pedidos;
    private Produtos produtos;

    public Integer getCodpedidos() {
        return codpedidos;
    }

    public void setCodpedidos(Integer codpedidos) {
        this.codpedidos = codpedidos;
    }

    public Integer getCodprodutos() {
        return codprodutos;
    }

    public void setCodprodutos(Integer codprodutos) {
        this.codprodutos = codprodutos;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }
    
    
    
}

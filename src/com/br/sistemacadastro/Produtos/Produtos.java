/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Produtos;

import java.sql.Date;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class Produtos {
    
private Integer codprod;
private String descricao;
private Double preco;
private String unidade;
private double qtde_inicial;
private String data_cad;
private double qtde_atual;

    public Integer getCodprod() {
        return codprod;
    }

    public void setCodprod(Integer codprod) {
        this.codprod = codprod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getQtde_inicial() {
        return qtde_inicial;
    }

    public void setQtde_inicial(double qtde_inicial) {
        this.qtde_inicial = qtde_inicial;
    }

    public String getData_cad() {
        return data_cad;
    }

    public void setData_cad(String data_cad) {
        this.data_cad = data_cad;
    }

    public double getQtde_atual() {
        return qtde_atual;
    }

    public void setQtde_atual(double qtde_atual) {
        this.qtde_atual = qtde_atual;
    }



    
}

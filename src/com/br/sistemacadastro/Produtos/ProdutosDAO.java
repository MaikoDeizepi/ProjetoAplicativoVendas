/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Produtos;

import BD.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class ProdutosDAO {
    public Produtos produtos;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    private int numero;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;
    
    public ProdutosDAO(){
        bd = new BD();
        produtos = new Produtos();
    }
    
    
    
    
    public boolean localizar(){
        sql = "select * from produtos where codprod = ?" ;
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1,produtos.getCodprod());
            resultSet = statement.executeQuery();
            resultSet.next();
            produtos.setCodprod(Integer.parseInt(resultSet.getString(1)));
            produtos.setDescricao(resultSet.getString(2));
            produtos.setPreco(resultSet.getDouble(3));
            produtos.setUnidade(resultSet.getString(4));
            produtos.setQtde_inicial(resultSet.getDouble(5));
            produtos.setData_cad(resultSet.getString(6));
            produtos.setQtde_atual(resultSet.getDouble(7));
            return true;
              
        }
        catch (SQLException erro)
        {
            return false;
        }
        
    }
    public String atualizar(int operacao)
    {
        men="Operação realizada com sucesso!!!";
        try{
            if (operacao==INCLUSAO){
                sql = "INSERT INTO `produtos` (`codprod`, `descricao`, `preco`, `unidade`, `qtde_inicial`, `data_cad`, `qtde_atual`) VALUES (?,?,?,?,?,?,?);";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(produtos.getCodprod()));
                statement.setString(2,produtos.getDescricao());
                statement.setDouble(3,produtos.getPreco());
                statement.setString(4,produtos.getUnidade());
                statement.setDouble(5,produtos.getQtde_inicial());
                statement.setString(6,produtos.getData_cad());
                statement.setDouble(7,produtos.getQtde_atual());
                
            }
            
            else if (operacao==ALTERACAO){
                sql ="UPDATE `produtos` SET `descricao` = ?, `preco` = ?, `unidade` = ?, `qtde_inicial` = ?, `data_cad` = ?, `qtde_atual` = ? WHERE `produtos`.`codprod` = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1,produtos.getDescricao());
                statement.setDouble(2,produtos.getPreco());
                statement.setString(3,produtos.getUnidade());
                statement.setDouble(4,produtos.getQtde_inicial());
                statement.setString(5,produtos.getData_cad());
                statement.setDouble(6,produtos.getQtde_atual());
                statement.setInt(7,(produtos.getCodprod()));
            }
            else if (operacao==EXCLUSAO){
                sql ="delete from produtos where codprod = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(produtos.getCodprod()));
                
            }
            if (statement.executeUpdate()==0){
                 men="Falha na operação!!";
            }
        }catch (SQLException erro){
               numero = erro.getErrorCode();
               if (numero==1062)
                   men="Este Codigo já existe!";
               else
               men="Falha na operação"+ erro.toString();
        }
        return men;
    }
    
}

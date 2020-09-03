/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Itens_pedidos;

import BD.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class Itens_pedidosDAO {
      
    public Itens_pedidos itenspedidos;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    private int numero;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;
    
    public Itens_pedidosDAO(){
        bd = new BD();
        itenspedidos = new Itens_pedidos();
    }
    
    
    
    
    public boolean localizar(){
        
        sql = "SELECT * FROM `itens_ped` WHERE `Produtos_codprod` = ?" ;
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1,itenspedidos.getCodpedidos());
            resultSet = statement.executeQuery();
            resultSet.next();
            itenspedidos.setCodpedidos(Integer.parseInt(resultSet.getString(1)));
            itenspedidos.setCodprodutos(Integer.parseInt(resultSet.getString(2)));
            itenspedidos.setQtde(Double.parseDouble(resultSet.getString(3)));
           
            
            
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
                
                sql = "INSERT INTO `itens_ped` (`Produtos_codprod`, `pedidos_codped`, `qtde`) VALUES (?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,itenspedidos.getCodpedidos());
                statement.setInt(2,itenspedidos.getCodprodutos());
                statement.setDouble(3,itenspedidos.getQtde());
             
            }
            /* else if (operacao==ALTERACAO){
                
                sql ="UPDATE `pedidos` SET `Clientes_codcli` = ?, `data_2` = ? WHERE `pedidos`.`codped` = ?";
                statement = bd.connection.prepareStatement(sql);
                
                statement.setInt(1,itenspedidos.getCodpedidos());
                statement.setInt(2,itenspedidos.getCodprodutos());
                statement.setDouble(3,itenspedidos.getQtde());
 
                
            }
            else if (operacao==EXCLUSAO){
                
                sql ="DELETE FROM `pedidos` WHERE `pedidos`.`codped` = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,itenspedidos.getCodpedidos());
                
            }
            
           */
         
            if (statement.executeUpdate()==0){
                 men="Falha na operação!!";
            }
        }catch (SQLException erro){
               numero = erro.getErrorCode();
               if (numero==1062)
                   men="Este RA já existe!";
               else
               men="Falha na operação"+ erro.toString();
        }
        return men;
    }
}

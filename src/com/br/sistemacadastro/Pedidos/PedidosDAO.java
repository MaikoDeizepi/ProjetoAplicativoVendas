/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Pedidos;

import BD.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class PedidosDAO {
    
    public Pedidos pedidos;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    private int numero;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;
    
    public PedidosDAO(){
        bd = new BD();
        pedidos = new Pedidos();
    }
    
    
    
    
    public boolean localizar(){
        
        sql = "SELECT * FROM `pedidos` WHERE `codped` = ?" ;
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1,pedidos.getCodped());
            resultSet = statement.executeQuery();
            resultSet.next();
            pedidos.setCodped(Integer.parseInt(resultSet.getString(1)));
            pedidos.setCodcliente(Integer.parseInt(resultSet.getString(2)));
            pedidos.setData(resultSet.getString(3));
           
            
            
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
                
                sql = "INSERT INTO `pedidos` (`codped`, `Clientes_codcli`, `data_2`) VALUES (?, ?, ?);";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(pedidos.getCodped()));
                statement.setInt(2,(pedidos.getCodcliente()));
                statement.setString(3,pedidos.getData());
             
            }
            
            else if (operacao==ALTERACAO){
                
                sql ="UPDATE `pedidos` SET `Clientes_codcli` = ?, `data_2` = ? WHERE `pedidos`.`codped` = ?";
                statement = bd.connection.prepareStatement(sql);
                
                statement.setInt(1,pedidos.getCodcliente());
                statement.setString(2,pedidos.getData());
                statement.setInt(3,pedidos.getCodped());
 
                
            }
            else if (operacao==EXCLUSAO){
                
                sql ="DELETE FROM `pedidos` WHERE `pedidos`.`codped` = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,pedidos.getCodped());
                
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Clientes;

import BD.BD;
import com.br.sistemacadastro.Clientes.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class ClientesDAO {
    public Clientes clientes;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    private int numero;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;
    
    public ClientesDAO(){
        bd = new BD();
        clientes = new Clientes();
    }
    
    
    
    
    public boolean localizar(){
        sql = "select * from clientes where codcli  = ?" ;
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1,clientes.getCodcliente());
            resultSet = statement.executeQuery();
            resultSet.next();
            clientes.setCodcliente(Integer.parseInt(resultSet.getString(1)));
            clientes.setNome(resultSet.getString(2));
            clientes.setEndereco(resultSet.getString(3));
            clientes.setBairro(resultSet.getString(4));
            clientes.setCidade(resultSet.getString(5));
            clientes.setCep(resultSet.getString(6));
            clientes.setUf(resultSet.getString(7));
            clientes.setEmail(resultSet.getString(8));
            clientes.setFone(resultSet.getString(9));
            clientes.setCelular(resultSet.getString(10));
            
            
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
                sql = "INSERT INTO `clientes` (`codcli`, `nome`, `endereco`, `bairro`, `cidade`, `cep`, `uf`, `email`, `fone`, `celular`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(clientes.getCodcliente()));
                statement.setString(2,clientes.getNome());
                statement.setString(3,clientes.getEndereco());
                statement.setString(4,clientes.getBairro());
                statement.setString(5,clientes.getCidade());
                statement.setString(6,clientes.getCep());
                statement.setString(7,clientes.getUf());
                statement.setString(8,clientes.getEmail());
                statement.setString(9,clientes.getFone());
                statement.setString(10,clientes.getCelular());
                
                
            }
            
            else if (operacao==ALTERACAO){
                sql ="UPDATE `clientes` SET `nome` = ?, `endereco` = ?, `bairro` = ?, `cidade` = ?, `cep` = ?, `uf` = ?, `email` = ?, `fone` = ?, `celular` = ? WHERE `clientes`.`codcli` = ?";
                statement = bd.connection.prepareStatement(sql);
                
                statement.setString(1,clientes.getNome());
                statement.setString(2,clientes.getEndereco());
                statement.setString(3,clientes.getBairro());
                statement.setString(4,clientes.getCidade());
                statement.setString(5,clientes.getCep());
                statement.setString(6,clientes.getUf());
                statement.setString(7,clientes.getEmail());
                statement.setString(8,clientes.getFone());
                statement.setString(9,clientes.getCelular());
                statement.setInt(10,(clientes.getCodcliente()));
            }
            else if (operacao==EXCLUSAO){
                sql ="delete from clientes where codcli = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(clientes.getCodcliente()));
                
            }
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

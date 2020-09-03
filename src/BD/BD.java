/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import java.sql.*;
/**
 *
 * @author maiko
 */
public class BD {
    public Connection connection = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DBNAME = "aula";
    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    private final String LOGIN = "root";
    private final String SENHA = "root";  //123456
    
    
    //método para conexão com banco de dados
    
    public boolean getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,LOGIN, SENHA);
            System.out.println("Conectou");
            return (true);
            
        }
        catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado "+ erro.toString());
            return (false);
        }
        catch (SQLException erro){
            System.out.println("Falha ao conectar "+ erro.toString());
            return(false);
        }
    }
    
    public void close(){
        try{
            connection.close();
            System.out.println("Desconectou ");
        }
        catch (SQLException erro){
    }
}
}
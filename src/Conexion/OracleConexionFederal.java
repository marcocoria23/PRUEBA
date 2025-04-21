/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO.CORIA
 */
public class OracleConexionFederal {
        private Connection conexion; 
     
    public Connection getConexion() { 
        return conexion; 
    }    
    public void setConexion(Connection conexion) { 
        this.conexion = conexion; 
    }  
    
    //CONEXION A ORACLE ESQUEMA PRODUCTIVO
    public OracleConexionFederal Conectar() { 
        try { 
            Class.forName("oracle.jdbc.OracleDriver"); 
            //:ssid  /Server
           // String BaseDeDatos = "jdbc:oracle:thin:@cengob_bd.inegi.gob.mx:1521/cengob.inegi.gob.mx"; 
             String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:xe";
             conexion = DriverManager.getConnection(BaseDeDatos,"SYSTEM","admin");       
            if (conexion != null) { 
                System.out.println("Conexion exitosa!"); 
            } else { 
                System.out.println("Conexion fallida!"); 
            } 
        } catch (ClassNotFoundException | SQLException e) { 
            System.out.println("Error: " + e);
        }        
        return this; 
    } 
    
    public boolean escribir(String sql) { 
        try {
            Statement sentencia; 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql); 
            //getConexion().commit(); 
            sentencia.close();
             
        } catch (SQLException e) { 
            System.out.print("ERROR SQL"+e); 
            return false; 
        }         
        return true; 
    } 
    
    public ResultSet consultar(String sql) {
        ResultSet rst = null;
        try {
            Statement sentencia; 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rst = sentencia.executeQuery(sql);
        } catch (SQLException e) { 
            System.out.println("error sql "+e);
            return null; 
        }
        return rst; 
    }
    
    public void close() throws SQLException{
        System.out.println("Conexion Cerrada");
        conexion.close();
    }
}

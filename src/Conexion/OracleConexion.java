/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
/**
 *
 * @author ANTONIO.CORIA
 */
import Screen_laborales.PInsertTMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mx.org.BD.ReadCSV_Audiencias;

/**
 *
 * @author ANTONIO.CORIA
 */
public class OracleConexion {
        private Connection conexion; 
     
    public Connection getConexion() { 
        return conexion; 
    }    
    public void setConexion(Connection conexion) { 
        this.conexion = conexion; 
    }  
    
    //CONEXION A ORACLE ESQUEMA PRODUCTIVO
    public OracleConexion Conectar() { 
        try { 
           //:ssid  /Server
            //CONECTARSE A PROGRESQL
           // Class.forName("org.postgresql.Driver");
           // String BaseDeDatos = "jdbc:postgresql://localhost:5432/postgres";
            //conexion = DriverManager.getConnection(BaseDeDatos,"postgres","admin");
             Class.forName("oracle.jdbc.OracleDriver"); 
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
    
    
      public boolean Insertar(String sql,String[] campos)throws SQLException {
        ReadCSV_Audiencias Read=new ReadCSV_Audiencias();
        PInsertTMP TMP =new PInsertTMP();
        String var="";
          try {
            Statement sentencia; 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeQuery(sql); 
        } catch (SQLException e) { 
           if (TMP.tabla.equals("Control_expediente")){
             var= "ERROR SQL  CLAVE_ORGANO: "+campos[2]+" "+e;  
           }
           if (TMP.tabla.equals("T.1.0_audiencias")){
            var= "ERROR SQL  CLAVE_ORGANO: "+campos[1]+"  Expediente_clave:  "+campos[2]+" Id_audiencia:  "+campos[4]+" "+e;
           }    
           Read.impErro=var+Read.impErro;
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
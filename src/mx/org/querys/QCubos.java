/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexion;
import Conexion.OracleConexionFederal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANTONIO.CORIA
 */
public class QCubos {
    
OracleConexion conexion = new OracleConexion();
OracleConexionFederal conexionFed= new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul; 


public String Envio(){
    conexion.Conectar();
    String En=""; 
    sql="select max(Envio+1) Envio  from RES_TAB_01";
    try {
          resul=conexion.consultar(sql);
          if (resul.next()) {
                  En=resul.getString("Envio");      
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return En;
}



    
}

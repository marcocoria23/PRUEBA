/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexion;
import Conexion.OracleConexionFederal;
import Screen_laborales.PCambioEstatus;
import Screen_laborales.PEstatus;
import Screen_laborales.PValidacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO.CORIA
 */
public class QEstatus {
OracleConexion conexion = new OracleConexion();
OracleConexionFederal conexionFed= new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
 ResultSet resul; 

 
 
 
 public void V1CambioEstatus(){
 conexion.Conectar();
     
      sql="UPDATE V1_TR_CONTROLJL SET ESTATUS='"+PCambioEstatus.estatus+"' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";
      System.out.println(sql);
      try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos"+ex);
        }
       
}
 
 
 public void V2CambioEstatus(){
 conexion.Conectar();
     
      sql="UPDATE V2_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='"+PCambioEstatus.estatus+"' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";
      System.out.println(sql);
     try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos"+ex);
        }
       
}
 
 public void V3CambioEstatus(){
 conexion.Conectar();
     String sql1="",sql2="",sql3="";  
     sql1="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='CE' WHERE ESTATUS='CEU' and CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"'";
     sql2="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='I' WHERE ESTATUS='IU' and CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"'";
     sql3="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='C' WHERE ESTATUS='CU' and CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"'";          
      if (PCambioEstatus.estatus.equals("CE")){
          sql="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='CEU' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";     
      }
      if(PCambioEstatus.estatus.equals("I")){
          sql="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='IU' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";    
      }
      if(PCambioEstatus.estatus.equals("C")){
          sql="UPDATE V3_TR_CONTROL_EXPEDIENTEJL SET ESTATUS='CU' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";    
      }
      System.out.println(sql1);
      System.out.println(sql);
      
      try {
      conexion.escribir(sql1);
      conexion.escribir(sql2);
      conexion.escribir(sql3);
      conexion.escribir(sql); 
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos"+ex);
        }
       
}

 
 
 public void V1FedCambioEstatus(){
 conexionFed.Conectar();
     
      sql="UPDATE TR_FED_V1_CONTROL_EXPEDIENTEJL SET ESTATUS='"+PCambioEstatus.estatus+"' WHERE CLAVE_ORGANO='"+PCambioEstatus.clave_organo+"' AND PERIODO='"+PCambioEstatus.periodo+"'";
      System.out.println(sql);
      try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos"+ex);
        }
       
}
 
 
 public ArrayList V1Estatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select ID,CLAVE_ORGANO,REPLACE(SEDE,',','')SEDE,ENTIDAD_CLAVE, ENTIDAD_NOMBRE, MUNICIPIO_CLAVE, MUNICIPIO_NOMBRE,PERIODO, ESTATUS from V1_TR_controljl where substr(clave_organo,0,2)='"+PEstatus.Entidad+"' and clave_organo='"+PEstatus.Clave_organo+"'  order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ID"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SEDE"),
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("ENTIDAD_NOMBRE"),
                  resul.getString("MUNICIPIO_CLAVE"),
                  resul.getString("MUNICIPIO_NOMBRE"),
                  resul.getString("PERIODO"),
                  resul.getString("ESTATUS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 
 public ArrayList V2Estatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select ID_CONTROL, CLAVE_ORGANO, REPLACE(SEDE,',','')SEDE, ENTIDAD_CLAVE, ENTIDAD_NOMBRE, MUNICIPIO_CLAVE, MUNICIPIO_NOMBRE, PERIODO, ESTATUS from v2_tr_control_expedientejl where substr(clave_organo,0,2)='"+PEstatus.Entidad+"' and clave_organo='"+PEstatus.Clave_organo+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ID_CONTROL"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SEDE"),
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("ENTIDAD_NOMBRE"),
                  resul.getString("MUNICIPIO_CLAVE"),
                  resul.getString("MUNICIPIO_NOMBRE"),
                  resul.getString("PERIODO"),
                  resul.getString("ESTATUS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 
 public ArrayList V3Estatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select ID_CONTROL, CLAVE_ORGANO,REPLACE(SEDE,',','')SEDE, ENTIDAD_CLAVE, ENTIDAD_NOMBRE, MUNICIPIO_CLAVE, MUNICIPIO_NOMBRE, PERIODO, ESTATUS from v3_tr_control_expedientejl where substr(clave_organo,0,2)='"+PEstatus.Entidad+"' and clave_organo='"+PEstatus.Clave_organo+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ID_CONTROL"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SEDE"),
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("ENTIDAD_NOMBRE"),
                  resul.getString("MUNICIPIO_CLAVE"),
                  resul.getString("MUNICIPIO_NOMBRE"),
                  resul.getString("PERIODO"),
                  resul.getString("ESTATUS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 
 public ArrayList V1Federal(){
     conexionFed.Conectar();
      Array = new ArrayList();
      sql="select ID_CONTROL, CLAVE_ORGANO, SEDE, ENTIDAD_CLAVE, ENTIDAD_NOMBRE, MUNICIPIO_CLAVE, MUNICIPIO_NOMBRE, PERIODO, ESTATUS from TR_FED_V1_CONTROL_EXPEDIENTEJL where substr(clave_organo,0,2)='"+PEstatus.Entidad+"' and clave_organo='"+PEstatus.Clave_organo+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ID_CONTROL"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SEDE"),
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("ENTIDAD_NOMBRE"),
                  resul.getString("MUNICIPIO_CLAVE"),
                  resul.getString("MUNICIPIO_NOMBRE"),
                  resul.getString("PERIODO"),
                  resul.getString("ESTATUS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 
}

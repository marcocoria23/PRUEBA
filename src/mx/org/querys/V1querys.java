/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexion;
import Screen_laborales.PEstatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Screen_laborales.PValidacion;


/**
 *
 * @author ANTONIO.CORIA
 */
public class V1querys {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
String NMunicipio="";
private String Clave;
private String Periodo;
 ResultSet resul;   
    
 
 //Query donde selecciona la clave de organo unica estas claves se mandan a llamar a la pantalla con nombre PValidacion.
 public ArrayList Clave_organo(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(CLAVE_ORGANO)CLAVE_ORGANO from V1_TR_controljl order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 public ArrayList Clave_organoEstatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(CLAVE_ORGANO)CLAVE_ORGANO from V1_TR_controljl where substr(clave_organo,0,2)='"+PEstatus.Entidad+"'  order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 
 
 //Query donde selecciona el periodo unico mandandolo a llamar a la pantalla con nombre PValidacion.
  public ArrayList Periodo(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(PERIODO)PERIODO from V1_TR_controljl  where clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  public ArrayList PeriodoEstatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(PERIODO)PERIODO from V1_TR_controljl  where clave_organo='"+PEstatus.Clave_organo+"' or substr(clave_organo,0,2)='"+PEstatus.Entidad+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  //Query donde selecciona la entidad=substr(clave_organo,0,2) mandandolo a llamar a la pantalla con nombre PValidacion.
  public ArrayList Entidad(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(substr(clave_organo,0,2)) Entidad from V1_TR_controljl order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("Entidad")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  //Query donde selecciona la clave de municipio.
  public String Nombre_Municipio(){
     conexion.Conectar();
      sql="select Descripcion from catalogos_municipiojl where municipio_id="+PValidacion.clave_organo.substring(0, 5)+"";
      System.out.println(PValidacion.clave_organo.substring(0, 5));
      resul=conexion.consultar(sql);
      try {
          if (resul.next()) {
              NMunicipio=resul.getString("Descripcion");
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return NMunicipio;
 }
 
  
     

}


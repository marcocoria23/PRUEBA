/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;


import Conexion.OracleConexion;
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
public class V1QSsocial {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;


//Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
public ArrayList Año_Expe_SSocial(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V1_TR_ssocialjl ) WHERE  FECHA_APERTURA <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURAS"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 //Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente.   
 public ArrayList Fecha_Admision_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_admision_demanda,'DD/MM/YYYY') fecha_admision_demanda ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_admision_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_admision_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"'  ) where  to_date(fecha_admision_demanda,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_admision_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
//Query de validacion donde la fecha de audiencia a juicio no debe de ser menor o igual  a la fecha de apertura del expediente
 public ArrayList Fecha_Audienciaj(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_audienciaj,'DD/MM/YYYY') fecha_audienciaj ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audienciaj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audienciaj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audienciaj,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_audienciaj")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de audiencia preliminar no debe de ser menor o igual a la fecha de apertura del expediente
  public ArrayList Fecha_Audienciap(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_audienciap,'DD/MM/YYYY') fecha_audienciap ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audienciap <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audienciap <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audienciap,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_audienciap")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 //Query de validacion donde la fecha de emplazamiento de la demanda  no debe de ser menor o igual a la fecha de apertura del expediente 
   public ArrayList Fecha_Emplazam_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_emplazam_demanda,'DD/MM/YYYY') fecha_emplazam_demanda ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_emplazam_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_emplazam_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_emplazam_demanda,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_emplazam_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
      //Query de validacion donde la fecha de resolucion (audiencia de juicio) no debe de ser menor a la fecha de apertura del expediente
      public ArrayList Fecha_Resolucion_Aj(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_resolucion_aj,'DD/MM/YYYY') fecha_resolucion_aj ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_aj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_aj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_resolucion_aj,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_resolucion_aj")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
            //Query de validacion donde la fecha de resolucion (audiencia preliminar) no debe de ser menor a la fecha de apertura del expediente
            public ArrayList Fecha_Resolucion_Ap(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_resolucion_ap,'DD/MM/YYYY') fecha_resolucion_ap ,periodo\n" +
          "from V1_TR_ssocialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_ap <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_ap <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_resolucion_ap,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_resolucion_ap")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

            
            
            
            
}

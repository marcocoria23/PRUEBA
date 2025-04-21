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
public class V1QColectivo {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;
    
 //Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
    public ArrayList Año_Expe_Colectivo(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V1_TR_colectivojl ) WHERE  FECHA_APERTURA <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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

//Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente
    public ArrayList Fecha_Admi_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(select entidad_clave,clave_organo,expediente_clave,fecha_apertura,to_char(fecha_admi_demanda,'DD/MM/YYYY') fecha_admi_demanda,periodo\n" +
          "from V1_TR_colectivojl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_admi_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_admi_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_admi_demanda,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_admi_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
    //Query de validacion donde la fecha de audiencia a juicio no debe de ser menor a la fecha de apertura del expediente
    public ArrayList Fecha_Audiencia_Juicio(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_audiencia_juicio,'DD/MM/YYYY') fecha_audiencia_juicio ,periodo\n" +
          "from V1_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audiencia_juicio <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audiencia_juicio <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_audiencia_juicio,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_audiencia_juicio")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

     //Query de validacion donde la fecha de depuracion no debe de ser menor a la fecha de apertura del expediente
        public ArrayList Fecha_Depuracion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_depuracion,'DD/MM/YYYY') fecha_depuracion   ,periodo\n" +
          "from V1_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_depuracion <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_depuracion <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_depuracion,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_depuracion")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
        
         //Query de validacion donde la fecha dicto depuracion no debe de ser menor a la fecha de apertura del expediente
                public ArrayList Fecha_Dicto_Depuracion_Ad(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_dicto_depuracion_ad,'DD/MM/YYYY') fecha_dicto_depuracion_ad ,periodo\n" +
          "from V1_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_dicto_depuracion_ad <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_dicto_depuracion_ad <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_dicto_depuracion_ad,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_dicto_depuracion_ad")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

               //Query de validacion donde la fecha de emplazamiento a la demanda no debe de ser menor o igual a la fecha de apertura del expediente
                public ArrayList Fecha_Emplaz_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_emplaz_demanda,'DD/MM/YYYY') fecha_emplaz_demanda  ,periodo\n" +
          "from V1_TR_colectivojl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_emplaz_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_emplaz_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_emplaz_demanda,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("fecha_emplaz_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }                
     //Query de validacion donde la fecha de resolucion por audiencia de juicio no debe de ser menor a la fecha de apertura del expediente 
                public ArrayList Fecha_Resolucion_Aj(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_resolucion_aj,'DD/MM/YYYY') fecha_resolucion_aj,periodo\n" +
          "from V1_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_aj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_aj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_resolucion_aj,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
     System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
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

}





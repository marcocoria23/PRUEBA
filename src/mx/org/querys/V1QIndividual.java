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
public class V1QIndividual {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
 ResultSet resul;
 
 
////Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente. 
 public ArrayList Año_Expe_Individual(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V1_TR_individualjl ) WHERE  FECHA_APERTURA <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
public ArrayList Fecha_Admi_demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(fecha_admi_demanda,'DD/MM/YYYY') fecha_admi_demanda ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
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

 //Query de validacion donde la fecha de Emplazamiento de la demanda no debe de ser menor o igual a la fecha de apertura del expediente
public ArrayList Fecha_Emplaz_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_EMPLAZ_DEMANDA,'DD/MM/YYYY') FECHA_EMPLAZ_DEMANDA  ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_EMPLAZ_DEMANDA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_EMPLAZ_DEMANDA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_EMPLAZ_DEMANDA,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_EMPLAZ_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de depuracion no debe de ser menor o igual a la fecha de apertura del expediente.
public ArrayList Fecha_Depuracion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_DEPURACION,'DD/MM/YYYY') FECHA_DEPURACION  ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DEPURACION <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DEPURACION <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DEPURACION,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_DEPURACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de audiencia preliminar no debe de ser menor a la fecha de apertura del expediente.
public ArrayList Fecha_Audiencia_Prelim(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_AUDIENCIA_PRELIM,'DD/MM/YYYY') FECHA_AUDIENCIA_PRELIM  ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIENCIA_PRELIM <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIENCIA_PRELIM <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_AUDIENCIA_PRELIM,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_AUDIENCIA_PRELIM")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de audiencia a juicio no debe de ser menor o igual a la fecha de apertura del expediente
public ArrayList Fecha_Audiencia_Juicio(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_AUDIENCIA_JUICIO,'DD/MM/YYYY') FECHA_AUDIENCIA_JUICIO ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIENCIA_JUICIO <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIENCIA_JUICIO <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_AUDIENCIA_JUICIO,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_AUDIENCIA_JUICIO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de resolucion (Tramitacion sin audiencias) no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Resolucion_Ta(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_RESOLUCION_TA,'DD/MM/YYYY') FECHA_RESOLUCION_TA  ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_RESOLUCION_TA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLUCION_TA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_RESOLUCION_TA,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_RESOLUCION_TA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la fecha de resolucion (auto depuracion) no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Resolucion_Ad(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_DICTO_RESOLUCION_AD,'DD/MM/YYYY') FECHA_DICTO_RESOLUCION_AD  ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DICTO_RESOLUCION_AD <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DICTO_RESOLUCION_AD <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DICTO_RESOLUCION_AD,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_DICTO_RESOLUCION_AD")
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
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_DICTO_RESOLUCION_AP,'DD/MM/YYYY') FECHA_DICTO_RESOLUCION_AP ,periodo\n" +
          "from V1_TR_individualjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DICTO_RESOLUCION_AP <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DICTO_RESOLUCION_AP <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DICTO_RESOLUCION_AP,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_DICTO_RESOLUCION_AP")
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
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_DICTO_RESOLUCION_AJ,'DD/MM/YYYY') FECHA_DICTO_RESOLUCION_AJ ,periodo\n" +
          "from V1_TR_individualjl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DICTO_RESOLUCION_AJ <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DICTO_RESOLUCION_AJ <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DICTO_RESOLUCION_AJ,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_DICTO_RESOLUCION_AJ")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

}

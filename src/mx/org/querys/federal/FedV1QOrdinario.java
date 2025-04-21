/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.federal;

import Conexion.OracleConexionFederal;
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
public class FedV1QOrdinario {
    OracleConexionFederal conexion = new OracleConexionFederal();
String sql,cuenta="";
ArrayList<String[]> Array;
 ResultSet resul;
 
 //Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche.
 public ArrayList Año_JudicialCampeche(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
" SELECT * FROM( \n" +
"        SELECT * FROM( \n" +
"        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,\n" +
"        SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  FECHA_APERTURA_EXPEDIENTE,\n" +
"TO_NUMBER(SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4))+1  FECHA_APERTURA_EXPEDIENTES1,\n" +
"        substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO, \n" +
"        PERIODO \n" +
"        from TR_FED_V1_ordinariojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')  \n" +
"        WHERE  FECHA_APERTURA_EXPEDIENTES  \n" +
"        NOT BETWEEN     to_date('01/09/'||FECHA_APERTURA_EXPEDIENTE||'')\n" +
"         AND  TO_date('01/08/'||(FECHA_APERTURA_EXPEDIENTES1)||'') ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche comprendiendo los años 2020,2021,2022.
  public ArrayList Año_DIF_Campeche(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
" SELECT * FROM( \n" +
"        SELECT * FROM( \n" +
"        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  \n" +
"        FECHA_APERTURA_EXPEDIENTE,substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ',''),'l','')),-4,4) EXPE_AÑO, \n" +
"        PERIODO \n" +
"        from TR_FED_V1_ordinariojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') WHERE EXPE_AÑO NOT IN "+PValidacion.AñoJuridico+" ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 
 
 
 //Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
 public ArrayList Año_Expe_OrdinarioNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from TR_FED_V1_ordinariojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde la Fecha del último acto procesal no debe de ser menor a la fecha de apertura del expediente.
  public ArrayList Fecha_Acto_ProcesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Acto_Procesal,'DD/MM/YYYY')Fecha_Acto_Procesal,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Acto_Procesal <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Acto_Procesal <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(Fecha_Acto_Procesal,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_Acto_Procesal")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde la Fecha de admision de la demanda no debe de ser menor a la fecha de apertura del expediente.
 public ArrayList Fecha_Admi_demandaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_admi_demanda,'DD/MM/YYYY')fecha_admi_demanda,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_admi_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_admi_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_admi_demanda,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_admi_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 //Query de validacion donde la Fecha de audiencia de juicio no debe de ser menor o igual a la fecha de apertura del expediente.
 public ArrayList Fecha_Audiencia_JuicioNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_audiencia_juicio,'DD/MM/YYYY')fecha_audiencia_juicio,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audiencia_juicio <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audiencia_juicio <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audiencia_juicio,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_audiencia_juicio")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 //Query de validacion donde la Fecha de audiencia preliminar no debe de ser menor o igual a la fecha de apertura del expediente.
  public ArrayList Fecha_Audiencia_PrelimNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_audiencia_prelim,'DD/MM/YYYY') fecha_audiencia_prelim  ,periodo\n" +
          "from TR_FED_V1_ordinariojl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audiencia_prelim <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audiencia_prelim <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audiencia_prelim,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_audiencia_prelim")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde la Fecha en la que se dictó la resolución (Fase escrita) no debe de ser menor a la fecha de apertura del expediente.
  public ArrayList Fecha_Dicto_ResolucionFE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_DICTO_RESOLUCIONFE,'DD/MM/YYYY') FECHA_DICTO_RESOLUCIONFE  ,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DICTO_RESOLUCIONFE <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DICTO_RESOLUCIONFE <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DICTO_RESOLUCIONFE,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_DICTO_RESOLUCIONFE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 
  
  //Query de validacion donde la Fecha en la que se dictó la resolución (Audiencia preliminar) no debe de ser menor a la fecha de apertura del expediente.
  public ArrayList Fecha_Dicto_ResolucionAP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_DICTO_RESOLUCIONAP,'DD/MM/YYYY') FECHA_DICTO_RESOLUCIONAP  ,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_DICTO_RESOLUCIONAP <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_DICTO_RESOLUCIONAP <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_DICTO_RESOLUCIONAP,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_DICTO_RESOLUCIONAP")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 
 
 //Query de validacion donde la Fecha en la que se dictó la resolución (Audiencia de juicio) no debe de ser menor a la fecha de apertura del expediente.
  public ArrayList Fecha_ResolucionAJ(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_RESOLUCIONAJ,'DD/MM/YYYY') FECHA_RESOLUCIONAJ  ,periodo\n" +
          "from TR_FED_V1_ordinariojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_RESOLUCIONAJ <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLUCIONAJ <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_RESOLUCIONAJ,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_RESOLUCIONAJ")
                });   
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QOrdinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  
}
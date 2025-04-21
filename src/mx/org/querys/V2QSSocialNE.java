/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexionNE;
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
public class V2QSSocialNE {
OracleConexionNE conexion = new OracleConexionNE();
String sql;
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
"        from V2_TR_seg_socialjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')  \n" +
"        WHERE  FECHA_APERTURA_EXPEDIENTES  \n" +
"        NOT BETWEEN     to_date('01/09/'||FECHA_APERTURA_EXPEDIENTE||'')\n" +
"         AND  TO_date('01/08/'||(FECHA_APERTURA_EXPEDIENTES1)||'') ";
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
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
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
"        FECHA_APERTURA_EXPEDIENTE,substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO, \n" +
"        PERIODO \n" +
"        from V2_TR_seg_socialjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') WHERE EXPE_AÑO NOT IN "+PValidacion.AñoJuridico+" ";
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
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 

//Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
public ArrayList Año_Expe_SSocialNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V2_TR_seg_socialjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
//Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente
 public ArrayList Fecha_Admision_DemandaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_admision_demanda,'DD/MM/YYYY') fecha_admision_demanda ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_admision_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_admision_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"'  ) where  to_date(fecha_admision_demanda,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_admision_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la Fecha de audiencia de juicio no debe de ser menor o igual a la fecha de apertura del expediente
 public ArrayList Fecha_AudienciajNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_audienciaj,'DD/MM/YYYY') fecha_audienciaj ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audienciaj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audienciaj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audienciaj,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_audienciaj")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 //Query de validacion donde la Fecha de audiencia preliminar no debe de ser menor o igual a la fecha de apertura del expediente
  public ArrayList Fecha_AudienciapNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_audienciap,'DD/MM/YYYY') fecha_audienciap ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audienciap <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audienciap <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_audienciap,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_audienciap")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde la Fecha del último acto procesal no debe de ser menor a la fecha de apertura del expediente
   public ArrayList Fecha_Acto_procesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Acto_procesal,'DD/MM/YYYY') Fecha_Acto_procesal ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Acto_procesal <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Acto_procesal <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(Fecha_Acto_procesal,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_Acto_procesal")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

   //Query de validacion donde la Fecha en la que se dictó la resolución (Solución (Audiencia de juicio)) no debe de ser menor a la fecha de apertura del expediente
      public ArrayList Fecha_Resolucion_AjNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_resolucion_aj,'DD/MM/YYYY') fecha_resolucion_aj ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_aj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_aj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_resolucion_aj,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_resolucion_aj")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

      //Query de validacion donde la Fecha en la que se dictó la resolución ( (Audiencia preliminar)) no debe de ser menor a la fecha de apertura del expediente
            public ArrayList Fecha_Resolucion_ApNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_resolucion_ap,'DD/MM/YYYY') fecha_resolucion_ap ,periodo\n" +
          "from V2_TR_seg_socialjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_ap <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_ap <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_resolucion_ap,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
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


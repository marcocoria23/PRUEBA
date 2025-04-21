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
public class V3QEjecucion {
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
"        from V3_TR_ejecucionjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
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
"        from V3_TR_ejecucionjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO AND EXPE_AÑO NOT IN ('2021','2022','2023','2020','2024','2025')) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
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
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  //Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
   public ArrayList Año_Expe_EjecucionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V3_TR_ejecucionjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
   
      public ArrayList FECHA_APERTURA_EXPEDIENTE_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_EJECUCIONJL \n" +
"WHERE \n" +
"(FECHA_APERTURA_EXPEDIENTE>SYSDATE \n" +
"AND FECHA_APERTURA_EXPEDIENTE <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}
   
 public ArrayList FECHA_PRESENTACION_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_PRESENTACION,'DD/MM/YYYY')FECHA_PRESENTACION,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_EJECUCIONJL \n" +
"WHERE \n" +
"(FECHA_PRESENTACION>SYSDATE \n" +
"AND FECHA_PRESENTACION <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_PRESENTACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_CONCLUSION_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_CONCLUSION,'DD/MM/YYYY')FECHA_CONCLUSION,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_EJECUCIONJL \n" +
"WHERE \n" +
"(FECHA_CONCLUSION>SYSDATE \n" +
"AND FECHA_CONCLUSION <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_CONCLUSION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

   
   
   
   public ArrayList Duplicidad_expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO FROM V3_TR_EJECUCIONJL\n" +
"WHERE CLAVE_ORGANO||TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))||PERIODO\n" +
"IN(\n" +
"SELECT CLAVE_ORGANO||EXPEDIENTE_CLAVE2||PERIODO FROM(\n" +
"SELECT CLAVE_ORGANO,COUNT(expediente_clave2)CUENTAexpediente_clave2,expediente_clave2,PERIODO FROM(\n" +
"select CLAVE_ORGANO,expediente_clave,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO from V3_TR_EJECUCIONJL \n" +
"WHERE (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"') or (CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"')  \n" +
"ORDER BY CLAVE_ORGANO,expediente_clave2)\n" +
"GROUP BY CLAVE_ORGANO,expediente_clave2,PERIODO)WHERE CUENTAexpediente_clave2>1)\n" +
"ORDER BY CLAVE_ORGANO,expediente_clave2";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
   
   
    public ArrayList Fecha_PresentacionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select ESTATUS_EXPE,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_PRESENTACION,'dd/mm/yyyy')FECHA_PRESENTACION,PERIODO\n" +
"from V3_TR_EJECUCIONJL\n" +
"WHERE to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') < to_date(FECHA_PRESENTACION,'dd/mm/yyyy')) where  periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_PRESENTACION <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_PRESENTACION <> '09/09/1899'  and periodo = '"+PValidacion.periodo+"'  and periodo = '"+PValidacion.periodo+"'\n";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ESTATUS_EXPE"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_PRESENTACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
   
//Query de validacion donde la fecha de conclusion no debe de ser menor a la fecha de apertura del expediente.
public ArrayList Fecha_ConclusionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select ESTATUS_EXPE,clave_organo,expediente_clave,to_char(fecha_apertura_expediente,'DD/MM/YYYY') fecha_apertura_expediente,to_char(fecha_conclusion,'DD/MM/YYYY') fecha_conclusion  ,periodo\n" +
          "from V3_TR_EJECUCIONJL where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_conclusion <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_conclusion <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(fecha_conclusion,'dd/mm/yyyy')  < to_date(fecha_apertura_expediente,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ESTATUS_EXPE"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura_expediente"),
                  resul.getString("fecha_conclusion")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

}

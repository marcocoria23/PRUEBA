/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexionNE;
import Screen_laborales.PValidacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANTONIO.CORIA
 */
public class V3QAudiencias {
OracleConexionNE conexion = new OracleConexionNE();
String sql;
ArrayList<String[]> Array;
ResultSet resul;
    

public ArrayList FECHA_AUDIEN_CELEBRADA_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_AUDIENCIASJL\n" +
"WHERE \n" +
"(FECHA_AUDIEN_CELEBRADA > SYSDATE \n" +
"AND FECHA_AUDIEN_CELEBRADA <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_AUDIEN_CELEBRADA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList FORMATO_INICIO(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(select \n" +
"CLAVE_ORGANO,\n" +
"  EXPEDIENTE_CLAVE,\n" +
" ID_AUDIENCIA,\n" +
" DECODE(TIPO_PROCED,'1','Ordinario','2','Especial Individual','3','Especial Colectivo','4','Huelga','5','Colectivo Ecónomica','9','No identificado') TIPO_PROCED,\n" +
" INICIO,\n" +
" CASE WHEN LENGTH(regexp_replace(INICIO, '[^0-9]', ''))=4 THEN SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),0,2) \n" +  
" WHEN LENGTH(regexp_replace(INICIO, '[^0-9]', ''))=3 THEN SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),0,1) END CAM_INICIO, \n" +              
" PERIODO \n" +
" from V3_TR_AUDIENCIASJL) WHERE ((INICIO NOT LIKE '%:%' or REPLACE(length(TRIM(INICIO)),' ','')<5 or CAM_INICIO IN ('1','01','2','02','3','03','4','04','5','05','6','06')) AND\n" +
" (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'\n" +
"AND PERIODO='"+PValidacion.periodo+"')) OR ((INICIO NOT LIKE '%:%' or REPLACE(length(TRIM(INICIO)),' ','')<5 or CAM_INICIO IN ('1','01','2','02','3','03','4','04','5','05','6','06')) AND\n" +
" (CLAVE_ORGANO='"+PValidacion.clave_organo+"'\n" +
"AND PERIODO='"+PValidacion.periodo+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ID_AUDIENCIA"),
                  resul.getString("TIPO_PROCED"),
                  resul.getString("INICIO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList FORMATO_CONCLU(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(select \n" +
"CLAVE_ORGANO,\n" +
"  EXPEDIENTE_CLAVE,\n" +
" ID_AUDIENCIA,\n" +
"  DECODE(TIPO_PROCED,'1','Ordinario','2','Especial Individual','3','Especial Colectivo','4','Huelga','5','Colectivo Ecónomica','9','No identificado') TIPO_PROCED, \n" +
" CONCLU,\n" +
" CASE WHEN LENGTH(regexp_replace(CONCLU, '[^0-9]', ''))=4 THEN SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),0,2) \n" +
" WHEN LENGTH(regexp_replace(CONCLU, '[^0-9]', ''))=3 THEN SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),0,1) END CAM_CONCLU, \n" +
" PERIODO \n" +
" from V3_TR_AUDIENCIASJL) WHERE ((CONCLU NOT LIKE '%:%' or REPLACE(length(TRIM(CONCLU)),' ','')<5 or CAM_CONCLU IN ('1','01','2','02','3','03','4','04','5','05','6','06')) AND\n" +
" (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'\n" +
"AND PERIODO='"+PValidacion.periodo+"')) OR ((CONCLU NOT LIKE '%:%' or REPLACE(length(TRIM(CONCLU)),' ','')<5 or CAM_CONCLU IN ('1','01','2','02','3','03','4','04','5','05','6','06')) AND\n" +
" (CLAVE_ORGANO='"+PValidacion.clave_organo+"'\n" +
"AND PERIODO='"+PValidacion.periodo+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ID_AUDIENCIA"),
                  resul.getString("TIPO_PROCED"),
                  resul.getString("CONCLU")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList SEGUNDOS_INICIO(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select \n" +
"CLAVE_ORGANO,\n" +
"  EXPEDIENTE_CLAVE,\n" +
" ID_AUDIENCIA,\n" +
"  DECODE(TIPO_PROCED,'1','Ordinario','2','Especial Individual','3','Especial Colectivo','4','Huelga','5','Colectivo Ecónomica','9','No identificado') TIPO_PROCED,\n" +
" INICIO,\n" +
" PERIODO\n" +
" from V3_TR_AUDIENCIASJL WHERE ((SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),-2)>59) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"') AND (INICIO NOT IN ('99:99')) ) OR \n" +
"((SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),-2)>59) AND (CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"') AND (INICIO NOT IN ('99:99')) )";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ID_AUDIENCIA"),
                  resul.getString("TIPO_PROCED"),
                  resul.getString("INICIO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList SEGUNDOS_CONCLU(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select \n" +
"CLAVE_ORGANO,\n" +
"  EXPEDIENTE_CLAVE,\n" +
" ID_AUDIENCIA,\n" +
"  DECODE(TIPO_PROCED,'1','Ordinario','2','Especial Individual','3','Especial Colectivo','4','Huelga','5','Colectivo Ecónomica','9','No identificado') TIPO_PROCED, \n" +
" CONCLU,\n" +
" PERIODO\n" +
" from V3_TR_AUDIENCIASJL WHERE ((SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),-2)>59) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"') AND (CONCLU NOT IN ('99:99'))) OR \n" +
"((SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),-2)>59) AND (CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"') AND (CONCLU NOT IN ('99:99')))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ID_AUDIENCIA"),
                  resul.getString("TIPO_PROCED"),
                  resul.getString("CONCLU")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList CONCLU_MENOR(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM("
              + "select \n" +
"CLAVE_ORGANO,\n" +
"EXPEDIENTE_CLAVE,\n" +
"ID_AUDIENCIA,\n" +
"DECODE(TIPO_PROCED,'1','Ordinario','2','Especial Individual','3','Especial Colectivo','4','Huelga','5','Colectivo Ecónomica','9','No identificado') TIPO_PROCED, \n" +              
"PERIODO,\n" +
"case when length(INICIO)<5 then '0'||inicio else INICIO end inicio,\n" +
"case when length(CONCLU)<5 then '0'||CONCLU else CONCLU end CONCLU \n" +
" from V3_TR_AUDIENCIASJL WHERE (TO_TIMESTAMP(CONCLU,'HH24:MI') < TO_TIMESTAMP(inicio,'HH24:MI') and (SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),-2)<60) and (SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),-2)<60) AND SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'\n" +
"AND PERIODO='"+PValidacion.periodo+"' AND CONCLU like ('%:%') and inicio like ('%:%')) OR (CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"' AND  (SUBSTR(regexp_replace(CONCLU, '[^0-9]', ''),-2)<60) and (SUBSTR(regexp_replace(INICIO, '[^0-9]', ''),-2)<60) AND TO_TIMESTAMP(CONCLU,'HH24:MI') < TO_TIMESTAMP(inicio,'HH24:MI') AND CONCLU like ('%:%') and inicio like ('%:%'))  \n"
              + " ) WHERE INICIO NOT IN ('99:99') OR CONCLU NOT IN ('99:99')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ID_AUDIENCIA"),
                 resul.getString("TIPO_PROCED"),
                  resul.getString("INICIO"),
                  resul.getString("CONCLU")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }





}

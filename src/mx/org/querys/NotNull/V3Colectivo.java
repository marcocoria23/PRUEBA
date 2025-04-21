/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.NotNull;

import Conexion.OracleConexion;
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
public class V3Colectivo {
 
 OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;



//Tipo de asunto no debe de ser =9 No_identificado
public ArrayList Tipo_Asunto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'9','No_identificado') TIPO_ASUNTO,PERIODO from V3_TR_COLECTIVOJL\n" +
"WHERE ((TIPO_ASUNTO ='9' OR TIPO_ASUNTO IS NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_ASUNTO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




//--Cuando ¿Se anexó constancia de no conciliación expedida por el Centro Conciliación?=NO no debe de capturar Clave/identificador de la constancia
public ArrayList Cons_Expedida(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CONSTANCIA_CONS_EXPEDIDA,'2','No','9','No identificado') CONSTANCIA_CONS_EXPEDIDA,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE (CONSTANCIA_CONS_EXPEDIDA IN ('2','9') AND INCOMPETENCIA='1' AND (CONSTANCIA_CLAVE IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CONSTANCIA_CONS_EXPEDIDA"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--Cuando Suspensión temporal de las relaciones de trabajo=No o No identificado no debe capturar desde Por causas de fuerza mayor o caso fortuito no imputable al patrón hasta Por falta de ministración por parte del Estado. 
public ArrayList Suspencion_temporal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(SUSPENSION_TMP,'2','No','9','No identificado') SUSPENSION_TMP,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE (SUSPENSION_TMP IN ('2','9') AND TIPO_ASUNTO='2' AND (NO_IMPUTABLE_ST IS NOT NULL OR INCAPACIDAD_FISICA_ST IS NOT NULL OR FALTA_MATERIA_PRIM_ST IS NOT NULL OR  FALTA_MINISTRACION_ST IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("SUSPENSION_TMP"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--cuando Terminacion colectiva de las relaciones de trabajo=No o No identificado, no debe capturarse desde Por causas de fuerza mayor o caso fortuito no imputable al patrón hasta Por agotamiento de la materia objeto de una industria extractiva
public ArrayList Terminacion_Colectiva(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TERMINACION_TRAB,'2','No','9','No identificado') TERMINACION_TRAB,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE (TERMINACION_TRAB in ('2','9') and TIPO_ASUNTO='2'  AND (FUERZA_MAYOR_TC IS NOT NULL OR INCAPACIDAD_FISICA_TC IS NOT NULL OR QUIEBRA_LEGAL_TC IS NOT NULL OR AGOTAMIENTO_MATERIA_TC IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TERMINACION_TRAB"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--cuando Violaciones a derechos fundamentales en materia colectiva=No o No identificado, no debe capturarse desde Libertad de asociación hasta  Especifique (otro tipo de violaciones a derechos fundamentales en materia colectiva).
public ArrayList Viola_Derechos(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(VIOLA_DERECHOS,'2','No','9','No identificado') VIOLA_DERECHOS,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE (VIOLA_DERECHOS='2' AND TIPO_ASUNTO='2' AND (LIBERTAD_ASOCIACION IS NOT NULL OR  LIBERTAD_SINDICAL IS NOT NULL \n" +
"OR  DERECHO_COLECTIVA IS NOT NULL OR OTRO_COLECTIVA IS NOT NULL OR OTRO_ESP_COLECTIVA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("VIOLA_DERECHOS"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--Estatus de la demanda no debe de ser 9=No_identificado.
public ArrayList Estatus_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'9','No_identificado') ESTATUS_DEMANDA,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_COLECTIVOJL\n" +
"WHERE ((ESTATUS_DEMANDA ='9' OR ESTATUS_DEMANDA IS NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_DEMANDA"),
                  resul.getString("COMENTARIOS"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--Estatus del expediente no debe de ser 9=No_identificado.
public ArrayList Estatus_ExpedienteNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,'9','No_identificado') ESTATUS_EXPEDIENTE,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_COLECTIVOJL\n" +
"WHERE ( (ESTATUS_EXPEDIENTE ='9' OR ESTATUS_EXPEDIENTE IS NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("COMENTARIOS"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_COLECTIVOJL\n" +
" where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')where incompetencia='9' OR incompetencia IS NULL"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
                  resul.getString("COMENTARIOS"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList Tipo_IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
" SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA,PERIODO FROM V3_TR_COLECTIVOJL WHERE INCOMPETENCIA='2')\n" +
"WHERE TIPO_INCOMPETENCIA IS NOT NULL  \n" +
" )where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//INCOMPETENCIA =SI NO DEBE DE CAPTURAR DESPUES DE ESPECIFIQUE OTRO INCOMPETENCIA
public ArrayList PivIncompetencia(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO, EXPEDIENTE_CLAVE, ENTIDAD_CLAVE, MUNICIPIO_CLAVE,PERIODO,\n" +
"INCOMPETENCIA, OTRO_ESP_INCOMP,FECHA_PRES_DEMANDA,CONSTANCIA_CONS_EXPEDIDA,CONSTANCIA_CLAVE,\n" +
"ASUN_EXCEP_CONCILIACION,PREVE_DEMANDA,DESAHOGO_PREV_DEMANDA,ESTATUS_DEMANDA,FECHA_ADMI_DEMANDA,\n" +
"CANTIDAD_ACTORES,CANTIDAD_DEMANDADOS,AUTO_DEPURACION,FECHA_DEPURACION,AUDIENCIA_JUICIO,FECHA_AUDIENCIA_JUICIO,\n" +
"ESTATUS_EXPEDIENTE,FECHA_ACTO_PROCESAL,FASE_SOLI_EXPEDIENTE,FORMA_SOLUCION_AD,OTRO_ESP_SOLUCION_AD,FECHA_DICTO_RESOLUCION_AD,\n" +
"TIPO_SENTENCIA_AD,MONTO_SOLUCION_AD,FORMA_SOLUCION_AJ,OTRO_ESP_SOLUCION_AJ,FECHA_RESOLUCION_AJ,TIPO_SENTENCIA_AJ,MONTO_SOLUCION_AJ\n" +
"FROM  V3_TR_COLECTIVOJL\n" +
"WHERE \n" +
"(INCOMPETENCIA = '1' ) \n" +
"AND ( \n" +
"FECHA_PRES_DEMANDA IS NOT NULL OR\n" +
"CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR\n" +
"CONSTANCIA_CLAVE IS NOT NULL OR\n" +
"ASUN_EXCEP_CONCILIACION IS NOT NULL OR\n" +
"PREVE_DEMANDA IS NOT NULL OR\n" +
"DESAHOGO_PREV_DEMANDA IS NOT NULL OR\n" +
"ESTATUS_DEMANDA IS NOT NULL OR\n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR\n" +
"CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"AUTO_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR\n" +
"AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCION_AJ IS NOT NULL )) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//Cuando ¿Se formuló prevención a la demanda?=NO o No identificado, no debe de capturar ¿Se desahogó la prevención de la demanda?
public ArrayList Preve_demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(PREVE_DEMANDA,'2','No','9','No identificado') PREVE_DEMANDA,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE (PREVE_DEMANDA in ('2','9') AND incompetencia='2' and  (DESAHOGO_PREV_DEMANDA IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("PREVE_DEMANDA"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//ESTATUS DEMANDA =DESECHADA,ARCHIVO, NO SE DIO TRAMITE A LA DEMANDA APARTIR DE FECHA_ADMI_DEMANDA NO DEBEN DE CONTESTAR
public ArrayList Estatus_Demanda_Desechada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (SELECT CLAVE_ORGANO, EXPEDIENTE_CLAVE, ENTIDAD_CLAVE, MUNICIPIO_CLAVE,PERIODO,\n" +
"INCOMPETENCIA, ESTATUS_DEMANDA,FECHA_ADMI_DEMANDA,\n" +
"CANTIDAD_ACTORES,\n" +
"CANTIDAD_DEMANDADOS,\n" +
"AUTO_DEPURACION,\n" +
"FECHA_DEPURACION,\n" +
"AUDIENCIA_JUICIO,\n" +
"FECHA_AUDIENCIA_JUICIO,\n" +
"ESTATUS_EXPEDIENTE,\n" +
"FECHA_ACTO_PROCESAL,\n" +
"FASE_SOLI_EXPEDIENTE,\n" +
"FORMA_SOLUCION_AD,\n" +
"OTRO_ESP_SOLUCION_AD,\n" +
"FECHA_DICTO_RESOLUCION_AD,\n" +
"TIPO_SENTENCIA_AD,\n" +
"MONTO_SOLUCION_AD,\n" +
"FORMA_SOLUCION_AJ,\n" +
"OTRO_ESP_SOLUCION_AJ,\n" +
"FECHA_RESOLUCION_AJ,\n" +
"TIPO_SENTENCIA_AJ,\n" +
"MONTO_SOLUCION_AJ\n" +
"FROM  V3_TR_COLECTIVOJL\n" +
"WHERE \n" +
"(ESTATUS_DEMANDA = '2' OR ESTATUS_DEMANDA = '3' OR ESTATUS_DEMANDA = '4'  ) \n" +
"AND (FECHA_ADMI_DEMANDA IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"AUTO_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR\n" +
"AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCION_AJ IS NOT NULL)) where  SUBSTR(CLAVE_ORGANO,0,2) = '"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


////Cuando ¿Hubo tramitación por auto de depuración? =No o No identificado, no debe de capturarse Fecha de auto de depuración.
public ArrayList Tram_depuracion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUTO_DEPURACION,'2','No','9','No identificado') AUTO_DEPURACION,PERIODO FROM V3_TR_COLECTIVOJL \n" +
"WHERE (AUTO_DEPURACION in ('2','9') and ESTATUS_DEMANDA='1' AND (FECHA_DEPURACION IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUTO_DEPURACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


////Cuando ¿Hubo celebración de audiencia de juicio? =No o No identificado, no debe de capturarse Fecha de audiencia de juicio
public ArrayList Audiencia_juicio(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_JUICIO,'2','No','9','No identificado') AUDIENCIA_JUICIO,PERIODO FROM V3_TR_COLECTIVOJL \n" +
"WHERE (AUDIENCIA_JUICIO in ('2','9') and ESTATUS_DEMANDA='1' AND (FECHA_AUDIENCIA_JUICIO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUDIENCIA_JUICIO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }





//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Estatus_Expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
               "SELECT * FROM (SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V3_TR_COLECTIVOJL WHERE \n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE ESTATUS_EXPEDIENTE='2'";      
 System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Fecha_acto_procesal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,FECHA_ACTO_PROCESAL,PERIODO\n" +
"from V3_TR_COLECTIVOJL WHERE ESTATUS_EXPEDIENTE='1' AND\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ";      
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("FECHA_ACTO_PROCESAL")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList Fase_Sol_expNoExiste(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,S.DESCRIPCION FASE_SOLI_EXPEDIENTE\n" +
"FROM V3_TR_COLECTIVOJL P, V3_TC_FASE_EXPEDIENTEJL S\n" +
"WHERE  P.FASE_SOLI_EXPEDIENTE=S.ID\n" +
"AND FASE_SOLI_EXPEDIENTE NOT IN('2','3','99') AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )"; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//Cuando Fase de solicitud del expediente (FASE_SOLI_EXPEDIENTE) =No identificado, no debe capturar desde Forma de solución (FORMA_SOLUCION_AD) hasta Monto estipulado en la forma de solución (MONTO_SOLUCION_AJ).
public ArrayList Fase_Sol_expNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(FASE_SOLI_EXPEDIENTE,'99','No identificado')FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_COLECTIVOJL \n" +
"WHERE FASE_SOLI_EXPEDIENTE='99' AND (FORMA_SOLUCION_AD IS NOT NULL OR OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_RESOLUCION_AJ IS NOT NULL OR \n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL) and \n"
              + "  (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )" +
""; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=TRAMITACION POR AUTODEPURACION NO DEBE DE CAPTURAR SOLUCION (AUDIENCIA DE JUICIO)
public ArrayList Fase_Sol_exp_TAP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_COLECTIVOJL WHERE \n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_RESOLUCION_AJ IS NOT NULL OR \n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN ('3')"; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA JUICIO NO DEBE DE CAPTURAR   SOLUCION(TRAMITACION POR AUTO DEPURACION)
public ArrayList Fase_Sol_exp_AJ(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_COLECTIVOJL WHERE \n" +
"FORMA_SOLUCION_AD IS NOT NULL OR  OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR  MONTO_SOLUCION_AD IS NOT NULL \n" +
" ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN ('2')"; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList SinMotivo_Conflicto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT\n" +
"    CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE\n" +
"FROM\n" +
"    V3_tr_ColectivoJL\n" +
"WHERE\n" +
"    declaracion_perdida_may = '2'\n" +
"     AND suspension_tmp = '2'\n" +
"     AND terminacion_trab = '2' \n" +
"     AND contratacion_colectiva = '2'\n" +
"     AND omisiones_reglamento = '2'\n" +
"     AND reduccion_personal = '2'\n" +
"     AND viola_derechos = '2'\n" +
"     AND eleccion_sindicales = '2'\n" +
"     AND sancion_sindicales = '2'\n" +
"     AND otro_conflicto = '2' and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')"; 
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
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


}


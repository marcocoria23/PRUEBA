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
public class V2Colectivo {
  
 OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;





///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO from V2_TR_COLECTIVOJL\n" +
" where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')where incompetencia=9"; 
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



///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList Tipo_IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
" SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA,PERIODO FROM V2_TR_COLECTIVOJL WHERE INCOMPETENCIA=2)\n" +
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
"FROM  V2_TR_COLECTIVOJL\n" +
"WHERE \n" +
"(INCOMPETENCIA = 1 ) \n" +
"AND (FECHA_PRES_DEMANDA IS NOT NULL OR CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR CONSTANCIA_CLAVE IS NOT NULL OR ASUN_EXCEP_CONCILIACION IS NOT NULL OR\n" +
"PREVE_DEMANDA IS NOT NULL OR DESAHOGO_PREV_DEMANDA IS NOT NULL OR ESTATUS_DEMANDA IS NOT NULL OR FECHA_ADMI_DEMANDA \n" +
"IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR AUTO_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR AUDIENCIA_JUICIO IS NOT NULL OR FECHA_AUDIENCIA_JUICIO IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL )) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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


//ESTATUS DEMANDA =DESECHADA,ARCHIVO, NO SE DIO TRAMITE A LA DEMANDA APARTIR DE FECHA_ADMI_DEMANDA NO DEBEN DE CONTESTAR
public ArrayList Estatus_Demanda_Desechada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (SELECT CLAVE_ORGANO, EXPEDIENTE_CLAVE, ENTIDAD_CLAVE, MUNICIPIO_CLAVE,PERIODO,\n" +
"INCOMPETENCIA,ESTATUS_DEMANDA,FECHA_ADMI_DEMANDA,\n" +
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
"FROM  V2_TR_COLECTIVOJL\n" +
"WHERE \n" +
"(ESTATUS_DEMANDA = 2 OR ESTATUS_DEMANDA = 3 OR ESTATUS_DEMANDA = 4  ) \n" +
"AND (FECHA_ADMI_DEMANDA \n" +
"IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR AUTO_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR AUDIENCIA_JUICIO IS NOT NULL OR FECHA_AUDIENCIA_JUICIO IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL)) where  SUBSTR(CLAVE_ORGANO,0,2) = '"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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


//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Estatus_Expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
               "SELECT     CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM (SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V2_TR_COLECTIVOJL WHERE \n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE ESTATUS_EXPEDIENTE=2";      
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
"from V2_TR_COLECTIVOJL WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
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



//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=TRAMITACION POR AUTODEPURACION NO DEBE DE CAPTURAR SOLUCION (AUDIENCIA DE JUICIO)
public ArrayList Fase_Sol_exp_TAP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_COLECTIVOJL WHERE \n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCION_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (3)"; 
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_COLECTIVOJL WHERE \n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL" +
" ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (2)"; 
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
    
    
}

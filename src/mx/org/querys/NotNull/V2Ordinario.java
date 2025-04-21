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
public class V2Ordinario {

OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;





///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO from V2_TR_ORDINARIOJL\n" +
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA,PERIODO FROM V2_TR_ORDINARIOJL WHERE INCOMPETENCIA=2)\n" +
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
"SELECT         CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO FROM V2_TR_ORDINARIOJL\n" +
"WHERE  \n" +
"(FECHA_PRES_DEMANDA IS NOT NULL OR\n" +
"CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR \n" +
"CONSTANCIA_CLAVE IS NOT NULL OR\n" +
"ASUN_EXCEP_CONCILIACION IS NOT NULL OR\n" +
"PREVE_DEMANDA IS NOT NULL OR\n" +
"DESAHOGO_PREV_DEMANDA IS NOT NULL OR\n" +
"ESTATUS_DEMANDA IS NOT NULL OR\n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR \n" +
"CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR \n" +
"AUDIENCIA_PRELIM IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR \n" +
"AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR \n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION IS NOT NULL OR \n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCIONAJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION IS NOT NULL OR \n" +
"TIPO_SENTENCIA IS NOT NULL OR \n" +
"MONTO_SOLUCION_AJ IS NOT NULL)AND INCOMPETENCIA=1) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
      sql="SELECT * FROM( SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA,PERIODO FROM V2_TR_ORDINARIOJL WHERE\n" +
"(FECHA_ADMI_DEMANDA IS NOT NULL OR\n" +
"CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"AUDIENCIA_PRELIM IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR\n" +
"AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR\n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCIONAJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION IS NOT NULL OR\n" +
"TIPO_SENTENCIA IS NOT NULL OR\n" +
"MONTO_SOLUCION_AJ IS NOT NULL\n" +
")AND ESTATUS_DEMANDA IN (2,3,4))where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V2_TR_ORDINARIOJL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCIONAJ IS NOT NULL OR\n" +
"FECHA_RESOLUCION IS NOT NULL OR\n" +
"TIPO_SENTENCIA IS NOT NULL OR\n" +
"MONTO_SOLUCION_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
"from V2_TR_ORDINARIOJL WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
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




//---CUANDO FASE DEL EXPEDIENTE=AUDIENCIA PRELIMINAR NO DEBE DE CAPTURAR RUBRO (AUDIENCIA DE JUICIO)
public ArrayList Fase_Sol_exp_AP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_ORDINARIOJL WHERE\n" +
"(FORMA_SOLUCIONAJ IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCIONAJ IS NOT NULL OR \n" +
"FECHA_RESOLUCION IS NOT NULL OR \n" +
"TIPO_SENTENCIA IS NOT NULL OR \n" +
"MONTO_SOLUCION_AJ IS NOT NULL) AND FASE_SOLI_EXPEDIENTE IN (1)) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'" ; 
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


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA JUICIO NO DEBE DE CAPTURAR  SOLUCION (AUDIENCIA PRELIMINAR)
public ArrayList Fase_Sol_exp_AJ(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_ORDINARIOJL WHERE \n" +
"(FORMA_SOLUCIONAP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL) AND ESTATUS_EXPEDIENTE IN (2) ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'"; 
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

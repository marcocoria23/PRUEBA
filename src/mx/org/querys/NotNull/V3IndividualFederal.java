/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.NotNull;

import Conexion.OracleConexion;
import Conexion.OracleConexionFederal;
import Screen_laborales.PValidacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.querys.V1querys;

/**
 *
 * @author ANTONIO.CORIA
 */
public class V3IndividualFederal {
OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;






///Tipo de asunto no debe de ser =9 No_identificado
public ArrayList Tipo_Asunto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'9','No_identificado') TIPO_ASUNTO from TR_FED_INDIVIDUAL\n" +
"WHERE (TIPO_ASUNTO =9 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_ASUNTO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




//Cuando ¿El trabajador contó con contrato escrito? = No o No identificado, no debe de capturar tipo de contrato
public ArrayList Contrato_Escrito(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CONTRATO_ESCRITO,'2','No','9','No identificado') CONTRATO_ESCRITO FROM TR_FED_INDIVIDUAL \n" +
"WHERE (CONTRATO_ESCRITO in (2,9) and ( TIPO_CONTRATO IS NOT NULL ) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CONTRATO_ESCRITO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



////Cuando ¿Se anexó constancia de no conciliación expedida por el Centro Conciliación?=NO O NO IDENTIFICADO no debe de capturar Clave/identificador de la constancia
public ArrayList Centro_conciliacion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CONSTANCIA_CONS_EXPEDIDA,'2','No','9','No identificado') CONSTANCIA_CONS_EXPEDIDA FROM TR_FED_INDIVIDUAL\n" +
"WHERE (CONSTANCIA_CONS_EXPEDIDA in (2,9) and incompetencia=2 AND (CONSTANCIA_CLAVE IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CONSTANCIA_CONS_EXPEDIDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




////Cuando ¿Se formuló prevención a la demanda?=NO O NO IDENTIFICADO no debe de capturar ¿Se desahogó la prevención de la demanda?
public ArrayList Preve_demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(PREVE_DEMANDA,'2','No','9','No identificado') PREVE_DEMANDA FROM TR_FED_INDIVIDUAL\n" +
"WHERE (PREVE_DEMANDA in (2,9) and incompetencia=2 AND (DESAHOGO_PREV_DEMANDA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("PREVE_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


////Estatus de la demanda no debe de ser 9=No_identificado.
public ArrayList Estatus_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'9','No_identificado') ESTATUS_DEMANDA,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_INDIVIDUAL\n" +
"WHERE (ESTATUS_DEMANDA =9 AND (CLAVE_ORGANO||EXPEDIENTE_CLAVE NOT IN ('12035106/2022')) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_DEMANDA"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



////Cuando Estatus de la demanda =Admitida no debe de capturarse Causas que impiden la admisión de la demanda
public ArrayList Estatus_Demanda_admitida(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'1','Admitida') ESTATUS_DEMANDA FROM TR_FED_INDIVIDUAL \n" +
"WHERE (ESTATUS_DEMANDA=1 AND (CAU_IMPI_ADMI_DEMANDA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
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
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TRAMITACION_DEPURACION,'2','No','9','No identificado') TRAMITACION_DEPURACION FROM TR_FED_INDIVIDUAL \n" +
"WHERE (TRAMITACION_DEPURACION in (2,9) and ESTATUS_DEMANDA=1 AND (FECHA_DEPURACION IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TRAMITACION_DEPURACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

////Cuando ¿Hubo celebración de audiencia preliminar? =No o No identificado, no debe de capturarse Fecha de audiencia preliminar.
public ArrayList Audiencia_preliminar(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_PRELIM,'2','No','9','No identificado') AUDIENCIA_PRELIM FROM TR_FED_INDIVIDUAL \n" +
"WHERE (AUDIENCIA_PRELIM in (2,9) and ESTATUS_DEMANDA=1 AND (FECHA_AUDIENCIA_PRELIM IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUDIENCIA_PRELIM")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


////Cuando ¿Hubo celebración de audiencia de juicio? =No o No identificado, no debe de capturarse Fecha de audiencia de juicio.
public ArrayList Audiencia_juicio(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_JUICIO,'2','No','9','No identificado') AUDIENCIA_JUICIO FROM TR_FED_INDIVIDUAL \n" +
"WHERE (AUDIENCIA_JUICIO in (2,9) and ESTATUS_DEMANDA=1 AND (FECHA_AUDIENCIA_JUICIO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
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


////Estatus del expediente no debe de ser 9=No_identificado.
public ArrayList Estatus_ExpedienteNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,'9','No identificado') ESTATUS_EXPEDIENTE,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_INDIVIDUAL\n" +
"WHERE (ESTATUS_EXPEDIENTE =9 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("COMENTARIOS")
                  
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_INDIVIDUAL\n" +
" where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )where incompetencia=9"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
                  resul.getString("COMENTARIOS")
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA FROM TR_FED_INDIVIDUAL WHERE INCOMPETENCIA=2)\n" +
"WHERE TIPO_INCOMPETENCIA IS NOT NULL  \n" +
" )where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA")
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
"SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA from TR_FED_INDIVIDUAL\n" +
"WHERE  FECHA_PRES_DEMANDA IS NOT NULL OR CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR CONSTANCIA_CLAVE IS NOT NULL OR \n" +
"ASUN_EXCEP_CONCILIACION IS NOT NULL OR PREVE_DEMANDA IS NOT NULL OR DESAHOGO_PREV_DEMANDA IS NOT NULL OR \n" +
"ESTATUS_DEMANDA IS NOT NULL OR CAU_IMPI_ADMI_DEMANDA IS NOT NULL OR FECHA_ADMI_DEMANDA IS NOT NULL OR \n" +
"CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR TRAMITACION_DEPURACION IS NOT NULL OR \n" +
"FECHA_DEPURACION IS NOT NULL OR AUDIENCIA_PRELIM IS NOT NULL OR FECHA_AUDIENCIA_PRELIM IS NOT NULL OR AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR FECHA_ACTO_PROCESAL IS NOT NULL OR \n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR OTRO_ESP_SOLUCION_AD IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR MONTO_SOLUCION_AD IS NOT NULL OR \n" +
"FORMA_SOLUCION_TA IS NOT NULL OR OTRO_ESP_SOLUCION_TA IS NOT NULL OR \n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR TIPO_SENTENCIA_TA IS NOT NULL OR \n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR FORMA_SOLUCION_AP IS NOT NULL OR OTRO_ESP_SOLUCION_AP IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR MONTO_SOLUCION_AP IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCIÓN_AJ IS NOT NULL ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE INCOMPETENCIA=1";      
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//Favor de verificar Incompetencia ya que contiene dato apartir de Tipo de incompetencia (TIPO_INCOMPETENCIA)
public ArrayList PivIncompetencia_Noindentificado(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA from TR_FED_INDIVIDUAL\n" +
"WHERE TIPO_INCOMPETENCIA IS NOT NULL OR OTRO_ESP_INCOMP IS NOT NULL OR FECHA_PRES_DEMANDA IS NOT NULL OR CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR CONSTANCIA_CLAVE IS NOT NULL OR \n" +
"ASUN_EXCEP_CONCILIACION IS NOT NULL OR PREVE_DEMANDA IS NOT NULL OR DESAHOGO_PREV_DEMANDA IS NOT NULL OR \n" +
"ESTATUS_DEMANDA IS NOT NULL OR CAU_IMPI_ADMI_DEMANDA IS NOT NULL OR FECHA_ADMI_DEMANDA IS NOT NULL OR \n" +
"CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR TRAMITACION_DEPURACION IS NOT NULL OR \n" +
"FECHA_DEPURACION IS NOT NULL OR AUDIENCIA_PRELIM IS NOT NULL OR FECHA_AUDIENCIA_PRELIM IS NOT NULL OR AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR FECHA_ACTO_PROCESAL IS NOT NULL OR \n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_AD IS NOT NULL OR OTRO_ESP_SOLUCION_AD IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR TIPO_SENTENCIA_AD IS NOT NULL OR MONTO_SOLUCION_AD IS NOT NULL OR \n" +
"FORMA_SOLUCION_TA IS NOT NULL OR OTRO_ESP_SOLUCION_TA IS NOT NULL OR \n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR TIPO_SENTENCIA_TA IS NOT NULL OR \n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR FORMA_SOLUCION_AP IS NOT NULL OR OTRO_ESP_SOLUCION_AP IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR MONTO_SOLUCION_AP IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCIÓN_AJ IS NOT NULL ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE INCOMPETENCIA=9";      
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA")
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
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA FROM TR_FED_INDIVIDUAL WHERE \n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR\n" +
"CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"TRAMITACION_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR\n" +
"AUDIENCIA_PRELIM IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR\n" +
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
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL\n" +
") where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE ESTATUS_DEMANDA IN (2,3,4) ";     
      //System.out.println(sql);
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




//ESTATUS DEMANDA =DESECHADA,ARCHIVO, NO SE DIO TRAMITE A LA DEMANDA APARTIR DE FECHA_ADMI_DEMANDA NO DEBEN DE CONTESTAR
public ArrayList Estatus_Demanda_Noidentificada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA FROM TR_FED_INDIVIDUAL WHERE \n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR\n" +
"CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"TRAMITACION_DEPURACION IS NOT NULL OR\n" +
"FECHA_DEPURACION IS NOT NULL OR\n" +
"AUDIENCIA_PRELIM IS NOT NULL OR\n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR\n" +
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
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL\n" +
") where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE ESTATUS_DEMANDA IN (9) ";     
      //System.out.println(sql);
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL\n" +
") where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE ESTATUS_EXPEDIENTE=2";      
//System.out.println(sql);
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
public ArrayList Estatus_Expediente_Noidentificado(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR\n" +
"FORMA_SOLUCION_AD IS NOT NULL OR  FECHA_ACTO_PROCESAL IS NOT NULL OR  \n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL\n" +
") where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE ESTATUS_EXPEDIENTE=9";      
//System.out.println(sql);
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

//cuando el estatus del expediente es = solucionado no debe de haber nada en fecha del ultimo acto procesal
public ArrayList Fecha_acto_procesal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,FECHA_ACTO_PROCESAL\n" +
"from TR_FED_INDIVIDUAL WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ";      
//System.out.println(sql);
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
"FROM TR_FED_INDIVIDUAL P, TC_FED_FASE_EXPEDIENTE S\n" +
"WHERE  P.FASE_SOLI_EXPEDIENTE=S.ID\n" +
"AND FASE_SOLI_EXPEDIENTE NOT IN(1,2,3,4,99) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  )"; 
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
      sql="    SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(FASE_SOLI_EXPEDIENTE,'99','No identificado')FASE_SOLI_EXPEDIENTE FROM TR_FED_INDIVIDUAL \n" +
"WHERE FASE_SOLI_EXPEDIENTE='99' AND (FORMA_SOLUCION_AD IS NOT NULL OR OTRO_ESP_SOLUCION_AD IS NOT NULL OR FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR \n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR MONTO_SOLUCION_AD IS NOT NULL OR FORMA_SOLUCION_TA IS NOT NULL OR OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR TIPO_SENTENCIA_TA IS NOT NULL OR MONTO_SOLUCIÓN_TA IS NOT NULL OR FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR MONTO_SOLUCION_AP IS NOT NULL OR FORMA_SOLUCION_AJ IS NOT NULL OR \n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR TIPO_SENTENCIA_AJ IS NOT NULL OR MONTO_SOLUCIÓN_AJ IS NOT NULL) and \n"
              + "  (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  )" +
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

//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=Tramitación por auto de depuración
public ArrayList Fase_Sol_exp_TD(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE \n" +
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (3)"; 
//System.out.println(sql);
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


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=Tramitación sin audiencias
public ArrayList Fase_Sol_exp_TA(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n"
              + "SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE \n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (4)"; 
//System.out.println(sql);
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


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA PRELIMINAR
public ArrayList Fase_Sol_exp_AP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n"
              + "SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE \n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AJ IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AJ IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AJ IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AJ IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_AJ IS NOT NULL ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )WHERE FASE_SOLI_EXPEDIENTE IN (1)"; 
//System.out.println(sql);
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

//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA DE JUICIO
public ArrayList Fase_Sol_exp_AJ(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n"
              + "SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_INDIVIDUAL WHERE \n" +
"FORMA_SOLUCION_AD IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AD IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AD IS NOT NULL OR\n" +
"TIPO_SENTENCIA_AD IS NOT NULL OR\n" +
"MONTO_SOLUCION_AD IS NOT NULL OR\n" +
"FORMA_SOLUCION_TA IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_TA IS NOT NULL OR\n" +
"FECHA_RESOLUCION_TA IS NOT NULL OR\n" +
"TIPO_SENTENCIA_TA IS NOT NULL OR\n" +
"MONTO_SOLUCIÓN_TA IS NOT NULL OR\n" +
"FORMA_SOLUCION_AP IS NOT NULL OR\n" +
"OTRO_ESP_SOLUCION_AP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCION_AP IS NOT NULL OR\n" +
"MONTO_SOLUCION_AP IS NOT NULL )where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (2)";
     //System.out.println(sql);
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
"    TR_FED_individual\n" +
"WHERE\n" +
"     indole_trabajo = 2\n" +
"    AND prestacion_fp = 2\n" +
"    AND arrendam_trab = 2\n" +
"    AND capacitacion = 2\n" +
"    AND antiguedad = 2\n" +
"    AND prima_antiguedad = 2\n" +
"    AND convenio_trab = 2\n" +
"    AND designacion_trab_falle = 2\n" +
"    AND designacion_trab_act_delic = 2\n" +
"    AND terminacion_lab = 2\n" +
"    AND recuperacion_carga = 2\n" +
"    AND gastos_traslados = 2\n" +
"    AND indemnizacion = 2\n" +
"    AND pago_indemnizacion = 2\n" +
"    AND desacuerdo_medicos = 2\n" +
"    AND cobro_prestaciones = 2\n" +
"    AND conf_seguro_social = 2\n" +
"    AND otro_conf = 2 and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )"; 
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

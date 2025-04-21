/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.NotNull;


import Conexion.OracleConexionFederal;
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
public class V3Colect_EconomFederal {
    
    OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;






///--Tipo de asunto no debe de ser =9 No_identificado o Null.
public ArrayList Tipo_Asunto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'9','No_identificado') TIPO_ASUNTO from TR_FED_COLECT_ECONOM\n" +
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


///----cuando Suspensión temporal de las relaciones colectivas de trabajo=No no debe capturarse a partir de Exceso de producción con relación a sus condiciones económica hasta Falta de fondos y la imposibilidad de obtenerlos para la prosecución normal de los trabajos
public ArrayList Suspencion_Temporal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(SUSPENSION_TEMPORAL,'2','No') SUSPENSION_TEMPORAL FROM TR_FED_COLECT_ECONOM \n" +
"WHERE (SUSPENSION_TEMPORAL=2 and ( EXCESO_PRODUCCION IS NOT NULL OR INCOSTEABILIDAD IS NOT NULL OR FALTA_FONDOS IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("SUSPENSION_TEMPORAL")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//Estatus de la demanda no debe de ser 9=No_identificado.
public ArrayList Estatus_demandaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'9','No_identificado') ESTATUS_DEMANDA,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_COLECT_ECONOM\n" +
"WHERE (ESTATUS_DEMANDA =9 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS"),
                  resul.getString("ESTATUS_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//-Estatus del expediente no debe de ser 9=No_identificado.
public ArrayList Estatus_expedienteNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,'9','No_identificado') ESTATUS_EXPEDIENTE,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_COLECT_ECONOM\n" +
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_COLECT_ECONOM\n" +
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA FROM TR_FED_COLECT_ECONOM WHERE INCOMPETENCIA=2)\n" +
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
      sql="SELECT * FROM(\n  "
              + "SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA from TR_FED_COLECT_ECONOM\n" +
"WHERE FECHA_PRES_DEMANDA IS NOT NULL OR CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR CONSTANCIA_CLAVE IS NOT NULL OR \n" +
"ASUN_EXCEP_CONCILIACION IS NOT NULL OR PREVE_DEMANDA IS NOT NULL OR DESAHOGO_PREV_DEMANDA IS NOT NULL OR \n" +
"ESTATUS_DEMANDA IS NOT NULL OR FECHA_ADMISION_DEMANDA IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"AUDIENCIA_ECONOM IS NOT NULL OR FECHA_AUDIENCIA_ECONOM IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION IS NOT NULL OR ESPECIFIQUE_FORMA IS NOT NULL OR FECHA_RESOLUCION IS NOT NULL OR\n" +
"TIPO_SENTENCIA IS NOT NULL OR AUMENTO_PERSONAL IS NOT NULL OR DISMINUCION_PERSONAL IS NOT NULL OR AUMENTO_JORNADALAB IS NOT NULL OR\n" +
"DISMINUCION_JORNADALAB IS NOT NULL OR AUMENTO_SEMANA IS NOT NULL OR DISMINUCION_SEMANA IS NOT NULL OR AUMENTO_SALARIOS IS NOT NULL OR\n" +
"DISMINUCION_SALARIOS IS NOT NULL OR OTRO_TIPO IS NOT NULL OR ESPECIFIQUE_TIPO IS NOT NULL OR COMENTARIOS IS NOT NULL  )  where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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


//ESTATUS DEMANDA =DESECHADA,ARCHIVO, NO SE DIO TRAMITE A LA DEMANDA APARTIR DE FECHA_ADMI_DEMANDA NO DEBEN DE CONTESTAR
public ArrayList Estatus_Demanda_Desechada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA FROM TR_FED_COLECT_ECONOM WHERE \n" +
"FECHA_ADMISION_DEMANDA IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR AUDIENCIA_ECONOM IS NOT NULL OR FECHA_AUDIENCIA_ECONOM IS NOT NULL OR\n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION IS NOT NULL OR\n" +
"ESPECIFIQUE_FORMA IS NOT NULL OR FECHA_RESOLUCION IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR AUMENTO_PERSONAL IS NOT NULL OR \n" +
"DISMINUCION_PERSONAL IS NOT NULL OR AUMENTO_JORNADALAB IS NOT NULL OR DISMINUCION_JORNADALAB IS NOT NULL OR AUMENTO_SEMANA IS NOT NULL OR \n" +
"DISMINUCION_SEMANA IS NOT NULL OR AUMENTO_SALARIOS IS NOT NULL OR DISMINUCION_SALARIOS IS NOT NULL OR OTRO_TIPO IS NOT NULL OR ESPECIFIQUE_TIPO IS NOT NULL   ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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


//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Estatus_Expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE FROM TR_FED_COLECT_ECONOM WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION IS NOT NULL OR \n" +
"ESPECIFIQUE_FORMA IS NOT NULL OR FECHA_RESOLUCION IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR AUMENTO_PERSONAL IS NOT NULL OR\n" +
"DISMINUCION_PERSONAL IS NOT NULL OR AUMENTO_JORNADALAB IS NOT NULL OR DISMINUCION_JORNADALAB IS NOT NULL OR \n" +
"AUMENTO_SEMANA IS NOT NULL OR DISMINUCION_SEMANA IS NOT NULL OR AUMENTO_SALARIOS IS NOT NULL OR DISMINUCION_SALARIOS IS NOT NULL OR\n" +
"OTRO_TIPO IS NOT NULL OR ESPECIFIQUE_TIPO IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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

//cuando el estatus del expediente es = solucionado no debe de haber nada en fecha del ultimo acto procesal
public ArrayList Fecha_acto_procesal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,FECHA_ACTO_PROCESAL\n" +
"from TR_FED_COLECT_ECONOM WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
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
"FROM TR_FED_COLECT_ECONOM P, TC_FED_FASE_EXPEDIENTE S\n" +
"WHERE  P.FASE_SOLI_EXPEDIENTE=S.ID\n" +
"AND FASE_SOLI_EXPEDIENTE NOT IN(8,99) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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

//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=Tramitación por auto de depuración
public ArrayList Forma_Solucion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FORMA_SOLUCION  FROM TR_FED_COLECT_ECONOM WHERE \n" +
"TIPO_SENTENCIA IS NOT NULL OR AUMENTO_PERSONAL IS NOT NULL OR DISMINUCION_PERSONAL IS NOT NULL OR AUMENTO_JORNADALAB IS NOT NULL OR\n" +
"DISMINUCION_JORNADALAB IS NOT NULL OR AUMENTO_SEMANA IS NOT NULL OR DISMINUCION_SEMANA IS NOT NULL OR AUMENTO_SALARIOS IS NOT NULL OR\n" +
"DISMINUCION_SALARIOS IS NOT NULL OR OTRO_TIPO IS NOT NULL OR ESPECIFIQUE_TIPO IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) WHERE FORMA_SOLUCION IN (2,3,4,5)"; 
//System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FORMA_SOLUCION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

}

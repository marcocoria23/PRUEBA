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
import mx.org.querys.V1querys;

/**
 *
 * @author ANTONIO.CORIA
 */
public class V3Ordinario {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;







///Tipo de asunto no debe de ser =9 No_identificado
public ArrayList Tipo_asuntoNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,decode(TIPO_ASUNTO,'9','No_identificado')TIPO_ASUNTO,PERIODO from V3_TR_ORDINARIOJL\n" +
"WHERE ((TIPO_ASUNTO ='9' OR TIPO_ASUNTO IS NULL)  AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')) "; 
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


///--Cuando Tipo de asunto = Colectivo no debe de capturar ¿El trabajador contó con contrato escrito? ni Tipo de contrato
public ArrayList Tipo_asunto_ColectNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'2','Colectivo') TIPO_ASUNTO,PERIODO FROM V3_TR_ORDINARIOJL \n" +
"WHERE (TIPO_ASUNTO='2' AND ( CONTRATO_ESCRITO IS NOT NULL OR  TIPO_CONTRATO IS NOT NULL ) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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



///--Cuando ¿El trabajador contó con contrato escrito? = No o No identificado, no debe de capturar tipo de contrato
public ArrayList Contrato_escritoNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CONTRATO_ESCRITO,'2','No','9','No identificado') CONTRATO_ESCRITO,PERIODO FROM V3_TR_ORDINARIOJL \n" +
"WHERE (TIPO_ASUNTO='1' AND CONTRATO_ESCRITO IN ('2','9') and ( TIPO_CONTRATO IS NOT NULL ) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CONTRATO_ESCRITO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///--Cuando Tipo de asunto=Colectivo no debe capturarse desde ¿El conflicto laboral se encuentra vinculado al esquema de subcontratación (outsourcing)? hasta  Especifique (otro tipo de prestaciones)
public ArrayList Ta_Colectivo(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'2','Colectivo') CONTRATO_ESCRITO,PERIODO  FROM V3_TR_ORDINARIOJL \n" +
"WHERE (TIPO_ASUNTO='2' and ( SUBCONTRATACION IS NOT NULL OR DESPIDO IS NOT NULL OR RESCISION_RL IS NOT NULL OR TERMINACION_RESCISION_RL IS NOT NULL OR VIOLACION_CONTRATO IS NOT NULL OR\n" +
"RIESGO_TRABAJO IS NOT NULL OR REVISION_CONTRATO IS NOT NULL OR PART_UTILIDADES IS NOT NULL OR OTRO_MOTIV_CONFLICTO IS NOT NULL OR OTRO_ESP_CONFLICTO IS NOT NULL OR CIRCUNS_MOTIVO_CONFL IS NOT NULL OR \n" +
"DETERM_EMPLEO_EMBARAZO IS NOT NULL OR DETERM_EMPLEO_EDAD IS NOT NULL OR DETERM_EMPLEO_GENERO IS NOT NULL OR DETERM_EMPLEO_ORIEN_SEX IS NOT NULL OR DETERM_EMPLEO_DISCAPACIDAD IS NOT NULL OR DETERM_EMPLEO_SOCIAL\n" +
"IS NOT NULL OR DETERM_EMPLEO_ORIGEN IS NOT NULL OR DETERM_EMPLEO_RELIGION IS NOT NULL OR DETERM_EMPLEO_MIGRA IS NOT NULL OR  OTRO_DISCRIMINACION IS NOT NULL OR  OTRO_ESP_DISCRIMI IS NOT NULL OR  TRATA_LABORAL IS NOT NULL OR  TRABAJO_FORZOSO IS NOT NULL OR  TRABAJO_INFANTIL IS NOT NULL OR  HOSTIGAMIENTO IS NOT NULL OR  ACOSO_SEXUAL IS NOT NULL OR  PAGO_PRESTACIONES IS NOT NULL OR  INDEMNIZACION IS NOT NULL OR  REINSTALACION IS NOT NULL OR  SALARIO_RETENIDO IS NOT NULL OR  AUMENTO_SALARIO IS NOT NULL OR  DERECHO_ASCENSO IS NOT NULL OR  DERECHO_PREFERENCIA IS NOT NULL OR  DERECHO_ANTIGUEDAD IS NOT NULL OR  OTRO_CONCEPTO IS NOT NULL OR \n" +
"OTRO_ESP_RECLAMADO IS NOT NULL OR  AGUINALDO IS NOT NULL OR  VACACIONES IS NOT NULL OR  PRIMA_VACACIONAL IS NOT NULL OR  PRIMA_ANTIGUEDAD IS NOT NULL OR  OTRO_TIPO_PREST IS NOT NULL OR  OTRO_ESP_PRESTAC IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CONTRATO_ESCRITO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




///--cuando ¿Aplican circunstancias vinculadas al motivo del conflicto? = No o No identificado, no debe capturarse a partir Discriminación en el empleo y ocupación por embarazo hasta Acoso sexual
public ArrayList Mot_conflicto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CIRCUNS_MOTIVO_CONFL,'2','No','9','No identificado') CIRCUNS_MOTIVO_CONFL,PERIODO FROM V3_TR_ORDINARIOJL \n" +
"WHERE (CIRCUNS_MOTIVO_CONFL in ('2','9') and TIPO_ASUNTO='1' and ( DETERM_EMPLEO_EMBARAZO IS NOT NULL OR DETERM_EMPLEO_EDAD IS NOT NULL OR DETERM_EMPLEO_GENERO IS NOT NULL OR DETERM_EMPLEO_ORIEN_SEX IS NOT NULL OR\n" +
"DETERM_EMPLEO_DISCAPACIDAD IS NOT NULL OR DETERM_EMPLEO_SOCIAL\n" +
"IS NOT NULL OR DETERM_EMPLEO_ORIGEN IS NOT NULL OR DETERM_EMPLEO_RELIGION IS NOT NULL OR DETERM_EMPLEO_MIGRA IS NOT NULL OR \n" +
"OTRO_DISCRIMINACION IS NOT NULL OR  OTRO_ESP_DISCRIMI IS NOT NULL OR  TRATA_LABORAL IS NOT NULL OR  TRABAJO_FORZOSO IS NOT NULL OR  TRABAJO_INFANTIL IS NOT NULL OR \n" +
"HOSTIGAMIENTO IS NOT NULL OR  ACOSO_SEXUAL IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CIRCUNS_MOTIVO_CONFL"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//Cuando Pago de prestaciones=No o no identificado, No debe capturarse desde Aguinaldo hasta   Especifique (otro tipo de prestaciones).uando ¿Aplican circunstancias vinculadas al motivo del conflicto? = No no debe capturarse a partir Discriminación en el empleo y ocupación por embarazo hasta Acoso sexual
public ArrayList Pago_prestaciones(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(PAGO_PRESTACIONES,'2','No','9','No identificado') PAGO_PRESTACIONES,PERIODO FROM V3_TR_ORDINARIOJL \n" +
"WHERE (PAGO_PRESTACIONES in ('2','9') and TIPO_ASUNTO='1' and ( AGUINALDO IS NOT NULL OR  VACACIONES IS NOT NULL OR  PRIMA_VACACIONAL IS NOT NULL OR  PRIMA_ANTIGUEDAD IS NOT NULL OR  OTRO_TIPO_PREST IS NOT NULL OR  OTRO_ESP_PRESTAC IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("PAGO_PRESTACIONES"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//Cuando ¿Se anexó constancia de no conciliación expedida por el Centro Conciliación?=NO o No identificado, no debe de capturar Clave/identificador de la constancia
public ArrayList Cons_expedida(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(CONSTANCIA_CONS_EXPEDIDA,'2','No','9','No identificado') CONSTANCIA_CONS_EXPEDIDA,PERIODO FROM V3_TR_ORDINARIOJL\n" +
"WHERE (CONSTANCIA_CONS_EXPEDIDA in ('2','9') AND incompetencia='2' and  (CONSTANCIA_CLAVE IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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


//Cuando ¿Se formuló prevención a la demanda?=NO o No identificado, no debe de capturar ¿Se desahogó la prevención de la demanda?
public ArrayList Preve_demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(PREVE_DEMANDA,'2','No','9','No identificado') PREVE_DEMANDA,PERIODO FROM V3_TR_ORDINARIOJL\n" +
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






//Cuando ¿Se anexó constancia de no conciliación expedida por el Centro Conciliación?=NO no debe de capturar Clave/identificador de la constancia
public ArrayList Estatus_demaNi(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'9','No_identificado') ESTATUS_DEMANDA,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_ORDINARIOJL\n" +
"WHERE (ESTATUS_DEMANDA ='9' AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
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


//-Cuando Estatus de la demanda =Admitida o No identificado , debe de capturarse Causas que impiden la admisión de la demanda
public ArrayList Impiden_admision_demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_DEMANDA,'1','Admitida','9','No identificado') ESTATUS_DEMANDA,PERIODO FROM V3_TR_ORDINARIOJL\n" +
"WHERE (ESTATUS_DEMANDA IN ('1','9') AND INCOMPETENCIA='2' AND (CAU_IMP_ADM_DEMANDA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_DEMANDA"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//Cuando ¿Hubo celebración de audiencia preliminar?=NO o No identificado, no debe de capturar Fecha de audiencia preliminar.
public ArrayList audiencia_preliminar(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_PRELIM,'2','No','9','No identificado') AUDIENCIA_PRELIM,PERIODO FROM V3_TR_ORDINARIOJL\n" +
"WHERE (AUDIENCIA_PRELIM in ('2','9') AND incompetencia='2' and  (FECHA_AUDIENCIA_PRELIM IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUDIENCIA_PRELIM"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//Cuando ¿Hubo celebración de audiencia de juicio?=NO o No identificado, no debe de capturar Fecha de audiencia de juicio
public ArrayList audiencia_juicio(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_JUICIO,'2','No','9','No identificado') AUDIENCIA_JUICIO,PERIODO FROM V3_TR_ORDINARIOJL\n" +
"WHERE (AUDIENCIA_JUICIO in ('2','9') AND incompetencia='2' and  (FECHA_AUDIENCIA_JUICIO IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUDIENCIA_JUICIO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




//-Estatus del expediente no debe de ser 9=No_identificado.
public ArrayList Estatus_ExpedienteNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select  CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,'9','No_identificado') ESTATUS_EXPEDIENTE,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_ORDINARIOJL\n" +
"WHERE ((ESTATUS_EXPEDIENTE ='9' OR  ESTATUS_EXPEDIENTE IS NULL )AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_ORDINARIOJL\n" +
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA,PERIODO FROM V3_TR_ORDINARIOJL WHERE INCOMPETENCIA='2')\n" +
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
"SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO from V3_TR_ORDINARIOJL\n" +
"WHERE  FECHA_PRES_DEMANDA IS NOT NULL OR \n" +
"CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR  CONSTANCIA_CLAVE IS NOT NULL\n" +
"OR ASUN_EXCEP_CONCILIACION IS NOT NULL OR \n" +
"PREVE_DEMANDA IS NOT NULL OR\n" +
"DESAHOGO_PREV_DEMANDA IS NOT NULL OR\n" +
"ESTATUS_DEMANDA IS NOT NULL OR\n" +
"CAU_IMP_ADM_DEMANDA IS NOT NULL OR\n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR  CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR  AUDIENCIA_PRELIM IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR  AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR  FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )WHERE INCOMPETENCIA='1'"; 
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


//Favor de verificar Incompetencia ya que contiene dato apartir de Tipo de incompetencia (TIPO_INCOMPETENCIA)
public ArrayList PivIncompetencia_Noidentificado(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS,PERIODO from V3_TR_ORDINARIOJL\n" +
"WHERE TIPO_INCOMPETENCIA IS NOT NULL OR OTRO_ESP_INCOMP IS NOT NULL OR  FECHA_PRES_DEMANDA IS NOT NULL OR \n" +
"CONSTANCIA_CONS_EXPEDIDA IS NOT NULL OR  CONSTANCIA_CLAVE IS NOT NULL\n" +
"OR ASUN_EXCEP_CONCILIACION IS NOT NULL OR \n" +
"PREVE_DEMANDA IS NOT NULL OR\n" +
"DESAHOGO_PREV_DEMANDA IS NOT NULL OR\n" +
"ESTATUS_DEMANDA IS NOT NULL OR\n" +
"CAU_IMP_ADM_DEMANDA IS NOT NULL OR\n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR  CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR  AUDIENCIA_PRELIM IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR  AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR  FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )WHERE INCOMPETENCIA='9' OR INCOMPETENCIA IS NULL"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
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



//ESTATUS DEMANDA =DESECHADA,ARCHIVO, NO SE DIO TRAMITE A LA DEMANDA APARTIR DE FECHA_ADMI_DEMANDA NO DEBEN DE CONTESTAR
public ArrayList Estatus_Demanda_Desechada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM( SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA,PERIODO FROM V3_TR_ORDINARIOJL WHERE \n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR  CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR  AUDIENCIA_PRELIM IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR  AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR  FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL)where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )WHERE ESTATUS_DEMANDA IN ('2','3','4')"; 
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




//Favor de Verificar Estatus_demanda ya que contiene informacion a apartir de Fecha de Admision de la demanda (FECHA_ADMI_DEMANDA).
public ArrayList Estatus_Demanda_NoIdentificada(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM( SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_DEMANDA,PERIODO FROM V3_TR_ORDINARIOJL WHERE \n" +
"FECHA_ADMI_DEMANDA IS NOT NULL OR  CANTIDAD_ACTORES IS NOT NULL OR\n" +
"CANTIDAD_DEMANDADOS IS NOT NULL OR  AUDIENCIA_PRELIM IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_PRELIM IS NOT NULL OR  AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"FECHA_AUDIENCIA_JUICIO IS NOT NULL OR \n" +
"ESTATUS_EXPEDIENTE IS NOT NULL OR  FECHA_ACTO_PROCESAL IS NOT NULL OR\n" +
"FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL)where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )WHERE ESTATUS_DEMANDA IN ('9') OR ESTATUS_DEMANDA IS NULL"; 
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
public ArrayList Estatus_Expediente_Noidentificado(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR \n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE ESTATUS_EXPEDIENTE='9' OR ESTATUS_EXPEDIENTE IS NULL";      
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
"from V3_TR_ORDINARIOJL WHERE ESTATUS_EXPEDIENTE='1' AND\n" +
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
"FROM V3_TR_ORDINARIOJL P, V3_TC_FASE_EXPEDIENTEJL S\n" +
"WHERE  P.FASE_SOLI_EXPEDIENTE=S.ID\n" +
"AND FASE_SOLI_EXPEDIENTE NOT IN('1','2','9','99') AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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

//Cuando Fase de solicitud del expediente (FASE_SOLI_EXPEDIENTE) =No identificado, no debe capturar desde Forma de solución (FORMA_SOLUCIONFE) hasta Monto estipulado en la forma de solución (MONTO_SOLUCIONAJ).
public ArrayList Fase_Sol_expNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(FASE_SOLI_EXPEDIENTE,'99','No identificado')FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL \n" +
"WHERE FASE_SOLI_EXPEDIENTE='99' AND (FORMA_SOLUCIONFE IS NOT NULL OR OTRO_ESP_SOLUCIONFE IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAP IS NOT NULL OR OTRO_ESP_SOLUCIONAP IS NOT NULL OR FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR MONTO_SOLUCION_AP\n" +
"IS NOT NULL OR FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) and \n"
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



//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=FASE ESCRITA NO DEBE DE CAPTURAR APARTIR DE SOLUCION(AUDIENCIA PRELIMINAR) SOLUCION (AUDIENCIA DE JUICIO)
public ArrayList Fase_Sol_exp_FE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL WHERE \n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR \n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN ('9') OR FASE_SOLI_EXPEDIENTE IS NULL \n" +
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





//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA PRELIMINAR NO DEBE DE CAPTURAR   SOLUCION(FASE ESCRITA) SOLUCION (AUDIENCIA DE JUICIO)
public ArrayList Fase_Sol_exp_AP(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL WHERE \n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL OR\n" +
"FORMA_SOLUCIONAJ IS NOT NULL OR OTRO_ESP_SOLUCIONAJ IS NOT NULL OR FECHA_RESOLUCIONAJ IS NOT NULL OR \n" +
"TIPO_SENTENCIAAJ IS NOT NULL OR MONTO_SOLUCIONAJ IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN ('1')"; 
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


//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=AUDIENCIA JUICIO NO DEBE DE CAPTURAR   SOLUCION(FASE ESCRITA) SOLUCION (AUDIENCIA PRELIMINAR)
public ArrayList Fase_Sol_exp_AJ(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V3_TR_ORDINARIOJL WHERE \n" +
"FORMA_SOLUCIONAP IS NOT NULL OR  OTRO_ESP_SOLUCIONAP IS NOT NULL OR\n" +
"FECHA_DICTO_RESOLUCIONAP IS NOT NULL OR  MONTO_SOLUCION_AP IS NOT NULL OR\n" +
"FORMA_SOLUCIONFE IS NOT NULL OR  OTRO_ESP_SOLUCIONFE IS NOT NULL OR \n" +
"FECHA_DICTO_RESOLUCIONFE IS NOT NULL OR MONTO_SOLUCION_FE IS NOT NULL ) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
"    V3_tr_ordinarioJL\n" +
"WHERE\n" +
"    despido = '2'\n" +
"    AND rescision_rl = '2'\n" +
"    AND terminacion_rescision_rl = '2'\n" +
"    AND violacion_contrato = '2'\n" +
"    AND riesgo_trabajo = '2'\n" +
"    AND revision_contrato = '2'\n" +
"    AND part_utilidades = '2'\n" +
"    AND otro_motiv_conflicto = '2'\n" +
"    AND circuns_motivo_confl = '1'  and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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

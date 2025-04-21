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

/**
 *
 * @author ANTONIO.CORIA
 */
public class V3HuelgaFederal {
OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;






//Tipo de asunto no debe de ser =9 No_identificado o Null.
public ArrayList Tipo_Asunto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_ASUNTO,'9','No_identificado') TIPO_ASUNTO from TR_FED_HUELGA\n" +
"WHERE (TIPO_ASUNTO =9 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
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


////Cuando ¿Hubo emplazamiento a huelga? =No o No identificado, no debe de capturarse Fecha del emplazamiento a huelga.
public ArrayList Emplaz_huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(EMPLAZAMIENTO_HUELGA,'2','No','9','No identificado') EMPLAZAMIENTO_HUELGA FROM TR_FED_HUELGA \n" +
"WHERE (EMPLAZAMIENTO_HUELGA in (2,9)  AND (FECHA_EMPLAZAMIENTO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("EMPLAZAMIENTO_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



////Cuando ¿Hubo prehuelga? =No o No identificado, no debe de capturarse ¿Hubo audiencia de conciliación? ni Fecha de audiencia de conciliación.
public ArrayList Preghuelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(PREHUELGA,'2','No','9','No identificado') PREHUELGA FROM TR_FED_HUELGA \n" +
"WHERE (PREHUELGA in (2,9)  AND (AUDIENCIA_CONCILIACION IS NOT NULL or FECHA_AUDIENCIA is not null) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("PREHUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


////Cuando ¿Hubo audiencia de conciliación? =No o No identificado, no debe de capturarse Fecha de audiencia de conciliación.
public ArrayList Aud_conciliacion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(AUDIENCIA_CONCILIACION,'2','No','9','No identificado') AUDIENCIA_CONCILIACION FROM TR_FED_HUELGA \n" +
"WHERE (PREHUELGA in (1)  AND (AUDIENCIA_CONCILIACION in (2,9) and  FECHA_AUDIENCIA is not null) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("AUDIENCIA_CONCILIACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

////Cuando ¿Hubo estallamiento de la huelga?=No o No identificado, no debe de capturarse Declaración de licitud de la huelga,Declaración de existencia de la huelga.
public ArrayList Estallamiento_huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTALLAMIENTO_HUELGA,'2','No','9','No identificado') ESTALLAMIENTO_HUELGA FROM TR_FED_HUELGA \n" +
"WHERE (ESTALLAMIENTO_HUELGA in (2,9)  AND (DECLARA_LICITUD_HUELGA IS NOT NULL or DECLARA_EXISTEN_HUELGA is not null) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTALLAMIENTO_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//--Estatus del expediente no debe de ser 9=No_identificado.
public ArrayList Estatus_ExpedienteNI (){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,'9','No_identificado') ESTATUS_EXPEDIENTE,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_HUELGA\n" +
"WHERE (ESTATUS_EXPEDIENTE =9 AND(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,REPLACE(COMENTARIOS,',','')COMENTARIOS from TR_FED_HUELGA\n" +
" where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )where incompetencia=9"; 
      System.out.println(sql);
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA FROM TR_FED_HUELGA WHERE INCOMPETENCIA=2)\n" +
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA from TR_FED_HUELGA\n" +
"WHERE  FECHA_PRESENTA_PETIC IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"EMPLAZAMIENTO_HUELGA IS NOT NULL OR FECHA_EMPLAZAMIENTO IS NOT NULL OR PREHUELGA IS NOT NULL OR\n" +
"AUDIENCIA_CONCILIACION IS NOT NULL OR FECHA_AUDIENCIA IS NOT NULL OR ESTALLAMIENTO_HUELGA IS NOT NULL OR\n" +
"DECLARA_LICITUD_HUELGA IS NOT NULL OR DECLARA_EXISTEN_HUELGA IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL OR FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  )WHERE INCOMPETENCIA=1"; 
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




//Favor de verificar
public ArrayList PivIncompetencia_Noidentificado(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA from TR_FED_HUELGA\n" +
"WHERE  FECHA_PRESENTA_PETIC IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"EMPLAZAMIENTO_HUELGA IS NOT NULL OR FECHA_EMPLAZAMIENTO IS NOT NULL OR PREHUELGA IS NOT NULL OR\n" +
"AUDIENCIA_CONCILIACION IS NOT NULL OR FECHA_AUDIENCIA IS NOT NULL OR ESTALLAMIENTO_HUELGA IS NOT NULL OR\n" +
"DECLARA_LICITUD_HUELGA IS NOT NULL OR DECLARA_EXISTEN_HUELGA IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL OR FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  )WHERE INCOMPETENCIA=9"; 
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








//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Estatus_Expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE FROM TR_FED_HUELGA WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL OR FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ) WHERE ESTATUS_EXPEDIENTE=2";      
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,FECHA_ACTO_PROCESAL\n" +
"from TR_FED_HUELGA WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ";      
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
"FROM TR_FED_HUELGA P, TC_FED_FASE_EXPEDIENTE S\n" +
"WHERE  P.FASE_SOLI_EXPEDIENTE=S.ID\n" +
"AND FASE_SOLI_EXPEDIENTE NOT IN(5,6,7,99) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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


//Cuando Fase de solicitud del expediente (FASE_SOLI_EXPEDIENTE) =No identificado, no debe capturar desde Forma de solución (FORMA_SOLUCION_EMPLAZ) hasta Porcentaje por salarios caídos (SALARIOS_CAIDOS).
public ArrayList Fase_Sol_expNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(FASE_SOLI_EXPEDIENTE,'99','No identificado')FASE_SOLI_EXPEDIENTE FROM TR_FED_HUELGA \n" +
"WHERE FASE_SOLI_EXPEDIENTE='99' AND (FORMA_SOLUCION_EMPLAZ IS NOT NULL OR  ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR  FECHA_RESOLU_EMPLAZ IS NOT NULL OR  \n" +
"INCREMENTO_SOLICITADO IS NOT NULL OR  INCREMENTO_OTORGADO IS NOT NULL OR  FORMA_SOLUCION_HUELGA IS NOT NULL OR  ESPECIFI_FORMA_HUELGA IS NOT NULL OR  \n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR  TIPO_SENTENCIA IS NOT NULL OR  FECHA_ESTALLAM_HUELGA IS NOT NULL OR \n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR  MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) and \n"
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

//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=EMPLAZAMIENTO A HUELGA NO DEBE DE CAPTURAR RUBRO Solución (Huelga)
public ArrayList Fase_Sol_exp_EMPH(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_HUELGA WHERE \n" +
"FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ) WHERE FASE_SOLI_EXPEDIENTE IN (5,6)\n" +
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





//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=Huelga  NO DEBE DE CAPTURAR   Solución (Emplazamiento/Prehuelga)
public ArrayList Fase_Sol_exp_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE FROM TR_FED_HUELGA WHERE \n" +
"FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ) WHERE FASE_SOLI_EXPEDIENTE IN (7)"; 
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


//---Cuando el expediente No se admitio a tramite la fase de solicitud debe ser emplazamiento a huelga o prehuelga.
public ArrayList Fase_Sol_Desechamiento(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT unique(CLAVE_ORGANO||EXPEDIENTE_CLAVE) unique_code,CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,FORMA_SOLUCION_HUELGA, ESPECIFI_FORMA_HUELGA, COMENTARIOS FROM(\n" +
"SELECT P1.CLAVE_ORGANO,P1.EXPEDIENTE_CLAVE,DECODE(P1.FASE_SOLI_EXPEDIENTE,'1','Audiencia preliminar','2','Audiencia de juicio','3','Tramitación por auto de depuración','4','Tramitación sin audiencias','5','Emplazamiento a huelga','6','Prehuelga','7','Huelga','8','Audiencia dentro del procedimiento colectivo de naturaleza económica','9','Fase escrita','99','No identificado') FASE_SOLI_EXPEDIENTE\n" +
",DECODE(P1.FORMA_SOLUCION_HUELGA,'1','Sentencia','2','Convenio conciliatorio','3','Desistimiento','4','Caducidad','5','Otra forma de solución (especifique)','9','No Identificado') FORMA_SOLUCION_HUELGA,P1.ESPECIFI_FORMA_HUELGA,P1.COMENTARIOS,P2.DESCRIPCION DES_FASE,P3.DESCRIPCION DES_FORMA_SOLUCION,P4.DESCRIPCION DES_ESPECIFIQUE,P5.DESCRIPCION DES_COMENTARIOS\n" +
"FROM \n" +
"TR_FED_HUELGA P1,TC_RALAB_FASEJL P2,TC_FED_FASE_EXPEDIENTE PP2,TC_RALAB_FORMA_SOLUCIONJL P3,TC_FED_FORMA_SOLUCION PP3,TC_RALAB_ESPECIFIQUEJL P4, TC_RALAB_COMENTARIOSJL P5)\n" +
"WHERE TRIM(UPPER(FASE_SOLI_EXPEDIENTE)) = TRIM(UPPER(DES_FASE)) AND TRIM(UPPER(FORMA_SOLUCION_HUELGA))=TRIM(UPPER(DES_FORMA_SOLUCION)) AND TRIM(UPPER(ESPECIFI_FORMA_HUELGA)) LIKE TRIM(UPPER(DES_ESPECIFIQUE))\n" +
"OR TRIM(UPPER(COMENTARIOS)) LIKE TRIM(UPPER(DES_COMENTARIOS))) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  "; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE"),
                  resul.getString("FORMA_SOLUCION_HUELGA"),
                   resul.getString("ESPECIFI_FORMA_HUELGA"),
                   resul.getString("COMENTARIOS")
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
"    TR_FED_HUELGA\n" +
"WHERE\n" +
"     firma_contrato = 2\n" +
"     AND revision_contrato = 2\n" +
"     AND incumplim_contrato = 2\n" +
"     AND revision_salario = 2\n" +
"     AND reparto_utilidades = 2\n" +
"     AND apoyo_otra_huelga = 2\n" +
"     AND desequilibrio_fac_prod = 2\n" +
"     AND otro_motivo = 2 and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
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
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
public class V2Huelga {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;





///INCOMPETENCIA NO DEBE SER = NO IDENTIFICADA
public ArrayList IncompetenciaNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO from V2_TR_HUELGAJL\n" +
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(INCOMPETENCIA,'2','No') INCOMPETENCIA,TIPO_INCOMPETENCIA,PERIODO FROM V2_TR_HUELGAJL WHERE INCOMPETENCIA=2)\n" +
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
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,INCOMPETENCIA,PERIODO from V2_TR_HUELGAJL\n" +
"WHERE  FECHA_PRESENTA_PETIC IS NOT NULL OR CANTIDAD_ACTORES IS NOT NULL OR CANTIDAD_DEMANDADOS IS NOT NULL OR\n" +
"EMPLAZAMIENTO_HUELGA IS NOT NULL OR FECHA_EMPLAZAMIENTO IS NOT NULL OR PREHUELGA IS NOT NULL OR\n" +
"AUDIENCIA_CONCILIACION IS NOT NULL OR FECHA_AUDIENCIA IS NOT NULL OR ESTALLAMIENTO_HUELGA IS NOT NULL OR\n" +
"DECLARA_LICITUD_HUELGA IS NOT NULL OR DECLARA_EXISTEN_HUELGA IS NOT NULL OR ESTATUS_EXPEDIENTE IS NOT NULL OR\n" +
"FECHA_ACTO_PROCESAL IS NOT NULL OR FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL OR FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' )WHERE INCOMPETENCIA=1"; 
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


//ESTATUS DEL EXPEDIENTE = EN PROCESO DE RESOLUCION APARTIR DE FORMA_SOLUCIONFE NO DEBE DE CONTESTARSE
public ArrayList Estatus_Expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ESTATUS_EXPEDIENTE,PERIODO FROM V2_TR_HUELGAJL WHERE FASE_SOLI_EXPEDIENTE IS NOT NULL OR FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL OR FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
"from V2_TR_HUELGAJL WHERE ESTATUS_EXPEDIENTE=1 AND\n" +
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




//---CUANDO FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE=EMPLAZAMIENTO A HUELGA NO DEBE DE CAPTURAR RUBRO Solución (Huelga)
public ArrayList Fase_Sol_exp_EMPH(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
"SELECT * FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_HUELGAJL WHERE \n" +
"FORMA_SOLUCION_HUELGA IS NOT NULL OR ESPECIFI_FORMA_HUELGA IS NOT NULL OR\n" +
"FECHA_RESOLU_HUELGA IS NOT NULL OR TIPO_SENTENCIA IS NOT NULL OR FECHA_ESTALLAM_HUELGA IS NOT NULL OR\n" +
"FECHA_LEVANT_HUELGA IS NOT NULL OR   MONTO_ESTIPULADO IS NOT NULL OR SALARIOS_CAIDOS IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (5,6)\n" +
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
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,FASE_SOLI_EXPEDIENTE,PERIODO FROM V2_TR_HUELGAJL WHERE \n" +
"FORMA_SOLUCION_EMPLAZ IS NOT NULL OR\n" +
"ESPECIFI_FORMA_EMPLAZ IS NOT NULL OR FECHA_RESOLU_EMPLAZ IS NOT NULL OR INCREMENTO_SOLICITADO IS NOT NULL OR\n" +
"INCREMENTO_OTORGADO IS NOT NULL) where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"' ) WHERE FASE_SOLI_EXPEDIENTE IN (7)"; 
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
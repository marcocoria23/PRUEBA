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
public class V3Part_Dem_colectivoFederal {
   
 OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;



///Demandado no debe de ser 9=No identificado
public ArrayList DemandadoNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT  CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'9','No identificado') DEMANDADO  FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (9)) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//Cuando Demandado=otro no debe de capturar desde Nombre del sindicato hasta longitud
public ArrayList Demandado_Otro(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'5','Otro') DEMANDADO  FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (5)  AND (NOMBRE_SINDICATO_DEM IS NOT NULL OR  REG_ASOC_SINDICAL_DEM IS NOT NULL OR TIPO_SINDICATO_DEM IS NOT NULL OR\n" +
" OTRO_ESP_SINDICATO_DEM IS NOT NULL OR ORG_OBRERA_DEM IS NOT NULL OR\n" +
" NOMBRE_ORG_OBRERA_DEM IS NOT NULL OR OTRO_ESP_OBRERA_DEM IS NOT NULL OR  HOMBRES_DEM IS NOT NULL OR\n" +
" MUJERES_DEM IS NOT NULL OR NO_IDENTIFICADO_DEM IS NOT NULL OR TIPO_DEM_PAT IS NOT NULL OR\n" +
" RFC_PATRON_DEM IS NOT NULL OR RAZON_SOCIAL_EMPR_DEM IS NOT NULL OR \n" +
" CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR \n" +
" CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR\n" +
" MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 



//Cuando Demandado=Sindicato no debe de capturar desde tipo hasta longitud
public ArrayList Demandado_Sindicato(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'2','Sindicato') DEMANDADO FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (2)  AND ( TIPO_DEM_PAT IS NOT NULL OR\n" +
" RFC_PATRON_DEM IS NOT NULL OR RAZON_SOCIAL_EMPR_DEM IS NOT NULL OR \n" +
" CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR \n" +
" CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR\n" +
" MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 




//Cuando Demandado=Coalicion_de_trabajadores solo debe  capturar Cantidad de trabajadores involucrados,Hombres,Mujeres,No_identificado      
public ArrayList Demandado_Coalicion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'3','Coalicion_de_trabajadores') DEMANDADO FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (3)  AND ( NOMBRE_SINDICATO_DEM IS NOT NULL OR  REG_ASOC_SINDICAL_DEM IS NOT NULL OR TIPO_SINDICATO_DEM IS NOT NULL OR\n" +
" OTRO_ESP_SINDICATO_DEM IS NOT NULL OR ORG_OBRERA_DEM IS NOT NULL OR\n" +
" NOMBRE_ORG_OBRERA_DEM IS NOT NULL OR OTRO_ESP_OBRERA_DEM IS NOT NULL OR TIPO_DEM_PAT IS NOT NULL OR\n" +
" RFC_PATRON_DEM IS NOT NULL OR RAZON_SOCIAL_EMPR_DEM IS NOT NULL OR \n" +
" CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR \n" +
" CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR\n" +
" MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 



//Cuando Demandado=Patron no debe de capturar desde Nombre del sindicato hasta No_identificado    
public ArrayList Demandado_Patron(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'1','Patron') DEMANDADO FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (1)  AND ( NOMBRE_SINDICATO_DEM IS NOT NULL OR  REG_ASOC_SINDICAL_DEM IS NOT NULL OR TIPO_SINDICATO_DEM IS NOT NULL OR\n" +
" OTRO_ESP_SINDICATO_DEM IS NOT NULL OR ORG_OBRERA_DEM IS NOT NULL OR\n" +
" NOMBRE_ORG_OBRERA_DEM IS NOT NULL OR OTRO_ESP_OBRERA_DEM IS NOT NULL OR  HOMBRES_DEM IS NOT NULL OR\n" +
" MUJERES_DEM IS NOT NULL OR NO_IDENTIFICADO_DEM IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }




//Cuando Demandado=Patron y tipo=persona_Fisica no debe de capturar desde Razon social hasta Longitud 
public ArrayList persona_fisica(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_SINDICATO_DEM,'1','persona_Fisica') TIPO_SINDICATO_DEM FROM TR_FED_PART_DEM_COLECTIVO\n" +
"WHERE (DEMANDADO IN (1) AND TIPO_SINDICATO_DEM=1  AND (RAZON_SOCIAL_EMPR_DEM IS NOT NULL OR \n" +
" CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR \n" +
" CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR\n" +
" MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL ) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_SINDICATO_DEM")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



}

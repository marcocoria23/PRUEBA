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
public class V3Part_Dem_ordinario {
     OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;





///Demandado no debe de ser 9=No identificado
public ArrayList DemandadoNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT  CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'9','No identificado') DEMANDADO,PERIODO  FROM V3_TR_PART_DEM_ORDINARIOJL\n" +
"WHERE (DEMANDADO IN ('9') or DEMANDADO is null) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



///Cuando Demandado=otro no debe de capturar desde tipo hasta longitud
public ArrayList Demandado_Otro(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT  CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'5','Otro') DEMANDADO,PERIODO  FROM V3_TR_PART_DEM_ORDINARIOJL\n" +
"WHERE (DEMANDADO IN ('5')  AND (TIPO IS NOT NULL OR RFC_PATRON IS NOT NULL OR RAZON_SOCIAL_EMPR IS NOT NULL OR\n" +
"CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR \n" +
"COLONIA IS NOT NULL OR CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR\n" +
"MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR \n" +
"MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR\n" +
"IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("DEMANDADO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//Cuando Demandado=otro no debe de capturar desde tipo hasta longitud
public ArrayList Tipo_pf(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO,'1','persona_Fisica') TIPO,PERIODO  FROM V3_TR_PART_DEM_ORDINARIOJL\n" +
"WHERE (DEMANDADO IN ('1','9') and TIPO IN ('1')  AND (RAZON_SOCIAL_EMPR IS NOT NULL OR\n" +
"CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR \n" +
"COLONIA IS NOT NULL OR CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR\n" +
"MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR \n" +
"MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR\n" +
"IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }





}

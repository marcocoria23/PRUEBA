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
public class V3Part_Dem_huelgaFederal {
      OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;



///Demandado no debe de ser 9=No identificado
public ArrayList DemandadoNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT  CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(DEMANDADO,'9','No identificado') DEMANDADO  FROM TR_FED_PART_DEM_HUELGA\n" +
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


//Cuando Tipo=Persona fisica no debe de capturar desde Razon social hasta longitud
public ArrayList Persona_fisica(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(tipo,'1','Persona_fisica') tipo FROM TR_FED_PART_DEM_HUELGA\n" +
"WHERE (DEMANDADO IN (1) and tipo in (1) AND (RAZON_SOCIAL_EMPR IS NOT NULL OR\n" +
"CALLE IS NOT NULL OR N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR CP IS NOT NULL OR\n" +
"ENTIDAD_NOMBRE_EMPR IS NOT NULL OR ENTIDAD_CLAVE_EMPR IS NOT NULL OR \n" +
"MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR MUNICIPIO_CLAVE_EMPR IS NOT NULL OR LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("tipo")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


}

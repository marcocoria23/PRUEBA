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
public class V3Part_Act_colectivo {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;


///Actor no debe ser No identificado
public ArrayList ActorNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'99','No identificado') Actor,PERIODO FROM V3_TR_PART_ACT_COLECTIVOJL\n" +
"WHERE (ACTOR IN ('99') or ACTOR is null ) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("Actor"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//CUANDO ACTOR = Sindicato  no debe capturar desde Tipo hasta Longitud
public ArrayList Actor_Sindicato(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ACTOR,'3','Sindicato') ACTOR,PERIODO FROM V3_TR_PART_ACT_COLECTIVOJL\n" +
"WHERE (ACTOR IN ('3')  AND (TIPO IS NOT NULL OR RFC_PATRON IS NOT NULL OR RAZON_SOCIAL_EMPR IS NOT NULL OR CALLE IS NOT NULL OR\n" +
"N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR\n" +
"ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR MUNICIPIO_CLAVE_EMPR IS NOT NULL OR \n" +
"LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ACTOR"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



//CUANDO ACTOR = Patron no debe de capturar desde Nombre del sindicato hasta No_identificado.
public ArrayList Actor_Patron(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ACTOR,'2','Patron') ACTOR,PERIODO FROM V3_TR_PART_ACT_COLECTIVOJL\n" +
"WHERE (ACTOR IN ('2')  AND (NOMBRE_SINDICATO IS NOT NULL OR  REG_ASOC_SINDICAL IS NOT NULL OR TIPO_SINDICATO IS NOT NULL OR\n" +
"OTRO_ESP_SINDICATO IS NOT NULL OR ORG_OBRERA IS NOT NULL OR NOMBRE_ORG_OBRERA IS NOT NULL OR OTRO_ESP_OBRERA IS NOT NULL OR \n" +
"HOMBRES IS NOT NULL OR MUJERES IS NOT NULL OR NO_IDENTIFICADO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ACTOR")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


//CUANDO Tipo= persona_Fisica  no debe de capturar desde Razon social hasta Longitud.
public ArrayList Persona_Fisica(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO,'1','Persona_fisica') TIPO,PERIODO FROM V3_TR_PART_ACT_COLECTIVOJL\n" +
"WHERE (ACTOR IN ('2') AND TIPO='1'   AND (RAZON_SOCIAL_EMPR IS NOT NULL OR CALLE IS NOT NULL OR\n" +
"N_EXT IS NOT NULL OR N_INT IS NOT NULL OR COLONIA IS NOT NULL OR CP IS NOT NULL OR ENTIDAD_NOMBRE_EMPR IS NOT NULL OR\n" +
"ENTIDAD_CLAVE_EMPR IS NOT NULL OR MUNICIPIO_NOMBRE_EMPR IS NOT NULL OR MUNICIPIO_CLAVE_EMPR IS NOT NULL OR\n" +
"LATITUD_EMPR IS NOT NULL OR LONGITUD_EMPR IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO"),
                  resul.getString("ACTOR")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
    



    
}

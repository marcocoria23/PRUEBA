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
public class V3Part_Act_huelga {
    OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;



///Actor no debe ser No identificado
public ArrayList ActorNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'99','No identificado') Actor,PERIODO FROM V3_TR_PART_ACT_HUELGAJL\n" +
"WHERE (ACTOR IN ('99') or ACTOR is null) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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


//CUANDO ACTOR = Otro  no debe capturar desde Nombre del sindicato hasta No_identificado.
public ArrayList Actor_Otro(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ACTOR,'7','Otro') ACTOR,PERIODO FROM V3_TR_PART_ACT_HUELGAJL\n" +
"WHERE (ACTOR IN ('7')  AND (NOMBRE_SINDICATO IS NOT NULL OR REG_ASOC_SINDICAL IS NOT NULL OR TIPO_SINDICATO IS NOT NULL OR\n" +
"OTRO_ESP_SINDICATO IS NOT NULL OR ORG_OBRERA IS NOT NULL OR NOMBRE_ORG_OBRERA IS NOT NULL OR\n" +
"OTRO_ESP_OBRERA IS NOT NULL OR  HOMBRES IS NOT NULL OR MUJERES IS NOT NULL OR NO_IDENTIFICADO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')) "; 
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



//-CUANDO ACTOR = Mayoria_de_Trabajadores  no debe capturar desde Nombre del sindicato hasta Especifique (otra organización obrera)
public ArrayList Actor_Mayoria(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(ACTOR,'5','Mayoria_de_Trabajadores') ACTOR,PERIODO FROM V3_TR_PART_ACT_HUELGAJL\n" +
"WHERE (ACTOR IN ('5')  AND (NOMBRE_SINDICATO IS NOT NULL OR REG_ASOC_SINDICAL IS NOT NULL OR TIPO_SINDICATO IS NOT NULL OR\n" +
"OTRO_ESP_SINDICATO IS NOT NULL OR ORG_OBRERA IS NOT NULL OR NOMBRE_ORG_OBRERA IS NOT NULL OR\n" +
"OTRO_ESP_OBRERA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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





}

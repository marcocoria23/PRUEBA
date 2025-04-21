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
public class V3Part_Act_ordinario {
 OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;


///Actor no debe ser No identificado
public ArrayList ActorNI(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'99','No identificado') Actor,PERIODO FROM V3_TR_PART_ACT_ORDINARIOJL\n" +
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




///CUANDO ACTOR = Trabajador  no debe capturar desde Nombre del sindicato hasta No_identificado.
public ArrayList Actor_Trabajador(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'1','Trabajador') Actor,PERIODO FROM V3_TR_PART_ACT_ORDINARIOJL\n" +
"WHERE (ACTOR IN ('1')  AND (NOMBRE_SINDICATO IS NOT NULL OR REG_ASOC_SINDICAL IS NOT NULL OR\n" +
"TIPO_SINDICATO IS NOT NULL OR OTRO_ESP_SINDICATO IS NOT NULL OR ORG_OBRERA IS NOT NULL OR\n" +
"NOMBRE_ORG_OBRERA IS NOT NULL OR OTRO_ESP_OBRERA IS NOT NULL  OR HOMBRES\n" +
"IS NOT NULL OR  MUJERES IS NOT NULL OR NO_IDENTIFICADO IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')) "; 
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

///CUANDO ACTOR = Trabajador  no debe capturar desde Nombre del sindicato hasta No_identificado.
public ArrayList Actor_Sindicato(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'3','sindicato') Actor,PERIODO  FROM V3_TR_PART_ACT_ORDINARIOJL\n" +
"WHERE (ACTOR IN ('3')  AND (SEXO IS NOT NULL OR  EDAD IS NOT NULL OR OCUPACION IS NOT NULL OR NSS IS NOT NULL OR\n" +
"CURP IS NOT NULL OR RFC_TRABAJADOR IS NOT NULL OR JORNADA IS NOT NULL)AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
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



///CUANDO ACTOR = Trabajador  no debe capturar desde Nombre del sindicato hasta No_identificado.
public ArrayList Actor_Coalicion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(Actor,'4','Coalicion_de_trabajadores') Actor,PERIODO FROM V3_TR_PART_ACT_ORDINARIOJL\n" +
"WHERE (ACTOR IN ('4')  AND (SEXO IS NOT NULL OR  EDAD IS NOT NULL OR OCUPACION IS NOT NULL OR NSS IS NOT NULL OR\n" +
"CURP IS NOT NULL OR RFC_TRABAJADOR IS NOT NULL OR JORNADA IS NOT NULL) AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'))"; 
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

}

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
public class V3Control_expediente {
     OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

///Horario debe de estar completo ejemplo: 08:30 a 16:30
public ArrayList Horario(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select  REPLACE(NOMBRE_ORGANO_JURIS,',','')NOMBRE_ORGANO_JURIS,CLAVE_ORGANO,HORARIO,PERIODO from V3_TR_CONTROL_EXPEDIENTEJL\n" +
"    where (LENGTH(TRIM(REPLACE(HORARIO,' ','')))<6)  AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("NOMBRE_ORGANO_JURIS"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("HORARIO"),
                  resul.getString("PERIODO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList SubJuecesHom(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,CASE WHEN SUMHOM IS NULL THEN 0 ELSE SUMHOM END SUMHOM,CASE WHEN JUECES_LABORAL_SUB_HOM IS NULL THEN 0 ELSE JUECES_LABORAL_SUB_HOM END JUECES_LABORAL_SUB_HOM,periodo FROM(\n" +
"SELECT  CLAVE_ORGANO, (TO_NUMBER(TRIM(JUECES_LABORAL_MIX_HOM))+TO_NUMBER(TRIM(JUECES_LABORAL_INDIV_HOM))+TO_NUMBER(TRIM(JUECES_LABORAL_COLEC_HOM)))  SUMHOM,\n" +
"JUECES_LABORAL_SUB_HOM,periodo \n" +
"FROM V3_TR_CONTROL_EXPEDIENTEJL)WHERE TO_NUMBER(SUMHOM)<>TO_NUMBER(JUECES_LABORAL_SUB_HOM)  AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SUMHOM"),
                  resul.getString("JUECES_LABORAL_SUB_HOM")
                  
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList SubJuecesMuj(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,CASE WHEN SUMMUJ IS NULL THEN 0 ELSE SUMMUJ END SUMMUJ,CASE WHEN JUECES_LABORAL_SUB_MUJ IS NULL THEN 0 ELSE JUECES_LABORAL_SUB_MUJ END JUECES_LABORAL_SUB_MUJ,periodo FROM(\n" +
"SELECT  CLAVE_ORGANO, (TO_NUMBER(TRIM(JUECES_LABORAL_MIX_MUJ))+TO_NUMBER(TRIM(JUECES_LABORAL_INDIV_MUJ))+TO_NUMBER(TRIM(JUECES_LABORAL_COLEC_MUJ)))  SUMMUJ,\n" +
"JUECES_LABORAL_SUB_MUJ,periodo\n" +
"FROM V3_TR_CONTROL_EXPEDIENTEJL)WHERE TO_NUMBER(SUMMUJ)<>TO_NUMBER(JUECES_LABORAL_SUB_MUJ)  AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SUMMUJ"),
                  resul.getString("JUECES_LABORAL_SUB_MUJ")
                  
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList TotalJueces(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,CASE WHEN SUMHM IS NULL THEN 0 ELSE SUMHM END SUMHM,CASE WHEN JUECES_LABORAL_TOTAL IS NULL THEN 0 ELSE JUECES_LABORAL_TOTAL END JUECES_LABORAL_TOTAL,periodo FROM(\n" +
"SELECT  CLAVE_ORGANO, (TO_NUMBER(TRIM(JUECES_LABORAL_SUB_HOM))+TO_NUMBER(TRIM(JUECES_LABORAL_SUB_MUJ))) SUMHM,\n" +
"JUECES_LABORAL_TOTAL,periodo\n" +
"FROM V3_TR_CONTROL_EXPEDIENTEJL)WHERE TO_NUMBER(SUMHM)<>TO_NUMBER(JUECES_LABORAL_TOTAL)  AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("SUMHM"),
                  resul.getString("JUECES_LABORAL_TOTAL")
                  
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



}

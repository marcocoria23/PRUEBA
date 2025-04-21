/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Screen_laborales.PValidacion;


/**
 *
 * @author ANTONIO.CORIA
 */
public class V1QDesgloses {

OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;    

// Diferencia entre el total de expedientes ordinario vs el desglose de expedientes en procedimiento ordinario.
public ArrayList Desglose_Ordinario(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,Ordinario,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_ordinariojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,ordinario order by clave_organo";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("Ordinario"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes individual vs el desglose de expedientes en procedimiento individual.
public ArrayList Desglose_Individual(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_INDIVI,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_individualjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,ESPECIAL_INDIVI order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("ESPECIAL_INDIVI"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes Colectivo vs el desglose de expedientes en procedimiento Colectivo.
public ArrayList Desglose_Colectivo(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_COLECT,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_colectivojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,ESPECIAL_COLECT order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("ESPECIAL_COLECT"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
// Diferencia entre el total de expedientes Huelga vs el desglose de expedientes en procedimiento Huelga.
public ArrayList Desglose_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,HUELGA,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_huelgajl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,HUELGA order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("HUELGA"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes SSocial vs el desglose de expedientes en procedimiento SSocial.
public ArrayList Desglose_Ssocial(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,INDIV_SEGURO_SOCIAL,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_ssocialjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,INDIV_SEGURO_SOCIAL order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("INDIV_SEGURO_SOCIAL"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes Colectivo Economico vs el desglose de expedientes en procedimiento Colectivo Economico.
  public ArrayList Desglose_Colec_Econom(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,COL_NATU_ECONOMICA,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_colec_economjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,COL_NATU_ECONOMICA order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("COL_NATU_ECONOMICA"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de paraprocesal vs el desglose de expedientes en procedimiento Paraprocesal.
  public ArrayList Desglose_Paraprocesal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PARAP_VOLUNTARIO,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_paraprocesaljl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,PARAP_VOLUNTARIO order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("PARAP_VOLUNTARIO"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de expedientes Tercerias vs el desglose de expedientes en procedimiento Tercerias.
  public ArrayList Desglose_Tercerias(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,TERCERIAS,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_terceriasjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,TERCERIAS order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("TERCERIAS"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de expedientes Preferencia Credito vs el desglose de expedientes en procedimiento preferencia credito.
  public ArrayList Desglose_Pref_Credito(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PREF_CREDITO,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_preferencia_creditojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,PREF_CREDITO order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("PREF_CREDITO"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
   // Diferencia entre el total de expedientes ejecucion vs el desglose de expedientes en procedimiento ejecucion.
  public ArrayList Desglose_Ejecucion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,EJECUCION,count(sec.expediente_clave) Total_expe from V1_TR_controljl PRIN \n" +
      "left join V1_TR_ejecucionjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,EJECUCION order by clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("EJECUCION"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
}

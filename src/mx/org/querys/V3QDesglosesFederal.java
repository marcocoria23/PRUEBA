/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexionFederal;
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
public class V3QDesglosesFederal {

OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;    

// Diferencia entre el total de expedientes ordinario vs el desglose de expedientes en procedimiento ordinario.
public ArrayList Desglose_OrdinarioNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,Ordinario,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_ordinario sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"' \n" +
      "group by prin.clave_organo,ordinario ORDER BY clave_organo";
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
public ArrayList Desglose_IndividualNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_INDIVI,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_individual sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,ESPECIAL_INDIVI ORDER BY clave_organo";
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

// Diferencia entre el total de expedientes colectivo vs el desglose de expedientes en procedimiento colectivo.
public ArrayList Desglose_ColectivoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_COLECT,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_colectivo sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,ESPECIAL_COLECT ORDER BY clave_organo";
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
 
// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Huelga.
public ArrayList Desglose_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,HUELGA,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_huelga sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,HUELGA ORDER BY clave_organo";
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

// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Colectivo econom.
  public ArrayList Desglose_Colec_EconomNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,COL_NATU_ECONOMICA,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_colect_econom sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,COL_NATU_ECONOMICA ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento paraprocesal.
  public ArrayList Desglose_ParaprocesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PARAP_VOLUNTARIO,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_paraprocesal sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,PARAP_VOLUNTARIO ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento tercerias.
  public ArrayList Desglose_TerceriasNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,TERCERIAS,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_tercerias sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,TERCERIAS ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento preferencia credito.
  public ArrayList Desglose_Pref_CreditoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PREF_CREDITO,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_pref_credito sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,PREF_CREDITO ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento ejecucion.
  public ArrayList Desglose_EjecucionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,EJECUCION,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_ejecucion sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   \n" +
      "group by prin.clave_organo,EJECUCION ORDER BY clave_organo";
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

  
  
  
  public ArrayList Desglose_OrdinarioNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,Ordinario,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_ordinario sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,ordinario ORDER BY clave_organo";
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
public ArrayList Desglose_IndividualNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_INDIVI,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_individual sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,ESPECIAL_INDIVI ORDER BY clave_organo";
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

// Diferencia entre el total de expedientes colectivo vs el desglose de expedientes en procedimiento colectivo.
public ArrayList Desglose_ColectivoNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_COLECT,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_colectivo sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,ESPECIAL_COLECT ORDER BY clave_organo";
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
 
// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Huelga.
public ArrayList Desglose_HuelgaNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,HUELGA,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_huelga sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,HUELGA ORDER BY clave_organo";
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

// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Colectivo econom.
  public ArrayList Desglose_Colec_EconomNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,COL_NATU_ECONOMICA,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_colect_econom sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,COL_NATU_ECONOMICA ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento paraprocesal.
  public ArrayList Desglose_ParaprocesalNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PARAP_VOLUNTARIO,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_paraprocesal sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,PARAP_VOLUNTARIO ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento tercerias.
  public ArrayList Desglose_TerceriasNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,TERCERIAS,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_tercerias sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,TERCERIAS ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento preferencia credito.
  public ArrayList Desglose_Pref_CreditoNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PREF_CREDITO,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_pref_credito sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,PREF_CREDITO ORDER BY clave_organo";
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
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento ejecucion.
  public ArrayList Desglose_EjecucionNEAnt(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,EJECUCION,count(sec.expediente_clave) Total_expe from tr_fed_control_expediente PRIN \n" +
      "left join tr_fed_ejecucion sec\n" +
      "on  Prin.clave_organo=sec.clave_organo " +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' or prin.clave_organo='"+PValidacion.clave_organo+"'  \n" +
      "group by prin.clave_organo,EJECUCION ORDER BY clave_organo";
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

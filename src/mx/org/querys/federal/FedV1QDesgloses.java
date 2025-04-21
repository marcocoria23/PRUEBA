/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.federal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class FedV1QDesgloses {

OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;    

// Diferencia entre el total de expedientes ordinario vs el desglose de expedientes en procedimiento ordinario.
public ArrayList Desglose_OrdinarioNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,Ordinario,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_ordinariojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes individual vs el desglose de expedientes en procedimiento individual.
public ArrayList Desglose_IndividualNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_INDIVI,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_individualjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

// Diferencia entre el total de expedientes colectivo vs el desglose de expedientes en procedimiento colectivo.
public ArrayList Desglose_ColectivoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,ESPECIAL_COLECT,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_colectivojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Huelga.
public ArrayList Desglose_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,HUELGA,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_huelgajl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList Desglose_SsocialNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,SEG_SOC,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_SEG_SOCIALJL sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
      "group by prin.clave_organo,SEG_SOC ORDER BY clave_organo";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("SEG_SOC"),
                  resul.getString("Total_expe")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }





// Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento Colectivo econom.
  public ArrayList Desglose_Colec_EconomNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,COL_NATU_ECONOMICA,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_colect_economjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento paraprocesal.
  public ArrayList Desglose_ParaprocesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PARAP_VOLUNTARIO,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_paraprocesaljl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento tercerias.
  public ArrayList Desglose_TerceriasNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,TERCERIAS,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_terceriasjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento preferencia credito.
  public ArrayList Desglose_Pref_CreditoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,PREF_CREDITO,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_pref_creditojl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  // Diferencia entre el total de expedientes huelga vs el desglose de expedientes en procedimiento ejecucion.
  public ArrayList Desglose_EjecucionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select prin.clave_organo clave_organo,EJECUCION,count(sec.expediente_clave) Total_expe from TR_FED_V1_control_expedientejl PRIN \n" +
      "left join TR_FED_V1_ejecucionjl sec\n" +
      "on  Prin.clave_organo=sec.clave_organo and prin.periodo=sec.periodo\n" +
      "where substr(prin.clave_organo,0,2)='"+PValidacion.clave_entidad+"' and prin.periodo='"+PValidacion.periodo+"'  or prin.clave_organo='"+PValidacion.clave_organo+"'   and prin.periodo='"+PValidacion.periodo+"' \n" +
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
            Logger.getLogger(FedV1QDesgloses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

    
  
  
  

}


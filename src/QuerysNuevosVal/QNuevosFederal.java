/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuerysNuevosVal;

import Conexion.OracleConexionFederal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.querys.V1querys;



public class QNuevosFederal {
OracleConexionFederal conexion = new OracleConexionFederal();
String sql,NMunicipio;
ArrayList<String[]> Array;
ResultSet resul;   


public ArrayList  OrdinarioEstatusFE(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS \n" +
"FROM TR_FED_ORDINARIO\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=9\n" +
"AND (FECHA_DICTO_RESOLUCIONFE IS NULL OR FECHA_DICTO_RESOLUCIONFE='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  

public ArrayList  OrdinarioEstatusAP(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT *\n" +
"FROM TR_FED_ORDINARIO\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=1\n" +
"AND (FECHA_DICTO_RESOLUCIONAP IS NULL OR FECHA_DICTO_RESOLUCIONAP='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList  OrdinarioEstatusAJ(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT *\n" +
"FROM TR_FED_ORDINARIO\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=2\n" +
"AND (FECHA_RESOLUCIONAJ IS NULL OR FECHA_DICTO_RESOLUCIONAP='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList  IndividualEstatusAD(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_INDIVIDUAL\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=3\n" +
"AND (FECHA_DICTO_RESOLUCION_AD IS NULL OR FECHA_DICTO_RESOLUCION_AD='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

  public ArrayList  IndividualEstatusTA(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_INDIVIDUAL\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=4\n" +
"AND (FECHA_RESOLUCION_TA IS NULL OR FECHA_RESOLUCION_TA='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList  IndividualEstatusAP(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_INDIVIDUAL\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=1\n" +
"AND (FECHA_DICTO_RESOLUCION_AP IS NULL OR FECHA_DICTO_RESOLUCION_AP='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

 
public ArrayList  IndividualEstatusAJ(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_INDIVIDUAL\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=2\n" +
"AND (FECHA_DICTO_RESOLUCION_AJ IS NULL OR FECHA_DICTO_RESOLUCION_AJ='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



public ArrayList  ColectivoEstatusAD(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_COLECTIVO\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=3\n" +
"AND (FECHA_DICTO_DEPURACION_AD IS NULL OR FECHA_DICTO_DEPURACION_AD='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList  ColectivoEstatusAJ(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_COLECTIVO\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=2\n" +
"AND (FECHA_RESOLUCION_AJ IS NULL OR FECHA_RESOLUCION_AJ='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



public ArrayList  HuelgaEstatusEH(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"    FROM TR_FED_HUELGA\n" +
"    WHERE ESTATUS_EXPEDIENTE=1\n" +
"    AND FASE_SOLI_EXPEDIENTE=5\n" +
"    AND (FECHA_RESOLU_EMPLAZ IS NULL OR FECHA_RESOLU_EMPLAZ='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList  HuelgaEstatusPH(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_HUELGA\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=6\n" +
"AND (FECHA_RESOLU_EMPLAZ IS NULL OR FECHA_RESOLU_EMPLAZ='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList  HuelgaEstatusH(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_HUELGA\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=7\n" +
"AND (FECHA_RESOLU_HUELGA IS NULL OR FECHA_RESOLU_HUELGA='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList  Colect_EconomEstatusCE(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_COLECT_ECONOM\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND FASE_SOLI_EXPEDIENTE=8\n" +
"AND (FECHA_RESOLUCION IS NULL OR FECHA_RESOLUCION='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



public ArrayList  ParaprocesalEstatus(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_PARAPROCESAL\n" +
"WHERE ESTATUS_EXPEDIENTE=1\n" +
"AND (FECHA_CONCLUSION_EXPE IS NULL OR FECHA_CONCLUSION_EXPE='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

public ArrayList  EjecucionEstatus(String claveorgano,String entidad,String periodo){
     conexion.Conectar();
      Array = new ArrayList();
      sql=" SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,COMENTARIOS\n" +
"FROM TR_FED_EJECUCION\n" +
"WHERE ESTATUS_EXPE=1\n" +
"AND (FECHA_CONCLUSION IS NULL OR FECHA_CONCLUSION='09/09/1899')\n" +
"AND ((CLAVE_ORGANO='"+claveorgano+"')OR (SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


}

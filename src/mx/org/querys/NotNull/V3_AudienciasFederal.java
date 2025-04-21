/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.NotNull;


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
public class V3_AudienciasFederal {
    
    OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

///Cuando Tipo de procedimiento =Ordinario no debe de capturar Especial individual,Especial colectivo,Huelga,Colectivo de naturaleza económica
public ArrayList Aud_Ordinario(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_PROCED,'1','Ordinario')TIPO_PROCED from TR_FED_AUDIENCIAS\n" +
"WHERE \n" +
"(\n" +
"ESPECIAL_INDIVI_TA IS NOT NULL OR\n" +
"ESPECIAL_COLECT_TA IS NOT NULL OR\n" +
"HUELGA_TA IS NOT NULL OR\n" +
"COL_NATU_ECONOMICA_TA IS NOT NULL\n" +
")AND TIPO_PROCED=1 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_PROCED")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
  ///Cuando Tipo de procedimiento =Individual no debe de capturar Ordinario,Especial colectivo,Huelga,Colectivo de naturaleza económica
public ArrayList Aud_Individual(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_PROCED,'2','INDIVIDUAL')TIPO_PROCED from TR_FED_AUDIENCIAS\n" +
"WHERE \n" +
"(\n" +
"ORDINARIO_TA IS NOT NULL OR\n" +
"ESPECIAL_COLECT_TA IS NOT NULL OR\n" +
"HUELGA_TA IS NOT NULL OR\n" +
"COL_NATU_ECONOMICA_TA IS NOT NULL\n" +
")AND TIPO_PROCED=2 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_PROCED")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  

///Cuando Tipo de procedimiento =Colectivo no debe de capturar Ordinario,Especial Individual,Huelga,Colectivo de naturaleza económica
public ArrayList Aud_Colectivo(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_PROCED,'3','Colectivo')TIPO_PROCED from TR_FED_AUDIENCIAS\n" +
"WHERE \n" +
"(\n" +
"ORDINARIO_TA IS NOT NULL OR\n" +
"ESPECIAL_INDIVI_TA IS NOT NULL OR\n" +
"HUELGA_TA IS NOT NULL OR\n" +
"COL_NATU_ECONOMICA_TA IS NOT NULL\n" +
")AND TIPO_PROCED=3 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_PROCED")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///Cuando Tipo de procedimiento =Huelga no debe de capturar Ordinario,Especial Individual,Colectivo,Colectivo de naturaleza económica
public ArrayList Aud_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_PROCED,'4','Huelga')TIPO_PROCED from TR_FED_AUDIENCIAS\n" +
"WHERE \n" +
"(\n" +
"ORDINARIO_TA IS NOT NULL OR\n" +
"ESPECIAL_INDIVI_TA IS NOT NULL OR\n" +
"ESPECIAL_COLECT_TA IS NOT NULL OR\n" +
"COL_NATU_ECONOMICA_TA IS NOT NULL\n" +
")AND TIPO_PROCED=4 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_PROCED")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_Colect_econom(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO,EXPEDIENTE_CLAVE,DECODE(TIPO_PROCED,'5','Colectivo Economico')TIPO_PROCED from TR_FED_AUDIENCIAS\n" +
"WHERE \n" +
"(\n" +
"ORDINARIO_TA IS NOT NULL OR\n" +
"ESPECIAL_INDIVI_TA IS NOT NULL OR\n" +
"ESPECIAL_COLECT_TA IS NOT NULL OR\n" +
"HUELGA_TA IS NOT NULL \n" +
")AND TIPO_PROCED=5 AND (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' ) "; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("TIPO_PROCED")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_NE_ORDINARIO(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ID_AUDIENCIA,EXPEDIENTE_CLAVE2 FROM(\n" +
"select S.CLAVE_ORGANO,S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE2\n" +
"from TR_FED_AUDIENCIAS S \n" +
"LEFT JOIN TR_FED_ORDINARIO P\n" +
"ON S.CLAVE_ORGANO=P.CLAVE_ORGANO\n" +
"AND S.EXPEDIENTE_CLAVE=P.EXPEDIENTE_CLAVE \n" +
"WHERE\n" +
"TIPO_PROCED=1\n" +
"GROUP BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE,S.CLAVE_ORGANO\n" +
"ORDER BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA) WHERE EXPEDIENTE_CLAVE2 IS NULL)WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ORDER BY CLAVE_ORGANO,EXPEDIENTE_CLAVE"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_NE_INDIVIDUAL(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ID_AUDIENCIA,EXPEDIENTE_CLAVE2 FROM(\n" +
"select S.CLAVE_ORGANO,S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE2\n" +
"from TR_FED_AUDIENCIAS S \n" +
"LEFT JOIN TR_FED_INDIVIDUAL P\n" +
"ON S.CLAVE_ORGANO=P.CLAVE_ORGANO\n" +
"AND S.EXPEDIENTE_CLAVE=P.EXPEDIENTE_CLAVE \n" +
"WHERE\n" +
"TIPO_PROCED=2\n" +
"GROUP BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE,S.CLAVE_ORGANO\n" +
"ORDER BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA) WHERE EXPEDIENTE_CLAVE2 IS NULL)WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ORDER BY CLAVE_ORGANO,EXPEDIENTE_CLAVE"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE")
                 
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_NE_COLECTIVO(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ID_AUDIENCIA,EXPEDIENTE_CLAVE2 FROM(\n" +
"select S.CLAVE_ORGANO,S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE2\n" +
"from TR_FED_AUDIENCIAS S \n" +
"LEFT JOIN TR_FED_COLECTIVO P\n" +
"ON S.CLAVE_ORGANO=P.CLAVE_ORGANO\n" +
"AND S.EXPEDIENTE_CLAVE=P.EXPEDIENTE_CLAVE \n" +
"WHERE\n" +
"TIPO_PROCED=3\n" +
"GROUP BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE,S.CLAVE_ORGANO\n" +
"ORDER BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA) WHERE EXPEDIENTE_CLAVE2 IS NULL)WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ORDER BY CLAVE_ORGANO,EXPEDIENTE_CLAVE"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE")
                  
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_NE_HUELGA(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ID_AUDIENCIA,EXPEDIENTE_CLAVE2 FROM(\n" +
"select S.CLAVE_ORGANO,S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE2\n" +
"from TR_FED_AUDIENCIAS S \n" +
"LEFT JOIN TR_FED_HUELGA P\n" +
"ON S.CLAVE_ORGANO=P.CLAVE_ORGANO\n" +
"AND S.EXPEDIENTE_CLAVE=P.EXPEDIENTE_CLAVE \n" +
"WHERE\n" +
"TIPO_PROCED=4\n" +
"GROUP BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE,S.CLAVE_ORGANO\n" +
"ORDER BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA) WHERE EXPEDIENTE_CLAVE2 IS NULL)WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ORDER BY CLAVE_ORGANO,EXPEDIENTE_CLAVE"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE")
                 
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


///Cuando Tipo de procedimiento =Colectivo Econom no debe de capturar Ordinario,Especial Individual,Colectivo,Huelga
public ArrayList Aud_NE_COLECT_ECONOM(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO FROM(\n" +
"SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,ID_AUDIENCIA,EXPEDIENTE_CLAVE2 FROM(\n" +
"select S.CLAVE_ORGANO,S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE2\n" +
"from TR_FED_AUDIENCIAS S \n" +
"LEFT JOIN TR_FED_COLECT_ECONOM P\n" +
"ON S.CLAVE_ORGANO=P.CLAVE_ORGANO\n" +
"AND S.EXPEDIENTE_CLAVE=P.EXPEDIENTE_CLAVE \n" +
"WHERE\n" +
"TIPO_PROCED=5\n" +
"GROUP BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA,P.EXPEDIENTE_CLAVE,S.CLAVE_ORGANO\n" +
"ORDER BY\n" +
"S.EXPEDIENTE_CLAVE,S.ID_AUDIENCIA) WHERE EXPEDIENTE_CLAVE2 IS NULL)WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"'  ORDER BY CLAVE_ORGANO,EXPEDIENTE_CLAVE"; 
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE")
                  
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V3Ordinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


}

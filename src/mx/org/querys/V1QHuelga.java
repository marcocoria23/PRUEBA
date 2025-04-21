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


public class V1QHuelga {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
 ResultSet resul;
 
  //Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
  public ArrayList Año_Expe_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V1_TR_huelgajl ) WHERE  FECHA_APERTURA <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURAS"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

  //Query de validacion donde la fecha de emplazamiento a huelga no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Emplazamiento(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY') FECHA_EMPLAZAMIENTO  ,periodo\n" +
          "from V1_TR_huelgajl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_EMPLAZAMIENTO <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_EMPLAZAMIENTO <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_EMPLAZAMIENTO,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_EMPLAZAMIENTO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de audiencia no debe de ser menor o igual a la fecha de apertura del expediente
public ArrayList Fecha_Audiencia(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_AUDIENCIA,'DD/MM/YYYY') FECHA_AUDIENCIA  ,periodo\n" +
          "from V1_TR_huelgajl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIENCIA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIENCIA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_AUDIENCIA,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_AUDIENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de reolucion a emplazamineto no debe de ser menor o igual a la fecha de apertura del expediente PUEDE SER MAYOR O IGUAL A LA FECHA DE APERTURA  CUANDO LA FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE NO SEA emplazamiento a huelga” o “prehuelga”  Y LA FORMA DE SOLUCION NO SEA NO DAR TRAMITE AL ESCRITO DE EMPLAZAMIENTO POR NO CUMPLIR CON LOS REQUISITOS ESTABLECIDOS
public ArrayList Fecha_Resolu_Emplaz(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_RESOLU_EMPLAZ,'DD/MM/YYYY') FECHA_RESOLU_EMPLAZ  ,periodo,SOLUCION_EXPEDIENTE,FORMA_SOLUCION_EMPLAZ\n" +
          "from V1_TR_huelgajl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_RESOLU_EMPLAZ <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLU_EMPLAZ <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_RESOLU_EMPLAZ,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy'))where SOLUCION_EXPEDIENTE not in (5,6) and FORMA_SOLUCION_EMPLAZ not in (1)  ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_RESOLU_EMPLAZ")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de resolucion  no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Resolu_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_RESOLU_HUELGA,'DD/MM/YYYY') FECHA_RESOLU_HUELGA  ,periodo\n" +
          "from V1_TR_huelgajl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_RESOLU_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLU_HUELGA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where  to_date(FECHA_RESOLU_HUELGA,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_RESOLU_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de estallamiento a huelga  no debe de ser menor o igual a la fecha de apertura del expediente
public ArrayList Fecha_Estallam_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_ESTALLAM_HUELGA,'DD/MM/YYYY') FECHA_ESTALLAM_HUELGA  ,periodo\n" +
          "from V1_TR_huelgajl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_ESTALLAM_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_ESTALLAM_HUELGA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_ESTALLAM_HUELGA,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_ESTALLAM_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de levantamiento  no debe de ser menor o igual a la fecha de apertura del expediente
public ArrayList Fecha_Levant_Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_LEVANT_HUELGA,'DD/MM/YYYY') FECHA_LEVANT_HUELGA  ,periodo\n" +
          "from V1_TR_huelgajl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_LEVANT_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_LEVANT_HUELGA <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_LEVANT_HUELGA,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_LEVANT_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

}

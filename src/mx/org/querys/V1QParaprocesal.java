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
public class V1QParaprocesal {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

//Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
public ArrayList Año_Expe_Paraprocesal(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V1_TR_paraprocesaljl ) WHERE  FECHA_APERTURA <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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

//Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Admi_Soli(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select ESTATUS_EXPEDIENTE,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_ADMISION_SOLI,'DD/MM/YYYY') FECHA_ADMISION_SOLI ,periodo\n" +
          "from V1_TR_paraprocesaljl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_ADMISION_SOLI <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_ADMISION_SOLI <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_ADMISION_SOLI,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_ADMISION_SOLI")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la fecha de resolucion del expediente no debe de ser menor a la fecha de apertura del expediente
public ArrayList Fecha_Resolucion_Expe(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select ESTATUS_EXPEDIENTE,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(FECHA_RESOLUCION_EXPE,'DD/MM/YYYY') FECHA_RESOLUCION_EXPE ,periodo\n" +
          "from V1_TR_paraprocesaljl where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_RESOLUCION_EXPE <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLUCION_EXPE <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where  to_date(FECHA_RESOLUCION_EXPE,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("FECHA_RESOLUCION_EXPE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


}

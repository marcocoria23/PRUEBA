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
 *res
 * @author ANTONIO.CORIA
 */
public class V1QColec_Econom {
OracleConexion conexion = new OracleConexion();
String sql;
ArrayList<String[]> Array;
ResultSet resul;
    
    //Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
    public ArrayList Año_Expe_ColecEconom(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA,'dd/mm/yyyy') FECHA_APERTURAS ,SUBSTR(TO_CHAR(fecha_apertura, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO,COMENTARIOS\n" +
        "from V1_TR_colec_economjl ) WHERE  (FECHA_APERTURA <>EXPE_AÑO) and SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURAS"),
                  resul.getString("COMENTARIOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

   //Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente
    public ArrayList Fecha_Admision_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select Id_entidad,clave_organo,expediente_clave,fecha_apertura,to_char(Fecha_Admision_Demanda,'DD/MM/YYYY') Fecha_Admision_Demanda ,periodo\n" +
          "from V1_TR_colec_economjl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Admision_Demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Admision_Demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where  to_date(Fecha_Admision_Demanda,'dd/mm/yyyy')  < to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("Id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("Fecha_Admision_Demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
    //Query de validacion donde la fecha de emplazamiento de la demanda no debe de ser menor o igual a la fecha de apertura del expediente
    public ArrayList Fecha_Emplazam_Demanda(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select Id_entidad,clave_organo,expediente_clave,to_char(fecha_apertura,'DD/MM/YYYY') fecha_apertura,to_char(Fecha_Emplazam_Demanda,'DD/MM/YYYY') Fecha_Emplazam_Demanda ,periodo\n" +
          "from V1_TR_colec_economjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Emplazam_Demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Emplazam_Demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where to_date(Fecha_Emplazam_Demanda,'dd/mm/yyyy')  <= to_date(fecha_apertura,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("Id_entidad"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("fecha_apertura"),
                  resul.getString("Fecha_Emplazam_Demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

}

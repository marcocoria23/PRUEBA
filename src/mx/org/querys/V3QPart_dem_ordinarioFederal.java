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
public class V3QPart_dem_ordinarioFederal {
  OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

//Query de validacion para saber si Actor y Demandado no se encuntra desagregados.
public ArrayList ExpeNDesglose(){
  conexion.Conectar();
    Array = new ArrayList();
    sql="select CLAVE_ORGANO, EXPEDIENTE_CLAVE, decode(INCOMPETENCIA,2,'NO') INCOMPETENCIA, DECODE(ESTATUS_DEMANDA,1,'ADMITIDA') ESTATUS_DEMANDA, \n" +
"CANTIDAD_ACTORES,CANTIDAD_DEMANDADOS\n" +
"from(\n" +
"select * from(\n" +
"select * from TR_FED_ordinario where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"and incompetencia=2 and estatus_demanda=1 OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
"and incompetencia=2 and estatus_demanda=1) where \n" +
" CANTIDAD_DEMANDADOS>0) where expediente_clave not in (select expediente_clave from TR_FED_part_dem_ordinario where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
" OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
")";
resul=conexion.consultar(sql);
    System.out.println(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
                  resul.getString("ESTATUS_DEMANDA"),
                  resul.getString("CANTIDAD_ACTORES"),
                  resul.getString("CANTIDAD_DEMANDADOS")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}

//Query de validacion para saber si Cuando el expediente es incompetencia = SI, Cantidad de Actores y Cantidad de demandados es No aplica por ende no se debe de desglosar actores ni demandados.
public ArrayList IncompetenciaNE(){
    conexion.Conectar();
    Array = new ArrayList();
sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_PART,SEC.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_ORDINARIO,decode(sec.incompetencia,'1','Sí','2','NO')incompetencia FROM(\n" +
"SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO\n" +
"FROM TR_FED_part_dem_ordinario\n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' )PRIN LEFT JOIN\n" +
"TR_FED_ORDINARIO SEC \n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE \n" +
") WHERE INCOMPETENCIA='Sí'";
    resul=conexion.consultar(sql);
    System.out.println(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE_ORDINARIO"),
                  resul.getString("INCOMPETENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}

//Query de validacion para saber si  el estatus de la demanda=DESECHADA,ARCHIVO,NO SE DIO TRAMITE AL ESCRITO DE DEMANDA. Cantidad de Actores y Cantidad de demandados es No aplica por ende no se debe de desglosar actores ni demandados.";
public ArrayList Estatus_demandaNE(){
    conexion.Conectar();
    Array = new ArrayList();
sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_PART,SEC.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_ORDINARIO,decode(sec.estatus_demanda,'1','Admitida','2','Desechada','3','Archivo','4','No se dio trámite al escrito de demanda') estatus_demanda FROM(\n" +
"SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO\n" +
"FROM TR_FED_part_dem_ordinario\n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' )PRIN LEFT JOIN\n" +
"TR_FED_ORDINARIO SEC \n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE \n" +
")where ESTATUS_DEMANDA IN ('Desechada','Archivo','No se dio trámite al escrito de demanda')";
    resul=conexion.consultar(sql);
 System.out.println(sql);     
try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE_ORDINARIO"),
                  resul.getString("ESTATUS_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}

//Query de validacion para saber si La cantidad de Demandados  es diferente a el desglose de Demandados.
public ArrayList Dif_demandadosNE(){
    conexion.Conectar();
    Array = new ArrayList();
    sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE,\n" +
"CASE WHEN sec.cantidad_demandados IS NULL THEN 0 ELSE sec.cantidad_demandados END cantidad_demandados,\n" +
"CASE WHEN PRIN.DESGLOSE_DEMANDADO IS NULL THEN 0 ELSE PRIN.DESGLOSE_DEMANDADO END DESGLOSE_DEMANDADO,\n" +
"CASE WHEN sec.incompetencia IS NULL THEN 'NULLO' ELSE TO_CHAR(sec.incompetencia) END incompetencia,"
+ "CASE WHEN sec.estatus_demanda IS NULL THEN 'NULLO' ELSE TO_CHAR(sec.estatus_demanda) END estatus_demanda\n" +
"FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,COUNT(ID_DEMANDADO) DESGLOSE_DEMANDADO\n" +
"FROM TR_FED_part_dem_ordinario \n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND ID_DEMANDADO NOT LIKE '%-%'\n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND ID_DEMANDADO NOT LIKE '%-%'\n" +
"GROUP BY CLAVE_ORGANO,EXPEDIENTE_CLAVE)PRIN LEFT JOIN TR_FED_ORDINARIO SEC\n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE  \n" +
") WHERE INCOMPETENCIA<>'1' AND ESTATUS_DEMANDA NOT IN ('2','3','4')\n" +
"AND cantidad_demandados<>DESGLOSE_DEMANDADO";
resul=conexion.consultar(sql);
 System.out.println(sql);    
try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CANTIDAD_DEMANDADOS"),
                  resul.getString("DESGLOSE_DEMANDADO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}

}

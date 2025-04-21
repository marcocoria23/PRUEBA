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


public class V3QPart_act_huelgaFederal {
OracleConexionFederal conexion = new OracleConexionFederal();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

//Query de validacion para saber si Actor y Demandado no se encuntra desagregados.
public ArrayList ExpeNDesglose(){
  conexion.Conectar();
    Array = new ArrayList();
    sql=" select * from ( \n" + 
            " select CLAVE_ORGANO, EXPEDIENTE_CLAVE, decode(INCOMPETENCIA,2,'NO') INCOMPETENCIA, \n" +
"CANTIDAD_ACTORES,CANTIDAD_DEMANDADOS,FASE_SOLI_EXPEDIENTE  \n" +
"from(\n" +
"select * from(\n" +
"select * from TR_FED_huelga where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
" and incompetencia=2  OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
" and incompetencia=2) where CANTIDAD_ACTORES>0\n" +
") where expediente_clave not in (select expediente_clave from TR_FED_part_act_HUELGA where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
"  OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
"))WHERE FASE_SOLI_EXPEDIENTE<>5";
resul=conexion.consultar(sql);
    System.out.println(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("INCOMPETENCIA"),
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
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_PART,SEC.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_HUELGA,decode(sec.incompetencia,'1','Sí','2','NO')incompetencia FROM(\n" +
"SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO\n" +
"FROM TR_FED_part_act_HUELGA\n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' )PRIN LEFT JOIN\n" +
"TR_FED_HUELGA SEC \n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE \n" +
") WHERE INCOMPETENCIA='Sí'";
resul=conexion.consultar(sql);
    System.out.println(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE_HUELGA"),
                  resul.getString("INCOMPETENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}


///Query de validacion para saber si La cantidad de Actores  es diferente a el desglose de Actores.
public ArrayList Dif_ActoresNE(){
    conexion.Conectar();
    Array = new ArrayList();
    sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE,\n" +
"CASE WHEN sec.cantidad_actores IS NULL THEN 0 ELSE sec.cantidad_actores END cantidad_actores,\n" +
"CASE WHEN PRIN.DESGLOSE_ACTOR IS NULL THEN 0 ELSE PRIN.DESGLOSE_ACTOR END DESGLOSE_ACTORES,\n" +
"CASE WHEN sec.incompetencia IS NULL THEN '0' ELSE TO_CHAR(sec.incompetencia) END incompetencia,"
+ "CASE WHEN sec.FASE_SOLI_EXPEDIENTE IS NULL THEN '0' ELSE TO_CHAR(sec.FASE_SOLI_EXPEDIENTE) END FASE_SOLI_EXPEDIENTE \n" +
"FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,COUNT(ID_ACTOR) DESGLOSE_ACTOR\n" +
"FROM TR_FED_part_act_HUELGA \n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND ID_ACTOR NOT LIKE '%-%'\n" +
" OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND ID_ACTOR NOT LIKE '%-%'\n" +
"\n" +
"GROUP BY CLAVE_ORGANO,EXPEDIENTE_CLAVE)PRIN LEFT JOIN TR_FED_HUELGA SEC\n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE  \n" +
") WHERE INCOMPETENCIA<>'1' AND FASE_SOLI_EXPEDIENTE <> '5'  \n" +
"AND CANTIDAD_ACTORES<>DESGLOSE_ACTORES";
resul=conexion.consultar(sql);
 System.out.println(sql);     
try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("CANTIDAD_ACTORES"),
                  resul.getString("DESGLOSE_ACTORES")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}


    
}

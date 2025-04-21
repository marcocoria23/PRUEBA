/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys.federal;


/**
 *
 * @author ANTONIO.CORIA
 */
import Conexion.OracleConexionFederal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Screen_laborales.PValidacion;

public class FedV1QPart_act_individual {
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
"select * from TR_FED_V1_INDIVIDUALjl where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"AND PERIODO ='"+PValidacion.periodo+"' and incompetencia=2 and estatus_demanda=1 OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
"AND PERIODO ='"+PValidacion.periodo+"' and incompetencia=2 and estatus_demanda=1) where CANTIDAD_ACTORES>0\n" +
"or CANTIDAD_DEMANDADOS>0) where expediente_clave not in (select expediente_clave from TR_FED_V1_part_ACT_INDIVIDUALjl where  SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' \n" +
"AND PERIODO ='"+PValidacion.periodo+"'  OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' \n" +
"AND PERIODO ='"+PValidacion.periodo+"')";
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
            Logger.getLogger(FedV1QPart_act_individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}


//Query de validacion para saber si Cuando el expediente es incompetencia = SI, Cantidad de Actores y Cantidad de demandados es No aplica por ende no se debe de desglosar actores ni demandados.
public ArrayList IncompetenciaNE(){
    conexion.Conectar();
    Array = new ArrayList();
     sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_PART,SEC.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_individual,decode(sec.incompetencia,'1','Sí','2','NO')incompetencia FROM(\n" +
"SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO,PERIODO\n" +
"FROM TR_FED_V1_part_ACT_INDIVIDUALjl\n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"'\n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"')PRIN LEFT JOIN\n" +
"TR_FED_V1_individualJL SEC \n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE \n" +
"AND PRIN.PERIODO=SEC.PERIODO) WHERE INCOMPETENCIA='Sí'";
resul=conexion.consultar(sql);
    //System.out.println(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE_individual"),
                  resul.getString("INCOMPETENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QPart_act_individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}

//Query de validacion para saber si  el estatus de la demanda=DESECHADA,ARCHIVO,NO SE DIO TRAMITE AL ESCRITO DE DEMANDA. Cantidad de Actores y Cantidad de demandados es No aplica por ende no se debe de desglosar actores ni demandados.";
public ArrayList Estatus_demandaNE(){
    conexion.Conectar();
    Array = new ArrayList();
     sql="SELECT * FROM(\n" +
"SELECT PRIN.CLAVE_ORGANO,PRIN.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_PART,SEC.EXPEDIENTE_CLAVE EXPEDIENTE_CLAVE_INDIVIDUAL,decode(sec.estatus_demanda,'1','Admitida','2','Desechada','3','Archivo','4','No se dio trámite al escrito de demanda') estatus_demanda FROM(\n" +
"SELECT UNIQUE(EXPEDIENTE_CLAVE)EXPEDIENTE_CLAVE,CLAVE_ORGANO,PERIODO\n" +
"FROM TR_FED_V1_part_ACT_INDIVIDUALjl\n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"'\n" +
"OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"')PRIN LEFT JOIN\n" +
"TR_FED_V1_INDIVIDUALJL SEC \n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE \n" +
"AND PRIN.PERIODO=SEC.PERIODO)where ESTATUS_DEMANDA IN ('Desechada','Archivo','No se dio trámite al escrito de demanda')";
resul=conexion.consultar(sql);
 //System.out.println(sql);     
try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE_INDIVIDUAL"),
                  resul.getString("ESTATUS_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(FedV1QPart_act_individual.class.getName()).log(Level.SEVERE, null, ex);
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
"CASE WHEN sec.incompetencia IS NULL THEN 'NULLO' ELSE TO_CHAR(sec.incompetencia) END incompetencia,"
+ "CASE WHEN sec.estatus_demanda IS NULL THEN 'NULLO' ELSE TO_CHAR(sec.estatus_demanda) END estatus_demanda,PRIN.PERIODO\n" +
"FROM(\n" +
"select CLAVE_ORGANO,EXPEDIENTE_CLAVE,PERIODO,COUNT(ID_ACTOR) DESGLOSE_ACTOR\n" +
"FROM TR_FED_V1_part_ACT_INDIVIDUALjl \n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND ID_ACTOR NOT LIKE '%-%'\n" +
"AND PERIODO ='"+PValidacion.periodo+"' OR CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND ID_ACTOR NOT LIKE '%-%'\n" +
"AND PERIODO ='"+PValidacion.periodo+"'\n" +
"GROUP BY CLAVE_ORGANO,EXPEDIENTE_CLAVE,PERIODO)PRIN LEFT JOIN TR_FED_V1_INDIVIDUALJL SEC\n" +
"ON PRIN.CLAVE_ORGANO=SEC.CLAVE_ORGANO AND PRIN.EXPEDIENTE_CLAVE=SEC.EXPEDIENTE_CLAVE AND \n" +
"PRIN.PERIODO=SEC.PERIODO) WHERE INCOMPETENCIA<>'1' AND ESTATUS_DEMANDA NOT IN ('2','3','4')\n" +
"AND CANTIDAD_ACTORES<>DESGLOSE_ACTORES";
resul=conexion.consultar(sql);
 //System.out.println(sql);     
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
            Logger.getLogger(FedV1QPart_act_individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;    
}
}


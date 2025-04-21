/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

import Conexion.OracleConexionNE;
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
public class V3QColec_Econom {
 OracleConexionNE conexion = new OracleConexionNE();
String sql;
ArrayList<String[]> Array;
ResultSet resul;

//Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche.
  public ArrayList Año_JudicialCampeche(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
" SELECT * FROM( \n" +
"        SELECT * FROM( \n" +
"        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,\n" +
"        SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  FECHA_APERTURA_EXPEDIENTE,\n" +
"TO_NUMBER(SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4))+1  FECHA_APERTURA_EXPEDIENTES1,\n" +
"        substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO, \n" +
"        PERIODO \n" +
"        from V3_TR_colect_economjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')  \n" +
"        WHERE  FECHA_APERTURA_EXPEDIENTES  \n" +
"        NOT BETWEEN     to_date('01/09/'||FECHA_APERTURA_EXPEDIENTE||'')\n" +
"         AND  TO_date('01/08/'||(FECHA_APERTURA_EXPEDIENTES1)||'') ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
  //Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche comprendiendo los años 2020,2021,2022.
  public ArrayList Año_DIF_Campeche(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM (\n" +
" SELECT * FROM( \n" +
"        SELECT * FROM( \n" +
"        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  \n" +
"        FECHA_APERTURA_EXPEDIENTE,substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO, \n" +
"        PERIODO \n" +
"        from V3_TR_colect_economjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"') WHERE EXPE_AÑO NOT IN "+PValidacion.AñoJuridico+" ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES"),
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
public ArrayList Año_Expe_ColecEconomNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V3_TR_colect_economjl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO AND EXPE_AÑO NOT IN ('2021','2022','2023','2020','2024','2025')) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"'";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTES")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }


public ArrayList FECHA_APERTURA_EXPEDIENTE_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_APERTURA_EXPEDIENTE> SYSDATE \n" +
"AND FECHA_APERTURA_EXPEDIENTE <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_PRES_DEMANDA_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_PRES_DEMANDA,'DD/MM/YYYY')FECHA_PRES_DEMANDA,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_PRES_DEMANDA> SYSDATE \n" +
"AND FECHA_PRES_DEMANDA <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_PRES_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
   return Array;
}


public ArrayList FECHA_ADMISION_DEMANDA_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_ADMISION_DEMANDA,'DD/MM/YYYY')FECHA_ADMISION_DEMANDA,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_ADMISION_DEMANDA> SYSDATE \n" +
"AND FECHA_ADMISION_DEMANDA <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_ADMISION_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}


public ArrayList FECHA_AUDIENCIA_ECONOM_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_AUDIENCIA_ECONOM,'DD/MM/YYYY')FECHA_AUDIENCIA_ECONOM,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_AUDIENCIA_ECONOM> SYSDATE \n" +
"AND FECHA_AUDIENCIA_ECONOM <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_AUDIENCIA_ECONOM")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_ACTO_PROCESAL_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_ACTO_PROCESAL,'DD/MM/YYYY')FECHA_ACTO_PROCESAL,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_ACTO_PROCESAL> SYSDATE \n" +
"AND FECHA_ACTO_PROCESAL <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_ACTO_PROCESAL")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_RESOLUCION_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_RESOLUCION,'DD/MM/YYYY')FECHA_RESOLUCION,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECT_ECONOMJL \n" +
"WHERE \n" +
"(FECHA_RESOLUCION> SYSDATE \n" +
"AND FECHA_RESOLUCION <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_RESOLUCION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}


public ArrayList Duplicidad_expediente(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO FROM V3_TR_colect_economjl\n" +
"WHERE CLAVE_ORGANO||TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))||PERIODO\n" +
"IN(\n" +
"SELECT CLAVE_ORGANO||EXPEDIENTE_CLAVE2||PERIODO FROM(\n" +
"SELECT CLAVE_ORGANO,COUNT(expediente_clave2)CUENTAexpediente_clave2,expediente_clave2,PERIODO FROM(\n" +
"select CLAVE_ORGANO,expediente_clave,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO from V3_TR_colect_economjl \n" +
"WHERE (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' AND PERIODO='"+PValidacion.periodo+"') or (CLAVE_ORGANO='"+PValidacion.clave_organo+"' AND PERIODO='"+PValidacion.periodo+"')  \n" +
"ORDER BY CLAVE_ORGANO,expediente_clave2)\n" +
"GROUP BY CLAVE_ORGANO,expediente_clave2,PERIODO)WHERE CUENTAexpediente_clave2>1)\n" +
"ORDER BY CLAVE_ORGANO,expediente_clave2";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

  public ArrayList Fecha_PresentacionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE,to_char(fecha_pres_demanda,'dd/mm/yyyy')fecha_pres_demanda,PERIODO\n" +
"from V3_TR_colect_economjl\n" +
"WHERE to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy')) where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_pres_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_pres_demanda <> '09/09/1899' AND periodo = '"+PValidacion.periodo+"' \n";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_pres_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  
  
   //ls fecha de admision de la demanda no debe de ser menor a la fecha depresentacion de la demanda
   public ArrayList Fecha_PresentacionAdmiNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_ADMISION_DEMANDA,'dd/mm/yyyy')FECHA_ADMISION_DEMANDA,to_char(fecha_pres_demanda,'dd/mm/yyyy')fecha_pres_demanda,PERIODO \n" +   
"from V3_TR_COLECT_ECONOMJL \n" +
"WHERE to_date(FECHA_ADMISION_DEMANDA,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy')) where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_pres_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_pres_demanda <> '09/09/1899' AND periodo = '"+PValidacion.periodo+"'\n";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_ADMISION_DEMANDA"),
                  resul.getString("fecha_pres_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }



 //Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente
    public ArrayList Fecha_Admision_DemandaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Admision_Demanda,'DD/MM/YYYY') Fecha_Admision_Demanda ,periodo\n" +
          "from V3_TR_colect_economjl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Admision_Demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Admision_Demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where  to_date(Fecha_Admision_Demanda,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_Admision_Demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
      //Query de validacion donde la Fecha de audiencia dentro del procedimiento colectivo de naturaleza económica  no debe de ser menor o igual a la fecha de apertura del expediente
    public ArrayList Fecha_audiencia_EconomNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_audiencia_Econom,'DD/MM/YYYY') Fecha_audiencia_Econom ,periodo\n" +
          "from V3_TR_colect_economjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_audiencia_Econom <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_audiencia_Econom <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(Fecha_audiencia_Econom,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_audiencia_Econom")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
     //Query de validacion donde la Fecha del último acto procesal no debe de ser menor a la fecha de apertura del expediente.
    public ArrayList Fecha_acto_procesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_acto_procesal,'DD/MM/YYYY') Fecha_acto_procesal ,periodo\n" +
          "from V3_TR_colect_economjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_acto_procesal <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_acto_procesal <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(Fecha_acto_procesal,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_audiencia_Econom")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
    
    //Query de validacion donde la Fecha en la que se dictó la resolución no debe de ser menor a la fecha de apertura del expediente.
    public ArrayList Fecha_resolucionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_resolucion,'DD/MM/YYYY') Fecha_resolucion ,periodo\n" +
          "from V3_TR_colect_economjl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_resolucion <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_resolucion <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(Fecha_resolucion,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_resolucion")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

    
// LA FECHA DE AUDIENCIA CELEBRARA NO DEBE SER MENOR A LA FECHA DE PRESENTACION DE LA DEMANDA
  public ArrayList Fecha_Aud_Presentacion(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_PRES_DEMANDA,'DD/MM/YYYY') FECHA_PRES_DEMANDA, P.TIPO_PROCED,P.PERIODO,P.ID_AUDIENCIA\n" +
"FROM V3_TR_AUDIENCIASJL P,V3_TR_colect_economjl S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=5 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy') \n" +
               " )where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_PRES_DEMANDA <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_PRES_DEMANDA <> '09/09/1899' \n" +
          "and periodo = '"+PValidacion.periodo+"' ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_AUDIEN_CELEBRADA"),
                  resul.getString("FECHA_PRES_DEMANDA"),
                  resul.getString("ID_AUDIENCIA")
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  

  
  public ArrayList Fecha_Aud_Apertura(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE, P.TIPO_PROCED,P.PERIODO,P.ID_AUDIENCIA\n" +
"FROM V3_TR_AUDIENCIASJL P,V3_TR_colect_economjl S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=5 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') \n" +
               " )where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_APERTURA_EXPEDIENTE <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_APERTURA_EXPEDIENTE <> '09/09/1899' \n" +
          "and periodo = '"+PValidacion.periodo+"' ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_AUDIEN_CELEBRADA"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("ID_AUDIENCIA")
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  
  
  
  
  //ORDINARIO LA FECHA DE AUDIENCIA CELEBRARA NO DEBE SER MENOR A LA FECHA DE PRESENTACION DE LA DEMANDA
  public ArrayList Fecha_Aud_Admision(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_ADMISION_DEMANDA,'DD/MM/YYYY') FECHA_ADMISION_DEMANDA, P.TIPO_PROCED,P.PERIODO,P.ID_AUDIENCIA\n" +
"FROM V3_TR_AUDIENCIASJL P,V3_TR_colect_economjl S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=5 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_ADMISION_DEMANDA,'dd/mm/yyyy') \n" +
               " )where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_ADMISION_DEMANDA <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_ADMISION_DEMANDA <> '09/09/1899' \n" +
          "and periodo = '"+PValidacion.periodo+"' ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_AUDIEN_CELEBRADA"),
                  resul.getString("FECHA_ADMISION_DEMANDA"),
                  resul.getString("ID_AUDIENCIA")
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }              
  
  
  public ArrayList SinMotivo_Conflicto(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT\n" +
"    CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE\n" +
"FROM\n" +
"    V3_TR_COLECT_ECONOMJL\n" +
"WHERE\n" +
"     modif_condiciones = '2'\n" +
"     AND nuevas_condiciones = '2'\n" +
"     AND suspension_temporal = '2'\n" +
"     AND terminacion_colectiva = '2'\n" +
"     AND otro_motivo_econom = '2' and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
" or clave_organo='"+PValidacion.clave_organo+"' and periodo = '"+PValidacion.periodo+"')"; 
System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    

}

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
public class V3QColectivo {
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
"        from V3_TR_colectivojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
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
"        from V3_TR_colectivojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"'  \n" +
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
public ArrayList Año_Expe_ColectivoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n" +
        "PERIODO\n" +
        "from V3_TR_colectivojl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO AND EXPE_AÑO NOT IN ('2021','2022','2023','2020','2024','2025')) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"' and periodo = '"+PValidacion.periodo+"' \n" +
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
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_APERTURA_EXPEDIENTE>SYSDATE \n" +
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
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_PRES_DEMANDA>SYSDATE \n" +
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

public ArrayList FECHA_ADMI_DEMANDA_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_ADMI_DEMANDA,'DD/MM/YYYY')FECHA_ADMI_DEMANDA,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_ADMI_DEMANDA>SYSDATE \n" +
"AND FECHA_ADMI_DEMANDA <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_ADMI_DEMANDA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_DEPURACION_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_DEPURACION,'DD/MM/YYYY')FECHA_DEPURACION,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_DEPURACION>SYSDATE \n" +
"AND FECHA_DEPURACION <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_DEPURACION")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_AUDIENCIA_JUICIO_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_AUDIENCIA_JUICIO,'DD/MM/YYYY')FECHA_AUDIENCIA_JUICIO,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_AUDIENCIA_JUICIO>SYSDATE \n" +
"AND FECHA_AUDIENCIA_JUICIO <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_AUDIENCIA_JUICIO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_DICTO_RESOLUCION_AD_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_DICTO_RESOLUCION_AD,'DD/MM/YYYY')FECHA_DICTO_RESOLUCION_AD,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_DICTO_RESOLUCION_AD>SYSDATE \n" +
"AND FECHA_DICTO_RESOLUCION_AD <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_DICTO_RESOLUCION_AD")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
}

public ArrayList FECHA_RESOLUCION_AJ_FUT(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_RESOLUCION_AJ,'DD/MM/YYYY')FECHA_RESOLUCION_AJ,\n" +
"TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM V3_TR_COLECTIVOJL \n" +
"WHERE \n" +
"(FECHA_RESOLUCION_AJ>SYSDATE \n" +
"AND FECHA_RESOLUCION_AJ <> '09/09/1899')\n" +
"and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"'  and periodo='"+PValidacion.periodo+"' ) or  (clave_organo='"+PValidacion.clave_organo+"'  and periodo='"+PValidacion.periodo+"' ))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("periodo"),
                  resul.getString("FECHA_RESOLUCION_AJ")
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
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO FROM V3_TR_COLECTIVOJL\n" +
"WHERE CLAVE_ORGANO||TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))||PERIODO\n" +
"IN(\n" +
"SELECT CLAVE_ORGANO||EXPEDIENTE_CLAVE2||PERIODO FROM(\n" +
"SELECT CLAVE_ORGANO,COUNT(expediente_clave2)CUENTAexpediente_clave2,expediente_clave2,PERIODO FROM(\n" +
"select CLAVE_ORGANO,expediente_clave,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO from V3_TR_COLECTIVOJL \n" +
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
"from V3_TR_COLECTIVOJL\n" +
"WHERE to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy')) where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_pres_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_pres_demanda <> '09/09/1899' and periodo = '"+PValidacion.periodo+"'\n";
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
      sql="select * from (select entidad_clave,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_ADMI_DEMANDA,'dd/mm/yyyy')FECHA_ADMI_DEMANDA,to_char(fecha_pres_demanda,'dd/mm/yyyy')fecha_pres_demanda,PERIODO \n" +
"from V3_TR_COLECTIVOJL \n" +
"WHERE to_date(FECHA_ADMI_DEMANDA,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy')) where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_pres_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_pres_demanda <> '09/09/1899' and periodo = '"+PValidacion.periodo+"'\n";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_ADMI_DEMANDA"),
                  resul.getString("fecha_pres_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 


//Query de validacion donde la fecha de admisionde la demanda no debe de ser menor a la fecha de apertura del expediente
    public ArrayList Fecha_Admi_DemandaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,FECHA_APERTURA_EXPEDIENTE,fecha_admi_demanda,periodo\n" +
          "from V3_TR_colectivojl where  periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_admi_demanda <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_admi_demanda <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where  to_date(fecha_admi_demanda,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_admi_demanda")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
    
      //Query de validacion donde la Fecha de audiencia de juicio no debe de ser menor o igual a la fecha de apertura del expediente
    public ArrayList Fecha_Audiencia_JuicioNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_audiencia_juicio,'DD/MM/YYYY') fecha_audiencia_juicio ,periodo\n" +
          "from V3_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_audiencia_juicio <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_audiencia_juicio <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"') where to_date(fecha_audiencia_juicio,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_audiencia_juicio")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

       //Query de validacion donde la Fecha de auto de depuración no debe de ser menor o igual a la fecha de apertura del expediente.
        public ArrayList Fecha_DepuracionNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_depuracion,'DD/MM/YYYY') fecha_depuracion,periodo\n" +
          "from V3_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_depuracion <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_depuracion <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_depuracion,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_depuracion")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
        //Query de validacion donde la Fecha del último acto procesal no debe de ser menor a la fecha de apertura del expediente.
        public ArrayList Fecha_Acto_ProcesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Acto_Procesal,'DD/MM/YYYY') Fecha_Acto_Procesal   ,periodo\n" +
          "from V3_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Acto_Procesal <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Acto_Procesal <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(Fecha_Acto_Procesal,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_Acto_Procesal")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
                        
       //Query de validacion donde la Fecha en la que se dictó la resolución (audiencia de juicio) no debe de ser menor a la fecha de apertura del expediente.
                public ArrayList Fecha_Resolucion_AjNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(fecha_resolucion_aj,'DD/MM/YYYY') fecha_resolucion_aj,periodo\n" +
          "from V3_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and fecha_resolucion_aj <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and fecha_resolucion_aj <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(fecha_resolucion_aj,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("fecha_resolucion_aj")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }         
                
 //Query de validacion donde la Fecha en la que se dictó la resolución (auto depuracion) no debe de ser menor a la fecha de apertura del expediente.
          public ArrayList Fecha_Dicto_Resolucion_ADNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Dicto_Resolucion_AD,'DD/MM/YYYY') Fecha_Dicto_Resolucion_AD,periodo\n" +
          "from V3_TR_colectivojl where periodo = '"+PValidacion.periodo+"'\n" +
          "and Fecha_Dicto_Resolucion_AD <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Dicto_Resolucion_AD <> '09/09/1899'\n" +
          "and periodo = '"+PValidacion.periodo+"' ) where to_date(Fecha_Dicto_Resolucion_AD,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      //System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("Fecha_Dicto_Resolucion_AD")
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
"FROM V3_TR_AUDIENCIASJL P,V3_TR_COLECTIVOJL S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=3 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_PRES_DEMANDA,'dd/mm/yyyy') \n" +
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
"FROM V3_TR_AUDIENCIASJL P,V3_TR_COLECTIVOJL S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=3 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') \n" +
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
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_ADMI_DEMANDA,'DD/MM/YYYY') FECHA_ADMI_DEMANDA, P.TIPO_PROCED,P.PERIODO,P.ID_AUDIENCIA\n" +
"FROM V3_TR_AUDIENCIASJL P,V3_TR_COLECTIVOJL S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE AND P.PERIODO=S.PERIODO\n" +
"AND P.TIPO_PROCED=3 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_ADMI_DEMANDA,'dd/mm/yyyy') \n" +
               " )where periodo = '"+PValidacion.periodo+"'\n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_ADMI_DEMANDA <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_ADMI_DEMANDA <> '09/09/1899' \n" +
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
                  resul.getString("FECHA_ADMI_DEMANDA"),
                  resul.getString("ID_AUDIENCIA")
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }          
          
          
  
}

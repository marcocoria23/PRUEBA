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
public class V3QHuelgaFederal {
  OracleConexionFederal conexion = new OracleConexionFederal();
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
"        substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO \n" +
"        from TR_FED_huelga ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'   \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' )  \n" +
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
"        FECHA_APERTURA_EXPEDIENTE,substr(TRIM(replace(replace(replace(replace(replace(replace(replace(replace(expediente_clave,'-',''),'I',''),'J',''),'L',''),'/',''),'ll',''),'II',''),'I ','')),-4,4) EXPE_AÑO \n" +
"        from TR_FED_huelga ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'   \n" +
"        or clave_organo='"+PValidacion.clave_organo+"' ) WHERE EXPE_AÑO NOT IN "+PValidacion.AñoJuridico+" ";
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
  public ArrayList Año_Expe_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="SELECT * FROM(\n" +
        "SELECT * FROM(\n" +
        "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n" +
        "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO\n" +
        "from TR_FED_huelga ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO AND EXPE_AÑO NOT IN ('2021','2022','2023','2020','2024','2025')) WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
        "or clave_organo='"+PValidacion.clave_organo+"' ";
      //System.out.println(sql);
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
  
  
      public ArrayList FECHA_APERTURA_EXPEDIENTE_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_APERTURA_EXPEDIENTE > SYSDATE \n" +
    "AND FECHA_APERTURA_EXPEDIENTE <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_APERTURA_EXPEDIENTE")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
      
      
          public ArrayList FECHA_PRESENTA_PETIC_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_PRESENTA_PETIC,'DD/MM/YYYY')FECHA_PRESENTA_PETIC,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_PRESENTA_PETIC > SYSDATE \n" +
    "AND FECHA_PRESENTA_PETIC <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_PRESENTA_PETIC")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
          
          
              public ArrayList FECHA_EMPLAZAMIENTO_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY')FECHA_EMPLAZAMIENTO,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_EMPLAZAMIENTO > SYSDATE \n" +
    "AND FECHA_EMPLAZAMIENTO <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_EMPLAZAMIENTO")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
              
      public ArrayList FECHA_AUDIENCIA_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_AUDIENCIA,'DD/MM/YYYY')FECHA_AUDIENCIA,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_AUDIENCIA > SYSDATE \n" +
    "AND FECHA_AUDIENCIA <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_AUDIENCIA")
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
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_ACTO_PROCESAL,'DD/MM/YYYY')FECHA_ACTO_PROCESAL,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_ACTO_PROCESAL > SYSDATE \n" +
    "AND FECHA_ACTO_PROCESAL <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_ACTO_PROCESAL")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
          
              public ArrayList FECHA_RESOLU_EMPLAZ_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_RESOLU_EMPLAZ,'DD/MM/YYYY')FECHA_RESOLU_EMPLAZ,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_RESOLU_EMPLAZ > SYSDATE \n" +
    "AND FECHA_RESOLU_EMPLAZ <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_RESOLU_EMPLAZ")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
              
                  public ArrayList FECHA_RESOLU_HUELGA_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_RESOLU_HUELGA,'DD/MM/YYYY')FECHA_RESOLU_HUELGA,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_RESOLU_HUELGA > SYSDATE \n" +
    "AND FECHA_RESOLU_HUELGA <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_RESOLU_HUELGA")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
                  
                      public ArrayList FECHA_ESTALLAM_HUELGA_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_ESTALLAM_HUELGA,'DD/MM/YYYY')FECHA_ESTALLAM_HUELGA,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_ESTALLAM_HUELGA > SYSDATE \n" +
    "AND FECHA_ESTALLAM_HUELGA <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_ESTALLAM_HUELGA")
                    });
              }
          conexion.close();
         } catch (SQLException ex) {
                Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Array;
     }
                      
          public ArrayList FECHA_LEVANT_HUELGA_FUT(){
          conexion.Conectar();
          Array = new ArrayList();
          sql="SELECT clave_organo, expediente_clave, TO_CHAR(FECHA_LEVANT_HUELGA,'DD/MM/YYYY')FECHA_LEVANT_HUELGA,\n" +
    "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  TR_FED_HUELGA  \n" +
    "WHERE \n" +
    "(FECHA_LEVANT_HUELGA > SYSDATE \n" +
    "AND FECHA_LEVANT_HUELGA <> '09/09/1899')\n" +
    "and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' ) or  (clave_organo='"+PValidacion.clave_organo+"' ))";
          System.out.println(sql);
          resul=conexion.consultar(sql);
          try {
              while (resul.next()) {
                  Array.add(new String[]{
                      resul.getString("clave_organo"),
                      resul.getString("expediente_clave"),
                      resul.getString("FECHA_LEVANT_HUELGA")
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
      sql="SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2 FROM TR_FED_HUELGA\n" +
"WHERE CLAVE_ORGANO||TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))\n" +
"IN(\n" +
"SELECT CLAVE_ORGANO||EXPEDIENTE_CLAVE2 FROM(\n" +
"SELECT CLAVE_ORGANO,COUNT(expediente_clave2)CUENTAexpediente_clave2,expediente_clave2 FROM(\n" +
"select CLAVE_ORGANO,expediente_clave,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2 from TR_FED_HUELGA \n" +
"WHERE (SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+") or (CLAVE_ORGANO='"+PValidacion.clave_organo+")  \n" +
"ORDER BY CLAVE_ORGANO,expediente_clave2)\n" +
"GROUP BY CLAVE_ORGANO,expediente_clave2)WHERE CUENTAexpediente_clave2>1)\n" +
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
      sql="select * from (select entidad_clave,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_PRESENTA_PETIC,'dd/mm/yyyy')FECHA_PRESENTA_PETIC\n" +
"from TR_FED_HUELGA\n" +
"WHERE to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') < to_date(FECHA_PRESENTA_PETIC,'dd/mm/yyyy')) where  \n" +
          " FECHA_PRESENTA_PETIC <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_PRESENTA_PETIC <> '09/09/1899'  \n";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_PRESENTA_PETIC")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 } 
  
  
  
  
  
  
  
  
  
//Query de validacion donde la fecha de emplazamiento no debe de ser menor a la fecha de apertura del expediente.
public ArrayList Fecha_EmplazamientoNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY') FECHA_EMPLAZAMIENTO  \n" +
          "from TR_FED_huelga where  \n" +
          " FECHA_EMPLAZAMIENTO <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_EMPLAZAMIENTO <> '09/09/1899'\n" +
          " ) where  to_date(FECHA_EMPLAZAMIENTO,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_EMPLAZAMIENTO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la Fecha del emplazamiento a huelga  no debe de ser menor o igual a la fecha de apertura del expediente.
public ArrayList Fecha_AudienciaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_AUDIENCIA,'DD/MM/YYYY') FECHA_AUDIENCIA  \n" +
          "from TR_FED_huelga where \n" +
          " FECHA_AUDIENCIA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIENCIA <> '09/09/1899'\n" +
          ") where  to_date(FECHA_AUDIENCIA,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_AUDIENCIA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la Fecha del ultimo acto procesal no debe de ser menor a la fecha de apertura del expediente.
public ArrayList Fecha_Acto_ProcesalNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Acto_Procesal,'DD/MM/YYYY') Fecha_Acto_Procesal  \n" +
          "from TR_FED_huelga where \n" +
          " Fecha_Acto_Procesal <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and Fecha_Acto_Procesal <> '09/09/1899'\n" +
          " ) where  to_date(Fecha_Acto_Procesal,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
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

//Query de validacion donde la Fecha en la que se dictó la resolución (Emplazamiento/Prehuelga) no debe de ser menor o igual a la fecha de apertura del expediente PUEDE SER MAYOR O IGUAL A LA FECHA DE APERTURA  CUANDO LA FASE EN LA QUE SE SOLUCIONO EL EXPEDIENTE NO SEA emplazamiento a huelga” o “prehuelga”  Y LA FORMA DE SOLUCION NO SEA NO DAR TRAMITE AL ESCRITO DE EMPLAZAMIENTO POR NO CUMPLIR CON LOS REQUISITOS ESTABLECIDOS
public ArrayList Fecha_Resolu_EmplazNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from(select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_RESOLU_EMPLAZ,'DD/MM/YYYY') FECHA_RESOLU_EMPLAZ  ,FASE_SOLI_EXPEDIENTE,FORMA_SOLUCION_EMPLAZ \n" +
          "from TR_FED_huelga where \n" +
          " FECHA_RESOLU_EMPLAZ <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLU_EMPLAZ <> '09/09/1899'\n" +
          " ) where  to_date(FECHA_RESOLU_EMPLAZ,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy'))";
      //where FASE_SOLI_EXPEDIENTE not in (5,6) and FORMA_SOLUCION_EMPLAZ not in (1) System.out.println(sql);
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_RESOLU_EMPLAZ")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la Fecha en la que se dictó la resolución (Huelga) no debe de ser menor a la fecha de apertura del expediente.
public ArrayList Fecha_Resolu_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_RESOLU_HUELGA,'DD/MM/YYYY') FECHA_RESOLU_HUELGA  \n" +
          "from TR_FED_huelga where \n" +
          " FECHA_RESOLU_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_RESOLU_HUELGA <> '09/09/1899'\n" +
          ") where  to_date(FECHA_RESOLU_HUELGA,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_RESOLU_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la Fecha de estallamiento de la huelga no debe de ser menor o igual a la fecha de apertura del expediente.
public ArrayList Fecha_Estallam_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_ESTALLAM_HUELGA,'DD/MM/YYYY') FECHA_ESTALLAM_HUELGA  \n" +
          "from TR_FED_huelga where \n" +
          " FECHA_ESTALLAM_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_ESTALLAM_HUELGA <> '09/09/1899'\n" +
          " ) where  to_date(FECHA_ESTALLAM_HUELGA,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_ESTALLAM_HUELGA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }

//Query de validacion donde la Fecha de levantamiento de la huelga no debe de ser menor o igual a la fecha de apertura del expediente.
public ArrayList Fecha_Levant_HuelgaNE(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select * from (select entidad_clave,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_LEVANT_HUELGA,'DD/MM/YYYY') FECHA_LEVANT_HUELGA  \n" +
          "from TR_FED_huelga where \n" +
          "and FECHA_LEVANT_HUELGA <> '09/09/1899'  and clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_LEVANT_HUELGA <> '09/09/1899'\n" +
          " ) where  to_date(FECHA_LEVANT_HUELGA,'dd/mm/yyyy')  <= to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("entidad_clave"),
                  resul.getString("clave_organo"),
                  resul.getString("expediente_clave"),
                  resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                  resul.getString("FECHA_LEVANT_HUELGA")
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
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_PRESENTA_PETIC,'DD/MM/YYYY') FECHA_PRESENTA_PETIC, P.TIPO_PROCED,P.ID_AUDIENCIA\n" +
"FROM TR_FED_AUDIENCIAS P,TR_FED_HUELGA S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE \n" +
"AND P.TIPO_PROCED=4 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_PRESENTA_PETIC,'dd/mm/yyyy') \n" +
               " )where \n" +
          " FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_PRESENTA_PETIC <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_PRESENTA_PETIC <> '09/09/1899' \n" +
          " ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("FECHA_AUDIEN_CELEBRADA"),
                  resul.getString("FECHA_PRESENTA_PETIC"),
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
      sql="select * from (SELECT S.ENTIDAD_CLAVE,P.CLAVE_ORGANO,P.EXPEDIENTE_CLAVE,to_char(P.FECHA_AUDIEN_CELEBRADA,'DD/MM/YYYY')FECHA_AUDIEN_CELEBRADA,to_char(S.FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE, P.TIPO_PROCED,P.ID_AUDIENCIA\n" +
"FROM TR_FED_AUDIENCIAS P,TR_FED_HUELGA S\n" +
"WHERE P.CLAVE_ORGANO=S.CLAVE_ORGANO AND P.EXPEDIENTE_CLAVE=S.EXPEDIENTE_CLAVE \n" +
"AND P.TIPO_PROCED=4 AND to_date(FECHA_AUDIEN_CELEBRADA,'dd/mm/yyyy') < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') \n" +
               " )where \n" +
          " FECHA_AUDIEN_CELEBRADA <> '09/09/1899'  and FECHA_APERTURA_EXPEDIENTE <> '09/09/1899' and  clave_organo='"+PValidacion.clave_organo+"' or substr(clave_organo,0,2)='"+PValidacion.clave_entidad+"' \n" +
          "and FECHA_AUDIEN_CELEBRADA <> '09/09/1899' and  FECHA_APERTURA_EXPEDIENTE <> '09/09/1899' \n" +
          " ";
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
  
  
  
  
  public ArrayList Huelga(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO, EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,1,'Solucionado')ESTATUS_EXPEDIENTE,decode(FASE_SOLI_EXPEDIENTE,7,'Huelga')FASE_SOLI_EXPEDIENTE,DECODE(EMPLAZAMIENTO_HUELGA,'1','SI','2','NO')EMPLAZAMIENTO_HUELGA,DECODE(prehuelga,'1','SI','2','NO')prehuelga,DECODE(ESTALLAMIENTO_HUELGA,'1','SI','2','NO')ESTALLAMIENTO_HUELGA,\n" +
"to_char(FECHA_ESTALLAM_HUELGA,'DD/MM/YYYY')FECHA_ESTALLAM_HUELGA,to_char(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY')FECHA_EMPLAZAMIENTO\n" +
",to_char(FECHA_RESOLU_HUELGA,'DD/MM/YYYY')FECHA_RESOLU_HUELGA,to_char(FECHA_LEVANT_HUELGA,'DD/MM/YYYY')FECHA_LEVANT_HUELGA\n" +
"from TR_FED_HUELGA WHERE\n" +
"((( FASE_SOLI_EXPEDIENTE=7 and ESTATUS_EXPEDIENTE=1) and (EMPLAZAMIENTO_HUELGA<>1 or prehuelga<>1 or ESTALLAMIENTO_HUELGA<>1 or FECHA_RESOLU_HUELGA IN (NULL,'09/09/1899') or FECHA_ESTALLAM_HUELGA IN (NULL,'09/09/1899') or FECHA_LEVANT_HUELGA IN (NULL,'09/09/1899')))  and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+") or (clave_organo='"+PValidacion.clave_organo+")))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE"),
                  resul.getString("EMPLAZAMIENTO_HUELGA"),
                  resul.getString("prehuelga"),
                  resul.getString("ESTALLAMIENTO_HUELGA"),
                  resul.getString("FECHA_ESTALLAM_HUELGA"),
                  resul.getString("FECHA_EMPLAZAMIENTO"),
                  resul.getString("FECHA_RESOLU_HUELGA"),
                  resul.getString("FECHA_LEVANT_HUELGA"),
              
                  
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
  
  
  
   public ArrayList EMPLAZAMIENTO_HUELGA(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO, EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,1,'Solucionado')ESTATUS_EXPEDIENTE,decode(FASE_SOLI_EXPEDIENTE,5,'Emplazamiento a huelga')FASE_SOLI_EXPEDIENTE,DECODE(EMPLAZAMIENTO_HUELGA,'1','SI','2','NO')EMPLAZAMIENTO_HUELGA,DECODE(prehuelga,'1','SI','2','NO')prehuelga,DECODE(ESTALLAMIENTO_HUELGA,'1','SI','2','NO')ESTALLAMIENTO_HUELGA\n" +
",to_char(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY')FECHA_EMPLAZAMIENTO, to_char(FECHA_RESOLU_EMPLAZ,'DD/MM/YYYY')FECHA_RESOLU_EMPLAZ from TR_FED_HUELGA WHERE\n" +
"((( FASE_SOLI_EXPEDIENTE=5 and ESTATUS_EXPEDIENTE=1) and (ESTALLAMIENTO_HUELGA<>2 or prehuelga<>2))  and ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+") or (clave_organo='"+PValidacion.clave_organo+")))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE"),
                  resul.getString("EMPLAZAMIENTO_HUELGA"),
                  resul.getString("prehuelga"),
                  resul.getString("ESTALLAMIENTO_HUELGA"),
                  resul.getString("FECHA_EMPLAZAMIENTO"),
                  resul.getString("FECHA_RESOLU_EMPLAZ"),
              
                  
                });            
  
   }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }  
   
   
   
   
    public ArrayList PREHUELGA(){
      conexion.Conectar();
      Array = new ArrayList();
      sql="select CLAVE_ORGANO, EXPEDIENTE_CLAVE,DECODE(ESTATUS_EXPEDIENTE,1,'Solucionado')ESTATUS_EXPEDIENTE,decode(FASE_SOLI_EXPEDIENTE,6,'Prehuelga')FASE_SOLI_EXPEDIENTE,DECODE(EMPLAZAMIENTO_HUELGA,'1','SI','2','NO')EMPLAZAMIENTO_HUELGA,DECODE(prehuelga,'1','SI','2','NO')prehuelga,DECODE(ESTALLAMIENTO_HUELGA,'1','SI','2','NO')ESTALLAMIENTO_HUELGA\n" +
",to_char(FECHA_EMPLAZAMIENTO,'DD/MM/YYYY')FECHA_EMPLAZAMIENTO,to_char(FECHA_RESOLU_EMPLAZ,'DD/MM/YYYY')FECHA_RESOLU_EMPLAZ from TR_FED_HUELGA WHERE\n" +
"((( FASE_SOLI_EXPEDIENTE=6 and ESTATUS_EXPEDIENTE=1) and (ESTALLAMIENTO_HUELGA<>2 or prehuelga<>1 OR EMPLAZAMIENTO_HUELGA <>1)) and  ((substr(clave_organo,0,2)='"+PValidacion.clave_entidad+") or (clave_organo='"+PValidacion.clave_organo+")))";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("EXPEDIENTE_CLAVE"),
                  resul.getString("ESTATUS_EXPEDIENTE"),
                  resul.getString("FASE_SOLI_EXPEDIENTE"),
                  resul.getString("EMPLAZAMIENTO_HUELGA"),
                  resul.getString("prehuelga"),
                  resul.getString("ESTALLAMIENTO_HUELGA"),
                  resul.getString("FECHA_EMPLAZAMIENTO"),
                  resul.getString("FECHA_RESOLU_EMPLAZ"),
              
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
"    TR_FED_HUELGAJL\n" +
"WHERE\n" +
"     firma_contrato = 2\n" +
"     AND revision_contrato = 2\n" +
"     AND incumplim_contrato = 2\n" +
"     AND revision_salario = 2\n" +
"     AND reparto_utilidades = 2\n" +
"     AND apoyo_otra_huelga = 2\n" +
"     AND desequilibrio_fac_prod = 2\n" +
"     AND otro_motivo = 2 and(SUBSTR(CLAVE_ORGANO,0,2)='"+PValidacion.clave_entidad+"'  \n" +
" or clave_organo='"+PValidacion.clave_organo+"' )"; 
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

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
public class V3QParaprocesal {

    OracleConexionNE conexion = new OracleConexionNE();
    String sql;
    ArrayList<String[]> Array;
    ResultSet resul;

//Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche.
    public ArrayList Año_JudicialCampeche() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT * FROM (\n"
                + " SELECT * FROM( \n"
                + "        SELECT * FROM( \n"
                + "        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,\n"
                + "        SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  FECHA_APERTURA_EXPEDIENTE,\n"
                + "TO_NUMBER(SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4))+1  FECHA_APERTURA_EXPEDIENTES1,\n"
                + "        SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO, \n"
                + "        PERIODO \n"
                + "        from V3_TR_paraprocesaljl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='" + PValidacion.clave_entidad + "' and periodo = '" + PValidacion.periodo + "'  \n"
                + "        or clave_organo='" + PValidacion.clave_organo + "' and periodo = '" + PValidacion.periodo + "')  \n"
                + "        WHERE  FECHA_APERTURA_EXPEDIENTES  \n"
                + "        NOT BETWEEN     to_date('01/09/'||FECHA_APERTURA_EXPEDIENTE||'')\n"
                + "         AND  TO_date('01/08/'||(FECHA_APERTURA_EXPEDIENTES1)||'') ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTES"),});
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //Query de validacion donde el  año de la fecha de apertura sea diferente al año de la clave del expediente dependiendo del año judicial del estado de Campeche comprendiendo los años 2020,2021,2022. 
    public ArrayList Año_DIF_Campeche() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT * FROM (\n"
                + " SELECT * FROM( \n"
                + "        SELECT * FROM( \n"
                + "        select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4)  \n"
                + "        FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO, \n"
                + "        PERIODO \n"
                + "        from V3_TR_paraprocesaljl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO) WHERE SUBSTR(CLAVE_ORGANO,0,2)='" + PValidacion.clave_entidad + "' and periodo = '" + PValidacion.periodo + "'  \n"
                + "        or clave_organo='" + PValidacion.clave_organo + "' and periodo = '" + PValidacion.periodo + "') WHERE EXPE_AÑO NOT IN " + PValidacion.AñoJuridico + " ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTES"),});
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

//Query de validacion donde el año de la fecha de apertura es diferente al año de la clave del expediente.
    public ArrayList Año_Expe_ParaprocesalNE() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT * FROM(\n"
                + "SELECT * FROM(\n"
                + "select  clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') FECHA_APERTURA_EXPEDIENTES ,SUBSTR(TO_CHAR(FECHA_APERTURA_EXPEDIENTE, 'DD-MM-YYYY'),-4,4) \n"
                + "FECHA_APERTURA_EXPEDIENTE,SUBSTR(regexp_replace(expediente_clave, '[^0-9]', ''),-4,4)  EXPE_AÑO,\n"
                + "PERIODO\n"
                + "from V3_TR_paraprocesaljl ) WHERE  FECHA_APERTURA_EXPEDIENTE <>EXPE_AÑO AND EXPE_AÑO NOT IN ('2021','2022','2023','2020','2024','2025')) WHERE SUBSTR(CLAVE_ORGANO,0,2)='" + PValidacion.clave_entidad + "' and periodo = '" + PValidacion.periodo + "' \n"
                + "or clave_organo='" + PValidacion.clave_organo + "' and periodo = '" + PValidacion.periodo + "'";
        //System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTES"),});
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList FECHA_APERTURA_EXPEDIENTE_FUT() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,\n"
                + "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  V3_TR_PARAPROCESALJL  \n"
                + "WHERE \n"
                + "(FECHA_APERTURA_EXPEDIENTE > SYSDATE \n"
                + "AND FECHA_APERTURA_EXPEDIENTE <> '09/09/1899')\n"
                + "and  ((substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "'  and periodo='" + PValidacion.periodo + "' ) or  (clave_organo='" + PValidacion.clave_organo + "'  and periodo='" + PValidacion.periodo + "' ))";
        System.out.println(sql);
        resul = conexion.consultar(sql);
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

    public ArrayList FECHA_PRESENTA_SOLI_FUT() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_PRESENTA_SOLI,'DD/MM/YYYY')FECHA_PRESENTA_SOLI,\n"
                + "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  V3_TR_PARAPROCESALJL  \n"
                + "WHERE \n"
                + "(FECHA_PRESENTA_SOLI > SYSDATE \n"
                + "AND FECHA_PRESENTA_SOLI <> '09/09/1899')\n"
                + "and  ((substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "'  and periodo='" + PValidacion.periodo + "' ) or  (clave_organo='" + PValidacion.clave_organo + "'  and periodo='" + PValidacion.periodo + "' ))";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("periodo"),
                    resul.getString("FECHA_PRESENTA_SOLI")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList FECHA_ADMISION_SOLI_FUT() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_ADMISION_SOLI,'DD/MM/YYYY')FECHA_ADMISION_SOLI,\n"
                + "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  V3_TR_PARAPROCESALJL  \n"
                + "WHERE \n"
                + "(FECHA_ADMISION_SOLI > SYSDATE \n"
                + "AND FECHA_ADMISION_SOLI <> '09/09/1899')\n"
                + "and  ((substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "'  and periodo='" + PValidacion.periodo + "' ) or  (clave_organo='" + PValidacion.clave_organo + "'  and periodo='" + PValidacion.periodo + "' ))";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("periodo"),
                    resul.getString("FECHA_ADMISION_SOLI")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList FECHA_CONCLUSION_EXPE_FUT() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT clave_organo, expediente_clave, periodo, TO_CHAR(FECHA_CONCLUSION_EXPE,'DD/MM/YYYY')FECHA_CONCLUSION_EXPE,\n"
                + "TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM  V3_TR_PARAPROCESALJL  \n"
                + "WHERE \n"
                + "(FECHA_CONCLUSION_EXPE > SYSDATE \n"
                + "AND FECHA_CONCLUSION_EXPE <> '09/09/1899')\n"
                + "and  ((substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "'  and periodo='" + PValidacion.periodo + "' ) or  (clave_organo='" + PValidacion.clave_organo + "'  and periodo='" + PValidacion.periodo + "' ))";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("periodo"),
                    resul.getString("FECHA_CONCLUSION_EXPE")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList Duplicidad_expediente() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT CLAVE_ORGANO,EXPEDIENTE_CLAVE,TO_CHAR(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY')FECHA_APERTURA_EXPEDIENTE,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO FROM V3_TR_PARAPROCESALJL\n"
                + "WHERE CLAVE_ORGANO||TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))||PERIODO\n"
                + "IN(\n"
                + "SELECT CLAVE_ORGANO||EXPEDIENTE_CLAVE2||PERIODO FROM(\n"
                + "SELECT CLAVE_ORGANO,COUNT(expediente_clave2)CUENTAexpediente_clave2,expediente_clave2,PERIODO FROM(\n"
                + "select CLAVE_ORGANO,expediente_clave,TO_NUMBER(regexp_replace(expediente_clave, '[^0-9]', ''))expediente_clave2,PERIODO from V3_TR_PARAPROCESALJL \n"
                + "WHERE (SUBSTR(CLAVE_ORGANO,0,2)='" + PValidacion.clave_entidad + "' AND PERIODO='" + PValidacion.periodo + "') or (CLAVE_ORGANO='" + PValidacion.clave_organo + "' AND PERIODO='" + PValidacion.periodo + "')  \n"
                + "ORDER BY CLAVE_ORGANO,expediente_clave2)\n"
                + "GROUP BY CLAVE_ORGANO,expediente_clave2,PERIODO)WHERE CUENTAexpediente_clave2>1)\n"
                + "ORDER BY CLAVE_ORGANO,expediente_clave2";
        System.out.println(sql);
        resul = conexion.consultar(sql);
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

    public ArrayList Fecha_PresentacionNE() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (select ESTATUS_EXPEDIENTE,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_PRESENTA_SOLI,'dd/mm/yyyy')FECHA_PRESENTA_SOLI,PERIODO\n"
                + "from V3_TR_PARAPROCESALjl\n"
                + "WHERE to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy') < to_date(FECHA_PRESENTA_SOLI,'dd/mm/yyyy')) where  periodo = '" + PValidacion.periodo + "'\n"
                + "and FECHA_PRESENTA_SOLI <> '09/09/1899'  and clave_organo='" + PValidacion.clave_organo + "' or substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "' \n"
                + "and FECHA_PRESENTA_SOLI <> '09/09/1899'  and periodo = '" + PValidacion.periodo + "'\n";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ESTATUS_EXPEDIENTE"),
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                    resul.getString("FECHA_PRESENTA_SOLI")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ls fecha de admision de la demanda no debe de ser menor a la fecha depresentacion de la demanda
    public ArrayList Fecha_PresentacionAdmiNE() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (select ESTATUS_EXPEDIENTE,CLAVE_ORGANO,EXPEDIENTE_CLAVE,to_char(FECHA_ADMISION_SOLI,'dd/mm/yyyy')FECHA_ADMISION_SOLI,to_char(FECHA_PRESENTA_SOLI,'dd/mm/yyyy')FECHA_PRESENTA_SOLI,PERIODO \n"
                + "from V3_TR_PARAPROCESALJL \n"
                + "WHERE to_date(FECHA_ADMISION_SOLI,'dd/mm/yyyy') < to_date(FECHA_PRESENTA_SOLI,'dd/mm/yyyy')) where  periodo = '" + PValidacion.periodo + "'\n"
                + "and FECHA_PRESENTA_SOLI <> '09/09/1899'  and clave_organo='" + PValidacion.clave_organo + "' or substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "' \n"
                + "and FECHA_PRESENTA_SOLI <> '09/09/1899'  and periodo = '" + PValidacion.periodo + "'\n";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ESTATUS_EXPEDIENTE"),
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_ADMISION_SOLI"),
                    resul.getString("FECHA_PRESENTA_SOLI")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //Query de validacion donde la fecha de admision no debe de ser menor a la fecha de apertura del expediente.
    public ArrayList Fecha_Admision_SoliNE() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (select ESTATUS_EXPEDIENTE,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(FECHA_ADMISION_SOLI,'DD/MM/YYYY') FECHA_ADMISION_SOLI ,periodo\n"
                + "from V3_TR_paraprocesaljl where periodo = '" + PValidacion.periodo + "'\n"
                + "and FECHA_ADMISION_SOLI <> '09/09/1899'  and clave_organo='" + PValidacion.clave_organo + "' or substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "' \n"
                + "and FECHA_ADMISION_SOLI <> '09/09/1899'\n"
                + "and periodo = '" + PValidacion.periodo + "' ) where  to_date(FECHA_ADMISION_SOLI,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
        //System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ESTATUS_EXPEDIENTE"),
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                    resul.getString("FECHA_ADMISION_SOLI")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

//Query de validacion donde la fecha de conclusion del expediente no debe de ser menor a la fecha de apertura del expediente.
    public ArrayList Fecha_Conclusion_ExpeNE() {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (select ESTATUS_EXPEDIENTE,clave_organo,expediente_clave,to_char(FECHA_APERTURA_EXPEDIENTE,'DD/MM/YYYY') FECHA_APERTURA_EXPEDIENTE,to_char(Fecha_Conclusion_Expe,'DD/MM/YYYY') Fecha_Conclusion_Expe ,periodo, comentarios\n"
                + "from V3_TR_paraprocesaljl where periodo = '" + PValidacion.periodo + "'\n"
                + "and Fecha_Conclusion_Expe <> '09/09/1899'  and clave_organo='" + PValidacion.clave_organo + "' or substr(clave_organo,0,2)='" + PValidacion.clave_entidad + "' \n"
                + "and Fecha_Conclusion_Expe <> '09/09/1899'\n"
                + "and periodo = '" + PValidacion.periodo + "' ) where  to_date(Fecha_Conclusion_Expe,'dd/mm/yyyy')  < to_date(FECHA_APERTURA_EXPEDIENTE,'dd/mm/yyyy')";
        //System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ESTATUS_EXPEDIENTE"),
                    resul.getString("clave_organo"),
                    resul.getString("expediente_clave"),
                    resul.getString("FECHA_APERTURA_EXPEDIENTE"),
                    resul.getString("Fecha_Conclusion_Expe"),
                    resul.getString("comentarios")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }
}

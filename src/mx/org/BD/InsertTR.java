/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.BD;

import Conexion.OracleDAOFactory;
import Screen_laborales.PInsertTR;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mx.org.querys.V3Querys;

/**
 *
 * @author ANTONIO.CORIA
 */
public class InsertTR {
    
    public void InsertaTR() throws FileNotFoundException, IOException {
         
         
         
          try {
            if (PInsertTR.tabla.equals("INICIO()"))
            {
            Inserta_Inicio(); 
            }  
            if (PInsertTR.tabla.equals("V3_TR_CONTROL_EXPEDIENTEJL"))
            {
            Inserta_Control_expediente(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_AUDIENCIASJL"))
            {
            Inserta_Audiencias(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_ORDINARIOJL"))
            {
            Inserta_Ordinario(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_ACT_ORDINARIOJL"))
            {
            Inserta_Par_Act_Ordinario(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_DEM_ORDINARIOJL"))
            {
            Inserta_Par_Dem_Ordinario(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_INDIVIDUALJL"))
            {
            Inserta_Individual(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_ACT_INDIVIDUALJL"))
            {
            Inserta_Part_Act_Individual(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_DEM_INDIVIDUALJL"))
            {
            Inserta_Part_Dem_Individual(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_COLECTIVOJL"))
            {
            Inserta_Colectivo(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_ACT_COLECTIVOJL"))
            {
            Inserta_Part_Act_Colectivo(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_DEM_COLECTIVOJL"))
            {
            Inserta_Part_Dem_Colectivo(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_HUELGAJL"))
            {
            Inserta_Huelga(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_ACT_HUELGAJL"))
            {
            Inserta_Part_Act_Huelga(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_DEM_HUELGAJL"))
            {
            Inserta_Part_Dem_Huelga(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_COLECT_ECONOMJL"))
            {
            Inserta_Colect_Econom(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_ACT_COLECT_ECONOMJL"))
            {
            Inserta_Part_Act_Colect_Econom(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PART_DEM_COLECT_ECONOMJL"))
            {
            Inserta_Part_Dem_Colect_Econom(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_TERCERIASJL"))
            {
            Inserta_Tercerias(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_PARAPROCESALJL"))
            {
            Inserta_Paraprocesal(); 
            }
            if (PInsertTR.tabla.equals("V3_TR_EJECUCIONJL"))
            {
            Inserta_Ejecucion(); 
            }
            
         
                
            
          }catch (Exception ex) {
              System.out.println("+++"+ex);
        }
         
     }
     
     
    public void Inserta_Inicio() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALAB_INICIO(PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_INICIO(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Control_expediente() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_CONTROL_EXPEDIENTEJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Audiencias() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_AUDIENCIASJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Ordinario() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_ORDINARIOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Par_Act_Ordinario() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_ACT_ORDINARIOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Par_Dem_Ordinario() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_DEM_ORDINARIOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Individual() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_INDIVIDUALJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Act_Individual() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_ACT_INDIVIDUALJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Dem_Individual() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_DEM_INDIVIDUALJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Colectivo() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_COLECTIVOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Act_Colectivo() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_ACT_COLECTIVOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Dem_Colectivo() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_DEM_COLECTIVOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Huelga() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_HUELGAJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Act_Huelga() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_ACT_HUELGAJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Dem_Huelga() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_DEM_HUELGAJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Colect_Econom() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_COLECT_ECONOMJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Act_Colect_Econom() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_ACT_COLECT_ECONOMJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Part_Dem_Colect_Econom() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PART_DEM_COLECT_ECONOMJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Tercerias() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_TERCERIASJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Pref_Credito() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PREF_CREDITOJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Paraprocesal() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_PARAPROCESALJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }

    public void Inserta_Ejecucion() throws Exception {

        CallableStatement st;
        Connection con = null;
        con = OracleDAOFactory.creaConexion();
        V3Querys Querys = new V3Querys();
        try {
            System.out.println("1");
            Querys.Limpia_V3_ERRORES_INSERT_RALABTR("'" + PInsertTR.tabla + "'", PInsertTR.clave_entidad, PInsertTR.clave_organo, PInsertTR.periodo);
            st = con.prepareCall("{call V3_PKG_CARGA_LABORALES.PM_V3_TR_EJECUCIONJL(?, ?, ?)}");
            // Registra el parámetro de salida
            st.setString(1, PInsertTR.clave_organo);
            st.setString(2, PInsertTR.clave_entidad);
            st.setString(3, "");
            st.execute();
            JOptionPane.showMessageDialog(null, "Los Registros se han insertado correctamente, favor de revisar pantalla Errores de insert TR");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }

    }


     
}

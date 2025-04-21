/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.BD;

import Conexion.OracleDAOFactory;
import Screen_laborales.PAcumula;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO.CORIA
 */
public class AcumulaTR {
    
    
    
    public void Acumula() throws FileNotFoundException, IOException, SQLException {
    CallableStatement st=null;
    Connection con = null;
    con = OracleDAOFactory.creaConexion();    
    PAcumula PA=new PAcumula();
    
      try {
            System.out.println("1");
           if (PA.pru.equals("1"))
           {  
             st = con.prepareCall("{call Revisa_Acumulado_ralab_v3(?, ?, ?)}"); 
               System.out.println("{call Revisa_Acumulado_ralab_v3(?, ?, ?)}");
           }
           if (PA.pru.equals("0"))
           {
             st = con.prepareCall("{call Insert_Acumulado_ralab_v3(?, ?, ?)}");  
             System.out.println("{call Insert_Acumulado_ralab_v3(?, ?, ?)}");
           }    
            // Registra el parámetro de salida
           System.out.println("+++++++"+PA.clave_organo+PA.periodo+PA.periodo2);
            st.setString(1, PA.clave_organo);
            st.setString(2, PA.periodo2);
            st.setString(3, PA.periodo);
            st.execute();
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

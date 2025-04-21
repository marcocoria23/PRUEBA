/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.BD;

import Conexion.OracleConexion;
import Conexion.OracleDAOFactory;
import Screen_laborales.PInsertTMP;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import Bean_Procedures.Paraprocesal;
import Screen_laborales.cargando;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ANTONIO.CORIA
 */
public class ReadCSV_Paraprocesal {

    public static String impErro = "", RutaT = "";
    public static int TotalRegistros = 0;
    public static boolean borra_ruta = false;
    ArrayList Array;
    public static String rutaCarpetaArchivos = "";
    OracleConexion conexion = new OracleConexion();
    Convertir_utf8 conUTF8 = new Convertir_utf8();

    public void Read_Paraprocesal() throws FileNotFoundException, IOException {
        PInsertTMP Insert = new PInsertTMP();
        //FileInputStream f = new FileInputStream(Insert.rutaT);

       try {          
                System.out.println("1.....");
                if (Insert.CarpetaArchivos==true){
                     System.out.println("2.....");
                 rutaCarpetaArchivos=Insert.rutaT+"CSV_BD_T.6.1_parapro.csv";
                 System.out.println("+++++2"+rutaCarpetaArchivos);  
              }else{
                   System.out.println("3.....");  
                  rutaCarpetaArchivos=Insert.rutaT;
                   System.out.println("+++++3"+rutaCarpetaArchivos);  
              } 
              if (Insert.Bandera==false){
              System.out.println("4.....");     
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(rutaCarpetaArchivos))) {
            byte[] bytes = new byte[3];
            int bytesRead = inputStream.read(bytes);
            
            if (bytesRead >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                System.out.println("Archivo en UTF-8");
                 System.out.println("5.....");
                 IN_PARAPROCESAL(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("6.....");
                conUTF8.Convertir_utf8_EBaseDatos(rutaCarpetaArchivos);
                 IN_PARAPROCESAL(conUTF8.rutaNuevoArchivo,Insert.Periodo);
                 rutaCarpetaArchivos=conUTF8.rutaNuevoArchivo;
                          
            }
            } catch (IOException e) {
            e.printStackTrace();
        }    
              }else{
                   System.out.println("7.....");
              try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(rutaCarpetaArchivos))) {
            byte[] bytes = new byte[3];
            int bytesRead = inputStream.read(bytes);
            
            if (bytesRead >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                 System.out.println("8.....");
                 System.out.println("Archivo en UTF-8"); 
                  IN_PARAPROCESAL(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("9.....");
               conUTF8.Convertir_utf8(rutaCarpetaArchivos);
              IN_PARAPROCESAL(conUTF8.rutaNuevoArchivo,Insert.Periodo);         
               rutaCarpetaArchivos=conUTF8.rutaNuevoArchivo; 
            }     
             } catch (IOException e) {
            e.printStackTrace();
        }      
                System.out.println("++++++"+rutaCarpetaArchivos);
              }            
        } catch (Exception ex) {
        }    
     

    }

    public void IN_PARAPROCESAL(String Ruta, String Periodo) throws Exception {

        String rutaArchivoCSV = Ruta;
        ArrayList<String[]> Array;
        Array = new ArrayList();
        ARRAY array_to_pass;
        CallableStatement st;
        Connection con = null;
        STRUCT[] structs;
        StructDescriptor sd;
        ArrayDescriptor descriptor;
        TotalRegistros = 0;

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(Ruta))) {
            byte[] bytes = new byte[3];
            int bytesRead = inputStream.read(bytes);

            if (bytesRead >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                System.out.println("El archivo parece estar en UTF-8.");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivoCSV), StandardCharsets.UTF_8));
                        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
                    int numeroColumnas = 0;
                    CSVRecord firstRecord = csvParser.iterator().next();
                    numeroColumnas = firstRecord.size();
                    if (numeroColumnas == 20) {
                        System.out.println("+hellooou+" + numeroColumnas);
                        cargando cargar = new cargando();
                        
                        ArrayList<Paraprocesal> ad = new ArrayList<>();
                        for (CSVRecord record : csvParser) {
                            //System.out.println("llenado de csv");
                            TotalRegistros++;
                            Paraprocesal c = new Paraprocesal();
                            c.SetNOMBRE_ORGANO_JURIS(record.get(0).toUpperCase());
                            c.SetCLAVE_ORGANO(record.get(1).toUpperCase());
                            c.SetEXPEDIENTE_CLAVE(record.get(2).toUpperCase().replace("\\n", "").trim());
                            c.SetFECHA_APERTURA_EXPEDIENTE(record.get(3).toUpperCase());
                            c.SetRAMA_INVOLUCRAD(record.get(4).toUpperCase());
                            c.SetSECTOR_RAMA(record.get(5).toUpperCase());
                            c.SetSUBSECTOR_RAMA(record.get(6).toUpperCase());
                            c.SetMOTIVO_SOLICITUD(record.get(7).toUpperCase());
                            c.SetESPECIFIQUE_MOTIVO(record.get(8).toUpperCase());
                            c.SetINCOMPETENCIA(record.get(9).toUpperCase());
                            c.SetTIPO_INCOMPETENCIA(record.get(10).toUpperCase());
                            c.SetESPECIFIQUE_INCOMP(record.get(11).toUpperCase());
                            c.SetFECHA_PRESENTA_SOLI(record.get(12).toUpperCase());
                            c.SetFECHA_ADMISION_SOLI(record.get(13).toUpperCase());
                            c.SetPROMOVENTE(record.get(14).toUpperCase());
                            c.SetESPECIFIQUE_PROMOVENTE(record.get(15).toUpperCase());
                            c.SetESTATUS_EXPEDIENTE(record.get(16).toUpperCase());
                            c.SetFECHA_CONCLUSION_EXPE(record.get(17).toUpperCase());
                            c.SetCOMENTARIOS(record.get(18).toUpperCase());
                            c.SetPERIODO(Periodo);

                            // System.out.println(record.get(0) + ":" + record.get(1) + ":" + record.get(2) + ":" + record.get(3) + ":" + record.get(4) + ":" + record.get(5) + ":" + record.get(6) + ":" + record.get(7) + ":" + record.get(8) + ":" + record.get(9) + ":" + record.get(10) + ":" + record.get(11) + ":" + record.get(12) + ":" + record.get(13) + ":" + record.get(14) + ":" + record.get(15));
                            ad.add(c);
                        }
                        if (TotalRegistros > 0) {
                            System.out.println("entro 1");
                            cargar.setVisible(true);
                            con = OracleDAOFactory.creaConexion();
                            sd = StructDescriptor.createDescriptor("OBJ_PARAPROCESAL", con);
                            structs = new STRUCT[ad.size()];
                            System.out.println("entro 2");
                            System.out.println("tamaño " + ad.size());

                            for (int i = 0; i < ad.size(); i++) {
                                structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                            }

                            System.out.println("entro 3");
                            descriptor = ArrayDescriptor.createDescriptor("ARR_OBJ_PARAPROCESAL", con);
                            System.out.println("entro 4");
                            array_to_pass = new ARRAY(descriptor, con, structs);
                            System.out.println("entro 5");
                            st = con.prepareCall("{? = call(PKG_INTEGRADORCSV.Paraprocesal(?))}");
                            System.out.println("entro 6");
                            st.registerOutParameter(1, OracleTypes.INTEGER);
                            System.out.println("entro 7");
                            st.setArray(2, array_to_pass);
                            System.out.println("entro 8");
                            st.execute();
                            System.out.println("entro 9");
                            cargar.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Favor de revisar ventana -*Errores de insert*- Total registros en .CSV:" + TotalRegistros);
                        } else {
                            JOptionPane.showMessageDialog(null, "Archivo .CSV sin Registros");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El total de numero de columnas en el archivo .CSV no coincide con la bd Oracle");
                    }
                } catch (IOException e) {
                    System.out.println("++" + e);
                } finally {
                    try {
                        array_to_pass = null;
                        structs = null;
                        descriptor = null;
                        if (con != null) {
                            System.out.println("cierraaa");
                            JOptionPane.showMessageDialog(null, "CONEXION CERRADA!!");
                            con.close();
                            con = null;
                        }
                    } catch (SQLException ex) {
                        throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo no esta en formato UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

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
import Bean_Procedures.Part_Act_Huelga;
import Screen_laborales.cargando;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ANTONIO.CORIA
 */
public class ReadCSV_Part_Act_Huelga {

    public static String impErro = "", RutaT = "";
    public static int TotalRegistros = 0;
    public static boolean borra_ruta = false;
    ArrayList Array;
    public static String rutaCarpetaArchivos = "";
    OracleConexion conexion = new OracleConexion();
    Convertir_utf8 conUTF8 = new Convertir_utf8();

    public void Read_Part_Act_Huelga() throws FileNotFoundException, IOException {
        PInsertTMP Insert = new PInsertTMP();
       // FileInputStream f = new FileInputStream(Insert.rutaT);

        try {          
                System.out.println("1.....");
                if (Insert.CarpetaArchivos==true){
                     System.out.println("2.....");
                 rutaCarpetaArchivos=Insert.rutaT+"CSV_BD_T.4.2_part_act_huelga.csv";
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
                 IN_PART_ACT_HUELGA(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("6.....");
                conUTF8.Convertir_utf8_EBaseDatos(rutaCarpetaArchivos);
                 IN_PART_ACT_HUELGA(conUTF8.rutaNuevoArchivo,Insert.Periodo);
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
                  IN_PART_ACT_HUELGA(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("9.....");
               conUTF8.Convertir_utf8(rutaCarpetaArchivos);
              IN_PART_ACT_HUELGA(conUTF8.rutaNuevoArchivo,Insert.Periodo);         
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

    public void IN_PART_ACT_HUELGA(String Ruta, String Periodo) throws Exception {

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
        boolean Inserta = true;

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
                    if (numeroColumnas == 19) {
                        System.out.println("+hellooou+" + numeroColumnas);
                        cargando cargar = new cargando();
                        
                        ArrayList<Part_Act_Huelga> ad = new ArrayList<>();
                        for (CSVRecord record : csvParser) {
                            //System.out.println("llenado de csv");
                            TotalRegistros++;
                            Part_Act_Huelga c = new Part_Act_Huelga();
                            c.SetNOMBRE_ORGANO_JURIS(record.get(0).toUpperCase());
                            c.SetCLAVE_ORGANO(record.get(1).toUpperCase());
                            c.SetEXPEDIENTE_CLAVE(record.get(2).toUpperCase().replace("\\n", "").trim());
                            c.SetID_ACTOR(record.get(3).toUpperCase());
                            c.SetACTOR(record.get(4).toUpperCase());
                            c.SetDEFENSA_ACT(record.get(5).toUpperCase());
                            c.SetNOMBRE_SINDICATO(record.get(6).toUpperCase());
                            c.SetREG_ASOC_SINDICAL(record.get(7).toUpperCase());
                            c.SetTIPO_SINDICATO(record.get(8).toUpperCase());
                            c.SetOTRO_ESP_SINDICATO(record.get(9).toUpperCase());
                            c.SetORG_OBRERA(record.get(10).toUpperCase());
                            c.SetNOMBRE_ORG_OBRERA(record.get(11).toUpperCase());
                            c.SetOTRO_ESP_OBRERA(record.get(12).toUpperCase());
                            c.SetCANT_TRABAJA_INV(record.get(13).toUpperCase());
                            c.SetHOMBRES(record.get(14).toUpperCase());
                            c.SetMUJERES(record.get(15).toUpperCase());
                            c.SetNO_IDENTIFICADO(record.get(16).toUpperCase());
                            c.SetCOMENTARIOS(record.get(17).toUpperCase());
                            c.SetPERIODO(Periodo);
                            // System.out.println(record.get(0) + ":" + record.get(1) + ":" + record.get(2) + ":" + record.get(3) + ":" + record.get(4) + ":" + record.get(5) + ":" + record.get(6) + ":" + record.get(7) + ":" + record.get(8) + ":" + record.get(9) + ":" + record.get(10) + ":" + record.get(11) + ":" + record.get(12) + ":" + record.get(13) + ":" + record.get(14) + ":" + record.get(15));
                            ad.add(c);
                            if (record.get(4).equals("1") || record.get(4).equals("2") || record.get(4).equals("4") || record.get(4).equals("6")) {
                                JOptionPane.showMessageDialog(null, "Error en el campo ACTOR fuera de catalogo  Clave_organo:" + record.get(1) + " Expediente:" + record.get(2) + "Id_actor:" + record.get(3) + " nota: campo ACTOR solo puede tener opcion 3,5,7 Y 99");
                                Inserta = false;
                            }
                        }
                        if (TotalRegistros > 0) {
                            System.out.println("entro 1");
                            cargar.setVisible(true);
                            con = OracleDAOFactory.creaConexion();
                            sd = StructDescriptor.createDescriptor("OBJ_PART_ACT_HUELGA", con);
                            structs = new STRUCT[ad.size()];
                            System.out.println("entro 2");
                            System.out.println("tamaño " + ad.size());

                            for (int i = 0; i < ad.size(); i++) {
                                structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                            }

                            System.out.println("entro 3");
                            descriptor = ArrayDescriptor.createDescriptor("ARR_OBJ_PART_ACT_HUELGA", con);
                            System.out.println("entro 4");
                            array_to_pass = new ARRAY(descriptor, con, structs);
                            System.out.println("entro 5");
                            st = con.prepareCall("{? = call(PKG_INTEGRADORCSV.Part_Act_Huelga(?))}");
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

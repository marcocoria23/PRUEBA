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
import Bean_Procedures.Individual;
import Screen_laborales.cargando;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ANTONIO.CORIA
 */
public class ReadCSV_Individual {

    public static String impErro = "", RutaT = "";
    public static int TotalRegistros = 0;
    public static boolean borra_ruta = false;
    ArrayList Array;
    public static String rutaCarpetaArchivos = "";
    OracleConexion conexion = new OracleConexion();
    Convertir_utf8 conUTF8 = new Convertir_utf8();

    public void Read_Individual() throws FileNotFoundException, IOException {
        PInsertTMP Insert = new PInsertTMP();
       // FileInputStream f = new FileInputStream(Insert.rutaT);

         try {          
                System.out.println("1.....");
                if (Insert.CarpetaArchivos==true){
                     System.out.println("2.....");
                 rutaCarpetaArchivos=Insert.rutaT+"CSV_BD_T.2.1_esp_indiv.csv";
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
                 IN_INDIVIDUAL(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("6.....");
                conUTF8.Convertir_utf8_EBaseDatos(rutaCarpetaArchivos);
                 IN_INDIVIDUAL(conUTF8.rutaNuevoArchivo,Insert.Periodo);
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
                  IN_INDIVIDUAL(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("9.....");
               conUTF8.Convertir_utf8(rutaCarpetaArchivos);
              IN_INDIVIDUAL(conUTF8.rutaNuevoArchivo,Insert.Periodo);         
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

    public void IN_INDIVIDUAL(String Ruta, String Periodo) throws Exception {

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
                    if (numeroColumnas == 79) {
                        System.out.println("+hellooou+" + numeroColumnas);
                        cargando cargar = new cargando();
                        
                        ArrayList<Individual> ad = new ArrayList<>();
                        for (CSVRecord record : csvParser) {
                            //System.out.println("llenado de csv");
                            TotalRegistros++;
                            Individual c = new Individual();
                            c.SetNOMBRE_ORGANO_JURIS(record.get(0).toUpperCase());
                            c.SetCLAVE_ORGANO(record.get(1).toUpperCase());
                            c.SetEXPEDIENTE_CLAVE(record.get(2).toUpperCase().replace("\\n", "").trim());
                            c.SetFECHA_APERTURA_EXPEDIENTE(record.get(3).toUpperCase());
                            c.SetTIPO_ASUNTO(record.get(4).toUpperCase());
                            c.SetNAT_CONFLICTO(record.get(5).toUpperCase());
                            c.SetCONTRATO_ESCRITO(record.get(6).toUpperCase());
                            c.SetTIPO_CONTRATO(record.get(7).toUpperCase());
                            c.SetRAMA_INDUS_INVOLUCRADA(record.get(8).toUpperCase());
                            c.SetSECTOR_RAMA(record.get(9).toUpperCase());
                            c.SetSUBSECTOR_RAMA(record.get(10).toUpperCase());
                            c.SetENTIDAD_CLAVE(record.get(11).toUpperCase());
                            c.SetENTIDAD_NOMBRE(record.get(12).toUpperCase());
                            c.SetMUNICIPIO_CLAVE(record.get(13).toUpperCase());
                            c.SetMUNICIPIO_NOMBRE(record.get(14).toUpperCase());
                            c.SetSUBCONTRATACION(record.get(15).toUpperCase());
                            c.SetINDOLE_TRABAJO(record.get(16).toUpperCase());
                            c.SetPRESTACION_FP(record.get(17).toUpperCase());
                            c.SetARRENDAM_TRAB(record.get(18).toUpperCase());
                            c.SetCAPACITACION(record.get(19).toUpperCase());
                            c.SetANTIGUEDAD(record.get(20).toUpperCase());
                            c.SetPRIMA_ANTIGUEDAD(record.get(21).toUpperCase());
                            c.SetCONVENIO_TRAB(record.get(22).toUpperCase());
                            c.SetDESIGNACION_TRAB_FALLE(record.get(23).toUpperCase());
                            c.SetDESIGNACION_TRAB_ACT_DELIC(record.get(24).toUpperCase());
                            c.SetTERMINACION_LAB(record.get(25).toUpperCase());
                            c.SetRECUPERACION_CARGA(record.get(26).toUpperCase());
                            c.SetGASTOS_TRASLADOS(record.get(27).toUpperCase());
                            c.SetINDEMNIZACION(record.get(28).toUpperCase());
                            c.SetPAGO_INDEMNIZACION(record.get(29).toUpperCase());
                            c.SetDESACUERDO_MEDICOS(record.get(30).toUpperCase());
                            c.SetCOBRO_PRESTACIONES(record.get(31).toUpperCase());
                            c.SetCONF_SEGURO_SOCIAL(record.get(32).toUpperCase());
                            c.SetOTRO_CONF(record.get(33).toUpperCase());
                            c.SetOTRO_ESP_CONF(record.get(34).toUpperCase());
                            c.SetINCOMPETENCIA(record.get(35).toUpperCase());
                            c.SetTIPO_INCOMPETENCIA(record.get(36).toUpperCase());
                            c.SetOTRO_ESP_INCOMP(record.get(37).toUpperCase());
                            c.SetFECHA_PRES_DEMANDA(record.get(38).toUpperCase());
                            c.SetCONSTANCIA_CONS_EXPEDIDA(record.get(39).toUpperCase());
                            c.SetCONSTANCIA_CLAVE(record.get(40).toUpperCase());
                            c.SetASUN_EXCEP_CONCILIACION(record.get(41).toUpperCase());
                            c.SetPREVE_DEMANDA(record.get(42).toUpperCase());
                            c.SetDESAHOGO_PREV_DEMANDA(record.get(43).toUpperCase());
                            c.SetESTATUS_DEMANDA(record.get(44).toUpperCase());
                            c.SetCAU_IMPI_ADMI_DEMANDA(record.get(45).toUpperCase());
                            c.SetFECHA_ADMI_DEMANDA(record.get(46).toUpperCase());
                            c.SetCANTIDAD_ACTORES(record.get(47).toUpperCase());
                            c.SetCANTIDAD_DEMANDADOS(record.get(48).toUpperCase());
                            c.SetTRAMITACION_DEPURACION(record.get(49).toUpperCase());
                            c.SetFECHA_DEPURACION(record.get(50).toUpperCase());
                            c.SetAUDIENCIA_PRELIM(record.get(51).toUpperCase());
                            c.SetFECHA_AUDIENCIA_PRELIM(record.get(52).toUpperCase());
                            c.SetAUDIENCIA_JUICIO(record.get(53).toUpperCase());
                            c.SetFECHA_AUDIENCIA_JUICIO(record.get(54).toUpperCase());
                            c.SetESTATUS_EXPEDIENTE(record.get(55).toUpperCase());
                            c.SetFECHA_ACTO_PROCESAL(record.get(56).toUpperCase());
                            c.SetFASE_SOLI_EXPEDIENTE(record.get(57).toUpperCase());
                            c.SetFORMA_SOLUCION_AD(record.get(58).toUpperCase());
                            c.SetOTRO_ESP_SOLUCION_AD(record.get(59).toUpperCase());
                            c.SetFECHA_DICTO_RESOLUCION_AD(record.get(60).toUpperCase());
                            c.SetTIPO_SENTENCIA_AD(record.get(61).toUpperCase());
                            c.SetMONTO_SOLUCION_AD(record.get(62).toUpperCase());
                            c.SetFORMA_SOLUCION_TA(record.get(63).toUpperCase());
                            c.SetOTRO_ESP_SOLUCION_TA(record.get(64).toUpperCase());
                            c.SetFECHA_RESOLUCION_TA(record.get(65).toUpperCase());
                            c.SetTIPO_SENTENCIA_TA(record.get(66).toUpperCase());
                            c.SetMONTO_SOLUCIÓN_TA(record.get(67).toUpperCase());
                            c.SetFORMA_SOLUCION_AP(record.get(68).toUpperCase());
                            c.SetOTRO_ESP_SOLUCION_AP(record.get(69).toUpperCase());
                            c.SetFECHA_DICTO_RESOLUCION_AP(record.get(70).toUpperCase());
                            c.SetMONTO_SOLUCION_AP(record.get(71).toUpperCase());
                            c.SetFORMA_SOLUCION_AJ(record.get(72).toUpperCase());
                            c.SetOTRO_ESP_SOLUCION_AJ(record.get(73).toUpperCase());
                            c.SetFECHA_DICTO_RESOLUCION_AJ(record.get(74).toUpperCase());
                            c.SetTIPO_SENTENCIA_AJ(record.get(75).toUpperCase());
                            c.SetMONTO_SOLUCIÓN_AJ(record.get(76).toUpperCase());
                            c.SetCOMENTARIOS(record.get(77).toUpperCase());
                            c.SetPERIODO(Periodo);

                            System.out.println(record.get(0) + ":" + record.get(1) + ":" + record.get(2) + ":" + record.get(3) + ":" + record.get(4) + ":" + record.get(5) + ":" + record.get(6) + ":" + record.get(7) + ":" + record.get(8) + ":" + record.get(9) + ":" + record.get(10) + ":" + record.get(11) + ":" + record.get(12) + ":" + record.get(13) + ":" + record.get(14) + ":" + record.get(15) + ":" + record.get(16) + ":" + record.get(17) + ":" + record.get(18) + ":" + record.get(19) + ":" + record.get(20) + ":" + record.get(21) + ":" + record.get(22) + ":" + record.get(23) + ":" + record.get(24) + ":" + record.get(25) + ":" + record.get(26) + ":" + record.get(27) + ":" + record.get(28) + ":" + record.get(29) + ":" + record.get(30) + ":" + record.get(31) + ":" + record.get(32) + ":" + record.get(33) + ":" + record.get(34) + ":" + record.get(35) + ":" + record.get(36) + ":" + record.get(37) + ":" + record.get(38) + ":" + record.get(39) + ":" + record.get(40) + ":" + record.get(41) + ":" + record.get(42) + ":" + record.get(43) + ":" + record.get(44) + ":" + record.get(45) + ":" + record.get(46) + ":" + record.get(47) + ":" + record.get(48) + ":" + record.get(49) + ":" + record.get(50) + ":" + record.get(51) + ":" + record.get(52) + ":" + record.get(53) + ":" + record.get(54) + ":" + record.get(55) + ":" + record.get(56) + ":" + record.get(57) + ":" + record.get(58) + ":" + record.get(59) + ":" + record.get(60) + ":" + record.get(61) + ":" + record.get(62) + ":" + record.get(63) + ":" + record.get(64) + ":" + record.get(65) + ":" + record.get(66) + ":" + record.get(67) + ":" + record.get(68) + ":" + record.get(69) + ":" + record.get(70) + ":" + record.get(71) + ":" + record.get(72) + ":" + record.get(73) + ":" + record.get(74) + ":" + record.get(75) + ":" + record.get(76) + ":" + record.get(77) + ":" + Periodo );
                            ad.add(c);
                            if (record.get(68).equals("1")) {
                                JOptionPane.showMessageDialog(null, "Error en el campo FORMA_SOLUCION_AP fuera de catalogo  Clave_organo:" + record.get(1) + " Expediente:" + record.get(2) + " nota: campo FORMA_SOLUCION_AP solo puede tener opcion 2,3,4,5 Y 9");
                                Inserta = false;
                            }

                        }
                        System.out.println("entro 1");
                        if (TotalRegistros > 0) {
                            if (Inserta == true) {
                                cargar.setVisible(true);
                                con = OracleDAOFactory.creaConexion();
                                sd = StructDescriptor.createDescriptor("OBJ_INDIVIDUAL", con);
                                structs = new STRUCT[ad.size()];
                                System.out.println("entro 2");
                                System.out.println("tamaño " + ad.size());

                                for (int i = 0; i < ad.size(); i++) {
                                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                                }

                                System.out.println("entro 3");
                                descriptor = ArrayDescriptor.createDescriptor("ARR_OBJ_INDIVIDUAL", con);
                                System.out.println("entro 4");
                                array_to_pass = new ARRAY(descriptor, con, structs);
                                System.out.println("entro 5");
                                st = con.prepareCall("{? = call(PKG_INTEGRADORCSV.Individual(?))}");
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
                                System.out.println("Cierra insert pantalla" + Inserta);
                                cargar.setVisible(false);
                            }
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

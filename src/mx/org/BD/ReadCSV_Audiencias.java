/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
CUANDO EL PROGRAMA SE QUEDA EN ST.EXECUTE POR CADA PROCEDIMIENTO FAVOR DE REVISAR DOCUMENTO YA QUE PUEDE SER LA TABLA
 TMP POR EL RAISE.APLICATION Y POR QUE LOS CAMPOS FECHA EN ,CSV NO ESTAN EN FORMATO FECHA
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
import Bean_Procedures.Audiencias;
import Screen_laborales.cargando;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ANTONIO.CORIA
 */
public class ReadCSV_Audiencias {

    public static String impErro = "",RutaT="";
    public static int TotalRegistros=0;
    public static boolean borra_ruta=false;
    ArrayList Array;
    public static String rutaCarpetaArchivos="";
    OracleConexion conexion = new OracleConexion();
 Convertir_utf8 conUTF8=new Convertir_utf8();
 
    public void Read_Audiencias() throws FileNotFoundException, IOException {
        PInsertTMP Insert = new PInsertTMP();
        //FileInputStream f = new FileInputStream(Insert.rutaT);  
       
            try {          
                System.out.println("1.....");
                if (Insert.CarpetaArchivos==true){
                     System.out.println("2.....");
                 rutaCarpetaArchivos=Insert.rutaT+"CSV_BD_T.1.0_audiencias.csv";
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
                 IN_AUDIENCIAS(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("6.....");
                conUTF8.Convertir_utf8_EBaseDatos(rutaCarpetaArchivos);
                 IN_AUDIENCIAS(conUTF8.rutaNuevoArchivo,Insert.Periodo);
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
                  IN_AUDIENCIAS(rutaCarpetaArchivos,Insert.Periodo);
            }else{
                 System.out.println("9.....");
               conUTF8.Convertir_utf8(rutaCarpetaArchivos);
              IN_AUDIENCIAS(conUTF8.rutaNuevoArchivo,Insert.Periodo);         
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
    
    
   

    public void IN_AUDIENCIAS(String Ruta,String Periodo) throws Exception {

        String rutaArchivoCSV = Ruta;
        ArrayList<String[]> Array;
        Array = new ArrayList();
        ARRAY array_to_pass;
        CallableStatement st;
        Connection con = null;
        STRUCT[] structs;
        StructDescriptor sd;
        ArrayDescriptor descriptor;
        TotalRegistros=0;
        boolean Inserta=true;

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(Ruta))) {
            byte[] bytes = new byte[3];
            int bytesRead = inputStream.read(bytes);

            if (bytesRead >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                System.out.println("El archivo parece estar en UTF-8.");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivoCSV), StandardCharsets.UTF_8));
                        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
                    int  numeroColumnas = 0;
                    CSVRecord firstRecord = csvParser.iterator().next();
                    numeroColumnas = firstRecord.size();
                    System.out.println("numcol"+numeroColumnas);
                    if (numeroColumnas == 16) {
                        System.out.println("+hellooou+" + numeroColumnas);
                        cargando cargar = new cargando();
                        
                        ArrayList<Audiencias> ad = new ArrayList<>();
                        for (CSVRecord record : csvParser) {
                            System.out.println("llenado de csv");
                            TotalRegistros++;
                            Audiencias c = new Audiencias();
                            c.SetNOMBRE_ORGANO_JURIS(record.get(0).toUpperCase());
                            c.SetCLAVE_ORGANO(record.get(1).toUpperCase());
                            c.SetEXPEDIENTE_CLAVE(record.get(2).toUpperCase().replace("\\n","").trim());
                            c.SetTIPO_PROCED(record.get(3).toUpperCase());
                            c.SetID_AUDIENCIA(record.get(4).toUpperCase());
                            c.SetORDINARIO_TA(record.get(5).toUpperCase());
                            c.SetESPECIAL_INDIVI_TA(record.get(6).toUpperCase());
                            c.SetESPECIAL_COLECT_TA(record.get(7).toUpperCase());
                            c.SetHUELGA_TA(record.get(8).toUpperCase());
                            c.SetCOL_NATU_ECONOMICA_TA(record.get(9).toUpperCase());
                            c.SetESP_OTRO_AUDIENCIA(record.get(10).toUpperCase());
                            c.SetFECHA_AUDIEN_CELEBRADA(record.get(11).toUpperCase());
                            c.SetINICIO(record.get(12).toUpperCase());
                            c.SetCONCLU(record.get(13).toUpperCase());
                            c.SetCOMENTARIOS(record.get(14).toUpperCase());
                            c.SetPERIODO(Periodo);
                            System.out.println(record.get(0) + ":" + record.get(1) + ":" + record.get(2) + ":" + record.get(3) + ":" + record.get(4) + ":" + record.get(5) + ":" + record.get(6) + ":" + record.get(7) + ":" + record.get(8) + ":" + record.get(9) + ":" + record.get(10) + ":" + record.get(11) + ":" + record.get(12) + ":" + record.get(13) + ":" + record.get(14) + ":" + Periodo);
                            ad.add(c);
                           if(record.get(3).equals("1") && !(record.get(5).equals("") || record.get(5).equals("1") || record.get(5).equals("2") || record.get(5).equals("6") || record.get(5).equals("9")) ){
                               
                               if(record.get(3).equals("1") && (record.get(5).equals("3")))
                                       {
                                        c.SetORDINARIO_TA("6"); 
                                        c.SetESP_OTRO_AUDIENCIA("Audiencia de conciliación");
                                       }
                               if(record.get(3).equals("1") && (record.get(5).equals("4")))
                                       {
                                        c.SetORDINARIO_TA("6"); 
                                        c.SetESP_OTRO_AUDIENCIA("Audiencia conforme al artículo 937 (LFT)");
                                       }
                               if(record.get(3).equals("1") && (record.get(5).equals("5")))
                                       {
                                        c.SetORDINARIO_TA("6"); 
                                        c.SetESP_OTRO_AUDIENCIA("Audiencia dentro del procedimiento colectivo de naturaleza económica");
                                       }
                               
                               JOptionPane.showMessageDialog(null, "Error en el campo ORDINARIO_TA fuera de catalogo  Clave_organo:"+record.get(1) +" Expediente:"+record.get(2)+ " Id_audiencia:"+record.get(4)+ " nota: campo ORDINARIO_TA solo puede tener opcion 1,2,6 Y 9");
                               
                                Inserta=false;
                                    }
                            if(record.get(3).equals("2") && !(record.get(6).equals("") || record.get(6).equals("1") || record.get(6).equals("2") || record.get(6).equals("6") || record.get(6).equals("9")) ){
                                JOptionPane.showMessageDialog(null, "Error en el campo ESPECIAL_INDIVI_TA fuera de catalogo  Clave_organo:"+record.get(1) +" Expediente:"+record.get(2)+ " Id_audiencia:"+record.get(4)+ " nota: campo ESPECIAL_INDIVI_TA solo puede tener opcion 1,2,6 Y 9");
                                Inserta=false;
                                    }
                           if(record.get(3).equals("3") && !(record.get(7).equals("") || record.get(7).equals("2") || record.get(7).equals("6") || record.get(7).equals("9")) ){
                                JOptionPane.showMessageDialog(null, "Error en el campo ESPECIAL_COLECT_TA fuera de catalogo  Clave_organo:"+record.get(1) +" Expediente:"+record.get(2)+ " Id_audiencia:"+record.get(4)+ " nota: campo ESPECIAL_COLECT_TA solo puede tener opcion 2,6 Y 9");
                                Inserta=false;
                                    }
                           if(record.get(3).equals("4") && !(record.get(8).equals("") || record.get(8).equals("3") || record.get(8).equals("4") || record.get(8).equals("6") || record.get(8).equals("9")) ){
                                JOptionPane.showMessageDialog(null, "Error en el campo HUELGA_TA fuera de catalogo  Clave_organo:"+record.get(1) +" Expediente:"+record.get(2)+ " Id_audiencia:"+record.get(4)+ " nota: campo HUELGA_TA solo puede tener opcion 3,4,6 Y 9");
                                Inserta=false;
                                    }
                          if(record.get(3).equals("5") && !(record.get(9).equals("") || record.get(9).equals("5") || record.get(9).equals("6") || record.get(9).equals("9")) ){
                                JOptionPane.showMessageDialog(null, "Error en el campo COL_NATU_ECONOMICA_TA fuera de catalogo  Clave_organo:"+record.get(1) +" Expediente:"+record.get(2)+ " Id_audiencia:"+record.get(4)+ " nota: campo COL_NATU_ECONOMICA_TA solo puede tener opcion 5,6 Y 9");
                                Inserta=false;
                                    }   
                        }
                        
                 System.out.println("entro 1");
                if(TotalRegistros>0){ 
                 if (Inserta==true){
                        cargar.setVisible(true);
                        con = OracleDAOFactory.creaConexion();
                        sd = StructDescriptor.createDescriptor("OBJ_AUDIENCIAS", con);
                        structs = new STRUCT[ad.size()];
                        System.out.println("entro 2");
                        System.out.println("tamaño " + ad.size());

                        for (int i = 0; i < ad.size(); i++) {
                            structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                        }

                        System.out.println("entro 3");
                        descriptor = ArrayDescriptor.createDescriptor("ARR_OBJ_AUDIENCIAS", con);
                        System.out.println("entro 4");
                        array_to_pass = new ARRAY(descriptor, con, structs);
                        System.out.println("entro 5");
                        st = con.prepareCall("{? = call(PKG_INTEGRADORCSV.Audiencias(?))}");
                        System.out.println("entro 6");
                        st.registerOutParameter(1, OracleTypes.INTEGER);
                        System.out.println("entro 7");
                        st.setArray(2, array_to_pass);
                        System.out.println("entro 8");
                        st.execute();
                        System.out.println("entro 9");
                        cargar.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Registros insertados V3_TMP_AUDIENCIASJL"
                                + " Favor de revisar ventana -*Errores de insert*- Total registros en .CSV:" + TotalRegistros);
                  }else{
                        cargar.setVisible(false);
                                  }  
                 }else{
                 JOptionPane.showMessageDialog(null, "Archivo .CSV sin Registros-V3_TMP_AUDIENCIASJL");
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
                            JOptionPane.showMessageDialog(null, "CONEXION CERRADA!!-V3_TMP_AUDIENCIASJL");
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
    
    
    


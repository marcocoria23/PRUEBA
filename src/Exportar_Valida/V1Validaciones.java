/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exportar_Valida;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import java.awt.FileDialog;
import java.io.DataOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import mx.org.querys.V1QOrdinario;
import mx.org.querys.V1QDesgloses;
import mx.org.querys.V1QIndividual;
import mx.org.querys.V1QColectivo;
import mx.org.querys.V1QHuelga;
import mx.org.querys.V1QParaprocesal;
import mx.org.querys.V1QSsocial;
import mx.org.querys.V1QEjecucion;
import mx.org.querys.V1QColec_Econom;
import Screen_laborales.PValidacion;


/**
 *
 * @author ANTONIO.CORIA
 */
public class V1Validaciones {
    
    
    ArrayList<String[]> ArrayResult;
    String[] parts;
    String parts0="",parts1="",partsfechas="";    
    //DateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy");
    
    public void Valida_Laborales() throws SQLException, IOException, ParseException{
    
    //FORMATO ESPECIFICO PARA FECHA Y HORA
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    //variable encabezado concatenacion con fecha actual hora actual
    String encabezado = "Fecha Generacion Doc.: " + dtf.format(LocalDateTime.now());
     
    HSSFWorkbook libro = new HSSFWorkbook();
     //inicio de creacion de fuente y bordes 
     HSSFCellStyle estiloCelda0 = libro.createCellStyle();
    //creacion de fuente con nombre de variable fuente 0
     HSSFFont fuente0 = libro.createFont();
     fuente0.setFontHeightInPoints((short) 12);
     fuente0.setColor(HSSFColor.WHITE.index);
     fuente0.setFontName(fuente0.FONT_ARIAL);
     fuente0.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     //creacion de la celda con nombre de variable estiloCelda0
     estiloCelda0.setFont(fuente0);
     estiloCelda0.setWrapText(true);
     estiloCelda0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     estiloCelda0.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
     estiloCelda0.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
     estiloCelda0.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     
     HSSFCellStyle estiloCelda1 = libro.createCellStyle();
     HSSFFont fuente2 = libro.createFont();
     fuente2.setFontHeightInPoints((short) 10);
     fuente2.setColor(HSSFColor.WHITE.index);
     fuente2.setFontName(fuente2.FONT_ARIAL);
     fuente2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     estiloCelda1.setFont(fuente2);
     estiloCelda1.setWrapText(true);
     estiloCelda1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     estiloCelda1.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
     estiloCelda1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
     estiloCelda1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     estiloCelda1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     estiloCelda1.setBottomBorderColor((short) 8);
     estiloCelda1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
     estiloCelda1.setLeftBorderColor((short) 8);
     estiloCelda1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
     estiloCelda1.setRightBorderColor((short) 8);
     estiloCelda1.setBorderTop(HSSFCellStyle.BORDER_THIN);
     estiloCelda1.setTopBorderColor((short) 8);
     estiloCelda1.setWrapText(true);

     //creaciion de relleno color amarillo + borde en negritas
     HSSFCellStyle PAmarillo = libro.createCellStyle();
     PAmarillo.setFillForegroundColor(HSSFColor.YELLOW.index);
     PAmarillo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     PAmarillo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     PAmarillo.setBottomBorderColor((short) 8);
     PAmarillo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
     PAmarillo.setLeftBorderColor((short) 8);
     PAmarillo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
     PAmarillo.setRightBorderColor((short) 8);
     PAmarillo.setBorderTop(HSSFCellStyle.BORDER_THIN);
     PAmarillo.setTopBorderColor((short) 8);  
     PAmarillo.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
         
      HSSFCellStyle estiloCeldabordes0 = libro.createCellStyle();
      estiloCeldabordes0.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      estiloCeldabordes0.setBottomBorderColor((short) 8);
      estiloCeldabordes0.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
      estiloCeldabordes0.setLeftBorderColor((short) 8);
      estiloCeldabordes0.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
      estiloCeldabordes0.setRightBorderColor((short) 8);
      estiloCeldabordes0.setBorderTop(HSSFCellStyle.BORDER_THIN);
      estiloCeldabordes0.setTopBorderColor((short) 8);  
      estiloCeldabordes0.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
      HSSFFont fuente1 = libro.createFont();
      fuente1.setFontHeightInPoints((short) 11);
      fuente1.setFontName(fuente1.FONT_ARIAL);
      estiloCeldabordes0.setFont(fuente1);
      estiloCeldabordes0.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
      //Fin de creacion de fuente y bordes   
      
       //creacion de pestaña con nombre Resumen_Validacion     
        HSSFSheet hojaresumenval = libro.createSheet("Resumen_Validacion");
        hojaresumenval.setColumnWidth((short) 0, (short) 9000);//tamañno de la columna 0 en excel A
        hojaresumenval.setColumnWidth((short) 1, (short) 3000);//tamañno de la columna 1 en excel B
        hojaresumenval.setColumnWidth((short) 3, (short) 10000);//tamañno de la columna 3 en excel D
        //creacion de pestaña con nobre Desgloses     
        HSSFSheet hoja8 = libro.createSheet("Desgloses");
        hoja8.setColumnWidth((short) 0, (short) 1500);//tamañno de la columna 0 en excel A
        hoja8.setColumnWidth((short) 1, (short) 4000);//tamañno de la columna 1 en excel B
        hoja8.setColumnWidth((short) 2, (short) 4000);//tamañno de la columna 2 en excel C
        hoja8.setColumnWidth((short) 3, (short) 4000);//tamañno de la columna 3 en excel D
        hoja8.setColumnWidth((short) 4, (short) 4000);//tamañno de la columna 4 en excel E
        hoja8.setColumnWidth((short) 5, (short) 4000);//tamañno de la columna 5 en excel F
        hoja8.setColumnWidth((short) 6, (short) 4000);//tamañno de la columna 6 en excel G
        hoja8.setColumnWidth((short) 7, (short) 4000);//tamañno de la columna 7 en excel H
        hoja8.setColumnWidth((short) 8, (short) 4000);//tamañno de la columna 8 en excel I
        hoja8.setColumnWidth((short) 9, (short) 4000);//tamañno de la columna 9 en excel J
        hoja8.setColumnWidth((short) 10, (short) 4000);//tamañno de la columna 10 en excel K
        hoja8.setColumnWidth((short) 11, (short) 4000);//tamañno de la columna 11 en excel L
        hoja8.setColumnWidth((short) 12, (short) 4000);//tamañno de la columna 12 en excel M
        hoja8.setColumnWidth((short) 13, (short) 4000);//tamañno de la columna 13 en excel N
        hoja8.setColumnWidth((short) 14, (short) 4250);//tamañno de la columna 14 en excel O
        hoja8.setColumnWidth((short) 15, (short) 4250);//tamañno de la columna 15 en excel P
        hoja8.setColumnWidth((short) 16, (short) 4000);//tamañno de la columna 16 en excel Q
        hoja8.setColumnWidth((short) 17, (short) 4000);//tamañno de la columna 17 en excel R
        hoja8.setColumnWidth((short) 18, (short) 4000);//tamañno de la columna 18 en excel S
        hoja8.setColumnWidth((short) 19, (short) 4000);//tamañno de la columna 19 en excel T
        hoja8.setColumnWidth((short) 20, (short) 4000);//tamañno de la columna 20 en excel U
        hoja8.setColumnWidth((short) 21, (short) 4000);//tamañno de la columna 21 en excel V
        //creacion de pestaña con nombre Ordinario
        HSSFSheet hoja1 = libro.createSheet("Ordinario");
        hoja1.setColumnWidth((short) 0, (short) 6000);
        hoja1.setColumnWidth((short) 1, (short) 6000);
        hoja1.setColumnWidth((short) 2, (short) 6000);
        hoja1.setColumnWidth((short) 3, (short) 6000);
        hoja1.setColumnWidth((short) 4, (short) 30000);
        //creacion de pestaña con nombre Individual
        HSSFSheet hoja2 = libro.createSheet("Individual");
        hoja2.setColumnWidth((short) 0, (short) 6000);
        hoja2.setColumnWidth((short) 1, (short) 6000);
        hoja2.setColumnWidth((short) 2, (short) 6000);
        hoja2.setColumnWidth((short) 3, (short) 6000);
        hoja2.setColumnWidth((short) 4, (short) 30000);
        //creacion de pestaña con nombre Colectivo
        HSSFSheet hoja3 = libro.createSheet("Colectivo");
        hoja3.setColumnWidth((short) 0, (short) 6000);
        hoja3.setColumnWidth((short) 1, (short) 6000);
        hoja3.setColumnWidth((short) 2, (short) 6000);
        hoja3.setColumnWidth((short) 3, (short) 6000);
        hoja3.setColumnWidth((short) 4, (short) 30000);
        //creacion de pestaña con nombre Huelga
        HSSFSheet hoja4 = libro.createSheet("Huelga");
        hoja4.setColumnWidth((short) 0, (short) 6000);
        hoja4.setColumnWidth((short) 1, (short) 6000);
        hoja4.setColumnWidth((short) 2, (short) 6000);
        hoja4.setColumnWidth((short) 3, (short) 6000);
        hoja4.setColumnWidth((short) 4, (short) 30000);
        //creacion de pestaña con nombre Paraprocesal
        HSSFSheet hoja5 = libro.createSheet("Paraprocesal");
        hoja5.setColumnWidth((short) 0, (short) 6000);
        hoja5.setColumnWidth((short) 1, (short) 6000);
        hoja5.setColumnWidth((short) 2, (short) 6000);
        hoja5.setColumnWidth((short) 3, (short) 6000);
        hoja5.setColumnWidth((short) 4, (short) 30000);
       //creacion de pestaña con nombre SSocial
        HSSFSheet hoja6 = libro.createSheet("SSocial");
        hoja6.setColumnWidth((short) 0, (short) 6000);
        hoja6.setColumnWidth((short) 1, (short) 6000);
        hoja6.setColumnWidth((short) 2, (short) 6000);
        hoja6.setColumnWidth((short) 3, (short) 6000);
        hoja6.setColumnWidth((short) 4, (short) 30000);
       //creacion de pestaña con nombre Ejecucion
        HSSFSheet hoja7 = libro.createSheet("Ejecucion");
        hoja7.setColumnWidth((short) 0, (short) 6000);
        hoja7.setColumnWidth((short) 1, (short) 6000);
        hoja7.setColumnWidth((short) 2, (short) 6000);
        hoja7.setColumnWidth((short) 3, (short) 6000);
        hoja7.setColumnWidth((short) 4, (short) 30000);
        //creacion de pestaña con nombre Colect_Econom
        HSSFSheet hoja9 = libro.createSheet("Colect_Econom");
        hoja9.setColumnWidth((short) 0, (short) 6000);
        hoja9.setColumnWidth((short) 1, (short) 6000);
        hoja9.setColumnWidth((short) 2, (short) 6000);
        hoja9.setColumnWidth((short) 3, (short) 6000);
        hoja9.setColumnWidth((short) 4, (short) 30000);
       
        //Proceso pestaña con nombre Resumen_Validacion
        Resumen(libro, hojaresumenval, estiloCelda0, estiloCeldabordes0, encabezado, estiloCelda1);//Proceso pestaña con nombre Resumen_Validacion
        Desglose(libro, hoja8, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Desgloses
        Despliega_Ordinario(libro, hoja1, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Ordinario
        Despliega_Individual(libro, hoja2, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Individual
        Despliega_Colectivo(libro, hoja3, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Colectivo
        Despliega_Huelga(libro, hoja4, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Huelga
        Despliega_Paraprocesal(libro, hoja5, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Paraprocesal
        Despliega_SSocial(libro, hoja6, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre SSocial
        Despliega_Ejecucion(libro, hoja7, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Ejecucion
        Despliega_Colect_Econom(libro, hoja9, hojaresumenval, estiloCelda0, estiloCelda1, estiloCeldabordes0, PAmarillo);//Proceso pestaña con nombre Colect_Econom
        SaveFileTo(libro);//Proceso Guardar Libro En Excel         

    }

    public void Resumen(HSSFWorkbook libro, HSSFSheet hojaresumenval, HSSFCellStyle estiloCelda0, HSSFCellStyle estiloCeldabordes0,String encabezado,HSSFCellStyle estiloCelda1) {
         
        PValidacion validacion=new PValidacion();
       //creacion titulo Resumen Validacion 
        HSSFRow row0 = hojaresumenval.createRow((short) 0);//posicionamiento de la fila 0
        HSSFCell celda0 = row0.createCell((short) 0);//posicionamiento de la columna 0
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = "Resumen Validacion";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hojaresumenval.addMergedRegion(new Region(0, (short) 0, 0, (short) 1));//Ajustar y combinar fila 0, columna 1
        celda0.setCellValue(texto0);
        row0.setHeight((short) 350);    
        //creacion de encabezado Fecha Generacion Doc.: +  (fecha actual)+ hora actual
        HSSFRow fila3 = hojaresumenval.createRow(0);//posicionamiento de la fila 0
        HSSFCell celda3 = fila3.createCell((short) 3);//posicionamiento de la columna 3    
        HSSFRichTextString texto3 = new HSSFRichTextString(encabezado);
        celda3.setCellValue(texto3);
        fila3.setHeight((short)350);
        
        //En caso de que la variable clave_entidad sea diferente a vacio
        if(!validacion.clave_entidad.equals("")){
        //Crea comentario en fila 1 columna 3 Entidad: + clave de la entidad
        HSSFRow filaEntidad = hojaresumenval.createRow(1);//FILA
        HSSFCell celdaEntidad = filaEntidad.createCell((short) 3);//COLUMNA    
        HSSFRichTextString textoEntidad = new HSSFRichTextString("Entidad: "+validacion.clave_entidad + "  " +validacion.NMunicipio);
        celdaEntidad.setCellValue(textoEntidad);
        filaEntidad.setHeight((short)300);
        }
         //En caso de que la variable clave_organo sea diferente a vacio
        if(!validacion.clave_organo.equals("")){
      //Crea comentario en fila 1 columna 3 Clave organo: + clave del organo
        HSSFRow filaEntidad = hojaresumenval.createRow(1);//FILA
        HSSFCell celdaEntidad = filaEntidad.createCell((short) 3);//COLUMNA    
        HSSFRichTextString textoEntidad = new HSSFRichTextString("Clave organo: "+validacion.clave_organo);
        celdaEntidad.setCellValue(textoEntidad);
        filaEntidad.setHeight((short)300);
        }
        //creacion de comentario "Periodo" en fila 2 columna 3
        HSSFRow filaEntidad = hojaresumenval.createRow(2);//FILA
        HSSFCell celdaEntidad = filaEntidad.createCell((short) 3);//COLUMNA    
        HSSFRichTextString textoEntidad = new HSSFRichTextString("Periodo: "+validacion.periodo);
        celdaEntidad.setCellValue(textoEntidad);
        filaEntidad.setHeight((short)300);
       
         //creacion de comentario "Actualización de App: 05/04/2022" en fila 0 columna 4
        HSSFRow filaEntidad1 = hojaresumenval.createRow(0);//FILA
        HSSFCell celdaEntidad1 = filaEntidad1.createCell((short) 4);//COLUMNA    
        HSSFRichTextString textoEntidad1 = new HSSFRichTextString("Actualización de App: 05/04/2022");
        celdaEntidad1.setCellValue(textoEntidad1);
        filaEntidad1.setHeight((short)300);
        
        //creacion de titulo procedimiento en fila 1 columna 0
        HSSFRow fila30 = hojaresumenval.createRow(1);//FILA
        HSSFCell celda30 = fila30.createCell((short) 0);//COLUMNA
        celda30.setCellStyle(estiloCelda1);
        celda30.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt30 ="Procedimiento";        
        HSSFRichTextString texto30 = new HSSFRichTextString(txt30);
        celda30.setCellValue(texto30);
        fila30.setHeight((short)350);
        
        //creacion de titulo Inconsis en fila 1 columna 1
        HSSFRow fila31 = hojaresumenval.createRow(1);//FILA
        HSSFCell celda31 = fila31.createCell((short) 1);//COLUMNA
        celda31.setCellStyle(estiloCelda1);
        celda31.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt31 ="Inconsis";        
        HSSFRichTextString texto31 = new HSSFRichTextString(txt31);
        celda31.setCellValue(texto31);
        fila31.setHeight((short)350);
        
        
        //creacion de titulo Ordinario en fila 2 columna 0
        HSSFRow fila1 = hojaresumenval.createRow(2);//FILA
        HSSFCell celda1 = fila1.createCell((short) 0);//COLUMNA
        celda1.setCellStyle(estiloCeldabordes0);
        celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt0 ="Ordinario";        
        HSSFRichTextString texto1 = new HSSFRichTextString(txt0);
        celda1.setCellValue(texto1);
        fila1.setHeight((short)300);

        HSSFRow fila2 = hojaresumenval.createRow(2);//FILA
        HSSFCell celda2 = fila2.createCell((short) 1);//COLUMNA
        celda2.setCellStyle(estiloCeldabordes0);
        
        //creacion de titulo Individual en fila 3 columna 0
        HSSFRow fila4 = hojaresumenval.createRow(3);//FILA
        HSSFCell celda4 = fila4.createCell((short) 0);//COLUMNA
        celda4.setCellStyle(estiloCeldabordes0);
        celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt4 ="Individual";        
        HSSFRichTextString texto4 = new HSSFRichTextString(txt4);
        celda4.setCellValue(texto4);
        fila4.setHeight((short)300);

        HSSFRow fila5 = hojaresumenval.createRow(3);//FILA
        HSSFCell celda5 = fila5.createCell((short) 1);//COLUMNA
        celda5.setCellStyle(estiloCeldabordes0);
        
        //creacion de titulo Individual en fila 4 columna 0
        HSSFRow fila6 = hojaresumenval.createRow(4);//FILA
        HSSFCell celda6 = fila6.createCell((short) 0);//COLUMNA
        celda6.setCellStyle(estiloCeldabordes0);
        celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt6 ="Colectivo";        
        HSSFRichTextString texto6 = new HSSFRichTextString(txt6);
        celda6.setCellValue(texto6);
        fila6.setHeight((short)300);

        HSSFRow fila7 = hojaresumenval.createRow(4);//FILA
        HSSFCell celda7 = fila7.createCell((short) 1);//COLUMNA
        celda7.setCellStyle(estiloCeldabordes0);
        
        //creacion de titulo Huelga en fila 5 columna 0
        HSSFRow filaA10 = hojaresumenval.createRow(5);//FILA
        HSSFCell celdaA10 = filaA10.createCell((short) 0);//COLUMNA
        celdaA10.setCellStyle(estiloCeldabordes0);
        celdaA10.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA10 ="Huelga";        
        HSSFRichTextString textoA10 = new HSSFRichTextString(txtA10);
        celdaA10.setCellValue(textoA10);
        filaA10.setHeight((short)300);

        HSSFRow filaA11 = hojaresumenval.createRow(5);//FILA
        HSSFCell celdaA11 = filaA11.createCell((short) 1);//COLUMNA
        celdaA11.setCellStyle(estiloCeldabordes0);
        
        HSSFRow fila9 = hojaresumenval.createRow(6);//FILA
        HSSFCell celda9 = fila9.createCell((short) 0);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 ="Paraprocesal";        
        HSSFRichTextString texto9 = new HSSFRichTextString(txt9);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);

        HSSFRow fila20 = hojaresumenval.createRow(6);//FILA
        HSSFCell celda20 = fila20.createCell((short) 1);//COLUMNA
        celda20.setCellStyle(estiloCeldabordes0);
        
        HSSFRow fila21 = hojaresumenval.createRow(7);//FILA
        HSSFCell celda21 = fila21.createCell((short) 0);//COLUMNA
        celda21.setCellStyle(estiloCeldabordes0);
        celda21.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt21 ="Ssocial";        
        HSSFRichTextString texto21 = new HSSFRichTextString(txt21);
        celda21.setCellValue(texto21);
        fila21.setHeight((short)300);

        HSSFRow fila12 = hojaresumenval.createRow(7);//FILA
        HSSFCell celda12 = fila12.createCell((short) 1);//COLUMNA
        celda12.setCellStyle(estiloCeldabordes0);
        
        HSSFRow fila13 = hojaresumenval.createRow(8);//FILA
        HSSFCell celda13 = fila13.createCell((short) 0);//COLUMNA
        celda13.setCellStyle(estiloCeldabordes0);
        celda13.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt13 ="Ejecucion";        
        HSSFRichTextString texto13 = new HSSFRichTextString(txt13);
        celda13.setCellValue(texto13);
        fila13.setHeight((short)300);

        HSSFRow fila14 = hojaresumenval.createRow(8);//FILA
        HSSFCell celda14 = fila14.createCell((short) 1);//COLUMNA
        celda14.setCellStyle(estiloCeldabordes0);
        
        
        HSSFRow fila15 = hojaresumenval.createRow(9);//FILA
        HSSFCell celda15 = fila15.createCell((short) 0);//COLUMNA
        celda15.setCellStyle(estiloCeldabordes0);
        celda15.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt15 ="Colect_Econom";        
        HSSFRichTextString texto15 = new HSSFRichTextString(txt15);
        celda15.setCellValue(texto15);
        fila15.setHeight((short)300);

        HSSFRow fila16 = hojaresumenval.createRow(9);//FILA
        HSSFCell celda16 = fila16.createCell((short) 1);//COLUMNA
        celda16.setCellStyle(estiloCeldabordes0);
              
          } 
    
    public void Desglose(HSSFWorkbook libro,HSSFSheet hoja8,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
     V1QDesgloses Desgloses = new V1QDesgloses();
     
        
        HSSFRow row0 = hoja8.createRow((short) 1);
        HSSFCell celda0 = row0.createCell((short) 1);
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " DESGLOSE POR PROCEDIMIENTO";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600); 
        hoja8.addMergedRegion(new Region(1, (short) 1, 1, (short) 21));
        
        
        HSSFRow fila1 = hoja8.createRow(2);//FILA
        HSSFCell celda1 = fila1.createCell((short) 1);//COLUMNA
        celda1.setCellStyle(estiloCelda1);
        celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt1 ="CLAVE ORGANO";        
        HSSFRichTextString texto1 = new HSSFRichTextString(txt1);
        celda1.setCellValue(texto1);
        fila1.setHeight((short)500);
        
        ArrayResult=Desgloses.Desglose_Ordinario();
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila21 = hoja8.createRow(3+i);//FILA
        HSSFCell celda21 = fila21.createCell((short) 1);//COLUMNA
        celda21.setCellStyle(estiloCeldabordes0);
        celda21.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt21 =Arrays.toString(ArrayResult.get(i));        
        txt21=txt21.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt21.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto21 = new HSSFRichTextString(parts0);
        celda21.setCellValue(texto21);
        fila21.setHeight((short)300);
        }
        
        HSSFRow fila2 = hoja8.createRow(2);//FILA
        HSSFCell celda2 = fila2.createCell((short) 2);//COLUMNA
        celda2.setCellStyle(estiloCelda1);
        celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt2 ="ORDINARIO";        
        HSSFRichTextString texto2 = new HSSFRichTextString(txt2);
        celda2.setCellValue(texto2);
        fila2.setHeight((short)500);
        
         for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 2);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        //TITULO
        HSSFRow fila3 = hoja8.createRow(2);//FILA
        HSSFCell celda3 = fila3.createCell((short) 3);//COLUMNA
        celda3.setCellStyle(estiloCelda1);
        celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt3 ="DESGLOSE ORDINARIO";        
        HSSFRichTextString texto3 = new HSSFRichTextString(txt3);
        celda3.setCellValue(texto3);
        fila3.setHeight((short)500);
        
        
         for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 3);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Individual();
        HSSFRow fila4 = hoja8.createRow(2);//FILA
        HSSFCell celda4 = fila4.createCell((short) 4);//COLUMNA
        celda4.setCellStyle(estiloCelda1);
        celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt4 ="INDIVIDUAL";        
        HSSFRichTextString texto4 = new HSSFRichTextString(txt4);
        celda4.setCellValue(texto4);
        fila4.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 4);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila24 = hoja8.createRow(2);//FILA
        HSSFCell celda24 = fila24.createCell((short) 5);//COLUMNA
        celda24.setCellStyle(estiloCelda1);
        celda24.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt24 ="DESGLOSE INDIVIDUAL";        
        HSSFRichTextString texto24 = new HSSFRichTextString(txt24);
        celda24.setCellValue(texto24);
        fila24.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 5);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
       
        ArrayResult=Desgloses.Desglose_Colectivo();
        HSSFRow fila5 = hoja8.createRow(2);//FILA
        HSSFCell celda5 = fila5.createCell((short) 6);//COLUMNA
        celda5.setCellStyle(estiloCelda1);
        celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt5 ="COLECTIVO";        
        HSSFRichTextString texto5 = new HSSFRichTextString(txt5);
        celda5.setCellValue(texto5);
        fila5.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 6);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila6 = hoja8.createRow(2);//FILA
        HSSFCell celda6 = fila6.createCell((short) 7);//COLUMNA
        celda6.setCellStyle(estiloCelda1);
        celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt6 ="DESGLOSE COLECTIVO";        
        HSSFRichTextString texto6 = new HSSFRichTextString(txt6);
        celda6.setCellValue(texto6);
        fila6.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 7);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        
         ArrayResult=Desgloses.Desglose_Huelga();
        HSSFRow fila7 = hoja8.createRow(2);//FILA
        HSSFCell celda7 = fila7.createCell((short) 8);//COLUMNA
        celda7.setCellStyle(estiloCelda1);
        celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt7 ="HUELGA";        
        HSSFRichTextString texto7 = new HSSFRichTextString(txt7);
        celda7.setCellValue(texto7);
        fila7.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 8);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        
        HSSFRow fila8 = hoja8.createRow(2);//FILA
        HSSFCell celda8 = fila8.createCell((short) 9);//COLUMNA
        celda8.setCellStyle(estiloCelda1);
        celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt8 ="DESGLOSE HUELGA";        
        HSSFRichTextString texto8 = new HSSFRichTextString(txt8);
        celda8.setCellValue(texto8);
        fila8.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 9);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Ssocial();
        HSSFRow fila9 = hoja8.createRow(2);//FILA
        HSSFCell celda9 = fila9.createCell((short) 10);//COLUMNA
        celda9.setCellStyle(estiloCelda1);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 ="SSOCIAL";        
        HSSFRichTextString texto9 = new HSSFRichTextString(txt9);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 10);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila10 = hoja8.createRow(2);//FILA
        HSSFCell celda10 = fila10.createCell((short) 11);//COLUMNA
        celda10.setCellStyle(estiloCelda1);
        celda10.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt10 ="DESGLOSE SSOCIAL";        
        HSSFRichTextString texto10 = new HSSFRichTextString(txt10);
        celda10.setCellValue(texto10);
        fila10.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 11);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Colec_Econom();
         HSSFRow fila11 = hoja8.createRow(2);//FILA
        HSSFCell celda11 = fila11.createCell((short) 12);//COLUMNA
        celda11.setCellStyle(estiloCelda1);
        celda11.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt11 ="COLEC ECONOM";        
        HSSFRichTextString texto11 = new HSSFRichTextString(txt11);
        celda11.setCellValue(texto11);
        fila11.setHeight((short)500);
       
         for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 12);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila12 = hoja8.createRow(2);//FILA
        HSSFCell celda12 = fila12.createCell((short) 13);//COLUMNA
        celda12.setCellStyle(estiloCelda1);
        celda12.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt12 ="DESGLOSE COLEC ECONOM";        
        HSSFRichTextString texto12 = new HSSFRichTextString(txt12);
        celda12.setCellValue(texto12);
        fila12.setHeight((short)500);
        
         for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 13);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Paraprocesal();
         HSSFRow fila13 = hoja8.createRow(2);//FILA
        HSSFCell celda13 = fila13.createCell((short) 14);//COLUMNA
        celda13.setCellStyle(estiloCelda1);
        celda13.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt13 ="PARAPROCESAL";        
        HSSFRichTextString texto13 = new HSSFRichTextString(txt13);
        celda13.setCellValue(texto13);
        fila13.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 14);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila14 = hoja8.createRow(2);//FILA
        HSSFCell celda14 = fila14.createCell((short) 15);//COLUMNA
        celda14.setCellStyle(estiloCelda1);
        celda14.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt14 ="DESGLOSE PARAPROCESAL";        
        HSSFRichTextString texto14 = new HSSFRichTextString(txt14);
        celda14.setCellValue(texto14);
        fila14.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 15);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        ArrayResult=Desgloses.Desglose_Tercerias();
        HSSFRow fila15 = hoja8.createRow(2);//FILA
        HSSFCell celda15 = fila15.createCell((short) 16);//COLUMNA
        celda15.setCellStyle(estiloCelda1);
        celda15.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt15 ="TERCERIAS";        
        HSSFRichTextString texto15 = new HSSFRichTextString(txt15);
        celda15.setCellValue(texto15);
        fila15.setHeight((short)500);
        
                for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 16);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
     
        HSSFRow fila16 = hoja8.createRow(2);//FILA
        HSSFCell celda16 = fila16.createCell((short) 17);//COLUMNA
        celda16.setCellStyle(estiloCelda1);
        celda16.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt16 ="DESGLOSE TERCERIAS";        
        HSSFRichTextString texto16 = new HSSFRichTextString(txt16);
        celda16.setCellValue(texto16);
        fila16.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 17);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Pref_Credito();
        HSSFRow fila17 = hoja8.createRow(2);//FILA
        HSSFCell celda17 = fila17.createCell((short) 18);//COLUMNA
        celda17.setCellStyle(estiloCelda1);
        celda17.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt17 ="PREF CREDITO";        
        HSSFRichTextString texto17 = new HSSFRichTextString(txt17);
        celda17.setCellValue(texto17);
        fila17.setHeight((short)500);
        
                for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 18);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila18 = hoja8.createRow(2);//FILA
        HSSFCell celda18 = fila18.createCell((short) 19);//COLUMNA
        celda18.setCellStyle(estiloCelda1);
        celda18.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt18 ="DESGLOSE PREF CREDITO";        
        HSSFRichTextString texto18 = new HSSFRichTextString(txt18);
        celda18.setCellValue(texto18);
        fila18.setHeight((short)500);
        
               for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 19);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
        
        ArrayResult=Desgloses.Desglose_Ejecucion();
        HSSFRow fila19 = hoja8.createRow(2);//FILA
        HSSFCell celda19 = fila19.createCell((short) 20);//COLUMNA
        celda19.setCellStyle(estiloCelda1);
        celda19.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt19 ="EJECUCION";        
        HSSFRichTextString texto19 = new HSSFRichTextString(txt19);
        celda19.setCellValue(texto19);
        fila19.setHeight((short)500);
        
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila23 = hoja8.createRow(3+i);//FILA
        HSSFCell celda23 = fila23.createCell((short) 20);//COLUMNA
        celda23.setCellStyle(estiloCeldabordes0);
        celda23.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt23 =Arrays.toString(ArrayResult.get(i));        
        txt23=txt23.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt23.split(",");
        parts0=parts[1].trim();
        parts1=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda23.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto23 = new HSSFRichTextString(parts0);
        celda23.setCellValue(texto23);
        fila23.setHeight((short)300);
        }
        
        HSSFRow fila20 = hoja8.createRow(2);//FILA
        HSSFCell celda20 = fila20.createCell((short) 21);//COLUMNA
        celda20.setCellStyle(estiloCelda1);
        celda20.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt20 ="DESGLOSE EJECUCION";        
        HSSFRichTextString texto20 = new HSSFRichTextString(txt20);
        celda20.setCellValue(texto20);
        fila20.setHeight((short)500);
        
          for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila22 = hoja8.createRow(3+i);//FILA
        HSSFCell celda22 = fila22.createCell((short) 21);//COLUMNA
        celda22.setCellStyle(estiloCeldabordes0);
        celda22.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt22 =Arrays.toString(ArrayResult.get(i));        
        txt22=txt22.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt22.split(",");
        parts1=parts[1].trim();
        parts0=parts[2].trim();
        if (!parts0.equals(parts1)){
         celda22.setCellStyle(PAmarillo);
        }
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto22 = new HSSFRichTextString(parts0);
        celda22.setCellValue(texto22);
        fila22.setHeight((short)300);
        }
        
    }
   
    public void Despliega_Ordinario(HSSFWorkbook libro,HSSFSheet hoja1,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
       
        V1QOrdinario Ordinario=new V1QOrdinario();
        
            int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        
        HSSFRow row0 = hoja1.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO ORDINARIO ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja1.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
        ArrayResult=Ordinario.Año_Expe_Ordinario();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(2);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja1.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja1.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja1.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja1.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja1.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja1.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja1.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja1.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja1.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja1.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja1.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja1.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Ordinario.Fecha_Admi_demanda();
        if (ArrayResult.size()>0){  
             System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        HSSFRow fila00 = hojaresumenval.createRow(2);//FILA
        HSSFCell celda00 = fila00.createCell((short) 1);//COLUMNA    
        celda00.setCellStyle(PAmarillo);
        
        HSSFRow fila1 = hoja1.createRow(conEnc);//FILA
        HSSFCell celda1 = fila1.createCell((short) 0);//COLUMNA
        celda1.setCellStyle(estiloCelda1);
        celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString texto1 = new HSSFRichTextString(txt1);
        celda1.setCellValue(texto1);
        fila1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila6 = hoja1.createRow(conDat + i);//FILA
        HSSFCell celda6 = fila6.createCell((short) 0);//COLUMNA
        celda6.setCellStyle(estiloCeldabordes0);
        celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt6 =Arrays.toString(ArrayResult.get(i));        
        txt6=txt6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto6 = new HSSFRichTextString(parts0);
        celda6.setCellValue(texto6);
        fila6.setHeight((short)300);
        }
        
        HSSFRow fila2 = hoja1.createRow(conEnc);//FILA
        HSSFCell celda2 = fila2.createCell((short) 1);//COLUMNA
        celda2.setCellStyle(estiloCelda1);
        celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString texto2 = new HSSFRichTextString(txt2);
        celda2.setCellValue(texto2);
        fila2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila7 = hoja1.createRow(conDat + i);//FILA
        HSSFCell celda7 = fila7.createCell((short) 1);//COLUMNA
        celda7.setCellStyle(estiloCeldabordes0);
        celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt7 =Arrays.toString(ArrayResult.get(i));        
        txt7=txt7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto7 = new HSSFRichTextString(parts0);
        celda7.setCellValue(texto7);
        fila7.setHeight((short)300);
        }
        
        HSSFRow fila3 = hoja1.createRow(conEnc);//FILA
        HSSFCell celda3 = fila3.createCell((short) 2);//COLUMNA
        celda3.setCellStyle(estiloCelda1);
        celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString texto3 = new HSSFRichTextString(txt3);
        celda3.setCellValue(texto3);
        fila3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila8 = hoja1.createRow(conDat + i);//FILA
        HSSFCell celda8 = fila8.createCell((short) 2);//COLUMNA
        celda8.setCellStyle(estiloCeldabordes0);
        celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt8 =Arrays.toString(ArrayResult.get(i));        
        txt8=txt8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto8 = new HSSFRichTextString(parts0);
        celda8.setCellValue(texto8);
        fila8.setHeight((short)300);
        }
        
        HSSFRow fila4 = hoja1.createRow(conEnc);//FILA
        HSSFCell celda4 = fila4.createCell((short) 3);//COLUMNA
        celda4.setCellStyle(estiloCelda1);
        celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt4 ="FECHA DE ADMISIÓN DE LA DEMANDA";        
        HSSFRichTextString texto4 = new HSSFRichTextString(txt4);
        celda4.setCellValue(texto4);
        fila4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja1.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 3);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 =Arrays.toString(ArrayResult.get(i));        
        txt9=txt9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(parts0);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        }
       
        HSSFRow fila5 = hoja1.createRow(conEnc);//FILA
        HSSFCell celda5 = fila5.createCell((short) 4);//COLUMNA
        celda5.setCellStyle(estiloCelda1);
        celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt5 ="OBSERVACIONES";        
        HSSFRichTextString texto5 = new HSSFRichTextString(txt5);
        celda5.setCellValue(texto5);
        fila5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja1.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 4);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 ="La Fecha de admisión de la demanda no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(txt9);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        coni++;
        }
        System.out.println("contador i: "+coni);
        conEnc=conEnc+coni;
        conDat=conDat+coni;
        coni=1;
        System.out.println("contador i: "+conEnc + " "+conDat+" "+ArrayResult.size());
        }
       //1 2
        ArrayResult=Ordinario.Fecha_Audiencia_Juicio();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaA00 = hojaresumenval.createRow(2);//filaA
        HSSFCell celdaA00 = filaA00.createCell((short) 1);//COLUMNA    
        celdaA00.setCellStyle(PAmarillo);
         
        HSSFRow filaA1 = hoja1.createRow(conEnc);//filaA
        HSSFCell celdaA1 = filaA1.createCell((short) 0);//COLUMNA
        celdaA1.setCellStyle(estiloCelda1);
        celdaA1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtA1);
        celdaA1.setCellValue(textoA1);
        filaA1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA6 = hoja1.createRow(conDat + i);//filaA
        HSSFCell celdaA6 = filaA6.createCell((short) 0);//COLUMNA
        celdaA6.setCellStyle(estiloCeldabordes0);
        celdaA6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA6 =Arrays.toString(ArrayResult.get(i));        
        txtA6=txtA6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaA6.setCellValue(textoA6);
        filaA6.setHeight((short)300);
        }
        
        HSSFRow filaA2 = hoja1.createRow(conEnc);//filaA
        HSSFCell celdaA2 = filaA2.createCell((short) 1);//COLUMNA
        celdaA2.setCellStyle(estiloCelda1);
        celdaA2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtA2);
        celdaA2.setCellValue(textoA2);
        filaA2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA7 = hoja1.createRow(conDat + i);//filaA
        HSSFCell celdaA7 = filaA7.createCell((short) 1);//COLUMNA
        celdaA7.setCellStyle(estiloCeldabordes0);
        celdaA7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA7 =Arrays.toString(ArrayResult.get(i));        
        txtA7=txtA7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaA7.setCellValue(textoA7);
        filaA7.setHeight((short)300);
        }
        
        HSSFRow filaA3 = hoja1.createRow(conEnc);//filaA
        HSSFCell celdaA3 = filaA3.createCell((short) 2);//COLUMNA
        celdaA3.setCellStyle(estiloCelda1);
        celdaA3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtA3);
        celdaA3.setCellValue(textoA3);
        filaA3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA8 = hoja1.createRow(conDat + i);//filaA
        HSSFCell celdaA8 = filaA8.createCell((short) 2);//COLUMNA
        celdaA8.setCellStyle(estiloCeldabordes0);
        celdaA8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA8 =Arrays.toString(ArrayResult.get(i));        
        txtA8=txtA8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaA8.setCellValue(textoA8);
        filaA8.setHeight((short)300);
        }
        
        HSSFRow filaA4 = hoja1.createRow(conEnc);//filaA
        HSSFCell celdaA4 = filaA4.createCell((short) 3);//COLUMNA
        celdaA4.setCellStyle(estiloCelda1);
        celdaA4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA4 ="FECHA DE AUDIENCIA DE JUICIO";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtA4);
        celdaA4.setCellValue(textoA4);
        filaA4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja1.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 3);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 =Arrays.toString(ArrayResult.get(i));        
        txtA9=txtA9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(parts0);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
        }
       
        HSSFRow filaA5 = hoja1.createRow(conEnc);//filaA
        HSSFCell celdaA5 = filaA5.createCell((short) 4);//COLUMNA
        celdaA5.setCellStyle(estiloCelda1);
        celdaA5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtA5);
        celdaA5.setCellValue(textoA5);
        filaA5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja1.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 4);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 ="La Fecha de audiencia de juicio no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtA9);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Ordinario.Fecha_Audiencia_Prelim();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(2);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja1.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoA1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja1.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoA6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja1.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoA2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja1.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoA7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja1.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoA3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja1.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoA8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja1.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA DE AUDIENCIA PRELIMINAR";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoA4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja1.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoA9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja1.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoA5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja1.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de audiencia preliminar no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoA9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }

        
        ArrayResult=Ordinario.Fecha_Dicto_Resolucion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(2);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoA1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoA6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoA2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoA7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoA3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoA8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA DICTO RESOLUCION";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoA4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoA9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoA5);
        filaC5.setHeight((short)750);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoA9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Ordinario.Fecha_Emplaz_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(2);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoA1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoA6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoA2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoA7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoA3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoA8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA EMPLAZAMIENTO DE LA DEMANDA";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoA4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaD9 = filaD9.createCell((short) 3);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 =Arrays.toString(ArrayResult.get(i));        
        txtD9=txtD9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoA5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaD9 = filaD9.createCell((short) 4);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 ="La Fecha de emplazamiento de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtD9);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Ordinario.Fecha_Resolucion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(2);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoA1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoA6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoA2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoA7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoA3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoA8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA RESOLUCION";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoA4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja1.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoD5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja1.createRow(conDat + i);//filaC
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
    }
    
    public void Despliega_Individual(HSSFWorkbook libro,HSSFSheet hoja2,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
      
         V1QIndividual Individual=new V1QIndividual ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja2.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO INDIVIDUAL ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja2.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
         ArrayResult=Individual.Año_Expe_Individual();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(3);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja2.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja2.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja2.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja2.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja2.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja2.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja2.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja2.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja2.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja2.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja2.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja2.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Individual.Fecha_Admi_demanda();
        if (ArrayResult.size()>0){  
        System.out.println("contador Encabezado Fecha_Admi_demanda: "+conEnc + "Contador Datos: "+ conDat );
        HSSFRow fila00 = hojaresumenval.createRow(3);//FILA
        HSSFCell celda00 = fila00.createCell((short) 1);//COLUMNA    
        celda00.setCellStyle(PAmarillo);
        
        HSSFRow fila1 = hoja2.createRow(conEnc);//FILA
        HSSFCell celda1 = fila1.createCell((short) 0);//COLUMNA
        celda1.setCellStyle(estiloCelda1);
        celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString texto1 = new HSSFRichTextString(txt1);
        celda1.setCellValue(texto1);
        fila1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila6 = hoja2.createRow(conDat + i);//FILA
        HSSFCell celda6 = fila6.createCell((short) 0);//COLUMNA
        celda6.setCellStyle(estiloCeldabordes0);
        celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt6 =Arrays.toString(ArrayResult.get(i));        
        txt6=txt6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto6 = new HSSFRichTextString(parts0);
        celda6.setCellValue(texto6);
        fila6.setHeight((short)300);
        }
        
        HSSFRow fila2 = hoja2.createRow(conEnc);//FILA
        HSSFCell celda2 = fila2.createCell((short) 1);//COLUMNA
        celda2.setCellStyle(estiloCelda1);
        celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString texto2 = new HSSFRichTextString(txt2);
        celda2.setCellValue(texto2);
        fila2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila7 = hoja2.createRow(conDat + i);//FILA
        HSSFCell celda7 = fila7.createCell((short) 1);//COLUMNA
        celda7.setCellStyle(estiloCeldabordes0);
        celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt7 =Arrays.toString(ArrayResult.get(i));        
        txt7=txt7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto7 = new HSSFRichTextString(parts0);
        celda7.setCellValue(texto7);
        fila7.setHeight((short)300);
        }
        
        HSSFRow fila3 = hoja2.createRow(conEnc);//FILA
        HSSFCell celda3 = fila3.createCell((short) 2);//COLUMNA
        celda3.setCellStyle(estiloCelda1);
        celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString texto3 = new HSSFRichTextString(txt3);
        celda3.setCellValue(texto3);
        fila3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila8 = hoja2.createRow(conDat + i);//FILA
        HSSFCell celda8 = fila8.createCell((short) 2);//COLUMNA
        celda8.setCellStyle(estiloCeldabordes0);
        celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt8 =Arrays.toString(ArrayResult.get(i));        
        txt8=txt8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto8 = new HSSFRichTextString(parts0);
        celda8.setCellValue(texto8);
        fila8.setHeight((short)300);
        }
        
        HSSFRow fila4 = hoja2.createRow(conEnc);//FILA
        HSSFCell celda4 = fila4.createCell((short) 3);//COLUMNA
        celda4.setCellStyle(estiloCelda1);
        celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt4 ="FECHA DE ADMISIÓN DE LA DEMANDA";        
        HSSFRichTextString texto4 = new HSSFRichTextString(txt4);
        celda4.setCellValue(texto4);
        fila4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja2.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 3);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 =Arrays.toString(ArrayResult.get(i));        
        txt9=txt9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(parts0);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        }
       
        HSSFRow fila5 = hoja2.createRow(conEnc);//FILA
        HSSFCell celda5 = fila5.createCell((short) 4);//COLUMNA
        celda5.setCellStyle(estiloCelda1);
        celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt5 ="OBSERVACIONES";        
        HSSFRichTextString texto5 = new HSSFRichTextString(txt5);
        celda5.setCellValue(texto5);
        fila5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja2.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 4);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 ="La Fecha de admisión de la demanda no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(txt9);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        System.out.println("contador FECHA_ADMI_DEMANDA i: "+coni+" "+i+""+ArrayResult.size());
        coni++;
        }
        System.out.println("contador FECHA_ADMI_DEMANDA i: "+coni+" "+ArrayResult.size());
        conEnc=conEnc+coni;
        conDat=conDat+coni;
        coni=1;
        }
        
        
        ArrayResult=Individual.Fecha_Audiencia_Juicio();
        if (ArrayResult.size()>0)
        {
            System.out.println("ENTRO A Fecha_Audiencia_Juicio");
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaA00 = hojaresumenval.createRow(3);//filaA
        HSSFCell celdaA00 = filaA00.createCell((short) 1);//COLUMNA    
        celdaA00.setCellStyle(PAmarillo);
         
        HSSFRow filaA1 = hoja2.createRow(conEnc);//filaA
        HSSFCell celdaA1 = filaA1.createCell((short) 0);//COLUMNA
        celdaA1.setCellStyle(estiloCelda1);
        celdaA1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtA1);
        celdaA1.setCellValue(textoA1);
        filaA1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA6 = hoja2.createRow(conDat + i);//filaA
        HSSFCell celdaA6 = filaA6.createCell((short) 0);//COLUMNA
        celdaA6.setCellStyle(estiloCeldabordes0);
        celdaA6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA6 =Arrays.toString(ArrayResult.get(i));        
        txtA6=txtA6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaA6.setCellValue(textoA6);
        filaA6.setHeight((short)300);
        }
        
        HSSFRow filaA2 = hoja2.createRow(conEnc);//filaA
        HSSFCell celdaA2 = filaA2.createCell((short) 1);//COLUMNA
        celdaA2.setCellStyle(estiloCelda1);
        celdaA2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtA2);
        celdaA2.setCellValue(textoA2);
        filaA2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA7 = hoja2.createRow(conDat + i);//filaA
        HSSFCell celdaA7 = filaA7.createCell((short) 1);//COLUMNA
        celdaA7.setCellStyle(estiloCeldabordes0);
        celdaA7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA7 =Arrays.toString(ArrayResult.get(i));        
        txtA7=txtA7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaA7.setCellValue(textoA7);
        filaA7.setHeight((short)300);
        }
        
        HSSFRow filaA3 = hoja2.createRow(conEnc);//filaA
        HSSFCell celdaA3 = filaA3.createCell((short) 2);//COLUMNA
        celdaA3.setCellStyle(estiloCelda1);
        celdaA3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtA3);
        celdaA3.setCellValue(textoA3);
        filaA3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA8 = hoja2.createRow(conDat + i);//filaA
        HSSFCell celdaA8 = filaA8.createCell((short) 2);//COLUMNA
        celdaA8.setCellStyle(estiloCeldabordes0);
        celdaA8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA8 =Arrays.toString(ArrayResult.get(i));        
        txtA8=txtA8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaA8.setCellValue(textoA8);
        filaA8.setHeight((short)300);
        }
        
        HSSFRow filaA4 = hoja2.createRow(conEnc);//filaA
        HSSFCell celdaA4 = filaA4.createCell((short) 3);//COLUMNA
        celdaA4.setCellStyle(estiloCelda1);
        celdaA4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA4 ="FECHA DE AUDIENCIA DE JUICIO";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtA4);
        celdaA4.setCellValue(textoA4);
        filaA4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja2.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 3);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 =Arrays.toString(ArrayResult.get(i));        
        txtA9=txtA9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(parts0);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
        }
       
        HSSFRow filaA5 = hoja2.createRow(conEnc);//filaA
        HSSFCell celdaA5 = filaA5.createCell((short) 4);//COLUMNA
        celdaA5.setCellStyle(estiloCelda1);
        celdaA5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtA5);
        celdaA5.setCellValue(textoA5);
        filaA5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja2.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 4);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 ="La Fecha de audiencia de juicio no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtA9);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        System.out.println("contador FECHA_AUDIENCIA_JUICIO Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Individual.Fecha_Audiencia_Prelim();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(3);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja2.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja2.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja2.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja2.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja2.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja2.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja2.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA DE AUDIENCIA PRELIMINAR";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja2.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja2.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja2.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de audiencia preliminar no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        System.out.println("contador FECHA_AUDINECIA_PRELIMINAR Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Individual.Fecha_Depuracion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(3);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja2.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoC1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoC1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja2.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoC6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja2.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoC2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoC2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja2.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoC7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja2.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoC3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoC3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja2.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoC8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja2.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA DE AUTO DE DEPURACION";        
        HSSFRichTextString textoC4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoC4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja2.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja2.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoC5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoC5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja2.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha de auto de depuración no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        System.out.println("contador feCHA_EMPLAZ_DEMANDA Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Individual.Fecha_Emplaz_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaD00 = hojaresumenval.createRow(3);//filaD
        HSSFCell celdaD00 = filaD00.createCell((short) 1);//COLUMNA    
        celdaD00.setCellStyle(PAmarillo);
         
        HSSFRow filaD1 = hoja2.createRow(conEnc);//filaD
        HSSFCell celdaD1 = filaD1.createCell((short) 0);//COLUMNA
        celdaD1.setCellStyle(estiloCelda1);
        celdaD1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtD1);
        celdaD1.setCellValue(textoD1);
        filaD1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD6 = hoja2.createRow(conDat + i);//filaD
        HSSFCell celdaD6 = filaD6.createCell((short) 0);//COLUMNA
        celdaD6.setCellStyle(estiloCeldabordes0);
        celdaD6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD6 =Arrays.toString(ArrayResult.get(i));        
        txtD6=txtD6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaD6.setCellValue(textoD6);
        filaD6.setHeight((short)300);
        }
        
        HSSFRow filaD2 = hoja2.createRow(conEnc);//filaD
        HSSFCell celdaD2 = filaD2.createCell((short) 1);//COLUMNA
        celdaD2.setCellStyle(estiloCelda1);
        celdaD2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtD2);
        celdaD2.setCellValue(textoD2);
        filaD2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD7 = hoja2.createRow(conDat + i);//filaD
        HSSFCell celdaD7 = filaD7.createCell((short) 1);//COLUMNA
        celdaD7.setCellStyle(estiloCeldabordes0);
        celdaD7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD7 =Arrays.toString(ArrayResult.get(i));        
        txtD7=txtD7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaD7.setCellValue(textoD7);
        filaD7.setHeight((short)300);
        }
        
        HSSFRow filaD3 = hoja2.createRow(conEnc);//filaD
        HSSFCell celdaD3 = filaD3.createCell((short) 2);//COLUMNA
        celdaD3.setCellStyle(estiloCelda1);
        celdaD3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtD3);
        celdaD3.setCellValue(textoD3);
        filaD3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD8 = hoja2.createRow(conDat + i);//filaD
        HSSFCell celdaD8 = filaD8.createCell((short) 2);//COLUMNA
        celdaD8.setCellStyle(estiloCeldabordes0);
        celdaD8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD8 =Arrays.toString(ArrayResult.get(i));        
        txtD8=txtD8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaD8.setCellValue(textoD8);
        filaD8.setHeight((short)300);
        }
        
        HSSFRow filaD4 = hoja2.createRow(conEnc);//filaD
        HSSFCell celdaD4 = filaD4.createCell((short) 3);//COLUMNA
        celdaD4.setCellStyle(estiloCelda1);
        celdaD4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD4 ="FECHA EMPLAZAMIENTO DEMANDA";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtD4);
        celdaD4.setCellValue(textoD4);
        filaD4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja2.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 3);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 =Arrays.toString(ArrayResult.get(i));        
        txtD9=txtD9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
        }
       
        HSSFRow filaD5 = hoja2.createRow(conEnc);//filaD
        HSSFCell celdaD5 = filaD5.createCell((short) 4);//COLUMNA
        celdaD5.setCellStyle(estiloCelda1);
        celdaD5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtD5);
        celdaD5.setCellValue(textoD5);
        filaD5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja2.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 4);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 ="La Fecha de emplazamiento de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtD9);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Individual.Fecha_Resolucion_Ad();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(3);//filaE
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja2.createRow(conEnc);//filaE
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoE1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoE1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja2.createRow(conDat + i);//filaE
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoE6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja2.createRow(conEnc);//filaE
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoE2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoE2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja2.createRow(conDat + i);//filaE
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoE7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja2.createRow(conEnc);//filaE
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoE3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoE3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja2.createRow(conDat + i);//filaE
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoE8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja2.createRow(conEnc);//filaE
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA DICTO AUTO DE DEPURACION";        
        HSSFRichTextString textoE4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoE4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja2.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja2.createRow(conEnc);//filaE
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoE5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoE5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja2.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha en la que se dictó auto de depuración no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Individual.Fecha_Resolucion_Aj();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaF00 = hojaresumenval.createRow(3);//filaF
        HSSFCell celdaF00 = filaF00.createCell((short) 1);//COLUMNA    
        celdaF00.setCellStyle(PAmarillo);
         
        HSSFRow filaF1 = hoja2.createRow(conEnc);//filaF
        HSSFCell celdaF1 = filaF1.createCell((short) 0);//COLUMNA
        celdaF1.setCellStyle(estiloCelda1);
        celdaF1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoF1 = new HSSFRichTextString(txtF1);
        celdaF1.setCellValue(textoF1);
        filaF1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF6 = hoja2.createRow(conDat + i);//filaF
        HSSFCell celdaF6 = filaF6.createCell((short) 0);//COLUMNA
        celdaF6.setCellStyle(estiloCeldabordes0);
        celdaF6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF6 =Arrays.toString(ArrayResult.get(i));        
        txtF6=txtF6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoF6 = new HSSFRichTextString(parts0);
        celdaF6.setCellValue(textoF6);
        filaF6.setHeight((short)300);
        }
        
        HSSFRow filaF2 = hoja2.createRow(conEnc);//filaF
        HSSFCell celdaF2 = filaF2.createCell((short) 1);//COLUMNA
        celdaF2.setCellStyle(estiloCelda1);
        celdaF2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoF2 = new HSSFRichTextString(txtF2);
        celdaF2.setCellValue(textoF2);
        filaF2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF7 = hoja2.createRow(conDat + i);//filaF
        HSSFCell celdaF7 = filaF7.createCell((short) 1);//COLUMNA
        celdaF7.setCellStyle(estiloCeldabordes0);
        celdaF7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF7 =Arrays.toString(ArrayResult.get(i));        
        txtF7=txtF7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoF7 = new HSSFRichTextString(parts0);
        celdaF7.setCellValue(textoF7);
        filaF7.setHeight((short)300);
        }
        
        HSSFRow filaF3 = hoja2.createRow(conEnc);//filaF
        HSSFCell celdaF3 = filaF3.createCell((short) 2);//COLUMNA
        celdaF3.setCellStyle(estiloCelda1);
        celdaF3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoF3 = new HSSFRichTextString(txtF3);
        celdaF3.setCellValue(textoF3);
        filaF3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF8 = hoja2.createRow(conDat + i);//filaF
        HSSFCell celdaF8 = filaF8.createCell((short) 2);//COLUMNA
        celdaF8.setCellStyle(estiloCeldabordes0);
        celdaF8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF8 =Arrays.toString(ArrayResult.get(i));        
        txtF8=txtF8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoF8 = new HSSFRichTextString(parts0);
        celdaF8.setCellValue(textoF8);
        filaF8.setHeight((short)300);
        }
        
        HSSFRow filaF4 = hoja2.createRow(conEnc);//filaF
        HSSFCell celdaF4 = filaF4.createCell((short) 3);//COLUMNA
        celdaF4.setCellStyle(estiloCelda1);
        celdaF4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF4 ="FECHA DICTO RESOLUCION [AUDIENCIA JUICIO]";        
        HSSFRichTextString textoF4 = new HSSFRichTextString(txtF4);
        celdaF4.setCellValue(textoF4);
        filaF4.setHeight((short)750);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF9 = hoja2.createRow(conDat + i);//filaF
        HSSFCell celdaF9 = filaF9.createCell((short) 3);//COLUMNA
        celdaF9.setCellStyle(estiloCeldabordes0);
        celdaF9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF9 =Arrays.toString(ArrayResult.get(i));        
        txtF9=txtF9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoF9 = new HSSFRichTextString(parts0);
        celdaF9.setCellValue(textoF9);
        filaF9.setHeight((short)300);
        }
       
        HSSFRow filaF5 = hoja2.createRow(conEnc);//filaF
        HSSFCell celdaF5 = filaF5.createCell((short) 4);//COLUMNA
        celdaF5.setCellStyle(estiloCelda1);
        celdaF5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF5 ="OBSERVACIONES";        
        HSSFRichTextString textoF5 = new HSSFRichTextString(txtF5);
        celdaF5.setCellValue(textoF5);
        filaF5.setHeight((short)750);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF9 = hoja2.createRow(conDat + i);//filaF
        HSSFCell celdaF9 = filaF9.createCell((short) 4);//COLUMNA
        celdaF9.setCellStyle(estiloCeldabordes0);
        celdaF9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoF9 = new HSSFRichTextString(txtF9);
        celdaF9.setCellValue(textoF9);
        filaF9.setHeight((short)350);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Individual.Fecha_Resolucion_Ap();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaG00 = hojaresumenval.createRow(3);//filaG
        HSSFCell celdaG00 = filaG00.createCell((short) 1);//COLUMNA    
        celdaG00.setCellStyle(PAmarillo);
         
        HSSFRow filaG1 = hoja2.createRow(conEnc);//filaG
        HSSFCell celdaG1 = filaG1.createCell((short) 0);//COLUMNA
        celdaG1.setCellStyle(estiloCelda1);
        celdaG1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoG1 = new HSSFRichTextString(txtG1);
        celdaG1.setCellValue(textoG1);
        filaG1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG6 = hoja2.createRow(conDat + i);//filaG
        HSSFCell celdaG6 = filaG6.createCell((short) 0);//COLUMNA
        celdaG6.setCellStyle(estiloCeldabordes0);
        celdaG6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG6 =Arrays.toString(ArrayResult.get(i));        
        txtG6=txtG6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG6 = new HSSFRichTextString(parts0);
        celdaG6.setCellValue(textoG6);
        filaG6.setHeight((short)300);
        }
        
        HSSFRow filaG2 = hoja2.createRow(conEnc);//filaG
        HSSFCell celdaG2 = filaG2.createCell((short) 1);//COLUMNA
        celdaG2.setCellStyle(estiloCelda1);
        celdaG2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoG2 = new HSSFRichTextString(txtG2);
        celdaG2.setCellValue(textoG2);
        filaG2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG7 = hoja2.createRow(conDat + i);//filaG
        HSSFCell celdaG7 = filaG7.createCell((short) 1);//COLUMNA
        celdaG7.setCellStyle(estiloCeldabordes0);
        celdaG7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG7 =Arrays.toString(ArrayResult.get(i));        
        txtG7=txtG7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG7 = new HSSFRichTextString(parts0);
        celdaG7.setCellValue(textoG7);
        filaG7.setHeight((short)300);
        }
        
        HSSFRow filaG3 = hoja2.createRow(conEnc);//filaG
        HSSFCell celdaG3 = filaG3.createCell((short) 2);//COLUMNA
        celdaG3.setCellStyle(estiloCelda1);
        celdaG3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoG3 = new HSSFRichTextString(txtG3);
        celdaG3.setCellValue(textoG3);
        filaG3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG8 = hoja2.createRow(conDat + i);//filaG
        HSSFCell celdaG8 = filaG8.createCell((short) 2);//COLUMNA
        celdaG8.setCellStyle(estiloCeldabordes0);
        celdaG8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG8 =Arrays.toString(ArrayResult.get(i));        
        txtG8=txtG8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG8 = new HSSFRichTextString(parts0);
        celdaG8.setCellValue(textoG8);
        filaG8.setHeight((short)300);
        }
        
        HSSFRow filaG4 = hoja2.createRow(conEnc);//filaG
        HSSFCell celdaG4 = filaG4.createCell((short) 3);//COLUMNA
        celdaG4.setCellStyle(estiloCelda1);
        celdaG4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG4 ="FECHA DICTO RESOLUCION [AUDIENCIA PRELIMINAR]";        
        HSSFRichTextString textoG4 = new HSSFRichTextString(txtG4);
        celdaG4.setCellValue(textoG4);
        filaG4.setHeight((short)750);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG9 = hoja2.createRow(conDat + i);//filaG
        HSSFCell celdaG9 = filaG9.createCell((short) 3);//COLUMNA
        celdaG9.setCellStyle(estiloCeldabordes0);
        celdaG9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG9 =Arrays.toString(ArrayResult.get(i));        
        txtG9=txtG9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG9 = new HSSFRichTextString(parts0);
        celdaG9.setCellValue(textoG9);
        filaG9.setHeight((short)300);
        }
       
        HSSFRow filaG5 = hoja2.createRow(conEnc);//filaG
        HSSFCell celdaG5 = filaG5.createCell((short) 4);//COLUMNA
        celdaG5.setCellStyle(estiloCelda1);
        celdaG5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG5 ="OBSERVACIONES";        
        HSSFRichTextString textoG5 = new HSSFRichTextString(txtG5);
        celdaG5.setCellValue(textoG5);
        filaG5.setHeight((short)750);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG9 = hoja2.createRow(conDat + i);//filaG
        HSSFCell celdaG9 = filaG9.createCell((short) 4);//COLUMNA
        celdaG9.setCellStyle(estiloCeldabordes0);
        celdaG9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG9 = new HSSFRichTextString(txtG9);
        celdaG9.setCellValue(textoG9);
        filaG9.setHeight((short)350);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
                ArrayResult=Individual.Fecha_Resolucion_Ta();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaH00 = hojaresumenval.createRow(3);//filaH
        HSSFCell celdaH00 = filaH00.createCell((short) 1);//COLUMNA    
        celdaH00.setCellStyle(PAmarillo);
         
        HSSFRow filaH1 = hoja2.createRow(conEnc);//filaH
        HSSFCell celdaH1 = filaH1.createCell((short) 0);//COLUMNA
        celdaH1.setCellStyle(estiloCelda1);
        celdaH1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoH1 = new HSSFRichTextString(txtH1);
        celdaH1.setCellValue(textoH1);
        filaH1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaH6 = hoja2.createRow(conDat + i);//filaH
        HSSFCell celdaH6 = filaH6.createCell((short) 0);//COLUMNA
        celdaH6.setCellStyle(estiloCeldabordes0);
        celdaH6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH6 =Arrays.toString(ArrayResult.get(i));        
        txtH6=txtH6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtH6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoH6 = new HSSFRichTextString(parts0);
        celdaH6.setCellValue(textoH6);
        filaH6.setHeight((short)300);
        }
        
        HSSFRow filaH2 = hoja2.createRow(conEnc);//filaH
        HSSFCell celdaH2 = filaH2.createCell((short) 1);//COLUMNA
        celdaH2.setCellStyle(estiloCelda1);
        celdaH2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoH2 = new HSSFRichTextString(txtH2);
        celdaH2.setCellValue(textoH2);
        filaH2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaH7 = hoja2.createRow(conDat + i);//filaH
        HSSFCell celdaH7 = filaH7.createCell((short) 1);//COLUMNA
        celdaH7.setCellStyle(estiloCeldabordes0);
        celdaH7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH7 =Arrays.toString(ArrayResult.get(i));        
        txtH7=txtH7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtH7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoH7 = new HSSFRichTextString(parts0);
        celdaH7.setCellValue(textoH7);
        filaH7.setHeight((short)300);
        }
        
        HSSFRow filaH3 = hoja2.createRow(conEnc);//filaH
        HSSFCell celdaH3 = filaH3.createCell((short) 2);//COLUMNA
        celdaH3.setCellStyle(estiloCelda1);
        celdaH3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoH3 = new HSSFRichTextString(txtH3);
        celdaH3.setCellValue(textoH3);
        filaH3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaH8 = hoja2.createRow(conDat + i);//filaH
        HSSFCell celdaH8 = filaH8.createCell((short) 2);//COLUMNA
        celdaH8.setCellStyle(estiloCeldabordes0);
        celdaH8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH8 =Arrays.toString(ArrayResult.get(i));        
        txtH8=txtH8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtH8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoH8 = new HSSFRichTextString(parts0);
        celdaH8.setCellValue(textoH8);
        filaH8.setHeight((short)300);
        }
        
        HSSFRow filaH4 = hoja2.createRow(conEnc);//filaH
        HSSFCell celdaH4 = filaH4.createCell((short) 3);//COLUMNA
        celdaH4.setCellStyle(estiloCelda1);
        celdaH4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH4 ="FECHA DICTO RESOLUCION [DEMANDADO]";        
        HSSFRichTextString textoH4 = new HSSFRichTextString(txtH4);
        celdaH4.setCellValue(textoH4);
        filaH4.setHeight((short)750);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaH9 = hoja2.createRow(conDat + i);//filaH
        HSSFCell celdaH9 = filaH9.createCell((short) 3);//COLUMNA
        celdaH9.setCellStyle(estiloCeldabordes0);
        celdaH9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH9 =Arrays.toString(ArrayResult.get(i));        
        txtH9=txtH9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtH9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoH9 = new HSSFRichTextString(parts0);
        celdaH9.setCellValue(textoH9);
        filaH9.setHeight((short)300);
        }
       
        HSSFRow filaH5 = hoja2.createRow(conEnc);//filaH
        HSSFCell celdaH5 = filaH5.createCell((short) 4);//COLUMNA
        celdaH5.setCellStyle(estiloCelda1);
        celdaH5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH5 ="OBSERVACIONES";        
        HSSFRichTextString textoH5 = new HSSFRichTextString(txtH5);
        celdaH5.setCellValue(textoH5);
        filaH5.setHeight((short)750);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaH9 = hoja2.createRow(conDat + i);//filaH
        HSSFCell celdaH9 = filaH9.createCell((short) 4);//COLUMNA
        celdaH9.setCellStyle(estiloCeldabordes0);
        celdaH9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtH9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoH9 = new HSSFRichTextString(txtH9);
        celdaH9.setCellValue(textoH9);
        filaH9.setHeight((short)350);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
    }
    
    public void Despliega_Colectivo(HSSFWorkbook libro,HSSFSheet hoja3,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
     
        V1QColectivo Colectivo=new V1QColectivo ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja3.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO COLECTIVO ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja3.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
         ArrayResult=Colectivo.Año_Expe_Colectivo();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(4);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja3.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja3.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja3.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja3.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja3.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja3.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja3.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja3.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja3.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja3.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja3.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja3.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Colectivo.Fecha_Admi_Demanda();
         if (ArrayResult.size()>0){  
            // System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        HSSFRow fila00 = hojaresumenval.createRow(4);//FILA
        HSSFCell celda00 = fila00.createCell((short) 1);//COLUMNA    
        celda00.setCellStyle(PAmarillo);
        
        HSSFRow fila1 = hoja3.createRow(conEnc);//FILA
        HSSFCell celda1 = fila1.createCell((short) 0);//COLUMNA
        celda1.setCellStyle(estiloCelda1);
        celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString texto1 = new HSSFRichTextString(txt1);
        celda1.setCellValue(texto1);
        fila1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila6 = hoja3.createRow(conDat + i);//FILA
        HSSFCell celda6 = fila6.createCell((short) 0);//COLUMNA
        celda6.setCellStyle(estiloCeldabordes0);
        celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt6 =Arrays.toString(ArrayResult.get(i));        
        txt6=txt6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto6 = new HSSFRichTextString(parts0);
        celda6.setCellValue(texto6);
        fila6.setHeight((short)300);
        }
        
        HSSFRow fila2 = hoja3.createRow(conEnc);//FILA
        HSSFCell celda2 = fila2.createCell((short) 1);//COLUMNA
        celda2.setCellStyle(estiloCelda1);
        celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString texto2 = new HSSFRichTextString(txt2);
        celda2.setCellValue(texto2);
        fila2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila7 = hoja3.createRow(conDat + i);//FILA
        HSSFCell celda7 = fila7.createCell((short) 1);//COLUMNA
        celda7.setCellStyle(estiloCeldabordes0);
        celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt7 =Arrays.toString(ArrayResult.get(i));        
        txt7=txt7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto7 = new HSSFRichTextString(parts0);
        celda7.setCellValue(texto7);
        fila7.setHeight((short)300);
        }
        
        HSSFRow fila3 = hoja3.createRow(conEnc);//FILA
        HSSFCell celda3 = fila3.createCell((short) 2);//COLUMNA
        celda3.setCellStyle(estiloCelda1);
        celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString texto3 = new HSSFRichTextString(txt3);
        celda3.setCellValue(texto3);
        fila3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila8 = hoja3.createRow(conDat + i);//FILA
        HSSFCell celda8 = fila8.createCell((short) 2);//COLUMNA
        celda8.setCellStyle(estiloCeldabordes0);
        celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt8 =Arrays.toString(ArrayResult.get(i));        
        txt8=txt8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto8 = new HSSFRichTextString(parts0);
        celda8.setCellValue(texto8);
        fila8.setHeight((short)300);
        }
        
        HSSFRow fila4 = hoja3.createRow(conEnc);//FILA
        HSSFCell celda4 = fila4.createCell((short) 3);//COLUMNA
        celda4.setCellStyle(estiloCelda1);
        celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt4 ="FECHA DE ADMISIÓN DE LA DEMANDA";        
        HSSFRichTextString texto4 = new HSSFRichTextString(txt4);
        celda4.setCellValue(texto4);
        fila4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja3.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 3);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 =Arrays.toString(ArrayResult.get(i));        
        txt9=txt9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txt9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(parts0);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        }
       
        HSSFRow fila5 = hoja3.createRow(conEnc);//FILA
        HSSFCell celda5 = fila5.createCell((short) 4);//COLUMNA
        celda5.setCellStyle(estiloCelda1);
        celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt5 ="OBSERVACIONES";        
        HSSFRichTextString texto5 = new HSSFRichTextString(txt5);
        celda5.setCellValue(texto5);
        fila5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow fila9 = hoja3.createRow(conDat + i);//FILA
        HSSFCell celda9 = fila9.createCell((short) 4);//COLUMNA
        celda9.setCellStyle(estiloCeldabordes0);
        celda9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txt9 ="La Fecha de admisión de la demanda no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString texto9 = new HSSFRichTextString(txt9);
        celda9.setCellValue(texto9);
        fila9.setHeight((short)300);
        coni++;
        }
        System.out.println("contador i: "+coni);
        conEnc=conEnc+coni;
        conDat=conDat+coni;
        coni=1;
        }
        
         ArrayResult=Colectivo.Fecha_Audiencia_Juicio();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaA00 = hojaresumenval.createRow(4);//filaA
        HSSFCell celdaA00 = filaA00.createCell((short) 1);//COLUMNA    
        celdaA00.setCellStyle(PAmarillo);
         
        HSSFRow filaA1 = hoja3.createRow(conEnc);//filaA
        HSSFCell celdaA1 = filaA1.createCell((short) 0);//COLUMNA
        celdaA1.setCellStyle(estiloCelda1);
        celdaA1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtA1);
        celdaA1.setCellValue(textoA1);
        filaA1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA6 = hoja3.createRow(conDat + i);//filaA
        HSSFCell celdaA6 = filaA6.createCell((short) 0);//COLUMNA
        celdaA6.setCellStyle(estiloCeldabordes0);
        celdaA6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA6 =Arrays.toString(ArrayResult.get(i));        
        txtA6=txtA6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaA6.setCellValue(textoA6);
        filaA6.setHeight((short)300);
        }
        
        HSSFRow filaA2 = hoja3.createRow(conEnc);//filaA
        HSSFCell celdaA2 = filaA2.createCell((short) 1);//COLUMNA
        celdaA2.setCellStyle(estiloCelda1);
        celdaA2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtA2);
        celdaA2.setCellValue(textoA2);
        filaA2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA7 = hoja3.createRow(conDat + i);//filaA
        HSSFCell celdaA7 = filaA7.createCell((short) 1);//COLUMNA
        celdaA7.setCellStyle(estiloCeldabordes0);
        celdaA7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA7 =Arrays.toString(ArrayResult.get(i));        
        txtA7=txtA7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaA7.setCellValue(textoA7);
        filaA7.setHeight((short)300);
        }
        
        HSSFRow filaA3 = hoja3.createRow(conEnc);//filaA
        HSSFCell celdaA3 = filaA3.createCell((short) 2);//COLUMNA
        celdaA3.setCellStyle(estiloCelda1);
        celdaA3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtA3);
        celdaA3.setCellValue(textoA3);
        filaA3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA8 = hoja3.createRow(conDat + i);//filaA
        HSSFCell celdaA8 = filaA8.createCell((short) 2);//COLUMNA
        celdaA8.setCellStyle(estiloCeldabordes0);
        celdaA8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA8 =Arrays.toString(ArrayResult.get(i));        
        txtA8=txtA8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaA8.setCellValue(textoA8);
        filaA8.setHeight((short)300);
        }
        
        HSSFRow filaA4 = hoja3.createRow(conEnc);//filaA
        HSSFCell celdaA4 = filaA4.createCell((short) 3);//COLUMNA
        celdaA4.setCellStyle(estiloCelda1);
        celdaA4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA4 ="FECHA DE AUDIENCIA DE JUICIO";        
        HSSFRichTextString textoA4 = new HSSFRichTextString(txtA4);
        celdaA4.setCellValue(textoA4);
        filaA4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja3.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 3);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 =Arrays.toString(ArrayResult.get(i));        
        txtA9=txtA9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtA9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(parts0);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
        }
       
        HSSFRow filaA5 = hoja3.createRow(conEnc);//filaA
        HSSFCell celdaA5 = filaA5.createCell((short) 4);//COLUMNA
        celdaA5.setCellStyle(estiloCelda1);
        celdaA5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtA5);
        celdaA5.setCellValue(textoA5);
        filaA5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaA9 = hoja3.createRow(conDat + i);//filaA
        HSSFCell celdaA9 = filaA9.createCell((short) 4);//COLUMNA
        celdaA9.setCellStyle(estiloCeldabordes0);
        celdaA9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtA9 ="La Fecha de audiencia de juicio no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtA9);
        celdaA9.setCellValue(textoA9);
        filaA9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        
        ArrayResult=Colectivo.Fecha_Depuracion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(4);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja3.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja3.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja3.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja3.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja3.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja3.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja3.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA DE AUTO DE DEPURACION";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja3.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja3.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja3.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de auto de depuración no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Colectivo.Fecha_Dicto_Depuracion_Ad();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(4);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja3.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoC1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoC1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja3.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoC6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja3.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoC2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoC2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja3.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoC7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja3.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoC3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoC3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja3.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoC8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja3.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA DICTO AUTO DE DEPURACION";        
        HSSFRichTextString textoC4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoC4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja3.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja3.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoC5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoC5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja3.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha en la que se dictó auto de depuración no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        ArrayResult=Colectivo.Fecha_Emplaz_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaD00 = hojaresumenval.createRow(4);//filaD
        HSSFCell celdaD00 = filaD00.createCell((short) 1);//COLUMNA    
        celdaD00.setCellStyle(PAmarillo);
         
        HSSFRow filaD1 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaD1 = filaD1.createCell((short) 0);//COLUMNA
        celdaD1.setCellStyle(estiloCelda1);
        celdaD1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtD1);
        celdaD1.setCellValue(textoD1);
        filaD1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD6 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaD6 = filaD6.createCell((short) 0);//COLUMNA
        celdaD6.setCellStyle(estiloCeldabordes0);
        celdaD6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD6 =Arrays.toString(ArrayResult.get(i));        
        txtD6=txtD6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaD6.setCellValue(textoD6);
        filaD6.setHeight((short)300);
        }
        
        HSSFRow filaD2 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaD2 = filaD2.createCell((short) 1);//COLUMNA
        celdaD2.setCellStyle(estiloCelda1);
        celdaD2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtD2);
        celdaD2.setCellValue(textoD2);
        filaD2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD7 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaD7 = filaD7.createCell((short) 1);//COLUMNA
        celdaD7.setCellStyle(estiloCeldabordes0);
        celdaD7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD7 =Arrays.toString(ArrayResult.get(i));        
        txtD7=txtD7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaD7.setCellValue(textoD7);
        filaD7.setHeight((short)300);
        }
        
        HSSFRow filaD3 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaD3 = filaD3.createCell((short) 2);//COLUMNA
        celdaD3.setCellStyle(estiloCelda1);
        celdaD3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtD3);
        celdaD3.setCellValue(textoD3);
        filaD3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD8 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaD8 = filaD8.createCell((short) 2);//COLUMNA
        celdaD8.setCellStyle(estiloCeldabordes0);
        celdaD8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD8 =Arrays.toString(ArrayResult.get(i));        
        txtD8=txtD8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaD8.setCellValue(textoD8);
        filaD8.setHeight((short)300);
        }
        
        HSSFRow filaD4 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaD4 = filaD4.createCell((short) 3);//COLUMNA
        celdaD4.setCellStyle(estiloCelda1);
        celdaD4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD4 ="FECHA EMPLAZAMIENTO DEMANDA";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtD4);
        celdaD4.setCellValue(textoD4);
        filaD4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 3);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 =Arrays.toString(ArrayResult.get(i));        
        txtD9=txtD9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
        }
       
        HSSFRow filaD5 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaD5 = filaD5.createCell((short) 4);//COLUMNA
        celdaD5.setCellStyle(estiloCelda1);
        celdaD5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtD5);
        celdaD5.setCellValue(textoD5);
        filaD5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 4);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 ="La Fecha de emplazamiento de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtD9);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
   
     ArrayResult=Colectivo.Fecha_Resolucion_Aj();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(4);//filaD
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoE1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoE1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoE6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoE2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoE2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoE7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoE3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoE3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoE8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA RESOLUCION";        
        HSSFRichTextString textoE4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoE4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja3.createRow(conEnc);//filaD
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoE5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoE5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja3.createRow(conDat + i);//filaD
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }  
    }
    
    public void Despliega_Huelga(HSSFWorkbook libro,HSSFSheet hoja4,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
        V1QHuelga Huelga=new V1QHuelga ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja4.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO HUELGA ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja4.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
          ArrayResult=Huelga.Año_Expe_Huelga();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(5);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja4.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja4.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja4.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja4.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja4.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja4.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja4.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja4.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja4.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja4.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja4.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja4.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        
        ArrayResult=Huelga.Fecha_Audiencia();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(5);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja4.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja4.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja4.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja4.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja4.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja4.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja4.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA AUDIENCIA";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja4.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja4.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja4.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de audiencia de conciliación no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Huelga.Fecha_Emplazamiento();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(5);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoC1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoC1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoC6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoC2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoC2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoC7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoC3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoC3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoC8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA EMPLAZAMIENTO";        
        HSSFRichTextString textoC4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoC4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoC5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoC5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha de emplazamiento a huelga no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
                ArrayResult=Huelga.Fecha_Estallam_Huelga();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(5);//filaC
        HSSFCell celdaD00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaD00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaD1 = filaC1.createCell((short) 0);//COLUMNA
        celdaD1.setCellStyle(estiloCelda1);
        celdaD1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtD1);
        celdaD1.setCellValue(textoD1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaD6 = filaC6.createCell((short) 0);//COLUMNA
        celdaD6.setCellStyle(estiloCeldabordes0);
        celdaD6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD6 =Arrays.toString(ArrayResult.get(i));        
        txtD6=txtD6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaD6.setCellValue(textoD6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaD2 = filaC2.createCell((short) 1);//COLUMNA
        celdaD2.setCellStyle(estiloCelda1);
        celdaD2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtD2);
        celdaD2.setCellValue(textoD2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaD7 = filaC7.createCell((short) 1);//COLUMNA
        celdaD7.setCellStyle(estiloCeldabordes0);
        celdaD7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD7 =Arrays.toString(ArrayResult.get(i));        
        txtD7=txtD7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaD7.setCellValue(textoD7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaD3 = filaC3.createCell((short) 2);//COLUMNA
        celdaD3.setCellStyle(estiloCelda1);
        celdaD3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtD3);
        celdaD3.setCellValue(textoD3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaD8 = filaC8.createCell((short) 2);//COLUMNA
        celdaD8.setCellStyle(estiloCeldabordes0);
        celdaD8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD8 =Arrays.toString(ArrayResult.get(i));        
        txtD8=txtD8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaD8.setCellValue(textoD8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaD4 = filaC4.createCell((short) 3);//COLUMNA
        celdaD4.setCellStyle(estiloCelda1);
        celdaD4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD4 ="FECHA ESTALLAMIENTO";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtD4);
        celdaD4.setCellValue(textoD4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaD9 = filaC9.createCell((short) 3);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 =Arrays.toString(ArrayResult.get(i));        
        txtD9=txtD9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaD9.setCellValue(textoD9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja4.createRow(conEnc);//filaC
        HSSFCell celdaD5 = filaC5.createCell((short) 4);//COLUMNA
        celdaD5.setCellStyle(estiloCelda1);
        celdaD5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtD5);
        celdaD5.setCellValue(textoD5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja4.createRow(conDat + i);//filaC
        HSSFCell celdaD9 = filaC9.createCell((short) 4);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 ="La Fecha de Estallamiento de la huelga no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtD9);
        celdaD9.setCellValue(textoD9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
                        ArrayResult=Huelga.Fecha_Levant_Huelga();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(5);//filaE
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja4.createRow(conEnc);//filaE
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoE1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoE1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja4.createRow(conDat + i);//filaE
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoE6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja4.createRow(conEnc);//filaE
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoE2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoE2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja4.createRow(conDat + i);//filaE
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoE7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja4.createRow(conEnc);//filaE
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoE3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoE3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja4.createRow(conDat + i);//filaE
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoE8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja4.createRow(conEnc);//filaE
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA LEVANTAMIENTO";        
        HSSFRichTextString textoE4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoE4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja4.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja4.createRow(conEnc);//filaE
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoE5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoE5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja4.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha de Levantamiento de la huelga no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoE9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        
        ArrayResult=Huelga.Fecha_Resolu_Emplaz();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaF00 = hojaresumenval.createRow(5);//filaF
        HSSFCell celdaF00 = filaF00.createCell((short) 1);//COLUMNA    
        celdaF00.setCellStyle(PAmarillo);
         
        HSSFRow filaF1 = hoja4.createRow(conEnc);//filaF
        HSSFCell celdaF1 = filaF1.createCell((short) 0);//COLUMNA
        celdaF1.setCellStyle(estiloCelda1);
        celdaF1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoE1 = new HSSFRichTextString(txtF1);
        celdaF1.setCellValue(textoE1);
        filaF1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF6 = hoja4.createRow(conDat + i);//filaF
        HSSFCell celdaF6 = filaF6.createCell((short) 0);//COLUMNA
        celdaF6.setCellStyle(estiloCeldabordes0);
        celdaF6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF6 =Arrays.toString(ArrayResult.get(i));        
        txtF6=txtF6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE6 = new HSSFRichTextString(parts0);
        celdaF6.setCellValue(textoE6);
        filaF6.setHeight((short)300);
        }
        
        HSSFRow filaF2 = hoja4.createRow(conEnc);//filaF
        HSSFCell celdaF2 = filaF2.createCell((short) 1);//COLUMNA
        celdaF2.setCellStyle(estiloCelda1);
        celdaF2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoE2 = new HSSFRichTextString(txtF2);
        celdaF2.setCellValue(textoE2);
        filaF2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF7 = hoja4.createRow(conDat + i);//filaF
        HSSFCell celdaF7 = filaF7.createCell((short) 1);//COLUMNA
        celdaF7.setCellStyle(estiloCeldabordes0);
        celdaF7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF7 =Arrays.toString(ArrayResult.get(i));        
        txtF7=txtF7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE7 = new HSSFRichTextString(parts0);
        celdaF7.setCellValue(textoE7);
        filaF7.setHeight((short)300);
        }
        
        HSSFRow filaF3 = hoja4.createRow(conEnc);//filaF
        HSSFCell celdaF3 = filaF3.createCell((short) 2);//COLUMNA
        celdaF3.setCellStyle(estiloCelda1);
        celdaF3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoE3 = new HSSFRichTextString(txtF3);
        celdaF3.setCellValue(textoE3);
        filaF3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF8 = hoja4.createRow(conDat + i);//filaF
        HSSFCell celdaF8 = filaF8.createCell((short) 2);//COLUMNA
        celdaF8.setCellStyle(estiloCeldabordes0);
        celdaF8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF8 =Arrays.toString(ArrayResult.get(i));        
        txtF8=txtF8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE8 = new HSSFRichTextString(parts0);
        celdaF8.setCellValue(textoE8);
        filaF8.setHeight((short)300);
        }
        
        HSSFRow filaF4 = hoja4.createRow(conEnc);//filaF
        HSSFCell celdaF4 = filaF4.createCell((short) 3);//COLUMNA
        celdaF4.setCellStyle(estiloCelda1);
        celdaF4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF4 ="FECHA RESOLUCION (EMPLAZAMIENTO/PREHUELGA)";        
        HSSFRichTextString textoE4 = new HSSFRichTextString(txtF4);
        celdaF4.setCellValue(textoE4);
        filaF4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF9 = hoja4.createRow(conDat + i);//filaF
        HSSFCell celdaF9 = filaF9.createCell((short) 3);//COLUMNA
        celdaF9.setCellStyle(estiloCeldabordes0);
        celdaF9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF9 =Arrays.toString(ArrayResult.get(i));        
        txtF9=txtF9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtF9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(parts0);
        celdaF9.setCellValue(textoE9);
        filaF9.setHeight((short)300);
        }
       
        HSSFRow filaF5 = hoja4.createRow(conEnc);//filaF
        HSSFCell celdaF5 = filaF5.createCell((short) 4);//COLUMNA
        celdaF5.setCellStyle(estiloCelda1);
        celdaF5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF5 ="OBSERVACIONES";        
        HSSFRichTextString textoE5 = new HSSFRichTextString(txtF5);
        celdaF5.setCellValue(textoE5);
        filaF5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaF9 = hoja4.createRow(conDat + i);//filaF
        HSSFCell celdaF9 = filaF9.createCell((short) 4);//COLUMNA
        celdaF9.setCellStyle(estiloCeldabordes0);
        celdaF9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtF9 ="La Fecha en la que se dictó la resolución (Emplazamiento/prehuelga) no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoE9 = new HSSFRichTextString(txtF9);
        celdaF9.setCellValue(textoE9);
        filaF9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        
                ArrayResult=Huelga.Fecha_Resolu_Huelga();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaG00 = hojaresumenval.createRow(5);//filaG
        HSSFCell celdaG00 = filaG00.createCell((short) 1);//COLUMNA    
        celdaG00.setCellStyle(PAmarillo);
         
        HSSFRow filaG1 = hoja4.createRow(conEnc);//filaG
        HSSFCell celdaG1 = filaG1.createCell((short) 0);//COLUMNA
        celdaG1.setCellStyle(estiloCelda1);
        celdaG1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoG1 = new HSSFRichTextString(txtG1);
        celdaG1.setCellValue(textoG1);
        filaG1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG6 = hoja4.createRow(conDat + i);//filaG
        HSSFCell celdaG6 = filaG6.createCell((short) 0);//COLUMNA
        celdaG6.setCellStyle(estiloCeldabordes0);
        celdaG6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG6 =Arrays.toString(ArrayResult.get(i));        
        txtG6=txtG6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG6 = new HSSFRichTextString(parts0);
        celdaG6.setCellValue(textoG6);
        filaG6.setHeight((short)300);
        }
        
        HSSFRow filaG2 = hoja4.createRow(conEnc);//filaG
        HSSFCell celdaG2 = filaG2.createCell((short) 1);//COLUMNA
        celdaG2.setCellStyle(estiloCelda1);
        celdaG2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoG2 = new HSSFRichTextString(txtG2);
        celdaG2.setCellValue(textoG2);
        filaG2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG7 = hoja4.createRow(conDat + i);//filaG
        HSSFCell celdaG7 = filaG7.createCell((short) 1);//COLUMNA
        celdaG7.setCellStyle(estiloCeldabordes0);
        celdaG7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG7 =Arrays.toString(ArrayResult.get(i));        
        txtG7=txtG7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG7 = new HSSFRichTextString(parts0);
        celdaG7.setCellValue(textoG7);
        filaG7.setHeight((short)300);
        }
        
        HSSFRow filaG3 = hoja4.createRow(conEnc);//filaG
        HSSFCell celdaG3 = filaG3.createCell((short) 2);//COLUMNA
        celdaG3.setCellStyle(estiloCelda1);
        celdaG3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoG3 = new HSSFRichTextString(txtG3);
        celdaG3.setCellValue(textoG3);
        filaG3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG8 = hoja4.createRow(conDat + i);//filaG
        HSSFCell celdaG8 = filaG8.createCell((short) 2);//COLUMNA
        celdaG8.setCellStyle(estiloCeldabordes0);
        celdaG8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG8 =Arrays.toString(ArrayResult.get(i));        
        txtG8=txtG8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG8 = new HSSFRichTextString(parts0);
        celdaG8.setCellValue(textoG8);
        filaG8.setHeight((short)300);
        }
        
        HSSFRow filaG4 = hoja4.createRow(conEnc);//filaG
        HSSFCell celdaG4 = filaG4.createCell((short) 3);//COLUMNA
        celdaG4.setCellStyle(estiloCelda1);
        celdaG4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG4 ="FECHA RESOLUCION (HUELGA)";        
        HSSFRichTextString textoG4 = new HSSFRichTextString(txtG4);
        celdaG4.setCellValue(textoG4);
        filaG4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG9 = hoja4.createRow(conDat + i);//filaG
        HSSFCell celdaG9 = filaG9.createCell((short) 3);//COLUMNA
        celdaG9.setCellStyle(estiloCeldabordes0);
        celdaG9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG9 =Arrays.toString(ArrayResult.get(i));        
        txtG9=txtG9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtG9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG9 = new HSSFRichTextString(parts0);
        celdaG9.setCellValue(textoG9);
        filaG9.setHeight((short)300);
        }
       
        HSSFRow filaG5 = hoja4.createRow(conEnc);//filaG
        HSSFCell celdaG5 = filaG5.createCell((short) 4);//COLUMNA
        celdaG5.setCellStyle(estiloCelda1);
        celdaG5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG5 ="OBSERVACIONES";        
        HSSFRichTextString textoG5 = new HSSFRichTextString(txtG5);
        celdaG5.setCellValue(textoG5);
        filaG5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaG9 = hoja4.createRow(conDat + i);//filaG
        HSSFCell celdaG9 = filaG9.createCell((short) 4);//COLUMNA
        celdaG9.setCellStyle(estiloCeldabordes0);
        celdaG9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtG9 ="La Fecha en la que se dictó la resolución (Huelga) no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoG9 = new HSSFRichTextString(txtG9);
        celdaG9.setCellValue(textoG9);
        filaG9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
    }
    
    public void Despliega_Paraprocesal(HSSFWorkbook libro,HSSFSheet hoja5,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
        
        V1QParaprocesal Paraprocesal=new V1QParaprocesal ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja5.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO PARAPROCESAL ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja5.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
          ArrayResult=Paraprocesal.Año_Expe_Paraprocesal();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(6);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja5.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja5.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja5.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja5.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja5.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja5.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja5.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja5.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja5.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja5.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja5.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja5.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Paraprocesal.Fecha_Admi_Soli();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(6);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja5.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja5.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja5.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja5.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja5.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja5.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja5.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA ADMISION DE LA SOLICITUD";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja5.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja5.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja5.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de admisión de la solicitud o promoción no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
                ArrayResult=Paraprocesal.Fecha_Resolucion_Expe();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(6);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja5.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoC1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoC1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja5.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoC6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja5.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoC2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoC2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja5.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoC7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja5.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoC3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoC3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja5.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoC8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja5.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA RESOLUCION";        
        HSSFRichTextString textoC4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoC4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja5.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja5.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoC5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoC5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja5.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha en la que se dictó la resolución no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
    }
    
    public void Despliega_SSocial(HSSFWorkbook libro,HSSFSheet hoja6,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
        
        V1QSsocial SSocial=new V1QSsocial ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja6.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO SSOCIAL ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja6.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
         ArrayResult=SSocial.Año_Expe_SSocial();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(7);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja6.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja6.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja6.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja6.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja6.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja6.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja6.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja6.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja6.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja6.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja6.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja6.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=SSocial.Fecha_Admision_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(7);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja6.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja6.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja6.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja6.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja6.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja6.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja6.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA ADMISION DE LA DEMANDA";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja6.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja6.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja6.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de admisión de la demanda no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
                ArrayResult=SSocial.Fecha_Audienciaj();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaC00 = hojaresumenval.createRow(7);//filaC
        HSSFCell celdaC00 = filaC00.createCell((short) 1);//COLUMNA    
        celdaC00.setCellStyle(PAmarillo);
         
        HSSFRow filaC1 = hoja6.createRow(conEnc);//filaC
        HSSFCell celdaC1 = filaC1.createCell((short) 0);//COLUMNA
        celdaC1.setCellStyle(estiloCelda1);
        celdaC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoC1 = new HSSFRichTextString(txtC1);
        celdaC1.setCellValue(textoC1);
        filaC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC6 = hoja6.createRow(conDat + i);//filaC
        HSSFCell celdaC6 = filaC6.createCell((short) 0);//COLUMNA
        celdaC6.setCellStyle(estiloCeldabordes0);
        celdaC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC6 =Arrays.toString(ArrayResult.get(i));        
        txtC6=txtC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC6 = new HSSFRichTextString(parts0);
        celdaC6.setCellValue(textoC6);
        filaC6.setHeight((short)300);
        }
        
        HSSFRow filaC2 = hoja6.createRow(conEnc);//filaC
        HSSFCell celdaC2 = filaC2.createCell((short) 1);//COLUMNA
        celdaC2.setCellStyle(estiloCelda1);
        celdaC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoC2 = new HSSFRichTextString(txtC2);
        celdaC2.setCellValue(textoC2);
        filaC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC7 = hoja6.createRow(conDat + i);//filaC
        HSSFCell celdaC7 = filaC7.createCell((short) 1);//COLUMNA
        celdaC7.setCellStyle(estiloCeldabordes0);
        celdaC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC7 =Arrays.toString(ArrayResult.get(i));        
        txtC7=txtC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC7 = new HSSFRichTextString(parts0);
        celdaC7.setCellValue(textoC7);
        filaC7.setHeight((short)300);
        }
        
        HSSFRow filaC3 = hoja6.createRow(conEnc);//filaC
        HSSFCell celdaC3 = filaC3.createCell((short) 2);//COLUMNA
        celdaC3.setCellStyle(estiloCelda1);
        celdaC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoC3 = new HSSFRichTextString(txtC3);
        celdaC3.setCellValue(textoC3);
        filaC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC8 = hoja6.createRow(conDat + i);//filaC
        HSSFCell celdaC8 = filaC8.createCell((short) 2);//COLUMNA
        celdaC8.setCellStyle(estiloCeldabordes0);
        celdaC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC8 =Arrays.toString(ArrayResult.get(i));        
        txtC8=txtC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC8 = new HSSFRichTextString(parts0);
        celdaC8.setCellValue(textoC8);
        filaC8.setHeight((short)300);
        }
        
        HSSFRow filaC4 = hoja6.createRow(conEnc);//filaC
        HSSFCell celdaC4 = filaC4.createCell((short) 3);//COLUMNA
        celdaC4.setCellStyle(estiloCelda1);
        celdaC4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC4 ="FECHA DE AUDIENCIA DE JUICIO";        
        HSSFRichTextString textoC4 = new HSSFRichTextString(txtC4);
        celdaC4.setCellValue(textoC4);
        filaC4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja6.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 3);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 =Arrays.toString(ArrayResult.get(i));        
        txtC9=txtC9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtC9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(parts0);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
        }
       
        HSSFRow filaC5 = hoja6.createRow(conEnc);//filaC
        HSSFCell celdaC5 = filaC5.createCell((short) 4);//COLUMNA
        celdaC5.setCellStyle(estiloCelda1);
        celdaC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC5 ="OBSERVACIONES";        
        HSSFRichTextString textoC5 = new HSSFRichTextString(txtC5);
        celdaC5.setCellValue(textoC5);
        filaC5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaC9 = hoja6.createRow(conDat + i);//filaC
        HSSFCell celdaC9 = filaC9.createCell((short) 4);//COLUMNA
        celdaC9.setCellStyle(estiloCeldabordes0);
        celdaC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtC9 ="La Fecha de audiencia de juicio no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoC9 = new HSSFRichTextString(txtC9);
        celdaC9.setCellValue(textoC9);
        filaC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
    
        
        ArrayResult=SSocial.Fecha_Audienciap();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaD00 = hojaresumenval.createRow(7);//filaD
        HSSFCell celdaD00 = filaD00.createCell((short) 1);//COLUMNA    
        celdaD00.setCellStyle(PAmarillo);
         
        HSSFRow filaD1 = hoja6.createRow(conEnc);//filaD
        HSSFCell celdaD1 = filaD1.createCell((short) 0);//COLUMNA
        celdaD1.setCellStyle(estiloCelda1);
        celdaD1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtD1);
        celdaD1.setCellValue(textoD1);
        filaD1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD6 = hoja6.createRow(conDat + i);//filaD
        HSSFCell celdaD6 = filaD6.createCell((short) 0);//COLUMNA
        celdaD6.setCellStyle(estiloCeldabordes0);
        celdaD6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD6 =Arrays.toString(ArrayResult.get(i));        
        txtD6=txtD6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaD6.setCellValue(textoD6);
        filaD6.setHeight((short)300);
        }
        
        HSSFRow filaD2 = hoja6.createRow(conEnc);//filaD
        HSSFCell celdaD2 = filaD2.createCell((short) 1);//COLUMNA
        celdaD2.setCellStyle(estiloCelda1);
        celdaD2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtD2);
        celdaD2.setCellValue(textoD2);
        filaD2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD7 = hoja6.createRow(conDat + i);//filaD
        HSSFCell celdaD7 = filaD7.createCell((short) 1);//COLUMNA
        celdaD7.setCellStyle(estiloCeldabordes0);
        celdaD7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD7 =Arrays.toString(ArrayResult.get(i));        
        txtD7=txtD7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaD7.setCellValue(textoD7);
        filaD7.setHeight((short)300);
        }
        
        HSSFRow filaD3 = hoja6.createRow(conEnc);//filaD
        HSSFCell celdaD3 = filaD3.createCell((short) 2);//COLUMNA
        celdaD3.setCellStyle(estiloCelda1);
        celdaD3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtD3);
        celdaD3.setCellValue(textoD3);
        filaD3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD8 = hoja6.createRow(conDat + i);//filaD
        HSSFCell celdaD8 = filaD8.createCell((short) 2);//COLUMNA
        celdaD8.setCellStyle(estiloCeldabordes0);
        celdaD8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD8 =Arrays.toString(ArrayResult.get(i));        
        txtD8=txtD8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaD8.setCellValue(textoD8);
        filaD8.setHeight((short)300);
        }
        
        HSSFRow filaD4 = hoja6.createRow(conEnc);//filaD
        HSSFCell celdaD4 = filaD4.createCell((short) 3);//COLUMNA
        celdaD4.setCellStyle(estiloCelda1);
        celdaD4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD4 ="FECHA DE AUDIENCIA PRELIMINAR";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtD4);
        celdaD4.setCellValue(textoD4);
        filaD4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja6.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 3);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 =Arrays.toString(ArrayResult.get(i));        
        txtD9=txtD9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtD9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
        }
       
        HSSFRow filaD5 = hoja6.createRow(conEnc);//filaD
        HSSFCell celdaD5 = filaD5.createCell((short) 4);//COLUMNA
        celdaD5.setCellStyle(estiloCelda1);
        celdaD5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtD5);
        celdaD5.setCellValue(textoD5);
        filaD5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaD9 = hoja6.createRow(conDat + i);//filaD
        HSSFCell celdaD9 = filaD9.createCell((short) 4);//COLUMNA
        celdaD9.setCellStyle(estiloCeldabordes0);
        celdaD9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtD9 ="La Fecha de audiencia preliminar no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtD9);
        celdaD9.setCellValue(textoD9);
        filaD9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
    
        
        ArrayResult=SSocial.Fecha_Emplazam_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(7);//filaE
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoD1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoD6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoD2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoD7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoD3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoD8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA DE EMPLAZAMIENTO";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoD4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoD5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha de emplazamiento de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
      }
        
        
        
                        ArrayResult=SSocial.Fecha_Resolucion_Aj();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(7);//filaE
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoD1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoD6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoD2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoD7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoD3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoD8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA RESOLUCION (AUDIENCIA DE JUICIO)";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoD4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoD5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha en la que se dictó la resolución (Audiencia de juicio) no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }

        
        
        ArrayResult=SSocial.Fecha_Resolucion_Ap();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaE00 = hojaresumenval.createRow(7);//filaE
        HSSFCell celdaE00 = filaE00.createCell((short) 1);//COLUMNA    
        celdaE00.setCellStyle(PAmarillo);
         
        HSSFRow filaE1 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE1 = filaE1.createCell((short) 0);//COLUMNA
        celdaE1.setCellStyle(estiloCelda1);
        celdaE1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoD1 = new HSSFRichTextString(txtE1);
        celdaE1.setCellValue(textoD1);
        filaE1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE6 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE6 = filaE6.createCell((short) 0);//COLUMNA
        celdaE6.setCellStyle(estiloCeldabordes0);
        celdaE6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE6 =Arrays.toString(ArrayResult.get(i));        
        txtE6=txtE6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD6 = new HSSFRichTextString(parts0);
        celdaE6.setCellValue(textoD6);
        filaE6.setHeight((short)300);
        }
        
        HSSFRow filaE2 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE2 = filaE2.createCell((short) 1);//COLUMNA
        celdaE2.setCellStyle(estiloCelda1);
        celdaE2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoD2 = new HSSFRichTextString(txtE2);
        celdaE2.setCellValue(textoD2);
        filaE2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE7 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE7 = filaE7.createCell((short) 1);//COLUMNA
        celdaE7.setCellStyle(estiloCeldabordes0);
        celdaE7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE7 =Arrays.toString(ArrayResult.get(i));        
        txtE7=txtE7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD7 = new HSSFRichTextString(parts0);
        celdaE7.setCellValue(textoD7);
        filaE7.setHeight((short)300);
        }
        
        HSSFRow filaE3 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE3 = filaE3.createCell((short) 2);//COLUMNA
        celdaE3.setCellStyle(estiloCelda1);
        celdaE3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoD3 = new HSSFRichTextString(txtE3);
        celdaE3.setCellValue(textoD3);
        filaE3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE8 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE8 = filaE8.createCell((short) 2);//COLUMNA
        celdaE8.setCellStyle(estiloCeldabordes0);
        celdaE8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE8 =Arrays.toString(ArrayResult.get(i));        
        txtE8=txtE8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD8 = new HSSFRichTextString(parts0);
        celdaE8.setCellValue(textoD8);
        filaE8.setHeight((short)300);
        }
        
        HSSFRow filaE4 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE4 = filaE4.createCell((short) 3);//COLUMNA
        celdaE4.setCellStyle(estiloCelda1);
        celdaE4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE4 ="FECHA RESOLUCION (AUDIENCIA PRELIMINAR)";        
        HSSFRichTextString textoD4 = new HSSFRichTextString(txtE4);
        celdaE4.setCellValue(textoD4);
        filaE4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 3);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 =Arrays.toString(ArrayResult.get(i));        
        txtE9=txtE9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtE9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(parts0);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
        }
       
        HSSFRow filaE5 = hoja6.createRow(conEnc);//filaE
        HSSFCell celdaE5 = filaE5.createCell((short) 4);//COLUMNA
        celdaE5.setCellStyle(estiloCelda1);
        celdaE5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE5 ="OBSERVACIONES";        
        HSSFRichTextString textoD5 = new HSSFRichTextString(txtE5);
        celdaE5.setCellValue(textoD5);
        filaE5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaE9 = hoja6.createRow(conDat + i);//filaE
        HSSFCell celdaE9 = filaE9.createCell((short) 4);//COLUMNA
        celdaE9.setCellStyle(estiloCeldabordes0);
        celdaE9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtE9 ="La Fecha en la que se dictó la resolución (Audiencia Preliminar) no debe ser menor a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoD9 = new HSSFRichTextString(txtE9);
        celdaE9.setCellValue(textoD9);
        filaE9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }
  
    }
    
    public void Despliega_Ejecucion(HSSFWorkbook libro,HSSFSheet hoja7,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
     V1QEjecucion Ejecucion=new V1QEjecucion ();
        
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja7.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO EJECUCION ";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja7.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
        
         ArrayResult=Ejecucion.Año_Expe_Ejecucion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(8);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja7.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja7.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja7.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja7.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja7.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja7.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja7.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja7.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja7.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja7.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja7.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja7.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }
        
        
        ArrayResult=Ejecucion.Fecha_Conclusion();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(8);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja7.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja7.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja7.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja7.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja7.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja7.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja7.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA CONCLUCION";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja7.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja7.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja7.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha en la que se concluyó el procedimiento de ejecución no debe ser menor  a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }   
    }
    
    public void Despliega_Colect_Econom(HSSFWorkbook libro,HSSFSheet hoja9,HSSFSheet hojaresumenval,HSSFCellStyle estiloCelda0,HSSFCellStyle estiloCelda1, HSSFCellStyle estiloCeldabordes0,HSSFCellStyle PAmarillo){
    
     V1QColec_Econom Economico=new V1QColec_Econom ();
       
        int conEnc=1,conDat=2,coni=1;      
        //fecha de admision de la demanda
        HSSFRow row0 = hoja9.createRow((short) 0);//Fila
        HSSFCell celda0 = row0.createCell((short) 0);//Coumna
        celda0.setCellStyle(estiloCelda0);
        celda0.setCellType(HSSFCell.CELL_TYPE_STRING);
        String titulo0 = " PROCEDIMIENTO COLECTIVO ECONOMICO";
        HSSFRichTextString texto0 = new HSSFRichTextString((titulo0));
        hoja9.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
        celda0.setCellValue(texto0);
        row0.setHeight((short) 600);
        
         ArrayResult=Economico.Año_Expe_ColecEconom();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaBC00 = hojaresumenval.createRow(9);//filaBC
        HSSFCell celdaBC00 = filaBC00.createCell((short) 1);//COLUMNA    
        celdaBC00.setCellStyle(PAmarillo);
         
        HSSFRow filaBC1 = hoja9.createRow(conEnc);//filaBC
        HSSFCell celdaBC1 = filaBC1.createCell((short) 0);//COLUMNA
        celdaBC1.setCellStyle(estiloCelda1);
        celdaBC1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoA1 = new HSSFRichTextString(txtBC1);
        celdaBC1.setCellValue(textoA1);
        filaBC1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC6 = hoja9.createRow(conDat + i);//filaBC
        HSSFCell celdaBC6 = filaBC6.createCell((short) 0);//COLUMNA
        celdaBC6.setCellStyle(estiloCeldabordes0);
        celdaBC6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC6 =Arrays.toString(ArrayResult.get(i));        
        txtBC6=txtBC6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC6.split(",");
        parts0=parts[0].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA6 = new HSSFRichTextString(parts0);
        celdaBC6.setCellValue(textoA6);
        filaBC6.setHeight((short)300);
        }
        
        HSSFRow filaBC2 = hoja9.createRow(conEnc);//filaBC
        HSSFCell celdaBC2 = filaBC2.createCell((short) 1);//COLUMNA
        celdaBC2.setCellStyle(estiloCelda1);
        celdaBC2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoA2 = new HSSFRichTextString(txtBC2);
        celdaBC2.setCellValue(textoA2);
        filaBC2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC7 = hoja9.createRow(conDat + i);//filaBC
        HSSFCell celdaBC7 = filaBC7.createCell((short) 1);//COLUMNA
        celdaBC7.setCellStyle(estiloCeldabordes0);
        celdaBC7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC7 =Arrays.toString(ArrayResult.get(i));        
        txtBC7=txtBC7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC7.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA7 = new HSSFRichTextString(parts0);
        celdaBC7.setCellValue(textoA7);
        filaBC7.setHeight((short)300);
        }
        
        HSSFRow filaBC3 = hoja9.createRow(conEnc);//filaBC
        HSSFCell celdaBC3 = filaBC3.createCell((short) 2);//COLUMNA
        celdaBC3.setCellStyle(estiloCelda1);
        celdaBC3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoA3 = new HSSFRichTextString(txtBC3);
        celdaBC3.setCellValue(textoA3);
        filaBC3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC8 = hoja9.createRow(conDat + i);//filaBC
        HSSFCell celdaBC8 = filaBC8.createCell((short) 2);//COLUMNA
        celdaBC8.setCellStyle(estiloCeldabordes0);
        celdaBC8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC8 =Arrays.toString(ArrayResult.get(i));        
        txtBC8=txtBC8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtBC8.split(",");
        parts0=parts[2].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA8 = new HSSFRichTextString(parts0);
        celdaBC8.setCellValue(textoA8);
        filaBC8.setHeight((short)300);
        }
        
        HSSFRow filaBC5 = hoja9.createRow(conEnc);//filaBC
        HSSFCell celdaBC5 = filaBC5.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC5 = hoja9.createRow(conEnc);//filaBC
        HSSFCell celdaBBC5 = filaBBC5.createCell((short) 4);//COLUMNA
        celdaBC5.setCellStyle(estiloCelda1);
        celdaBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC5.setCellStyle(estiloCelda1);
        celdaBBC5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC5 ="OBSERVACIONES";        
        HSSFRichTextString textoA5 = new HSSFRichTextString(txtBC5);
        hoja9.addMergedRegion(new Region(conEnc, (short) 3, conEnc, (short) 4));
        celdaBC5.setCellValue(textoA5);
        filaBC5.setHeight((short)600);
        
        //OBCservaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaBC9 = hoja9.createRow(conDat + i);//filaBC
        HSSFCell celdaBC9 = filaBC9.createCell((short) 3);//COLUMNA
        HSSFRow filaBBC9 = hoja9.createRow(conDat + i);//filaBC
        HSSFCell celdaBBC9 = filaBBC9.createCell((short) 4);//COLUMNA
        celdaBC9.setCellStyle(estiloCeldabordes0);
        celdaBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        celdaBBC9.setCellStyle(estiloCeldabordes0);
        celdaBBC9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtBC9 ="Favor de verificar el año de la clave del expediente con respecto al año de ingreso del mismo.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoA9 = new HSSFRichTextString(txtBC9);
        hoja9.addMergedRegion(new Region(conDat + i, (short) 3, conDat + i, (short) 4));
        celdaBC9.setCellValue(textoA9);
        filaBC9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador EncaBCezado: "+conEnc + "Contador Datos: "+ conDat );
        }   
        
          ArrayResult=Economico.Fecha_Admision_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(8);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA ADMISION DEMANDA";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de admision de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }   
        
        
         ArrayResult=Economico.Fecha_Emplazam_Demanda();
        if (ArrayResult.size()>0)
        {
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );    
        HSSFRow filaB00 = hojaresumenval.createRow(8);//filaB
        HSSFCell celdaB00 = filaB00.createCell((short) 1);//COLUMNA    
        celdaB00.setCellStyle(PAmarillo);
         
        HSSFRow filaB1 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB1 = filaB1.createCell((short) 0);//COLUMNA
        celdaB1.setCellStyle(estiloCelda1);
        celdaB1.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB1 ="CLAVE DEL ORGANO";        
        HSSFRichTextString textoB1 = new HSSFRichTextString(txtB1);
        celdaB1.setCellValue(textoB1);
        filaB1.setHeight((short)600);
        
        //clave_organo
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB6 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB6 = filaB6.createCell((short) 0);//COLUMNA
        celdaB6.setCellStyle(estiloCeldabordes0);
        celdaB6.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB6 =Arrays.toString(ArrayResult.get(i));        
        txtB6=txtB6.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB6.split(",");
        parts0=parts[1].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB6 = new HSSFRichTextString(parts0);
        celdaB6.setCellValue(textoB6);
        filaB6.setHeight((short)300);
        }
        
        HSSFRow filaB2 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB2 = filaB2.createCell((short) 1);//COLUMNA
        celdaB2.setCellStyle(estiloCelda1);
        celdaB2.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB2 ="CLAVE DEL EXPEDIENTE";        
        HSSFRichTextString textoB2 = new HSSFRichTextString(txtB2);
        celdaB2.setCellValue(textoB2);
        filaB2.setHeight((short)600);
        
         //clave del expediente
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB7 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB7 = filaB7.createCell((short) 1);//COLUMNA
        celdaB7.setCellStyle(estiloCeldabordes0);
        celdaB7.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB7 =Arrays.toString(ArrayResult.get(i));        
        txtB7=txtB7.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB7.split(",");
        parts0=parts[2].trim();
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB7 = new HSSFRichTextString(parts0);
        celdaB7.setCellValue(textoB7);
        filaB7.setHeight((short)300);
        }
        
        HSSFRow filaB3 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB3 = filaB3.createCell((short) 2);//COLUMNA
        celdaB3.setCellStyle(estiloCelda1);
        celdaB3.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB3 ="FECHA DE APERTURA DEL EXPEDIENTE";        
        HSSFRichTextString textoB3 = new HSSFRichTextString(txtB3);
        celdaB3.setCellValue(textoB3);
        filaB3.setHeight((short)600);
        
         //FECHA DE APERTURA DEL EXPEDIENTE
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB8 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB8 = filaB8.createCell((short) 2);//COLUMNA
        celdaB8.setCellStyle(estiloCeldabordes0);
        celdaB8.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB8 =Arrays.toString(ArrayResult.get(i));        
        txtB8=txtB8.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB8.split(",");
        parts0=parts[3].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB8 = new HSSFRichTextString(parts0);
        celdaB8.setCellValue(textoB8);
        filaB8.setHeight((short)300);
        }
        
        HSSFRow filaB4 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB4 = filaB4.createCell((short) 3);//COLUMNA
        celdaB4.setCellStyle(estiloCelda1);
        celdaB4.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB4 ="FECHA EMPLAZAMIENTO DEMANDA";        
        HSSFRichTextString textoB4 = new HSSFRichTextString(txtB4);
        celdaB4.setCellValue(textoB4);
        filaB4.setHeight((short)600);
        
        //FECHA DE admision de la demanda
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 3);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 =Arrays.toString(ArrayResult.get(i));        
        txtB9=txtB9.replace("[", "").replace("]", "").replace(" 00:00:00.0", "");
        parts=txtB9.split(",");
        parts0=parts[4].trim();
       // partsfechas=dateformat.format(parts0);
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(parts0);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
        }
       
        HSSFRow filaB5 = hoja9.createRow(conEnc);//filaB
        HSSFCell celdaB5 = filaB5.createCell((short) 4);//COLUMNA
        celdaB5.setCellStyle(estiloCelda1);
        celdaB5.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB5 ="OBSERVACIONES";        
        HSSFRichTextString textoB5 = new HSSFRichTextString(txtB5);
        celdaB5.setCellValue(textoB5);
        filaB5.setHeight((short)600);
        
        //Observaciones
        for (int i=0;i<ArrayResult.size();i++){
        HSSFRow filaB9 = hoja9.createRow(conDat + i);//filaB
        HSSFCell celdaB9 = filaB9.createCell((short) 4);//COLUMNA
        celdaB9.setCellStyle(estiloCeldabordes0);
        celdaB9.setCellType(HSSFCell.CELL_TYPE_STRING);
        String txtB9 ="La Fecha de emplazamiento de la demanda no debe ser menor o igual a la fecha de apertura del expediente.";
        //System.out.println(Arrays.toString(ArrayResult.get(i)));
        HSSFRichTextString textoB9 = new HSSFRichTextString(txtB9);
        celdaB9.setCellValue(textoB9);
        filaB9.setHeight((short)300);
         coni++;
        }
        conDat=conDat+coni;         
        conEnc=conEnc+coni;
        coni=1;
        //System.out.println("contador Encabezado: "+conEnc + "Contador Datos: "+ conDat );
        }   
        
        
        
    }
    
   
    public static void SaveFileTo(HSSFWorkbook libro) throws FileNotFoundException, IOException {
       DataOutputStream h = null;
        FileDialog d = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
        d.setFile("*.xls");
        d.setVisible(true);
        String dir;
        dir = d.getDirectory();
        String nomarchi=dir + d.getFile();
       int Pos=nomarchi.length();
       String Ext=nomarchi.substring(Pos-4,Pos);
        File oneFile = new File(dir + d.getFile());
       System.out.println(Ext);
       if (!dir.equals(null) ){
       if(Ext.equals(".xls")){
       FileOutputStream f = new FileOutputStream(oneFile);
       libro.write(f);
       f.close();
       JOptionPane.showMessageDialog(null,"Archivo Guardado Correctamente","",JOptionPane.INFORMATION_MESSAGE);
       }else{
       JOptionPane.showMessageDialog(null,"Paso Algo con el Archivo","",JOptionPane.WARNING_MESSAGE);   
       }
       }    
   }
    
    
}

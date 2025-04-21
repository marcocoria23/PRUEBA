/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.querys;

/**
 *
 * @author ANTONIO.CORIA
 */
import Conexion.OracleConexionFederal;
import Screen_laborales.PEstatus;
import Screen_laborales.PValidacion;
import Screen_laborales.Errores_InsertTMP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class V3QuerysFederal {
OracleConexionFederal conexion = new OracleConexionFederal();
String sql,NMunicipio;
ArrayList<String[]> Array;
ResultSet resul;   

 
     //Query  para el despliegue de la clave de organo unica estas claves se mandan a llamar a la pantalla con nombre PValidacion.
     public ArrayList Clave_organoNE(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(CLAVE_ORGANO)CLAVE_ORGANO from TR_FED_control_expediente order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
     
     
 public ArrayList Clave_organoAcumula(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(CLAVE_ORGANO)CLAVE_ORGANO from TR_FED_control_expediente where substr(clave_organo,0,2) in ('03','04','07','08','10','27','32') order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }    
     
   public ArrayList Clave_organoNEstatus(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(CLAVE_ORGANO)CLAVE_ORGANO from TR_FED_control_expediente where substr(clave_organo,0,2)='"+PEstatus.Entidad+"' order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("CLAVE_ORGANO")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }   
     
     
      //Query  para el despliegue de la entidad=substr(clave_organo,0,2) mandandolo a llamar a la pantalla con nombre PValidacion. 
   public ArrayList EntidadNE(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select unique(substr(clave_organo,0,2)) Entidad from TR_FED_control_expediente order by 1";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("Entidad")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
   
   
 public ArrayList Contacto(){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select ID, ENTIDAD_CLAVE, ENTIDAD_NOMBRE, JEFE_DEPARTAMENTO, ENLACE from CONTACTOS_RALAB order by 2";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("ID"),
                  resul.getString("ENTIDAD_CLAVE"),
                  resul.getString("ENTIDAD_NOMBRE"),
                  resul.getString("JEFE_DEPARTAMENTO"),
                  resul.getString("ENLACE")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }   
 
 
 public ArrayList TErroresInser(String Tabla,String claveorgano,String entidad){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select TABLA ,\n" +
"CLAVE_ORGANO ,CLAVE_EXPEDIENTE ,ID ,replace(EXCEPCION,',','') EXCEPCION,USUARIO ,FECHA_HORA from ERRORES_INSERT_RALABTMP\n" +
"WHERE TABLA='"+Tabla+"' AND SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' OR\n" +
" TABLA='"+Tabla+"' AND CLAVE_ORGANO='"+claveorgano+"'  ORDER BY TABLA,CLAVE_ORGANO,CLAVE_EXPEDIENTE";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("TABLA"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("CLAVE_EXPEDIENTE"),
                  resul.getString("ID"),
                  resul.getString("EXCEPCION"),
                  resul.getString("USUARIO"),
                  resul.getString("FECHA_HORA")                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }   
 
 
 public ArrayList TAllErroresInser(String Tabla,String claveorgano,String entidad){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select TABLA ,\n" +
"CLAVE_ORGANO ,CLAVE_EXPEDIENTE ,ID ,replace(EXCEPCION,',','') EXCEPCION,USUARIO ,FECHA_HORA from ERRORES_INSERT_RALABTMP\n" +
"WHERE  SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' OR\n" +
"CLAVE_ORGANO='"+claveorgano+"'  ORDER BY TABLA,CLAVE_ORGANO,CLAVE_EXPEDIENTE";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("TABLA"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("CLAVE_EXPEDIENTE"),
                  resul.getString("ID"),
                  resul.getString("EXCEPCION"),
                  resul.getString("USUARIO"),
                  resul.getString("FECHA_HORA")                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
 
 public void EliminaRegERRORES_INSERT_RALAB(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
     
      sql="DELETE FROM ERRORES_INSERT_RALABTMP WHERE CLAVE_ORGANO='"+claveorgano+"' AND TABLA='"+Tabla+"'"
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' AND TABLA='"+Tabla+"'";
      System.out.println(sql);
      try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
       
}
 
  
 public void EliminaRegTMPRalab(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
     
   
      sql="DELETE FROM "+Tabla+" WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' ";
      System.out.println(sql);
      try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:"+Tabla);
        }
       
}
 
 public ArrayList TablaErroresInsert(String entidad,String claveorgano){
     conexion.Conectar();
      Array = new ArrayList(); 
      sql="SELECT UNIQUE(TABLA) TABLA FROM ERRORES_INSERT_RALABTMP WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' ";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("TABLA")
                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
       
}
   
 public String Total_Reg_NI(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_Reg FROM ERRORES_INSERT_RALABTMP WHERE CLAVE_ORGANO='"+claveorgano+"' AND TABLA='"+Tabla+"'"
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' AND TABLA='"+Tabla+"'";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_Reg");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;
       
} 
 
 
 public String AllTotal_Reg_NI(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_Reg FROM ERRORES_INSERT_RALABTMP WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' ";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_Reg");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;
       
}
 
 public String Total_Reg_insertados(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_Reg FROM "+Tabla+" WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'  ";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_Reg");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALABTMP");
        }
      return TotalReg;
       
} 
 
    //Query  para el despliegue de la descripcion del municipio.
  public String Nombre_Municipio(){
     conexion.Conectar();
      sql="select Descripcion from tc_municipio where municipio_id="+PValidacion.clave_organo.substring(0, 5)+"";
      System.out.println(PValidacion.clave_organo.substring(0, 5));
      resul=conexion.consultar(sql);
      try {
          if (resul.next()) {
              NMunicipio=resul.getString("Descripcion");
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return NMunicipio;
 }
  
  
  public void Limpia_ERRORES_INSERT_RALABTR(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
      sql="DELETE FROM ERRORES_INSERT_RALABTR WHERE CLAVE_ORGANO='"+claveorgano+"' AND TABLA="+Tabla+" "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' AND TABLA="+Tabla+"  ";
      System.out.println(sql);
     try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:"+Tabla);
        }
}
  
  public void Limpia_ERRORES_INSERT_RALAB_INICIO(String entidad,String claveorgano){
 conexion.Conectar();
      sql="DELETE FROM ERRORES_INSERT_RALABTR WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'  ";
      System.out.println(sql);
     try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:"+Tabla);
        }
}
  
  public ArrayList TErroresInserTR(String Tabla,String claveorgano,String entidad){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select TABLA ,\n" +
"CLAVE_ORGANO ,CLAVE_EXPEDIENTE ,ID ,replace(EXCEPCION,',','') EXCEPCION,USUARIO ,FECHA_HORA from ERRORES_INSERT_RALABTR \n" +
"WHERE TABLA='"+Tabla+"' AND SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' OR\n" +
" TABLA='"+Tabla+"' AND CLAVE_ORGANO='"+claveorgano+"'  ORDER BY CLAVE_ORGANO,CLAVE_EXPEDIENTE";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("TABLA"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("CLAVE_EXPEDIENTE"),
                  resul.getString("ID"),
                  resul.getString("EXCEPCION"),
                  resul.getString("USUARIO"),
                  resul.getString("FECHA_HORA")                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  
  
  
  
  public ArrayList TErroresInserTRInicio(String claveorgano,String entidad){
     conexion.Conectar();
      Array = new ArrayList();
      sql="select TABLA ,\n" +
"CLAVE_ORGANO ,CLAVE_EXPEDIENTE ,ID ,replace(EXCEPCION,',','') EXCEPCION,USUARIO ,FECHA_HORA from ERRORES_INSERT_RALABTR \n" +
"WHERE   SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' OR\n" +
" CLAVE_ORGANO='"+claveorgano+"'  ORDER BY TABLA,CLAVE_ORGANO,CLAVE_EXPEDIENTE";
      System.out.println(sql);
      resul=conexion.consultar(sql);
      try {
          while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("TABLA"),
                  resul.getString("CLAVE_ORGANO"),
                  resul.getString("CLAVE_EXPEDIENTE"),
                  resul.getString("ID"),
                  resul.getString("EXCEPCION"),
                  resul.getString("USUARIO"),
                  resul.getString("FECHA_HORA")                });
          }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(V1querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Array;
 }
  
  public String Total_Reg_insertadosTR(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_Reg FROM "+Tabla+" WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'  ";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_Reg");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALABTMP");
        }
      return TotalReg;
       
} 
  
  public String Total_Reg_NITR(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_Reg FROM ERRORES_INSERT_RALABTR WHERE CLAVE_ORGANO='"+claveorgano+"' AND TABLA='"+Tabla+"'"
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' AND TABLA='"+Tabla+"'";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_Reg");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;
       
} 
  
  
  public String Total_Reg_TMP(String Tabla,String entidad,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT COUNT(*) Total_RegTMP FROM " +Tabla.replace("TR", "TMP")+"  WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_RegTMP");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;   
} 
  
  
  
  public String Total_Reg_Acum(String Tabla,String claveorgano){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT count(*)Total_RegAcum FROM  "+Tabla+" \n" +
"WHERE CLAVE_ORGANO='"+claveorgano+"' \n" +
"AND EXPEDIENTE_CLAVE NOT IN (SELECT EXPEDIENTE_CLAVE FROM "+Tabla+" WHERE CLAVE_ORGANO='"+claveorgano+"'\n" +
") ";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_RegAcum");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;   
} 
  
  
  
  public void CleanerBD(String Tabla,String entidad,String claveorgano){
   conexion.Conectar();
      sql="DELETE FROM " +Tabla+ " WHERE CLAVE_ORGANO='"+claveorgano+"' "
              + " OR SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"' ";
      System.out.println(sql);
      try {
      conexion.escribir(sql);   
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
     }
       
}
  
  
  public String Total_Organos(String entidad){
 conexion.Conectar();
 String TotalReg="";
      sql="SELECT count(unique(CLAVE_ORGANO))Total_organos FROM TR_FED_CONTROL_EXPEDIENTE \n" +
"WHERE SUBSTR(CLAVE_ORGANO,0,2)='"+entidad+"'" ;
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_organos");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QEstatus.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:ERRORES_INSERT_RALAB");
        }
      return TotalReg;   
} 
  
}
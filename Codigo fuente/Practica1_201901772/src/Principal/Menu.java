
package Principal;
//importo todos mis paquetes ya que cree varios y dentro de ellos clases que utilizo en esta clase
import Archivos.ManejadordeArchivos;
import Desencriptar.Decifrar;
import Desencriptar.Inversa;
import Encriptacion.Encriptar;
import Encriptacion.Multiplicacion;
import Gauss.GaussJordan;
import java.io.IOException;

/*
 *
 * @author Daniel Dubón
 */
public class Menu {
    //Declaro objetos y variables que utilizare en mi programa de forma global
    Teclado entrada =new Teclado();
    Utilidades util=new Utilidades();
    String cadena;
    public static StringBuilder rc; //esta variable es para construir una cadena y crear el reporte de cifrar
    StringBuilder cssrc,cssD, reported, dds;
    
    
    public void MenuPrincipal() throws IOException, InterruptedException //aca empiezo hacer mi menu y agrego excepciones ya que limpio la consola
    {
        util.limpiar();
        boolean activo=true;
        while(activo)
        {
           util.limpiar();//limpia la consola
           System.out.println("----------------------MENU PRINCIPAL---------------------");    //muestro todas las opciones de mi menu principal
           System.out.println("1. Cifrar \n2. Descifrar \n3. Gauss-Jordan \n4. Salir");
           int opcion = entrada.getInt("Seleccione una opcion");
           
           switch(opcion)
           {
               //aca veo que opcion eligen y mando a llamar mi metodo correspondiente que realiza tales acciones mencionadas
               case 1: util.limpiar();Cifrar(); break;
               case 2: util.limpiar(); DesCifrar();break;
               case 3: util.limpiar(); Gauss(); break;
               case 4: activo=false; break;
               //en caso de no encuntrar una opcion valida mostrar mensaje
               default: System.out.println("OPCION NO VALIDA"); util.pausa(); break;          
           }
        }
        
    }
    
    public void Cifrar() throws IOException, InterruptedException //aca hago mi metodo que cifra una cadena
    {
        //Declaro variables y objetos que utilizare en mi metodo
        ManejadordeArchivos arc=new ManejadordeArchivos();//este objeto es de mi clase manejadora de archivos se encarga de leer y crear archivos
        Funciones fn=new Funciones();//creo un objeto que contiene funciones basicas
        rc=new StringBuilder(); //instancio mi constructor de texto para crear mi reporte
        cssrc=new StringBuilder();
        Encriptar cifrar=new Encriptar();//este objeto tiene un metodo que me cifra el texto
        Multiplicacion multiplicar=new Multiplicacion();//este objeto contiene el metodo de multiplicacion de matrices
        String path, cadena;
        util.limpiar();//esto me limpia la consola
        System.out.println("-------------------------Cifrar------------------------"); //muestro instrucciones
        System.out.println("Ingrese el texto a cifrar");
        cadena=entrada.getCadena1("> ");//obtengo la cadena a cifrar
        headerCifrar(cadena);
        System.out.println("");
        System.out.println("Ingrese la Ruta del Archivo");
        path=entrada.getCadena("> ");//obtengo la ruta del archivo
        if(arc.verificarArchivo(path))//verifico si el archivo existe
        {
           fn.arregloCadena(path);//extraigo todo lo que contiene el archivo ingresado
           cifrar.tamanioMatriz(cadena);//ejecuto mi metodo que halla las dimenciones de mi matriz
           if(fn.dimension()==cifrar.obtenerCuadrada())//verfico que mi matriz ingresada sea cuadrada
           {
               cifrar.llenarAscii(cadena);//con este metodo convierto mi cadena en caracteres ascii
               int[][] resultado=new int[cifrar.obtenerfila()][cifrar.obtenercolumna()];
               multiplicar.algoritmo(fn.obtenervecInt(cifrar.obtenerfila()),cifrar.obtenerTxt(),resultado,cifrar.obtenercolumna());//aca multiplico la llave con la matriz ascii
               crearcssrc();//creo mis css y mi reporte.html
               arc.crearArchivo(cssrc.toString(), "styleC.css");
               crearfinalrc(multiplicar.devolvercifrado());
               arc.crearArchivo(rc.toString(), "ReporteCifrado.html");
               System.out.println("");
               util.pausa();
           }
           //en caso de que no se cumpliera ninguna condicion muestro estos mensajes
           else
           {
               System.out.println("ERROR el sistema no es "+(int)Math.sqrt(cifrar.obtenerCuadrada())+" x "+(int)Math.sqrt(cifrar.obtenerCuadrada()));
           }
        }
        else
        {
           System.out.println("El archivo no exite en la ruta espeficada");
        }
     
    }
    
  public void DesCifrar() throws IOException, InterruptedException
    {
        ManejadordeArchivos arcT=new ManejadordeArchivos();//creo objetos que contienen metodos de manejador de archivos
        ManejadordeArchivos arcC=new ManejadordeArchivos();
        Funciones fnT=new Funciones();
        Funciones fnC=new Funciones();
        Inversa inv=new Inversa();
        Decifrar Dcifrar=new Decifrar();
        reported=new StringBuilder();
        dds=new StringBuilder();
        String pathC, pathT;
        util.limpiar();
        System.out.println("-------------------------Descifrar------------------------"); //muestro instrucciones
        System.out.println("Ingrese la Ruta del Archivo que contiene el texto cifrado");
        pathT=entrada.getCadena1("> ");
        System.out.println("");
        if(arcT.verificarArchivo(pathT))//verifico si existe el archivo
        {
           System.out.println("Ingrese la Ruta del Archivo que contiene la llave");
           pathC=entrada.getCadena("> "); System.out.println("");
           if(arcC.verificarArchivo(pathC))//verifico si existe el archivo
            {
                fnT.arregloCadena(pathT);
                fnC.arregloCadena(pathC);//obtengo mis diferentes matrices
                int filaT=fnT.filasA();
                int columnaT=(fnT.dimension()/filaT);
                int filaC=fnC.filasA();
                int columnaC=(fnC.dimension()/filaC);
                crearheaderd(filaT,columnaT,filaC,columnaC);//creo el header de mi reporte
                int aux=filaC*2;
                inv.algoritmo(fnC.obtenervecDobC(filaC, aux), filaC, (aux));
                double[][] resultado=new double[filaC][columnaT];
                llenarT(fnT.obtenervecDob(filaT, columnaT),filaT,columnaT);//estos metodo llenan el reporte
                llenarC(fnC.obtenervecDobC(filaC, aux),filaC,columnaC);
                llenarInv(inv.regresarInversa(),filaC,columnaC);
                Dcifrar.algoritmo(inv.regresarInversa(), fnT.obtenervecDob(filaT, columnaT), resultado, columnaT);
                llenarDs(Dcifrar.devolverm(),filaT,columnaT);
                llenarDA(Dcifrar.devolverm(),filaT,columnaT);
                crearcssD();
                crearfinald(dds.toString());//crea los reportes
                arcT.crearArchivo(cssD.toString(), "styleD.css");
                arcT.crearArchivo(reported.toString(), "ReporteDescifrado.html");
                util.pausa();
            }
            else
            {
                System.out.println("El archivo de llave no exite en la ruta espeficada");
            }
        }
        else
        {
           System.out.println("El archivo de texto cifrado no exite en la ruta espeficada");
        }
     
    }
    
    public void Gauss() throws IOException, InterruptedException// este metodo realiza los calculos para resolver mi sistema de ecuaciones
    {
        //creo mis variables y objetos que utilizare en la ejecucion del programa
       ManejadordeArchivos arc=new ManejadordeArchivos();//este objeto tiene un metodo que me permite leer los archivos
       Funciones fn=new Funciones();
       GaussJordan resolver =new GaussJordan();//este objeto tiene el algortimo para aplicar gauss-jordan
       String path;
       util.limpiar();
       System.out.println("-------------------------Gauss-Jordan------------------------"); //muestro las instrucciones
       System.out.println("Ingrese la Ruta del Archivo\n");
       path=entrada.getCadena("> ");//obtengo la ruta del archivo
       if(arc.verificarArchivo(path))//verifico que el archivo que contiene la matriz exista en la ubicacion mencionada
       {
           fn.arregloCadena(path);//extraigo lo que tiene el archivo
           if(fn.dimension()==12)//verifico que la matriz ingresada sea un sistema 3x3
           {
               resolver.algoritmo(fn.obtenervec(), 3);//mando los datos obtenidos al algoritmo que se encarga de resolverlo
           }
           //en caso de no cumplirse mis condiciones muestro tales mensajes
           else
           {
               System.out.println("ERROR el sistema no es 3x3");
           }
           System.out.println("");
           util.pausa();
       }
       else
       {
           System.out.println("El archivo no exite en la ruta espeficada");
       }
    }
    
     public void headerCifrar(String cadena)//aca creo el header de mi reporte cifrar
     {
        rc.append("<!DOCTYPE html>\n" +"<html>\n" +"<head>\n" +"<link rel=\"stylesheet\" type=\"text/css\" href=\"styleC.css\">\n" +"<title>Reporte Cifrado</title>\n" +
        "</head>\n" +"<body>\n" +"<div id=\"contenedor\">\n" +
        "<h3 class=\"normal\">Texto ingresado por el usuario<span class=\"forma\"></span></h3>\n" +
        "<p align=\"center\">"+cadena+"</p>\n" +
        "<h3 class=\"derecha\" id=\"e2\">Texto convertido a una matriz<span class=\"formad\"></span></h3>\n" +
        "<h3 class=\"normal\" id=\"e3\">Texto convertido a Ascii<span class=\"forma\"></span></h3>\n" +
        "<h3 class=\"derecha\" id=\"e4\">Llave Ingresada<span class=\"formad\"></span></h3>\n" +
        "<h3 class=\"normal\" id=\"e5\">Llave multplicada por la matriz Ascii<span class=\"forma\"></span></h3>\n" +
        "<h3 class=\"derecha\" id=\"e6\">Texto Cifrado<span class=\"formad\"></span></h3>\n");}
     
     public void crearfinalrc(String texto)//creo el final de mi reporte
     {
         rc.append("	</div>\n" +"<div class=\"caja\">"+texto+"</div>\n" +"</body>\n" +"</html>");
     }
     
     public void crearcssrc()//creo mi css de mi reporte cifrar
     {
        cssrc.append("#contenedor{width: 800px; height: 2330px; font-family: Arial; background-color: #ccc; margin: auto; padding-top: 30px;}\n" +
        ".normal{color: #fff; background-color: #09f; padding: 10px 20px; margin-left: -20px;position:relative;width: 50%; box-shadow: 1px 1px 5px #000;margin-bottom: 50px;}\n" +
        ".forma{width: 0px; height: 0px;	line-height: 0px; border-left: 20px solid transparent; border-top: 10px solid #03f; position: absolute;top: 100%;left: 0px;}\n" +
        ".derecha{color: #fff; background-color: #09f; padding: 10px 20px; margin-right: -20px;position:relative;width: 50%; box-shadow: 1px 1px 5px #000;margin-bottom: 70px;float: right;}\n" +
        ".formad{width: 0px; height: 0px;	line-height: 0px; border-right: 20px solid transparent; border-top: 10px solid #03f; position: absolute;top: 100%; right: 0px;}\n" +
        ".norma{color: #fff; background-color: #09f; padding: 10px 20px; margin-left: 20px;position:relative; box-shadow: 1px 1px 5px #000;margin-bottom: 50px;}\n" +
        "table{background-color: #ecf0f1;text-align: center;\n" +
        "width: 30%;border-collapse: collapse;left: 50%;\n" +
        "transform: translate(-50%,-50%);position: absolute;}\n" +
        "#tb1{top: 360px;}#tb2{top: 790px;} #tb3{top: 1250px;}#tb4{top: 1720px;}#tb5{top: 1900px;}#tb6{top: 2100px;}\n" +
        "#e3{top: 440px}#e4{top: 750px}#e5{top: 1240px}#e6{top: 1800px}\n" +
        "th, td{border: solid 1px black;padding: 8px;}\n" +
        "tr:hover td{background-color: #1289A7;color: white;}\n" +".caja { \n" +
        "font-family: Century Gothic,CenturyGothic,AppleGothic,sans-serif; \n" +
        "color: #ffffff; font-size: 16px; font-weight: 400; text-align: left; \n" +
        "background: #2ecc71; margin: 0 0 25px; overflow: hidden; padding: 20px; border-radius: 35px 0px 35px 0px; \n" +
        "-moz-border-radius: 35px 0px 35px 0px; -webkit-border-radius: 35px 0px 35px 0px; border: 2px solid #27ae60;}");
     }
     
     public void llenarrc(String s)//funcion que me sirve para llenar mi stringbuilder
     {
         rc.append(s);
     }
     
     public void crearcssD()//crea el css de decifrar

     {
        cssD=new StringBuilder();
        cssD.append("#contenedor{width: 830px; height: 2260px; font-family: Arial; background-color: #bdc3c7; margin: auto; padding-top: 30px;}\n" +
        ".normal{color: #fff; background-color: #e67e22; padding: 10px 20px; margin-left: -20px;position:relative;width: 50%; box-shadow: 1px 1px 5px #000;margin-bottom: 50px;}\n" +
        ".forma{width: 0px; height: 0px;	line-height: 0px; border-left: 20px solid transparent; border-top: 10px solid #d35400; position: absolute;top: 100%;left: 0px;}\n" +
        ".derecha{color: #fff; background-color: #e74c3c; padding: 10px 20px; margin-right: -20px;position:relative;width: 50%; box-shadow: 1px 1px 5px #000;margin-bottom: 70px;float: right;}\n" +
        ".formad{width: 0px; height: 0px;	line-height: 0px; border-right: 20px solid transparent; border-top: 10px solid #c0392b; position: absolute;top: 100%; right: 0px;}\n" +
        ".norma{color: #fff; background-color: #09f; padding: 10px 20px; margin-left: 20px;position:relative; box-shadow: 1px 1px 5px #000;margin-bottom: 50px;}\n" +
        "table{background-color: #ecf0f1;text-align: center;\n" +
        "width: 30%;border-collapse: collapse;left: 50%;\n" +
        "transform: translate(-50%,-50%);position: absolute;}\n" +
        "#tb1{top: 260px;}#tb2{top: 680px;} #tb3{top: 1070px;}#tb4{top: 1520px;}#tb5{top: 1980px;}#tb6{top: 2100px;}\n" +
        "#e2{top: 300px}#e3{top: 720px}#e4{top: 970px}#e5{top: 1480px}#e6{top: 1750px}\n" +
        "th, td{border: solid 1px black;padding: 8px;}\n" +
        "tr:hover td{background-color: #3498db;color: white;}\n" +".caja { \n" +
        "font-family: Century Gothic,CenturyGothic,AppleGothic,sans-serif; \n" +
        "color: #ffffff; font-size: 16px; font-weight: 400; text-align: left; \n" +
        "background: #2ecc71; margin: 0 0 25px; overflow: hidden; padding: 20px; border-radius: 35px 0px 35px 0px; \n" +
        "-moz-border-radius: 35px 0px 35px 0px; -webkit-border-radius: 35px 0px 35px 0px; border: 2px solid #27ae60;}");
     }
     
     public void crearheaderd(int filaT, int columnaT, int filaC, int columnaC)//crea el header de descifrar
     {
         reported.append("<!DOCTYPE html>\n" +
        "<html>\n" +"<head>\n" +"<link rel=\"stylesheet\" type=\"text/css\" href=\"styleD.css\">\n" +"<title>Reporte Cifrado</title>\n" +
        "</head>\n" +"<body>\n" +"<div id=\"contenedor\">\n" +
        "<h3 class=\"normal\">Mensaje Cifrado Matriz "+filaT+"x"+columnaT+"<span class=\"forma\"></span></h3>\n" +
        "<h3 class=\"derecha\" id=\"e2\">Llave Ingresada Matriz "+filaC+"x"+columnaC+"<span class=\"formad\"></span></h3>\n" +
        "<h3 class=\"normal\" id=\"e3\">Inversa de la llave<span class=\"forma\"></span></h3>\n" +
        "<h3 class=\"derecha\" id=\"e4\">Multiplicacion de la llave inversa con el mensaje cifrado<span class=\"formad\"></span></h3>\n" +
        "<h3 class=\"normal\" id=\"e5\">Matriz Descifrada<span class=\"forma\"></span></h3>\n" +
        "<h3 class=\"derecha\" id=\"e6\">Mensaje Descifrado<span class=\"formad\"></span></h3>");
     }
     
     public void crearfinald(String texto)//crea el final de descifrar
     {
        reported.append("</div>\n" +
        "<div class=\"caja\">"+texto+"</div>\n" +"</body>\n" +"</html>");
     }
     
     public void llenarreported(String s)//metodo que me llena el reporte
     {
         reported.append(s);
     }
     
     public void llenarT(double[][] matriz,int filaT, int columnaT)//llena en el reporte la matriz de texto
     {
            reported.append("<table id=\"tb1\">");
            for (int fila = 0; fila < filaT; fila++) 
            {
                reported.append("<tr>");
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    reported.append("<td>"+matriz[fila][columna]+"</td>");
                }
                reported.append("</tr>");
            }
            reported.append("</table>");
     }
     
     public void llenarC(double[][] matriz,int filaT, int columnaT)//llena el en reporte la matriz cuadrada
     {
            reported.append("<table id=\"tb2\">");
            for (int fila = 0; fila < filaT; fila++) 
            {
                reported.append("<tr>");
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    reported.append("<td>"+matriz[fila][columna]+"</td>");
                }
                reported.append("</tr>");
            }
            reported.append("</table>");
     }
     
     public void llenarInv(double[][] matriz,int filaT, int columnaT)//llena en el reporte la inversa
     {
            reported.append("<table id=\"tb3\">");
            for (int fila = 0; fila < filaT; fila++) 
            {
                reported.append("<tr>");
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    reported.append("<td>"+matriz[fila][columna]+"</td>");
                }
                reported.append("</tr>");
            }
            reported.append("</table>");
     }
     
     public void llenarDs(double[][] matriz,int filaT, int columnaT)//llena en el reporte la matriz descifrada
     {
            reported.append("<table id=\"tb4\">");
            for (int fila = 0; fila < filaT; fila++) 
            {
                reported.append("<tr>");
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    reported.append("<td>"+matriz[fila][columna]+"</td>");
                }
                reported.append("</tr>");
            }
            reported.append("</table>");
     }
     
     public void llenarDA(double[][] matriz,int filaT, int columnaT)//llena en el reporte la matriz en letras descifradas
     {
            reported.append("<table id=\"tb5\">");
            for (int fila = 0; fila < filaT; fila++) 
            {
                reported.append("<tr>");
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    reported.append("<td>"+(char)matriz[fila][columna]+"</td>");
                    dds.append((char)matriz[fila][columna]);
                }
                reported.append("</tr>");
            }
            reported.append("</table>");
     }
}


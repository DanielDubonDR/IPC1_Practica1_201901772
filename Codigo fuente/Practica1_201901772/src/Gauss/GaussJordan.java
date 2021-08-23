
package Gauss;

import Archivos.ManejadordeArchivos;
import java.util.Scanner;

/**
 *
 * @author Daniel Dubón
 */
public class GaussJordan {
    int j=2;
    int tamanio = 0, pivote = 0;  //creo variables que utilizare en la ejecucion del programa
    double matriz[][];
    static double[] resultado=new double[3];
    ManejadordeArchivos arc=new ManejadordeArchivos();//para poder creaar el reporte
    StringBuilder reporte = new StringBuilder();
    StringBuilder h=new StringBuilder();
    public void algoritmo(double matriz[][], int tamanio)//algorimto que maneja gauss-jordan
    {
        crearheader();//crea el headeer
        MostrarMatrizIN(matriz,tamanio);//muestra la matriz ingresada en el reporte
            for (int a = 0; a < tamanio; a++) 
            {
                pivote(matriz, pivote, tamanio);//hago pivotes en la matriz
                MostrarMatriz2(matriz,tamanio);
                hacerceros(matriz, pivote, tamanio);//hago ceros las columnas con el pivote encontado
                MostrarMatriz2(matriz,tamanio);//muesta la matriz resultante en el reporte
                pivote++;//incremento el pivote
            }  
            Double l=Double.valueOf(matriz[0][2]);
            if(l.isInfinite() || l.isNaN())//verifico si el sistema tiene solucion
            {
                System.out.println("El sistema ingresado no tiene solucion o puede tener infinitas soluciones");
                sinReporte();
                arc.crearArchivo(h.toString(), "ReporteGauss.html");
            }
            else
            {
                mostrar(matriz,tamanio);
                crearCSS();
                reporte.append("</table>\n" +"<button class=\"btn\">Respuestas: X="+resultado[0]+"  Y="+resultado[1]+"  Z="+resultado[2]+"</button>\n" +"</body>\n" +"</html>");
                arc.crearArchivo(reporte.toString(), "ReporteGauss.html");
            }
            
}

    public void mostrar(double matriz[][], int tamanio)//muestra la solucion de sistema
     {
         System.out.println("\nRESULTADOS:");
        for (int x = 0; x < tamanio; x++)
             {
                 System.out.println("\t- Variable X" + (x + 1) + " = " + error(matriz[x][tamanio]));
                 resultado[x]=matriz[x][tamanio];
            }
    }

    public void MostrarMatrizIN(double matriz[][], int tamanio)//muesta la matriz ingresada en el reporte
    {
        for (int fila = 0; fila <tamanio; fila++) 
            {
                reporte.append("<tr>");
                for (int columna = 0; columna < (tamanio + 1); columna++) 
                {
                    reporte.append("<td>"+matriz[fila][columna]+"</td>");
                }
                reporte.append("</tr>");
            }
    }
    
    public void MostrarMatriz2(double matriz[][], int tamanio)//muesta la solucion de matriz en el reporte
    {
        reporte.append("</table>\n" +"\n" +"<table id=\"tb"+j+"\">\n" +"<thead>\n" +"<tr>\n" +"<td>X</td>\n" +
"<td>Y</td>\n" +"<td>Z</td>\n" +"<td>T.I</td>\n" +"</tr>\n" +"</thead>");
        for (int fila = 0; fila <(tamanio); fila++) 
            {
                reporte.append("<tr>");
                for (int columna = 0; columna < (tamanio + 1); columna++) 
                {
                    reporte.append("<td>"+error(matriz[fila][columna])+"</td>");
                }
                reporte.append("</tr>");
            }
        j++;
    }
    public double error(double numero)//corrigo los errores de decimales y redondeo
    {
        double resultado;
        resultado=numero*Math.pow(10,12);
        resultado=Math.round(resultado);
        resultado=resultado/Math.pow(10,12);
        return resultado;
    }
    public void pivote(double matriz[][], int pivote, int tamanio) //hago pivotes en diagonal a las diferentes filas
    {
        double aux = 0;
        aux = matriz[pivote][pivote];
        for (int columna = 0; columna < (tamanio + 1); columna++) {

            matriz[pivote][columna] = matriz[pivote][columna] / aux;
        }
    }

    public void hacerceros(double matriz[][], int pivote, int tamanio) //hago ceros con los pivores en las columnas
    {
        for (int fila = 0; fila < tamanio; fila++) {
            if (fila != pivote) {
                
                double eliminador = matriz[fila][pivote];
                for (int j = 0; j < (tamanio + 1); j++) {
                    matriz[fila][j] = ((-1 * eliminador) * matriz[pivote][j]) + matriz[fila][j];
                }
            }
        }
    }
   
    public void crearCSS()//creo el css de gauss-jordan
    {
        String contenido="body{background-color: #bdc3c7;font-family: Arial; display: flex;\n" +
        "align-items: center;justify-content: center;}\n" +
        "table{background-color: #ecf0f1;text-align: center;\n" +
        "width: 30%;border-collapse: collapse;left: 50%;\n" +
        "transform: translate(-50%,-50%);position: absolute;}\n" +
        "#tb1{top: 200px;}#tb2{top: 480px;}\n" +
        "#tb3{top: 760px;}#tb4{top: 1040px;}\n" +
        "#tb5{top: 1320px;}#tb6{top: 1600px;}#tb7{top: 1880px;}\n" +
        "th, td{border: solid 1px black;padding: 8px;}\n" +
        "thead{background-color: #006266;border-bottom: solid 3px #000000;color white;}\n" +
        "tr:hover td{background-color: #1289A7;color: white;}\n" +
        ".center-box{position: absolute;top: 60px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;\n" +
            "text-align: center;line-height: 60px;}\n" +
        ".center-box p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;\n" +
        "line-height: 60px;text-align: center;}\n" +
        ".center-box2{position: absolute;top: 340px;\n" +
        "left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box2 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box2 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;\n" +
        "text-align: center;line-height: 60px;}\n" +
        ".center-box2 p{float: center;margin: 0px;padding: 0 6px;font-size: 16px;\n" +
        "font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".center-box3{position: absolute;top: 620px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box3 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box3 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;text-align: center;line-height: 60px;}\n" +
        ".center-box3 p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".center-box4{position: absolute;top: 900px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box4 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box4 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;text-align: center;line-height: 60px;}\n" +
        ".center-box4 p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".center-box5{position: absolute;top: 1180px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box5 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box5 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;text-align: center;line-height: 60px;}\n" +
        ".center-box5 p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".center-box6{position: absolute;top: 1460px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box6 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box6 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;text-align: center;line-height: 60px;}\n" +
        ".center-box6 p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".center-box7{position: absolute;top: 1740px;left: 50%;transform: translate(-50%,-50%);}\n" +
        ".center-box7 div\n" +
        "{width: 600px;height: 60px;overflow: hidden;margin: 8px 0;color: #313131;}\n" +
        ".center-box7 span{float: left;width: 40px;\n" +
        "height: 60px;font-size: 60px;text-align: center;line-height: 60px;}\n" +
        ".center-box7 p{float: center;margin: 0px;padding: 0 6px;\n" +
        "font-size: 16px;font-weight: 700;line-height: 60px;text-align: center;}\n" +
        ".m1{background: #3498db;}.m1 span{background: #2980b9;}\n" +
        ".m2{background: #2ecc71;}.m2 span{background: #27ae60;}\n" +
        ".m3{background: #f1c40f;}.m3 span{background: #f39c12;}\n" +
        ".m4{background: #e74c3c;}.m4 span{background: #c0392b;}\n" +
        ".m5{background: #e67e22;}.m5 span{background: #d35400;}\n" +
        ".m6{background: #9b59b6;}.m6 span{background: #8e44ad;}\n" +
        ".m7{background: #34495e;}.m7 span{background: #2c3e50;}\n" +
        ".btn{width: 600px;height: 60px;top: 1980px; \n" +
        "background: none;border: 4px solid;\n" +
        "color: #c0392b;font-weight: 700;\n" +
        "text-transform: uppercase;cursor: pointer;\n" +
        "font-size: 16px; position: relative;}\n" +
        ".btn::before,.btn::after{\n" +
        "content: \"\";position: absolute;width: 14px;\n" +
        "height: 4px;background: #bdc3c7;\n" +
        "transform: skewX(50deg);transition: .4s linear;}\n" +
        ".btn::before{top: -4px;left: 10%;}\n" +
        ".btn::after{bottom: -4px;right: 10%;}\n" +
        ".btn:hover::before{left: 80%;}\n" +
        ".btn:hover::after{ right: 80%;}";
        arc.crearArchivo(contenido, "tabla.css");
    }
    
    public void crearheader(){//creo el header de mi reporte
        reporte.append("<!DOCTYPE html><html><head><title>Reporte Gauss-Jordan</title><link rel=\"stylesheet\" href=\"tabla.css\">\n" +
        "</head><body>\n" +
        "<div class=\"center-box\"><div class=\"m1\"><span><p>1</p></span><p>Matriz Ingresada</p></div></div>\n" +
        "<div class=\"center-box2\"><div class=\"m2\"><span><p>2</p></span><p>Haciendo pivote en Fila 1 Columna 1</p></div></div>\n" +
        "<div class=\"center-box3\"><div class=\"m3\"><span><p>3</p></span><p>Haciendo ceros en Columna 1</p></div></div>\n" +
        "<div class=\"center-box4\"><div class=\"m4\"><span><p>4</p></span><p>Haciendo pivote en Fila 2 Columna 2</p></div></div>\n" +
        "<div class=\"center-box5\"><div class=\"m5\"><span><p>5</p></span><p>Haciendo ceros en Columna 2</p></div></div>\n" +
        "<div class=\"center-box6\"><div class=\"m6\"><span><p>6</p></span><p>Haciendo pivote en Fila 3 Columna 3</p></div></div>\n" +
        "<div class=\"center-box7\"><div class=\"m7\"><span><p>7</p></span><p>Sistema Resuelto</p></div></div>\n" +
        "<table id=\"tb1\"><thead><tr><td>X</td><td>Y</td><td>Z</td><td>T.I</td></tr></thead>");
        
    }
    
    public void sinReporte()//mensaje si no se reporta nada
    {
        
        h.append("<!DOCTYPE html><html><head><title>Reporte Gauss-Jordan</title><link rel=\"stylesheet\" href=\"tabla.css\">\n" +
        "</head><body>\n" +
        "<div class=\"center-box\"><div class=\"m1\"><span><p>1</p></span><p>Sin nada que reportar, matriz sin solucion</p></div></div>"+"</body>\n" +"</html>");
    }
}

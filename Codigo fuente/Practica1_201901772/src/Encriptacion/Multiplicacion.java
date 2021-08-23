//esta clase realiza la multplicacion para cifrar el texto
package Encriptacion;

import Principal.Menu;

/**
 *
 * @author Daniel Dubón
 */
public class Multiplicacion {
    Menu reportarrc= new Menu();
    int contadooor=4;
    StringBuilder cadenafinal;
    public void algoritmo(int[][] cuadrada, int[][] texto,int[][] resultado,int columna)//en los parametros envio la llave con que se a a cifrar y el tamaño de la matriz
    {
        reportarCuadrada(cuadrada);//creo reporte sobre el cifrado
        for(int fila=0; fila<cuadrada.length;fila++)//este ciclo me indica en que fila debo muliplicar
        {
            multiplicar(fila,columna,cuadrada, texto, resultado);
            
        }
        mostrar(cuadrada.length,columna,resultado);//muestro mi matriz reslutante
    }
    
    public void multiplicar(int fila,int columna, int[][] cuadrada, int[][]texto, int[][]resultado)//este metodo es el que multiplica filasxcolumnas
    {
        int aux=0;
        for(int c=0; c<columna;c++)
        {
            for(int f=0;f<cuadrada.length;f++)
            {
                aux+=cuadrada[fila][f]*texto[f][c];//se obtiene el resultado de la multplicacion y se envia a insertarla a la matriz resultande
            }
            llenarMatriz(aux,fila,c,resultado);
            aux=0;
        }
        if(contadooor<6)
        {
            reportarMultipliacion(resultado,fila,columna);
        }
    }
    public void reportarMultipliacion(int[][] resultado,int fila, int columna)//genera el reporte del cifrado
    {
        
        reportarrc.llenarrc("<table id=\"tb"+contadooor+"\">");
        for(int i=0;i<fila;i++)
        {
            reportarrc.llenarrc("<tr>");
            for(int j=0;j<columna;j++)
            {
                reportarrc.llenarrc("<td>"+resultado[i][j]+"</td>");
            }
            reportarrc.llenarrc("</tr>");
            contadooor++;
        }
        reportarrc.llenarrc("</table>");
    }
    public void llenarMatriz(int dato, int fila, int columna, int[][] resultado)//me llena la matriz resultadnte
    {
        resultado[fila][columna]=dato;
    }
    
    public void mostrar(int fila, int columna, int[][] resultado)//imprimi la matriz resultante
    {
        cadenafinal=new StringBuilder();
        System.out.println("\n> El texto cifrado es: \n");
        reportarrc.llenarrc("<table id=\"tb6\">");
        for(int i=0;i<fila;i++)
        {
            reportarrc.llenarrc("<tr>");
            for(int j=0;j<columna;j++)
            {
                System.out.print(resultado[i][j]+" ");
                reportarrc.llenarrc("<td>"+resultado[i][j]+"</td>");
                cadenafinal.append(resultado[i][j]+"    ");
            }
            reportarrc.llenarrc("</tr>");
        }
        reportarrc.llenarrc("</table>");
    }
    
    public void reportarCuadrada(int cuadrada[][])//reporta la llave
    {
        reportarrc.llenarrc("<table id=\"tb3\">");
        for(int i=0;i<cuadrada.length;i++)
        {
            reportarrc.llenarrc("<tr>");
            for(int j=0;j<cuadrada.length;j++)
            {
                reportarrc.llenarrc("<td>"+cuadrada[i][j]+"</td>");
            }
            reportarrc.llenarrc("</tr>");
        }
        reportarrc.llenarrc("</table>");
    }
    
    public String devolvercifrado()//devuelve la el texto cifrado
    {
                return cadenafinal.toString();
    }
}

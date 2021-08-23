//esta clase me encripta los mensajes
package Encriptacion;

import Principal.Menu;

/**
 *
 * @author Daniel Dubón
 */
public class Encriptar {
    int[][] matrizTxt; //creo variable a utlizar en los metodos
    int fila, columna;
    Menu reporte=new Menu();
    public void tamanioMatriz(String cadena)//obtengo las dimenciones de la matriz en las que cabe el texto
    {
        int cantidad=cadena.length(), multiplo=0, n;
        int[] biblioteca={3,4,5,7,11,13,17};//creo una pequeña libreria donde saco modulos
        for(int i=0; i<biblioteca.length; i++)
        {
            if(cantidad%biblioteca[i]==0)
            {
                multiplo=biblioteca[i];
                //System.out.println("Multiplo de: "+biblioteca[i]);
                break;
            }
        }
        columna=cantidad/multiplo;
        fila=multiplo;
        matrizTxt=new int[multiplo][columna];//obtengo mis filas y columnas del texto
        //System.out.println("Matriz de "+multiplo+"x"+(columna));
    }
    

    
    public void llenarAscii(String cadena)//lleno mi matriz con el texto convertido en caracteres ascii
    {
        llenarLetras(cadena);
        int cont=0;
        int aux;
        reporte.llenarrc("<table id=\"tb2\">");
        for(int i=0; i<fila; i++)
        {
            reporte.llenarrc("<tr>");
            for(int j=0; j<columna; j++)
            {
        
                aux=cadena.codePointAt(cont);//aca se realiza la conversion a ascii
                matrizTxt[i][j]=aux;
                reporte.llenarrc("<td>"+aux+"</td>");
                cont++;
            }
            reporte.llenarrc("</tr>");
        }
        reporte.llenarrc("</table>");
//        for(int i=0; i<fila; i++)
//        {
//            for(int j=0; j<columna; j++)
//            {
//                System.out.print(matrizTxt[i][j]+" ");
//            }
//        }
//        System.out.println("");
    }
    
    public int obtenerCuadrada()//todos estas funciones me devuelven cantidades
    {
        return fila*fila;
    }
    
    public int[][] obtenerTxt()
    {
        return matrizTxt;
    }
    
    public int obtenerfila()
    {
        return fila;
    }
    
    public int obtenercolumna()
    {
        return columna;
    }
    public void llenarLetras(String cadena)//aca lleno mi reporte con el texto ingresado
    {
        int cont=0;
        reporte.llenarrc("<table id=\"tb1\">");
        for(int i=0; i<fila; i++)
        {   
            reporte.llenarrc("<tr>");
            for(int j=0; j<columna; j++)
            {
                reporte.llenarrc("<td>"+cadena.charAt(cont)+"</td>");
                cont++;
            }
            reporte.llenarrc("</tr>");
        }
        reporte.llenarrc("</table>");
    }
}

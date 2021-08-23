//esta clase me arregla las cadenas y me extrae las diferentes matrices ingresadas en un archivo de texto
package Principal;

import Archivos.ManejadordeArchivos;
import java.io.IOException;

/**
 *
 * @author Daniel Dubón
 */
public class Funciones {
    private int contador,filasA;
    private String[] extraccion;
    Menu reporte=new Menu();
    public int dimension()//retorno valores de mis matrices
    {
        return contador;
    }
    public int filasA()
    {
        return filasA;
    }
    public void arregloCadena(String path) throws IOException//este metodo me extrae lo que contiene el archvio y me lo ingresa a una matriz
    {
        ManejadordeArchivos arc=new ManejadordeArchivos();
        arc.obtenerMatriz(path);
        String cadena=arc.devolverMatriz();
        extraccion= cadena.split(",",(cadena.length()-1));
        contador=extraccion.length-1;
        filasA=arc.obtenerFilas();
    }
    public double[][] obtenervec() throws IOException//obtengo la matriz para hacer gauss-jordan
    {
        double[][] matriz;
        int tamanio=3, i=0;
            matriz = new double[tamanio][tamanio + 1];
            for (int fila = 0; fila < tamanio; fila++) 
            {
                for (int columna = 0; columna < (tamanio + 1); columna++) 
                {
                    matriz[fila][columna] = Double.parseDouble(extraccion[i]);
                    i++;
                }
            }
            return matriz;
    }
    
    public int[][] obtenervecInt(int tamanio) throws IOException//obtengo la matriz para cifrar textos
    {
        int[][] matriz;
        int i=0;
            matriz = new int[tamanio][tamanio];
            for (int fila = 0; fila < tamanio; fila++) 
            {
                for (int columna = 0; columna < (tamanio); columna++) 
                {
                    matriz[fila][columna] = Integer.parseInt(extraccion[i]);
                    i++;
                }
            }
            return matriz;
    }
    
    public double[][] obtenervecDob(int filaT, int columnaT) throws IOException//obtengo la matriz cifrada
    {
        
        double[][] matriz;
        int i=0;
            matriz = new double[filaT][columnaT];
            for (int fila = 0; fila < filaT; fila++) 
            {
                for (int columna = 0; columna < (columnaT); columna++) 
                {
                    matriz[fila][columna] = Double.parseDouble(extraccion[i]);
                    i++;
                }
            }
            return matriz;
    }
    
    public double[][] obtenervecDobC(int filaC, int columnaC) throws IOException//obtengo la llave
    {
        double[][] matriz;
        int i=0;
            matriz = new double[filaC][columnaC];
            for (int fila = 0; fila < filaC; fila++) 
            {
                for (int columna = 0; columna < (filaC); columna++) 
                {
                    matriz[fila][columna] = Double.parseDouble(extraccion[i]);
                    i++;
                }
            }
            return matriz;
    }
}

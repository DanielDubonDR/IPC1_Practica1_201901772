
package Desencriptar;

/**
 *
 * @author Daniel Dubón
 */
public class Decifrar {
    double[][] aux;
    public void algoritmo(double[][] cuadrada, double[][] texto,double[][] resultado,int columna)//esta funcion es la que me descifra los mensajes, desde el menu le envio los parametros
    {
        Double l=Double.valueOf(cuadrada[0][0]);//aca verifico que mi matriz cuadrada si tenga inversa
        if(l.isInfinite() || l.isNaN())
        {
            System.out.println("ERROR, la llave ingresada no tiene inversa");
        }
        else
        {
            aux=new double[cuadrada.length][columna];//aca ejecuto todo el algorimo de la multiplicacion
            for(int fila=0; fila<cuadrada.length;fila++)//este ciclo me indica en que fila debo operar
            {
                multiplicar(fila,columna,cuadrada, texto, resultado);//mando a llamar a otra funcion enviandole las posiciones a operar
            
            }
            publica(resultado);//metodo auxiliar para obtener la cadena descifrada
            mostrar(cuadrada.length,columna,resultado);//muestro el resultado
            
        }
    }
    
    public void multiplicar(int fila,int columna, double[][] cuadrada, double[][]texto, double[][]resultado)//aca obtengo el dato de una celda
    {
        double aux=0;
        for(int c=0; c<columna;c++)
        {
            for(int f=0;f<cuadrada.length;f++)
            {
                aux+=cuadrada[fila][f]*texto[f][c];//hago las sumas y multiplicaciones correspondientes
            }
            llenarMatriz(aux,fila,c,resultado);//mando el dato encontrado y lo inserto en la matriz resultante
            aux=0;
        }
    }
    
    public void llenarMatriz(double dato, int fila, int columna, double[][] resultado)//inserto el dato encontrado
    {
        resultado[fila][columna]=error(dato);
    }
    public double error(double numero)//este metodo me quita los errores decimales que se generan
    {
        double resultado;
        resultado=numero*Math.pow(10,10);
        resultado=Math.round(resultado);
        resultado=resultado/Math.pow(10,10);
        return resultado;
    }
    public void mostrar(int fila, int columna, double[][] resultado)//este metodo me muestras lo que contiene mi matriz resultante
    {
        System.out.println(">El mensaje descifrado es: \n");//recorro toda mi matriz con un for anidado
        System.out.println("");
        for(int i=0;i<fila;i++)
        {
            for(int j=0;j<columna;j++)
            {
                //System.out.print(resultado[i][j]+" ");
                System.out.print((char)resultado[i][j]);
            }
        }
        System.out.println("");
    }
    public void publica(double[][] resultado)//este metodo me hace publico la matriz resultante
    {
        aux=resultado;
    }
    public double[][] devolverm()//esta funcio me duvuelve mi matriz
    {
        return aux;
    }
}

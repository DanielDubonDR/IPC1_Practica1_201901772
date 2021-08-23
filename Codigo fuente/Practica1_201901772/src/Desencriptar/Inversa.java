
package Desencriptar;

/**
 *
 * @author Daniel Dubón
 */
public class Inversa {
    int tamanio = 0, pivote = 0, aux=0;
    double matriz[][], inversa[][];
    public void algoritmo(double matriz[][], int tamanio, int aux)
    {
            //este algortimo es la reutilizacion del gauss-jordan
            colocarceros(matriz,aux,tamanio);//llamo a la funcion que me coloca ceros en la matriz
            identidad(matriz,aux,tamanio);//genero la matriz identidad
            for (int a = 0; a < tamanio; a++) //aplico el algoritmo de gauss-jordan
            {
                pivote(matriz, pivote, tamanio, aux);//mando mis pivotes con lo que hare ceros
                hacerceros(matriz, pivote, tamanio, aux);//hago ceros con los pivotes encontrados
                pivote++;
            }     
            //muestramatriz(matriz, aux, tamanio);
            obtenerInversa(matriz,tamanio, aux);//obtengo la inversa
            //MostrarInversa(tamanio);
}

    static void pivote(double matriz[][], int pivote, int tamanio, int aux) //este metodo me genera los diferentes pivotes en diagonal
    {
        double temp = 0;
        temp = matriz[pivote][pivote];
        for (int columna = 0; columna < aux; columna++) {

            matriz[pivote][columna] = matriz[pivote][columna] / temp;
        }
    }
    static void colocarceros(double matriz[][], int aux, int tamanio)//inserto ceros en mi matriz para crear la matriz identidad
    {
        for (int fila = 0; fila < tamanio; fila++) 
            {
                for (int columna = tamanio; columna < (aux); columna++) 
                {
                    
                        matriz[fila][columna]=0;
                }
            }
    }
    
    static void identidad(double matriz[][], int aux, int tamanio)//incerto 1 en diagonal para tener la matriz identidad
    {
        for (int fila = 0; fila < tamanio; fila++) 
            {
                        matriz[fila][fila+tamanio]=1;
            }
    }
    
    static void muestramatriz(double matriz[][], int aux, int tamanio) //recorre mi matriz he imprime el contenido
        {
            for (int fila = 0; fila < tamanio; fila++) 
            {
                for (int columna = 0; columna < aux; columna++) 
                {
                System.out.print(" " + matriz[fila][columna] + " ");
                }
                System.out.println("\n");
            }
            
        }
    
    static void hacerceros(double matriz[][], int pivote, int tamanio, int aux) //hago ceros con los pivotes antes encontrados
    {
        for (int fila = 0; fila < tamanio; fila++) {
            if (fila != pivote) {
                
                double c = matriz[fila][pivote];
                for (int z = 0; z < (aux); z++) {
                    matriz[fila][z] = ((-1 * c) * matriz[pivote][z]) + matriz[fila][z];
                }
            }
        }
    }
    
    public void obtenerInversa(double matriz[][],int tamanio, int aux)//extraigo la inversa de la matriz que tiene la identidad
    {
        inversa=new double[tamanio][tamanio];
        int cont=0;
        for(int i=0; i<tamanio; i++)
            {
                for(int j=tamanio; j<aux; j++)
                {
                    inversa[i][cont]=matriz[i][j];
                    if(cont==tamanio-1)
                    {
                        cont=0;
                    }
                    else cont++;
                }
        }
    }
    public double[][] regresarInversa()//devuelve la inversa
    {
        return inversa;
    }
    public void MostrarInversa(int tamanio)//muestra la inversa
    {
        
        for(int i=0; i<tamanio; i++)
            {
                for(int j=0; j<tamanio; j++)
                {
                    System.out.println(inversa[i][j]);
                    
                }
        }
    }
}

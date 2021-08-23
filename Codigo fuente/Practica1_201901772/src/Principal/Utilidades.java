
package Principal;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Dubón
 */
public class Utilidades {
   final Scanner entrada=new Scanner(System.in);
   public void pausa() //cree una funcion que me permite pausar el contenido y leer la ultima opcion que ingrese
    { 
        String seguir;
        System.out.println("PRESIONE ENTER PARA CONTINUAR....");
        try
        {
            seguir = entrada.nextLine(); //hago un try catch por si me da error, practicamente lo que hago es que pido que ingrese algo en el tecldo
        }   
        catch(Exception e)
        {}  
    }
   
   public void limpiar() throws IOException, InterruptedException //cree una funcion que me limpia la consola
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}

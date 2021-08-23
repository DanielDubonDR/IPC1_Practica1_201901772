
package Principal;

import Gauss.GaussJordan;
import java.io.IOException;

/**
 *
 * @author Daniel Dubón
 */
public class Inicio {
    public static void main(String[] args) throws IOException, InterruptedException
    {
       Menu menu=new Menu();//instancio mi objeto y mando a llamar el metodo que contiene que es el menu
       menu.MenuPrincipal();
    }
}

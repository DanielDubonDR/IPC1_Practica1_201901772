
package Principal;

import java.util.Scanner;

/**
 *
 * @author Daniel Dubón
 */
public class Teclado {
     private Scanner entrada; //declaro una variable de tipo scanner
   
    public Teclado() {
        entrada = new Scanner(System.in); //inicializo mi variable
    }
 
    public String getString(String titulo) { //hago una funcion al cual le envio un parametro de texto
        String s1 = ""; //decalro una variable auxiliar y la inicializo
        while (s1.equals("")) { //hasta que se ponga algo no va a dejar de repertirse el bucle
            System.out.println(titulo + ": "); //imprime el texto que le envie
            s1 = entrada.nextLine(); //recibe lo ingresado por el teclado
            if (!s1.matches("^[A-Za-z ]*$")) {//compara si esta dentro del rango de caracteres de texto
                s1 = "";
            }
        }
        return s1;
    }
    
    public String getString1(String titulo) {
        String s1 = "";
        while (s1.equals("")) {
            System.out.print(titulo);
            s1 = entrada.nextLine(); 
            if (!s1.matches("^[A-Za-z ]*$")) {
                s1 = "";
                System.out.println("CARACTERES NO VALIDOS");
            }
        }
        return s1;
    }
   
    public int getInt(String titulo) {
        String s1 = "";
        while (s1.equals("")) {
            System.out.print(titulo + ": ");
            s1 = entrada.nextLine(); 
            if (!s1.matches("[0-9]*$")) { 
                s1 = "";
                System.out.println("\nOPCION NO VALIDA");
            }
        }
        return Integer.parseInt(s1);
    }
    
    public String getCadena(String titulo) {//solo toma los valores y los devuelve
        String s1;
        System.out.print(titulo);
        s1 = entrada.nextLine();     
        return s1;
    }
    
    public String getCadena1(String titulo) {//hasta que no ingrese las restricciones se repitira 
        String s1="";
        while (s1.equals("")) 
        {
            System.out.print(titulo);
            s1 = entrada.nextLine();     
            if (s1.equals("")||s1.length()<3) { 
                s1 = "";
                System.out.println("\nIngrese una cadena valida");
            }
        }
        return s1;
    }
}

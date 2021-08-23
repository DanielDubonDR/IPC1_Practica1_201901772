//Esta clase se encarga de manejar archivos, crear, modificar, eliminar, verificar
package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Daniel Dubón
 */
public class ManejadordeArchivos {
    FileReader entrada;
    String j;
    int filas=0;
    public boolean verificarArchivo(String path)//esta funcion me verifica si existe el archivo deseado
    {
        File archivo=new File(path);
        if(archivo.exists())
        {
            return true;
        }
        else return false;
    }
    
    public void obtenerMatriz(String path) throws IOException//este metodo me extrae lo que contiene el archivo de texto
    {
        try 
         {
             entrada=new FileReader(path);
             String cadena;
             StringBuilder a=new StringBuilder();
             BufferedReader lector =new BufferedReader(entrada);//creo un buffer que me lee por lineas
             while((cadena=lector.readLine())!=null)
             {
                 a.append(cadena.trim()+",");
                 filas++;
             }
             j=a.toString();
             //System.out.println(j);
         } catch (FileNotFoundException ex) {
             System.out.print("No se encuentra el archivo"); //agrego excepciones por si hay algun error
         }
            catch (IOException e)
            {
                System.out.println("Error");
            }
         finally
         {
            entrada.close();//simpre cierro mi buffer para evitar problemas
         }
    }
    
    public String devolverMatriz()//esta funcion me devuelve la matriz obtenida
    {
        return j;
    }
    
    public void crearArchivo(String contenido,String titulo){//este metodo me crea archivos planos
        
        File archivo = new File(titulo);
        
        try {
            FileWriter escritor = new FileWriter(archivo);//declaro mi variable que va a leer los datos
            escritor.write(contenido);
            escritor.close();
        } 
        catch (IOException ex) {
            
            ex.printStackTrace();
            
        }
        
    }
    
    public int obtenerFilas()//esta funcion me develve cuantas lineas leyo en el archivo
    {
        return filas;
    }
    
    public void eliminar(String path)//este metodo me elimina archivos
    {
        File archivo =new File(path);
        boolean eliminado=archivo.delete();
        if(!eliminado)
        {
            System.out.println("Error en volver a generar el reporte, vuelva a iniciar el programa");
        }
    }
}

package Tema1.EjerciciosTema;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class vocales {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Número de argumentos incorrecto.\n\nSintáxis: \n\t java vocales [ vocal ] [ ruta del archivo ]");
            System.exit(0);
        }

        if (args[0].length() != 1){
            System.out.println("Error: Debe ser exactamente un carácter");
            System.exit(0);
        }

        char vocal = Character.toLowerCase(args[0].charAt(0));
        if (vocal != 'a' && vocal != 'e' && vocal != 'i' && vocal != 'o' && vocal != 'u') {
            System.out.println("Error: Debe ser una vocal");
            System.exit(0);
        }

        try {
            FileReader archivoLectura = new FileReader(args[1]);

            int contador = 0;
            int bytesTotales = 0;
            int caracter;
            do{
                caracter = archivoLectura.read();

                if(Character.toLowerCase(caracter) == vocal) contador++;
                bytesTotales++;

            }while(caracter != -1);

            System.out.println("Se han comprobando " + (bytesTotales-1) + " totales");
            System.out.println("Se han contado " + contador + " \"" + vocal + "\" en total");
            
            archivoLectura.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }

    }

}

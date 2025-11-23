package Tema1.EjerciciosTema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class headTails {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println(
                    "Número de argumentos incorrecto.\n\nSintáxis: \n\t java headTails [ head | tails ] [número de líneas] ruta_del_archivo");
            System.out.println("Ejemplos:");
            System.out.println("\t java headTails head archivo.txt          (muestra 10 líneas por defecto)");
            System.out.println("\t java headTails head 5 archivo.txt        (muestra 5 líneas)");
            System.exit(0);
        }

        try {
            String comando = args[0];
            int numLineas = 10; // valor por defecto
            String rutaArchivo;
            
            // Determinar si se proporcionó un número de líneas
            if (args.length == 3) {
                // Formato: comando numLineas rutaArchivo
                try {
                    numLineas = Integer.parseInt(args[1]);
                    rutaArchivo = args[2];
                } catch (NumberFormatException e) {
                    System.out.println("Error: El segundo argumento debe ser un número válido");
                    return;
                }
            } else {
                // Formato: comando rutaArchivo (usa valor por defecto)
                rutaArchivo = args[1];
            }

            if (comando.equals("head")) {
                headTails.mostrarHead(rutaArchivo, numLineas);
            } else if (comando.equals("tails")) {
                headTails.mostrarTails(rutaArchivo, numLineas);
            } else {
                System.out.println("No entiendo la orden " + comando + "\n");
                System.out.println("Comandos válidos: head, tails");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(headTails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void mostrarHead(String rutaArchivo, int numLineas){
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            for (int i = 0; i < numLineas; i++) {
                linea = archivo.readLine();

                if(linea != null){
                    System.out.println((i+1) + ". " + linea);
                } else{
                    System.out.println("\nNo hay más líneas para mostrar");
                    break;
                } 
            }
 
            archivo.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }

    private static void mostrarTails(String rutaArchivo, int numLineas){
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
            ArrayList<String> lineas = new ArrayList<>();
            String linea;

            do {
                linea = archivo.readLine();
                if (linea != null) lineas.add(linea);
                if(lineas.size() > numLineas) lineas.removeFirst();
            } while (linea != null);

            for (int i = 0; i < lineas.size(); i++) {
                System.out.println((i+1) + ". " + lineas.get(i));
            }

            archivo.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
 
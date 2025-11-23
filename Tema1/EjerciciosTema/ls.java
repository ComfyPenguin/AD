package Tema1.EjerciciosTema;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ls {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Número de argumentos incorrecto.\n\nSintáxis: \n\t java ls [ lista | columnas | tabla ] ruta de la carpeta");
            System.exit(0);
        }

        try {
            if (args[0].equals("lista")) {
                ls.mostrarLista(args[1]);
            } else if (args[0].equals("columnas")) {
                ls.mostrarColumnas(args[1]);
            } else if (args[0].equals("tabla")) {
                ls.mostrarTabla(args[1]);
            } else {
                System.out.println("No entiendo la orden " + args[0] + "\n");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(ls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Muestra los archivos y carpetas de un directorio en formato de lista simple
    private static void mostrarLista(String ruta) {
        File f = new File(ruta);

        if (f.exists()) {
            if (f.isDirectory()) {
                String[] archivos = f.list();
                System.out.println("El directorio \"" + ruta + "\" contiene: ");
                for (String archivo : archivos) {
                    System.out.println("\t- " + archivo);
                }
            } else {
                System.out.println("La ruta especificada no es un directorio.");
            }
        } else {
            System.out.println("La ruta especificada no existe.");
        }
    }

    // Muestra los archivos y carpetas en varias columnas
    private static void mostrarColumnas(String ruta) {
        File f = new File(ruta);

        if (f.exists()) {
            if (f.isDirectory()) {
                String[] archivos = f.list();
                ls.columnas(archivos);
            } else {
                System.out.println("La ruta especificada no es un directorio.");
            }
        } else {
            System.out.println("La ruta especificada no existe.");
        }
    }

    // Da formato en columnas a los nombres de archivo recibidos (Código del profesor)
    private static void columnas(String[] filenames) {
        int MAX_FILES_BY_COLUMN = 4;

        int columnas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;

        String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];

        // Llenar la matriz 'salida' con los nombres de archivo
        for (int i = 0; i < filenames.length; i++) {
            salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames[i];
        }
        // Encontrar el nombre de archivo más largo para establecer el ancho de columna
        int maxLength = 0;
        for (String filename : filenames) {
            if (filename.length() > maxLength) {
                maxLength = filename.length();
            }
        }
        // Ajustar el formato para que cada columna tenga el mismo ancho
        String format = "%-" + (maxLength + 2) + "s";
        // Bucle para mostrar salida con formato simétrico
        for (int i = 0; i < MAX_FILES_BY_COLUMN; i++) {
            for (int j = 0; j < columnas; j++) {
                if (salida[i][j] != null) {
                    System.out.printf(format, salida[i][j]); // printf para aplicar el formato
                }
            }
            System.out.println();
        }
    }

    // Muestra los archivos y carpetas en formato de tabla
    private static void mostrarTabla(String ruta) {
        File f = new File(ruta);

        if (f.exists()) {
            if (f.isDirectory()) {
                File[] archivos = f.listFiles();

                int tamañoNombre = 0;
                for (File archivo : archivos) {
                    if (archivo.getName().length() > tamañoNombre) {
                        tamañoNombre = archivo.getName().length();
                    }
                }

                String formato = "%-" + (tamañoNombre + 2) + "s | %10s | %s%n";

                System.out.printf(formato, "Nombre", "Tamaño", "Información");

                for (File archivo : archivos) {
                    ArrayList<String> info = new ArrayList<>();

                    if (archivo.isDirectory()) info.add("D");
                    else info.add("F");

                    if (archivo.canRead()) info.add("R");
                    else info.add("-");

                    if (archivo.canWrite()) info.add("W");
                    else info.add("-");
                        
                    if (archivo.isHidden()) info.add("H");
                    else info.add("-");

                    System.out.printf(formato, archivo.getName(), archivo.length(), String.join("", info));
                }
            } else {
                System.out.println("La ruta especificada no es un directorio.");
            }
        } else {
            System.out.println("La ruta especificada no existe.");
        }
    }

}

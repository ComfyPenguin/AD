import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class FileCopy {
    /*
    Classe per provar FileInputStream i FileOutputStream.

    Copia fitxers byte a byte.

    Sintaxi:
        FileCopy fitxerOrigen fitxerDesti.
    */
    public static void main(String[] args) throws Exception {
        String bytes = args[2];

        // Buffer per llegir blocs de 32 bytes
        byte[] buffer = new byte[Integer.parseInt(bytes)];
        int bytesRead;        
        long bytesCopied = 0; // Comptador de bytes copiats

        FileInputStream fis = null; // Stream per llegir el fitxer origen
        FileOutputStream fos = null; // Stream per escriure el fitxer destí
        File f; // Objecte per obtenir informació del fitxer origen

        // Comprovem que hi ha dos arguments (origen i destí)
        if(args.length != 3){
            System.out.println("Nombre d'arguments erroni. Sintaxi:\n FileCopy fitxerOrigen fitxerDesti");
            return;
        }

        try{
            // Mostrem la mida total del fitxer origen
            f = new File(args[0]);
            System.out.println("Total: " + f.length() + " bytes");

            // Obrim els streams
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            // Llegim i escrivim en blocs de 32 bytes fins que no queden dades
            while ((bytesRead = fis.read(buffer)) != -1) {
                // Escrivim només els bytes llegits (pot ser menys de 32 al final)
                fos.write(buffer, 0, bytesRead);
                // Actualitzem el comptador de bytes copiats
                bytesCopied += bytesRead;
                // Mostrem el progrés per pantalla
                System.out.print("\rCopiats " + bytesCopied + " bytes...");
            }
            System.out.println("\nDone it!");

        } catch (IOException exc) {
            System.out.println("Error d'entrada i eixida: " + exc);
        } finally {
            // Tanquem els streams, encara que hi hagi hagut error
            try {
                if (fis != null) fis.close();
            } catch (IOException exc) {
                System.out.println("Error en tancar el fitxer d'origen.");
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException exc) {
                System.out.println("Error en tancar el fitxer destí.");
            }
        }
    }
}
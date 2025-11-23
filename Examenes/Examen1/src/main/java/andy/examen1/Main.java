package andy.examen1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Buffer per llegir blocs de 32 bytes
        int bytes = Integer.parseInt(args[2]);

        byte[] buffer = new byte[bytes];
        int bytesRead;
        long bytesCopied = 0;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f;

        if(args.length != 3){
            System.out.println("Nombre d'arguments erroni. Sintaxi:\n FileCopy fitxerOrigen fitxerDesti bytes");
            return;
        }

        try{
            f = new File(args[0]);
            System.out.println("Total: " + f.length() + " bytes");

            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                bytesCopied += bytesRead;
                System.out.print("\rCopiats " + bytesCopied + " bytes...");
            }
            System.out.println("\nDone it!");

            fis.close();

        } catch (IOException exc) {
            System.out.println("Error d'entrada i eixida: " + exc);
        } finally {
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
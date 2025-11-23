import java.io.*;

public class Moduls{

    // several arrays with modules data
    String[] moduls={"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimèdia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Itinerari per a l'Ocupabilitat II"};
    int[]  hores={4, 2, 5, 4, 4, 3};
    double[] notes={8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

    public void readFiLe(String name) throws IOException {
        // Per lleginr el fitxer binari, creem un DataInputStream
        // a partir del FileInputStream creat a partir del nom
        DataInputStream f = new DataInputStream(new FileInputStream(name));

        // Mentre el DataInputStream tinga dades disponibles
        while (f.available()>0){
            // Llegirem del fitxer cada dada, amb l'ordre corresponent
            // en funció del tipus
            // (per tant, hem de saber l'ordre en què guardem!)
            System.out.println("Mòdul: " + f.readUTF());
            System.out.println("Hores: " + f.readInt());
            System.out.println("Notes: " + f.readDouble());
            System.out.println();
        }
        f.close();
    }

    public void writeFile(String name) throws IOException {
        // Per escriure el fitxer, fem ús de DataOutputStream
        DataOutputStream f = new DataOutputStream(new FileOutputStream(name));

        // Recorrerem qualsevol dels vectors (tots haurien de tindre)
        // la mateixa longitud
        for (int i=0;i<this.moduls.length;i++){
            // I per a cada posició, escriurem en funció del tipus de dada
            f.writeUTF(this.moduls[i]);
            f.writeInt(this.hores[i]);
            f.writeDouble(this.notes[i]);

        }
        f.close();
    }

    // [x] Ejercici
    public void writeToText(String origen, String desti) throws IOException{
        writeFile(origen);

        DataInputStream f = new DataInputStream(new FileInputStream(origen));
        BufferedWriter bw = new BufferedWriter(new FileWriter(desti));

        while (f.available()>0){
            String modul = f.readUTF();
            int hores = f.readInt();
            double nota = f.readDouble();

            bw.write("Mòdul: " + modul);
            bw.newLine();
            bw.write("Hores: " + hores);
            bw.newLine();
            bw.write("Notes: " + nota);
            bw.newLine();
            bw.newLine();
        }
        f.close();
        bw.close();
    }

    public static void main(String[] args) throws IOException {

        // Comprovem els arguments
        if (args.length < 2 || args.length > 3){
            System.out.println("Nombre d'arguments incorrecte.\n\nSintaxi: \n\t java Moduls [read | write] fitxer.dat\n\t java Moduls transcribe fitxer.dat fitxer.txt");
            System.exit(0);
        }

        // Defining the class
        Moduls moduls=new Moduls();

        // Depending the args, we will proceed
        if (args[0].equals("read"))
            moduls.readFiLe(args[1]);
        else if (args[0].equals("write"))
            moduls.writeFile(args[1]);
        else if (args[0].equals("transcribe")) {
            if (args.length != 3) {
                System.out.println("Sintaxi: java Moduls transcribe fitxer.dat fitxer.txt");
                System.exit(0);
            }
            moduls.writeToText(args[1], args[2]);
            System.out.println("Transcripció completada a " + args[2]);
        }
        else
            System.out.println("No entenc l'ordre "+args[0]+"\n");
    }
}
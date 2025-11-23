import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PracticaXML {

    public static Document OpenXML(String name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Create an instance of DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // Using the DocumentBuilderFactory instance we create a DocumentBuilder
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //And we use DocumentBuilder's "parse" method to get the document
        Document doc = (Document) dBuilder.parse(new File(name));

        return doc;
    }

    public void readingXML(String name) throws IOException, SAXException, ParserConfigurationException {

        Document doc = OpenXML(name);

        Element root = doc.getDocumentElement();

        System.out.println("Llegint XML...");

        // We will get a list of nodes (Step 1)
        NodeList alumnes = root.getElementsByTagName("alumne");

        //System.out.println(modules.getLength());
        // For each node (Step 2)
        for (int i = 0; i < alumnes.getLength(); i++) {
            Element alumne = (Element) alumnes.item(i);

            // Display the node name (Step 3)
            System.out.println("----- " + alumne.getNodeName().toUpperCase() + " " + (i + 1) + " -----");

            // And we show the value of the different tags
            System.out.println("Nom: " + alumne.getElementsByTagName("nom").item(0).getFirstChild().getNodeValue());
            System.out.println("Cognom: " + alumne.getElementsByTagName("cognom").item(0).getFirstChild().getNodeValue());
            System.out.println("Edat: " + alumne.getElementsByTagName("edat").item(0).getFirstChild().getNodeValue());
            System.out.println("Direcció: " + alumne.getElementsByTagName("direccio").item(0).getFirstChild().getNodeValue());
            System.out.println("Projecte: " + alumne.getElementsByTagName("nom_projecte").item(0).getFirstChild().getNodeValue());

            System.out.println();
            System.out.println(alumne.getElementsByTagName("moduls").item(0).getNodeName().toUpperCase() + ":");
            NodeList moduls = alumne.getElementsByTagName("modul");

            for (int j = 0; j < moduls.getLength(); j++) {
                Element inner_el = (Element) moduls.item(j);
                System.out.println("Nom: " + inner_el.getElementsByTagName("nom").item(0).getFirstChild().getNodeValue());
                System.out.println("Qualificació: " + inner_el.getElementsByTagName("qualificacio").item(0).getFirstChild().getNodeValue());
                System.out.println("Professor: " + inner_el.getElementsByTagName("profe").item(0).getFirstChild().getNodeValue());
                System.out.println();
            }

            System.out.println();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, TransformerConfigurationException {

        // test the args
        if (args.length != 2) {
            System.out.println("Nombre d'arguments incorrecte.\n\nSintaxi: \n\t java PracticaXML readxml nom_del_fitxer");
            System.exit(0);
        }

        PracticaXML moduls = new PracticaXML();

        try {
            // depending the args
            if (args[0].equals("readxml"))
                moduls.readingXML(args[1]);

        } catch (IOException | SAXException | ParserConfigurationException ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(PracticaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}




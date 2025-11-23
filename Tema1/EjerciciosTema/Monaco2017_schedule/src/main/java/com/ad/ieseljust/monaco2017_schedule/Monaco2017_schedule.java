/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ad.ieseljust.monaco2017_schedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jasb
 */
public class Monaco2017_schedule {

    public Document OpenXML(String name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Create an instance of DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // Using the DocumentBuilderFactory instance we create a DocumentBuilder
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //And we use DocumentBuilder's "parse" method to get the document
        Document doc = dBuilder.parse(new File(name));

        return doc;
    }

    public void carregaXML(String filename) {

        try {
            Document root = OpenXML(filename);
            NodeList resultatsPilots = root.getElementsByTagName("Result");

            //Recorrer els resultats dels pilots i crear instancies de ResultadoCarrera. Posteriorment mostrar el contingut amb el mètode toString de ResultadoCarrera().
            for (int i = 0; i < resultatsPilots.getLength(); i++) {
                Element el = (Element) resultatsPilots.item(i);
                ResultadoCarrera rs = new ResultadoCarrera(el);
                System.out.println(rs.toString());
            }

        } catch (IOException | SAXException | ParserConfigurationException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

    }

    public ArrayList<ResultadoCarrera> carregaResultadosXML(String filename) {
        ArrayList<ResultadoCarrera> resultados = new ArrayList<>();

        try {
            Document root = OpenXML(filename);
            NodeList resultatsPilots = root.getElementsByTagName("Result");

            // Recorrer los resultados de los pilotos y crear instancias de ResultadoCarrera
            for (int i = 0; i < resultatsPilots.getLength(); i++) {
                Element el = (Element) resultatsPilots.item(i);
                ResultadoCarrera rs = new ResultadoCarrera(el);
                resultados.add(rs);
            }

        } catch (IOException | SAXException | ParserConfigurationException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        return resultados;
    }

    public static void main(String[] args) {
        //TO-DO
        // Crear l'app a partir de la classe i crida a carregarXML amb el nom de l'arxiu .xml que retornarà
        // (funció ArrayList<ResultadoCarrera> carregaResultadosXML(String nomXML))
        //      - Carregar en memoria el .xml  (openXML)
        //      - Obtindre el NodeList del "Results", recorrer-los i crear un objecte ResultadoCarrera passant-li l'item (Element) del NodeList. 
        //      - afegir-lo a una ArrayList ArrayList<ResultadoCarrera> elsResultats = new ArrayList() que retornarem;


        Monaco2017_schedule app = new Monaco2017_schedule();

        app.carregaXML("monaco_2017.xml");

        //System.out.println(app.carregaResultadosXML("monaco_2017.xml"));
    }
}

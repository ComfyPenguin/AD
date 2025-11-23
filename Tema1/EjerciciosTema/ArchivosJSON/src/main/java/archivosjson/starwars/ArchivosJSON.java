package archivosjson.starwars;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchivosJSON {
    public static void main(String[] args) {
        try {
            // Leer el archivo JSON
            String contenido = new String(Files.readAllBytes(Paths.get("StarWars.json")));
            JSONObject jsonObject = new JSONObject(contenido);
            JSONArray personatges = jsonObject.getJSONArray("personatges");

            // Lista para almacenar los personajes
            List<Character> characters = new ArrayList<>();

            // Parsear los personajes del JSON
            for (int i = 0; i < personatges.length(); i++) {
                JSONObject persona = personatges.getJSONObject(i);

                String name = persona.getString("name");

                // Parsear mass (puede ser "unknown")
                int mass = 0;
                String massStr = persona.getString("mass");
                if (!massStr.equals("unknown")) {
                    // Eliminar comas si existen (ej: "1,358")
                    massStr = massStr.replace(",", "");
                    try {
                        mass = Integer.parseInt(massStr);
                    } catch (NumberFormatException e) {
                        mass = 0;
                    }
                }

                String url = persona.getString("url");
                int films = persona.getJSONArray("films").length();
                int vehicles = persona.getJSONArray("vehicles").length();

                characters.add(new Character(name, mass, url, films, vehicles));
            }

            // 1. Personajes que no conducen ningún vehículo
            System.out.println("========================================");
            System.out.println("PERSONAJES SIN VEHÍCULOS:");
            System.out.println("========================================");
            characters.stream()
                    .filter(c -> c.getVehicles() == 0)
                    .forEach(System.out::println);

            // 2. Lista de personajes ordenados por número de películas
            System.out.println("\n========================================");
            System.out.println("PERSONAJES ORDENADOS POR PELÍCULAS:");
            System.out.println("========================================");
            characters.stream()
                    .sorted(Comparator.comparingInt(Character::getFilms).reversed())
                    .forEach(System.out::println);

            // 3. Guardar objetos con ObjectOutputStream (serialización binaria)
            guardarObjetosSerializados(characters, "characters.obj");

            // 4. Crear archivo XML leyendo desde el archivo serializado
            crearArchivoXML("characters.obj");
            System.out.println("\n========================================");
            System.out.println("Archivo XML creado: characters.xml");
            System.out.println("========================================");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Guarda los objetos Character en formato binario usando ObjectOutputStream
     * Escribe cada objeto individualmente
     * @param characters Lista de personajes a serializar
     * @param filename Nombre del archivo donde guardar los objetos
     */
    private static void guardarObjetosSerializados(List<Character> characters, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {

            // Escribir cada personaje individualmente
            for (Character character : characters) {
                oos.writeObject(character);
            }

            System.out.println("\n========================================");
            System.out.println("Se han serializado " + characters.size() + " personajes.");
            System.out.println("Archivo binario creado: " + filename);
            System.out.println("========================================");

        } catch (IOException e) {
            System.err.println("Error al serializar los objetos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Crea un archivo XML leyendo objetos desde un archivo binario con ObjectInputStream
     * y usando DocumentBuilder para construir el documento XML
     * @param file Ruta del archivo .obj o .dat a leer
     */
    private static void crearArchivoXML(String file) {
        try {
            // Leer objetos con ObjectInputStream
            ObjectInputStream f;
            if (file != null) {
                f = new ObjectInputStream(new FileInputStream(file));
            } else {
                f = new ObjectInputStream(new FileInputStream("characters.obj"));
            }

            // Crear documento XML vacío con DocumentBuilder
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // Insertar el elemento raíz: "characters"
            Element root = doc.createElement("characters");
            doc.appendChild(root);

            int contador = 0;

            // Leer objetos uno por uno hasta llegar al final del archivo
            while (f != null) {
                try {
                    Character character = (Character) f.readObject();

                    // Crear elemento character con atributos
                    Element characterElement = doc.createElement("character");
                    characterElement.setAttribute("films", String.valueOf(character.getFilms()));
                    characterElement.setAttribute("vehicles", String.valueOf(character.getVehicles()));

                    // Crear elemento name
                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(character.getName()));
                    characterElement.appendChild(name);

                    // Crear elemento mass
                    Element mass = doc.createElement("mass");
                    mass.appendChild(doc.createTextNode(String.valueOf(character.getMass())));
                    characterElement.appendChild(mass);

                    // Crear elemento url
                    Element url = doc.createElement("url");
                    url.appendChild(doc.createTextNode(character.getUrl()));
                    characterElement.appendChild(url);

                    // Añadir character al root
                    root.appendChild(characterElement);

                    contador++;

                } catch (EOFException e) {
                    // Fin del archivo
                    f.close();
                    break;
                }
            }

            // Transformar el DOM (doc) a archivo de texto
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream("characters.xml"));

            trans.transform(source, result);

            System.out.println("Se han procesado " + contador + " personajes desde el archivo binario.");

        } catch (Exception e) {
            System.err.println("Error al crear el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


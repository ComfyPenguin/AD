package com.ieseljust.practicaunitat1;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Piloto implements Serializable{
    int id;
    String nombre;
    String equipo;
    String pais;
    int edad;
    double puntos;
    int podios;
    int victorias;
    boolean activo;
    double altura_m;
    double vuelta_rapida_seg;

    public Piloto(){}

    public Piloto(int id, String nombre, String equipo, String pais, int edad, double puntos, int podios, int victorias, boolean activo, double altura_m, double vuelta_rapida_seg) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = equipo;
        this.pais = pais;
        this.edad = edad;
        this.puntos = puntos;
        this.podios = podios;
        this.victorias = victorias;
        this.activo = activo;
        this.altura_m = altura_m;
        this.vuelta_rapida_seg = vuelta_rapida_seg;
    }


    @Override
    public String toString() {
        return "Piloto [id=" + id + ", nombre=" + nombre + ", equipo=" + equipo + ", pais=" + pais + ", edad=" + edad
                + ", puntos=" + puntos + ", podios=" + podios + ", victorias=" + victorias + ", activo=" + activo
                + ", altura_m=" + altura_m + ", vuelta_rapida_seg=" + vuelta_rapida_seg + "]";
    }


    public void toXML(ObjectInputStream archivo) throws ClassNotFoundException, IOException{
        System.out.println("<pilotos>");
        // Imprimir cada uno de los pilotos
        while (true) {
            try {
                Piloto piloto = (Piloto) archivo.readObject();
        
                // Imprimir en formato XML en la terminal
                System.out.println("    <piloto>");
                System.out.println("        <id>" + piloto.getId() + "</id>");
                System.out.println("        <nombre>" + piloto.getNombre() + "</nombre>");
                System.out.println("        <equipo>" + piloto.getEquipo() + "</equipo>");
                System.out.println("        <pais>" + piloto.getPais() + "</pais>");
                System.out.println("        <edad>" + piloto.getEdad() + "</edad>");
                System.out.println("        <puntos>" + piloto.getPuntos() + "</puntos>");
                System.out.println("        <podios>" + piloto.getPodios() + "</podios>");
                System.out.println("        <victorias>" + piloto.getVictorias() + "</victorias>");
                System.out.println("        <activo>" + piloto.isActivo() + "</activo>");
                System.out.println("        <altura_m>" + piloto.getAltura_m() + "</altura_m>");
                System.out.println("        <vuelta_rapida_seg>" + piloto.getVuelta_rapida_seg() + "</vuelta_rapida_seg>");
                System.out.println("    </piloto>");
            } catch (EOFException e) {
                archivo.close();
                break;
            }
        }
        System.out.println("<pilotos>");
    }


    public void toJSON(ObjectInputStream archivo) throws ClassNotFoundException ,IOException{
        System.out.println("{\"pilotos\": [");
        boolean primero = true;
        // Imprimir cada piloto
        while (true) {
            try {
                Piloto piloto = (Piloto) archivo.readObject();

                // Comprobar que no sea la primera línea
                if (!primero) System.out.println(",");
                else primero = false;

                // Imprimir en formato JSON en la terminal
                System.out.print("  {\n");
                System.out.print("      \"id\":" + piloto.getId() + ",\n");
                System.out.print("      \"nombre\":\"" + piloto.getNombre() + "\",\n");
                System.out.print("      \"equipo\":\"" + piloto.getEquipo() + "\",\n");
                System.out.print("      \"pais\":\"" + piloto.getPais() + "\",\n");
                System.out.print("      \"edad\":" + piloto.getEdad() + ",\n");
                System.out.print("      \"puntos\":" + piloto.getPuntos() + ",\n");
                System.out.print("      \"podios\":" + piloto.getPodios() + ",\n");
                System.out.print("      \"victorias\":" + piloto.getVictorias() + ",\n");
                System.out.print("      \"activo\":" + piloto.isActivo() + ",\n");
                System.out.print("      \"altura_m\":" + piloto.getAltura_m() + ",\n");
                System.out.print("      \"vuelta_rapida_seg\":" + piloto.getVuelta_rapida_seg() + "\n");
                System.out.print("  }");
            } catch (EOFException e) {
                archivo.close();
                break;
            }
        }
        System.out.println("\n]}");
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEquipo() {
        return equipo;
    }


    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }


    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }


    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public double getPuntos() {
        return puntos;
    }


    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }


    public int getPodios() {
        return podios;
    }


    public void setPodios(int podios) {
        this.podios = podios;
    }


    public int getVictorias() {
        return victorias;
    }


    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }


    public boolean isActivo() {
        return activo;
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public double getAltura_m() {
        return altura_m;
    }


    public void setAltura_m(double altura_m) {
        this.altura_m = altura_m;
    }


    public double getVuelta_rapida_seg() {
        return vuelta_rapida_seg;
    }


    public void setVuelta_rapida_seg(double vuelta_rapida_seg) {
        this.vuelta_rapida_seg = vuelta_rapida_seg;
    }

    
}

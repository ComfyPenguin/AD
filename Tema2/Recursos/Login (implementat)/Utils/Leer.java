/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author joange
 */
public class Leer {
    private final static BufferedReader entradaConsola = 
        new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

/**
* Llig un text del teclat. 
* @param mensaje Text que es passa a l'usuaro
* @return el text introduit. Blanc en cas d'error
*/
public static String leerTexto(String mensaje) {
    String respuesta=null;
    do{
        try {
            System.out.print(mensaje);
            respuesta= entradaConsola.readLine();
        } // ()
        catch (IOException ex) {
            return "";
        }
    } while(respuesta ==null);
    return respuesta;
    
} // ()


/**
* Introducció de numeros enters
* @param mensaje Missatge que es dona a l'usuari
* @return un enter amb el valor
*/
public static int leerEntero(String mensaje) {
    int n=0;
    boolean aconseguit=false;
    while(!aconseguit){
        try{
            n= Integer.parseInt(leerTexto(mensaje));
            aconseguit=true;
        }
        catch(NumberFormatException ex){
            System.out.println("Deus posar un numero correcte");
        }
    }
    return n;
}

/**
* Introducció de numeros enters
* @param mensaje Missatge que es dona a l'usuari
* @return un enter amb el valor
*/
public static double leerDouble(String mensaje) {
    double n=0.0;
    boolean aconseguit=false;
    while(!aconseguit){
        try{
            n= Double.parseDouble(leerTexto(mensaje));
            aconseguit=true;
        }
        catch(NumberFormatException ex){
            System.out.println("Deus posar un numero correcte");
        }
    }
    return n;
}

public final static void clearConsole()
{
    System.out.print("\033[H\033[2J");  
    System.out.flush(); 
}

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ad.ieseljust.galactictribesra3;

import com.ad.ieseljust.galactictribesra3.models.Planet;
import com.ad.ieseljust.galactictribesra3.models.Player;
import com.ad.ieseljust.galactictribesra3.models.Tribe;
import com.ad.ieseljust.galactictribesra3.utils.HibernateUtil;
import com.ad.ieseljust.galactictribesra3.utils.Utilitats;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author jasb
 */
public class GalacticTribesRA3 {

    public static void main(String[] args) {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        // get a Session and start a transaction
        laSesion.getTransaction().begin();

        System.out.println("Galactic Tribes running!");

        GalacticTribesRA3 app = new GalacticTribesRA3();

        app.start(laSesion);

        //close all
        laSesion.getTransaction().commit();
        laSesion.close();
    }


    private void start(Session laSesion) {
        int opcio;
        int select;

        do {

            System.out.println("\n\t\t- - - - GALACTIC TRIBES - - - - \n");
            System.out.println("\t\t 1. Mostrar tots els jugadors");
            System.out.println("\t\t 2. Mostrar noms de totes les tribus");
            System.out.println("\t\t 3. Mostrar quantitat de tribus per planeta.");
            System.out.println("\t\t 4. Mostrar l'arsenal d'armes d'una tribu.");
            System.out.println("\t\t 5. Eixir ");

            opcio = Utilitats.leerEnteroC("\n\tSelecciona una opció (1-5): ");

            switch (opcio) {
                case 1:
                    show_players(laSesion);

                    break;
                case 2:
                    show_tribes(laSesion);

                    break;
                case 3:
                    show_tribes_planeta(laSesion);

                    break;
                case 4:

                    Query<Object[]> q = laSesion.createQuery("select T.tribe_id, T.name from Tribe T", Object[].class);
                    System.out.println("\nTribus disponibles.");
                    List<Object[]> lesTribus = q.getResultList();

                    for (Object[] t : lesTribus) {
                        System.out.println("\t" + t[0] + " - " + t[1]);
                    }
                    int idTribu = Utilitats.leerEnteroC("\n\tEligeix el número de la tribu i es mostrarà el seu arsenal: ");


                    show_arsenal(idTribu, laSesion);

                    break;

                case 5:
                    break;
                default:
                    System.out.println("Opcio incorrecta.");
                    break;
            }

        } while (opcio != 5);

    }

    private void show_players(Session laSesion) {

        // TO-DO: Mostrarà tota la informació dels jugadors, i fent ús d'una @NamedQuery en la classe Player

        Query<Player> q = laSesion.createNamedQuery("Player.findAll", Player.class);
        List<Player> players = q.getResultList();

        for (Player p : players) {
            System.out.println(p.toString());
        }

    }

    private void show_tribes(Session laSesion) {

        // TO-DO: Mostrarà sols el nom de totes les tribus i fent ús d'una @NamedQuery en la classe Tribe

        Query<Tribe> q = laSesion.createNamedQuery("Tribe.findAll", Tribe.class);
        List<Tribe> tribes = q.getResultList();

        for (int i = 0; i < tribes.size(); i++) {
            System.out.println("Tribu " + (i + 1) + ": " + tribes.get(i).getName());
        }
    }

    private void show_tribes_planeta(Session laSesion) {

        // TO-DO: Mostrarà el nom de tots els planetes i el número de tribus que habiten en eixe planeta

        Query<Planet> q = laSesion.createNamedQuery("Planet.findAll", Planet.class);
        List<Planet> planets = q.getResultList();

        for (Planet p : planets){
            int num_tribes =  p.getTribes().size();
            System.out.println("En el planeta " + p.getName() + " habitan " + num_tribes + " tribu/s");
        }
    }

    private void show_arsenal(int id, Session laSesion) {

        // TO-DO: Mostrarà l'arsenal d'armes de la tribu passada per paràmetre

        // Obtener la tribu por su id
        Tribe tribe = laSesion.get(Tribe.class, id);
        System.out.println("Arsenal de la tribu " + tribe.getName() + " :");

        // Obtener los jugadores de la tribu (?1 para parámetros)
        Query<Player> q2 = laSesion.createQuery("select p from Player p where p.myTribe = ?1", Player.class);
        q2.setParameter(1, tribe);
        List<Player> players = q2.getResultList();

        for (Player p : players) {
            if (p.getWeapon() != null) {
                System.out.println("\tArma: " + p.getWeapon().getName() + " (" + p.getName() + ")");
            }
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ad.ieseljust.practica2;

import Utils.HibernateUtil;
import com.ad.ieseljust.practica2.entitats.Circuit;
import com.ad.ieseljust.practica2.entitats.Cotxe;
import com.ad.ieseljust.practica2.entitats.Equip;
import com.ad.ieseljust.practica2.entitats.Pilot;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jasb
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ValidacioW vld = new ValidacioW();
        //vld.setVisible(true);

        // Crear sessió de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // ==================== EXEMPLE 1 ====================
            System.out.println("=== CREANT EXEMPLE 1: Fernando Alonso ===");

            // 1. Crear un cotxe
            Cotxe cotxe1 = new Cotxe("Mercedes V6 Turbo Hybrid", "1000");

            // 2. Crear un equip
            Equip equip1 = new Equip("Aston Martin F1 Team", "Silverstone, UK", "Verd fosc");

            // 3. Crear circuits
            Circuit circuit1 = new Circuit("Circuit de Catalunya", "Espanya", "Montmeló", 4);
            Circuit circuit2 = new Circuit("Circuit de Mónaco", "Mónaco", "Monte Carlo", 3);

            // 4. Crear pilot amb les seues dades bàsiques
            Pilot pilot1 = new Pilot("Fernando", "Alonso", "Espanyola", 42);

            // 5. Establir relació un a un: Pilot - Cotxe
            pilot1.setCotxe(cotxe1);
            cotxe1.setPilot(pilot1);

            // 6. Establir relació molts a un: Pilot - Equip
            pilot1.setEquip(equip1);

            // 7. Establir relació molts a molts: Pilot - Circuits
            List<Circuit> circuitsPilot1 = new ArrayList<>();
            circuitsPilot1.add(circuit1);
            circuitsPilot1.add(circuit2);
            pilot1.setCircuits(circuitsPilot1);

            // 8. Guardar totes les entitats (l'ordre és important)
            session.persist(circuit1); // Circuits primer (no tenen dependències)
            session.persist(circuit2);
            session.persist(equip1);   // Equip després
            session.persist(pilot1);   // Pilot al final (té totes les relacions, cascade guardarà el cotxe)

            System.out.println("✓ Pilot 1 creat: " + pilot1.getNom() + " " + pilot1.getCognom());
            System.out.println("  - Cotxe: " + cotxe1.getMotor());
            System.out.println("  - Equip: " + equip1.getNom());
            System.out.println("  - Circuits: " + circuitsPilot1.size());

            // ==================== EXEMPLE 2 ====================
            System.out.println("\n=== CREANT EXEMPLE 2: Lewis Hamilton ===");

            // 1. Crear un altre cotxe
            Cotxe cotxe2 = new Cotxe("Ferrari V6 Turbo", "950");

            // 2. Crear un altre equip
            Equip equip2 = new Equip("Scuderia Ferrari", "Maranello, Itàlia", "Roig");

            // 3. Crear més circuits
            Circuit circuit3 = new Circuit("Silverstone Circuit", "Regne Unit", "Silverstone", 5);
            Circuit circuit4 = new Circuit("Autodromo Nazionale Monza", "Itàlia", "Monza", 5);

            // 4. Crear segon pilot
            Pilot pilot2 = new Pilot("Lewis", "Hamilton", "Britànica", 39);

            // 5. Establir relació un a un: Pilot - Cotxe
            pilot2.setCotxe(cotxe2);
            cotxe2.setPilot(pilot2);

            // 6. Establir relació molts a un: Pilot - Equip
            pilot2.setEquip(equip2);

            // 7. Establir relació molts a molts: Pilot - Circuits
            // Aquest pilot ha corregut en circuits diferents + alguns comuns
            List<Circuit> circuitsPilot2 = new ArrayList<>();
            circuitsPilot2.add(circuit1); // Circuit comú amb pilot1
            circuitsPilot2.add(circuit3);
            circuitsPilot2.add(circuit4);
            pilot2.setCircuits(circuitsPilot2);

            // 8. Guardar entitats noves
            session.persist(circuit3);
            session.persist(circuit4);
            session.persist(equip2);
            session.persist(pilot2);

            System.out.println("✓ Pilot 2 creat: " + pilot2.getNom() + " " + pilot2.getCognom());
            System.out.println("  - Cotxe: " + cotxe2.getMotor());
            System.out.println("  - Equip: " + equip2.getNom());
            System.out.println("  - Circuits: " + circuitsPilot2.size());

            // Confirmar transacció
            transaction.commit();

            System.out.println("\n=== DADES INSERTADES CORRECTAMENT ===");
            System.out.println("Total pilots creats: 2");
            System.out.println("Total cotxes creats: 2");
            System.out.println("Total equips creats: 2");
            System.out.println("Total circuits creats: 4");
            System.out.println("Relacions pilot-circuit creades: " +
                (circuitsPilot1.size() + circuitsPilot2.size()));

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error en la inserció de dades: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}

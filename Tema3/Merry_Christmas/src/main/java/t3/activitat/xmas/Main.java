package t3.activitat.xmas;

import org.hibernate.Session;
import org.hibernate.Transaction;
import t3.activitat.xmas.Utils.HibernateUtil;
import t3.activitat.xmas.entities.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear sessió de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        // ----------------------------------------------------------------------------------
        // Les consultes del main les he fet amb ChatGPT per a probar que funcionara tot bé,
        // no me donaba temps a probarles per mi mateixa per a saber que estiguera tot bé,
        // El deixe mes que res per si servira de algo
        // ----------------------------------------------------------------------------------

        try {
            transaction = session.beginTransaction();
            // Consultas HQL
            System.out.println("\n=== CONSULTAS HQL ===\n");

            // 1. Obtener todos los MagicKing
            List<MagicKing> kings = session.createQuery("FROM MagicKing", MagicKing.class).list();
            System.out.println("=== Reyes Magos ===");
            for (MagicKing king : kings) {
                System.out.println("- " + king.getName() + " (Camello: " + king.getCamel().getNom() + ")");
            }

            // 2. Obtener todos los Children
            List<Child> children = session.createQuery("FROM Child", Child.class).list();
            System.out.println("\n=== Niños ===");
            for (Child child : children) {
                System.out.println("- " + child.getName() + " (" + child.getAddress() + ") - Bueno: " + child.getIsNice() + " - Rey: " + child.getMyKing().getName());
            }

            // 3. Obtener todos los Camellos
            List<Camel> camels = session.createQuery("FROM Camel", Camel.class).list();
            System.out.println("\n=== Camellos ===");
            for (Camel camel : camels) {
                String kingName = camel.getMagicKing() != null ? camel.getMagicKing().getName() : "Sin asignar";
                System.out.println("- " + camel.getNom() + " (Pertenece a: " + kingName + ")");
            }

            // 4. Obtener todos los Emisarios
            List<Emissary> emissaries = session.createQuery("FROM Emissary", Emissary.class).list();
            System.out.println("\n=== Emisarios ===");
            for (Emissary emissary : emissaries) {
                System.out.println("- " + emissary.getName() + " (Trabaja para: " + emissary.getMagicKing().getName() + ")");
            }

            // 5. Obtener todos los Pajes
            List<Paje> pajes = session.createQuery("FROM Paje", Paje.class).list();
            System.out.println("\n=== Pajes ===");
            for (Paje paje : pajes) {
                System.out.println("- " + paje.getName());
            }

            // 6. Obtener todos los Regalos
            List<Gift> gifts = session.createQuery("FROM Gift", Gift.class).list();
            System.out.println("\n=== Regalos ===");
            for (Gift gift : gifts) {
                System.out.println("- " + gift.getName() +
                                 " | Para: " + gift.getChild().getName() +
                                 " | Empaquetado: " + gift.getIsPackaged() +
                                 " | Enviado: " + gift.getIsSent() +
                                 " | Pajes: " + gift.getPajes().size() +
                                 " | Emisarios: " + gift.getEmissaries().size());
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error en la inserció de dades: " + e.getMessage());
            e.printStackTrace();
        }finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }

    }
}
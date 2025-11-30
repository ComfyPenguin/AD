/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad.ieseljust.practica2;

import Utils.HibernateUtil;
import Utils.Utilitats;

import com.ad.ieseljust.practica2.entitats.Equip;
import com.ad.ieseljust.practica2.entitats.Pilot;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

@Getter
public class ConnexioBD {

    Connection laConnexio = null;

    public void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            this.laConnexio = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Connection to SQL has been established.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
    }

    public void connectMySQL(String nomBD) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionUrl = "jdbc:mysql://localhost:3308/" + nomBD + "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
            this.laConnexio = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos MySQL" + ex.getMessage());
        }
    }

    public void disConnect() {

        // TO DO
        if (laConnexio != null) {
            try {
                laConnexio.close();
                System.out.println("Desconectat!!");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". Status: " + ex.getSQLState());
            }
        }
    }

    public boolean validaUser(String user) {

        boolean trobat = false;

        // The query
        String SQL = "Select * from users where username = ?";
        // The statement
        PreparedStatement pst;
        try {
            pst = this.laConnexio.prepareStatement(SQL);

            // fill placeholders
            pst.setString(1, user);

            // The execution
            ResultSet rst = pst.executeQuery();

            if (rst.next()) {
                trobat = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trobat;

    }

    public int validaPass(String user, String pass) {
        int res = -1;

        if (!this.validaUser(user)) {
            return 1;
        } else {
            // The query
            String SQL = "Select * from users where username = ? and password = ?";

            // The statement
            PreparedStatement pst;
            try {
                pst = this.laConnexio.prepareStatement(SQL);

                // fill placeholders
                pst.setString(1, user);
                pst.setString(2, pass);

                // The execution
                ResultSet rst = pst.executeQuery();

                if (rst.next()) {
                    res = 0;
                } else {
                    res = 2;
                }

            } catch (SQLException ex) {
                Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return res;

    }

    public int insertUser(String user, String pass) {
        int res = -1;

        // The statement
        Statement st;
        try {
            String SQL = "INSERT INTO users VALUES ('" + user + "', '" + pass + "')";
            System.out.println(SQL);

            st = this.laConnexio.createStatement();

            res = st.executeUpdate(SQL);

            System.out.println("L'usuari " + user + " s'ha afegit a la BBDD amb la password xifrada: " + pass);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: No s'ha pogut insertar el nou registre.",
                    "Instituto APP", JOptionPane.ERROR_MESSAGE);
        }

        return res;

    }

    public void executarScript(String filename) {

        BufferedReader bfr = null;

        try {

            File script = new File("sql/" + filename);
            bfr = new BufferedReader(new FileReader(script));
            String line = null;
            StringBuilder sb = new StringBuilder();
            // Obtenemos el salto de linea del sistema subyacente
            String breakLine = System.getProperty("line.separator");
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
                sb.append(breakLine);
            }
            String query = sb.toString();   // generemos el Script en un String
            Statement stm = this.laConnexio.createStatement();
            int result = stm.executeUpdate(query);

            JOptionPane.showMessageDialog(null,
                    "La Base de dades ha sigut creada.",
                    "Instituto APP", JOptionPane.INFORMATION_MESSAGE);
            bfr.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: No s'ha pogut executar l'script.",
                    "Instituto APP", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void consultaByPais() {
        try{

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "ERROR: " + ex.getMessage(),
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void consultaByEdat() {

        try {
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: " + ex.getMessage(),
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void consultaByEquip() {
       try {
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: " + ex.getMessage(),
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertar() {

        try {
            String nom = Utilitats.leerTextoG("Dis-me el nom: ");
            String equip = Utilitats.leerTextoG("Dis-me l'equip: ");
            String pais = Utilitats.leerTextoG("Dis-me el pais: ");
            int edat = Utilitats.leerEnteroG("Dis-me l'edat: ");
            int victories = Utilitats.leerEnteroG("Dis-me les victories: ");

            // Iniciar la sesion de Hibernate
            Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
            // Iniciar una transacción
            Transaction tr = laSesion.beginTransaction();

            // Equip e = new Equip(equip);
            // Creación del objeto a persistir
            // Pilot p = new Pilot(nom, new Equip(equip), pais, edat, victories);

            // Persistir (Que Hibernate lo siga) el objeto
            // laSesion.persist(p);
            // Confirmar los cambios realizados
            tr.commit();

            JOptionPane.showMessageDialog(null,
                    "Registre insertat correctament.",
                    "Formula1 APP", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: No s'ha pogut insertar el registre.",
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void importar() {

        try {
            String filename = Utilitats.leerTextoG("Digues el nom de l'arxiu CSV amb les dades a incorporar a la base de dades: ");

            // Leer todas las lineas del csv
            List<String> linies = Files.readAllLines(Paths.get(filename));

            // Descarta la primera linea
            linies = linies.subList(1, linies.size() - 1);

            // Iniciar la sesion de Hibernate
            Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
            // Iniciar una transacción
            Transaction tr = laSesion.beginTransaction();

            // Bucle para insertar línea tras línea los datos
            int insertedRows = 0;
            for (String linea : linies) {

                // Extraer campos separados por comas
                String[] camps = linea.split(",");

                // Lectura de atributos por campos
                String nom = camps[0];
                String equip = camps[1];
                String pais = camps[2];
                int edat = Integer.parseInt(camps[3]);
                int victories = Integer.parseInt(camps[4]);



                // Creación del objeto a persistir
                // Pilot p = new Pilot(nom, new Equip(equip), pais, edat, victories);

                // Persistir (Que Hibernate lo siga) el objeto
                // laSesion.persist(p);


                insertedRows++;
            }

            // Confirmar los cambios realizados
            tr.commit();

            JOptionPane.showMessageDialog(null,
                    "Les dades han sigut importades a la base de dades.",
                    "Formula1 APP", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("\n");
            System.out.println("Se han insertat " + insertedRows + " camps");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: No s'ha pogut importar el CSV.",
                    "Instituto APP", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eliminar() {

        try {
            ArrayList<String> pilots = new ArrayList<>();
            // The query
            String SQL = "Select * from pilotos";
            // The statement
            Statement st;

            st = this.laConnexio.createStatement();

            // The execution
            ResultSet rst = st.executeQuery(SQL);

            while (rst.next()) {
                pilots.add(rst.getString(1) + " - " + rst.getString("nombre") + " , " + rst.getString("equipo") + " , " + rst.getString("pais") + " , " + rst.getString("edad"));
            }

            // Convertir el ArrayList a un array
            String[] opcions = pilots.toArray(new String[0]);

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona un piloto:",
                    "Llista de pilots",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcions, // Aquí pasamos el array convertido
                    opcions[0] // Opción por defecto
            );

            // Mostrar el resultado seleccionado
            if (seleccion != null) {
                String[] camps = seleccion.split("-");

                // The query
                String SQLD = "Delete from pilotos where idPiloto = ?";

                // The statement
                PreparedStatement pst;
                try {
                    pst = this.laConnexio.prepareStatement(SQLD);

                    // fill placeholders
                    pst.setInt(1, Integer.parseInt(camps[0].trim()));

                    // show the query after resolve placeholders
                    System.out.println(pst);

                    // The execution
                    int deletedtedRows = pst.executeUpdate();

                    // how many roas affecte
                    JOptionPane.showMessageDialog(null,
                            "Registre eliminat correctament.",
                            "Formula1 APP", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                    "No s'ha pogut eliminar el registre (" + ex.getMessage() + ")",
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
                }

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: " + ex.getMessage(),
                    "Instituto APP", JOptionPane.ERROR_MESSAGE);
        }
    }

    // TODO
    public void modificar(int opcio) {

        ArrayList<String> persones = new ArrayList<>();
        // The query
        String SQL = "Select * from pilotos";
        // The statement
        Statement st;

        try {
            st = this.laConnexio.createStatement();

            // The execution
            ResultSet rst = st.executeQuery(SQL);

            while (rst.next()) {
                persones.add(rst.getString(1) + " - " + rst.getString("nombre") + " , " + rst.getString("equipo") + " , " + rst.getString("pais") + " , " + rst.getString("edad"));
            }

            // Convertir el ArrayList a un array
            String[] opcions = persones.toArray(new String[0]);

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona un pilot:",
                    "Llista de pilots",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcions, // Aquí pasamos el array convertido
                    opcions[0] // Opción por defecto
            );

            if (seleccion != null) {

                String[] camps = seleccion.split("-");
                String SQLUpd;
                PreparedStatement pst;

                switch (opcio) {
                    case 1:
                        String nom = Utilitats.leerTextoG("Dis-me el nou nom: ");
                        // The query
                        SQLUpd = "UPDATE pilotos SET nombre = ? where idPiloto = ?";
                        pst = this.laConnexio.prepareStatement(SQLUpd);
                        // fill placeholders            
                        pst.setString(1, nom);
                        pst.setInt(2, Integer.parseInt(camps[0].trim()));
                        break;
                    case 2:
                        String equip = Utilitats.leerTextoG("Dis-me el nou equip: ");
                        SQLUpd = "UPDATE pilotos SET equipo = ? where idPiloto = ?";
                        pst = this.laConnexio.prepareStatement(SQLUpd);
                        // fill placeholders            
                        pst.setString(1, equip);
                        pst.setInt(2, Integer.parseInt(camps[0].trim()));
                        break;
                    case 3:
                        int edat = Utilitats.leerEnteroG("Dis-me la nova edat: ");
                        SQLUpd = "UPDATE pilotos SET edad = ? where idPiloto = ?";
                        pst = this.laConnexio.prepareStatement(SQLUpd);
                        // fill placeholders            
                        pst.setInt(1, edat);
                        pst.setInt(2, Integer.parseInt(camps[0].trim()));
                        break;
                    default:
                        throw new Exception("Opcio incorrecta");
                }
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "Registre actualitzat correctament.",
                        "Formula1 APP", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "No s'ha pogut actualitzar el registre.",
                    "Formula1 APP", JOptionPane.ERROR_MESSAGE);
        }

    }
}

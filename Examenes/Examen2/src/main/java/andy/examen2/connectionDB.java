package andy.examen2;

import lombok.Data;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public class connectionDB {
    Connection connection = null;

    public void connectMySQL(String nameDB) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionUrl = "jdbc:mysql://localhost:3308/" + nameDB + "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
            this.connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos MySQL: " + ex.getMessage());
        }
    }

    public void executarScript(String filename) {

        BufferedReader bfr = null;

        try {

            File script = new File(filename);
            bfr = new BufferedReader(new FileReader(script));
            String line = null;
            StringBuilder sb = new StringBuilder();
            // Obtenemos el salto de linea del sistema subyacente
            String breakLine = System.lineSeparator();
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
                sb.append(breakLine);
            }
            String query = sb.toString();   // generemos el Script en un String
            Statement stm = this.connection.createStatement();
            int result = stm.executeUpdate(query);

            JOptionPane.showMessageDialog(null,
                    "La Base de dades ha sido creada.",
                    "Marathonians APP", JOptionPane.INFORMATION_MESSAGE);
            bfr.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "ERROR: No se ha podido ejecutar el script",
                    "Marathonians APP", JOptionPane.ERROR_MESSAGE);
        }

    }
}
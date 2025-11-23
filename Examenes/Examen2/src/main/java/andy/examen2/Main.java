package andy.examen2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        try {
            // Leer el archivo JSON
            String contenido = new String(Files.readAllBytes(Paths.get("BigMarathonians.json")));
            JSONObject jsonObject = new JSONObject(contenido);
            JSONArray runners = jsonObject.getJSONArray("runners");

            ArrayList<Runner> marathonians = new ArrayList<>();

            // Parsear los personajes del JSON
            for (int i = 0; i < runners.length(); i++) {
                JSONObject runner = runners.getJSONObject(i);

                String name = runner.getString("name");
                int age = runner.getInt("age");
                int marathons = runner.getJSONArray("marathon_names").length();
                String nationality = runner.getString("nationality");
                String notable_fact = runner.getString("notable_fact");

                JSONObject runnerJSON = new JSONObject();

                runnerJSON.put("name", name);
                runnerJSON.put("age", age);
                runnerJSON.put("marathons",  marathons);
                runnerJSON.put("nationality",  nationality);
                runnerJSON.put("notable_fact",  notable_fact);

                marathonians.add(new Runner(runnerJSON));
            }


            connectionDB conn = new connectionDB();
            conn.connectMySQL("");
            conn.executarScript("BigMarathonians.sql");

            int numRegistros = 0;
            PreparedStatement pst;
            for (Runner marathonian : marathonians) {
                String SQL = "INSERT INTO Runner (nom, edat, num_marathons, pais, logro) VALUES (? , ? , ? , ? , ?);";

                pst = conn.getConnection().prepareStatement(SQL);

                pst.setString(1, marathonian.getName());
                pst.setInt(2, marathonian.getAge());
                pst.setInt(3, marathonian.getMarathons());
                pst.setString(4, marathonian.getNationality());
                pst.setString(5, marathonian.getNotable_fact());

                numRegistros++;

                pst.executeUpdate();
            }
            System.out.println("Se han registrado " + numRegistros + " en total");


        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}